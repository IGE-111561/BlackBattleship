package iscteiul.ista.blackbattleship.pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

/**
 * Page Object Model para a US19 - Consultar o Changelog.
 *
 * Esta classe contém os métodos necessários para abrir a página
 * da Batalha Naval e aceder ao menu Changelog.
 */
public class US19ChangelogPage {

    private static final String BATTLESHIP_URL = "https://papergames.io/en/battleship";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By changelogLink = By.linkText("Changelog");

    /**
     * Construtor da Page Object Class.
     *
     * @param driver instância do WebDriver usada para controlar o browser
     */
    public US19ChangelogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página da Batalha Naval.
     */
    public void openBattleshipPage() {
        driver.get(BATTLESHIP_URL);
    }

    /**
     * Define o tamanho da janela conforme gravado no ficheiro .side.
     */
    public void setRecordedWindowSize() {
        driver.manage().window().setSize(new Dimension(1254, 812));
    }

    /**
     * Clica na ligação Changelog.
     */
    public void clickChangelog() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(changelogLink));
        link.click();
    }

    /**
     * Verifica se a página de Changelog está visível.
     *
     * @return true se o URL ou o conteúdo indicarem a página Changelog
     */
    public boolean isChangelogPageVisible() {
        try {
            wait.until(ExpectedConditions.urlContains("/en/changelog"));
            String bodyText = driver.findElement(By.tagName("body")).getText().toLowerCase();

            return driver.getCurrentUrl().contains("/en/changelog")
                    || bodyText.contains("changelog");
        } catch (TimeoutException e) {
            return false;
        }
    }
}