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
 * Page Object Model para a US26 - Ver preço APIs.
 */
public class US26VerPrecoAPIsPage {

    private static final String HOME_URL = "https://papergames.io/en/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By apiLink =
            By.linkText("API");

    private final By cookieAcceptButton =
            By.cssSelector("button.fc-cta-consent");

    private final By cookieOverlay =
            By.cssSelector(".fc-dialog-overlay");

    public US26VerPrecoAPIsPage(WebDriver driver) {
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
     * Faz scroll para topo.
     */
    public void scrollToTop() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0,0)");
    }

    /**
     * Aceita cookies se popup existir.
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
     * Abre página API Pricing.
     */
    public void openApiPricing() {

        acceptCookiesIfPresent();

        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieOverlay));
        } catch (Exception ignored) {
        }

        WebElement api = wait.until(
                ExpectedConditions.presenceOfElementLocated(apiLink)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", api);
    }

    /**
     * Verifica se página API pricing abriu.
     */
    public boolean isApiPricingPageVisible() {

        try {

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("/pricing"),
                    ExpectedConditions.urlContains("/games-as-a-service")
            ));

            String url = driver.getCurrentUrl().toLowerCase();

            return url.contains("pricing")
                    || url.contains("games-as-a-service");

        } catch (TimeoutException e) {

            return false;
        }
    }
}