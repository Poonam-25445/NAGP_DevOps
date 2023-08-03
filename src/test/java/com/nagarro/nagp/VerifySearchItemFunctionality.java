package com.nagarro.nagp;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nagarro.nagp.constants.FunctionalGroups;
import com.nagarro.nagp.constants.Severity;
import com.nagarro.nagp.core.TestBase;
import com.nagarro.nagp.dataobjects.DataProviders;
import com.nagarro.nagp.keywords.SeKeywords;
import com.nagarro.nagp.pageObjects.HomePage;

/**
 * @author poonam
 * This class will check search functionality
 * 
 */
public class VerifySearchItemFunctionality extends TestBase {
	
	
/**
 * 
 * This test fill verify search item functionality
 * 1) check for search button is enable/disable
 * 2) search particular item
 * 3) verify searched item
 * 
 * @param homePageText			screen text
 * @param searchedProduct		product name to be searched
 * @param searchedText			searched text on screen
 * 
 */
	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, groups = { Severity.CRITICAL, FunctionalGroups.SEARCH })
	public void testSearchItem(String homePageText, String searchedProduct, String searchedText) {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(HomePage.getTextFromHomePage().contains(homePageText), "Home is not loaded properly");
		softAssert.assertFalse(HomePage.checkSearchButtonEnable(), "Search button is enable my default");
		HomePage.searchItem(searchedProduct);
		softAssert.assertTrue(HomePage.checkSearchButtonEnable(), "Search button is disable");
		HomePage.clickSearchButton();
		softAssert.assertTrue(SeKeywords.verifyAnyTextOnPage(searchedText), "Text not present");
		softAssert.assertTrue(HomePage.verifySearchItem(searchedProduct),"Searched item not present");
		softAssert.assertAll();
	}
}
