package org.mercurytour.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.olo.bot.BrowserBot;

public class SelectFlightPage {
	
	private WebDriver driver;
	BrowserBot browser;
	
	public SelectFlightPage(WebDriver driver) {
		this.driver=driver;
	}

	public SelectFlightPage initElements() {
		return PageFactory.initElements(driver, SelectFlightPage.class);
	}
	
	@FindBy(xpath="//form[@name='results']/table[1]//tr[5]/td/input")
	private WebElement radioButtonDepartFlight;
	
	@FindBy(xpath="//form[@name='results']/table[2]//tr[5]/td/input")
	private WebElement radioButtonReturnFlight;
	
	@FindBy(name="reserveFlights")
	private WebElement buttonContinue;
	
	//Method to select a flight. Returns BookFlight Page
	public BookFlightPage selectFlight() {
		try {
			radioButtonDepartFlight.click();
			radioButtonReturnFlight.click();
			buttonContinue.click();
		} catch(Exception e) {
			e.printStackTrace();
		} return new BookFlightPage(driver);
	}
	
}
