package ru.courses;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UiPikabuTest {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\papka\\java_AT\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://pikabu.ru/");
    }

    @Test
    public void pikabuTest() throws InterruptedException {
        Assert.assertEquals("Горячее – самые интересные и обсуждаемые посты | Пикабу", driver.getTitle());

        driver.findElement(By.cssSelector("button[class=\"pkb-normal-btn header-right-menu__login-button\"]")).click();

        driver.findElement(By.cssSelector("div[class=\"auth-modal\"]")).isDisplayed();
        driver.findElement(By.cssSelector("div[class=auth-modal] input[class=\"input__input\"][name=username][placeholder=Логин]")).isDisplayed();
        driver.findElement(By.cssSelector("div[class=auth-modal] input[class=\"input__input\"][name=password][placeholder=Пароль]")).isDisplayed();
        driver.findElement(By.cssSelector("div[class=auth-modal] button[type=\"submit\"] span[class=\"button__title\"]")).isDisplayed();

        driver.findElement(By.cssSelector("div[class=auth-modal] input[class=\"input__input\"][name=username][placeholder=Логин]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector("div[class=auth-modal] input[class=\"input__input\"][name=password][placeholder=Пароль]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector("div[class=\"auth-modal\"] div[class=\"tabs__tab tabs__tab_visible auth\"] button[type=\"submit\"]")).click();
        //подтверждаем, что не робот
        //пупупу там дальше реальная проверка
        //driver.findElement(By.cssSelector("span[class*=\"Checkbox__check\"]")).click();
        //руками за это время прощелкиваю капчу
        Thread.sleep(15000);

        String errorText = driver.findElement(By.cssSelector("div[class=\"popup__content\"] span[class=\"auth__error auth__error_top\"]")).getText();
        Assert.assertEquals("Ошибка. Вы ввели неверные данные авторизации", errorText);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}