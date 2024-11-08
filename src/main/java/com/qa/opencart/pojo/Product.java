package com.qa.opencart.pojo;

public class Product {
	private String searchKey;
	private String productName;
	private int productImageCount;
	public Product(String searchKey, String productName, int productImageCount) {
		this.searchKey = searchKey;
		this.productName = productName;
		this.productImageCount = productImageCount;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductImageCount() {
		return productImageCount;
	}
	public void setProductImageCount(int productImageCount) {
		this.productImageCount = productImageCount;
	}
	@Override
	public String toString() {
		return "Product [searchKey=" + searchKey + ", productName=" + productName + ", productImageCount="
				+ productImageCount + "]";
	}
	
	

}
