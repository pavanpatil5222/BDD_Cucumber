package pages.chemicalsearch;

import java.awt.AWTExceptio;
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
 * @author Rashmi/Anand
 *
 */

public class Page_SavedRecords extends Controller {

	@FindBy(xpath = "//section[@class='record ng-star-inserted']/section//mat-checkbox[@class='mat-checkbox mat-accent ng-untouched ng-pristine ng-valid']/label/div/input")
	private WebElement savedRecordsCheckbox;
	@FindBy(xpath="//mat-select[contains(@aria-label,'Items per page:')]")
    WebElement dropdown_ItemsPerPage;
	@FindBy(xpath = "//mat-select/div/div[1]/span/span")
	private WebElement value_Dropdown;
	
	@FindBy(xpath="//section[@class='new-folder ng-star-inserted']/div")
	private WebElement folderErrorMessage;
	@FindBy(xpath="//app-page-saved-records/section/section[2]/section/form/section/section[2]/section[1]/mat-checkbox/label/div/input")
	private WebElement globalCheckBox;
	
	@FindBy(xpath = "//section[2]/section/form/section/section[1]/section[1]/section[1]/section/div")
	private WebElement savedRecordsTitle;
	
	@FindBy(xpath = "//div/div/div[1]/div[2]")
	private WebElement recordViewTitle;
	
	@FindBy(xpath = "//section[@class='actions']/button[@title='Delete record(s)']")
	private WebElement savedRecordsDelete;
	
	@FindBy(xpath = "//section[2]/section/section/section/section/button[1]")
	private WebElement btnEdit;
	
	@FindBy(xpath = "//button[@title='Save']/span[contains(text(),'Save')]")
	private WebElement btnSave;

	@FindBy(xpath = "//span[contains(text(),'Cancel')]")
	private WebElement btnCancel;
	
	@FindBy(css = "button.cdx-but-md.cdx-but-link.mat-button.mat-button-base.mat-primary > span")
	private WebElement btnSavedRecords;
	
	@FindBy(xpath="//section[@class='paginator-bar bgwhite ng-star-inserted']//mat-checkbox/label/div")
	private WebElement globalSavedRecordsCheckBox;
	
	@FindBy(xpath="//section[@class='paginator-bar bgwhite ng-star-inserted']//section[@class='actions']/button/span")
	private WebElement globalSavedRecordsDelete;
	
	@FindBy(xpath="//section[@class='paginator-bar bgwhite ng-star-inserted']//section[@class='actions']/button/span")
	private WebElement getCountDeletedFolder;
	
	@FindBy(xpath = "//section[@class='saved-records']//span[contains(text(), 'Create a new folder')]/ancestor::button")
	private WebElement linkCreateNewFolder;

	@FindBy(xpath = "//app-page-saved-records/section/section[1]/section/section[2]/section/input")
	private WebElement txtFolderName;
	
	
	
	@FindBy(xpath = "//app-page-saved-records/section/section[2]/section/section/section/input")
	private WebElement editTxtFolderName;

	
	@FindBy(xpath = "//span[text() ='Create']")
	private WebElement btnCreate;
	
	@FindBy(css="button:nth-child(2) > span > mat-icon > svg")
	private WebElement btnDeleteFolder;
	
	@FindBy(css="mat-dialog-actions > button.cdx-but-xl.mat-flat-button.mat-button.mat-button-base.mat-primary")
	private WebElement btnDeleteFolderConfirmation;	
	
	@FindBy(xpath="//app-page-saved-records/section/section[1]/section/button[2]/span")
	private WebElement annotationFolderLink;
	
	@FindBy(xpath="//app-page-saved-records/section/section[2]/section/section/section/div")
	private WebElement annotationFolderCount;
	
	@FindBy(xpath="//app-page-saved-records/section/section[2]/section/form/section/section[1]/section/section[1]/section[1]/section[2]/button")
	private WebElement annotationIcon;
	
	@FindBy(xpath = "//app-result-set-annotation-modal/div/form/div/div/mat-dialog-actions/button[2]/span")
	private WebElement saveAnnotation;

	@FindBy(xpath = "//app-result-set-annotation-modal/div/form/div/div/mat-dialog-actions/button[1]/span")
	private WebElement deleteAnnotation;
	
	@FindBy(xpath = "//mat-form-field/div/div[1]/div[3]/textarea")
	private WebElement annotationTextArea;
	

	@FindBy(xpath = "//app-page-saved-records/section/section[2]/section/form/section/section[2]/section[1]/mat-checkbox")
	private WebElement annotationFolderGlobalChkBox;
	
