package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

//	private By locators

	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//button[text()='Login']");
	private By forgotPwdLink = By.xpath("//div[@class='mb-3']//a[text()='Forgotten Password']");
	private By header = By.cssSelector("div#logo img");
	private By registerLink= By.linkText("Register");

//  constructor

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil=new ElementUtil(driver);
	}

//   public page actions/steps

	@Step("Getting login Page Title.......")
	public String getLoginPageTitle() {
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5);
	}

	@Step("Getting login Page Header.......")
	public String getPageHeaderText() {
		return elementUtil.doGetAttribute(header, "alt");
	}

	@Step("Checking Forgot Password Link displayed on login page.......")
	public boolean isForgotPwdExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}

	@Step("Login to application with username {0} and password {1}........")
	public AccountsPage doLogin(String un, String pwd) {
		elementUtil.doSendKeys(emailId, un);
		elementUtil.doSendKeys(password, pwd);
		elementUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("Navigating to Registration page.....")
	public RegistrationsPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationsPage(driver);
	}

}
