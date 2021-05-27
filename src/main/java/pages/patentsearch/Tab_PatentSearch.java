package pages.patentsearch;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.relevantcodes.extentreports.LogStatus;

import support.Controller;

/**
 * 
 * @author Anand/Rashmi
 *
 */

public class Tab_PatentSearch extends Controller {

	
	@FindBy(xpath="(//span[contains(.,'View as result set')])[1]")
    private WebElement citingPatentViewAsResultSet;
       
    @FindBy(xpath="(//span[contains(.,'View as result set')])[2]")
    private WebElement citedPatentViewAsResultSet;

    @FindBy(xpath = "(//mat-panel-title[contains(@class,'mat-expansion-panel-header-title citation-headers')])[1]")
    private WebElement citingPatentLink;
    
    @FindBy(xpath = "(//mat-panel-title[contains(@class,'mat-expansion-panel-header-title citation-headers')])[2]")
    private WebElement citedPatentLink;    

	/*@FindBy(xpath = "//mat-accordion/mat-expansion-panel[6]/mat-expansion-panel-header/span/mat-panel-title")
	private WebElement citingPatentLink;

	@FindBy(xpath = "//mat-accordion/mat-expansion-panel[7]/mat-expansion-panel-header/span/mat-panel-title")
	private WebElement citedPatentLink;*/

	@FindBy(xpath = "//app-record-view/section/div//mat-accordion/mat-expansion-panel[6]/mat-expansion-panel-header")
	private WebElement citingPatentLinkAttribute;

	@FindBy(xpath = "//app-result-paginator-bar/section[1]/span[1]/mat-checkbox/label/div/input")
	private WebElement globalCheckBox;
	
	@FindBy(css = "app-keyword-search > mat-chip-list > div > mat-chip > div > span")
	private WebElement patentViewAsResultSet;

	@FindBy(css = "div > div.sec1 > div.publication-number")
	WebElement publicationNumber;

	@FindBy(xpath = "//app-result-paginator-bar//section//span[contains(@class , 'record-selected-txt')]")
	private WebElement recordSelection;

	@FindBy(xpath = "//section[@class='card-section ng-star-inserted']//mat-checkbox//div/input")
	private WebElement singleRecordCheckBox;

	@FindBy(xpath = "//span[@class='mat-option-text']")
	private List<WebElement> dropdownFields;

	@FindBy(xpath = "//app-record-view/section/div//mat-accordion/mat-expansion-panel[7]/mat-expansion-panel-header")
	private WebElement citedPatentLinkAttribute;

	@FindBy(xpath = "//mat-expansion-panel[6]/mat-expansion-panel-header/span/mat-panel-title")
	private WebElement citingPatentCount;

	@FindBy(xpath = "//mat-expansion-panel[7]/mat-expansion-panel-header/span/mat-panel-title")
	private WebElement citedPatentCount;

	@FindBy(xpath = "//table[contains(@class, 'citation-family')]//button[1]")
	private WebElement citingTitle;

	@FindBy(xpath = "(//table[contains(@class, 'citation-family')])[2]//button[1]")
	private WebElement citedTitle;

	@FindBy(xpath = "//app-record-view/section/div/div[1]/div[1]/div")
	private WebElement patRecordViewTitle;

	@FindBy(xpath = "//app-record-view/section/div//mat-accordion//mat-expansion-panel[4]//mat-expansion-panel-header")
	private WebElement dwpiPatentTableAttribute;

	@FindBy(xpath = "//app-record-view/section/div//mat-accordion//mat-expansion-panel[4]//mat-expansion-panel-header/span/mat-panel-title")
	private WebElement dwpiTableLink;

	@FindBy(xpath = "//table[contains(@class, 'dw-family')]//button[1]")
	private WebElement dwpiPublicationNumber;

	@FindBy(xpath = "//app-record-view/section/div/div[1]/div[1]/div")
	private WebElement recordViewDwpiPublicationNumber;

	@FindBy(xpath = "//div[@class='mat-select-arrow']")
	private WebElement dropdownMoreFilters;

	@FindBy(xpath = "//section[contains(@class, 'add')]//button[contains(@class, 'add-button')]")
	private WebElement button_Add;
	@FindBy(css = "section > app-result-set:nth-child(1) > section > mat-card > mat-card-content > div:nth-child(1) > section.assignee.ng-star-inserted > p > span > span > a")
	private WebElement linkAssignee;

	@FindBy(css = "div > mat-chip > div > span > span")
	private WebElement moreLikeKeyword;

	@FindBy(css = "section.insights-chart > app-suggested-keyword > section > section.view.disable-click")
	private WebElement keywordDisable;

	@FindBy(css = "section > app-result-set:nth-child(1) > section > mat-card > mat-card-content > div:nth-child(1) > section.inventor.ng-star-inserted > p > span > span:nth-child(1) > a")
	private WebElement linkInventor;

	@FindBy(css = "app-result-set:nth-child(1) > section > aside > section:nth-child(4) > div")
	private WebElement linkMoreLikeThis;

	@FindBy(css = "#mat-chip-list-0 > div > mat-chip > mat-icon")
	private WebElement moreLikeCloseIcon;

	@FindBy(css = "app-result-set:nth-child(1) > section > aside > div:nth-child(1) > span.pn")
	private WebElement rsPublicationNumber;

	@FindBy(css = "div:nth-child(1) > section.assignee > span > span > a")
	private WebElement patentRecordViewAssignee;

	@FindBy(css = "div:nth-child(1) > section.inventor > span > span:nth-child(1) > a")
	private WebElement patentRecordViewInventor;

	@FindBy(xpath = "//app-result-search-bar/section/section/div[1]/div[2]/div[2]")
	private WebElement getPatentResultsCount;

	@FindBy(xpath = "(//div[contains(.,'Patent')])[5]")
	private WebElement txtPatent;

	@FindBy(xpath = "//*[@id='chemexp']//section[2]//h1")
	WebElement element_FetchRecord;

	@FindBy(xpath = "//*[@id='chemexp']/section[2]/app-page-search-result/div/app-result-search-bar/section/section/div[1]")
	private WebElement tabPatent;

	@FindBy(xpath = "//app-cluster-map/section/mat-expansion-panel/mat-expansion-panel-header")
	private WebElement linkSuggestedKeyword;

	@FindBy(xpath = "//*[@id='plus_txt_0']")
	private WebElement Keyword;

	@FindBy(xpath = "//span[contains(text(),'Apply filters')]/parent::button")
	WebElement button_ApplyFilter;

	@FindBy(xpath = " //section[contains(@class, 'major-inventor')]//div[@class='chart']//*[name()='svg']//*[name()='rect']")
	private WebElement barInventors;

	@FindBy(xpath = " //div[contains(normalize-space(text()), 'Inventors' )]")
	private WebElement tabInventors;

	@FindBy(xpath = " //section[contains(@class, 'major-inventor')]//div[@class='chart']//*[name()='svg']//*[name()='text']")
	private WebElement textInventors;

	@FindBy(xpath = " //section[@class='filter-details']//div[@class='ng-star-inserted']//mat-expansion-panel[1][contains(@class, 'disable-click')]")
	private WebElement filterFieldDisable;

	@FindBy(xpath = "//span[@class='mat-button-wrapper'][contains(.,'Clear all')]")
	WebElement button_ClearAllFilter;

	@FindBy(xpath = "(//mat-card-subtitle[contains(@class,'title mat-card-subtitle')])[1]")
	private WebElement linkPatenRecord;

	@FindBy(xpath = "//span[@class='material-icons'][contains(.,'close')]")
	private WebElement closePatentRecord;

	@FindBy(css = "div > svg > g > text:nth-child(31)")
	private WebElement patnetInsightsKeyword;

	@FindBy(xpath = "//app-result-count-bar//section//span[@class='result-count']")
	private WebElement getResultsCount;

	@FindBy(xpath = "//app-result-search-bar/section/section/div[2]/div[2]/div[2]")
	private WebElement getLiteratureResultsCount;

	@FindBy(xpath = "//textarea[@ng-reflect-maxlength='2000']")
	private WebElement txtSearchBox;

	@FindBy(xpath = "//app-keyword-search/section/div[3]/button[2]/span/mat-icon")
	private WebElement btnClearAll;

	@FindBy(xpath = "//app-keyword-search/section/div[3]/button[1]/span/mat-icon")
	private WebElement btnSearchIcon;

	@FindBy(xpath = "//*[@id='mat-chip-list-0']/div/mat-chip[1]")
	private WebElement keyWordMouseHover;

	@FindBy(xpath = "//div[@class='ng-star-inserted' and @style='flex: 1 1 100%; box-sizing: border-box; max-width: 100%;']")
	private WebElement recordViewDetails;

	@FindBy(css = "#mat-chip-list-input-0")
	private WebElement txtKeyWord;

	@FindBy(xpath = "//*[@id='chemexp']//app-most-relavent-card[1]//mat-card-content/div/span")
	private WebElement txtInventors;

	@FindBy(xpath = "//input[contains(@class,'fromDateInput')]/parent::div/following-sibling::div//button")
	private WebElement calenderMark_FromDateInput;

	@FindBy(xpath = "//input[contains(@class,'toDateInput')]/parent::div/following-sibling::div//button")
	private WebElement calenderMark_ToDateInput;

