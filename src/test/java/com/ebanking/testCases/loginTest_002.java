package com.ebanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ebanking.PageObjects.LoginPage;
import com.ebanking.Utilities.XLutils;

public class loginTest_002 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException {
		
		LoginPage lp=new LoginPage(driver);
		Thread.sleep(3000);
		lp.setUsername(user);
		Thread.sleep(3000);
		logger.info("username provided");
		lp.setpassword(pwd);
		Thread.sleep(3000);
		logger.info("password provided");
		lp.clickbtn();
		
		Thread.sleep(3000);
		
		if (isAlertpresent()==true)
		{
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("login passed	");
			lp.clicklgout();
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertpresent()//user defined method to check if alert is present
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	}
		
	@DataProvider(name="LoginData")
	String[][]getData() throws IOException
	{
	String path=System.getProperty("user.dir")+"/src/test/java/com/ebanking/TestData/logindata.xlsx";
	
	int rownum=XLutils.getRowCount(path, "sheet1");
	
	int colcount=XLutils.getCellCount(path, "sheet1",1);
	
	String logindata[][]=new String[rownum][colcount];
	
	for(int i=1;i<=rownum;i++)
	{
		for(int j=0;j<colcount;j++)
		{
		logindata[i-1][j]=XLutils.getCellData(path, "sheet1", i, j);// 1 0
		}
				
	}
	return logindata;
	}
		
	}


