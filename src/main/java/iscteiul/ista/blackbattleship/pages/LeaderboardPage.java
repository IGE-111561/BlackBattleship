package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class para o Leaderboard do Battleship no papergames.io.
 *
 * <p><b>User Story 12:</b> Como jogador registado, quero ver o leaderboard
 * diário da Batalha Naval para comparar a minha performance com a de outros
 * jogadores.
 *
 * <p>Localizadores obtidos da gravação Selenium IDE
 * {@code TestSuite_US12_93263.side}.
 *
 * <p>Fluxo gravado:
 * <ol>
 *   <li>Abrir {@code /en/} (janela 1215×1097)</li>
 *   <li>Clicar na imagem do Battleship ({@code //img[@alt='Battleship']})</li>
 *   <li>Clicar em "See all" na secção de leaderboard da página do jogo</li>
 *   <li>Chegar à página do leaderboard ({@code /en/t/zPiTEEyUl})</li>
 * </ol>
 *
 * @see <a href="https://papergames.io/en/battleship">papergames.io – Battleship</a>
 */
// page_url = https://papergames.io/en/
public class LeaderboardPage {

    /** URL de entrada. */
    public static final String BASE_URL = "https://papergames.io/en/";

    /** Fragmento de URL esperado na página de leaderboard. */
    public static final String LEADERBOARD_URL_FRAGMENT = "/en/t/";

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;

    // -------------------------------------------------------------------------
    // Cookie banner
    // -------------------------------------------------------------------------

    private final By cookieAcceptBtn = By.cssSelector("button.fc-cta-consent");

    // -------------------------------------------------------------------------
    // Homepage – imagem do Battleship
    // -------------------------------------------------------------------------

    /**
     * Imagem do Battleship na grelha de jogos.
     *
     * <p>CSS (gravação): {@code .game-item:nth-child(1) .img-fluid}
     * <br>XPath (gravação): {@code //img[@alt='Battleship']}
     */
    @FindBy(xpath = "//img[@alt='Battleship']")
    private WebElement battleshipImage;

    // -------------------------------------------------------------------------
    // Página do Battleship – link "See all"
    // -------------------------------------------------------------------------

    /**
     * Link "See all" na secção de leaderboard da página do Battleship.
     *
     * <p>linkText (gravação): {@code See all}
     * <br>CSS (gravação): {@code .w-100 > .text-end > .btn}
     * <br>XPath (gravação): {@code //a[contains(@href, '/en/t/zPiTEEyUl')]}
     */
    @FindBy(linkText = "See all")
    private WebElement seeAllLink;

    /**
     * Secção de leaderboard na página do Battleship – confirma que existe
     * antes de clicar "See all".
     */
    private final By leaderboardSectionLocator = By.cssSelector(
            "app-leaderboard, .leaderboard, [class*='leaderboard']"
    );

    // =========================================================================
    // Construtor
    // =========================================================================

    /**
     * Inicializa o Page Object e os campos {@link FindBy}.
     *
     * @param driver instância do WebDriver a utilizar
     */
    public LeaderboardPage(WebDriver driver) {
        this.driver    = driver;
        this.wait      = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // =========================================================================
    // Navegação
    // =========================================================================

    /** Abre a página inicial ({@code /en/}). */
    public void open() {
        driver.get(BASE_URL);
    }

    /** Aceita o banner de cookies se estiver visível. */
    public void acceptCookiesIfPresent() {
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(cookieAcceptBtn)).click();
            Thread.sleep(600);
        } catch (TimeoutException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // =========================================================================
    // Homepage → página do Battleship
    // =========================================================================

    /**
     * Verifica se a imagem do Battleship está visível na grelha de jogos.
     *
     * @return {@code true} se a imagem for visível
     */
    public boolean isBattleshipImageVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(battleshipImage));
            return battleshipImage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica na imagem do Battleship para navegar para a página do jogo.
     */
    public void clickBattleshipImage() {
        wait.until(ExpectedConditions.elementToBeClickable(battleshipImage)).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Página do Battleship – secção de leaderboard
    // =========================================================================

    /**
     * Verifica se existe uma secção de leaderboard na página do jogo.
     *
     * @return {@code true} se o componente de leaderboard for visível
     */
    public boolean isLeaderboardSectionVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(leaderboardSectionLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Verifica se o link "See all" está visível na secção de leaderboard.
     *
     * @return {@code true} se o link for visível
     */
    public boolean isSeeAllLinkVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(seeAllLink));
            return seeAllLink.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica em "See all" para navegar para a página completa do leaderboard.
     */
    public void clickSeeAll() {
        wait.until(ExpectedConditions.elementToBeClickable(seeAllLink)).click();
        try { Thread.sleep(1200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // URL
    // =========================================================================

    /**
     * Devolve o URL actual do browser.
     *
     * @return URL actual como {@code String}
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
