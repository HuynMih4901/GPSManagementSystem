package com.example.demo.model;


import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Device {

	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	//bảng device là số thiết bị gps người dùng đã đăng ký dịch vụ 
	private Long deviceId;
	private Double totalPrice; //tổng giá tiền thiết bị + dịch vụ => phục vụ báo cáo doanh thu bên admin
	private Integer totalQuantity;//số thiết bị ng dùng mua
	private String imageUrl;
	private String vehicle; //thông tin xe gắn gps => người dùng được quyền nhập, xoá,sửa, thông tin mang tính bổ trợ gps được gắn xe nào.
	private Boolean status; //trạng thái on-off dịch vụ; 1: on, 0: off; on khi ng dùng dki dịch vụ 
	
	
	
	 public Long getDeviceId() {
		return deviceId;
	}


	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}


	public String getVehicle() {
		return vehicle;
	}


	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	@OneToOne
	 @JsonIgnore
	 private Users users;
	   
	
	 @OneToMany(cascade = CascadeType.ALL)
	 @JsonIgnore
	 List<GPS> product;


	public Double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Integer getTotalQuantity() {
		return totalQuantity;
	}


	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}


	public List<GPS> getProduct() {
		return product;
	}


	public void setProduct(List<GPS> product) {
		this.product = product;
	}
	 
		
	
}
