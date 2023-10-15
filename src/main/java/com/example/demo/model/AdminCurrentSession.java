package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class AdminCurrentSession {

	@Id
	
	private Long adminId;
	private String adminKey;
	private LocalDateTime localDateTime;

	public AdminCurrentSession() {};
	public AdminCurrentSession(Long adminId, String adminKey, LocalDateTime localDateTime) {
		this.adminId = adminId;
		this.adminKey = adminKey;
		this.localDateTime = localDateTime;

	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminKey() {
		return adminKey;
	}

	public void setAdminKey(String adminKey) {
		this.adminKey = adminKey;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
}
