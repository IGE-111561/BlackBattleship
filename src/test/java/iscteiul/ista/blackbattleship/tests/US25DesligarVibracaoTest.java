package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.US25DesligarVibracaoPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para a US25 - Desligar Vibração.
 *
 * Este teste reproduz em Java + Selenium WebDriver + JUnit
 * o cenário gravado no ficheiro TestSuite_123774.side.
 */
public class US25DesligarVibracaoTest {

    private WebDriver driver;
    private US25DesligarVibracaoPage page;

    /**
     * Inicializa o browser antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new US25DesligarVibracaoPage(driver);
    }

    /**
     * Fecha o browser depois de cada teste.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Valida que o utilizador consegue abrir as definições
     * e interagir com o botão para desligar a vibração.
     */
    @Test
    public void desligarVibracaoNasDefinicoes() {
        page.openHomePage();
        page.setRecordedWindowSize();

        page.openSettings();

        assertTrue(
                page.isVibrationControlVisible(),
                "O controlo de vibração deve estar visível no painel de definições."
        );

        page.disableVibration();

        assertTrue(
                page.isVibrationControlVisible(),
                "Após clicar, o controlo de vibração deve continuar visível."
        );
    }
}