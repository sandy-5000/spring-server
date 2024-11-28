package com.darkube.server.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Controller
public class ViewController {

    @GetMapping("/**")
    public ResponseEntity<Resource> view() {
        Resource file = new ClassPathResource("static/index.html");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .body(file);
    }

}
