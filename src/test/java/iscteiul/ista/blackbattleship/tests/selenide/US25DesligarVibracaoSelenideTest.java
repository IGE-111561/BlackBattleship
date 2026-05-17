package iscteiul.ista.blackbattleship.tests.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class US25DesligarVibracaoSelenideTest {

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
    public void desligarVibracaoNasDefinicoes() {

        open("https://papergames.io/en/");

        /*
         * Esperar carregamento
         */
        sleep(3000);

        /*
         * Fechar popup cookies via JS
         */
        executeJavaScript(
                """
                const btn = document.querySelector('button.fc-cta-consent');
                if(btn){ btn.click(); }
                """
        );

        /*
         * Esperar overlay desaparecer
         */
        sleep(3000);

        /*
         * Abrir settings via JavaScript
         */
        executeJavaScript(
                """
                document.querySelector(
                    'app-header-nav:nth-child(2) .mat-mdc-button-touch-target'
                ).click();
                """
        );

        sleep(2000);

        $("#settings-vibrations-button")
                .shouldBe(visible);

        /*
         * Toggle vibration via JS
         */
        executeJavaScript(
                """
                document.querySelector(
                    '#settings-vibrations-button .mdc-switch__icons'
                ).click();
                """
        );

        sleep(2000);

        $("#settings-vibrations-button")
                .shouldBe(visible);
    }
}