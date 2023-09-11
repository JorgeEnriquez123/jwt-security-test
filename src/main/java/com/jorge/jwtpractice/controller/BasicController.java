package com.jorge.jwtpractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    @GetMapping("/showmessage")
    public ResponseEntity<String> showProtectedMessage(){
        return ResponseEntity.ok().body("Hello, from a secured endpoint");
    }
}
