package com.afterdrawing.backendapi.controller;

import com.afterdrawing.backendapi.core.entity.Element;
import com.afterdrawing.backendapi.core.entity.Interface;
import com.afterdrawing.backendapi.core.service.ElementService;
import com.afterdrawing.backendapi.core.service.InterfaceService;
import com.afterdrawing.backendapi.core.service.ProjectService;
import com.afterdrawing.backendapi.core.service.UserService;
import com.afterdrawing.backendapi.resource.ElementResource;
import com.afterdrawing.backendapi.resource.InterfaceResource;
import com.afterdrawing.backendapi.resource.SaveElementResource;
import com.afterdrawing.backendapi.resource.SaveInterfaceResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class ElementController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    public UserService userService;

    @Autowired
    public ProjectService projectService;

    @Autowired
    public InterfaceService interfaceService;
    @Autowired
    public ElementService elementService;
    @Operation(summary = "Create a Element", description = "Created a Element by  UserId, ProjectId and InterfaceId", tags = { "elements" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Element created", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users/{userId}/projects/{projectId}/interfaces/{interfaceId}/elements")
    public ElementResource createElement(@PathVariable(name = "userId") Long userId,
                                             @PathVariable(name = "projectId") Long projectId,
                                           @PathVariable(name = "interfaceId") Long interfaceId,
                                             @Valid @RequestBody SaveElementResource resource){
        return convertToResourceElement(elementService.saveElement( convertToEntityElement(resource), userId,projectId,interfaceId));
    }
    @Operation(summary = "Get a Elements", description = "Get a Elements by   ProjectId", tags = { "elements" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All elements by ProjectId Returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/projects/{projectId}/elements")
    public Page<ElementResource> getAllElementsByProjectId(
            @PathVariable(name = "projectId") Long projectId,
            Pageable pageable) {
        Page<Element> elementPage = elementService.getAllElementsByProjectId(projectId, pageable);
        List<ElementResource> resources = elementPage.getContent().stream().map(this::convertToResourceElement).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get a Elements", description = "Get a Elements by   UserId", tags = { "elements" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All elements by UserId Returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/elements")
    public Page<ElementResource> getAllElementsByUserId(
            @PathVariable(name = "userId") Long userId,
            Pageable pageable) {
        Page<Element> elementPage = elementService.getAllElementsByUserId(userId, pageable);
        List<ElementResource> resources = elementPage.getContent().stream().map(this::convertToResourceElement).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get a Elements", description = "Get a Elements by   InterfaceId", tags = { "elements" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All elements by InterfaceId Returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/interfaces/{interfaceId}/elements")
    public Page<ElementResource> getAllElementsByInterfaceId(
            @PathVariable(name = "interfaceId") Long interfaceId,
            Pageable pageable) {
        Page<Element> elementPage = elementService.getAllElementsByInterfaceId(interfaceId, pageable);
        List<ElementResource> resources = elementPage.getContent().stream().map(this::convertToResourceElement).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get a Elements", description = "Get a Elements in Pages", tags = { "elements" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All elements  Returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/elements/{elementId}")
    public ElementResource getElementById(
            @PathVariable(name = "elementId") Long elementId) {
        return convertToResourceElement(elementService.getElementById(elementId));
    }
    @Operation(summary = "Update a Element", description = "Update a Element by  ElementId", tags = { "elements" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Element Updated", content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/elements/{elementId}")
    public ElementResource updateElement(
            @PathVariable(name = "elementId") Long elementId,
            @Valid @RequestBody SaveElementResource resource) {
        return convertToResourceElement(elementService.updateElement(elementId, convertToEntityElement(resource)));
    }
    @Operation(summary = "Delete a Element", description = "Delete a Element by  ElementId", tags = { "elements" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A Element deleted", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/elements/{elementId}")
    public ResponseEntity<?> deleteElement(
            @PathVariable(name = "elementId") Long elementId) {
        return elementService.deleteElement(elementId);
    }
    private Element convertToEntityElement(SaveElementResource resource){
        return mapper.map(resource, Element.class);
    }
    private ElementResource convertToResourceElement(Element entity){
        return mapper.map(entity, ElementResource.class);
    }
}
