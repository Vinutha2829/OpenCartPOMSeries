package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.frameworkexception.FrameException;

public class ElementUtil {
	private  WebDriver driver;
	private JavaScriptUtil js;
	 public ElementUtil(WebDriver driver)
	 {
		 this.driver=driver;
		  js=new JavaScriptUtil(this.driver);
	 }
	 public  WebElement getElement(By locator)
		{
		 WebElement ele;
		 try
		 {
			ele= driver.findElement(locator);
		 }
		 catch(NoSuchElementException el)
		 {
			 System.out.println("Element is not found"+locator);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ele= driver.findElement(locator);
		 }
		 if(Boolean.parseBoolean(DriverFactory.highLightVar))
		 {
			 js.flash(ele);
		 }
			return ele;
		}
		public  void doSendKeys(By locator,String value)
		{
			if(value==null)
			{
				System.out.println("null values are not allowed");
				throw new FrameException("VALUECANNOTBENULL");
			}
			WebElement ele=getElement(locator);
			ele.clear();
			ele.sendKeys(value);
			
		}
		public void doClick(By locator) {
			getElement(locator).click();
		}
		public boolean checkElementIsDisplayed(By locator) {
			return getElement(locator).isDisplayed();
		}
		public  String doGetAtrributeValue(By locator,String attrName)
		{
			String string =getElement(locator).getAttribute(attrName);
			return string;
		}
		public String doGetElementText(By locator) {
			return getElement(locator).getText();
		}
		public void tearDown(WebDriver driver)
		{
			driver.close();
		}
		public  String getTextFieldValue(By locator,String attrName)
		{
			String string=getElement(locator).getAttribute(attrName);
			return string;
		}
		public  List<WebElement> getElements(By locator)
		{
			return driver.findElements(locator);
			 
		}
		public  List<String> getOnlyStringElements(By locator)
		{
			List<String> links=new ArrayList<String>();
			List<WebElement>element =getElements(locator);
			for(int i=0;i<element.size();i++)
			{
				String string =element.get(i).getText();
				if(string.length()>0)
				{
					links.add(string);
				}
			}
			return links;
		}
		public int getOnlyStringElementsCount(By locator)
		{
			return getOnlyStringElements(locator).size();
		}
		public int getElementsCount(By locator)
		{
			return getElements(locator).size();
			
		}
		public  List<String> getElementAttributeValue(By locator,String attrName)
		{
			List<String> value=new ArrayList<String>();
			List<WebElement> element =getElements(locator);
			for(WebElement e:element)
			{
				String src=e.getAttribute(attrName);
				value.add(src);
			}
			return value;
		}
		public  int getElementAttributeValueCount(By locator,String attrName)
		{
			return getElementAttributeValue(locator,attrName).size();
		}
		public  List<String> getElementSections(By locator)
		{
			List<String> string =new ArrayList<String>();
			List<WebElement> element =getElements(locator);
			for(WebElement e:element)
			{
				String value=e.getText();
				string.add(value);
			}
			return string;
		}
		public  String clickElementFromPageSource(By locator,String value)
		{
			List<String> string =new ArrayList<String>();
			List<WebElement> element =getElements(locator);
			for(WebElement e:element)
			{
				if(e.getText().equals(value))
				{
					e.click();
					break;
				}
				
			}
			return driver.getTitle();
		}
		// **************************Drop Down Utils************************//
		
		public void doSelectDropDownByIndex(By locator, int index) {
			Select select = new Select(getElement(locator));
			select.selectByIndex(index);
		}

		public void doSelectDropDownByVisibleText(By locator, String text) {
			Select select = new Select(getElement(locator));
			select.selectByVisibleText(text);
		}

		public void doSelectDropDownByValueAttribute(By locator, String value) {
			Select select = new Select(getElement(locator));
			select.selectByValue(value);
		}

		public int getDropDownValueCount(By locator) {
			return getAllDropDownOptions(locator).size();
		}

		public List<String> getAllDropDownOptions(By locator) {
			Select select = new Select(getElement(locator));
			List<WebElement> optionsList = select.getOptions();
			List<String> optionsValueList = new ArrayList<String>();
			System.out.println("total values : " + optionsList.size());

			for (WebElement e : optionsList) {
				String text = e.getText();
				System.out.println(text);
				optionsValueList.add(text);
			}
			return optionsValueList;
		}

		public boolean doSelectDropDownValue(By locator, String dropDownValue) {
			boolean flag = false;
			Select select = new Select(getElement(locator));
			List<WebElement> optionsList = select.getOptions();
			System.out.println("total values : " + optionsList.size());

			for (WebElement e : optionsList) {
				String text = e.getText();
				System.out.println(text);
				if (text.equals(dropDownValue)) {
					flag = true;
					e.click();
					break;
				}
			}

			if (flag == false) {
				System.out.println(dropDownValue + " is not present in the drop down " + locator);
			}
			return flag;
		}

