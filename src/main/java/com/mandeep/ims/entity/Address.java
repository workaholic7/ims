package com.mandeep.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mandeep.ims.dto.CustomerDto;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 128)
	private String addressLine1;

	@Column(length = 128)
	private String addressLine2;

	@Column(length = 64)
	private String state;

	@Column(length = 16)
	private String zipCode;

	@Column(length = 64)
	private String country;

	public Address() {
		super();
	}

	public Address(CustomerDto customerDto) {
		this.addressLine1 = customerDto.getAddressLine1();
		this.addressLine2 = customerDto.getAddressLine2();
		this.state = customerDto.getState();
		this.zipCode = customerDto.getZipCode();
		this.country = customerDto.getCountry();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
