package com.example.demo.service;

import com.example.demo.dto.sim.SimResponseDTO;
import com.example.demo.dto.sim.SimSearchParamDTO;
import com.example.demo.model.Sim;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SimService {

  void create(List<Sim> request);

  void update();

  void delete(int simId);

  void find();

  SimResponseDTO getSimBySimCode(String simCode);

  Page<SimResponseDTO> findSims(SimSearchParamDTO request, Pageable pageable);
}
