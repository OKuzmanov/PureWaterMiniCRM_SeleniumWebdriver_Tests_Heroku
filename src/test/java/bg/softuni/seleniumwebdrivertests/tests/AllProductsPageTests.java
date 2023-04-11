package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AllProductsPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AllProductsPageTests extends BaseTest {

    private AllProductsPage allProducts;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_PRODUCTS_RESOURCE);

        allProducts = new AllProductsPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        int countTableColumns = allProducts.getCountTableColumns();
        List<String> tableColumnNames = allProducts.getTableColumnNames();

        Assertions.assertEquals(allProducts.pageTitle, allProducts.allProductsHeader.getText());
        Assertions.assertNotNull(allProducts.allProductsTable);
        Assertions.assertEquals(4, countTableColumns);
        Assertions.assertEquals(allProducts.typeCol, tableColumnNames.get(0));
        Assertions.assertEquals(allProducts.quantityCol, tableColumnNames.get(1));
        Assertions.assertEquals(allProducts.productionDateCol, tableColumnNames.get(2));
    }
}
