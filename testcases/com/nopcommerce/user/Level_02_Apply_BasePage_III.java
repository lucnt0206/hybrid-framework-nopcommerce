 package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Apply_BasePage_III {
  WebDriver driver;
  
  BasePage basePage;
  String projectPath = System.getProperty("user.dir");
  String emailAddress;
  
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDriver\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  basePage = new BasePage();
	  emailAddress = "autofc"+generateNumber()+"@mail.co";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("https://demo.nopcommerce.com/");
	  
  }

  @Test
  public void TC01_Register_Emptydata() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	 
  }
  
  @Test
  public void TC02_Register_Invalid_Email() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", "Auto@123#*");
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");

	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	  
  }
  
  @Test
  public void TC03_Register_Success() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
	  
	  basePage.waitForElementClickable(driver, "//a[@class='ico-logout']");
	  basePage.clickToElement(driver, "//a[@class='ico-logout']");
  }
  
  @Test
  public void TC04_Register_Existing_Email() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
  }
  
  @Test
  public void TC05_Register_Pass_Less_Than_6_Chars() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "12345");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
  }
  
  @Test
  public void TC06_Register_Invalid_Confirm_Pass() {
	  basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
	  basePage.clickToElement(driver, "//a[@class='ico-register']");
	  
	  basePage.sendkeyToElement(driver, "//input[@id='FirstName']", "Automaiton");
	  basePage.sendkeyToElement(driver, "//input[@id='LastName']", "FC");
	  basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
	  basePage.sendkeyToElement(driver, "//input[@id='Password']", "123456");
	  basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123457");
	  
	  basePage.waitForElementClickable(driver, "//button[@id='register-button']");
	  basePage.clickToElement(driver, "//button[@id='register-button']");
	  
	  Assert.assertEquals(basePage.getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
