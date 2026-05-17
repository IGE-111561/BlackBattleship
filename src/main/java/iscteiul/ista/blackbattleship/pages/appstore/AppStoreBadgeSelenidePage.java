package iscteiul.ista.blackbattleship.pages.appstore;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * Page Object (Selenide) para os badges de Google Play e App Store
 * presentes na página inicial do papergames.io.
 *
 * <p><b>User Story 21:</b> Como jogador, quero encontrar no site as ligações
 * para a app na Google Play e App Store para poder jogar no telemóvel.
 *
 * <p>Localizadores obtidos da gravação {@code TestSuite_US21_93263.side}.
 *
 * @see <a href="https://papergames.io/en/">papergames.io</a>
 */
// page_url = https://papergames.io/en/
public class AppStoreBadgeSelenidePage {

    public static final String URL = "https://papergames.io/en/";
    public static final String GOOGLE_PLAY_DOMAIN = "play.google.com";
    public static final String APP_STORE_DOMAIN   = "apps.apple.com";

    private final SelenideElement cookieAcceptButton =
            $("button.fc-cta-consent");

    /**
     * Badge Google Play (gravação: {@code app-playstore-badge img},
     * alt="Get it on Playstore").
     */
    private final SelenideElement googlePlayBadgeImg =
            $("app-playstore-badge img");

    /** Link que envolve o badge Google Play – para ler o href. */
    private final SelenideElement googlePlayBadgeLink =
            $("app-playstore-badge a");

    /**
     * Badge App Store (gravação: {@code app-appstore-badge img},
     * alt="Get it on the AppStore").
     */
    private final SelenideElement appStoreBadgeImg =
            $("app-appstore-badge img");

    /** Link que envolve o badge App Store – para ler o href. */
    private final SelenideElement appStoreBadgeLink =
            $("app-appstore-badge a");

    @Step("Abrir a página inicial do papergames.io")
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

    @Step("Verificar se o badge Google Play está visível")
    public boolean isGooglePlayBadgeVisible() {
        return googlePlayBadgeImg.is(visible);
    }

    @Step("Obter o href do badge Google Play")
    public String getGooglePlayHref() {
        googlePlayBadgeLink.shouldBe(visible, Duration.ofSeconds(10));
        String href = googlePlayBadgeLink.getAttribute("href");
        return href != null ? href : "";
    }

    @Step("Verificar se o badge App Store está visível")
    public boolean isAppStoreBadgeVisible() {
        return appStoreBadgeImg.is(visible);
    }

    @Step("Obter o href do badge App Store")
    public String getAppStoreHref() {
        appStoreBadgeLink.shouldBe(visible, Duration.ofSeconds(10));
        String href = appStoreBadgeLink.getAttribute("href");
        return href != null ? href : "";
    }
}
