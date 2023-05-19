package cz.cvut.fel.ts1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"bm-search\"]")
    private WebElement searchField;

    @FindBy(xpath = "/html/body/header/div[1]/div[1]/div[1]/form/fieldset/button")
    private WebElement submitButton;

    @FindBy(xpath = "/html/body/main/div[2]/div/h1/span")
    private WebElement searchResult;

    @FindBy(xpath = "/html/body/main/div[2]/p")
    private WebElement noResultsFoundText;

    private LoginPage loginPage;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        loginPage = new LoginPage(driver);
    }

    public void searchFor(String searchTerm) {
        searchField.sendKeys(searchTerm);
        submitButton.click();
    }

    public String getSearchResultText() {
        return searchResult.getText();
    }

    public String getNoResultsFoundText() {
        return noResultsFoundText.getText();
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }
}
