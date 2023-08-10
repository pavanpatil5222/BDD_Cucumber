package StepDefinitions;

import Login_Pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestProjectLogin_POM {

    WebDriver driver;
    Pages page1;

    @Given("User open google browser")
    public void user_open_google_browser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @And("User is on Test project login page")
    public void user_is_on_Test_Project_login_page() {
        driver.get("https://example.testproject.io/web/");
    }

    @When("^User enter valid (.*) and (.*)$")
    public void user_enter_valid_username_and_password(String UN, String PWD) throws InterruptedException {

        page1 = new Pages(driver);
        page1.Enter_username(UN);
        Thread.sleep(2000);
        page1.Enter_password(PWD);
        Thread.sleep(2000);
    }

    @And("click on login")
    public void click_on_login_button() throws InterruptedException {
        page1 = new Pages(driver);
        page1.Click_Login_btn();
        Thread.sleep(2000);
    }

    @Then("User navigated to test project home page")
    public void user_navigated_to_test_project_home_page() throws InterruptedException {

        page1 = new Pages(driver);
       page1.isLogout_btn_displayed();
       Thread.sleep(2000);
       driver.close();
       driver.quit();
    }
}
