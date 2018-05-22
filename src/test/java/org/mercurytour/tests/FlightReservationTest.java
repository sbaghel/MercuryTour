package org.mercurytour.tests;

import org.mercurytour.features.AccountPage;
import org.mercurytour.features.BookFlightPage;
import org.mercurytour.features.FindFlightPage;
import org.mercurytour.features.FlightConfirmationPage;
import org.mercurytour.features.MercuryTourHomePage;
import org.mercurytour.features.SelectFlightPage;
import org.mercurytour.features.SignOnPage;
import org.mercurytour.initiators.ApplicationInitiator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlightReservationTest extends ApplicationInitiator {
	
	AccountPage objAccountPage;
	SignOnPage objSignOnPage;
	FindFlightPage objFindFlightPage;
	SelectFlightPage objSelectFlightPage;
	BookFlightPage objBookFlightPage;
	FlightConfirmationPage objFlightConfirmationPage;
	
	//Method to test flight reservation
	@Test()
	public void testFlightReservation() throws Exception {
		WebDriver driver=getDriver();
		objSignOnPage=new MercuryTourHomePage(driver).initElements().clickSignOnLink();
		objFindFlightPage=objSignOnPage.initElements().signIn();
		boolean flag=objFindFlightPage.initElements().assertSignIn();
		Assert.assertTrue(flag);
		objSelectFlightPage=objFindFlightPage.initElements().findFlight();
		objBookFlightPage=objSelectFlightPage.initElements().selectFlight();
		objFlightConfirmationPage=objBookFlightPage.initElements().purchaseFlightTickets();
		flag=objFlightConfirmationPage.initElements().assertFlightBooking();
		Assert.assertTrue(flag);
	}
	
	
	
	

}
