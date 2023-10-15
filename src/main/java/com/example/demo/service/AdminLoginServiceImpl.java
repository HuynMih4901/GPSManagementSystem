package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdminLoginDto;
import com.example.demo.exceptions.AdminNotFoundException;
import com.example.demo.model.Admin;
import com.example.demo.model.AdminCurrentSession;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CurrentAdminSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginServiceImpl implements AdminLoginService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CurrentAdminSessionDao currentAdminSessionDao;

	@Override
	public String logIntoAccount(AdminLoginDto adminLoginDto) throws AdminNotFoundException {
	
		Admin admin = adminRepository.findByUsername(adminLoginDto.getUsername());
		if(admin==null) {
			throw new AdminNotFoundException("Admin not present with this usename");
		}
		Optional<AdminCurrentSession> isValid = currentAdminSessionDao.findById(admin.getAdminId());
		
		if(isValid.isPresent()) {
			throw new AdminNotFoundException("admin with this username is already present");
		}
		if(admin.getPassword().equals(adminLoginDto.getPassword())) {
			String key = RandomString.make(4);
			AdminCurrentSession adminCurrentSession = new AdminCurrentSession(admin.getAdminId(),key, LocalDateTime.now());
			currentAdminSessionDao.save(adminCurrentSession);
			return "Loggin Successfull with this key :" + key;
		}else {
			throw new AdminNotFoundException("password Incorrect...");
		}
		
		
		
	}

	@Override
	public String logoutFromAccount(String key) throws AdminNotFoundException {
		
		AdminCurrentSession adminCurrentSession = currentAdminSessionDao.findByAdminKey(key);
		if(adminCurrentSession==null) {
			throw new AdminNotFoundException("Incorrect key..");
			
		}
		currentAdminSessionDao.delete(adminCurrentSession);
		return "Logout From Account..";
	}
	
}
