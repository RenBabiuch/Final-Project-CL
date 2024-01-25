package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewAddressPage {

    private final WebDriver driver;

    @FindBy(id = "field-alias")
    private WebElement aliasInput;

    @FindBy(name = "address1")
    private WebElement addressInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "postcode")
    private WebElement postalCodeInput;

    @FindBy(name = "id_country")
    private WebElement selectCountryOptions;

    @FindBy(name = "phone")
    private WebElement phoneInput;

    @FindBy(className = "form-control-submit")
    private WebElement saveButton;

    public NewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterAliasName(String alias) {
        aliasInput.sendKeys(alias);
    }

    public void enterAddressName(String address) {
        addressInput.sendKeys(address);
    }

    public void enterCityName(String city) {
        cityInput.sendKeys(city);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
    }

    public void selectCountryName(String country) {
        selectCountryOptions.click();
        driver.findElement(By.xpath("//option[contains(text(), '" + country + "')]")).click();
    }

    public void enterPhoneNumber(String phone) {
        phoneInput.sendKeys(phone);
    }

    public void enterNewAddressInfo(String alias, String address, String city, String postalCode, String country, String phone) {
        enterAliasName(alias);
        enterAddressName(address);
        enterCityName(city);
        enterPostalCode(postalCode);
        selectCountryName(country);
        enterPhoneNumber(phone);
    }

    public void saveTheForm() {
        saveButton.click();
    }

    public String getAliasName() {
        return aliasInput.getAttribute("value");
    }

    public String getAddressName() {
        return addressInput.getAttribute("value");
    }

    public String getCityName() {
        return cityInput.getAttribute("value");
    }

    public String getPostalCodeName() {
        return postalCodeInput.getAttribute("value");
    }

    public String getCountryName() {
        WebElement selectedCountry = driver.findElement(By.xpath("//select[@name='id_country']//option[not(@disabled)]"));
        return selectedCountry.getText();
    }
    public String getPhoneName() {
        return phoneInput.getAttribute("value");
    }
}