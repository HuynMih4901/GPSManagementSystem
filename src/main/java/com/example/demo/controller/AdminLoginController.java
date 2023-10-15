package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdminLoginDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.exceptions.AdminNotFoundException;
import com.example.demo.exceptions.LoginException;
import com.example.demo.service.AdminLoginService;


@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {

	@Autowired
	private AdminLoginService adminLoginService;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> adminLoginHandler(@RequestBody  AdminLoginDto adminLoginDto) throws AdminNotFoundException{
		
		return new  ResponseEntity<String>(adminLoginService.logIntoAccount(adminLoginDto) ,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> adminLogoutHandler(String key) throws AdminNotFoundException{
		
		return new ResponseEntity<String>(adminLoginService.logoutFromAccount(key),HttpStatus.OK);
	}
}
