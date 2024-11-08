package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppContants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetUp()
	{
		accPage=loginPage.doLogin(prop.getProperty("userName"),prop.getProperty("password"));
	}
	
	@Test
	public void accPageTitleTest()
	{
		Assert.assertEquals(accPage.getAccPageTitle(),AppContants.ACC_PAGE_TITLE);
	}
	@Test
	public void isLogoutLinkExistTest()
	{
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	@Test
	public void accPageHeadersCountTest()
	{
		Assert.assertEquals(accPage.getAccountPageHeadersList().size(), 4);
	}
	@Test
	public void accPageHeadersTest()
	{
		List<String>actHeadersList=accPage.getAccountPageHeadersList();
		//Assert.assertTrue(accHeadersValueList.contains("My Account"));
		List<String>expHeaderList=AppContants.EXP_ACCOUNTS_HEADER_LIST;
		Collections.sort(actHeadersList);
		Collections.sort(expHeaderList);
		Assert.assertEquals(actHeadersList,expHeaderList);
	}
	@Test
	public void isMyAccLinkExistTest()
	{
		Assert.assertTrue(accPage.isMyAccountLinkExist());
	}
	

}
