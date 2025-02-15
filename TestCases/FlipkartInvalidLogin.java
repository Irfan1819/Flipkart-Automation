package flipkart.org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartInvalidLogin {
	WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase08() {
	//click on login menu
	WebElement loginMenu=driver.findElement(By.xpath("//span[contains(text(),'Login')]"));
	loginMenu.click();
	
	//enter invalid phone number
	WebElement invalidPhnNumber=driver.findElement(By.className("r4vIwl"));
	invalidPhnNumber.sendKeys("+913456777098", Keys.ENTER);
	
	//verify that invalid message
	WebElement invalidMessage=driver.findElement(By.className("llBOFA"));
	try {
	Assert.assertTrue(invalidMessage.isDisplayed());
	System.out.println("Invalid Message is displayed Successfully");
}catch(AssertionError ae) {
	
}

}
@AfterMethod
public void tearDown() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
}
}