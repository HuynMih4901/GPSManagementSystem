package com.example.demo.service;

import com.example.demo.dto.provider.ProviderCreateBodyDTO;
import com.example.demo.dto.provider.ProviderResponseDTO;
import com.example.demo.dto.provider.ProviderSearchParamDTO;
import com.example.demo.dto.provider.ProviderUpdateBodyDTO;
import com.example.demo.model.Provider;

import java.util.List;

public interface ProviderService {

  void create(ProviderCreateBodyDTO request);

  void update(int id, ProviderUpdateBodyDTO request);

  void delete(int id);

  ProviderResponseDTO get(int id);

  List<ProviderResponseDTO> find(ProviderSearchParamDTO request);

  Provider getProviderByCode(String providerCode);
}
