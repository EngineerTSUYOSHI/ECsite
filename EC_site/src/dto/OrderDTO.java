package dto;

import java.io.Serializable;

public class OrderDTO implements Serializable {
	private int categoryCode;
	private String categoryName; 
	
	public OrderDTO() {}
	public OrderDTO(int categoryCode ,String categoryName) {
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	public int getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
