package com.kalpesh.gharte.login.exception;

public class UsernameAndPasswordMismatchException extends Exception {

    private String message;

    public UsernameAndPasswordMismatchException(){}

    public UsernameAndPasswordMismatchException(String message){
        this.message=message;
    }
}
