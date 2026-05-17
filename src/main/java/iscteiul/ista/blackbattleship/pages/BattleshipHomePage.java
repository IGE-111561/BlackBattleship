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

    @FindBy(xpath = "//h2[normalize-space()='Who are you?']")
    private WebElement nicknameDialogTitle;

    @FindBy(css = "input[formcontrolname='username']")
    private WebElement nicknameInput;

    @FindBy(css = "footer button[type='submit']")
    private WebElement nicknameContinueButton;

    private final By cookieAcceptButton = By.cssSelector("button.fc-cta-consent");
    private final By cookieDialog = By.cssSelector("div.fc-dialog, div.fc-consent-root");

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

    public void clickPlayVsRobot() {
        wait.until(ExpectedConditions.elementToBeClickable(playVsRobotButton)).click();
    }

    public void clickPlayWithFriend() {
        wait.until(ExpectedConditions.elementToBeClickable(playWithFriendButton)).click();
    }

    public boolean isNicknameDialogVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(nicknameDialogTitle));
            return nicknameDialogTitle.isDisplayed() && nicknameInput.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void submitNickname(String nickname) {
        wait.until(ExpectedConditions.visibilityOf(nicknameInput));
        nicknameInput.sendKeys(nickname);
        wait.until(ExpectedConditions.elementToBeClickable(nicknameContinueButton)).click();
    }

    public boolean isPlayingAgainstRobot() {
        try {
            WebElement opponent = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[normalize-space()='Paper Man']")
            ));
            return opponent.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isShareLinkPageVisible() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[normalize-space()='Share this link with a friend']")
            ));
            return title.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getShareableLink() {
        try {
            WebElement linkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("app-copy-text span")
            ));
            return linkElement.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }

}