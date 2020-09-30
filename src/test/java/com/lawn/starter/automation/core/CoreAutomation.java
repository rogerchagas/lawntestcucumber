package com.lawn.starter.automation.core;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CoreAutomation {

	private final Logger log = Logger.getLogger(CoreAutomation.class);

	private CoreConfig config;

	private EventFiringWebDriver driver;

	/***
	 * <h1>CoreAutomation</h1>
	 * <p>
	 * purpose: Load environment.
	 * </p>
	 */
	public CoreAutomation() {
		// Grab config values from config.json
		config = new CoreConfig();

		// Configure platform URLs, set driver, set browser
		this.driver = loadEnvironment();

		// Set system properties for Cucumber
		setDefaultTimeOut();

	}

	/**
	 * <h1>config</h1>
	 * <p>
	 * purpose: Grab this session's config instance
	 * </p>
	 * 
	 * @return CoreConfig for this session
	 */
	public CoreConfig config() {
		return config;
	}

	public EventFiringWebDriver getDriver() {
		return driver;
	}

	public String getOS() {
		String os = System.getProperty("os.name").toLowerCase();
		String returnOS = "ERROR";

		if (os.contains("win")) {
			returnOS = "windows";
		} else if (os.contains("mac")) {
			returnOS = "mac";
		} else if (os.contains("nux")) {
			returnOS = "linux";
		}
		return returnOS;

	}


	private EventFiringWebDriver loadEnvironment() {
		EventFiringWebDriver driver = null;
		setDriverPath(getOS());
		driver = new EventFiringWebDriver(new ChromeDriver());
		Objects.requireNonNull(driver).manage().window().maximize();
		return driver;
	}

	private void setDriverPath(String os) {

		System.setProperty("webdriver.firefox.logfile", "drivers/logs/firefox/fire.log");
		switch (os) {
		case "mac":
			System.setProperty("webdriver.chrome.driver", config.getValue("chromeDriverLocation_mac"));
			System.setProperty("webdriver.gecko.driver", config.getValue("firefoxDriverLocation_mac"));
			break;
		case "windows":
			System.setProperty("webdriver.chrome.driver", config.getValue("chromeDriverLocation_windows"));
			System.setProperty("webdriver.gecko.driver", config.getValue("firefoxDriverLocation_windows"));
			System.setProperty("webdriver.ie.driver", config.getValue("IE11DriverLocation_windows"));
			break;
		case "linux":
			System.setProperty("webdriver.chrome.driver", config.getValue("chromeDriverLocation_linux"));
			System.setProperty("webdriver.gecko.driver", config.getValue("firefoxDriverLocation_linux"));
			break;
		default:
			System.out.println("ERROR: The OS, '" + os + "' is unkown.");
		}
	}

	private void setDefaultTimeOut() {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getValue("timeout")), TimeUnit.SECONDS);
		System.setProperty("timeOut", config.getValue("timeout"));
		System.setProperty("timeOutUnit", config.getValue("timeoutUnit"));
	}

	public void PrintScreenResolution() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get the dimension of screen
		System.out.println("Screen Width: " + screenSize.getWidth());
		System.out.println("Screen Height: " + screenSize.getHeight());
	}

	public void waitForNumberOfWindowsToEqual(final int numberOfWindows) {
		new WebDriverWait(driver, 10) {
		}.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (driver.getWindowHandles().size() == numberOfWindows);
			}
		});
	}
}