	@FindBy(xpath = "//app-page-saved-records/section/section[2]/section/form/section/section[2]/section[1]/section[2]/button/span/mat-icon")
	private WebElement annotationGlobalDelete;
	
	
	@FindBy(xpath = "//app-confirmation-modal/div/div[2]/mat-dialog-actions/button[1]/span")
	private WebElement annotationConfirmation;
	
	

	public Page_SavedRecords(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}

	public void clickOnLinkSavedRecords() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnSavedRecords);
			jsClick(btnSavedRecords);
			controller.waitUntilFectchRecordProgressBarToDisappears();
		} catch (Exception ex) {
			throw new Exception("clickOnLinkSavedRecords is not working" + ex);
		}
	}

	public void clickOnLinkCreateNewFolder() throws Exception {
		try {
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

	public void setTextFolderName(String value) throws Exception {
		try {
			click(txtFolderName);
			txtFolderName.clear();
			setText(txtFolderName, value);
		} catch (Exception e) {
			throw new Exception("setTextFolderName is not working.." + e);
		}
	}
	
	public void setEditTextFolderName(String value) throws Exception {
		try {
			click(editTxtFolderName);
			editTxtFolderName.clear();
			setText(editTxtFolderName, value);
		} catch (Exception e) {
			throw new Exception("setTextFolderName is not working.." + e);
		}
	}

	public boolean isFolderDisplayed(String checkboxname) throws Exception {
		try {
			WebElement ele = controller.driver
					.findElement(By.xpath("//section[@class='saved-records']//span[contains(text(), '" + checkboxname + "')]/ancestor::button"));
			return isElementDisplayed(ele);
		} catch (Exception ex) {
			throw new Exception("isFolderDisplayed is not working" + ex);
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
	
	public void clickOnLinkSavedRecordsCreate() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnCreate);
			jsClick(btnCreate);
		} catch (Exception ex) {
			throw new Exception("clickOnLinkSavedRecordsCreate is not working" + ex);
		}
	}
	
	public void clickOnButtonEdit() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnEdit);
			jsClick(btnEdit);
		} catch (Exception ex) {
			throw new Exception("clickOnButtonEdit is not working" + ex);
		}
	}
	
	public void clickOnButtonSave() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnSave);
			jsClick(btnSave);
		} catch (Exception ex) {
			throw new Exception("clickOnButtonSave is not working" + ex);
		}
	}
	public void clickOnButtonCancel() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnCancel);
			jsClick(btnCancel);
		} catch (Exception ex) {
			throw new Exception("clickOnButtonCancel is not working" + ex);
		}
	}
	
	public void clickOnButtonDelete() throws Exception {
		try {
			waitUntilElementIsDisplayed(btnDeleteFolder);
			btnDeleteFolder.click();
			controller.waitTime(1);
			btnDeleteFolderConfirmation.click();
			controller.waitUntilFectchRecordProgressBarToDisappears();
		} catch (Exception ex) {
			throw new Exception("clickOnButtonDelete is not working" + ex);
		}
	}

	/*public void deleteExistingFolders() throws Exception{
		List<WebElement> folders = driver.findElements(By.xpath("//button[contains(@id,'btnFolder')]"));
		System.out.println("size" + folders.size());
		if (folders.size() > 0) {
			for (WebElement ele : folders) {
				btnDeleteFolder.click();
				controller.waitTime(1);
				btnDeleteFolderConfirmation.click();
				controller.waitUntilFectchRecordProgressBarToDisappears();
			}
			
		}
		
	}*/
	
	public void deleteExistingFolders() throws Exception{
        List<WebElement> folders = driver.findElements(By.xpath("//button[contains(@id,'btnFolder')]"));
        System.out.println("size" + folders.size());
        controller.Logger.Logger.log(LogStatus.INFO, +folders.size()+ "\t\t CURRENT FOLDER SIZE BEFORE THE DELETE"); 
        if (folders.size() > 0) {
               for (WebElement ele : folders) {
                      controller.waitTime(2);
                      btnDeleteFolder.click();
                      controller.waitTime(1);
                      btnDeleteFolderConfirmation.click();
                      controller.waitUntilFectchRecordProgressBarToDisappears();
              }
             }
        controller.Logger.Logger.log(LogStatus.INFO, +folders.size()+ "\t\t CURRENT FOLDER SIZE AFTER THE DELETE"); 
        
  }

	@SuppressWarnings("static-access")
	public String isSelectedCheckBox(int checkboxnum) throws Exception {
		try {
		WebElement ele = controller.driver.findElement(By.xpath("//app-page-saved-records/section/section[2]/section/form/section/section[1]/section/section["+checkboxnum+"]/section[1]/mat-checkbox"));
		String chk = controller.getElementAttribute(ele, "class");
		return chk;
	} catch (Exception ex) {
		throw new Exception("isSelectedCheckBox is not working" + ex);
	}
	}

