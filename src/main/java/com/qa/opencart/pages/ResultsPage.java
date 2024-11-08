package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppContants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.constructor of the class
	public ResultsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	private By resultsProduct=By.cssSelector("div.product-layout.product-grid");
	//2.public page actions/methods
	public String getResultsPageTitle(String searchKey)
	{
		return eleUtil.waitForTitleIsAndCapture(searchKey,5);
//		System.out.println("Results Page Title:"+title);
//		return title;
	}
	public int getProductResultsCount()
	{
		int productCount=eleUtil.waitForElementsVisible(resultsProduct, AppContants.LONG_TIME).size();
		System.out.println("Product search results count:======>"+productCount);
		return productCount;
	}
	public ProductInfoPage selectProduct(String productName)
	{
		By productNameLocator=By.linkText(productName);
		eleUtil.doClick(productNameLocator);
		return new ProductInfoPage(driver);
		
	}

}
