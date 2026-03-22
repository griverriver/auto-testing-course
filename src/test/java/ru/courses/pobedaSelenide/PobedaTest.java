package ru.courses.pobedaSelenide;

import com.codeborne.selenide.Configuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class PobedaTest {
    HomePage objHomePage;
    InfoPopUp objInfoPopUp;
    BookingManagementPage objBookingManagementPage;
    ViewOrderPage objViewOrderPage;

    @Before
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
        Assert.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками", objHomePage.getHomePageTitle());

        Assert.assertTrue(objHomePage.isLogoDisplayed());

        objHomePage.openInfoPopUp();

        Assert.assertEquals("Подготовка к полёту", objInfoPopUp.getReadyToFlyText());
        Assert.assertEquals("Полезная информация", objInfoPopUp.getUsefulInfoText());
        Assert.assertEquals("О компании", objInfoPopUp.getAboutCompanyText());
    }

    @Test
    public void ticketSearchTest() {
        objHomePage.closeTicketOneRubPopUp();
        objHomePage.scrollToTicketSearch();
        Assert.assertEquals("Поиск билета", objHomePage.getTicketSearchBlockText());
        Assert.assertEquals("Откуда", objHomePage.getFromWhereText());
        Assert.assertEquals("Куда", objHomePage.getToWhereText());
        Assert.assertEquals("Туда", objHomePage.getDepartingDateText());
        Assert.assertEquals("Обратно", objHomePage.getReturningDateText());

        objHomePage.enterFromWhere("Москва");
        objHomePage.enterToWhere("Санкт-Петербург");

        objHomePage.clickSearchButton();

        Assert.assertEquals("true", objHomePage.checkRedOutline());
    }

    @Test
    public void bookingManagementTest() throws InterruptedException {
        objHomePage.scrollToManageMyBooking();
        objHomePage.clickOnManageMyBooking();

        Assert.assertEquals("Номер бронирования или билета", objBookingManagementPage.getTicketNumberText());
        Assert.assertEquals("Фамилия клиента", objBookingManagementPage.getClientsLastNameText());
        Assert.assertEquals("поиск", objBookingManagementPage.getSearchButtonText().toLowerCase());

        objBookingManagementPage.enterTicketNumber("XXXXXX");
        objBookingManagementPage.enterClientsLastName("Qwerty");
        objBookingManagementPage.clickSearchButton();
        switchTo().window(1);

        objViewOrderPage.clickOnPrivacyPolicyCheckbox();
        objViewOrderPage.clickOnFindOrderButton();
        //время для прохождения капчи если она появляется
        Thread.sleep(15000);
        Assert.assertTrue(objViewOrderPage.isErrorMessageDisplayed());
    }
}
