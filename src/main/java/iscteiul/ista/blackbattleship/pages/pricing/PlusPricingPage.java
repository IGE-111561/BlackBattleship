package iscteiul.ista.blackbattleship.pages.pricing;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Page Object para a página de subscrição do plano "Plus" em
 * <a href="https://papergames.io/en/pricing/plus">papergames.io/en/pricing/plus</a>.
 *
 * <p>Esta página apresenta planos de pagamento ("1-Month Pass", "1-Year Pass")
 * com botões "Get Plus" para subscrição. A subscrição efetiva exige
 * autenticação, pelo que clicar nestes botões dispara o diálogo de login.</p>
 */
public class PlusPricingPage {

    public static final String URL = "https://papergames.io/en/pricing/plus";

    private final SelenideElement planTitle = $x("//h2[normalize-space()='Plus']");
    private final SelenideElement playAdFreeButton = $x("//a[normalize-space()='Play ad-free']");
    private final SelenideElement getPlusButton = $x("(//button[normalize-space()='Get Plus'])[1]");
    private final SelenideElement cookieAcceptButton = $("button.fc-cta-consent");

    @Step("Abrir página de subscrição Plus")
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

    @Step("Verificar se o botão 'Play ad-free' está visível na hero section")
    public boolean isHeroCTAVisible() {
        return playAdFreeButton.is(visible);
    }

    @Step("Verificar se o botão 'Get Plus' está visível")
    public boolean isGetPlusButtonVisible() {
        return getPlusButton.is(visible);
    }

    @Step("Clicar em 'Get Plus' (1-Month Pass)")
    public void clickGetPlus() {
        getPlusButton.shouldBe(visible).click();
    }
}