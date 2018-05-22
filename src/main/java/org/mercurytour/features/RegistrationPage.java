package org.mercurytour.features;

import org.mercurytour.initiators.ConfigPropertiesForMercuryTour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.olo.bot.BrowserBot;

public class RegistrationPage {
	
	private WebDriver driver;
	BrowserBot browser;
	
	public RegistrationPage(WebDriver driver) {
		this.driver =  driver;
	}
	
	public RegistrationPage initElements(){
		return PageFactory.initElements(driver, RegistrationPage.class);
	}
	
	@FindBy(name="firstName")
	private WebElement textFirstName;
	
	@FindBy(name="lastName")
	private WebElement textLastName;
	
	@FindBy(name="phone")
	private WebElement textPhone;
	
	@FindBy(id="userName")
	private WebElement textEmail;
	
	@FindBy(name="address1")
	private WebElement textAddress;
	
	@FindBy(name="city")
	private WebElement textCity;
	
	@FindBy(name="state")
	private WebElement textState;
	
	@FindBy(name="postalCode")
	private WebElement textPostalCode;
	
	@FindBy(id="email")
	private WebElement textUserName;
	
	@FindBy(name="password")
	private WebElement textPassword;
	
	@FindBy(name="confirmPassword")
	private WebElement textConfirmPassword;
	
	@FindBy(name="register")
	private WebElement buttonSubmit;
	
	//Method to register a new user. Returns Account Page
	public AccountPage registerNewUser() {
		try { 
			textFirstName.sendKeys(ConfigPropertiesForMercuryTour.getFirstName());
			textLastName.sendKeys(ConfigPropertiesForMercuryTour.getLastName());
			textPhone.sendKeys(ConfigPropertiesForMercuryTour.getPhone());
			textEmail.sendKeys(ConfigPropertiesForMercuryTour.getEmail());
			
			textAddress.sendKeys(ConfigPropertiesForMercuryTour.getAddress());
			textCity.sendKeys(ConfigPropertiesForMercuryTour.getAddress());
			textState.sendKeys(ConfigPropertiesForMercuryTour.getState());
			textPostalCode.sendKeys(ConfigPropertiesForMercuryTour.getPostalCode());
						
			textUserName.sendKeys(ConfigPropertiesForMercuryTour.getUserName());
			textPassword.sendKeys(ConfigPropertiesForMercuryTour.getPassword());
			textConfirmPassword.sendKeys(ConfigPropertiesForMercuryTour.getConfirmPassword());
			
			buttonSubmit.click();
						
		} catch(Exception e) {
			e.printStackTrace();
		} return new AccountPage(driver);
		
	}

}
