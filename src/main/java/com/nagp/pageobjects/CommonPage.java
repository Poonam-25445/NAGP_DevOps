package com.nagp.pageobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;

import com.nagp.keywords.MKeywords;
import com.nagp.keywords.SeKeywords;

public class CommonPage {
	private CommonPage() {
		throw new UnsupportedOperationException("Cannot instantiate utility class");
	}
	private static final String PAGENAME = PAGENAME;	
	private static final String USER_INFO = "UserInfo";
	
	public static final String MY_ACCOUNT = "My Account";
	public static final String MY_WISH_LIST = "My Wish List";
	public static final String SIGN_OUT = "Sign Out";
	protected static final List<String> LOGIN_USER_LINKS = new ArrayList<>(
			Arrays.asList(MY_ACCOUNT, MY_WISH_LIST, SIGN_OUT));
	public static final String WELCOME_MESSAGE = "Welcome, Poonam Verma!";
	
	/**
	 * Checks whether Url starts with Https.
	 *
	 * @return true , If yes
	 */
	public static String getURL() {
		return SeKeywords.getCurrentURL();
	}
	
	/**
	 * Get the page title.
	 *
	 * @return String, if page title verified.
	 */
	public static String getPageTitle() {
		return SeKeywords.getPageTitle();
	}

	/**
	 * Verify default welcome message on home screen
	 * 
	 * @return true/false
	 */
	public static boolean verifyDefaultWelcomMessage() {
		By defaultMessage = MKeywords.findElement(PAGENAME, "DefaultLoginMsg");
		SeKeywords.waitForElementVisibility(defaultMessage, 30);
		return SeKeywords.isElementVisible(defaultMessage);
	}

	/**
	 * Verify Welcome user drodown info options
	 * 
	 * @return true/false
	 */
	public static boolean verifyDropDownValuesInUserMenu(List<String> linkListForAccessDeniedPage) {
		By userInfo = MKeywords.findElement(PAGENAME, USER_INFO);
		SeKeywords.waitForElementToBeClickable(userInfo, 15);
		List<String> displayedList = MKeywords.getDropdownValues(userInfo,
				MKeywords.findElement(PAGENAME, "WelcomeList"));
		displayedList.remove(displayedList.size() - 1); // remove the copyright string
		return MKeywords.compareTwoArrayOfStrings(linkListForAccessDeniedPage, displayedList);
	}
	
	
	/**
	 * Click welcome list.
	 */
	public static void clickUserInfo(){
		SeKeywords.click(MKeywords.findElement(PAGENAME, USER_INFO));
	}
	
	/**
	 * Select welcome list option from drop down list.
	 *
	 * @param value		value user wants to select.
	 */
	public static void selectWelcomeListOption(String value){
		By userInfo = MKeywords.findElement(PAGENAME, USER_INFO);
		SeKeywords.waitForStalenessOfElement(userInfo, 30);  
		MKeywords.selectDropdownValue(userInfo, MKeywords.findElement(PAGENAME, "WelcomeList"), value);
	}

}
