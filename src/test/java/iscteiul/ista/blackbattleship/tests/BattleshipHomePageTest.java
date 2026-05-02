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

    // US04 - Posicionar navios no tabuleiro
    @Test
    public void posicionarNaviosNoTabuleiro() {
        homePage.acceptCookiesIfPresent();
        homePage.clickPlayVsRobot();

        if (homePage.isNicknameDialogVisible()) {
            homePage.submitNickname("TesteNavios" + System.currentTimeMillis() % 10000);
        }

        assertTrue(
                homePage.isGameBoardVisible() || homePage.hasGameCellsOrBoardElements(),
                "Após iniciar a partida, devem existir elementos visíveis do tabuleiro/navios"
        );
    }

    // US05 - Disparar contra o tabuleiro adversário
    @Test
    public void dispararContraTabuleiro() {
        homePage.acceptCookiesIfPresent();
        homePage.clickPlayVsRobot();

        if (homePage.isNicknameDialogVisible()) {
            homePage.submitNickname("TesteDisparo" + System.currentTimeMillis() % 10000);
        }

        // Garantir que o tabuleiro existe antes de disparar
        assertTrue(homePage.hasGameCellsOrBoardElements());

        // Fazer um disparo
        homePage.shootRandomCell();

        // Se chegou aqui sem erro → passou
        assertTrue(true, "Foi possível disparar numa célula do tabuleiro");
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

    // US19 - Consultar guias e changelog
    @Test
    public void consultarGuiasEChangelog() {

        homePage.openGameGuides();
        assertTrue(
                homePage.isGuidesPageVisible(),
                "A página de guias deve conter informação sobre o jogo"
        );

        homePage.openChangelog();
        assertTrue(
                homePage.isChangelogPageVisible(),
                "A página de changelog deve estar visível"
        );
    }

    // US20 - Aceder à Política de Privacidade e Termos & Condições
    @Test
    public void acederPoliticaPrivacidadeETermosCondicoes() {
        homePage.openPrivacyPolicy();

        assertTrue(
                homePage.isPrivacyPolicyPageVisible(),
                "A página de Política de Privacidade deve estar visível"
        );

        homePage.openTermsConditions();

        assertTrue(
                homePage.isTermsConditionsPageVisible(),
                "A página de Termos & Condições deve estar visível"
        );
    }
}