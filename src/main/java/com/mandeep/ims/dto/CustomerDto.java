package com.mandeep.ims.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mandeep.ims.entity.Customer;

public class CustomerDto {

	@NotBlank
	@Size(max = 128, message = "Name cannot be longer than 128 characters")
	private String name;

	@NotBlank
	@Size(max = 32, message = "Phone number cannot be longer than 32 characters")
	private String phoneNum;

	@NotBlank
	@Size(max = 128, message = "Address line 1 cannot be longer than 128 characters")
	private String addressLine1;

	@Size(max = 128, message = "Address line 2 cannot be longer than 128 characters")
	private String addressLine2;

	@NotBlank
	@Size(max = 64, message = "State cannot be longer than 64 characters")
	private String state;

	@NotBlank
	@Size(max = 16, message = "Zip code cannot be longer than 16 characters")
	private String zipCode;

	@NotBlank
	@Size(max = 64, message = "Country name cannot be longer than 64 characters")
	private String country;

	@Size(max = 128, message = "Company name cannot be longer than 128 characters")
	@Pattern(regexp = "^[\\w\\d\\s ,.-]+", message = "Company name can contain only characters, comma, period & hash only")
	private String company;

	public CustomerDto(Customer customer) {
		this.name = customer.getName();
		this.phoneNum = customer.getPhoneNum();
		this.addressLine1 = customer.getAddress().getAddressLine1();
		this.addressLine2 = customer.getAddress().getAddressLine2();
		this.state = customer.getAddress().getState();
		this.zipCode = customer.getAddress().getZipCode();
		this.country = customer.getAddress().getCountry();
		this.company = customer.getCompany();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
