package TestPackage.DemoProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.stream.FileImageInputStream;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
     public static WebDriver driver;
     public static String path = "./data.properties" ;
     
     public static  String loaddata(String key) throws FileNotFoundException, IOException {
    	 
    	 File f = new File(path);
    	 FileInputStream fis = new FileInputStream(f);
    	 Properties p = new Properties();
          p.load(fis);
          return p.getProperty(key);
     }
     
     
     public static void openBrowser(String browser  ) throws FileNotFoundException, IOException {
    	 
     
    	 
		if( loaddata(browser).equalsIgnoreCase("Chrome")){
    		 
    		 
    		 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"//Driver//chromedriver.exe");
    		 
    	     driver = new ChromeDriver();  	}	
    		 
    		    	 
      	 else if (loaddata(browser).equalsIgnoreCase("FireFox")){
    		 
    		 driver = new FirefoxDriver(); }
    			 
    		 
    	   	 
    	 else if (loaddata(browser).equalsIgnoreCase("IE")){
    		 
    		 System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"//Driver//IEDriverServer.exe");
    	 	 
    	 		  driver = new InternetExplorerDriver();  }
    	    	 
	 String logfile	= "log4j.properties" ;
	 
	 PropertyConfigurator.configure(logfile);
		 
     }
     
     public void navigate(String urlKey) throws FileNotFoundException, IOException {
    		 
    	 driver.get(loaddata(urlKey));
    	 driver.manage().window().maximize();
    	}
     
     public void type(String locatorKey, String data) throws FileNotFoundException, IOException {
    		 
    		//driver.findElement(By.id(loaddata(locatorKey))).sendKeys(data);
    	 getElement(locatorKey).sendKeys(data);
    	}
     
     public WebElement getElement(String locatorKey) throws FileNotFoundException, IOException {
    	 WebElement e = null;
    	 try {
			if(locatorKey.endsWith("_id"))
				e= driver.findElement(By.id(loaddata(locatorKey)));
			 else if(locatorKey.endsWith("_name"))
				 e=  driver.findElement(By.name(loaddata(locatorKey)));
			 else if(locatorKey.endsWith("_xpath"))
				 e=  driver.findElement(By.xpath(loaddata(locatorKey)));
			 else
			 {
				 reportFailure("Locator not correct ---"+ locatorKey );
			 }
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			 
		}
		return e;
		 
		
	}


	public void reportFailure(String Mssg) throws IOException {
		 takeScreenShot();
		 
		
	}


	public void takeScreenShot() throws IOException {
		 
		Date dt = new Date();
		String screenshotFileName	=  dt.toString().replace(":", "_").replace(" ", "_")+".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		  
		  //FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFileName));
		  FileHandler.copy(scrFile, new File(System.getProperty("user.dir")+"//Failure//"+screenshotFileName));
	} 


	public void click(String locatorKey) throws FileNotFoundException, IOException {
 		
    	 driver.findElement(By.xpath(loaddata(locatorKey))).click();
    	}
        
     public   void elementwait(int time, WebElement element) {
    	 
      	  WebDriverWait wt = new WebDriverWait(driver, time);
      		  wt.until(ExpectedConditions.elementToBeClickable(element));
       }
     
     
     public int rnumber() {
 		Random rnum = new Random();
 		int Randnum = rnum.nextInt(99);
 		return Randnum;
 	}
     
     public   void selectoption(WebElement element, int index) {
    	 
    	 Select cal = new Select(element);
    	 cal.selectByIndex(index);
     }
}
   
  

