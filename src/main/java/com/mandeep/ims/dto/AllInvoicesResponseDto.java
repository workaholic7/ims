package com.mandeep.ims.dto;

import java.util.List;

public class AllInvoicesResponseDto {

	private long totalCustomers;
	
	private long totalInvoices;
	
	private List<InvoiceResponseDto> invoices;

	public AllInvoicesResponseDto(long totalCustomers, long totalInvoices, List<InvoiceResponseDto> invoices) {
		this.totalCustomers = totalCustomers;
		this.totalInvoices = totalInvoices;
		this.invoices = invoices;
	}

	public long getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public long getTotalInvoices() {
		return totalInvoices;
	}

	public void setTotalInvoices(long totalInvoices) {
		this.totalInvoices = totalInvoices;
	}

	public List<InvoiceResponseDto> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<InvoiceResponseDto> invoices) {
		this.invoices = invoices;
	}
	
	
}
