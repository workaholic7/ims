package com.mandeep.ims.dto;

import com.mandeep.ims.entity.Customer;
import com.mandeep.ims.util.Util;

public class CustomerResponseDto {

	private int id;
	private String name;
	private String date;

	public CustomerResponseDto(Customer customer) {
		super();
		this.id = customer.getId();
		this.name = Util.decrypt(customer.getName());
		this.date = Util.formatLocalDateTime(customer.getModifiedDate());
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

	public final String getDate() {
		return date;
	}

	public final void setDate(String date) {
		this.date = date;
	}

}
