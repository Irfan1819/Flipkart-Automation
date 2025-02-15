package flipkart.org.testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartUserProfileUpdate {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase16() throws InterruptedException {
	
	//click on login 
	WebElement loginMenu=driver.findElement(By.xpath("//span[text()='Login']"));
	loginMenu.click();
	
	//Enter Phone number
	WebElement phnNumber=driver.findElement(By.className("r4vIwl"));
	phnNumber.sendKeys("1234567890");
	
	//click on request otp
	WebElement requestOtpButton=driver.findElement(By.className("QqFHMw"));
	requestOtpButton.click();
	Thread.sleep(15000);

	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	//Navigate to Account Menu
	WebElement accountMenu=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='M E']")));
	Actions act=new Actions(driver);
	act.moveToElement(accountMenu).perform();
	
	//click on My Profile
	WebElement myProfile=driver.findElement(By.className("AT0fUR"));
	myProfile.click();
	
	//click on edit in personal information
	WebElement editMenu=driver.findElement(By.xpath("//span[text()='Edit']"));
	editMenu.click();

	//update profile fields like firstname and lastName
	WebElement firstName=driver.findElement(By.name("firstName"));
	firstName.clear();
	Thread.sleep(2000);
	firstName.sendKeys("Alice");
	
	
	
	WebElement lastName=driver.findElement(By.name("lastName"));
	lastName.clear();
	Thread.sleep(2000);
	lastName.sendKeys("John Doe");
	
	//click on save 
	WebElement saveButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'SAVE')]")));
	saveButton.click();
	
	System.out.println("changes saved SuccessFully");
	
}
@AfterMethod
public void tearDown() {
	if(driver != null) {
		driver.quit();
	}
}
}
