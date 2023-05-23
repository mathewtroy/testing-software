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

/**
 * @author Aleksandr Kross
 * @author Vladyslav Hordiienko
 *
 */

class ChangeAddressPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private ChangeAddressPage changeAddressPage;
    private String loginUrl = "https://www.pinkorblue.cz/Konto/";

    private String warningText = "Vyplňte prosím všechna pole označená \"*\"!";
    private String requiredName = "Křestní jméno je povinná informace";
    private String requiredSurname = "Příjmení je povinná informace";

    private String name = "Martin";
    private String surname = "Klima";
    private String street = "Dejvicka";
    private String houseNumber = "2";
    private String psc = "16800";
    private String city = "Brno";
    private String phone = "123456789";


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


    @Test
    public void changeAddress(){
        login();
        changeAddressPage.clickChangeAddressB();
        changeAddressPage.setJmeno(name);
        changeAddressPage.setPrijmeni(surname);
        changeAddressPage.setUlice(street);
        changeAddressPage.setCisloDomu(houseNumber);
        changeAddressPage.setPsc(psc);
        changeAddressPage.setMesto(city);
        changeAddressPage.setPhone(phone);

        changeAddressPage.ulozit();

        String expectedTextName = name;
        String expectedTextSurname = surname;
        String expectedTextStreet = street;
        String expectedTextHouseNumber = houseNumber;
        String expectedTextPsc = psc;
        String expectedTextCity = city;
        String expectedTextPhone = phone;

        String actualTextName = changeAddressPage.getJmenoText();
        String actualTextSurname = changeAddressPage.getPrijmeniText();
        String actualTextStreet = changeAddressPage.getUliceText();
        String actualTextHouseNumber = changeAddressPage.getCisloDomuText();
        String actualTextPsc = changeAddressPage.getPscText();
        String actualTextCity = changeAddressPage.getMestoText();
        String actualTextPhone = changeAddressPage.getPhoneText();

        Assertions.assertEquals(expectedTextName, actualTextName);
        Assertions.assertEquals(expectedTextSurname, actualTextSurname);
        Assertions.assertEquals(expectedTextStreet, actualTextStreet);
        Assertions.assertEquals(expectedTextHouseNumber, actualTextHouseNumber);
        Assertions.assertEquals(expectedTextPsc, actualTextPsc);
        Assertions.assertEquals(expectedTextCity, actualTextCity);
        Assertions.assertEquals(expectedTextPhone, actualTextPhone);
    }



    @Test
    public void changeAddressIncorrectJmeno(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setJmeno("+ěš123123");
        changeAddressPage.ulozit();
        String actualTextName = changeAddressPage.getErrorNameText();
        Assertions.assertEquals(warningText, actualTextName);
    }

    @Test
    public void changeAddressIncorrectPrijmeni(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setPrijmeni("+123123");
        changeAddressPage.ulozit();
        String actualTextSurname = changeAddressPage.getErrorSurnameText();
        Assertions.assertEquals(warningText, actualTextSurname);
    }

    @Test
    public void changeAddressIncorectUlice(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setUlice("+ěš123123");
        changeAddressPage.ulozit();
        String actualTextStreet = changeAddressPage.getErrorUliceText();
        Assertions.assertEquals(warningText, actualTextStreet);
    }

    @Test
    public void changeAddressIncorrectCisloDomu(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setCisloDomu("qwe");
        changeAddressPage.ulozit();
        String actualTextHouseNumber = changeAddressPage.getErrorUliceText();
        Assertions.assertEquals(warningText, actualTextHouseNumber);
    }


    @Test
    public void changeAddressIncorrectPsc(){
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setPsc("168asd00");
        changeAddressPage.ulozit();
        String actualTextPsc = changeAddressPage.getErrorPscText();
        Assertions.assertEquals(warningText, actualTextPsc);
    }



    @Test
    public void changeAddressIncorrectMesto(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setMesto("+123123");
        changeAddressPage.ulozit();
        String actualTextCity = changeAddressPage.getErrorPscText();
        Assertions.assertEquals(warningText, actualTextCity);
    }

    @Test
    public void changeAddressIncorrectTelefon(){ // CHYBA STRANKY
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setPhone("qweqew");
        changeAddressPage.ulozit();
        String actualTextPhone = changeAddressPage.getErrorTelefonText();
        Assertions.assertEquals(warningText, actualTextPhone);
    }



    @Test
    public void changeAddressNameSecondNameToNull(){
        login();
        changeAddressPage.clickChangeAddressB();

        changeAddressPage.setJmeno("");
        changeAddressPage.ulozit();
        String actualTextName = changeAddressPage.getErrorNameText();
        Assertions.assertEquals(requiredName, actualTextName);

        changeAddressPage.setPrijmeni("");
        changeAddressPage.ulozit();
        String actualTextSurname = changeAddressPage.getErrorSurnameText();
        Assertions.assertEquals(requiredSurname, actualTextSurname);

    }




    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}