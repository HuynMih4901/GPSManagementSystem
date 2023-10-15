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

public class Users extends AbstractUser {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@JsonIgnore
	@OneToOne(mappedBy = "users" , cascade = CascadeType.ALL)
     private Device cart;
	
	@JsonIgnore
	@OneToMany( mappedBy = "users", cascade = CascadeType.PERSIST)
	private List<Service> orders;


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Device getCart() {
		return cart;
	}


	public void setCart(Device cart) {
		this.cart = cart;
	}


	public List<Service> getOrders() {
		return orders;
	}


	public void setOrders(List<Service> orders) {
		this.orders = orders;
	}
	
}
