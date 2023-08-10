package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DI_Home_Page {

    WebDriver driver;

    @FindBy(xpath = "//span//span[text()='Welcome']")
    private WebElement WelcomePage;

    public DI_Home_Page(WebDriver driver) {
      //  this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void is_Welcome_displayed()
    {
        WelcomePage.isDisplayed();
    }


}
