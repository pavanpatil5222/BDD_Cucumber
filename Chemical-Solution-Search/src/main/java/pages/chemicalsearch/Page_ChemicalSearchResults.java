package pages.chemicalsearch;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import pages.literaturesearch.Tab_LiteratureSearch;
import pages.patentsearch.Tab_PatentSearch;
import support.Controller;
/**
 * 
 * @author Anand/Rashmi
 *
 */

public class Page_ChemicalSearchResults extends Controller{


	@FindBy(xpath="//app-result-count-bar//section/span[1]")
	private WebElement getResultsCount;
		
	@FindBy(xpath = "//textarea[@id='mat-input-1']")
	private WebElement txtSearchBox;
	
	@FindBy(xpath = "//span[text()='Filtered by 1 fields']")
	private WebElement label_filteredByOneFields;
			
	@FindBy(xpath = "//span[text()='Filtered by 0 fields']")
	private WebElement label_filteredByZeroFields;
	
	@FindBy(xpath = "//mat-error[contains(@class,'mat-error')]")
	private WebElement errorMessage_PublicationDate;
	
	@FindBy(xpath = "//mat-icon[contains(.,'search')]")
	private WebElement btnSearchIcon;
	
	@FindBy(xpath = "//*[@id='mat-chip-list-0']/div/mat-chip[1]")
	private WebElement keyWordMouseHover;
		
	@FindBy(xpath="(//span[@class='cdx-chip-name'])[3]")
	private WebElement KeywordPillText;
	
	@FindBy(xpath="(//img[@role='img'])[3]")
	private WebElement thumsUpIcon;
	
	@FindBy(xpath="(//img[@role='img'])[4]")
	private WebElement thumbsDownIcon;
	
	@FindBy(xpath="//app-result-search-bar/section/section/div[1]/div[2]/div[2]")
	private WebElement getPatentResultsCount;
	
	@FindBy(xpath="//app-result-search-bar/section/section/div[2]/div[2]/div[2]")
	private WebElement getLiteratureResultsCount;
		
	@FindBy(xpath = "//div[contains(@ng-reflect-klass,'tab tab-1')]")
	private WebElement tabPatent;
	
	@FindBy(xpath = "//div[contains(@ng-reflect-klass,'tab tab-2')]")
	private WebElement tabLiterature;
	
	@FindBy(xpath = "//app-keyword-search/section/div[3]/button[2]/span/mat-icon")
	private WebElement btnClearAll;
		
	@FindBy(xpath = "//span[not(contains(.,'Your search query is cleared out as action of \"clear all\". You could start a new search and generate new keywords or could undo to go back to previous state.'))]")
	private WebElement notContaintoastMessage;
	
	@FindBy(xpath = "//span[contains(.,'Your search query is cleared out as action of ')]")
	private WebElement toastMessage;
	
	@FindBy(xpath = "//span[@class='mat-button-wrapper'][contains(.,'Undo')]")
	private WebElement textUndo;
	
	@FindBy(xpath = "//button[contains(@title,'clear search')]")
	private WebElement buttonCrossMark;

	@FindBy(css = "input[id^=mat-chip-list-input-]")
	private WebElement txtKeyWord;
	
	@FindBy(xpath = "//input[contains(@class,'fromDateInput')]/parent::div/following-sibling::div//button")
	private WebElement calenderMark_FromDateInput;
	
	@FindBy(xpath = "//input[contains(@class,'toDateInput')]/parent::div/following-sibling::div//button")
	private WebElement calenderMark_ToDateInput;
			
	@FindBy(xpath = "//section[@class='filterDetails']//div[contains(@class,'mat-expansion-panel-body')]/div/mat-accordion/mat-expansion-panel")
	private WebElement fields_filters;
	
	@FindBy(xpath = "//div[contains(text(),'Filter')]/ancestor::mat-expansion-panel-header")
	private WebElement linkFilters;
	
	@FindBy(xpath = "//div[contains(text(),'Publication Date')]")
	private WebElement filters_PublicationDate;
	
	@FindBy(xpath = "//input[contains(@class,'fromDateInput')]")
	private WebElement format_FromDateInput;
	
	//div//span[text()='to']
	@FindBy(xpath = "//div//span[text()='to']")
	private WebElement label_ToDate;
	
	@FindBy(xpath = "//input[contains(@class,'toDateInput')]")
	private WebElement format_ToDateInput;
	
	@FindBy(xpath = "//mat-icon[contains(text(),'biotech')]/following-sibling::h1")
	private WebElement errorMessage_NoResultsPage;
	
	@FindBy(xpath = "//button[@title='Feedback']/span")
	private WebElement linkFeedback;
	
	@FindBy(xpath = "//a/img[@alt='PDF']")
	private WebElement linkPDF;
	
	@FindBy(xpath = "//body[contains(text(),'PDF is not available')]")
	private WebElement page_PdfNotAvailable;
	
	@FindBy(xpath = "//section[@class='filterDetails']//div[contains(@class,'mat-expansion-panel-body')]/mat-accordion//mat-expansion-panel-header//div")
	private WebElement filtersName;
	
	@FindBy(css = "#cluster-map > svg > g > text:nth-child(32)")
	private WebElement suggestedKeyword;
	
	public Page_ChemicalSearchResults(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}
	
	public boolean checkIfResultsFound() throws Exception {
		if (getResultsCount() > 0)
			return true;
		else
			return false;
	}

