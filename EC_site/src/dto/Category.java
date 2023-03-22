package dto;

import java.io.Serializable;

public class Category implements Serializable {
	private int category_code;
	private int category_type;
	private String category_name;
	private int deleteFlg;
	private String createDatetime;
	private String updateDatetime;
	
	public Category() {}
	
	public Category(int category_code,String category_name) {
		this.category_code = category_code;
		this.category_name = category_name;
	}
	
	public Category(int category_code,int category_type,String category_name,int deleteFlg,String createDatetime,String updateDatetime) {
		this.category_code = category_code;
		this.category_type = category_type;
		this.category_name = category_name;
		this.deleteFlg = deleteFlg;
		this.createDatetime = createDatetime;
		this.updateDatetime = updateDatetime;
	}

	public int getCategory_code() {
		return category_code;
	}

	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}

	public int getCategory_type() {
		return category_type;
	}

	public void setCategory_type(int category_type) {
		this.category_type = category_type;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
