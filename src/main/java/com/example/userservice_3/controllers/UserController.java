package com.example.userservice_3.controllers;


import com.example.userservice_3.Dto.LoginRequestDto;
import com.example.userservice_3.Dto.LogoutRequestDto;
import com.example.userservice_3.Dto.SignUpRequestDto;
import com.example.userservice_3.Dto.UserDto;
import com.example.userservice_3.models.Token;
import com.example.userservice_3.models.User;
import com.example.userservice_3.services.UserService;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    public  UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto request){

        String email = request.getEmail();
        String password = request.getPassword();

        return userService.login(email,password);
    }


    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpRequestDto request ){

        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();



        return  userService.signUp(name, email,password);
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logOut(@RequestBody LogoutRequestDto request)
    {
        String token = request.getToken();

        userService.logOut(token);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") @NonNull String tokenValue){
        return UserDto.from(userService.validateToken(tokenValue));
    }

}
