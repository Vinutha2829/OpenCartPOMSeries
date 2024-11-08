package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataprovider.ProductDataProvider;
import com.qa.opencart.pojo.Product;

public class SearchTest extends BaseTest {
	@BeforeClass
	public void searchTestSetUp()
	{
		accPage=loginPage.doLogin(prop.getProperty("userName"), "password");
	}
//	@DataProvider
//	public Object[][] getProductSearchKey()
//	{
//		return new Object[][]{
//			{"Macbook"},
//			{"iMac"},
//			{"Samsung"}
//				};
//	}
	@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void searchProductResultCountTest(Product prod)
	{
		resultPage=accPage.doSearch(prod.getSearchKey());
		int actCount=resultPage.getProductResultsCount();
		Assert.assertTrue(actCount>0);
	}
	@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void searchPageTitleTest(Product prod)
	{
		resultPage=accPage.doSearch(prod.getSearchKey());
		String actTitle=resultPage.getResultsPageTitle(prod.getSearchKey());
		System.out.println("searchPageTitleTest:====>"+actTitle);
		Assert.assertEquals(actTitle, "Search - "+ prod.getSearchKey());
	}
	
	@Test(dataProvider="searchProductName",dataProviderClass=ProductDataProvider.class)
	public void getProductResultsCount(String searchKey,int count)
	{
		resultPage=accPage.doSearch(searchKey);
		int actProductCount=resultPage.getProductResultsCount();
		Assert.assertEquals(actProductCount,count);
	}

	@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void selectProductTest(Product prod)
	{
		resultPage=accPage.doSearch(prod.getSearchKey());
		prodPage=resultPage.selectProduct(prod.getProductName());
		String actResult=prodPage.getProductHeaderName();
		System.out.println("Selected Product Header  Name:===>"+actResult);
		Assert.assertEquals(actResult,prod.getProductName());
	}

	@Test(dataProvider="productData",dataProviderClass=ProductDataProvider.class)
	public void selectedProductImageCount(Product prod)
	{
		resultPage=accPage.doSearch(prod.getSearchKey());
		prodPage=resultPage.selectProduct(prod.getProductName());
		int actCount=prodPage.getProductImagesCount();
		System.out.println("Selected Product Image Count:===>"+actCount);
		Assert.assertEquals(actCount,prod.getProductImageCount());
	}
	

}
