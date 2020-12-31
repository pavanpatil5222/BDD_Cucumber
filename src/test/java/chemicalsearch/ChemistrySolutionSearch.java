package chemicalsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.chemicalsearch.Page_ChemicalSearchResults;
import pages.chemicalsearch.Page_ChemicalSearchLandingPage;

import support.Controller;
/**
 * 
 * @author Anand/Rashmi
 *
 */
public class ChemistrySolutionSearch {
	
	Page_ChemicalSearchLandingPage page_ChemicalSearchLandingPage;
	Page_ChemicalSearchResults page_ChemicalSearchResults;
	
/*CHEMEXP-379
 Pre-requiste: Login to the application
Steps:
1. Don't enter any text in search box and verify search icon
2.Enter some text in the search box
3.Click on Search Icon
Expected:
1.Search icon should be disabled/grayed out.
2.Search icon/button must be enabled (green) only when user enters some text in the box
3.System must run the search and land user on result set with relevant results. Page must be refreshed where user would see search box and result set with two tabs.	
 */
	@Test
	public boolean validateSearchTextBox(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		boolean blnchk = false;
		boolean flag = true;
		String patent = "Patent";
		String literature = "Literature";	
		String rsPatent,rsLiterature,rsSearchBox;
		try {
			Application.Logger.addStep("1.VERIFY SEARCH ICON WHEN TEXT IS NOT ENTERED IN THE SEARCH TEXT BOX","SEARCH ICON SHOULD BE GRAYED/DISABLED");
			blnchk=page_ChemicalSearchLandingPage.isDisabledSearchIcon();
			if(blnchk)
			{
				Application.Logger.addsubStep(LogStatus.PASS, "SEARCH ICON IS DISABLED", false);
			}
			else
			{
				Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH ICON IS NOT DISABLED", true);	
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.VERIFY SEARCH ICON WHEN TEXT IS ENTERED IN THE SEARCH TEXT BOX","SEARCH ICON SHOULD BE ENABLED");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			blnchk=page_ChemicalSearchLandingPage.isDisabledSearchIcon();
			if(!blnchk)
			{
				Application.Logger.addsubStep(LogStatus.PASS, "SEARCH ICON IS ENABLED", false);
			}
			else
			{
				Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH ICON IS NOT ENABLED", true);	
			}
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			//Application.waitTime(3);
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
			 Application.Logger.addsubStep(LogStatus.PASS,"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); 
			 } 
			 else {
			 throw new Exception("Results set is not loaded for the chemical search");
			 }
			 
			Application.Logger.endStep();
			Application.Logger.addStep("3.VERIFY SEARCH BOX,PATENT AND LITERATURE TABS IN THE RESULTS SET PAGE","SEARCH BOX,PATENT AND LITERATURE TABS SHOULD BE DISPLAYED IN THE RESULTS SET PAGE");
			rsSearchBox=page_ChemicalSearchResults.getTextSearchBox();
			if(rsSearchBox.trim().equals(searchText))
			{
			Application.Logger.addsubStep(LogStatus.PASS, "SEARCH BOX WITH TEXT IS DISPLAYED IN THE RESULTS SET PAGE", false);	
			}
			else
			{
			Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH BOX WITH TEXT IS NOT DISPLAYED IN THE RESULTS SET PAGE", true);	
			}
			rsPatent=page_ChemicalSearchResults.tabPatentSearch().getTextPatentName();
			rsLiterature =page_ChemicalSearchResults.tabLiteratureSearch().getTextLiteratureName();
			if (patent.equals(rsPatent.trim()) && literature.equals(rsLiterature.trim()))
			{
			Application.Logger.addsubStep(LogStatus.PASS, "PATENT AND LITERATURE TABS ARE DISPLAYED IN RESULTS SET PAGE", false);
			}
			else
			{
				Application.Logger.addsubStep(LogStatus.FAIL, "PATENT AND LITERATURE TABS ARE NOT DISPLAYED IN RESULTS SET PAGE", true);	
			}
			Application.Logger.endStep();
		}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}

/*CHEMEXP-381
 Pre-requiste: Login to the application
Steps:
1.Don't enter any letter in search box
2.Enter any letter in search box
3.click on X
Expected:
1.X must be removed from search box
2.X in green color must only appear, when user enters first letter in the box.
3.X must clear all the content from the search box
 */

    @Test
	public boolean validateSearchTextBoxClearAll(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		boolean flag = true;
		String expSearchText;
		try {
			Application.Logger.addStep("1.VERIFY CLEAR ALL ICON (X) WHEN NOTHING IS ENTERED IN THE SEARCH TEXT BOX","CLEAR ALL ICON (X) SHOULD NOT BE DISPLAYED IN THE SEARCH TEXT BOX");
			if(page_ChemicalSearchLandingPage.isDisplayedButtonClearAll())
			{
			Application.Logger.addsubStep(LogStatus.PASS, "CLEAR ALL ICON (X)IS NOT DISPLAYED ", false);	
			}
			else
			{
			Application.Logger.addsubStep(LogStatus.FAIL, "CLEAR ALL ICON (X)IS DISPLAYED ", true);		
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.ENTER A LETTER AND VERIFY THE CLEAR ALL (X) ICON","CLEAR ALL ICON (X) SHOULD APPEAR WHEN THE USER ENTERS THE FIRST LETTER");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			if(page_ChemicalSearchLandingPage.isDisplayedButtonClearAll())
			{
			Application.Logger.addsubStep(LogStatus.PASS, "CLEAR ALL ICON (X)IS DISPLAYED ", false);	
			}
			else
			{
			Application.Logger.addsubStep(LogStatus.FAIL, "CLEAR ALL ICON (X)IS NOT DISPLAYED ", true);		
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.CLICK ON CLEAR ALL (X) ICON","CLEAR ALL ICON (X) SHOULD CLEAR ALL THE CONTENTS IN SEARCH BOX");
			page_ChemicalSearchLandingPage.clickOnButtonClearAll();
			expSearchText=page_ChemicalSearchLandingPage.getTextSearchTextBox();
			if(expSearchText.isEmpty())
			{
			Application.Logger.addsubStep(LogStatus.PASS, "SEARCH TEXT BOX IS EMPTY AFTER THE CLICK ON CLEAR ALL (X) ICON", false);	
			}
			else
			{
			Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH TEXT BOX IS NOT EMPTY AFTER THE CLICK ON CLEAR ALL (X) ICON", true);		
			}
			Application.Logger.endStep();
		}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}

    
/*CHEMEXP-450
Steps:
	1.Login to the application
	2.Perform search with "antibacterial polyethylene resin containing transition metals"
	3.Click on the Who are the Suggested keywords?and click on any keyword
	4.Perform search
	Expected:
	1.Should logged in successfully
	2.Search is performed
	3.Keyword is availble in bottom section
	4.Search performed with inserted keyword
    */
  @Test
  public boolean validatePatentSuggestedKeyWord(Controller Application, HashMap<String, String> input) {
       		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
       		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
       		String searchText = input.get("searchtext");
       		boolean flag = true;
       		String expKeywordText="epoxy resin";
       		String rsKeyword;
       		try {
       			Application.Logger.addStep("1.PERFORM A SEARCH WITH THE TEXT antibacterial polyethylene resin containing transition metals","RESULTS SET SHOULD BE DISPLAYED");
    			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
    			page_ChemicalSearchLandingPage.clickOnSearchIcon();
    			if (page_ChemicalSearchResults.checkIfResultsFound()) {
    			 Application.Logger.addsubStep(LogStatus.PASS,"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); 
    			 } 
    			 else {
    			 throw new Exception("Results set is not loaded for the chemical search");
    			 }
    			Application.Logger.endStep();
    			Application.Logger.addStep("2.CLICK ON THE KEYWORD FROM SUGGESTED KEY WORD SECTION AND VERIFY THE KEY WORD IS ADDED IN THE KEYWORD BOTTOM SECTION","KEYWORD SHOULD BE ADDED IN THE BOTTOM SECTION");
    			page_ChemicalSearchResults.tabPatentSearch().clickOnTabPatentSearch();
    			page_ChemicalSearchResults.tabPatentSearch().clickOnSuggestedKeyWordlink();
    			page_ChemicalSearchResults.tabPatentSearch().clickOnFirstKeyWord();
    			page_ChemicalSearchResults.clickOnSearchIcon();
    			rsKeyword=page_ChemicalSearchResults.getTextKeyword();
    			if(rsKeyword.trim().equals(expKeywordText.trim()))
    			{
    			Application.Logger.addsubStep(LogStatus.PASS, "SEARCH IS PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION", false);	
    			}
    			else
    			{
    			Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH IS NOT PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION", true);		
    			}
    			Application.Logger.endStep();
       		}
       		
       	 catch (Exception e) {
       		Application.Logger.addException(e.getMessage());
       		return flag = false;
       	}
       	return flag;
       }
    


 /*CHEMEXP-451
Steps:
	1.Login to the application
	2.Perform search with "antibacterial polyethylene resin containing transition metals"
	3.Switc to Literature tab and Click on the Who are the Suggested keywords?and click on any keyword
	4.Perform search
	Expected:
	1.Should logged in successfully
	2.Search is performed
	3.Keyword is availble in bottom section
	4.Search performed with inserted keyword
    */

    @Test
   	public boolean validateLiteratureSuggestedKeyWord(Controller Application, HashMap<String, String> input) {
   		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
   		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
   		String searchText = input.get("searchtext");
   		boolean flag = true;
   		String expKeywordText="epoxy resin";
   		String rsKeyword;
   		try {
   			Application.Logger.addStep("1.PERFORM A SEARCH WITH THE TEXT antibacterial polyethylene resin containing transition metals","RESULTS SET SHOULD BE DISPLAYED");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
			 Application.Logger.addsubStep(LogStatus.PASS,"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); 
			 } 
			 else {
			 throw new Exception("Results set is not loaded for the chemical search");
			 }
			Application.Logger.endStep();
			Application.Logger.addStep("2.CLICK ON THE KEYWORD FROM SUGGESTED KEY WORD SECTION AND VERIFY THE KEY WORD IS ADDED IN THE KEYWORD BOTTOM SECTION","KEYWORD SHOULD BE ADDED IN THE BOTTOM SECTION");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnTabLiteratureSearch();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnLinkSuggestedKeyWord();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnFirstKeyWord();
			page_ChemicalSearchResults.clickOnSearchIcon();
			rsKeyword=page_ChemicalSearchResults.getTextKeyword();
			if(rsKeyword.trim().equals(expKeywordText.trim()))
			{
			Application.Logger.addsubStep(LogStatus.PASS, "SEARCH IS PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION", false);	
			}
			else
			{
			Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH IS NOT PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION", true);		
			}
			Application.Logger.endStep();
   		}
   		
   	 catch (Exception e) {
   		Application.Logger.addException(e.getMessage());
   		return flag = false;
   	}
   	return flag;
   }



 /*CHEMEXP-433
 Pre-requiste: Login to the application and perform a search with a phrase displaying the results in the RS page
Steps:
1. Click on the Patents Tab
2.Click on thumbs up for the record
3.Click on thumbs down for the record
Expected:
1.Patent Record set should be displayed.
2.Thumbs up icon should be changed to solid green 
3.Thumbs icon should be changed to hollow state and thumbs down should be changed to solid green
 */

  @Test
  public boolean validatePatentThumbsUpThumbsDown(Controller Application, HashMap<String, String> input) {
       		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
       		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
       		String searchText = input.get("searchtext");
       		boolean flag = true;
       		String expThumbsUpHollowState;
       		String expThumbsDownHollowState;
       		try {
       			Application.Logger.addStep("1.PERFORM A SEARCH WITH THE TEXT ","RESULTS SET SHOULD BE DISPLAYED");
    			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
    			page_ChemicalSearchLandingPage.clickOnSearchIcon();
    			if (page_ChemicalSearchResults.checkIfResultsFound()) {
    			 Application.Logger.addsubStep(LogStatus.PASS,"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); 
    			 } 
    			 else {
    			 throw new Exception("Results set is not loaded for the chemical search");
    			 }
    			page_ChemicalSearchResults.tabPatentSearch().clickOnTabPatentSearch();
    			Application.Logger.endStep();
    			Application.Logger.addStep("2.CLICK ON THUMBS UP ICON FOR A RECORD","ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
    			page_ChemicalSearchResults.clickOnThumbsUpIcon();
    			expThumbsDownHollowState=page_ChemicalSearchResults.checkThumbsDownHollowState();
    			if(!expThumbsDownHollowState.contains("filled"))
    			{
    			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS IN HOLLOW STATE", false);	
    			}
    			else
    			{
    			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT IN HOLLOW STATE", false);	
    			}
    			Application.Logger.endStep();
    			Application.Logger.addStep("3.CLICK ON THUMBS DOWN ICON FOR A RECORD","ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
    			page_ChemicalSearchResults.clickOnThumbsDownIcon();
    			expThumbsUpHollowState=page_ChemicalSearchResults.checkThumbsUpHollowState();
    			if(!expThumbsUpHollowState.contains("filled"))
    			{
    			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS IN HOLLOW STATE", false);	
    			}
    			else
    			{
    			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT IN HOLLOW STATE", false);	
    			}
    			Application.Logger.endStep();
       		}
       	 catch (Exception e) {
       		Application.Logger.addException(e.getMessage());
       		return flag = false;
       	}
       	return flag;
       }


/*CHEMEXP-434
 Pre-requiste: Login to the application and perform a search with a phrase displaying the results in the RS page
Steps:
1. Click on the Literature Tab
2.Click on thumbs up for the record
3.Click on thumbs down for the record
Expected:
1.Literature Record set should be displayed.
2.Thumbs up icon should be changed to solid green 
3.Thumbs icon should be changed to hollow state and thumbs down should be changed to solid green*/

   @Test
   public boolean validateLiteratureThumbsUpThumbsDown(Controller Application, HashMap<String, String> input) {
        		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
        		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
        		String searchText = input.get("searchtext");
        		boolean flag = true;
        		String expThumbsUpHollowState;
        		String expThumbsDownHollowState;
        		try {
        		Application.Logger.addStep("1.PERFORM A SEARCH WITH THE TEXT ","RESULTS SET SHOULD BE DISPLAYED");
     			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
     			page_ChemicalSearchLandingPage.clickOnSearchIcon();
     			if (page_ChemicalSearchResults.checkIfResultsFound()) {
     			 Application.Logger.addsubStep(LogStatus.PASS,"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); 
     			 } 
     			 else {
     			 throw new Exception("Results set is not loaded for the chemical search");
     			 }
     			page_ChemicalSearchResults.tabLiteratureSearch().clickOnTabLiteratureSearch();
     			Application.Logger.endStep();
     			Application.Logger.addStep("2.CLICK ON THUMBS UP ICON FOR A RECORD","ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
     			page_ChemicalSearchResults.clickOnThumbsUpIcon();
     			expThumbsDownHollowState=page_ChemicalSearchResults.checkThumbsDownHollowState();
     			if(!expThumbsDownHollowState.contains("filled"))
     			{
     			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS IN HOLLOW STATE", false);	
     			}
     			else
     			{
     			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT IN HOLLOW STATE", false);	
     			}
     			Application.Logger.endStep();
     			Application.Logger.addStep("3.CLICK ON THUMBS DOWN ICON FOR A RECORD","ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
     			page_ChemicalSearchResults.clickOnThumbsDownIcon();
     			expThumbsUpHollowState=page_ChemicalSearchResults.checkThumbsUpHollowState();
     			if(!expThumbsUpHollowState.contains("filled"))
     			{
     			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS IN HOLLOW STATE", false);	
     			}
     			else
     			{
     			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT IN HOLLOW STATE", false);	
     			}
     			Application.Logger.endStep();
        		}
        	 catch (Exception e) {
        		Application.Logger.addException(e.getMessage());
        		return flag = false;
        	}
        	return flag;
        }
	

   
   /*CHEMEXP-431
  Pre-requiste: Login to the application and perform a search with a phrase displaying the results in the RS page
Steps:
1. Click on the Patents Tab
2.Verify the thumbs up and thumbs down for every record
Expected:
1.Patent Record set should be displayed.
2.Thumbs up and thumbs down should be displayed for every record
   */

    @Test
    public boolean validatePatentThumbsUpThumbsDownForRecords(Controller Application, HashMap<String, String> input) {
         		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
         		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
         		String searchText = input.get("searchtext");
         		boolean flag = true;
         		try {
         		Application.Logger.addStep("1.PERFORM A SEARCH WITH THE TEXT ","RESULTS SET SHOULD BE DISPLAYED");
      			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
      			page_ChemicalSearchLandingPage.clickOnSearchIcon();
      			if (page_ChemicalSearchResults.checkIfResultsFound()) {
      			 Application.Logger.addsubStep(LogStatus.PASS,"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); 
      			 } 
      			 else {
      			 throw new Exception("Results set is not loaded for the chemical search");
      			 }
      			page_ChemicalSearchResults.tabPatentSearch().clickOnTabPatentSearch();
      			Application.Logger.endStep();
      			Application.Logger.addStep("2.VERIFY THUMBS UP ICON FOR EVERY RECORD","THUMBS UP ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
      			if(page_ChemicalSearchResults.isDisplayedThumbsUpIcon())
      			{
      			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS DISPLAYED FOR EVERY RECORD", false);	
      			}
      			else
      			{
      			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT DISPLAYED FOR EVERY RECORD", false);	
      			}
      			Application.Logger.endStep();
      			Application.Logger.addStep("3.VERIFY THUMBS DOWN ICON FOR EVERY RECORD","THUMBS DOWN ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
      			if(page_ChemicalSearchResults.isDisplayedPatentThumbsDownIcon())
      			{
      			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS DISPLAYED FOR EVERY RECORD", false);	
      			}
      			else
      			{
      			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT DISPLAYED FOR EVERY RECORD", false);	
      			}
      			Application.Logger.endStep();
         		}
         	 catch (Exception e) {
         		Application.Logger.addException(e.getMessage());
         		return flag = false;
         	}
         	return flag;
         }

    
 /*CHEMEXP-432
Pre-requiste: Login to the application and perform a search with a phrase displaying the results in the RS page
Steps:
1. Click on the Literature Tab
2.Verify the thumbs up and thumbs down for every record
Expected:
1.Literature Record set should be displayed.
2.Thumbs up and thumbs down should be displayed for every record
  */

 @Test
 public boolean validateLiteratureThumbsUpThumbsDownForRecords(Controller Application, HashMap<String, String> input) {
           		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
           		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
           		String searchText = input.get("searchtext");
           		boolean flag = true;
           		try {
           		Application.Logger.addStep("1.PERFORM A SEARCH WITH THE TEXT ","RESULTS SET SHOULD BE DISPLAYED");
        			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
        			page_ChemicalSearchLandingPage.clickOnSearchIcon();
        			if (page_ChemicalSearchResults.checkIfResultsFound()) {
        			 Application.Logger.addsubStep(LogStatus.PASS,"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); 
        			 } 
        			 else {
        			 throw new Exception("Results set is not loaded for the chemical search");
        			 }
        			page_ChemicalSearchResults.tabLiteratureSearch().clickOnTabLiteratureSearch();
        			Application.Logger.endStep();
        			Application.Logger.addStep("2.VERIFY THUMBS UP ICON FOR EVERY RECORD","THUMBS UP ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
        			if(page_ChemicalSearchResults.isDisplayedThumbsUpIcon())
        			{
        			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS DISPLAYED FOR EVERY RECORD", false);	
        			}
        			else
        			{
        			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT DISPLAYED FOR EVERY RECORD", false);	
        			}
        			Application.Logger.endStep();
        			Application.Logger.addStep("3.VERIFY THUMBS DOWN ICON FOR EVERY RECORD","THUMBS DOWN ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
        			if(page_ChemicalSearchResults.isDisplayedLiteratureThumbsDownIcon())
        			{
        			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS DISPLAYED FOR EVERY RECORD", false);	
        			}
        			else
        			{
        			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT DISPLAYED FOR EVERY RECORD", false);	
        			}
        			Application.Logger.endStep();
           		}
           	 catch (Exception e) {
           		Application.Logger.addException(e.getMessage());
           		return flag = false;
           	}
           	return flag;
           }

 
 
 /*CHEMEXP-380
 Pre-requiste: Login to the application and Text should be available in search box
Steps:
	1.Enter text in-between the existing text
	2.Enter text after existing text
	3.Enter text before existing text
	4.Select text and delete it
	Expected:
	1.Text should be inserted in-between existing text
	2.Text should be inserted after existing text
	3.Text should be inserted before existing text
	4.Should be able select and delete selected text. 
   @Test
   public boolean validateLandingPageSearchBox(Controller Application, HashMap<String, String> input) {
        		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
        		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
        		String searchText = input.get("searchtext");
        		boolean flag = true;
        		String expKeywordText="titanium dioxide";
        		String expSearchText;
        		try {
        		page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
        		Application.Logger.addStep("1.ENTER THE TEXT IN BETWEEN THE TEXT","TEXT SHOULD BE INSERTED IN-BETWEEN THE TEXT");
        		page_ChemicalSearchLandingPage.enterTextAnywhereInSearchBox("fib",4);
        		expSearchText = page_ChemicalSearchLandingPage.getTextSearchTextBox();
        		if(!searchText.equals(expSearchText))
        		{
        		Application.Logger.addsubStep(LogStatus.PASS, "TEXT WAS INSERTED SUCCESSFULLY IN BETWEEN THE TEXT", false);	
        		}
        		else
        		{
        		Application.Logger.addsubStep(LogStatus.FAIL, "TEXT WAS NOT INSERTED SUCCESSFULLY IN BETWEEN THE TEXT Actual Text:"+searchText+"Expected Text:"+expSearchText, true);	
        		}
        		Application.Logger.addStep("2.ENTER THE TEXT AFTER THE EXISTING TEXT","TEXT SHOULD BE INSERTED AFTER THE EXISTING TEXT");
     			Application.Logger.addStep("3.ENTER THE TEXT BEFORE THE EXISTING TEXT","TEXT SHOULD BE INSERTED BEFORE THE EXISTING TEXT");
     			Application.Logger.addStep("4.SELECT THE TEXT AND DELETED THE TEXT","TEXT SHOULD BE SELECTED AND DELETED");
     			expSearchText = page_ChemicalSearchLandingPage.getTextSearchTextBox();
     			page_ChemicalSearchLandingPage.deleteTextSearchTextBox();
     			if(expSearchText.isEmpty())
     			{
        		Application.Logger.addsubStep(LogStatus.PASS,"TEXT IS DELETED SUCCESSFULLY", false);	
        		}
        		else
        		{
        		Application.Logger.addsubStep(LogStatus.FAIL,"TEXT IS NOT DELETED SUCCESSFULLY,SEARCH TEXT "+searchText, true);	
        		}
     			Application.Logger.endStep();
        		}   		
        	 catch (Exception e) {
        		Application.Logger.addException(e.getMessage());
        		return flag = false;
        	}
        	return flag;
        }*/
     

/*CHEMEXP-405
   Pre-requiste:
Steps:
	1. Perform search with polymer
	2.Click on Search Icon
	3.Navigate to Patent Record view click on X
	4. Switch to Literature tab and Click on the X
Expected:
	1.Must accept keywords or natural language block of text for their search criteria.
	2.Result set page should be displayed
	3.Record view is closed
	4.Record view is closed */
     @Test
     public boolean validatePatentAndLiteratureRecordViewCloseButton(Controller Application, HashMap<String, String> input) {
          		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
          		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
          		String searchText = input.get("searchtext");
          		boolean flag = true;
          		try {
          			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHRASE ","RESULTS SET SHOULD BE DISPLAYED");
        			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
        			page_ChemicalSearchLandingPage.clickOnSearchIcon();
        			if (page_ChemicalSearchResults.checkIfResultsFound()) {
        			 Application.Logger.addsubStep(LogStatus.PASS,"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); 
        			 } 
        			 else {
        			 throw new Exception("Results set is not loaded for the chemical search");
        			 }
        			Application.Logger.endStep();
        			Application.Logger.addStep("2.CLICK ON THE CLOSE ICON FOR THE PATENT IN THE RECORD VIEW","PATENT RECORD VIEW SHOULD BE CLOSED SUCCESSFULLY");
        			page_ChemicalSearchResults.tabPatentSearch().clickOnTabPatentSearch();
        			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord();
        			page_ChemicalSearchResults.tabPatentSearch().clickOnClosePatentRecordView();
        			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedPatentRecordCloseIcon())
        			{
                	Application.Logger.addsubStep(LogStatus.PASS,"PATENT RECORD VIEW IS CLOSED SUCCESSFULLY", false);	
                	}
                	else
                	{
                	Application.Logger.addsubStep(LogStatus.FAIL,"PATENT RECORD VIEW IS NOT CLOSED SUCCESSFULLY", true);	
                	}
             		Application.Logger.endStep();
             		Application.Logger.addStep("3.CLICK ON THE CLOSE ICON FOR THE LITERATURE IN THE RECORD VIEW","LITERATURE RECORD VIEW SHOULD BE CLOSED SUCCESSFULLY");
        			page_ChemicalSearchResults.tabLiteratureSearch().clickOnTabLiteratureSearch();
        			page_ChemicalSearchResults.tabLiteratureSearch().clickOnLiteratureRecord();
        			page_ChemicalSearchResults.tabLiteratureSearch().clickOnCloseLiteratureRecordView();
        			if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedLiteratureRecordCloseIcon())
        			{
                	Application.Logger.addsubStep(LogStatus.PASS,"LITERATURE RECORD VIEW IS CLOSED SUCCESSFULLY", false);	
                	}
                	else
                	{
                	Application.Logger.addsubStep(LogStatus.FAIL,"LITERATURE RECORD VIEW IS NOT CLOSED SUCCESSFULLY", true);	
                	}
             		Application.Logger.endStep();
        			
          		}   		
          	 catch (Exception e) {
          		Application.Logger.addException(e.getMessage());
          		return flag = false;
          	}
          	return flag;
          } 
     


	
}
