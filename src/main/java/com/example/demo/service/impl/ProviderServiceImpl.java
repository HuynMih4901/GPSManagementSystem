package com.example.demo.service.impl;

import com.example.demo.dto.provider.*;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.District;
import com.example.demo.model.Provider;
import com.example.demo.model.Province;
import com.example.demo.model.Ward;
import com.example.demo.repository.ProviderRepository;
import com.example.demo.service.AddressService;
import com.example.demo.service.ProviderService;
import com.example.demo.utils.GPSUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

  private final ProviderRepository providerRepository;
  private final AddressService addressService;

  @Override
  public void create(ProviderCreateBodyDTO request) {
    if (providerRepository.existsByCode(request.getCode())) {
      throw new GPSException(ExceptionUtils.E_RECORD_EXIST);
    }
    Provider provider =
        Provider.builder()
            .code(request.getCode())
            .name(request.getName())
            .addressDetail(request.getAddressDetail())
            .email(request.getEmail())
            .contactName(request.getContactName())
            .contactPhone(request.getContactPhone())
            .ward(addressService.getWard(request.getWardCode()))
            .idCard(request.getIdCard())
            .build();
    providerRepository.save(provider);
  }

  @Override
  public void update(int id, ProviderUpdateBodyDTO request) {
    Provider provider =
        providerRepository
            .findById(id)
            .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
    if (providerRepository.existsByCode(request.getCode())
        && !provider.getCode().equals(request.getCode())) {
      throw new GPSException(ExceptionUtils.E_RECORD_EXIST);
    }
    provider.setCode(request.getCode());
    provider.setName(request.getName());
    provider.setContactName(request.getContactName());
    provider.setEmail(request.getEmail());
    provider.setWard(addressService.getWard(request.getWardCode()));
    provider.setAddressDetail(request.getAddressDetail());
    provider.setContactPhone(request.getContactPhone());
    provider.setIdCard(request.getIdCard());
    providerRepository.save(provider);
  }

  @Override
  public void delete(int id) {
    if (providerRepository.existsById(id)) {
      throw new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST);
    }
    providerRepository.deleteById(id);
  }

  @Override
  public ProviderResponseDTO get(int id) {
    Provider provider =
        providerRepository
            .findById(id)
            .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));

    Ward ward = provider.getWard();
    District district = ward.getDistrict();
    Province province = district.getProvince();

    return ProviderResponseDTO.builder()
        .id(provider.getId())
        .code(provider.getCode())
        .name(provider.getName())
        .addressDetail(provider.getAddressDetail())
        .idCard(provider.getIdCard())
        .email(provider.getEmail())
        .contactName(provider.getContactName())
        .contactPhone(provider.getContactPhone())
        .wardCode(ward.getCode())
        .wardName(ward.getName())
        .provinceCode(province.getCode())
        .provinceName(province.getName())
        .districtCode(district.getCode())
        .districtName(district.getName())
        .build();
  }

  @Override
  public List<ProviderPageResponseDTO> find(ProviderSearchParamDTO request) {

    String name =
        StringUtils.isBlank(request.getName())
            ? null
            : GPSUtils.appendLikeExpression(request.getName());
    String code =
        StringUtils.isBlank(request.getCode())
            ? null
            : GPSUtils.appendLikeExpression(request.getCode());
    request.setName(name);
    request.setCode(code);
    return providerRepository.find(request);
  }

  @Override
  public Provider getProviderByCode(String providerCode) {
    return providerRepository
        .findByCode(providerCode)
        .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
  }
}
