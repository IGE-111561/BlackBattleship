package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.US27MonstersShopPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para a US27 - Consultar lista de Monsters na loja.
 *
 * Este teste reproduz em Java + Selenium WebDriver + JUnit
 * o cenário gravado no ficheiro TestSuite_123762.side.
 */

public class US27MonstersShopPageTest {

    private WebDriver driver;
    private US27MonstersShopPage page;

    /**
     * Inicializa o browser antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new US27MonstersShopPage(driver);
    }

    /**
     * Fecha o browser após cada teste.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Testa o acesso à categoria Monsters através da loja.
     */
    @Test
    public void consultarListaDeMonstersNaLoja() {
        page.openBattleshipPage();
        page.setRecordedWindowSize();
        page.openShop();
        page.openMonstersCategory();

        assertTrue(
                page.isMonstersPageVisible(),
                "A página/categoria Monsters deve ficar visível após selecionar Monsters na loja."
        );
    }
}