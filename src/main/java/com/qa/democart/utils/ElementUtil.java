package com.qa.democart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.democart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtil(driver);
	}
	
	/**
	 * This method is used to return By locator based on Locator Type and Locator value
	 * @param locatorType
	 * @param value
	 * @return
	 */
	public By getBy(String locatorType, String value) {
		By locator=null;
		switch (locatorType) {
		case "id":
			locator=By.id(value);
			break;
		case "name":
			locator=By.name(value);
			break;
		case "className":
			locator=By.className(value);
			break;
		case "linkText":
			locator=By.linkText(value);
			break;
		case "partialLinkText":
			locator=By.partialLinkText(value);
			break;
		case "xpath":
			locator=By.xpath(value);
			break;
		case "cssSelector":
			locator=By.cssSelector(value);
			break;
		case "tagName":
			locator=By.tagName(value);
			break;
		default:
			System.out.println("Please provide correct Locator Type");
			break;
		}
		return locator;
	}
	

	/**
	 * This method is used to return WebElement based on By locator
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public WebElement getElement(By locator) {
		WebElement element=driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight.trim())) {
		jsUtil.flash(element);	
		}
		return element;
	}

	/**
	 * This method is used to return List of WebElements based on By locator
	 * 
	 * @param locator
	 * @return WebElement
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * This method is used to perform sendKeys on a WebElement based on By locator
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		WebElement ele=getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	/**
	 * This method is used to click on a web element based on By locator.
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * This method is used to get Attribute value of the element based on By locator
	 * 
	 * @param locator
	 * @param attributeName
	 * @return Attribute value of the WebElement
	 */
	public String doGetAttribute(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}

	/**
	 * This method is used to get Text value of the element based on By locator
	 * 
	 * @param locator
	 * @return String text value of the WebElement
	 */
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * This method is used to check if WebElement is displayed or not.
	 * 
	 * @param locator
	 * @return
	 */
	public Boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	/**
	 * This method is used to check if WebElement is Enabled or not.
	 * 
	 * @param locator
	 * @return
	 */
	public Boolean doIsEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	/**
	 * This method is used to click on a WebElement from a list of WebElements.
	 * 
	 * @param locator
	 * @param value
	 */
	public void clickOnElement(By locator, String value) {
		List<WebElement> list = getElements(locator);
		System.out.println(list.size());
		for (WebElement e : list) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * This method is used to return a List of text of all WebElements found through
	 * a By locator.
	 * 
	 * @param locator
	 * @return
	 */
//	public List<String> getLinksTextList(By locator) {
//		List<WebElement> eleList = getElements(locator);
//		System.out.println("Element count " + eleList.size());
//		List<String> eleTextList = new ArrayList<String>();
//		for (WebElement e : eleList) {
//			if (!e.getText().isBlank()) {
//				eleTextList.add(e.getText());
//			}
//
//		}
//		return eleTextList;
//
//	}

	/**
	 * This method is used to find if Element is present or not.
	 * 
	 * @param locator
	 * @return
	 */
	public boolean isElementExist(By locator) {
		List<WebElement> list = getElements(locator);
		if (list.size() > 0) {
			System.out.println("Element is present");
			return true;
		}
		return false;
	}

	/******************************* Dropdown utilities(Select Tag)********************************/

	/**
	 * This method is used to select an option from the dropdown using index for
	 * select tag element.
	 * 
	 * @param locator
	 * @param index
	 */
	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	/**
	 * This method is used to select an option from the dropdown using value
	 * attribute for select tag element.
	 * 
	 * @param locator
	 * @param index
	 */
	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	/**
	 * This method is used to select an option from the dropdown using text for
	 * select tag element.
	 * 
	 * @param locator
	 * @param index
	 */
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	/**
	 * This method is used to return a List of text of all options found through a
	 * By locator in a Select dropdown.
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		List<String> optionsListText = new ArrayList<String>();
		List<WebElement> optionsList = select.getOptions();

		System.out.println(optionsList.size());

		for (WebElement e : optionsList) {
			optionsListText.add(e.getText());
		}
		return optionsListText;

	}

	/**
	 * This method is used to select value from the dropdown without using Select
	 * class methods.
	 * 
	 * @param locator
	 * @param value
	 */
	public void clickDropDownValue(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}

	}

	/**
	 * This method is used to select one, multiple or all options in the dropdown without using Select method. Pass "select_all" if all options needs to be selected.
	 * @param locator
	 * @param value
	 */
	public void selectChoice(By locator, String... value) {
		List<WebElement> choiceList = getElements(locator);

		if (!value[0].equalsIgnoreCase("select_all")) {
			for (WebElement e : choiceList) {
				String text = e.getText();
				for (int j = 0; j < value.length; j++) {
					if (text.equals(value[j])) {
						e.click();
						break;
					}
				}
			}
		} else {
			try {
				for (WebElement e : choiceList) {
					e.click();
				}
			} catch (Exception e) {

			}
		}
	}
	
	/********************************Actions Class**********************************************************/
	
	/**
	 * This method is used to handle two level menus.
	 * @param parentMenu
	 * @param childMenu
	 * @throws InterruptedException
	 */
	public void twoLevelMenuHandle(By parentMenu, By childMenu) throws InterruptedException {
		Actions act=new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();
	}
	
	/**
	 * This method is used to handle threee level menus.
	 * @param parentMenu
	 * @param childMenu
	 * @throws InterruptedException
	 */
	public void threeLevelMenuHandle(By parentMenu1, By parentMenu2, By childMenu) throws InterruptedException {
		Actions act=new Actions(driver);
		act.moveToElement(getElement(parentMenu1)).perform();
		Thread.sleep(2000);
		act.moveToElement(getElement(parentMenu2)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();
	}
	
	/**
	 * This method is used to drag and drop.
	 * @param sourceEle
	 * @param targetEle
	 */
	public void dragAndDropHandle(By sourceEle, By targetEle) {
		Actions act=new Actions(driver);
		act.dragAndDrop(getElement(sourceEle), getElement(targetEle)).perform();
	}
	
	/**
	 * This method is used to sendKeys using Actions class
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeys(By locator, String value) {
		Actions act=new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}
	
	/**
	 * This method is used to click using Actions class
	 * @param locator
	 */
	public void doActionsClick(By locator) {
		Actions act=new Actions(driver);
		act.click(getElement(locator)).perform();
	}
	
	/**
	 * This method is used to sendKeys each character in a string with pause of 2000ms
	 * @param locator
	 * @param value
	 */
	public void doActionsSendKeysWithPause(By locator, String value) {
        Actions act = new Actions(driver);
		char val[] = value.toCharArray();
		for (char c : val) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(Duration.ofMillis(2000)).build().perform();
		}
	}
	
	/**
	 * This method is used to scroll 1 page down using Actions Class.
	 */
	public void ActionsScrollPageDown() {
		Actions act= new Actions(driver);
		act.sendKeys(Keys.PAGE_DOWN).perform();
	}
	
	/**
	 * This method is used to scroll 1 page Up using Actions Class.
	 */
	public void ActionsScrollPageUp() {
		Actions act= new Actions(driver);
		act.sendKeys(Keys.PAGE_UP).perform();
	}
	
	/**
	 * This method is used to scroll Entire page down using Actions Class.
	 */
	public void ActionsScrollEntirePageDown() {
		Actions act= new Actions(driver);
		act.sendKeys(Keys.CONTROL).sendKeys(Keys.END).perform();
	}
	
	/**
	 * This method is used to scroll Entire page Up using Actions Class.
	 */
	public void ActionsScrollEntirePageUp() {
		Actions act= new Actions(driver);
		act.sendKeys(Keys.CONTROL).sendKeys(Keys.HOME).perform();
	}
	
	/**
	 * This method is used to scroll to a particular WebElement and click it using Actions class. This method is available only in Selenium 4
	 * @param locator
	 */
	public void ActionsScrollToElement(By locator) {
		Actions act= new Actions(driver);
//		act.scrollToElement(WebElement).click(WebElement).build.perform();
	}
	
	/**
	 * This method is used to refresh page using Actions class.
	 */
	public void ActionsRefresh() {
		Actions act= new Actions(driver);
        act.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).build().perform();
	}
	
	/**
	 * This method is used to slide horizontally using Actions class.
	 * @param locator
	 */
	public void sliderHandle(By locator) {
		WebElement slider = getElement(locator);
		int width = slider.getSize().getWidth();
		Actions act = new Actions(driver);
		act.clickAndHold(slider).moveByOffset((width / 2) - 500, 0).build().perform();
	}
	
	/**
	 * This method is used to draw signature using Actions class.
	 * @param locator
	 */
	public void drawSignature(By locator) {
		WebElement signaturePad = getElement(locator);
		Actions act = new Actions(driver);
		act.moveToElement(signaturePad, 30, 10)
		   .clickAndHold(signaturePad)
		     .moveToElement(signaturePad, -200, -50)
		        .moveByOffset(50, -50)
		           .moveByOffset(50, -50)
		              .moveByOffset(3, 3)
		                 .release(signaturePad)
		                     .build()
		                       .perform();

	}
	
