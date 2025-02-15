package flipkart.org.testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartValidateFilter {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase23() throws InterruptedException {
	//search for a product 'Shoes'
	WebElement searchProduct=driver.findElement(By.name("q"));
	searchProduct.sendKeys("Shoes",Keys.ENTER);
	
	Thread.sleep(2000);
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	WebElement brand=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fxf7w6")));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView(true);", brand);
	
	//Apply filters like "Brand : Nike" and "Color : 'Black' "
	WebElement shoeBrand=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'NIKE')]")));
	shoeBrand.click();
	
	Thread.sleep(3000);
	
	WebElement color=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Color')]")));
	js.executeScript("arguments[0].scrollIntoView(true);", color);
	
	WebElement shoeColor=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Black')]")));
	shoeColor.click();
	
	//Verify that the Displayed filters 
	
	if(shoeBrand.isDisplayed()) {
		System.out.println("Displayed results match the applied result.");
	}
	
	if(shoeColor.isDisplayed()) {
		System.out.println("Displayed results match the applied result.");
	}
	//Assertions
	//Ensure only filtered Products are displayed
	WebElement assertShoeBrand=driver.findElement(By.xpath("//div[contains(text(),'NIKE')]"));
	Assert.assertTrue(assertShoeBrand.isDisplayed(),"Filter is not Applied");
	System.out.println("Filters are verified successfully: " + assertShoeBrand.getText());
	
	WebElement assertShoeColor=driver.findElement(By.xpath("//div[contains(text(),'Black')]"));
	Assert.assertTrue(assertShoeColor.isDisplayed(),"Filter is not Applied");
	System.out.println("Filters are verified successfully: " + assertShoeColor.getText());
}
@AfterMethod
public void tearDown() throws InterruptedException {
	if(driver != null) {
		Thread.sleep(5000);
		driver.quit();
	}
}
}
