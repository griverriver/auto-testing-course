package ru.courses.pobeda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ViewOrderPage {
    WebDriver driver;

    @FindBy(css = "div[class='customCheckbox']")
    WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//button[text()='Найти заказ']")
    WebElement findOrderButton;

    @FindBy(xpath = "//div[text()='Заказ с указанными параметрами не найден']")
    WebElement errorMessage;

    public ViewOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnPrivacyPolicyCheckbox(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(privacyPolicyCheckbox));
        privacyPolicyCheckbox.click();
    }

    public void clickOnFindOrderButton(){
        findOrderButton.click();
    }

    public boolean isErrorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }
}
