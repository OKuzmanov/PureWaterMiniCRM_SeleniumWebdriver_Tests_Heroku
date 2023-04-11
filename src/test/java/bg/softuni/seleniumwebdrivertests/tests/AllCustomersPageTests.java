package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AllCustomersPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AllCustomersPageTests extends BaseTest {

    private AllCustomersPage allCustomersPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_CUSTOMERS_RESOURCE);

        allCustomersPage = new AllCustomersPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        int countTableColumns = allCustomersPage.getCountTableColumns();
        List<String> tableColumnNames = allCustomersPage.getTableColumnNames();

        Assertions.assertEquals(allCustomersPage.pageTitle, allCustomersPage.allCustomersHeader.getText());
        Assertions.assertNotNull(allCustomersPage.allCustomersTable);
        Assertions.assertEquals(6, countTableColumns);
        Assertions.assertEquals(allCustomersPage.companyNameCol, tableColumnNames.get(0));
        Assertions.assertEquals(allCustomersPage.emailCol, tableColumnNames.get(1));
        Assertions.assertEquals(allCustomersPage.addressCol, tableColumnNames.get(2));
        Assertions.assertEquals(allCustomersPage.phoneNumberCol, tableColumnNames.get(3));
        Assertions.assertEquals(allCustomersPage.descriptionCol, tableColumnNames.get(4));
    }
}
