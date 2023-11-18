package com.example.demo.service;

import com.example.demo.dto.admin.AdminUpdateRequestDTO;
import com.example.demo.model.Adminitrator;

public interface AdminService {

  Adminitrator getAdmin(String adminCode);

  void updateAdmin(AdminUpdateRequestDTO request, String adminCode); //
}
