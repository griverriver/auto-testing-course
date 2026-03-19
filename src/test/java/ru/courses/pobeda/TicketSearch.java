package ru.courses.pobeda;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TicketSearch {
    WebDriver driver;

    @FindBy(xpath = "//span[contains(text(), 'Поиск билета') and not (@aria-hidden)]")
    WebElement ticketSearchBlock;

    @FindBy(css = "input[placeholder='Откуда'][id=':Rlama5durm:']")
    WebElement fromWhere;

    @FindBy(css = "input[placeholder='Куда'][id=':Rlqma5durm:']")
    WebElement toWhere;

    @FindBy(css = "input[placeholder='Туда'][id=':Rlcma5durm:']")
    WebElement departingDate;

    @FindBy(xpath = "//input[@placeholder='Туда'][@id=':Rlcma5durm:']/parent::div")
    WebElement departingDateOutlineCheck;

    @FindBy(css = "input[placeholder='Обратно'][id=':Rtcma5durm:']")
    WebElement returningDate;

    @FindBy(xpath = "//button/span[contains(text(), 'Поиск')]")
    WebElement searchButton;

    @FindBy(css = "button[data-testid = 'ads-popup-close-icon']")
    WebElement adPopUpCloseButton;

    public TicketSearch(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeTicketOneRubPopUp(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(adPopUpCloseButton)).click();
        } catch (Exception ignored) {
        }
    }

    public void scrollToTicketSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(ticketSearchBlock));
        Actions action = new Actions(driver);
        action.scrollToElement(ticketSearchBlock).perform();
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
        fromWhere.sendKeys(city);
        fromWhere.sendKeys(Keys.ENTER);
    }

    public void enterToWhere(String city) {
        toWhere.sendKeys(city);
        toWhere.sendKeys(Keys.ENTER);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String checkRedOutline() {
        return departingDateOutlineCheck.getAttribute("data-failed");
    }

}
