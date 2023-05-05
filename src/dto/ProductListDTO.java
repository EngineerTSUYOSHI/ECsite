package dto;

import java.util.ArrayList;

public class ProductListDTO {
	private ArrayList<Category> categorys;
	private ArrayList<Product> products;
	private ArrayList<Category> recommends;
	private SearchDTO search;
	private int now_page;
	
	public ProductListDTO() {}
	public ProductListDTO(ArrayList<Product> products,ArrayList<Category> categorys
			,ArrayList<Category> recommends,SearchDTO search,int now_page) {
		this.products = products;
		this.categorys = categorys;
		this.recommends = recommends;
		this.search = search;
		this.now_page = now_page;
	}
	
	public int getNow_page() {
		return now_page;
	}
	public void setNow_page(int now_page) {
		this.now_page = now_page;
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
}
