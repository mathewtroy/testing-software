package cz.cvut.fel.ts1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


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
//      driver.findElement(By.cssSelector(".js-cookie-modal-accept-all")).click();


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
        String expectedText = "Hledej \"shit\" (157 Výrobky)";
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

    /**
     * The purpose of the performSearch method is to perform a search on a web page
     * and then verify that the search results contain the expected text and that the URL
     * of the search results page matches the expected URL.
     *
     * The Java doc comment at the beginning of the code snippet provides documentation for the method
     * parameters. The @param tag is used to document each parameter, with the parameter name and
     * a brief description following the tag. In this case, the @param tag is used three times,
     * once for each parameter, to document what each parameter represents.
     *
     * @param searchText - String that represents the text to be searched for.
     * @param expectedText - String that represents the expected text that should appear in the search results.
     * @param expectedUrl - String that represents the expected URL of the search results page.
     *
     */
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


    /**
     * This test case verifies that an empty shopping cart message is displayed on the shopping cart page.
     *
     * The purpose of the testEmptyCart method is to verify that an empty shopping cart message is displayed on a web page. The method performs the following steps:
     * Opens the shopping cart page by navigating to the URL "https://www.pinkorblue.cz/kosik/".
     * Locates an empty cart message on the page using a CSS class name selector.
     * Asserts that the empty cart message is displayed by calling the isDisplayed method on the
     * WebElement object that represents the message element.
     * Uses the Assertions class to verify that the boolean expression emptyCartMessage.isDisplayed()
     * evaluates to true.
     *
     */


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

    // ТЕСТ ОДНОРАЗОВЬІЙ
    // НАДО КАК ТО ПОДКЛЮЧИТЬ CSV
    @Test
    public void testRegistrace(){
        driver.get("https://www.pinkorblue.cz/Erstanmeldung/");

        // Accept cookies
        driver.findElement(By.cssSelector(".cookie-banner__button.js-cookie-modal-accept-all")).click();
        driver.findElement(By.cssSelector("#vorname")).sendKeys("Albert");
        driver.findElement(By.cssSelector("#nachname")).sendKeys("Švehla");
        driver.findElement(By.cssSelector("#email")).sendKeys("albertsvehla@gmail.com");
        driver.findElement(By.cssSelector("#pwd")).sendKeys("albertsvehla@gmail.com");
        driver.findElement(By.cssSelector("#pwdrep")).sendKeys("albertsvehla@gmail.com");


        driver.findElement(By.cssSelector("#checkOutForm > div.form-group.margin-top > div > button")).click();

        String expectedLink = "https://www.pinkorblue.cz/Konto/";
        String expectedMessage = "Dobrý den, Albert";
        Assertions.assertEquals(expectedLink, driver.getCurrentUrl());
        Assertions.assertEquals(expectedMessage, driver.findElement(By.cssSelector("#siteMain > div > div.col-sm-3 > div > div.sidemenu__title")).getText());


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



    @Test
    public void testOrderPlacement() {
        // Login
        testAddToCart();

        // Go to checkout
        WebElement toCheckoutButton =
                driver.findElement(By.xpath("//*[@id='to_checkout_button']/form/button"));
        toCheckoutButton.click();

        //        // Fill in personal details
        //        WebElement firstNameInput = driver.findElement(By.xpath("//*[@id='vorname-l']"));
        //        firstNameInput.sendKeys("Jiri");
        //        WebElement lastNameInput = driver.findElement(By.xpath("//*[@id='nachname-l']"));
        //        lastNameInput.sendKeys("Prochazka");
        //        WebElement streetInput = driver.findElement(By.xpath("//*[@id='strasse-l']"));
        //        streetInput.sendKeys("Technicka");
        //        WebElement houseNumberInput = driver.findElement(By.xpath("//*[@id='hausnr-l']"));
        //        houseNumberInput.sendKeys("2");
        //        WebElement postcodeInput = driver.findElement(By.xpath("//*[@id='plz-l']"));
        //        postcodeInput.sendKeys("16900");
        //        WebElement cityInput = driver.findElement(By.xpath("//*[@id='ort-l']"));
        //        cityInput.sendKeys("Praha");
        //        WebElement countrySelect = driver.findElement(By.xpath("//*[@id='country']"));
        //        countrySelect.sendKeys("Česká republika");
        //        WebElement phoneInput = driver.findElement(By.xpath("//*[@id='telefon-l']"));
        //        phoneInput.sendKeys("777888666");


        // Agree to terms and conditions

        // Checkbox element
        WebElement checkbox =
                driver.findElement(By.xpath("//*[@id=\"checkOutForm\"]/div[10]/div[1]/div/label/span"));

        // Checking if the checkbox is not selected
        if (!checkbox.isSelected()) {
            // If the checkbox is not selected, then click on it
            checkbox.click();
        }

        // Checking if the checkbox is selected
        if (checkbox.isSelected()) {
            // If the checkbox is selected, then we do the necessary actions

            // Place order
            WebElement placeOrderButton = driver.findElement(By.xpath("//*[@id='checkOutForm']/div[4]/div[1]/div/button"));
            placeOrderButton.click();

            // Check payment methods page
            String paymentMethodsText = driver.findElement(By.xpath("//p[contains(text(),'Způsoby platby')]")).getText();
            assertEquals("Způsoby platby", paymentMethodsText);
        }


        //driver.quit();
        //tearDown();

    }

    @Test
    public void testCheckOrder() {

        testOrderPlacement();


////*[@id="payment3"]/div/label/div[1]/span
//// *[@id="payment3"]/div/label/div[1]/span
//        // Choose payment method
//        WebElement toMethodPaymentButton = driver.findElement(By.xpath("//*[@id=\"payment3\"]/div/label/div[1]/span"));
//        toMethodPaymentButton.click();


//        //*[@id="siteMain"]/button
//*[@id="siteMain"]/div[2]/div[1]/p/button


        // Go to payment page
        // K pokladne
        WebElement toPaymentButton = driver.findElement(By.xpath("//*[@id=\"siteMain\"]/button"));
        toPaymentButton.click();
        //*[@id="to_checkout_button"]/form/button


//        WebElement toPaymentButton = driver.findElement(By.cssSelector("#siteMain > button"));
//        toPaymentButton.click();


        // Check name
        WebElement nameElement =
                driver.findElement(By.cssSelector("#siteMain > div.row > div:nth-child(1) > form > div > ul > li:nth-child(1) > span:nth-child(3)"));

        //driver.findElement(By.cssSelector("//*[@id='siteMain']/div[3]/div[1]/form/div/ul/li[1]/span[2]"));

        Assertions.assertEquals("Jiri", nameElement.getText());


        // Check lastname
        WebElement lastNameElement = driver.findElement(By.xpath("//*[@id='siteMain']/div[3]/div[1]/form/div/ul/li[1]/span[3]"));
        Assertions.assertEquals("Prochazka", lastNameElement.getText());

        // Check street
        WebElement streetElement = driver.findElement(By.xpath("//*[@id='siteMain']/div[3]/div[1]/form/div/ul/li[2]/span[1]"));
        Assertions.assertEquals("Technicka222", streetElement.getText());

        // Check street number
        WebElement streetNumberElement = driver.findElement(By.xpath("//*[@id='siteMain']/div[3]/div[1]/form/div/ul/li[2]/span[2]"));
        Assertions.assertEquals("2", streetNumberElement.getText());

        // Check zip index
        WebElement zipIndexElement = driver.findElement(By.xpath("//*[@id='siteMain']/div[3]/div[1]/form/div/ul/li[3]/span[1]"));
        Assertions.assertEquals("16900", zipIndexElement.getText());

        // Check city
        WebElement cityElement = driver.findElement(By.xpath("//*[@id='siteMain']/div[3]/div[1]/form/div/ul/li[3]/span[2]"));
        Assertions.assertEquals("Praha", cityElement.getText());

        // Check state
        WebElement stateElement = driver.findElement(By.xpath("//*[@id='siteMain']/div[3]/div[1]/form/div/ul/li[4]"));
        Assertions.assertEquals("Česká republika", stateElement.getText());

        // Check phone number
        WebElement phoneNumberElement = driver.findElement(By.xpath("//*[@id='siteMain']/div[3]/div[1]/form/div/ul/li[5]/span"));
        Assertions.assertEquals("777666888", phoneNumberElement.getText());

        //driver.quit();

        // name Jiri
        //*[@id="siteMain"]/div[3]/div[1]/form/div/ul/li[1]/span[2]

        // lastname Prochazka
        //*[@id="siteMain"]/div[3]/div[1]/form/div/ul/li[1]/span[3]

        // street Technicka
        //*[@id="siteMain"]/div[3]/div[1]/form/div/ul/li[2]/span[1]

        // number of street 2
        //*[@id="siteMain"]/div[3]/div[1]/form/div/ul/li[2]/span[2]

        // ZIP index 16900
        //*[@id="siteMain"]/div[3]/div[1]/form/div/ul/li[3]/span[1]

        // city Praha
        //*[@id="siteMain"]/div[3]/div[1]/form/div/ul/li[3]/span[2]

        // stat Ceska republika
        //*[@id="siteMain"]/div[3]/div[1]/form/div/ul/li[4]

        // phone number
        //*[@id="siteMain"]/div[3]/div[1]/form/div/ul/li[5]/span


    }

    @Test
    public void testCheckProductInFavorite() {
        // Login
        testAddToCart();


        // Go to checkout
        WebElement toFavorite =
                driver.findElement(By.xpath("/html/body/header/div[1]/div[1]/div[2]/nav/div[3]/a"));
        toFavorite.click();


        //"Pampers Sensitive Vlhčené ubrousky 15 balení = 1200 vlhčených ubrousků"
        String favText = driver.findElement(By.xpath("//*[@id=\"item-3a417d51-e740-11ed-a7ed-02d16b37478a\"]/div[1]/a")).getText();

        //*[@id="item-3a417d51-e740-11ed-a7ed-02d16b37478a"]/div[1]/a/text()
        assertEquals("Pampers Sensitive Vlhčené ubrousky 15 balení = 1200 vlhčených ubrousků", favText);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/favorite-products.csv", numLinesToSkip = 1)
    public void testCheckProductInFavorite2(String productName) {
        // Login
        testLogin();

        // Go to checkout
        WebElement toFavorite =
                driver.findElement(By.xpath("/html/body/header/div[1]/div[1]/div[2]/nav/div[3]/a"));
        toFavorite.click();

        // Check if product is in favorites
        String favText = driver.findElement(By.xpath("//*[@id=\"item-3a417d51-e740-11ed-a7ed-02d16b37478a\"]/div[1]/a")).getText();
        assertEquals(productName, favText);

    }


//    @ParameterizedTest
//    @CsvFileSource(resources = "/favorite-products.csv", numLinesToSkip = 1)
//    public void testCheckProductInFavorite3(String productName, String expectedErrorMessage) {
//        // Login
//        testLogin();
//
//        // Go to checkout
//        WebElement toFavorite =
//                driver.findElement(By.xpath("/html/body/header/div[1]/div[1]/div[2]/nav/div[3]/a"));
//        toFavorite.click();
//
//        // Check if product is in favorites
//        try {
//            String favText = driver.findElement(By.xpath("//*[@id=\"item-3a417d51-e740-11ed-a7ed-02d16b37478a\"]/div[1]/a")).getText();
//            assertEquals(productName, favText);
//        } catch (AssertionError e) {
//            assertEquals(expectedErrorMessage, e.getMessage());
//        }
//    }


}
