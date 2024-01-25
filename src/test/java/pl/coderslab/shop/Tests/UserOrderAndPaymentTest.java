package pl.coderslab.shop.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.shop.*;

import java.time.Duration;

public class UserOrderAndPaymentTest {

    private WebDriver driver;

    String email = "adres.mailowy4@gmail.com";
    String password = "heloł";
    String productName = "Hummingbird Printed Sweater";
    String productSize = "M";
    int productQuantity = 5;
    String productDiscount = "Save 20%";
    String expectedOrderStatus = "Awaiting check payment";


    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://mystore-testlab.coderslab.pl");
    }
    @Test
    public void makeUserOrderAndPayment() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToSignIn();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.enterInfoToSignInAndSubmit(email, password);

        AccountPage accountPage = new AccountPage(driver);
        accountPage.searchProduct(productName);

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.goToProduct();

        ProductPage productPage = new ProductPage(driver);

        Assertions.assertEquals(productDiscount.toLowerCase(), productPage.getDiscountInfo(), "This product should have discount -20%");

        productPage.selectSize(productSize);
        productPage.enterQuantity(productQuantity);
        //productPage.setQuantity(productQuantity);       // II sposób
        productPage.addProductToCart();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        productPage.proceedToCheckoutOption();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.confirmUserAddress();
        checkoutPage.selectPickUpInStoreShippingMethod();
        checkoutPage.confirmShippingMethod();
        checkoutPage.selectPayByCheckPayment();
        checkoutPage.approveTermsAndPlaceOrder();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        orderConfirmationPage.doScreenshotOfOrderConfirmation();

        String orderValue = orderConfirmationPage.goToSaveOrderValue();

        orderConfirmationPage.goToUserAccount();
        accountPage.goToOrderHistoryAndDetails();

        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryPage.goToOrderDetails();

        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);

        Assertions.assertEquals(expectedOrderStatus, orderDetailsPage.getOrderStatusInfo()); 
        Assertions.assertEquals(orderValue, orderDetailsPage.getTotalPriceInfo());
    }

    @AfterEach
        public void tearDown() {
        driver.quit();
    }

}