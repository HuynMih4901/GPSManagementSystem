package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Sim;
@Repository
public interface SimRepository extends JpaRepository<Sim, Long>{

	public Sim findBySerial(String serial);
}
