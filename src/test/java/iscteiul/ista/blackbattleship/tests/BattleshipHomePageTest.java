package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.BattleshipHomePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class que valida os cenários de teste de caixa-preta da página
 * inicial do jogo de Batalha Naval em <a href="https://papergames.io/en/battleship">papergames.io</a>.
 *
 * <p>Cada método de teste corresponde a uma User Story do Product Backlog:
 * <ul>
 *   <li>{@link #acederAPaginaDoJogo()} — US01</li>
 *   <li>{@link #aceitarCookies()} — US02</li>
 *   <li>{@link #iniciarPartidaContraRobot()} — US03</li>
 *   <li>{@link #jogarContraAmigoComLinkPartilhavel()} — US07</li>
 * </ul>
 * </p>
 *
 * <p>Os testes utilizam JUnit 5 (Jupiter) e o padrão Page Object Model:
 * todas as interações com a página são delegadas à {@link BattleshipHomePage},
 * mantendo esta classe focada apenas na lógica de teste e nas asserções.</p>
 *
 * @see BattleshipHomePage
 */
public class BattleshipHomePageTest {

    /** WebDriver instanciado por cada teste para garantir isolamento. */
    private WebDriver driver;

    /** Page Object da página inicial do jogo de Batalha Naval. */
    private BattleshipHomePage homePage;

    /**
     * Inicializa o ambiente de teste antes de cada cenário: arranca uma
     * nova instância do Chrome, maximiza a janela, configura o tempo
     * de espera implícito e navega para a URL do jogo.
     */
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new BattleshipHomePage(driver);
        homePage.open();
    }

    /**
     * Termina a sessão do browser após cada teste, libertando recursos.
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * US01 — Aceder à página do jogo de Batalha Naval.
     *
     * <p>Verifica que ao abrir a URL do jogo, o título "Battleship Online"
     * é apresentado e os botões dos modos de jogo principais
     * ("Play vs robot" e "Play with a friend") estão visíveis sem
     * necessidade de autenticação prévia.</p>
     */
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

    /**
     * US02 — Aceitar cookies do site.
     *
     * <p>Verifica que na primeira visita à página é apresentado o banner
     * de cookies e que, após o utilizador aceitar, o banner desaparece
     * e deixa de bloquear a interação com a página.</p>
     */
    @Test
    public void aceitarCookies() {
        assertTrue(homePage.isCookieBannerVisible(),
                "O banner de cookies deve aparecer na primeira visita");

        homePage.acceptCookies();

        assertFalse(homePage.isCookieBannerVisible(),
                "O banner de cookies deve desaparecer após aceitar");
    }

    /**
     * US03 — Iniciar partida contra o robot.
     *
     * <p>Verifica o fluxo: clicar em "Play vs robot" abre o diálogo de
     * pedido de nickname; após submissão de um nickname válido, o
     * jogador é redirecionado para a sala de jogo, onde o adversário
     * "Paper Man" (robot) é apresentado.</p>
     */
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