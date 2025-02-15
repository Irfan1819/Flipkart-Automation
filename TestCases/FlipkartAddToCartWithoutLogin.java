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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartAddToCartWithoutLogin {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}

@Test
public void testCase15() throws InterruptedException {
	// verify the flipkart page is visible or not
	WebElement homePage=driver.findElement(By.xpath("//body"));
	if(homePage.isDisplayed()) {
		System.out.println("'flipkart' Page is Visible Successfully");
	}
	
	//search for product eg. Washing Machine
	WebElement searchProduct=driver.findElement(By.name("q"));
	searchProduct.sendKeys("Washing Machine",Keys.ENTER);
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	//click on any product
	WebElement clickProduct=wait.until(ExpectedConditions.elementToBeClickable(By.className("KzDlHZ")));
	clickProduct.click();
	for(String tabHandle:driver.getWindowHandles()) {
		driver.switchTo().window(tabHandle);
	}
	//click on add to cart
	WebElement addToCart=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add to cart')]")));
	addToCart.click();
	Thread.sleep(3000);
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.history.back();");
	
	Thread.sleep(3000);
	
	//assert that cart count is updated
	WebElement cartCount=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'1')]")));
	if(cartCount.isDisplayed()) {
		System.out.println("The Product is added successfully " + cartCount.getText());
	}
	
	//verify that the product is added or not
		WebElement cartPage=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Cart']")));
		cartPage.click();
		
	
}
@AfterMethod
public void tearDown() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
}
}
