 package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Apply_BasePage_I extends BasePage{
  WebDriver driver;
  
  
  String projectPath = System.getProperty("user.dir");
  String emailAddress;
  
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDriver\\geckodriver.exe");
	  driver = new FirefoxDriver();
	 
	  emailAddress = "autofc"+generateNumber()+"@mail.co";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("https://demo.nopcommerce.com/");
	  
  }

  @Test
  public void TC01_Register_Emptydata() {
	  waitForElementClickable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  waitForElementClickable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
	  Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
	  Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
	  Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	 
  }
  
  @Test
  public void TC02_Register_Invalid_Email() {
	  waitForElementClickable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  sendkeyToElement(driver, "//input[@id='Email']", "Auto@123#*");
	  sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  waitForElementClickable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");

	  Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	  
  }
  
  @Test
  public void TC03_Register_Success() {
	  waitForElementClickable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  waitForElementClickable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
	  
	  waitForElementClickable(driver, "//a[@class='ico-logout']");
	  clickToElement(driver, "//a[@class='ico-logout']");
  }
  
  @Test
  public void TC04_Register_Existing_Email() {
	  waitForElementClickable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  waitForElementClickable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
  }
  
  @Test
  public void TC05_Register_Pass_Less_Than_6_Chars() {
	  waitForElementClickable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  sendkeyToElement(driver, "//input[@id='Password']", "12345");
	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");
	  
	  waitForElementClickable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
  }
  
  @Test
  public void TC06_Register_Invalid_Confirm_Pass() {
	  waitForElementClickable(driver, "//a[@class='ico-register']");
	  clickToElement(driver, "//a[@class='ico-register']");
	  
	  sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123457");
	  
	  waitForElementClickable(driver, "//button[@id='register-button']");
	  clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
  }
  
  @AfterClass
  public void afterClass() {
//	  driver.quit();
  }

  public int generateNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
  }
}
