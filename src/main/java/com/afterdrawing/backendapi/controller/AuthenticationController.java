package com.afterdrawing.backendapi.controller;

import com.afterdrawing.backendapi.resource.authentication.*;
import com.afterdrawing.backendapi.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/authentication")
@CrossOrigin()
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Create a user with encoded password (RECOMMENDED)", description = "Create a user with registered encoded paswword in the database")
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpResource registrationRequest){
        return authenticationService.signUp(registrationRequest);
        //return new ResponseEntity<>("User registration was successfull", HttpStatus.OK);
    }

    @Operation(summary = "Login", description = "login with valid user credentials")
    @PostMapping("/sign-in")
    public AuthenticationResource signIn(@RequestBody SignInResource loginRequest)  {
        return authenticationService.signIn(loginRequest);
    }

    /*@PostMapping("/refresh-token")
    public AuthenticationResource refreshToken(@Valid @RequestBody RefreshTokenResource refreshTokenRequest, Principal principal){
        String email = principal.getName();
        refreshTokenRequest.setEmail(email);
        return authenticationService.refreshToken(refreshTokenRequest);
    }*/

    /*@PostMapping("/sign-out")
    public ResponseEntity<String> signOut(@Valid @RequestBody RefreshTokenResource refreshTokenRequest){
        authenticationService.signOut(refreshTokenRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Refresh token has been deleted");
    }*/

    /*@GetMapping("/verification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable(name = "token") String token){
        authenticationService.verifyAccount(token);
        return new ResponseEntity<>("User account has been activated", HttpStatus.OK);
    }*/

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordResource forgotPasswordRequest){
        return authenticationService.forgotPassword(forgotPasswordRequest);
        //return new ResponseEntity<>("Petition sent", HttpStatus.OK);
    }

    /*@PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordResource resetPasswordRequest){
        authenticationService.resetPassword(resetPasswordRequest);
        return new ResponseEntity<>("Password changed", HttpStatus.OK);
    }*/

    @Operation(summary = "Change user's password ", description = "Change current user´s password for other given password, ")
    @PostMapping("/change-password/{userId}")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordResource changePasswordRequest, @PathVariable(name = "userId") Long userId){
        //String username = principal.getName();
        return authenticationService.changePassword(userId, changePasswordRequest);
        //return new ResponseEntity<>("Password changed", HttpStatus.OK);
    }

}
