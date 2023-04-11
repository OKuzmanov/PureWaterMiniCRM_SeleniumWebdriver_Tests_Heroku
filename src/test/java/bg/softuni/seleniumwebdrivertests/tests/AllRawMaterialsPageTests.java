package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AllRawMaterialsPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AllRawMaterialsPageTests extends BaseTest {

    private AllRawMaterialsPage allRawMaterialsPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_RAW_MATERIALS_RESOURCE);

        allRawMaterialsPage = new AllRawMaterialsPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        int countTableColumns = allRawMaterialsPage.getCountTableColumns();
        List<String> tableColumnNames = allRawMaterialsPage.getTableColumnNames();

        Assertions.assertEquals(allRawMaterialsPage.pageTitle, allRawMaterialsPage.allRawMaterialsHeader.getText());
        Assertions.assertNotNull(allRawMaterialsPage.allRawMaterialsTable);
        Assertions.assertEquals(5, countTableColumns);
        Assertions.assertEquals(allRawMaterialsPage.typeCol, tableColumnNames.get(0));
        Assertions.assertEquals(allRawMaterialsPage.quantityCol, tableColumnNames.get(1));
        Assertions.assertEquals(allRawMaterialsPage.deliveryDateCol, tableColumnNames.get(2));
        Assertions.assertEquals(allRawMaterialsPage.supplierCol, tableColumnNames.get(3));
    }
}
