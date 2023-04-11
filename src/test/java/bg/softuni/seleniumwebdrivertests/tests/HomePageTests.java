package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.HomePage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class HomePageTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeEach
    void logUser() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        this.homePage = new HomePage(chromeDriver);
    }

    @Test
    public void test_homePageLoadsCorrectly() {

        Assertions.assertEquals(homePage.HOME_PAGE_HEADER, homePage.homeHeader.getText());
        Assertions.assertEquals(homePage.ORDERS_TABLE_HEADER, homePage.ordersTableHeader.getText());
        Assertions.assertEquals(homePage.EMPLOYEES_TABLE_HEADER, homePage.employeesTableHeader.getText());
        Assertions.assertNotNull(homePage.ordersTable);
        Assertions.assertNotNull(homePage.usersTable);
    }

    @Test
    void test_testNavbarLinks_addOrder() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Order")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ADD_ORDER_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_addCustomer() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Customer")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ADD_CUSTOMER_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_addSupplier() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Supplier")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ADD_SUPPLIER_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_addProducts() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Products")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ADD_PRODUCTS_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_addRawMaterials() {

        homePage.navigationBar.findElement(By.partialLinkText("Add Raw Materials")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ADD_RAW_MATERIALS_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_ordersHistory() {

        homePage.navigationBar.findElement(By.partialLinkText("Orders History")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ORDERS_HISTORY_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_allCustomers() {

        homePage.getAllCustomers().click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ALL_CUSTOMERS_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_allSuppliers() {

        homePage.getAllSuppliers().click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ALL_SUPPLIERS_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_allProducts() {

        homePage.getAllProducts().click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ALL_PRODUCTS_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_allRawMaterials() {

        homePage.getAllRawMaterials().click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ALL_RAW_MATERIALS_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_userProfile() {

        homePage.navigationBar.findElement(By.cssSelector("li:nth-child(8) > div > div:nth-child(1) > a")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + USER_PROFILE_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_logout() {

        homePage.navigationBar.findElement(By.cssSelector("li:nth-child(9) > form > input.btn.btn-link.nav-link")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl(), currentUrl);
    }

    @Test
    void test_orderTableReadyLink_NotEnoughProductsInStorageErr() {
        this.homePage.getFirstReadyLinkOrderTable().click();

        Assertions.assertNotNull(homePage.getNotEnoughProductsInStorageAlert());
        Assertions.assertEquals(homePage.NOT_ENOUGH_PRODUCTS, homePage.getNotEnoughProductsInStorageAlert().getText());
    }
}
