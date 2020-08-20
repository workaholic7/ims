package com.mandeep.ims.dto;

import com.mandeep.ims.entity.Invoice;

public class CreateInvoiceResponseDto {

	private String referenceNumber;
	private long timestamp;

	public CreateInvoiceResponseDto(Invoice inv) {
		this.referenceNumber = inv.getReferenceNum();
		this.timestamp = inv.getDate();
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
