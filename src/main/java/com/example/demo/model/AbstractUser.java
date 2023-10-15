package com.example.demo.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass

public class AbstractUser {

	@NotNull(message = " UserName Can't be null.")
	@NotBlank(message = " UserName Can't be Blank.")
	@NotEmpty(message = " UserName Can't be Empty.")
	private String username;

	@NotNull(message = " Pssword Can't be null.")
	private String password;

	@NotNull(message = " Name Can't be null.")
	@NotBlank(message = " Name Can't be Blank.")
	@NotEmpty(message = " Name Can't be Empty.")
	private String name;

	@Embedded
	private Address address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull(message = "Mobile Number cannot be null.")
	private String mobileNumber;

	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotNull(message = "Email Can't be null.")
	private String email;

}