		public boolean DoSelectValueFromDropDownWithoutSelect(By locator, String value) {
			boolean flag = false;
			List<WebElement> optionsList = getElements(locator);
			for (WebElement e : optionsList) {
				String text = e.getText();
				if (text.equals(value)) {
					flag = true;
					e.click();
					break;
				}
			}

			if (flag == false) {
				System.out.println(value + " is not present in the drop down " + locator);
			}

			return flag;

		}
		// **********************************Actions class util************************ //
		public  void doActionsSendKeys(By locator,String value)
		{
			Actions act=new Actions(driver);
			act.sendKeys(getElement((locator)), value).build().perform();
		}
		public  void doActionsClick(By locator)
		{
			Actions act=new Actions(driver);
			act.click(getElement(locator)).build().perform();
		}
		public  void doDragAndDrop(By drag,By drop) throws InterruptedException
		{
			Actions act=new Actions(driver);
			Thread.sleep(1500);
			act.dragAndDrop(getElement(drag), getElement(drop)).build().perform();
			//act.clickAndHold(drag).moveToElement(drop).release().build().perform();
		}
		public  void doContextClick(By locator)
		{
			Actions act=new Actions(driver);
			act.contextClick(getElement(locator)).build().perform();
		}
		
		public  int doContentClcikSize(By locator,By valueclick)
		{
			doContextClick(locator);
			List<WebElement> link =getElements(valueclick);
			int size=link.size();
			return size;
		}
		public  List<String> doContentClcikValues(By locator,By valueclick)
		{
			doContextClick(locator);
			List<WebElement> link =getElements(valueclick);
			List<String> string =new ArrayList<String>();
			for(WebElement e: link)
			{
				String value=e.getText();
				string.add(value);
			}
			return string;
		}
		public  void doActionsClickAndGetTextAndAct(By locator)
		{
			doActionsClick(locator);
			doAlertText();
			//doAlertAccept();
		}
		public  void handleTwoLevelMenu(By parentMenu,By childMenu) throws InterruptedException
		{
			WebElement contactMenu =getElement(parentMenu);
			Actions act=new Actions(driver);
			act.moveToElement(contactMenu).build().perform();
			Thread.sleep(1500);
			getElement(childMenu).click();
		}
		public  void handleTwoLevelMenu(By parentMenu,String value) throws InterruptedException
		{
			WebElement contactMenu =getElement(parentMenu);
			Actions act=new Actions(driver);
			act.moveToElement(contactMenu).build().perform();
			Thread.sleep(1500);
			getElement(By.linkText(value)).click();
		}
		public  void multiLevelMenuChildMenuHandle(By parentMenu,By childMenu,By click) throws InterruptedException
		{
			Actions action=new Actions(driver);
			
			action.moveToElement(getElement(parentMenu)).build().perform();
			Thread.sleep(1000);
			action.moveToElement(getElement(childMenu)).build().perform();
			Thread.sleep(1000);
			getElement(click).click();
		}
		// *************************************** Alert util ****************************//
		public  void doAlertText()
		{
			Alert alert=driver.switchTo().alert();
			System.out.println(alert.getText());
			doAlertAccept();
		}
		public  void doAlertAccept()
		{
			Alert alert=driver.switchTo().alert();
			alert.accept();
		}
		// ***********************Wait Utils***********************//

		public Alert waitForAlertJsPopUp(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
		}

		public String alertJSGetText(int timeOut) {
			return waitForAlertJsPopUp(timeOut).getText();
		}

		public void alertAccpet(int timeOut) {
			waitForAlertJsPopUp(timeOut).accept();
		}

		public void alertDismiss(int timeOut) {
			waitForAlertJsPopUp(timeOut).dismiss();
		}

