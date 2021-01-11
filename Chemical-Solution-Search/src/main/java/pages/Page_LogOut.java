package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import support.Controller;
/**
 * 
 * @author Anand/Rashmi
 *
 */
public class Page_LogOut extends Controller {
	
	@FindBy(css="div[class='logout-title']")
	WebElement logouttitle;
	@FindBy(css="div[class='logout-text']")
	WebElement logouttext;
	@FindBy(xpath=".//button[contains(.,'Product Login')]")
	WebElement productlogin;
	
	public Page_LogOut(Controller controller){
		super(controller);
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean isDisplayedProductLogin() throws Exception {
		return controller.isElementDisplayed(productlogin);
	}
	
	public void clickOnButtonProductLogin() throws Exception {
		controller.jsClick(productlogin);
		controller.waitTime(2);
		Page_Login page_Login = new Page_Login(controller);
		controller.waitUntilElementIsDisplayed(page_Login.btn_Login);
		
	}
	
	public String getTitle() throws Exception{
		return controller.getText(logouttitle);
		
	}
	public String getLogOutText() throws Exception{
		return controller.getText(logouttext);
		
	}


	
			
			
	
}