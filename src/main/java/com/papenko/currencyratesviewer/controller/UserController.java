package com.papenko.currencyratesviewer.controller;

import com.papenko.currencyratesviewer.dto.ErrorMessage;
import com.papenko.currencyratesviewer.dto.UserDto;
import com.papenko.currencyratesviewer.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService service;

    @GetMapping
    public String home() {
        return "Go to /register and provide request body of {email, hash} to sign-up";
    }

    @PostMapping("register")
    public String register(@RequestBody UserDto user) {
        service.persistFreeUser(user);
        return "Success!";
    }

    @GetMapping("login")
    public String login(){
        return "Use POST method and provide email/hash combination";
    }

    @PostMapping("login")
    public String login(HttpServletRequest req, @RequestBody UserDto user){
        service.authenticate(req, user);
        return "Success!";
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
    }
}
