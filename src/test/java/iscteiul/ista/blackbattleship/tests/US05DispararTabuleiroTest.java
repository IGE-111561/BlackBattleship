package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.US05DispararTabuleiroPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de teste para a US05 - Disparar contra o tabuleiro adversário.
 *
 * Este teste reproduz em Java + Selenium WebDriver + JUnit
 * o cenário gravado no ficheiro TestSuite_123762.side.
 */
public class US05DispararTabuleiroTest {

    private WebDriver driver;
    private US05DispararTabuleiroPage page;

    /**
     * Inicializa o browser antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        page = new US05DispararTabuleiroPage(driver);
    }

    /**
     * Fecha o browser depois de cada teste.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * Valida que o jogador consegue iniciar uma partida contra o robot
     * e disparar numa célula do tabuleiro adversário.
     */
    @Test
    public void dispararContraTabuleiroAdversario() {
        page.openHomePage();
        page.setRecordedWindowSize();

        page.selectBattleshipGame();
        page.startGameAgainstRobot();

        assertTrue(
                page.hasPlayableBoardCells(),
                "Devem existir células do tabuleiro disponíveis para interação."
        );

        //page.hoverPlayableCell();
        page.shootTargetCell();

        assertTrue(
                page.hasPlayableBoardCells(),
                "Após o disparo, o tabuleiro deve continuar visível."
        );
    }
}