package org.mercurytour.features;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.olo.bot.BrowserBot;

public class AccountPage {
	
	private WebDriver driver;
	BrowserBot browser;
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public AccountPage initElements(){
		return PageFactory.initElements(driver, AccountPage.class);
	}
	
	@FindBy(css="a>font>b")
	private WebElement textRegisteredUserName;
	
	@FindBy(linkText=" sign-in")
	private WebElement linkSignIn;
	
	boolean flag=false;
	
	//Method to verify if new user registration is successful. Returns boolean value
	public boolean assertNewUserRegistration(String userName) {
		try {
			String registeredUserName=textRegisteredUserName.getText();
			flag=registeredUserName.contains(userName);
			
		} catch(Exception e) {
			e.printStackTrace();
		}return flag;
	}
	
	//Method to click on Sign In link. Returns SignOn Page
	public SignOnPage clickSignInLink() {
		try {
			linkSignIn.click();
			
		} catch(Exception e) {
			e.printStackTrace();
		}return new SignOnPage(driver);
	}
	
	

}