package com.jorge.jwtpractice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {
    @GetMapping
    @PreAuthorize("hasAuthority('MANAGER_READ')")
    public ResponseEntity<String> get(){
        return ResponseEntity.ok().body("Get - Manager");
    }
    @PostMapping
    @PreAuthorize("hasAuthority('MANAGER_CREATE')")
    public ResponseEntity<String> post(){
        return ResponseEntity.ok().body("Post - Manager");
    }
    @PutMapping
    @PreAuthorize("hasAuthority('MANAGER_UPDATE')")
    public ResponseEntity<String> put(){
        return ResponseEntity.ok().body("Put - Manager");
    }
    @DeleteMapping
    @PreAuthorize("hasAuthority('MANAGER_DELETE')")
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok().body("Delete - Manager");
    }
}
