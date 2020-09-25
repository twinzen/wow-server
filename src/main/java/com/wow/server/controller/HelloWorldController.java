package com.wow.server.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    private static final Logger log = LogManager.getLogger(HelloWorldController.class);

    @GetMapping("/hello")
    public ResponseEntity<Map> hello() {

        log.debug("hello world controller call...");
        Map response = new HashMap();
        response.put("return", "ok");
        return ResponseEntity.ok(response);
    }
}
