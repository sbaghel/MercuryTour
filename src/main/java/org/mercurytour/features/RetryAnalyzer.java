package org.mercurytour.features;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.log4j.Logger;
import org.testng.util.RetryAnalyzerCount;


public class RetryAnalyzer extends RetryAnalyzerCount implements IRetryAnalyzer  {
    private final int MAX_COUNT = 2;
    private final Map<String, Integer> retryCount = new HashMap();
    private Logger logger = Logger.getLogger(RetryAnalyzer.class.getName());
    private FileWriter writer;
   
    
    public RetryAnalyzer() {
        setCount(MAX_COUNT);
    }

    @Override
    public boolean retryMethod(ITestResult paramITestResult) {
      String str = TestUtilities.getIdentifier(paramITestResult);
      int i = 1;
      if (this.retryCount.containsKey(str)) {
        i = ((Integer)this.retryCount.get(str)).intValue();
      }
      boolean bool=false;
      if (i <= MAX_COUNT) {
      	  this.logger.info("CURRENT COUNT "+ i + "TEST CASE NAME "+ str);
          this.retryCount.put(str, Integer.valueOf(i + 1));
          bool = true;
      }
      return bool;
    }
}




