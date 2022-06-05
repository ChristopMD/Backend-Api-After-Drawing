package com.afterdrawing.backendapi.resource.authentication;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RefreshTokenResource {
    @NotBlank
    private String refreshToken;

    private String email;

}