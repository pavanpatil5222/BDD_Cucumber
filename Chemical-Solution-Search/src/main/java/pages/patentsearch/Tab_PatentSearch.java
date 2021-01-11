package pages.patentsearch;
import java.util.ArrayList;
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

public class Tab_PatentSearch extends Controller{
		
	@FindBy(xpath="//app-result-search-bar/section/section/div[1]/div[2]/div[2]")
	private WebElement getPatentResultsCount;
	
	@FindBy(xpath="(//div[contains(.,'Patent')])[5]")
	private WebElement txtPatent;
	
	@FindBy(xpath="//*[@id='chemexp']//section[2]//h1")
    WebElement element_FetchRecord;
	
	@FindBy(xpath="//*[@id='chemexp']/section[2]/app-page-search-result/div/app-result-search-bar/section/section/div[1]")
	private WebElement tabPatent;
	
	@FindBy(xpath="//app-cluster-map/section/mat-expansion-panel/mat-expansion-panel-header")
	private WebElement linkSuggestedKeyword;
	
	@FindBy(xpath="//*[@id='txt_0']")
	private WebElement Keyword;
	
	@FindBy(xpath="//span[contains(text(),'Apply filters')]/parent::button")
    WebElement button_ApplyFilter;
	
	@FindBy(xpath="//span[@class='mat-button-wrapper'][contains(.,'Clear all filters')]")
    WebElement button_ClearAllFilter;
	
	@FindBy(xpath="(//mat-card-subtitle[contains(@class,'title mat-card-subtitle')])[1]")
	private WebElement linkPatenRecord;
			
	@FindBy(xpath="(//span[contains(.,'close')])[3]")
	private WebElement closePatentRecord;
	

	@FindBy(xpath="//app-result-count-bar//section//span[@class='result-count']")
	private WebElement getResultsCount;
	
	@FindBy(xpath="//app-result-search-bar/section/section/div[2]/div[2]/div[2]")
	private WebElement getLiteratureResultsCount;
	
	@FindBy(xpath = "//*[@id='mat-input-1']")
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
		
	@FindBy(xpath="//*[@id='chemexp']//app-most-relavent-card[1]//mat-card-content/div/span")
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

	@FindBy(xpath="//mat-select[contains(@aria-label,'Items per page:')]")
    WebElement dropdown_ItemsPerPage;
		
	@FindBy(xpath="//span[contains(text(),'close')]/ancestor::button")
    WebElement closeMarkX;
	
	@FindBy(xpath="//label//strong[contains(text(),'')]")
    WebElement tagName_Highlighted;
	
	@FindBy(xpath = "//div[contains(@class,'mat-paginator-range-label')]")
	private WebElement label_PaginatorRange;
	
	@FindBy(xpath="//section[@class='filterDetails']//div[contains(@class,'mat-chip-list-wrapper')]")
    WebElement box_PillFilter;
	
	@FindBy(xpath="//div/h2[contains(text(),'Novelty')]")
    WebElement field_Novelty;
	
	@FindBy(xpath="//div/h2[contains(text(),'Detailed Description')]")
    WebElement field_DetailedDesc;
    
    @FindBy(xpath="//div/h2[contains(text(),'Use')]")
    WebElement field_Use;
    
    @FindBy(xpath="//div/h2[contains(text(),'Advantage')]")
    WebElement field_Advantage;
    
    @FindBy(xpath="//div/h2[contains(text(),'Technology Focus')]")
    WebElement field_TechFocus;
    
    @FindBy(xpath="//div/h2[contains(text(),'Technology Focus')]/parent::div/following-sibling::div/h2[contains(text(),'Definitions')]")
    WebElement field_TechFocusDefinitions;
    
    @FindBy(xpath="//div/h2[contains(text(),'Technology Focus')]/parent::div/following-sibling::div/h2[contains(text(),'Example')]")
    WebElement field_TechFocusExamples;
    
    @FindBy(xpath="//mat-panel-title[contains(text(),'Claims')]")
    WebElement field_Claims;
	
