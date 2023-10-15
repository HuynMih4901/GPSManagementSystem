package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.exceptions.AdminNotFoundException;
import com.example.demo.model.Admin;
import com.example.demo.service.AdminSevice;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminSevice adminSevice;
	
	@PostMapping("/add")
	public ResponseEntity<Admin> insertNewAdminHandler(@RequestBody Admin admin) throws AdminNotFoundException{
		System.out.print(admin.toString());
		
		return new ResponseEntity<Admin>(adminSevice.saveNewAdminDetails(admin),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{key}")
     public ResponseEntity<Admin> updateAdminHandler(@RequestBody Admin admin, @PathVariable("key") String key) throws AdminNotFoundException{
		
		return new ResponseEntity<Admin>(adminSevice.updateAdmin(key, admin),HttpStatus.ACCEPTED);
	}
	
	
		@DeleteMapping("/delete/{key}")
	public ResponseEntity<String> deleteAdminHandler(@PathVariable("key") String key)throws AdminNotFoundException{
		
		return new ResponseEntity<String>(adminSevice.deleteAdmin(key), HttpStatus.OK);
	}
}
