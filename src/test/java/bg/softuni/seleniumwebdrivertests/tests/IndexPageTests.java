package bg.softuni.seleniumwebdrivertests.tests;

import bg.softuni.pages.guest.IndexPage;
import bg.softuni.seleniumwebdrivertests.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class IndexPageTests extends BaseTest {

    private IndexPage indexPage;

    @BeforeEach
    public void preTestsSetup() {
        chromeDriver.navigate().to(getGlobalUrl());

        indexPage = new IndexPage(chromeDriver);
    }

    @Test
    void test_indexPageLoadsCorrectly() {

        String resultMessage = indexPage.welcomeMessage.getText();

        Assertions.assertEquals(indexPage.WELCOME_MESSAGE, resultMessage);
        Assertions.assertNotNull(indexPage.navigationBar);
        Assertions.assertNotNull(indexPage.footer);
    }

    @Test
    void test_testNavbarLinks_about() {

        indexPage.navigationBar.findElement(By.partialLinkText("About")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + ABOUT_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_login() {

        indexPage.navigationBar.findElement(By.partialLinkText("Login")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + LOGIN_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_register() {

        indexPage.navigationBar.findElement(By.partialLinkText("Register")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl() + REGISTER_RESOURCE, currentUrl);
    }

    @Test
    void test_testNavbarLinks_logo() {

        indexPage.navigationBar.findElement(By.cssSelector("a.navbar-brand")).click();

        String currentUrl = chromeDriver.getCurrentUrl();

        Assertions.assertEquals(getGlobalUrl(), currentUrl);
    }
}
