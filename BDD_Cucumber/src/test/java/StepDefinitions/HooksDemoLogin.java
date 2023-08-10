package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class HooksDemoLogin {

    WebDriver driver;

    public void BrowserSetup()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Given("User is on PW DI login page")
    public void user_is_on_pw_di_login_page() {


    }

    @When("User enter PW DI valid UN and PWD")
    public void user_enter_pw_di_valid_un_and_pwd() {


    }

    @And("click on login button for PW DI Login")
    public void click_on_login_button_for_pw_di_login() {


    }

    @Then("User navigated to PW DI home page")
    public void user_navigated_to_pw_di_home_page() {


    }


    public void Teardown()
    {
        driver.close();
        driver.quit();
    }

}
