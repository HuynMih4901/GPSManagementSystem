package com.example.demo.repository;

import com.example.demo.dto.provider.ProviderPageResponseDTO;
import com.example.demo.dto.provider.ProviderSearchParamDTO;
import com.example.demo.model.Provider;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProviderRepository extends JpaRepository<Provider, Integer> {

  Optional<Provider> findByCode(String code);

  @Query(value = "select distinct p from Provider p")
  List<Provider> findAllDistinct();

  boolean existsByCode(String code);

  boolean existsById(int id);

  @Query(
      value =
          """
select new com.example.demo.dto.provider.ProviderPageResponseDTO(p)
from Provider p
where (:#{#request.code} is null or lower(p.code) like :#{#request.code})
and (:#{#request.name} is null or lower(p.name) like :#{#request.name})
""")
  List<ProviderPageResponseDTO> find(ProviderSearchParamDTO request);
}
