package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ServiceBill;

@Repository
public interface ServiceBillRepository extends JpaRepository<ServiceBill, Long> {

	
}
