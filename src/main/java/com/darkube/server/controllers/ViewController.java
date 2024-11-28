package com.darkube.server.controllers;

import java.util.HashMap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Controller
public class ViewController {

    private static final HashMap<String, String> MIME_TYPES = new HashMap<>();

    static {
        MIME_TYPES.put("js", "application/javascript");
        MIME_TYPES.put("css", "text/css");
        MIME_TYPES.put("png", "image/png");
        MIME_TYPES.put("jpg", "image/jpeg");
        MIME_TYPES.put("jpeg", "image/jpeg");
        MIME_TYPES.put("svg", "image/svg+xml");
        MIME_TYPES.put("gif", "image/gif");
        MIME_TYPES.put("webp", "image/webp");
        MIME_TYPES.put("woff", "font/woff");
        MIME_TYPES.put("woff2", "font/woff2");
        MIME_TYPES.put("ttf", "font/ttf");
        MIME_TYPES.put("eot", "application/vnd.ms-fontobject");
    }

    @GetMapping("/assets/**")
    public ResponseEntity<Resource> assets(HttpServletRequest request) {
        String route = request.getRequestURI();
        Resource file = new ClassPathResource("static/" + route);

        String extension = getFileExtension(route);
        String mimeType = MIME_TYPES.getOrDefault(extension,
                MediaType.APPLICATION_OCTET_STREAM_VALUE);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, mimeType)
                .body(file);
    }

    @GetMapping("/**")
    public ResponseEntity<Resource> view() {
        Resource file = new ClassPathResource("static/index.html");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .body(file);
    }

    private String getFileExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex != -1) ? filename.substring(dotIndex + 1) : "";
    }

}
