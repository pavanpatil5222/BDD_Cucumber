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
		
		@FindBy(xpath = "//*[@id='chemexp']//section[2]//button[1]")
		private WebElement btnClearAll;
			
		@FindBy(xpath = "//*[@id='chemexp']/section[2]//div[3]/button")
		private WebElement btnSearchIconStatus;
		
		@FindBy(xpath = "//*[@id='chemexp']/section[2]//div[3]/button[2]")
		private WebElement btnSearchIcon;
		
		@FindBy(xpath = "//textarea[@id='mat-input-0']")
		private WebElement txtSearchBox;
						
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
		
		public void enterTextAnywhereInSearchBox(String typeText,int positionToTypeText) {

			 

			WebElement textarea=driver.findElement(By.xpath("//textarea[contains(@placeholder,'Enter keywords, phrases or text blocks to search...')]"));;
	 

	        insert(textarea, typeText, positionToTypeText);
	    }
	    
	    public void insert(WebElement textElement, String insertText, int offset) {
	        Robot robot = null; 
	        String currentText = textElement.getAttribute("formcontrolname");
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

}

