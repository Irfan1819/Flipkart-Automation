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

public class FlipkartWishlist {
WebDriver driver;
@BeforeClass
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testcase07() {
	//login to the flipkart Account
	WebElement login=driver.findElement(By.xpath("//span[contains(text(),'Login')]"));
	login.click();
	
	//enter login credentials
	WebElement phnNumber=driver.findElement(By.className("r4vIwl"));
	phnNumber.sendKeys("7893079790",Keys.ENTER);
	
	//search for laptops
	WebElement searchLaptops=driver.findElement(By.name("q"));
	searchLaptops.sendKeys("Laptops",Keys.ENTER);
	
	//add to wishlist for any product
	WebElement addWishlist=driver.findElement(By.className("x1UMqG"));
	addWishlist.click();
	
	//verify that product is in wishlist
	WebElement productWishlist=driver.findElement(By.className("_3ZeUN+"));
	Assert.assertTrue(productWishlist.isDisplayed(),"Product is Successfully added");
	
	
	}
@AfterClass
public void tearDown() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
}
}

