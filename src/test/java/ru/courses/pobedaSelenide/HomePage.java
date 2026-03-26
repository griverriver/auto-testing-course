package ru.courses.pobedaSelenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;

public class HomePage {

    private SelenideElement logo = $("img[src*='logo-rus-white']");
    private SelenideElement infoPopUp = $("a[href='/information']");
    private SelenideElement manageMyBooking = $(By.xpath("//a[1][text()='Управление бронированием']"));

    private SelenideElement ticketSearchBlock = $(By.xpath("//span[contains(text(), 'Поиск билета') and not (@aria-hidden)]"));
    private SelenideElement fromWhere = $("input[placeholder='Откуда'][id=':Rlama5durm:']");
    private SelenideElement toWhere = $("input[placeholder='Куда'][id=':Rlqma5durm:']");
    private SelenideElement departingDate = $("input[placeholder='Туда'][id=':Rlcma5durm:']");
    private SelenideElement departingDateOutlineCheck = $(By.xpath("//input[@placeholder='Туда'][@id=':Rlcma5durm:']/parent::div"));
    private SelenideElement returningDate = $("input[placeholder='Обратно'][id=':Rtcma5durm:']");
    private SelenideElement searchButton = $(By.xpath("//button/span[contains(text(), 'Поиск')]"));
    private SelenideElement adPopUpCloseButton = $("button[data-testid = 'ads-popup-close-icon']");


    @Step("Проверка наименования страницы")
    public String getHomePageTitle() {
        return title();
    }

    @Step("Проверка отображения логотипа")
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    @Step("Навести мышку на пункт 'Информация' в верхней меню панели")
    public void openInfoPopUp() {
        infoPopUp.hover();
    }

    @Step("Проскролить к пункту 'Управление бронированием' в нижнем меню сайта")
    public void scrollToManageMyBooking() {
        manageMyBooking.scrollTo().shouldBe(visible);
    }

    @Step("Нажать на 'Управление бронированием'")
    public void clickOnManageMyBooking() {
        manageMyBooking.click();
    }

    @Step("Закрыть поп-ап рекламу")
    public void closeTicketOneRubPopUp(){
        if (adPopUpCloseButton.is(visible)) {
            adPopUpCloseButton.click();
        }
    }

    @Step("Проскролить до блока поиска билета")
    public void scrollToTicketSearch() {
        ticketSearchBlock.scrollTo().shouldBe(visible);
    }

    @Step("Проверка отображения блока поиска билета")
    public String getTicketSearchBlockText(){
        return ticketSearchBlock.getText();
    }

    @Step("Проверка отображения текста в плейсхолдере 'Откуда'")
    public String getFromWhereText(){
        return fromWhere.getAttribute("placeholder");
    }

    @Step("Проверка отображения текста в плейсхолдере 'Куда'")
    public String getToWhereText(){
        return toWhere.getAttribute("placeholder");
    }

    @Step("Проверка отображения текста в плейсхолдере 'Туда'")
    public String getDepartingDateText(){
        return departingDate.getAttribute("placeholder");
    }

    @Step("Проверка отображения текста в плейсхолдере 'Обратно'")
    public String getReturningDateText(){
        return returningDate.getAttribute("placeholder");
    }

    @Step("Ввести значение поля 'Откуда'")
    public void enterFromWhere(String city) {
        fromWhere.setValue(city).pressEnter();
    }

    @Step("Ввести значение поля 'Куда'")
    public void enterToWhere(String city) {
        toWhere.setValue(city).pressEnter();
    }

    @Step("Нажать на кнопку 'Поиск'")
    public void clickSearchButton() {
        searchButton.click();
    }

    @Step("Проверка отображения красной обводки")
    public String checkRedOutline() {
        return departingDateOutlineCheck.shouldBe(visible).getAttribute("data-failed");
    }
}