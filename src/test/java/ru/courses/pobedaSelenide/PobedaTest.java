package ru.courses.pobedaSelenide;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

@Epic("Тестирование сайта авиакомпании Победа")
@DisplayName("Тестирование сайта авиакомпании Победа")
//с дескрипшеном не работает
// @Description(value = "описание")
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
    @DisplayName("Вкладка 'Информация'")
    @Feature("Вкладка 'Информация'")
    @Description("Проверка содержимого вкладки 'Информация'")
    public void openInfoPopUpTest() {
        Assertions.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками", objHomePage.getHomePageTitle());

        Assertions.assertTrue(objHomePage.isLogoDisplayed());

        objHomePage.openInfoPopUp();

        Assertions.assertEquals("Подготовка к полёту", objInfoPopUp.getReadyToFlyText());
        Assertions.assertEquals("Полезная информация", objInfoPopUp.getUsefulInfoText());
        Assertions.assertEquals("О компании", objInfoPopUp.getAboutCompanyText());
    }

    @Test
    @DisplayName("Блок поиска билета")
    @Feature("Блок поиска билета")
    @Description("Проверка обязательности заполнения полей")
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
    @DisplayName("Страница 'Управление бронированием'")
    @Feature("Страница 'Управление бронированием'")
    @Description("Проверка поиска несуществующего бронирования")
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

    @Test
    @DisplayName("Главная страница - ТЕСТ С ОШИБКОЙ")
    @Feature("Главная страница - ТЕСТ С ОШИБКОЙ")
    @Description("ТЕСТ С ОШИБКОЙ")
    public void failedTest(){
        Assertions.assertEquals("Некорректный тайтл", objHomePage.getHomePageTitle());
    }
}
