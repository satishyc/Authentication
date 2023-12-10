package com.authentication.entity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupEntityRepository extends MongoRepository<SignupEntity, String> {

    SignupEntity findUserByUserName(String userName);


}
