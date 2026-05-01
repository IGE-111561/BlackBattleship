package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://papergames.io/en/battleship
public class BattleshipHomePage {
    public static final String URL = "https://papergames.io/en/battleship";

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(css = "h1.area-title")
    private WebElement pageTitle;

    @FindBy(xpath = "//span[normalize-space()='Play vs robot']")
    private WebElement playVsRobotButton;

    @FindBy(xpath = "//span[normalize-space()='Play with a friend']")
    private WebElement playWithFriendButton;
    private final By cookieDialog = By.cssSelector("div.fc-dialog, div.fc-consent-root");

    private final By cookieAcceptButton = By.cssSelector("button.fc-cta-consent");

    public BattleshipHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (TimeoutException e) {
            // sem banner, segue
        }
    }

    public String getPageTitleText() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public boolean isPlayVsRobotVisible() {
        return playVsRobotButton.isDisplayed();
    }

    public boolean isPlayWithFriendVisible() {
        return playWithFriendButton.isDisplayed();
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
    }

    public boolean isCookieBannerVisible() {
        try {
            return driver.findElement(cookieDialog).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}