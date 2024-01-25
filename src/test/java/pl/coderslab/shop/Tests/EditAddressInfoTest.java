package pl.coderslab.shop.Tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/shop-user-account-edit.feature", plugin = {"pretty", "html:out.html"})

public class EditAddressInfoTest {

}
