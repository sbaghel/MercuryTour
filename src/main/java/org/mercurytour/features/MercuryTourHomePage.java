package org.mercurytour.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.olo.bot.BrowserBot;

public class MercuryTourHomePage {
	
	private WebDriver driver;
	BrowserBot browser;
	
	public MercuryTourHomePage(WebDriver driver) {
		this.driver =  driver;
	}
	
	public MercuryTourHomePage initElements(){
		return PageFactory.initElements(driver, MercuryTourHomePage.class);
	}
	
	@FindBy(linkText="REGISTER")
	private WebElement linkRegister;
	
	@FindBy(linkText="SIGN-ON")
	private WebElement linkSignOn;
	
	//Method to click on register link. Returns Registration Page
	public RegistrationPage clickRegisterLink() {
		try {
			linkRegister.click();
		} catch(Exception e) {
			e.printStackTrace();
		}return new RegistrationPage(driver);
	}
	
	//Method to click on signon link. Returns SignOn Page
	public SignOnPage clickSignOnLink() {
		try {
			linkSignOn.click();
		} catch(Exception e) {
			e.printStackTrace();
		} return new SignOnPage(driver);
	}

}
