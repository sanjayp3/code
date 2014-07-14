package com;

import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class NativeIOSAppTest {
	
	private WebDriver driver;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet ;
	Map<String, Object[]> data;
	int key=0;
	String testcaseID = "TC_";
	Properties prop = new Properties();
	 String login;
	 String login1;
	
	@BeforeClass
	public void createExcel()
	{
		//This data needs to be written (Object[])
	     data = new TreeMap<String, Object[]>();
	    data.put(""+0, new Object[] {" ID ", " MODULE NAME " , "  TESTCASE NAME  ", " RESULTS "});
		prop= new MobileUtility().loadProperties();
		
    }
	
	@BeforeMethod
	  public void beforeMethod() throws MalformedURLException {
		  driver = MobileUtility.createWebDriver("iOS");
		  
			
	  }
	
  @Test
  public void viewCatalogItems() {
	  driver.findElement(By.xpath(prop.getProperty("login"))).click();
	  data.put(""+ (++key), new Object[] {""+testcaseID+key,  " GENDER SELECTION " , "isShopAvailable" , " Fail "});	
  }
  
  
  
  

  @Test
  public void viewCatalogItems1() {
	  driver.findElement(By.xpath(prop.getProperty("login1"))).click();
	  data.put(""+(++key), new Object[] {""+testcaseID+key, " GENDER SELECTION " , "performLogin" , " Pass "});
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }
  
  @AfterClass
  public void generateExcelReport()
  {
	ExcelGenerator.generateExcelReport(data);
  }

}
