package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.guest.RegisterPage;
import bg.softuni.pages.logged.*;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class RawMaterialsDetailsPageTests extends BaseTest {

    private LoginPage loginPage;
    private AllRawMaterialsPage allRawMaterials;
    private RawMaterialDetailsPage rawMaterialDetailsPage;
    private RegisterPage registerPage;
    private UsersProfilePage usersProfilePage;


    @Test
    public void test_pageLoadsCorrectly() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_RAW_MATERIALS_RESOURCE);

        allRawMaterials = new AllRawMaterialsPage(chromeDriver);

        WebElement firstRawMaterialDetails = allRawMaterials.getFirstRawMaterialsDetails();

        if(firstRawMaterialDetails == null) {
            Assertions.fail("No raw materials present in DB.");
        }

        firstRawMaterialDetails.click();

        rawMaterialDetailsPage = new RawMaterialDetailsPage(chromeDriver);

        Assertions.assertEquals(rawMaterialDetailsPage.RAW_MATERIAL_DETAILS_PAGE_HEADER, rawMaterialDetailsPage.rawMaterialDetailsTitle.getText());
        Assertions.assertNotNull(rawMaterialDetailsPage.typeInput);
        Assertions.assertNotNull(rawMaterialDetailsPage.quantityInput);
        Assertions.assertNotNull(rawMaterialDetailsPage.deliveredAtInput);
        Assertions.assertNotNull(rawMaterialDetailsPage.supplierNameInput);
        Assertions.assertNotNull(rawMaterialDetailsPage.updateBtn);
        Assertions.assertNotNull(rawMaterialDetailsPage.deleteBtn);
    }

    @Test
    public void test_productRawMaterialUpdate_unsuccessfully() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_RAW_MATERIALS_RESOURCE);

        allRawMaterials = new AllRawMaterialsPage(chromeDriver);

        WebElement firstRawMaterialDetails = allRawMaterials.getFirstRawMaterialsDetails();

        if(firstRawMaterialDetails == null) {
            Assertions.fail("No raw materials present in DB.");
        }

        firstRawMaterialDetails.click();

        rawMaterialDetailsPage = new RawMaterialDetailsPage(chromeDriver);

        rawMaterialDetailsPage.updateBtn.click();

        Assertions.assertNotNull(rawMaterialDetailsPage.getUnsuccessfulAlert());
        Assertions.assertEquals(rawMaterialDetailsPage.UNSUCCESSFUL_UPDATE_ALERT, rawMaterialDetailsPage.getUnsuccessfulAlert().getText());
    }

    @Test
    public void test_productRawMaterialUpdate_accessDenied() {
        chromeDriver.navigate().to(getGlobalUrl() + REGISTER_RESOURCE);

        registerPage = new RegisterPage(chromeDriver);

        int rnd = new Random().nextInt( 9999);
        String testFirstName = "fname" + rnd;
        String testLastName = "lname" + rnd;
        String testUsername = "user" + rnd;
        String testEmail =  rnd + "@1.c";
        String testPassword = "12345" + rnd;
        String testConfirmPassword = "12345" + rnd;
        registerPage.registerUser(testFirstName, testLastName, testUsername,
                testEmail, testPassword, testConfirmPassword);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(testUsername, testPassword);

        chromeDriver.navigate().to(getGlobalUrl() + ALL_RAW_MATERIALS_RESOURCE);

        allRawMaterials = new AllRawMaterialsPage(chromeDriver);

        WebElement firstRawMaterialDetails = allRawMaterials.getFirstRawMaterialsDetails();

        if(firstRawMaterialDetails == null) {
            Assertions.fail("No raw materials present in DB.");
        }

        firstRawMaterialDetails.click();

        rawMaterialDetailsPage = new RawMaterialDetailsPage(chromeDriver);

        rawMaterialDetailsPage.updateBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(getGlobalUrl() + USER_PROFILE_RESOURCE);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }

    @Test
    public void test_productRawMaterialDelete_accessDenied() {
        chromeDriver.navigate().to(getGlobalUrl() + REGISTER_RESOURCE);

        registerPage = new RegisterPage(chromeDriver);

        int rnd = new Random().nextInt( 9999);
        String testFirstName = "fname" + rnd;
        String testLastName = "lname" + rnd;
        String testUsername = "user" + rnd;
        String testEmail =  rnd + "@1.c";
        String testPassword = "12345" + rnd;
        String testConfirmPassword = "12345" + rnd;
        registerPage.registerUser(testFirstName, testLastName, testUsername,
                testEmail, testPassword, testConfirmPassword);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(testUsername, testPassword);

        chromeDriver.navigate().to(getGlobalUrl() + ALL_RAW_MATERIALS_RESOURCE);

        allRawMaterials = new AllRawMaterialsPage(chromeDriver);

        WebElement firstRawMaterialDetails = allRawMaterials.getFirstRawMaterialsDetails();

        if(firstRawMaterialDetails == null) {
            Assertions.fail("No raw materials present in DB.");
        }

        firstRawMaterialDetails.click();

        rawMaterialDetailsPage = new RawMaterialDetailsPage(chromeDriver);

        rawMaterialDetailsPage.deleteBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(getGlobalUrl() + USER_PROFILE_RESOURCE);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }

    @Test
    public void test_productRawMaterialDelete_successfully() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_RAW_MATERIALS_RESOURCE);

        allRawMaterials = new AllRawMaterialsPage(chromeDriver);

        int rowsBefore = allRawMaterials.getCountTableRows();

        WebElement firstRawMaterialDetails = allRawMaterials.getFirstRawMaterialsDetails();

        if(firstRawMaterialDetails == null) {
            Assertions.fail("No raw materials present in DB.");
        }

        firstRawMaterialDetails.click();

        rawMaterialDetailsPage = new RawMaterialDetailsPage(chromeDriver);

        rawMaterialDetailsPage.deleteBtn.click();

        allRawMaterials = new AllRawMaterialsPage(chromeDriver);

        int rowsAfter = allRawMaterials.getCountTableRows();

        Assertions.assertEquals(getGlobalUrl() + ALL_RAW_MATERIALS_RESOURCE, chromeDriver.getCurrentUrl());
        Assertions.assertTrue(rowsBefore > rowsAfter);

    }
}
