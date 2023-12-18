package com.authentication.service;

import com.authentication.entity.UserLoginEntity;
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
public class UserLoginEntityValidation {
    private final Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    private static final Logger logger = LoggerFactory.getLogger(UserLoginEntityValidation.class);

    public UserLoginEntity validateLoginDetails(String signupData){
        UserLoginEntity userLoginEntity;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userLoginEntity = objectMapper.readValue(signupData, UserLoginEntity.class);
        }
        catch (JsonProcessingException ex){
            logger.error("Encountering an exception during the parsing of Login data, Exception Details"+ex);
            throw new IllegalArgumentException("Verify that the input JSON data does not adhere to the expected JSON format.");
        }
        validateViolations(userLoginEntity);
        return userLoginEntity;
    }
    public void validateViolations(UserLoginEntity userLoginEntity){
        Set<ConstraintViolation<UserLoginEntity>> violations = validator.validate(userLoginEntity);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserLoginEntity> violation : violations) {
                logger.error("Details of Signup Validation Failure: "+violation.getPropertyPath() + ": " + violation.getMessage());
                throw new IllegalArgumentException(violation.getMessage());
            }

        }

    }
}
