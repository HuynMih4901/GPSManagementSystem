package com.example.demo.repository;

import com.example.demo.model.Adminitrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Adminitrator, Integer> {

  boolean existsByEmail(String email);

  Optional<Adminitrator> findByCode(String code);
}
