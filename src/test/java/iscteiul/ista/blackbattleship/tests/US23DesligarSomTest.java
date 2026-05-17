package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.US23DesligarSomPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para a US23 - Desligar Som.
 *
 * Este teste reproduz em Java + Selenium WebDriver + JUnit
 * o cenário gravado no ficheiro TestSuite_123774.side.
 */
public class US23DesligarSomTest {

    private WebDriver driver;
    private US23DesligarSomPage page;

    /**
     * Inicializa o browser antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new US23DesligarSomPage(driver);
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
     * e interagir com o botão para desligar o som.
     */
    @Test
    public void desligarSomNasDefinicoes() {
        page.openHomePage();
        page.setRecordedWindowSize();

        page.openSettings();

        assertTrue(
                page.isSoundControlVisible(),
                "O controlo de som deve estar visível no painel de definições."
        );

        page.disableSound();

        assertTrue(
                page.isSoundControlVisible(),
                "Após clicar, o controlo de som deve continuar visível."
        );
    }
}