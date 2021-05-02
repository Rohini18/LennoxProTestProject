package com.liidaveqa.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.liidaveqa.pagesObjects.LennoxProsPage;
import com.liidaveqa.utilities.Excelutility;

public class Base {
	public ExtentHtmlReporter htmlReporter;

	public static ExtentReports extentReport;
	public static ExtentTest parentTestCase;
	public static ExtentTest testCase;
	protected static String customValue;
	public static Excelutility excelReader;

	public static LennoxProsPage lennoxPage;

	protected WebDriver webdriver = null;

	public static String getdate() {
		String dateName = new SimpleDateFormat("ddMMyyyy-HH.mm.ss").format(new Date());
		return dateName;
	}

	public void startUp(String browserName) {

		if(browserName.equalsIgnoreCase("CHROME")) {
		System.setProperty("webdriver.chrome.driver", "./resources/Executables/chromedriver.exe");
		webdriver = new ChromeDriver();
		}
		

	}
	
	public void initializeTestData() {
	excelReader = new Excelutility("./resources/TestData/" + "testdata.xlsx");
	}
	
	public void initializeTestReport() throws Exception {
		customValue = ("Lennox Report" + "_" + getdate());
		/* <---------- Creating extent report in specified location ---------> */
		htmlReporter = new ExtentHtmlReporter("./Results/" + customValue + ".html");
		htmlReporter.config().setReportName("LENNOXPRO");
		htmlReporter.config().setTheme(Theme.DARK);
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
	}

}
