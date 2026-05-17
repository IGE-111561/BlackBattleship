package iscteiul.ista.blackbattleship.tests;

import iscteiul.ista.blackbattleship.pages.ShopPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Page Test Class – User Story 22: Aceder à loja e à página de goodies.
 *
 * <p><b>User Story:</b><br>
 * Como jogador, quero aceder à loja ({@code /pt-br/shop}) e à página de
 * goodies ({@code merch.papergames.io}) para comprar itens dentro do jogo
 * e merchandising oficial.
 *
 * <p><b>Critérios de Aceitação verificados:</b>
 * <ol>
 *   <li>{@link #lojaEntryPointVisibleInNav()} –
 *       Existe uma entrada "Loja" visível no menu de navegação.</li>
 *   <li>{@link #clickingLojaNavigatesToShopPage()} –
 *       Clicar em "Loja" navega para uma URL que contém {@code /shop}.</li>
 *   <li>{@link #shopPageShowsMonstrosAndPresentesCategories()} –
 *       A página da loja apresenta as categorias "Monstros" e "Presentes".</li>
 *   <li>{@link #monstrosCategoryShowsItemsWithBuyButtons()} –
 *       A categoria "Monstros" apresenta itens com botão de compra visível.</li>
 *   <li>{@link #buyButtonOpensConfirmDialog()} –
 *       Clicar no botão de compra abre o diálogo de confirmação com o botão
 *       "Confirmar".</li>
 *   <li>{@link #presentesOpensGoodieSiteInNewTab()} –
 *       Clicar em "Presentes" abre {@code merch.papergames.io} numa nova tab.</li>
 * </ol>
 *
 * <p>Nota: os testes não finalizam qualquer compra. O TC5 clica "Confirmar"
 * sem sessão activa – o comportamento esperado é que o sistema responda com
 * um toast de erro/aviso, o que é igualmente verificado.
 *
 * @see ShopPage
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShopPageTest {

    private WebDriver driver;
    private ShopPage shopPage;

    // =========================================================================
    // Fixture
    // =========================================================================

    /**
     * Inicializa o ChromeDriver com a dimensão de janela usada na gravação
     * (599×691) e abre a página inicial antes de cada teste.
     */
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if ("true".equalsIgnoreCase(System.getProperty("headless"))) {
            options.addArguments("--headless=new");
        }
        // Dimensão usada na gravação Selenium IDE
        options.addArguments("--window-size=599,691");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        shopPage = new ShopPage(driver);
        shopPage.open();
        shopPage.acceptCookiesIfPresent();
    }

    /** Encerra o browser após cada teste. */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // =========================================================================
    // Testes
    // =========================================================================

    /**
     * <b>TC1 – Entrada "Loja" visível no menu de navegação.</b>
     *
     * <p>Valida o critério: «Verificar se existe uma entrada visível para a
     * Loja a partir do menu principal do site.»
     */
    @Test
    @Order(1)
    @DisplayName("TC1 – Item 'Loja' visível no menu de navegação")
    public void lojaEntryPointVisibleInNav() {
        assertTrue(
                shopPage.isLojaNavItemVisible(),
                "O item 'Loja' deve estar visível no menu lateral de navegação "
                + "na página inicial."
        );
    }

    /**
     * <b>TC2 – Clicar em "Loja" navega para a página da loja.</b>
     *
     * <p>Valida o critério: «Verificar se existe uma entrada visível para a
     * Loja a partir do menu principal do site.»
     */
    @Test
    @Order(2)
    @DisplayName("TC2 – Clicar em 'Loja' navega para URL que contém /shop")
    public void clickingLojaNavigatesToShopPage() {
        shopPage.clickLoja();

        String url = shopPage.getCurrentUrl();
        assertTrue(
                url.contains("/shop"),
                "Após clicar em 'Loja', o URL deve conter '/shop'. URL actual: " + url
        );
    }

    /**
     * <b>TC3 – Página da loja apresenta categorias "Monstros" e "Presentes".</b>
     *
     * <p>Valida o critério: «Validar se a loja apresenta itens claramente
     * identificados (testar elementos estáticos).»
     */
    @Test
    @Order(3)
    @DisplayName("TC3 – Loja apresenta as categorias Monstros e Presentes")
    public void shopPageShowsMonstrosAndPresentesCategories() {
        shopPage.clickLoja();

        assertAll("Categorias da loja devem estar visíveis",
                () -> assertTrue(
                        shopPage.isMonstrosCategoryVisible(),
                        "A categoria 'Monstros' deve estar visível na página da loja."
                ),
                () -> assertTrue(
                        shopPage.isPresentesCategoryVisible(),
                        "A categoria 'Presentes' deve estar visível na página da loja."
                )
        );
    }

    /**
     * <b>TC4 – Categoria "Monstros" apresenta itens com botão de compra.</b>
     *
     * <p>Valida o critério: «Validar se a loja apresenta itens com preço e
     * botão de compra claramente identificado.»
     */
    @Test
    @Order(4)
    @DisplayName("TC4 – Categoria Monstros apresenta itens com botão de compra")
    public void monstrosCategoryShowsItemsWithBuyButtons() {
        shopPage.clickLoja();
        shopPage.clickMonstros();

        assertTrue(
                shopPage.hasBuyButtons(),
                "A categoria 'Monstros' deve apresentar pelo menos um item com um "
                + "botão de compra visível (app-shop-purchase-product-button .btn)."
        );
    }

    /**
     * <b>TC5 – Clicar no botão de compra abre o diálogo de confirmação.</b>
     *
     * <p>O diálogo {@code app-confirm-dialog} deve aparecer com o botão
     * "Confirmar". Após clicar "Confirmar" sem sessão activa, o sistema
     * responde com um toast de aviso – ambos os estados são verificados.
     *
     * <p>Valida o critério: «Validar se a loja apresenta botão de compra
     * claramente identificado.»
     */
    @Test
    @Order(5)
    @DisplayName("TC5 – Botão de compra abre diálogo de confirmação; Confirmar gera feedback")
    public void buyButtonOpensConfirmDialog() {
        shopPage.clickLoja();
        shopPage.clickMonstros();
        shopPage.clickTenthProductBuyButton();

        assertTrue(
                shopPage.isConfirmDialogOpen(),
                "Após clicar no botão de compra, o diálogo 'app-confirm-dialog' deve "
                + "ficar visível com o botão 'Confirmar'."
        );

        // Clica Confirmar (sem sessão activa – o sistema responde com toast)
        shopPage.clickConfirmar();

        // O toast de feedback (erro ou aviso) deve aparecer
        assertTrue(
                shopPage.isToastVisible(),
                "Após clicar 'Confirmar' sem sessão activa, um toast de feedback deve "
                + "aparecer no ecrã."
        );
    }

    /**
     * <b>TC6 – "Presentes" abre merch.papergames.io numa nova tab.</b>
     *
     * <p>Valida o critério: «Confirmar que a página de merch é acessível a
     * partir do site e abre o catálogo de produtos físicos.»
     */
    @Test
    @Order(6)
    @DisplayName("TC6 – Presentes abre merch.papergames.io numa nova tab")
    public void presentesOpensGoodieSiteInNewTab() {
        shopPage.clickLoja();

        int tabsBefore = shopPage.getOpenTabCount();

        shopPage.clickPresentes();

        // Verifica que foi aberta uma nova tab
        assertEquals(
                tabsBefore + 1,
                shopPage.getOpenTabCount(),
                "Clicar em 'Presentes' deve abrir uma nova tab no browser."
        );

        // Muda para a nova tab e verifica o URL
        shopPage.switchToNewTab();
        String merchandiseUrl = shopPage.getCurrentUrl();

        assertTrue(
                merchandiseUrl.contains("merch.papergames.io"),
                "A nova tab deve abrir 'merch.papergames.io'. URL actual: " + merchandiseUrl
        );
    }
}
