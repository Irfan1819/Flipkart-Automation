package flipkart.org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartValidateCoupon {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase17() throws InterruptedException {
	//search for products ex puma Shoes
	WebElement productSearch=driver.findElement(By.name("q"));
	productSearch.sendKeys("Puma Shoes",Keys.ENTER);
	
	//click on any product
	WebElement clickProduct=driver.findElement(By.linkText("SkateCat Pro Sneakers For Men"));
	clickProduct.click();
	
	for(String tabhandle:driver.getWindowHandles()) {
		driver.switchTo().window(tabhandle);
	}
	
	//enter pincode
	WebElement pincodeInput = driver.findElement(By.cssSelector("input[placeholder='Enter delivery pincode']"));
	pincodeInput.sendKeys("515301",Keys.ENTER);
	
	Thread.sleep(3000);
	
	//click on AddCart
	WebElement addToCart=driver.findElement(By.className("QqFHMw"));
	addToCart.click();
	
	Thread.sleep(2000);
	
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.history.back();");
	
	//click on cart
	WebElement cartMenu=driver.findElement(By.xpath("//span[text()='Cart']"));
	cartMenu.click();
	
	WebElement couponAvailability=driver.findElement(By.className("k9WPjB"));
	if(couponAvailability.isDisplayed()) {
		System.out.println("Discount Applied Successfully");
	}
	Assert.assertTrue(couponAvailability.isDisplayed(),"Coupon is not applied");
	System.out.println("Coupon applied Successful");
	


}
@AfterMethod
public void tearDown() throws InterruptedException {
	Thread.sleep(3000);
	if(driver != null) {
		driver.quit();
	}

}
}