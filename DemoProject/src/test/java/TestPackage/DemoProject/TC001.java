package TestPackage.DemoProject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TC001 extends BasePage{
	
	public static final Logger log = Logger.getLogger(TC001.class.getName());
  @Test
  public void f() throws FileNotFoundException, IOException {
	  
	  openBrowser("ChromeBrowser");
	  log.info("opened the browser -"+ loaddata("ChromeBrowser"));
	  Reporter.log("opened the browser -"+ loaddata("ChromeBrowser"));
	  
	  navigate("AmazonUrl");
	  log.info("opened the URL -"+ loaddata("AmazonUrl"));
	  Reporter.log("opened the URL -"+ loaddata("AmazonUrl"));
	  
	  type("amazonsearchtext_id","Mobiles");
	  log.info("Entered the text -" + " Mobiles " + "With using locator - "+ loaddata("amazonsearchtext_id"));
	  Reporter.log("Entered the text -" + " Mobiles " + "With using locator - "+ loaddata("amazonsearchtext_id"));
	  
	  click("amazonsearchbutton_xpath");
	  log.info("Clicked the Amazon Search button with using locator -" +  loaddata("amazonsearchbutton_xpath"));
	  Reporter.log("Clicked the Amazon Search button with using locator -" +  loaddata("amazonsearchbutton_xpath"));
  }






}
