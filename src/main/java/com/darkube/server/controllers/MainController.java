package com.darkube.server.controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

import com.darkube.server.types.DynamicObject;
import com.darkube.server.types.Err;
import com.darkube.server.types.Message;

@RestController
public class MainController {

    final String route = "/api";

    @GetMapping(value = route, produces = "application/json")
    public Message root() {

        return new Message("Server [Darkube]");

    }

    @GetMapping(value = route + "/_____", produces = "application/json")
    public HashMap<String, Object> _____(/* @RequestParam String param */) {
        try {

            DynamicObject profile = new DynamicObject();
            profile.put("___0__", "___0__.___/_______");
            profile.put("___1__", "___1__.___/_______");

            DynamicObject details = new DynamicObject();
            details.put("name", "__________");
            details.put("age", 3000);
            details.put("phone", new String[] { "000-000-000", "(000) 000-000" });
            details.put("links", profile.map());

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

    @RequestMapping(value = { route + "/**", "/**" }, produces = "application/json")
    @ResponseBody
    public Err notFound(HttpServletRequest request) {
        String route = request.getRequestURI();
        String message = "(method not allowed) `Method: %s` `Route: %s`";
        if (route.startsWith("/api")) {
            message = "(path not found) `Method: %s` `Route: %s`";
        }
        String method = request.getMethod();
        return new Err(String.format(message, method, route), 404);
    }

    @GetMapping(value = "/**", produces = "text/html")
    public String view() {
        return "<p style=\"text-align: center; margin-top: 100px\">Darkube</p>";
    }

}
