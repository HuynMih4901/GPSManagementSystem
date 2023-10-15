package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CurrentUserSession;

@Repository
public interface CurrentUserSessionDao extends JpaRepository<CurrentUserSession, Long> {

	public CurrentUserSession findByUserKey(String userKey);
}
