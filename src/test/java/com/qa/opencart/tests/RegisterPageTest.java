package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppContants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest  extends BaseTest{
	@BeforeClass
	public void regSetUp()
	{
		regPage=loginPage.navigateToRegisterPage();
	}
//	@DataProvider(name = "regData")
//	public Object[][] getUserRegTestData() {
//		return new Object[][] {
//			{"abhi", "anand", "9876545678","Selenium@123" ,"yes"},
//			{"robinson", "matinez", "9876545600", "Selenium@123", "no"},
//			{"amber", "automation", "9876545998", "Selenium@123", "yes"},
//		};
//	}
	@DataProvider(name = "regData")
	public Object[][] getUserRegTestDataWithExcel() {
		return ExcelUtil.productData("register");
		
	}
	@Test(dataProvider="regData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		String actRegSuccMessg = 
				regPage.registerUser(firstName, lastName, randomEmailGenerate(), telephone, password,subscribe);
		Assert.assertEquals(actRegSuccMessg, AppContants.USER_RESG_SUCCESS_MESSG);
	}
	public String randomEmailGenerate()
	{
		return "automation"+System.currentTimeMillis()+"@gmail.com";
	}

}
