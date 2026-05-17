package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

/**
 * Page Object Model para a US05 - Disparar contra o tabuleiro adversário.
 *
 * Esta classe contém os métodos necessários para abrir o jogo Batalha Naval,
 * iniciar uma partida contra o robot e disparar numa célula do tabuleiro.
 */
public class US05DispararTabuleiroPage {

    private static final String HOME_URL = "https://papergames.io/en/";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By battleshipGame = By.xpath("//img[@alt='Battleship']");
    private final By playVsRobotButton = By.cssSelector(".fa-robot");
    private final By targetCell = By.cssSelector(".target");
    private final By hoverCell = By.cssSelector(".hover");
    private final By hoverCircle = By.cssSelector(".hover > .circle");

    /**
     * Construtor da Page Object Class.
     *
     * @param driver instância do WebDriver usada para controlar o browser
     */
    public US05DispararTabuleiroPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página inicial do site.
     */
    public void openHomePage() {
        driver.get(HOME_URL);
    }

    /**
     * Define o tamanho da janela conforme gravado no ficheiro .side.
     */
    public void setRecordedWindowSize() {
        driver.manage().window().setSize(new Dimension(947, 700));
    }

    /**
     * Seleciona o jogo Batalha Naval na página inicial.
     */
    public void selectBattleshipGame() {
        WebElement game = wait.until(ExpectedConditions.elementToBeClickable(battleshipGame));
        game.click();
    }

    /**
     * Inicia uma partida contra o robot.
     */
    public void startGameAgainstRobot() {
        WebElement robot = wait.until(ExpectedConditions.elementToBeClickable(playVsRobotButton));
        robot.click();
    }

    /**
     * Move o rato sobre uma célula jogável, simulando interação do utilizador.
     */
    public void hoverPlayableCell() {
        WebElement cell = wait.until(ExpectedConditions.visibilityOfElementLocated(hoverCell));
        new Actions(driver).moveToElement(cell).perform();
    }

    /**
     * Clica numa célula do tabuleiro adversário para disparar.
     */
    public void shootTargetCell() {
        WebElement cell = wait.until(ExpectedConditions.elementToBeClickable(targetCell));
        cell.click();
    }

    /**
     * Clica numa célula atualmente em estado hover.
     */
    public void shootHoverCell() {
        WebElement cell = wait.until(ExpectedConditions.elementToBeClickable(hoverCell));
        cell.click();
    }

    /**
     * Clica no círculo visível dentro de uma célula em hover, quando disponível.
     */
    public void shootHoverCircleIfPresent() {
        try {
            WebElement circle = wait.until(ExpectedConditions.elementToBeClickable(hoverCircle));
            circle.click();
        } catch (TimeoutException ignored) {
            // Se o círculo não estiver disponível, o teste pode continuar.
        }
    }

    /**
     * Verifica se existem células do tabuleiro disponíveis.
     *
     * @return true se forem encontrados elementos do tabuleiro
     */
    public boolean hasPlayableBoardCells() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector(".target, .hover, .circle, [class*='cell'], [class*='board']")
            ));

            return !driver.findElements(
                    By.cssSelector(".target, .hover, .circle, [class*='cell'], [class*='board']")
            ).isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }
}