package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model para a US20 - Aceder à Política de Privacidade
 * e aos Termos & Condições.
 *
 * Esta classe contém as operações necessárias para navegar para a home,
 * aceder às ligações de Privacy e Terms & conditions e validar o conteúdo
 * das respetivas páginas.
 */

public class US20PrivacyTermsPage {

    private static final String HOME_URL = "https://papergames.io/en/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By privacyLink = By.linkText("Privacy");
    private final By termsLink = By.linkText("Terms & conditions");

    /**
     * Construtor da Page Object Class.
     *
     * @param driver instância do WebDriver usada para controlar o browser
     */
    public US20PrivacyTermsPage(WebDriver driver) {
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
        driver.manage().window().setSize(new Dimension(947, 700));
    }

    /**
     * Clica na ligação Privacy.
     */
    public void clickPrivacy() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(privacyLink));
        link.click();
    }

    /**
     * Regressa à página inicial para permitir clicar novamente em ligações do rodapé.
     */
    public void returnToHomePage() {
        driver.get(HOME_URL);
    }

    /**
     * Clica na ligação Terms & conditions.
     */
    public void clickTermsAndConditions() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(termsLink));
        link.click();
    }

    /**
     * Verifica se a página de Privacy está visível.
     *
     * @return true se o URL ou o conteúdo indicarem a página de política de privacidade
     */
    public boolean isPrivacyPageVisible() {
        try {
            wait.until(ExpectedConditions.urlContains("privacy-policy"));
            return driver.getCurrentUrl().contains("privacy-policy")
                    || driver.findElement(By.tagName("body")).getText().toLowerCase().contains("privacy");
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Verifica se a página de Terms & conditions está visível.
     *
     * @return true se o URL ou o conteúdo indicarem a página de termos e condições
     */
    public boolean isTermsPageVisible() {
        try {
            wait.until(ExpectedConditions.urlContains("terms-conditions"));
            return driver.getCurrentUrl().contains("terms-conditions")
                    || driver.findElement(By.tagName("body")).getText().toLowerCase().contains("terms");
        } catch (TimeoutException e) {
            return false;
        }
    }
}