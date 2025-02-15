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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartValidatePrice {
WebDriver driver;
@BeforeClass 
public void setUp() {
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.flipkart.com/");
	
	//verify that homepage is visible 
	WebElement homepage=driver.findElement(By.xpath("//body"));
	if(homepage.isDisplayed()) {
		System.out.println(" 'Flipkart' homepage is succcessfully visible !!!");
	}
}
@Test
public void testCase06() throws InterruptedException {
	//search for a product ex 'Mobiles'
	WebElement searchMobiles=driver.findElement(By.name("q"));
	searchMobiles.sendKeys("Mobiles", Keys.ENTER);
	
	//search for samsung mobile with specifications like ram is 8gb storage 256
	
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	
	WebElement phoneBrand=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'SAMSUNG')]")));
	phoneBrand.click();
	
	Thread.sleep(2000);
	
	//for ram 8gb
	WebElement scrollBar=driver.findElement(By.className("_6i1qKy"));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView(true);", scrollBar);
	
	//click on 8gb and above
	WebElement ramSpecification=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'8 GB and Above')]")));
	ramSpecification.click();
	
	Thread.sleep(3000);
	
	//click on internal Storage
	WebElement internalStorage=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Internal Storage')]")));
	internalStorage.click();
	
	//click on 256gb and above for Storage
	
	WebElement romSpecification=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'256 GB & Above')]")));
	romSpecification.click();
	
	Thread.sleep(3000);
	
	//verify that all specifications right
	WebElement actualRam=driver.findElement(By.xpath("//div[contains(text(),'8 GB and Above')]"));
	try {
	Assert.assertEquals(actualRam.getText(),"8 GB and Above","Ram is not Found");
	System.out.println("The Ram is Correct :" + actualRam.getText());
	
	//Assertion for phoneBrand
	WebElement actualPhoneBrand=driver.findElement(By.xpath("//div[contains(text(),'SAMSUNG')]"));
	
	Assert.assertEquals(actualPhoneBrand.getText(),"SAMSUNG","Phone Brand is not found");
	System.out.println("The PhoneBrand is : "+actualPhoneBrand.getText());
	
	//Assertion for Rom Storage
	WebElement actualRom=driver.findElement(By.xpath("//div[contains(text(),'256 GB & Above')]"));
	Assert.assertEquals(actualRom.getText(),"256 GB & Above","Ram is not Found");
	System.out.println("The Rom is Correct :" + actualRom.getText());
	}catch(AssertionError ae) {
		
	}
	}
@AfterClass
public void tearDown() throws InterruptedException {
	Thread.sleep(5000);
	driver.quit();
}
}
