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
 * Page Object Model para a US25 - Desligar Vibração.
 */
public class US25DesligarVibracaoPage {

    private static final String HOME_URL = "https://papergames.io/en/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By settingsButton =
            By.cssSelector("app-header-nav:nth-child(2) .mat-mdc-button-touch-target");

    private final By vibrationButton =
            By.cssSelector("#settings-vibrations-button");

    /**
     * IMPORTANTE:
     * usar icons em vez de ripple
     */
    private final By vibrationToggle =
            By.cssSelector("#settings-vibrations-button .mdc-switch__icons");

    private final By cookieAcceptButton =
            By.cssSelector("button.fc-cta-consent");

    private final By cookieOverlay =
            By.cssSelector(".fc-dialog-overlay");

    public US25DesligarVibracaoPage(WebDriver driver) {
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
     * Abre painel settings.
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
     * Desliga vibração.
     */
    public void disableVibration() {

        WebElement toggle = wait.until(
                ExpectedConditions.presenceOfElementLocated(vibrationToggle)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", toggle);
    }

    /**
     * Verifica se controlo de vibração está visível.
     */
    public boolean isVibrationControlVisible() {

        try {

            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(vibrationButton)
            );

            return element.isDisplayed();

        } catch (TimeoutException e) {

            return false;
        }
    }
}