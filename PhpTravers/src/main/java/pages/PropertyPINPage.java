package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriverClass;


public class PropertyPINPage {
	private static Logger log = LogManager.getLogger(PropertyPINPage.class.getName());
	private WebDriver driver;
	private static WebDriverWait wait;
	
	By pageHeader = By.xpath("//h1[contains(text(),'Property')]");
	By pinRadioBtn = By.className("mat-radio-inner-circle");
	By pinRangeRadioBtn = By.className("mat-radio-outer-circle");
	By blockNumb = By.id("block");
	By propertyNumb = By.id("pin");
	By propertyEndNumb = By.id("pinTo");
	By requestedBy = By.id("requested-by");
	By searchBtn = By.cssSelector("button.btn.btn-primary.requested-by");
	By searchResultsHeader = By.xpath("//h2[contains(text(),'Showing 1 to 1 of 1 search results for PIN')]");
	//By searchResultsHeader = By.xpath("//h2[@class='ng-star-inserted']//span[@class='medium'][contains(text(),'10428-0795')]");
	//By searchResultsHeader = By.cssSelector("h2.ng-star-inserted");
	//By searchResults = By.xpath("//span[@class='medium.no-wrap']");
	By searchResults = By.cssSelector("span.medium.no-wrap");
	By buyParcelRegBtn = By.xpath("//button[@class='btn btn-primary']");
	

	public PropertyPINPage() {
		this.driver = MyDriverClass.getDriver();	
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(pageHeader));
		log.info("**********Property Search by PIN page********* ");
	}
	
	public WebElement getPageHeader() {return driver.findElement(pageHeader);}
	public void selectPinRadioBtn() {driver.findElement(pinRadioBtn).click();}
	public void selectPinRangeRadioBtn() {driver.findElement(pinRangeRadioBtn).click();}
	public WebElement getBlockNumb() { return driver.findElement(blockNumb);}
	public void enterBlockNumb(String blockNumber) {
		wait.until(ExpectedConditions.elementToBeClickable(blockNumb));
		driver.findElement(blockNumb).sendKeys(blockNumber);}
	public void enterPropertyNumb(String propertyNumber ) {driver.findElement(propertyNumb).sendKeys(propertyNumber);}
	public void enterPropertyEndNumb(String propertyEndNumber ) {driver.findElement(propertyEndNumb).sendKeys(propertyEndNumber);}
	public void enterRequestedBy(String requestBy ) {driver.findElement(requestedBy).sendKeys(requestBy);}
	public void clickSearch() {driver.findElement(searchBtn).click();}
	public WebElement getSearchResults() {return driver.findElement(searchResults);}
	public void clickBuyParcelRegBtn() {driver.findElement(buyParcelRegBtn).click();}
	public WebElement getSearchResultsHeader() {
		wait.until(ExpectedConditions.elementToBeClickable(searchResultsHeader));
		return driver.findElement(searchResultsHeader);}
}
