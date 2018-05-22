package org.mercurytour.tests;

import org.mercurytour.features.AccountPage;
import org.mercurytour.features.MercuryTourHomePage;
import org.mercurytour.features.RegistrationPage;
import org.mercurytour.initiators.ApplicationInitiator;
import org.mercurytour.initiators.ConfigPropertiesForMercuryTour;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends ApplicationInitiator {
	
	RegistrationPage objRegistrationPage;
	AccountPage objAccountPage;
	
	//Method to test new user registration
	@Test
	public void testRegisterNewUser() throws Exception {
		WebDriver driver=getDriver();
		objRegistrationPage=new MercuryTourHomePage(driver).initElements().clickRegisterLink();
		objAccountPage=objRegistrationPage.initElements().registerNewUser();
		boolean flag=objAccountPage.initElements().assertNewUserRegistration(ConfigPropertiesForMercuryTour.getUserName());
		Assert.assertTrue(flag);
	}

}
