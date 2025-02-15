package flipkart.org.testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartPaymentValidation {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase11() throws InterruptedException {
	try {
	//close loginPopup\
	WebElement loginPopup=driver.findElement(By.xpath("//button[contains(text(),'X')]"));
	loginPopup.click();
	
	}catch(Exception e) {
		System.out.println("Login popup is not Found !!!");
	}
	//verify homepage is visible
	WebElement verifyPage=driver.findElement(By.xpath("//body"));
	if(verifyPage.isDisplayed()) {
		System.out.println("'Flipkart' page is Displayed Successfully!!!");
	}
	
	//search for product ex:Watches
	WebElement searchwatches=driver.findElement(By.name("q"));
	searchwatches.sendKeys("Watches",Keys.ENTER);
	
	//select any product and click on add to cart
	WebElement watchSelection=driver.findElement(By.className("WKTcLC"));
	
	watchSelection.click();
	
	for(String tabHandle:driver.getWindowHandles()) {
		driver.switchTo().window(tabHandle);
	}
	WebElement addCart=driver.findElement(By.xpath("//button[contains(text(),'Add to cart')]"));
	addCart.click();
	 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	 
	//click on place order
	WebElement placeOrder=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Place Order')]")));
	placeOrder.click();
	
	//click on continue
	WebElement continueButton=driver.findElement(By.xpath("//button[contains(text(),'CONTINUE')]"));
	continueButton.click();
	
	//add invalid card details to get invalid card details
	
	//click on Credit / Debit / ATM Card
	WebElement creditCardSelection=driver.findElement(By.xpath("//span[contains(text(),'Credit / Debit / ATM Card')]"));
	creditCardSelection.click();
	
	//Enter card number
	WebElement cardNumber=driver.findElement(By.name("cardNumber"));
	cardNumber.sendKeys("123456789000");
	//select month and year
	WebElement monthSelection=driver.findElement(By.name("month"));
	Select s1=new Select(monthSelection);
	s1.selectByVisibleText("06");
	
	Thread.sleep(2000);
	
	WebElement yearSelection=driver.findElement(By.name("year"));
	Select s2=new Select(yearSelection);
	s2.selectByVisibleText("28");
	
	String color=cardNumber.getCssValue("color");
	
	if(color.equals("255, 0, 0")) {
		System.out.println("The Card Details Entered is Incorrect");
	}
	else {
		System.out.println("Card Details is Correct");
	}
	
	
}
@AfterMethod
public void tearDown() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
}
}
