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
import pages.patentsearch.Tab_PatentSearch;
import support.Controller;
/**
 * 
 * @author Anand//Rashmi
 *
 */

public class Page_ChemicalSearchResults extends Controller{

	@FindBy(xpath = "//button[contains(@title,'person' )]")
	private WebElement personIcon;
	
	@FindBy(xpath = "//span[contains(.,'Structure')]")
	private WebElement txtStructure;
	
	//btnPersonIconStatus
	
	@FindBy(xpath = "//button[contains(@title,'company/organization' )]")
	private WebElement organizationIcon;

	@FindBy(xpath = "//app-chem-pill-input-modal/section/div/button")
	private WebElement btnAdd;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Enter name')]")
	private WebElement txtCompanyPerson;
	
	/*@FindBy(css="#cdk-overlay-2 > snack-bar-container > simple-snack-bar > span")
	private WebElement companyPersonToastMsg;*/
	
	//body > div.cdk-overlay-container > div > div
	
	@FindBy(css="body > div.cdk-overlay-container > div > div")
	private WebElement companyPersonToastMsg;
	
	//#cdk-overlay-1 > snack-bar-container > simple-snack-bar > span
	@FindBy(css="#cdk-overlay-4 > snack-bar-container > simple-snack-bar > span")
	private WebElement personLimitToastMsg;
	
	@FindBy(css="#cdk-overlay-5 > snack-bar-container > simple-snack-bar > span")
	private WebElement companyLimitToastMsg;
	
	
	//#cdk-overlay-4 > snack-bar-container > simple-snack-bar > span
	
	/*@FindBy(xpath = "//span[contains(.,'Company pill is added')]")
	private WebElement companyPersonToastMsg;*/
	@FindBy(css="body > div.cdk-overlay-container > div > div")
	private WebElement personToastMsg;
	/*@FindBy(xpath = "//span[contains(.,'Person pill is added')]")
	private WebElement personToastMsg;*/
	
	
	@FindBy(xpath="//app-result-paginator-bar/section/section[1]/span[1]/mat-checkbox/label/div/input")
	private WebElement globalCheckBox;
	@FindBy(xpath="//div/div[1]/section[3]/section/button/span/span")
	private WebElement linkCreateNewFolder;
	@FindBy(css="section.result-set > section > app-result-set:nth-child(1) > section > section > section:nth-child(3) > div > span:nth-child(1) > button")
	private WebElement saveIcon;
	@FindBy(css="#saveto-container > section.footer-section > form > section:nth-child(1) > div:nth-child(2) > span")
	private WebElement folderErrorMessage;
	
	@FindBy(xpath="//button[contains(@aria-label,'Next page')]")
	private WebElement arrowNextPage;
	
	@FindBy(xpath="//button[contains(@aria-label,'Previous page')]")
	private WebElement arrowPreviousPage;
	
	@FindBy(xpath="//section[@class='card-section ng-star-inserted']//mat-checkbox[@id='mat-checkbox-1']//div/input[@aria-checked='true']")
	private WebElement singleRecordCheckBox;
	
	@FindBy(css="#saveto-container > section.footer-section > form > section:nth-child(2) > button > span")
	private WebElement btnCreate;
	
	@FindBy(css="span:nth-child(3) > button > span > img")
	private WebElement rsFooterSaveIcon;
	
	@FindBy(xpath="//app-result-set-saveto-modal/div/div[1]/section[3]/section/button/span")
	private WebElement rsFooterCreateNewFolder;
	
	@FindBy(xpath="//section[3]/form/section[1]/mat-form-field/div/div[1]/div[3]/input")
	private WebElement txtFolderName;
	
	@FindBy(css="section.result-count > div")
	private WebElement getResultsCount;
		
	@FindBy(xpath = " //div[@class='chart']//*[name()='svg']//*[name()='circle']")
	private WebElement circleClustermap;
	
	@FindBy(xpath = "//textarea[contains(@maxlength,'2000')]")
	private WebElement txtSearchBox;
	
	//" //section[@class='filter-section ng-star-inserted']//div[contains(text(),'" + filter + "')]"
	//@FindBy(xpath = "//section[@class='filter-section']//div[@class='ng-star-inserted']//mat-expansion-panel[1]")
	@FindBy(xpath = "//section[@class='filter-section ng-star-inserted']//div[contains(text(),'Assignee')]")
	private WebElement collapseExpand;
	
	@FindBy(xpath = "//div[9]/button/span[contains(.,'More filters')]")
	private WebElement moreFiltersDropdown;
	
	@FindBy(xpath = "(//span[contains(.,'More filters')])[4]")
	private WebElement moreSmartFiltersDropdown;

	//@FindBy(xpath = "(//section[@class='filter-details']//div[@class='mat-form-field-infix']//mat-icon)[1]")
	
	@FindBy(xpath = "//div/mat-chip[1]/button/span/mat-icon")
	private WebElement xMark;
	
	
	@FindBy(xpath = "//span[text()='Filtered by 1 fields']")
	private WebElement label_filteredByOneFields;
			
	@FindBy(xpath = "//span[text()=Filtered by 0 fields']")
	private WebElement label_filteredByZeroFields;
	
	@FindBy(xpath = "//div[contains(text(), 'Please enter the date in a valid format: YYYYMMDD, YYYY-MM-DD or YYYY/MM/DD.' )]")
	private WebElement errorMessage_PublicationDate;
	
	@FindBy(xpath = "//div[contains(text(),  'You have entered a date range that specifies a greater than and less than combination (> AND <) that is not possible. Please update your query and resubmit it. ' )]")
	private WebElement errorMessage_FromTOPublicationDate;
	
	@FindBy(xpath = "//mat-icon[contains(.,'search')]")
	private WebElement btnSearchIcon;
	
	@FindBy(xpath = "(//span[@class='cdx-chip-name'])[1]")
	private WebElement keyWordMouseHover;
		
	//@FindBy(xpath = "(//span[@class='cdx-chip-name'])[1]")
	@FindBy(xpath = "(//span[@class='cdx-chip-name ng-star-inserted'])[1]")
	private WebElement mouseHoverFirstKeyWord;
	
	@FindBy(xpath="//mat-chip-list/div/mat-chip[3]/div[2]/span[2]")
	private WebElement KeywordPillText;	
		
	@FindBy(xpath="//app-result-search-bar/section/section/div[1]/div[2]/div[2]")
	private WebElement getPatentResultsCount;
	
	@FindBy(xpath="//app-result-search-bar/section/section/div[2]/div[2]/div[2]")
	private WebElement getLiteratureResultsCount;
		
	@FindBy(xpath = "//div[contains(@class,'tab tab-1')]")
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

