package ru.courses.pobedaSelenide;

import com.codeborne.selenide.SelenideElement;
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


    public String getHomePageTitle() {
        return title();
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public void openInfoPopUp() {
        infoPopUp.hover();
    }

    public void scrollToManageMyBooking() {
        manageMyBooking.scrollTo().shouldBe(visible);
    }

    public void clickOnManageMyBooking() {
        manageMyBooking.click();
    }


    public void closeTicketOneRubPopUp(){
        if (adPopUpCloseButton.is(visible)) {
            adPopUpCloseButton.click();
        }
    }

    public void scrollToTicketSearch() {
        ticketSearchBlock.scrollTo().shouldBe(visible);
    }

    public String getTicketSearchBlockText(){
        return ticketSearchBlock.getText();
    }

    public String getFromWhereText(){
        return fromWhere.getAttribute("placeholder");
    }

    public String getToWhereText(){
        return toWhere.getAttribute("placeholder");
    }

    public String getDepartingDateText(){
        return departingDate.getAttribute("placeholder");
    }

    public String getReturningDateText(){
        return returningDate.getAttribute("placeholder");
    }

    public void enterFromWhere(String city) {
        fromWhere.setValue(city).pressEnter();
    }

    public void enterToWhere(String city) {
        toWhere.setValue(city).pressEnter();
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String checkRedOutline() {
        return departingDateOutlineCheck.shouldBe(visible).getAttribute("data-failed");
    }
}