package com.example.demo.repository;

import com.example.demo.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, Integer> {

    Optional<Ward> findByCode(String code);
}
