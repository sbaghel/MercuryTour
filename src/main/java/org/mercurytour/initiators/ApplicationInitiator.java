package org.mercurytour.initiators;

import java.awt.Robot;
import java.io.IOException;

import org.mercurytour.features.RetryAnalyzer;
import org.openqa.selenium.Platform;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.olo.initiator.InitiatorUtil;



public class ApplicationInitiator extends InitiatorUtil{
	String browser=ConfigPropertiesForMercuryTour.getBrowserName();	
	
	@BeforeClass(alwaysRun=true)
	public void beforeSuite(ITestContext context) {
	     for (ITestNGMethod method : context.getAllTestMethods()) {
	         method.setRetryAnalyzer(new RetryAnalyzer());
	     }
	}

	@BeforeMethod
	public void beforeTestMethod(ITestContext ctx) throws Exception{
		configureDriver(ctx);
		if(browser.contains("internet explorer"))
        {     
			   Robot r=new Robot();
        	   r.mouseMove(500,0);
               System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/"+Platform.WINDOWS.toString()+"/IEDriverServer.exe");
        try {
               Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 1");
               Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2");
               } catch (IOException e) {
               e.printStackTrace();
               }
        }else if(browser.contains("fir")){
        	
        }
		openUrl(ConfigPropertiesForMercuryTour.getApplicationUrl());

	}
	
	@AfterMethod(alwaysRun=true)
	public void afterTestMethod(ITestResult result){
		captureScreenShotOnTestFailure(result);
		closeDriver();
	}
}
