package com.lawn.starter.automation.ui.stepdefs.home;

import com.lawn.starter.automation.core.CoreAutomation;

import com.lawn.starter.automation.core.User;
import com.lawn.starter.automation.core.Util;
import com.lawn.starter.automation.ui.pageobject.GetYourQuoteModalPage;
import com.lawn.starter.automation.ui.pageobject.LawnStarterPage;
import com.lawn.starter.automation.ui.pageobject.ProfilePage;
import com.lawn.starter.automation.ui.pageobject.SingleSignUpFormPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

public class SIgnUpStepdefs {
	
	private static final Logger log = Logger.getLogger(SIgnUpStepdefs.class);


	private CoreAutomation coreAutomation;
	private LawnStarterPage lawnStarterPage;
	private GetYourQuoteModalPage getYourQuoteModalPage;
	private SingleSignUpFormPage singleSignUpFormPage;
	private ProfilePage profilePage;
	
	public SIgnUpStepdefs(CoreAutomation coreAutomation) {
		this.coreAutomation = coreAutomation;
		lawnStarterPage = new LawnStarterPage(coreAutomation);
		getYourQuoteModalPage = new GetYourQuoteModalPage(coreAutomation);
		singleSignUpFormPage = new SingleSignUpFormPage(coreAutomation);
		profilePage = new ProfilePage(coreAutomation);
	}


	@Given("^I have accessed the home Page$")
	public void iHaveAccessedTheHomePage(){
		lawnStarterPage.goToHomePage();
	}

	@Given("^I have provide my basic info to get a quote for lawn service:$")
	public void iHaveProvidedMyBasicInfo(DataTable dataTable){
		Map<String, List<String>> registration = new Util().putDataTableIntoMap(dataTable);
		User user = new User();
		user.setAddress(registration.get("Address").get(0));
		user.setFirstName(registration.get("First Name").get(0));
		user.setPhone(registration.get("Phone").get(0));
		lawnStarterPage.fillFormSeePrice(user.getAddress(),user.getPhone(),user.getFirstName());
		lawnStarterPage.clickSeePrice();
		getYourQuoteModalPage.clickGetYourQuote();

	}

	@When("^I fill the form from single sign up with the follow information:$")
	public void iFillTheFormFromSIngleSignUp (DataTable dTable) {
		Map<String, List<String>> registration = new Util().putDataTableIntoMap(dTable);
		User user = new User();
		user.setFirstName(registration.get("First Name").get(0));
		user.setLastName(registration.get("Last Name").get(0));
		user.setPhone(registration.get("Phone").get(0));
		user.setCardNumber(registration.get("Card Number").get(0));
		user.setExpDate(registration.get("Exp Date").get(0));
		user.setExpYear(registration.get("Exp Year").get(0));
		user.setCvv(registration.get("CVV").get(0));
		singleSignUpFormPage.completeServiceSteps(user.getLastName(), new Util().getRandomEmail(),user.getCardNumber(),
				user.getExpDate(),user.getExpYear(),user.getCvv()).
				completePropertyDetailsFlow().
					completePolicyPage().
						completeShrubsAndHedgesPage();

	}

	@Then("^I can see my profile on the Lawn Starter Page Profile$")
			public void iCanSeeTheProfileOnTheLawnStarterPage(){
		profilePage.completeFlowAndAccept().
						verifyUserNameAndLogout();

	}

	}


