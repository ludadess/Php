package tests;

import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import tests.ExtentTestManager;
import utils.MyDriverClass;
 
public class Retry implements IRetryAnalyzer {
 
    private int count = 0;
    private static int maxTry = 1; //Run the failed test 2 times
 
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
            if (count < maxTry) {                            //Check if maxtry count is reached
                count++;                                     //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                extendReportsFailOperations(iTestResult);    //ExtentReports fail operations
                return true;                                 //Tells TestNG to re-run the test
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
 
    public void extendReportsFailOperations (ITestResult iTestResult) {
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
}