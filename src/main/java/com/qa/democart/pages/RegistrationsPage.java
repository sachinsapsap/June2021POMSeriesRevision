package com.qa.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ElementUtil;

public class RegistrationsPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By subscribeYes = By.id("input-newsletter-yes");
	private By subscribeNo = By.id("input-newsletter-no");
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//button[text()='Continue']");
	private By successMessage = By.cssSelector("div#content h1");
	private By logout=By.linkText("Logout");
	private By register=By.linkText("Register");

	public RegistrationsPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public boolean accountRegistration(String firstName, String lastName, String email, String password,
			String subscribe) {
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.password, password);
		if (subscribe.equalsIgnoreCase("yes")) {
			elementUtil.doActionsClick(subscribeYes);
		} else {
			elementUtil.doActionsClick(subscribeNo);
		}
		elementUtil.doActionsClick(agreeCheckBox);
		elementUtil.doActionsClick(continueButton);
		String message=elementUtil.waitForElementPresence(successMessage,Constants.DEFAULT_TIME_OUT).getText();
		if(message.contains(Constants.REGISTER_CONFIRMATION_MESSAGE)) {
			elementUtil.doClick(logout);
			elementUtil.doClick(register);
			return true;
				}
		else {
			return false;
		}

	}

}
