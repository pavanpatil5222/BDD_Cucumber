package pages.literaturesearch;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.Keys;
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
public class Tab_LiteratureSearch extends Controller{
	
	@FindBy(xpath = "//*/section[2]//div/section/section/section[2]/section/app-result-set[1]//section[2]/div/button")
	private WebElement linkMoreLikeThis;	
	
	@FindBy(xpath = "//span[text()=' Restore defaults ']")
	private WebElement btnRestoreDefaults;	
	
	@FindBy(xpath = "(//input[@maxlength='40'])[2]")
	private WebElement txtFirstClusterItem;
	
	@FindBy(xpath = "(//input[@maxlength='40'])[3]")
	private WebElement txtSecondClusterItem;
	
	@FindBy(css = "#mat-hint-0 > span")
	private WebElement txtClusterErrMsg;
	
	@FindBy(xpath = "//div[@class='chart']//*[name()='svg']//*[name()='circle']")
	private WebElement firstBubble;

	@FindBy(xpath = "//h4[contains(.,'Edit existing cluster items')]")
	private WebElement txtClusterLabel;
	
	@FindBy(xpath = "//span[contains(.,'Customize')]")
	private WebElement linkCustomize;

	@FindBy(xpath = "//div[2]/mat-dialog-actions/button[2]")
	private WebElement btnApply;
	
	@FindBy(xpath = "(//button//span//mat-icon[text()='close'])[6]")
	private WebElement moreLikeCloseIcon;
	
	@FindBy(xpath = "//app-record-view/section/div/div[2]/div[1]/span/span[1]/button")
	private WebElement rsAuthor;
	
	@FindBy(xpath="//div[@title='Structure search not yet available for Literature']")
    private WebElement disableLitTab;
	
	@FindBy(css = "section.insights-chart > app-suggested-keyword > section > section.view.disable-click")
	private WebElement keywordDisable;
	
	@FindBy(xpath = "//app-keyword-search/app-chem-pills/section/section/mat-chip-list/div/mat-chip/div[2]/span[2]/span")
	private WebElement moreLikeKeyword;
	
	@FindBy(xpath="//app-result-search-bar/section/section/div[2]/div[2]/div[2]")
	private WebElement getLiteratureResultsCount;
	
	@FindBy(xpath="(//div[contains(.,'Literature')])[5]")
	private WebElement txtLiterature;
	
	@FindBy(xpath="//app-cluster-map/section/mat-expansion-panel/mat-expansion-panel-header")
	private WebElement linkSuggestedKeyword;
	
	@FindBy(xpath="//*[@id='plus_txt_0']")
	private WebElement Keyword;
	
	@FindBy(xpath="//*[@id='chemexp']/section[2]/app-page-search-result/div/app-result-search-bar/section/section/div[2]")
	private WebElement tabLiterature;
	
	@FindBy(xpath="(//mat-card-title[@class='literature-title mat-card-title'])[1]")
	private WebElement linkLiteratureRecord;
	
	@FindBy(xpath="//span[@class='material-icons'][contains(.,'close')]")
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
	
	@FindBy(xpath="//div/section/app-result-count-bar/section/section[3]")
    WebElement btn_TabInsights;
	
	@FindBy(xpath="//app-result-count-bar/section/section[3]/button")
    WebElement btn_Insights;	
	
	@FindBy(css="#label_txt_0")
	private WebElement literatureInsightsKeyword;

	@FindBy(xpath ="//app-result-set[1]/section/mat-card/mat-card-content/section[2]/div/div[1]/button")
	private WebElement thumsUpIcon;
	
	@FindBy(xpath ="//app-result-set[1]/section/mat-card/mat-card-content/section[2]/div/div[2]/button")
	private WebElement thumbsDownIcon;
		
	@FindBy(xpath = "//div[@id='insights-tab-3']")
	private WebElement tabAuthors;
	
	@FindBy(xpath = " //section[contains(@class, 'major-inventor')]//div[@class='chart']//*[name()='svg']//*[name()='rect']")
	private WebElement barAuthors;

	@FindBy(xpath = "//section[@id='major-inventor']//*[name()='text' and @id='playerText_0']")
	private WebElement textAuthors;
	
	@FindBy(xpath="//app-publication-year/section/mat-expansion-panel/mat-expansion-panel-header/span[2]")
    private WebElement linkPublicationYear;
	
