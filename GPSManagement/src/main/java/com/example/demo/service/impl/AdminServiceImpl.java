package com.example.demo.service.impl;

import com.example.demo.dto.admin.AdminUpdateRequestDTO;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Adminitrator;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

  private final AdminRepository adminRepository;
  private final AddressService addressService;

  @Override
  public Adminitrator getAdmin(String adminCode) {
    return adminRepository
        .findByCode(adminCode)
        .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
  }

  @Override
  public void updateAdmin(AdminUpdateRequestDTO request, String adminCode) {
    Adminitrator admin =
        adminRepository
            .findByCode(adminCode)
            .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
    admin.setEmail(request.getEmail());
    admin.setWard(addressService.getWard(request.getWardCode()));
    admin.setName(request.getName());
    admin.setPhone(request.getPhone());
    admin.setAddressDetail(request.getAddressDetail());

    adminRepository.save(admin);
  }
}
