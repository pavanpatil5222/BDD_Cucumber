package pages.chemicalsearch;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
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
	
	@FindBy(xpath = "//app-keyword-search/section/div[3]/button[2]/span/mat-icon")
	private WebElement btnClearAll;
		
	@FindBy(xpath = "//mat-icon[contains(.,'search')]")
	private WebElement btnSearchIcon;
	
	@FindBy(xpath = "//*[@id='mat-chip-list-0']/div/mat-chip[1]")
	private WebElement keyWordMouseHover;
	
	@FindBy(css = "#mat-chip-list-input-0")
	private WebElement txtKeyWord;
	
	@FindBy(xpath="(//span[@class='cdx-chip-name'])[3]")
	private WebElement KeywordPillText;
	
	@FindBy(xpath="(//img[@role='img'])[3]")
	private WebElement thumsUpIcon;
	
	@FindBy(xpath="(//img[@role='img'])[4]")
	private WebElement thumbsDownIcon;
			

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
	
	public Tab_PatentSearch tabPatentSearch() {
		return new Tab_PatentSearch(controller);
	}
	
	public Tab_LiteratureSearch tabLiteratureSearch() {
		return new Tab_LiteratureSearch(controller);
	}	
	
}

