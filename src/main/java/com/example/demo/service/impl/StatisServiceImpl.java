package com.example.demo.service.impl;

import com.example.demo.dto.statis.StatisResponseDTO;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.SimRepository;
import com.example.demo.service.StatisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisServiceImpl implements StatisService {

  private final SimRepository simRepository;
  private final CustomerRepository customerRepository;

  @Override
  public StatisResponseDTO getStatis() {
    Integer numberOfCustomers = customerRepository.getAllCustomer();
    Integer numberOfSims = simRepository.getAllSims();
    StatisResponseDTO statisResponseDTO =
        StatisResponseDTO.builder()
            .numberOfCustomers(numberOfCustomers)
            .numberOfSims(numberOfSims)
            .build();
    return statisResponseDTO;
  }
}
