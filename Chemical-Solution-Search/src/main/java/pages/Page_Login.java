package pages;

import java.util.Set;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;
import support.Controller;
/**
 * 
 * @author Anand/Rashmi
 *
 */
public class Page_Login extends Controller {
		
	@FindBy(css="#mat-error-7")
	WebElement loginerr;
	@FindBy(xpath="//a[@href='#/home']")
	WebElement dilogo;
	@FindBy(css="#mat-input-0")
	WebElement textbox_Email;
	@FindBy(css="#mat-input-1")
	WebElement textbox_Password;
	@FindBy(xpath="//button[@ name = 'login-btn']")
	WebElement btn_Login;
	
	
	public Page_Login(Controller controller){
		super(controller);
		PageFactory.initElements(driver, this);
	}

	
	public void setCredentials(String email, String password) throws Exception {
		controller.setText(textbox_Email, email);
		controller.setText(textbox_Password, password);
	}
	
	
	public void clickOnButtonLogin() throws Exception {
		try{
		controller.jsClick(btn_Login);
		controller.waitTime(2);
		boolean errorstatus = super.isElementDisplayed(loginerr);
		if(errorstatus){
			throw new Exception("Please provide valid credentials..");	
		}		
		}catch(Exception e){
			throw new Exception("clickOn_Btn_Login is not working.."+e);
		}
	}
	
	public void loginToCS(String userName, String password) throws Exception {
        controller.waitTime(1);
        setCredentials(userName, password);
        clickOnButtonLogin();
        controller.waitTime(1);
		 boolean logostatus =  super.isElementDisplayed(dilogo);
		  if (logostatus)
		  controller.Logger.Logger.log(LogStatus.PASS, "Login is happend"); 
		 else
		  controller.Logger.Logger.log(LogStatus.FAIL, "Login not happend");
		 
    	
 }

	public boolean isDisplayedButtonLogin() throws Exception{
		return controller.isElementDisplayed(btn_Login);
	}
	
	public String validateInvalidCredentials(String email, String password) throws Exception{
		setCredentials(email, password);
		controller.jsClick(btn_Login);
		controller.waitTime(1);
		return getText(loginerr);
		}
	
	
}