package com.mandeep.ims.dto;

import com.mandeep.ims.entity.Customer;

public class CustomerNameResponseDto {

	private int id;
	private String name;

	public CustomerNameResponseDto(Customer customer) {
		this.id = customer.getId();
		this.name = customer.getName();
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}
}
