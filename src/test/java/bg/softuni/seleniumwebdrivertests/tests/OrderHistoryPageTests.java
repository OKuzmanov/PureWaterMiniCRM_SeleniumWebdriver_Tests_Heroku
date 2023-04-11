package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.OrderHistory;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrderHistoryPageTests extends BaseTest {

    private OrderHistory orderHistory;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ORDERS_HISTORY_RESOURCE);

        orderHistory = new OrderHistory(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        int countTableColumns = orderHistory.getCountTableColumns();
        List<String> tableColumnNames = orderHistory.getTableColumnNames();

        Assertions.assertEquals(orderHistory.pageTitle, orderHistory.orderHistoryHeader.getText());
        Assertions.assertNotNull(orderHistory.orderHistoryTable);
        Assertions.assertEquals(5, countTableColumns);
        Assertions.assertEquals(orderHistory.nameCol, tableColumnNames.get(0));
        Assertions.assertEquals(orderHistory.typeCol, tableColumnNames.get(1));
        Assertions.assertEquals(orderHistory.quantityCol, tableColumnNames.get(2));
        Assertions.assertEquals(orderHistory.priceCol, tableColumnNames.get(3));
        Assertions.assertEquals(orderHistory.completionDateCol, tableColumnNames.get(4));
    }
}
