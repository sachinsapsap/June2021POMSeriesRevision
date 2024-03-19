package com.qa.democart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By accSections = By.cssSelector("div#content h2");
	private By header = By.cssSelector("div#logo img");
	private By logout = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchBtn = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		return elementUtil.waitForTitleIs(Constants.ACCOUNT_PAGE_TITLE, 5);
	}

	public String getAccountPageUrl() {
		return elementUtil.waitForUrlContains(Constants.ACCOUNT_PAGE_URL_FRACTION, 5);
	}

	public String getAccountPageHeader() {
		return elementUtil.doGetAttribute(header, "alt");
	}

	public List<String> getAccountsSectionList() {
		List<String> accSecValList = new ArrayList<String>();
		List<WebElement> accSecList = elementUtil.waitForElementsToBeVisibleUsingBy(accSections, 5);
		for (WebElement e : accSecList) {
			accSecValList.add(e.getText());
		}
//		Collections.sort(accSecValList);
		return accSecValList;
	}

	public boolean isLogoutLinkExist() {
		return elementUtil.doIsDisplayed(logout);
	}

	public ResultsPage doSearch(String productName) {
		System.out.println("Searching the product : " + productName);
		elementUtil.doSendKeys(searchField, productName);
		elementUtil.doClick(searchBtn);
		return new ResultsPage(driver);
	}

}
