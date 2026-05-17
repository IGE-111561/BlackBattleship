package iscteiul.ista.blackbattleship.tests.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Teste Selenide para a US27 - Consultar lista de Monsters na loja.
 */
public class US27MonstersShopSelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1396x737";
        Configuration.timeout = 15000;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    public void consultarListaMonstersNaLoja() {
        open("https://papergames.io/en/shop");

        $x("//img[@alt='Monsters']")
                .shouldBe(visible)
                .click();

        $("body").shouldHave(or("conteúdo Monsters",
                text("Monsters"),
                text("Monster")
        ));
    }
}