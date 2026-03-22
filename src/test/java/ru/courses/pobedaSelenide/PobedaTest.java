package ru.courses.pobedaSelenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class PobedaTest {
    HomePage objHomePage;
    InfoPopUp objInfoPopUp;
    BookingManagementPage objBookingManagementPage;
    ViewOrderPage objViewOrderPage;

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1440x900";
        Configuration.pageLoadTimeout = 50000;
        open("https://www.flypobeda.ru/");

        objHomePage = new HomePage();
        objInfoPopUp = new InfoPopUp();
        objBookingManagementPage = new BookingManagementPage();
        objViewOrderPage = new ViewOrderPage();
    }

    @Test
    public void openInfoPopUpTest() {
        Assertions.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками", objHomePage.getHomePageTitle());

        Assertions.assertTrue(objHomePage.isLogoDisplayed());

        objHomePage.openInfoPopUp();

        Assertions.assertEquals("Подготовка к полёту", objInfoPopUp.getReadyToFlyText());
        Assertions.assertEquals("Полезная информация", objInfoPopUp.getUsefulInfoText());
        Assertions.assertEquals("О компании", objInfoPopUp.getAboutCompanyText());
    }

    @Test
    public void ticketSearchTest() {
        objHomePage.closeTicketOneRubPopUp();
        objHomePage.scrollToTicketSearch();
        Assertions.assertEquals("Поиск билета", objHomePage.getTicketSearchBlockText());
        Assertions.assertEquals("Откуда", objHomePage.getFromWhereText());
        Assertions.assertEquals("Куда", objHomePage.getToWhereText());
        Assertions.assertEquals("Туда", objHomePage.getDepartingDateText());
        Assertions.assertEquals("Обратно", objHomePage.getReturningDateText());

        objHomePage.enterFromWhere("Москва");
        objHomePage.enterToWhere("Санкт-Петербург");

        objHomePage.clickSearchButton();

        Assertions.assertEquals("true", objHomePage.checkRedOutline());
    }

    @Test
    public void bookingManagementTest() throws InterruptedException {
        objHomePage.scrollToManageMyBooking();
        objHomePage.clickOnManageMyBooking();

        Assertions.assertEquals("Номер бронирования или билета", objBookingManagementPage.getTicketNumberText());
        Assertions.assertEquals("Фамилия клиента", objBookingManagementPage.getClientsLastNameText());
        Assertions.assertEquals("поиск", objBookingManagementPage.getSearchButtonText().toLowerCase());

        objBookingManagementPage.enterTicketNumber("XXXXXX");
        objBookingManagementPage.enterClientsLastName("Qwerty");
        objBookingManagementPage.clickSearchButton();
        switchTo().window(1);

        objViewOrderPage.clickOnPrivacyPolicyCheckbox();
        objViewOrderPage.clickOnFindOrderButton();
        //время для прохождения капчи если она появляется
        Thread.sleep(15000);
        Assertions.assertTrue(objViewOrderPage.isErrorMessageDisplayed());
    }
}
