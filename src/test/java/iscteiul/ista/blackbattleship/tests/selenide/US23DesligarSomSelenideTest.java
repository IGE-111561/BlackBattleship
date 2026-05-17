package iscteiul.ista.blackbattleship.tests.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class US23DesligarSomSelenideTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "797x809";
        Configuration.timeout = 15000;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }

    @Test
    public void desligarSomNasDefinicoes() {
        open("https://papergames.io/en/");

        if ($("button.fc-cta-consent").exists()) {
            $("button.fc-cta-consent").click();
        }

        $("app-header-nav:nth-child(2) .mat-mdc-button-touch-target")
                .shouldBe(visible)
                .click();

        $("#settings-sound-button")
                .shouldBe(visible);

        executeJavaScript(
                "document.querySelector('#settings-sound-button .mdc-switch__icons, #settings-sound-button .mdc-switch__ripple').click();"
        );

        $("#settings-sound-button")
                .shouldBe(visible);
    }
}