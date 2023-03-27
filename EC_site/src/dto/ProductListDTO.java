package dto;

import java.util.ArrayList;

public class ProductListDTO {
	private ArrayList<Category> categorys;
	private ArrayList<Product> products;
	private ArrayList<Category> recommends;
	private SearchDTO search;
	private int limit;
	private int offset;
	
	public ProductListDTO() {}
	public ProductListDTO(ArrayList<Product> products,ArrayList<Category> categorys
			,ArrayList<Category> recommends,SearchDTO search,int limit,int offset) {
		this.products = products;
		this.categorys = categorys;
		this.recommends = recommends;
		this.search = search;
		this.limit = limit;
		this.offset = offset;
	}
	
	public ArrayList<Category> getCategorys() {
		return categorys;
	}
	public void setCategorys(ArrayList<Category> categorys) {
		this.categorys = categorys;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public ArrayList<Category> getRecommends() {
		return recommends;
	}
	public void setRecommends(ArrayList<Category> recommends) {
		this.recommends = recommends;
	}
	public SearchDTO getSearch() {
		return search;
	}
	public void setSearch(SearchDTO search) {
		this.search = search;
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
}
