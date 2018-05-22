package org.mercurytour.features;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.internal.Utils;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlSuite;

import com.olo.mailer.MailClient;
import com.olo.propertyutil.MailProperties;
import com.olo.reporter.Utility;
import com.olo.util.Commons;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CustomReporter implements IReporter{
	
	private static final Logger LOGGER = Logger.getLogger(CustomReporter.class);
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		try {
			generateNewIndex(suites,outputDirectory);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
	}
	
	private void generateNewIndex(List<ISuite> suites,String outputDirectory){
	    
		int totalFailedTests = 0;
		int totalPassedTests = 0;
		int totalSkippedTests = 0;
		long startTimeOfSuites = 0;
	    long endTimeOfSuites = 0;
	    String title="Test Results";
	    
	    /*finding the start time to sort the list
	     */
	    for (ISuite suite : suites) {
	    	long suiteStartTime=0;
	    	long temp = 0;
	    	int ctr = 0;
	    	Map<String, ISuiteResult> results = suite.getResults();
	    	for (ISuiteResult suiteResult : results.values()) {
	    		ITestContext suiteTestContext = suiteResult.getTestContext();
	    		if(ctr == 0){
	    			suiteStartTime=suiteTestContext.getStartDate().getTime();
	    			ctr++;
	    		}else{
	    			temp = suiteTestContext.getStartDate().getTime();
	    			if(temp < suiteStartTime)
	    				suiteStartTime = temp;
				}
	    	}
	    	suite.setAttribute("suiteStartTime_sort", suiteStartTime);
	    }
	    
	    Collections.sort(suites, Utility.suiteStartComp);
	    
	    StringBuffer suiteListDetails = new StringBuffer();
	    
	    int totalSuitesSize = suites.size();
	    int suiteCounter = 1;
	    for (ISuite suite : suites) {
	    	if (suite.getResults().size() == 0) {
				continue;
			}
	    	
			
			int suiteFailedTests=0;
			int suitePassedTests=0;
			int suiteSkippedTests=0;
			long suiteStartTime=0;
			long suiteEndTime=0;
			long temp = 0;
			int ctr = 0;
			int noOfTestsFailedThreeTimes = 0;
		    
			
			
			Map<String, ISuiteResult> results = suite.getResults();
			for (ISuiteResult suiteResult : results.values()) {
				ITestContext suiteTestContext = suiteResult.getTestContext();
				List<ITestNGMethod> methodsToRemove = new ArrayList<ITestNGMethod>();
				

		        for(ITestResult failed_result : suiteTestContext.getFailedTests().getAllResults())
		        {
		        	String failed_testName = failed_result.getMethod().getMethodName();
		            String failingTest_className = failed_result.getClass().getName();
		            for(ITestResult passed_result : suiteTestContext.getPassedTests().getAllResults())
		            {
		            	String passing_testName = passed_result.getMethod().getMethodName();
		                String passingTest_className = passed_result.getClass().getName();
		                if(failed_testName.equals(passing_testName) &&  
		                        passingTest_className.equals(failingTest_className) )
		                {
		                	methodsToRemove.add(failed_result.getMethod());
		                       suiteFailedTests=0;
		                     break;

		                }
		            }
		          
		            
		            
		        }

		         for(ITestNGMethod failedMethodToRemove : methodsToRemove)
		        {
		        	suiteTestContext.getFailedTests().removeResult(failedMethodToRemove);
		        }
		         for(int i=0;i<suiteTestContext.getAllTestMethods().length;i++){
		        	 System.out.println("suiteTestContext.getAllTestMethods()[i].getCurrentInvocationCount() "+suiteTestContext.getAllTestMethods()[i].getCurrentInvocationCount());
		        	if(suiteTestContext.getAllTestMethods()[i].getCurrentInvocationCount()==3){
		        		System.out.println("inside 1st if statement");
		 				if (suiteTestContext.getFailedTests().getResults(suiteTestContext.getAllTestMethods()[i]).size() == 3 ){
		 					System.out.println("inside 2nd if if statement");
		 				//	suiteTestContext.getFailedTests().removeResult(suiteTestContext.getAllTestMethods()[i]);
		 					//suiteFailedTests=suiteTestContext.getFailedTests().size()-3;
		 					System.out.println("suiteTestContext.getFailedTests().size() "+suiteTestContext.getFailedTests().size());
		 				//	noOfTestsFailedThreeTimes=suiteTestContext.getFailedTests().size()-3+1;
		 					//suiteFailedTests=suiteFailedTests+suiteTestContext.getFailedTests().size()-3;
		 					noOfTestsFailedThreeTimes=noOfTestsFailedThreeTimes+1;
		 					System.out.println("noOfTestsFailedThreeTimes "+noOfTestsFailedThreeTimes);
		 				}
		 			}
		        //	suiteFailedTests=suiteFailedTests+noOfTestsFailedThreeTimes;
		        	System.out.println("noOfTestsFailedThreeTimes "+noOfTestsFailedThreeTimes);
		 		}
		         suiteFailedTests=noOfTestsFailedThreeTimes;
		         //     suiteFailedTests+=suiteTestContext.getFailedTests().size();
				suitePassedTests+= suiteTestContext.getPassedTests().size();
				suiteSkippedTests+=suiteTestContext.getSkippedTests().size();
				if(ctr == 0){
					suiteStartTime=suiteTestContext.getStartDate().getTime();
					suiteEndTime=suiteTestContext.getEndDate().getTime();
					ctr++;
				}else{
					temp = suiteTestContext.getStartDate().getTime();
					if(temp < suiteStartTime)
						suiteStartTime = temp;
					temp = suiteTestContext.getEndDate().getTime();
					if(temp > suiteEndTime)
						suiteEndTime = temp;
				}
			}
			if(suiteCounter == 1){
				startTimeOfSuites=suiteStartTime;
	    	}
			if(suiteCounter == totalSuitesSize){
				endTimeOfSuites = suiteEndTime;
			}
			suiteListDetails.append(Utility.suiteListTableDetailsRow(false,suite.getName(),suiteStartTime,suiteEndTime,suitePassedTests,suiteFailedTests,suiteSkippedTests));
			ctr=0;
			totalFailedTests+=suiteFailedTests;
			totalPassedTests+=suitePassedTests;
			totalSkippedTests+=suiteSkippedTests;
			suiteCounter++;
	    }
	    
	    StringBuffer suitesSummaryHtml = new StringBuffer();
	    suitesSummaryHtml.append(Utility.getHtmlToHead());
	    suitesSummaryHtml.append(Utility.suitesSummaryHead(title, totalPassedTests, totalFailedTests, totalSkippedTests));
	    suitesSummaryHtml.append(Utility.endHeadAndStartBody());
	    suitesSummaryHtml.append(Utility.startContainer());
	    suitesSummaryHtml.append(Utility.headerTitle(title));
	    suitesSummaryHtml.append(Utility.startRow());
	    suitesSummaryHtml.append(Utility.configTableDiv());
	    suitesSummaryHtml.append(Utility.chartDiv());
	    suitesSummaryHtml.append(Utility.endRow());
	    suitesSummaryHtml.append(Utility.startResponsiveTableDiv());
	    suitesSummaryHtml.append(Utility.startTableWithHover());
	    suitesSummaryHtml.append(Utility.suiteListTableHeaderRow());
	    suitesSummaryHtml.append(suiteListDetails);
	    suitesSummaryHtml.append(Utility.suitesSummaryRow(startTimeOfSuites, endTimeOfSuites, totalPassedTests, totalFailedTests, totalSkippedTests));
	    suitesSummaryHtml.append(Utility.endTable());
	    suitesSummaryHtml.append(Utility.endResponsiveTableDiv());
	    suitesSummaryHtml.append(Utility.endContainerToHtml());
	   /* 
	    String fileName = "suites-summary-index.html";
	    Utils.writeFile(outputDirectory, fileName, suitesSummaryHtml.toString());*/
	    String fileName = "HappyTrips-Mail-Suites-Summary-Index.html";
	    Utils.writeFile(outputDirectory, fileName, suitesSummaryHtml.toString());
	    
	    try{
    		String cc = MailProperties.getMailCC();
	    	String to = MailProperties.getMailTo();
	    	String subject = "HappyTrips Suites Summary Report - Total : "+(totalPassedTests+totalFailedTests+totalSkippedTests)+"; Passed : "+totalPassedTests+"; Failed : "+totalFailedTests+"; Skipped : "+totalSkippedTests;
	    	StringBuffer body = new StringBuffer();
	    	body.append("Execution Summary Report is in below mentioned location.<br/><br/>Ip Address : "+Commons.getSystemIpAddress()+"<br/>Folder Path : "+outputDirectory);
	    	MailClient mail = new MailClient();
	    	if(cc==null || cc.equals("")){
	    		mail.sendMail(to.split(","), subject, body,outputDirectory+File.separator+fileName);
	    	}else{
	    		mail.sendMail(to.split(","), subject, body, cc.split(","), outputDirectory+File.separator+fileName);
	    	}
	    	LOGGER.info("Suites Summary mail sent");
	    }catch(Exception e){
	    	LOGGER.error("Mail sending failed!!");
	    	e.printStackTrace();
	    }
	    
	}
	
}
