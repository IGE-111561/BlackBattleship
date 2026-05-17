package iscteiul.ista.blackbattleship.tests.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class US24DesligarMusicaSelenideTest {

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
    public void desligarMusicaNasDefinicoes() {

        open("https://papergames.io/en/");

        /*
         * Esperar popup cookies
         */
        sleep(2000);

        /*
         * Aceitar cookies se existir
         */
        if ($("button.fc-cta-consent").exists()) {

            $("button.fc-cta-consent").click();

            sleep(2000);
        }

        /*
         * Abrir settings via JavaScript
         */
        executeJavaScript(
                "document.querySelector('app-header-nav:nth-child(2) .mat-mdc-button-touch-target').click();"
        );

        $("#settings-music-button")
                .shouldBe(visible);

        /*
         * Clicar toggle music via JS
         */
        executeJavaScript(
                "document.querySelector('#settings-music-button .mdc-switch__icons').click();"
        );

        $("#settings-music-button")
                .shouldBe(visible);
    }
}