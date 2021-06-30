package com.ebanking.testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.pdfbox.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.ebanking.Utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	ReadConfig rg=new ReadConfig();
	public String url=rg.getAppUrl();
	public String browser=rg.getbrowser();	

	public String username=rg.getusername();
	public String password=rg.getpassword();
	public String title=rg.gettitle();

	public static WebDriver driver;//static keyword defines constant variable or method i.e.same for every instance of a class & can be called without creating an object

	//WebDriver is a class in selenium and driver is instance variable

	
	
	public static Logger logger;
     @Parameters("browser")
     @BeforeClass
	public void setup(String br) throws InterruptedException  {//method to start browser
		
    		logger=Logger.getLogger("eBanking_project");//for log4j.properties
    		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equalsIgnoreCase("chrome"))
		{
			//System.setProperty("webdriver.chrome.driver",prop.getProperty("chromepath"));//Configuring the system properties of chrome driver.setProperty method has two attributes â€“ â€œpropertyNameâ€ and â€œvalue.â€ The propertyName represents the name of the browser-specific driver, and the value points to the path of that browser driver.
			//OR
			//when webdrivermanager dependecy added to maven,it will update browser version automatically and don't have to use System.setProperty()
			WebDriverManager.chromedriver().setup();
			
			driver=new ChromeDriver();//Initializing the browser driver to access its methods through object
			
			
		}else if(br.equalsIgnoreCase("firefox"))
		{
			//System.setProperty("webdriver.gecko.driver",prop.getProperty("firefoxpath"));//Configuring the system properties of chrome driver.setProperty method has two attributes â€“ â€œpropertyNameâ€ and â€œvalue.â€ The propertyName represents the name of the browser-specific driver, and the value points to the path of that browser driver.
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();//Initializing the browser driver
			
			
		}else if(br.equalsIgnoreCase("edge"))
		{
			//System.setProperty("webdriver.msedge.driver",prop.getProperty("edgepath"));//Configuring the system properties of chrome driver.setProperty method has two attributes â€“ â€œpropertyNameâ€ and â€œvalue.â€ The propertyName represents the name of the browser-specific driver, and the value points to the path of that browser driver.
			
			WebDriverManager.edgedriver().setup();
			
		
			driver=new EdgeDriver();//Initializing the browser driver
			
			
		} 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);//wait for a certain measure of time before throwing an exception
		//driver.navigate().to(prop.getProperty("url"));
		//OR
		//driver.get(prop.getProperty("url"));
		
	//logger=LogManager.getLogger("eBanking_project");....for log4j.xml
		
	
	driver.get(url);
	Thread.sleep(3000);
	logger.info("url opened");
		
	}
     
     public void captureScreenshot(WebDriver driver,String tname) throws IOException
     {
    	 TakesScreenshot ts=(TakesScreenshot) driver;
    	 
			File source=ts.getScreenshotAs(OutputType.FILE);
			File target=new File(".//screenshot//"+tname+".png");
			FileUtils.copyFile(source,target);
			System.out.println("Screenshot taken");
     }
     public String getScreenshotBase64() throws IOException
     {
    	 TakesScreenshot ts=(TakesScreenshot) driver;
    	 
			File source=ts.getScreenshotAs(OutputType.FILE);
			String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
			String scname=System.getProperty("user.dir")+"/scrshots/" + timestamp + ".png";	
			File target=new File(scname);
			FileUtils.copyFile(source,target);
			byte[] imageBytes=IOUtils.toByteArray(new FileInputStream(scname));
			return Base64.getEncoder().encodeToString(imageBytes);
     }
     public static String randomstring()
 	{
 		String genString=RandomStringUtils.randomAlphabetic(8);
 		return genString;
 	}
 	public static String randomNum()
 	{
 		String genString2=RandomStringUtils.randomNumeric(4);
 		return genString2;
 	}


    @AfterClass
	public void teardown()
	{
		driver.quit();
	}

}
