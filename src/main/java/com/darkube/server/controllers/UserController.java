package com.darkube.server.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.darkube.server.types.LoginBody;
import com.darkube.server.types.Message;
import com.darkube.server.models.User;
import com.darkube.server.services.collections.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/api/user/login", produces = "application/json")
    public Object login(@RequestBody LoginBody body) {

        try {
            final Optional<User> user = userService.login(body);
            if (user.isPresent()) {
                return user;
            }
            return new Message("User Not present");
        } catch (Exception e) {
            return new Message(e.getMessage());
        }

    }

    @PostMapping(value = "/api/user/register", produces = "application/json")
    public Object register(@RequestBody User user) {

        try {
            return userService.save(user);
        } catch (DataAccessException e) {
            return new Message(e.getMessage());
        } catch (Exception e) {
            return new Message(e.getMessage());
        }

    }

    @GetMapping(value = "/api/user/{username}", produces = "application/json")
    public Optional<User> profile(@PathVariable String username) {

        return userService.get(username);

    }

}
