package cz.cvut.fel.ts1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * @author Aleksandr Kross
 * @author Vladyslav Hordiienko
 *
 */

public class SearchPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private String loginUrl = "https://www.pinkorblue.cz/Konto/";

    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=\"\"");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);

        driver.get(loginUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    private void login() {
        loginPage.acceptCookie();
        loginPage.setEmail("qwerty12@cz.cz");
        loginPage.setPassword("qwerty12@cz.cz");
        loginPage.clickLogin();
    }


    @Test
    public void testSearchResultText() {
        login();
        String searchTerm = "smartphone";
        searchPage.searchFor(searchTerm);

        // Found items
        String searchResultText = searchPage.getSearchResultText();
        String expextedResultText = "(3 Výrobky)";
        Assertions.assertEquals(expextedResultText, searchResultText);
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/data-search.csv", numLinesToSkip = 1)
    public void testNoResultsFoundText(String searchTerm, String expectedNoResultsFoundText) {
        login();
        searchPage.searchFor(searchTerm);

        // Items not found
        String noResultsFoundText = searchPage.getNoResultsFoundText();
        Assertions.assertEquals(expectedNoResultsFoundText, noResultsFoundText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}