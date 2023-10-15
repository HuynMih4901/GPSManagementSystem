package com.example.demo.exceptions;

public class UsersNotFoundException extends Exception {

	public UsersNotFoundException() {
		
	}
	public UsersNotFoundException(String message) {
		super(message);
	}
}
