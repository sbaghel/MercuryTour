package org.mercurytour.features;

import org.mercurytour.initiators.ConfigPropertiesForMercuryTour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.olo.bot.BrowserBot;

public class SignOnPage {
	
	private WebDriver driver;
	BrowserBot browser;
	
	public SignOnPage(WebDriver driver) {
		this.driver=driver;
	}

	public SignOnPage initElements(){
		return PageFactory.initElements(driver, SignOnPage.class);
	}
	
	@FindBy(name="userName")
	private WebElement textUserName;
	
	@FindBy(name="password")
	private WebElement textPassword;
	
	@FindBy(name="login")
	private WebElement buttonLogin;
	
	//Method to sign in to the application. Returns FindFlight Page	
	public FindFlightPage signIn() {
		try {
			textUserName.sendKeys(ConfigPropertiesForMercuryTour.getUserName());
			textPassword.sendKeys(ConfigPropertiesForMercuryTour.getPassword());
			buttonLogin.click();
		} catch(Exception e) {
			e.printStackTrace();
		} return new FindFlightPage(driver);
	}
	
	
}
