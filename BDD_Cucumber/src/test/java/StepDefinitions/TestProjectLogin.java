package StepDefinitions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestProjectLogin {

//    WebDriver driver;
//
//    @Given("User open google browser")
//    public void user_open_google_browser() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//    }
//
//    @And("User is on Test project login page")
//    public void user_is_on_Test_Project_login_page() {
//        driver.get("https://example.testproject.io/web/");
//    }
//
//    @When("^User enter valid (.*) and (.*)$")
//    public void user_enter_valid_username_and_password(String username, String password) throws InterruptedException {
//        driver.findElement(By.xpath("//input[@id='name']")).sendKeys(username);
//        Thread.sleep(2000);
//        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
//        Thread.sleep(2000);
//    }
//
//    @And("click on login")
//    public void click_on_login_button() throws InterruptedException {
//        driver.findElement(By.xpath("//button[@id='login']")).click();
//        Thread.sleep(2000);
//    }
//
//    @Then("User navigated to test project home page")
//    public void user_navigated_to_test_project_home_page() {
//        driver.findElement(By.xpath("//button[@id='logout']")).isDisplayed();
//        driver.close();
//        driver.quit();
//    }


}
