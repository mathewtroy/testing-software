package cz.cvut.fel.ts1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"bm-search\"]")
    private WebElement searchField;

    @FindBy(xpath = "/html/body/header/div[1]/div[1]/div[1]/form/fieldset/button")
    private WebElement searchSubmitButton;

    @FindBy(xpath = "/html/body/main/div[3]/div/ul/li[1]/a/article/div[3]/p/span")
    private WebElement firstProductLink;

    @FindBy(xpath = "//*[@id=\"basket-submit\"]")
    private WebElement addToCartButton;

    @FindBy(xpath = "/html/body/main/div[3]/div[1]/div[2]/div[3]/div[1]/div[2]/div/div[2]/a[1]")
    private WebElement goToCartButton;

    @FindBy(xpath = "/html/body/div[3]/div[1]/div[1]/ul/form/li/ul/a")
    private WebElement addedProductTitle;

    @FindBy(xpath = "//*[@id=\"to_checkout_button\"]/form/button\n")
    private WebElement goToCartAgree;

    @FindBy(xpath = "/html/body/header/div[1]/div[1]/div[2]/nav/div[4]")
    private WebElement goToCart;

//TODO because does not accept agreement

    @FindBy(xpath = "//*[@id=\"checkOutForm\"]/div[14]/div[1]")
    private WebElement acceptAgreement;
    //*[@id="checkOutForm"]/div[10]/div[1]/div/label
    //*[@id="checkOutForm"]/div[10]/div[1]/div
    //*[@id="checkOutForm"]/div[10]/div[2]
    //*[@id="checkOutForm"]/div[14]/div[1]


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String productName) {
        searchField.sendKeys(productName);
        searchSubmitButton.click();
    }

    public void openProductDetails() {
        firstProductLink.click();
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public void clickGoToCartButton() {
        goToCartButton.click();
    }

    public String getAddedProductTitle() {
        return addedProductTitle.getText();
    }

    public void clickGoToCart() {
        goToCart.click();
    }

    public void clickGoToCartAgree() {
        goToCartAgree.click();
    }

    public void clickAcceptAgreement() {
        acceptAgreement.click();
    }


}
