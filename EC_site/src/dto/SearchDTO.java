package dto;

import java.io.Serializable;
//検索条件を取得する為のクラス
public class SearchDTO implements Serializable {
	private String productName;
	private int categoryCode;
	private int lowPrice;
	private int upPrice;
	private int recommendCode;
	private int limit;
	private int offset;
	
	public SearchDTO() {}
	public SearchDTO(String productName,int categoryCode,int lowPrice,int upPrice,int recommendCode,int limit,int offset) {
		this.productName = productName;
		this.categoryCode = categoryCode;
		this.lowPrice = lowPrice;
		this.upPrice = upPrice;
		this.categoryCode = categoryCode;
		this.limit = limit;
		this.offset = offset;
	}
	public int getRecommendCode() {
		return recommendCode;
	}
	public void setRecommendCode(int recommendCode) {
		this.recommendCode = recommendCode;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
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
