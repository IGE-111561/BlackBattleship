package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model para a US24 - Desligar Música.
 */
public class US24DesligarMusicaPage {

    private static final String HOME_URL = "https://papergames.io/en/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By settingsButton =
            By.cssSelector("app-header-nav:nth-child(2) .mat-mdc-button-touch-target");

    private final By musicButton =
            By.cssSelector("#settings-music-button");

    /**
     * ALTERAÇÃO IMPORTANTE:
     * usar .mdc-switch__icons em vez de .mdc-switch__ripple
     */
    private final By musicToggle =
            By.cssSelector("#settings-music-button .mdc-switch__icons");

    private final By cookieAcceptButton =
            By.cssSelector("button.fc-cta-consent");

    private final By cookieOverlay =
            By.cssSelector(".fc-dialog-overlay");

    public US24DesligarMusicaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre homepage.
     */
    public void openHomePage() {
        driver.get(HOME_URL);
    }

    /**
     * Define tamanho da janela.
     */
    public void setRecordedWindowSize() {
        driver.manage().window().setSize(new Dimension(797, 809));
    }

    /**
     * Aceita cookies se aparecer popup.
     */
    public void acceptCookiesIfPresent() {

        try {

            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));

            button.click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieOverlay));

        } catch (Exception ignored) {
        }
    }

    /**
     * Abre definições.
     */
    public void openSettings() {

        acceptCookiesIfPresent();

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieOverlay));
        } catch (Exception ignored) {
        }

        WebElement settings = wait.until(
                ExpectedConditions.presenceOfElementLocated(settingsButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", settings);
    }

    /**
     * Desliga música.
     */
    public void disableMusic() {

        WebElement toggle = wait.until(
                ExpectedConditions.presenceOfElementLocated(musicToggle)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", toggle);
    }

    /**
     * Verifica se controlo de música está visível.
     */
    public boolean isMusicControlVisible() {

        try {

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(musicButton)
            );

            return element.isDisplayed();

        } catch (TimeoutException e) {

            return false;
        }
    }
}