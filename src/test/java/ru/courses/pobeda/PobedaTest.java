package ru.courses.pobeda;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class PobedaTest {
    WebDriver driver;
    HomePage objHomePage;
    InfoPopUp objInfoPopUp;
    TicketSearch objTicketSearch;
    BookingManagementPage objBookingManagementPage;
    ViewOrderPage objViewOrderPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
        driver.get("https://www.flypobeda.ru/");
    }

    @Test
    public void openInfoPopUpTest() {
        objHomePage = new HomePage(driver);
        objInfoPopUp = new InfoPopUp(driver);

        Assertions.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками", objHomePage.getHomePageTitle());

        Assertions.assertTrue(objHomePage.isLogoDisplayed());

        objHomePage.openInfoPopUp();

        objInfoPopUp.waitForPopUp();
        Assertions.assertEquals("Подготовка к полёту", objInfoPopUp.getReadyToFlyText());
        Assertions.assertEquals("Полезная информация", objInfoPopUp.getUsefulInfoText());
        Assertions.assertEquals("О компании", objInfoPopUp.getAboutCompanyText());
    }

    @Test
    public void ticketSearchTest() {
        objTicketSearch = new TicketSearch(driver);

        objTicketSearch.closeTicketOneRubPopUp();
        objTicketSearch.scrollToTicketSearch();
        Assertions.assertEquals("Поиск билета", objTicketSearch.getTicketSearchBlockText());
        Assertions.assertEquals("Откуда", objTicketSearch.getFromWhereText());
        Assertions.assertEquals("Куда", objTicketSearch.getToWhereText());
        Assertions.assertEquals("Туда", objTicketSearch.getDepartingDateText());
        Assertions.assertEquals("Обратно", objTicketSearch.getReturningDateText());

        objTicketSearch.enterFromWhere("Москва");
        objTicketSearch.enterToWhere("Санкт-Петербург");

        objTicketSearch.clickSearchButton();

        String isRedOutline = objTicketSearch.checkRedOutline();
        Assertions.assertEquals("true", isRedOutline);
    }

    @Test
    public void bookingManagementTest() throws InterruptedException {
        objBookingManagementPage = new BookingManagementPage(driver);
        objViewOrderPage = new ViewOrderPage(driver);
        objHomePage = new HomePage(driver);

        objHomePage.scrollToManageMyBooking();
        objHomePage.clickOnManageMyBooking();

        Assertions.assertEquals("Номер бронирования или билета", objBookingManagementPage.getTicketNumberText());
        Assertions.assertEquals("Фамилия клиента", objBookingManagementPage.getClientsLastNameText());
        Assertions.assertEquals("поиск", objBookingManagementPage.getSearchButtonText().toLowerCase());

        objBookingManagementPage.enterTicketNumber("XXXXXX");
        objBookingManagementPage.enterClientsLastName("Qwerty");
        objBookingManagementPage.clickSearchButton();
        Object[] windows = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windows[1]);

        objViewOrderPage.clickOnPrivacyPolicyCheckbox();
        objViewOrderPage.clickOnFindOrderButton();
        //время для прохождения капчи если она появляется
        Thread.sleep(15000);
        Assertions.assertTrue(objViewOrderPage.isErrorMessageDisplayed());
    }

    @AfterEach
    public void closeBrowser() {
        driver.quit();
    }
}
