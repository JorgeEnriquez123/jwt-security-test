package com.jorge.jwtpractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public ResponseEntity<String> get(){
        return ResponseEntity.ok().body("Get - Admin");
    }
    @PostMapping
    public ResponseEntity<String> post(){
        return ResponseEntity.ok().body("Post - Admin");
    }
    @PutMapping
    public ResponseEntity<String> put(){
        return ResponseEntity.ok().body("Put - Admin");
    }
    @DeleteMapping
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok().body("Delete - Admin");
    }

}
