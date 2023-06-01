package dto;

import java.io.Serializable;

public class Information implements Serializable {
	private String entryNumber;
	private String userId;
	private int productNumber ;
	private int quantity;
	private int productPrice;
	
	public Information() {}
	
	public Information(String entryNumber, String userId, int productNumber, int quantity, int productPrice) {
		this.entryNumber = entryNumber;
		this.userId = userId;
		this.productNumber = productNumber;
		this.quantity = quantity;
		this.productPrice = productPrice;
	}
	
	public String getEntryNumber() {
		return entryNumber;
	}
	public void setEntryNumber(String entryNumber) {
		this.entryNumber = entryNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	
}
