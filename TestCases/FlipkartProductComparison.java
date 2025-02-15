package flipkart.org.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartProductComparison {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
	
	WebElement homePage=driver.findElement(By.xpath("//body"));
	if(homePage.isDisplayed()) {
		System.out.println("'Flipkart' homePage is Successfully Visible");
	}
}
@Test
public void testCase09() throws InterruptedException {
	//search for laptops
	WebElement searchLaptops=driver.findElement(By.name("q"));
	searchLaptops.sendKeys("Laptops",Keys.ENTER);
	
	//click on any laptop
	WebElement laptopSelection1=driver.findElement(By.className("KzDlHZ"));
	laptopSelection1.click();
	
	//Click on compare
	WebElement compare1=driver.findElement(By.className("uu79Xy"));
	compare1.click();
	
	Thread.sleep(5000);
	
	Set<String> allTabs=driver.getWindowHandles();
	ArrayList<String> tabs=new ArrayList<>(allTabs);
	
	driver.switchTo().window(tabs.get(0));
	
	Thread.sleep(3000);
	//click on brand Menu
	WebElement brandMenu=driver.findElement(By.className("fxf7w6"));
	brandMenu.click();
	
	Thread.sleep(3000);
	
	WebElement hpLaptop=driver.findElement(By.className("_6i1qKy"));
	hpLaptop.click();
	
	Thread.sleep(3000);
	
	WebElement laptopSelection2=driver.findElement(By.className("KzDlHZ"));
	laptopSelection2.click();
	
	//click on compare
	WebElement compare2=driver.findElement(By.className("uu79Xy"));
	compare2.click();
	
	for(String tabHandle:driver.getWindowHandles()) {
		driver.switchTo().window(tabHandle);
	}
	
	
	//click on compare button
	WebElement compareButton=driver.findElement(By.className("vYpSTw"));
	compareButton.click();
	
	Thread.sleep(3000);
	
	//verify that two items comparison is visible or not
	List<WebElement> comparedProducts=driver.findElements(By.xpath("//div[@class='_3YhLQA']"));
	
	Assert.assertTrue(comparedProducts.size() >=2,"Product is not Comparison not Successful.");
	
	System.out.println("Products compared successfully");
	
	for(WebElement product : comparedProducts) {
		Assert.assertTrue(product.isDisplayed(),"Product details not visible");
	}

	
}
@AfterMethod
public void tearDown() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
}
}
