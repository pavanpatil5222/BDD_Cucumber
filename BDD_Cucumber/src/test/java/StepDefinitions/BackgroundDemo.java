package StepDefinitions;

import POM.DI_Home_Page;
import POM.DI_Login_Page;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BackgroundDemo {

    WebDriver driver;

    @Given("User is on login")
    public void user_is_on_login() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://patentweb.derwentinnovation.com/login/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @When("User enter UN and PWD")
    public void user_enter_un_and_pwd() {
        driver.findElement(By.xpath("//input[@id='tr-login-username']")).sendKeys("DIRUserAnalystAdmin@AnyAdmDWPI1.com");
        driver.findElement(By.xpath("//input[@id='tr-login-password']")).sendKeys("Admin@123");
    }

    @When("Click on Login")
    public void click_on_login() throws InterruptedException {
        driver.findElement(By.xpath("(//input[@tr-login='login_btn'])[1]")).click();
        Thread.sleep(15000);
        driver.findElement(By.xpath("//button[text()='Accept all']")).click();
        Thread.sleep(5000);
    }

    @Then("User is on home page")
    public void user_is_on_home_page() {
        driver.findElement(By.xpath("(//a[@aria-label='To home page'])[1]")).isDisplayed();
    }

    @When("User click on welcome link")
    public void user_click_on_welcome_link() throws InterruptedException {
        driver.findElement(By.xpath("//button//span//span//span[text()='Welcome']")).click();
        Thread.sleep(2000);
    }

    @Then("Logout link is displayed")
    public void logout_link_is_displayed() {
        driver.findElement(By.xpath("//span[normalize-space()='Log out']")).isDisplayed();
    }

//    @When("User click on Patent Search tile")
//    public void user_click_on_patent_search_tile() {
//        driver.findElement(By.xpath("//div//h4[text()='Patent Search']")).click();
//    }
//
//    @Then("Patent search page displayed")
//    public void patent_search_page_displayed() {
//        driver.findElement(By.xpath("//div//h2[text()='PATENT SEARCH']")).isDisplayed();
//    }


}
