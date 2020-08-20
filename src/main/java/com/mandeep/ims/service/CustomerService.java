package com.mandeep.ims.service;

import java.util.List;

import com.mandeep.ims.dto.CustomerDto;
import com.mandeep.ims.entity.Customer;
import com.mandeep.ims.exception.CustomException;

public interface CustomerService {
	
	public Customer saveCustomer(CustomerDto customerDto) throws CustomException;

	public Customer getCustomerById(int id) throws CustomException;

	public void deleteCustomerById(int id) throws CustomException;

	public List<Customer> getAllCustomers();

	public Customer updateCustomer(int id, CustomerDto customerDto) throws CustomException;

}
