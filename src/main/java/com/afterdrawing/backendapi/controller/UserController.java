package com.afterdrawing.backendapi.controller;

import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.service.UserService;
import com.afterdrawing.backendapi.resource.SaveUserResource;
import com.afterdrawing.backendapi.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin()
public class UserController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get Users", description = "Get All Users by Pages", tags = { "users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users")
    public Page<UserResource> getAllUsers() {
        Pageable pageable = PageRequest.of(0, 100000);
        Page<User> usersPage = userService.getAllUsers(pageable);
        List<UserResource> resources = usersPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }

/*
    @Operation(summary = "Get User by Id", description = "Get a User by specifying Id", tags = { "users" })
    @GetMapping("/users/{userId}")
    public UserResource getUserById(
            @Parameter(description="User Id")
            @PathVariable(name = "userId") Long userId) {
        return convertToResource(userService.getUserById(userId));
    }
*/
    @Operation(summary = "Get User by email", description = "Get a User by specifying email", tags = { "users" })
    @GetMapping("/users/{email}")
    public UserResource getUserByEmail(
            @Parameter(description="email")
            @PathVariable(name = "email") String email) {
        return convertToResource(userService.getUserByEmail(email));
    }

    //@Operation()
    @Operation(summary = "Create User (DON'T USE ,USE SIGN-UP FROM AUTHENTICATION-CONTROLLER)", description = "Create a User", tags = { "users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Created and returned", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users")
    public UserResource createUser( @RequestBody SaveUserResource resource)  {
        User user = convertToEntity(resource);
        return convertToResource(userService.saveUser(user));
    }

    @Operation(summary = "Update User ", description = "Update a User", tags = { "users" })
    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable(name = "userId") Long userId, @Valid @RequestBody SaveUserResource resource) {
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId, user));
    }

    @Operation( summary = "Delete User ", description = "Delete a User", tags = { "users" })
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") Long userId) {
        return userService.deleteUser(userId);
    }
    // Auto Mapper
    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
