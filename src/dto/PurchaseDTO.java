package dto;

import java.io.Serializable;

public class PurchaseDTO implements Serializable {
	private Product product;
	
	public PurchaseDTO() {}
	public PurchaseDTO(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
