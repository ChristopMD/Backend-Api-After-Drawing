package com.afterdrawing.backendapi.resource.authentication;


import lombok.Data;

@Data
public class ChangePasswordResource {
    private String oldPassword;
    private String newPassword;

}
