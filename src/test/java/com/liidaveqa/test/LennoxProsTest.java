package com.liidaveqa.test;

import java.lang.reflect.Method;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.liidaveqa.base.Base;
import com.liidaveqa.dataProvider.MyDataProvider;
import com.liidaveqa.pagesObjects.LennoxProsPage;

public class LennoxProsTest extends Base {


	@Test(dataProvider="LoginData",dataProviderClass= MyDataProvider.class)
	public void TC01_LoginAndNavigate(Map<String, String> testData,Method method) {
		testCase=parentTestCase.createNode(method.getName());
		try {
			lennoxPage.openURL(testData.get("URL"));
			testCase.log(Status.INFO, "Navigated to URL");
			lennoxPage.login(testData.get("username"), testData.get("password"));
			testCase.log(Status.INFO, "Logged In Successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider="AddLeadData",dataProviderClass= MyDataProvider.class)
	public void TC02_AddLeadTest(Map<String, String> testData,Method method) {
		testCase=parentTestCase.createNode(method.getName());
		try {
			lennoxPage.navigateToPage();
			lennoxPage.addLead(testData.get("First Name"), testData.get("Last Name"), testData.get("Phone Number"), testData.get("Email"),testData.get("Scheduled Date"),testData.get("Scheduled Time"),testData.get("Document Name"),testData.get("Image Name"));
			Assert.assertEquals(lennoxPage.getMessage(), "Lead Saved Successfully");
			testCase.log(Status.INFO, testData.get("First Name") +" "+ lennoxPage.getMessage());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterSuite
	public void close() {
		lennoxPage.closeApp();
	}
}
