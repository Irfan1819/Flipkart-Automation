package flipkart.org.testing;

import java.time.Duration;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

public class FlipkartPlaceOrder {
    public static final String ACCOUNT_SID = "ACb0739868f9898be9a4b9e791ce8f97d3";
    public static final String AUTH_TOKEN = "27c53b34f6cd3ede8f399a5313bb564b";
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");
    }

    @Test
    public void testCase04() throws InterruptedException {
        // Verify Flipkart homepage is displayed
        WebElement homePage = driver.findElement(By.tagName("body"));
        Assert.assertTrue(homePage.isDisplayed(), "'Flipkart' Homepage is not displayed!");

        // Search for a product, e.g., Mobiles
        WebElement searchProduct = driver.findElement(By.name("q"));
        searchProduct.sendKeys("Mobiles", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the price filter dropdown is visible
        WebElement minPriceDropdown = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='suthUA']//select"))
        );
        new Select(minPriceDropdown).selectByValue("10000");  // Selecting ₹10000

        Thread.sleep(3000);

        WebElement maxPriceDropdown = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='tKgS7w']//select"))
        );
        new Select(maxPriceDropdown).selectByValue("20000");  // Selecting ₹20000

        Thread.sleep(2000);

        // Select the phone brand as 'Samsung'
        WebElement phoneBrand = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SAMSUNG')]"))
        );
        phoneBrand.click();
        
        // Verify the selected phone brand
        WebElement actualPhoneBrand = driver.findElement(By.xpath("//div[contains(text(),'SAMSUNG')]"));
        Assert.assertEquals(actualPhoneBrand.getText(), "SAMSUNG", "Phone Brand is Different");
        
        Thread.sleep(3000);

        // Select a phone
        WebElement selectingPhone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='KzDlHZ']")));
        selectingPhone.click();

        Thread.sleep(5000);
        for (String tabHandle : driver.getWindowHandles()) {
            driver.switchTo().window(tabHandle);
        }

        // Click on Add to Cart
        WebElement addCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Add to cart')]")));
        addCart.click();
        
        System.out.println("The title of the page is : " + driver.getTitle());

        Thread.sleep(3000);

        // Click on Place Order
        WebElement placeOrder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Place Order')]")));
        placeOrder.click();

        // Enter mobile number
        WebElement mobileNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("r4vIwl")));
        mobileNumber.sendKeys("+18482604770", Keys.ENTER);

        // Get OTP using Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        try {
            String otp = getMessage();
            System.out.println("Received OTP: " + otp);

            // Enter OTP in the input field
            WebElement otpField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='text']"))
            );
            otpField.sendKeys(otp);
        } catch (Exception e) {
            System.out.println("Error retrieving OTP: " + e.getMessage());
        }
    }

    public String getMessage() {
        return getMessages()
            .filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
            .filter(m -> m.getTo().equals("+18482604770"))
            .map(Message::getBody)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No OTP message found!"));
    }

    private Stream<Message> getMessages() {
        ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
        return StreamSupport.stream(messages.spliterator(), false);
    }
    
    

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
