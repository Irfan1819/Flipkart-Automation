package flipkart.org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartSignUp {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        
        // Maximize the browser window
        driver.manage().window().maximize();
        
        // Navigate to Flipkart website
        driver.get("https://www.flipkart.com/");
        
        // Verify homepage is displayed
        WebElement homePage = driver.findElement(By.tagName("body"));
        Assert.assertTrue(homePage.isDisplayed(), "Flipkart homepage is not displayed!");
        System.out.println("Flipkart homepage is successfully displayed.");
    }

    @Test
    public void testCase01() {
        try {
            // Click on login menu
            WebElement loginMenu = driver.findElement(By.xpath("//span[contains(text(),'Login')]"));
            loginMenu.click();
            
            // Enter phone number
            WebElement phnNumber = driver.findElement(By.className("r4vIwl"));
            phnNumber.sendKeys("1234567890", Keys.ENTER);
            
            // Fetch OTP using Twilio
            String otp = OtpSmsHandler.fetchOtpFromSms();
            Assert.assertNotNull(otp, "OTP not received!");
            
            // Enter the OTP
            WebElement otpEnter = driver.findElement(By.id("OtpInput"));
            otpEnter.sendKeys(otp);
            
            System.out.println("OTP entered successfully.");
        } catch (Exception e) {
            Assert.fail("Login failed due to an exception: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}
