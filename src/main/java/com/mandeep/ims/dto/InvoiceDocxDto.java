package com.mandeep.ims.dto;

import java.util.List;

public class InvoiceDocxDto {

	private String name;
	private String phoneNum;
	private String address;
	private List<ItemCtx> items;

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getPhoneNum() {
		return phoneNum;
	}

	public final void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	public final List<ItemCtx> getItems() {
		return items;
	}

	public final void setItems(List<ItemCtx> items) {
		this.items = items;
	}
	
	

}
