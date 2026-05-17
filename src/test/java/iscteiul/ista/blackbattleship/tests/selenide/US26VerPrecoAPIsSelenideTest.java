package iscteiul.ista.blackbattleship.tests.selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class US26VerPrecoAPIsSelenideTest {

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
    public void verPrecoAPIs() {

        open("https://papergames.io/en/");

        /*
         * Esperar carregamento inicial
         */
        sleep(3000);

        /*
         * Aceitar cookies se popup existir
         */
        if ($("button.fc-cta-consent").exists()) {

            $("button.fc-cta-consent").click();

            /*
             * Esperar overlay desaparecer
             */
            sleep(3000);
        }

        /*
         * Scroll topo
         */
        executeJavaScript("window.scrollTo(0,0)");

        sleep(1000);

        /*
         * Abrir API via JavaScript
         */
        executeJavaScript(
                "document.querySelector('a[href*=\"games-as-a-service\"]').click();"
        );

        sleep(3000);

        assertTrue(
                url().contains("pricing")
                        || url().contains("games-as-a-service"),
                "O URL deve indicar página API pricing."
        );
    }
}