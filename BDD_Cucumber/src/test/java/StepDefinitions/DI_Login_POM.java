package StepDefinitions;

import POM.DI_Home_Page;
import POM.DI_Login_Page;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DI_Login_POM {

    WebDriver driver;
    DI_Login_Page LoginPage;
    DI_Home_Page HomePage;

    @Given("Google browser is open")
    public void google_browser_is_open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @And("User is on DI login page")
    public void user_is_on_di_login_page() {
        driver.get("https://qa1.ui.dev-innovation.com/login/");
    }

    @When("User enter DI valid {string} and {string}")
    public void user_enter_di_valid_LoginID_and_Pwd(String UN, String PWD) throws InterruptedException {

    LoginPage = new DI_Login_Page(driver);
    LoginPage.Enter_LoginID(UN);
    Thread.sleep(2000);
    LoginPage.Enter_pwd(PWD);
    }

    @And("click on login button")
    public void click_on_login_button() throws InterruptedException {
        Thread.sleep(2000);
        LoginPage = new DI_Login_Page(driver);
        LoginPage.Click_Login_btn();
        Thread.sleep(5000);
    }

    @Then("User navigated to DI home page")
    public void user_navigated_to_di_home_page() throws InterruptedException {

        HomePage = new DI_Home_Page(driver);
        HomePage.is_Welcome_displayed();
        Thread.sleep(2000);
        driver.close();
        driver.quit();
    }
}
