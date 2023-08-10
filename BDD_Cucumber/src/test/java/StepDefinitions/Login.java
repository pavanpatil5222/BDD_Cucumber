package StepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {

    @Given("User is on login page")
    public void VerifyLoginPage()
    {
        System.out.println("User is on login page");
    }

    @When("User enter username and password")
    public void EnterUNPWD()
    {
        System.out.println("User enter username and password");
    }

    @And("clicks on login button")
    public void ClickLoginButton()
    {
        System.out.println("clicks on login button");
    }

    @Then("User can see home page")
    public void VerifyHomePage()
    {
        System.out.println("User can see home page");
    }


}
