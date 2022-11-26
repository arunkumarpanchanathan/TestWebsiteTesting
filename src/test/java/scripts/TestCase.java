package scripts;

import java.io.IOException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import context.TestContext;
import dataProvider.ConfigFileReader;
import dataProvider.ReadWriteExcel;
import extentReport.ExtentReport;
import objectManager.DriverManager;
import pageObjects.RelevelPageObjects;
import utils.Action;
import utils.Logging;

public class TestCase 
{
	WebDriver driver;
	RelevelPageObjects pageObjects;
	TestContext testContext;
	RelevelPageObjects relevelPageObjects;
	Action action;
	ExtentReport extentReport;
	SoftAssert softAssert;
	ReadWriteExcel excel;
	
	@BeforeSuite
	public void beforeSuite() throws IOException
	{
		driver = DriverManager.getDriver();
		driver.get(ConfigFileReader.getUrl());		
		testContext = new TestContext();
		relevelPageObjects = testContext.getPageObjectManager().getRelevelPageObjects();
		action = testContext.getActionObject();
		extentReport = testContext.getExtentReport();
		softAssert = new SoftAssert();
		excel = new ReadWriteExcel();	
		PropertyConfigurator.configure("log4j.properties");
	}
	
	@AfterSuite()
	public void afterSuite() throws IOException
	{		
		softAssert.assertAll();
		extentReport.flush();		
	}
	
	@BeforeMethod()
	public void beforemethod()
	{}
	
	@AfterMethod()
	public void afterMethod() throws InterruptedException
	{}
	
	
  @Test(description="TC001-This test verifies user see a validation message when invalid mobile number entered for login", priority=1,enabled=true)
  public void tc_1() throws Exception 
  {	
	  try
	  {
		  HSSFSheet sheet = excel.getSheet("TC001");
		  for(int i=1;i<=sheet.getLastRowNum();i++)
		  {
			  extentReport.createTest("TC001-This test verifies user see a validation message when invalid mobile number entered for login");
			  action.clickLink(relevelPageObjects.lnkLogin, "Login link");
			  extentReport.info("Login link clicked");
			  action.sendKeys(relevelPageObjects.txtMobileNo,sheet.getRow(i).getCell(0).getStringCellValue() , "Mobile Number textbox");
			  extentReport.info("Mobile number entered");
			  action.pressTab(relevelPageObjects.txtMobileNo);
			  extentReport.info("Tab pressed");
			  if(driver.findElements(By.xpath("//*[text()='Please enter valid mobile number']")).size()>0)
			  {
				  extentReport.pass("Please enter valid mobile number validation message is displayed");
				  extentReport.addScreenshot(driver);
				  Logging.info("Please enter valid mobile number validation message is displayed ");
				  Logging.endTestCase();
			  }
			  else
			  {
				  extentReport.fail("Validation message for invalid mobile number is not displayed");
				  extentReport.addScreenshot(driver);
				  Logging.info("Validation message for invalid mobile number is not displayed");
				  Logging.endTestCase();
			  }
		  driver.get(ConfigFileReader.getUrl());
		  }	 
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  extentReport.fail(e.getMessage());
		  Logging.info(e.getMessage());
		  Logging.endTestCase();
	  }
  }
  
