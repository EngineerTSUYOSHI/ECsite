package dto;

import java.io.Serializable;

public class PurchaseDTO implements Serializable {
	private String productName;
	private int searchCategoryCode;
	private String searchLowPrice;
	private String searchUpPrice;
	private int orderCode;
	private Product product ;
	private String errorMessage;
	private String applicationNumber;
	private int quantity;
	private String quantityErrorMessage;
	private int totalPrice;
	private int nowPage;
	private int productNumber;
	
	public PurchaseDTO() {}
	public PurchaseDTO(String productName, int searchCategoryCode, String searchLowPrice, String searchUpPrice, int orderCode,
						Product product, String errorMessage, String applicationNumber, int quantity, String quantityErrorMessage,
						int totalPrice, int nowPage, int productNumber) {
		this.productName = productName;
		this.searchCategoryCode = searchCategoryCode;
		this.searchLowPrice = searchLowPrice; 
		this.searchUpPrice = searchUpPrice; 
		this.orderCode = orderCode;
		this.product = product;
		this.errorMessage = errorMessage;
		this.applicationNumber = applicationNumber; 
		this.quantity = quantity;
		this.quantityErrorMessage = quantityErrorMessage;
		this.totalPrice = totalPrice;
		this.nowPage = nowPage;
		this.productNumber = productNumber;
	}
	public int getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductName() {
		return productName;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getSearchCategoryCode() {
		return searchCategoryCode;
	}
	public void setSearchCategoryCode(int searchCategoryCode) {
		this.searchCategoryCode = searchCategoryCode;
	}
	public String getSearchLowPrice() {
		return searchLowPrice;
	}
	public void setSearchLowPrice(String searchLowPrice) {
		this.searchLowPrice = searchLowPrice;
	}
	public String getSearchUpPrice() {
		return searchUpPrice;
	}
	public void setSearchUpPrice(String searchUpPrice) {
		this.searchUpPrice = searchUpPrice;
	}
	public int getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(int orderCode) {
		this.orderCode = orderCode;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getQuantityErrorMessage() {
		return quantityErrorMessage;
	}
	public void setQuantityErrorMessage(String quantityErrorMessage) {
		this.quantityErrorMessage = quantityErrorMessage;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
