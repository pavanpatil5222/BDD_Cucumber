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
import support.Controller;
/**
 * 
 * @author Anand/Rashmi
 *
 */
	public class Page_ChemicalSearchLandingPage extends Controller{
		
		@FindBy(xpath = "(//span[contains(.,'close')])[2]")
		private WebElement btnClearAll;
			
		@FindBy(xpath = "//*[@id='chemexp']/section[2]//div[3]/button")
		private WebElement btnSearchIconStatus;
		
		@FindBy(xpath = "//*[@id='chemexp']/section[2]//div[3]/button[2]")
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
				return true;
				
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
				String expMsg="";
				waitUntilElementIsDisplayed(txtSearchBox);
				if(controller.isElementDisplayed(txtSearchBox))
				expMsg= controller.getText(txtSearchBox);
				return expMsg;
				} catch (Exception e) {
				throw new Exception("getTextSearchTextBox is not working.." + e);
			}
		}
		
		public void deleteTextSearchTextBox() throws Exception {
			try {
			WebElement txtArea = driver.findElement(
			By.xpath("//textarea"));
			txtArea.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
				} catch (Exception e) {
				throw new Exception("deleteTextSearchTextBox is not working.." + e);
			}
		}
		
		public void copyAndPasteTextSearchTextBox() throws Exception {
			try {
			WebElement txtArea = driver.findElement(
			By.xpath("//textarea"));
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
				String expMsg="";
				if(controller.isElementDisplayed(txtLimitMsg))
				expMsg= controller.getText(txtLimitMsg);
				return expMsg;
				} catch (Exception e) {
				throw new Exception("getTextLimitErrorMessage is not working.." + e);
			}
			
		}
		
		public void enterTextAnywhereInSearchBox(String typeText,int positionToTypeText) {

			 

			WebElement textarea=driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter keywords, phrases or text blocks to search...')]"));;
	 

	        insert(textarea, typeText, positionToTypeText);
	    }
	    
	    public void insert(WebElement textElement, String insertText, int offset) {
	    	Robot robot = null; 
	        String currentText = textElement.getAttribute("innerh");
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
	
		public boolean isDisabledSearchIcon() throws Exception{
			boolean searchIcon = false;
			try {
				waitUntilElementIsDisplayed(btnSearchIconStatus);
				String iconStatus = getElementAttribute(btnSearchIconStatus, "disabled");
	  			searchIcon = Boolean.parseBoolean(iconStatus);
	     	}catch (Exception e) {
	     		throw new Exception("isDisabledSearchIcon is not working"+e);
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
				WebElement feedbackValue = driver.findElement(By.xpath("//span[contains(text(),'"+value+"')]"));
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
					String expMsg="";
					if(controller.isElementDisplayed(txtLimitMsg))
					expMsg= controller.getText(txtLimitMsg);
					return expMsg;
					} catch (Exception e) {
					throw new Exception("setSearchText is not working.." + e);
				}
			}
		
}

