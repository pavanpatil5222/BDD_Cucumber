package pages.patentsearch;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;

import support.Controller;
/**
 * 
 * @author Anand/Rashmi
 *
 */

public class Tab_PatentSearch extends Controller{
		
	@FindBy(xpath="//app-result-search-bar/section/section/div[1]/div[2]/div[2]")
	private WebElement getPatentResultsCount;
	
	@FindBy(xpath="(//div[contains(.,'Patent')])[5]")
	private WebElement txtPatent;
	
	@FindBy(xpath="//*[@id='chemexp']/section[2]/app-page-search-result/div/app-result-search-bar/section/section/div[1]")
	private WebElement tabPatent;
	
	@FindBy(xpath="//app-cluster-map/section/mat-expansion-panel/mat-expansion-panel-header")
	private WebElement linkSuggestedKeyword;
	
	@FindBy(xpath="//*[@id='txt_0']")
	private WebElement Keyword;
	
	@FindBy(xpath="(//mat-card-subtitle[contains(@class,'title mat-card-subtitle')])[1]")
	private WebElement linkPatenRecord;
			
	@FindBy(xpath="(//span[contains(.,'close')])[3]")
	private WebElement closePatentRecord;
	
	@FindBy(xpath="//*[@id='chemexp']//app-most-relavent-card[1]//mat-card-content/div/span")
	private WebElement txtInventors;
	
		public Tab_PatentSearch(Controller controller) {
			super(controller);
			PageFactory.initElements(driver, this);
		}
		
		
		public String getTextPatentName() throws Exception {
			 waitUntilElementIsDisplayed(txtPatent);
			String patent= controller.getText(txtPatent);
			return(patent);
		}
		
		public void clickOnTabPatentSearch() throws Exception{
			try {
			waitUntilElementIsDisplayed(tabPatent);
			jsClick(tabPatent);
			controller.waitUntilProgressBarToDisappears();
				} catch (Exception ex) {
			throw new Exception("clickOnTabPatentSearch is not working for :: "+ ex);
			}
		}
		
		public void clickOnPatentRecord() throws Exception {
			try {
				waitUntilElementIsDisplayed(linkPatenRecord);
				jsClick(linkPatenRecord);
				controller.waitUntilProgressBarToDisappears();
			} catch (Exception ex) {
				throw new Exception("clickOnPatentRecord is not working" + ex);
			}
		}
		
		public void clickOnClosePatentRecordView() throws Exception {
			try {
				waitUntilElementIsDisplayed(closePatentRecord);
				jsClick(closePatentRecord);
				controller.waitUntilProgressBarToDisappears();
				} catch (Exception ex) {
				throw new Exception("clickOnClosePatentRecordView is not working" + ex);
			}
		}
		
		public boolean isDisplayedPatentRecordCloseIcon() throws Exception {
			try {
				if (!controller.isElementDisplayed(closePatentRecord)) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				throw new Exception("isDisplayedPatentRecordCloseIcon is not working" + e);
			}
		}
		
		public int getPatentResultsCount() throws Exception {
			 waitUntilElementIsDisplayed(getPatentResultsCount);
			String totaltxt = getText(getPatentResultsCount).trim();
			totaltxt = totaltxt.replace(",", "").replaceAll("[\\D]", "");
			return Integer.parseInt(totaltxt);
		}
		
		public String getTextInventors() throws Exception {
			String inventors;
			 waitUntilElementIsDisplayed(txtInventors);
			 inventors=controller.getText(txtInventors);
			return inventors;
		}
		
		public void clickOnSuggestedKeyWordlink() throws Exception {
			try
			{
			String status;
			 waitUntilElementIsDisplayed(linkSuggestedKeyword);
			 status= controller.getElementAttribute(linkSuggestedKeyword, "aria-expanded");
			 if(status.equals("false"))
			 {
				 jsClick(linkSuggestedKeyword);
			 }
			
		} catch (Exception ex) {
			throw new Exception("clickOnSuggestedKeyWordlink is not working" + ex);
		}
		}
		
		public void clickOnFirstKeyWord() throws Exception {
			 waitUntilElementIsDisplayed(Keyword);
			 click(Keyword);
			}
}
