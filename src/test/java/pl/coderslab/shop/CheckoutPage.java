package pl.coderslab.shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    private final WebDriver driver;
    @FindBy(name = "confirm-addresses")
    private WebElement continueAddressesButton;

    @FindBy(xpath = "//input[@id = 'delivery_option_8']")
    private WebElement pickUpOption;

    @FindBy(xpath = "//button[@name = 'confirmDeliveryOption']")
    private WebElement continueShippingButton;

    @FindBy(xpath = "//input[@id = 'payment-option-1']")
    private WebElement payByCheckRadio;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsCheckbox;

    @FindBy(xpath = "//button[contains(text(), 'Place order')]")
    private WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void confirmUserAddress() {
        continueAddressesButton.click();
    }

    public void selectPickUpInStoreShippingMethod() {
        if (!pickUpOption.isSelected()) {
            pickUpOption.click();
        }
    }

    public void confirmShippingMethod() {
        continueShippingButton.click();
    }

    public void selectPayByCheckPayment() {
        payByCheckRadio.click();
    }

    public void approveTermsAndConditions() {
        termsCheckbox.click();
    }

    public void confirmPaymentAndPlaceOrder() {
        placeOrderButton.click();
    }

    public void approveTermsAndPlaceOrder() {
        approveTermsAndConditions();
        confirmPaymentAndPlaceOrder();
    }

}