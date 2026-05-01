package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.BattleshipHomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class BattleshipHomePageTest {
    private WebDriver driver;
    private BattleshipHomePage homePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new BattleshipHomePage(driver);
        homePage.open();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    // US01 - Aceder à página do jogo de Batalha Naval
    @Test
    public void acederAPaginaDoJogo() {
        homePage.acceptCookiesIfPresent();

        assertEquals("Battleship Online", homePage.getPageTitleText(),
                "O título da página deve ser 'Battleship Online'");
        assertTrue(homePage.isPlayVsRobotVisible(),
                "O botão 'Play vs robot' deve estar visível");
        assertTrue(homePage.isPlayWithFriendVisible(),
                "O botão 'Play with a friend' deve estar visível");
    }
}