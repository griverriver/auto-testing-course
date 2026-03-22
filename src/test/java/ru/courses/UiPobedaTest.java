package ru.courses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UiPobedaTest {

    WebDriver driver;
    WebDriverWait wait;
    WebElement flyToKaliningrad;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1388, 713));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
        driver.get("https://www.google.com/");
    }

    @Test
    public void pobedaTest() throws InterruptedException {
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Сайт компании Победа");
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys(Keys.ENTER);
        //капча появляется, поэтому делаю ожидание, чтобы пройти ее вручную
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='recaptcha']")));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3)[1]")));
        driver.findElement(By.xpath("(//h3)[1]")).click();

        flyToKaliningrad = driver.findElement(By.xpath("//div[contains(text(),'Полетели в Калининград')]"));
        waitForText(flyToKaliningrad, "Полетели в Калининград");
        Assertions.assertEquals("Полетели в Калининград!", flyToKaliningrad.getText());

        driver.findElement(By.xpath("//button[contains(text(), 'РУС')]")).click();
        driver.findElement(By.xpath("//div[contains(text(), 'English')]")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[contains(text(),'Ticket search') and not (@aria-hidden)]"), "Ticket search"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[contains(text(), 'Online check-in') and not (@aria-hidden)]"), "Online check-in"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[contains(text(), 'Manage my booking') and not (@aria-hidden)]"), "Manage my booking"));
        String ticketSearch = driver.findElement(By.xpath("//span[contains(text(), 'Ticket search') and not (@aria-hidden)]")).getText();
        String onlineCheckIn = driver.findElement(By.xpath("//span[contains(text(), 'Online check-in') and not (@aria-hidden)]")).getText();
        String manageMyBooking = driver.findElement(By.xpath("//span[contains(text(), 'Manage my booking') and not (@aria-hidden)]")).getText();
        Assertions.assertEquals("Ticket search", ticketSearch);
        Assertions.assertEquals("Online check-in", onlineCheckIn);
        Assertions.assertEquals("Manage my booking", manageMyBooking);
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }

    public static void waitForText(WebElement element, String expectedText) {
        float waitingTime = 0;
        float MAX_WAITING_TIME = 20000;
        long startLoadingTime = System.currentTimeMillis();

        while (!element.getText().contains(expectedText)) {
            waitingTime = System.currentTimeMillis() - startLoadingTime;

            if (waitingTime > MAX_WAITING_TIME) {
                System.out.println("Condition wasn't executed within time limit");
                break;
            }
        }

        if (element.getText().contains(expectedText)) {
            System.out.println("Condition was executed in " + waitingTime + " ms");
        }

    }
}