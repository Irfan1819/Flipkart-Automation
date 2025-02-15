package flipkart.org.testing;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartOutOfStockProducts {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");
    }

    @Test
    public void testCase13() {
        WebElement homepage = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(homepage.isDisplayed(), "'Flipkart' homepage is not displayed!");

        try {
            WebElement loginPopup = driver.findElement(By.xpath("//button[contains(@class,'_2doB4z')]"));
            loginPopup.click();
        } catch (Exception e) {
            System.out.println("Login popup is not displayed.");
        }

        // Search for a product
        WebElement searchProduct = driver.findElement(By.name("q"));
        searchProduct.sendKeys("Laptops", Keys.ENTER);

        // Wait for products to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickProduct = wait.until(ExpectedConditions.elementToBeClickable(By.className("KzDlHZ")));
        clickProduct.click();

        // Switch to new tab
        for (String tabHandle : driver.getWindowHandles()) {
            driver.switchTo().window(tabHandle);
        }

        // Click on 'Add to Cart'
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add to cart')]")));
        addToCart.click();

        // Wait for 'Out of Stock' message
        WebElement outOfStockMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Product is now out of stock.')]")));

        // Validate message
        Assert.assertEquals(outOfStockMessage.getText(), "Product is now out of stock.", "Error Found");
        System.out.println("Out of Stock message is displayed successfully!");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