public void clickOnSavedRecordsFooterGlobalCheckBox() throws Exception {
	try {
		waitUntilElementIsDisplayed(globalSavedRecordsCheckBox);
			jsClick(globalSavedRecordsCheckBox);	
	} catch (Exception ex) {
		throw new Exception("clickOnSavedRecordsFooterGlobalCheckBox is not working" + ex);
	}
}


public void clickOnGlobalSavedRecordsDelete() throws Exception {
	try {
		waitUntilElementIsDisplayed(globalSavedRecordsDelete);
			jsClick(globalSavedRecordsDelete);
			btnDeleteFolderConfirmation.click();
			controller.waitUntilFectchRecordProgressBarToDisappears();
	} catch (Exception ex) {
		throw new Exception("clickOnGlobalSavedRecordsDelete is not working" + ex);
	}
}


public void clickOnSavedRecordsDelete() throws Exception {
	try {
		waitUntilElementIsDisplayed(savedRecordsDelete);
			jsClick(savedRecordsDelete);
			btnDeleteFolderConfirmation.click();
			controller.waitUntilFectchRecordProgressBarToDisappears();
	} catch (Exception ex) {
		throw new Exception("clickOnSavedRecordsDelete" + ex);
	}
}

public void clickOnSavedRecordCheckBox() throws Exception {
	try {
		//waitUntilElementIsDisplayed(savedRecordsCheckbox);
			jsClick(savedRecordsCheckbox);
			controller.waitUntilFectchRecordProgressBarToDisappears();
	} catch (Exception ex) {
		throw new Exception("clickOnSavedRecordCheckbox is not working" + ex);
	}
}

	public String getCountAfterDeletedFolder(String checkboxname) throws Exception {
	try {
		WebElement ele = controller.driver
				.findElement(By.xpath("//section[@class='header']/div"));
		return getText(ele);
	} catch (Exception ex) {
		throw new Exception("getCountAfterDeletedFolder is not working" + ex);
	}
}
	
	public String getTitle() throws Exception {
		waitUntilElementIsDisplayed(savedRecordsTitle);
		return controller.getText(savedRecordsTitle);
	}

	public String getTitleRecordView() throws Exception {
		waitUntilElementIsDisplayed(recordViewTitle);
		return controller.getText(recordViewTitle);
	}
	
	public void clickOnTitle() throws Exception {
		try {
				jsClick(savedRecordsTitle);
				controller.waitUntilFectchRecordProgressBarToDisappears();
		} catch (Exception ex) {
			throw new Exception("clickOnTitle is not working" + ex);
		}
	}
	@SuppressWarnings("static-access")
	public int getFolderNameSize(String checkboxname) throws Exception {
		try {
			WebElement ele = controller.driver
					.findElement(By.xpath("//section[@class='saved-records']//span[contains(text(), '" + checkboxname + "')]/ancestor::button"));
			 return getText(ele).length();
		} catch (Exception ex) {
			throw new Exception("getFolderNameSize is not working" + ex);
		}
	}
	
	public void clickOnFolderName(int foldernumber) throws Exception {
		try {
			WebElement ele=	driver.findElement(By.xpath("//app-page-saved-records/section/section[1]/section/button["+foldernumber+"]/span"));
			jsClick(ele);
			controller.waitUntilFectchRecordProgressBarToDisappears();
			} catch (Exception ex) {
			throw new Exception("clickOnFolderName is not working" + ex);
		}
	}

	public int getFolderRecordCount() throws Exception {
	List<WebElement> folderRecordCount = driver.findElements(By.xpath("//*[@id='saved-records']/section[2]/section/form/section/section[1]/section/section[@fxlayout='row']"));
	return folderRecordCount.size();
	}
	
	public void clickOnItemsPerPageDropDown() throws Exception {
		try {
			super.jsClick(dropdown_ItemsPerPage);
			} catch (Exception ex) {
			throw new Exception("clickOnItemsPerPageDropDown is not working" + ex);
		}
	
}


