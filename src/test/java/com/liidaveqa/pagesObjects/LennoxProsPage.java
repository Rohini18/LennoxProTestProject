package com.liidaveqa.pagesObjects;

import java.io.File;
import java.text.DateFormat;
import java.text.spi.DateFormatProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LennoxProsPage {

	protected static Actions builder = null;

	protected WebDriver ldriver;
	public WebDriverWait wait;

	public LennoxProsPage(WebDriver driver) {
		ldriver = driver;
		wait = new WebDriverWait(ldriver, 3);
	}

	// Page Objects

	protected By signInLink = By.xpath("//a[text()='Sign In']");
	protected By userName = By.id("j_username");
	protected By passWord = By.id("j_password");
	protected By loginButton = By.id("loginSubmit");

	protected By menuButton = By.xpath("//a/i[@class='far fa-bars v2-hamburger-menu']");
	protected By salesMenu = By.xpath("//a[text()='Sales Tools']");
	protected By proposalMenu = By.xpath("//a[text()='Build a Proposal']");
	protected By leadButton = By.xpath("//a[text()='SELECT LEAD']");
	protected By addLeadButton = By.xpath("//*[@id=\"content\"]/div[2]/div[4]/div[2]/div[2]/div[1]/div[2]/a[2]");

	protected By firstName = By.id("firstName");
	protected By lastName = By.id("lastName");
	protected By phoneField = By.id("phNo");
	protected By emailField = By.id("email");
	protected By calenderInput = By.id("calender1");
	protected By calenderTable = By.id("ui-datepicker-div");
	protected By timeDropdown = By.id("scheduleRequestTime");
	protected By addFile = By.className("open-add-document");
	protected By docType = By.xpath("//*[@id=\"modal-edit-lead-add-doc\"]/div/div/div[2]/div[2]/div[1]/div/select");
	protected By uploadFile = By.id("multipleFileSelect-1");
	protected By addUploadedFile = By.xpath("//*[@id=\"modal-edit-lead-add-doc\"]/div/div/div[6]/a[2]");
	protected By uploadImgFile = By.id("multipleImageSelect[0]");
	protected By saveBtn = By.id("btn-addLeadsForm");
	protected By successMsg = By.id("globalMessages");

	// Page Actions
	private WebElement identify(By locator) throws Exception {
		WebElement element = null;
		try {
			element = ldriver.findElement(locator);
		} catch (Exception e) {
			throw new Exception("Failed To find the element:" + locator);
		}
		return element;
	}

	private void clickOn(By locator) throws Exception {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			identify(locator).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			throw new Exception("Failed To Click On:" + locator);
		}
	}

	// Enter value to the field
	protected void type(String text, By locator) throws Exception {
		try {
			identify(locator).clear();
			identify(locator).sendKeys(text);
			Thread.sleep(1000);
		} catch (Exception e) {
			throw new Exception("Failed To Type:" + text + " Inside:" + locator);
		}
	}

	public void upload(String filename, By locator) throws Exception {
		try {
			File file = new File(filename);
			identify(locator).sendKeys(file.getAbsolutePath());
			Thread.sleep(1000);
		} catch (Exception e) {
			throw new Exception("Failed To Upload");
		}
	}

	public boolean selectByWithValue(By element, String data) throws Exception {
		boolean bReturn = false;
		try {
			Select dropdown = new Select(identify(element));
			dropdown.selectByValue(data);
			bReturn = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed To select radio button with value" + data);
		}
		return bReturn;
	}

	public void openURL(String url) throws Exception {
		try {

			ldriver.get(url);
			ldriver.manage().window().maximize();
			Thread.sleep(3000);
		} catch (Exception e) {
			throw new Exception("Failed to open URL");
		}
	}

	public void login(String username, String password) throws Exception {
		try {
			clickOn(signInLink);
			type(username, userName);
			type(password, passWord);
			clickOn(loginButton);
			Thread.sleep(3000);
		} catch (Exception e) {
			throw new Exception("Failed to login to Application");
		}
	}

	public void navigateToPage() throws Exception {
		try {

			clickOn(menuButton);
			clickOn(salesMenu);
			clickOn(proposalMenu);
			clickOn(leadButton);
			Thread.sleep(3000);
		} catch (Exception e) {
			throw new Exception("Failed to login to Application");
		}
	}

	public void addLead(String firstname, String lastname, String phone, String email, String scheduledDate,
			String time, String pdfFile, String imageFile) {
		try {
			clickOn(addLeadButton);
			type(firstname, firstName);
			type(lastname, lastName);
			type(phone, phoneField);
			type(email, emailField);
			selectDate(scheduledDate);
			selectTime(time);
			if ((pdfFile != "") && (pdfFile != null)) {
				UploadpdfFile(pdfFile);
			}
			if ((imageFile != "") && (imageFile != null)) {
				UploadImageFile(imageFile);
			}
			clickOn(saveBtn);
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void selectDate(String scheduledDate) {
		String[] scheduledDateSplitter = scheduledDate.split("-");
		try {
			clickOn(calenderInput);
			WebElement datePicker = identify(calenderTable);
			boolean flag = false;
			do {
				if (!(datePicker.findElement(By.className("ui-datepicker-month")).getText())
						.equals(scheduledDateSplitter[1])
						|| (!(datePicker.findElement(By.className("ui-datepicker-year")).getText())
								.equals(scheduledDateSplitter[2]))) {
					identify(By.xpath("//span[text()='Next']")).click();
				} else {
					flag = true;
				}
				if (flag == true) {
					datePicker.findElement(By.tagName("table"))
							.findElement(By.xpath("//td/a[text()='" + scheduledDateSplitter[0] + "']")).click();
					break;
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selectTime(String time) {
		try {
			Thread.sleep(2000);
			selectByWithValue(timeDropdown, time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void UploadpdfFile(String fileName) {
		try {
			Thread.sleep(2000);
			String name = "./resources/Uploads/" + fileName + ".pdf";
			clickOn(addFile);
			selectByWithValue(docType, "OTHER");
			upload(name, uploadFile);
			clickOn(addUploadedFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void UploadImageFile(String fileName) {
		try {
			String name = "./resources/Uploads/" + fileName + ".jpg";
			upload(name, uploadImgFile);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getMessage() throws Exception {
		return (identify(successMsg).getText().trim());

	}
	public void closeApp()  {
		ldriver.close();
		ldriver.quit();

	}
	
	
}
