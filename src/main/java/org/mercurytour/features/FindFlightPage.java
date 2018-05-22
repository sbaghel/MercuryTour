package org.mercurytour.features;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.olo.bot.BrowserBot;

public class FindFlightPage {
	
	private WebDriver driver;
	private BrowserBot browser;
	
	public FindFlightPage(WebDriver driver) {
		this.driver=driver;
		browser=new BrowserBot(driver);
	}
	
	public FindFlightPage initElements() {
		return PageFactory.initElements(driver, FindFlightPage.class);
	}
	
	@FindBy(linkText="SIGN-OFF")
	private WebElement linkSignOff;
	
	@FindBy(name="passCount")
	private WebElement dropdownPassengerCount;
	
	@FindBy(name="fromPort")
	private WebElement dropdownDepartingFrom;
	
	@FindBy(name="fromMonth")
	private WebElement dropdownDepartingMonth;
	
	@FindBy(name="fromDay")
	private WebElement dropdownDepartingDate;
	
	@FindBy(name="toPort")
	private WebElement dropdownArrivingIn;
	
	@FindBy(name="toMonth")
	private WebElement dropdownArrivingMonth;
	
	@FindBy(name="toDay")
	private WebElement dropdownArrivingDate;
	
	@FindBy(name="findFlights")
	private WebElement buttonContinue;
	
	boolean flag=false;
	
	//Method to verify if user signin is successful. Returns boolean value
	public boolean assertSignIn() {
		try {
			flag=linkSignOff.isDisplayed();
		} catch(Exception e) {
			e.printStackTrace();
		} return flag;
	}
	
	//Method to find flight. Returns SelectFlight Page
	public SelectFlightPage findFlight() {
		try {
			new Select(dropdownPassengerCount).selectByValue("2");
			browser.Wait(5);
			new Select(dropdownDepartingFrom).selectByValue("Frankfurt");
			new Select(dropdownDepartingMonth).selectByValue("12");
			new Select(dropdownDepartingDate).selectByValue("22");
			new Select(dropdownArrivingIn).selectByValue("Sydney");
			new Select(dropdownArrivingMonth).selectByValue("1");
			new Select(dropdownArrivingDate).selectByValue("30");
			buttonContinue.click();
		} catch(Exception e) {
			e.printStackTrace();
		}return new SelectFlightPage(driver);
	} 

}