	@FindBy(xpath = "//section[@class='filterDetails']//div[contains(@class,'mat-expansion-panel-body')]/div/mat-accordion/mat-expansion-panel")
	private WebElement fields_filters;

	@FindBy(xpath = "//input[contains(@class,'fromDateInput')]")
	private WebElement format_FromDateInput;

	@FindBy(xpath = "//input[contains(@class,'toDateInput')]")
	private WebElement format_ToDateInput;

	@FindBy(xpath = "//div[text()='Publication Date']")
	private WebElement filters_PublicationDate;

	@FindBy(xpath = "//div[contains(text(),' Filters ')]")
	private WebElement linkFilters;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Who are the major players?')]")
	private WebElement label_WhoAreTheMajorPlayers;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Who are the major Inventors?')]")
	private WebElement label_WhoAreTheMajorInventors;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'What are the suggested keywords?')]/ancestor::app-cluster-map/preceding-sibling::*//child::div[contains(text(),'Filter')]")
	private WebElement labelFilters_SuggestedKeywords;

	@FindBy(xpath = "//div[@class='mat-select-value']/span/span")
	private WebElement value_Dropdown;

	@FindBy(xpath = "//button[contains(@aria-label,'Next page')]")
	private WebElement arrow_NextPage;

	@FindBy(xpath = "//mat-select[contains(@aria-label,'Items per page:')]")
	WebElement dropdown_ItemsPerPage;

	@FindBy(xpath = "//span[contains(text(),'close')]/ancestor::button")
	WebElement closeMarkX;

	@FindBy(xpath = "//label//strong[contains(text(),'')]")
	WebElement tagName_Highlighted;

	@FindBy(xpath = "//div[contains(@class,'mat-paginator-range-label')]")
	private WebElement label_PaginatorRange;

	@FindBy(xpath = "//section[@class='filterDetails']//div[contains(@class,'mat-chip-list-wrapper')]")
	WebElement box_PillFilter;

	@FindBy(xpath = "//div/h2[contains(text(),'Novelty')]")
	WebElement field_Novelty;

	@FindBy(xpath = "//div/h2[contains(text(),'Detailed Description')]")
	WebElement field_DetailedDesc;

	@FindBy(xpath = "//div/h2[contains(text(),'Use')]")
	WebElement field_Use;

	@FindBy(xpath = "//div/h2[contains(text(),'Advantage')]")
	WebElement field_Advantage;

	@FindBy(xpath = "//div/h2[contains(text(),'Technology Focus')]")
	WebElement field_TechFocus;

	@FindBy(xpath = "//div/h2[contains(text(),'Technology Focus')]/parent::div/following-sibling::div/h2[contains(text(),'Definitions')]")
	WebElement field_TechFocusDefinitions;

	@FindBy(xpath = "//div/h2[contains(text(),'Technology Focus')]/parent::div/following-sibling::div/h2[contains(text(),'Example')]")
	WebElement field_TechFocusExamples;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Claims')]")
	WebElement field_Claims;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'DWPI family')]")
	WebElement field_DWPIFamily;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Description')]")
	WebElement field_Desciption;

	@FindBy(xpath = "//div[contains(@class,'record-title')]")
	WebElement recordTitle;

	@FindBy(xpath = "//div[contains(@class,'date')]/span[contains(text(),'published:')]")
	WebElement text_Published;

	@FindBy(xpath = "//div[contains(@class,'date')]/span[contains(text(),'Status:')]")
	WebElement text_Status;

	@FindBy(xpath = "//div[contains(@class,'date')]/span[contains(text(),'Status:')]/b")
	WebElement value_Status;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Bibliography')]")
	WebElement section_Bibliography;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Bibliography')]/ancestor::mat-expansion-panel-header")
	WebElement field_Bibliography;

	@FindBy(xpath = "//div/h2[contains(text(),'Assignee')]")
	WebElement field_Assignee;

	@FindBy(xpath = "//div/h2[contains(text(),'Inventor')]")
	WebElement field_Inventor;

	@FindBy(xpath = "//div/h2[contains(text(),'Priority')]")
	WebElement field_Priority;

	@FindBy(xpath = "//div/h2[contains(text(),'Application')]")
	WebElement field_Application;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Images')]")
	WebElement section_Image;

	@FindBy(xpath = "//mat-panel-title[contains(text(),'Abstract')]/ancestor::mat-expansion-panel-header")
	WebElement field_Abstract;

	@FindBy(xpath = "//div/section/app-result-count-bar/section/section[3]")
	WebElement btn_TabInsights;

	@FindBy(xpath = "//app-result-count-bar/section/section[3]/button")
	WebElement btn_Insights;

	@FindBy(css = "section > app-result-set:nth-child(1) > section > aside > section:nth-child(3) > div > span:nth-child(2) > button > span > mat-icon")
	private WebElement thumsUpIcon;

	@FindBy(css = "section > app-result-set:nth-child(1) > section > aside > section:nth-child(3) > div > span:nth-child(3) > button > span > mat-icon")
	private WebElement thumbsDownIcon;

	@FindBy(xpath = "//app-publication-year/section/mat-expansion-panel/mat-expansion-panel-header/span[2]")
	private WebElement linkPublicationYear;

	@FindBy(css = "#mat-expansion-panel-header-0 > span.mat-content")
	private WebElement publicationYearLabel;

	@FindBy(xpath = "//app-publication-year/section/mat-expansion-panel/div/div/section/button[1]/span/mat-icon")
	private WebElement publicationYearLeftArrow;

	@FindBy(xpath = "//app-publication-year/section/mat-expansion-panel/div/div/section/button[2]/span/mat-icon")
	private WebElement publicationYearRightArrow;

	@FindBy(xpath = "//div[contains(text(),'Publication year')]/ancestor::mat-expansion-panel-header")
	private WebElement publicationYearFilter;

	@FindBy(xpath = " //div[@class='publ-year-chart']")
	private WebElement publicationYearFilterDisable;

	public Tab_PatentSearch(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}

	public String getTextPatentName() throws Exception {
		waitUntilElementIsDisplayed(txtPatent);
		String patent = controller.getText(txtPatent);
		return (patent);
	}

	public void clickOnPatentRecord(int rowNumber) throws Exception {
		try {
			WebElement ele = driver.findElement(By.cssSelector("app-result-set:nth-child(" + rowNumber
					+ ") > section > mat-card > mat-card-header > div > mat-card-subtitle"));
			ele.click();
			controller.waitUntilFectchRecordProgressBarToDisappears();
		} catch (Exception ex) {
			throw new Exception("clickOnPatentRecord is not working" + ex);
		}
	}

	public void clickOnClosePatentRecordView() throws Exception {
		try {
			waitUntilElementIsDisplayed(closePatentRecord);
			jsClick(closePatentRecord);
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
		inventors = controller.getText(txtInventors);
		return inventors;
	}

	public void clickOnSuggestedKeyWordlink() throws Exception {
		try {
			String status;
			waitUntilElementIsDisplayed(linkSuggestedKeyword);
			status = controller.getElementAttribute(linkSuggestedKeyword, "aria-expanded");
			if (status.equals("false")) {
				jsClick(linkSuggestedKeyword);
			}

		} catch (Exception ex) {
			throw new Exception("clickOnSuggestedKeyWordlink is not working" + ex);
		}
	}

	public void clickOnButtonFirstKeyWord() throws Exception {
		try {
			waitUntilElementIsDisplayed(Keyword);
			click(Keyword);

		} catch (Exception ex) {
			throw new Exception("clickOnButtonFirstKeyWord is not working" + ex);
		}
	}

	public boolean isDisplayedTitle(int rowNumber) throws Exception {
		try {
			List<WebElement> ele = driver
					.findElements(By.xpath("//mat-card-subtitle[@class='title ul mat-card-subtitle']"));

			WebElement eleTitle = ele.get(rowNumber);
			boolean blnChkTitle = controller.isElementDisplayed(eleTitle);

			if (blnChkTitle)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnTitle(int rowNumber) throws Exception {
		try {
			List<WebElement> ele = driver
					.findElements(By.xpath("//mat-card-subtitle[@class='title mat-card-subtitle']"));
			WebElement eleTitle = ele.get(rowNumber);
			eleTitle.click();
			waitUntilElementIsDisplayed(recordViewDetails);
		} catch (Exception e) {
			throw new Exception("clickOnTitle is not working" + e);
		}
	}

	public boolean isDisplayedPN(int rowNumber) throws Exception {
		try {
			List<WebElement> ele = driver.findElements(By.xpath("(//span[contains(@class,'pn')])"));
			WebElement elePN = ele.get(rowNumber);
			boolean blnChkPN = controller.isElementDisplayed(elePN);

			if (blnChkPN)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedAbstract(int rowNumber) throws Exception {
		try {
			List<WebElement> ele = driver.findElements(By.xpath("//mat-card-content[@class='mat-card-content']"));
			WebElement eleAbstract = ele.get(rowNumber);
			boolean blnChkAbstract = controller.isElementDisplayed(eleAbstract);
			if (blnChkAbstract)
				return true;
			else
				return false;
		}

		catch (Exception e) {
			return false;
		}

	}

	public boolean isDisplayedUse(int rowNumber) throws Exception {
		try {
			List<WebElement> ele = driver
					.findElements(By.xpath("//div[@class='ng-star-inserted']//section[text()='Use']"));
			WebElement eleUse = ele.get(rowNumber);
			boolean blnChkUse = controller.isElementDisplayed(eleUse);
			if (blnChkUse)
				return true;
			else
				return false;
		}

		catch (Exception e) {
			return false;
		}

	}

	public boolean isDisplayedNovelty(int rowNumber) throws Exception {
		try {
			List<WebElement> ele = driver
					.findElements(By.xpath("//div[@class='ng-star-inserted']//section[text()='Novelty']"));
			WebElement eleNovelty = ele.get(rowNumber);
			boolean blnChkNovelty = controller.isElementDisplayed(eleNovelty);
			if (blnChkNovelty)
				return true;
			else
				return false;
		}

		catch (Exception e) {
			return false;
		}

	}

	public boolean isDisplayedImg(int rowNumber) throws Exception {
		try {
			WebElement eleImage = driver.findElement(By.cssSelector(
					"app-result-set:nth-child(" + rowNumber + ") > section > aside > div.image-block > img"));
			boolean blnChkImage = controller.isElementDisplayed(eleImage);
			if (blnChkImage)
				return true;
			else
				return false;
		}

		catch (Exception e) {
			return false;
		}

	}

	public boolean isDisplayedFilterFieldsInCollapsedState() throws Exception {
		try {
			boolean Status = true;
			String filterFieldsAttribute;
			List<WebElement> listOfFilteredFields = driver.findElements(By.xpath(
					"//section[@class='filterDetails']//div[contains(@class,'mat-expansion-panel-body')]/div/mat-accordion/mat-expansion-panel"));
			for (WebElement ele : listOfFilteredFields) {
				filterFieldsAttribute = controller.getElementAttribute(ele, "ng-reflect-expanded");
				if (!filterFieldsAttribute.contentEquals("false")) {
					return false;
				}
			}
			return Status;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnLinkFilters() throws Exception {
		try {
			// super.waitUntilElementIsDisplayed(btnClearAll);
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
			controller.jsScrollToElement(filters_PublicationDate);
			String fromDateInput = controller.getElementAttribute(format_FromDateInput, "aria-label");
			return fromDateInput;
		} catch (Exception e) {
			return "";
		}
	}

	public String getDateFormatToDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filters_PublicationDate);
			String toDateInput = controller.getElementAttribute(format_ToDateInput, "aria-label");
			return toDateInput;
		} catch (Exception e) {
			return "";
		}
	}

	public int getCountOfHighlightedTextInRS(String searchText) throws Exception {
		try {
			String lowerCasesearchText = searchText.toLowerCase();
			List<WebElement> elements;
			elements = driver.findElements(By.xpath("//mark[contains(text(),'" + lowerCasesearchText + "')]"));
			return elements.size();
		} catch (Exception e) {
			throw new Exception("getCountOfHighlightedTextInRS is not working.." + e);
		}
	}

	public boolean isDisplayedCalenderMarkInFromDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filters_PublicationDate);
			boolean status = controller.isElementDisplayed(calenderMark_FromDateInput);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedCalenderMarkInToDateInput() throws Exception {
		try {
			controller.jsScrollToElement(filters_PublicationDate);
			boolean status = controller.isElementDisplayed(calenderMark_ToDateInput);
			return status;
		} catch (Exception e) {
			return false;
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

	public void clickOnButtonApplyFilters() throws Exception {
		try {
			controller.jsScrollToElement(button_ApplyFilter);
			super.jsClick(button_ApplyFilter);
		} catch (Exception ex) {
			throw new Exception("clickOnbutton apply Filters is not working" + ex);
		}
	}

	public void clickOnButtonClearAllFilters() throws Exception {
		try {
			controller.jsScrollToElement(button_ClearAllFilter);
			super.jsClick(button_ClearAllFilter);
			controller.waitTime(2);
		} catch (Exception ex) {
			throw new Exception("clickOnbutton ClearAll Filters is not working" + ex);
		}
	}

	public boolean isEnabledButtonClearAll() throws Exception {
		try {
			waitTime(2);
			return controller.isElementDisplayed(button_ClearAllFilter);

		} catch (Exception e) {
			return false;

		}
	}

	public boolean isDisabledButtonClearAll() throws Exception {
		try {
			waitTime(2);
			return controller.isElementDisplayed(button_ClearAllFilter);

		} catch (Exception e) {
			return false;

		}
	}

	public boolean isDisplayedFilterAboveSuggestedKeywords() throws Exception {
		try {
			boolean status = controller.isElementDisplayed(labelFilters_SuggestedKeywords);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSectionFilterInExpandedCollapsedState() throws Exception {
		try {

			boolean Status = true;
			String filterAttribute;
			WebElement sectionFilter = driver
					.findElement(By.xpath("//div[contains(text(),'Filter')]/ancestor::mat-expansion-panel-header"));
			filterAttribute = controller.getElementAttribute(sectionFilter, "aria-expanded");
			if (!filterAttribute.contentEquals("false")) {
				return false;
			}
			clickOnLinkFilters();
			filterAttribute = controller.getElementAttribute(sectionFilter, "aria-expanded");
			if (!filterAttribute.contentEquals("true")) {
				return false;
			}
			clickOnLinkFilters();
			filterAttribute = controller.getElementAttribute(sectionFilter, "aria-expanded");
			if (!filterAttribute.contentEquals("false")) {
				return false;
			}
			return Status;
		} catch (Exception e) {
			return false;
		}
	}

	public String getTextPaginatorRange() throws Exception {
		try {
			return controller.getText(label_PaginatorRange);
		} catch (Exception ex) {
			throw new Exception("no range fetched" + ex);
		}
	}

	public String getValueFromItemsPerPageDropdown() throws Exception {
		try {
			return controller.getText(value_Dropdown);
		} catch (Exception ex) {
			throw new Exception("dropdown value has not fetched" + ex);
		}
	}

	public boolean isDisabledPreviousPageArrow() throws Exception {
		try {

			boolean Status = true;
			String previousPageAttribute;
			WebElement previousPage = driver.findElement(By.xpath("//button[contains(@aria-label,'Previous page')]"));
			previousPageAttribute = controller.getElementAttribute(previousPage, "disabled");
			if (!previousPageAttribute.contentEquals("true")) {
				return false;
			}
			return Status;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnArrowNextPage() throws Exception {
		try {
			super.jsClick(arrow_NextPage);
		} catch (Exception ex) {
			throw new Exception("clickOnlink Filters is not working" + ex);
		}
	}

	public void clickOnArrowNextPageTillLastPage() throws Exception {
		try {

			String paginationRange = getTextPaginatorRange();
			String[] paginationValue = paginationRange.split("of");
			String nextRange = paginationValue[1].trim();
			int j = Integer.parseInt(nextRange);
			String firstRange = paginationValue[0].trim();
			String[] firstRangeAfterSplit = firstRange.split("–");
			String valueForFirstRange = firstRangeAfterSplit[1].trim();
			int i = Integer.parseInt(valueForFirstRange);
			String selectedDropdownValue = getValueFromItemsPerPageDropdown();
			int selectedValue = Integer.parseInt(selectedDropdownValue);
			while (i < j) {
				clickOnArrowNextPage();
				waitUntilFectchRecordProgressBarToDisappears();
				i += selectedValue;
			}
		} catch (Exception ex) {
			throw new Exception("clickOn arrow next page is not working" + ex);
		}
	}

	public boolean isDisabledNextPageArrow() throws Exception {
		try {

			boolean Status = true;
			String nextPageAttribute;
			clickOnArrowNextPageTillLastPage();
			WebElement arrowNextPage = driver.findElement(By.xpath("//button[contains(@aria-label,'Next page')]"));
			nextPageAttribute = controller.getElementAttribute(arrowNextPage, "disabled");
			if (!nextPageAttribute.contentEquals("true")) {
				return false;
			}
			return Status;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnItemsPerPageDropDown() throws Exception {
		try {
			controller.waitTime(2);
			this.waitUntilElementIsDisplayed(dropdown_ItemsPerPage);
			super.jsClick(dropdown_ItemsPerPage);
			controller.waitTime(2);
		} catch (Exception ex) {
			throw new Exception("clickOn dropdown is not working" + ex);
		}
	}

	public void selectItemsPerPageFromDropDown(String itemValue) throws Exception {
		try {
			clickOnItemsPerPageDropDown();
			controller.waitTime(2);
			List<WebElement> listOfDropdownValue = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
			for (WebElement dropdownValue : listOfDropdownValue) {
				if (dropdownValue.getText().equalsIgnoreCase(itemValue)) {
					dropdownValue.click();
					waitUntilFectchRecordProgressBarToDisappears();
					break;
				}
			}
		} catch (Exception ex) {
			throw new Exception("item has not selected" + ex);
		}
	}

	public void pillBoxValidation(String itemValue) throws Exception {
		try {

			List<WebElement> listOfDropdownValue = driver.findElements(By.xpath(
					"//section[@class='filterDetails']//mat-chip-list[@class='mat-chip-list']/div/mat-chip/div"));

			if (listOfDropdownValue.size() > 0)
				for (WebElement dropdownValue : listOfDropdownValue) {

					if (dropdownValue.isDisplayed()) {
						String pillValue = dropdownValue.getText();
						if (pillValue.contains("+") && pillValue.contains("more")) {
							controller.Logger.addsubStep(LogStatus.INFO,
									"Personal Folder checkbox is already deselected", true);
							break;
						}
					}

				}
		} catch (Exception ex) {
			throw new Exception("clickOnlink Filters is not working" + ex);
		}
	}

	public void clickOnButtonSelectAllForGivenFilter(String filterName) throws Exception {
		try {

			WebElement selectAll = driver.findElement(By.xpath("//div[contains(text(),'" + filterName
					+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//span[contains(text(),'Select all')]/parent::button"));
			controller.jsScrollToElement(selectAll);
			super.jsClick(selectAll);
		} catch (Exception ex) {
			throw new Exception("clickOn button select all is not working" + ex);
		}
	}

	public boolean isDisplayedCloseMarkX() throws Exception {
		try {
			boolean status = controller.isElementDisplayed(closeMarkX);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnCloseMarkX() throws Exception {
		try {

			if (isDisplayedCloseMarkX()) {
				super.jsClick(closeMarkX);
			}
		} catch (Exception ex) {
			throw new Exception("click on mark X is not working" + ex);
		}
	}

	public int getNoOfPillBoxes() throws Exception {
		try {
			waitUntilElementIsDisplayed(getResultsCount);
			List<WebElement> ele = driver
					.findElements(By.xpath("//div[@class='cdx-chip-block']//span[@class='cdx-chip-name']"));
			int noOfPillBoxes = ele.size();
			return noOfPillBoxes;
		} catch (Exception e) {
			throw new Exception("getListOfPillBoxes is not working.." + e);
		}
	}

	public boolean isDisplayedCloseMarkX(int indexNumber) throws Exception {
		try {
			boolean status = controller.isElementDisplayed(
					driver.findElement(By.xpath("(//mat-icon[contains(.,'close')])[" + (indexNumber + 3) + "]")));
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnCloseMarkX(String pillName) throws Exception {
		try {
			int j = 0;
			List<WebElement> listOfPills = driver.findElements(By.xpath(
					"//mat-chip[contains(@class,'mat-chip mat-primary mat-standard-chip mat-chip-with-trailing-icon ng-star-inserted')]//div[@class='cdx-chip-block']//span[@class='cdx-chip-name']"));
			for (int i = 0; i < listOfPills.size(); i++) {
				if (listOfPills.get(i).getText().equals(pillName)) {
					j = i + 1;
					break;
				}
			}
			super.jsClick(driver.findElement(By
					.xpath("(//mat-chip[@class='mat-chip mat-primary mat-standard-chip mat-chip-with-trailing-icon ng-star-inserted']//mat-icon[contains(text(),'close')])["
							+ j + "]")));
		} catch (Exception ex) {
			throw new Exception("click on mark X is not working" + ex);
		}
	}

	public String getFilteredPillName(int indexNumber) throws Exception {
		try {
			String pillName = driver.findElement(By
					.xpath("(//mat-chip[contains(@class,'mat-chip mat-primary mat-standard-chip mat-chip-with-trailing-icon ng-star-inserted')]//div[@class='cdx-chip-block']//span[@class='cdx-chip-name'])["
							+ indexNumber + "]"))
					.getText();
			return pillName;
		} catch (Exception e) {
			return "";
		}
	}

	public String getTagName() throws Exception {
		try {
			String tagName = tagName_Highlighted.getTagName();
			return tagName;
		} catch (Exception e) {
			return "";
		}
	}

	public String getTextTheHighlightedFilterList() throws Exception {
		try {
			String tagName = tagName_Highlighted.getText();
			return tagName;
		} catch (Exception e) {
			return "";
		}
	}

	public int getCountOfFilterListForGivenFilter(String filterName) throws Exception {
		try {
			List<WebElement> elements;
			elements = driver.findElements(By.xpath(
					"//div[contains(text(),'+filterName+')]//ancestor::mat-expansion-panel-header//following-sibling::div//mat-selection-list/mat-list-item"));
			return elements.size();
		} catch (Exception e) {
			throw new Exception("getCountOfHighlightedKeyword is not working.." + e);
		}
	}

	public void enterTextInSearchAreaForGivenFilter(String filterName, String searchText) throws Exception {
		try {
			WebElement searchArea = driver.findElement(By.xpath("//div[contains(text(),'" + filterName
					+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//input[@aria-label='Search']"));
			controller.jsScrollToElement(searchArea);
			super.jsClick(searchArea);
			setText(searchArea, searchText);
		} catch (Exception ex) {
			throw new Exception("text has not enetered in text area" + ex);
		}
	}

	public boolean isCheckboxAvailableForAllTheFilterItemForGivenFilter(String filterName) throws Exception {
		try {
			boolean Status = true;
			List<WebElement> listOfDropdownValue = driver.findElements(By.xpath(
					"//div[contains(text(),'+filterName+')]//ancestor::mat-expansion-panel-header//following-sibling::div//input[@type='checkbox']"));
			for (WebElement dropdownValue : listOfDropdownValue) {
				if (!dropdownValue.isDisplayed()) {
					return false;
				}
			}
			return Status;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isCheckboxCheckedForTheFilterItem(String filterValue) throws Exception {
		try {
			String eleForValue = driver.findElement(By.xpath(
					"//mat-expansion-panel-header//following-sibling::div//label[text()='" + filterValue + "'])[1]"))
					.getAttribute("for");
			if (controller.isElementSelected(driver.findElement(By.xpath("//input[@id='" + eleForValue + "']"))))
				;
			{
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isCheckboxAvailableAndUncheckedForAllTheFilterItemForGivenFilter(List<String> listOfFilterName)
			throws Exception {
		try {
			boolean Status = true;

			for (String filterName : listOfFilterName) {
				WebElement ele = driver.findElement(By.xpath("//span//div[contains(text(),'" + filterName + "')]"));
				super.jsScrollToElement(ele);

				WebElement filteredFields = driver.findElement(By.xpath(
						"//span/div[contains(text(),'" + filterName + "')]//ancestor::mat-expansion-panel-header"));
				String filterFieldsAttribute = controller.getElementAttribute(filteredFields, "aria-expanded");
				if (filterFieldsAttribute.contentEquals("true")) {
					List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'" + filterName
							+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//label"));
					for (WebElement filterValue : listOfFilters) {
						super.jsScrollToElement(filterValue);
						if (!(controller.isElementDisplayed(filterValue)
								&& !(controller.isElementSelected(filterValue)))) {
							return false;
						}
					}
				} else

				{
					super.click(ele);
					List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'" + filterName
							+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//label"));
					for (WebElement filterValue : listOfFilters) {
						super.jsScrollToElement(filterValue);
						if (!(controller.isElementDisplayed(filterValue)
								&& !(controller.isElementSelected(filterValue)))) {
							return false;
						}
					}
				}

			}
			return Status;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isCheckboxSelectedForAnyFilterItemForGivenFilter(String filterName) throws Exception {
		try {
			boolean Status = true;
			List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'" + filterName
					+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//label//preceding-sibling::input"));
			for (WebElement filterValue : listOfFilters) {
				controller.jsScrollToElement(filterValue);
				if (!controller.isElementSelected(filterValue)) {
					return false;
				}
			}
			return Status;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isDisabledButtonApplyFilters() throws Exception {
		try {

			boolean Status = true;
			String filterAttribute;
			super.jsScrollToElement(button_ApplyFilter);
			filterAttribute = controller.getElementAttribute(button_ApplyFilter, "disabled");
			if (!filterAttribute.contentEquals("true")) {
				return false;
			}
			return Status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isEnabledButtonApplyFilters() throws Exception {
		try {
			return controller.isElementEnabled(button_ApplyFilter);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedButtonDeselectAllForGivenFilter(String filterName) throws Exception {
		try {

			WebElement deselectAll = driver.findElement(By.xpath("//div[contains(text(),'" + filterName
					+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//span[contains(text(),'Deselect all')]/parent::button"));
			controller.jsScrollToElement(deselectAll);
			return controller.isElementDisplayed(deselectAll);

		} catch (Exception ex) {
			throw new Exception("deselect all button is not displayed" + ex);
		}
	}

	public void clickOnButtonDeselectAllForGivenFilter(String filterName) throws Exception {
		try {

			WebElement deselectAll = driver.findElement(By.xpath("//div[contains(text(),'" + filterName
					+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//span[contains(text(),'Deselect all')]/parent::button"));
			controller.jsScrollToElement(deselectAll);
			super.jsClick(deselectAll);
		} catch (Exception ex) {
			throw new Exception("click on button deselect all is not working" + ex);
		}
	}

	public boolean isCheckboxDeselectedForAllTheFilterItemForGivenFilter(String filterName) throws Exception {
		try {
			boolean Status = true;
			List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'" + filterName
					+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//label//preceding-sibling::input"));
			for (WebElement filterValue : listOfFilters) {
				controller.jsScrollToElement(filterValue);
				if (controller.isElementSelected(filterValue)) {
					return false;
				}
			}
			return Status;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isDisplayedAlltheFilterListForGivenFilter(String filterName) throws Exception {
		try {
			boolean Status = true;
			List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'" + filterName
					+ "')]//ancestor::mat-expansion-panel-header//following-sibling::div//label"));
			for (WebElement filterValue : listOfFilters) {
				super.jsScrollToElement(filterValue);
				if (!controller.isElementDisplayed(filterValue)) {
					return false;
				}
			}

			return Status;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isDisplayedPillFilterBox() throws Exception {
		try {
			return controller.isElementDisplayed(box_PillFilter);
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedCrossXForAllThePills() throws Exception {
		try {
			boolean Status = true;
			List<WebElement> listOfPillFilteredValue = driver.findElements(
					By.xpath("//section[contains(@class,'filterDetails')]//mat-icon[contains(text(),'close')]"));

			if (listOfPillFilteredValue.size() > 0)
				for (WebElement filteredValue : listOfPillFilteredValue) {
					if (!filteredValue.isDisplayed()) {
						return false;
					}

				}
			return Status;

		} catch (Exception ex) {
			throw new Exception("DisplayedCrossXForAllThePills is not working" + ex);
		}
	}

	public boolean isPillFilterBoxCollideWithSearchResultString() throws Exception {
		try {
			boolean Status = false;
			List<WebElement> listOfPillFilteredValue = driver.findElements(By.xpath(
					"//section[@class='filterDetails']//mat-chip-list[@class='mat-chip-list']/div/mat-chip/div"));

			if (listOfPillFilteredValue.size() > 0)
				for (WebElement filteredValue : listOfPillFilteredValue) {

					if (filteredValue.isDisplayed()) {
						String pillValue = filteredValue.getText();
						if (pillValue.contains("+") && pillValue.contains("More")) {

							Status = true;
						}
					}

				}

			return Status;
		} catch (Exception ex) {
			throw new Exception("PillFilterBoxCollideWithSearchResultString is not working" + ex);
		}
	}

	public boolean isHeaderAndTextDisplayedForDifferentPillsExceptTheSearchedString() throws Exception {
		try {
			boolean Status = true;
			List<WebElement> listOfPillFilteredValue = driver.findElements(By.xpath(
					"//section[@class='filterDetails']//mat-chip-list[@class='mat-chip-list']/div/mat-chip/div"));

			if (listOfPillFilteredValue.size() > 0)
				for (WebElement filteredValue : listOfPillFilteredValue) {
					String pillValue = filteredValue.getText();
					if (pillValue.contains("+") && pillValue.contains("More")) {
						controller.jsScrollToElement(filteredValue);
						controller.hoverOnWebelement(filteredValue);
						List<WebElement> pillFilteredList = driver.findElements(By.xpath(
								"//section[@class='filterDetails']//mat-chip-list[@class='mat-chip-list']/div/mat-chip/div/span"));
						for (int i = 0; i < pillFilteredList.size(); i = i + 2)

						{
							controller.jsScrollToElement(pillFilteredList.get(i));
							String headerValue = pillFilteredList.get(i).getText();
							String textValue = pillFilteredList.get(i + 1).getText();
							if (!pillValue.contains(headerValue) && !pillValue.contains(textValue)) {
								if (headerValue.isEmpty() && textValue.isEmpty())

								{
									return false;
								}
							}

						}
					}

				}
			return Status;

		} catch (Exception ex) {
			throw new Exception("clickOnlink Filters is not working" + ex);
		}
	}

	public boolean isDisplayedPublicationNumber() throws Exception {
		try {
			boolean status = controller.isElementDisplayed(publicationNumber);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedRecordTitle() throws Exception {
		try {
			boolean status = controller.isElementDisplayed(recordTitle);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedTextPublished() throws Exception {
		try {
			boolean status = controller.isElementDisplayed(text_Published);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedTextStatus() throws Exception {
		try {
			boolean status = controller.isElementDisplayed(text_Status);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public String getStatusValue() throws Exception {
		try {
			return controller.getText(value_Status);

		} catch (Exception e) {
			return "";
		}
	}

	public boolean isDisplayedFieldBibliography() throws Exception {
		try {
			controller.jsScrollToElement(section_Bibliography);
			boolean status = controller.isElementDisplayed(section_Bibliography);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSectionBibliographyExpandedAndCollapsed() throws Exception {
		try {

			boolean Status = true;

			super.jsScrollToElement(button_ApplyFilter);
			String bibliographyAttributeExpanded = controller.getElementAttribute(field_Bibliography, "aria-expanded");
			if (!bibliographyAttributeExpanded.contentEquals("true")) {
				return false;
			}

			else {
				controller.jsClick(field_Bibliography);
				String bibliographyAttributeCollapsed = controller.getElementAttribute(field_Bibliography,
						"aria-expanded");
				if (!bibliographyAttributeCollapsed.contentEquals("false")) {
					return false;
				}

			}

			controller.jsClick(field_Bibliography);
			return Status;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldAssignee() throws Exception {
		try {
			controller.jsScrollToElement(field_Assignee);
			boolean status = controller.isElementDisplayed(field_Assignee);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldInventor() throws Exception {
		try {
			controller.jsScrollToElement(field_Inventor);
			boolean status = controller.isElementDisplayed(field_Inventor);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldPriority() throws Exception {
		try {
			controller.jsScrollToElement(field_Priority);
			boolean status = controller.isElementDisplayed(field_Priority);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public List<String> getBibliographyPriorityDetails() throws Exception {
		try {
			List<WebElement> listOfPriority = driver
					.findElements(By.xpath("//div/h2[contains(text(),'Priority')]/following-sibling::div"));
			return controller.getListWebElementText(listOfPriority);
		} catch (Exception ex) {
			return null;
		}
	}

	public boolean isDisplayedFieldApplication() throws Exception {
		try {
			controller.jsScrollToElement(field_Application);
			boolean status = controller.isElementDisplayed(field_Application);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public List<String> getBibliographyApplicationDetails() throws Exception {
		try {
			List<WebElement> listOfApplication = driver
					.findElements(By.xpath("//div/h2[contains(text(),'Application')]/following-sibling::div"));
			return controller.getListWebElementText(listOfApplication);
		} catch (Exception ex) {
			return null;
		}
	}

	public boolean isDisplayedSectionImage() throws Exception {
		try {
			controller.jsScrollToElement(section_Image);
			boolean status = controller.isElementDisplayed(section_Image);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSectionAbstractExpandedAndCollapsed() throws Exception {
		try {

			boolean Status = true;

			super.jsScrollToElement(field_Abstract);
			String bibliographyAttributeExpanded = controller.getElementAttribute(field_Abstract, "aria-expanded");
			if (!bibliographyAttributeExpanded.contentEquals("true")) {
				return false;
			}

			else {
				controller.jsClick(field_Abstract);
				String bibliographyAttributeCollapsed = controller.getElementAttribute(field_Abstract, "aria-expanded");
				if (!bibliographyAttributeCollapsed.contentEquals("false")) {
					return false;
				}

			}

			controller.jsClick(field_Abstract);
			return Status;

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldNovelty() throws Exception {
		try {
			controller.jsScrollToElement(field_Novelty);
			boolean status = controller.isElementDisplayed(field_Novelty);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldDetailedDesc() throws Exception {
		try {
			controller.jsScrollToElement(field_DetailedDesc);
			boolean status = controller.isElementDisplayed(field_DetailedDesc);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldUse() throws Exception {
		try {
			controller.jsScrollToElement(field_Use);
			boolean status = controller.isElementDisplayed(field_Use);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldAdvantage() throws Exception {
		try {
			controller.jsScrollToElement(field_Advantage);
			boolean status = controller.isElementDisplayed(field_Advantage);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldTechnologyFocus() throws Exception {
		try {
			controller.jsScrollToElement(field_TechFocus);
			boolean status = controller.isElementDisplayed(field_TechFocus);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldTechnologyFocusDefinitions() throws Exception {
		try {
			controller.jsScrollToElement(field_TechFocusDefinitions);
			boolean status = controller.isElementDisplayed(field_TechFocusDefinitions);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldTechnologyFocusExamples() throws Exception {
		try {
			controller.jsScrollToElement(field_TechFocusExamples);
			boolean status = controller.isElementDisplayed(field_TechFocusExamples);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldClaims() throws Exception {
		try {
			controller.jsScrollToElement(field_Claims);
			boolean status = controller.isElementDisplayed(field_Claims);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldDWPIFamily() throws Exception {
		try {
			controller.jsScrollToElement(field_DWPIFamily);
			boolean status = controller.isElementDisplayed(field_DWPIFamily);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDisplayedFieldDescription() throws Exception {
		try {
			controller.jsScrollToElement(field_Desciption);
			boolean status = controller.isElementDisplayed(field_Desciption);
			return status;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnButtonInsights() throws Exception {
		try {
			controller.waitTime(2);
			if (!btn_TabInsights.getAttribute("ng-reflect-fx-flex").toLowerCase().contains("385px")) {
				controller.jsClick(btn_Insights);

			} else {
				controller.Logger.addsubStep(LogStatus.INFO, "TAB INSIGHTS IS SELECTED ALREADY", false);
			}
		} catch (Exception ex) {
			throw new Exception("click on Button Tab Insights is not working for :: " + ex);
		}
	}

	public String getTextPatentInsightsKeyword() throws Exception {
		String keyword;
		waitUntilElementIsDisplayed(patnetInsightsKeyword);
		keyword = getText(patnetInsightsKeyword);
		return (keyword);
	}

	public void clickOnThumbsUpIcon() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumsUpIcon);
			jsClick(thumsUpIcon);
			iconStatus = getElementAttribute(thumsUpIcon, "ng-reflect-svg-icon");
			if (iconStatus.contains("thumbup-filled")) {
				controller.Logger.addsubStep(LogStatus.PASS, "THUMBS UP ICON CHANGED TO SOLID GREEN COLOR", false);
			} else {
				controller.Logger.addsubStep(LogStatus.FAIL, "THUMBS UP ICON DIDNT CHANGED TO SOLID GREEN COLOR",
						false);
			}

		} catch (Exception ex) {
			throw new Exception("clickOnThumbsUpIcon is not working" + ex);
		}
	}

	public String checkThumbsUpHollowState() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumsUpIcon);
			iconStatus = getElementAttribute(thumsUpIcon, "ng-reflect-svg-icon");
			return (iconStatus);
		} catch (Exception ex) {
			throw new Exception("clickOnThumbsUpIcon is not working" + ex);
		}
	}

	public String checkThumbsDownHollowState() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumbsDownIcon);
			iconStatus = getElementAttribute(thumbsDownIcon, "ng-reflect-svg-icon");
			return (iconStatus);
		} catch (Exception ex) {
			throw new Exception("checkThumbsDownHollowState is not working" + ex);
		}
	}

	public String checkGlobalCheckboxState() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(globalCheckBox);
			iconStatus = getElementAttribute(globalCheckBox, "aria-checked");
			return (iconStatus);
		} catch (Exception ex) {
			throw new Exception("checkGlobalCheckboxState is not working" + ex);
		}
	}

	public void clickOnSingleRecordCheckBox() throws Exception {
		try {
			waitUntilElementIsDisplayed(singleRecordCheckBox);
			String status = controller.getElementAttribute(singleRecordCheckBox, "aria-checked");
			if (status.equals("false")) {
				jsClick(singleRecordCheckBox);

			} else {
				controller.Logger.Logger.log(LogStatus.INFO, "RS Footer Global Check Box is already selected");
			}
		} catch (Exception ex) {
			throw new Exception("clickOnSingleRecordCheckBox is not working" + ex);
		}
	}

	public void clickOnThumbsDownIcon() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumbsDownIcon);
			jsClick(thumbsDownIcon);
			iconStatus = getElementAttribute(thumbsDownIcon, "ng-reflect-svg-icon");
			if (iconStatus.contains("thumbdown-filled")) {
				controller.Logger.addsubStep(LogStatus.PASS, "THUMBS DOWN ICON CHANGED TO SOLID GREEN COLOR", false);
			} else {
				controller.Logger.addsubStep(LogStatus.FAIL, "THUMBS DOWN ICON DIDNT CHANGED TO SOLID GREEN COLOR",
						false);
			}

		} catch (Exception ex) {
			throw new Exception("clickOnThumbsDownIcon is not working" + ex);
		}
	}

	public boolean isDisplayedPatentThumbsUpIcon() throws Exception {
		int j = 1;
		boolean status = true;
		try {
			List<WebElement> thumbsUpIcons = driver
					.findElements(By.xpath("//section/aside/section[1]/div/span[2]/button"));
			System.out.println("size" + thumbsUpIcons.size());
			if (thumbsUpIcons.size() > 0) {
				for (WebElement ele : thumbsUpIcons) {
					if (controller.isElementDisplayed(
							driver.findElement(By.xpath("//section/aside/section[1]/div/span[2]/button")))) {
						controller.Logger.addsubStep(LogStatus.PASS,
								"THUMBS UP ICON IS DISPLAYED FOR THE RECORD:" + (j) + "", false);
						j = j + 1;
					} else {
						controller.Logger.addsubStep(LogStatus.FAIL,
								"THUMBS UP ICON IS NOT DISPLAYED FOR THE RECORD:" + (j) + "", true);
						status = false;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("isDisplayedPatentThumbsUpIcon is not working" + e);
		}
		return status;
	}

	public boolean isDisplayedPatentThumbsDownIcon() throws Exception {
		int j = 1;
		boolean status = true;
		try {
			List<WebElement> thumbsDownIcons = driver
					.findElements(By.xpath("//section/aside/section[1]/div/span[3]/button"));
			System.out.println("size" + thumbsDownIcons.size());
			if (thumbsDownIcons.size() > 0) {
				for (WebElement ele : thumbsDownIcons) {
					if (controller.isElementDisplayed(
							driver.findElement(By.xpath("//section/aside/section[1]/div/span[3]/button")))) {
						controller.Logger.addsubStep(LogStatus.PASS,
								"THUMBS DOWN ICON IS DISPLAYED FOR THE RECORD:" + (j) + "", false);
						j = j + 1;
					} else {
						controller.Logger.addsubStep(LogStatus.FAIL,
								"THUMBS DOWN ICON IS NOT DISPLAYED FOR THE RECORD:" + (j) + "", true);
						status = false;
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("isDisplayedLiteratureThumbsDownIcon is not working" + e);
		}
		return status;
	}

	public void clickOnButtonThumsUp(int recordNumber) throws Exception {
		try {
			WebElement listOfThumsUp = driver.findElement(By.cssSelector("section > app-result-set:nth-child("
					+ recordNumber
					+ ") > section > aside > section:nth-child(3) > div > span:nth-child(2) > button > span > mat-icon"));
			controller.jsClick(listOfThumsUp);
			controller.Logger.addsubStep(LogStatus.INFO, "Clicked On Thumbs Up Button Successfully", false);
		} catch (Exception e) {
			throw new Exception("clickOnButtonThumsUp is not working.." + e);
		}
	}

	public boolean isDisplayedButtonThumsUpWithFillColor(int recordNumber) throws Exception {
		try {
			WebElement listOfThumsUp = driver.findElement(By.cssSelector("section > app-result-set:nth-child("
					+ recordNumber
					+ ") > section > aside > section:nth-child(3) > div > span:nth-child(2) > button > span > mat-icon"));
			if (controller.getElementAttribute(listOfThumsUp, "ng-reflect-svg-icon").contains("filled")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

	}

	public boolean isDisplayedButtonThumsDownWithFillColor(int recordNumber) throws Exception {
		try {
			WebElement listOfThumsDown = driver.findElement(By.cssSelector("section > app-result-set:nth-child("
					+ recordNumber
					+ ") > section > aside > section:nth-child(3) > div > span:nth-child(3) > button > span > mat-icon"));
			if (controller.getElementAttribute(listOfThumsDown, "ng-reflect-svg-icon").contains("filled")) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			throw new Exception("isDisplayedButtonThumsDownWithFillColor is not working.." + e);
		}
	}

	public void clickOnButtonThumsDown(int recordNumber) throws Exception {
		try {
			WebElement listOfThumsDown = driver.findElement(By.cssSelector("section > app-result-set:nth-child("
					+ recordNumber
					+ ") > section > aside > section:nth-child(3) > div > span:nth-child(3) > button > span > mat-icon"));
			controller.jsClick(listOfThumsDown);
			controller.Logger.addsubStep(LogStatus.INFO, "Clicked On Thumbs Down Button Successfully", false);
		} catch (Exception e) {
			throw new Exception("clickOnButtonThumsDown is not working.." + e);
		}
	}

	public boolean isDisabledFilterField() throws Exception {
		try {
			super.jsScrollToElement(filterFieldDisable);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnInventorsTab() throws Exception {
		try {
			waitUntilElementIsDisplayed(tabInventors);
			jsClick(tabInventors);
		} catch (Exception ex) {
			throw new Exception("clickOnInventorsTab is not working" + ex);
		}
	}

	public void clickOnInventorsBar() throws Exception {
		try {
			waitUntilElementIsDisplayed(barInventors);
			Actions builder = new Actions(driver);
			builder.click(barInventors).build().perform();
		} catch (Exception ex) {
			throw new Exception("lclickOnInventorsBar  is not working" + ex);
		}
	}

	public void clickOnInventorsText() throws Exception {
		try {
			waitUntilElementIsDisplayed(textInventors);
			Actions builder = new Actions(driver);
			builder.click(textInventors).build().perform();
		} catch (Exception ex) {
			throw new Exception("clickOnInventorsText  is not working" + ex);
		}
	}

	public boolean isDisplayedPublicationYearChartExpanded() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(linkPublicationYear);
			String chart = controller.getElementAttribute(linkPublicationYear, "style");
			if (chart.contains("180deg")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception("isDisplayedPatentRecordCloseIcon is not working" + e);
		}
	}

	public String getTextPublicationChartLabelName() throws Exception {
		waitUntilElementIsDisplayed(publicationYearLabel);
		String label = controller.getText(publicationYearLabel);
		return (label);
	}

	public void selectMultiplePublicationChartFilters() throws Exception {
		try {
			driver.findElement(By.cssSelector("#publ_year_rect_2007")).click();
			controller.waitUntilFectchRecordProgressBarToDisappears();
			controller.waitTime(2);
			driver.findElement(By.cssSelector("#publ_year_rect_2010")).click();
			controller.waitUntilFectchRecordProgressBarToDisappears();
			controller.waitTime(3);
		} catch (Exception e) {
			throw new Exception("selectMultiplePublicationChartFilters is not working.." + e);
		}
	}

	public void deSelectMultiplePublicationChartFilters() throws Exception {
		try {
			driver.findElement(By.cssSelector("#publ_year_rect_2007")).click();
			controller.waitUntilFectchRecordProgressBarToDisappears();
			controller.waitTime(2);
			driver.findElement(By.cssSelector("#publ_year_rect_2010")).click();
			controller.waitUntilFectchRecordProgressBarToDisappears();
			controller.waitTime(3);
		} catch (Exception e) {
			throw new Exception("deSelectMultiplePublicationChartFilters is not working.." + e);
		}
	}

	public void clickOnPYChartExpandCollapseIcon() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(linkPublicationYear);
			linkPublicationYear.click();
		} catch (Exception e) {
			throw new Exception("clickOnPYChartExpandCollapseIcon is not working" + e);
		}
	}

	public void clickOnPYChartLeftArrow() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(publicationYearLeftArrow);
			String color = controller.getElementAttribute(publicationYearLeftArrow, "style");
			if (color.contains("black"))
				publicationYearLeftArrow.click();
		} catch (Exception e) {
			throw new Exception("clickOnPYChartLeftArrow is not working" + e);
		}
	}

	public boolean isEnabledPYChartRightArrow() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(publicationYearRightArrow);
			String color = controller.getElementAttribute(publicationYearRightArrow, "style");
			if (color.contains("black")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception("isDisabledPYChartRightArrow is not working" + e);
		}
	}

	public boolean isEnabledPYCharLeftArrow() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(publicationYearLeftArrow);
			String color = controller.getElementAttribute(publicationYearLeftArrow, "style");
			if (color.contains("black")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception("isEnabledPYCharLeftArrow is not working" + e);
		}
	}

	public boolean isDisabledPYChartRightArrow() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(publicationYearRightArrow);
			String color = publicationYearRightArrow.getText();
			if (color.contains("chevron_right")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new Exception("isDisabledPYChartRightArrow is not working" + e);
		}
	}

	public void clickOnPublicationYearFilter() throws Exception {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(publicationYearFilter).click().perform();
		} catch (Exception ex) {
			throw new Exception("clickOnPublicationYearFilter is not working" + ex);
		}
	}

	public boolean isDisabledPublicationYearFilter() throws Exception {
		try {
			String checkStatus;
			controller.waitUntilElementIsDisplayed(publicationYearFilter);
			checkStatus = controller.getElementAttribute(publicationYearFilter, "aria-expanded");
			if (checkStatus.equals("false"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isDisabledPublicationYearFilter is not working" + e);
		}
	}

	public void clickOnPublicationYearChart() throws Exception {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(publicationYearFilterDisable).click().perform();
		} catch (Exception ex) {
			throw new Exception("clickOnPublicationYearChart is not working" + ex);
		}
	}

	public boolean isDisabledPublicationYearChart() throws Exception {
		try {
			String checkStatus;
			controller.waitUntilElementIsDisplayed(publicationYearFilterDisable);
			checkStatus = controller.getElementAttribute(publicationYearFilterDisable, "style");
			if (checkStatus.contains("cursor: not-allowed"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isDisabledPublicationYearChart is not working" + e);
		}
	}

	public void clickOnLinkAssignee() throws Exception {
		try {
			waitUntilElementIsDisplayed(linkAssignee);
			jsClick(linkAssignee);
		} catch (Exception ex) {
			throw new Exception("clickOnLinkAssignee is not working" + ex);
		}
	}

	public String getTextKeyWordMoreLike() throws Exception {
		waitUntilElementIsDisplayed(moreLikeKeyword);
		String morelikekeyword = controller.getElementAttribute(moreLikeKeyword, "title");
		return (morelikekeyword);
	}

	public String getTextAssignee(int rownumber) throws Exception {
		try {
			WebElement assignee = driver.findElement(By.cssSelector("section > app-result-set:nth-child(" + rownumber
					+ ") > section > mat-card > mat-card-content > div:nth-child(1) > section.assignee.ng-star-inserted > p > span > span > a"));
			return assignee.getText();
		} catch (Exception ex) {
			throw new Exception("getTextAssignee is not working" + ex);
		}
	}

	public void clickOnLinkInventor() throws Exception {
		try {
			waitUntilElementIsDisplayed(linkInventor);
			jsClick(linkInventor);
		} catch (Exception ex) {
			throw new Exception("clickOnLinkInventor is not working" + ex);
		}
	}

	public String getTextInventor(int rownumber) throws Exception {
		try {
			WebElement inventor = driver.findElement(By.cssSelector("section > app-result-set:nth-child(" + rownumber
					+ ") > section > mat-card > mat-card-content > div:nth-child(1) > section.inventor.ng-star-inserted > p > span > span:nth-child(1) > a"));
			return inventor.getText();
		} catch (Exception ex) {
			throw new Exception("getTextInventor is not working" + ex);
		}
	}

	public void clickOnLinkMoreLikeThis() throws Exception {
		try {
			waitUntilElementIsDisplayed(linkMoreLikeThis);
			jsClick(linkMoreLikeThis);
		} catch (Exception ex) {
			throw new Exception("clickOnLinkMoreLikeThis is not working" + ex);
		}
	}

	public String getTextPublicationNumber() throws Exception {
		waitUntilElementIsDisplayed(rsPublicationNumber);
		String publicationnumber = controller.getText(rsPublicationNumber);
		return (publicationnumber);
	}

	public void clickOnLinkPatentRecordViewInventor() throws Exception {
		try {
			waitUntilElementIsDisplayed(patentRecordViewInventor);
			jsClick(patentRecordViewInventor);
		} catch (Exception ex) {
			throw new Exception("clickOnLinkPatentRecordViewInventor is not working" + ex);
		}
	}

	public void clickOnLinkPatentRecordViewAssignee() throws Exception {
		try {
			waitUntilElementIsDisplayed(patentRecordViewAssignee);
			jsClick(patentRecordViewAssignee);
		} catch (Exception ex) {
			throw new Exception("clickOnLinkPatentRecordViewAssignee is not working" + ex);
		}
	}

	public boolean isDisabledKeyWordSection() throws Exception {
		try {
			String disable;
			waitUntilElementIsDisplayed(keywordDisable);
			disable = controller.getElementAttribute(keywordDisable, "class");
			if (disable.contains("view disable-click"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isDisabledKeyWordSection is not working" + e);
		}
	}

	public void clickOnCloseIconMoreLike() throws Exception {
		try {
			waitUntilElementIsDisplayed(moreLikeCloseIcon);
			jsClick(moreLikeCloseIcon);
		} catch (Exception ex) {
			throw new Exception("clickOnCloseIconMoreLike is not working" + ex);
		}
	}

	public boolean isDisabledAddButton() throws Exception {
		try {
			super.jsScrollToElement(button_Add);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isExpandedDwpiTable() throws Exception {
		try {
			String collapse;
			waitUntilElementIsDisplayed(dwpiPatentTableAttribute);
			collapse = controller.getElementAttribute(dwpiPatentTableAttribute, "aria-expanded");
			if (collapse.equals("true"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isExpandedDwpiTable is not working" + e);
		}
	}

	public void clickOnLinkDwpiTable() throws Exception {
		try {
			waitUntilElementIsDisplayed(dwpiTableLink);
			jsClick(dwpiTableLink);
		} catch (Exception ex) {
			throw new Exception("clickOnLinkDwpiTable is not working" + ex);
		}
	}

	public boolean isCollapsedDwpiTable() throws Exception {
		try {
			String collapse;
			waitUntilElementIsDisplayed(dwpiPatentTableAttribute);
			collapse = controller.getElementAttribute(dwpiPatentTableAttribute, "aria-expanded");
			if (collapse.equals("false"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isCollapsedDwpiTable is not working" + e);
		}
	}

	public String getDwpiPublicationNumber() throws Exception {
		waitUntilElementIsDisplayed(dwpiPublicationNumber);
		return controller.getText(dwpiPublicationNumber);
	}

	public String getPublicationNumberRecordView() throws Exception {
		waitUntilElementIsDisplayed(recordViewDwpiPublicationNumber);
		return controller.getText(recordViewDwpiPublicationNumber);
	}

	public void clickOnDwpiPublicationNumber() throws Exception {
		try {
			waitUntilElementIsDisplayed(dwpiPublicationNumber);
			jsClick(dwpiPublicationNumber);
		} catch (Exception ex) {
			throw new Exception("clickOnDwpiPublicationNumber is not working" + ex);
		}
	}

	public boolean isCollapsedCitingPatent() throws Exception {
		try {
			String collapse;
			waitUntilElementIsDisplayed(citingPatentLinkAttribute);
			collapse = controller.getElementAttribute(citingPatentLinkAttribute, "aria-expanded");
			if (collapse.equals("false"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isCollapsedCitingPatent is not working" + e);
		}
	}

	public boolean isCollapsedCitedPatent() throws Exception {
		try {
			String collapse;
			waitUntilElementIsDisplayed(citedPatentLinkAttribute);
			collapse = controller.getElementAttribute(citedPatentLinkAttribute, "aria-expanded");
			if (collapse.equals("false"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isCollapsedCitedPatent is not working" + e);
		}
	}

	public void clickOnLinkCitingPatent() throws Exception {
		try {
			int count;
			waitUntilElementIsDisplayed(citingPatentCount);
			String totaltxt = getText(citingPatentCount).trim();
			totaltxt = totaltxt.replace("(", "").replaceAll("[\\D]", "");
			totaltxt = totaltxt.replace(")", "").replaceAll("[\\D]", "");
			count = Integer.parseInt(totaltxt);
			if (count == 0) {
				controller.Logger.Logger.log(LogStatus.INFO, "CITING LITERATURE SECTION IS IN DISABLED STATE");
			} else {
				jsClick(citingPatentLink);
			}
		} catch (Exception ex) {
			throw new Exception("clickOnLinkCitingPatent is not working" + ex);
		}
	}

	public boolean isExpandedCitingPatent() throws Exception {
		try {
			String collapse;
			waitUntilElementIsDisplayed(citingPatentLinkAttribute);
			collapse = controller.getElementAttribute(citingPatentLinkAttribute, "aria-expanded");
			if (collapse.equals("true"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isExpandedCitingPatent is not working" + e);
		}
	}

	public void clickOnLinkCitedPatent() throws Exception {
		try {
			int count;
			waitUntilElementIsDisplayed(citedPatentCount);
			String totaltxt = getText(citedPatentCount).trim();
			totaltxt = totaltxt.replace("(", "").replaceAll("[\\D]", "");
			totaltxt = totaltxt.replace(")", "").replaceAll("[\\D]", "");
			count = Integer.parseInt(totaltxt);
			if (count == 0) {
				controller.Logger.Logger.log(LogStatus.INFO, "CITING PATENT SECTION IS IN DISABLED STATE");
			} else {
				jsClick(citedPatentLink);
			}
		} catch (Exception ex) {
			throw new Exception("clickOnLinkCitedPatent is not working" + ex);
		}
	}

	public boolean isExpandedCitedPatent() throws Exception {
		try {
			String collapse;
			waitUntilElementIsDisplayed(citedPatentLinkAttribute);
			collapse = controller.getElementAttribute(citedPatentLinkAttribute, "aria-expanded");
			if (collapse.equals("true"))
				return true;
			else
				return false;
		} catch (Exception e) {
			throw new Exception("isExpandedCitedPatent is not working" + e);
		}
	}

	public void clickOnCitingPatentTitle() throws Exception {
		try {
			waitUntilElementIsDisplayed(citingTitle);
			jsClick(citingTitle);
		} catch (Exception ex) {
			throw new Exception("clickOnCitingPatentTitle is not working" + ex);
		}
	}

	public void clickOnCitedPatentTitle() throws Exception {
		try {
			waitUntilElementIsDisplayed(citedTitle);
			jsClick(citedTitle);
		} catch (Exception ex) {
			throw new Exception("clickOnCitedPatentTitle is not working" + ex);
		}
	}

	public String getTextCitingTitle() throws Exception {
		waitUntilElementIsDisplayed(citingTitle);
		return controller.getText(citingTitle);
	}

	public String getTextCitedTitle() throws Exception {
		waitUntilElementIsDisplayed(citedTitle);
		return controller.getText(citedTitle);
	}

	public String getTextPatentRecordViewTitle() throws Exception {
		waitUntilElementIsDisplayed(patRecordViewTitle);
		return controller.getText(patRecordViewTitle);
	}

	public void clickCloseOnFilterFields() throws Exception {
		try {
			List<WebElement> closeButtons = driver.findElements(By.xpath(
					"//button[@title='Remove filter field']//span[@class='mat-button-wrapper'][contains(.,'close')]"));
			for (int i = 1; i <= closeButtons.size(); i++) {
				waitTime(1);
				WebElement closeFirstButton = waitUntilElementIsAvailable(By.xpath(
						"//button[@title='Remove filter field']//span[@class='mat-button-wrapper'][contains(.,'close')][1]"));
				waitTime(1);
				jsScrollToElement(closeFirstButton);
				waitTime(1);
				controller.jsClick(closeFirstButton);
				controller.waitTime(2);
				controller.waitTime(2);
			}
		} catch (Exception e) {
			throw new Exception("clickCloseOnFilterFields is not working" + e);
		}
	}

	public LinkedHashSet<String> getFilterFields() throws Exception {
		List<WebElement> firstlevel = dropdownFields;
		LinkedHashSet<String> actualfields = new LinkedHashSet<String>();
		Actions mousehover = new Actions(driver);
		for (WebElement menu : firstlevel) {
			mousehover.moveToElement(menu).build().perform();
			String mainField = menu.getText();
			System.out.println(mainField);
			actualfields.add(mainField.trim());
		}
		return actualfields;
	}

	public boolean verifyFilterFieldsInFilterSection(List<String> filterlist) throws Exception {
		for (String filter : filterlist) {
			String filterxpath = " //section[@class='filter-section ng-star-inserted']//div[contains(text(),'" + filter
					+ "')]";
			if (!controller.isElementDisplayed(controller.driver.findElement(By.xpath(filterxpath)))) {
				return false;
			}
		}
		for (int i = 0; i < filterlist.size(); i++) {
			String filter = filterlist.get(i);
		}
		return true;
	}

	public void addAllDropdownFields(List<String> filterlist) throws Exception {
		try {
			List<WebElement> firstlevel = dropdownFields;
			firstlevel.get(0).click();
			String dropdownfilter = "//div[@class='mat-select-arrow']";
			for (String filter : filterlist) {
				for (int j = 0; j < 3; j++) {
					try {
						WebElement dropdown = controller.driver.findElement(By.xpath(dropdownfilter));
						controller.waitUntilElementIsDisplayed(dropdown);
						controller.waitUntilElementClickable(dropdown);
						controller.jsScrollToElement(dropdown);
						dropdown.click();
						break;
					} catch (Exception e) {
					}
				}
				int index = filter.indexOf("(");
				if (index >= 0) {
					filter = filter.substring(0, index - 1);
				}
				filter = filter.replace(".", "");
				String optionxPath = "//mat-option[contains(@ng-reflect-value," + "'" + filter + "')]";
				System.out.println(optionxPath);
				for (int j = 0; j < 3; j++) {
					try {
						WebElement filteroption = controller.driver.findElement(By.xpath(optionxPath));
						controller.waitUntilElementClickable(filteroption);
						filteroption.click();
						break;
					} catch (Exception e) {
					}
				}
				String buttonxpath = "//section[contains(@class, 'add')]//button[contains(@class, 'add-button')]";
				for (int j = 0; j < 3; j++) {
					try {
						WebElement addbutton = controller.driver.findElement(By.xpath(buttonxpath));
						controller.waitUntilElementIsDisplayed(addbutton);
						addbutton.click();
						break;
					} catch (Exception e) {
					}
				}
			}
		} catch (Exception e) {
			throw new Exception("addAllDropdownFields is not working" + e);
		}
	}

	public void clickOnMoreFiltersDropdown() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(dropdownMoreFilters);
			dropdownMoreFilters.click();
		} catch (Exception e) {
			throw new Exception("clickOnMoreFiltersDropdown is not working" + e);
		}
	}

	public void clickOnHalfFilledGlobalCheckBox() throws Exception {
		try {
			waitUntilElementIsDisplayed(globalCheckBox);
			String status = controller.getElementAttribute(globalCheckBox, "aria-checked");
			if (status.equalsIgnoreCase("mixed")) {
				jsClick(globalCheckBox);

			} else {
				controller.Logger.Logger.log(LogStatus.INFO, "RS Footer Global Check Box is already selected");
			}
		} catch (Exception ex) {
			throw new Exception("clickOnHalfFilledGlobalCheckBox is not working" + ex);
		}
	}

	public String getTextRecordSelection() throws Exception {
		try {
			controller.waitTime(2);
			return controller.getText(recordSelection);

		} catch (Exception ex) {
			throw new Exception("No Results Page error msg has not displayed" + ex);
		}
	}

	public String getTextKeyWordResultSet() throws Exception {
		waitUntilElementIsDisplayed(patentViewAsResultSet);
		String resultset = controller.getText(patentViewAsResultSet);
		return (resultset);
	}

	public void clickOnCitedPatentViewASResultSet() throws Exception {
		try {
			waitUntilElementIsDisplayed(citedPatentViewAsResultSet);
			jsClick(citedPatentViewAsResultSet);
		} catch (Exception ex) {
			throw new Exception("clickOnCitedPatentViewASResultSet is not working" + ex);
		}
	}

	public String getTextPatentRecordViewPublicationNumber() throws Exception {
		String pnumber;
		waitUntilElementIsDisplayed(publicationNumber);
		pnumber = getText(publicationNumber);
		return (pnumber);
	}

	public void clickOnCitingPatentViewASResultSet() throws Exception {
		try {
			waitUntilElementIsDisplayed(citingPatentViewAsResultSet);
			jsClick(citingPatentViewAsResultSet);
		} catch (Exception ex) {
			throw new Exception("clickOnCitingPatentViewASResultSet is not working" + ex);
		}
	}
}
