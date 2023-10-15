package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Address {

	@NotNull(message = "City Can't be null.")
	@NotBlank(message = "City Can't be Blank.")
	@NotEmpty(message = "City Can't be Empty.")
	private String country;

	@NotNull(message = "State Can't be null.")
	@NotBlank(message = "State Can't be Blank.")
	@NotEmpty(message = "State Can't be Empty.")
	private String province;

	@NotNull(message = "Country Can't be null.")
	@NotBlank(message = "Country Can't be Blank.")
	@NotEmpty(message = "Country Can't be Empty.")
	private String district;

	@NotNull(message = "Pincode Can't be null.")
	@NotBlank(message = "Pincode Can't be Blank.")
	@NotEmpty(message = "Pincode Can't be Empty.")
	private String ward;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}
}
