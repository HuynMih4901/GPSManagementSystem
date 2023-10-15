package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class CurrentUserSession {
      
	
	@Id
	private Long userId;
	private String userKey;
	private LocalDateTime localDateTime;
	
	public CurrentUserSession() {};
	public CurrentUserSession(Long userId, String userKey, LocalDateTime localDateTime) {
		this.userId = userId;
		this.userKey = userKey;
		this.localDateTime = localDateTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
}
