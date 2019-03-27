package tests;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.Utilities;

public class Listener2 implements ITestListener{

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("################################Test "+result.getName()+" started");
	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("################################Test "+result.getName()+" completed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("################################Test "+result.getName()+" failed");
		Utilities.getScreenShot(result.getName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("################################Test "+result.getName()+" skipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
