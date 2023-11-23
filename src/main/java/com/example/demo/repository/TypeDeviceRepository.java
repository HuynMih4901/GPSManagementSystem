package com.example.demo.repository;

import com.example.demo.model.TypeDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeDeviceRepository extends JpaRepository <TypeDevice, Integer>{
    List<TypeDevice> findByNameContaining(String name);
}
