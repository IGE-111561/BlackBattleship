package iscteiul.ista.blackbattleship.pages.shared;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object para o diálogo de autenticação ("Welcome back!") apresentado pelo
 * site papergames.io quando o utilizador tenta aceder a funcionalidades que
 * exigem login (criar torneios, subscrever planos pagos, etc.).
 *
 * <p>Esta classe é partilhada por várias Page Objects desta test suite, dado
 * que o mesmo diálogo é apresentado em múltiplos fluxos.</p>
 */
public class LoginDialog {

    private final SelenideElement dialogTitle = $x("//h2[normalize-space()='Welcome back!']");
    private final SelenideElement continueWithEmailButton = $x("//button[normalize-space()='Continue with email']");
    private final SelenideElement googleButton = $x("//button[contains(., 'Continue with Google')]");
    private final SelenideElement facebookButton = $x("//button[contains(., 'Continue with Facebook')]");
    private final SelenideElement discordButton = $x("//button[contains(., 'Continue with Discord')]");
    private final SelenideElement closeButton = $("div.dialog-close button");

    @Step("Verificar se o diálogo de login 'Welcome back!' está visível")
    public boolean isVisible() {
        try {
            dialogTitle.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    @Step("Verificar se o botão 'Continue with email' está visível")
    public boolean hasEmailOption() {
        return continueWithEmailButton.is(visible);
    }

    @Step("Verificar se as opções de login social (Google, Facebook, Discord) estão visíveis")
    public boolean hasSocialLoginOptions() {
        return googleButton.is(visible)
                && facebookButton.is(visible)
                && discordButton.is(visible);
    }

    @Step("Verificar se o botão de fechar do diálogo está visível")
    public boolean hasCloseButton() {
        return closeButton.is(visible);
    }
}