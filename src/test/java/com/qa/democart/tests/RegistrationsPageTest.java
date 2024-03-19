package com.qa.democart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.democart.utils.Constants;
import com.qa.democart.utils.ExcelUtil;

public class RegistrationsPageTest extends BaseTest {
	
	@BeforeClass
	public void regSetUp() {
		registrationsPage=loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {
		Random random= new Random();
		String emailId="testautomation"+random.nextInt(5000)+"@gmail.com";
		return emailId;
	}
	
	@DataProvider
	public Object[][] getRegistrationData(){
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider ="getRegistrationData")
	public void registrationTest(String firstName, String lastName, String password, String subscribe) {
		Assert.assertTrue(registrationsPage.accountRegistration(firstName,lastName,getRandomEmail(),password,subscribe));
	}

}
