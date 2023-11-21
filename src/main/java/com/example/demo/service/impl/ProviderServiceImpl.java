package com.example.demo.service.impl;

import com.example.demo.dto.provider.ProviderCreateBodyDTO;
import com.example.demo.dto.provider.ProviderResponseDTO;
import com.example.demo.dto.provider.ProviderSearchParamDTO;
import com.example.demo.dto.provider.ProviderUpdateBodyDTO;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Provider;
import com.example.demo.repository.ProviderRepository;
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

  @Override
  public void create(ProviderCreateBodyDTO request) {
    if (providerRepository.existsByCode(request.getCode())) {
      throw new GPSException(ExceptionUtils.E_RECORD_EXIST);
    }
    Provider provider = Provider.builder().code(request.getCode()).name(request.getName()).build();
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
    return ProviderResponseDTO.builder()
        .id(provider.getId())
        .code(provider.getCode())
        .name(provider.getName())
        .build();
  }

  @Override
  public List<ProviderResponseDTO> find(ProviderSearchParamDTO request) {
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
