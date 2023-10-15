package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserLoginDto;
import com.example.demo.exceptions.LoginException;
import com.example.demo.service.UserLoginService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class UsersLoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> userLoginHandler(@RequestBody UserLoginDto userLoginDto) throws LoginException{
		
		return new  ResponseEntity<String>(userLoginService.logIntoAccount(userLoginDto) ,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> userLogoutHandler(String key) throws LoginException{
		
		return new ResponseEntity<String>(userLoginService.logoutFromAccount(key),HttpStatus.OK);
	}
}
