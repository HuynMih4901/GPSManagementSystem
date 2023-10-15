package com.example.demo.controller;



import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsersDto;
import com.example.demo.exceptions.LoginException;
import com.example.demo.exceptions.UsersNotFoundException;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@PostMapping("/save")
	private ResponseEntity<Users> insertNewUsersHandler(@Valid  @RequestBody Users users) throws UsersNotFoundException{
		
		return new ResponseEntity<Users>(usersService.addNewUsers(users),HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UsersDto> updateUsersHandler(@PathVariable("userId") Long userId,UsersDto usersDto) throws UsersNotFoundException,LoginException{
		return new ResponseEntity<UsersDto>(usersService.updateUser(userId, usersDto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUsersHandler(@PathVariable("userId") Long deleteId) throws UsersNotFoundException,LoginException{
		return new ResponseEntity<String>(usersService.deleteUser(deleteId),HttpStatus.OK);
	}
	
}
