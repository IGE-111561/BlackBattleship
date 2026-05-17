package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.US20PrivacyTermsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para a US20 - Privacidade, Termos e Condições.
 *
 * Este teste reproduz em Java + Selenium WebDriver + JUnit
 * o cenário gravado no ficheiro TestSuite_123762.side.
 */

public class US20PrivacyTermsPageTest {

    private WebDriver driver;
    private US20PrivacyTermsPage page;

    /**
     * Inicializa o browser antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new US20PrivacyTermsPage(driver);
    }

    /**
     * Fecha o browser depois de cada teste.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Valida que o utilizador consegue aceder à Política de Privacidade
     * e aos Termos & Condições a partir da home.
     */
    @Test
    public void acederAPrivacidadeETermosCondicoes() {
        page.openHomePage();
        page.setRecordedWindowSize();

        page.clickPrivacy();
        assertTrue(
                page.isPrivacyPageVisible(),
                "A página de Política de Privacidade deve ser apresentada."
        );

        page.returnToHomePage();

        page.clickTermsAndConditions();
        assertTrue(
                page.isTermsPageVisible(),
                "A página de Termos & Condições deve ser apresentada."
        );
    }
}