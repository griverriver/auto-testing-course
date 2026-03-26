package ru.courses.pobedaSelenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BookingManagementPage {
    private SelenideElement clientsLastName = $("input[placeholder='Фамилия клиента']");
    private SelenideElement ticketNumber = $("input[placeholder='Номер бронирования или билета']");
    private SelenideElement searchButton = $(By.xpath("//button[span[text()='Поиск']]"));

    @Step("Проверка отображения текста в плейсхолдере 'Фамилия клиента'")
    public String getClientsLastNameText(){
        return clientsLastName.shouldBe(visible).getAttribute("placeholder");
    }

    @Step("Проверка отображения текста в плейсхолдере 'Номер бронирования'")
    public String getTicketNumberText(){
        return ticketNumber.shouldBe(visible).getAttribute("placeholder");
    }

    @Step("Проверка отображения кнопки 'Поиск'")
    public String getSearchButtonText(){
        return searchButton.shouldBe(visible).getText();
    }

    @Step("Ввести фамилию клиента")
    public void enterClientsLastName(String lastName) {
        clientsLastName.setValue(lastName);
    }

    @Step("Ввести номер бронирования")
    public void enterTicketNumber(String ticket) {
        ticketNumber.setValue(ticket);
    }

    @Step("Нажать на кнопку 'Поиск'")
    public void clickSearchButton() {
        searchButton.click();
    }
}
