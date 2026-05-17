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
import java.util.ArrayList;
import java.util.List;

/**
 * Page Object Class para a Loja do papergames.io.
 *
 * <p><b>User Story 22:</b> Como jogador, quero aceder à loja ({@code /en/shop})
 * e à página de goodies ({@code merch.papergames.io}) para comprar itens
 * dentro do jogo e merchandising oficial.
 *
 * <p>Localizadores obtidos directamente da gravação Selenium IDE
 * {@code TestSuite_US22_93263.side}.
 *
 * <p>Fluxo gravado:
 * <ol>
 *   <li>Abrir {@code /pt-br/}</li>
 *   <li>Clicar em "Loja" no menu lateral de navegação</li>
 *   <li>Entrar na categoria "Monstros"</li>
 *   <li>Clicar no botão de compra do 10.º produto</li>
 *   <li>Confirmar a compra no diálogo {@code app-confirm-dialog}</li>
 *   <li>Descartar o toast de notificação</li>
 *   <li>Voltar à loja via breadcrumb</li>
 *   <li>Clicar em "Presentes" (abre {@code merch.papergames.io} em nova tab)</li>
 * </ol>
 *
 * @see <a href="https://papergames.io/pt-br/">papergames.io</a>
 */
// page_url = https://papergames.io/pt-br/
public class ShopPage {

    /** URL da página inicial usada como ponto de entrada. */
    public static final String BASE_URL = "https://papergames.io/pt-br/";

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;

    // -------------------------------------------------------------------------
    // Cookie banner
    // -------------------------------------------------------------------------

    private final By cookieAcceptBtn = By.cssSelector("button.fc-cta-consent");

    // -------------------------------------------------------------------------
    // Menu de navegação lateral – entrada para a Loja
    // -------------------------------------------------------------------------

    /**
     * Item "Loja" no menu lateral de navegação.
     *
     * <p>CSS (gravação): {@code .cdk-focused > .hide-if-collapsed}
     * <br>XPath (gravação): {@code //span[contains(.,'Loja')]}
     */
    @FindBy(xpath = "//span[contains(.,'Loja')]")
    private WebElement lojaNavItem;

    // -------------------------------------------------------------------------
    // Página da Loja – categorias
    // -------------------------------------------------------------------------

    /**
     * Imagem da categoria "Monstros" na página da loja.
     *
     * <p>XPath (gravação): {@code //img[@alt='Monstros']}
     */
    @FindBy(xpath = "//img[@alt='Monstros']")
    private WebElement monstrosImage;

    /**
     * Título/link da categoria "Presentes" na página da loja.
     * Ao clicar abre {@code merch.papergames.io} numa nova tab.
     *
     * <p>CSS (gravação): {@code .box-shadow-1:nth-child(5) h3}
     * <br>XPath (gravação): {@code //h3[contains(.,'Presentes')]}
     */
    @FindBy(xpath = "//h3[contains(.,'Presentes')]")
    private WebElement presentesLink;

    // -------------------------------------------------------------------------
    // Categoria Monstros – produtos
    // -------------------------------------------------------------------------

    /**
     * Botão de compra do 10.º produto na listagem de Monstros.
     *
     * <p>CSS (gravação): {@code .h-100:nth-child(10) .btn}
     * <br>XPath (gravação):
     * {@code //app-avatar-product[10]/div/app-shop-purchase-product-button/button}
     */
    @FindBy(css = ".h-100:nth-child(10) .btn")
    private WebElement tenthProductBuyBtn;

    /**
     * Todos os botões de compra visíveis na listagem de produtos.
     * Usado para verificar que existem itens com botão de compra.
     */
    @FindBy(css = "app-shop-purchase-product-button .btn")
    private List<WebElement> allBuyButtons;

    // -------------------------------------------------------------------------
    // Diálogo de confirmação de compra
    // -------------------------------------------------------------------------

    /**
     * Botão "Confirmar" no diálogo {@code app-confirm-dialog}.
     *
     * <p>CSS (gravação): {@code .btn-secondary:nth-child(2)}
     * <br>XPath (gravação): {@code //span[contains(.,'Confirmar')]}
     */
    @FindBy(xpath = "//span[contains(.,'Confirmar')]")
    private WebElement confirmarBtn;

    /** Contentor do diálogo de confirmação – confirma que está aberto. */
    private final By confirmDialogLocator =
            By.cssSelector("mat-dialog-container app-confirm-dialog");

    // -------------------------------------------------------------------------
    // Toast de notificação
    // -------------------------------------------------------------------------

    /**
     * Toast de notificação que aparece após tentar comprar sem sessão activa.
     *
     * <p>CSS (gravação): {@code .ng-trigger}
     * <br>XPath (gravação): {@code //div[@id='toast-container']/div}
     */
    private final By toastLocator = By.cssSelector("#toast-container .ng-trigger");

