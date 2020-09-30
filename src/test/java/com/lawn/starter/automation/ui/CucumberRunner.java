package com.lawn.starter.automation.ui;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:target/cucumber-report-files/cucumber.json"},
        features = {"src/test/resources/features/"},
        glue = {"com.lawn.starter.automation.ui.stepdefs"})
public class CucumberRunner {
	

}
