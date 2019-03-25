package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriverClass;


public class AddToCartPopUpPage {
	private static Logger log = LogManager.getLogger(AddToCartPopUpPage.class.getName());
	private WebDriver driver;
	private static WebDriverWait wait;
	
	By pageHeader = By.cssSelector("h1[id^='mat-dialog-title-']");
	By checkoutBtn = By.id("selectCheckout");
	By continueBtn = By.id("selectContinue");
	
	
	public AddToCartPopUpPage() {
		this.driver = MyDriverClass.getDriver();	
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(pageHeader));
		log.info("**********Add To Cart PopUp********* ");
	}
	
	public void clickCheckoutBtn() {driver.findElement(checkoutBtn).click();}
	public void clickContinueBtn() {driver.findElement(continueBtn).click();}
}