    // -------------------------------------------------------------------------
    // Breadcrumb – regresso à Loja
    // -------------------------------------------------------------------------

    /**
     * Link "Loja" no breadcrumb para regressar à listagem de categorias.
     *
     * <p>CSS (gravação): {@code .xng-breadcrumb-link}
     * <br>XPath (gravação): {@code //a[contains(text(),'Loja')]}
     */
    @FindBy(css = ".xng-breadcrumb-link")
    private WebElement lojaBreadcrumb;

    // =========================================================================
    // Construtor
    // =========================================================================

    /**
     * Inicializa o Page Object e os campos {@link FindBy}.
     *
     * @param driver instância do WebDriver a utilizar
     */
    public ShopPage(WebDriver driver) {
        this.driver    = driver;
        this.wait      = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // =========================================================================
    // Navegação
    // =========================================================================

    /** Abre a página inicial. */
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
    // Menu de navegação – Loja
    // =========================================================================

    /**
     * Verifica se o item "Loja" está visível no menu lateral.
     *
     * @return {@code true} se o item "Loja" for visível
     */
    public boolean isLojaNavItemVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(lojaNavItem));
            return lojaNavItem.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica em "Loja" no menu lateral de navegação.
     */
    public void clickLoja() {
        wait.until(ExpectedConditions.elementToBeClickable(lojaNavItem)).click();
        try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Página da Loja – categorias
    // =========================================================================

    /**
     * Verifica se a categoria "Monstros" está visível na página da loja.
     *
     * @return {@code true} se a imagem "Monstros" for visível
     */
    public boolean isMonstrosCategoryVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(monstrosImage));
            return monstrosImage.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica na categoria "Monstros".
     */
    public void clickMonstros() {
        wait.until(ExpectedConditions.elementToBeClickable(monstrosImage)).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    /**
     * Verifica se a categoria "Presentes" está visível na página da loja.
     *
     * @return {@code true} se o link "Presentes" for visível
     */
    public boolean isPresentesCategoryVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(presentesLink));
            return presentesLink.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica em "Presentes". Este clique abre {@code merch.papergames.io}
     * numa nova tab do browser.
     */
    public void clickPresentes() {
        wait.until(ExpectedConditions.elementToBeClickable(presentesLink)).click();
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Categoria Monstros – produtos
    // =========================================================================

    /**
     * Verifica se existem botões de compra visíveis na listagem de Monstros.
     *
     * @return {@code true} se pelo menos um botão de compra estiver visível
     */
    public boolean hasBuyButtons() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(allBuyButtons));
            return !allBuyButtons.isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica no botão de compra do 10.º produto da listagem de Monstros.
     */
    public void clickTenthProductBuyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(tenthProductBuyBtn)).click();
        try { Thread.sleep(600); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Diálogo de confirmação
    // =========================================================================

    /**
     * Verifica se o diálogo de confirmação de compra está aberto.
     *
     * @return {@code true} se {@code app-confirm-dialog} for visível
     */
    public boolean isConfirmDialogOpen() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDialogLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica no botão "Confirmar" no diálogo de confirmação.
     */
    public void clickConfirmar() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmarBtn)).click();
        try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Toast de notificação
    // =========================================================================

    /**
     * Verifica se o toast de notificação apareceu (esperado quando o utilizador
     * não tem sessão activa e tenta confirmar a compra).
     *
     * @return {@code true} se o toast for visível
     */
    public boolean isToastVisible() {
        try {
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Clica no toast para o descartar.
     */
    public void dismissToast() {
        try {
            WebElement toast = shortWait.until(
                    ExpectedConditions.elementToBeClickable(toastLocator));
            toast.click();
            Thread.sleep(500);
        } catch (TimeoutException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // =========================================================================
    // Breadcrumb
    // =========================================================================

    /**
     * Clica no link "Loja" no breadcrumb para regressar à listagem de
     * categorias da loja.
     */
    public void clickLojaBreadcrumb() {
        wait.until(ExpectedConditions.elementToBeClickable(lojaBreadcrumb)).click();
        try { Thread.sleep(800); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    // =========================================================================
    // Gestão de tabs (merch.papergames.io)
    // =========================================================================

    /**
     * Muda o foco do WebDriver para a tab mais recentemente aberta.
     * Usado após {@link #clickPresentes()} para interagir com a nova tab.
     */
    public void switchToNewTab() {
        List<String> handles = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(handles.size() - 1));
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    /**
     * Devolve o URL actual do browser.
     *
     * @return URL actual como {@code String}
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Devolve o número de tabs/janelas abertas actualmente.
     *
     * @return número de handles de janela
     */
    public int getOpenTabCount() {
        return driver.getWindowHandles().size();
    }
}
