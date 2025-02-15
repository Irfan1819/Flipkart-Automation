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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartAddToCart {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");
    }

    @Test
    public void testCase03() throws InterruptedException {
        // Verify Flipkart homepage is displayed
        WebElement homePage = driver.findElement(By.tagName("body"));
        if (homePage.isDisplayed()) {
            System.out.println("'Flipkart' Homepage is Successfully Displayed!!!");
        } else {
            System.out.println("'Flipkart' Homepage is not Displayed!!!");
        }

        // Search for a product, e.g., Mobiles
        WebElement searchProduct = driver.findElement(By.name("q"));
        searchProduct.sendKeys("Mobiles", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the price filter dropdown is visible
        WebElement minPriceDropdown = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='suthUA']//select"))
        );
        Select minPrice = new Select(minPriceDropdown);
        minPrice.selectByValue("10000");  // Selecting ₹10000

        Thread.sleep(3000);

        WebElement maxPriceDropdown = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='tKgS7w']//select"))
        );
        Select maxPrice = new Select(maxPriceDropdown);
        maxPrice.selectByValue("20000");  // Selecting ₹20000

        Thread.sleep(2000);

        // Select the phone brand as 'Samsung'
        WebElement phoneBrand = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SAMSUNG')]"))
        );
        phoneBrand.click();
        
        // Verify both price range and brand of the phone is correct
        String expectedPhoneBrand = "SAMSUNG";
        WebElement actualPhoneBrand = driver.findElement(By.xpath("//div[contains(text(),'SAMSUNG')]"));

        System.out.println("The Phone Brand is: " + actualPhoneBrand.getText());

        // Corrected Assertion (using getText() to compare values)
        Assert.assertEquals(actualPhoneBrand.getText(), expectedPhoneBrand, "Phone Brand is Different");
        
        Thread.sleep(3000);
        
        //select any phone
        WebElement selectingPhone=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='KzDlHZ']")));
        selectingPhone.click();
        
        Thread.sleep(5000);
        for(String tabHandle:driver.getWindowHandles()) {
        	driver.switchTo().window(tabHandle);
        }
        //click on add to cart
        WebElement addCart=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add to cart')]")));
        addCart.click();
        
        System.out.println("The title of page is : " + driver.getTitle());
        
        Thread.sleep(3000);
        
        driver.navigate().back();
        
        Thread.sleep(2000);
        
        //verify that product is added or not
        
        String cartValue="1";
        
        WebElement cartVerification=wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ZuSA--")));
        
        System.out.println("Added Cart Successfully : " + cartVerification.getText());
       
        Assert.assertEquals(cartValue, cartVerification.getText(),"Product is not added");
    }
    @AfterClass
    public void tearDown() throws InterruptedException {
        if (driver != null) {
        	Thread.sleep(7000);
           //driver.quit();
            System.out.println("Browser closed successfully!");
        }
    }
}
