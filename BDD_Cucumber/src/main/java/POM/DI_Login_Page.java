package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Today date - 13.10.2023
public class DI_Login_Page {

    WebDriver driver;
    @FindBy (xpath = "//input[@id='tr-login-username']")
    private WebElement LoginID;

    @FindBy (xpath = "//input[@id='tr-login-password']")
    private WebElement Pwd;

    @FindBy (xpath = "(//input[@tr-login='login_btn'])[1]")
    private WebElement Btn_Login;

    public DI_Login_Page(WebDriver driver) {
     //  this.driver = driver;
       PageFactory.initElements(driver, this);
    }
    public void Enter_LoginID(String username)
    {
        LoginID.sendKeys(username);
    }
    public void Enter_pwd(String password){

        Pwd.sendKeys(password);
    }

    public void Click_Login_btn()
    {
        Btn_Login.click();
    }

}
