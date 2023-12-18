package com.authentication.controllers;

import com.authentication.entity.UserEntity;
import com.authentication.entity.UserLoginEntity;
import com.authentication.service.UserEntityValidation;
import com.authentication.service.UserLoginEntityValidation;
import com.authentication.service.UserRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserRegistrationService registrationService;
    @Autowired
    private UserLoginEntityValidation validation;
    @PostMapping("/login")
    public ResponseEntity<String> handlePostRequest(@RequestBody String requestBody) {
        UserLoginEntity userLoginEntity = validation.validateLoginDetails(requestBody);
        registrationService.validateUserLogin(userLoginEntity);

        return new ResponseEntity<>("User Logged in Successful", HttpStatus.OK);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An error occurred: ",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
