package com.ebanking.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {
	WebDriver ldriver;
	
	
public AddCustomerPage(WebDriver rdriver)
{
	ldriver=rdriver;
 PageFactory.initElements(rdriver, this);
}

@FindBy(how=How.XPATH,using="/html/body/div[3]/div/ul/li[2]/a")
@CacheLookup
WebElement lnkAddNewCustomer;

@FindBy(how=How.NAME,using="name")
@CacheLookup
WebElement txtCustomerName;

@FindBy(how=How.NAME,using="rad1")
@CacheLookup
WebElement rdGender;

@FindBy(how=How.ID_OR_NAME,using="dob")
@CacheLookup
WebElement txtdob;

@FindBy(how=How.NAME,using="addr")
@CacheLookup
WebElement txtaddress;


@FindBy(how=How.NAME,using="city")
@CacheLookup
WebElement txtcity;

@FindBy(how=How.NAME,using="state")
@CacheLookup
WebElement txtstate;

@FindBy(how=How.NAME,using="pinno")
@CacheLookup
WebElement txtpinno;

@FindBy(how=How.NAME,using="telephoneno")
@CacheLookup
WebElement txttelephoneno;

@FindBy(how=How.NAME,using="emailid")
@CacheLookup
WebElement txtemailid;

@FindBy(how=How.NAME,using="password")
@CacheLookup
WebElement txtpassword;

@FindBy(how=How.NAME,using="sub")
@CacheLookup
WebElement btnsubmit;

public void lnkAddNewCustomer()
{
	lnkAddNewCustomer.click();
}

public void CustomerName(String cname)
{
	txtCustomerName.sendKeys(cname);
}


public void CustomerGender(String cgender)
{
	rdGender.click();
}


public void custDob(String mm,String yy,String dd)
{
	txtdob.sendKeys(mm);
	txtdob.sendKeys(dd);
	txtdob.sendKeys(yy);
}

public void custAddress(String caddress)
{
	txtaddress.sendKeys(caddress);
}

public void custcity(String ccity)
{
	txtcity.sendKeys(ccity);
}

public void custstate(String cstate)
{
	txtstate.sendKeys(cstate);
}

public void custpinno(String cpinno)
{
	txtpinno.sendKeys(String.valueOf(cpinno));
}

public void custtelephoneno(String ctelephoneno)
{
	txttelephoneno.sendKeys(ctelephoneno);
}

//test
public void custemailid(String cemailid)
{
	txtemailid.sendKeys(cemailid);
}

public void custpassword(String cpassword)
{
	txtpassword.sendKeys(cpassword);
}

public void custsubmit()
{
	btnsubmit.click();
}



















}
