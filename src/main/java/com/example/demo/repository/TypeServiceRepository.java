package com.example.demo.repository;

import com.example.demo.model.TypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeServiceRepository extends JpaRepository<TypeService, Integer> {
}
