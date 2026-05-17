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

    // US02 - Aceitar cookies do site
    @Test
    public void aceitarCookies() {
        assertTrue(homePage.isCookieBannerVisible(),
                "O banner de cookies deve aparecer na primeira visita");

        homePage.acceptCookies();

        assertFalse(homePage.isCookieBannerVisible(),
                "O banner de cookies deve desaparecer após aceitar");
    }

    // US03 - Iniciar partida contra o robot
    @Test
    public void iniciarPartidaContraRobot() {
        homePage.acceptCookiesIfPresent();
        homePage.clickPlayVsRobot();

        assertTrue(homePage.isNicknameDialogVisible(),
                "Ao clicar 'Play vs robot' deve aparecer o pedido de nickname");

        homePage.submitNickname("TesteBot" + System.currentTimeMillis() % 10000);

        assertTrue(homePage.isPlayingAgainstRobot(),
                "Após submeter nickname, o jogador deve estar na sala de jogo contra 'Paper Man'");
    }


    // US07 - Jogar contra um amigo via link partilhável
    @Test
    public void jogarContraAmigoComLinkPartilhavel() {
        homePage.acceptCookiesIfPresent();
        homePage.clickPlayWithFriend();

        assertTrue(homePage.isNicknameDialogVisible(),
                "Ao clicar 'Play with a friend' deve aparecer o pedido de nickname");

        homePage.submitNickname("TesteAmigo" + System.currentTimeMillis() % 10000);

        assertTrue(homePage.isShareLinkPageVisible(),
                "Após submeter nickname, deve aparecer a página com a mensagem 'Share this link with a friend'");

        String shareableLink = homePage.getShareableLink();
        assertTrue(shareableLink.startsWith("https://papergames.io/en/r/"),
                "O link partilhável deve estar visível e começar por 'https://papergames.io/en/r/'. Link obtido: " + shareableLink);
    }

}