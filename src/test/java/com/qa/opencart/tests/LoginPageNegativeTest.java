package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppContants;

public class LoginPageNegativeTest  extends BaseTest{
	
	@DataProvider
	public Object[][] inCorrectLoginTestData(){
		return new Object[][] {
			{"vinubnhaggb@gmail",""},
			{"","sdfghj"},
			{"",""},
			{"Vinuece2905@gmail.com","sfghg@29"},
			{"vinuece2905@gmail.com","asdgh@29"},
			{"Vinuece2905","Vinutha@29"}
		};
		
	}
	@Test(dataProvider="inCorrectLoginTestData")
	public void loginWithWrongCredentialsTest(String user,String pwd)
	{
		String message=loginPage.doLoginWithWrongCredentials(user, pwd);
		//Assert.assertEquals(message, " Warning: No match for E-Mail Address and/or Password.");
		System.out.println(message);
		Assert.assertTrue(message.contains(AppContants.LOGIN_ERROR_MESSG));
	}

}
