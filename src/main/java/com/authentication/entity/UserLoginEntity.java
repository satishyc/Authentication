package com.authentication.entity;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class UserLoginEntity {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


}
