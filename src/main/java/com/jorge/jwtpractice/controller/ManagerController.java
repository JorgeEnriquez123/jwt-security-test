package com.jorge.jwtpractice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
@PreAuthorize("hasRole('MANAGER')")
@Tag(name = "Manager Actions")
public class ManagerController {
    @GetMapping
    @PreAuthorize("hasAuthority('MANAGER_READ')")
    @Operation(
            summary = "Get Operation of Manager",
            description = "This endpoint will send a message",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized - Invalid Token",
                            responseCode = "403"
                    )
            }

    )
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
