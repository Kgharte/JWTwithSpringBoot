package com.kalpesh.gharte.login.repository;

import com.kalpesh.gharte.login.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>
{
    public User findByUserIdAndPassword(String userId,String password);

}
