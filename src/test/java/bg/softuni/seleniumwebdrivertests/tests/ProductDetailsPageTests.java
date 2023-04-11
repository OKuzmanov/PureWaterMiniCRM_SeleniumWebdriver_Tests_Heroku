package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.LoginPage;
import bg.softuni.pages.guest.RegisterPage;
import bg.softuni.pages.logged.*;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Random;

public class ProductDetailsPageTests extends BaseTest {

    private LoginPage loginPage;
    private AllProductsPage allProductsPage;
    private ProductDetailsPage productDetailsPage;
    private RegisterPage registerPage;
    private UsersProfilePage usersProfilePage;
    private AddProductsPage addProductsPage;

    @Test
    public void test_pageLoadsCorrectly() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_PRODUCTS_RESOURCE);

        allProductsPage = new AllProductsPage(chromeDriver);

        WebElement firstProductsDetails = allProductsPage.getFirstProductDetails();

        if(firstProductsDetails == null) {
            Assertions.fail("No products present in DB.");
        }

        firstProductsDetails.click();

        productDetailsPage = new ProductDetailsPage(chromeDriver);

        Assertions.assertEquals(productDetailsPage.PRODUCT_DETAILS_PAGE_HEADER, productDetailsPage.productDetailsTitle.getText());
        Assertions.assertNotNull(productDetailsPage.typeInput);
        Assertions.assertNotNull(productDetailsPage.quantityInput);
        Assertions.assertNotNull(productDetailsPage.productionDateInput);
        Assertions.assertNotNull(productDetailsPage.updateBtn);
        Assertions.assertNotNull(productDetailsPage.deleteBtn);
    }

    @Test
    public void test_productDetailsUpdate_unsuccessfully() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_PRODUCTS_RESOURCE);

        allProductsPage = new AllProductsPage(chromeDriver);

        WebElement firstProductDetails = allProductsPage.getFirstProductDetails();

        if(firstProductDetails == null) {
            Assertions.fail("No products present in DB.");
        }

        firstProductDetails.click();

        productDetailsPage = new ProductDetailsPage(chromeDriver);

        productDetailsPage.updateBtn.click();

        Assertions.assertNotNull(productDetailsPage.getUnsuccessfulAlert());
        Assertions.assertEquals(productDetailsPage.UNSUCCESSFUL_UPDATE_ALERT, productDetailsPage.getUnsuccessfulAlert().getText());
    }

    @Test
    public void test_productDetailsUpdate_accessDenied() {
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

        chromeDriver.navigate().to(getGlobalUrl() + ALL_PRODUCTS_RESOURCE);

        allProductsPage = new AllProductsPage(chromeDriver);

        WebElement firstProductDetails = allProductsPage.getFirstProductDetails();

        if(firstProductDetails == null) {
            Assertions.fail("No products present in DB.");
        }

        firstProductDetails.click();

        productDetailsPage = new ProductDetailsPage(chromeDriver);

        productDetailsPage.updateBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(getGlobalUrl() + USER_PROFILE_RESOURCE);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }

    @Test
    public void test_productDetailsDelete_successfully() {
        chromeDriver.navigate().to(getGlobalUrl() + LOGIN_RESOURCE);

        loginPage = new LoginPage(chromeDriver);

        loginPage.loginUser(getGlobalUsername(), getGlobalPassword());

        chromeDriver.navigate().to(getGlobalUrl() + ALL_PRODUCTS_RESOURCE);

        allProductsPage = new AllProductsPage(chromeDriver);

        int rowsBefore = allProductsPage.getCountTableRows();

        WebElement firstProductDetails = allProductsPage.getFirstProductDetails();

        if(firstProductDetails == null) {
            Assertions.fail("No products present in DB.");
        }

        firstProductDetails.click();

        productDetailsPage = new ProductDetailsPage(chromeDriver);

        productDetailsPage.deleteBtn.click();

        allProductsPage = new AllProductsPage(chromeDriver);

        int rowsAfter = allProductsPage.getCountTableRows();

        Assertions.assertEquals(getGlobalUrl() + ALL_PRODUCTS_RESOURCE, chromeDriver.getCurrentUrl());
        Assertions.assertTrue(rowsBefore > rowsAfter);
    }

    @Test
    public void test_productDetailsDelete_accessDenied() {
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

        chromeDriver.navigate().to(getGlobalUrl() + ALL_PRODUCTS_RESOURCE);

        allProductsPage = new AllProductsPage(chromeDriver);

        WebElement firstProductDetails = allProductsPage.getFirstProductDetails();

        if(firstProductDetails == null) {
            Assertions.fail("No products present in DB.");
        }

        firstProductDetails.click();

        productDetailsPage = new ProductDetailsPage(chromeDriver);

        productDetailsPage.deleteBtn.click();

        Assertions.assertEquals(ACCESS_DENIED, chromeDriver.getTitle());

        chromeDriver.navigate().to(getGlobalUrl() + USER_PROFILE_RESOURCE);

        usersProfilePage = new UsersProfilePage(chromeDriver);

        usersProfilePage.deleteBtn.click();
    }
}