	public int getResultsCount() throws Exception {
		waitUntilElementIsDisplayed(getResultsCount);
		String resultCount= controller.getText(getResultsCount);
		String[] finalResultCount = resultCount.split(" "); 
		//String totaltxt = finalResultCount[0].trim().replace(",", "").replaceAll("[\\D]", "");
		return Integer.parseInt(finalResultCount[0]);
	}
	
	
	public String getTextSearchBox() throws Exception {
		waitUntilElementIsDisplayed(txtSearchBox);
		String searchbox=controller.getElementAttribute(txtSearchBox, "title");
		return(searchbox);
	}

	public void closeAllExistingKeyWords() throws Exception{
		controller.hoverOnWebelement(keyWordMouseHover);
		List<WebElement> closekeywords = driver.findElements(By.cssSelector("#mat-chip-list-0 > div > mat-chip > mat-icon"));
		System.out.println("size" + closekeywords.size());
		if (closekeywords.size() > 0) {
			for (WebElement ele : closekeywords) {
				WebElement closekeyword = driver.findElement(By.cssSelector("#mat-chip-list-0 > div > mat-chip > mat-icon"));
				waitTime(1);
				jsScrollToElement(closekeyword);
				controller.jsClick(closekeyword);
			}
		}
	}
	
	public void clickOnSearchIcon() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnSearchIcon);
			jsClick(btnSearchIcon);
			controller.waitUntilProgressBarToDisappears();
		} catch (Exception ex) {
			throw new Exception("clickOnSearchIcon is not working" + ex);
		}
	}
	
	public void clickOnThumbsUpIcon() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumsUpIcon);
			jsClick(thumsUpIcon);
			iconStatus=getElementAttribute(thumsUpIcon, "src");
			if(iconStatus.contains("filled"))
			{
			controller.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON CHANGED TO SOLID GREEN COLOR", false); 
			}
			else
			{
			controller.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON DIDNT CHANGED TO SOLID GREEN COLOR", false);	
			}
				
		} catch (Exception ex) {
			throw new Exception("clickOnThumbsUpIcon is not working" + ex);
		}
	}
	
	
	public String checkThumbsUpHollowState() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumsUpIcon);
			iconStatus=getElementAttribute(thumsUpIcon, "src");
			return(iconStatus);
			} catch (Exception ex) {
			throw new Exception("clickOnThumbsUpIcon is not working" + ex);
		}
	}
	
	public String checkThumbsDownHollowState() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumbsDownIcon);
			iconStatus=getElementAttribute(thumbsDownIcon, "src");
			return(iconStatus);
			} catch (Exception ex) {
			throw new Exception("checkThumbsDownHollowState is not working" + ex);
		}
	}
	
	public void clickOnThumbsDownIcon() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumbsDownIcon);
			jsClick(thumbsDownIcon);
			iconStatus=getElementAttribute(thumbsDownIcon, "src");
			if(iconStatus.contains("filled"))
			{
			controller.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON CHANGED TO SOLID GREEN COLOR", false); 
			}
			else
			{
			controller.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON DIDNT CHANGED TO SOLID GREEN COLOR", false);	
			}
				
		} catch (Exception ex) {
			throw new Exception("clickOnThumbsDownIcon is not working" + ex);
		}
	}
	
	public void setKeyWords(List <String> keywords) throws Exception {
		try {
		 List<String> keywordtxt = new ArrayList<String>();
		for (String keys: keywords) {
		keywordtxt.add(keys);
		}
		for(int i=0;i<keywordtxt.size();i++)
			 {
				 setText(txtKeyWord, keywordtxt.get(i));
				 new Actions(driver).sendKeys(Keys.ENTER).build().perform();
				 controller.waitTime(1);
			 }
			} catch (Exception e) {
			throw new Exception("setKeyWords is not working.." + e);
		}
	}
	
	public String getTextKeyword() throws Exception {
		String keyword;
		 waitUntilElementIsDisplayed(KeywordPillText);
		 keyword = getText(KeywordPillText);
		 return(keyword);
		}
	
	public boolean isDisplayedThumbsUpIcon() throws Exception {
		int j=1;
		int i=3;
		boolean status=true;
		try {
			while(j<15)
			{
			if(controller.isElementDisplayed(driver.findElement(By.xpath("(//img[@role='img'])["+i+"]")))) {
			controller.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS DISPLAYED FOR THE RECORD:"+(j)+"", false);
			i=i+3;
			j++;
			} 
			
			else 
			{
			controller.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT DISPLAYED FOR THE RECORD:"+(j)+"", true);
			status=false;
			}
			}
		}
		 catch (Exception e) {
			throw new Exception("isDisplayedThumbsUpIcon is not working" + e);
		}
		return status;
	}
	
	
	public boolean isDisplayedPatentThumbsDownIcon() throws Exception {
		int j=1;
		int i=4;
		boolean status=true;
		try {
			while(j<15)
			{
			if(controller.isElementDisplayed(driver.findElement(By.xpath("(//img[@role='img'])["+i+"]")))) {
			controller.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS DISPLAYED FOR THE RECORD:"+(j)+"", false);
			i=i+3;
			j++;
			} 
			
			else 
			{
			controller.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT DISPLAYED FOR THE RECORD:"+(j)+"", true);
			status=false;
			}
			}
		}
		 catch (Exception e) {
			throw new Exception("isDisplayedLiteratureThumbsDownIcon is not working" + e);
		}
		return status;
	}
	
	
	
	public boolean isDisplayedLiteratureThumbsDownIcon() throws Exception {
		int j=1;
		int i=4;
		boolean status=true;
		try {
			while(j<15)
			{
			if(controller.isElementDisplayed(driver.findElement(By.xpath("(//img[@role='img'])["+i+"]")))) {
			controller.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS DISPLAYED FOR THE RECORD:"+(j)+"", false);
			i=i+2;
			j++;
			} 
			
			else 
			{
			controller.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT DISPLAYED FOR THE RECORD:"+(j)+"", true);
			status=false;
			}
			}
		}
		 catch (Exception e) {
			throw new Exception("isDisplayedLiteratureThumbsDownIcon is not working" + e);
		}
		return status;
	}
	
	
	public int getPatentResultsCount() throws Exception {
		 waitUntilElementIsDisplayed(getPatentResultsCount);
		String totaltxt = getText(getPatentResultsCount).trim();
		totaltxt = totaltxt.replace(",", "").replaceAll("[\\D]", "");
		return Integer.parseInt(totaltxt);
	}
	
	public int getLiteratureResultsCount() throws Exception {
		 waitUntilElementIsDisplayed(getLiteratureResultsCount);
		String totaltxt = getText(getLiteratureResultsCount).trim();
		totaltxt = totaltxt.replace(",", "").replaceAll("[\\D]", "");
		return Integer.parseInt(totaltxt);
	}
	
	public String getTabPatentResultsCount() throws Exception,NumberFormatException {
		 waitUntilElementIsDisplayed(driver.findElement(By.xpath("//div[@ng-reflect-klass='tab tab-1']//div[@class='ng-star-inserted']")));
		String totaltxt = getText(driver.findElement(By.xpath("//div[@ng-reflect-klass='tab tab-1']//div[@class='ng-star-inserted']"))).trim();
		totaltxt = totaltxt.replaceAll(" ", "");
		return totaltxt;
	}
	
	public String getTabLiteratureResultsCount() throws Exception {
		 waitUntilElementIsDisplayed(driver.findElement(By.xpath("//div[@ng-reflect-klass='tab tab-2']//div[@class='ng-star-inserted']")));
		String totaltxt = getText(driver.findElement(By.xpath("//div[@ng-reflect-klass='tab tab-2']//div[@class='ng-star-inserted']"))).trim();
		totaltxt = totaltxt.replaceAll(" ", "");
		return totaltxt;
	}
	
	
	/*public void closeAllExistingKeyWords() throws Exception{
		controller.hoverOnWebelement(keyWordMouseHover);
		List<WebElement> closekeywords = driver.findElements(By.cssSelector("#mat-chip-list-0 > div > mat-chip > mat-icon"));
		System.out.println("size" + closekeywords.size());
		if (closekeywords.size() > 0) {
			for (WebElement ele : closekeywords) {
				WebElement closekeyword = driver.findElement(By.cssSelector("#mat-chip-list-0 > div > mat-chip > mat-icon"));
				waitTime(1);
				jsScrollToElement(closekeyword);
				controller.jsClick(closekeyword);
			}
		}
	}*/
	
	public void clickOnButtonThumsUp(int recordNumber) throws Exception
	{
		try {
			WebElement listOfThumsUp = driver.findElement(By.xpath("(//img[contains(@alt,'vote as relevant') and contains(@src,'/assets/images/thumbup')])["+recordNumber+"]"));
			controller.jsClick(listOfThumsUp);
			controller.Logger.addsubStep(LogStatus.INFO,"Clicked On Thumbs Up Button Successfully", false);
			}
			catch(Exception e) 
			{
				throw new Exception("setKeyWords is not working.." + e);
			}
	}
	
	public boolean isDisplayedButtonThumsUpWithFillColor(int recordNumber) throws Exception
	{
		try 
		{
			WebElement listOfThumsUp = driver.findElement(By.xpath("(//img[contains(@alt,'vote as relevant') and contains(@src,'/assets/images/thumbup')])["+recordNumber+"]"));
			if(controller.getElementAttribute(listOfThumsUp, "src").contains("/assets/images/thumbup-filled.svg")) 
			{
				return true;
			}else 
			{
				return false;
			}
			
		}
		catch(Exception e) 
		{
			return false;
		}
		
	}
	
	public boolean isDisplayedButtonThumsDownWithFillColor(int recordNumber) throws Exception
	{
		try 
		{
			WebElement listOfThumsUp = driver.findElement(By.xpath("(//img[contains(@alt,'vote as relevant') and contains(@src,'/assets/images/thumbdown')])["+recordNumber+"]"));
			if(controller.getElementAttribute(listOfThumsUp, "src").contains("/assets/images/thumbdown-filled.svg")) 
			{
				return true;
			}else 
			{
				return false;
			}
			
		}
		catch(Exception e) 
		{
			return false;
		}
	}
	
	public void clickOnButtonThumsDown(int recordNumber) throws Exception
	{
		try {
		WebElement listOfThumsDown = driver.findElement(By.xpath("(//img[contains(@alt,'vote as relevant') and contains(@src,'/assets/images/thumbdown')])["+recordNumber+"]"));
		controller.jsClick(listOfThumsDown);
		controller.Logger.addsubStep(LogStatus.INFO,"Clicked On Thumbs Down Button Successfully", false);
		}
		catch(Exception e) 
		{
			throw new Exception("setKeyWords is not working.." + e);
		}
	}
	/*public void setKeyWords(List<String> keywords) throws Exception {
		try {
		 List<String> keywordtxt = new ArrayList<String>();
		for (String keys: keywords) {
		keywordtxt.add(keys);
		}
		for(int i=0;i<keywordtxt.size();i++)
			 {
		
			setText(txtKeyWord, keywordtxt.get(i));
				 new Actions(driver).sendKeys(Keys.ENTER).build().perform();
				 controller.waitTime(1);
			 }
			} catch (Exception e) {
			throw new Exception("setKeyWords is not working.." + e);
		}
	}*/
	

	
	public boolean isDisplayedFilterFieldsInCollapsedState() throws Exception {
		try {
			boolean Status = true;
			String filterFieldsAttribute;
			List<WebElement> listOfFilteredFields = driver.findElements(By.xpath("//section[@class='filterDetails']//div[contains(@class,'mat-expansion-panel-body')]/div/mat-accordion/mat-expansion-panel"));
			for (WebElement ele:listOfFilteredFields) {
				filterFieldsAttribute=controller.getElementAttribute(ele, "ng-reflect-expanded");
			if(!filterFieldsAttribute.contentEquals("false"))
			{
				return false;
			}
			} 
			return Status ;
		 }catch (Exception e) {
		return false;
		}
	}
	
	
	public void clickOnLinkFilters() throws Exception {
		try {
			//super.waitUntilElementIsDisplayed(btnClearAll);
			super.jsClick(linkFilters);
			} catch (Exception ex) {
			throw new Exception("clickOnlink Filters is not working" + ex);
		}
	}

	
	public void clickOnFiltersPublicationDate() throws Exception {
		try {
			//super.waitUntilElementIsDisplayed(btnClearAll);
			controller.jsScrollToElement(filters_PublicationDate);
			super.jsClick(filters_PublicationDate);
			} catch (Exception ex) {
			throw new Exception("clickOnfilters Publication Date is not working" + ex);
		}
	}
	
	
	public String getDateFormatFromDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filters_PublicationDate);
		    String fromDateInput=controller.getElementAttribute(format_FromDateInput, "aria-label");
			return fromDateInput;	
		 }catch (Exception e) {
		return "";
		}
	}
	
	public String getDateFormatToDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filters_PublicationDate);
		    String toDateInput=controller.getElementAttribute(format_ToDateInput, "aria-label");
			return toDateInput;	
		 }catch (Exception e) {
		return "";
		}
	}
	

	public boolean isDisplayedCalenderMarkInFromDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filters_PublicationDate);
		    boolean status=controller.isElementDisplayed(calenderMark_FromDateInput);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedCalenderMarkInToDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filters_PublicationDate);
		    boolean status=controller.isElementDisplayed(calenderMark_ToDateInput);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedTabPatent() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    boolean status=controller.isElementDisplayed(tabPatent);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedSearchBoxNextToTabLiturature() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    boolean status=controller.isElementDisplayed(driver.findElement(By.xpath("//div[contains(@ng-reflect-klass,'tab tab-2')]/following-sibling::app-keyword-search//section[@class='ng-star-inserted']")));
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedSearchBoxWithWaterMark() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    boolean status=controller.isElementDisplayed(driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter keywords, phrases or text blocks to search...')]")));
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public String getColorOfButtonSerach() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    WebElement ele=driver.findElement(By.xpath("//button[@title='make a search']"));
		    System.out.println(ele.getCssValue("background-color"));
			return ele.getCssValue("background-color");	
		 }catch (Exception e) {
			 throw new Exception("getColorOfSerachBox is not working.." + e);
		}
	}
	
	public String getColorOfSerachIcon() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    WebElement ele=driver.findElement(By.xpath("//button[@title='make a search']"));
		    System.out.println(ele.getCssValue("color"));
			return ele.getCssValue("color");	
		 }catch (Exception e) {
			 throw new Exception("getColorOfSerachIcon is not working.." + e);
		}
	}
	
	public String getColorOfPillBox(int pillNumber) throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("(//mat-chip[contains(@class,'mat-chip mat-primary mat-standard-chip')])["+pillNumber+"]"));
		    System.out.println(ele.getCssValue("border-color"));
			return ele.getCssValue("border-color");	
		 }catch (Exception e) {
			 throw new Exception("getColorOfPillBox is not working.." + e);
		}
	}
	
	public String getTextOfSerachBox() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    WebElement ele=driver.findElement(By.xpath("//textarea[contains(@id,'mat-input-1')]"));
			return ele.getAttribute("title");
		 }catch (Exception e) {
			 throw new Exception("getColorOfSerachBox is not working.." + e);
		}
	}
	
	public boolean isDisplayedTabLiterature() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    boolean status=controller.isElementDisplayed(tabLiterature);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isNotDisplayedToastMessage() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    boolean status=controller.isElementDisplayed(notContaintoastMessage);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedToastMessage() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    boolean status=controller.isElementDisplayed(toastMessage);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedActionTextUndo() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    boolean status=controller.isElementDisplayed(textUndo);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
	public void setTextPhrase(String phrase) throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement phraseElement=driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter keywords, phrases or text blocks to search...')]"));
			phraseElement.click();
			phraseElement.sendKeys(phrase);
		 }catch (Exception e) {
			 throw new Exception("setTextPhrase is not working.." + e);
		}
	}
	
	public void clickOnTextBoxPhrase() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement phraseElement=driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter keywords, phrases or text blocks to search...')]"));
			phraseElement.click();
		 }catch (Exception e) {
			 throw new Exception("clickOnPhrase is not working.." + e);
		}
	}
	
	public String getErrorMessagePhrase(String phrase) throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
			setTextPhrase(phrase);
			WebElement ele=driver.findElement(By.xpath("//mat-hint[contains(@class,'error mat-hint')]"));
			String errorMessagePhrase=ele.getAttribute("innerText");
		    return errorMessagePhrase;
			 	
		 }catch (Exception e) {
		return "";
		}
	}
	
	
	public void clickOnActionTextUndo() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    textUndo.click();	
		 }catch (Exception e) {
			 throw new Exception("setting value to from date input is not working.." + e);
		}
	}
	
	public void setDateInFromDateInput(String dateFormat) throws Exception {
		try {
			super.click(format_FromDateInput);
			setText(format_FromDateInput, dateFormat);
			} catch (Exception e) {
			throw new Exception("setting value to from date input is not working.." + e);
		}
	}
	
	public void setDateInToDateInput(String dateFormat) throws Exception {
		try {
			super.click(format_ToDateInput);
			setText(format_ToDateInput, dateFormat);
			} catch (Exception e) {
			throw new Exception("setting value to from date input is not working.." + e);
		}
	}
		
	public void clickOnTabLiterature() {
		try {
			//WebElement ele=driver.findElement(By.xpath("//div[contains(@ng-reflect-klass,'tab tab-2')]"));
			WebElement ele=driver.findElement(By.xpath("//div[contains(@class,'tab tab-2')]"));
			if(controller.getElementAttribute(ele,"class").contains("mat-ripple tab tab-2 active")) 
			{
				controller.Logger.addsubStep(LogStatus.INFO, "Tab Literature is already selected ", false);
			}else 
			{
			   // controller.click(tabLiterature);
				driver.findElement(By.xpath("//div[contains(@class,'tab tab-2')]")).click();
			    controller.Logger.addsubStep(LogStatus.PASS, "Tab Literature is selected successfully.", false);
				
			}
			waitUntilProgressBarToDisappears();
		} catch (Exception e) {
			controller.Logger.addException("clickOnTabLiterature is not working"+e.getMessage());
		}
	}
	
	public void clickOnTabPatent() {
		try {
			//WebElement ele=driver.findElement(By.xpath("//div[contains(@ng-reflect-klass,'tab tab-1')]"));
			WebElement ele=driver.findElement(By.xpath("//div[contains(@class,'tab tab-1')]"));
			
			if(controller.getElementAttribute(ele,"class").contains("mat-ripple tab tab-1 active")) 
			{
				controller.Logger.addsubStep(LogStatus.INFO, "Tab Patent is already selected ", false);
			}else 
			{
			   // controller.click(tabPatent);
			    driver.findElement(By.xpath("//div[contains(@class,'tab tab-1')]")).click();
			    controller.Logger.addsubStep(LogStatus.PASS, "Tab Patent is selected successfully.", false);
				
			}
			waitUntilProgressBarToDisappears();
		} catch (Exception e) {
			controller.Logger.addException("clickOnTabPatent is not working"+e.getMessage());
		}
	}
	
	public void clickOnClearAllMark() {
		try {
			buttonCrossMark.click();
			//waitUntilElementIsDisplayed(toastMessage);
		} catch (Exception e) {
			controller.Logger.addException("clickOnClearAllMark is not working "+e.getMessage());
		}
	}
	
	
	public String getTopSectionBarText() throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("//textarea[contains(@id,'mat-input-1')]"));
		    String topSectionBarText=controller.getElementAttribute(ele, "ng-reflect-model");
			return topSectionBarText;	
		 }
		catch (Exception e) 
		 {
			return "";
		 }
	}
	
	public String getTopSectionBarCompleteText() throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("//textarea[contains(@id,'mat-input-1')]"));
			controller.jsScrollToElement(ele);
		    String topSectionFullText=controller.getElementAttribute(ele, "title");
			return topSectionFullText;	
		 }
		catch (Exception e) 
		 {
			return "";
		 }
	}
	
	public boolean isDisplayedPillBox(int pillNumber) throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("(//div[@class='cdx-chip-block']//span[@class='cdx-chip-name'])["+pillNumber+"]"));
		    boolean toDateInput=controller.isElementDisplayed(ele);
			return toDateInput;	
		 }
		catch (Exception e) 
		 {
			return false;
		 }
	}
	
	public void isDisabledBottomSection() throws Exception {
		try 
		{
          waitUntilElementIsDisplayed(getResultsCount);
			List<WebElement> ele=driver.findElements(By.xpath("//mat-chip[contains(@class,'mat-chip mat-primary mat-standard-chip')]"));
			for(WebElement elem:ele)
			{
				if(elem.getAttribute("disabled").equals("true")) 
				{
					controller.Logger.addsubStep(LogStatus.PASS,"Bottom Section text is in Disable Mode", false);
				}
				else 
				{
					controller.Logger.addsubStep(LogStatus.FAIL,"Bottom Section text is in Enable Mode", true);
				}
			 }	
		   }
		catch (Exception e) 
		 {
			throw new Exception("isEnabledBottomSection is not working.." + e);
		 }
	}
	
	public boolean isDisabledTopSection() throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("//textarea[contains(@id,'mat-input-1')]"));
		    String blnChkTopSectionEnabled=ele.getAttribute("disabled");
			if(blnChkTopSectionEnabled.equals("true")) 
			{	
				return true;
			}
			else {
				return false;
			}
		 }
		catch (Exception e) 
		 {
			return false;
		 }
	}
	
	public String getColorOfButtomSection() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("//div[@class='mat-chip-list-wrapper']"));
		    System.out.println(ele.getCssValue("background-color"));
			return ele.getCssValue("background-color");	
		 }catch (Exception e) {
			 throw new Exception("getColorOfButtomSection is not working.." + e);
		}
	}
	
	public String getColorOfTopSection() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("//textarea[contains(@id,'mat-input-1')]"));
		    System.out.println(ele.getCssValue("background-color"));
			return ele.getCssValue("background-color");	
		 }catch (Exception e) {
			 throw new Exception("getColorOfTopSection is not working.." + e);
		}
	}
	
	public List<String> getListOfPillBoxes() throws Exception {
		try 
		{
			List<String> listOfPills=new ArrayList<String>();
			waitUntilElementIsDisplayed(getResultsCount);
			List<WebElement> ele=driver.findElements(By.xpath("//div[@class='cdx-chip-block']//span[@class='cdx-chip-name']"));
			for(WebElement stringText:ele) 
			{
				listOfPills.add(stringText.getAttribute("innerText"));
			}
			
			return listOfPills;	
		 }
		catch (Exception e) 
		 {
			throw new Exception("getListOfPillBoxes is not working.." + e);
		 }
	}
	
	
	public void mouseOverToPillBox(int numPill) throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("(//div[@class='cdx-chip-block']//span[@class='cdx-chip-name'])["+numPill+"]"));
			new Actions(driver).moveToElement(ele).build().perform();
		 }
		catch (Exception e) 
		 {
			throw new Exception("mouseOverToPillBox is not working.." + e);
		 }
	}
	
	
	public int getNoOfPillBoxes() throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			List<WebElement> ele=driver.findElements(By.xpath("//div[@class='cdx-chip-block']//span[@class='cdx-chip-name']"));
			int noOfPillBoxes=ele.size();
			return noOfPillBoxes;
		 }
		catch (Exception e) 
		 {
			throw new Exception("getListOfPillBoxes is not working.." + e);
		 }
	}
	
	public boolean isDisplayedRemoveIcon(int removeIconNumber) throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("(//mat-icon[contains(@class,'mat-icon notranslate mat-chip-remove mat-chip-trailing-icon')])["+removeIconNumber+"]"));
			boolean blnChkRemoveIcon=controller.isElementDisplayed(ele);
			return blnChkRemoveIcon;	
		 }
		catch (Exception e) 
		 {
			return false;
		 }
	}
	
	public void removePillBox(int removeIconNumber) throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("(//mat-icon[contains(@class,'mat-icon notranslate mat-chip-remove mat-chip-trailing-icon')])["+removeIconNumber+"]"));
			ele.click();
			
		 }
		catch (Exception e) 
		 {
			throw new Exception("removePillBox is not working.." + e);
		 }
	}
	
	public boolean isDisplayedTextBoxKeyword() throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("//input[contains(@id,'mat-chip-list-input') and @placeholder='Enter keywords...']"));
			boolean blnChkKeywordText=controller.isElementDisplayed(ele);
			return blnChkKeywordText;	
		 }
		catch (Exception e) 
		 {
			return false;
		 }
	}
	
	public void clickOnPillBox(int pillNumber) throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("(//div[@class='cdx-chip-block']//span[@class='cdx-chip-name'])["+pillNumber+"]"));
		    ele.click();
		 }
		catch (Exception e) 
		 {
			throw new Exception("clickOnPillBox is not working.." + e);
		 }
	}
	
	public void enterTextAnywhereInSearchBox(String typeText,int positionToTypeText) {

	    WebElement textarea=driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter keywords, phrases or text blocks to search...')]"));;

	    insert(textarea, typeText, positionToTypeText);
	}
	
	public void insert(WebElement textElement, String insertText, int offset) {
		Robot robot = null; 
	    String currentText = textElement.getAttribute("title");
	    int len = currentText.length();
	    if (len < offset) {
	        throw new IllegalArgumentException(String.format("len(%d) < offset(%d)", len, offset));
	    }
	    
	    try
       {
       robot = new Robot();
       //robot.mouseMove(50, 50);
       }
       catch (AWTException e)
       {
       e.printStackTrace();
       }
	   
	    robot.setAutoDelay(20);
	    // On focus.
	    textElement.click();
	    // Move cursor for head.
	    type(robot, KeyEvent.VK_CONTROL, KeyEvent.VK_HOME);
	    for (int i = 0; i < offset; i++) {
	        type(robot, KeyEvent.VK_RIGHT);
	    }
	    textElement.sendKeys(insertText);
	}

	public void type(Robot robot, int... keycodes) {
	    for (int keycode : keycodes) {
	        robot.keyPress(keycode);
	    }
	    for (int keycode : keycodes) {
	        robot.keyRelease(keycode);
	    }
	}
	
	 public void deleteText() throws Exception {
        try {
        WebElement txtArea = driver.findElement(
        By.xpath("//textarea"));
        txtArea.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
            } catch (Exception e) {
            throw new Exception("deleteText is not working.." + e);
        }
    }
	 
	 public void clickOnFiltersBasedOnFiltersName(String filtersName) throws Exception {
			try {
				//super.waitUntilElementIsDisplayed(btnClearAll);
				//WebElement filterName=driver.findElement(By.xpath("//span[@class='mat-content']//mat-icon[text()='arrow_drop_down']/following-sibling::span[text()='"+filtersName+"']"));
				WebElement filterName=driver.findElement(By.xpath("//span[@class='mat-content']//div[contains(text(),'"+filtersName+"')]"));
				//span[@class='mat-content']//div[contains(text(),'Publication year')]
				
				super.jsClick(filterName);
				} catch (Exception ex) {
				throw new Exception("clickOn filter is not working" + ex);
			}
		}
	
	 public void clickOnAnyCheckboxBasedOnFiltersName(String filtersName,int chkboxPostion) throws Exception {
			try {
				//super.waitUntilElementIsDisplayed(btnClearAll);
				clickOnFiltersBasedOnFiltersName(filtersName);
			//	WebElement chkboxName=driver.findElement(By.xpath("//span[text()='"+filtersName+"']/ancestor::mat-expansion-panel-header/following-sibling::div//mat-selection-list[@role='listbox']/mat-list-item[@role='listitem']["+chkboxPostion+"]"));
				//span[text()='Publication country/region']/ancestor::mat-expansion-panel-header/following-sibling::div//mat-selection-list[@role='listbox']/mat-list-item[@role='listitem'][1]//input
				WebElement chkboxName=driver.findElement(By.xpath("//div[contains(text(),'"+filtersName+"')]/ancestor::mat-expansion-panel-header/following-sibling::div//mat-selection-list[@role='listbox']/mat-list-item[@role='listitem']["+chkboxPostion+"]//label"));
				//super.click(chkboxName);
				super.jsClick(chkboxName);
				} catch (Exception ex) {
				throw new Exception("checkbox is not selected" + ex);
			}
		}
	 
	 public void clickOnButtonApply() throws Exception {
			try {
				
				WebElement buttonApply=driver.findElement(By.xpath("//button[@class='cdx-but-md mat-flat-button mat-button-base mat-primary'][contains(.,'Apply filters')]"));
				super.jsClick(buttonApply);
				} catch (Exception ex) {
				throw new Exception("clickOnButtonApply is not selected" + ex);
			}
		}
	
	public Tab_PatentSearch tabPatentSearch() {
		return new Tab_PatentSearch(controller);
	}
	
	public Tab_LiteratureSearch tabLiteratureSearch() {
		return new Tab_LiteratureSearch(controller);
	}	
	
	public boolean isDisplayedFilterFieldsInCollapsedState(List<String> listOfFilters) throws Exception {
		
		try {
			boolean Status = true;
			String filterFieldsAttribute;
			for (String filters:listOfFilters)
			{
				WebElement listOfFilteredFields = driver.findElement(By.xpath("//span/div[contains(text(),'"+filters+"')]//ancestor::mat-expansion-panel-header"));
				filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
			    if(filters.equalsIgnoreCase("Publication country/region") || filters.equalsIgnoreCase("Publication year"))
			    	
			    {
			    	if(!filterFieldsAttribute.contentEquals("true"))
						return false;
			    }
			
			    else
			    	
			    	if(!filterFieldsAttribute.contentEquals("false"))
					{
						return false;
					}
			    
			}
			
			return Status;
			
	 }catch (Exception e) {
		return false;
		}
	}
	
	public boolean isDisplayedLabelFilteredByZeroFields() throws Exception {
		try {
		    boolean status=controller.isElementDisplayed(label_filteredByZeroFields);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	
public boolean isDisplayedFilterFieldsInExpandedCollapsedState(List<String> listOfFilters) throws Exception {
		
		try {
			boolean Status = true;
			String filterFieldsAttribute;
			for (String filters:listOfFilters)
			{
				WebElement listOfFilteredFields = driver.findElement(By.xpath("//span/div[contains(text(),'"+filters+"')]//ancestor::mat-expansion-panel-header"));
				if(filters.equalsIgnoreCase("Publication country/region") || filters.equalsIgnoreCase("Publication year"))
			    {
			    	listOfFilteredFields.click();
			    	filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
			    	if(!filterFieldsAttribute.contentEquals("false"))
						return false;
			    }
			    else {
			    	listOfFilteredFields.click();
			        filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
			    	if(!filterFieldsAttribute.contentEquals("true"))
					{
						return false;
					}
			    }
			}
			for (String filters:listOfFilters)
			{
				WebElement listOfFilteredFields = driver.findElement(By.xpath("//span/div[contains(text(),'"+filters+"')]//ancestor::mat-expansion-panel-header"));
				if(filters.equalsIgnoreCase("Publication country/region") || filters.equalsIgnoreCase("Publication year"))
			    {
			    	listOfFilteredFields.click();
			    	filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
			    	if(!filterFieldsAttribute.contentEquals("true"))
						return false;
			    }
			    else {
			    	listOfFilteredFields.click();
			    filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
			    	if(!filterFieldsAttribute.contentEquals("false"))
					{
						return false;
					}
			    }
			}
			return Status;
			
	 }catch (Exception e) {
		return false;
		}
	}

public boolean isDisplayedLabelFilteredByOneFields() throws Exception {
	try {
	    boolean status=controller.isElementDisplayed(label_filteredByOneFields);
		return status;	
	 }catch (Exception e) {
	return false;
	}
}

public String getTextErrorMessagePubDate() throws Exception {
	try {
		return controller.getText(errorMessage_PublicationDate);
		} catch (Exception ex) {
		throw new Exception("error msg has not displayed" + ex);
	}
}


public boolean isDisplayedFilterFieldsInExpandedCollapsedState() throws Exception {
	try {
		System.out.println();
		boolean Status = true;
		String filterFieldsAttribute;
		List<WebElement> listOfFilteredFields = driver.findElements(By.xpath("//section[@class='filterDetails']//div[contains(@class,'mat-expansion-panel-body')]/mat-accordion/mat-expansion-panel/mat-expansion-panel-header"));
		for (WebElement ele:listOfFilteredFields) {
			ele.click();
			filterFieldsAttribute=controller.getElementAttribute(ele, "aria-expanded");
		if(!filterFieldsAttribute.contentEquals("true"))
		{
			return false;
		}
		} 
		
		for (WebElement ele:listOfFilteredFields) {
			ele.click();
			filterFieldsAttribute=controller.getElementAttribute(ele, "aria-expanded");
			if(!filterFieldsAttribute.contentEquals("false"))
			{
				return false;
			}
		}
		return Status ;
	 }catch (Exception e) {
	return false;
	}
}


public void selectDateFromPublicationDateCalender(int day) throws Exception {
	try {
		List<WebElement> listOfDays = driver.findElements(By.xpath("//div[@class='mat-calendar-body-cell-content' or contains(@class,'mat-calendar-body-today')]"));
		for (WebElement ele:listOfDays) {
			String getDaysText=ele.getText();
			int i=Integer.parseInt(getDaysText); 
			if(i==day)
			{
				ele.click();
				break;
			}
		}
	} catch (Exception e) {
		throw new Exception("select date is not working" + e);
	}
}



public void clickOnCalenderIconFromDateInput() throws Exception {
	try {
		super.jsClick(calenderMark_FromDateInput);
		} catch (Exception ex) {
		throw new Exception("clickOn calender icon is not working" + ex);
	}
}

public void clickOnCalenderIconToDateInput() throws Exception {
	try {
		super.jsClick(calenderMark_ToDateInput);
		} catch (Exception ex) {
		throw new Exception("clickOn calender icon is not working" + ex);
	}
}


public boolean isDisplayedInventorWithMoreThanOneRecord() throws Exception {
	try {
		boolean Status = true;
		String filterFieldsAttribute;
		List<WebElement> listOfFilteredFields = driver.findElements(By.xpath("//section[text()='Inventor']//following-sibling::div/span[contains(@class,'pipe-sep')]"));
		for (WebElement ele:listOfFilteredFields) {
			
			
		filterFieldsAttribute=controller.getElementAttribute(ele, "aria-expanded");
		if(!filterFieldsAttribute.contentEquals("false"))
		{
			return false;
		}
		} 
		return Status ;
	 }catch (Exception e) {
	return false;
	}
}

public String getTextNoResultsPage() throws Exception {
	try {
		return controller.getText(errorMessage_NoResultsPage);
		} catch (Exception ex) {
		throw new Exception("No Results Page error msg has not displayed" + ex);
	}
}

public void clickOnLinkFeedback() throws Exception {
	try {
		super.jsClick(linkFeedback);
		} catch (Exception ex) {
		throw new Exception("clickOnlink feedback is not working" + ex);
	}
}

public void selectTemplateFromTemplateDropDown(String templateName) throws Exception {
	try {
		super.switchToIFrame(super.element_iframe);
	//	controller.jsClick(this.dropdown_Template);
	//	controller.waitUntilElementIsDisplayed(this.menuItem_template);
		// click(menuItem_template);
	//	String xpath = "//li[@title ='" + templateName + "']";
		
		String xpath = "//mat-select[contains(@placeholder,'Choose a topic')]//div//span[text()='" + templateName + "']";
		System.out.println(xpath);
		WebElement selectTemplate = driver.findElement(By.xpath(xpath));
		controller.waitUntilElementIsDisplayed(selectTemplate);
		controller.jsClick(selectTemplate);
		driver.switchTo().defaultContent();
	} catch (Exception e) {
		throw new Exception("selectTemplateFromTemplateDropDown is not loaded respective template" + e);
	}
}

public void clickOnLinkPdf() throws Exception {
	try {
		List<WebElement> listOfPdfLink = driver.findElements(By.xpath("//a/img[@alt='PDF']"));
		for(WebElement firstLink:listOfPdfLink) {
			super.jsClick(firstLink);
			break;
		}
		} catch (Exception ex) {
		throw new Exception("clickOnlink pdf is not working" + ex);
	}
}

public String getTextPdfNotAvailable() throws Exception {
	try {
		return controller.getText(page_PdfNotAvailable);
		} catch (Exception ex) {
		throw new Exception(" " + ex);
	}
}

public boolean isDisplayedScrollBarForWhoAreTheMajorPlayers() throws Exception{
	try {
	  
	  JavascriptExecutor javascript = (JavascriptExecutor) driver;
	  Boolean scrollBarStatus = (Boolean) javascript.executeScript("return document.getElementById('major-player').scrollWidth>document.getElementById('major-player').clientWidth;");
	  return scrollBarStatus;  
	}catch (Exception ex) {
			throw new Exception("scrollbar is not present");
		}
}

public boolean isDisplayedScrollBarForWhoAreTheMajorInventors() throws Exception{
	try {
	  
	  JavascriptExecutor javascript = (JavascriptExecutor) driver;
	  Boolean scrollBarStatus = (Boolean) javascript.executeScript("return document.getElementById('major-inventor').scrollWidth>document.getElementById('major-inventor').clientWidth;");
	  return scrollBarStatus;  
	}catch (Exception ex) {
			throw new Exception("scrollbar is not present");
		}
}

public boolean isDisplayedWhoAreTheMajorPlayersInCollapsedState() throws Exception {
	try {
		System.out.println();
		boolean Status = true;
		String filterFieldsAttribute;
		WebElement listOfFilteredFields = driver.findElement(By.xpath("//section[@class='major-player']//mat-expansion-panel-header"));
		filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
		if(filterFieldsAttribute.contentEquals("true"))
		{
			listOfFilteredFields.click();
			super.waitTime(2);
			filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
			if(!filterFieldsAttribute.contentEquals("false"))
			{
			return false;
			}
		}
		return Status;
	 }catch (Exception e) {
	return false;
	}
}

public boolean isDisplayedWhoAreTheMajorInventorsInCollapsedState() throws Exception {
	try {
		System.out.println();
		boolean Status = true;
		String filterFieldsAttribute;
		WebElement listOfFilteredFields = driver.findElement(By.xpath("//section[@class='major-inventor']//mat-expansion-panel-header"));
		filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
		if(filterFieldsAttribute.contentEquals("true"))
		{
			listOfFilteredFields.click();
			super.waitTime(2);
			filterFieldsAttribute=controller.getElementAttribute(listOfFilteredFields, "aria-expanded");
			if(!filterFieldsAttribute.contentEquals("false"))
			{
			return false;
			}
		}
		return Status;
	 }catch (Exception e) {
	return false;
	}
}



public Tab_LiteratureSearch tabLiterature() {
	return new Tab_LiteratureSearch(controller);
}

public Tab_PatentSearch tabPatent() {
	return new Tab_PatentSearch(controller);
}
}

