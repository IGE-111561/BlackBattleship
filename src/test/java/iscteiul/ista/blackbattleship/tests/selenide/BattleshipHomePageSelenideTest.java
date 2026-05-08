package iscteiul.ista.blackbattleship.tests.selenide;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.pages.selenide.BattleshipHomePageSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class baseada em Selenide para a página inicial da Batalha Naval.
 *
 * <p>Versão equivalente a
 * {@link iscteiul.ista.blackbattleship.tests.BattleshipHomePageTest}
 * mas reescrita com Selenide e instrumentada com Allure.</p>
 *
 * <p>O setup do browser é minimalista — Selenide trata da gestão do
 * WebDriver automaticamente. Apenas configuramos browser, dimensão da janela
 * e o listener do Allure para capturar passos no relatório.</p>
 */
public class BattleshipHomePageSelenideTest {

    private BattleshipHomePageSelenide homePage;

    @BeforeAll
    public static void configureSelenide() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10_000;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeEach
    public void setUp() {
        homePage = new BattleshipHomePageSelenide();
        homePage.open();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    /** US01 — Aceder à página do jogo de Batalha Naval. */
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

    /** US02 — Aceitar cookies do site. */
    @Test
    public void aceitarCookies() {
        assertTrue(homePage.isCookieBannerVisible(),
                "O banner de cookies deve aparecer na primeira visita");

        homePage.acceptCookies();

        assertFalse(homePage.isCookieBannerVisible(),
                "O banner de cookies deve desaparecer após aceitar");
    }

    /** US03 — Iniciar partida contra o robot. */
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

    /** US07 — Jogar contra um amigo via link partilhável. */
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
                "O link partilhável deve começar por 'https://papergames.io/en/r/'. Link obtido: " + shareableLink);
    }
}