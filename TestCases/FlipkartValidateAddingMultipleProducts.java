package flipkart.org.testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartValidateAddingMultipleProducts {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test 
public void testCase20() throws InterruptedException {
	//search for first product eg Mobile
	WebElement firstProduct=driver.findElement(By.name("q"));
	firstProduct.sendKeys("Mobile",Keys.ENTER);
	
	//select a product and click 'Add To Cart'
	WebElement selectProduct1=driver.findElement(By.className("KzDlHZ"));
	selectProduct1.click();

	for(String tabHandle:driver.getWindowHandles()) {
		driver.switchTo().window(tabHandle);	
	}
	
	//click on 'Add To Cart'
	WebElement addToCart1=driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
	addToCart1.click();
	
	Thread.sleep(3000);
	
	JavascriptExecutor js=(JavascriptExecutor)driver;
	
	js.executeScript("window.history.back();");
	
	Thread.sleep(2000);
	
	//search for first product eg Mobile
		WebElement secondProduct=driver.findElement(By.name("q"));
		secondProduct.sendKeys("Boat headphones",Keys.ENTER);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	
	//select a product and click 'Add To Cart'
		WebElement selectProduct2=wait.until(ExpectedConditions.elementToBeClickable(By.className("slAVV4")));
		selectProduct2.click();

		for(String tabHandle:driver.getWindowHandles()) {
			driver.switchTo().window(tabHandle);	
		}
		
		//click on 'Add To Cart'
		WebElement addToCart2=driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
		addToCart2.click();
		
		Thread.sleep(3000);
		js.executeScript("window.history.back();");
		Thread.sleep(3000);
		
		//navigate to cart
		WebElement navigateCart=driver.findElement(By.className("ZuSA--"));
		navigateCart.click();
		
		//First Cart Product verification
		WebElement cartFirstProduct=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("T2CNXf")));
		
		Thread.sleep(3000);
		
		WebElement cartSecondProduct=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("T2CNXf")));
		
		//assertions 
		Assert.assertTrue(cartFirstProduct.isDisplayed(),"First product is not in cart.");
		
		Assert.assertTrue(cartSecondProduct.isDisplayed(),"Second Product is not in cart.");
		
		System.out.println("Mutliple Products added Successfully");
			
		
}
@AfterMethod
public void tearDown() throws InterruptedException {
	if(driver != null) {
		Thread.sleep(4000);
		//driver.quit();
	}
}
}
