package com.nagarro.nagp;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nagarro.nagp.constants.FunctionalGroups;
import com.nagarro.nagp.constants.Severity;
import com.nagarro.nagp.core.TestBase;
import com.nagarro.nagp.dataobjects.DataProviders;
import com.nagarro.nagp.pageObjects.HomePage;

/**
 * @author poonam
 * This class is to verify shopping Cart
 * 
 */
public class VerifyCart extends TestBase {

	/**
	 * This test case test shopping cart on home page without login 
	 * 1) Click on Cart button 
	 * 2) Check for popup visibility 
	 * 3) Close popup
	 * 
	 * @param emptyCartMessage  Message after clicking on cart
	 * 
	 */
	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, groups = { Severity.CRITICAL, FunctionalGroups.CART })
	public void testCart(String emptyCartMessage) {

		SoftAssert softAssert = new SoftAssert();
		HomePage.clickCart();
		softAssert.assertTrue(HomePage.isCartPopupVisible(), "Cart popup not present");
		softAssert.assertEquals(HomePage.getCartMessage(), emptyCartMessage, "Cart empty message is not as expected");
		HomePage.closeCartPopup();
		softAssert.assertFalse(HomePage.isCartPopupVisible(), "Cart popup is still present");
		softAssert.assertAll();
	}
}
