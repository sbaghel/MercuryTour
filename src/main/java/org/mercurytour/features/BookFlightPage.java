package org.mercurytour.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookFlightPage {
	
	private WebDriver driver;
	
	public BookFlightPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public BookFlightPage initElements() {
		return PageFactory.initElements(driver, BookFlightPage.class);
	}
	
	@FindBy(name="passFirst0")
	private WebElement textPassengerOneFirstName;
	
	@FindBy(name="passLast0")
	private WebElement textPassengerOneLastName;
	
	@FindBy(name="passFirst1")
	private WebElement textPassengerTwoFirstName;
	
	@FindBy(name="passLast1")
	private WebElement textPassengerTwoLastName;
	
	@FindBy(name="creditnumber")
	private WebElement textCreditCardNumber;
	
	@FindBy(name="buyFlights")
	private WebElement buttonSecurePurchase;
	
	//Method to purchase flight tickets. Returns FlightConfirmation Page.
	public FlightConfirmationPage purchaseFlightTickets() {
		try {
			textPassengerOneFirstName.sendKeys("Akshath");
			textPassengerOneLastName.sendKeys("Kumar");
			textPassengerTwoFirstName.sendKeys("Romit");
			textPassengerTwoLastName.sendKeys("Mehra");
			textCreditCardNumber.sendKeys("7845965842457120");
			buttonSecurePurchase.click();
		} catch(Exception e) {
			e.printStackTrace();
		} return new FlightConfirmationPage(driver);
	}
	

}
