package iscteiul.ista.blackbattleship.pages.leaderboard;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object (Selenide) para o Leaderboard do Battleship no papergames.io.
 *
 * <p><b>User Story 12:</b> Como jogador registado, quero ver o leaderboard
 * diário da Batalha Naval para comparar a minha performance com outros jogadores.
 *
 * <p>Localizadores obtidos da gravação {@code TestSuite_US12_93263.side}.
 * Janela gravada: 1215×1097 (desktop).
 *
 * @see <a href="https://papergames.io/en/">papergames.io</a>
 */
// page_url = https://papergames.io/en/
public class LeaderboardSelenidePage {

    public static final String BASE_URL                = "https://papergames.io/en/";
    public static final String LEADERBOARD_URL_FRAGMENT = "/en/t/";

    private final SelenideElement cookieAcceptButton =
            $("button.fc-cta-consent");

    /**
     * Imagem do Battleship na grelha de jogos.
     * CSS (gravação): {@code .game-item:nth-child(1) .img-fluid}
     * XPath (gravação): {@code //img[@alt='Battleship']}
     */
    private final SelenideElement battleshipImage =
            $x("//img[@alt='Battleship']");

    /**
     * Link "See all" na secção de leaderboard.
     * linkText (gravação): {@code See all}
     * CSS (gravação): {@code .w-100 > .text-end > .btn}
     */
    private final SelenideElement seeAllLink =
            $(By.linkText("See all"));

    @Step("Abrir a página inicial do papergames.io")
    public void open() {
        Selenide.open(BASE_URL);
    }

    @Step("Aceitar cookies se o banner estiver presente")
    public void acceptCookiesIfPresent() {
        try {
            cookieAcceptButton.shouldBe(visible, Duration.ofSeconds(5)).click();
        } catch (Exception e) {
            // Banner não apareceu, segue
        }
    }

    @Step("Verificar se a imagem do Battleship está visível na grelha de jogos")
    public boolean isBattleshipImageVisible() {
        try {
            battleshipImage.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clicar na imagem do Battleship para aceder à página do jogo")
    public void clickBattleshipImage() {
        battleshipImage.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    /**
     * Verifica se a secção de leaderboard existe na página do Battleship,
     * usando o link "See all" como indicador (gravação: .w-100 > .text-end > .btn).
     */
    @Step("Verificar se existe uma secção de leaderboard na página do jogo")
    public boolean isLeaderboardSectionVisible() {
        try {
            seeAllLink.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verificar se o link 'See all' está visível no leaderboard")
    public boolean isSeeAllLinkVisible() {
        try {
            seeAllLink.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Obter o href do link 'See all' (verifica destino sem navegar)")
    public String getSeeAllHref() {
        SelenideElement link = $(By.linkText("See all"))
                .shouldBe(visible, Duration.ofSeconds(10));
        String href = link.getAttribute("href");
        return href != null ? href : "";
    }

    @Step("Clicar em 'See all' para ver o leaderboard completo")
    public void clickSeeAll() {
        SelenideElement link = $(By.linkText("See all"))
                .shouldBe(visible, Duration.ofSeconds(10));
        Selenide.executeJavaScript("arguments[0].click();", link.toWebElement());
    }

    @Step("Obter o URL actual do browser")
    public String getCurrentUrl() {
        return Selenide.webdriver().driver().url();
    }
}
