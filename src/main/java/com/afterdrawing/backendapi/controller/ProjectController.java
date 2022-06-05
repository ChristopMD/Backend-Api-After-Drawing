package com.afterdrawing.backendapi.controller;

import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.service.ProjectService;
import com.afterdrawing.backendapi.core.service.UserService;
import com.afterdrawing.backendapi.resource.ProjectResource;
import com.afterdrawing.backendapi.resource.SaveProjectResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class ProjectController {
    @Autowired
    public UserService userService;
    @Autowired
    public ProjectService projectService;

    @Autowired
    private ModelMapper mapper;
    @Operation(summary = "Get Projects By UserId", description = "Get All Projects by Pages Using a UserId", tags = { "projects" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Projects returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/projects")
    public Page<ProjectResource> getAllProjectssByUserId(@PathVariable(name = "userId") Long userId) {
        Pageable pageable = PageRequest.of(0,1000);
        List<ProjectResource> projects = projectService.getAllProjectsByUserId(userId,pageable)
                .getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        int count = projects.size();
        return new PageImpl<>(projects, pageable, count);
    }
    @Operation(summary = "Get a Project By ProjectId", description = "Get Specific Project  Using a ProjectId", tags = { "projects" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Project Returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/projects/{projectId}")
    public ProjectResource getPostById(
            @PathVariable(name = "projectId") Long projectId) {
        return convertToResource(projectService.getProjectById(projectId));
    }
    @Operation(summary = "Get a Project By ProjectId And UserId", description = "Get Specific Project  Using a ProjectId and UserId", tags = { "projects" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Project Returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/projects/{projectId}")
    public ProjectResource getProjectByIdAndUserId(@PathVariable(name = "userId") Long userId,
                                                   @PathVariable(name = "projectId") Long projectId) {
        return convertToResource(projectService.getProjectByUserIdAndProjectId(userId, projectId));
    }
    @Operation(summary = "Create a Project By a ProjectRequest And UserId", description = "Create a Project  Using a ProjectRequest and UserId", tags = { "projects" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Project Created and Returned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users/{userId}/projects")
    public ProjectResource createProject(@PathVariable(name = "userId") Long userId,
                                         @Valid @RequestBody SaveProjectResource resource) {
        //SaveProjectResource resourcedos = new SaveProjectResource();
        //resourcedos.setTitle("hola");
        return convertToResource(projectService.saveProject( convertToEntity(resource),userId));

    }
    @Operation(summary = "Update a Project By a ProjectRequest, UserId and ProjectId", description = "Update a Project  Using a ProjectRequest, UserId and ProjectId", tags = { "projects" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Project update and Returned", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/users/{userId}/projects/{projectId}")
    public ProjectResource updateProject(@PathVariable(name = "userId") Long userId,
                                         @PathVariable(name = "projectId") Long projectId,
                                         @Valid @RequestBody SaveProjectResource resource) {
        return convertToResource(projectService.updateProject(projectId, convertToEntity(resource), userId));
    }
    @Operation(summary = "Delete a Project By UserId and ProjectId", description = "Delete a Project  Using UserId and ProjectId", tags = { "projects" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Project was Deleted and Returned response ok", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/users/{userId}/projects/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable(name = "userId") Long userId,
                                           @PathVariable(name = "projectId") Long projectId) {
        return projectService.deleteProject(projectId, userId);
    }
    @Operation(summary = "Get Projects ", description = "Get All Projects ", tags = { "projects" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Projects returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/projects")
    public Page<ProjectResource> getAllProjects() {
        Pageable pageable = PageRequest.of(0,10000, Sort.by("userId"));
        Page<Project> projects = projectService.getAllProjects(pageable);
        List<ProjectResource> resources = projects.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<ProjectResource>(resources,pageable , resources.size());
    }

    private Project convertToEntity(SaveProjectResource resource) {
        return mapper.map(resource, Project.class);
    }

    private ProjectResource convertToResource(Project entity) {
        return mapper.map(entity, ProjectResource.class);
    }



}
