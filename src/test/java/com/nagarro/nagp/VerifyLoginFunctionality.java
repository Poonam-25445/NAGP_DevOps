package com.nagarro.nagp;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.nagarro.nagp.constants.FunctionalGroups;
import com.nagarro.nagp.constants.Severity;
import com.nagarro.nagp.core.TestBase;
import com.nagarro.nagp.dataobjects.DataProviders;
import com.nagarro.nagp.pageObjects.CommonPage;
import com.nagarro.nagp.pageObjects.LoginPage;

/**
 * @author poonam
 * This class will verify login functionality
 * 
 */
public class VerifyLoginFunctionality extends TestBase {
	
	/**
	 * This test case verify login functionality with valid user name and password
	 * 
	 * @param username				Username or email id
	 * @param password				User password
	 * @param loginPageTitle		Page title
	 */

	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, groups = { Severity.SMOKE, FunctionalGroups.LOGIN })
	public void testSuccessfulLogin(String username, String password, String loginPageTitle) {

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(CommonPage.getPageTitle(), loginPageTitle, "Login Page title is not as expected");
		softAssert.assertTrue(CommonPage.getURL().startsWith("https"), "Https Url not present");
		LoginPage.clickSignInButton();

		softAssert.assertTrue(LoginPage.verifyLoginButton(), "LoginButton not Present");
		softAssert.assertTrue(LoginPage.verifyForgotPasswordButton(), "Forgot Password button not Present");

		LoginPage.setUserName(username);
		LoginPage.setPassword(password);
		LoginPage.clickLogin();
		softAssert.assertEquals(LoginPage.verifyAfterLoginText(), CommonPage.WELCOME_MESSAGE,"Account not able to login properly");
		softAssert.assertAll();
	}
	
	
	/**
	 * This test case verify login functionality with invalid user name and password
	 * 
	 * @param username					Username or email id
	 * @param password					user password
	 * @param invalidEmailId			invalid email id
	 * @param EmailIdError				error message
	 * @param incorrectCaptcha			Captcha error
	 */
	@Test(dataProvider = "TestData", dataProviderClass = DataProviders.class, groups = { Severity.SMOKE, FunctionalGroups.LOGIN })
	public void testUnsuccessfulLogin(String username, String password, String invalidEmailId, String EmailIdError, String incorrectCaptcha) {

		SoftAssert softAssert = new SoftAssert();
		LoginPage.clickSignInButton();

		LoginPage.setUserName(invalidEmailId);
		LoginPage.setPassword(password);
		LoginPage.clickLogin();
		softAssert.assertTrue(LoginPage.getInvalidUserErrorMessage().contains(EmailIdError), "Invalid user error message is not as expected");
		
		LoginPage.setUserName(username);
		LoginPage.setPassword(password);
		LoginPage.clickLogin();
		softAssert.assertEquals(LoginPage.getLoginErrorMessage(), incorrectCaptcha,"Invalid user error message not present");
		softAssert.assertAll();
	}
}
