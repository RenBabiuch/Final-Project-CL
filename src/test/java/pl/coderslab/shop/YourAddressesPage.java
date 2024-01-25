package pl.coderslab.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YourAddressesPage {

    private final WebDriver driver;

    @FindBy(xpath = "//a[@data-link-action = 'add-address']")
    private WebElement createNewAddressLink;

    @FindBy(xpath = "//a[@data-link-action='delete-address']")
    private List<WebElement> deleteAddressLink;

    @FindBy(css = "article.alert-success")
    private WebElement alertSuccess;

    public YourAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToCreateNewAddress() {
        createNewAddressLink.click();
    }

    public String getAlertSuccessCreateAddress() {
        return alertSuccess.getText();
    }

    public void goToVerification() {
        List<WebElement> updateAddressLink = driver.findElements(By.xpath("//a[@data-link-action='edit-address']"));
        updateAddressLink.getLast().click();
    }

    public void removeAddress() {
        List<WebElement> deleteAddressLink = driver.findElements(By.xpath("//a[@data-link-action='delete-address']"));
        deleteAddressLink.getLast().click();
    }

    public String getAlertSuccessDeleteAddress() {
        return alertSuccess.getText();
    }

}
