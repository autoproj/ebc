package com.ebanking.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ebanking.testCases.BaseClass;

public class Reporting  extends BaseClass implements ITestListener
{//TestListenerAdapter 
	//Listener class used to generate reports
		public ExtentReports extent;//specify location of the report
		public ExtentSparkReporter reporter;//what details should be populated in the report
		public ExtentTest test;
		
		
		@Override
		public void onStart(ITestContext context) {
			String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
			String repName="Test-Report"+timestamp+".html";		
				
			
			String reportpath=System.getProperty("user.dir")+"/test-output/"+repName;
			
			ExtentSparkReporter reporter=new ExtentSparkReporter(reportpath);
			
	/*		try {
				reporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-reports.xml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			

			
			extent=new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("host name", "local host");
			extent.setSystemInfo("environment", "QA");
			extent.setSystemInfo("operating system", "windows 10");
			extent.setSystemInfo("Tested By", "swap");

			reporter.config().setDocumentTitle("eBanking Test Project");//title of report
			reporter.config().setReportName("Functional Test Report");//name of the report
			//reporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of the chart
			reporter.config().setTheme(Theme.DARK);
		}
		
	
		@Override
		public void onTestSuccess(ITestResult result) {
			test=extent.createTest(result.getName());//create new entry in report
			test.log(Status.PASS, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.GREEN));
			test.log(Status.PASS, "Test case:"+result.getMethod().getMethodName()+"is passed");
			test.log(Status.PASS, result.getThrowable());
		}
		
		
		@Override
		public void onTestFailure(ITestResult result) {
			test=extent.createTest(result.getName());//create new entry in report
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getMethod().getMethodName(), ExtentColor.RED));
			test.log(Status.FAIL, "Test case:"+result.getMethod().getMethodName()+"is failed");
			
			File source=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
			String scname=result.getMethod().getMethodName()+timestamp+".png";		
			String scrshotpath=System.getProperty("user.dir")+"/scrshots/"+scname;
			//String scrshotpath="./scrshots/"+scname;
			
			File destination=new File(scrshotpath);
			try {
				FileUtils.copyFile(source,destination);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(source.exists())
			{
				//test.addScreenCaptureFromPath(scrshotpath, "Test case failure screenshot");
				//test.fail("Screenshot is below"+test.addScreenCaptureFromPath(scrshotpath));
				test.fail("failed test case screenshot",MediaEntityBuilder.createScreenCaptureFromPath(scrshotpath).build());
			System.out.println("Screenshot taken");
			}
			
			/*if(source.exists())
				try {
					
						test.fail("Screenshot is below"+test.addScreenCaptureFromPath(scrshotpath));
					}
				 catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			/*try {
				
			
				test.fail("failed test case screenshot",MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotBase64()).build());
				System.out.println("screenshot taken");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/**/
		}
		
	

		@Override
		public void onTestSkipped(ITestResult result) {
			
			test=extent.createTest(result.getMethod().getMethodName());//create new entry in report
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.ORANGE));
			test.log(Status.SKIP, "Test case:"+result.getMethod().getMethodName()+"is skipped");
			test.log(Status.SKIP, result.getThrowable());
		}
		
		
		
		@Override
		public void onFinish(ITestContext context) {
			extent.flush();
		
		
	/*	
		public void onStart(ITestContext testContext)
		{
			
			String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
			String repName="Test-Report"+timestamp+".html";		
				
			
			String reportpath=System.getProperty("user.dir")+"/test-output/"+repName;
			
			ExtentSparkReporter reporter=new ExtentSparkReporter(reportpath);
			
		/*	try {
				reporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-report.xml");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			

			
	/*		extent=new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("host name", "local host");
			extent.setSystemInfo("environment", "QA");
			extent.setSystemInfo("operating system", "windows 10");
			extent.setSystemInfo("Tested By", "swap");

			reporter.config().setDocumentTitle("eBanking Test Project");//title of report
			reporter.config().setReportName("Functional Test Report");//name of the report
			//reporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of the chart
			reporter.config().setTheme(Theme.DARK);
		}
		
		
		public void onTestSuccess(ITestResult tr)
		{
			test=extent.createTest(tr.getName());//create new entry in report
			test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		}
		
		public void onTestFailure(ITestResult tr)
		{
			test=extent.createTest(tr.getName());//create new entry in report
			test.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
			
			String screenshotPath=System.getProperty("usr.dir")+"\\Screenshots\\"+tr.getName()+".png";
			File f=new File(screenshotPath);
			if(f.exists())
				try {
					
						test.fail("Screenshot is below"+test.addScreenCaptureFromPath(screenshotPath));
					}
				 catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		
		public void onTestSkipped(ITestResult tr)
		{
			test=extent.createTest(tr.getName());//create new entry in report
			test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
		}
		
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
		}*/
		
		}	  
}
