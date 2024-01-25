package pl.coderslab.shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    private final WebDriver driver;

    @FindBy(css = "input[placeholder = 'Search our catalog']")
    private WebElement searchInput;

    @FindBy(id = "addresses-link")
    private WebElement addressesLink;

    @FindBy(id = "history-link")
    private WebElement orderDetailsLink;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToAddresses() {
        addressesLink.click();
    }

    public void goToOrderHistoryAndDetails() {
        orderDetailsLink.click();
    }

    public void searchProduct(String productName) {
        searchInput.sendKeys(productName);
        searchInput.submit();
    }

}