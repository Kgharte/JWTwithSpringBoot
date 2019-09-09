package com.kalpesh.gharte.login.controller;

import com.kalpesh.gharte.login.exception.UserNameOrPasswordEmpty;
import com.kalpesh.gharte.login.exception.UserNotFoundException;
import com.kalpesh.gharte.login.exception.UsernameAndPasswordMismatchException;
import com.kalpesh.gharte.login.model.User;
import com.kalpesh.gharte.login.security.SecurityTokenGenerator;
import com.kalpesh.gharte.login.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/")
public class UserController
{
    private UserService userService;
    ResponseEntity responseEntity;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }
    @PostMapping("user")
    public ResponseEntity  login(@RequestBody User loginDetails) throws ServletException, UserNotFoundException, UsernameAndPasswordMismatchException {
        try {
            String userId = loginDetails.getUserId();
            String password = loginDetails.getPassword();
            if (userId == null || password == null) {
                throw new UserNameOrPasswordEmpty("Userid and Password cannot be empty");
            }
            User user = userService.findByUserIdAndPassword(userId, password);
            if (user == null) {
                throw new UserNotFoundException("User with given Id does not exists");
            }
            String fetchedPassword = user.getPassword();
            if (!password.equals(fetchedPassword)) {
                throw new UsernameAndPasswordMismatchException("Invalid login credential, Please check username and password ");
            }
            // generating token

            SecurityTokenGenerator securityTokenGenrator = (User userDetails) -> {
                String jwtToken = "";
                jwtToken = Jwts.builder().setId(user.getUserId()).setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
                Map<String, String> map1 = new HashMap<>();
                map1.put("token", jwtToken);
                map1.put("message", "User successfully logged in");
                return map1;
            };
            Map<String, String> map = securityTokenGenrator.generateToken(user);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (UserNameOrPasswordEmpty | UserNotFoundException| UsernameAndPasswordMismatchException exception) {
            return new ResponseEntity<>("{ \"message\": \"" + exception.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("user")
    public ResponseEntity<?> getAllUser()
    {
        return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
    }


    @PostMapping("users")
    public ResponseEntity<?> saveEvent(@RequestBody User user)  throws UserNotFoundException {
        try {
            userService.saveUser(user);
            responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
        }catch (UserNotFoundException ex){
            responseEntity =  new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
        }
        return responseEntity;
    }

}
