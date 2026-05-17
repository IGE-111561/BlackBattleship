package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.US24DesligarMusicaPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para a US24 - Desligar Música.
 *
 * Este teste reproduz em Java + Selenium WebDriver + JUnit
 * o cenário gravado no ficheiro TestSuite_123774.side.
 */
public class US24DesligarMusicaTest {

    private WebDriver driver;
    private US24DesligarMusicaPage page;

    /**
     * Inicializa o browser antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new US24DesligarMusicaPage(driver);
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
     * e interagir com o botão para desligar a música.
     */
    @Test
    public void desligarMusicaNasDefinicoes() {
        page.openHomePage();
        page.setRecordedWindowSize();

        page.openSettings();

        assertTrue(
                page.isMusicControlVisible(),
                "O controlo de música deve estar visível no painel de definições."
        );

        page.disableMusic();

        assertTrue(
                page.isMusicControlVisible(),
                "Após clicar, o controlo de música deve continuar visível."
        );
    }
}