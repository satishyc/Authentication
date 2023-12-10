package com.authentication.service;

import com.authentication.controllers.SignupController;
import com.authentication.entity.SignupEntity;
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
public class SignupEntityValidation {
    private final Validator validator =  Validation.buildDefaultValidatorFactory().getValidator();

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    public SignupEntity validateSignupDetails(String signupData){
        SignupEntity signupEntity;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            signupEntity = objectMapper.readValue(signupData, SignupEntity.class);
        }
        catch (JsonProcessingException ex){
            logger.error("Encountering an exception during the parsing of signup data, Exception Details"+ex);
            throw new IllegalArgumentException("Verify that the input JSON data does not adhere to the expected JSON format.");
        }
        catch (Exception e) {
            logger.error("Encountering an exception during the parsing of signup data, Exception Details"+e);
            throw new IllegalArgumentException("Verify that the input JSON data does not adhere to the expected JSON format.");
        }
       return signupEntity;
    }
    public void validateViolations(SignupEntity signupEntity){
        Set<ConstraintViolation<SignupEntity>> violations = validator.validate(signupEntity);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<SignupEntity> violation : violations) {
                logger.error("Details of Signup Validation Failure: "+violation.getPropertyPath() + ": " + violation.getMessage());
                throw new IllegalArgumentException(violation.getMessage());
            }

        }

    }


}
