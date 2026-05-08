package iscteiul.ista.blackbattleship.pages.selenide;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object Class baseada em Selenide para a página inicial do jogo de
 * Batalha Naval em <a href="https://papergames.io/en/battleship">papergames.io</a>.
 */
public class BattleshipHomePageSelenide {

    public static final String URL = "https://papergames.io/en/battleship";

    private final SelenideElement pageTitle = $("h1.area-title");
    private final SelenideElement playVsRobotButton = $x("//span[normalize-space()='Play vs robot']");
    private final SelenideElement playWithFriendButton = $x("//span[normalize-space()='Play with a friend']");
    private final SelenideElement nicknameDialogTitle = $x("//h2[normalize-space()='Who are you?']");
    private final SelenideElement nicknameInput = $("input[formcontrolname='username']");
    private final SelenideElement nicknameContinueButton = $("footer button[type='submit']");
    private final SelenideElement cookieAcceptButton = $("button.fc-cta-consent");

    @Step("Abrir página da Batalha Naval")
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

    @Step("Aceitar cookies")
    public void acceptCookies() {
        cookieAcceptButton.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Verificar se o banner de cookies está visível")
    public boolean isCookieBannerVisible() {
        try {
            cookieAcceptButton.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    @Step("Obter texto do título da página")
    public String getPageTitleText() {
        return pageTitle.shouldBe(visible).getText();
    }

    @Step("Verificar se o botão 'Play vs robot' está visível")
    public boolean isPlayVsRobotVisible() {
        return playVsRobotButton.is(visible);
    }

    @Step("Verificar se o botão 'Play with a friend' está visível")
    public boolean isPlayWithFriendVisible() {
        return playWithFriendButton.is(visible);
    }

    @Step("Clicar em 'Play vs robot'")
    public void clickPlayVsRobot() {
        playVsRobotButton.shouldBe(visible).click();
    }

    @Step("Clicar em 'Play with a friend'")
    public void clickPlayWithFriend() {
        playWithFriendButton.shouldBe(visible).click();
    }

    @Step("Verificar se o diálogo de nickname está visível")
    public boolean isNicknameDialogVisible() {
        try {
            nicknameDialogTitle.shouldBe(visible, Duration.ofSeconds(10));
            nicknameInput.shouldBe(visible, Duration.ofSeconds(5));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    @Step("Submeter nickname: {nickname}")
    public void submitNickname(String nickname) {
        nicknameInput.shouldBe(visible).setValue(nickname);
        nicknameContinueButton.shouldBe(enabled).click();
    }

    @Step("Verificar se a sala de jogo contra o robot 'Paper Man' está visível")
    public boolean isPlayingAgainstRobot() {
        try {
            $x("//span[normalize-space()='Paper Man']").shouldBe(visible, Duration.ofSeconds(20));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    @Step("Verificar se a página de partilha de link está visível")
    public boolean isShareLinkPageVisible() {
        try {
            $x("//*[normalize-space()='Share this link with a friend']").shouldBe(visible, Duration.ofSeconds(20));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    @Step("Obter o link partilhável")
    public String getShareableLink() {
        return $("app-copy-text span").shouldBe(visible).getText();
    }
}