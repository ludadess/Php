package tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

import utils.MyDriverClass;

	 
	 
	public class Listener implements ITestListener{
		private static WebDriver driver = MyDriverClass.getDriver();
	   
		private static String getTestMethodName(ITestResult iTestResult) {
	        return iTestResult.getMethod().getConstructorOrMethod().getName();
	    }
	    
	  
	    //Before starting all tests, below method runs.
	    @Override
	    public void onStart(ITestContext iTestContext) {
	        System.out.println("I am in onStart method " + iTestContext.getName());
	        iTestContext.setAttribute("WebDriver", driver);
	      }
	 
	    //After ending all tests, below method runs.
	    @Override
	    public void onFinish(ITestContext iTestContext) {
	        System.out.println("I am in onFinish method " + iTestContext.getName());
	        //Do tier down operations for extentreports reporting!
	        ExtentTestManager.endTest();
	        ExtentManager.getReporter().flush();
	    }
	 
	    @Override
	    public void onTestStart(ITestResult iTestResult) {
	    	System.out.println("################################ Test "+iTestResult.getName()+" started");
	        //System.out.println("I am in onTestStart method " + iTestResult.getName()  + " start");
	        //Start operation for extentreports.
	        ExtentTestManager.startTest(iTestResult.getName(),"");
	    }
	 
	    @Override
	    public void onTestSuccess(ITestResult iTestResult) {
	        //System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
	        System.out.println("################################ Test "+iTestResult.getName()+" completed");
	        //Extentreports log operation for passed tests.
	        ExtentTestManager.getTest().log(LogStatus.PASS, iTestResult.getName(),"Test passed");
	    }
	 
	    @Override
	    public void onTestFailure(ITestResult iTestResult) {
	        //System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
	        System.out.println("################################ Test "+iTestResult.getName()+" failed");
	 
	        //Object testClass = iTestResult.getInstance();
	        WebDriver driver = MyDriverClass.getDriver();
	       	File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("MM_dd-HH_mm_ss").format(Calendar.getInstance().getTime());
			String pathImage = System.getProperty("user.dir") + "\\FailedTestsScreenshoots\\"+timeStamp+"_"+iTestResult.getName()+".png";
			try {
				FileUtils.copyFile(src, new File(pathImage));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	       
	        //Extentreports log and screenshot operations for failed tests.
			ExtentTestManager.getTest().log(LogStatus.FAIL, ExtentTestManager.getTest().addScreenCapture(pathImage),iTestResult.getThrowable().getMessage());
			ExtentTestManager.getTest().log(LogStatus.FAIL, iTestResult.getName(), "Test Failed");
			
	    }
	 
	    @Override
	    public void onTestSkipped(ITestResult iTestResult) {
	    	
	        //System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
	        System.out.println("################################ Test "+iTestResult.getName()+" skipped");
	        //Extentreports log operation for skipped tests.
	        ExtentTestManager.getTest().log(LogStatus.SKIP, iTestResult.getName(), "Test Skipped");
	    }
	 
	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	    }
}
