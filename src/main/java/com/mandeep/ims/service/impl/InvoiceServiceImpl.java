package com.mandeep.ims.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mandeep.ims.dto.CreateInvoiceDto;
import com.mandeep.ims.dto.CreateInvoiceResponseDto;
import com.mandeep.ims.dto.InvoiceResponseDto;
import com.mandeep.ims.entity.Customer;
import com.mandeep.ims.entity.Invoice;
import com.mandeep.ims.entity.ItemDetail;
import com.mandeep.ims.exception.CustomException;
import com.mandeep.ims.repository.CustomerRepository;
import com.mandeep.ims.repository.InvoiceRepository;
import com.mandeep.ims.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Value("${reference.number.prefix}")
	String prefix;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<InvoiceResponseDto> getAllInvoices() throws CustomException {
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
		return invoices;
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

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("/MM/yy");
			String generatedRefNum = prefix + inv.getId() + dtf.format(LocalDate.now());
			inv.setReferenceNum(generatedRefNum);
			invoiceRepository.save(inv);
		} catch (Exception e) {
			throw new CustomException("Some internal error occured. Please try again.");
		}
		return new CreateInvoiceResponseDto(inv);
	}

	@Override
	public CreateInvoiceResponseDto downloadInvoice(int id) {
		XWPFDocument document = new XWPFDocument();
		XWPFParagraph title = document.createParagraph();
		title.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun titleRun = title.createRun();
		titleRun.setText("Build Your REST API with Spring");

		FileOutputStream out;
		try {
			out = new FileOutputStream("testdoc.docx");
			document.write(out);
			out.close();
			document.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
