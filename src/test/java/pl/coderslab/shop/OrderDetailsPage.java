package pl.coderslab.shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderDetailsPage {

    public final WebDriver driver;

    String totalPriceText;
    String totalPriceTextSub;

    @FindBy(className = "label-pill")
    private WebElement orderStatus;

    @FindBy(className = "line-total")
    private WebElement totalPrice;


    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getOrderStatusInfo() {
        return orderStatus.getText();
    }

    public String getTotalPriceInfo() {
        totalPriceText = totalPrice.getText();
        totalPriceTextSub = totalPriceText.substring(6);
        return totalPriceTextSub;
    }

}