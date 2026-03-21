package ru.courses.pobeda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingManagementPage {
    WebDriver driver;

    @FindBy(css = "input[placeholder='Фамилия клиента']")
    WebElement clientsLastName;

    @FindBy(css = "input[placeholder='Номер бронирования или билета']")
    WebElement ticketNumber;

    @FindBy(xpath = "//button[span[text()='Поиск']]")
    WebElement searchButton;

    public BookingManagementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getClientsLastNameText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(clientsLastName));
        return clientsLastName.getAttribute("placeholder");
    }

    public String getTicketNumberText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(ticketNumber));
        return ticketNumber.getAttribute("placeholder");
    }

    public String getSearchButtonText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        return searchButton.getText();
    }

    public void enterClientsLastName(String lastName) {
        clientsLastName.sendKeys(lastName);
    }

    public void enterTicketNumber(String ticket) {
        ticketNumber.sendKeys(ticket);
    }

    public void clickSearchButton() {
        searchButton.click();
    }
}
