package pages.literaturesearch;
import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//app-result-count-bar//section//span[@class='result-count']")
	private WebElement getResultsCount;
	
	@FindBy(xpath="//app-result-search-bar/section/section/div[1]/div[2]/div[2]")
	private WebElement getPatentResultsCount;
		
	@FindBy(xpath = "//*[@id='mat-input-1']")
	private WebElement txtSearchBox;
	
	@FindBy(xpath = "//app-keyword-search/section/div[3]/button[2]/span/mat-icon")
	private WebElement btnClearAll;
		
	@FindBy(xpath = "//app-keyword-search/section/div[3]/button[1]/span/mat-icon")
	private WebElement btnSearchIcon;
	
	@FindBy(xpath = "//*[@id='mat-chip-list-0']/div/mat-chip[1]")
	private WebElement keyWordMouseHover;
	
	@FindBy(css = "#mat-chip-list-input-0")
	private WebElement txtKeyWord;
			
	@FindBy(xpath = "//div[@class='ng-star-inserted' and @style='flex: 1 1 100%; box-sizing: border-box; max-width: 100%;']")
	private WebElement recordViewDetails;
		
	@FindBy(xpath = "//input[contains(@class,'fromDateInput')]/parent::div/following-sibling::div//button")
	private WebElement calenderMark_FromDateInput;
	
	@FindBy(xpath = "//input[contains(@class,'toDateInput')]/parent::div/following-sibling::div//button")
	private WebElement calenderMark_ToDateInput;
		
	@FindBy(xpath = "//section[@class='filterDetails']//div[contains(@class,'mat-expansion-panel-body')]/div/mat-accordion/mat-expansion-panel")
	private WebElement fields_filters;
		
	@FindBy(xpath = "//div[contains(text(),' Filters ')]")
	private WebElement linkFilters;
	
	@FindBy(xpath = "//mat-panel-title[contains(text(),'Who are the major players?')]")
	private WebElement label_WhoAreTheMajorPlayers;
	
	@FindBy(xpath = "//mat-panel-title[contains(text(),'Who are the major Inventors?')]")
	private WebElement label_WhoAreTheMajorInventors;

	@FindBy(xpath="//div/h2[contains(text(),'Source')]")
    WebElement labelSource;
	
	@FindBy(xpath="//div/h2[contains(text(),'Source')]/following-sibling::div")
    WebElement label_SourceJournal;
	
	@FindBy(xpath="//div/h2[text()='Organization']")
    WebElement labelOrganization;
	
	@FindBy(xpath="//div/h2[text()='Organization address']")
    WebElement label_OrganizationAddr;
	
	@FindBy(xpath="//div/h2[text()='Language']")
    WebElement label_Langauge;
		
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
	
	
	public int numberOfMajorPlayersAssigneeDisplayedForLiterature() throws Exception {
		try {
			int playersCount=0;
			controller.jsScrollToElement(label_WhoAreTheMajorPlayers);
			List<WebElement> listOfMajorPlayers = driver.findElements(By.xpath("//*[@id='major-player']//*[name()='svg']//*[name()='g' ]//*[name()='text']"));
			
			for(WebElement ele:listOfMajorPlayers)
			{
				if(controller.isElementDisplayed(ele))
				{
					playersCount++;
					if(playersCount==8)
						break;
				}
		    }
			return playersCount;
			} catch (Exception ex) {
			throw new Exception("records are not displaying" + ex);
		}
	}
	
	
	public int numberOfMajorInventorsAssigneeDisplayedForLiterature() throws Exception {
		try {
			int playersCount=0;
			controller.jsScrollToElement(label_WhoAreTheMajorInventors);
			List<WebElement> listOfMajorPlayers = driver.findElements(By.xpath("//*[@id='major-inventor']//*[name()='svg']//*[name()='g']//*[name()='text']"));
			
			for(WebElement ele:listOfMajorPlayers)
			{
				if(controller.isElementDisplayed(ele))
				{
					playersCount++;
					if(playersCount==8)
						break;
				}
		    }
			return playersCount;
			} catch (Exception ex) {
			throw new Exception("records are not displaying" + ex);
		}
	}
	
	public boolean isDisplayedLabelSource() throws Exception {
		try {
		    boolean status=controller.isElementDisplayed(labelSource);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedLabelSourceJournal() throws Exception {
		try {
		    boolean status=controller.isElementDisplayed(label_SourceJournal);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedLiteratureRecordViewField() throws Exception {
		try {
			boolean status=false;
            List<WebElement> listOfSourceData = driver.findElements(By.xpath("//div/h2[contains(text(),'Source')]/following-sibling::section/span"));
			for (WebElement sourceData:listOfSourceData)
			{
				String sourceValue =sourceData.getText();
				if (sourceValue.split(":")[0].trim().equalsIgnoreCase("Volume"))	
				{
					if (sourceValue.split(":")[1].trim().contains("-"))
					{
						controller.Logger.addsubStep(LogStatus.INFO, "volume does not have value", false);
						status=true;
					}
					else
					controller.Logger.addsubStep(LogStatus.INFO, "volume does have value", false);
					status=true;	
				}
				if (sourceValue.split(":")[0].trim().equalsIgnoreCase("Issue"))	
				{
					if (sourceValue.split(":")[1].trim().contains("-"))
					{
						controller.Logger.addsubStep(LogStatus.INFO, "Issue does not have value", false);
						status=true;
					}
					else
					controller.Logger.addsubStep(LogStatus.INFO, "Issue does have value", false);
					status=true;
				}
				
				if (sourceValue.split(":")[0].trim().equalsIgnoreCase("Article Number"))	
				{
					if (sourceValue.split(":")[1].trim().contains("-"))
					{
						controller.Logger.addsubStep(LogStatus.INFO, "Article Number does not have value", false);
						status=true;
					}
					else
					controller.Logger.addsubStep(LogStatus.INFO, "Article number does have value", false);
					status=true;
				}
				
				if (sourceValue.split(":")[0].trim().equalsIgnoreCase("DOI"))	
				{
					if (sourceValue.split(":")[1].trim().contains("-"))
					{
						controller.Logger.addsubStep(LogStatus.INFO, "DOI does not have value", false);
						status=true;
					}
					else
					controller.Logger.addsubStep(LogStatus.INFO, "DOI does have value", false);
					status=true;
				}
				
				if (sourceValue.split(":")[0].trim().equalsIgnoreCase("Published"))	
				{
					if (sourceValue.split(":")[1].trim().contains("-"))
					{
						controller.Logger.addsubStep(LogStatus.INFO, "published does not have value", false);
						status=true;
					}
					else
					controller.Logger.addsubStep(LogStatus.INFO, "published does have value", false);
					status=true;
				}
				
				if (sourceValue.split(":")[0].trim().equalsIgnoreCase("Page"))	
				{
					if (sourceValue.split(":")[1].trim().contains("-"))
					{
						controller.Logger.addsubStep(LogStatus.INFO, "page does not have value", false);
						status=true;
					}
					else
					controller.Logger.addsubStep(LogStatus.INFO, "page does have value", false);
					status=true;
				}
				
			}
			return status;
			} catch (Exception ex) {
			throw new Exception("clickOnlink Filters is not working" + ex);
		}
	}
	
	public boolean isDisplayedLabelOrganization() throws Exception {
		try {
			controller.jsScrollToElement(labelOrganization);
		    boolean status=controller.isElementDisplayed(labelOrganization);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isValueForLabelOrganizationSeparatedBySemicolon() throws Exception {
		try {
			
			boolean status=false;
			controller.jsScrollToElement(labelOrganization);
			List<WebElement> listOfOrganizationValue = driver.findElements(By.xpath("//h2[text()='Organization']/parent::div//span"));
			List<String> listOfValues=controller.getListWebElementText(listOfOrganizationValue);
			if (listOfValues.contains(";"))
			{
				status=true;
			}
			return status;
			} catch (Exception ex) {
			throw new Exception("semicolon has not found" + ex);
		}
	}
	
	
	public boolean isDisplayedLabelOrganizationAddress() throws Exception {
		try {
			controller.jsScrollToElement(label_OrganizationAddr);
		    boolean status=controller.isElementDisplayed(label_OrganizationAddr);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	
	public boolean isDisplayedLabelLanguage() throws Exception {
		try {
			controller.jsScrollToElement(label_Langauge);
		    boolean status=controller.isElementDisplayed(label_Langauge);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public void clickOnTitle(int rowNumber) throws Exception {
		try{
			List<WebElement> ele=driver.findElements(By.xpath("//mat-card-title[@class='literature-title mat-card-title']"));
			WebElement eleTitle=ele.get(rowNumber);
			eleTitle.click();
			waitUntilElementIsDisplayed(recordViewDetails);
		}
		catch (Exception e) 
		{
			throw new Exception("clickOnTitle is not working" + e);
		}
	}
	
	public boolean isDisplayedTitle(int rowNumber) throws Exception {
		try{
			List<WebElement> ele=driver.findElements(By.xpath("//mat-card-title[@class='literature-title mat-card-title']"));
			
			WebElement eleTitle=ele.get(rowNumber);
			boolean blnChkTitle=controller.isElementDisplayed(eleTitle);
		
		if (blnChkTitle)
			return true;
		else
			return false;
		}
		catch (Exception e) {
			return false;
			}
	}
	
	public boolean isDisplayedAbstract(int rowNumber) throws Exception {
		try {
		List<WebElement> ele=driver.findElements(By.xpath("//mat-card-content[@class='mat-card-content']"));
		WebElement eleAbstract=ele.get(rowNumber);
		boolean blnChkAbstract=controller.isElementDisplayed(eleAbstract);
		if (blnChkAbstract)
			return true;
		else
			return false;
		}
		
		catch (Exception e) {
			return false;
			}
		
	}
}