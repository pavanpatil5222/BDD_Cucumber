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
	 @FindBy(css="div:nth-child(1) > a")
	WebElement chemlogo;
	@FindBy(css="#mat-input-0")
	WebElement textbox_Email;
	@FindBy(css="#mat-input-1")
	WebElement textbox_Password;
	@FindBy(xpath="//button[@title= 'Login']")
	WebElement btn_Login;
	@FindBy(xpath="//*[@id='onetrust-accept-btn-handler']")
	WebElement button_Cookies_AcceptAll;
	@FindBy(xpath="//*[@id='onetrust-banner-sdk']")
	WebElement footer_banner;
	
	
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
        controller.waitTime(3);
		 boolean logostatus =  super.isElementDisplayed(chemlogo);
		  if (logostatus)
		  controller.Logger.Logger.log(LogStatus.PASS, "Login is happend"); 
		 else
		  controller.Logger.Logger.log(LogStatus.FAIL, "Login not happend");
		  
		 if (isDisplayedManageCookiePreferencesBanner()) {
			  controller.Logger.Logger.log(LogStatus.INFO, "Cookies footer banner is displayed and Clicked on Accept all cookies button");
			  clickOnButtonAcceptAllCookies();
			  }
		 
    	
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
	
	public void clickOnButtonAcceptAllCookies() throws Exception {
	try {
	click(button_Cookies_AcceptAll);
	} catch (Exception e) {
	throw new Exception("clickOnButtonAcceptAllCookies is not working " + e);
	}
	}


	public boolean isDisplayedManageCookiePreferencesBanner() throws Exception {
	boolean status =false;
	try {
	waitUntilElementIsDisplayed(footer_banner);
	status = footer_banner.isDisplayed();
	} catch (Exception e) {
	status = false;
	}
	return status;
	}


	
	
}