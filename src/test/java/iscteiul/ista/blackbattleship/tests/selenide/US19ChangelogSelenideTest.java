package iscteiul.ista.blackbattleship.tests.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

/**
 * Teste Selenide para a US19 - Consultar Changelog.
 */
public class US19ChangelogSelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1254x812";
        Configuration.timeout = 15000;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    public void consultarChangelog() {
        open("https://papergames.io/en/changelog");

        $("body").shouldHave(text("Changelog"));
    }
}