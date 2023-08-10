package StepDefinitions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class GoogleSearch {

    WebDriver driver;

    @Given("browser is open")
    public void browser_is_open() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @And("User is on google search page")
    public void u_ser_is_on_google_search_page() {
        driver.get("https://www.google.com/");
    }

    @When("User enter text in search box")
    public void user_enter_text_in_search_box() {
        driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys("Automation step by step");
    }

    @And("clicks on search button")
    public void clicks_on_search_button() {
        driver.findElement(By.xpath("//textarea[@title='Search']")).sendKeys(Keys.ENTER);
    }

    @Then("User can see search results")
    public void user_can_see_search_results() {
        driver.getPageSource().contains("Online Courses");
        driver.close();
        driver.quit();
    }
}
