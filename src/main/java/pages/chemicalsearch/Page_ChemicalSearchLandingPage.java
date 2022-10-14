package pages.chemicalsearch;

import java.awt.Robot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import support.Controller;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Rashmi/Anand
 *
 */
public class Page_ChemicalSearchLandingPage extends Controller {

	@FindBy(xpath = "//div[3]/section[2]/div[2]/button[2]")
	private WebElement btnClearAll;
	
	@FindBy(css = "div:nth-child(1) > button:nth-child(2) > span > mat-icon")
	private WebElement personIcon;	
	
	@FindBy(xpath = "//div/div[1]/div[3]/section[2]/div[2]/button[2]")
	private WebElement organizationIcon;

	@FindBy(xpath = "//app-chem-pill-input-modal/section/div/button")
	private WebElement btnAdd;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Enter name')]")
	private WebElement txtCompanyPerson;
	
	
	@FindBy(xpath = "//section[1]/div/section[2]/div[1]/button[1]")
	private WebElement btn_ClearX;
	
	@FindBy(css = "div:nth-child(2) > button:nth-child(3) > span > span")
	private WebElement closeIcon;
	
	//section[2]/app-page-home/div/section/app-structure-search/div/section[2]/div[1]/button[1]/span
	@FindBy(css = "span:nth-child(3) > button > span > img")
	private WebElement rsFooterSaveIcon;

	@FindBy(xpath = "//section[2]/div[1]/button[3]")
	private WebElement btnSearchIconStatus;

	@FindBy(css = "#mat-menu-panel-2 > div > button:nth-child(3) > span")
	private WebElement showTipsTxt;

	@FindBy(css = "span > span > mat-icon")
	private WebElement btnSearchIcon;

	@FindBy(xpath = "//textarea[@data-placeholder='Enter keywords, phrases or text blocks to search...']")
	private WebElement txtSearchBox;

	@FindBy(xpath = "//*[@id='mat-hint-0']/span")
	private WebElement txtLimitMsg;

	@FindBy(xpath = "//button[contains(@title,'Feedback')]")
	private WebElement linkFeedback;

	@FindBy(xpath = "//mat-dialog-container[contains(@role,'dialog')]")
	private WebElement dialogBoxFeedback;

	@FindBy(xpath = "//div/span[contains(text(),'Choose a topic')]")
	private WebElement fieldDropdown;

	@FindBy(xpath = "//span[contains(text(),' Submit feedback ')]/parent::button")
	private WebElement btn_SubmitFeedback;

	@FindBy(xpath = "//button/span[contains(text(),'Contact us')]")
	private WebElement link_ContactUs;

	@FindBy(xpath = "//textarea[contains(@data-placeholder,'How can we improve?')]")
	private WebElement feedback_Textarea;

	@FindBy(css = "#mat-slide-toggle-1-input")
	private WebElement supportTipsOnOff;

	@FindBy(css = "button:nth-child(4) > span")
	private WebElement supportLink;
	
	@FindBy(xpath = "//*[@id='Path']")
	private WebElement structureSearchIcon;
	
	@FindBy(xpath = "//input[@placeholder='Enter a name or SMILES to generate a structure']")
	private WebElement txtSmiley;
	
	@FindBy(xpath = "(//span[contains(@class,'mat-option-text')])[1]")
	private WebElement firstStructureName;
	
	@FindBy(xpath = "//mat-radio-button[@role='radio'  and @value='substructure']")
	private WebElement radioBtnSubStructure;
	
	@FindBy(xpath = "//mat-radio-button[@role='radio'  and @value='exact']")
	private WebElement radioBtnExactStructure;
	
	@FindBy(css = "div:nth-child(2) > h3")
	private WebElement structureLable;
	
	@FindBy(xpath = "//section/section[1]/div[2]/div")
	private WebElement chemicalNameSearchTab;
	
	@FindBy(xpath = "//section/section[2]/section[2]/div/div[1]/input")
	private WebElement chemicalNameSearchTxtBox;
	
	@FindBy(xpath = "//mat-option[1]/span/div")
	private WebElement chemicalNameSearchFirstItem;
	
	@FindBy(xpath = "//mat-dialog-actions/button[2]")
	private WebElement ChemicalNameApplyBtn;
	

	@FindBy(xpath = "//app-structure-search-modal/div/div[1]/h2")
	private WebElement modifyChemicalNameLabel;
	

	@FindBy(xpath = "//section[1]/button[2]")
	private WebElement linkHistory;
	

