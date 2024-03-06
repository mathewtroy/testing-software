package cz.cvut.fel.ts1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Vladyslav Hordiienko
 *
 */


public class ChangeAddressPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='vorname-l']")
    private WebElement jmenoField;

    @FindBy(xpath = "//*[@id='nachname-l']")
    private WebElement prijmeniField;

    @FindBy(xpath = "//*[@id='strasse-l']")
    private WebElement uliceField;

    @FindBy(xpath = "//*[@id='hausnr-l']")
    private WebElement cisloDomuField;

    @FindBy(xpath = "//*[@id='plz-l']")
    private WebElement pscField;

    @FindBy(xpath = "//*[@id='ort-l']")
    private WebElement mestoField;

    @FindBy(xpath = "//*[@id='telefon-l']")
    private WebElement phoneField;

    @FindBy(xpath = "//*[@id=\"checkOutForm\"]/div[9]/div/input")
    private WebElement ulozitButt;


    @FindBy(xpath = "//*[@id=\"siteMain\"]/div/div[2]/div[1]/div[1]/div[1]/a")
    private WebElement changeAddressButt;



    @FindBy(xpath = "//*[@id=\"siteMain\"]/div/div[2]/div[2]/div/ul/li[1]/span[2]")
    private WebElement jmenoText;

    @FindBy(xpath = "//*[@id=\"siteMain\"]/div/div[2]/div[2]/div/ul/li[1]/span[3]")
    private WebElement prijmeniText;

    @FindBy(xpath = "//*[@id=\"siteMain\"]/div/div[2]/div[2]/div/ul/li[2]/span[1]")
    private WebElement uliceText;

    @FindBy(xpath = "//*[@id=\"siteMain\"]/div/div[2]/div[2]/div/ul/li[2]/span[2]")
    private WebElement cisloDomuText;

    @FindBy(xpath = "//*[@id=\"siteMain\"]/div/div[2]/div[2]/div/ul/li[3]/span[1]")
    private WebElement pscText;

    @FindBy(xpath = "//*[@id=\"siteMain\"]/div/div[2]/div[2]/div/ul/li[3]/span[2]")
    private WebElement mestoText;

    @FindBy(xpath = "//*[@id=\"siteMain\"]/div/div[2]/div[2]/div/ul/li[5]/span")
    private WebElement phoneText;

    @FindBy(xpath = "//*[@id=\"checkOutForm\"]/div[6]/div[4]")
    private WebElement errorPscMsg;

    @FindBy(xpath = "//*[@id=\"checkOutForm\"]/div[3]/div[2]")
    private WebElement errorNameMsg;

    @FindBy(xpath = "//*[@id=\"checkOutForm\"]/div[4]/div[2]")
    private WebElement errorSurnameMsg;

    @FindBy(xpath = "//*[@id=\"checkOutForm\"]/div[5]/div[4]")
    private WebElement errorUliceMsg;

    @FindBy(xpath = "//*[@id=\"checkOutForm\"]/div[8]/div[2]")
    private WebElement errorTelefonMsg;


    public ChangeAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setJmeno(String str){
        jmenoField.clear();
        jmenoField.sendKeys(str);
    }

    public void setPrijmeni(String str){
        prijmeniField.clear();
        prijmeniField.sendKeys(str);
    }

    public void setUlice(String str){
        uliceField.clear();
        uliceField.sendKeys(str);
    }

    public void setPsc(String str){
        pscField.clear();
        pscField.sendKeys(str);
    }

    public void setCisloDomu(String str){
        cisloDomuField.clear();
        cisloDomuField.sendKeys(str);
    }

    public void setMesto(String str){
        mestoField.clear();
        mestoField.sendKeys(str);
    }

    public void setPhone(String str){
        phoneField.clear();
        phoneField.sendKeys(str);
    }

    public void ulozit(){ulozitButt.click();}

    public void clickChangeAddressB(){changeAddressButt.click();}


    public String getJmenoText(){return jmenoText.getText();}

    public String getPrijmeniText(){return prijmeniText.getText();}

    public String getUliceText(){return uliceText.getText();}

    public String getPscText(){ return pscText.getText();}

    public String getCisloDomuText(){return cisloDomuText.getText();}

    public String getMestoText(){return mestoText.getText();}

    public String getPhoneText(){return phoneText.getText();}

    public String getErrorPscText(){return errorPscMsg.getText();}

    public String getErrorNameText(){return errorNameMsg.getText();}

    public String getErrorSurnameText(){return errorSurnameMsg.getText();}

    public String getErrorUliceText(){return errorUliceMsg.getText();}

    public String getErrorTelefonText(){return errorTelefonMsg.getText();}

}
