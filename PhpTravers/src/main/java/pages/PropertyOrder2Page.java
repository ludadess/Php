package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriverClass;


public class PropertyOrder2Page {
	private static Logger log = LogManager.getLogger(PropertyOrder2Page.class.getName());
	private WebDriver driver;
	private static WebDriverWait wait;
	
	By pageHeader = By.xpath("//h3[contains(text(),'Step 2 of 2: Choose Purchase Options')]");
	By addCertCopyLink = By.xpath("//add-certified-copy-toggle[@class='full-width']//div//button[@class='link']");
	By AddToCartBtn = By.cssSelector("button.btn.btn-primary.fixed-width");

	
	
	public PropertyOrder2Page() {
		this.driver = MyDriverClass.getDriver();	
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(pageHeader));
		log.info("**********Property Order Step2 page********* ");
	}
	
	public void clickAddCertCopy() {
		wait.until(ExpectedConditions.elementToBeClickable(addCertCopyLink));
		driver.findElement(addCertCopyLink).click();}
	
	public void clickAddToCartBtn() {
		driver.findElement(AddToCartBtn).click();}
}
