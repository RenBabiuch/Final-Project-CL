package pl.coderslab.shop;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class OrderConfirmationPage {

    private final WebDriver driver;

    String orderValueText;
    String orderValueTextSub;

    @FindBy(className = "page-order-confirmation")
    private WebElement orderInfo;

    @FindBy(css = "a[title = 'View my customer account']")
    private WebElement userAccountLink;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doScreenshotOfOrderConfirmation() {

        try {
            File screenshot = orderInfo.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("src/test/java/screenshot.png"));

        } catch (WebDriverException | IOException e) {
           e.printStackTrace();
        }
    }

    public void goToUserAccount() {
        userAccountLink.click();
    }

    public String goToSaveOrderValue() {
        WebElement totalOrderValue = driver.findElement(By.xpath("//*[contains(text(), 'Payment amount.')]"));
        orderValueText = totalOrderValue.getText();
        orderValueTextSub = orderValueText.substring(16);
        return orderValueTextSub;
    }

}