package utils;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyDriverClass {
	
	private static WebDriver driver;
	public static final String USERNAME = "Valdess";
	public static final String ACCESS_KEY = "bcc2229e-853b-4290-a1b4-3392eba941df";
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	
	
	public static WebDriver getDriver() { return driver; }
	
	public static WebDriver Initialize() throws IOException {
		String browserSession = Utilities.readProperty("config","browser" );
		if (browserSession.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe");
			driver = new ChromeDriver();			
		}
		else if(browserSession.contentEquals("firefox"))	{
			System.setProperty("webdriver.gecko.driver", "C:\\Work\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (browserSession.contentEquals("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\Work\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if (browserSession.contentEquals("cloud")){
		
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			 caps.setCapability("platform", "Windows 10");
			 caps.setCapability("version", "latest");
			 driver = new RemoteWebDriver(new URL(URL), caps);		
		}		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		return driver;
	}

}
