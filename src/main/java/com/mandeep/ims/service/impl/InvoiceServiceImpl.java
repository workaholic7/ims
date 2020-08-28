package com.mandeep.ims.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mandeep.ims.dto.AllInvoicesResponseDto;
import com.mandeep.ims.dto.CreateInvoiceDto;
import com.mandeep.ims.dto.CreateInvoiceResponseDto;
import com.mandeep.ims.dto.InvoiceResponseDto;
import com.mandeep.ims.entity.Customer;
import com.mandeep.ims.entity.Invoice;
import com.mandeep.ims.entity.ItemDetail;
import com.mandeep.ims.exception.CustomException;
import com.mandeep.ims.repository.CustomerRepository;
import com.mandeep.ims.repository.InvoiceRepository;
import com.mandeep.ims.service.DocumentStorageService;
import com.mandeep.ims.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Value("${reference.number.prefix}")
	String prefix;
	
	@Value("${upload_dir}")
	String uploadDir;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
    private DocumentStorageService documneStorageService;

	@Override
	public AllInvoicesResponseDto getAllInvoices() throws CustomException {
		List<InvoiceResponseDto> invoices = new ArrayList<>();
		Iterable<Invoice> allInvoices = null;
		try {
			allInvoices = invoiceRepository.findAll();
		} catch (Exception e) {
			throw new CustomException("Some internal error occured. Please try again.");
		}

		Iterator<Invoice> invoiceItr = allInvoices.iterator();
		while (invoiceItr.hasNext()) {
			invoices.add(new InvoiceResponseDto(invoiceItr.next()));
		}
		return new AllInvoicesResponseDto(customerRepository.count(), invoiceRepository.count(), invoices);
	}

	@Override
	public CreateInvoiceResponseDto createInvoice(CreateInvoiceDto createInvoiceDto) throws CustomException {
		List<ItemDetail> itemDetail = null;
		Invoice inv = null;
		Customer cus = null;
		Optional<Customer> customer = customerRepository.findById(createInvoiceDto.getCustomerId());
		if (customer.isPresent()) {
			cus = customer.get();
		} else {
			throw new CustomException("Customer doesn't exist");
		}
		itemDetail = createInvoiceDto.getItems().stream().map(ItemDetail::new).collect(Collectors.toList());
		try {
			inv = invoiceRepository.save(new Invoice(createInvoiceDto.getTotal(), cus, itemDetail));

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("-MM-yy");
			String generatedRefNum = prefix + inv.getId() + dtf.format(LocalDate.now());
			inv.setReferenceNum(generatedRefNum);
			invoiceRepository.save(inv);
		} catch (Exception e) {
			throw new CustomException("Some internal error occured. Please try again.");
		}
		return new CreateInvoiceResponseDto(inv);
	}

	@Override
	public ResponseEntity downloadInvoice(int id) {
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if(invoice.isPresent()) {
			String fileName = uploadDir+"/"+"Invoice_"+invoice.get().getReferenceNum()+".pdf";
			Resource resource = null;
			if(fileName !=null && !fileName.isEmpty()) {
				try {
					resource = documneStorageService.loadFileAsResource(fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(resource==null) {
					try {
						resource = documneStorageService.createNewFile(invoice.get(), fileName);
					} catch (CustomException e) {
						return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
				}
				String contentType = "application/pdf";
				return ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(contentType))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
						.body(resource);	
			} else {
				return ResponseEntity.notFound().build();
			}


		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