	@FindBy(xpath = "//section[2]/app-chem-pills/section/section/div/section[1]/button/span/mat-icon")
	private WebElement clickPlusIcon;
	
	@FindBy(xpath = "//div/div/app-chem-pill-input-modal/section/div/input")
	private WebElement txtKeyWord;
	
	@FindBy(xpath = "//div/div/div[1]/mat-form-field/div/div[1]/div[2]/mat-datepicker-toggle/button")
	private WebElement calenderMark_FromDateInput;
	
	@FindBy(xpath = "//div/div/div[2]/mat-form-field/div/div[1]/div[2]/mat-datepicker-toggle/button")
	private WebElement calenderMark_ToDateInput;
			
	@FindBy(xpath = "//section[@class='filterDetails']//div[contains(@class,'mat-expansion-panel-body')]/div/mat-accordion/mat-expansion-panel")
	private WebElement fields_filters;
	
	@FindBy(xpath = "//div[contains(text(),'Filter')]/ancestor::mat-expansion-panel-header")
	private WebElement linkFilters;
	
	@FindBy(xpath = "//div[contains(text(),'Publication Date')]")
	private WebElement filters_PublicationDate;
	
	@FindBy(xpath = "(//input[@placeholder='YYYY-MM-DD'])[1]")
	private WebElement filtersFromPublicationDate;
	

	@FindBy(xpath = "(//input[@placeholder='YYYY-MM-DD'])[2]")
	private WebElement filterToPublicationDate;
	
	//@FindBy(xpath = "//div//input[contains(@placeholder,'YYYY-MM-DD')]')]")
	//private WebElement format_FromDateInput;
	
	@FindBy(xpath = "//div[1]/mat-form-field/div/div[1]/div[1]/input")
	private WebElement format_FromDateInput;
	
	//@FindBy(xpath = "//input[contains(@class,'fromDateInput')]")
	//private WebElement format_FromDateInput;
	
	@FindBy(xpath = "//div//span[text()='to']")
	private WebElement label_ToDate;
	
	//@FindBy(xpath = "//input[contains(@class,'toDateInput')]")
	@FindBy(xpath = "//div[2]/mat-form-field/div/div[1]/div[1]/input")
	private WebElement format_ToDateInput;
	
	@FindBy(xpath = "//app-page-search-result/div/section/app-no-result-bar//h1")
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
	
	@FindBy(xpath = "//a[@class='company-name']")
	private WebElement chemExpHomePage;
	
	@FindBy(xpath = "//section[contains(@class, 'major-player')] //span[@class='mat-button-wrapper'][contains(.,'Clear all')]")
	WebElement button_ClearAllPlayers;
	
	@FindBy(xpath = "//section[contains(@class, 'major-inventor')] //span[@class='mat-button-wrapper'][contains(.,'Clear all')]")
	WebElement button_ClearAllInventors;
	
	@FindBy(xpath = " //button/span[text()=' Cluster map ']")
	private WebElement linkClustermap;
	
	@FindBy(xpath = " //div[@class='chart']//*[name()='svg']//*[name()='circle']")
	private WebElement bubbleClustermap;
	
	@FindBy(xpath = " //div[contains(@class, 'sub-keyword')]//button[contains(@class, 'cdx-but-link')]")
	private WebElement linkSubKeyword;
	
	@FindBy(xpath = " //div[@class='chart']//*[name()='svg']//*[name()='line'][3]")
	private WebElement bubbleLine;
	
	@FindBy(xpath = " //div[contains(normalize-space(text()), 'Players' )]")
	private WebElement tabPlayers;
	
	@FindBy(xpath = " //div[contains(normalize-space(text()), 'Keywords' )]")
	private WebElement tabKeywords;
	
	@FindBy(xpath = " //section[contains(@class, 'major-player')]//div[@class='chart']//*[name()='svg']//*[name()='rect']")
	private WebElement barPlayers;
	
	@FindBy(xpath = " //section[contains(@class, 'major-player')]//div[@class='chart']//*[name()='svg']//*[name()='text'][3]")
	private WebElement textPlayers;
	
	@FindBy(xpath = " //section[contains(@class, 'major-inventor')]//div[@class='chart']//*[name()='svg']//*[name()='rect']")
	private WebElement barAuthors;
	
	@FindBy(xpath = " //section[contains(@class, 'suggested-keyword')]//div[@class='chart']//*[name()='svg']//*[name()='rect']")
	private WebElement barKeywords;
	
	@FindBy(xpath = "//section[contains(@class, 'suggested-keyword')]//div[@class='chart']//*[name()='svg']//*[name()='text']")
	private WebElement textKeywords;
	
	@FindBy(xpath = " //section[contains(@class, 'major-inventor')]//div[@class='chart']//*[name()='svg']//*[name()='text']")
	private WebElement textAuthors;
	
	@FindBy(xpath = " //section[@class='filter-details']//div[@class='ng-star-inserted']//mat-expansion-panel[1][contains(@class, 'disable-click')]")
	private WebElement inventorFilterDisable;
	
	@FindBy(xpath = "//span[contains(.,'You are in Help mode. Use the toggle in the Support menu to turn the tips on and off any time.')]")
	private WebElement guidedTipsToastMsg;
	
	@FindBy(css = "section > div:nth-child(2) > section.ng-star-inserted > app-tour-guide > section > div.hotspot > mat-icon")
	private WebElement guidedTipsKeyword;
	
	@FindBy(css = "app-suggested-keyword > section > section.title > app-tour-guide > section > div.hotspot > mat-icon")
	private WebElement guidedInsightsTipsKeyword;
	
	@FindBy(css = "section.button-section > section > button > span")
	private WebElement linkManageFields;
	
	@FindBy(xpath = "//app-keyword-search/section/div[2]/section[2]/app-chem-pills/section/section/div/mat-form-field/div/div[1]/div/mat-chip-list/div/mat-chip[1]/div[2]/span[2]")
	private WebElement button_Edit;
	
	@FindBy(xpath = "//section[@class='sketch']")
	private WebElement modifyChemicalStructureModal;
	
	@FindBy(xpath = "//button/span[contains(.,' Apply ')]")
	private WebElement btnApply;
	
	@FindBy(xpath = "//button/span[contains(.,' Cancel ')]")
	private WebElement btnCancel;
	
		
	@FindBy(xpath = "(//mat-icon[@role='button'][contains(.,'close')])[1]")
	private WebElement structurePillX;
	
	@FindBy(css = "polygon[fill='#A9A9B0']")
	private WebElement structureSearchIconDisable;
	
	@FindBy(xpath = "//section/app-keyword-search/section/div[3]/button[1]")
	private WebElement structureSearchIconEnable;
	
	@FindBy(xpath = "//section/section[2]/section[2]/mat-form-field/div/div[1]/div[3]/mat-select")
	private WebElement dropdown_ItemsPerPage;
	
