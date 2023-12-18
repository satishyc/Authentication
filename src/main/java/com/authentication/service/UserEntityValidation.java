package com.authentication.service;

import com.authentication.controllers.SignupController;
import com.authentication.entity.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserEntityValidation {
    private final Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    public UserEntity validateSignupDetails(String signupData){
        UserEntity userEntity;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userEntity = objectMapper.readValue(signupData, UserEntity.class);
        }
        catch (JsonProcessingException ex){
            logger.error("Encountering an exception during the parsing of signup data, Exception Details"+ex);
            throw new IllegalArgumentException("Verify that the input JSON data does not adhere to the expected JSON format.");
        }

       return userEntity;
    }
    public void validateViolations(UserEntity userEntity){
        Set<ConstraintViolation<UserEntity>> violations = validator.validate(userEntity);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserEntity> violation : violations) {
                logger.error("Details of Signup Validation Failure: "+violation.getPropertyPath() + ": " + violation.getMessage());
                throw new IllegalArgumentException(violation.getMessage());
            }

        }

    }


}
