package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.AppStoreBadgePage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class – User Story 21: Ligações para Google Play e App Store.
 *
 * <p><b>User Story:</b><br>
 * Como jogador, quero encontrar no site as ligações para a app na Google Play
 * e App Store para poder jogar Batalha Naval no telemóvel.
 *
 * <p><b>Critérios de Aceitação verificados:</b>
 * <ol>
 *   <li>{@link #googlePlayBadgeIsVisible()} –
 *       O badge Google Play está visível na página inicial.</li>
 *   <li>{@link #googlePlayBadgeLinkPointsToPlayStore()} –
 *       O {@code href} do badge aponta para {@code play.google.com}.</li>
 *   <li>{@link #appStoreBadgeIsVisible()} –
 *       O badge App Store está visível na página inicial.</li>
 *   <li>{@link #appStoreBadgeLinkPointsToAppStore()} –
 *       O {@code href} do badge aponta para {@code apps.apple.com}.</li>
 * </ol>
 *
 * <p>Os testes verificam exclusivamente a presença e os atributos dos badges
 * na página do papergames.io. Não navegam para as lojas externas.
 *
 * @see AppStoreBadgePage
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppStoreBadgePageTest {

    private WebDriver driver;
    private AppStoreBadgePage appStoreBadgePage;

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

        appStoreBadgePage = new AppStoreBadgePage(driver);
        appStoreBadgePage.open();
        appStoreBadgePage.acceptCookiesIfPresent();
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
     * <b>TC1 – Badge Google Play visível na página inicial.</b>
     *
     * <p>Valida o critério: «Validar que na home existem botões visíveis
     * para Google Play.»
     *
     * <p>Localizador (gravação): {@code app-playstore-badge img}
     */
    @Test
    @Order(1)
    @DisplayName("TC1 – Badge Google Play visível na página inicial")
    public void googlePlayBadgeIsVisible() {
        assertTrue(
                appStoreBadgePage.isGooglePlayBadgeVisible(),
                "O badge 'Get it on Playstore' (app-playstore-badge img) deve estar "
                + "visível na página inicial."
        );
    }

    /**
     * <b>TC2 – Link do badge Google Play aponta para play.google.com.</b>
     *
     * <p>Valida o critério: «Verificar se os botões contêm as hiperligações
     * (href) corretas que abrem as fichas oficiais da aplicação nas respetivas
     * lojas.»
     */
    @Test
    @Order(2)
    @DisplayName("TC2 – href do badge Google Play aponta para play.google.com")
    public void googlePlayBadgeLinkPointsToPlayStore() {
        String href = appStoreBadgePage.getGooglePlayHref();

        assertTrue(
                href.contains(AppStoreBadgePage.GOOGLE_PLAY_DOMAIN),
                "O href do badge Google Play deve conter 'play.google.com'. "
                + "href actual: " + href
        );
    }

    /**
     * <b>TC3 – Badge App Store visível na página inicial.</b>
     *
     * <p>Valida o critério: «Validar que na home existem botões visíveis
     * para App Store.»
     *
     * <p>Localizador (gravação): {@code app-appstore-badge img}
     */
    @Test
    @Order(3)
    @DisplayName("TC3 – Badge App Store visível na página inicial")
    public void appStoreBadgeIsVisible() {
        assertTrue(
                appStoreBadgePage.isAppStoreBadgeVisible(),
                "O badge 'Get it on the AppStore' (app-appstore-badge img) deve estar "
                + "visível na página inicial."
        );
    }

    /**
     * <b>TC4 – Link do badge App Store aponta para apps.apple.com.</b>
     *
     * <p>Valida o critério: «Verificar se os botões contêm as hiperligações
     * (href) corretas que abrem as fichas oficiais da aplicação nas respetivas
     * lojas.»
     */
    @Test
    @Order(4)
    @DisplayName("TC4 – href do badge App Store aponta para apps.apple.com")
    public void appStoreBadgeLinkPointsToAppStore() {
        String href = appStoreBadgePage.getAppStoreHref();

        assertTrue(
                href.contains(AppStoreBadgePage.APP_STORE_DOMAIN),
                "O href do badge App Store deve conter 'apps.apple.com'. "
                + "href actual: " + href
        );
    }
}
