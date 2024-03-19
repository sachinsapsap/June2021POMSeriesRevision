package com.qa.democart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.democart.listeners.TestAllureListener;
import com.qa.democart.pages.AccountsPage;
import com.qa.democart.utils.Constants;
import com.qa.democart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 100 : Design Login Page for Open Cart Application.....")
@Story("US 101 : Login Page with different features....")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {
	
	@Description("login page title test.....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle=loginPage.getLoginPageTitle();
		System.out.println("Login Page Title : "+actualTitle);
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE,Errors.TITLE_ERROR_MESG);
	}
	
	@Description("login page header test.....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageHeaderTest() {
		String actualHeaderText=loginPage.getPageHeaderText();
		System.out.println("Login Page Header : "+actualHeaderText);
		Assert.assertEquals(actualHeaderText, Constants.PAGE_HEADER, Errors.HEADER_ERROR_MESG );
	}
	
	@Description("forgot password link test.....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkText() {
		Assert.assertTrue(loginPage.isForgotPwdExist());
	}
	
	@Description("login page test.....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {
		AccountsPage accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
