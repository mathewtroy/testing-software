package cz.cvut.fel.ts1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FavouriteListPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id=\"buybox-options\"]/div[2]/div/div[1]/div[1]/button")
    private WebElement addToFav1;

    @FindBy(xpath = "//*[@id=\"buybox-options\"]/div[2]/div/div[2]/div[2]/div[3]/div[1]/div[2]/div/div[2]/a[1]")
    private WebElement addToFav2;

    @FindBy(xpath = "//*[@id=\"item-7bc93d6d-f748-11ed-a7ed-02d16b37478a\"]/div[1]/a")
    private WebElement favText;


    public FavouriteListPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickFav1(){ addToFav1.click();}

    public void clickFav2(){ addToFav2.click();}

    public String getTextFav(){ return favText.getText();}

}
