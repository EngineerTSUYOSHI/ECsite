package dto;

import java.io.Serializable;

public class Product implements Serializable{
	private int productNumber;
	private String productName;
	private int category_code;
	private int productPrice;
	private int recommend;
	private String validateStartDate;
	private String validateEndDate;
	private int deleteFlg;
	private String product_img;
	private String createDatetime;
	private String updateDatetime;
	
	
	public Product() {}
	
	public Product(int productNumber,String productName,int category_code,int productPrice,int recommend
			,String validateStartDate,String validateEndDate ,int deleteFlg, String product_img) {
		this.productNumber = productNumber; 
		this.productName = productName;
		this.category_code = category_code;
		this.productPrice = productPrice;
		this.recommend = recommend;
		this.validateStartDate =validateStartDate;
		this.validateEndDate = validateEndDate;
		this.deleteFlg = deleteFlg;
		this.product_img = product_img;
	}
	
	public Product(int productNumber,String productName,int category_code,int productPrice,int recommend
			,String validateStartDate,String validateEndDate ,int deleteFlg,String createDatetime,String updateDatetime,String product_img) {
		this(productNumber, productName,category_code,productPrice,recommend,validateStartDate,validateEndDate,deleteFlg,product_img);
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}
	
	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
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

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getValidateStartDate() {
		return validateStartDate;
	}

	public void setValidateStartDate(String validateStartDate) {
		this.validateStartDate = validateStartDate;
	}

	public String getValidateEndDate() {
		return validateEndDate;
	}

	public void setValidateEndDate(String validateEndDate) {
		this.validateEndDate = validateEndDate;
	}

	public int getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(String createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(String updateDatetime) {
		this.updateDatetime = updateDatetime;
	}
	
	
}
