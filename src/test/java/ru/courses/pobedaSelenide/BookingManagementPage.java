package ru.courses.pobedaSelenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class BookingManagementPage {
    private SelenideElement clientsLastName = $("input[placeholder='Фамилия клиента']");
    private SelenideElement ticketNumber = $("input[placeholder='Номер бронирования или билета']");
    private SelenideElement searchButton = $(By.xpath("//button[span[text()='Поиск']]"));

    public String getClientsLastNameText(){
        return clientsLastName.shouldBe(visible).getAttribute("placeholder");
    }

    public String getTicketNumberText(){
        return ticketNumber.shouldBe(visible).getAttribute("placeholder");
    }

    public String getSearchButtonText(){
        return searchButton.shouldBe(visible).getText();
    }

    public void enterClientsLastName(String lastName) {
        clientsLastName.setValue(lastName);
    }

    public void enterTicketNumber(String ticket) {
        ticketNumber.setValue(ticket);
    }

    public void clickSearchButton() {
        searchButton.click();
    }
}
