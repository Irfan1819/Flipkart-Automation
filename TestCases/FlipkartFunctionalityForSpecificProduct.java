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

public class FlipkartFunctionalityForSpecificProduct {
WebDriver driver;
@BeforeMethod
public void setUP() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase19() {
	//search for specific product eg 'Iphone 14'
	WebElement specificProduct=driver.findElement(By.name("q"));
	specificProduct.sendKeys("Iphone 14",Keys.ENTER);
	
	//verify the search product is correct
	WebElement searchProductVerify=driver.findElement(By.xpath("//span[text()='Iphone 14']"));
	if(searchProductVerify.isDisplayed()) {
		System.out.println("The Product is correct !!!");
	}
	//using assertions
	Assert.assertTrue(searchProductVerify.isDisplayed(),"Iphone 14");
	System.out.println("Product is correct and verified !!!");
}

@AfterMethod
public void tearDown() {
	if(driver != null) {
		driver.quit();
	}
}
}
