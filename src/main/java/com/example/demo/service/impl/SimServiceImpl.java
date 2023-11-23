package com.example.demo.service.impl;

import com.example.demo.dto.sim.SimResponseDTO;
import com.example.demo.dto.sim.SimSearchParamDTO;
import com.example.demo.enums.CodeTypeEnum;
import com.example.demo.exceptions.ExceptionUtils;
import com.example.demo.exceptions.GPSException;
import com.example.demo.model.Provider;
import com.example.demo.model.Sim;
import com.example.demo.repository.SimRepository;
import com.example.demo.service.ProviderService;
import com.example.demo.service.SimService;
import com.example.demo.utils.GPSUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimServiceImpl implements SimService {

  private final SimRepository simRepository;
  private final ProviderService providerService;

  @PersistenceContext private EntityManager entityManager;

  @Override
  public void create(List<Sim> request) {
    Integer maxId = simRepository.findMaxId();
    if (maxId == null) {
      maxId = 0;
    }
    for (Sim sim : request) {
      sim.setCode(GPSUtils.generateCode(CodeTypeEnum.SIM.getCode(), ++maxId));
    }
    simRepository.saveAll(request);
  }

  @Override
  public void update() {}

  @Override
  public void delete(int simId) {
    Sim sim =
        simRepository
            .findById(simId)
            .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
    simRepository.delete(sim);
  }

  @Override
  public void find() {}

  @Override
  public SimResponseDTO getSimBySimCode(String simCode) {
    Sim sim =
        simRepository
            .findByCode(simCode)
            .orElseThrow(() -> new GPSException(ExceptionUtils.E_RECORD_NOT_EXIST));
    Provider provider = sim.getProvider();
    return SimResponseDTO.builder()
        .id(sim.getId())
        .simCode(sim.getCode())
        .phone(sim.getPhone())
        .price(sim.getPrice())
        .providerCode(provider.getCode())
        .providerName(provider.getName())
        .build();
  }

  @Override
  public Page<SimResponseDTO> findSims(SimSearchParamDTO request, Pageable pageable) {
    Map<String, Object> queryParams = new HashMap<>();
    StringBuilder sql = new StringBuilder();
    String resultQuery =
        """
select s.id, s.code as sim_code, s.phone, s.price, p.code as provider_code, p.name as provider_name
""";
    String fromQuery = """
from sims s join providers p on s.provider_code = p.code
""";
    StringBuilder whereQuery = new StringBuilder(" where true ");
    if (StringUtils.isNotBlank(request.getSimCode())) {
      whereQuery.append(" and (s.code like :simCode) ");
      queryParams.put("simCode", GPSUtils.appendLikeExpression(request.getSimCode().toUpperCase()));
    }
    if (StringUtils.isNotBlank(request.getProviderCode())) {
      whereQuery.append(" and (p.code = :providerCode) ");
      queryParams.put("providerCode", request.getProviderCode().toUpperCase());
    }
    if (StringUtils.isNotBlank(request.getPhone())) {
      whereQuery.append(" and (s.phone like :phone) ");
      queryParams.put("phone", request.getPhone());
    }
    sql.append(resultQuery).append(fromQuery).append(whereQuery);
    Query query = entityManager.createNativeQuery(sql.toString(), Tuple.class);
    int pageNumber = pageable.getPageNumber();
    int pageSize = pageable.getPageSize();
    query.setFirstResult(pageNumber * pageSize);
    query.setMaxResults(pageSize);
    GPSUtils.setQueryParameters(queryParams, query);
    List<Tuple> tuples = query.getResultList();
    List<SimResponseDTO> simResponseDTOList = new ArrayList<>();
    for (Tuple tuple : tuples) {
      int id = tuple.get("id", Integer.class);
      String simCode = tuple.get("sim_code", String.class);
      String phone = tuple.get("phone", String.class);
      Double price = tuple.get("price", Double.class);
      String providerCode = tuple.get("provider_code", String.class);
      String providerName = tuple.get("provider_name", String.class);
      SimResponseDTO simResponseDTO =
          SimResponseDTO.builder()
              .simCode(simCode)
              .phone(phone)
              .price(price)
              .providerCode(providerCode)
              .providerName(providerName)
              .build();
      simResponseDTOList.add(simResponseDTO);
    }
    StringBuilder countSql = new StringBuilder(" select count(*) ");
    countSql.append(fromQuery).append(whereQuery);
    Query countQuery = entityManager.createNativeQuery(countSql.toString());
    GPSUtils.setQueryParameters(queryParams, countQuery);
    long totalCount = (long) countQuery.getSingleResult();
    return new PageImpl<>(simResponseDTOList, pageable, totalCount);
  }
}
