package ru.courses.pobeda;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

    @Before
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

        Assert.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками", objHomePage.getHomePageTitle());

        Assert.assertTrue(objHomePage.isLogoDisplayed());

        objHomePage.openInfoPopUp();

        objInfoPopUp.waitForPopUp();
        Assert.assertEquals("Подготовка к полёту", objInfoPopUp.getReadyToFlyText());
        Assert.assertEquals("Полезная информация", objInfoPopUp.getUsefulInfoText());
        Assert.assertEquals("О компании", objInfoPopUp.getAboutCompanyText());
    }

    @Test
    public void ticketSearchTest() {
        objTicketSearch = new TicketSearch(driver);

        objTicketSearch.closeTicketOneRubPopUp();
        objTicketSearch.scrollToTicketSearch();
        Assert.assertEquals("Поиск билета", objTicketSearch.getTicketSearchBlockText());
        Assert.assertEquals("Откуда", objTicketSearch.getFromWhereText());
        Assert.assertEquals("Куда", objTicketSearch.getToWhereText());
        Assert.assertEquals("Туда", objTicketSearch.getDepartingDateText());
        Assert.assertEquals("Обратно", objTicketSearch.getReturningDateText());

        objTicketSearch.enterFromWhere("Москва");
        objTicketSearch.enterToWhere("Санкт-Петербург");

        objTicketSearch.clickSearchButton();

        String isRedOutline = objTicketSearch.checkRedOutline();
        Assert.assertEquals("true", isRedOutline);
    }

    @Test
    public void bookingManagementTest() throws InterruptedException {
        objBookingManagementPage = new BookingManagementPage(driver);
        objViewOrderPage = new ViewOrderPage(driver);
        objHomePage = new HomePage(driver);

        objHomePage.scrollToManageMyBooking();
        objHomePage.clickOnManageMyBooking();

        Assert.assertEquals("Номер бронирования или билета", objBookingManagementPage.getTicketNumberText());
        Assert.assertEquals("Фамилия клиента", objBookingManagementPage.getClientsLastNameText());
        Assert.assertEquals("поиск", objBookingManagementPage.getSearchButtonText().toLowerCase());

        objBookingManagementPage.enterTicketNumber("XXXXXX");
        objBookingManagementPage.enterClientsLastName("Qwerty");
        objBookingManagementPage.clickSearchButton();
        Object[] windows = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windows[1]);

        objViewOrderPage.clickOnPrivacyPolicyCheckbox();
        objViewOrderPage.clickOnFindOrderButton();
        //время для прохождения капчи если она появляется
        Thread.sleep(15000);
        Assert.assertTrue(objViewOrderPage.isErrorMessageDisplayed());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
