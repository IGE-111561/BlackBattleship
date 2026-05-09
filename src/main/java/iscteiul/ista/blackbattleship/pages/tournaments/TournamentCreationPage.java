package iscteiul.ista.blackbattleship.pages.tournaments;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object para a página de criação de torneios em
 * <a href="https://papergames.io/en/t/create-tournament">papergames.io/en/t/create-tournament</a>.
 *
 * <p>Esta página apresenta um formulário público para criação de torneios,
 * implementado com componentes Angular Material (mat-select, mat-input).
 * O botão "Create and share" só fica ativo quando todos os campos obrigatórios
 * são preenchidos. A submissão efetiva exige autenticação, pelo que clicar
 * no botão dispara o diálogo de login.</p>
 *
 * <p>Campos obrigatórios:</p>
 * <ul>
 *   <li>Game (mat-select) — vazio por defeito</li>
 *   <li>Mode (mat-select) — preenchido por defeito ("Leaderboard")</li>
 *   <li>Tournament name (input) — vazio por defeito</li>
 *   <li>Matchs per round (mat-select) — preenchido por defeito ("Best of 3")</li>
 *   <li>Minutes per player (mat-select) — vazio por defeito</li>
 *   <li>Penalty points (mat-select) — preenchido por defeito ("15 pts")</li>
 * </ul>
 */
public class TournamentCreationPage {

    public static final String URL = "https://papergames.io/en/t/create-tournament";

    private final SelenideElement pageTitle = $x("//h1[normalize-space()='Tournament creation']");
    private final SelenideElement gameSelect = $("mat-select[formcontrolname='type']");
    private final SelenideElement timerSelect = $("mat-select[formcontrolname='timerTimeout']");
    private final SelenideElement tournamentNameInput = $("input[formcontrolname='name']");
    private final SelenideElement createAndShareButton = $x("//button[normalize-space()='Create and share']");
    private final SelenideElement cookieAcceptButton = $("button.fc-cta-consent");

    @Step("Abrir página de criação de torneio")
    public void open() {
        Selenide.open(URL);
    }

    @Step("Aceitar cookies se o banner estiver presente")
    public void acceptCookiesIfPresent() {
        try {
            cookieAcceptButton.shouldBe(visible, Duration.ofSeconds(10)).click();
        } catch (Throwable e) {
            // Banner não apareceu, segue
        }
    }

    @Step("Obter texto do título da página")
    public String getPageTitleText() {
        return pageTitle.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    @Step("Verificar se o formulário de criação de torneio está visível")
    public boolean isFormVisible() {
        return tournamentNameInput.is(visible) && createAndShareButton.is(visible);
    }

    @Step("Selecionar jogo: {gameName}")
    public void selectGame(String gameName) {
        gameSelect.shouldBe(visible).click();
        // O dropdown abre num overlay ao nível do <body>; selecionar a mat-option pelo texto
        $x("//mat-option//div[contains(@class, 'fw-bold') and normalize-space()='" + gameName + "']")
                .shouldBe(visible, Duration.ofSeconds(5))
                .click();
    }

    @Step("Selecionar tempo por jogador (primeira opção disponível)")
    public void selectFirstAvailableTimer() {
        timerSelect.shouldBe(visible).click();
        // Selecionar a primeira opção disponível no dropdown aberto
        $x("(//div[contains(@class, 'mat-mdc-select-panel')]//mat-option)[1]")
                .shouldBe(visible, Duration.ofSeconds(5))
                .click();
    }

    @Step("Preencher nome do torneio: {name}")
    public void fillTournamentName(String name) {
        tournamentNameInput.shouldBe(visible).setValue(name);
    }

    @Step("Preencher campos obrigatórios do formulário")
    public void fillRequiredFields(String gameName, String tournamentName) {
        selectGame(gameName);
        fillTournamentName(tournamentName);
        selectFirstAvailableTimer();
    }

    @Step("Verificar se o botão 'Create and share' está ativo")
    public boolean isSubmitButtonEnabled() {
        return createAndShareButton.is(enabled);
    }

    @Step("Submeter criação do torneio")
    public void submitTournamentCreation() {
        createAndShareButton.shouldBe(enabled, Duration.ofSeconds(5)).click();
    }
}