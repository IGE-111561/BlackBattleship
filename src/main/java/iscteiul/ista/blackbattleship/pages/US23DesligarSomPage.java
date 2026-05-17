package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model para a US23 - Desligar Som.
 *
 * Esta classe representa as operações necessárias para abrir a página inicial,
 * aceder às definições e desligar o som através do botão respetivo.
 */
public class US23DesligarSomPage {

    private static final String HOME_URL = "https://papergames.io/en/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By settingsButton =
            By.cssSelector("app-header-nav:nth-child(2) .mat-mdc-button-touch-target");

    private final By soundButton =
            By.cssSelector("#settings-sound-button");

    private final By soundToggle =
            By.cssSelector("#settings-sound-button .mdc-switch__ripple");

    private final By cookieAcceptButton =
            By.cssSelector("button.fc-cta-consent");

    private final By cookieOverlay =
            By.cssSelector(".fc-dialog-overlay");

    /**
     * Construtor da Page Object Class.
     *
     * @param driver instância do WebDriver usada para controlar o browser
     */
    public US23DesligarSomPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página inicial do site.
     */
    public void openHomePage() {
        driver.get(HOME_URL);
    }

    /**
     * Define o tamanho da janela conforme gravado no ficheiro .side.
     */
    public void setRecordedWindowSize() {
        driver.manage().window().setSize(new Dimension(797, 809));
    }

    /**
     * Aceita os cookies, caso o banner esteja visível.
     */
    public void acceptCookiesIfPresent() {
        try {
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));

            button.click();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieOverlay));
        } catch (Exception ignored) {
            // Se não existir banner de cookies, continua normalmente.
        }
    }

    /**
     * Abre o painel de definições.
     */
    public void openSettings() {
        acceptCookiesIfPresent();

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieOverlay));
        } catch (Exception ignored) {
            // Caso o overlay não exista, continua.
        }

        WebElement settings = wait.until(
                ExpectedConditions.presenceOfElementLocated(settingsButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", settings);
    }

    /**
     * Desliga o som clicando no botão Sound.
     */
    public void disableSound() {
        WebElement toggle = wait.until(
                ExpectedConditions.presenceOfElementLocated(soundToggle)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", toggle);
    }

    /**
     * Verifica se o controlo de som está visível.
     *
     * @return true se o controlo de som estiver presente no painel de definições
     */
    public boolean isSoundControlVisible() {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(soundButton)
            );

            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}