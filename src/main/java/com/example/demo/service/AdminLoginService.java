package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminLoginDto;
import com.example.demo.exceptions.AdminNotFoundException;

@Service
public interface AdminLoginService {

	public String logIntoAccount(AdminLoginDto adminLoginDto) throws AdminNotFoundException;
	
	
	public String logoutFromAccount(String key) throws AdminNotFoundException;
	
	
}
