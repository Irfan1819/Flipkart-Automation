package flipkart.org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartImageZoomFunctionality {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase12() throws InterruptedException {
	//search for any product
	WebElement searchProduct=driver.findElement(By.name("q"));
	searchProduct.sendKeys("Mobiles",Keys.ENTER);
	
	//click on any product
	WebElement clickProduct=driver.findElement(By.className("_4WELSP"));
	clickProduct.click();
	
	for(String tabHandle:driver.getWindowHandles())
	{
		driver.switchTo().window(tabHandle);
}
	WebElement zoomedImage=driver.findElement(By.cssSelector("._0DkuPH"));
	Actions act=new Actions(driver);
	act.moveToElement(zoomedImage).perform();
	Thread.sleep(2000);
	Assert.assertTrue(zoomedImage.isDisplayed(),"Image zoom feature did not work");
	System.out.println("Image zoom feature validated successfully");
}
@AfterMethod
public void tearDown() throws InterruptedException {
	Thread.sleep(3000);
	driver.quit();
}
}