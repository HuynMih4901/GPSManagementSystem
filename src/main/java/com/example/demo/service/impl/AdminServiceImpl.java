package com.example.demo.service.impl;

import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Adminitrator;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final AdminRepository adminRepository;

  @Override
  public Adminitrator getAdmin(String adminCode) {
    return adminRepository
        .findByCode(adminCode)
        .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
  }
}
