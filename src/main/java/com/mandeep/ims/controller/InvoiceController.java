package com.mandeep.ims.controller;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mandeep.ims.dto.AllInvoicesResponseDto;
import com.mandeep.ims.dto.CreateInvoiceDto;
import com.mandeep.ims.dto.CreateInvoiceResponseDto;
import com.mandeep.ims.dto.InvoiceResponseDto;
import com.mandeep.ims.exception.CustomException;
import com.mandeep.ims.service.InvoiceService;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	@GetMapping
	public ResponseEntity<AllInvoicesResponseDto> getAll() throws URISyntaxException {
		AllInvoicesResponseDto response = invoiceService.getAllInvoices();
		return ok().body(response);
	}

	@PostMapping
	public ResponseEntity<CreateInvoiceResponseDto> createInvoice(@RequestBody CreateInvoiceDto createInvoiceDto) {
		try {
			CreateInvoiceResponseDto createInvoiceResponseDto = invoiceService.createInvoice(createInvoiceDto);
			return ok().body(createInvoiceResponseDto);
		} catch (CustomException e) {
			e.printStackTrace();
			return notFound().build();
		}

	}

}
