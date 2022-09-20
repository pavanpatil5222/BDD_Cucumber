package pages.chemicalsearch;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;
import pages.patentsearch.Tab_PatentSearch;
import support.Controller;
/**
 * 
 * @author Anand
 *
 */

public class Page_ManageFields extends Controller{

	@FindBy(css="section.available-fields-section > div:nth-child(3) > button > span")
	private WebElement btnClearAll;	

	@FindBy(css="mat-dialog-actions > button.cdx-but-xl.cdx-but-link.mat-button.mat-button-base.mat-primary > span")
	private WebElement btnRestoreDefaults;
	
	@FindBy(css="button.cdx-but-xl.mat-stroked-button.mat-button-base.mat-primary > span")
	private WebElement btnCancel;
	
	@FindBy(css="button.cdx-but-xl.mat-flat-button.mat-button.mat-button-base.mat-primary > span")
	private WebElement btnApply;	
	
	@FindBy(css="#result-set-manage-fields-modal > form > div.cdx-dialog-top > button > span > mat-icon")
	private WebElement closeIcon;	
	
	public Page_ManageFields(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnButtonCancel() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnCancel);
			jsClick(btnCancel);
			} catch (Exception ex) {
			throw new Exception("clickOnButtonCancel is not working" + ex);
		}
}
	
	public void clickOnButtonClearAll() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnClearAll);
			jsClick(btnClearAll);
			} catch (Exception ex) {
			throw new Exception("clickOnButtonClearAll is not working" + ex);
		}
}
	
	public void clickOnCloseIcon() throws Exception {
		try {
			waitUntilElementIsDisplayed(closeIcon);
			jsClick(closeIcon);
			} catch (Exception ex) {
			throw new Exception("clickOnCloseIcon is not working" + ex);
		}
}
	
	public void clickOnButtonRestoreDefaults() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnRestoreDefaults);
			jsClick(btnRestoreDefaults);
			} catch (Exception ex) {
			throw new Exception("clickOnButtonRestoreDefaults is not working" + ex);
		}
}
	
	public void clickOnRadioButtonDrawingSize(int radiobutton) throws Exception {
		try {
			WebElement radiobtn=controller.driver.findElement(By.xpath("//mat-radio-group/mat-radio-button["+radiobutton+"]/label/span[1]/input"));
			if(!radiobtn.isSelected())
			jsClick(radiobtn);
			else
				controller.Logger.Logger.log(LogStatus.INFO, "Radio button is already selected");
			} catch (Exception ex) {
			throw new Exception("clickOnRadioButtonDrawingSize is not working" + ex);
		}
}
	
	
	public boolean isRadioButtonSelected(int radiobutton) throws Exception {
		try {
			WebElement radiobtn=controller.driver.findElement(By.xpath("//mat-radio-group/mat-radio-button["+radiobutton+"]"));
			String radio=controller.getElementAttribute(radiobtn, "class");
			if(radio.contains("mat-radio-checked"))
				return true;
			else
				return false;
			} catch (Exception ex) {
			throw new Exception("isRadioButtonSelected is not working" + ex);
		}
}
	
	
	public void clickOnButtonApply() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnApply);
			jsClick(btnApply);
			} catch (Exception ex) {
			throw new Exception("clickOnButtonApply is not working" + ex);
		}
}
	
	public List<String> getAllManageFieldsOptions() throws Exception{
		try {
		//	List<WebElement> options = driver.findElements(By.xpath("//div/mat-checkbox/label/span[not(contains(., 'Filler'))]"));
			List<WebElement> options = driver.findElements(By.xpath("//mat-dialog-content/section[1]//label/span[2][not(contains(., 'Filler'))]"));
 			List<String> optionlabels =new ArrayList<String>();
 			
 			if(options.size() > 0) {
 				 for(WebElement element:options) {
  					String optionlabel = controller.getText(element);
  					optionlabels.add(optionlabel); 					
  				}
 			}
 			return optionlabels;
     	}catch (Exception e) {
     		throw new Exception("getAllManageFieldsOptions is not working" + e);
     	}
	}
	
	@SuppressWarnings("static-access")
	public void selectManageFieldCheckBoxes(List<String> fields) throws Exception {
		try {
			String check;
			for (String expectedField : fields) {
				WebElement manageFieldCheckBox = controller.driver.findElement(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'"+expectedField+"')]//preceding-sibling::span//input[@type='checkbox']"));
				check=controller.getElementAttribute(manageFieldCheckBox, "aria-checked");
				if(check.contains("false"))						
				{
					jsClick(manageFieldCheckBox);
					controller.Logger.addsubStep(LogStatus.PASS, expectedField + " FIELD IS CHECKED", false);	
				}
				else
				{
				controller.Logger.addsubStep(LogStatus.INFO, expectedField + " FIELD IS ALREADY CHECKED", false);	
				}
}
		} catch (Exception e) {
			throw new Exception("selectManageFieldCheckBoxes is not working.."+e);
		}	
}

	
	@SuppressWarnings("static-access")
	public void deSelectManageFieldCheckBoxes(List<String> fields) throws Exception {
		try {
			String check;
			for (String expectedField : fields) {
				WebElement manageFieldCheckBox = controller.driver.findElement(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'"+expectedField+"')]//preceding-sibling::span//input[@type='checkbox']"));
				check=controller.getElementAttribute(manageFieldCheckBox, "aria-checked");
				if(check.contains("true"))						
				{
					jsClick(manageFieldCheckBox);
					controller.Logger.addsubStep(LogStatus.PASS, expectedField + " FIELD IS UNCHECKED", false);	
				}
				else
				{
				controller.Logger.addsubStep(LogStatus.INFO, expectedField + " FIELD IS ALREADY UNCHECKED", false);	
				}
}
		} catch (Exception e) {
			throw new Exception("deSelectManageFieldCheckBoxes is not working.."+e);
		}	
}
	
	public List<String> getManageFieldCheckBoxStatus(List<String> fields) throws Exception {
		try {
			String check;
			List<String>ariacheck = new ArrayList<String>();
			for (String expectedField : fields) {
				WebElement manageFieldCheckBox = controller.driver.findElement(By.xpath("//span[@class='mat-checkbox-label'][contains(text(),'"+expectedField+"')]//preceding-sibling::span//input[@type='checkbox']"));
				check=controller.getElementAttribute(manageFieldCheckBox, "aria-checked");
				ariacheck.add(check);	
			}
			return ariacheck;
		}
		catch (Exception e) {
		throw new Exception("getManageFieldCheckBoxStatus is not working.."+e);
		}
	}	
	
}