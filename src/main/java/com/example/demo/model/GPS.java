package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class GPS {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gpsId;
	private String gpsName;
	private String description;
	private String url;
	private Double price;
	private Integer quantity;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	private Sim sim;

	

	public Long getGpsId() {
		return gpsId;
	}


	public void setGpsId(Long gpsId) {
		this.gpsId = gpsId;
	}


	public String getGpsName() {
		return gpsName;
	}


	public void setGpsName(String gpsName) {
		this.gpsName = gpsName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}
	

	public Sim getSim() {
		return sim;
	}


	public void setSim(Sim sim) {
		this.sim = sim;
	}
	
}
