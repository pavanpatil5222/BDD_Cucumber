package pages.chemicalsearch;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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
 * @author Anand/Rashmi
 *
 */
public class Page_ChemicalSearchLandingPage extends Controller {

	@FindBy(xpath = "(//span[contains(.,'close')])[2]")
	private WebElement btnClearAll;
	
	@FindBy(css = "button:nth-child(2) > span > mat-icon")
	private WebElement personIcon;
	
	
	
	@FindBy(css = "div.bottom-icons > button:nth-child(1) > span > mat-icon")
	private WebElement organizationIcon;

	@FindBy(css = "section.add-button > button")
	private WebElement btnAdd;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Enter name')]")
	private WebElement txtCompanyPerson;
	
	
	@FindBy(xpath = "//section[2]/app-page-home/div/section/app-structure-search/div/section[2]/div[1]/button[1]/span")
	private WebElement btn_ClearX;

	@FindBy(css = "span:nth-child(3) > button > span > img")
	private WebElement rsFooterSaveIcon;

	@FindBy(xpath = "//div[1]/div[3]/button[2]")
	private WebElement btnSearchIconStatus;

	@FindBy(css = "#cdk-overlay-0 > div > div > button:nth-child(3) > span")
	private WebElement showTipsTxt;

	@FindBy(xpath = "//button//span/span/mat-icon[@class ='mat-icon notranslate material-icons mat-icon-no-color']")
	private WebElement btnSearchIcon;

	@FindBy(xpath = " //textarea[@placeholder='Enter keywords, phrases or text blocks to search...']")
	private WebElement txtSearchBox;

	@FindBy(xpath = "//*[@id='mat-hint-0']/span")
	private WebElement txtLimitMsg;

	@FindBy(xpath = "//button[contains(@title,'Feedback')]")
	private WebElement linkFeedback;

	@FindBy(xpath = "//mat-dialog-container[contains(@role,'dialog')]")
	private WebElement dialogBoxFeedback;

	@FindBy(xpath = "//mat-select[contains(@placeholder,'Choose a topic')]/parent::div")
	private WebElement fieldDropdown;

	@FindBy(xpath = "//span[contains(text(),' Submit feedback ')]/parent::button")
	private WebElement btn_SubmitFeedback;

	@FindBy(xpath = "//a[contains(@class,'contact-us')]")
	private WebElement link_ContactUs;

	@FindBy(xpath = "//textarea[contains(@placeholder,'How can we improve?')]")
	private WebElement feedback_Textarea;

	@FindBy(css = "#mat-slide-toggle-1-input")
	private WebElement supportTipsOnOff;

	@FindBy(css = "div.links > button:nth-child(5) > span")
	private WebElement supportLink;
	
	@FindBy(css = "span > mat-icon > svg")
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
			click(txtSearchBox);
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
			waitUntilElementIsDisplayed(structureSearchIcon);
			structureSearchIcon.click();
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
			click(txtSmiley);
			setText(txtSmiley, value);
			Actions action = new Actions(driver);
			action.moveToElement(firstStructureName).click().build().perform();
			controller.waitTime(2);
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
			waitUntilElementIsDisplayed(organizationIcon);
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
 					WebElement element = controller.driver.findElement(By.cssSelector("mat-chip:nth-child("+i+") > div.cdx-chip-block > span > span.cdx-chip-name"));
 					String Label = controller.getText(element);
 					Labels.add(Label); 					
  				}
 			
		return Labels;
     	}catch (Exception e) {
     		throw new Exception("getAllPersonsOrganizations is not working" + e);
     	}
	}
	
	

}


