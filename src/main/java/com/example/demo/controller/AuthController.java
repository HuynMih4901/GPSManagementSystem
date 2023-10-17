package com.example.demo.controller;

import com.example.demo.dto.user.UserSignUpRequestDTO;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

//  private final UserService userService;

//  @PostMapping("/signup")
//  public ResponseEntity<?> signUp(@RequestBody @Valid UserSignUpRequestDTO request) {
//    userService.signUp(request);
//    return ResponseEntity.status(HttpStatus.CREATED).build();
//  }
}
