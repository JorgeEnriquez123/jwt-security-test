package com.jorge.jwtpractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    @GetMapping
    public ResponseEntity<String> get(){
        return ResponseEntity.ok().body("Get - Manager");
    }
    @PostMapping
    public ResponseEntity<String> post(){
        return ResponseEntity.ok().body("Post - Manager");
    }
    @PutMapping
    public ResponseEntity<String> put(){
        return ResponseEntity.ok().body("Put - Manager");
    }
    @DeleteMapping
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok().body("Delete - Manager");
    }
}
