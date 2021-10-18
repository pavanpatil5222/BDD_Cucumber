package pages.chemicalsearch;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
 * @author Rashmi//Anand
 *
 */

public class Page_FilterChemicalStructure extends Controller {


	@FindBy(xpath = "//div[@class='mat-card-header-text']//mat-checkbox//label//div/input")
	private WebElement singleStructureCheckbox;
	
	@FindBy(xpath = "//button/span[contains(.,' Apply filters ')]")
	private WebElement btnApplyFilters;
	
	@FindBy(xpath = "//div[@class='structure-footer']//mat-checkbox//label//div/input")
	private WebElement globalStructureCheckbox;
	
	@FindBy(xpath = "//app-result-paginator-bar//section//span[contains(@class , 'modal-global-chkbox-text ng-star-inserted')]")
	private WebElement structureRecordSelection;

	@FindBy(xpath="//app-filter-structure-search-modal/div/div[2]/app-result-paginator-bar/section/section[2]/mat-paginator/div/div/div[2]/button[2]")
	private WebElement arrowNextPage;
	
	
	
	@FindBy(xpath="//app-filter-structure-search-modal/div/div[2]/app-result-paginator-bar/section/section[2]/mat-paginator/div/div/div[2]/button[1]")
	private WebElement arrowPreviousPage;
	
	
	
	@FindBy(xpath ="//div[2]//mat-select[contains(@aria-label,'Items per page:')]")
	WebElement dropdown_ItemsPerPage;
	
	
	public Page_FilterChemicalStructure(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnSingleStructure() throws Exception {
		try {
			waitUntilElementIsDisplayed(singleStructureCheckbox);
			String status = controller.getElementAttribute(singleStructureCheckbox, "aria-checked");
			if (status.equals("false")) {
				jsClick(singleStructureCheckbox);

			} else {
				controller.Logger.Logger.log(LogStatus.INFO, "Check Box is already selected");
			}
			
			} catch (Exception ex) {
			throw new Exception("clickOnSingleStructure is not working" + ex);
		}
	}
		public void clickOnButtonApplyFilters() throws Exception {
			try {
				waitUntilElementIsDisplayed(btnApplyFilters);
				jsClick(btnApplyFilters);
				} catch (Exception ex) {
				throw new Exception("clickOnButtonApplyFilters is not working" + ex);
			}
	}
		
		public void clickOnGlobalStructureCheckbox() throws Exception {
			try {
				waitUntilElementIsDisplayed(globalStructureCheckbox);
				String status = controller.getElementAttribute(globalStructureCheckbox, "aria-checked");
				if (status.equals("mixed")) {
					jsClick(globalStructureCheckbox);

				} else {
					controller.Logger.Logger.log(LogStatus.INFO, "Footer Global Check Box is already selected");
				}
				
				} catch (Exception ex) {
				throw new Exception("clickOnGlobalStructureCheckbox is not working" + ex);
			}
		}
		
		
		public String checkGlobalStructureCheckboxState() throws Exception {
			try {
				String iconStatus;
				waitUntilElementIsDisplayed(globalStructureCheckbox);
				iconStatus = getElementAttribute(globalStructureCheckbox, "aria-checked");
				return (iconStatus);
			} catch (Exception ex) {
				throw new Exception("checkGlobalStructureCheckboxState is not working" + ex);
			}
		}
		public String getStructureTextRecordSelection() throws Exception {
			try {
				controller.waitTime(2);
				return controller.getText(structureRecordSelection);

			} catch (Exception ex) {
				throw new Exception("getStructureTextRecordSelection is not working" + ex);
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
		
		public void clickOnHalfFilledGlobalCheckBox() throws Exception {
			try {
				waitUntilElementIsDisplayed(globalStructureCheckbox);
				String status = controller.getElementAttribute(globalStructureCheckbox, "aria-checked");
				if (status.equalsIgnoreCase("mixed")) {
					jsClick(globalStructureCheckbox);

				} else {
					controller.Logger.Logger.log(LogStatus.INFO, "RS Footer Global Check Box is already selected");
				}
			} catch (Exception ex) {
				throw new Exception("clickOnHalfFilledGlobalCheckBox is not working" + ex);
			}
		}
		
		public void clickOnItemsPerPageDropDown() throws Exception {
			try {
				controller.waitTime(2);
				this.waitUntilElementIsDisplayed(dropdown_ItemsPerPage);
				super.jsClick(dropdown_ItemsPerPage);
				controller.waitTime(2);
			} catch (Exception ex) {
				throw new Exception("clickOn ItemsPerPagedropdown is not working" + ex);
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
}


