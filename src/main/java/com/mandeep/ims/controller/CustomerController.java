package com.mandeep.ims.controller;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mandeep.ims.dto.CustomerDto;
import com.mandeep.ims.dto.CustomerNameResponseDto;
import com.mandeep.ims.dto.CustomerResponseDto;
import com.mandeep.ims.dto.ErrorResponseDto;
import com.mandeep.ims.entity.Customer;
import com.mandeep.ims.exception.CustomException;
import com.mandeep.ims.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
		List<CustomerResponseDto> customers = customerService.getAllCustomers();
		return ok().body(customers);
	}

	@GetMapping("/name")
	public ResponseEntity<List<CustomerNameResponseDto>> getAllCustomerNames() {
		List<CustomerNameResponseDto> customerNames;
		try {
			customerNames = customerService.getAllCustomerNames();
			return ok().body(customerNames);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity(new ErrorResponseDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") int id) throws URISyntaxException {
		CustomerDto customer;
		try {
			customer = customerService.getCustomerById(id);
			return ok().body(customer);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity(new ErrorResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody CustomerDto customerDto)
			throws URISyntaxException {
		Customer customer;
		try {
			customer = customerService.saveCustomer(customerDto);
			URI uri = new URI("/customer/" + customer.getId());
			return created(uri).body(customer);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity(new ErrorResponseDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @Valid @RequestBody CustomerDto customerDto)
			throws URISyntaxException {
		Customer customer;
		try {
			customer = customerService.updateCustomer(id, customerDto);
			URI uri = new URI("/customer/" + customer.getId());
			return created(uri).body(customer);
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity(new ErrorResponseDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Customer> deleteCustomerById(@PathVariable int id) {
		try {
			customerService.deleteCustomerById(id);
			return noContent().build();
		} catch (CustomException e) {
			e.printStackTrace();
			return new ResponseEntity(new ErrorResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
		}
	}
}
