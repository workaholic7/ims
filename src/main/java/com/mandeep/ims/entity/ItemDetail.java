package com.mandeep.ims.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.mandeep.ims.dto.ItemDto;

@Entity
public class ItemDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Enumerated(EnumType.ORDINAL)
	private ItemType itemType;

	private String description;

	private int quantity;

	private float unitPrice;

	private float amount;

	@ManyToOne(fetch = FetchType.LAZY)
	private Invoice invoiceId;

	public ItemDetail() {
		super();
	}

	public ItemDetail(ItemDto itemDto) {
		this.itemType = ItemType.valueOf(itemDto.getName());
		this.description = itemDto.getDescription();
		this.quantity = itemDto.getQuantity();
		this.unitPrice = itemDto.getUnitPrice();
		this.amount = itemDto.getAmount();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ItemType getType() {
		return itemType;
	}

	public void setType(ItemType type) {
		this.itemType = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Invoice getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Invoice invoice) {
		this.invoiceId = invoice;
	}

}
