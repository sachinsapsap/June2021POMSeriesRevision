package com.qa.democart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.democart.factory.DriverFactory;
import com.qa.democart.pages.AccountsPage;
import com.qa.democart.pages.LoginPage;
import com.qa.democart.pages.ProductInfoPage;
import com.qa.democart.pages.RegistrationsPage;
import com.qa.democart.pages.ResultsPage;

public class BaseTest {
	
	DriverFactory df;
	WebDriver driver;
	Properties prop;
	SoftAssert softAssert;
	
	LoginPage loginPage;
	AccountsPage accPage;
	ResultsPage resultsPage;
	ProductInfoPage productInfoPage;
	RegistrationsPage registrationsPage;

	@BeforeTest
	public void setup() {
		softAssert= new SoftAssert();
		df= new DriverFactory();
		prop=df.initProperties();
		driver= df.initDriver(prop);
		loginPage=new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
