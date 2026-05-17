package iscteiul.ista.blackbattleship.pages.shop;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

/**
 * Page Object (Selenide) para a Loja do papergames.io.
 *
 * <p><b>User Story 22:</b> Como jogador, quero aceder à loja e à página de
 * goodies (merch.papergames.io) para comprar itens e merchandising oficial.
 *
 * <p>Localizadores obtidos da gravação {@code TestSuite_US22_93263.side}.
 *
 * @see <a href="https://papergames.io/pt-br/">papergames.io</a>
 */
// page_url = https://papergames.io/pt-br/
public class ShopSelenidePage {

    public static final String BASE_URL = "https://papergames.io/pt-br/";

    private final SelenideElement cookieAcceptButton =
            $("button.fc-cta-consent");

    /** Item "Loja" no menu lateral (gravação: xpath innerText). */
    private final SelenideElement lojaNavItem =
            $x("//span[contains(.,'Loja')]");

    /** Imagem da categoria Monstros (gravação: //img[@alt='Monstros']). */
    private final SelenideElement monstrosImage =
            $x("//img[@alt='Monstros']");

    /** Título/link da categoria Presentes (gravação: //h3[contains(.,'Presentes')]). */
    private final SelenideElement presentesLink =
            $x("//h3[contains(.,'Presentes')]");

    /** Botão de compra do 10.º produto na listagem de Monstros. */
    private final SelenideElement tenthProductBuyBtn =
            $(".h-100:nth-child(10) .btn");

    /** Botão "Confirmar" no diálogo de confirmação de compra. */
    private final SelenideElement confirmarBtn =
            $x("//span[contains(.,'Confirmar')]");

    /** Toast de notificação após tentativa de compra sem login. */
    private final SelenideElement toast =
            $("#toast-container .ng-trigger");

    @Step("Abrir a página inicial (locale pt-br)")
    public void open() {
        Selenide.open(BASE_URL);
    }

    @Step("Aceitar cookies se o banner estiver presente")
    public void acceptCookiesIfPresent() {
        try {
            cookieAcceptButton.shouldBe(visible, Duration.ofSeconds(5)).click();
        } catch (Exception e) {
            // Banner não apareceu, segue
        }
    }

    @Step("Verificar se o item 'Loja' está visível no menu de navegação")
    public boolean isLojaNavItemVisible() {
        try {
            lojaNavItem.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clicar em 'Loja' no menu de navegação")
    public void clickLoja() {
        lojaNavItem.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Verificar se a categoria 'Monstros' está visível na loja")
    public boolean isMonstrosCategoryVisible() {
        try {
            // shouldBe(visible) aguarda até ao timeout — necessário após navegação
            monstrosImage.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verificar se a categoria 'Presentes' está visível na loja")
    public boolean isPresentesCategoryVisible() {
        try {
            presentesLink.shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clicar na categoria 'Monstros'")
    public void clickMonstros() {
        monstrosImage.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Verificar se existem botões de compra na listagem de Monstros")
    public boolean hasBuyButtons() {
        try {
            // Aguarda que o primeiro botão de compra fique visível após navegar
            $$("app-shop-purchase-product-button .btn").first()
                    .shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clicar no botão de compra do 10.º produto")
    public void clickTenthProductBuyButton() {
        tenthProductBuyBtn.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Verificar se o diálogo de confirmação de compra está aberto")
    public boolean isConfirmDialogOpen() {
        try {
            $("mat-dialog-container app-confirm-dialog")
                    .shouldBe(visible, Duration.ofSeconds(10));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Clicar no botão 'Confirmar' no diálogo de compra")
    public void clickConfirmar() {
        confirmarBtn.shouldBe(visible).click();
    }

    @Step("Verificar se o toast de feedback está visível")
    public boolean isToastVisible() {
        try {
            toast.shouldBe(visible, Duration.ofSeconds(5));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verificar quantas tabs estão abertas no browser")
    public int getOpenTabCount() {
        return Selenide.webdriver().driver().getWebDriver().getWindowHandles().size();
    }

    @Step("Clicar em 'Presentes' (abre merch.papergames.io em nova tab)")
    public void clickPresentes() {
        presentesLink.shouldBe(visible, Duration.ofSeconds(10)).click();
    }

    @Step("Mudar o foco para a nova tab mais recente")
    public void switchToNewTab() {
        var handles = Selenide.webdriver().driver().getWebDriver().getWindowHandles()
                .stream().toList();
        Selenide.webdriver().driver().getWebDriver()
                .switchTo().window(handles.get(handles.size() - 1));
    }

    @Step("Obter o URL actual do browser")
    public String getCurrentUrl() {
        return Selenide.webdriver().driver().url();
    }
}
