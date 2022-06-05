package com.afterdrawing.backendapi.resource.authentication;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SignUpResource {
    private String email;

    private String userName;
    private String password;

    private String firstName;

    private String lastName;
}