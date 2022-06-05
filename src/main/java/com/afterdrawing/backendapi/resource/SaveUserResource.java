package com.afterdrawing.backendapi.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Data
public class SaveUserResource {
    @Size(max = 25)
    @Column(name = "userName", nullable = false, unique = true)
    private String userName;

    @Size(max = 25)
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Size(max = 25)
    @Column(name = "lastName", nullable = false)
    private String lastName;
/*
    @Size(max = 50)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Size(max = 25)
    @Lob
    @Column(name = "password", nullable = false)
    private String password;
*/
}
