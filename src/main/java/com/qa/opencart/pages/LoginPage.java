package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppContants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.constructor of the page class
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	//2. maintain Private By locator
	private By emailId=By.id("input-email");
	private  By pwd=By.id("input-password");
	private By loginBtn=By.cssSelector("input[value='Login']");
	private  By forgotPwdlink=By.linkText("Forgotten Password");
	private  By footerLinks=By.xpath("//div[@class='row']//div//ul[@class='list-unstyled']/li/a");
	private By wrongCredentials=By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private  By registerlink=By.linkText("Register");
	//3.public page Actions/Method
	public String getLoginPageTitle()
	{
		String title= eleUtil.waitForTitleIsAndCapture(AppContants.LOGIN_PAGE_TITLE,AppContants.SHORT_TIME);
		System.out.println("Login Page Title:"+title);
		return title;
	}
	public String getLoginURL()
	{
		String url= eleUtil.waitForURLContainsAndCapture(AppContants.LOGIN_PAGE_URL, AppContants.SHORT_TIME);
		System.out.println("Login page Url:"+url);
		return url;
	}
	public boolean isForgetPwdLinkExist()
	{
		return eleUtil.checkElementIsDisplayed(forgotPwdlink);
		
	}
	public AccountsPage doLogin(String userName,String password)
	{
		eleUtil.waitForElementVisible(emailId, AppContants.MEDIUM_TIME).sendKeys(userName);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(loginBtn);
		System.out.println("username:"+userName+"  Password:"+password);
		return new AccountsPage(driver);
	}
	public List<String> getFooterLinksLsit()
	{
		List<WebElement> list=eleUtil.waitForElementsVisible(footerLinks,AppContants.MEDIUM_TIME);
		List<String> footerLinks=new ArrayList<String>();
		for(WebElement e:list)
		{
			String text=e.getText();
			footerLinks.add(text);
		}
		return footerLinks;
	}
	public String doLoginWithWrongCredentials(String userName,String password)
	{
		eleUtil.waitForElementVisible(emailId, AppContants.MEDIUM_TIME);
		eleUtil.doSendKeys(emailId,userName);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(loginBtn);
		//return new AccountsPage(driver);
		return eleUtil.doGetElementText(wrongCredentials);
	}
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerlink);
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
