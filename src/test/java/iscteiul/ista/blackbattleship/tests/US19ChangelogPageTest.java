package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.US19ChangelogPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para a US19 - Consultar Changelog.
 *
 * Este teste reproduz em Java + Selenium WebDriver + JUnit
 * o cenário gravado no ficheiro TestSuite_123762.side.
 */
public class US19ChangelogPageTest {

    private WebDriver driver;
    private US19ChangelogPage page;

    /**
     * Inicializa o browser antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new US19ChangelogPage(driver);
    }

    /**
     * Fecha o browser depois de cada teste.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Valida que o utilizador consegue aceder à página de Changelog
     * a partir da página da Batalha Naval.
     */
    @Test
    public void consultarChangelog() {
        page.openBattleshipPage();
        page.setRecordedWindowSize();

        page.clickChangelog();

        assertTrue(
                page.isChangelogPageVisible(),
                "A página Changelog deve ser apresentada após clicar no respetivo menu."
        );
    }
}
