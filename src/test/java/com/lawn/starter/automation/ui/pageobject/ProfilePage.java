package com.lawn.starter.automation.ui.pageobject;

import com.lawn.starter.automation.core.CoreAutomation;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends PageObject {

    @FindBy(xpath = "//h1[text()='Welcome to LawnStarter!']")
    private WebElement welcomeToLawnStarter;

    @FindBy(xpath = "//span[text()='continue to my services']")
    private WebElement buttonContinueMyServices;

    @FindBy(className = "user-menu")
    private WebElement divUserMenuElement;

    @FindBy(className = "user-name")
    private WebElement userNameElement;

    @FindBy(className = "chevron")
    private WebElement chevronMenu;

    @FindBy(css = "span.user-link.sign-out")
    private WebElement signoutButton;


    public ProfilePage(CoreAutomation coreAutomation) {
        super(coreAutomation.getDriver());
    }

    @Override
    public void isLoaded() {
        if (!waitForVisibilityOf(divUserMenuElement)
                || !waitForVisibilityOf(userNameElement)) {
            throw new Error("Lawn Starter Page not loaded");
        }
    }

    @Override
    public boolean isNotLoaded() {
        return waitForInvisibilityOfElement(divUserMenuElement, 15)
                && waitForInvisibilityOfElement(userNameElement, 15);
    }

    public ProfilePage completeFlowAndAccept(){
        waitForVisibilityOf(welcomeToLawnStarter);
        click(buttonContinueMyServices);
        return this;
    }

    public ProfilePage verifyUserNameAndLogout(){
        waitForVisibilityOf(divUserMenuElement);
        click(chevronMenu);
        waitForVisibilityOf(signoutButton);
        click(signoutButton);
        return this;
    }


}
