package selenium;

import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClsBrowser extends ClsWebElements {
	
	
	public static String BrowserName;
	protected static WebDriver objDriver;
    public static WebDriverWait wait;
    
    
    public ClsBrowser() {
		BrowserName = "Chrome";
	}
 
    
    public WebDriver OpenBrowser()
    {
		switch (BrowserName.toUpperCase()) {
		case "CHROME":
			System.setProperty("webdriver.chrome.driver", "./src/test/resources/selenium_driver/chromedriver.exe");
			objDriver = new ChromeDriver();
			objDriver.manage().window().maximize();
			objDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			objDriver.manage().deleteAllCookies();
			break;
		case "EDGE":
			System.setProperty("webdriver.edge.driver", "./src/test/resources/selenium_driver/msedgedriver.exe");
			objDriver = new EdgeDriver();
			break;
		case "FIREFOX":
			System.setProperty("webdriver.geckodriver.driver", "./src/test/resources/selenium_driver/gecko.exe");
			objDriver = new FirefoxDriver();
			break;
		default:
			System.out.println("The browser is "+ BrowserName +" not supported.");
			break;
		}

        return objDriver;
    }
    
    
    

    public void CloseBrowser()
    {
        objDriver.close();
        objDriver.quit();
    }

    public void NavigateToUrl(String pstrUrl)
    {
		try{
			ClsReport.fnLog(Status.INFO, "Step - Getting URL: " + pstrUrl, false);
			objDriver.get(pstrUrl);
			ClsReport.fnLog(Status.PASS, "Step - Getting URL: " + pstrUrl + " was found as expected.",false );
		}catch (Exception e){
			ClsReport.fnLog(Status.FAIL, "Step - Getting URL: " + pstrUrl + " was found not found.", true);
		}

    }
	
	
}
