package com.lawn.starter.automation.ui.pageobject;

import com.lawn.starter.automation.core.CoreAutomation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GetYourQuoteModalPage extends PageObject {
    private CoreAutomation coreAutomation;

    public GetYourQuoteModalPage(CoreAutomation coreAutomation) {
        super(coreAutomation.getDriver());
    }

    @FindBy(css = "ls-panel ls-get-quote-form__panel")
    private WebElement modalPageLawnStartElement;

    @FindBy(css = "button.ls-button.ls-button--block.ls-button--lg.ls-button--yellow")
    private WebElement getYourQuoteButton;

    @Override
    public void isLoaded() {
        if (!waitForVisibilityOf(modalPageLawnStartElement)
                || !waitForVisibilityOf(getYourQuoteButton)) {
            throw new Error("Lawn Starter Page not loaded");
        }

    }

    @Override
    public boolean isNotLoaded() {
        return waitForInvisibilityOfElement(modalPageLawnStartElement, 15)
                && waitForInvisibilityOfElement(getYourQuoteButton, 15);
    }

    public void clickGetYourQuote(){
        waitForVisibilityOf(getYourQuoteButton);
        click(getYourQuoteButton);
    }
}
