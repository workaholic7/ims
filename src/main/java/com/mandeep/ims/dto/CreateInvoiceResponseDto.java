package com.mandeep.ims.dto;

import com.mandeep.ims.entity.Invoice;
import com.mandeep.ims.util.Util;

public class CreateInvoiceResponseDto {

	private String referenceNumber;
	private String date;

	public CreateInvoiceResponseDto(Invoice inv) {
		this.referenceNumber = inv.getReferenceNum();
		this.setDate(Util.formatLocalDateTime(inv.getDate()));
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	

	
}
