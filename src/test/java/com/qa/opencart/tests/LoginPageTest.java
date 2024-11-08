package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppContants;

public class LoginPageTest extends BaseTest {
	
	
	@Test(retryAnalyzer=com.qa.opencart.listeners.Retry.class)
	public void loginPageTitleTest()
	{
		String text=loginPage.getLoginPageTitle();
		Assert.assertEquals(text,AppContants.LOGIN_PAGE_TITLE);
	}
	@Test
	public void getLoginURLTest()
	{
		String act=loginPage.getLoginURL();
		Assert.assertTrue(act.contains(AppContants.LOGIN_PAGE_URL));
	}
	@Test
	public void isForgetPwdLinkExistTest()
	{
		Assert.assertTrue(loginPage.isForgetPwdLinkExist());
	}
	@Test
	public void loginTest()
	{
		accPage=loginPage.doLogin(prop.getProperty("userName"),prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	


