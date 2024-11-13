package com.darkube.server.controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.darkube.server.types.DynamicObject;
import com.darkube.server.types.Message;

@RestController
public class MainController {

    @GetMapping(value = "/api", produces = "application/json")
    public Message getMethodName() {

        return new Message("Server [Darkube]");

    }

    @GetMapping(value = "/api/_____", produces = "application/json")
    public HashMap<String, Object> home(/* @RequestParam String param */) {

        DynamicObject object = new DynamicObject();
        try {
            object.put("message", "Server [Darkube]");
            object.put("details.name", "__________");
            object.put("details.age", 3000);
            HashMap<String, Object> profile = new HashMap<>();
            profile.put("github", "github.com/_______");
            profile.put("linkedIn", "linkedin.com/xx/_______");
            object.put("details.profile", profile);
            object.put("details.phone", new String[] { "", "(000) 000-000" });
            String[] phoneNumbers = (String [])object.get("details.phone");
            phoneNumbers[0] = "0000000000";
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
