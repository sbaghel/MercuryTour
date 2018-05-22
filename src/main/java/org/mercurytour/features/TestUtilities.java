package org.mercurytour.features;

import org.testng.ITestResult;

public class TestUtilities
{
  public static String getIdentifier(ITestResult paramITestResult)
  {
    int i = printParameters(paramITestResult.getParameters()).hashCode();
    if (i < 0) {
      i = -1 * i;
    }
    return paramITestResult.getInstanceName() + "." + paramITestResult.getMethod().getMethodName() + "#" + i;
  }
  
  public static String printParameters(Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (Object localObject : paramArrayOfObject)
    {
      if (localStringBuilder.length() > 0) {
        localStringBuilder.append(',');
      }
      localStringBuilder.append("'").append(localObject).append("'");
    }
    return localStringBuilder.toString();
  }
  
 
}
