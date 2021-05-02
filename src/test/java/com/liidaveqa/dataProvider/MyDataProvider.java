package com.liidaveqa.dataProvider;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.liidaveqa.base.Base;


public class MyDataProvider extends Base{
	@DataProvider(name = "LoginData")
	public static Object[][] dataProviderMethod1(Method method) throws Exception {
		Object[][] testDataArray = null;
		excelReader.setExcelSheet("Login");
		/* Test method name and value in test data should be same(value highlighted) */
		testDataArray = excelReader.getTestData(method.getName());
		return testDataArray;
	}
	
	@DataProvider(name = "AddLeadData")
	public static Object[][] dataProviderMethod2(Method method) throws Exception {
		Object[][] testDataArray = null;
		excelReader.setExcelSheet("AddLead");
		/* Test method name and value in test data should be same(value highlighted) */
		testDataArray = excelReader.getTestData(method.getName());
		return testDataArray;
	}
}
