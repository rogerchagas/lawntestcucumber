package com.lawn.starter.automation.ui.pageobject;

import cucumber.api.Scenario;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/***
 * <h1>Class BasePageActions</h1>
 * 
 * @author rogerioreis
 *         <p>
 *         details: This class defines methods for pageObject locator and
 *         manipulation. Should be inherited.
 *         </p>
 */
public abstract class BasePageActions {

	public static final Integer DEFAULT_WAIT_TIMEOUT = 30;

	public static final Long DEFAULT_WAIT_SLEEP = 250L;

	private static final Logger logger = Logger.getLogger(BasePageActions.class);

	protected WebDriver driver;

	public BasePageActions(WebDriver driver) {
		this.driver = driver;
	}

	/* PRIVATE METHODS */

	private FluentWait<WebDriver> getWait(Integer... timeout) {
		int tout = timeout.length > 0 ? timeout[0] : DEFAULT_WAIT_TIMEOUT;
		return new WebDriverWait(this.driver, tout, DEFAULT_WAIT_SLEEP);
	}

	protected void waitFor(ExpectedCondition<?> condition, Integer timeout) {
		getWait(timeout).until(condition);
	}

	private void waitForList(ExpectedCondition<List<WebElement>> condition, Integer timeout) {
		getWait(timeout).until(condition);
	}

	/* PUBLIC METHODS */

	protected void goToUrl(String url) {
		this.driver.get(url);
	}

	protected WebElement find(By locator) {
		return getWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected void click(WebElement element) {
		/*
		 * Chrome dev tools have a bug when emulating mobile devies where the touch
		 * event is inconsistent, sometimes the click event works and other times don't.
		 * This is a known bug
		 * https://bugs.chromium.org/p/chromedriver/issues/detail?id=2599. One solution
		 * is to downgrade the chrome version and the other is to use the execute
		 * javascript to perform the click. So for mobile only we are using that
		 * solution.
		 */

		try {
			element.click();
		} catch (Throwable t) {
		System.out.println("Element not clicable");
		}
	}
	protected void clickWithJs(WebElement element){
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	protected void click(By locator) {
		waitForVisibilityOf(find(locator));
		click(find(locator));
	}

	protected void typeWithTab(String inputText, WebElement element) {
		waitForVisibilityOf(element);
		element.clear();
		element.sendKeys(inputText);
		element.sendKeys(Keys.TAB);
	}

	protected void type(String inputText, WebElement element) {
		waitForVisibilityOf(element);
		element.clear();
		element.sendKeys(inputText);
	}
	protected String getText(WebElement element) {
		waitForVisibilityOf(element);
		String text = element.getText();
		if (StringUtils.isEmpty(text)) {
			text = element.getAttribute("innerText");
		}
		return text.trim();
	}



	protected boolean waitForVisibilityOf(By locator, Integer... timeout) {
		try {
			waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
					(timeout.length > 0 ? timeout[0] : DEFAULT_WAIT_TIMEOUT));
		} catch (Throwable t) {
			return false;
		}
		return true;
	}

	protected boolean waitForVisibilityOf(WebElement element, Integer... timeout) {
		try {
			waitFor(ExpectedConditions.visibilityOf(element), (timeout.length > 0 ? timeout[0] : DEFAULT_WAIT_TIMEOUT));
		} catch (Throwable t) {
			return false;
		}
		return true;
	}

	protected boolean waitForInvisibilityOfElement(WebElement elem, Integer... timeout) {
		try {
			getWait(timeout).until(ExpectedConditions.invisibilityOf(elem));
		} catch (Throwable t) {
			return false;
		}
		return true;
	}

	protected void selectOption(WebElement element, String option) {
		Select select = new Select(element);
		select.selectByValue(option);
	}

	protected void waitForSpinner(By locator) {
		// Check if the spinner is visible before waiting for it
		if (this.doesPageObjectExist(locator)){
			System.out.println(" INFO: Waiting for spinner.");
			int time = DEFAULT_WAIT_TIMEOUT * 2;
			new WebDriverWait(driver, time)
					.withMessage("Test Timed out: Spinner did not disappear after \"" + time + "\" seconds")
					.pollingEvery(Duration.ofMillis(70))
					.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		}
	}
		private Boolean doesPageObjectExist (By locator){
			this.setManualTimeOut(500, TimeUnit.MILLISECONDS);
			List<WebElement> ele = driver.findElements(locator);
			return !ele.isEmpty();
		}

		private void setManualTimeOut (Integer timeOut, TimeUnit timeUnit){
			driver.manage().timeouts().implicitlyWait(timeOut, timeUnit);
		}

	}
