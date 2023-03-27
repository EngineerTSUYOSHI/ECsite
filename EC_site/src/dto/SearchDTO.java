package dto;

import java.io.Serializable;
//検索条件を取得する為のクラス
public class SearchDTO implements Serializable {
	private String productName;
	private int categoryCode;
	private int lowPrice;
	private int upPrice;
	
	public SearchDTO() {}
	public SearchDTO(String productName,int categoryCode,int lowPrice,int upPrice) {
		this.productName = productName;
		this.categoryCode = categoryCode;
		this.lowPrice = lowPrice;
		this.upPrice = upPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public int getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}
	public int getUpPrice() {
		return upPrice;
	}
	public void setUpPrice(int upPrice) {
		this.upPrice = upPrice;
	}
}
