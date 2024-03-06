package cz.cvut.fel.ts1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Aleksandr Kross
 *
 */

public class LoginPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"js-cookie-banner\"]/div/div[2]/div")
    private WebElement acceptCookieButton;

    @FindBy(xpath = "//*[@id=\"loginUser\"]")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id=\"loginPwd\"]")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id=\"loginButton\"]")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setEmail(String email) {
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void acceptCookie() {
        acceptCookieButton.click();
    }

}
