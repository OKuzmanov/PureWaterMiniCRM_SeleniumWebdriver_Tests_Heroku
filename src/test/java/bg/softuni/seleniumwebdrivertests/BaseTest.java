package bg.softuni.seleniumwebdrivertests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected final String ABOUT_RESOURCE = "about";
    protected final String LOGIN_RESOURCE = "users/login";
    protected final String REGISTER_RESOURCE = "users/register";
    protected final String HOME_RESOURCE = "home";
    protected final String ADD_ORDER_RESOURCE = "orders/add";
    protected final String ADD_CUSTOMER_RESOURCE = "customers/add";
    protected final String ADD_SUPPLIER_RESOURCE = "suppliers/add";
    protected final String ADD_PRODUCTS_RESOURCE = "products/add";
    protected final String ADD_RAW_MATERIALS_RESOURCE = "materials/add";
    protected final String ORDERS_HISTORY_RESOURCE = "orders/history";
    protected final String ALL_CUSTOMERS_RESOURCE = "customers/all";
    protected final String ALL_SUPPLIERS_RESOURCE = "suppliers/all";
    protected final String ALL_PRODUCTS_RESOURCE = "products/all";
    protected final String ALL_RAW_MATERIALS_RESOURCE = "materials/all";
    protected final String USER_PROFILE_RESOURCE = "users/profile";
    protected final String ACCESS_DENIED = "Access Denied";

    protected static WebDriver chromeDriver;

    @BeforeAll
    public static void setup() {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--remote-allow-origins=*");
//        chromeOptions.addArguments("--headless");

        chromeDriver = new ChromeDriver(chromeOptions);
    }

    @AfterEach
    void deleteAllCookies() {
        chromeDriver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        chromeDriver.quit();
    }
    public String getGlobalUrl() {
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
            Properties properties = new Properties();
            properties.load(fis);
            return properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getGlobalUsername() {
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
            Properties properties = new Properties();
            properties.load(fis);
            return properties.getProperty("username");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getGlobalPassword() {
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
            Properties properties = new Properties();
            properties.load(fis);
            return properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