		public void EnterAlertValue(int timeOut, String value) {
			waitForAlertJsPopUp(timeOut).sendKeys(value);
		}
		
		
		public String waitForTitleIsAndCapture(String titleFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.titleContains(titleFraction))) {
				String title = driver.getTitle();
				return title;
			}
			else {
				System.out.println("title is not present within the given timeout : " + timeOut);
				return null;
			}
		}
		
		
		public String waitForFullTitleAndCapture(String titleVal, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.titleIs(titleVal))) {
				String title = driver.getTitle();
				return title;
			}
			else {
				System.out.println("title is not present within the given timeout : " + timeOut);
				return null;
			}
		}
		
		
		public  String waitForURLContainsAndCapture(String urlFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
				String url = driver.getCurrentUrl();
				return url;
			}
			else {
				System.out.println("url is not present within the given timeout : " + timeOut);
				return null;
			}
		}
		
		public String waitForURLAndCapture(String urlValue, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			if(wait.until(ExpectedConditions.urlToBe(urlValue))) {
				String url = driver.getCurrentUrl();
				return url;
			}
			else {
				System.out.println("url is not present within the given timeout : " + timeOut);
				return null;
			}
		}
		
		
		public boolean waitForTotalWindows(int totalWindowsToBe, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			return wait.until(ExpectedConditions.numberOfWindowsToBe(totalWindowsToBe));
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			WebElement ele= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if(Boolean.parseBoolean(DriverFactory.highLightVar))
			 {
				 js.flash(ele);
			 }
			return ele;
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible on the page. Visibility means that the element is not only
		 * displayed but also has a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			WebElement ele= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if(Boolean.parseBoolean(DriverFactory.highLightVar))
			 {
				 js.flash(ele);
			 }
			return ele;
		}

		/**
		 * An expectation for checking that all elements present on the web page that
		 * match the locator are visible. Visibility means that the elements are not
		 * only displayed but also have a height and width that is greater than 0.
		 * default timeout = 500 ms
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		//default timeout = intervalTime
		public List<WebElement> waitForElementsVisible(By locator, int timeOut, int intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

		/**
		 * An expectation for checking that there is at least one element present on a
		 * web page.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}

		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it.
		 * 
		 * @param locator
		 * @param timeOut
		 */
		public void clickElementWhenReady(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}

		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it.
		 * 
		 * @param locator
		 * @param timeOut
		 */
		public WebElement checkElementClickable(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			WebElement ele= wait.until(ExpectedConditions.elementToBeClickable(locator));
			if(Boolean.parseBoolean(DriverFactory.highLightVar))
			 {
				 js.flash(ele);
			 }
			return ele;
		}
		
		
		//frames with wait:
	
		public void waitForFrameAndSwitchToItByIDOrName(int timeOut, String idOrName) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
		}

		public void waitForFrameAndSwitchToItByIndex(int timeOut, int frameIndex) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}

		public void waitForFrameAndSwitchToItByFrameElement(int timeOut, WebElement frameElement) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}

		public void waitForFrameAndSwitchToItByFrameLoctor(int timeOut, By frameLocator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}
		public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(ElementNotInteractableException.class)
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.withMessage("------time out is done...element is not found.....");
			
			WebElement ele= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if(Boolean.parseBoolean(DriverFactory.highLightVar))
			 {
				 js.flash(ele);
			 }
			return ele;

		}
		
		public WebElement waitForElementPresenceWithFluentWait(By locator, int timeOut, int pollingTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class)
					.ignoring(ElementNotInteractableException.class)
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.withMessage("------time out is done...element is not found.....");
			
			WebElement ele= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if(Boolean.parseBoolean(DriverFactory.highLightVar))
			 {
				 js.flash(ele);
			 } 
			return ele;

		}
		
		
		public WebElement retryingElement(By locator, int timeOut) {// 20

			WebElement element = null;
			int attempts = 0;

			while (attempts < timeOut) {
				try {
					element = getElement(locator);
					System.out.println("element is found...." + locator + " in attempt : " + attempts);
					break;
				} catch (NoSuchElementException e) {
					System.out.println("element is not found..." + locator + " in attempt : " + attempts);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				attempts++;
			}

			if (element == null) {
				System.out.println(
						"element is not found...tried for " + timeOut + " secs " + " with the interval of 500 millisecons");
			}
			if(Boolean.parseBoolean(DriverFactory.highLightVar))
			 {
				 js.flash(element);
			 }
			return element;

		}
		
		public WebElement retryingElement(By locator, int timeOut, int pollingTime) {// 20

			WebElement element = null;
			int attempts = 0;

			while (attempts < timeOut) {
				try {
					element = getElement(locator);
					System.out.println("element is found...." + locator + " in attempt : " + attempts);
					break;
				} catch (NoSuchElementException e) {
					System.out.println("element is not found..." + locator + " in attempt : " + attempts);
					try {
						Thread.sleep(pollingTime);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				attempts++;
			}

			if (element == null) {
				System.out.println(
						"element is not found...tried for " + timeOut + " secs " + " with the interval of 500 millisecons");
			}
			if(Boolean.parseBoolean(DriverFactory.highLightVar))
			 {
				 js.flash(element);
			 }
			return element;

		}

		public Boolean isPageLoaded() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == \'complete\'"))
					.toString(); //"true"
			return Boolean.parseBoolean(flag);//true
		}

		public void waitForPageLoad(int timeOut) {

			long endTime = System.currentTimeMillis() + timeOut;
			while (System.currentTimeMillis() < endTime) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				String pageState = js.executeScript("return document.readyState").toString();
				if (pageState.equals("complete")) {
					System.out.println("PAGE DOM is fully loaded now....");
					break;
				} else {
					System.out.println("PAGE IS not loaded...." + pageState);
				}
			}
		}
	

}
