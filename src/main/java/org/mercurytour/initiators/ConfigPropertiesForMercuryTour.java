package org.mercurytour.initiators;

import java.util.Properties;

import org.testng.log4testng.Logger;

import com.olo.propertyutil.ConfigProperties;

public class ConfigPropertiesForMercuryTour extends ConfigProperties{
	
	private static final Logger LOGGER = Logger.getLogger(ConfigPropertiesForMercuryTour.class);
	protected static Properties configProp = new Properties();
	
	static{
		loadProperties();
	}
	
	private static void loadProperties() {
		try {
			if(configProp.isEmpty()){
				configProp.load(ConfigPropertiesForMercuryTour.class.getResourceAsStream("/config/config.properties"));
			}
		} catch (Exception e) {
			LOGGER.error("An error occured while loading configuration properties "+e.getMessage());
			System.exit(1);
		}
	}
	
	public static String getApplicationUrl(){
		return configProp.getProperty("applicationUrl");
	}
	
	public static String getCapabilitiesFile(){
		return configProp.getProperty("capabilitiesFile");
	}
	
	public static boolean getRunOnGrid(){
		try {
			return Boolean.parseBoolean(configProp.getProperty("runOnGrid"));
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getHubUrl(){
		return configProp.getProperty("hubURL");
	}
	
	public static int getImplicitWait(){
		try {
			return Integer.parseInt(configProp.getProperty("implicitWait"));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int getPageLoadTimeout(){
		try {
			return Integer.parseInt(configProp.getProperty("pageLoadTimeout"));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static int getWaitTimeOut(){
		try {
			return Integer.parseInt(configProp.getProperty("waitTimeOut"));
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static boolean getWindowMaximize(){
		try {
			return Boolean.parseBoolean(configProp.getProperty("windowMaximize"));
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getBrowserName()
	{
		return configProp.getProperty("browserName");
	}
	
	public static void setBrowserVersion(String version){
		configProp.setProperty("version", version);
	}
	
	public static String getBrowserVersion(){
		return configProp.getProperty("version");
	}
	
	public static void setPlatform(String platform){
		configProp.setProperty("platform", platform);
	}
	
	public static String getPlatform(){
		return configProp.getProperty("platform");
	}
	
	public static String getFirstName(){
		return configProp.getProperty("firstName");
	}
	
	public static String getLastName(){
		return configProp.getProperty("lastName");
	}
	
	public static String getPhone(){
		return configProp.getProperty("phone");
	}
	
	public static String getEmail() {
		return configProp.getProperty("email");
	}
	
	public static String getAddress(){
		return configProp.getProperty("address");
	}
	
	public static String getCity(){
		return configProp.getProperty("city");
	}
	
	public static String getState(){
		return configProp.getProperty("state");
	}
	
	public static String getPostalCode(){
		return configProp.getProperty("postalCode");
	}
	
	public static String getUserName(){
		return configProp.getProperty("userName");
	}
	
	public static String getPassword(){
		return configProp.getProperty("password");
	}
	
	public static String getConfirmPassword(){
		return configProp.getProperty("confirmPassword");
	}
	
}