    @FindBy(xpath="//mat-panel-title[contains(text(),'DWPI family')]")
    WebElement field_DWPIFamily;
    
    @FindBy(xpath="//mat-panel-title[contains(text(),'Description')]")
    WebElement field_Desciption;
    
    @FindBy(xpath="//div[contains(@class,'publication-number')]")
    WebElement publicationNumber;
	
	@FindBy(xpath="//div[contains(@class,'record-title')]")
    WebElement recordTitle;
    
    @FindBy(xpath="//div[contains(@class,'date')]/span[contains(text(),'published:')]")
    WebElement text_Published;
    
    @FindBy(xpath="//div[contains(@class,'date')]/span[contains(text(),'Status:')]")
    WebElement text_Status;
	
    @FindBy(xpath="//div[contains(@class,'date')]/span[contains(text(),'Status:')]/b")
    WebElement  value_Status;
    
    @FindBy(xpath="//mat-panel-title[contains(text(),'Bibliography')]")
    WebElement section_Bibliography;
	
	@FindBy(xpath="//mat-panel-title[contains(text(),'Bibliography')]/ancestor::mat-expansion-panel-header")
    WebElement field_Bibliography;
	
	@FindBy(xpath="//div/h2[contains(text(),'Assignee')]")
    WebElement field_Assignee;
	
	@FindBy(xpath="//div/h2[contains(text(),'Inventor')]")
    WebElement field_Inventor;

    @FindBy(xpath="//div/h2[contains(text(),'Priority')]")
    WebElement field_Priority;
    
    @FindBy(xpath="//div/h2[contains(text(),'Application')]")
    WebElement field_Application;
	
	@FindBy(xpath="//mat-panel-title[contains(text(),'Images')]")
    WebElement section_Image;
	
	@FindBy(xpath="//mat-panel-title[contains(text(),'Abstract')]/ancestor::mat-expansion-panel-header")
    WebElement field_Abstract;
	
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
		
