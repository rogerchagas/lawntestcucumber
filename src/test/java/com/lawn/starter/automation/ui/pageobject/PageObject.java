package com.lawn.starter.automation.ui.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageObject extends BasePageActions {
	
	
	 public PageObject(WebDriver driver) {
	        super(driver);
	        PageFactory.initElements(driver, this);
	    }

	    public abstract void isLoaded();

	    public abstract boolean isNotLoaded();
	}

