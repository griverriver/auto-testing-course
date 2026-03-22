package ru.courses.pobedaSelenide;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InfoPopUp {

    private SelenideElement readyToFly = $("a[href='/information#flight']");
    private SelenideElement usefulInfo = $("a[href='/information#useful']");
    private SelenideElement aboutCompany = $("a[href='/information#company']");

    public String getReadyToFlyText(){
        return readyToFly.shouldBe(visible).getText();
    }

    public String getUsefulInfoText(){
        return usefulInfo.shouldBe(visible).getText();
    }

    public String getAboutCompanyText(){
        return aboutCompany.shouldBe(visible).getText();
    }
}
