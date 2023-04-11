package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AllSuppliersPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AllSuppliersPageTests extends BaseTest {

    private AllSuppliersPage allSuppliersPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_SUPPLIERS_RESOURCE);

        allSuppliersPage = new AllSuppliersPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        int countTableColumns = allSuppliersPage.getCountTableColumns();
        List<String> tableColumnNames = allSuppliersPage.getTableColumnNames();

        Assertions.assertEquals(allSuppliersPage.pageTitle, allSuppliersPage.allSuppliersHeader.getText());
        Assertions.assertNotNull(allSuppliersPage.allSuppliersTable);
        Assertions.assertEquals(6, countTableColumns);
        Assertions.assertEquals(allSuppliersPage.companyNameCol, tableColumnNames.get(0));
        Assertions.assertEquals(allSuppliersPage.emailCol, tableColumnNames.get(1));
        Assertions.assertEquals(allSuppliersPage.addressCol, tableColumnNames.get(2));
        Assertions.assertEquals(allSuppliersPage.phoneNumberCol, tableColumnNames.get(3));
        Assertions.assertEquals(allSuppliersPage.descriptionCol, tableColumnNames.get(4));
    }
}
