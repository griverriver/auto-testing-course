package ru.courses.pobeda;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;

    @FindBy(css = "img[src*='logo-rus-white']")
    WebElement logo;

    @FindBy(css = "a[href='/information']")
    WebElement infoPopUp;

    @FindBy(xpath = "//a[1][text()='Управление бронированием']")
    WebElement manageMyBooking;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHomePageTitle(){
        return driver.getTitle();
    }

    public boolean isLogoDisplayed(){
        return logo.isDisplayed();
    }

    public void openInfoPopUp(){
        Actions action = new Actions(driver);
        action.moveToElement(infoPopUp).perform();
    }

    public void scrollToManageMyBooking() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.END).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(manageMyBooking));
    }

    public void clickOnManageMyBooking(){
        manageMyBooking.click();
    }
}