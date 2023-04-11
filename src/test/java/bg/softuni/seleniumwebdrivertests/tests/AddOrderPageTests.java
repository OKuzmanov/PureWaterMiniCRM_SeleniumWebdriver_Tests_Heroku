package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AddOrderPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddOrderPageTests extends BaseTest {

    private AddOrderPage addOrderPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ADD_ORDER_RESOURCE);

        addOrderPage = new AddOrderPage(chromeDriver);
    }

    @Test
    public void test_addOrderPage_pageLoadsCorrectly() {

        Assertions.assertEquals(addOrderPage.orderPageTitle, addOrderPage.addOrderTitle.getText());
        Assertions.assertNotNull(addOrderPage.orderNameInput);
        Assertions.assertNotNull(addOrderPage.quantityInput);
        Assertions.assertNotNull(addOrderPage.categoryInput);
        Assertions.assertNotNull(addOrderPage.expiryDateInput);
        Assertions.assertNotNull(addOrderPage.descriptionInput);
        Assertions.assertNotNull(addOrderPage.customerInput);
        Assertions.assertNotNull(addOrderPage.addOrderBtn);
    }

    @Test
    public void test_addOrderPage_validationsWorkCorrectly() {

        addOrderPage.addOrderBtn.click();
        Assertions.assertNotNull(addOrderPage.getOrderNameInputError());
        Assertions.assertNotNull(addOrderPage.getQuantityInputError());
        Assertions.assertNotNull(addOrderPage.getCategoryInputError());
        Assertions.assertNotNull(addOrderPage.getExpiryDateInputError());
        Assertions.assertNotNull(addOrderPage.getDescriptionInputError());
        Assertions.assertNotNull(addOrderPage.getCustomerInputError());
    }
}
