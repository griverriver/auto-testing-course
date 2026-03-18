package ru.courses.pobeda;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class PobedaTest {
    WebDriver driver;
    HomePage objHomePage;
    InfoPopUp objInfoPopUp;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1388, 713));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
        driver.get("https://www.flypobeda.ru/");
    }

    @Test
    public void openInfoPopUpTest() {
        objHomePage = new HomePage(driver);
        objInfoPopUp = new InfoPopUp(driver);

        Assert.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками", objHomePage.getHomePageTitle());

        Assert.assertTrue(objHomePage.isLogoDisplayed());

        objHomePage.openInfoPopUp();

        objInfoPopUp.waitForPopUp();
        Assert.assertEquals("Подготовка к полёту", objInfoPopUp.getReadyToFlyText());
        Assert.assertEquals("Полезная информация", objInfoPopUp.getUsefulInfoText());
        Assert.assertEquals("О компании", objInfoPopUp.getAboutCompanyText());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
