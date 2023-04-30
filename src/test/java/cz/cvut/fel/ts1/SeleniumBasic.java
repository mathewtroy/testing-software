package cz.cvut.fel.ts1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SeleniumBasic {
    WebDriver driver;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.pinkorblue.cz/kosik/");
        driver.manage().window().maximize();
    }

    @Test()
    public void searchBar1(){
//        driver.get("https://ts1.v-sources.eu/");
//        driver.manage().window().maximize();
////        String h1Test = driver.findElement(By.cssSelector("body > div > h1")).getAttribute("textContent");
////        Assertions.assertEquals("Flight order", h1Test);
//        driver.findElement(By.id("flight_form_firstName")).sendKeys("kytice");
//        driver.get("https://www.kytice-expres.cz/");
//        driver.manage().window().maximize();
////        String h1Test = driver.findElement(By.cssSelector("body > div > h1")).getAttribute("textContent");
////        Assertions.assertEquals("Flight order", h1Test);
//        WebElement sidebar = driver.findElement(By.cssSelector("input:nth-of-type(2)"));
//        sidebar.sendKeys("kytice");

        WebElement sidebar = driver.findElement(By.id("bm-search"));
        sidebar.sendKeys("shit");
        WebElement searchButton = driver.findElement(By.className("search__submit"));
        searchButton.click();
        WebElement test1 = driver.findElement(By.cssSelector("body > main > div > div > h1"));
        String text = test1.getText();
        String expected = "Hledej " + "\"shit\"" + " (158 Výrobky)";
        String act_url = driver.getCurrentUrl();
        Assertions.assertEquals(expected, text);
        Assertions.assertEquals("https://www.pinkorblue.cz/search/?q=shit", act_url);
    }
    @Test()
    public void searchBar2() {
        WebElement sidebar = driver.findElement(By.id("bm-search"));
        sidebar.sendKeys("pampers");
        WebElement searchButton = driver.findElement(By.className("search__submit"));
        searchButton.click();
        WebElement test1 = driver.findElement(By.cssSelector("body > main > div > div > h1"));
        String text = test1.getText();
        String expected = "Pampers" + " (54 Výrobky)";
        String act_url = driver.getCurrentUrl();
        Assertions.assertEquals(expected, text);
        Assertions.assertEquals("https://www.pinkorblue.cz/pampers/?isRedirect=true", act_url);


    }


}