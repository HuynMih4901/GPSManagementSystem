package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Role", description = "Role API")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    @Operation(summary = "Get all role", description = "Lấy ra tất cả role")
    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("dkfjdkfjdkj");
    }
}
