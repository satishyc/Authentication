package com.authentication.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserLoginEntity {
    @NotEmpty(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;
    @NotEmpty(message = "Password cannot be blank")
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


}
