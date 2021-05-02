package com.liidaveqa.listener;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.liidaveqa.base.Base;
import com.liidaveqa.pagesObjects.LennoxProsPage;

public class CustomListener extends Base implements ITestListener, ISuiteListener {


	public void onFinish(ISuite arg0) {
		extentReport.flush();
		
	}

	public void onStart(ISuite suite) {
		try {
			initializeTestReport();
		} catch (Exception e) {
			e.printStackTrace();
		}
		initializeTestData();
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		startUp("Chrome");
		 parentTestCase= extentReport.createTest(context.getCurrentXmlTest().getName());
		 lennoxPage = new LennoxProsPage(webdriver);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult testResult) {
		testCase.log(Status.FAIL, testResult.getThrowable().getLocalizedMessage());
		testCase.log(Status.FAIL, MarkupHelper.createLabel(testResult.getName()+" FAILED ", ExtentColor.RED).getMarkup());
		
	}

	public void onTestSkipped(ITestResult testResult) {
		testCase.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName()+" SKIPPED ", ExtentColor.LIME).getMarkup());
	
	}

	public void onTestSuccess(ITestResult testResult) {
		testCase.log(Status.PASS, MarkupHelper.createLabel(testResult.getName()+" PASSED ", ExtentColor.GREEN).getMarkup());
	
		
	}
}
