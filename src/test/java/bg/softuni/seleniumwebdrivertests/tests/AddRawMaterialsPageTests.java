package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.logged.AddRawMaterialsPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddRawMaterialsPageTests extends BaseTest {
    private AddRawMaterialsPage addRawMaterialsPage;
    private LoginPage loginPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ADD_RAW_MATERIALS_RESOURCE);

        addRawMaterialsPage = new AddRawMaterialsPage(chromeDriver);
    }

    @Test
    public void test_pageLoadsCorrectly() {
        Assertions.assertEquals(addRawMaterialsPage.pageTitle, addRawMaterialsPage.addRawMaterialsTitle.getText());
        Assertions.assertNotNull(addRawMaterialsPage.typeInput);
        Assertions.assertNotNull(addRawMaterialsPage.quantityInput);
        Assertions.assertNotNull(addRawMaterialsPage.deliveredAtInput);
        Assertions.assertNotNull(addRawMaterialsPage.supplierNameInput);
        Assertions.assertNotNull(addRawMaterialsPage.addRawMaterialsBtn);
    }

    @Test
    public void test_validationWorksCorrectly() {
        addRawMaterialsPage.addRawMaterialsBtn.click();
        Assertions.assertNotNull(addRawMaterialsPage.getTypeInputError());
        Assertions.assertNotNull(addRawMaterialsPage.getQuantityInputError());
        Assertions.assertNotNull(addRawMaterialsPage.getDeliveredOnInputError());
        Assertions.assertNotNull(addRawMaterialsPage.getSupplierNameInputError());
    }
}
