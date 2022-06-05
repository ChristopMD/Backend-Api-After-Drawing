package com.afterdrawing.backendapi.controller;

import com.afterdrawing.backendapi.core.entity.Interface;
import com.afterdrawing.backendapi.core.entity.Project;
import com.afterdrawing.backendapi.core.service.InterfaceService;
import com.afterdrawing.backendapi.core.service.ProjectService;
import com.afterdrawing.backendapi.core.service.UserService;
import com.afterdrawing.backendapi.resource.InterfaceResource;
import com.afterdrawing.backendapi.resource.SaveInterfaceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class InterfaceController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    public UserService userService;

    @Autowired
    public ProjectService projectService;

    @Autowired
    public InterfaceService interfaceService;

    @Operation(summary = "Post Interfaces", description = "Post Interfaces", tags = { "interfaces" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interface created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users/{userId}/projects/{projectId}/wireframes/{wireframeId}/interfaces")
    public InterfaceResource createInterface( @PathVariable(name = "userId") Long userId,
                                              @PathVariable(name = "projectId") Long projectId,
                                              @PathVariable(name = "wireframeId") Long wireframeId,
                                              @Valid @RequestBody SaveInterfaceResource resource){
        return convertToResourceInterface(interfaceService.saveInterface( convertToEntityInterface(resource), userId, projectId, wireframeId ));
    }
    @Operation(summary = "Get Interface by Project Id", description = "Get All Interfaces by Project Id", tags = { "interfaces" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Interfaces returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/projects/{projectId}/interfaces")
    public Page<InterfaceResource> getAllInterfacesByProjectId(
            @PathVariable(name = "projectId") Long projectId) {
        Pageable pageable = PageRequest.of(0,5000);
        Page<Interface> interfacePage = interfaceService.getAllInterfacesByProjectId(projectId, pageable);
        List<InterfaceResource> resources = interfacePage.getContent().stream().map(this::convertToResourceInterface).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get Interfaces by Project Id", description = "Get Interfaces Project Id", tags = { "interfaces" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Interfaces returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/interfaces")
    public Page<InterfaceResource> getAllInterfacesByUserId(
            @PathVariable(name = "userId") Long userId) {
        Pageable pageable = PageRequest.of(0,5000);

        Page<Interface> interfacePage = interfaceService.getAllInterfacesByUserId(userId, pageable);
        List<InterfaceResource> resources = interfacePage.getContent().stream().map(this::convertToResourceInterface).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Interface by Id", description = "Get Interface by Id", tags = { "interfaces" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interface returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/interfaces/{interfaceId}")
    public InterfaceResource getInterfaceById(
            @PathVariable(name = "interfaceId") Long interfaceId) {
        return convertToResourceInterface(interfaceService.getInterfaceById(interfaceId));
    }
    @Operation(summary = "Update Interface Name", description = "Update Interfaces Name", tags = { "interfaces" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interface updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/interfaces/{interfaceId}")
    public InterfaceResource updateInterface(
            @PathVariable(name = "interfaceId") Long interfaceId,
            @Valid @RequestBody SaveInterfaceResource resource) {
        return convertToResourceInterface(interfaceService.updateInterface(interfaceId, convertToEntityInterface(resource)));
    }
    @Operation(summary = "Delete Interfaces", description = "Delete Interfaces", tags = { "interfaces" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interface deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/interfaces/{interfaceId}")
    public ResponseEntity<?> deleteInterface(
            @PathVariable(name = "interfaceId") Long interfaceId) {
        return interfaceService.deleteInterface(interfaceId);
    }
    private Interface convertToEntityInterface(SaveInterfaceResource resource){
        return mapper.map(resource, Interface.class);
    }
    private InterfaceResource convertToResourceInterface(Interface entity){
        return mapper.map(entity, InterfaceResource.class);
    }

}
