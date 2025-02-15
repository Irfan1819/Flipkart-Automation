package flipkart.org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartValidatePageNavigation {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase10() throws InterruptedException {
	//close login popup
	try {
	WebElement closeLoginPopup=driver.findElement(By.xpath("//button[contains(text(),'X')]"));
	closeLoginPopup.click();
	
	}catch(Exception e) {
		System.out.println("Login popup is not displayed");
	}
	
	//search for headphones
	WebElement headPhonesSearch=driver.findElement(By.name("q"));
	headPhonesSearch.sendKeys("HeadPhones",Keys.ENTER);
	
	Thread.sleep(3000);
	
	WebElement nextButton=driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView(true)", nextButton);
	
	Thread.sleep(3000);
	
	js.executeScript("window.scrollBy(0,-200);");
	
	Thread.sleep(2000);
	
	nextButton.click();
	//verify that page is successfully navigated
	System.out.println("The Current URl is : " + driver.getCurrentUrl());
	String currentUrl=driver.getCurrentUrl();
	
	Assert.assertTrue(currentUrl.contains("page=2"),"Page Navigation is Failed !!!");
	System.out.println("Successfully Navigate to Page 2");
	Thread.sleep(3000);

	
	WebElement pageIndicator=driver.findElement(By.xpath("//span[@class='cn++Ap']"));
	Assert.assertTrue(pageIndicator.getText().contains("Page 2"), "UI page indicator not updated");
	
	//verify the color of number page
	WebElement colorPage=driver.findElement(By.className("cn++Ap"));
	String color=colorPage.getCssValue("color");
	System.out.println("The page Number Color is : " + color);
	
	}
}
