package com.qa.opencart.tests;

import java.util.Map;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataprovider.ProductDataProvider;
import com.qa.opencart.pojo.Product;

public class ProductPageInfoTest  extends BaseTest{
	@BeforeClass
	public void productPageInfoPageSetUp()
	{
		accPage=loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoTest()
	{
		resultPage=accPage.doSearch("Macbook");
		prodPage=resultPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap=prodPage.getProductInfo();
		System.out.println(productInfoMap);
		//Assert.assertEquals(productInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(productInfoMap.get("Reward Points"),"800");
		softAssert.assertEquals(productInfoMap.get("Availability"),"In Stock");
		softAssert.assertAll() ; // -->manendatory if not used Assert will perform but result will not display.
	}
//	@Test(dataProvider="productInfoDetails",dataProviderClass=ProductDataProvider.class)
//	public void productInfoTest(String searchKey,String productName,String productCode,String avail)
//	{
//		resultPage=accPage.doSearch(searchKey);
//		prodPage=resultPage.selectProduct(productName);
//		Map<String,String> productInfoMap=prodPage.getProductInfo();
//		System.out.println(productInfoMap);
//		//Assert.assertEquals(productInfoMap.get("Brand"),"Apple");
//		softAssert.assertEquals(productInfoMap.get("Product Code"),productCode);
//		softAssert.assertEquals(productInfoMap.get("Availability"),avail);
//		softAssert.assertAll() ; // -->manendatory if not used Assert will perform but result will not display.
//	}

}
