package TestPackage.DemoProject;

import java.util.Date;

import org.testng.annotations.Test;

public class TC002 {
  @Test
  public void f() {
	  
	  Date dt = new Date();
	  
	  System.out.println(dt);
	  
     String Var1	=  dt.toString().replace(":", "_").replace(" ", "_");
     System.out.println(Var1);
  }
}
