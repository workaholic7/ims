package com.mandeep.ims.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mandeep.ims.dto.AllInvoicesResponseDto;
import com.mandeep.ims.dto.CreateInvoiceDto;
import com.mandeep.ims.dto.CreateInvoiceResponseDto;
import com.mandeep.ims.entity.ItemType;
import com.mandeep.ims.exception.CustomException;

public interface InvoiceService {

	public AllInvoicesResponseDto getAllInvoices() throws CustomException;

	public CreateInvoiceResponseDto createInvoice(CreateInvoiceDto createInvoiceDto) throws CustomException;

	public ResponseEntity downloadInvoice(int id) throws CustomException;

	public List<ItemType> getItemTypes();

	public void deleteInvoiceById(int id) throws CustomException;

}
