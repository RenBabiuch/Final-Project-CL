package pl.coderslab.shop.Tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.coderslab.shop.*;

import java.time.Duration;

public class EditAddressInfoSteps {

    private WebDriver driver;
    NewAddressPage newAddressPage;
    YourAddressesPage yourAddressesPage;


    @Given("I'm on the main shop page")
    public void mainShopPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://mystore-testlab.coderslab.pl");
    }

    @When("I go to sign in")
    public void iGoToSignIn() {
        MainPage mainPage = new MainPage(driver);
        mainPage.goToSignIn();
    }

    @And("I log in using email {string} and password {string}")
    public void iLogInUsingEmailAndPassword(String email, String password) {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.enterInfoToSignInAndSubmit(email, password);
    }

    @And("I go to addresses page")
    public void iGoToAddressesPage() {
        AccountPage accountPage = new AccountPage(driver);
        accountPage.goToAddresses();
    }

    @When("I go to create new address")
    public void iGoToCreateNewAddress() {
        yourAddressesPage = new YourAddressesPage(driver);
        yourAddressesPage.goToCreateNewAddress();
    }

    @And("I enter new: alias {string}, address {string}, city {string}, postalCode {string}, country {string} and phone {string}")
    public void iEnterNewAliasAddressCityPostalCodeCountryAndPhone(String alias, String address, String city, String postalCode, String country, String phone) {
        newAddressPage = new NewAddressPage(driver);
        newAddressPage.enterNewAddressInfo(alias, address, city, postalCode, country, phone);
    }

    @And("I submit the form")
    public void iSubmitForm() {
        newAddressPage.saveTheForm();
    }

    @Then("I can see success alert message: {string}")
    public void iCanSeeSuccessAlertMessage(String successCreateAlert) {

        Assertions.assertEquals(successCreateAlert, yourAddressesPage.getAlertSuccessCreateAddress(), "The address should be created");
    }

    @And("I verify entered address: alias {string}, address {string}, city {string}, postalCode {string}, country {string} and phone {string}")
    public void iVerifyEnteredAddress(String alias, String address, String city, String postalCode, String country, String phone) {
        yourAddressesPage.goToVerification();

        Assertions.assertEquals(alias, newAddressPage.getAliasName(), "Alias is different than entered");
        Assertions.assertEquals(address, newAddressPage.getAddressName(), "Address is different than entered");
        Assertions.assertEquals(city, newAddressPage.getCityName(), "City is different than entered");
        Assertions.assertEquals(postalCode, newAddressPage.getPostalCodeName(), "PostalCode is different than entered");
        Assertions.assertEquals(country, newAddressPage.getCountryName(), "Country is different than selected");
        Assertions.assertEquals(phone, newAddressPage.getPhoneName(), "Phone is different than entered");
    }

    @When("I remove address")
    public void iRemoveAddress() {
        driver.navigate().back();
        yourAddressesPage.removeAddress();
    }

    @Then("I can see success remove alert message: {string}")
    public void iCanSeeSuccessRemoveAlertMessage(String successDeleteAlert) {

        Assertions.assertEquals(successDeleteAlert, yourAddressesPage.getAlertSuccessDeleteAddress());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @And("I close browser")
    public void iCloseBrowser() {
        driver.close();
    }
}
