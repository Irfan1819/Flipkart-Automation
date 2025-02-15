package flipkart.org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartProductSearch {
WebDriver driver;
@BeforeClass
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
	
}
@Test
public void testCase02(){
	
	//verify that homepage is visible
	WebElement homePage=driver.findElement(By.tagName("body"));
	
	if (homePage.isDisplayed()) {
		System.out.println(" 'Flipkart' page is successfully displayed");
	}
	else {
		System.out.println(" 'Flipkart' page is not displayed");
	}
	
	//search for Dell Laptops
	WebElement searchProduct=driver.findElement(By.name("q"));
	searchProduct.sendKeys("Dell Laptops",Keys.ENTER);
	
	//verify that our product search is visible or not
	
	WebElement verifyProduct=driver.findElement(By.xpath("//span[contains(text(),'Dell Laptops')]"));
	if(verifyProduct.isDisplayed()) {
		System.out.println("'Dell Laptop' Product is visible Successfully !!!");
	}
	else {
		System.out.println(" 'Dell Laptop' Product is not visible!!! 'Error found !!!'");
	}
}
@AfterClass
public void tearDown() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
}
}
