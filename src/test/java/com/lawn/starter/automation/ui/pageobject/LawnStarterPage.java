package com.lawn.starter.automation.ui.pageobject;

import com.lawn.starter.automation.core.CoreAutomation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LawnStarterPage extends PageObject {

    private CoreAutomation coreAutomation;
    private String URL = "https://dev-www.lawnstarter.com/";


    @FindBy(id = "streetAddress")
    private WebElement streetAddressInput;

    @FindBy(name = "name")
    private WebElement nameInput;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(css = "button.ls-hero__button.js-submitButton")
    private WebElement seePriceButton;

    @FindBy(css = "btn btn-rounded btn-signin")
    private WebElement signInButton;

    public LawnStarterPage(CoreAutomation coreAutomation)
    {
        super(coreAutomation.getDriver());
    }


    @Override
    public void isLoaded() {
        if (!waitForVisibilityOf(seePriceButton)
                || !waitForVisibilityOf(signInButton)) {
            throw new Error("Lawn Starter Page not loaded");
        }

    }

    @Override
    public boolean isNotLoaded() {
        return waitForInvisibilityOfElement(seePriceButton, 15)
                && waitForInvisibilityOfElement(signInButton, 15);
    }


    public LawnStarterPage goToHomePage() {
        goToUrl(URL);
        return this;
    }

    public LawnStarterPage fillFormSeePrice(String address, String phone, String firstName){
        typeWithTab(address,streetAddressInput);
        type(firstName,nameInput);
        type(phone,phoneInput);
        return this;
    }

    public void clickSeePrice(){
        click(seePriceButton);
    }

}
