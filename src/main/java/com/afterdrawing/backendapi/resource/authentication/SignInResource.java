package com.afterdrawing.backendapi.resource.authentication;

import lombok.Data;


@Data
public class SignInResource {
    private String email;
    private String password;
}