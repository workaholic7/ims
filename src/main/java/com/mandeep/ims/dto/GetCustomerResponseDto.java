package com.mandeep.ims.dto;

import com.mandeep.ims.entity.Customer;

public class GetCustomerResponseDto {

	private int id;
	
	private String name;
	
	private String phoneNum;
	
	private String address;
	
	public GetCustomerResponseDto(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
		this.phoneNum = customer.getPhoneNum();
		this.address = customer.getAddress().toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
