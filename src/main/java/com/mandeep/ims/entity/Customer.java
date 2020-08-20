package com.mandeep.ims.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.mandeep.ims.dto.CustomerDto;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 128)
	private String name;

	@Column(length = 32)
	private String phoneNum;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	private String company;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private List<Invoice> invoice;

	public Customer() {
		super();
	}

	public Customer(CustomerDto customerDto, Address address) {
		this.name = customerDto.getName();
		this.phoneNum = customerDto.getPhoneNum();
		this.address = address;
		this.company = customerDto.getCompany();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}
}
