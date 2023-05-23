package dto;

import java.io.Serializable;

public class Category implements Serializable {
	private int categoryCode;
	private int categoryType;
	private String categoryName;
	private int deleteFlg;
	private String createDatetime;
	private String updateDatetime;
	private String productName;
	private int lowPrice;
	private int upPrice;
	
	public Category() {}
	
	public Category(int categoryCode,String categoryName) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	
	public Category(int categoryCode,int categoryType,String categoryName,int deleteFlg,String createDatetime,String updateDatetime) {
		this.categoryCode = categoryCode;
		this.categoryType = categoryType;
		this.categoryName = categoryName;
		this.deleteFlg = deleteFlg;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	public Category(String productName, int categoryCode, int lowPrice, int upPrice) {
		this.productName = productName;
		this.categoryCode = categoryCode;
		this.lowPrice = lowPrice;
		this.upPrice = upPrice;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
