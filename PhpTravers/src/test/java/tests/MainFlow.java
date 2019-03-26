package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.AddToCartPopUpPage;
import pages.HomePage;
import pages.PropertyOrder1Page;
import pages.PropertyOrder2Page;
import pages.PropertyPINPage;
import pages.YourShoppingCartPage;
import reusableflows.Property;
import utils.MyDriverClass;
import utils.Utilities;



public class MainFlow {
	private static Logger log = LogManager.getLogger(MainFlow.class.getName());
	private WebDriver driver;
	
		
	@BeforeClass
	public void InitilizeDriver() throws IOException {
		driver = MyDriverClass.Initialize();
		driver.get(Utilities.readProperty("config","url"));
	}
	
	@Test (priority =1)
	public void locateLRO(){
//************ Home page ******************************
		HomePage homePg = new HomePage();
		homePg.selectLRO("80");
		log.error("=========== LRO 80 is selected================== ");
		homePg.clickPropertyBtn();
		log.warn("********** Search Property button is clicked********* ");
	}
	@Test (priority =2, dependsOnMethods="locateLRO")
	public void propertyByPINSearch(){
		//************ Search Property by PIN page ******		
		PropertyPINPage propertyPinPg = new PropertyPINPage();
		Property.searchPropertyByPIN("PIN");
		propertyPinPg.clickBuyParcelRegBtn();
	}
	
	@Test (priority =3, dependsOnMethods="propertyByPINSearch")
	public void propertyOrderStep1(){
//************ Property Order Step1  ***********************	
		PropertyOrder1Page propertyOrder1Pg = new PropertyOrder1Page();
		propertyOrder1Pg.clickCustDateRangeBtn();
		propertyOrder1Pg.clickDateRangeStartBtn();
		Property.selectCalendar(15);
		propertyOrder1Pg.clickContinueBtn();
	}
	
	@Test (priority =4, dependsOnMethods="propertyOrderStep1")
	public void propertyOrderStep2(){
//************ Property Order Step2  ***********************		
		PropertyOrder2Page propertyOrder2Pg = new PropertyOrder2Page();
		propertyOrder2Pg.clickAddCertCopy();
		log.fatal("**********Add Certified Copy link is selected********* ");
		propertyOrder2Pg.clickAddToCartBtn();
		log.info("**********Add To Cart button is clicked********* ");
	}
	
	@Test (priority =5, dependsOnMethods="propertyOrderStep2")
	public void propertyOrderAddToCart(){
//************ Add To Cart pop up  ***********************			
		AddToCartPopUpPage addToCartPg = new AddToCartPopUpPage();
		addToCartPg.clickCheckoutBtn();
		log.info("**********Checkout button is clicked********* ");
		
	}
	@Test (priority =6, dependsOnMethods="propertyOrderAddToCart")
	public void YourShoppingCart(){
//************ Your Shopping Cart page  ***********************			
		YourShoppingCartPage yourShoppCartPg = new YourShoppingCartPage();
		yourShoppCartPg.clickCheckoutBtn();
		log.info("**********Checkout button is clicked********* ");
		
	}
	
	@AfterClass
	public void closeBrowser() {	
		driver.close();
		
		
	}
}