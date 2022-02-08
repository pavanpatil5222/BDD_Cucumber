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
 * @author Rashmii/Anand
 *
 */

public class Page_History extends Controller {

	@FindBy(xpath = "//section[1]/div[1]/section/div/table/tbody/tr[1]/td[6]/button[1]")
	private WebElement reRunbutton;
	
	@FindBy(xpath = "//section[2]/section[1]/section[2]/section/mat-checkbox")
	private WebElement globalCheckBox;
	
	@FindBy(xpath = "//div[2]/mat-dialog-actions/button[1]")
	private WebElement confirmationYesButton;
	
	@FindBy(xpath = "//section[2]/section[1]/section[2]/section/section[2]/button")
	private WebElement globalDeleteButton;
	
	@FindBy(xpath = "//app-search-history/section/section[2]/section[1]/section[2]/section/section[1]")
	private WebElement recordCount;
	
	@FindBy(xpath = "//app-search-history/section/section[2]/section[1]/section[1]/div")
	private WebElement txtNoSearchHistory;
	
	public Page_History(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}

	public void clickOnButtonRerunIcon() throws Exception {
		try {
			waitUntilElementIsDisplayed(reRunbutton);
			click(reRunbutton);
			} catch (Exception ex) {
			throw new Exception("clickOnButtonRerunIcon is not working" + ex);
		}
	}
	
	
	public void clickOnRowDeleteButton(int rownumber) throws Exception {
		try {
			WebElement ele = driver.findElement(By.xpath("//section[1]/div[1]/section/div/table/tbody/tr["+rownumber+"]/td[6]/button[2]"));
			ele.click();
			} catch (Exception ex) {
			throw new Exception("clickOnRowDeleteButton is not working" + ex);
		}
	}
	
	
	public void clickOnButtonConfirmationYes() throws Exception {
		try {
			waitUntilElementIsDisplayed(confirmationYesButton);
			click(confirmationYesButton);
			} catch (Exception ex) {
			throw new Exception("clickOnButtonConfirmationYes is not working" + ex);
		}
	}
	
	
	public void clickOnGlobalCheckBox() throws Exception {
		try {
			waitUntilElementIsDisplayed(globalCheckBox);
			click(globalCheckBox);
			} catch (Exception ex) {
			throw new Exception("clickOnGlobalCheckBox is not working" + ex);
		}
	}
	
	public void clickOnGlobalDeleteButton() throws Exception {
		try {
			waitUntilElementIsDisplayed(globalDeleteButton);
			click(globalDeleteButton);
			} catch (Exception ex) {
			throw new Exception("clickOnGlobalDeleteButton is not working" + ex);
		}
	}
	
	
	
	public int getHistoryRecordCount() throws Exception {
		try {
			waitUntilElementIsDisplayed(recordCount);
			String totaltxt = getText(recordCount).trim();
			String[] finalResultCount = totaltxt.split(" ");
			return Integer.parseInt(finalResultCount[0]);
			} catch (Exception ex) {
			throw new Exception("getHistoryRecordCount is not working" + ex);
		}
	}
	
	
	public String getTextErrorMessageNoSearchHistory() throws Exception {
		try {
			controller.waitTime(2);
			return controller.getText(txtNoSearchHistory).trim();
			} catch (Exception ex) {
			throw new Exception("getTextErrorMessageNoSearchHistory is not working" + ex);
		}
	}
}