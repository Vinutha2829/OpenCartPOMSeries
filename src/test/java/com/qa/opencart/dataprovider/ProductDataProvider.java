package com.qa.opencart.dataprovider;

import org.testng.annotations.DataProvider;

import com.qa.opencart.pojo.Product;

public class ProductDataProvider {
	@DataProvider(name="productData")
	public Object[][] getProductDataTest()
	{
		return new Object[][] {
			{new Product("Macbook","MacBook Pro",4)},
			{new Product("iMac","iMac",3)},
			{new Product("samsung","Samsung Galaxy Tab 10.1",7)},
		};
	}
	@DataProvider
	public Object[][] getProductNameData()
	{
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"iMac","iMac"},
			{"samsung","Samsung Galaxy Tab 10.1"},
		};
	}
	@DataProvider
	public Object[][] getSelectProductImageCount()
	{
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"iMac","iMac",3},
			{"samsung","Samsung Galaxy Tab 10.1",7},
		};
	}
	@DataProvider(name="searchProductName")
	public Object[][] getProductCount()
	{
		return new Object[][] {
			{"Macbook",3},
			{"iMac",1},
			{"samsung",2},
		};
	}
	@DataProvider(name="productInfoDetails")
	public Object[][] getProductInfoDetails()
	{
		return new Object[][] {
			{"Macbook","MacBook Pro","Product 18","In Stock"},
			{"iMac","iMac","Product 14","Out Of Stock"},
			{"samsung","Samsung Galaxy Tab 10.1","SAM1","Pre-Order"},
		};
	}

}


















