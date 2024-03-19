package com.qa.democart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	
	 private WebDriver driver;
	 
     /**
      * This constructor is used to initialize driver	
      * @param driver Pass the driver
      */
	 public JavaScriptUtil(WebDriver driver) {
		 this.driver=driver;
	 }

	 /**
	  * This method is used to return the Page Title using Java Script Executor.
	  * @return This returns Page title
	  */
	public String getTitleByJS() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		return js.executeScript("return document.title;").toString();
		
	}

	/**
	 * This method is used to refresh browser using Java Script Executor.
	 */
	public void refreshBrowserByJS() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
		
	}
	
	/**
	 * This method is used to capture the entire Text of all the WebElements from the page using the Java Script Executor.
	 * @return This returns entire Text from the page for each WebElement
	 */
	public String getPageInnerText() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		return js.executeScript("return document.documentElement.innerText;").toString();
		
	}

	/**
	 * This method is used to generate alert using Java Script Executor.
	 * @param message Pass the alert message
	 */
	public void generateAlert(String message) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("alert('"+message+"')");
	}

	/**
	 * This method is used to flash the WebElement.
	 * @param element Pass the WebElement to be flashed.
	 */
	public void flash(WebElement element) {
		String bgcolor=element.getCssValue("backgroundColor");
		for(int i=0;i<15;i++) {
			changeColor("rgb(0,200,0)",element);
			changeColor(bgcolor,element);
		}
	}
	
    /**
     * This method is used to change color of the WebElement
     * @param color Pass the color to be applied on the WebElement.
     * @param element Pass the Element for which color has to be changed.
     */
	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element);
		try {
			Thread.sleep(20);
		}
		catch (InterruptedException e) {
		}
		}

	/**
	 * This method is used to click on an element using Java Script Executor.
	 * @param element Pass the WebElement to be clicked.
	 */
	public void clickElementByJS(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		
	}

	/**
	 * This method is used to scroll to the bottom of the page.
	 */
	public void scrollPageDown() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		
	}
	
	/**
	 * This method is used to scroll down till the height given.
	 * @param height Pass the height to which the page needs to be scrolled down.
	 */
	public void scrollPageDown(String height) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,'"+height+"')");
	}
	
	/**
	 * This method is used to scroll to the starting of the page.
	 */
	public void scrollPageUp() {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
		}
	
	/**
	 * This method is used to scroll till a particular WebElement is visible.
	 * @param element Pass the WebElement till which the page needs to be scrolled.
	 */
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
		}

	/**
	 * This method is used to create Red border around the WebElement.
	 * @param element Pass the WebElement for which border has to be created.
	 */
	public void drawBorder(WebElement element) {
	   JavascriptExecutor js=(JavascriptExecutor) driver;
	   js.executeScript("arguments[0].style.border='3px solid red'",element);
		
	}
	
	
	
	}
	


