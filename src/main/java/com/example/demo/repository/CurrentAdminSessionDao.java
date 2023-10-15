package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AdminCurrentSession;

public interface CurrentAdminSessionDao extends JpaRepository<AdminCurrentSession, Long> {

	public AdminCurrentSession findByAdminKey(String adminKey);
}
