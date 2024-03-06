package cz.cvut.fel.ts1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * @author Aleksandr Kross
 *
 */


public class ReviewPage {
    private WebDriver driver;

    @FindBy(xpath = "/html/body/div[4]/div/div[1]")
    private WebElement topZnackyButton;

    @FindBy(xpath = "//*[@id=\"brand-accordion-top\"]/div/div[2]/div[6]/a")
    private WebElement selectPhilips;

    @FindBy(xpath = "//*[@id=\"siteMain\"]/div[3]/div/ul/li[1]/a/article/div[3]/p/span")
    private WebElement openPhilips;

    @FindBy(xpath = "//*[@id=\"siteMain\"]/div[3]/div[2]/div[2]/div/div[2]/div[2]/div[2]/div/a")
    private WebElement readReview;


    @FindBy(xpath = "//*[@id=\"product-ratings-content\"]/div/div[6]/div[2]/div[2]/div[1]/div[1]/div[4]/span")
    private WebElement authorComment;

    @FindBy(xpath = "//*[@id=\"product-ratings-content\"]/div/div[6]/div[2]/div[2]/div[1]/div[2]/div[1]")
    private WebElement dateComment;

    @FindBy(xpath = "//*[@id=\"product-ratings-content\"]/div/div[6]/div[2]/div[2]/div[1]/div[2]/div[2]")
    private WebElement textComment;

    @FindBy(xpath = "//*[@id=\"product-ratings-content\"]/div/div[6]/div[2]/div[2]/div[1]/div[2]/div[4]/div[2]/button")
    private WebElement usefulButton;

    @FindBy(xpath = "//*[@id='product-ratings-content']/div/div[6]/div[2]/div[2]/div[1]/div[2]/div[4]/div[3]/span")
    private WebElement feedbackTextElement;


    public ReviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickTopZnackyButton() {
        topZnackyButton.click();
    }

    public void clickSelectPhilips() {
        selectPhilips.click();
    }

    public void clickOpenPhilips() {
        openPhilips.click();
    }

    public void clickReadReview() {
        readReview.click();
    }

    public String getAuthorComment() {
        return authorComment.getText();
    }

    public String getDateComment() {
        return dateComment.getText();
    }

    public String getTextComment() {
        return textComment.getText();
    }

    public void clickUsefulButton() {
        usefulButton.click();
    }

    public String getFeedbackText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(feedbackTextElement));
        return feedbackTextElement.getText();
    }
}
