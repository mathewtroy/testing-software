package cz.cvut.fel.ts1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumBasic {


    WebDriver driver;

    @BeforeEach
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=\"\"");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
    }

    @Test
    public void openForm(){
        driver.get("https://ts1.v-sources.eu/");

        String h1Test = driver
                .findElement(By.cssSelector("body > div > h1"))
                .getAttribute("textContent");
        Assertions.assertEquals("Flight order", h1Test);

        driver.findElement(By.id("flight_form_firstName")).sendKeys("Honza");
        Select selectDestination = new Select(driver.findElement(By.id("flight_form_destination")));
        selectDestination.selectByVisibleText("London");

    }
}
