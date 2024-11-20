package com.darkube.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.darkube.server.types.Message;
import com.darkube.server.models.User;
import com.darkube.server.repositories.UserRepository;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/api/user/login", produces = "application/json")
    public Message login() {

        return new Message("User Register");

    }

    @PostMapping(value = "/api/user/register", produces = "application/json")
    public Object register(@RequestBody User user) {

        try {
            userRepository.save(user);
            return user;
        } catch (DataAccessException e) {
            return new Message(e.getMessage());
        } catch (Exception e) {
            return new Message(e.getMessage());
        }

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
