package cz.cvut.fel.ts1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class SeleniumBasic {

    private WebDriver driver;

    /**
     * The @BeforeEach annotation indicates that the annotated method should be executed
     * before each test method in the class. In this case, the setUp method sets up the environment
     * for the tests by initializing a Chrome WebDriver instance, setting up the ChromeOptions
     * to whitelist IPs, navigating to the website's shopping cart page
     * (https://www.pinkorblue.cz/kosik/) and maximizing the browser window.
     *
     * The WebDriverManager.chromedriver().setup() call downloads and sets up the appropriate version
     * of the ChromeDriver executable binary for the current platform, which makes it possible to automate
     * the Chrome browser without needing to manually download and manage the driver executable.
     *
     * Overall, this method prepares the environment needed to run the tests in the class.
     *
     */
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=\"\"");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.get("https://www.pinkorblue.cz/kosik/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        driver.findElement(By.cssSelector(".js-cookie-modal-accept-all")).click();


    }

    /**
     * This code is a JUnit annotation for a method called tearDown().
     * The @AfterEach annotation indicates that the tearDown() method will be executed
     * after each test method in the test class.
     *
     * The tearDown() method is responsible for closing and quitting the WebDriver instance,
     * which was created and used in the setUp() method of the test class.
     * This is a best practice in Selenium testing to ensure that all resources
     * are properly released and cleaned up after each test so that subsequent tests
     * are not affected by any remaining state or resources from previous tests.
     *
     */
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /**
     * The test is designed to perform a search for the keyword "shit"
     * on the website https://www.pinkorblue.cz and verify that the resulting page
     * displays the expected text "Hledej "shit" (159 Výrobky)"
     * and the expected URL "https://www.pinkorblue.cz/search/?q=shit".
     * The expected text and URL are stored in local variables expectedText and expectedUrl, respectively.
     * The test calls the performSearch() method, passing in the search keyword
     * and the expected text and URL as parameters.
     *
     */
    @DisplayName("Search for \"shit\"")
    @Test
    public void searchBar1() {
        String expectedText = "Hledej \"shit\" (159 Výrobky)";
        String expectedUrl = "https://www.pinkorblue.cz/search/?q=shit";
        performSearch("shit", expectedText, expectedUrl);
    }

    /**
     * This code defines a JUnit test method named searchBar2() which tests the search functionality
     * of a website for the keyword "pampers". It sets the expected text to be "Pampers (54 Výrobky)"
     * and the expected URL to be "https://www.pinkorblue.cz/pampers/?isRedirect=true".
     * The test uses the performSearch() method to perform the search with the given search term
     * and verify that the actual search results page has the expected text and URL.
     *
     */
    @DisplayName("Search for \"pampers\"")
    @Test
    public void searchBar2() {
        String expectedText = "Pampers (54 Výrobky)";
        String expectedUrl = "https://www.pinkorblue.cz/pampers/?isRedirect=true";
        performSearch("pampers", expectedText, expectedUrl);
    }

    private void performSearch(String searchText, String expectedText, String expectedUrl) {
        WebElement sidebar = driver.findElement(By.id("bm-search"));
        sidebar.sendKeys(searchText);
        WebElement searchButton = driver.findElement(By.className("search__submit"));
        searchButton.click();
        WebElement resultTitle = driver.findElement(By.cssSelector("body > main > div > div > h1"));
        String text = resultTitle.getText();
        String act_url = driver.getCurrentUrl();
        Assertions.assertEquals(expectedText, text);
        Assertions.assertEquals(expectedUrl, act_url);
    }





    @Test
    public void testEmptyCart() {
        // Opening the shopping cart page
        driver.get("https://www.pinkorblue.cz/kosik/");

        // Checking that an empty cart message is displayed
        WebElement emptyCartMessage = driver.findElement(By.className("basket-empty__text"));
        Assertions.assertTrue(emptyCartMessage.isDisplayed());
    }


    @Test
    public void testLogin() {
        driver.get("https://www.pinkorblue.cz/Konto/");

        // Accept cookies
        driver.findElement(By.cssSelector(".cookie-banner__button.js-cookie-modal-accept-all")).click();

        // Enter login email and password
        driver.findElement(By.cssSelector("#loginUser")).sendKeys("qwerty12@cz.cz");
        driver.findElement(By.cssSelector("#loginPwd")).sendKeys("qwerty12@cz.cz");

        // Click on login button
        driver.findElement(By.cssSelector("#loginButton")).click();

        // Verify successful login message
        String expectedMessage = "Dobrý den, Jiri";
        String actualMessage = driver.findElement(By.cssSelector(".sidemenu__title")).getText();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void testAddToCart() {

        testLogin();

        driver.get("https://www.pinkorblue.cz/pampers-sensitive-vlhcene-ubrousky-15-baleni-1200-vlhcenych-ubrousku-a272654.html");

        // Press the button "Vložit do košíku"
        WebElement addToCartButton = driver.findElement(By.cssSelector("#basket-submit"));
        addToCartButton.click();

        // Press the button "Do košíku"
        WebElement viewCartButton = driver.findElement(By.xpath("/html/body/main/div[3]/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/a[1]"));
        viewCartButton.click();

        // Check if the added item is in the cart
        String expectedItemTitle = "Pampers Sensitive Vlhčené ubrousky 15 balení = 1200 vlhčených...";
        WebElement itemTitle = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/form/li/ul/a"));
        Assertions.assertEquals(expectedItemTitle, itemTitle.getText());
    }


//  Тот же тест
//  С новыми фичами
    @ParameterizedTest
    @CsvSource({
            "Pampers Sensitive Vlhčené ubrousky 15 balení = 1200 vlhčených..."
    })
    public void testAddToCart2(String expectedItemTitle) {

        testLogin();

        driver.get("https://www.pinkorblue.cz/pampers-sensitive-vlhcene-ubrousky-15-baleni-1200-vlhcenych-ubrousku-a272654.html");

        // Press the button "Vložit do košíku"
        WebElement addToCartButton = driver.findElement(By.cssSelector("#basket-submit"));
        addToCartButton.click();

        // Press the button "Do košíku"
        WebElement viewCartButton = driver.findElement(By.xpath("/html/body/main/div[3]/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/a[1]"));
        viewCartButton.click();

        // Check if the added item is in the cart
        WebElement itemTitle = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/ul/form/li/ul/a"));
        Assertions.assertEquals(expectedItemTitle, itemTitle.getText());
    }


}