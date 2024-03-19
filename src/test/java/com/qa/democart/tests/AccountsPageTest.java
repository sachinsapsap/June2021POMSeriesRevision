package com.qa.democart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.democart.listeners.TestAllureListener;
import com.qa.democart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 102: Accounts Page Test")
@Story("STORY: 10201: Accounts Page Test features")
@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Description("Accounts page title Test.........")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actualTitle = accPage.getAccountPageTitle();
		System.out.println("Accounts Page Title is : " + actualTitle);
		Assert.assertEquals(actualTitle, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Description("Accounts page Header Test.........")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accPageHeaderTest() {
		String actualHeader = accPage.getAccountPageHeader();
		System.out.println("Accounts Page Header is : " + actualHeader);
		Assert.assertEquals(actualHeader, Constants.PAGE_HEADER);
	}

	@Description("Accounts page Sections List Test..........")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void accSectionsListTest() {
		List<String> actualAccSecList = accPage.getAccountsSectionList();
		System.out.println("Actual Sections: " + actualAccSecList);
		Assert.assertEquals(actualAccSecList, Constants.EXPECTED_ACC_SEC_LIST);
	}

	@Description("Accounts page Logout Link Existence Test........")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@DataProvider
	public Object[][] getSearchData() {
		Object[][] data = { { "Macbook Pro" }, { "Macbook Air" }, { "Apple" } };
		return data;
	}

	@Description("Accounts page Search Test seraching for {0}..........")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5, dataProvider = "getSearchData")
	public void searchTest(String productName) {
		resultsPage = accPage.doSearch(productName);
		String actualHeader = resultsPage.getSearchPageHeader();
		System.out.println("Results page header is " + actualHeader);
		Assert.assertTrue(actualHeader.contains(productName));
	}

	@DataProvider
	public Object[][] getProductSelectData() {
		Object[][] data = { { "Macbook Pro", "Macbook Air" }, { "Macbook Air", "Macbook Pro" },
				{ "Apple", "Apple Cinema 30\"" } };
		return data;
	}

	@Description("Accounts page Select Product Test searching for {0} and {1}..........")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 6, dataProvider = "getProductSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		String actualProductHeader = productInfoPage.getProductHeaderText();
		Assert.assertEquals(actualProductHeader, mainProductName);
	}
}
