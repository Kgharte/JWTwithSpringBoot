package com.kalpesh.gharte.login.security;

import com.kalpesh.gharte.login.model.User;

import java.util.Map;

@FunctionalInterface
public interface SecurityTokenGenerator {
    Map<String, String> generateToken(User user);
}
