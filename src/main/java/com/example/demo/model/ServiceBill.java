package com.example.demo.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ServiceBill {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//administrator kích hoạt dịch vụ lần dầu cho khách 
	private Long billId;
	private LocalDate billDate = LocalDate.now();
	private LocalTime billTime = LocalTime.now();
	private LocalDate endDate;
	private LocalTime endTime;
	private Integer GPS; //thiết bị
	private Integer Sim;
	private Double totalPrice; //giá dịch vụ 
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Service service;

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public LocalTime getBillTime() {
		return billTime;
	}

	public void setBillTime(LocalTime billTime) {
		this.billTime = billTime;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Integer getGPS() {
		return GPS;
	}

	public void setGPS(Integer GPS) {
		GPS = GPS;
	}

	public Integer getSim() {
		return Sim;
	}

	public void setSim(Integer sim) {
		Sim = sim;
	}
	
	
	
}
