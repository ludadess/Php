package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriverClass;


public class HomePage {
	private static Logger log = LogManager.getLogger(HomePage.class.getName());
	private WebDriver driver;
	private WebDriverWait wait;
	By pageHeader = By.xpath("//h1[contains(text(),'Search Ontario Land Property Records')]");
	By lroList = By.cssSelector("input.type-ahead.ng-untouched.ng-pristine.ng-valid");
	By propertyLink = By.cssSelector("a[routerlink='property']");
	By propertyBtn = By.xpath("//ul[@class='search-tile-list container-content']//li[1]//a[1]//search-tile[1]//div[1]//div[3]//div[1]");
	
	public HomePage() {
		this.driver = MyDriverClass.getDriver();	
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(pageHeader));
		log.info("**********Home page********* ");
	}
	
	
	public void selectLRO(String lroNumber) {
		WebElement searchLRO = driver.findElement(lroList);
		Actions action = new Actions(driver);
		action.moveToElement(searchLRO).sendKeys(searchLRO,lroNumber).build().perform();
		try {
		    Thread.sleep(500);
		} catch (InterruptedException e) {
		   e.printStackTrace();
		}
		action.moveToElement(searchLRO).sendKeys(searchLRO,Keys.ENTER).build().perform();
	}
	
	public void clickPropertyBtn() {
		wait.until(ExpectedConditions.elementToBeClickable(propertyBtn));
		driver.findElement(propertyBtn).click();
	}
	public void clickPropertyLink() {
		driver.findElement(propertyLink).click();
	}
}
