package com.ebanking.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver ldriver;

	public LoginPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(name="uid")
	WebElement username;

	@FindBy(name="password")
	WebElement password;

	@FindBy(name="btnLogin")
	WebElement btnLogin;
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[10]/a")
	WebElement logout;
		
	public void setUsername(String uname)
	{
		username.sendKeys(uname);
	}

	public void setpassword(String pswd)
	{
		password.sendKeys(pswd);
	}

	public void clickbtn()
	{
		btnLogin.click();
	}

	public void clicklgout()
	{
		logout.click();
	}

}
