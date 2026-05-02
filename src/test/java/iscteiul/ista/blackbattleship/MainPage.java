package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://www.jetbrains.com/
public class MainPage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@data-test-marker='Products']")
    public WebElement seeDeveloperToolsButton;

    //O suggestion link está por cima de uma div que também tem um link em sí para /products
    @FindBy(xpath = "//*[@data-test='suggestion-link']")
    public WebElement findYourToolsButton;

    @FindBy(xpath = "//div[@data-test='main-menu-item' and @data-test-marker='Products']")
    public WebElement toolsMenu;

    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    private final By cookieAcceptButton = By.cssSelector("button.ch2-allow-all-btn");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btn = wait.until(
                    ExpectedConditions.elementToBeClickable(cookieAcceptButton)
            );
            btn.click();
        } catch (TimeoutException e) {
            // sem banner, segue
        }
    }
}