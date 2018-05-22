package org.mercurytour.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightConfirmationPage {
	
	private WebDriver driver;
	
	public FlightConfirmationPage(WebDriver driver) {
		this.driver=driver;
	}

	public FlightConfirmationPage initElements() {
		return PageFactory.initElements(driver, FlightConfirmationPage.class);
	}
	
	boolean flag=false;
	
	@FindBy(xpath="//font[contains(text(),'Confirmation')]")
	private WebElement textFlightConfirmation;
	
	//Verify if flight booking is successful. Returns boolean value
	public boolean assertFlightBooking() {
		try {
			String confirmationMessage=textFlightConfirmation.getText();
			flag=confirmationMessage.contains("Flight Confirmation");
		} catch(Exception e) {
			e.printStackTrace();
		} return flag;
	}
}
