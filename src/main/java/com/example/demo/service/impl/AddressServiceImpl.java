package com.example.demo.service.impl;

import com.example.demo.dto.address.DistrictResponseDTO;
import com.example.demo.dto.address.ProvinceResponseDTO;
import com.example.demo.dto.address.WardResponseDTO;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.District;
import com.example.demo.model.Province;
import com.example.demo.model.Ward;
import com.example.demo.repository.DistrictRepository;
import com.example.demo.repository.ProvinceRepository;
import com.example.demo.repository.WardRepository;
import com.example.demo.service.AddressService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final ProvinceRepository provinceRepository;
  private final DistrictRepository districtRepository;
  private final WardRepository wardRepository;

  @Override
  public List<ProvinceResponseDTO> getAllProvince() {
    List<Province> provinces = provinceRepository.getAllProvince();
    return provinces.stream().map(ProvinceResponseDTO::new).toList();
  }

  @Override
  public List<DistrictResponseDTO> getAllDistrictsOfProvince(String provinceCode) {
    List<District> districts = provinceRepository.findByCode(provinceCode).getDistricts();
    return districts.stream().map(DistrictResponseDTO::new).toList();
  }

  @Override
  public List<WardResponseDTO> getAllWardsOfDistrict(String districtCode) {
    List<Ward> wards = districtRepository.findByCode(districtCode).getWards();
    return wards.stream().map(WardResponseDTO::new).toList();
  }

  @Override
  public Ward getWard(String wardCode) {
    return wardRepository
        .findByCode(wardCode)
        .orElseThrow(() -> new GPSException(ExceptionUtils.E_ADDRESS_ERROR));
  }
}
