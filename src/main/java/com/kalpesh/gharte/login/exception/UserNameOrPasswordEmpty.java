package com.kalpesh.gharte.login.exception;

public class UserNameOrPasswordEmpty extends Exception {

    private String message;

    public UserNameOrPasswordEmpty()
    {}

    public UserNameOrPasswordEmpty(String message)
    {
        this.message=message;
    }
}
