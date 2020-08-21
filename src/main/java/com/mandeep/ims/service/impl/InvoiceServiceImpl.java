package com.mandeep.ims.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.mandeep.ims.repository.ItemDetailsRepository;
import com.mandeep.ims.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ItemDetailsRepository itemDetailsRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public AllInvoicesResponseDto getAllInvoices() {
		List<InvoiceResponseDto> invoices = new ArrayList<>();
		Iterable<Invoice> allInvoices = invoiceRepository.findAll();
		Iterator<Invoice> invoiceItr = allInvoices.iterator();
		while (invoiceItr.hasNext()) {
			invoices.add(new InvoiceResponseDto(invoiceItr.next()));
		}
		return new AllInvoicesResponseDto(customerRepository.count(), invoiceRepository.count(), invoices);
	}

	@Override
	public CreateInvoiceResponseDto createInvoice(CreateInvoiceDto createInvoiceDto) throws CustomException {
		List<ItemDetail> itemDetail = null;
		Customer cus = null;
		Optional<Customer> customer = customerRepository.findById(createInvoiceDto.getCustomerId());
		if (customer.isPresent()) {
			cus = customer.get();
		} else {
			throw new CustomException("Customer doesn't exist");
		}
		itemDetail = createInvoiceDto.getItems().stream().map(ItemDetail::new).collect(Collectors.toList());

		Invoice inv = invoiceRepository.save(new Invoice(createInvoiceDto.getTotal(), cus, itemDetail));
		// itemDetailsRepository.saveAll(itemDetail);
		return new CreateInvoiceResponseDto(inv);
	}
}
