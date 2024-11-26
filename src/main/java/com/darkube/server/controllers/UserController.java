package com.darkube.server.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.darkube.server.types.DynamicObject;
import com.darkube.server.types.LoginBody;
import com.darkube.server.types.Message;

import jakarta.servlet.http.HttpServletRequest;

import com.darkube.server.models.User;
import com.darkube.server.services.JwtService;
import com.darkube.server.services.collections.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping(value = "/api/user/login", produces = "application/json")
    public Object login(@RequestBody LoginBody body) {

        try {
            final Optional<User> user = userService.login(body);
            if (user.isPresent()) {
                final String token = jwtService
                        .generateToken(user.get());
                try {
                    DynamicObject object = new DynamicObject();
                    object.put("user", user.get());
                    object.put("token", token);
                    return object.map();
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Message("Something went wrong");
                }
            }
            return new Message("User Not present");
        } catch (Exception e) {
            e.printStackTrace();
            return new Message("Something went wrong");
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

        Optional<User> user = userService.get(username);
        return user;

    }

    @GetMapping(value = "/api/user/secure", produces = "application/json")
    public Object secure(
            @RequestHeader(value = "darkube-x-auth", required = false) String token,
            HttpServletRequest request) {

        if (!jwtService.verify(token)) {
            return new Message("Request not authorized");
        }
        DynamicObject object = new DynamicObject();
        try {
            object.put("user.message", "secure");
            return object.map();
        } catch (Exception e) {
            String route = request.getRequestURI();
            String method = request.getMethod();
            String message = "Error - `Method: %s` `Route: %s`";
            return new Message(String.format(message, method, route));
        }

    }

}
