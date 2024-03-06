package cz.cvut.fel.ts1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

/**
 * @author Aleksandr Kross
 * @author Vladyslav Hordiienko
 *
 */

class CartPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private CartPage cartPage;
    private FavouriteListPage favouriteListPage;
    private String loginUrl = "https://www.pinkorblue.cz/Konto/";


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=\"\"");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
        favouriteListPage = new FavouriteListPage(driver);

        driver.get(loginUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void login() {
        loginPage.acceptCookie();
        loginPage.setEmail("qwerty12@cz.cz");
        loginPage.setPassword("qwerty12@cz.cz");
        loginPage.clickLogin();
    }

    @Test
    public void testAddToCart() {

        login();
        // Search item
        String productName = "pampers";
        cartPage.searchProduct(productName);
        cartPage.openProductDetails();

        // Click on the "Add to Cart" button
        cartPage.clickAddToCartButton();

        // Click on the "Go to Cart" button
        cartPage.clickGoToCartButton();

        // Verify the product title in the cart
        String expectedTitle = "Pampers Sensitive Vlhčené ubrousky 15 balení = 1200 vlhčených...";
        String actualTitle = cartPage.getAddedProductTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);

    }

    @Test
    public void testCheckProductInFavorite() {
        // Login
        login();
        // Search item
        String productName = "pampers";
        cartPage.searchProduct(productName);
        cartPage.openProductDetails();

        favouriteListPage.clickFav1();
        favouriteListPage.clickFav2();
        String expectedText = "Pampers Premium Protection , velikost 6 Extra Large , 13 kg+, měsíční balení (1x 144 plen)";
        assertEquals(expectedText, favouriteListPage.getTextFav());

    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
