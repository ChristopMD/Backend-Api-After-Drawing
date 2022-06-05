package com.afterdrawing.backendapi.resource.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;


@Data
@AllArgsConstructor
public class AuthenticationResource {
    private String authenticationToken;
   // private Instant expiresAt;
    private Long id;
    private String email;
    private String userName;
    //private Boolean using2fa;
}
