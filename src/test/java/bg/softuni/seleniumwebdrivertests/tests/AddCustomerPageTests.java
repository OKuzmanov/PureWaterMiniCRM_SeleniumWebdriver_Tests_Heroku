package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AddCustomerPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AddCustomerPageTests extends BaseTest {

    private AddCustomerPage addCustomerPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ADD_CUSTOMER_RESOURCE);

        addCustomerPage = new AddCustomerPage(chromeDriver);
    }

    @Test
    public void test_loadPageCorrectly() {

        Assertions.assertEquals(addCustomerPage.pageTitle, addCustomerPage.addCustomerTitle.getText());
        Assertions.assertNotNull(addCustomerPage.companyNameInput);
        Assertions.assertNotNull(addCustomerPage.emailInput);
        Assertions.assertNotNull(addCustomerPage.addressInput);
        Assertions.assertNotNull(addCustomerPage.phoneNumberInput);
        Assertions.assertNotNull(addCustomerPage.descriptionInput);
        Assertions.assertNotNull(addCustomerPage.addCustomerBtn);
    }

    @Test
    public void test_validationsWorkCorrectly() {

        addCustomerPage.emailInput.sendKeys("1@1.com");
        addCustomerPage.addCustomerBtn.click();
        Assertions.assertNotNull(addCustomerPage.getCompanyNameInputError());
        Assertions.assertNotNull(addCustomerPage.getAddressInputError());
        Assertions.assertNotNull(addCustomerPage.getPhoneNumberInputError());
        Assertions.assertNotNull(addCustomerPage.getDescriptionInputError());
    }

    @Test
    public void test_createCustomer_validInput() {

        int rnd = new Random().nextInt(999);
        addCustomerPage.companyNameInput.sendKeys("test name" + rnd);
        addCustomerPage.emailInput.sendKeys(rnd + "1@1.com");
        addCustomerPage.addressInput.sendKeys("test address" + rnd);
        addCustomerPage.phoneNumberInput.sendKeys("12345678" + rnd);
        addCustomerPage.descriptionInput.sendKeys("test description" + rnd);
        addCustomerPage.addCustomerBtn.click();

        Assertions.assertEquals(getGlobalUrl() + HOME_RESOURCE, chromeDriver.getCurrentUrl());
    }
}
