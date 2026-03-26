package ru.courses.pobedaSelenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InfoPopUp {

    private SelenideElement readyToFly = $("a[href='/information#flight']");
    private SelenideElement usefulInfo = $("a[href='/information#useful']");
    private SelenideElement aboutCompany = $("a[href='/information#company']");

    @Step("Проверка отображения текста 'Подготовка к полёту'")
    public String getReadyToFlyText(){
        return readyToFly.shouldBe(visible).getText();
    }

    @Step("Проверка отображения текста 'Полезная информация'")
    public String getUsefulInfoText(){
        return usefulInfo.shouldBe(visible).getText();
    }

    @Step("Проверка отображения текста 'О компании'")
    public String getAboutCompanyText(){
        return aboutCompany.shouldBe(visible).getText();
    }
}
