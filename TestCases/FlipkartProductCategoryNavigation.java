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

public class FlipkartProductCategoryNavigation {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.flipkart.com");

        // Handle the login popup if present
        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[contains(text(),'✕')]"));
            if (closeButton.isDisplayed()) {
                closeButton.click();
            }
        } catch (Exception e) {
            System.out.println("Login popup not displayed.");
        }
    }

    @Test
    public void testCase14() throws InterruptedException {
        // Verify homepage is visible
        if (driver.findElement(By.tagName("body")).isDisplayed()) {
            System.out.println("'Flipkart' homepage is visible successfully!");
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Hover over the 'Electronics' category
        WebElement electronicsCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='Electronics']"))); // Adjust locator as needed

        Actions action = new Actions(driver);
        action.moveToElement(electronicsCategory).perform();

        System.out.println("Hovered over the Electronics category successfully!");
        
        //select gaming and click it
        WebElement gamingMenu=wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Gaming")));
        gamingMenu.click();
        
        Thread.sleep(2000);
        //check that page title is "Gaming"
        WebElement gamingPage=driver.findElement(By.xpath("//h1[contains(text(),'Gaming')]"));
        if(gamingPage.isDisplayed()) {
        	System.out.println(" page title is 'Gamoing' is Visible !!!");
        }
        
        Thread.sleep(2000);
        
        //verify the Filters category is visible or not
        
        WebElement filterPage=driver.findElement(By.xpath("//span[text()='Filters']"));
        Assert.assertEquals(filterPage, "Filters" ,"Filters menu is not visible");
        System.out.println("'Filter Page' is Successfully visible !!!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