	public Page_ChemicalSearchLandingPage(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}

	public void clickOnButtonClearAll() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnClearAll);
			jsClick(btnClearAll);
		} catch (Exception ex) {
			throw new Exception("clickOnButtonClearAll is not working" + ex);
		}
	}

	public boolean isDisplayedButtonClearAll() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnClearAll);
			return controller.isElementDisplayed(btnClearAll);
		} catch (Exception e) {
			return false;

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

	public void setTextSearchTextBox(String value) throws Exception {
		try {
			waitUntilElementIsDisplayed(txtSearchBox);
			controller.waitTime(2);
			click(txtSearchBox);
			controller.waitTime(2);
			setText(txtSearchBox, value);
		} catch (Exception e) {
			throw new Exception("setTextSearchTextBox is not working.." + e);
		}
	}

	public String getTextSearchTextBox() throws Exception {
		try {
			String expMsg = "";
			waitUntilElementIsDisplayed(txtSearchBox);
			if (controller.isElementDisplayed(txtSearchBox))
				expMsg = controller.getText(txtSearchBox);
			return expMsg;
		} catch (Exception e) {
			throw new Exception("getTextSearchTextBox is not working.." + e);
		}
	}

	public void deleteTextSearchTextBox() throws Exception {
		try {
			WebElement txtArea = driver.findElement(By.xpath("//textarea"));
			txtArea.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
		} catch (Exception e) {
			throw new Exception("deleteTextSearchTextBox is not working.." + e);
		}
	}

	public void copyAndPasteTextSearchTextBox() throws Exception {
		try {
			WebElement txtArea = driver.findElement(By.xpath("//textarea"));
			txtArea.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			txtArea.sendKeys(Keys.chord(Keys.CONTROL, "c"));
			deleteTextSearchTextBox();
			controller.waitTime(2);
			txtArea.sendKeys(Keys.chord(Keys.CONTROL, "v"));
		} catch (Exception e) {
			throw new Exception("copyAndPasteTextSearchTextBox is not working.." + e);
		}
	}

	public String getTextLimitErrorMessage() throws Exception {
		try {
			String expMsg = "";
			if (controller.isElementDisplayed(txtLimitMsg))
				expMsg = controller.getText(txtLimitMsg);
			return expMsg;
		} catch (Exception e) {
			throw new Exception("getTextLimitErrorMessage is not working.." + e);
		}

	}

	public boolean isDisabledSearchIcon() throws Exception {
		boolean searchIcon = false;
		try {
			waitUntilElementIsDisplayed(btnSearchIconStatus);
			String iconStatus = getElementAttribute(btnSearchIconStatus, "disabled");
			searchIcon = Boolean.parseBoolean(iconStatus);
		} catch (Exception e) {
			throw new Exception("isDisabledSearchIcon is not working" + e);
		}
		return searchIcon;
	}

	public void clickOnLinkFeedback() throws Exception {
		try {
			super.waitUntilElementIsDisplayed(linkFeedback);
			super.jsClick(linkFeedback);
			waitUntilElementIsDisplayed(dialogBoxFeedback);
		} catch (Exception ex) {
			throw new Exception("clickOnSearchIcon is not working" + ex);
		}
	}

	public boolean isDisplayedDialogBoxFeedback() throws Exception {
		try {
			return controller.isElementDisplayed(dialogBoxFeedback);

		} catch (Exception ex) {
			throw new Exception("clickOnlink Filters is not working" + ex);
		}
	}

	public boolean isDisplayedFieldDropdown() throws Exception {
		try {

			return controller.isElementDisplayed(fieldDropdown);

		} catch (Exception ex) {
			throw new Exception("dropdown section is not displayed" + ex);
		}
	}

	public boolean isDisplayedButtonSubmitFeedback() throws Exception {
		try {
			return controller.isElementDisplayed(btn_SubmitFeedback);

		} catch (Exception ex) {
			throw new Exception("button submit feedback is not displayed" + ex);
		}
	}

	public boolean isDisplayedLinkContactUs() throws Exception {
		try {

			return controller.isElementDisplayed(link_ContactUs);

		} catch (Exception ex) {
			throw new Exception("link contact us is not displayed" + ex);
		}
	}

	public void clickOnButtonSubmitFeedback() throws Exception {
		try {
			controller.jsClick(btn_SubmitFeedback);
			super.waitUntilElementIsNotDisplayed(dialogBoxFeedback);
		} catch (Exception ex) {
			throw new Exception("clickOnButtonSubmitFeedback is not working" + ex);
		}
	}

	public void clickOnFeedbackDropdownToChooseTopic() throws Exception {
		try {
			super.waitUntilElementIsDisplayed(fieldDropdown);
			controller.jsClick(fieldDropdown);
		} catch (Exception ex) {
			throw new Exception("clickOnFeedbackDropdownToChooseTopic is not working" + ex);
		}
	}

	public void selectFeedbackValueFromDropdown(String value) throws Exception {
		try {
			clickOnFeedbackDropdownToChooseTopic();
			WebElement feedbackValue = driver.findElement(By.xpath("//span[contains(text(),'" + value + "')]"));
			controller.jsClick(feedbackValue);
		} catch (Exception ex) {
			throw new Exception("selectFeedbackValueFromDropdown is not working" + ex);
		}
	}

	public void enterValueInFeedbackArea(String feedbackValue) throws Exception {
		try {
			super.click(feedback_Textarea);
			setText(feedback_Textarea, feedbackValue);
		} catch (Exception e) {
			throw new Exception("not able to enterValueInFeedbackArea" + e);
		}
	}

	public void setSearchText(String value) throws Exception {
		try {
			super.click(txtSearchBox);
			setText(txtSearchBox, value);
		} catch (Exception e) {
			throw new Exception("setSearchText is not working.." + e);
		}

	}

	public String getSearchText() throws Exception {
		try {
			String expMsg = "";
			if (controller.isElementDisplayed(txtLimitMsg))
				expMsg = controller.getText(txtLimitMsg);
			return expMsg;
		} catch (Exception e) {
			throw new Exception("setSearchText is not working.." + e);
		}
	}

	public String getTextShowTips() throws Exception {
		try {
			String expMsg = "";
			waitUntilElementIsDisplayed(showTipsTxt);
			if (controller.isElementDisplayed(showTipsTxt))
				expMsg = controller.getText(showTipsTxt);
			return expMsg;
		} catch (Exception e) {
			throw new Exception("getTextShowTips is not working.." + e);
		}
	}

	public void clickOnLinkSupportMenu() throws Exception {
		try {
			waitUntilElementIsDisplayed(supportLink);
			jsClick(supportLink);
			controller.waitUntilFectchRecordProgressBarToDisappears();
		} catch (Exception ex) {
			throw new Exception("clickOnSupportLink is not working" + ex);
		}
	}

	public void setToolTipsOptionON() throws Exception {
		try {
			boolean toolTip = false;
			waitUntilElementIsDisplayed(supportTipsOnOff);
			String toolTipStatus = controller.getElementAttribute(supportTipsOnOff, "aria-checked");
			toolTip = Boolean.parseBoolean(toolTipStatus);
			if (!toolTip) {
				jsClick(supportTipsOnOff);
			} else {
				controller.Logger.Logger.log(LogStatus.PASS, "Support tool tip is already set ON");
			}
		} catch (Exception ex) {
			throw new Exception("setToolTipsOptionON is not working" + ex);
		}
	}

	public void setToolTipsOptionOFF() throws Exception {
		try {
			boolean toolTip = true;
			waitUntilElementIsDisplayed(supportTipsOnOff);
			String toolTipStatus = controller.getElementAttribute(supportTipsOnOff, "aria-checked");
			toolTip = Boolean.parseBoolean(toolTipStatus);
			if (toolTip) {
				jsClick(supportTipsOnOff);
			} else {
				controller.Logger.Logger.log(LogStatus.PASS, "Support tool tip is already set OFF");
			}
		} catch (Exception ex) {
			throw new Exception("setToolTipsOptionOFF is not working" + ex);
		}
	}
	
	public void clickOnStructureSearchIcon() throws Exception {
		try {
			WebElement structureicon=driver.findElement(By.cssSelector("polygon[fill='#F7F7F7']"));
			waitUntilElementIsDisplayed(structureicon);
			structureicon.click();
			controller.waitTime(1);
		} catch (Exception ex) {
			throw new Exception("clickOnStructureSearchIcon is not working" + ex);
		}
	}
	
	public void selectRadioButtonSubstructure() throws Exception {
		try {
			String status = controller.getElementAttribute(radioBtnSubStructure, "class");
			if(!status.contains("mat-radio-checked")) {
				radioBtnSubStructure.click();
			}
			else
				controller.Logger.addsubStep(LogStatus.INFO, "Sub Structure radio button is Already selected", false);
			}
		catch(Exception ex){
		throw new Exception("selectRadioButtonSubstructure is not working "+ex);
		}
	}
	
	
	public void selectRadioButtonExactStructure() throws Exception {
		try {
			String status = controller.getElementAttribute(radioBtnExactStructure, "class");
			if(!status.contains("mat-radio-checked")) {
				radioBtnExactStructure.click();
			}
			else
				controller.Logger.addsubStep(LogStatus.INFO, "Exact Structure radio button is Already selected", false);
			}
		catch(Exception ex){
		throw new Exception("selectRadioButtonExactStructure is not working "+ex);
		}
	}
	
	public void selectStructureRadioButton(String structureName) throws Exception {
		try
		{
		if(structureName.trim().equalsIgnoreCase("Substructure"))
			selectRadioButtonSubstructure();
		else
			selectRadioButtonExactStructure();
		}catch(Exception ex){
			throw new Exception("selectStructureRadioButton is not working "+ex);
			}
		}

	public void setTextSmileyTextBox(String value) throws Exception {
		try {
			waitUntilElementIsDisplayed(txtSmiley);
			controller.waitTime(2);
			click(txtSmiley);
			setText(txtSmiley, value);
			controller.waitTime(10);
			Actions action = new Actions(driver);
			action.moveToElement(firstStructureName).click().build().perform();
			controller.waitTime(6);
			} catch (Exception e) {
			throw new Exception("setTextSmileyTextBox is not working.." + e);
		}
	}
	public boolean isDisplayedStructureSearchBoxWithWaterMark() throws Exception {
		try {
		    boolean status=controller.isElementDisplayed(driver.findElement(By.xpath("//div/input[contains(@placeholder,'Enter a name or SMILES to generate a structure')]")));
			return status;	
		 }catch (Exception e) {
		return false;
		}
	}
	public boolean isDisplayedButtonClearX() throws Exception {
		try {
			return controller.isElementDisplayed(btn_ClearX);

		} catch (Exception ex) {
			throw new Exception("button ClearX is not displayed" + ex);
		}
	}
	public void setSmileyTextBox(String value) throws Exception {
		try {
			waitUntilElementIsDisplayed(txtSmiley);
			click(txtSmiley);
			setText(txtSmiley, value);
			} catch (Exception e) {
			throw new Exception("setSmileyTextBox is not working.." + e);
		}
	}
	public void clickOnButtonX() throws Exception {
		try {
			waitUntilElementIsDisplayed(btn_ClearX);
			jsClick(btn_ClearX);
		} catch (Exception ex) {
			throw new Exception("clickOnButtonX is not working" + ex);
		}
	}
	
	public void clickOnCloseIcon() throws Exception {
		try {
			System.out.println("test");
			//WebElement ele = driver.findElement(By.xpath("//section[2]/div[2]/button[3]/span/span"));
			//jsClickforReskinningElements(ele);
			waitUntilElementIsDisplayed(closeIcon);
			jsClick(closeIcon);
			//jsClickforReskinningElements(closeIcon);
		} catch (Exception ex) {
			throw new Exception("clickOnCloseIcon is not working" + ex);
		}
	}
	
	
	public String getTextStructureSearchLable() throws Exception {
		try {
			String expMsg = "";
			if (controller.isElementDisplayed(structureLable))
				expMsg = controller.getText(structureLable);
			return expMsg;
		} catch (Exception e) {
			throw new Exception("getTextStructureSearchLable is not working.." + e);
		}
	}
	public void clickOnPersonIcon() throws Exception {
		try {
			waitUntilElementIsDisplayed(personIcon);
			personIcon.click();
			} catch (Exception ex) {
			throw new Exception("clickOnPersonIcon is not working" + ex);
		}
	}
	
	
	public void clickOnOrganizationIcon() throws Exception {
		try {
		//	waitUntilElementIsDisplayed(organizationIcon);
			organizationIcon.click();
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
	
	
	@SuppressWarnings("static-access")
	public List<String> getAllPersonsOrganizations() throws Exception{
		try {
			
 			List<String> Labels =new ArrayList<String>();
 				for(int i=1;i<=4;i++)
 				{
 					WebElement element = controller.driver.findElement(By.xpath("//div/mat-form-field/div/div[1]/div/mat-chip-list/div/mat-chip["+i+"]/div[2]/span[2]"));
 					String Label = controller.getText(element);
 					Labels.add(Label); 					
  				}
 			
		return Labels;
     	}catch (Exception e) {
     		throw new Exception("getAllPersonsOrganizationsRSPage is not working" + e);
     	}
	}

	
	@SuppressWarnings("static-access")
	public List<String> getAllPersonsOrganizationsStructureSearch() throws Exception{
		try {
			
 			List<String> Labels =new ArrayList<String>();
 				for(int i=1;i<=5;i++)
 				{
 					WebElement element = controller.driver.findElement(By.xpath("//div/mat-form-field/div/div[1]/div/mat-chip-list/div/mat-chip["+i+"]/div[2]/span[2]"));
 					String Label = controller.getText(element);
 					Labels.add(Label); 					
  				}
 			
		return Labels;
     	}catch (Exception e) {
     		throw new Exception("getAllPersonsOrganizationsRSPage is not working" + e);
     	}
	}
	
	
	public void clickOnTabChemicalNameSearch() throws Exception {
		try {
			waitUntilElementIsDisplayed(chemicalNameSearchTab);
			jsClick(chemicalNameSearchTab);
			} catch (Exception ex) {
			throw new Exception("clickOnTabChemicalNameSearch is not working" + ex);
		}
	}	

	public void setTextChemicalNameSearchTextBox(String value) throws Exception {
		try {
			waitUntilElementIsDisplayed(chemicalNameSearchTxtBox);
			click(chemicalNameSearchTxtBox);
			setText(chemicalNameSearchTxtBox, value);
			Actions action = new Actions(driver);
			action.moveToElement(chemicalNameSearchFirstItem).click().build().perform();
			controller.waitTime(2);
			} catch (Exception e) {
			throw new Exception("setTextChemicalNameSearchTextBox is not working.." + e);
		}
	}
	
	public void clickOnButtonApply() throws Exception {
		try {
			waitUntilElementIsDisplayed(ChemicalNameApplyBtn);
			click(ChemicalNameApplyBtn);
			} catch (Exception ex) {
			throw new Exception("clickOnButtonApply is not working" + ex);
		}
	}
	
	public void clickOnPillBox(int pillNumber) throws Exception {
		try 
		{
			WebElement ele=driver.findElement(By.xpath("(//div[@class='cdx-chip-block']//span[@class='cdx-chip-name'])["+pillNumber+"]"));
		    ele.click();
		 }
		catch (Exception e) 
		 {
			throw new Exception("clickOnPillBox is not working.." + e);
		 }
	}
	
	
	public String getTextModifyChemicalTitle() throws Exception {
		try {
			String expMsg = "";
			waitUntilElementIsDisplayed(modifyChemicalNameLabel);
			if (controller.isElementDisplayed(modifyChemicalNameLabel))
				expMsg = controller.getText(modifyChemicalNameLabel);
			return expMsg;
		} catch (Exception e) {
			throw new Exception("getTextModifyChemicalTitle is not working.." + e);
		}
	}
	
	
	public void clickOnPersonOrgPillBox(int pillNumber) throws Exception {
		try 
		{
			WebElement ele=driver.findElement(By.xpath("//div/div[1]/div/mat-chip-list/div/mat-chip["+pillNumber+"]"));
		    ele.click();
		 }
		catch (Exception e) 
		 {
			throw new Exception("clickOnPersonOrgPillBox is not working.." + e);
		 }
	}
	
	public String getColorOfPersonOrgPillBox(int pillNumber) throws Exception {
		try 
		{
		WebElement ele=driver.findElement(By.xpath("//div/div[1]/div/mat-chip-list/div/mat-chip["+pillNumber+"]"));
		  return ele.getAttribute("class");
		 }catch (Exception e) {
		 throw new Exception("getColorOfPersonOrgPillBox is not working.." + e);
			}
		}
		 
	
	public void clickOnLinkHistory() throws Exception {
		try {
			waitUntilElementIsDisplayed(linkHistory);
			click(linkHistory);
			} catch (Exception ex) {
			throw new Exception("clickOnLinkHistory is not working" + ex);
		}
	}
	
	
	public void clickOnLabel() throws Exception {
	try {
	WebElement element = driver.findElement(By.cssSelector("h1.ng-star-inserted"));
	Actions actions = new Actions(driver);
	actions.moveToElement(element).click().build().perform();
	controller.waitTime(3);
	} catch (Exception e) {
	throw new Exception("clickOnLabel is not working on " + e);
	}
	}


		
	}




