package com.example.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Sim {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long simId;
	private String serial;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sim")
	@JsonIgnore
	private List<GPS> list;

	public Long getsimId() {
		return simId;
	}

	public void setsimId(Long simId) {
		this.simId = simId;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public List<GPS> getList() {
		return list;
	}

	public void setList(List<GPS> list) {
		this.list = list;
	}

}
