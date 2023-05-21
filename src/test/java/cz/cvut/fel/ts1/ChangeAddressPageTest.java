package cz.cvut.fel.ts1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ChangeAddressPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ChangeAddressPage changeAddressPage;
    private String loginUrl = "https://www.pinkorblue.cz/Konto/";


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=\"\"");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
        changeAddressPage = new ChangeAddressPage(driver);
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
    //Here must be CSV
    @Test
    public void changeAddress(){
        login();
        changeAddressPage.clickChangeAddressB();
        changeAddressPage.setJmeno("Martin");
        changeAddressPage.setPrijmeni("Klima");
        changeAddressPage.setUlice("Dejvicka");
        changeAddressPage.setCisloDomu("2");
        changeAddressPage.setPsc("16800");
        changeAddressPage.setMesto("Brno");
        changeAddressPage.setPhone("123456789");

        changeAddressPage.ulozit();
        Assertions.assertEquals("Martin", changeAddressPage.getJmenoText());
        Assertions.assertEquals("Klima", changeAddressPage.getPrijmeniText());
        Assertions.assertEquals("Dejvicka", changeAddressPage.getUliceText());
        Assertions.assertEquals("2", changeAddressPage.getCisloDomuText());
        Assertions.assertEquals("16800", changeAddressPage.getPscText());
        Assertions.assertEquals("Brno", changeAddressPage.getMestoText());
        Assertions.assertEquals("123456789", changeAddressPage.getPhoneText());
    }



    @Test
    public void changeAddressIncorrectJmeno(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setJmeno("+ěš123123");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Vyplňte prosím všechna pole označená \"*\"!", changeAddressPage.getErrorMestoText());
    }

    @Test
    public void changeAddressIncorrectPrijmeni(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setPrijmeni("+123123");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Vyplňte prosím všechna pole označená \"*\"!", changeAddressPage.getErrorMestoText());
    }

    @Test
    public void changeAddressIncorectUlice(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setUlice("+ěš123123");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Vyplňte prosím všechna pole označená \"*\"!", changeAddressPage.getErrorMestoText());
    }

    @Test
    public void changeAddressIncorrectCisloDomu(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setCisloDomu("qwe");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Vyplňte prosím všechna pole označená \"*\"!", changeAddressPage.getErrorMestoText());
    }


    @Test
    public void changeAddressIncorrectPsc(){
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setPsc("168asd00");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Vyplňte prosím všechna pole označená \"*\"!", changeAddressPage.getErrorPscText());
    }



    @Test
    public void changeAddressIncorrectMesto(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setMesto("+123123");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Vyplňte prosím všechna pole označená \"*\"!", changeAddressPage.getErrorMestoText());
    }

    @Test
    public void changeAddressIncorrectTelefon(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setPhone("qweqew");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Vyplňte prosím všechna pole označená \"*\"!", changeAddressPage.getErrorMestoText());
    }



    @Test
    public void changeAddressNameSecondNameToNull(){
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setJmeno("");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Křestní jméno je povinná informace", changeAddressPage.getErrorMestoText());

        changeAddressPage.setPrijmeni("");
        changeAddressPage.ulozit();
        Assertions.assertEquals("Příjmení je povinná informace", changeAddressPage.getErrorMestoText());

    }




//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
}