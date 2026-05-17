package iscteiul.ista.blackbattleship.pages.settings;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object (Selenide) para o diálogo de Settings do papergames.io.
 *
 * <p><b>User Story 18:</b> Como jogador, quero aceder a uma página de
 * <em>Settings</em> para configurar preferências como idioma e dark mode.
 *
 * <p>Localizadores obtidos da gravação {@code US18.side} (DOM Angular Material).
 *
 * @see <a href="https://papergames.io/pt-br/batalha-naval">papergames.io</a>
 */
// page_url = https://papergames.io/pt-br/batalha-naval
public class SettingsSelenidePage {

    public static final String URL = "https://papergames.io/pt-br/batalha-naval";

    private final SelenideElement cookieAcceptButton =
            $("button.fc-cta-consent");

    /** Botão de settings na barra de navegação (gravação: app-settings-menu). */
    private final SelenideElement settingsNavButton =
            $("app-header-nav:nth-child(2) app-settings-menu .mat-mdc-button-touch-target");

    /** Diálogo de settings aberto. */
    private final SelenideElement settingsDialog =
            $("mat-dialog-container app-settings-dialog");

    /** Toggle de Dark Mode (gravação: #settings-dark-mode-button). */
    private final SelenideElement darkModeToggle =
            $("#settings-dark-mode-button .mdc-switch__icons");

    /** Botão de idioma dentro do diálogo (gravação: .px-2). */
    private final SelenideElement languageButton =
            $("mat-dialog-container app-settings-dialog .px-2");

    /** Opção "English" no mat-menu de idiomas (gravação: xpath innerText). */
    private final SelenideElement englishOption =
            $x("//span[contains(.,'English')]");

    @Step("Abrir a página do Battleship")
    public void open() {
        Selenide.open(URL);
    }

    @Step("Aceitar cookies se o banner estiver presente")
    public void acceptCookiesIfPresent() {
        try {
            cookieAcceptButton.shouldBe(visible, Duration.ofSeconds(5)).click();
        } catch (Exception e) {
            // Banner não apareceu, segue
        }
    }

    @Step("Verificar se o botão de settings está visível na barra de navegação")
    public boolean isSettingsButtonVisible() {
        return settingsNavButton.is(visible);
    }

    @Step("Clicar no botão de settings para abrir o diálogo")
    public void clickSettingsButton() {
        settingsNavButton.shouldBe(visible).click();
    }

    @Step("Verificar se o diálogo app-settings-dialog está aberto")
    public boolean isSettingsDialogOpen() {
        try {
            settingsDialog.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clicar no toggle de Dark Mode")
    public void clickDarkModeToggle() {
        darkModeToggle.shouldBe(visible).click();
    }

    @Step("Clicar no botão de selecção de idioma")
    public void clickLanguageButton() {
        languageButton.shouldBe(visible).click();
    }

    @Step("Selecionar o idioma English no menu de idiomas")
    public void selectEnglish() {
        englishOption.shouldBe(visible, Duration.ofSeconds(5)).click();
    }

    @Step("Obter o URL actual do browser")
    public String getCurrentUrl() {
        return Selenide.webdriver().driver().url();
    }
}
