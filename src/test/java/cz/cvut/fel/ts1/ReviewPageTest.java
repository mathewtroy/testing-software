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


public class ReviewPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ReviewPage reviewPage;
    private String loginUrl = "https://www.pinkorblue.cz/Konto/";


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=\"\"");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        reviewPage = new ReviewPage(driver);

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

    @ParameterizedTest
    @CsvFileSource(resources = "/data-comments.csv", numLinesToSkip = 1)
    public void testReviewComments(String authorComment, String dateComment, String textComment) {
        login();

        // Go to Review Page
        reviewPage.clickTopZnackyButton();
        reviewPage.clickSelectPhilips();
        reviewPage.clickOpenPhilips();
        reviewPage.clickReadReview();

        // Check comments
        Assertions.assertEquals(authorComment, reviewPage.getAuthorComment());
        Assertions.assertEquals(dateComment, reviewPage.getDateComment());
        Assertions.assertEquals(textComment, reviewPage.getTextComment());
    }


    @Test
    public void testUsefulCommentClick() {
        login();
        reviewPage.clickTopZnackyButton();
        reviewPage.clickSelectPhilips();
        reviewPage.clickOpenPhilips();

        // Click button "Užitečné"
        reviewPage.clickUsefulButton();

        // We need to wait for the text
        // "Děkujeme! Zpětná odezva k tomuto hodnocení byla zprostředkována."

        String actualResultText = reviewPage.getFeedbackText();

        String expextedResultText = "Děkujeme! Zpětná odezva k tomuto hodnocení byla zprostředkována.";

        Assertions.assertEquals(expextedResultText, actualResultText);
    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}