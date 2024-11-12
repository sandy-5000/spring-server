package com.darkube.server.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.darkube.server.types.DynamicObject;
import com.darkube.server.types.Server;

@RestController
public class MainController {

    @GetMapping("/api")
    public Server getMethodName() {

        return new Server("Server [Darkube]");

    }

    @GetMapping(value = "/api/_____", produces = "application/json")
    public Map<String, Object> home(/* @RequestParam String param */) {

        DynamicObject object = new DynamicObject();
        try {
            object.put("message", "Server [Darkube]");
            object.put("details.name", "sandy-blaze");
            object.put("details.age", 3000);
            Map<String, Object> profile = new HashMap<>();
            profile.put("github", "github.com/sandy-5000");
            profile.put("linkedIn", "linkedin.com/in/sandeep-kumar-bhaviri");
            object.put("details.profile", profile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object.map();

    }

    @GetMapping(value = "/api/{resourceId}", produces = "application/json")
    public String resourceId(@PathVariable String resourceId) {

        return "Received: " + resourceId;

    }

}