public void clickOnFooterGlobalCheckBox() throws Exception {
	try {
		waitUntilElementIsDisplayed(globalCheckBox);
		String status = controller.getElementAttribute(globalCheckBox, "aria-checked");
		if(status.equals("false"))
		{
			jsClick(globalCheckBox);	
			
		}
		else
		{
			controller.Logger.Logger.log(LogStatus.INFO, "Footer Global Check Box is already selected");	
		}
	} catch (Exception ex) {
		throw new Exception("clickOnFooterGlobalCheckBox is not working" + ex);
	}
}


public String getValueFromItemsPerPageDropdown() throws Exception {
	try {
		return controller.getText(value_Dropdown);
		} catch (Exception ex) {
		throw new Exception("getValueFromItemsPerPageDropdown is not working" + ex);
	}
}

public void selectItemsPerPageFromDropDown(String itemValue) throws Exception {
	try {
		clickOnItemsPerPageDropDown();
        List<WebElement> listOfDropdownValue = driver.findElements(By.xpath("//div[3]/div[2]/div/div/div/mat-option/span"));
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

public void clickOnLinkAnnotationRecords() throws Exception {
	try {
		waitUntilElementIsDisplayed(annotationFolderLink);
		jsClick(annotationFolderLink);
		controller.waitUntilFectchRecordProgressBarToDisappears();
		} catch (Exception ex) {
		throw new Exception("clickOnLinkAnnotationFolder is not working" + ex);
	}

}


public String getTextAnnotationFolderCount() throws Exception {
	String annCount;
	 waitUntilElementIsDisplayed(annotationFolderCount);
	 annCount = getText(annotationFolderCount);
	 return(annCount);
	}


@SuppressWarnings("static-access")
public void clickOnFolderAnnotationIcon(int recordnumber) throws Exception {
	try {
		WebElement ele = controller.driver.findElement(By.xpath("//app-page-saved-records/section/section[2]/section/form/section/section[1]/section/section["+recordnumber+"]/section[1]/section[2]/button"));
		jsClick(ele);
	} catch (Exception ex) {
		throw new Exception("clickOnFolderAnnotationIcon is not working" + ex);
	}
}

public void clickOnButtonDeleteAnnotation() throws Exception {
	try {
		waitUntilElementIsDisplayed(deleteAnnotation);
		deleteAnnotation.click();
	} catch (Exception ex) {
		throw new Exception("deleteAnnotation is not working" + ex);
	}
}


public void deleteExistingAnnotations() throws Exception{
	try {
	String annotationFolderCount=getTextAnnotationFolderCount();
	if(annotationFolderCount.contains("0"))
	{
		controller.Logger.Logger.log(LogStatus.INFO, "NO RECORDS ARE PRESENT IN THE ANNOTATION FOLDER"); 
	}
	else
	{
		if(!annotationFolderGlobalChkBox.isSelected())
		{
			annotationFolderGlobalChkBox.click();
		}
		annotationGlobalDelete.click();
		annotationConfirmation.click();
		controller.waitUntilFectchRecordProgressBarToDisappears();
		annotationFolderCount=getTextAnnotationFolderCount();
		if(annotationFolderCount.contains("0"))
		{
			controller.Logger.Logger.log(LogStatus.INFO, "NO RECORDS ARE PRESENT IN THE ANNOTATION FOLDER"); 
		}
	}
    
}
 catch (Exception ex) {
	throw new Exception("deleteExistingAnnoations is not working" + ex);
}
}

@SuppressWarnings("static-access")
public void clickOnAnnotationIcon(int recordnumber) throws Exception {
	try {
		WebElement ele = controller.driver.findElement(By.xpath("//app-page-saved-records/section/section[2]/section/form/section/section[1]/section/section["+recordnumber+"]/section[1]/section[2]/button/span/mat-icon"));
		jsClick(ele);
	} catch (Exception ex) {
		throw new Exception("clickOnAnnotationIcon is not working" + ex);
	}
}

public void setTextAnnotationText(String value) throws Exception {
	try {
		waitUntilElementIsDisplayed(annotationTextArea);
		annotationTextArea.click();
		setText(annotationTextArea, value);
	} catch (Exception e) {
		throw new Exception("setTextAnnotationText is not working.." + e);
	}
}

public void clickOnButtonSaveAnnotation() throws Exception {
	try {
		waitUntilElementIsDisplayed(saveAnnotation);
		saveAnnotation.click();
	} catch (Exception ex) {
		throw new Exception("clickOnButtonSaveAnnotation is not working" + ex);
	}
}

}