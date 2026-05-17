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
 * Page Object Class para os botões de Google Play e App Store
 * presentes na página inicial do papergames.io.
 *
 * <p><b>User Story 21:</b> Como jogador, quero encontrar no site as ligações
 * para a app na Google Play e App Store para poder jogar Batalha Naval no
 * telemóvel.
 *
 * <p>Localizadores obtidos directamente da gravação Selenium IDE
 * {@code TestSuite_US21_93263.side} (terceiro teste do ficheiro).
 *
 * <p>Fluxo gravado:
 * <ol>
 *   <li>Abrir {@code /en/} (janela 1215×1097)</li>
 *   <li>Clicar na imagem do badge Google Play
 *       ({@code app-playstore-badge img}) → abre Google Play em nova tab</li>
 *   <li>Regressar à tab original</li>
 *   <li>Clicar na imagem do badge App Store
 *       ({@code app-appstore-badge img}) → abre App Store em nova tab</li>
 * </ol>
 *
 * <p>Os testes limitam-se a verificar a <em>visibilidade</em> dos badges e
 * a validade do atributo {@code href} do link que os envolve, sem navegar
 * para as lojas externas.
 *
 * @see <a href="https://papergames.io/en/">papergames.io</a>
 */
// page_url = https://papergames.io/en/
public class AppStoreBadgePage {

    /** URL de entrada. */
    public static final String BASE_URL = "https://papergames.io/en/";

    /** Domínio esperado no href do badge Google Play. */
    public static final String GOOGLE_PLAY_DOMAIN = "play.google.com";

    /** Domínio esperado no href do badge App Store. */
    public static final String APP_STORE_DOMAIN = "apps.apple.com";

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;

    // -------------------------------------------------------------------------
    // Cookie banner
    // -------------------------------------------------------------------------

    private final By cookieAcceptBtn = By.cssSelector("button.fc-cta-consent");

    // -------------------------------------------------------------------------
    // Badge Google Play
    // -------------------------------------------------------------------------

    /**
     * Imagem do badge Google Play.
     *
     * <p>CSS (gravação): {@code app-playstore-badge img}
     * <br>XPath (gravação): {@code //img[@alt='Get it on Playstore']}
     */
    @FindBy(css = "app-playstore-badge img")
    private WebElement googlePlayBadgeImg;

    /**
     * Link ({@code <a>}) que envolve o badge Google Play – usado para ler
     * o atributo {@code href} sem clicar.
     */
    @FindBy(css = "app-playstore-badge a")
    private WebElement googlePlayBadgeLink;

    // -------------------------------------------------------------------------
    // Badge App Store
    // -------------------------------------------------------------------------

    /**
     * Imagem do badge App Store.
     *
     * <p>CSS (gravação): {@code app-appstore-badge img}
     * <br>XPath (gravação): {@code //img[@alt='Get it on the AppStore']}
     * <br>XPath posição (gravação): {@code //app-appstore-badge/a/img}
     */
    @FindBy(css = "app-appstore-badge img")
    private WebElement appStoreBadgeImg;

    /**
     * Link ({@code <a>}) que envolve o badge App Store – usado para ler
     * o atributo {@code href} sem clicar.
     */
    @FindBy(css = "app-appstore-badge a")
    private WebElement appStoreBadgeLink;

    // =========================================================================
    // Construtor
    // =========================================================================

    /**
     * Inicializa o Page Object e os campos {@link FindBy}.
     *
     * @param driver instância do WebDriver a utilizar
     */
    public AppStoreBadgePage(WebDriver driver) {
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
    // Google Play badge
    // =========================================================================

    /**
     * Verifica se a imagem do badge Google Play está visível na página.
     *
     * @return {@code true} se o badge for visível
     */
    public boolean isGooglePlayBadgeVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(googlePlayBadgeImg));
            return googlePlayBadgeImg.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Devolve o valor do atributo {@code href} do link do badge Google Play.
     *
     * @return URL de destino do badge, ou string vazia se não encontrado
     */
    public String getGooglePlayHref() {
        try {
            wait.until(ExpectedConditions.visibilityOf(googlePlayBadgeLink));
            String href = googlePlayBadgeLink.getAttribute("href");
            return href != null ? href : "";
        } catch (TimeoutException e) {
            return "";
        }
    }

    // =========================================================================
    // App Store badge
    // =========================================================================

    /**
     * Verifica se a imagem do badge App Store está visível na página.
     *
     * @return {@code true} se o badge for visível
     */
    public boolean isAppStoreBadgeVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(appStoreBadgeImg));
            return appStoreBadgeImg.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Devolve o valor do atributo {@code href} do link do badge App Store.
     *
     * @return URL de destino do badge, ou string vazia se não encontrado
     */
    public String getAppStoreHref() {
        try {
            wait.until(ExpectedConditions.visibilityOf(appStoreBadgeLink));
            String href = appStoreBadgeLink.getAttribute("href");
            return href != null ? href : "";
        } catch (TimeoutException e) {
            return "";
        }
    }
}
