package ru.courses.pobedaSelenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ViewOrderPage {

    private SelenideElement privacyPolicyCheckbox = $("div[class='customCheckbox']");
    private SelenideElement findOrderButton = $(By.xpath("//button[text()='Найти заказ']"));
    private SelenideElement errorMessage = $(By.xpath("//div[text()='Заказ с указанными параметрами не найден']"));

    @Step("Нажать на чекбокс согласия с 'Политикой конфиденциальности'")
    public void clickOnPrivacyPolicyCheckbox(){
        privacyPolicyCheckbox.shouldBe(visible).click();
    }

    @Step("Нажать на кнопку 'Найти заказ'")
    public void clickOnFindOrderButton(){
        findOrderButton.click();
    }

    @Step("Проверка отображения текста о некорректном поиске")
    public boolean isErrorMessageDisplayed(){
        return errorMessage.isDisplayed();
    }
}
