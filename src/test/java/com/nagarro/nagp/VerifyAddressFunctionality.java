package com.nagarro.nagp;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nagarro.nagp.constants.FunctionalGroups;
import com.nagarro.nagp.constants.Severity;
import com.nagarro.nagp.core.TestBase;
import com.nagarro.nagp.dataobjects.DataProviders;
import com.nagarro.nagp.pageObjects.AddressPage;
import com.nagarro.nagp.pageObjects.CommonPage;
import com.nagarro.nagp.pageObjects.LoginPage;

/**
 * @author poonam
 * This class will verify different address functionality:
 * 1) Add Address
 * 2) Edit Address
 * 3) Delete Address
 * 
 */
public class VerifyAddressFunctionality extends TestBase {
	
	
	/**
	 * This test case verify the add address functionality
	 * 
	 * @param username					Login username
	 * @param password					login password
	 * @param addressTitle				page title	
	 * @param addNewAddressPage			screen title
	 * @param savedAddressMessage		message after save address
	 * @param firstName					first name field
	 * @param lastName					last name field
	 * @param telephone					telephone no.
	 * @param street					street
	 * @param city						city
	 * @param state						state
	 * @param zipCode					zip code
	 * @param country					country
	 * 
	 */
	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, priority = 1, groups = { Severity.CRITICAL, FunctionalGroups.ADDRESS })
	public void testAddAddress(String username, String password, String addressTitle, String addNewAddressPage,
			String savedAddressMessage, String firstName, String lastName, String telephone, String street, String city,
			String state, String zipCode, String country) {

		SoftAssert softAssert = new SoftAssert();
		LoginPage.login(username, password);
		softAssert.assertTrue(CommonPage.verifyDropDownValuesInUserMenu(CommonPage.LOGIN_USER_LINKS),"Invalid links in user menu");
		CommonPage.clickUserInfo();
		CommonPage.selectWelcomeListOption(CommonPage.MY_ACCOUNT);
		softAssert.assertTrue(AddressPage.verifyManageAddressVisibility(), "Manage addresses link is not present");
		AddressPage.clickManageAddress();
		softAssert.assertEquals(AddressPage.getPageTitle(), addressTitle, "Not able to open address book");
		AddressPage.clickAddNewAddress();
		softAssert.assertEquals(AddressPage.getPageTitle(), addNewAddressPage, "Not able to open address book");
		AddressPage.addNewAddress(firstName, lastName, telephone, street, city, state, zipCode, country);
		softAssert.assertEquals(AddressPage.getAddressTableRowCount(), 1, "Count not matched");
		softAssert.assertTrue(AddressPage.getAddressMessages().contains(savedAddressMessage),"Saved address message not found");
		softAssert.assertAll();
	}

	/**
	 * This test case verify the edit address functionality
	 * 
	 * @param username					Login username
	 * @param password					login password
	 * @param editAddressText			message after address edit
	 * @param updatedNumber				updated value of telephone phone
	 * @param saveAddressMessage		Saved address message
	 */
	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, priority = 2, groups = { Severity.CRITICAL, FunctionalGroups.ADDRESS })
	public void testEditAddress(String username, String password, String editAddressText, String updatedNumber, CharSequence saveAddressMessage) {

		SoftAssert softAssert = new SoftAssert();
		LoginPage.login(username, password);
		CommonPage.selectWelcomeListOption(CommonPage.MY_ACCOUNT);
		AddressPage.clickManageAddress();
		softAssert.assertEquals(AddressPage.getAddressTableRowCount(), 1, "Count not matched");
		AddressPage.editAddress();
		softAssert.assertEquals(AddressPage.getPageTitle(), editAddressText, "Not able to edit address page");
		AddressPage.editTelephone(updatedNumber);
		AddressPage.saveAddress();
		softAssert.assertTrue(AddressPage.getAddressMessages().contains(saveAddressMessage),"Saved address message not found");
		softAssert.assertEquals(AddressPage.getTelephoneValue(), updatedNumber, "Phone no. not updated ");
		softAssert.assertAll();
	}

	/**
	 * This test case verify the delete address functionality
	 * 
	 * @param username					Login username
	 * @param password					login password
	 * @param popupOption				confirmation popup options
	 * @param confirmationMessage		confirmation message
	 * @param deletedAddressMessage		delete address message
	 */
	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, priority = 3,groups = { Severity.CRITICAL, FunctionalGroups.ADDRESS })
	public void testDeleteAddress(String username, String password, String popupOption, String confirmationMessage, String deletedAddressMessage) {

		SoftAssert softAssert = new SoftAssert();
		LoginPage.login(username, password);
		CommonPage.selectWelcomeListOption(CommonPage.MY_ACCOUNT);
		AddressPage.clickManageAddress();
		AddressPage.deleteAddress();
		softAssert.assertEquals(AddressPage.getPopUpMessage(), confirmationMessage, "Confirmation message not visible");
		AddressPage.clickPopMessage(popupOption);
		softAssert.assertTrue(AddressPage.getAddressMessages().contains(deletedAddressMessage),"Saved address message not found");
		softAssert.assertEquals(AddressPage.getAddressTableRowCount(), 0, "Count not matched");
		softAssert.assertAll();
	}
}
