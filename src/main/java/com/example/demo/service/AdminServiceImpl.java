package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.AdminNotFoundException;
import com.example.demo.model.Admin;
import com.example.demo.model.AdminCurrentSession;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CurrentAdminSessionDao;

@Service
public class AdminServiceImpl implements AdminSevice {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CurrentAdminSessionDao currentAdminSessionDao;
	

	@Override
	public Admin saveNewAdminDetails(Admin admin) throws AdminNotFoundException {
		 Admin admins = adminRepository.findByUsername(admin.getUsername());
		 
		 if(admins==null) {
			return adminRepository.save(admin);
		 }else {
			 throw new AdminNotFoundException("Admin with this username is already present");
		 }
		
		
	}

	@Override
	public Admin updateAdmin(String key,Admin admin ) throws AdminNotFoundException {
		
		   AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(key);

		    if (isPresent == null) {
		        throw new AdminNotFoundException("Admin not present with this key");
		    }

		    Optional<Admin> existingAdmin = adminRepository.findById(isPresent.getAdminId());
		    if (existingAdmin.isPresent()) {
		        Admin updatedAdmin = existingAdmin.get();
		        updatedAdmin.setAddress(admin.getAddress());
		        updatedAdmin.setEmail(admin.getEmail());
		        updatedAdmin.setMobileNumber(admin.getMobileNumber());
		        updatedAdmin.setName(admin.getName());
		        updatedAdmin.setPassword(admin.getPassword());
		        updatedAdmin.setUsername(admin.getUsername());

		        adminRepository.save(updatedAdmin);
		    }

		    return admin;
	}

	@Override
	public String deleteAdmin(String key) throws AdminNotFoundException {
		
		 AdminCurrentSession isPresent = currentAdminSessionDao.findByAdminKey(key);

		    if (isPresent == null) {
		        throw new AdminNotFoundException("Admin not present with this key");
		    }

		    Optional<Admin> existingAdmin = adminRepository.findById(isPresent.getAdminId());
		    if (existingAdmin.isPresent()) {
		    
		    	adminRepository.delete(existingAdmin.get());
		    	
		    
		    }
			return "Admin with this key is Successfully Deleted";
		    
		    }
		
		
	
	
}