/******************************************Wait Utilities************************************************************/	
	
	/**
	 * This method is used to return if page is loaded or not
	 * @param timeout
	 * @return
	 */
	public boolean isPageLoaded(int timeout) {
		WebDriverWait wait= new WebDriverWait(driver, 5);
		String flag=wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
	
	/**
	 * This method is used to check presence of element in DOM and return the WebElement
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementPresence(By locator,int timeout) {
		WebDriverWait wait=new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * This method is used to check presence of element in DOM and return the WebElement
	 * @param locator
	 * @param timeout
	 * @param pollingTime
	 * @return
	 */
	public WebElement waitForElementPresence(By locator,int timeout, int pollingTime) {
		WebDriverWait wait=new WebDriverWait(driver, timeout, pollingTime);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * This method is used to check present of Alert and return the Alert reference
	 * @param timeout
	 * @return
	 */
	public Alert waitForAlert(int timeout) {
		WebDriverWait wait= new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * This method is used to wait for presence of Alert, get it's Text and then accept it.
	 * @param timeout
	 * @return
	 */
	public String getAlertText(int timeout) {
		String text= waitForAlert(timeout).getText();
		acceptAlert(timeout);
		return text;
	}
	
	/**
	 * This method is used to wait for presence of Alert and then get accept it.
	 * @param timeout
	 */
	public void acceptAlert(int timeout) {
		waitForAlert(timeout).accept();
	}
	
	/**
	 * This method is used to wait for presence of Alert and then get dismiss it.
	 * @param timeout
	 */
	public void dismissAlert(int timeout) {
		waitForAlert(timeout).dismiss();
	}
	
	/**
	 * This method is used to wait for presence of Alert and then get send keys the value provided.
	 * @param timeout
	 * @param value
	 */
	public void sendKeysAlert(int timeout,String value) {
		waitForAlert(timeout).sendKeys(value);
	}
	
	/**
	 * This method is used to wait for title substring to be present and then return the title.
	 * @param value
	 * @param timeout
	 * @return
	 */
	public String waitForTitle(String value, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if (wait.until(ExpectedConditions.titleContains(value))) {
			return driver.getTitle();
		} else {
			return null;
		}

	}
	
	/**
	 * This method is used to wait for exact title to be present and then return the title.
	 * @param value
	 * @param timeout
	 * @return
	 */
	public String waitForTitleIs(String fullTitleValue, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		if (wait.until(ExpectedConditions.titleIs(fullTitleValue))) {
			return driver.getTitle();
		} else {
			return null;
		}

	}
	
	/**
	 * This method is used to wait for the url fraction to be present and then get the current url.
	 * @param urlFraction
	 * @param timeout
	 * @return
	 */
	public String waitForUrlContains(String urlFraction, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver,10);
		if(wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method is used to wait for the complete url to be present and then get the current url.
	 * @param url
	 * @param timeout
	 * @return
	 */
	public String waitForUrlToBe(String url, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver,10);
		if(wait.until(ExpectedConditions.urlToBe(url))) {
			return driver.getCurrentUrl();
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method is used to wait for frame to be available and then switch to it based on frame name.
	 * @param frameName
	 * @param timeout
	 */
	public void waitForFrameAndSwitchToIt(String frameName, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	/**
	 * This method is used to wait for frame to be available and then switch to it based on frame By locator.
	 * @param frameLocator
	 * @param timeout
	 */
	public void waitForFrameAndSwitchToIt(By frameLocator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	/**
	 * This method is used to wait for frame to be available and then switch to it based on frame index.
	 * @param frameIndex
	 * @param timeout
	 */
	public void waitForFrameAndSwitchToIt(int frameIndex, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	/**
	 * This method is used to wait for frame to be available and then switch to it based on frame WebElement.
	 * @param frameElement
	 * @param timeout
	 */
	public void waitForFrameAndSwitchToIt(WebElement frameElement, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	/**
	 * This method is used to wait for the Element to be visible and then return the WebElement
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementVisibleUsingBy(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * This method is used to wait for the Element to be visible and then return the WebElement
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementVisibleUsingWebElement(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	
	/**
	 * This method is used to wait for the Elements to be visible and then return the List of WebElements
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementsToBeVisibleUsingBy(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	/**
	 * This method is used to wait for the element to be visible and enabled such that you can click it and then return the WebElement
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait= new WebDriverWait(driver,timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	/**
	 * This method is used to wait for the element to be visible and enabled and then can click it
	 * @param locator
	 * @param timeout
	 */
	public void clickWhenReady(By locator, int timeout) {
		waitForElementToBeClickable(locator, timeout).click();
	}
	
	/**
	 * This method is used to wait for element using FLuent Wait features and then return the WebElement
	 * @param timeout
	 * @param pollingTime
	 * @param locator
	 * @return
	 */
	public WebElement waitForElementWithFluentWait(int timeout, long pollingTime, By locator) {
		Wait<WebDriver> wait= new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofMillis(pollingTime))
				.ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/**
	 * This method is used to select checkbox against the string in a webtable
	 * @param name
	 */
	public void selectContact(String name) {
		String checkBox="//a[text()='"+name+"']//parent::td//preceding-sibling::td/input[@type='checkbox']";
		driver.findElement(By.xpath(checkBox)).click();
	}

}