	@FindBy(xpath = "//mat-select/div/div[1]/span/span")
	private WebElement value_Dropdown;
	
      public Page_ChemicalSearchResults(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}
	
	public boolean checkIfResultsFound() throws Exception {
		controller.waitTime(2);
		if (getResultsCount() > 0)
			return true;
		else
			return false;
	}

	public int getResultsCount() throws Exception {
		controller.waitTime(2);
		waitUntilElementIsDisplayed(getResultsCount);
		String resultCount= controller.getText(getResultsCount);
		String[] finalResultCount = resultCount.split(" "); 
		return Integer.parseInt(finalResultCount[0]);
	}
	
	
	public String getTextSearchBox() throws Exception {
		waitUntilElementIsDisplayed(txtSearchBox);
		String searchbox=controller.getElementAttribute(txtSearchBox, "title");
		return(searchbox);
	}

	public void closeAllExistingKeyWords() throws Exception{
		List<WebElement> closekeywords = driver.findElements(By.xpath("//section[2]/app-chem-pills/section/section/div/mat-form-field/div/div[1]/div/mat-chip-list/div/mat-chip/button/span/mat-icon"));
		System.out.println("size" + closekeywords.size());
		if (closekeywords.size() > 0) {
			for (WebElement ele : closekeywords) {
				WebElement closekeyword = driver.findElement(By.xpath("//section[2]/app-chem-pills/section/section/div/mat-form-field/div/div[1]/div/mat-chip-list/div/mat-chip/button/span/mat-icon"));
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
			controller.waitUntilFectchRecordProgressBarToDisappears();
		} catch (Exception ex) {
			throw new Exception("clickOnSearchIcon is not working" + ex);
		}
	}
	
	public void setKeyWords(List <String> keywords) throws Exception {
		try {
		 List<String> keywordtxt = new ArrayList<String>();
		for (String keys: keywords) {
		keywordtxt.add(keys);
		}
		clickPlusIcon.click();
		for(int i=0;i<keywordtxt.size();i++)
			 {
			//waitUntilElementIsDisplayed(mouseHoverFirstKeyWord);
			//Actions action = new Actions(driver);
			//action.moveToElement(mouseHoverFirstKeyWord).perform();
			txtKeyWord.click();
			controller.waitTime(2);
			setText(txtKeyWord, keywordtxt.get(i));
				 new Actions(driver).sendKeys(Keys.ENTER).build().perform();
				 controller.waitTime(2);
				 }
			} catch (Exception e) {
			throw new Exception("setKeyWords is not working.." + e);
		}
	}
	
	public void clickOnRsFooterSaveIcon() throws Exception {
		try {
			waitUntilElementIsDisplayed(rsFooterSaveIcon);
			jsClick(rsFooterSaveIcon);
		} catch (Exception ex) {
			throw new Exception("clickOnRsFooterSaveIcon is not working" + ex);
		}
	}
	
	public void clickOnKeywordPlusIcon() throws Exception {
		try {
			jsClick(clickPlusIcon);
		} catch (Exception ex) {
			throw new Exception("clickOnKeywordPlusIcon is not working" + ex);
		}
	}
	
	public void setFirstKeyWord(List <String> keywords) throws Exception {
		try {
		 List<String> keywordtxt = new ArrayList<String>();
		for (String keys: keywords) {
		keywordtxt.add(keys);
		}
		clickPlusIcon.click();
		for(int i=0;i<keywordtxt.size();i++)
			 {
				txtKeyWord.click();
				setText(txtKeyWord, keywordtxt.get(i));
				 new Actions(driver).sendKeys(Keys.ENTER).build().perform();
				 controller.waitTime(1);
			 }
			} catch (Exception e) {
			throw new Exception("setKeyWords is not working.." + e);
		}
	}
	
	public void AddKeyWordsBottomSection(List <String> keywords) throws Exception {
		try {
		 List<String> keywordtxt = new ArrayList<String>();
		for (String keys: keywords) {
		keywordtxt.add(keys);
		}
		for(int i=0;i<keywordtxt.size();i++)
			 {
				txtKeyWord.click();
				setText(txtKeyWord, keywordtxt.get(i));
				 new Actions(driver).sendKeys(Keys.ENTER).build().perform();
				 controller.waitTime(1);
			 }
			} catch (Exception e) {
			throw new Exception("AddKeyWordsBottomSection is not working.." + e);
		}
	}
	
	public String getTextKeyword() throws Exception {
		String keyword;
		 waitUntilElementIsDisplayed(KeywordPillText);
		 keyword = getText(KeywordPillText);
		 return(keyword);
		}
	
	
	
	public int getPatentResultsCount() throws Exception {
		 waitUntilElementIsDisplayed(getPatentResultsCount);
		String totaltxt = getText(getPatentResultsCount).trim();
		totaltxt = totaltxt.replace(",", "").replaceAll("[\\D]", "");
		return Integer.parseInt(totaltxt);
	}
	
/*	public int getLiteratureResultsCount() throws Exception {
		 waitUntilElementIsDisplayed(getLiteratureResultsCount);
		String totaltxt = getText(getLiteratureResultsCount).trim();
		totaltxt = totaltxt.replace(",", "").replaceAll("[\\D]", "");
		return Integer.parseInt(totaltxt);
	}*/
	
	public String getTabPatentResultsCount() throws Exception,NumberFormatException {
		 waitUntilElementIsDisplayed(driver.findElement(By.xpath("//app-result-search-bar/section/section/div[1]/div[2]/div[2]")));
		String totaltxt = getText(driver.findElement(By.xpath("//app-result-search-bar/section/section/div[1]/div[2]/div[2]"))).trim();
		totaltxt = totaltxt.replaceAll(" ", "");
		return totaltxt;
	}
	
	/*public String getTabLiteratureResultsCount() throws Exception {
		 waitUntilElementIsDisplayed(driver.findElement(By.xpath("//app-result-search-bar/section/section/div[2]/div[2]/div[2]")));
		String totaltxt = getText(driver.findElement(By.xpath("//app-result-search-bar/section/section/div[2]/div[2]/div[2]"))).trim();
		totaltxt = totaltxt.replaceAll(" ", "");
		return totaltxt;
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
	
	public void clickOnXMark() {
		try {
			waitUntilElementIsDisplayed(xMark);
			xMark.click();
			} catch (Exception e) {
			controller.Logger.addException("clickOnMark is not working "+e.getMessage());
		}
	}
	
	
	public void hoverOnPhraseSection() throws Exception {
		try {
			waitUntilElementIsDisplayed(txtSearchBox);
			Actions action = new Actions(driver);
			action.moveToElement(txtSearchBox).perform();
			} catch (Exception ex) {
			throw new Exception("hoverOnPhraseSection is not working" + ex);
		}
	}
	
	
	public void hoverOnFirstKeyword() throws Exception {
		try {
			waitUntilElementIsDisplayed(mouseHoverFirstKeyWord);
			Actions action = new Actions(driver);
			action.moveToElement(mouseHoverFirstKeyWord).perform();
			} catch (Exception ex) {
			throw new Exception("hoverOnFirstKeyword is not working" + ex);
		}
	}

	public void clickOnLinkFilters() throws Exception {
		try {
			super.jsClick(linkFilters);
			} catch (Exception ex) {
			throw new Exception("clickOnlink Filters is not working" + ex);
		}
	}
	
	public void clickOnFiltersPublicationDate() throws Exception {
		try {
			controller.jsScrollToElement(filters_PublicationDate);
			super.jsClick(filters_PublicationDate);
			} catch (Exception ex) {
			throw new Exception("clickOnfilters Publication Date is not working" + ex);
		}
	}
	
	
	public String getDateFormatFromDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filtersFromPublicationDate);
		   // String fromDateInput=controller.getElementAttribute(format_FromDateInput, "aria-label");
		    String fromDateInput=controller.getElementAttribute(filtersFromPublicationDate, "placeholder");
			return fromDateInput;	
		 }catch (Exception e) {
		return "";
		}
	}
	
	public String getDateFormatToDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filterToPublicationDate);
		    String toDateInput=controller.getElementAttribute(filterToPublicationDate, "placeholder" );
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
		 	return ele.getAttribute("color");	
		 }catch (Exception e) {
			 throw new Exception("getColorOfSerachIcon is not working.." + e);
		}
	}
	
	public String getColorOfPillBox(int pillNumber) throws Exception {
		try {
			//waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("//div/mat-chip-list/div/mat-chip["+pillNumber+"]"));
		    return ele.getAttribute("class");
		 }catch (Exception e) {
			 throw new Exception("getColorOfPillBox is not working.." + e);
		}
	}
	
	public String getTextOfSerachBox() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    WebElement ele=driver.findElement(By.cssSelector("section > textarea"));
		    ele.click();
			return ele.getAttribute("title");
		 }catch (Exception e) {
			 throw new Exception("getTextOfSerachBox is not working.." + e);
		}
	}
	
/*	public boolean isDisplayedTabLiterature() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
		    boolean status=controller.isElementDisplayed(tabLiterature);
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}*/
	
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
	
	public void clickOnExpandDropdown() {
		try {
			waitUntilElementIsDisplayed(collapseExpand);
			collapseExpand.click();
			} catch (Exception e) {
			controller.Logger.addException("clickOnExpandDropdown is not working "+e.getMessage());
		}
	}
	public void clickOnMoreFiltersDropdown() {
		try {
			waitUntilElementIsDisplayed(moreFiltersDropdown);
			moreFiltersDropdown.click();
			} catch (Exception e) {
			controller.Logger.addException("clickOnMorefiltersDropdown is not working "+e.getMessage());
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
			phraseElement.clear();
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
			WebElement ele=driver.findElement(By.xpath("//section[1]/mat-hint/span"));
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
			jsClick(format_FromDateInput);			
			setText(format_FromDateInput, dateFormat);
			driver.findElement(By.xpath("//html")).click();
			} catch (Exception e) {
			throw new Exception("setting value to from date input is not working.." + e);
		}
	}
	
	public void setDateInToDateInput(String dateFormat) throws Exception {
		try {
			jsClick(format_ToDateInput);	
			setText(format_ToDateInput, dateFormat);
			driver.findElement(By.xpath("//html")).click();
			} catch (Exception e) {
			throw new Exception("setting value to from date input is not working.." + e);
		}
	}
		
/*	public void clickOnTabLiterature() {
		try {
		
			WebElement ele=driver.findElement(By.xpath("//*[@id='tab-2']"));
			if(controller.getElementAttribute(ele,"class").contains("mat-ripple tab tab-2 active")) 
			{
				controller.Logger.addsubStep(LogStatus.INFO, "Tab Literature is already selected ", false);
			}else 
			{
			   
				driver.findElement(By.xpath("//*[@id='tab-2']")).click();
			    controller.Logger.addsubStep(LogStatus.PASS, "Tab Literature is selected successfully.", false);
				
			}
			try {
				waitUntilFectchRecordProgressBarToDisappears();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		} catch (Exception e) {
			controller.Logger.addException("clickOnTabLiterature is not working"+e.getMessage());
		}
	}*/
	
	public void clickOnTabPatent() {
		try {
			
			WebElement ele=driver.findElement(By.xpath("//div[contains(@class,'tab tab-1')]"));
			
			if(controller.getElementAttribute(ele,"class").contains("mat-ripple tab tab-1 active")) 
			{
				controller.Logger.addsubStep(LogStatus.INFO, "Tab Patent is already selected ", false);
			}else 
			{
			
			    driver.findElement(By.xpath("//div[contains(@class,'tab tab-1')]")).click();
			    controller.Logger.addsubStep(LogStatus.PASS, "Tab Patent is selected successfully.", false);
				
			}
			waitUntilFectchRecordProgressBarToDisappears();
		} catch (Exception e) {
			controller.Logger.addException("clickOnTabPatent is not working"+e.getMessage());
		}
	}
	
	public void clickOnClearAllMark() {
		try {
			buttonCrossMark.click();
			} catch (Exception e) {
			controller.Logger.addException("clickOnClearAllMark is not working "+e.getMessage());
		}
	}
	
	public void clickOnChemExpHomePage() {
		try {
			chemExpHomePage.click();
			} catch (Exception e) {
			controller.Logger.addException("clickOnChemExpHomePage is not working "+e.getMessage());
		}
	}
	
	public String getTopSectionBarText() throws Exception {
		try 
		{
			waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter keywords, phrases or text blocks to search...')]"));
		    String topSectionBarText=controller.getElementAttribute(ele, "title");
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
			//waitUntilElementIsDisplayed(getResultsCount);
			WebElement ele=driver.findElement(By.xpath("(//span[contains(@class,'cdx-chip-name')])["+pillNumber+"]"));
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
          List<WebElement> ele=driver.findElements(By.xpath("//*[@id='mat-chip-list-22']/div/mat-chip"));
          for(WebElement elem:ele)
			{
				if(elem.getAttribute("ng-reflect-disabled").equals("true")) 
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
			WebElement ele=driver.findElement(By.xpath("//section/div[2]/section[1]/textarea"));
		    String blnChkTopSectionEnabled=ele.getAttribute("aria-invalid");
			if(blnChkTopSectionEnabled.equals("false")) 
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
			WebElement ele=driver.findElement(By.xpath("//section/div[2]/section[1]/textarea"));
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
			WebElement ele=driver.findElement(By.xpath("(//span[@class='cdx-chip-name'])["+numPill+"]"));
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
			List<WebElement> ele=driver.findElements(By.xpath("//span[@class='cdx-chip-name']"));
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
				WebElement filterName=driver.findElement(By.xpath("//div[contains(text(),'"+filtersName+"')]"));
				super.jsClick(filterName);
				} catch (Exception ex) {
				throw new Exception("clickOn filter is not working" + ex);
			}
		}
	 public void clickOnAnyCheckboxBasedOnFiltersName(String filtersName,int chkboxPostion) throws Exception {
			try {
				clickOnFiltersBasedOnFiltersName(filtersName);
				WebElement chkboxName=driver.findElement(By.xpath("//div[contains(text(),'"+filtersName+"')]/ancestor::mat-expansion-panel-header/following-sibling::div//mat-selection-list[@role='list']/mat-list-item[@role='listitem']["+chkboxPostion+"]//label"));
				super.jsClick(chkboxName);
				} catch (Exception ex) {
				throw new Exception("checkbox is not selected" + ex);
			}
		}
	 
	
	 public void clickOnChemicalStructureFilterCheckbox(String filtersName,int chkboxPostion) throws Exception {
			try {
				WebElement chkboxName=driver.findElement(By.xpath("//div[contains(text(),'"+filtersName+"')]/ancestor::mat-expansion-panel-header/following-sibling::div//mat-selection-list[@role='list']/mat-list-item[@role='listitem']["+chkboxPostion+"]/div/input[@role='checkbox']"));
				Actions builder = new Actions(driver);
				builder.click(chkboxName).build().perform();
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
	
	/*public Tab_LiteratureSearch tabLiteratureSearch() {
		return new Tab_LiteratureSearch(controller);
	}	*/
	
	public Page_ManageFields manageFields() {
		return new Page_ManageFields(controller);
	}
	
	public Page_FilterChemicalStructure filterChemicalStructure() {
		return new Page_FilterChemicalStructure(controller);
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

public String getTextErrorMessageFromToPubDate() throws Exception {
	try {
		return controller.getText(errorMessage_FromTOPublicationDate);
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

public String getTextNoResultsPage() throws Exception {
	try {
		controller.waitUntilElementIsDisplayed(errorMessage_NoResultsPage);
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
		List<WebElement> listOfPdfLink = driver.findElements(By.cssSelector("section > div > span:nth-child(4) > button > span > mat-icon"));
		for(WebElement firstLink:listOfPdfLink) {
			super.jsClick(firstLink);
			break;
			//controller.waitTime(2);
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

public void clickOnClustermapLink() throws Exception {
	try {
		waitUntilElementIsDisplayed(linkClustermap);
		jsClick(linkClustermap);
	} catch (Exception ex) {
		throw new Exception("clickOnClustermapLink is not working" + ex);
	}
}


public void clickOnBubble() throws Exception {
try {
	waitUntilElementIsDisplayed(bubbleClustermap);
	Actions builder = new Actions(driver);
	builder.click(bubbleClustermap).build().perform();
} 	catch (Exception ex) {
	throw new Exception("clickOnBubble is not working" + ex);
	}
}

public void clickOnSubKeyword() throws Exception {
try {
	waitUntilElementIsDisplayed(linkSubKeyword);
	Actions builder = new Actions(driver);
	builder.click(linkSubKeyword).build().perform();
	} catch (Exception ex) {
		throw new Exception("subkeywordLink is not working" + ex);
}
}
public void clickOnBubbleLine() throws Exception {
try {
	waitUntilElementIsDisplayed(bubbleLine);
	Actions builder = new Actions(driver);
	builder.click(bubbleLine).build().perform();
	} catch (Exception ex) {
		throw new Exception("line between bubble  is not working" + ex);
}
}


public void clickOnPlayersTab() throws Exception {
try {
	waitUntilElementIsDisplayed(tabPlayers);
	jsClick(tabPlayers);
	} catch (Exception ex) {
		throw new Exception("clickOnClustermapLink is not working" + ex);
	}
	}
public void clickOnPlayersBar() throws Exception {
try {
	waitUntilElementIsDisplayed(barPlayers);
	Actions builder = new Actions(driver);
	builder.click(barPlayers).build().perform();
	} catch (Exception ex) {
		throw new Exception("line between bubble  is not working" + ex);
}
}

public void clickOnPlayersText() throws Exception {
	try {
		waitUntilElementIsDisplayed(textPlayers);
		Actions builder = new Actions(driver);
		builder.click(textPlayers).build().perform();
	} catch (Exception ex) {
		throw new Exception("line between bubble  is not working" + ex);
}
}

public boolean isDisabledFilterInventorField() throws Exception {
	try {
		super.jsScrollToElement(inventorFilterDisable);
		return true;
	} catch (Exception e) {
		return false;
	}
}

public void clickOnKeywordsTab() throws Exception {
	try {
		controller.waitTime(2);
		waitUntilElementIsDisplayed(tabKeywords);
		jsClick(tabKeywords);
	} catch (Exception ex) {
		throw new Exception("clickOnTab is not working" + ex);
}
	}

public void clickOnKeywordsBar() throws Exception {
	try {
		waitUntilElementIsDisplayed(barKeywords);
		jsClick(tabKeywords);
	} catch (Exception ex) {
		throw new Exception("clickOnTab is not working" + ex);
}
}
public void clickOnKeywordsText() throws Exception {

try {
	waitUntilElementIsDisplayed(textKeywords);
	Actions builder = new Actions(driver);
	builder.click(textKeywords).build().perform();
}	 catch (Exception ex) {
	throw new Exception("Click on text not working" + ex);
}

}

public void clickOnClearAllPlayers() throws Exception {
	try {
		controller.jsScrollToElement(button_ClearAllPlayers);
		super.jsClick(button_ClearAllPlayers);
	} catch (Exception ex) {
		throw new Exception("clickOn ClearAll Filters is not working" + ex);
}
}

public void clickOnClearAllInventors() throws Exception {
	try {
		controller.jsScrollToElement(button_ClearAllInventors);
		super.jsClick(button_ClearAllInventors);
	} catch (Exception ex) {
		throw new Exception("clickOn ClearAll Filters is not working" + ex);
	}
	}

public String getTextGuidedTipsToastMessage() throws Exception {
	try {
		String expMsg="";
		waitUntilElementIsDisplayed(guidedTipsToastMsg);
		//controller.waitTime(2);
		if(controller.isElementDisplayed(guidedTipsToastMsg))
		expMsg= controller.getText(guidedTipsToastMsg);
		return expMsg;
		} catch (Exception e) {
		throw new Exception("getTextGuidedTipsToastMessage is not working.." + e);
	}
}

public boolean isDisplayedGuidedTipsKeywordCue() throws Exception {
	try {
		waitUntilElementIsDisplayed(guidedTipsKeyword);
		return controller.isElementDisplayed(guidedTipsKeyword);			
	} catch (Exception e) {
		return false;
		
	}
}


public boolean isDisplayedInsightsGuidedTipsKeywordCue() throws Exception {
	try {
		waitUntilElementIsDisplayed(guidedInsightsTipsKeyword);
		return controller.isElementDisplayed(guidedInsightsTipsKeyword);	
	} catch (Exception e) {
		return false;
		
	}
}

public boolean isNotDisplayedGuidedTipsKeywordCue() throws Exception {
	try {
		Boolean isPresent = driver.findElements(By.cssSelector("section > div:nth-child(2) > section.ng-star-inserted > app-tour-guide > section > div.hotspot > mat-icon")).size() < 0;
		return isPresent;
		} catch (Exception e) {
		return false;
		
	}
}

public boolean isNotDisplayedInsightsGuidedTipsKeywordCue() throws Exception {
	try {
		Boolean isPresent = driver.findElements(By.cssSelector("app-suggested-keyword > section > section.title > app-tour-guide > section > div.hotspot > mat-icon")).size() < 0;
		return isPresent;
		} catch (Exception e) {
		return false;
		
	}
}

public void clickOnLinkManageFields() throws Exception {
	try {
		waitUntilElementIsDisplayed(linkManageFields);
		jsClick(linkManageFields);
		} catch (Exception ex) {
		throw new Exception("clickOnLinkManageFields is not working" + ex);
	}
}

public boolean isDisplayedPublished(int recordnumber) throws Exception {
	try {
		WebElement published = controller.driver.findElement(By.xpath("//app-result-set["+recordnumber+"]/section/mat-card/mat-card-content/div[1]/h2[1]"));
		waitUntilElementIsDisplayed(published);
		return controller.isElementDisplayed(published);			
	} catch (Exception e) {
		return false;
		
	}
}

public boolean isDisplayedEarliestFamily(int recordnumber) throws Exception {
	try {
		WebElement earliestFamily = controller.driver.findElement(By.xpath("//app-result-set["+recordnumber+"]/section/mat-card/mat-card-content/div[1]/h2[2]"));
		waitUntilElementIsDisplayed(earliestFamily);
		return controller.isElementDisplayed(earliestFamily);			
	} catch (Exception e) {
		return false;
		
	}
}

public boolean isDisplayedAdvantage(int recordnumber) throws Exception {
	try {
		WebElement advantage = controller.driver.findElement(By.xpath("//app-result-set["+recordnumber+"]/section/mat-card/mat-card-content/div[2]/section"));
		waitUntilElementIsDisplayed(advantage);
		return controller.isElementDisplayed(advantage);			
	} catch (Exception e) {
		return false;
		
	}
}

public boolean isDisplayedFirstClaim(int recordnumber) throws Exception {
	try {
		WebElement firstclaim = controller.driver.findElement(By.xpath("//app-result-set["+recordnumber+"]/section/mat-card/mat-card-content/div[3]/section"));
		waitUntilElementIsDisplayed(firstclaim);
		return controller.isElementDisplayed(firstclaim);			
	} catch (Exception e) {
		return false;
		
	}
}

public boolean isDisplayedKeywords(int recordnumber) throws Exception {
	try {
		WebElement Keywords = controller.driver.findElement(By.xpath("//app-result-set["+recordnumber+"]/section/mat-card/mat-card-content/div[4]/section"));
		waitUntilElementIsDisplayed(Keywords);
		return controller.isElementDisplayed(Keywords);			
	} catch (Exception e) {
		return false;
		
	}
}


public String getTextImageSize(int recordnumber) throws Exception {
	try {
		String size;
		WebElement img = controller.driver.findElement(By.xpath("//app-result-set["+recordnumber+"]/section/section/div[2]/img"));
		controller.waitTime(2);
		size=controller.getElementAttribute(img, "width");
		controller.waitTime(2);
		return size;
		} catch (Exception ex) {
		throw new Exception("getTextImageSize is not working" + ex);
	}
}

public void clickOnRSFooterGlobalCheckBox() throws Exception {
	try {
		waitUntilElementIsDisplayed(globalCheckBox);
		String status = controller.getElementAttribute(globalCheckBox, "aria-checked");
		if(status.equalsIgnoreCase("false"))
		{
			jsClick(globalCheckBox);	
			
		}
		else
		{
			controller.Logger.Logger.log(LogStatus.INFO, "RS Footer Global Check Box is already selected");	
		}
	} catch (Exception ex) {
		throw new Exception("clickOnRSFooterGlobalCheckBox is not working" + ex);
	}
}




public void clickOnRsFooterCreateNewFolder() throws Exception {
	try {
		Actions action = new Actions(driver);
		action.moveToElement(rsFooterCreateNewFolder).click().build().perform();
	} catch (Exception ex) {
		throw new Exception("clickOnRsFooterCreateNewFolder is not working" + ex);
	}
}

public void setTextFolderName(String value) throws Exception {
	try {
		click(txtFolderName);
		txtFolderName.clear();
		setText(txtFolderName, value);
		} catch (Exception e) {
		throw new Exception("setTextFolderName is not working.." + e);
	}
}
public void clickOnLinkCreate() throws Exception {
	try {
		waitUntilElementIsDisplayed(btnCreate);
		jsClick(btnCreate);
	} catch (Exception ex) {
		throw new Exception("clickOnLinkCreate is not working" + ex);
	}
}

public void clickOnNextPage() throws Exception {
	try {
		controller.waitTime(2);
		waitUntilElementIsDisplayed(arrowNextPage);
		jsClick(arrowNextPage);
	} catch (Exception ex) {
		throw new Exception("clickOnNextPage is not working" + ex);
	}
}

public void clickOnPreviousPage() throws Exception {
	try {
		waitUntilElementIsDisplayed(arrowPreviousPage);
		jsClick(arrowPreviousPage);
	} catch (Exception ex) {
		throw new Exception(" clickOnPreviousPage is not working" + ex);
	}
}
@SuppressWarnings("static-access")
public void clickOnFolderNameCheckBox(String checkboxname) throws Exception {
	try {
		WebElement ele = controller.driver.findElement(By.xpath("//label[contains(@title,'"+checkboxname +"')]/preceding-sibling::input[@role='checkbox']"));
		if(!ele.isSelected())
		{	
			Actions action = new Actions(driver);
			action.moveToElement(ele).click().build().perform();
		}
	} catch (Exception ex) {
		throw new Exception("clickonFolderNameCheckBox is not working" + ex);
	}
}

public String getTextFolderName(String checkboxname) throws Exception {
	try {
	WebElement ele = controller.driver.findElement(By.xpath("//label[contains(@title,'"+checkboxname +"')]"));
	String txt = controller.getElementAttribute(ele, "title");
	return txt;
} catch (Exception ex) {
	throw new Exception("getTextFolderName is not working" + ex);
}
}
public void clickOnSaveIcon() throws Exception {
	try {
		waitUntilElementIsDisplayed(saveIcon);
		jsClick(saveIcon);
	} catch (Exception ex) {
		throw new Exception("clickOnSaveIcon is not working" + ex);
	}
}

public void clickOnLinkCreateNewFolder() throws Exception {
	try {
		controller.waitTime(2);
		Actions action = new Actions(driver);
		action.moveToElement(linkCreateNewFolder).click().build().perform();
	} catch (Exception ex) {
		throw new Exception("clickOnLinkCreateNewFolder is not working" + ex);
	}
}

public String getTextFolderErrorMEssage() throws Exception {
	String errMsg;
	 waitUntilElementIsDisplayed(folderErrorMessage);
	 errMsg = getText(folderErrorMessage);
	 return(errMsg);
	}

@SuppressWarnings("static-access")
public int getFolderNameSize(String checkboxname) throws Exception {
	try {
	WebElement ele = controller.driver.findElement(By.xpath("//label[contains(@title,'"+checkboxname+"')]"));
	String txt = controller.getElementAttribute(ele, "title");
	int length=txt.length();
	return length;
} catch (Exception ex) {
	throw new Exception("getFolderNameSize is not working" + ex);
}
}
public boolean isSelectedRSCheckBox(int checkboxnum) throws Exception {
	try {
	@SuppressWarnings("static-access")
	WebElement ele = controller.driver.findElement(By.xpath("//app-result-set["+checkboxnum+"]/section/section/div[1]/span[1]/mat-checkbox"));
	String bool = controller.getElementAttribute(ele, "class");
	if(bool.contains("checked"))
		return true;
	else		
		return false;
} catch (Exception ex) {
	throw new Exception("isSelectedRSCheckBox is not working" + ex);
}
}
public String getColorOfStructureSerachPillBox() throws Exception {
	try {
		waitUntilElementIsDisplayed(getResultsCount);
	    //WebElement ele=driver.findElement(By.xpath("//mat-chip[contains(@class,'mat-chip mat-focus-indicator structure-chip mat-primary mat-standard-chip mat-chip-with-trailing-icon ng-star-inserted')]"));
	  //mat-chip[contains(@class,'mat-chip mat-focus-indicator small structure mat-primary mat-standard-chip ng-star-inserted')]  
		WebElement ele=driver.findElement(By.xpath("//div[2]/section[2]/app-chem-pills/section/section/div/mat-form-field/div/div[1]/div/mat-chip-list/div/mat-chip[1]"));
	   // System.out.println(ele.getCssValue("background-color"));
		return ele.getAttribute("class");
	 }catch (Exception e) {
		 throw new Exception("getColorOfStructureSerachPillBox is not working.." + e);
	}
}
public void clickOnButtonEdit() throws Exception {
	try {
		waitUntilElementIsDisplayed(button_Edit);
		jsClick(button_Edit);
	} catch (Exception ex) {
		throw new Exception("clickOnButtonEdit is not working" + ex);
	}
}

public boolean isDisplayedModifyChemicalStructureModal() throws Exception {
	try {
		return controller.isElementDisplayed(modifyChemicalStructureModal);
		
	} catch (Exception ex) {
		throw new Exception("ModifyChemicalStructureModal is not displayed" + ex);
	}
}

public void clickOnStructureButtonApply() throws Exception {
	try {
		waitUntilElementIsDisplayed(btnApply);
		jsClick(btnApply);
		} catch (Exception ex) {
		throw new Exception("clickOnStructureButtonApply is not working" + ex);
	}
}
public void clickOnStructureModalButtonCancel() throws Exception {
	try {
		waitUntilElementIsDisplayed(btnCancel);
		jsClick(btnCancel);
		} catch (Exception ex) {
		throw new Exception("clickOnStructureModalButtonCancel" + ex);
	}
}

public void clickOnStructurePillX() throws Exception {
	try {
		waitUntilElementIsDisplayed(structurePillX);
		jsClick(structurePillX);
		} catch (Exception ex) {
		throw new Exception("clickOnStructurePillX is not working" + ex);
	}
}
public void clickOnPersonIcon() throws Exception {
	try {
		//waitUntilElementIsDisplayed(personIcon);
		//personIcon.click();
		//jsClick(personIcon);
		//Actions action = new Actions(driver);
		//action.moveToElement(personIcon).perform();
		jsScrollToElement(personIcon);
		jsClick(personIcon);
		} catch (Exception ex) {
		throw new Exception("clickOnPersonIcon is not working" + ex);
	}
}
public void clickOnOrganizationIcon() throws Exception {
	try {
		//waitUntilElementIsDisplayed(organizationIcon);
		//jsClick(organizationIcon);
		//Actions action = new Actions(driver);
		//action.moveToElement(organizationIcon).perform();
		jsScrollToElement(organizationIcon);
		jsClick(organizationIcon);
		} catch (Exception ex) {
		throw new Exception("clickOnOrganizationIcon is not working" + ex);
	}
}

public void clickOnButtonAdd() throws Exception {
	try {
		waitUntilElementIsDisplayed(btnAdd);
		btnAdd.click();
		} catch (Exception ex) {
		throw new Exception("clickOnButtonAdd is not working" + ex);
	}
}

public void setTextPersonOrOrganization(String value) throws Exception {
	try {
		waitUntilElementIsDisplayed(txtCompanyPerson);
		//txtCompanyPerson.click();
		setText(txtCompanyPerson, value);
	} catch (Exception e) {
		throw new Exception("setTextPersonOrOrganization is not working.." + e);
	}

}

public String getTextPersonOrganizationToastMessage() throws Exception {
	try {
		String expMsg="";
		waitUntilElementIsDisplayed(companyPersonToastMsg);
		controller.waitTime(2);
		if(controller.isElementDisplayed(companyPersonToastMsg))
		expMsg= controller.getText(companyPersonToastMsg);
		return expMsg;
		} catch (Exception e) {
		throw new Exception("getTextPersonOrganizationToastMessage is not working.." + e);
	}
}
public String getTextPersonToastMessage() throws Exception {
	try {
		String expMsg="";
		waitUntilElementIsDisplayed(personToastMsg);
		//controller.waitTime(2);
		if(controller.isElementDisplayed(personToastMsg))
		expMsg= controller.getText(personToastMsg);
		return expMsg;
		} catch (Exception e) {
		throw new Exception("getTextPersonToastMessage is not working.." + e);
	}
}
public boolean isDisabledPersonIcon() throws Exception {
	boolean personsIcon = false;
	try {
		waitUntilElementIsDisplayed(personIcon);
		String iconStatus = getElementAttribute(personIcon, "disabled");
		personsIcon = Boolean.parseBoolean(iconStatus);
	} catch (Exception e) {
		throw new Exception("isDisabledPersonIcon is not working" + e);
	}
	return personsIcon;
}
public boolean isDisabledCompanyIcon() throws Exception {
	boolean companyIcon = false;
	try {
		waitUntilElementIsDisplayed(organizationIcon);
		String iconStatus = getElementAttribute(organizationIcon, "disabled");
		companyIcon = Boolean.parseBoolean(iconStatus);
	} catch (Exception e) {
		throw new Exception("isDisabledCompanyIcon is not working" + e);
	}
	return companyIcon;
}
public String getTextPersonLimitToastMessage() throws Exception {
	try {
		String expMsg="";
		waitUntilElementIsDisplayed(personLimitToastMsg);
		//controller.waitTime(2);
		if(controller.isElementDisplayed(personLimitToastMsg))
		expMsg= controller.getText(personLimitToastMsg);
		return expMsg;
		} catch (Exception e) {
		throw new Exception("getTextPersonLimitToastMessage is not working.." + e);
	}
}
public String getTextCompanyLimitToastMessage() throws Exception {
	try {
		String expMsg="";
		waitUntilElementIsDisplayed(companyLimitToastMsg);
		//controller.waitTime(2);
		if(controller.isElementDisplayed(companyLimitToastMsg))
		expMsg= controller.getText(companyLimitToastMsg);
		return expMsg;
		} catch (Exception e) {
		throw new Exception(" getTextCompanyLimitToastMessage is not working.." + e);
	}
}

public String getColorOfPersonPillBox() throws Exception {
	try {
		waitUntilElementIsDisplayed(getResultsCount);
	    WebElement ele=driver.findElement(By.xpath("//mat-chip[contains(@class,'mat-chip mat-focus-indicator mat-primary mat-standard-chip cdx-chip-with-caption state_0 mat-chip-with-trailing-icon ng-star-inserted')]"));
	    System.out.println(ele.getCssValue("background-color"));
		return ele.getCssValue("background-color");	
	 }catch (Exception e) {
		 throw new Exception("getColorOfPersonPillBox is not working.." + e);
	}
}
public String getColorOfCompanyPillBox() throws Exception {
	try {
		waitUntilElementIsDisplayed(getResultsCount);
	    WebElement ele=driver.findElement(By.xpath("//mat-chip[contains(@class,'mat-chip mat-focus-indicator mat-primary mat-standard-chip cdx-chip-with-caption state_0 mat-chip-with-trailing-icon ng-star-inserted')][2]"));
	    System.out.println(ele.getCssValue("background-color"));
		return ele.getCssValue("background-color");	
	 }catch (Exception e) {
		 throw new Exception("getColorOfCompanyPillBox is not working.." + e);
	}
}
public String getTextStructure() throws Exception {
	String structure;
	 waitUntilElementIsDisplayed(txtStructure);
	 structure = getText(txtStructure);
	 return(structure);
	}

public void clickOnPersonOrgPillBox(int pillNumber) throws Exception {
	try 
	{
		waitUntilElementIsDisplayed(getResultsCount);
		WebElement ele=driver.findElement(By.xpath("//div/mat-chip["+pillNumber+"]"));
	    ele.click();
	 }
	catch (Exception e) 
	 {
		throw new Exception("clickOnPersonOrgPillBox is not working.." + e);
	 }
}

public boolean isDisabledStructureSearchIcon() throws Exception {
	try {
		waitUntilElementIsDisplayed(structureSearchIconDisable);
		String fill = getElementAttribute(structureSearchIconDisable, "fill");
		if(fill.contains("#A9A9B0"))
		return true;
		else
		return false;
	} catch (Exception e) {
		throw new Exception("isDisabledStructureSearchIcon is not working" + e);
	}

	}

public boolean isEnabledStructureSearchIcon() throws Exception {
	try {
		waitUntilElementIsDisplayed(structureSearchIconEnable);
		String fill = getElementAttribute(structureSearchIconEnable, "color");
		if(fill.contains("primary"))
		return true;
		else
		return false;
	} catch (Exception e) {
		throw new Exception("isEnabledStructureSearchIcon is not working" + e);
	}

	}

public void clickOnItemsPerPageDropDown() throws Exception {
	try {
		super.jsClick(dropdown_ItemsPerPage);
		} catch (Exception ex) {
		throw new Exception("clickOnItemsPerPageDropDown is not working" + ex);
	}

}


public String getValueFromRsSortOptionsPageDropdown() throws Exception {
	try {
		return controller.getText(value_Dropdown);
		} catch (Exception ex) {
		throw new Exception("getValueFromItemsPerPageDropdown is not working" + ex);
	}
}

public void selectRsSortOptionsFromDropDown(String itemValue) throws Exception {
	try {
		clickOnItemsPerPageDropDown();
        List<WebElement> listOfDropdownValue = driver.findElements(By.xpath("//div/mat-option/span"));
		for (WebElement dropdownValue:listOfDropdownValue)
		{
			if(dropdownValue.getText().equalsIgnoreCase(itemValue))
			{
				dropdownValue.click();
				waitUntilFectchRecordProgressBarToDisappears();
				break;
			}
		}
		} catch (Exception ex) {
		throw new Exception("selectItemsPerPageFromDropDown is not working" + ex);
	}
}


public List<String> getAllFilterOptions(String filtername) throws Exception{
	try {
		clickOnFiltersBasedOnFiltersName(filtername);
		List<WebElement> options = driver.findElements(By.xpath("//label[contains(@for,'smartflt-1-')]"));
			List<String> optionlabels =new ArrayList<String>();
			
			if(options.size() > 0) {
				 for(WebElement element:options) {
					String optionlabel = controller.getText(element);
					optionlabels.add(optionlabel); 					
				}
			}
			return optionlabels;
 	}catch (Exception e) {
 		throw new Exception("getAllFilterOptions is not working" + e);
 	}
}

	public void clickOnLabel() throws Exception {
		try {
	WebElement element = driver.findElement(By.cssSelector("div.cdx-header-branding > div > div > svg"));
	Actions actions = new Actions(driver);
	actions.moveToElement(element).click().build().perform();
	controller.waitTime(3);
	} catch (Exception e) {
		throw new Exception("clickOnLabel is not working on " + e);
	}
}
}
