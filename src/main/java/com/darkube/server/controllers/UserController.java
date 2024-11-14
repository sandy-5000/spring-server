package com.darkube.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.darkube.server.types.Message;

@RestController
public class UserController {

    @GetMapping(value = "/api/user/login", produces = "application/json")
    public Message login() {

        return new Message("User Login");

    }

    @GetMapping(value = "/api/user/register", produces = "application/json")
    public Message register() {

        return new Message("User Register");

    }

    @GetMapping(value = "/api/user/profile", produces = "application/json")
    public Message profile() {

        return new Message("User Profile");

    }

    @GetMapping(value = "/api/user/{operation}", produces = "application/json")
    public Message operation(@PathVariable String operation) {

        return new Message("User Operation: " + operation);

    }

}
