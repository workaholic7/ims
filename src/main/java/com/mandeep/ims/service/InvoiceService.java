package com.mandeep.ims.service;

import java.util.List;

import com.mandeep.ims.dto.CreateInvoiceDto;
import com.mandeep.ims.dto.CreateInvoiceResponseDto;
import com.mandeep.ims.dto.InvoiceResponseDto;
import com.mandeep.ims.exception.CustomException;

public interface InvoiceService {

	public List<InvoiceResponseDto> getAllInvoices();

	public CreateInvoiceResponseDto createInvoice(CreateInvoiceDto createInvoiceDto) throws CustomException;

}
