package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.SettingsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class – User Story 18: Configurar preferências em Settings.
 *
 * <p><b>User Story:</b><br>
 * Como jogador, quero aceder a uma página de <em>Settings</em> para
 * configurar preferências como idioma e dark mode.
 *
 * <p><b>Critérios de Aceitação verificados:</b>
 * <ol>
 *   <li>{@link #settingsButtonIsVisibleInNav()} –
 *       O botão de settings existe e é visível na barra de navegação.</li>
 *   <li>{@link #settingsDialogOpensOnClick()} –
 *       Clicar no botão abre o diálogo {@code app-settings-dialog}.</li>
 *   <li>{@link #darkModeToggleIsPresentInDialog()} –
 *       O toggle de Dark Mode está presente no diálogo.</li>
 *   <li>{@link #darkModeToggleChangesState()} –
 *       Clicar no toggle inverte o seu estado (on ↔ off).</li>
 *   <li>{@link #languageMenuOpensAndContainsEnglish()} –
 *       O botão de idioma abre um menu que contém a opção "English".</li>
 *   <li>{@link #selectingEnglishChangesLocaleInUrl()} –
 *       Selecionar "English" navega para o locale {@code /en/} (persistência
 *       de idioma reflectida no URL).</li>
 * </ol>
 *
 * <p>Cada teste é totalmente independente: {@code setUp} abre o browser e
 * {@code tearDown} fecha-o, garantindo isolamento entre testes.
 *
 * @see SettingsPage
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SettingsPageTest {

    private WebDriver driver;
    private SettingsPage settingsPage;

    /**
     * Inicializa o ChromeDriver e abre a página antes de cada teste.
     * Corre em modo headless se a propriedade de sistema {@code headless=true}.
     */
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if ("true".equalsIgnoreCase(System.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1041,694"); // dimensão usada na gravação

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        settingsPage = new SettingsPage(driver);
        settingsPage.open();
        settingsPage.acceptCookiesIfPresent();
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
     * <b>TC1 – Botão de settings visível na barra de navegação.</b>
     *
     * <p>Valida que o componente {@code app-settings-menu} está renderizado e
     * visível assim que a página carrega, sem qualquer interacção adicional.
     */
    @Test
    @Order(1)
    @DisplayName("TC1 – Botão de settings visível na barra de navegação")
    public void settingsButtonIsVisibleInNav() {
        assertTrue(
                settingsPage.isSettingsButtonVisible(),
                "O botão 'app-settings-menu' deve estar visível na barra de navegação "
                + "sem necessidade de qualquer interacção prévia."
        );
    }

    /**
     * <b>TC2 – Diálogo de settings abre ao clicar no botão.</b>
     *
     * <p>Simula o clique gravado no Selenium IDE e verifica que o
     * {@code mat-dialog-container} com {@code app-settings-dialog} fica visível.
     */
    @Test
    @Order(2)
    @DisplayName("TC2 – Clicar no botão abre o diálogo app-settings-dialog")
    public void settingsDialogOpensOnClick() {
        settingsPage.clickSettingsButton();

        assertTrue(
                settingsPage.isSettingsDialogOpen(),
                "Após clicar no botão de settings, o diálogo 'app-settings-dialog' "
                + "deve ficar visível dentro de um 'mat-dialog-container'."
        );
    }

    /**
     * <b>TC3 – Toggle de Dark Mode está presente no diálogo.</b>
     *
     * <p>Abre o diálogo e verifica que o elemento
     * {@code #settings-dark-mode-button .mdc-switch__icons} existe e é visível.
     */
    @Test
    @Order(3)
    @DisplayName("TC3 – Toggle de Dark Mode presente no diálogo de settings")
    public void darkModeToggleIsPresentInDialog() {
        settingsPage.clickSettingsButton();

        assertTrue(
                settingsPage.isDarkModeToggleVisible(),
                "O toggle '#settings-dark-mode-button' deve estar visível no diálogo "
                + "de settings."
        );
    }

    /**
     * <b>TC4 – Clicar no toggle de Dark Mode inverte o seu estado.</b>
     *
     * <p>Lê o estado inicial via {@code aria-checked}, clica no toggle
     * (fluxo gravado no Selenium IDE) e verifica que o estado mudou.
     */
    @Test
    @Order(4)
    @DisplayName("TC4 – Toggle de Dark Mode inverte o estado ao ser clicado")
    public void darkModeToggleChangesState() {
        settingsPage.clickSettingsButton();

        boolean before = settingsPage.isDarkModeEnabled();
        settingsPage.clickDarkModeToggle();
        boolean after = settingsPage.isDarkModeEnabled();

        assertNotEquals(
                before, after,
                "Após clicar no toggle de Dark Mode, o atributo 'aria-checked' deve "
                + "ter mudado. Estado antes: " + before + " | Estado depois: " + after
        );
    }

    /**
     * <b>TC5 – Menu de idiomas abre e contém a opção "English".</b>
     *
     * <p>Clica no botão de idioma ({@code .px-2}) e verifica que o
     * mat-menu fica visível com a opção "English" disponível.
     */
    @Test
    @Order(5)
    @DisplayName("TC5 – Clicar no botão de idioma abre o menu com a opção English")
    public void languageMenuOpensAndContainsEnglish() {
        settingsPage.clickSettingsButton();
        settingsPage.clickLanguageButton();

        assertTrue(
                settingsPage.isLanguageMenuOpen(),
                "Após clicar no botão de idioma ('.px-2'), o mat-menu de idiomas deve "
                + "ficar visível."
        );
    }

    /**
     * <b>TC6 – Selecionar "English" muda o locale no URL.</b>
     *
     * <p>Reproduz o fluxo completo da gravação: abre settings → clica Dark Mode
     * → clica botão de idioma → selecciona "English". Verifica que o URL passa
     * de {@code /pt-br/} para {@code /en/}, confirmando que a preferência de
     * idioma é persistida pelo site.
     */
    @Test
    @Order(6)
    @DisplayName("TC6 – Selecionar English muda o locale do URL de /pt-br/ para /en/")
    public void selectingEnglishChangesLocaleInUrl() {
        settingsPage.clickSettingsButton();
        settingsPage.clickDarkModeToggle(); // passo gravado antes da mudança de idioma
        settingsPage.clickLanguageButton();
        settingsPage.selectEnglish();

        String currentUrl = settingsPage.getCurrentUrl();

        assertTrue(
                currentUrl.contains("/en/"),
                "Após selecionar 'English', o URL deve conter '/en/'. URL actual: "
                + currentUrl
        );
    }
}
