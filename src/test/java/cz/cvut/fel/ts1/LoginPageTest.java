package cz.cvut.fel.ts1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;


public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private String loginUrl = "https://www.pinkorblue.cz/Konto/";


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=\"\"");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);

        driver.get(loginUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Test
    public void testSuccessLogin(){

        driver.get(loginUrl);
        loginPage.acceptCookie();
        loginPage.setEmail("qwerty12@cz.cz");
        loginPage.setPassword("qwerty12@cz.cz");
        loginPage.clickLogin();

        // Verify successful login message
        String expectedMessage = "Dobrý den, Jiri";
        String actualMessage = driver.findElement(By.cssSelector(".sidemenu__title")).getText();
        Assertions.assertEquals(expectedMessage, actualMessage);

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_for_login.csv", numLinesToSkip = 1)
    public void testIncorrectLogin(String email, String password) {
        driver.get(loginUrl);
        loginPage.acceptCookie();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        // Verify unsuccessful login message
        String expectedMessage = "Přihlášení není možné - překontrolujte prosím Vaši emailovou adresu a heslo.";
        String actualMessage = driver.findElement(By.xpath("/html/body/div[3]/div[1]/span")).getText();
        Assertions.assertEquals(expectedMessage, actualMessage);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}