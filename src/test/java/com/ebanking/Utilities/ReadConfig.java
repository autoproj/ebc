package com.ebanking.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
public static Properties prop;
	
	public ReadConfig() 
	{
	
	FileInputStream file;
	File src=new File("./configuration/config.properties");
	try {
		file = new FileInputStream(src);//create object of FileInputStream class and save file location in file variable
		prop=new Properties();//create object of properties class and save in prop instance variable
		
		prop.load(file);//access method of properties class using prop object to load file of given location
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Exception is :"+e.getMessage());
	}
	}
	public String getAppUrl()
	{
		String url=prop.getProperty("url");
		return url;
	}
	
	public String getbrowser()
	{
		String browser=prop.getProperty("browser");
		return browser;
	}
	/*public String getchromepath()
	{
		String chromepath=prop.getProperty("chromepath");
		return chromepath;
	}
	public String getfirefoxpath()
	{
		String firefoxpath=prop.getProperty("firefoxpath");
		return firefoxpath;
	}
	public String getedgepath()
	{
		String edgepath=prop.getProperty("edgepath");
		return edgepath;
	}*/
	
	public String getusername()
	{
		String username=prop.getProperty("username");
		return username;
	}
	public String getpassword()
	{
		String password=prop.getProperty("password");
		return password;
	}
	public String gettitle()
	{
		String title=prop.getProperty("title");
		return title;
	}

}
