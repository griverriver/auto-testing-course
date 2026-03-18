package ru.courses.pobeda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InfoPopUp {
    WebDriver driver;

    @FindBy(css = "a[href='/information#flight']")
    WebElement readyToFly;

    @FindBy(css = "a[href='/information#useful']")
    WebElement usefulInfo;

    @FindBy(css = "a[href='/information#company']")
    WebElement aboutCompany;

    public InfoPopUp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getReadyToFlyText(){
        return readyToFly.getText();
    }

    public String getUsefulInfoText(){
        return usefulInfo.getText();
    }

    public String getAboutCompanyText(){
        return aboutCompany.getText();
    }

    public void waitForPopUp(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(readyToFly, usefulInfo, aboutCompany));
    }
}
