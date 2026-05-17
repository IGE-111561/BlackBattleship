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
 * Page Object Class que representa a página inicial do jogo de Batalha Naval
 * em <a href="https://papergames.io/en/battleship">papergames.io</a>.
 *
 * <p>Esta classe segue o padrão Page Object Model (POM) e Page Factory,
 * encapsulando os localizadores CSS/XPath dos elementos da página e expondo
 * métodos de interação de alto nível para serem usados pelas classes de teste.</p>
 *
 * <p>Os elementos cobertos incluem:
 * <ul>
 *   <li>Título da página e botões dos modos de jogo</li>
 *   <li>Banner de cookies (Funding Choices)</li>
 *   <li>Diálogo de introdução de nickname</li>
 *   <li>Sala de jogo contra robot e página de partilha de link</li>
 * </ul>
 * </p>
 *
 * @see BattleshipHomePageTest
 */
public class BattleshipHomePage {

    /** URL da página inicial do jogo de Batalha Naval. */
    public static final String URL = "https://papergames.io/en/battleship";

    /** WebDriver utilizado para interagir com o browser. */
    private final WebDriver driver;

    /** Espera explícita partilhada por todos os métodos da página. */
    private final WebDriverWait wait;

    /** Título principal da página ({@code <h1>}). */
    @FindBy(css = "h1.area-title")
    private WebElement pageTitle;

    /** Botão para iniciar uma partida contra o robot. */
    @FindBy(xpath = "//span[normalize-space()='Play vs robot']")
    private WebElement playVsRobotButton;

    /** Botão para iniciar uma partida privada contra um amigo. */
    @FindBy(xpath = "//span[normalize-space()='Play with a friend']")
    private WebElement playWithFriendButton;

    /** Título do diálogo modal "Who are you?" que pede o nickname. */
    @FindBy(xpath = "//h2[normalize-space()='Who are you?']")
    private WebElement nicknameDialogTitle;

    /** Campo de input para introduzir o nickname do jogador. */
    @FindBy(css = "input[formcontrolname='username']")
    private WebElement nicknameInput;

    /** Botão "Continue" do diálogo de nickname. */
    @FindBy(css = "footer button[type='submit']")
    private WebElement nicknameContinueButton;

    /** Localizador do botão de aceitação do banner de cookies. */
    private final By cookieAcceptButton = By.cssSelector("button.fc-cta-consent");

    /** Localizador do contentor do diálogo de cookies. */
    private final By cookieDialog = By.cssSelector("div.fc-dialog, div.fc-consent-root");

    /**
     * Construtor da Page Object. Inicializa os elementos web através do
     * {@link PageFactory} e configura o tempo de espera explícito padrão.
     *
     * @param driver instância do WebDriver associada ao browser ativo
     */
    public BattleshipHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Navega para a URL da página inicial do jogo.
     */
    public void open() {
        driver.get(URL);
    }

    /**
     * Aceita o banner de cookies caso esteja presente. Se o banner não
     * aparecer dentro de 5 segundos, a execução prossegue normalmente,
     * permitindo reutilização do método em qualquer ponto do fluxo.
     */
    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
        } catch (TimeoutException e) {
            // Banner não apareceu: comportamento aceitável.
        }
    }

    /**
     * Aceita o banner de cookies, esperando que o botão "Consent" esteja clicável.
     * Lança exceção caso o banner não exista.
     */
    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton)).click();
    }

    /**
     * Verifica se o banner de cookies está atualmente visível na página.
     *
     * @return {@code true} se o banner estiver visível, {@code false} caso contrário
     */
    public boolean isCookieBannerVisible() {
        try {
            return driver.findElement(cookieDialog).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o texto do título principal da página.
     *
     * @return texto do {@code <h1>} principal (ex: "Battleship Online")
     */
    public String getPageTitleText() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    /**
     * Verifica se o botão "Play vs robot" está visível.
     *
     * @return {@code true} se o botão estiver visível
     */
    public boolean isPlayVsRobotVisible() {
        return playVsRobotButton.isDisplayed();
    }

    /**
     * Verifica se o botão "Play with a friend" está visível.
     *
     * @return {@code true} se o botão estiver visível
     */
    public boolean isPlayWithFriendVisible() {
        return playWithFriendButton.isDisplayed();
    }

    /**
     * Clica no botão "Play vs robot" para iniciar o fluxo de partida contra o robot.
     */
    public void clickPlayVsRobot() {
        wait.until(ExpectedConditions.elementToBeClickable(playVsRobotButton)).click();
    }

    /**
     * Clica no botão "Play with a friend" para iniciar o fluxo de partida privada.
     */
    public void clickPlayWithFriend() {
        wait.until(ExpectedConditions.elementToBeClickable(playWithFriendButton)).click();
    }

    /**
     * Verifica se o diálogo modal de pedido de nickname está visível.
     *
     * @return {@code true} se o diálogo "Who are you?" estiver visível com o input pronto
     */
    public boolean isNicknameDialogVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOf(nicknameDialogTitle));
            return nicknameDialogTitle.isDisplayed() && nicknameInput.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Insere um nickname no diálogo modal e submete o formulário.
     *
     * @param nickname nickname a introduzir (mínimo 3 caracteres, máximo 30)
     */
    public void submitNickname(String nickname) {
        wait.until(ExpectedConditions.visibilityOf(nicknameInput));
        nicknameInput.sendKeys(nickname);
        wait.until(ExpectedConditions.elementToBeClickable(nicknameContinueButton)).click();
    }

    /**
     * Verifica se o jogador foi colocado na sala de jogo contra o robot
     * "Paper Man", validando a presença do nome do adversário no UI.
     *
     * @return {@code true} se o oponente "Paper Man" estiver visível
     */
    public boolean isPlayingAgainstRobot() {
        try {
            WebElement opponent = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[normalize-space()='Paper Man']")
            ));
            return opponent.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Verifica se a página de partilha de link partilhável (modo "Play with a friend")
     * está visível, validando a presença da mensagem "Share this link with a friend".
     *
     * @return {@code true} se a página de partilha estiver visível
     */
    public boolean isShareLinkPageVisible() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[normalize-space()='Share this link with a friend']")
            ));
            return title.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Obtém o link partilhável gerado para convidar um amigo para a partida.
     *
     * @return URL completa começando por {@code https://papergames.io/en/r/...},
     *         ou string vazia se o elemento não estiver acessível
     */
    public String getShareableLink() {
        try {
            WebElement linkElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("app-copy-text span")
            ));
            return linkElement.getText();
        } catch (TimeoutException e) {
            return "";
        }
    }
}