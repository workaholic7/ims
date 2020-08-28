package com.mandeep.ims.dto;

import com.mandeep.ims.entity.Invoice;
import com.mandeep.ims.util.Util;

public class InvoiceResponseDto {

	private int id;
	private String customerName;
	private String referenceNum;
	private String date;

	public InvoiceResponseDto(Invoice invoice) {
		this.customerName = invoice.getCustomer().getName();
		this.referenceNum = invoice.getReferenceNum();
		this.date = Util.formatLocalDateTime(invoice.getDate());
		this.id = invoice.getId();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getReferenceNum() {
		return referenceNum;
	}

	public void setReferenceNum(String referenceNum) {
		this.referenceNum = referenceNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