	@FindBy(xpath="//app-publication-year/section/mat-expansion-panel/mat-expansion-panel-header/span[1]")
    private WebElement publicationYearLabel;
	
	@FindBy(xpath="//button[@class='mat-focus-indicator decrement-btn mat-icon-button mat-button-base']")
    private WebElement publicationYearLeftArrow;
	
	@FindBy(xpath="//button[@class='mat-focus-indicator increment-btn mat-icon-button mat-button-base']")
    private WebElement publicationYearRightArrow;
	
	@FindBy(xpath="//div[contains(text(),'Publication year')]/ancestor::mat-expansion-panel-header")
    private WebElement publicationYearFilter;
	
	@FindBy(xpath="//div[@class='publ-year-chart']")
    private WebElement publicationYearFilterDisable;
		
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
	
	public void clickOnLiteratureRecord(int rowNumber) throws Exception {
		try{
			WebElement ele = driver.findElement(By.cssSelector("app-result-set:nth-child("+rowNumber+") > section > mat-card > mat-card-header > div > mat-card-subtitle"));
			ele.click();
			controller.waitUntilFectchRecordProgressBarToDisappears();				
			} catch (Exception ex) {
				throw new Exception("clickOnLiteratureRecord is not working" + ex);
			}
		}
	
	
	public void clickOnCloseLiteratureRecordView() throws Exception {
		try {
			waitUntilElementIsDisplayed(closeLiteratureRecord);
			jsClick(closeLiteratureRecord);
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
	
	public void clickOnButtonFirstKeyWord() throws Exception {
		try
		{
		 waitUntilElementIsDisplayed(Keyword);
		 click(Keyword);
		
		}
	 catch (Exception ex) {
			throw new Exception("clickOnButtonFirstKeyWord is not working" + ex);
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
	
	public boolean isDisplayedTitle(int rowNumber) throws Exception {
		try{
			List<WebElement> ele=driver.findElements(By.xpath("//mat-card-header[@class='mat-card-header']"));
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
	
	public void clickOnButtonInsights() throws Exception{
		try {
			if (!btn_TabInsights.getAttribute("ng-reflect-fx-flex").toLowerCase().contains("385px")) {
				controller.jsClick(btn_Insights);
				
			} else {
				controller.Logger.addsubStep(LogStatus.INFO, "TAB INSIGHTS IS SELECTED ALREADY", false);
			}
		} catch (Exception ex) {
			throw new Exception("click on Button Tab Insights is not working for :: "+ ex);
		}
	}
	
	
	public String getTextLiteratureInsightsKeyword() throws Exception {
		String keyword;
		 waitUntilElementIsDisplayed(literatureInsightsKeyword);
		 keyword = getText(literatureInsightsKeyword);
		 return(keyword);
		}

	public void clickOnThumbsUpIcon() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumsUpIcon);
			jsClick(thumsUpIcon);
			iconStatus=getElementAttribute(thumsUpIcon, "class");
			if(iconStatus.contains("focus"))
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
			iconStatus=getElementAttribute(thumsUpIcon, "class");
			return(iconStatus);
			} catch (Exception ex) {
			throw new Exception("clickOnThumbsUpIcon is not working" + ex);
		}
	}
	
	public String checkThumbsDownHollowState() throws Exception {
		try {
			String iconStatus;
			waitUntilElementIsDisplayed(thumbsDownIcon);
			iconStatus=getElementAttribute(thumbsDownIcon, "class");
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
			iconStatus=getElementAttribute(thumbsDownIcon, "class");
			if(iconStatus.contains("focus"))
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
	
	public boolean isDisplayedLiteratureThumbsUpIcon() throws Exception {
		int j=1;
		boolean status=true;
		try {
			List<WebElement> thumbsUpIcons = driver.findElements(By.xpath("//button[@title='Vote as relevant']"));
			System.out.println("size" + thumbsUpIcons.size());
			if (thumbsUpIcons.size() > 0) {
				for (WebElement ele : thumbsUpIcons) {
					if(controller.isElementDisplayed(driver.findElement(By.xpath("//button[@title='Vote as relevant']")))) {
					controller.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS DISPLAYED FOR THE RECORD:"+(j)+"", false);
					j=j+1;
				}
				else 
				{
				controller.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT DISPLAYED FOR THE RECORD:"+(j)+"", true);
				status=false;
				}	
			}
		  }
		}
		 catch (Exception e) {
			throw new Exception("isDisplayedLiteratureThumbsUpIcon is not working" + e);
		}
		return status;
	}

	public boolean isDisplayedLiteratureThumbsDownIcon() throws Exception {
		int j=1;
		boolean status=true;
		try {
			List<WebElement> thumbsDownIcons = driver.findElements(By.xpath("//button[@title='Vote as irrelevant']"));
			System.out.println("size" + thumbsDownIcons.size());
			if (thumbsDownIcons.size() > 0) {
				for (WebElement ele : thumbsDownIcons) {
					if(controller.isElementDisplayed(driver.findElement(By.xpath("//button[@title='Vote as irrelevant']")))) {
					controller.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS DISPLAYED FOR THE RECORD:"+(j)+"", false);
					j=j+1;
				}
				else 
				{
				controller.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT DISPLAYED FOR THE RECORD:"+(j)+"", true);
				status=false;
				}	
			}
		  }
		}
		 catch (Exception e) {
			throw new Exception("isDisplayedLiteratureThumbsDownIcon is not working" + e);
		}
		return status;
	}
		
	
	public int getCountOfHighlightedTextInRS(String searchText) throws Exception {
		try {
			String lowerCasesearchText=searchText.toLowerCase();
			List<WebElement> elements;
			elements = driver.findElements(By.xpath("//mark[contains(text(),'"+lowerCasesearchText+"')]"));
			return elements.size();
		}catch (Exception e) {
			 throw new Exception("getCountOfHighlightedTextInRS is not working.." + e);
		}
	}
	

	public void clickOnButtonThumsUp(int recordNumber) throws Exception
	{
		try {
			WebElement listOfThumsUp = driver.findElement(By.cssSelector("app-result-set:nth-child("+recordNumber+") > section > mat-card > mat-card-content > section:nth-child(3) > div > div:nth-child(1) > button > span > mat-icon"));
			controller.jsClick(listOfThumsUp);
			controller.Logger.addsubStep(LogStatus.INFO,"Clicked On Thumbs Up Button Successfully", false);
			}
			catch(Exception e) 
			{
				throw new Exception("clickOnButtonThumsUp is not working.." + e);
			}
	}
	
	public boolean isDisplayedButtonThumsUpWithFillColor(int recordNumber) throws Exception
	{
		try 
		{
			WebElement listOfThumsUp = driver.findElement(By.cssSelector("app-result-set:nth-child("+recordNumber+") > section > mat-card > mat-card-content > section:nth-child(3) > div > div:nth-child(1) > button > span > mat-icon> svg > g > path"));
			if(controller.getElementAttribute(listOfThumsUp, "stroke").contains("#008474")) 
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
			WebElement listOfThumsDown = driver.findElement(By.cssSelector("app-result-set:nth-child("+recordNumber+") > section > mat-card > mat-card-content > section:nth-child(3) > div > div:nth-child(2) > button > span > mat-icon > svg > g > path"));
			if(controller.getElementAttribute(listOfThumsDown, "stroke").contains("#008474")) 
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
		WebElement listOfThumsDown = driver.findElement(By.cssSelector("app-result-set:nth-child("+recordNumber+") > section > mat-card > mat-card-content > section:nth-child(3) > div > div:nth-child(2) > button > span > mat-icon"));
		controller.jsClick(listOfThumsDown);
		controller.Logger.addsubStep(LogStatus.INFO,"Clicked On Thumbs Down Button Successfully", false);
		}
		catch(Exception e) 
		{
			throw new Exception("clickOnButtonThumsDown is not working.." + e);
		}
	}
	
	public void clickOnAuthorsTab() throws Exception {
		try {
			waitUntilElementIsDisplayed(tabAuthors);
			jsClick(tabAuthors);
		} catch (Exception ex) {
			throw new Exception("clickOnAuthorsTab is not working" + ex);
		}
	}
	public void clickOnAuthorsBar() throws Exception {
		try {
			waitUntilElementIsDisplayed(barAuthors);
			Actions builder = new Actions(driver);
			builder.click(barAuthors).build().perform();
		} catch (Exception ex) {
			throw new Exception("clickOnAuthorsBar is not working" + ex);
		}
	}
	public void clickOnAuthorsText() throws Exception {
		try {
			waitUntilElementIsDisplayed(textAuthors);
			Actions builder = new Actions(driver);
			builder.click(textAuthors).build().perform();
		} catch (Exception ex) {
			throw new Exception("clickOnAuthorsText is not working" + ex);
		}
	}
	
	public boolean isDisplayedPublicationYearChartExpanded() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(linkPublicationYear);
			String chart = controller.getElementAttribute(linkPublicationYear, "style");
			if(chart.contains("180deg"))
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			throw new Exception("isDisplayedPatentRecordCloseIcon is not working" + e);
		}
	}	
	

	public String getTextPublicationChartLabelName() throws Exception {
		 waitUntilElementIsDisplayed(publicationYearLabel);
		String label= controller.getText(publicationYearLabel);
		return(label);
	}
	
	public void selectMultiplePublicationChartFilters() throws Exception
    {
          try {
                 driver.findElement(By.cssSelector("#publ_year_rect_2007")).click();
                 controller.waitUntilFectchRecordProgressBarToDisappears();
                 controller.waitTime(2);
                 driver.findElement(By.cssSelector("#publ_year_rect_2010")).click();
                 controller.waitUntilFectchRecordProgressBarToDisappears();        
                 controller.waitTime(2);
          }
          catch(Exception e) 
          {
                 throw new Exception("selectMultiplePublicationChartFilters is not working.." + e);
          }
    }

	
	
	
	public void deSelectMultiplePublicationChartFilters() throws Exception
    {
          try {
                 driver.findElement(By.cssSelector("#publ_year_rect_2007")).click();
                 controller.waitUntilFectchRecordProgressBarToDisappears();
                 controller.waitTime(2);
                 driver.findElement(By.cssSelector("#publ_year_rect_2010")).click();
                 controller.waitUntilFectchRecordProgressBarToDisappears(); 
                 controller.waitTime(2);
          }
          catch(Exception e) 
          {
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
			if(color.contains("black"))
			publicationYearLeftArrow.click();				
		} catch (Exception e) {
			throw new Exception("clickOnPYChartLeftArrow is not working" + e);
		}
	}
	
	public boolean isEnabledPYChartRightArrow() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(publicationYearRightArrow);
			String color = controller.getElementAttribute(publicationYearRightArrow, "class");
			if(color.contains("mat-focus-indicator increment-btn mat-icon-button mat-button-base"))
			{
			return true;
			}
			else
			{
			return false;
			}
		} catch (Exception e) {
			throw new Exception("isDisabledPYChartRightArrow is not working" + e);
		}
	}
	
	
	public boolean isEnabledPYCharLeftArrow() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(publicationYearLeftArrow);
			String color = controller.getElementAttribute(publicationYearLeftArrow, "class");
			if(color.contains("mat-focus-indicator decrement-btn mat-icon-button mat-button-base"))
			{
			return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			throw new Exception("isEnabledPYCharLeftArrow is not working" + e);
		}
	}
	
	
	public boolean isDisabledPYChartRightArrow() throws Exception {
		try {
			controller.waitUntilElementIsDisplayed(publicationYearRightArrow);
			String color = controller.getElementAttribute(publicationYearRightArrow, "disabled");
			if(color.contains("true"))
			{
			return true;
			}
			else
			{
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
				checkStatus=controller.getElementAttribute(publicationYearFilter, "aria-expanded");
				if(checkStatus.equals("false"))
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
				checkStatus=controller.getElementAttribute(publicationYearFilterDisable, "style");
				if(checkStatus.contains("cursor: not-allowed"))
					return true;
				else
					return false;
			} catch (Exception e) {
				throw new Exception("isDisabledPublicationYearChart is not working" + e);
			}
		}
	 

	 public String getTextKeyWordMoreLike() throws Exception {
		 waitUntilElementIsDisplayed(moreLikeKeyword);
		 String morelikekeyword=controller.getElementAttribute(moreLikeKeyword, "title");
		 return(morelikekeyword);
		}
	 
	 public String getTextLiteratureTitle(int recordnumber) throws Exception {
		 try {
			WebElement ele = driver.findElement(By.cssSelector("app-result-set:nth-child("+recordnumber+") > section > mat-card > mat-card-header > div > mat-card-subtitle"));
			return controller.getText(ele);			
		}
		 catch (Exception ex) {
			throw new Exception("getTextLiteratureTitle is not working" + ex);
		}
	}
	 
		public void clickOnLinkLiteratureRecordViewAuthor() throws Exception {
			try {
				waitUntilElementIsDisplayed(rsAuthor);
				jsClick(rsAuthor);
			} catch (Exception ex) {
				throw new Exception("clickOnLinkLiteratureRecordViewAuthor is not working" + ex);
			}
		}

		public String getTextLiteratureRecordViewAuthor() throws Exception {
			try {
				waitUntilElementIsDisplayed(rsAuthor);
				return controller.getText(rsAuthor);
			} catch (Exception ex) {
				throw new Exception("getTextLiteratureRecordViewAuthor is not working" + ex);
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


		public boolean isDisabledKeyWordSection() throws Exception {
			try {
				String disable;
				waitUntilElementIsDisplayed(keywordDisable);
			    disable=controller.getElementAttribute(keywordDisable, "class");
			    if(disable.contains("view disable-click"))
				return true;
			    else
			   	return false;
			 }catch (Exception e) {
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
		public boolean isDisabledLiteratureTab() throws Exception {
			try {
				String disable;
				waitUntilElementIsDisplayed(disableLitTab);
			    disable=controller.getElementAttribute(disableLitTab, "title");
			    if(disable.contains("search not yet available"))
				return true;
			    else
			   	return false;
			 }catch (Exception e) {
				 throw new Exception("isDisabledLiteratureTab is not working" + e);
			}
		}
		
		public void clickOnLinkCustomize() throws Exception {
			try {
				waitUntilElementIsDisplayed(linkCustomize);
				jsClick(linkCustomize);
			} catch (Exception ex) {
				throw new Exception("clickOnLinkCustomize is not working" + ex);
			}
		}
		public String getTextClusterItemLabel() throws Exception {
			String citem;
			waitUntilElementIsDisplayed(txtClusterLabel);
			citem = getText(txtClusterLabel);
			return (citem);
		}
		

@SuppressWarnings("static-access")
public List<String> getAllClusterItems() throws Exception{
	try {
		
			List<String> citemLabels =new ArrayList<String>();
				for(int i=1;i<=12;i++)
				{
					WebElement element = controller.driver.findElement(By.xpath("(//input[contains(@maxlength,'40')])["+i+"]"));
					String citemLabel = controller.getJSText(element);
					citemLabels.add(citemLabel); 					
				}
			
	return citemLabels;
 	}catch (Exception e) {
 		throw new Exception("getAllClusterItems is not working" + e);
 	}
}

public void deleteExistingClusterItems() throws Exception {
	try {
		for(int i=1;i<=12;i++)
		{
		 WebElement clusterItem = driver.findElement(By.xpath("(//input[contains(@maxlength,'40')])["+i+"]"));
		 clusterItem.click();
		 clusterItem.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));	
		}
	} catch (Exception e) {
		throw new Exception("deleteExistingClusterItems is not working.." + e);
	}
}
public String getTextClusterErrorMessage() throws Exception {
	String cErrMsg;
	waitUntilElementIsDisplayed(txtClusterErrMsg);
	cErrMsg = getText(txtClusterErrMsg);
	return (cErrMsg);
}
public void clickOnButtonRestorDefault() throws Exception {
	try {
		waitUntilElementIsDisplayed(btnRestoreDefaults);
		jsClick(btnRestoreDefaults);
	} catch (Exception ex) {
		throw new Exception("clickOnButtonRestorDefault is not working" + ex);
	}
}
public void setTextFirstClusterItem(String value) throws Exception {
	try {
		waitUntilElementIsDisplayed(txtFirstClusterItem);
		txtFirstClusterItem.click();
		setText(txtFirstClusterItem, value);
	} catch (Exception e) {
		throw new Exception("setTextFirstClusterItem is not working.." + e);
	}
}
public void setTextSecondClusterItem(String value) throws Exception {
	try {
		waitUntilElementIsDisplayed(txtSecondClusterItem);
		txtSecondClusterItem.click();
		setText(txtSecondClusterItem, value);
	} catch (Exception e) {
		throw new Exception("setTextSecondClusterItem is not working.." + e);
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
public void clickOnFirstBubble() throws Exception {
	try {
		waitUntilElementIsDisplayed(firstBubble);
		Actions builder = new Actions(driver);
		builder.click(firstBubble).build().perform();
	} catch (Exception ex) {
		throw new Exception("clickOnFirstBubble is not working" + ex);
	}
}
}