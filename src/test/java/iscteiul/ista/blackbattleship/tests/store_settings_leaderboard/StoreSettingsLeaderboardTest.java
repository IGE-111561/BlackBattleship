package iscteiul.ista.blackbattleship.tests.store_settings_leaderboard;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.pages.appstore.AppStoreBadgeSelenidePage;
import iscteiul.ista.blackbattleship.pages.leaderboard.LeaderboardSelenidePage;
import iscteiul.ista.blackbattleship.pages.settings.SettingsSelenidePage;
import iscteiul.ista.blackbattleship.pages.shop.ShopSelenidePage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class (Selenide) – Test Suite: Store, Settings & Leaderboard.
 *
 * <p>Cobre as seguintes User Stories:
 * <ul>
 *   <li><b>US18</b> – Aceder às Settings para configurar idioma e dark mode.</li>
 *   <li><b>US21</b> – Encontrar os badges de Google Play e App Store.</li>
 *   <li><b>US22</b> – Aceder à loja e à página de merchandising.</li>
 *   <li><b>US12</b> – Ver o leaderboard diário da Batalha Naval.</li>
 * </ul>
 *
 * <p>Utiliza o Selenide (wrapper sobre Selenium WebDriver) com integração
 * Allure para geração de relatórios anotados com {@code @Step}.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StoreSettingsLeaderboardTest {

    @BeforeAll
    public static void configureSelenide() {
        Configuration.browser     = "chrome";
        Configuration.browserSize = "1215x1097";
        Configuration.timeout     = 10_000;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    // =========================================================================
    // US18 – Settings
    // =========================================================================

    /**
     * <b>US18 – Diálogo de Settings abre e permite mudar idioma para English.</b>
     *
     * <p>Fluxo: abrir página → clicar botão settings (app-settings-menu) →
     * confirmar que o diálogo app-settings-dialog está aberto → clicar
     * dark mode toggle → clicar botão de idioma → selecionar English →
     * confirmar URL muda para /en/.
     */
    @Test
    @Order(1)
    @DisplayName("US18 – Settings: botão visível, diálogo abre, idioma muda para English")
    public void settingsDialogOpensAndLanguageChanges() {
        SettingsSelenidePage page = new SettingsSelenidePage();
        page.open();
        page.acceptCookiesIfPresent();

        assertTrue(page.isSettingsButtonVisible(),
                "O botão de settings deve estar visível na barra de navegação.");

        page.clickSettingsButton();

        assertTrue(page.isSettingsDialogOpen(),
                "O diálogo app-settings-dialog deve abrir após clicar no botão de settings.");

        page.clickDarkModeToggle();
        page.clickLanguageButton();
        page.selectEnglish();

        assertTrue(page.getCurrentUrl().contains("/en/"),
                "Após selecionar English, o URL deve conter '/en/'. URL actual: "
                + page.getCurrentUrl());
    }

    // =========================================================================
    // US21 – App Store badges
    // =========================================================================

    /**
     * <b>US21 – Badges Google Play e App Store visíveis com hrefs correctos.</b>
     *
     * <p>Verifica a presença e os atributos {@code href} dos badges sem
     * navegar para as lojas externas.
     */
    @Test
    @Order(2)
    @DisplayName("US21 – App Store: badges visíveis e hrefs apontam para as lojas correctas")
    public void appStoreBadgesVisibleWithCorrectHrefs() {
        AppStoreBadgeSelenidePage page = new AppStoreBadgeSelenidePage();
        page.open();
        page.acceptCookiesIfPresent();

        assertTrue(page.isGooglePlayBadgeVisible(),
                "O badge Google Play (app-playstore-badge img) deve estar visível.");

        assertTrue(page.getGooglePlayHref().contains(AppStoreBadgeSelenidePage.GOOGLE_PLAY_DOMAIN),
                "O href do badge Google Play deve conter 'play.google.com'. href: "
                + page.getGooglePlayHref());

        assertTrue(page.isAppStoreBadgeVisible(),
                "O badge App Store (app-appstore-badge img) deve estar visível.");

        assertTrue(page.getAppStoreHref().contains(AppStoreBadgeSelenidePage.APP_STORE_DOMAIN),
                "O href do badge App Store deve conter 'apps.apple.com'. href: "
                + page.getAppStoreHref());
    }

    // =========================================================================
    // US22 – Shop
    // =========================================================================

    /**
     * <b>US22 – Loja: navegação, categorias, botão de compra e confirm dialog.</b>
     *
     * <p>Fluxo: abrir home → clicar "Loja" → confirmar categorias Monstros e
     * Presentes → entrar em Monstros → confirmar botões de compra → clicar
     * comprar → confirmar diálogo → clicar Confirmar → confirmar toast de
     * feedback (sem login activo).
     */
    @Test
    @Order(3)
    @DisplayName("US22 – Shop: navegação, categorias, compra e feedback de toast")
    public void shopNavigationAndPurchaseFlow() {
        ShopSelenidePage page = new ShopSelenidePage();
        page.open();
        page.acceptCookiesIfPresent();

        assertTrue(page.isLojaNavItemVisible(),
                "O item 'Loja' deve estar visível no menu de navegação.");

        page.clickLoja();

        assertAll("Categorias da loja devem estar visíveis",
                () -> assertTrue(page.isMonstrosCategoryVisible(),
                        "A categoria 'Monstros' deve estar visível na loja."),
                () -> assertTrue(page.isPresentesCategoryVisible(),
                        "A categoria 'Presentes' deve estar visível na loja.")
        );

        page.clickMonstros();

        assertTrue(page.hasBuyButtons(),
                "Devem existir botões de compra na categoria Monstros.");

        page.clickTenthProductBuyButton();

        assertTrue(page.isConfirmDialogOpen(),
                "O diálogo de confirmação de compra deve abrir.");

        page.clickConfirmar();

        assertTrue(page.isToastVisible(),
                "Um toast de feedback deve aparecer após confirmar sem sessão activa.");
    }

    /**
     * <b>US22 – Presentes abre merch.papergames.io em nova tab.</b>
     */
    @Test
    @Order(4)
    @DisplayName("US22 – Shop: 'Presentes' abre merch.papergames.io em nova tab")
    public void presentesOpensGoodieSiteInNewTab() {
        ShopSelenidePage page = new ShopSelenidePage();
        page.open();
        page.acceptCookiesIfPresent();
        page.clickLoja();

        int tabsBefore = page.getOpenTabCount();
        page.clickPresentes();

        assertEquals(tabsBefore + 1, page.getOpenTabCount(),
                "Clicar em 'Presentes' deve abrir uma nova tab.");

        page.switchToNewTab();
        assertTrue(page.getCurrentUrl().contains("merch.papergames.io"),
                "A nova tab deve abrir 'merch.papergames.io'. URL: " + page.getCurrentUrl());
    }

    // =========================================================================
    // US12 – Leaderboard
    // =========================================================================

    /**
     * <b>US12 – Leaderboard acessível a partir da página do Battleship.</b>
     *
     * <p>Fluxo: abrir home → clicar imagem Battleship → confirmar secção de
     * leaderboard e link "See all" → verificar que o href do link aponta
     * para o leaderboard ({@code /en/t/}).
     */
    @Test
    @Order(5)
    @DisplayName("US12 – Leaderboard: secção visível e href de 'See all' aponta para /en/t/")
    public void leaderboardAccessibleFromGamePage() {
        LeaderboardSelenidePage page = new LeaderboardSelenidePage();
        page.open();
        page.acceptCookiesIfPresent();

        assertTrue(page.isBattleshipImageVisible(),
                "A imagem do Battleship deve estar visível na grelha de jogos.");

        page.clickBattleshipImage();

        assertAll("Secção de leaderboard deve estar acessível",
                () -> assertTrue(page.isLeaderboardSectionVisible(),
                        "Deve existir uma secção de leaderboard na página do Battleship."),
                () -> assertTrue(page.isSeeAllLinkVisible(),
                        "O link 'See all' deve estar visível no leaderboard.")
        );

        String href = page.getSeeAllHref();
        assertTrue(href.contains(LeaderboardSelenidePage.LEADERBOARD_URL_FRAGMENT),
                "O href do link 'See all' deve conter '/en/t/'. href: " + href);
    }
}
