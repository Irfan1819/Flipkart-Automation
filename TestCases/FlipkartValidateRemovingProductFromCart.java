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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartValidateRemovingProductFromCart {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	//Maximize the browser
	driver.manage().window().maximize();
	
	//Navigate to URL
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase24() throws InterruptedException {
	try {
		//Close Login Popup
		WebElement closeLoginPopup=driver.findElement(By.xpath("//button[contains(text(),'X')]"));
		closeLoginPopup.click();
	}catch (Exception e) {
		System.out.println("Login popup is not Found");
	}
	//search for a product example 'Java Book'
	WebElement searchBook=driver.findElement(By.name("q"));
	searchBook.sendKeys("Java Book",Keys.ENTER);
	
	//click on any book
	WebElement selectBook=driver.findElement(By.className("wjcEIp"));
	selectBook.click();
	
	for(String tabHandles:driver.getWindowHandles()) {
		driver.switchTo().window(tabHandles);
	}
	
	//click on add to cart
	WebElement addToCart=driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
	addToCart.click();
	
	Thread.sleep(3000);
	
	//Navigate to Cart Page
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.history.back();");
	
	Thread.sleep(3000);
	
	WebElement cartPage=driver.findElement(By.xpath("//span[text()='Cart']"));
	cartPage.click();
	
	js.executeScript("window.scrollBy(0,200);");
	
	Thread.sleep(3000);
	
	//Click on 'Remove'
	WebElement removeMenu=driver.findElement(By.xpath("//div[contains(text(),'Remove')]"));
	removeMenu.click();
	
	Thread.sleep(2000);
		
	//Click on again 'Remove Alert'
	WebElement removeAlert=driver.findElement(By.className("sBxzFz"));
	removeAlert.click();
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	//verify that cart is empty
	WebElement cartEmpty=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Missing Cart items?')]")));
	if(cartEmpty.isDisplayed()) {
		System.out.println("Cart is Successfully emptied");
	}
	//assertion 
	//ensure the product is removed successfully
	WebElement removedSuccessfullMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("eIDgeN")));
	Assert.assertTrue(removedSuccessfullMessage.isDisplayed(),"Cart message is not displayed");
	System.out.println("Removed Product is successfully visibled");
	
	js.executeScript("window.history.back();");
	
	Thread.sleep(3000);
	
	//Verify that cart count is Updated 
	WebElement cartCount=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cziJ98")));
	Assert.assertTrue(cartCount.isDisplayed(),"Cart Count is not updated");
	System.out.println("Cart count Updated Successfully" + cartCount.getText());
	
	
}
}
