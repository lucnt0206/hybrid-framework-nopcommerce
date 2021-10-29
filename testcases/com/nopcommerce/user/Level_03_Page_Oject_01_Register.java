 package com.nopcommerce.user;

import org.testng.annotations.Test;
import pageObject.HomePageObject;
import pageObject.RegisterPageObject;
import org.testng.annotations.BeforeClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Oject {
  private WebDriver driver;
  private HomePageObject homePage ;
  private RegisterPageObject registerPage ;
  
 
  private String projectPath = System.getProperty("user.dir");
  private String emailAddress;
  
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath+"\\browserDriver\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  homePage = new HomePageObject(driver);
	  registerPage = new RegisterPageObject(driver);
	
	  emailAddress = "autofc"+generateNumber()+"@mail.co";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.get("https://demo.nopcommerce.com/");
	  
  }

  @Test
  public void TC01_Register_Emptydata() {
	  homePage.clickToRegisterLink();
	   
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

  }
  
  @Test
  public void TC02_Register_Invalid_Email() {
	  homePage.clickToRegisterLink();
	  
	  registerPage.inputToFirstnameTextbox("Automation");
	  registerPage.inputToLastnameTextbox("FC");
	  registerPage.inputToEmailTextbox("123@456#$%");
	  registerPage.inputToPasswordTextbox("123456");
	  registerPage.inputToConfirmPasswordTextbox("123456");
	  
	  registerPage.clickToRegisterButton();
	  
	  

	  Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	  
  }
  

@Test
  public void TC03_Register_Success() {
	  homePage.clickToRegisterLink();
	  
	  registerPage.inputToFirstnameTextbox("Automation");
	  registerPage.inputToLastnameTextbox("FC");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123456");
	  registerPage.inputToConfirmPasswordTextbox("123456");
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	  
	  registerPage.clickToLogoutLink();
  }
  
  @Test
  public void TC04_Register_Existing_Email() {
	  homePage.clickToRegisterLink();
	  
	  registerPage.inputToFirstnameTextbox("Automation");
	  registerPage.inputToLastnameTextbox("FC");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123456");
	  registerPage.inputToConfirmPasswordTextbox("123456");
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
  }
  
  @Test
  public void TC05_Register_Pass_Less_Than_6_Chars() {
	  homePage.clickToRegisterLink();
	  
	  registerPage.inputToFirstnameTextbox("Automation");
	  registerPage.inputToLastnameTextbox("FC");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123");
	  registerPage.inputToConfirmPasswordTextbox("123");
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
  }
  
  @Test
  public void TC06_Register_Invalid_Confirm_Pass() {
	  homePage.clickToRegisterLink();
	  
	  registerPage.inputToFirstnameTextbox("Automation");
	  registerPage.inputToLastnameTextbox("FC");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123456");
	  registerPage.inputToConfirmPasswordTextbox("123457");
	  
	  registerPage.clickToRegisterButton();
	  
	  Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
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
