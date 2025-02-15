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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlipkartSortingProductsByPrice {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.flipkart.com/");

        // Handle Flipkart login popup if it appears
        try {
            WebElement closePopup = driver.findElement(By.xpath("//button[contains(text(),'✕')]"));
            closePopup.click();
        } catch (Exception e) {
            System.out.println("Login popup not displayed.");
        }
    }

    @Test
    public void testCase21() throws InterruptedException {
        // Search for laptops
        WebElement searchLaptop = driver.findElement(By.name("q"));
        searchLaptop.sendKeys("Laptops", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the price filter dropdown is visible
        WebElement minPriceDropdown = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("(//select)[1]")));
        Select sel = new Select(minPriceDropdown);
        sel.selectByVisibleText("₹20000");

        Thread.sleep(3000);

        WebElement divElement = wait.until(ExpectedConditions.elementToBeClickable(By.className("tKgS7w")));
        WebElement maxPriceDropdown = divElement.findElement(By.tagName("select"));
        Select sel2 = new Select(maxPriceDropdown);
        sel2.selectByVisibleText("₹60000");

        Thread.sleep(3000);

        // Assert filter is applied or not
        WebElement filterVerification = driver.findElement(By.className("_6tw8ju"));
        Assert.assertTrue(filterVerification.isDisplayed(), "Filter is not Applied");
        System.out.println("Filter is applied Successfully: " + filterVerification.getText());

        Thread.sleep(3000);

        // Debugging Step: Print Expected and Actual Values
        String actualFilterText = filterVerification.getText().trim();
        String expectedFilterText = "₹20000-₹60000";

        System.out.println("Expected: [" + expectedFilterText + "] Length: " + expectedFilterText.length());
        System.out.println("Actual:   [" + actualFilterText + "] Length: " + actualFilterText.length());

        // Handling Unexpected Characters (₹ symbol or hidden spaces)
        actualFilterText = actualFilterText.replaceAll("[^\\x00-\\x7F₹-]", "").trim();

        // Print each character for debugging
        for (int i = 0; i < Math.min(expectedFilterText.length(), actualFilterText.length()); i++) {
            System.out.println("Char " + i + " - Expected: " + expectedFilterText.charAt(i) +
                    " | Actual: " + actualFilterText.charAt(i));
        }

        // Assert applied price is equal or not
        Assert.assertEquals(actualFilterText, expectedFilterText, "Filter value is failed");
        System.out.println("Filter price is equal to expected price and successfully verified.");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
