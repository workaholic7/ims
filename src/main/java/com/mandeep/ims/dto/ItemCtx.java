package com.mandeep.ims.dto;

public class ItemCtx {

		public ItemCtx(String description, int quantity, float unitPrice, float amount) {
			super();
			this.description = description;
			this.quantity = quantity;
			this.unitPrice = unitPrice;
			this.amount = amount;
		}

		private String description;
		private int quantity;
		private float unitPrice;
		private float amount;

		public String getDescription() {
			return description;
		}

		public void setDescription(String itemDescription) {
			this.description = itemDescription;
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

}
