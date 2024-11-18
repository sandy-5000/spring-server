package com.darkube.server.controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import com.darkube.server.types.DynamicObject;
import com.darkube.server.types.Message;

@RestController
public class MainController {

    @GetMapping(value = "/api", produces = "application/json")
    public Message root() {

        return new Message("Server [Darkube]");

    }

    @GetMapping(value = "/api/_____", produces = "application/json")
    public HashMap<String, Object> _____(/* @RequestParam String param */) {
        try {

            DynamicObject profile = new DynamicObject();
            profile.put("github", "github.com/_______");
            profile.put("gitlab", "gitlab.com/_______");

            DynamicObject details = new DynamicObject();
            details.put("name", "__________");
            details.put("age", 3000);
            details.put("phone", new String[] { "000-000-000", "(000) 000-000" });
            details.put("profile", profile.map());

            DynamicObject server = new DynamicObject();
            server.put("port", System.getProperty("port"));

            DynamicObject response = new DynamicObject();
            response.put("message", "Server [Darkube]");
            response.put("details", details.map());
            response.put("server", server.map());

            return response.map();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<>();

    }

    @RequestMapping(value = "/**", produces = "application/json")
    @ResponseBody
    public Message notFound(HttpServletRequest request) {
        String route = request.getRequestURI();
        String method = request.getMethod();
        return new Message("404 - `" + "Method: " + method + "` `Route: " + route + "` not avaliable");
    }

}
