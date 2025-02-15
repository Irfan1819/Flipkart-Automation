package flipkart.org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartLogout {
WebDriver driver;
@BeforeClass
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
	
	WebElement homePage=driver.findElement(By.tagName("//body"));
	if(homePage.isDisplayed()) {
		System.out.println(" 'Flipkart' homePage is Successfully Visible !!!!");
	}
}

@Test (priority = 1)
public void login() {
	// Click on login menu
    WebElement loginMenu = driver.findElement(By.xpath("//span[contains(text(),'Login')]"));
    loginMenu.click();
    
    // Enter phone number
    WebElement phnNumber = driver.findElement(By.className("r4vIwl"));
    phnNumber.sendKeys("1234567890", Keys.ENTER);
    
    // Fetch OTP using Twilio
    String otp = OtpSmsHandler.fetchOtpFromSms();
    Assert.assertNotNull(otp, "OTP not received!");
    
    // Enter the OTP
    WebElement otpEnter = driver.findElement(By.id("OtpInput"));
    otpEnter.sendKeys(otp);
    
    System.out.println("OTP entered successfully.");

}
@Test (priority =2)
public void testCase04() {
	//click on 'Account' menu for logout
	WebElement accountMenu=driver.findElement(By.xpath("//span[contains(text(),'Account')]"));
	accountMenu.click();
	
	//click on logout
	WebElement logout=driver.findElement(By.xpath("//a[@title='Logout']"));
	logout.click();
	

	System.out.println("Logout is Successful !!!");

}
@AfterClass
public void tearDown() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
	
}
}
