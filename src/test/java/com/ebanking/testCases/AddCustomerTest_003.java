package com.ebanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ebanking.PageObjects.AddCustomerPage;
import com.ebanking.PageObjects.LoginPage;

public class AddCustomerTest_003 extends BaseClass{
    @Test
	public void AddNewCustomer() throws InterruptedException, IOException
	{
    
    	LoginPage lp=new LoginPage(driver);
    	lp.setUsername(username);
    	logger.info("username provided");
    	lp.setpassword(password);
    	logger.info("username provided");
    	lp.clickbtn();
    	logger.info("username provided");
    	Thread.sleep(3000);
    	
    	AddCustomerPage ac=new AddCustomerPage(driver);
    	ac.lnkAddNewCustomer();
    	ac.CustomerName("swap");
    	logger.info("customername provided");
    	ac.CustomerGender("female");
    	logger.info("genername provided");
    	ac.custDob("10","03","1993");
    	logger.info("dob provided");
    	
    	Thread.sleep(3000);
    	
    	ac.custAddress("india");
    	logger.info("address provided");
    	ac.custcity("pune");
    	logger.info("city provided");
    	ac.custstate("MH");
    	logger.info("state provided");
    	ac.custpinno("465467");
    	logger.info("pinno provided");
    	ac.custtelephoneno("9201938998");
    	logger.info("telephoneno provided");
    	String email=randomstring()+"@gmail.com";
    	ac.custemailid(email);
    	logger.info("emailid provided");
    	//ac.custpassword("aghc");
    	//logger.info("password provided");
    	ac.custsubmit();
    	logger.info("clicked on submit button");
    	
    	Thread.sleep(3000);
    	logger.info("validation started");
    	
    	boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
    	if(res==true)
    		
    	{
    		Assert.assertTrue(true);
    		logger.info("testcase passed");
    	}
    	else
    	{
    		captureScreenshot(driver,"AddNewCustomer");
    		Assert.assertTrue(false);
    		logger.info("testcase failed");
    	}
	Thread.sleep(3000);
}
}
	
