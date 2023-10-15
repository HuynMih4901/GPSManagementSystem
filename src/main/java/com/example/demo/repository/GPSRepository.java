package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GPS;

@Repository
public interface GPSRepository extends JpaRepository<GPS, Long> {

	public List<GPS> findBygpsName(String gpsName);
}
