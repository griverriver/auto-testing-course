package ru.courses.pobeda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(css = "img[src*='logo-rus-white']")
    WebElement logo;

    @FindBy(css = "a[href='/information']")
    WebElement infoPopUp;

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
}