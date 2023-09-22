package com.jorge.jwtpractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/basic")
public class BasicController {
    @GetMapping("/publicmessage")
    public ResponseEntity<String> showPublicMessage(){
        return ResponseEntity.ok().body("Hello, from a public endpoint");
    }
    @GetMapping("/authmessage")
    public ResponseEntity<String> showProtectedMessage(){
        return ResponseEntity.ok().body("Hello, from a secured endpoint");
    }
    @GetMapping("/specialmessage")
    public ResponseEntity<String> showSpecialMessage(){
        return ResponseEntity.ok().body("Special Post Permission for User");
    }
}