  @Test(description="TC002-This test verify user can click login button when valid mobile number is provided",priority=2,enabled=true)
  public void tc_2() throws Exception 
  {
//	  Logging.startTestCase("TC002");
	  try
	  {
	  HSSFSheet sheet = excel.getSheet("TC002");
	  for(int i=1;i<=sheet.getLastRowNum();i++)
	  {
		  extentReport.createTest("TC002-This test verify user can click login button when valid mobile number is provided");
		  action.clickLink(relevelPageObjects.lnkLogin, "Login link");
		  extentReport.info("Login link clicked");
		  action.sendKeys(relevelPageObjects.txtMobileNo,sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
		  extentReport.info("Mobile number entered");
		  action.clickButton(relevelPageObjects.btnLogin, "Login button");
		  extentReport.info("Login button clicked");
		  Thread.sleep(3000);
			  if(driver.findElements(By.xpath("//*[contains(text(),'sent an OTP to your mobile number')]")).size()>0)
			  {
				  extentReport.pass("We've sent an OTP to your mobile number success message is displayed");
				  extentReport.addScreenshot(driver);
				  Logging.info("We've sent an OTP to your mobile number success message is displayed");
				  Logging.endTestCase();
			  }
			  else
			  {
				  extentReport.fail("Success message is not displayed");
				  extentReport.addScreenshot(driver);
				  Logging.info("Success message is not displayed");
				  Logging.endTestCase();				  
			  }	
			  driver.get(ConfigFileReader.getUrl());
	  }	 
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  extentReport.fail(e.getMessage());
		  Logging.info(e.getMessage());
		  Logging.endTestCase();
	  }
  }
  
  @Test(description="TC003-This test verify user can edit and login with new mobile number",priority=3,enabled=true)
  public void tc_3() throws Exception 
  {
//	  Logging.startTestCase("TC003");
	  try
	  {
	  HSSFSheet sheet = excel.getSheet("TC003");
	  for(int i=1;i<=sheet.getLastRowNum();i++)
	  {
	  extentReport.createTest("TC003-This test verify user can edit and login with new mobile number");
	  action.clickLink(relevelPageObjects.lnkLogin, "Login link");
	  extentReport.info("Login link clicked");
	  action.sendKeys(relevelPageObjects.txtMobileNo,sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
	  extentReport.info("Mobile number entered");
	  action.clickButton(relevelPageObjects.btnLogin, "Login button");
	  extentReport.info("Login button clicked");
	  action.clickLink(relevelPageObjects.lnkEditMobileNumber, "Edit");
	  extentReport.info("Edit mobile number link clicked");
	  action.sendKeys(relevelPageObjects.txtMobileNo,sheet.getRow(i).getCell(1).getStringCellValue(), "Mobile Number textbox");
	  extentReport.info("New mobile number entered");
	  action.clickButton(relevelPageObjects.btnLogin, "Login button");
	  extentReport.info("Login button clicked");  
	  if(driver.findElements(By.xpath("//*[contains(text(),'sent an OTP to your mobile number')]")).size()>0)
	  {
		  extentReport.pass("Sent an OTP to your mobile number message is displayed");
		  extentReport.addScreenshot(driver);
		  Logging.info("Sent an OTP to your mobile number message is displayed");
		  Logging.endTestCase();
	  }
	  else
	  {
		  extentReport.fail("OTP is not sent");
		  extentReport.addScreenshot(driver);
		  Logging.info("OTP is not sent");
		  Logging.endTestCase();
	  }	 
	  driver.get(ConfigFileReader.getUrl());
	  }
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  extentReport.fail(e.getMessage());
		  Logging.info(e.getMessage());
		  Logging.endTestCase();
	  }
  }
  
  @Test(description="TC004-This test verify validation message is shown when user enter invalid OTP for login",priority=4,enabled=true)
  public void tc_4() throws Exception 
  {
//	  Logging.startTestCase("TC004");
	  try
	  {
	  HSSFSheet sheet = excel.getSheet("TC004");
	  for(int i=1;i<=sheet.getLastRowNum();i++)
	  {
		  extentReport.createTest("TC004-This test verify validation message is shown when user enter invalid OTP for login");
		  action.clickLink(relevelPageObjects.lnkLogin, "Login link");
		  extentReport.info("Login link clicked");
		  action.sendKeys(relevelPageObjects.txtMobileNo,sheet.getRow(i).getCell(0).getStringCellValue(), "Mobile Number textbox");
		  extentReport.info("Mobile number entered");
		  action.clickButton(relevelPageObjects.btnLogin, "Login button");
		  action.sendKeys(relevelPageObjects.txtOtp,sheet.getRow(i).getCell(1).getStringCellValue(), "OTP textbox");
		  extentReport.info("OTP entered");
		  action.clickButton(relevelPageObjects.btnContinue,"Continue");
		  extentReport.info("Continue button clicked");
		  Thread.sleep(3000);
		  if(driver.findElements(By.xpath("//div[text()='This OTP is incorrect']")).size()>0)
		  {
			  extentReport.pass("This OTP is incorrect message is displayed");
			  extentReport.addScreenshot(driver);
			  Logging.info("This OTP is incorrect message is displayed");
			  Logging.endTestCase();
		  }
		  else
		  {
			  extentReport.fail("Invalid OTP message is not displayed");
			  extentReport.addScreenshot(driver);
			  Logging.info("Invalid OTP message is not displayed");
			  Logging.endTestCase();
		  }	
		  driver.get(ConfigFileReader.getUrl());
	  }
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  extentReport.fail(e.getMessage());
		  Logging.info(e.getMessage());
		  Logging.endTestCase();
	  }
  }
  
  @Test(description="TC005-This test verify user can view a tests from Test tab",priority=5,enabled=true)
  public void tc_5() throws Exception 
  {
//	  Logging.startTestCase("TC005");
	  try
	  {
	  extentReport.createTest("TC005-This test verify user can view a test from Test tab");	  
	  action.clickLink(relevelPageObjects.icnTests, "Test");	  
	  extentReport.info("Test link clicked");
	  action.clickLink(relevelPageObjects.lblFrontendtest, "Frontend Development Test");
	  extentReport.info("Frontend Development Test clicked");
	  action.clickButton(relevelPageObjects.btnBookFreeSlotTestTab, "Book your free slot");
	  if(driver.findElements(By.xpath("//*[text()='Sign up or login']")).size()>0)
	  {
		  extentReport.pass("Sign up or login screen displayed");
		  extentReport.addScreenshot(driver);
		  Logging.info("Sign up or login screen displayed");
		  Logging.endTestCase();
	  }
	  else
	  {
		  extentReport.fail("Sign up or login screen not displayed");
		  extentReport.addScreenshot(driver);
		  Logging.info("Sign up or login screen not displayed");
		  Logging.endTestCase();
	  }
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  extentReport.fail(e.getMessage());
		  Logging.info(e.getMessage());
		  Logging.endTestCase();
	  }	  
  }
  
  @Test(description="TC006-This test verify user can view a course from Courses tab",priority=6,enabled=true)
  public void tc_6() throws Exception 
  {
	  try
	  {		  
		  extentReport.createTest("TC006-This test verify user can view a tests from Courses tab");
		  action.clickLink(relevelPageObjects.lnkCourses, "Courses");
		  extentReport.info("Courses link clicked");
		  action.clickButton(relevelPageObjects.btnViewDetails, "View Details");
		  extentReport.info("Software testing course View details button clicked");
		  if(driver.findElements(By.xpath("//*[text()='Apply now']")).size()>0)
		  {
			  extentReport.pass("Apply now button is displayed");
			  extentReport.addScreenshot(driver);
			  Logging.info("Apply now button is displayed");
			  Logging.endTestCase();
		  }
		  else
		  {
			  extentReport.fail("Apply now button is not displayed");
			  extentReport.addScreenshot(driver);
			  Logging.info("Apply now button is not displayed");
			  Logging.endTestCase();
		  }
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  extentReport.fail(e.getMessage());
		  Logging.info(e.getMessage());
		  Logging.endTestCase();
	  }	  
  }   
}
