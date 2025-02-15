package flipkart.org.testing;

import java.time.Duration;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
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

public class FlipkartValidateGuestCheckout {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase22() throws InterruptedException {
	try {
	//close login popup
	WebElement closeLoginPopup=driver.findElement(By.xpath("//button[contains(text(),'X')]"));
	closeLoginPopup.click();
	} catch(Exception e) {
		System.out.println("Login popup is not displayed");
	}
	//Search for a product eg 'phones'
	WebElement searchProduct=driver.findElement(By.name("q"));
	searchProduct.sendKeys("Phones",Keys.ENTER);
	
	//select any product
	WebElement selectProduct=driver.findElement(By.className("KzDlHZ"));
	selectProduct.click();
	
	for(String tabHandles : driver.getWindowHandles() ) {
		driver.switchTo().window(tabHandles);
	}
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	WebElement addToCart=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add to cart')]")));
	addToCart.click();
	
	Thread.sleep(3000);
	

	WebElement placeOrder=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Place Order']")));
	placeOrder.click();
	
	//verify guest checkout or login prompt
	WebElement guestCheckoutPrompt=driver.findElement(By.className("PXFoIh"));
	Assert.assertTrue(guestCheckoutPrompt.isDisplayed(),"Guest checkout is not displayed");
	System.out.println("Guest checkout validation successful");

	
}
@AfterMethod
public void tearDown() throws InterruptedException {
	if(driver != null) {
		Thread.sleep(5000);
		driver.quit();
	}
}
}
