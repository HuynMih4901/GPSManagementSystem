package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Device;

@Repository
public interface DeviceRepository  extends JpaRepository<Device, Long>{

}
