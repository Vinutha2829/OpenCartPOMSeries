package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppContants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.constructor the class
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	//2.private BY locator
	private By logout=By.linkText("Logout");
	private By myAccount=By.linkText("My Account");
	private By accHeader=By.cssSelector("div#content h2");
	private By search=By.cssSelector("input[name='search']");
	private By searchIcon=By.xpath("//span[@class='input-group-btn']");
	
	//3.public Page Actions/Methods
	public String getAccPageTitle()
	{
		 String title=eleUtil.waitForTitleIsAndCapture(AppContants.ACC_PAGE_TITLE,AppContants.SHORT_TIME);
		System.out.println("Account Page Title:"+title);
		return title;
	}
	public boolean isLogoutLinkExist()
	{
		return eleUtil.checkElementIsDisplayed(logout);
	}
	public boolean isMyAccountLinkExist()
	{
		return eleUtil.checkElementIsDisplayed(myAccount);
	}
	public List<String> getAccountPageHeadersList()
	{
		List<WebElement>list=eleUtil.waitForElementsVisible(accHeader, AppContants.LONG_TIME);
		List<String>accPageHeadersList=new ArrayList<String>();
		for(WebElement e:list)
		{
			String text=e.getText();
			accPageHeadersList.add(text);
		}
		return accPageHeadersList;
	}
	public ResultsPage doSearch(String searchTerm)
	{
		eleUtil.waitForElementVisible(search, AppContants.LONG_TIME);
		eleUtil.doSendKeys(search, searchTerm);
		eleUtil.doClick(searchIcon);
		 return new ResultsPage(driver);
	}

}
