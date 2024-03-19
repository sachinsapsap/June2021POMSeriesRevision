package com.qa.democart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductInfoTest extends BaseTest {
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	
	@Test
	public void productImagesTest() {
		resultsPage=accPage.doSearch("iMac");
		productInfoPage=resultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(),3);
	}
	
	@Test
	public void productInfoTest() {
		resultsPage=accPage.doSearch("Macbook");
		productInfoPage=resultsPage.selectProduct("Macbook Pro");
		Map<String,String> actualProductInfoMap=productInfoPage.getProductInfo();
		softAssert.assertEquals(actualProductInfoMap.get("name"), "Macbook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualProductInfoMap.get("Price"), "$2000.00");
		softAssert.assertAll();
	}

}
