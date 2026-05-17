package iscteiul.ista.blackbattleship.tests.tournaments_pricing;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import iscteiul.ista.blackbattleship.pages.pricing.GruppoPricingPage;
import iscteiul.ista.blackbattleship.pages.pricing.PlusPricingPage;
import iscteiul.ista.blackbattleship.pages.shared.LoginDialog;
import iscteiul.ista.blackbattleship.pages.tournaments.TournamentCreationPage;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class para a test suite "Torneios e Subscrições".
 *
 * <p>Cobre as user stories US14 (aceder à criação de torneio), US16
 * (subscrição Plus) e US17 (subscrição Gruppo). Todas as ações privadas
 * exigem autenticação, pelo que os testes validam que o sistema apresenta
 * corretamente o diálogo de login ("Welcome back!") quando o utilizador
 * tenta aceder a essas funcionalidades sem sessão iniciada.</p>
 */
public class TournamentsAndPricingTest {

    private LoginDialog loginDialog;

    @BeforeAll
    public static void configureSelenide() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10_000;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @BeforeEach
    public void setUp() {
        loginDialog = new LoginDialog();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

    /**
     * US14 — Aceder à criação de torneio.
     *
     * <p>Valida que a página de criação de torneio é pública (formulário visível
     * e preenchível) mas a submissão efetiva exige autenticação, apresentando o
     * diálogo "Welcome back!".</p>
     */
    @Test
    public void acederACriacaoDeTorneio() {
        TournamentCreationPage page = new TournamentCreationPage();
        page.open();
        page.acceptCookiesIfPresent();

        assertEquals("Tournament creation", page.getPageTitleText(),
                "O título da página deve ser 'Tournament creation'");
        assertTrue(page.isFormVisible(),
                "O formulário de criação de torneio deve estar visível");

        String tournamentName = "TesteISCTE" + System.currentTimeMillis() % 10000;
        page.fillRequiredFields("Battleship", tournamentName);

        assertTrue(page.isSubmitButtonEnabled(),
                "Após preencher todos os campos obrigatórios, o botão 'Create and share' deve ficar ativo");

        page.submitTournamentCreation();

        assertTrue(loginDialog.isVisible(),
                "Ao submeter a criação de torneio sem login, deve aparecer o diálogo 'Welcome back!'");
        assertTrue(loginDialog.hasEmailOption(),
                "O diálogo deve apresentar o botão 'Continue with email'");
        assertTrue(loginDialog.hasSocialLoginOptions(),
                "O diálogo deve apresentar opções de login social (Google, Facebook, Discord)");
        assertTrue(loginDialog.hasCloseButton(),
                "O diálogo deve ter um botão de fechar");
    }

    /**
     * US16 — Subscrição do plano Plus.
     *
     * <p>Valida que a página do plano Plus é pública e mostra os planos
     * disponíveis, mas a subscrição exige autenticação.</p>
     */
    @Test
    public void subscreverPlusExigeLogin() {
        PlusPricingPage page = new PlusPricingPage();
        page.open();
        page.acceptCookiesIfPresent();

        assertEquals("Plus", page.getPlanTitle(),
                "O título do plano deve ser 'Plus'");
        assertTrue(page.isHeroCTAVisible(),
                "O botão 'Play ad-free' deve estar visível na hero section");
        assertTrue(page.isGetPlusButtonVisible(),
                "O botão 'Get Plus' deve estar visível");

        page.clickGetPlus();

        assertTrue(loginDialog.isVisible(),
                "Ao tentar subscrever Plus sem login, deve aparecer o diálogo 'Welcome back!'");
        assertTrue(loginDialog.hasEmailOption(),
                "O diálogo deve apresentar o botão 'Continue with email'");
        assertTrue(loginDialog.hasSocialLoginOptions(),
                "O diálogo deve apresentar opções de login social");
    }

    /**
     * US17 — Subscrição do plano Gruppo.
     *
     * <p>Valida que a página do plano Gruppo é pública e a subscrição exige
     * autenticação.</p>
     */
    @Test
    public void subscreverGruppoExigeLogin() {
        GruppoPricingPage page = new GruppoPricingPage();
        page.open();
        page.acceptCookiesIfPresent();

        assertEquals("Gruppo", page.getPlanTitle(),
                "O título do plano deve ser 'Gruppo'");
        assertTrue(page.isHeroCTAVisible(),
                "O botão 'Start playing now' deve estar visível na hero section");
        assertTrue(page.isGetGruppoButtonVisible(),
                "O botão 'Get Gruppo' deve estar visível");

        page.clickGetGruppo();

        assertTrue(loginDialog.isVisible(),
                "Ao tentar subscrever Gruppo sem login, deve aparecer o diálogo 'Welcome back!'");
        assertTrue(loginDialog.hasEmailOption(),
                "O diálogo deve apresentar o botão 'Continue with email'");
        assertTrue(loginDialog.hasSocialLoginOptions(),
                "O diálogo deve apresentar opções de login social");
    }
}