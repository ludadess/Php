package reusableflows;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pages.PropertyOrder1Page;
import pages.PropertyPINPage;
import utils.MyDriverClass;
import utils.Utilities;


public class Property {
	private static Logger log = LogManager.getLogger(Property.class.getName());
	static WebDriver driver = MyDriverClass.getDriver();
	static WebDriverWait wait;
	
	public static void searchPropertyByPIN (String TestSetName) {
		wait = new WebDriverWait(driver,20);
		PropertyPINPage propertyPinPg = new PropertyPINPage();
		Utilities.initilizeJSON ("myjson.json","PropertySearch", TestSetName);
		//wait.until(ExpectedConditions.elementToBeClickable(propertyPinPg.getBlockNumb()));
		propertyPinPg.enterBlockNumb(Utilities.getJSONData("Block#"));
		propertyPinPg.enterPropertyNumb(Utilities.getJSONData("Property#"));
		propertyPinPg.enterRequestedBy(Utilities.getJSONData("RequestedBy"));
		propertyPinPg.clickSearch();
		log.info("The following search data was entered: ");
		log.info("Block#: "+Utilities.getJSONData("Block#"));
		log.info("Property#: "+Utilities.getJSONData("Property#"));
		log.info("RequestedBy: "+Utilities.getJSONData("RequestedBy"));
		log.info("**********Property Search by PIN results page********* ");
		// verify correct PIN is displayed in search results header
		String expPIN = Utilities.getJSONData("Block#")+"-"+Utilities.getJSONData("Property#");
		wait.until(ExpectedConditions.elementToBeClickable(propertyPinPg.getSearchResultsHeader()));
		String abc = propertyPinPg.getSearchResultsHeader().getText();
		if(abc.contains(expPIN))
			System.out.println(propertyPinPg.getSearchResultsHeader().getText());
		else
			try {throw new Exception("PIN is not displayed in the Search Results header");
			} catch (Exception e) {
				e.printStackTrace();
			}
		// verify correct PIN is displayed in search results table
		
		wait.until(ExpectedConditions.elementToBeClickable(propertyPinPg.getSearchResults()));
		String actPIN = propertyPinPg.getSearchResults().getText();
		Assert.assertEquals(actPIN, expPIN);
		log.info("Search results: "+propertyPinPg.getSearchResults().getText()+" is displayed");
	}
	
	
	public static void selectCalendar(int numOfMonthsAgo ) {
		Calendar c = Calendar.getInstance(); 
	    c.setTime(new Date());
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    String dateTo = sdf.format(c.getTime());
	    Utilities.addPropData("userdata", "DateTo", dateTo);
	   
	    c.add(Calendar.MONTH, -1 * numOfMonthsAgo);
	    String dateFrom = sdf.format(c.getTime());
	    Utilities.addPropData("userdata", "DateFrom", dateFrom);
	    SimpleDateFormat sdf1 = new SimpleDateFormat("MMM yyyy");
	    String dateFromUpper = (sdf1.format(c.getTime())).toUpperCase();
	        
	    PropertyOrder1Page propertyOrder1Pg = new PropertyOrder1Page();
		int i =0;
		while(!dateFromUpper.equalsIgnoreCase(propertyOrder1Pg.getCalendarPeriod())) {
			propertyOrder1Pg.clickGoPrevMonthBtn();
			i++;
			if (i>numOfMonthsAgo)
				break;
		}
	    SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM dd yyyy");
	    String dayLocator = "td[aria-label='"+sdf2.format(c.getTime())+"']";
	    driver.findElement(By.cssSelector(dayLocator)).click();
	    log.info("Date From ia selected as :"+ sdf2.format(c.getTime()) );
			
	}
}
