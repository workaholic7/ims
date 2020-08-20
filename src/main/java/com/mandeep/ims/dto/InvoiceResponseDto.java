package com.mandeep.ims.dto;

import com.mandeep.ims.entity.Invoice;

public class InvoiceResponseDto {

	private String customerName;
	private String referenceNum;
	private long timestamp;

	public InvoiceResponseDto(Invoice invoice) {
		this.customerName = invoice.getCustomer().getName();
		this.referenceNum = invoice.getReferenceNum();
		this.timestamp = invoice.getDate();
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

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
