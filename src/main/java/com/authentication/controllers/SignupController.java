package com.authentication.controllers;

import com.authentication.entity.SignupEntity;
import com.authentication.service.SignupEntityValidation;
import com.authentication.service.UserRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class SignupController {


    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);
    @Autowired
    private  SignupEntityValidation signupEntityValidation;
    @Autowired
    private  UserRegistrationService registrationService;


    @PostMapping("/signup")
    public ResponseEntity<String> handlePostRequest(@RequestBody String requestBody) {

        logger.debug("Received POST Request for signup");
        SignupEntity signupEntity = signupEntityValidation.validateSignupDetails(requestBody);
        signupEntityValidation.validateViolations(signupEntity);
        if(signupEntity !=null){
            registrationService.saveUserDetails(signupEntity);
            String response = "Post request received successfully";
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
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
