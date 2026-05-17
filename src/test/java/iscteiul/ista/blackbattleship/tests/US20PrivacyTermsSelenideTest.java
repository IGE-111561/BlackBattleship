package iscteiul.ista.blackbattleship.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Teste Selenide para a US20 -
 * Política de Privacidade e Termos & Condições.
 */
public class US20PrivacyTermsSelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "947x700";
        Configuration.timeout = 15000;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    public void consultarPoliticaPrivacidadeETermos() {
        open("https://papergames.io/en/blog/privacy-policy");
        $("body").shouldHave(text("Privacy"));

        open("https://papergames.io/en/blog/terms-conditions");
        $("body").shouldHave(text("Terms"));
    }
}