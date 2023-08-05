package com.nagp.keywords;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.nagp.core.DriverFactory;
import com.nagp.logs.LoggingManager;

/**
 * This class contains all the keywords created using Java script.
 */
public class JavaScriptKeywords {
	
	public static final String SCROLL_TO_ELEMENT = "scrolled to element: ";
	
	private JavaScriptKeywords() {
		throw new UnsupportedOperationException("Cannot instantiate utility class");
	}

	/**
	 * This method click on element using java script by a WebElement.
	 *
	 * @param element element on page.
	 */
	public static void clickUsingJs(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getDriver();
		MKeywords.sleep(1000);
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * This method focus out of element using java script by a By element.
	 * 
	 * @param byElement - element on page
	 */
	public static void focusOutUsingJs(By byElement) {
		WebElement webElement = DriverFactory.getDriver().findElement(byElement);
		JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getDriver();
		executor.executeScript("arguments[0].blur();", webElement);
	}

	/**
	 * This method waits for 60 seconds max for ajax call to complete.
	 */
	public static void waitForAjax() {
		int seconds = 10;
		try {
			LoggingManager.getConsoleLogger().info("Number of ajax connections found: "
					+ ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("return jQuery.active"));
			if ((Long) ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("return jQuery.active") > 0) {
				LoggingManager.getConsoleLogger().info("Waiting for ajax call to complete...");
				new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(seconds))
						.until(new ExpectedCondition<Boolean>() {
							public Boolean apply(WebDriver driver) {
								Boolean isAjaxCallComplete = (Boolean) ((JavascriptExecutor) DriverFactory.getDriver())
										.executeScript("return jQuery.active == 0");
								if (isAjaxCallComplete) {
									LoggingManager.getConsoleLogger().info("\"" + "Ajax call completed." + "\"");
								}
								return isAjaxCallComplete;
							}
						});
			}
			MKeywords.sleep(2000);
		} catch (TimeoutException e) {
			LoggingManager.getConsoleLogger()
					.info("\"" + "Ajax call did not complete. Waited for " + seconds + " seconds. " + e + "\"");
		} catch (Exception e) {
			LoggingManager.getConsoleLogger().info("\"" + "Ajax call did not complete." + e + "\"");
		}
	}

	/**
	 * This method scroll the page to view upward.
	 *
	 * @param element    element user wants to see into view.
	 * @param scrollDown true if element user scrolling to is below the where user
	 *                   currently are or false if element user scrolling to is
	 *                   above where user currently are.
	 */
	public static void scrollElementIntoView(WebElement element, boolean scrollDown) {
		JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getDriver();
		executor.executeScript("arguments[0].scrollIntoView(" + scrollDown + ");", element);
		LoggingManager.getReportLogger().log(Status.INFO,
				"\"" + SCROLL_TO_ELEMENT + "\"" + element.getText() + "\"");
		LoggingManager.getConsoleLogger().info("\"" + SCROLL_TO_ELEMENT + "\"" + element.getText() + "\"");
	}

	/**
	 * This method scroll the page to view upward.
	 *
	 * @param element    element user wants to see into view.
	 * @param scrollDown true if element user scrolling to is below the where user
	 *                   currently are or false if element user scrolling to is
	 *                   above where user currently are.
	 */
	public static void scrollElementIntoView(By element, boolean scrollDown) {
		WebElement webElement = DriverFactory.getDriver().findElement(element);
		JavascriptExecutor executor = (JavascriptExecutor) DriverFactory.getDriver();
		executor.executeScript("arguments[0].scrollIntoView(" + scrollDown + ");", webElement);
		LoggingManager.getReportLogger().log(Status.INFO,
				"\"" + SCROLL_TO_ELEMENT + "\"" + webElement.getText() + "\"");
		LoggingManager.getConsoleLogger().info("\"" + SCROLL_TO_ELEMENT + "\"" + webElement.getText() + "\"");
	}
}
