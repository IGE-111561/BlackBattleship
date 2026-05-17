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
 * Page Object Class para o diálogo de Settings do papergames.io.
 *
 * <p><b>User Story 18:</b> Como jogador, quero aceder a uma página de
 * <em>Settings</em> para configurar preferências como idioma e dark mode.
 *
 * <p>Os localizadores foram obtidos directamente da gravação Selenium IDE
 * {@code US18.side} e reflectem o DOM real da aplicação Angular Material.
 *
 * <p>Fluxo gravado:
 * <ol>
 *   <li>Abrir {@code /pt-br/batalha-naval}</li>
 *   <li>Clicar no botão {@code app-settings-menu} na barra de navegação</li>
 *   <li>Clicar no toggle de Dark Mode ({@code #settings-dark-mode-button})</li>
 *   <li>Clicar no botão de idioma ({@code .px-2}) dentro do diálogo</li>
 *   <li>Selecionar "English" no menu de idiomas</li>
 * </ol>
 *
 * @see <a href="https://papergames.io/pt-br/batalha-naval">papergames.io</a>
 */
// page_url = https://papergames.io/pt-br/batalha-naval
public class SettingsPage {

    /** URL de entrada para todos os testes desta User Story. */
    public static final String BASE_URL = "https://papergames.io/pt-br/batalha-naval";

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;

    // -------------------------------------------------------------------------
    // Cookie banner
    // -------------------------------------------------------------------------

    /** Botão de aceitar cookies (Funding Choices). */
    private final By cookieAcceptBtn = By.cssSelector("button.fc-cta-consent");

    // -------------------------------------------------------------------------
    // Barra de navegação – botão de settings
    // -------------------------------------------------------------------------

    /**
     * Botão de abertura do diálogo de settings na barra de navegação.
     *
     * <p>CSS (gravação): {@code app-header-nav:nth-child(2) app-settings-menu
     * .mat-mdc-button-touch-target}
     * <br>XPath (gravação): {@code //app-settings-menu/button/span[3]}
     */
    @FindBy(css = "app-header-nav:nth-child(2) app-settings-menu .mat-mdc-button-touch-target")
    private WebElement settingsNavButton;

    // -------------------------------------------------------------------------
    // Diálogo de settings
    // -------------------------------------------------------------------------

    /**
     * Contentor do diálogo Angular Material – confirma que o diálogo abriu.
     */
    private final By settingsDialogLocator =
            By.cssSelector("mat-dialog-container app-settings-dialog");

    /**
     * Ícone/área clicável do toggle de Dark Mode.
     *
     * <p>CSS (gravação): {@code #settings-dark-mode-button .mdc-switch__icons}
     */
    @FindBy(css = "#settings-dark-mode-button .mdc-switch__icons")
    private WebElement darkModeToggleClickTarget;

    /**
     * Elemento raiz do toggle de Dark Mode – leitura do atributo
     * {@code aria-checked} para determinar o estado actual.
     *
     * <p>CSS: {@code #settings-dark-mode-button}
     */
    @FindBy(id = "settings-dark-mode-button")
    private WebElement darkModeToggleRoot;

    /**
     * Botão de selecção de idioma dentro do diálogo de settings.
     *
     * <p>CSS (gravação): {@code mat-dialog-container app-settings-dialog .px-2}
     * <br>XPath (gravação):
     * {@code //mat-dialog-container/…/app-settings-dialog/…/div[5]/button}
     */
    @FindBy(css = "mat-dialog-container app-settings-dialog .px-2")
    private WebElement languageButton;

    // -------------------------------------------------------------------------
    // Menu de idiomas (mat-menu)
    // -------------------------------------------------------------------------

    /**
     * Opção "English" no menu de idiomas.
     *
     * <p>XPath (gravação): {@code //span[contains(.,'English')]}
     */
    private final By englishOptionLocator =
            By.xpath("//span[contains(.,'English')]");

    /**
     * Painel do mat-menu de idiomas – confirma que o menu está aberto.
     */
    private final By languageMenuPanelLocator =
            By.cssSelector("div[id^='mat-menu-panel']");

    // =========================================================================
    // Construtor
    // =========================================================================

    /**
     * Inicializa o Page Object e os campos {@link FindBy}.
     *
     * @param driver instância do WebDriver a utilizar
     */
    public SettingsPage(WebDriver driver) {
        this.driver    = driver;
        this.wait      = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // =========================================================================
    // Navegação
    // =========================================================================

    /** Abre o URL base no browser. */
    public void open() {
        driver.get(BASE_URL);
    }

    /**
     * Aceita o banner de cookies se estiver visível; não faz nada caso contrário.
     */
    public void acceptCookiesIfPresent() {
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(cookieAcceptBtn)).click();
            Thread.sleep(600);
        } catch (TimeoutException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // =========================================================================
    // Botão de settings na nav
    // =========================================================================

    /**
     * Verifica se o botão de settings é visível na barra de navegação.
     *
     * @return {@code true} se o botão estiver visível
     */
    public boolean isSettingsButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(settingsNavButton));
            return settingsNavButton.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica no botão de settings para abrir o diálogo de configurações.
     */
    public void clickSettingsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(settingsNavButton)).click();
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Diálogo de settings
    // =========================================================================

    /**
     * Verifica se o diálogo de settings está aberto.
     *
     * @return {@code true} se {@code app-settings-dialog} estiver visível
     */
    public boolean isSettingsDialogOpen() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(settingsDialogLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    // =========================================================================
    // Dark Mode toggle
    // =========================================================================

    /**
     * Verifica se o toggle de Dark Mode está visível no diálogo.
     *
     * @return {@code true} se o toggle estiver presente
     */
    public boolean isDarkModeToggleVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(darkModeToggleClickTarget));
            return darkModeToggleClickTarget.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Lê o estado actual do Dark Mode através do atributo {@code aria-checked}.
     *
     * @return {@code true} se o Dark Mode estiver activo
     */
    public boolean isDarkModeEnabled() {
        wait.until(ExpectedConditions.visibilityOf(darkModeToggleRoot));
        return "true".equals(darkModeToggleRoot.getAttribute("aria-checked"));
    }

    /**
     * Clica no toggle de Dark Mode, invertendo o seu estado.
     */
    public void clickDarkModeToggle() {
        wait.until(ExpectedConditions.elementToBeClickable(darkModeToggleClickTarget)).click();
        try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Selector de idioma
    // =========================================================================

    /**
     * Verifica se o botão de selecção de idioma está visível no diálogo.
     *
     * @return {@code true} se o botão de idioma estiver presente
     */
    public boolean isLanguageButtonVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(languageButton));
            return languageButton.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica no botão de idioma para abrir o mat-menu de opções de idioma.
     */
    public void clickLanguageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(languageButton)).click();
        try { Thread.sleep(400); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    /**
     * Verifica se o painel de selecção de idioma está aberto.
     *
     * @return {@code true} se o mat-menu de idiomas estiver visível
     */
    public boolean isLanguageMenuOpen() {
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(languageMenuPanelLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Selecciona a opção "English" no menu de idiomas.
     * Pressupõe que {@link #clickLanguageButton()} foi chamado previamente.
     */
    public void selectEnglish() {
        wait.until(ExpectedConditions.elementToBeClickable(englishOptionLocator)).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Persistência / URL
    // =========================================================================

    /**
     * Devolve o URL actual – usado para verificar a mudança de locale após
     * alteração de idioma (ex.: {@code /pt-br/} → {@code /en/}).
     *
     * @return URL actual como {@code String}
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Recarrega a página (equivalente a F5) para validar a persistência
     * das preferências entre recarregamentos.
     */
    public void reloadPage() {
        driver.navigate().refresh();
        try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
