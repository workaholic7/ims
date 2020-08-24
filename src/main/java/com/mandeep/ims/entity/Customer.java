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

import org.springframework.beans.factory.annotation.Value;

import com.mandeep.ims.dto.CustomerDto;
import com.mandeep.ims.util.Util;

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
		this.setName(customerDto.getName());
		this.setPhoneNum(customerDto.getPhoneNum());
		this.address = address;
		this.setCompany(customerDto.getCompany());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		
		return Util.decrypt(name);
	}

	public void setName(String name) {
		this.name = Util.encrypt(name);
	}

	public String getPhoneNum() {
		return Util.decrypt(phoneNum);
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = Util.encrypt(phoneNum);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCompany() {
		return Util.decrypt(company);
	}

	public void setCompany(String company) {
		this.company = Util.encrypt(company);
	}

	public List<Invoice> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<Invoice> invoice) {
		this.invoice = invoice;
	}
}
