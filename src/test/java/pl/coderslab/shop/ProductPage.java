package pl.coderslab.shop;

import org.junit.platform.commons.function.Try;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private final WebDriver driver;

    @FindBy(css = "span.discount.discount-percentage")
    private WebElement discountInfo;

    @FindBy(css = "select.form-control-select")
    private WebElement sizeOptions;

    @FindBy(xpath = "//input[@id = 'quantity_wanted']")
    private WebElement quantityInput;

    @FindBy(css = "button.bootstrap-touchspin-up")
    private WebElement touchSpinUpButton;

    @FindBy(xpath = "//button[@data-button-action='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[contains(text(), 'Proceed to checkout')]")
    private WebElement checkoutButton;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getDiscountInfo() {
        return discountInfo.getText().toLowerCase();
    }

    public void selectSize(String size) {
        sizeOptions.click();
        driver.findElement(By.cssSelector("option[title='" + size + "']")).click();
    }

    public void enterQuantity(int quantity) {

        try {
        Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        quantityInput.click();
        quantityInput.clear();
        quantityInput.sendKeys(Keys.CONTROL + "a");
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public void setQuantity(int quantity) {

        for (int i = 0; i < quantity; i++) {
           touchSpinUpButton.click();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addProductToCart() {
        addToCartButton.click();
    }

    public void proceedToCheckoutOption() {
        // można też najpierw okno modalne zlokaliwac, a potem ten element
        //driver.findElement(By.xpath("//div[@class = 'modal-dialog']//*[contains(text(), 'Proceed to checkout')]")).click();
        checkoutButton.click();
        checkoutButton.click();
    }

}