		public boolean isDisplayedTitle(int rowNumber) throws Exception {
			try{
				List<WebElement> ele=driver.findElements(By.xpath("//mat-card-subtitle[@class='title mat-card-subtitle']"));
				
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

		public void clickOnTitle(int rowNumber) throws Exception {
			try{
				List<WebElement> ele=driver.findElements(By.xpath("//mat-card-subtitle[@class='title mat-card-subtitle']"));
				WebElement eleTitle=ele.get(rowNumber);
				eleTitle.click();
				waitUntilElementIsDisplayed(recordViewDetails);
			}
			catch (Exception e) 
			{
				throw new Exception("clickOnTitle is not working" + e);
			}
		}
		
		public boolean isDisplayedPN(int rowNumber) throws Exception {
			try{
				List<WebElement> ele=driver.findElements(By.xpath("//mat-card-title[@class='pn mat-card-title']"));
				WebElement elePN=ele.get(rowNumber);
				boolean blnChkPN=controller.isElementDisplayed(elePN);
			
			if (blnChkPN)
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
		
		public boolean isDisplayedUse(int rowNumber) throws Exception {
			try {
			List<WebElement> ele=driver.findElements(By.xpath("//div[@class='ng-star-inserted']//section[text()='Use']"));
			WebElement eleUse=ele.get(rowNumber);
			boolean blnChkUse=controller.isElementDisplayed(eleUse);
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
			List<WebElement> ele=driver.findElements(By.xpath("//div[@class='ng-star-inserted']//section[text()='Novelty']"));
			WebElement eleNovelty=ele.get(rowNumber);
			boolean blnChkNovelty=controller.isElementDisplayed(eleNovelty);
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
			List<WebElement> ele=driver.findElements(By.xpath("//div[@class='image']//img"));
			WebElement eleImage=ele.get(rowNumber);
			boolean blnChkImage=controller.isElementDisplayed(eleImage);
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
		
		public int getCountOfHighlightedKeyword(String searchText) throws Exception {
			try {
				String lowerCasesearchText=searchText.toLowerCase();
				List<WebElement> elements;
				elements = driver.findElements(By.xpath("//div[@class='ng-star-inserted' and @style='flex: 1 1 100%; box-sizing: border-box; max-width: 100%;']//mark[contains(text(),'"+lowerCasesearchText+"')]"));
				return elements.size();
			 }catch (Exception e) {
				 throw new Exception("getCountOfHighlightedKeyword is not working.." + e);
			}
		}
		
		public int getCountOfHighlightedTextInRS(String searchText) throws Exception {
			try {
				String lowersearchText=searchText.toLowerCase();
				List<WebElement> elements;
				elements = driver.findElements(By.xpath("//mat-card[@class='mat-card' and @style='flex: 1 1 100%; box-sizing: border-box; max-width: 100%;']//mark[contains(text(),'"+lowersearchText+"')]"));
				return elements.size();		
				
			 }catch (Exception e) {
				 throw new Exception("getCountOfHighlightedKeyword is not working.." + e);
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
		
		
		public boolean isDisplayedFetchRecordProgressBar() throws Exception {
	          try {
	                      waitTime(2);
	                      return controller.isElementDisplayed(element_FetchRecord);
	                         
	                 } catch (Exception e) {
	                	 throw new Exception(" FetchRecordProgressBar is not displaying.." + e);

	                              }
	           }
		
		
		public int numberOfMajorPlayersAssigneeDisplayedForPatent() throws Exception {
			try {
				int playersCount=0;
				controller.jsScrollToElement(label_WhoAreTheMajorPlayers);
				List<WebElement> listOfMajorPlayers = driver.findElements(By.xpath("//*[@class='major-player']//*[name()='svg']//*[name()='g' and @class='g']//*[name()='text']"));
				
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
		
		
		public int numberOfMajorInventorsAssigneeDisplayedForPatent() throws Exception {
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
		
		public List<String> getColorsForPatentAssigneeDocumentsBar(String assigneeName){
			    controller.jsScrollToElement(label_WhoAreTheMajorPlayers);
				List<String> getActualColors = new ArrayList<String>();
				List<WebElement> listOfColors = driver.findElements(By.xpath("//*[@id='major-player']//*[name()='text' and text()='"+assigneeName+"']//preceding-sibling::*"));
				
				for (WebElement color:listOfColors)
				
				{
					String actualColor = getElementAttribute(color, "style").trim();
					getActualColors.add(actualColor);
					
				}
				return getActualColors;
		}

		public String getColorOfDifferentAssigneeState(String assigneeState){
			controller.jsScrollToElement(label_WhoAreTheMajorPlayers);
			WebElement assigneeStateColors = driver.findElement(By.xpath("//*[@id='major-player']//*[name()='g' and @class='legend']//*[name()='text' and contains(text(),'"+assigneeState+"')]//preceding-sibling::*"));
			String colorAttribute= getElementAttribute(assigneeStateColors,"fill").trim();
			
			return colorAttribute;
				
	      }
		
		public boolean isDisplayedFilterAboveSuggestedKeywords() throws Exception {
			try {
			    boolean status=controller.isElementDisplayed(labelFilters_SuggestedKeywords);
				return status;	
			 }catch (Exception e) {
			return false;
			}
		}
		
		public boolean isSectionFilterInExpandedCollapsedState() throws Exception {
			try {

				boolean Status = true;
				String filterAttribute;
				WebElement sectionFilter = driver.findElement(By.xpath("//div[contains(text(),'Filter')]/ancestor::mat-expansion-panel-header"));
				filterAttribute=controller.getElementAttribute(sectionFilter, "aria-expanded");
				if(!filterAttribute.contentEquals("false"))
				{
					return false;
				}
				clickOnLinkFilters();
				filterAttribute=controller.getElementAttribute(sectionFilter, "aria-expanded");
				if(!filterAttribute.contentEquals("true"))
				{
					return false;
				}
				clickOnLinkFilters();
				filterAttribute=controller.getElementAttribute(sectionFilter, "aria-expanded");
				if(!filterAttribute.contentEquals("false"))
				{
					return false;
				}
				return Status ;
			 }catch (Exception e) {
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
				previousPageAttribute=controller.getElementAttribute(previousPage, "disabled");
				if(!previousPageAttribute.contentEquals("true"))
				{
					return false;
				}
				return Status;
			 }catch (Exception e) {
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
		
		public void waitUntilFectchRecordProgressBarToDisappears() throws Exception {
	          try {
	                         waitTime(2);
	                         waitUntilElementIsNotDisplayed(element_FetchRecord);
	                         waitForPageLoad();
	                 } catch (Exception e) {
	                	 throw new Exception("waitUntilDottedProgressBarToDisappears is not working.." + e);

	                              }
	           }
		      
		public void clickOnArrowNextPageTillLastPage() throws Exception {
			try {
				
				String paginationRange=getTextPaginatorRange();
				String[] paginationValue=paginationRange.split("of");
				String nextRange=paginationValue[1].trim();
				int j=Integer.parseInt(nextRange); 
				String firstRange=paginationValue[0].trim();
				String[] firstRangeAfterSplit= firstRange.split("–");
				String valueForFirstRange=firstRangeAfterSplit[1].trim(); 
				int i=Integer.parseInt(valueForFirstRange);
				String selectedDropdownValue=getValueFromItemsPerPageDropdown();
				int selectedValue=Integer.parseInt(selectedDropdownValue);
				while (i<j)
				{		
			      clickOnArrowNextPage();
			      waitUntilFectchRecordProgressBarToDisappears();
			      i+=selectedValue;
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
				nextPageAttribute=controller.getElementAttribute(arrowNextPage, "disabled");
				if(!nextPageAttribute.contentEquals("true"))
				{
					return false;
				}
				return Status;
			 }catch (Exception e) {
			return false;
			}
		}
		
		public void clickOnItemsPerPageDropDown() throws Exception {
			try {
				super.jsClick(dropdown_ItemsPerPage);
				} catch (Exception ex) {
				throw new Exception("clickOn dropdown is not working" + ex);
			}
		}
		
		public void selectItemsPerPageFromDropDown(String itemValue) throws Exception {
			try {
				clickOnItemsPerPageDropDown();
	            List<WebElement> listOfDropdownValue = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
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
				throw new Exception("item has not selected" + ex);
			}
		}
		
		public void pillBoxValidation(String itemValue) throws Exception {
			try {
				
	            List<WebElement> listOfDropdownValue = driver.findElements(By.xpath("//section[@class='filterDetails']//mat-chip-list[@class='mat-chip-list']/div/mat-chip/div"));
				
	            if(listOfDropdownValue.size()>0)
	            for (WebElement dropdownValue:listOfDropdownValue)
				{
	            	
					if (dropdownValue.isDisplayed())
					{
						String pillValue=dropdownValue.getText();
						if (pillValue.contains("+") && pillValue.contains("more"))
						{
							controller.Logger.addsubStep(LogStatus.INFO, "Personal Folder checkbox is already deselected", true);
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
				
				WebElement selectAll = driver.findElement(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//span[contains(text(),'Select all')]/parent::button"));
				controller.jsScrollToElement(selectAll);
				super.jsClick(selectAll);
				} catch (Exception ex) {
				throw new Exception("clickOn button select all is not working" + ex);
			}
		}
				
		public boolean isDisplayedCloseMarkX() throws Exception {
			try {
			    boolean status=controller.isElementDisplayed(closeMarkX);
				return status;	
			 }catch (Exception e) {
			return false;
			}
		}
		
		public void clickOnCloseMarkX() throws Exception {
			try {
				
				if(isDisplayedCloseMarkX()) {
				super.jsClick(closeMarkX);
				}
				} catch (Exception ex) {
				throw new Exception("click on mark X is not working" + ex);
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
		
		public boolean isDisplayedCloseMarkX(int indexNumber) throws Exception {
			try {
				//int noOfPills=getNoOfPillBoxes();
			    boolean status=controller.isElementDisplayed(driver.findElement(By.xpath("(//mat-icon[contains(.,'close')])["+(indexNumber+3)+"]")));
				return status;	
			 }catch (Exception e) {
			return false;
			}
		}
		
		public void clickOnCloseMarkX(String pillName) throws Exception {
			try {
				int j = 0;
				 List<WebElement> listOfPills=driver.findElements(By.xpath("//mat-chip[contains(@class,'mat-chip mat-primary mat-standard-chip mat-chip-with-trailing-icon ng-star-inserted')]//div[@class='cdx-chip-block']//span[@class='cdx-chip-name']"));
				for(int i=0;i<listOfPills.size();i++) 
				{
					if(listOfPills.get(i).getText().equals(pillName)) 
					{
						j=i+1;
						break;
					}
				}
				 //super.jsClick(driver.findElement(By.xpath("(//mat-icon[contains(.,'close')])["+(indexNumber+3)+"]")));
				super.jsClick(driver.findElement(By.xpath("(//mat-chip[@class='mat-chip mat-primary mat-standard-chip mat-chip-with-trailing-icon ng-star-inserted']//mat-icon[contains(text(),'close')])["+j+"]")));
				} catch (Exception ex) {
				throw new Exception("click on mark X is not working" + ex);
			}
		}
		
		public String getFilteredPillName(int indexNumber) throws Exception {
			try {
				String pillName=driver.findElement(By.xpath("(//mat-chip[contains(@class,'mat-chip mat-primary mat-standard-chip mat-chip-with-trailing-icon ng-star-inserted')]//div[@class='cdx-chip-block']//span[@class='cdx-chip-name'])["+indexNumber+"]")).getText();
				return pillName;	
			 }catch (Exception e) {
			return "";
			}
		}
		
		public String getTagName() throws Exception {
			try {
				String tagName=tagName_Highlighted.getTagName();
				return tagName;	
			 }catch (Exception e) {
			return "";
			}
		}
		
		public String getTextTheHighlightedFilterList() throws Exception {
			try {
				String tagName=tagName_Highlighted.getText();
				return tagName;	
			 }catch (Exception e) {
			return "";
			}
		}
		
		public int getCountOfFilterListForGivenFilter(String filterName) throws Exception {
			try {
				List<WebElement> elements;
				elements = driver.findElements(By.xpath("//div[contains(text(),'+filterName+')]//ancestor::mat-expansion-panel-header//following-sibling::div//mat-selection-list/mat-list-item"));
				return elements.size();
			 }catch (Exception e) {
				 throw new Exception("getCountOfHighlightedKeyword is not working.." + e);
			}
		}
		
		public void enterTextInSearchAreaForGivenFilter(String filterName, String searchText) throws Exception {
			try {
				WebElement searchArea = driver.findElement(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//input[@aria-label='Search']"));
				controller.jsScrollToElement(searchArea);
				super.jsClick(searchArea);
				setText(searchArea, searchText);
				} catch (Exception ex) {
				throw new Exception("text has not enetered in text area" + ex);
			}
		}
		
		public boolean isCheckboxAvailableForAllTheFilterItemForGivenFilter(String filterName) throws Exception {
			try {
		        boolean Status =true;
	            List<WebElement> listOfDropdownValue = driver.findElements(By.xpath("//div[contains(text(),'+filterName+')]//ancestor::mat-expansion-panel-header//following-sibling::div//input[@type='checkbox']"));
				for (WebElement dropdownValue:listOfDropdownValue)
				{
					if(!dropdownValue.isDisplayed())
					{
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
		        String eleForValue=driver.findElement(By.xpath("//mat-expansion-panel-header//following-sibling::div//label[text()='"+filterValue+"'])[1]")).getAttribute("for");
						if(controller.isElementSelected(driver.findElement(By.xpath("//input[@id='"+eleForValue+"']"))));
						{
							return true;				
						}
				} catch (Exception ex) {
				return false;
			}
		}

		
		
		public boolean isCheckboxAvailableAndUncheckedForAllTheFilterItemForGivenFilter(List<String> listOfFilterName) throws Exception {
			try {
		        boolean Status =true;
		        
		        for (String filterName:listOfFilterName)
		        { 
		        	WebElement ele = driver.findElement(By.xpath("//span//div[contains(text(),'"+filterName+"')]"));
		        	super.jsScrollToElement(ele);
		        	
		        	WebElement filteredFields = driver.findElement(By.xpath("//span/div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header"));
		        	String filterFieldsAttribute=controller.getElementAttribute(filteredFields, "aria-expanded");
		        	if(filterFieldsAttribute.contentEquals("true"))
		        	{
		        	List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//label"));
				   for (WebElement filterValue:listOfFilters)
				   {
					super.jsScrollToElement(filterValue);
					if(!(controller.isElementDisplayed(filterValue) && !(controller.isElementSelected(filterValue))))
					{
						return false;				
					}
				}
		        	}
		        	else 
		        		
		        	{
		        		super.click(ele);
			        	List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//label"));
					   for (WebElement filterValue:listOfFilters)
					{
						super.jsScrollToElement(filterValue);
						if(!(controller.isElementDisplayed(filterValue) && !(controller.isElementSelected(filterValue))))
						{
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
		        boolean Status =true;
	            List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//label//preceding-sibling::input"));
				for (WebElement filterValue:listOfFilters)
				{
					controller.jsScrollToElement(filterValue);
					if(!controller.isElementSelected(filterValue))
					{
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
				filterAttribute=controller.getElementAttribute(button_ApplyFilter, "disabled");
				if(!filterAttribute.contentEquals("true"))
				{
					return false;
				}
				return Status;
			 }catch (Exception e) {
			return false;
			}
		}


		public boolean isEnabledButtonApplyFilters() throws Exception {
			try {
	          return controller.isElementEnabled(button_ApplyFilter);
			 }catch (Exception e) {
			return false;
			}
		}

		public boolean isDisplayedButtonDeselectAllForGivenFilter(String filterName) throws Exception {
			try {
				
				WebElement deselectAll = driver.findElement(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//span[contains(text(),'Deselect all')]/parent::button"));
				controller.jsScrollToElement(deselectAll);
				return controller.isElementDisplayed(deselectAll);
				
				} catch (Exception ex) {
				throw new Exception("deselect all button is not displayed" + ex);
			}
		}
		
		public void clickOnButtonDeselectAllForGivenFilter(String filterName) throws Exception {
			try {
				
				WebElement deselectAll = driver.findElement(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//span[contains(text(),'Deselect all')]/parent::button"));
				controller.jsScrollToElement(deselectAll);
				super.jsClick(deselectAll);
				} catch (Exception ex) {
				throw new Exception("click on button deselect all is not working" + ex);
			}
		}
		
		public boolean isCheckboxDeselectedForAllTheFilterItemForGivenFilter(String filterName) throws Exception {
			try {
		        boolean Status =true;
	            List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//label//preceding-sibling::input"));
				for (WebElement filterValue:listOfFilters)
				{
					controller.jsScrollToElement(filterValue);
					if(controller.isElementSelected(filterValue))
					{
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
		        boolean Status =true;
		           List<WebElement> listOfFilters = driver.findElements(By.xpath("//div[contains(text(),'"+filterName+"')]//ancestor::mat-expansion-panel-header//following-sibling::div//label"));
				   for (WebElement filterValue:listOfFilters)
				   {
					super.jsScrollToElement(filterValue);
					if(!controller.isElementDisplayed(filterValue))
					{
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
				 }catch (Exception e) {
				return false;
				}
			}
			
			public boolean isDisplayedCrossXForAllThePills() throws Exception {
				try {
					boolean Status=true;
		            List<WebElement> listOfPillFilteredValue = driver.findElements(By.xpath("//section[contains(@class,'filterDetails')]//mat-icon[contains(text(),'close')]"));
					
		            if(listOfPillFilteredValue.size()>0)
		            for (WebElement filteredValue:listOfPillFilteredValue)
					{
						if (!filteredValue.isDisplayed())
						{
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
					boolean Status=false;
		            List<WebElement> listOfPillFilteredValue = driver.findElements(By.xpath("//section[@class='filterDetails']//mat-chip-list[@class='mat-chip-list']/div/mat-chip/div"));
					
		            if(listOfPillFilteredValue.size()>0)
		            for (WebElement filteredValue:listOfPillFilteredValue)
					{
		            	
						if (filteredValue.isDisplayed())
						{
							String pillValue=filteredValue.getText();
							if (pillValue.contains("+") && pillValue.contains("More"))
							{
								
							    Status=true;
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
					boolean Status=true;
		            List<WebElement> listOfPillFilteredValue = driver.findElements(By.xpath("//section[@class='filterDetails']//mat-chip-list[@class='mat-chip-list']/div/mat-chip/div"));
		           
		            if(listOfPillFilteredValue.size()>0)
		            for (WebElement filteredValue:listOfPillFilteredValue)
					{
							String pillValue=filteredValue.getText();
							if (pillValue.contains("+") && pillValue.contains("More"))
							{
								controller.jsScrollToElement(filteredValue);
							    controller.hoverOnWebelement(filteredValue);
							    List<WebElement> pillFilteredList = driver.findElements(By.xpath("//section[@class='filterDetails']//mat-chip-list[@class='mat-chip-list']/div/mat-chip/div/span"));
							    for (int i=0;i<pillFilteredList.size();i=i+2)
							    	
							    {
							    	controller.jsScrollToElement(pillFilteredList.get(i));
							    	String headerValue=pillFilteredList.get(i).getText();
							    	String textValue=pillFilteredList.get(i+1).getText();
							    	if(!pillValue.contains(headerValue) && !pillValue.contains(textValue)) {
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
					boolean status=controller.isElementDisplayed(publicationNumber);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}

		    public boolean isDisplayedRecordTitle() throws Exception {
				try {
					boolean status=controller.isElementDisplayed(recordTitle);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
				
		    public boolean isDisplayedTextPublished() throws Exception {
				try {
					boolean status=controller.isElementDisplayed(text_Published);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
			
		    public boolean isDisplayedTextStatus() throws Exception {
				try {
					boolean status=controller.isElementDisplayed(text_Status);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
		    
		    
		    public String getStatusValue() throws Exception {
				try {
					return controller.getText(value_Status);
					
				 }catch (Exception e) {
				return "";
				}
			}
		    
		    	
			public boolean isDisplayedFieldBibliography() throws Exception {
				try {
					controller.jsScrollToElement(section_Bibliography);
				    boolean status=controller.isElementDisplayed(section_Bibliography);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
			
			public boolean isSectionBibliographyExpandedAndCollapsed() throws Exception {
				try {

					boolean Status = true;
					
					super.jsScrollToElement(button_ApplyFilter);
					String bibliographyAttributeExpanded=controller.getElementAttribute(field_Bibliography, "aria-expanded");
					if(!bibliographyAttributeExpanded.contentEquals("true"))
					{
						return false;
					}
					
					else
					{
						controller.jsClick(field_Bibliography);
						String bibliographyAttributeCollapsed=controller.getElementAttribute(field_Bibliography, "aria-expanded");
						if(!bibliographyAttributeCollapsed.contentEquals("false"))
						{
							return false;
						}
						
					}
					
					controller.jsClick(field_Bibliography);
					return Status;

				}catch (Exception e) {
				return false;
				}
			}

			
		    public boolean isDisplayedFieldAssignee() throws Exception {
				try {
					controller.jsScrollToElement(field_Assignee);
				    boolean status=controller.isElementDisplayed(field_Assignee);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
			 
		    public boolean isDisplayedFieldInventor() throws Exception {
				try {
					controller.jsScrollToElement(field_Inventor);
				    boolean status=controller.isElementDisplayed(field_Inventor);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}

		    public boolean isDisplayedFieldPriority() throws Exception {
				try {
					controller.jsScrollToElement(field_Priority);
				    boolean status=controller.isElementDisplayed(field_Priority);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
			
		    public List<String> getBibliographyPriorityDetails() throws Exception {
				try {
			           List<WebElement> listOfPriority = driver.findElements(By.xpath("//div/h2[contains(text(),'Priority')]/following-sibling::div"));
					   return controller.getListWebElementText(listOfPriority);
			        } catch (Exception ex) {
					return null;
				}
			}
			
			
		    public boolean isDisplayedFieldApplication() throws Exception {
				try {
					controller.jsScrollToElement(field_Application);
				    boolean status=controller.isElementDisplayed(field_Application);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
			
			public List<String> getBibliographyApplicationDetails() throws Exception {
				try {
			           List<WebElement> listOfApplication = driver.findElements(By.xpath("//div/h2[contains(text(),'Application')]/following-sibling::div"));
					   return controller.getListWebElementText(listOfApplication);
			        } catch (Exception ex) {
					return null;
				}
			}
			
		    public boolean isDisplayedSectionImage() throws Exception {
				try {
					controller.jsScrollToElement(section_Image);
				    boolean status=controller.isElementDisplayed(section_Image);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
			
			public boolean isSectionAbstractExpandedAndCollapsed() throws Exception {
				try {

					boolean Status = true;
					
					super.jsScrollToElement(field_Abstract);
					String bibliographyAttributeExpanded=controller.getElementAttribute(field_Abstract, "aria-expanded");
					if(!bibliographyAttributeExpanded.contentEquals("true"))
					{
						return false;
					}
					
					else
					{
						controller.jsClick(field_Abstract);
						String bibliographyAttributeCollapsed=controller.getElementAttribute(field_Abstract, "aria-expanded");
						if(!bibliographyAttributeCollapsed.contentEquals("false"))
						{
							return false;
						}
						
					}
					
					controller.jsClick(field_Abstract);
					return Status;

				}catch (Exception e) {
				return false;
				}
			}
			
		    public boolean isDisplayedFieldNovelty() throws Exception {
				try {
					controller.jsScrollToElement(field_Novelty);
				    boolean status=controller.isElementDisplayed(field_Novelty);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}

		    public boolean isDisplayedFieldDetailedDesc() throws Exception {
				try {
					controller.jsScrollToElement(field_DetailedDesc);
				    boolean status=controller.isElementDisplayed(field_DetailedDesc);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
			
		    public boolean isDisplayedFieldUse() throws Exception {
				try {
					controller.jsScrollToElement(field_Use);
				    boolean status=controller.isElementDisplayed(field_Use);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
		    
		 
		   public boolean isDisplayedFieldAdvantage() throws Exception {
				try {
					controller.jsScrollToElement(field_Advantage);
				    boolean status=controller.isElementDisplayed(field_Advantage);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
		   
		    public boolean isDisplayedFieldTechnologyFocus() throws Exception {
				try {
					controller.jsScrollToElement(field_TechFocus);
				    boolean status=controller.isElementDisplayed(field_TechFocus);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
		    
		    public boolean isDisplayedFieldTechnologyFocusDefinitions() throws Exception {
				try {
					controller.jsScrollToElement(field_TechFocusDefinitions);
				    boolean status=controller.isElementDisplayed(field_TechFocusDefinitions);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
		    
		   
		    public boolean isDisplayedFieldTechnologyFocusExamples() throws Exception {
				try {
					controller.jsScrollToElement(field_TechFocusExamples);
				    boolean status=controller.isElementDisplayed(field_TechFocusExamples);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
		    
		    
		    public boolean isDisplayedFieldClaims() throws Exception {
				try {
					controller.jsScrollToElement(field_Claims);
				    boolean status=controller.isElementDisplayed(field_Claims);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
		    
		    public boolean isDisplayedFieldDWPIFamily() throws Exception {
				try {
					controller.jsScrollToElement(field_DWPIFamily);
				    boolean status=controller.isElementDisplayed(field_DWPIFamily);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
		    
		   
		    public boolean isDisplayedFieldDescription() throws Exception {
				try {
					controller.jsScrollToElement(field_Desciption);
				    boolean status=controller.isElementDisplayed(field_Desciption);
					return status;	
				 }catch (Exception e) {
				return false;
				}
			}
			
						
}
