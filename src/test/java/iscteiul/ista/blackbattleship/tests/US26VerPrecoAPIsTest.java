package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.US26VerPrecoAPIsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para a US26 - Ver preço APIs.
 *
 * Este teste reproduz em Java + Selenium WebDriver + JUnit
 * o cenário gravado no ficheiro TestSuite_123774.side.
 */
public class US26VerPrecoAPIsTest {

    private WebDriver driver;
    private US26VerPrecoAPIsPage page;

    /**
     * Inicializa o browser antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new US26VerPrecoAPIsPage(driver);
    }

    /**
     * Fecha o browser depois de cada teste.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Valida que o utilizador consegue aceder à página de preços da API.
     */
    @Test
    public void verPrecoAPIs() {
        page.openHomePage();
        page.setRecordedWindowSize();
        page.scrollToTop();

        page.openApiPricing();

        assertTrue(
                page.isApiPricingPageVisible(),
                "A página de preços da API deve ser apresentada após clicar no menu API."
        );
    }
}