package com.example.demo.repository;

import com.example.demo.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Integer> {

    Province findByCode(String code);

    @Query(value = "select distinct p from Province p")
    List<Province> getAllProvince();
}
