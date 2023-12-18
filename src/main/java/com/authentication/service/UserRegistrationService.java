package com.authentication.service;

import com.authentication.entity.UserEntity;
import com.authentication.entity.UserEntityRepository;
import com.authentication.entity.UserLoginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {
    @Autowired
    private UserEntityRepository repository;

    public void saveUserDetails(UserEntity userEntity){
        UserEntity userByUserName = repository.findUserByUserName(userEntity.getUserName());
        if(userByUserName==null){
            repository.save(userEntity);
        }
        else{
            throw new IllegalArgumentException(userByUserName.getUserName()+" this is user name is already exist in system");
        }

    }
    public void validateUserLogin(UserLoginEntity userLoginEntity){
        UserEntity userByUserName = repository.findUserByUserName(userLoginEntity.getUserName());
        if(userByUserName==null || !userByUserName.getPassword().equals(userLoginEntity.getPassword())){
            throw new IllegalArgumentException("UserName or Password is invalid please verify");
        }
    }

}
