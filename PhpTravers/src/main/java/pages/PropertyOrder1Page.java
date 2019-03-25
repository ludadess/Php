package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriverClass;


public class PropertyOrder1Page {
	private static Logger log = LogManager.getLogger(PropertyOrder1Page.class.getName());
	private WebDriver driver;
	private static WebDriverWait wait;
	
	By pageHeader = By.xpath("//h3[contains(text(),'Step 1 of 2: Parcel Register Options')]");
	By continueBtn = By.cssSelector("button.btn.btn-primary.continue");
	By custDateRangeBtn = By.xpath("//button[contains(text(),'Custom Date Range')]");
    By dateRangeStartBtn = By.xpath("//button[@aria-label='Open Start Date Calendar']");
    By goPrevMonthBtn = By.cssSelector("button.mat-calendar-previous-button.mat-icon-button");
    //By calendarPeriod = By.cssSelector("span.mat-button-wrapper");
    By calendarPeriod = By.xpath("//button[@aria-label='Select from table of years']");
   
	
	public PropertyOrder1Page() {
		this.driver = MyDriverClass.getDriver();	
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(pageHeader));
		log.info("**********Property Order Step1 page********* ");
	}
	
	public void clickContinueBtn() {driver.findElement(continueBtn).click();}
	public void clickCustDateRangeBtn() {driver.findElement(custDateRangeBtn).click();}
	public void clickDateRangeStartBtn() {driver.findElement(dateRangeStartBtn).click();}
	public void clickGoPrevMonthBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(goPrevMonthBtn));
		driver.findElement(goPrevMonthBtn).click();}
	
	public String getCalendarPeriod() {
		wait.until(ExpectedConditions.elementToBeClickable(calendarPeriod));
		WebElement element = driver.findElement(calendarPeriod);
		return element.getText(); }
	}
