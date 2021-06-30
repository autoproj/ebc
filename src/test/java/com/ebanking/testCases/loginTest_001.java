package com.ebanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ebanking.PageObjects.LoginPage;

public class loginTest_001 extends BaseClass {
	@Test
	public void LoginTest() throws IOException, InterruptedException 
	{
	
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setUsername(username);
		logger.info("username entered");
		Thread.sleep(2000);
		lp.setpassword(password);
		logger.info("password entered");
		Thread.sleep(2000);
		lp.clickbtn();
		Thread.sleep(2000);
		//System.out.println(driver.getTitle());
		
		if(driver.getTitle().equals(title))
		{
			Assert.assertTrue(true);
			logger.info("logintest passed");
			System.out.println("logintest passed");
		}
		else
		{
			
			//captureScreenshot(driver,"loginTest_001");
			
			Assert.assertTrue(false);
			logger.info("login test failed");
			System.out.println("logintest failed");
		
		}
		
	}


}
