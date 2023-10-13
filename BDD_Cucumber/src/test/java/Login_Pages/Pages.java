package Login_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pages {

    WebDriver driver;

    @FindBy (xpath = "//input[@id='name']")
    private WebElement Username;

    @FindBy (xpath = "//input[@id='password']")
    private WebElement Password;

    @FindBy (xpath = "//button[@id='login']")
    private WebElement Login_button;

    @FindBy (xpath = "//button[@id='logout']")
    private WebElement Logout_button;

    public Pages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void Enter_username(String username)
    {
        Username.sendKeys(username);
    }

    public void Enter_password(String password){
        Password.sendKeys(password);
    }

    public void Click_Login_btn()
    {
        Login_button.click();
    }

    public void isLogout_btn_displayed(){
        Logout_button.isDisplayed();
    }



}
