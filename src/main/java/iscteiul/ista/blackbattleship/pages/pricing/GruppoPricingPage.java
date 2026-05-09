package iscteiul.ista.blackbattleship.pages.pricing;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object para a página de subscrição do plano "Gruppo" em
 * <a href="https://papergames.io/en/pricing/gruppo">papergames.io/en/pricing/gruppo</a>.
 *
 * <p>Esta página apresenta o plano "Gruppo" orientado para reuniões online,
 * com listagem de funcionalidades e botão "Get Gruppo" para subscrição.
 * A subscrição efetiva exige autenticação.</p>
 */
public class GruppoPricingPage {

    public static final String URL = "https://papergames.io/en/pricing/gruppo";

    private final SelenideElement planTitle = $x("//h2[normalize-space()='Gruppo']");
    private final SelenideElement startPlayingButton = $x("//a[normalize-space()='Start playing now']");
    private final SelenideElement getGruppoButton = $x("//button[normalize-space()='Get Gruppo']");
    private final SelenideElement cookieAcceptButton = $("button.fc-cta-consent");

    @Step("Abrir página de subscrição Gruppo")
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

    @Step("Obter texto do título do plano")
    public String getPlanTitle() {
        return planTitle.shouldBe(visible, Duration.ofSeconds(10)).getText();
    }

    @Step("Verificar se o botão 'Start playing now' está visível na hero section")
    public boolean isHeroCTAVisible() {
        return startPlayingButton.is(visible);
    }

    @Step("Verificar se o botão 'Get Gruppo' está visível")
    public boolean isGetGruppoButtonVisible() {
        return getGruppoButton.is(visible);
    }

    @Step("Clicar em 'Get Gruppo'")
    public void clickGetGruppo() {
        getGruppoButton.shouldBe(visible).click();
    }
}