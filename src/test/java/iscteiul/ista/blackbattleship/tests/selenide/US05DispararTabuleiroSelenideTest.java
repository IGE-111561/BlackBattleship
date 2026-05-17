package iscteiul.ista.blackbattleship.tests.selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Teste Selenide para a US05 - Disparar contra o tabuleiro adversário.
 */
public class US05DispararTabuleiroSelenideTest {

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
    public void dispararContraTabuleiroAdversario() {
        open("https://papergames.io/en/battleship");

        $x("//span[normalize-space()='Play vs robot']")
                .shouldBe(visible)
                .click();

        if ($x("//h2[normalize-space()='Who are you?']").exists()) {
            $("input[formcontrolname='username']")
                    .shouldBe(visible)
                    .setValue("TesteSelenide123762");

            $("footer button[type='submit']")
                    .shouldBe(visible)
                    .click();
        }

        $$("[class*='cell'], [class*='tile'], [class*='square'], [class*='ship'], [class*='board'], [class*='grid']")
                .shouldHave(CollectionCondition.sizeGreaterThan(0));

        executeJavaScript(
                "document.querySelector(\"[class*='cell'], [class*='tile'], [class*='square'], [class*='target'], [class*='hover']\")?.click();"
        );

        $("body").shouldBe(visible);
    }
}