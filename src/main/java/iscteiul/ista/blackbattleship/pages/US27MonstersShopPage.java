package iscteiul.ista.blackbattleship.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Model para o cenário US27 - Consultar lista de Monsters na loja.
 *
 * Esta classe representa as operações necessárias para aceder à página
 * Battleship, abrir a loja e selecionar a categoria Monsters.
 */

public class US27MonstersShopPage {

    private static final String BATTLESHIP_URL = "https://papergames.io/en/battleship";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By shopMenuOption = By.xpath("//span[contains(normalize-space(),'Shop')]");
    private final By monstersCategory = By.xpath("//img[@alt='Monsters']");

    /**
     * Construtor da Page Object Class.
     *
     * @param driver instância do WebDriver usada para controlar o browser
     */
    public US27MonstersShopPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página do jogo Battleship.
     */
    public void openBattleshipPage() {
        driver.get(BATTLESHIP_URL);
    }

    /**
     * Define o tamanho da janela do browser conforme gravado no ficheiro .side.
     */
    public void setRecordedWindowSize() {
        driver.manage().window().setSize(new Dimension(1396, 737));
    }

    /**
     * Abre a página da loja através da opção Shop.
     */
    public void openShop() {
        WebElement shop = wait.until(ExpectedConditions.elementToBeClickable(shopMenuOption));
        shop.click();
    }

    /**
     * Seleciona a categoria Monsters dentro da loja.
     */
    public void openMonstersCategory() {
        WebElement monsters = wait.until(ExpectedConditions.elementToBeClickable(monstersCategory));
        monsters.click();
    }

    /**
     * Verifica se a categoria Monsters foi aberta corretamente.
     *
     * @return true se a página ou conteúdo de Monsters estiver visível
     */
    public boolean isMonstersPageVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.tagName("body")
        )).getText().toLowerCase().contains("monster");
    }
}