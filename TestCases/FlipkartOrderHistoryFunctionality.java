package flipkart.org.testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartOrderHistoryFunctionality {
WebDriver driver;
@BeforeMethod
public void setUp() {
	driver=new ChromeDriver();
	driver.get("https://www.flipkart.com/");
}
@Test
public void testCase18() throws InterruptedException {
	//click on login 
		WebElement loginMenu=driver.findElement(By.xpath("//span[text()='Login']"));
		loginMenu.click();
		
		//Enter Phone number
		WebElement phnNumber=driver.findElement(By.className("r4vIwl"));
		phnNumber.sendKeys("7893079790");
		
		//click on request otp
		WebElement requestOtpButton=driver.findElement(By.className("QqFHMw"));
		requestOtpButton.click();
		Thread.sleep(15000);
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//Navigate to Account Menu
		WebElement accountMenu=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='ME']")));
		Actions act=new Actions(driver);
		act.moveToElement(accountMenu).perform();
		Thread.sleep(3000);
		WebElement orderMenu=driver.findElement(By.className("_3pKU-e"));
		orderMenu.click();
		
		WebElement orderList=driver.findElement(By.xpath("//span[text()='WALKAROO Men Blue Sandals']"));
		orderList.click();
		
		WebElement order=driver.findElement(By.xpath("//div[contains(text,'WALKAROO Men Blue Sandals')]"));
		Assert.assertEquals(order.getText(),"WALKAROO Men Blue Sandals","Order is Invalid");
		System.out.println("order is Correct");
}
@AfterMethod
public void tearDown() {
	if(driver != null) {
		driver.quit();
	}
}
}
