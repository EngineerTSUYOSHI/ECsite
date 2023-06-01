package dto;

public class CompleteDTO {
	private String productImg;
	private String productName;
	private String entryNumber;
	private String searchProductName;
	private int searchCategoryCode;
	private int searchLowPrice;
	private int searchUpPrice;
	private int searchRecommendCode;
	private int searchPageNumber;
	private int errorCode;
	private String quantityErrorMessage;
	private String entryNumberErrorMessage;
	private int productNumber; 
	
	public CompleteDTO() {}
	public CompleteDTO(String productImg,String productName,String entryNumber,String searchProductName,
			int searchCategoryCode,int searchLowPrice,int searchUpPrice,int searchRecommendCode,
			int searchPageNumber,int errorCode,String quantityErrorMessage,String entryNumberErrorMessage,int productNumber) {
		
		this.productImg = productImg;
		this.productName = productName;
		this.entryNumber = entryNumber;
		this.searchProductName = searchProductName;
		this.searchCategoryCode = searchCategoryCode;
		this.searchLowPrice = searchLowPrice;
		this.searchUpPrice = searchUpPrice;
		this.searchRecommendCode = searchRecommendCode;
		this.searchPageNumber = searchPageNumber;
		this.errorCode = errorCode;
		this.quantityErrorMessage = quantityErrorMessage;
		this.entryNumberErrorMessage = entryNumberErrorMessage;
		this.productNumber = productNumber;
	}
	public int getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getEntryNumber() {
		return entryNumber;
	}
	public void setEntryNumber(String entryNumber) {
		this.entryNumber = entryNumber;
	}
	public int getSearchPageNumber() {
		return searchPageNumber;
	}
	public void setSearchPageNumber(int searchPageNumber) {
		this.searchPageNumber = searchPageNumber;
	}
	public String getSearchProductName() {
		return searchProductName;
	}
	public void setSearchProductName(String searchProductName) {
		this.searchProductName = searchProductName;
	}
	public int getSearchCategoryCode() {
		return searchCategoryCode;
	}
	public void setSearchCategoryCode(int searchCategoryCode) {
		this.searchCategoryCode = searchCategoryCode;
	}
	public int getSearchLowPrice() {
		return searchLowPrice;
	}
	public void setSearchLowPrice(int searchLowPrice) {
		this.searchLowPrice = searchLowPrice;
	}
	public int getSearchUpPrice() {
		return searchUpPrice;
	}
	public void setSearchUpPrice(int searchUpPrice) {
		this.searchUpPrice = searchUpPrice;
	}
	public int getSearchRecommendCode() {
		return searchRecommendCode;
	}
	public void setSearchRecommendCode(int searchRecommendCode) {
		this.searchRecommendCode = searchRecommendCode;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getQuantityErrorMessage() {
		return quantityErrorMessage;
	}
	public void setQuantityErrorMessage(String quantityErrorMessage) {
		this.quantityErrorMessage = quantityErrorMessage;
	}
	public String getEntryNumberErrorMessage() {
		return entryNumberErrorMessage;
	}
	public void setEntryNumberErrorMessage(String entryNumberErrorMessage) {
		this.entryNumberErrorMessage = entryNumberErrorMessage;
	}
	
}
