package pages.literaturesearch;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import support.Controller;
/**
 * 
 * @author Anand/Rashmi
 *
 */
public class Tab_LiteratureSearch extends Controller{
	
	@FindBy(xpath="//app-result-search-bar/section/section/div[2]/div[2]/div[2]")
	private WebElement getLiteratureResultsCount;
	
	@FindBy(xpath="(//div[contains(.,'Literature')])[5]")
	private WebElement txtLiterature;
	
	@FindBy(xpath="//app-cluster-map/section/mat-expansion-panel/mat-expansion-panel-header")
	private WebElement linkSuggestedKeyword;
	
	@FindBy(xpath="//*[@id='txt_0']")
	private WebElement Keyword;
	
	@FindBy(xpath="//*[@id='chemexp']/section[2]/app-page-search-result/div/app-result-search-bar/section/section/div[2]")
	private WebElement tabLiterature;
	
	@FindBy(xpath="(//mat-card-title[@class='literature-title mat-card-title'])[1]")
	private WebElement linkLiteratureRecord;
	
	@FindBy(xpath="(//span[contains(.,'close')])[3]")
	private WebElement closeLiteratureRecord;
		
	public Tab_LiteratureSearch(Controller controller) {
	super(controller);
	PageFactory.initElements(driver, this);
		}
	
	public String getTextLiteratureName() throws Exception {
		 waitUntilElementIsDisplayed(txtLiterature);
		String literature= controller.getText(txtLiterature);
		return(literature);
	}
	
	public int getLiteratureResultsCount() throws Exception {
		 waitUntilElementIsDisplayed(getLiteratureResultsCount);
		String totaltxt = getText(getLiteratureResultsCount).trim();
		totaltxt = totaltxt.replace(",", "").replaceAll("[\\D]", "");
		return Integer.parseInt(totaltxt);
	}
	
	public void clickOnLiteratureRecord() throws Exception {
		try {
			waitUntilElementIsDisplayed(linkLiteratureRecord);
			jsClick(linkLiteratureRecord);
			controller.waitUntilProgressBarToDisappears();
		} catch (Exception ex) {
			throw new Exception("clickOnLiteratureRecord is not working" + ex);
		}
	}
	
	
	public void clickOnCloseLiteratureRecordView() throws Exception {
		try {
			waitUntilElementIsDisplayed(closeLiteratureRecord);
			jsClick(closeLiteratureRecord);
			controller.waitUntilProgressBarToDisappears();
			} catch (Exception ex) {
			throw new Exception("clickOnCloseLiteratureRecord is not working" + ex);
		}
	}
	
	public void clickOnLinkSuggestedKeyWord() throws Exception {
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
		throw new Exception("clickOnLinkSuggestedKeyWord is not working" + ex);
	}
	}
	
	public void clickOnFirstKeyWord() throws Exception {
		 waitUntilElementIsDisplayed(Keyword);
		 click(Keyword);
		}
	
	public void clickOnTabLiteratureSearch() throws Exception{
		try {
		waitUntilElementIsDisplayed(tabLiterature);
		jsClick(tabLiterature);
		controller.waitUntilProgressBarToDisappears();
			} catch (Exception ex) {
		throw new Exception("clickOnTabLiteratureSearch is not working for :: "+ ex);
		}
	}
	
	public boolean isDisplayedLiteratureRecordCloseIcon() throws Exception {
		try {
			if (!controller.isElementDisplayed(closeLiteratureRecord)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception("isDisplayedLiteratureRecordCloseIcon is not working" + e);
		}
	}
}