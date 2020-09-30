package com.lawn.starter.automation.ui.stepdefs.hooks;

import java.time.Instant;
import java.util.Date;

import com.lawn.starter.automation.core.CoreAutomation;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	CoreAutomation automation;
	EventFiringWebDriver driver;

	public Hooks(CoreAutomation automation) {
		this.automation = automation;
		this.driver = automation.getDriver();

	}

	@Before
	public void beforeCallingScenario(Scenario scenario) {
		System.out.println("=======Starting Scenario: " + scenario.getName());

	}

	@After
	public void afterRunningScenario(Scenario scenario) {
		String out = String.format("=======Finishing Scenario: " + scenario.getName() +
			"\nLocal time: " + new java.util.Date().toString() +
			"\nUTC: " + Instant.now().toString());
		System.out.println(out);
		
		
		// get browser logs if failed 
		if (scenario.isFailed()) {
			LogEntries logs = driver.manage().logs().get("browser");
			for (LogEntry entry : logs) {
	            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
			}
		}


			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png"); // stick it in the report

		driver.quit();
	}

}
