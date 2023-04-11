package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AddSupplierPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AddSupplierPageTests extends BaseTest {
    private AddSupplierPage addSupplierPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ADD_SUPPLIER_RESOURCE);

        addSupplierPage = new AddSupplierPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        Assertions.assertEquals(addSupplierPage.pageTitle, addSupplierPage.addSupplierTitle.getText());
        Assertions.assertNotNull(addSupplierPage.companyNameInput);
        Assertions.assertNotNull(addSupplierPage.emailInput);
        Assertions.assertNotNull(addSupplierPage.addressInput);
        Assertions.assertNotNull(addSupplierPage.phoneNumberInput);
        Assertions.assertNotNull(addSupplierPage.descriptionInput);
        Assertions.assertNotNull(addSupplierPage.addSupplierBtn);
    }

    @Test
    public void test_validationsWorkCorrectly() {
        addSupplierPage.emailInput.sendKeys("1@1.com");
        addSupplierPage.addSupplierBtn.click();
        Assertions.assertNotNull(addSupplierPage.getCompanyNameInputError());
        Assertions.assertNotNull(addSupplierPage.getAddressInputError());
        Assertions.assertNotNull(addSupplierPage.getPhoneNumberInputError());
        Assertions.assertNotNull(addSupplierPage.getDescriptionInputError());
    }

    @Test
    public void test_createCustomer_validInput() {
        int rnd = new Random().nextInt(999);
        addSupplierPage.companyNameInput.sendKeys("test name" + rnd);
        addSupplierPage.emailInput.sendKeys(rnd + "1@1.com");
        addSupplierPage.addressInput.sendKeys("test address" + rnd);
        addSupplierPage.phoneNumberInput.sendKeys("12345678" + rnd);
        addSupplierPage.descriptionInput.sendKeys("test description" + rnd);
        addSupplierPage.addSupplierBtn.click();

        Assertions.assertEquals(getGlobalUrl() + HOME_RESOURCE, chromeDriver.getCurrentUrl());
    }
}
