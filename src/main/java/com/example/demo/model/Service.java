package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import com.example.demo.model.Users;


@Entity

public class Service {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long serviceId;
	private String price;
    private LocalDate startDate = LocalDate.now();
	private LocalTime startTime = LocalTime.now();
	private LocalDate endDate;
	private LocalDate endTime;
	private String status = "Pending"; //trạng thái gia hạn gói dịch vụ: gia hạn và huỷ gia hạn.
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
	private Users users;
	
	@OneToOne
	private Device device;
	
	@OneToOne(cascade = CascadeType.ALL)
	private ServiceBill productBill;

	

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice (Device device) {
		this.device = device ;
	}

	public ServiceBill getProductBill() {
		return productBill;
	}

	public void setProductBill(ServiceBill productBill) {
		this.productBill = productBill;
	}	
}
