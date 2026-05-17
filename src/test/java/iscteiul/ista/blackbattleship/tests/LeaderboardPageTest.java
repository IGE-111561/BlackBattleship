package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.LeaderboardPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class – User Story 12: Ver o leaderboard diário da Batalha Naval.
 *
 * <p><b>User Story:</b><br>
 * Como jogador registado, quero ver o leaderboard diário da Batalha Naval
 * para comparar a minha performance com a de outros jogadores.
 *
 * <p><b>Critérios de Aceitação verificados:</b>
 * <ol>
 *   <li>{@link #battleshipImageVisibleOnHomePage()} –
 *       A imagem do Battleship está visível na grelha de jogos.</li>
 *   <li>{@link #leaderboardSectionExistsOnGamePage()} –
 *       Existe uma secção de leaderboard com o link "See all" visível
 *       na página do jogo.</li>
 *   <li>{@link #seeAllNavigatesToLeaderboardPage()} –
 *       Clicar "See all" navega para a URL do leaderboard
 *       ({@code /en/t/}).</li>
 * </ol>
 *
 * @see LeaderboardPage
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LeaderboardPageTest {

    private WebDriver driver;
    private LeaderboardPage leaderboardPage;

    // =========================================================================
    // Fixture
    // =========================================================================

    /**
     * Inicializa o ChromeDriver com a dimensão usada na gravação (1215×1097)
     * e abre a página inicial antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if ("true".equalsIgnoreCase(System.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1215,1097");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        leaderboardPage = new LeaderboardPage(driver);
        leaderboardPage.open();
        leaderboardPage.acceptCookiesIfPresent();
    }

    /** Encerra o browser após cada teste. */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =========================================================================
    // Testes
    // =========================================================================

    /**
     * <b>TC1 – Imagem do Battleship visível na grelha de jogos.</b>
     *
     * <p>Pré-condição para aceder ao leaderboard: o utilizador consegue
     * identificar e clicar no jogo Battleship a partir da página inicial.
     */
    @Test
    @Order(1)
    @DisplayName("TC1 – Imagem do Battleship visível na grelha de jogos")
    public void battleshipImageVisibleOnHomePage() {
        assertTrue(
                leaderboardPage.isBattleshipImageVisible(),
                "A imagem do Battleship (alt='Battleship') deve estar visível na "
                + "grelha de jogos da página inicial."
        );
    }

    /**
     * <b>TC2 – Secção de leaderboard e link "See all" visíveis na página do jogo.</b>
     *
     * <p>Valida o critério: «Verificar que existe uma secção de leaderboard
     * acessível a partir da página do jogo.»
     */
    @Test
    @Order(2)
    @DisplayName("TC2 – Secção de leaderboard e link 'See all' visíveis na página do jogo")
    public void leaderboardSectionExistsOnGamePage() {
        leaderboardPage.clickBattleshipImage();

        assertAll("Secção de leaderboard deve estar acessível na página do jogo",
                () -> assertTrue(
                        leaderboardPage.isLeaderboardSectionVisible(),
                        "Deve existir um componente de leaderboard visível na página do Battleship."
                ),
                () -> assertTrue(
                        leaderboardPage.isSeeAllLinkVisible(),
                        "O link 'See all' deve estar visível na secção de leaderboard."
                )
        );
    }

    /**
     * <b>TC3 – Clicar "See all" navega para a página do leaderboard.</b>
     *
     * <p>Valida que o URL após clicar "See all" contém {@code /en/t/},
     * confirmando que o leaderboard é acessível.
     */
    @Test
    @Order(3)
    @DisplayName("TC3 – 'See all' navega para URL do leaderboard (/en/t/)")
    public void seeAllNavigatesToLeaderboardPage() {
        leaderboardPage.clickBattleshipImage();
        leaderboardPage.clickSeeAll();

        String url = leaderboardPage.getCurrentUrl();
        assertTrue(
                url.contains(LeaderboardPage.LEADERBOARD_URL_FRAGMENT),
                "Após clicar 'See all', o URL deve conter '/en/t/'. URL actual: " + url
        );
    }
}
