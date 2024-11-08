package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;
	public JavascriptExecutor  js;
	JavaScriptUtil(WebDriver driver)
	{
		this.driver=driver;
		js=(JavascriptExecutor)this.driver;
	}
	
	public void flash(WebElement ele)
	{
		
		String bgcolor=ele.getCssValue("backgroundColor");
		for(int i=0;i<10;i++)
		{
			changeColor("rgb(0,200,0)",ele);
			changeColor(bgcolor,ele);
		}
		
	}
	public void changeColor(String color,WebElement ele)
	{
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'", ele);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
