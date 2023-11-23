package com.example.demo.service;

import com.example.demo.dto.address.DistrictResponseDTO;
import com.example.demo.dto.address.ProvinceResponseDTO;
import com.example.demo.dto.address.WardResponseDTO;
import com.example.demo.model.Ward;

import java.util.List;

public interface AddressService {

    List<ProvinceResponseDTO> getAllProvince();

    List<DistrictResponseDTO> getAllDistrictsOfProvince(String provinceCode);

    List<WardResponseDTO> getAllWardsOfDistrict(String districtCode);

    Ward getWard(String wardCode);
}
