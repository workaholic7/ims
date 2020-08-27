package com.mandeep.ims.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.mandeep.ims.util.Util;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private String referenceNum;

	private long date;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoiceId", nullable = false)
	private List<ItemDetail> itemList;

	private float total;

	public Invoice() {
		super();
	}

	public Invoice(float total, Customer cus, List<ItemDetail> itemDetail) {
		this.customer = cus;
		this.itemList = itemDetail;
		this.date = Util.getCurrentTimeStamp();
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getReferenceNum() {
		return referenceNum;
	}

	public void setReferenceNum(String referenceNum) {
		this.referenceNum = referenceNum;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public List<ItemDetail> getItemList() {
		return itemList;
	}

	public void setItemList(List<ItemDetail> itemList) {
		this.itemList = itemList;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

}
