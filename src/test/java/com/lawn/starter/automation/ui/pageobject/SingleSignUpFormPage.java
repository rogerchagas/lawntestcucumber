package com.lawn.starter.automation.ui.pageobject;

import com.lawn.starter.automation.core.CoreAutomation;
import cucumber.runtime.junit.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class SingleSignUpFormPage extends PageObject {


    public SingleSignUpFormPage(CoreAutomation coreAutomation) {
        super(coreAutomation.getDriver());
    }
    @FindBy(xpath = "//h2[text()=' Schedule Your Lawn Service ']")
    private WebElement titleSignUpForm;

    /*Page object for Service Onboarding*/

    @FindBy(css = "div.mowing-selection__option")
    private List<WebElement>listOptionsFrequency;

    @FindBy(css = "input.ls-input.has-icon")
    private WebElement startDate;

    @FindBy(id = "fname")
    private WebElement firstName;

    @FindBy(id = "lname")
    private WebElement lastNameInputElement;

    @FindBy(id = "tel")
    private WebElement phoneNumber;

    @FindBy(id = "email")
    private WebElement emailElement;

    @FindBy(id = "ccnumber")
    private WebElement creditCardNumberInputElement;

    @FindBy(id = "exp_month")
    private WebElement selectMonth;

    @FindBy(id = "exp_year")
    private WebElement selectYear;

    @FindBy(id = "cvv2")
    private WebElement cvvInputElement;

    @FindBy(id = "leadSource")
    private WebElement chooseOneQuestion;

    @FindBy(id = "terms-of-service")
    private WebElement termsOfService;

    @FindBy(css = "button.ls-button.ls-button--block.ls-button--lg.ls-button--yellow.ls-reserve-booking__button")
    private WebElement buttonReserveYourBooking;

    /*Page object for Property Details Onboarding*/
    @FindBy(xpath = "//span[text()='2. Property Details']")
    private WebElement propertyDetailsPageTitle;

    @FindBy(name = "gate.has_gate")
    private List<WebElement> radioHasGate;

    @FindBy(name = "mowing_preferences.scope")
    private WebElement radioLawnPreference;

    @FindBy(xpath = "//span[text()='SAVE & CONTINUE']")
    private WebElement buttonSaveAndContinue;

    @FindBy(css = "label.b-radio.radio.lock-gate-input--code-div")
    private WebElement gateCodeElement;

    @FindBy(xpath = "//input[@name='lock_gate_input-code']")
    private List<WebElement> inputElements;

    @FindBy(className = "under-24")
    private WebElement under24Element;

    @FindBy(name = "mowing_preferences.scope")
    private List<WebElement> radioMowingPreference;

    @FindBy(name = "multitenant.is_multitenant")
    private List<WebElement>isDuplexOptions;

    @FindBy(name = "community.restricted")
    private List<WebElement> radioCommunityRestricted;

    @FindBy(xpath = "//span[text()='agree & continue']")
    private WebElement agreeContinueButtonPolicy;

    @FindBy(xpath = "//span[text()='3. Policies']")
    private WebElement policyPageTitle;

    @FindBy(xpath = "//span[text()='yes']")
    private WebElement buttonQuestionEarlySchedule;

    @FindBy(xpath = "//h1[text()='Shrubs and Hedges']")
    private WebElement titleShrubsAndHedgesPages;

    @FindBy(name = "bushes_0ft_5ft_count")
    private WebElement bushes0FT5FTCountSelect;

    @FindBy(name = "bushes_5ft_10ft_count")
    private WebElement bushes5FT10FTCountSelect;

    @FindBy(name = "bushes_over_10ft_count")
    private WebElement bushesOver10FTCountSelect;

    @FindBy(xpath = "//span[text()='submit & continue']")
    private WebElement buttonSubmitAndContinue;



    @Override
    public void isLoaded() {
        if (!waitForVisibilityOf(titleSignUpForm)
                || !waitForVisibilityOf(titleSignUpForm)) {
            throw new Error("Lawn Starter Page not loaded");
        }

    }

    @Override
    public boolean isNotLoaded() {

        return waitForInvisibilityOfElement(titleSignUpForm, 15)
                && waitForInvisibilityOfElement(titleSignUpForm, 15);
    }

    public SingleSignUpFormPage completeServiceSteps(String lastName ,String email,String creditCardNumber,
                                                     String expDateMonth,String expDateYear,
                                                     String cvv){
        waitForVisibilityOf(titleSignUpForm,10);
        click(listOptionsFrequency.get(1));
        type(lastName,lastNameInputElement);
        type(email,emailElement);
        type(creditCardNumber,creditCardNumberInputElement);
        selectOption(selectMonth,expDateMonth);
        selectOption(selectYear,expDateYear);
        type(cvv,cvvInputElement);
        selectOption(chooseOneQuestion,"google");
        if (!termsOfService.isSelected()){
            clickWithJs(termsOfService);
        }
        click(buttonReserveYourBooking);
        return this;
    }
    public SingleSignUpFormPage completePropertyDetailsFlow() {
        waitForVisibilityOf(propertyDetailsPageTitle);

        for (WebElement element:radioHasGate) {
            if (element.getAttribute("value").equals("true")){
                clickWithJs(element);
            }
        }
        click(gateCodeElement);

        inputElements.get(1).sendKeys("1234");
        click(under24Element);

        for (WebElement element:isDuplexOptions) {
            if (element.getAttribute("value").equals("false")) {
                clickWithJs(element);
                break;
            }

        }

        for (WebElement element:radioMowingPreference) {
            if(element.getAttribute("value").equals("frontonly"))   {
                clickWithJs(element);
                break;
            }
        }


        for (WebElement element:radioCommunityRestricted) {
            if (element.getAttribute("value").equals("false")){
                clickWithJs(element);
                break;
            }
        }
        click(buttonSaveAndContinue);

        return this;
    }

    public SingleSignUpFormPage completePolicyPage(){
        waitForVisibilityOf(policyPageTitle);
        click(agreeContinueButtonPolicy);
        waitForVisibilityOf(buttonQuestionEarlySchedule);
        click(buttonQuestionEarlySchedule);
        return this;
    }

    public SingleSignUpFormPage completeShrubsAndHedgesPage(){
        waitForVisibilityOf(titleShrubsAndHedgesPages);
        selectOption(bushes0FT5FTCountSelect,"2");
        selectOption(bushes5FT10FTCountSelect,"2");
        selectOption(bushesOver10FTCountSelect,"2");
        click(buttonSubmitAndContinue);
        return this;
    }

}
