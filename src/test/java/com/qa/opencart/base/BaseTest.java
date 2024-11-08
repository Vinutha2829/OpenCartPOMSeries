package com.qa.opencart.base;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ResultsPage resultPage;
	protected ProductInfoPage prodPage;
	protected RegisterPage regPage;
	protected DriverFactory df;
	protected Properties prop;
	protected SoftAssert softAssert;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional("chrome")String browserName)
	
	{
		df=new DriverFactory();
		prop=df.initProp();
		if(browserName!=null)
		{
			prop.setProperty("browser",browserName);
		}
		 driver=df.initDriver(prop);
		 loginPage=new LoginPage(driver);
		softAssert=new SoftAssert();
}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	@AfterSuite
    public void openExtentReport() {
		//extend html report
        String reportPath = "reports/TestExecutionReport.html"; 
            try {
            	 File htmlFile = new File(reportPath);
            	 if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } 
            	 else {
                     System.out.println("Report file not found:"+htmlFile.getAbsolutePath());
                 }
            }
            	 catch (IOException e) {
                e.printStackTrace();
            }
            //allure serve
        
            try {
                // Use ProcessBuilder to run the Allure command with the correct executable path
                String allureExecutable = "C:/Users/vinut/scoop/apps/allure/2.32.0/bin/allure.bat";
                ProcessBuilder builder = new ProcessBuilder(allureExecutable, "serve", "allure-results");
                builder.inheritIO(); // This will inherit the IO streams of the current process
                Process process = builder.start(); // Start the process to serve the report
                process.waitFor(); // Wait for the process to complete

                // Allure serve command automatically opens the report in a browser
                System.out.println("Allure reports served successfully.");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                System.out.println("Failed to serve Allure report.");
            }
}
}

