package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexception.FrameException;

public class DriverFactory {
	private WebDriver driver;
	public Properties prop;
     public OptionsManager opt;
     public static String highLightVar;
     public static ThreadLocal<WebDriver> tLDriver=new ThreadLocal<WebDriver>();
	public WebDriver initDriver(Properties prop)
	{
		highLightVar=prop.getProperty("highlight");
		 opt=new OptionsManager(prop);
		switch(prop.getProperty("browser").toLowerCase().trim())
		{
		case "chrome":
			driver=new ChromeDriver(opt.getChromeOptions());
			tLDriver.set(driver);
			break;
		case "firefox":
			driver=new FirefoxDriver(opt.getFirefoxOptions());
			tLDriver.set(driver);
			break;
		case "safari":
			driver=new SafariDriver();
			tLDriver.set(driver);
			break;
		case "edge":
			driver=new EdgeDriver();
			tLDriver.set(driver);
			break;
			default:
				System.out.println("Please pass the correct browser:");
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		//return driver;
		return getDriver();
	}
	public static WebDriver getDriver()
	{
		return tLDriver.get();
	}
	public Properties initProp()
	{
		 prop=new Properties();
		FileInputStream ip=null;
		//maven clean install -Denv
		//maven clean install
	
		String envName=System.getProperty("env");
		try {
			if (envName == null) {
				System.out.println("no env is given...hence running it on QA env..."+envName);
				ip = new FileInputStream("./src/main/resources/config/qa.config.properties");

			} else {
				System.out.println("Running test cases on env: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "dev":
					ip = new FileInputStream("./src/main/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/main/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/main/resources/config/prod.config.properties");
					break;

				default:
					System.out.println("plz pass the right env name...." + envName);
					throw new FrameException("NOVALIDENVGIVEN");
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public static String getScreenshot()
	{
		File scrfile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir") +"/Screenshot/" +System.currentTimeMillis() +".png";
		File destination=new File(path);
		try {
			FileUtils.copyFile(scrfile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}












