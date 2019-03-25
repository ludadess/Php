package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriverClass;


public class YourShoppingCartPage {
	private static Logger log = LogManager.getLogger(YourShoppingCartPage.class.getName());
	private WebDriver driver;
	private static WebDriverWait wait;
	
	By pageHeader = By.id("cartTitle");
	By productName = By.className("product-name");
	By productDescription = By.className("product-description");
	By checkoutBtn = By.id("cartTopCheckoutBtn");
	
	
	public YourShoppingCartPage() {
		this.driver = MyDriverClass.getDriver();	
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(pageHeader));
		log.info("**********Your Shopping Cart page ********* ");
	}
	
	public void clickCheckoutBtn() {driver.findElement(checkoutBtn).click();}
	
}
