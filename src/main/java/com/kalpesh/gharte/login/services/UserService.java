package com.kalpesh.gharte.login.services;

import com.kalpesh.gharte.login.exception.UserNotFoundException;
import com.kalpesh.gharte.login.exception.UsernameAndPasswordMismatchException;
import com.kalpesh.gharte.login.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user) throws UserNotFoundException;
    public List<User> getAllUsers();
    public User findByUserIdAndPassword(String userId,String password) throws UsernameAndPasswordMismatchException;

}
