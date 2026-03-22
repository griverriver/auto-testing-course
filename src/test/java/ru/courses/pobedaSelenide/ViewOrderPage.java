package ru.courses.pobedaSelenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ViewOrderPage {

    private SelenideElement privacyPolicyCheckbox = $("div[class='customCheckbox']");
    private SelenideElement findOrderButton = $(By.xpath("//button[text()='Найти заказ']"));
    private SelenideElement errorMessage = $(By.xpath("//div[text()='Заказ с указанными параметрами не найден']"));

    public void clickOnPrivacyPolicyCheckbox(){
        privacyPolicyCheckbox.shouldBe(visible).click();
    }

    public void clickOnFindOrderButton(){
        findOrderButton.click();
    }

    public boolean isErrorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }
}
