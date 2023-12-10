package com.authentication.service;

import com.authentication.entity.SignupEntity;
import com.authentication.entity.SignupEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    @Autowired
    private SignupEntityRepository repository;

    public void saveUserDetails(SignupEntity signupEntity){
        SignupEntity userByUserName = repository.findUserByUserName(signupEntity.getUserName());
        if(userByUserName==null){
            repository.save(signupEntity);
        }
        else{
            throw new IllegalArgumentException(userByUserName.getUserName()+" this is user name is already exist in system");
        }

    }

}
