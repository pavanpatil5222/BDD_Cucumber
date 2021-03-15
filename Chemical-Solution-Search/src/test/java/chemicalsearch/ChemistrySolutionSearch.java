package chemicalsearch;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
	
	/**CHEMEXP-739 */
 	@Test
	public boolean validateSearchTextBox(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String searchTextLandingPage = "p";
		boolean blnchk = false;
		boolean flag = true;
		String patent = "Patent";
		String literature = "Literature";	
		String rsPatent,rsLiterature,rsSearchBox,expSearchText;
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
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			Application.Logger.endStep();
			Application.Logger.addStep("4.VERIFY CLEAR ALL ICON (X) WHEN NOTHING IS ENTERED IN THE SEARCH TEXT BOX","CLEAR ALL ICON (X) SHOULD NOT BE DISPLAYED IN THE SEARCH TEXT BOX");
			if(page_ChemicalSearchLandingPage.isDisplayedButtonClearAll())
			{
			Application.Logger.addsubStep(LogStatus.PASS, "CLEAR ALL ICON (X)IS NOT DISPLAYED ", false);	
			}
			else
			{
			Application.Logger.addsubStep(LogStatus.FAIL, "CLEAR ALL ICON (X)IS DISPLAYED ", true);		
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.ENTER A LETTER AND VERIFY THE CLEAR ALL (X) ICON","CLEAR ALL ICON (X) SHOULD APPEAR WHEN THE USER ENTERS THE FIRST LETTER");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchTextLandingPage);
			if(page_ChemicalSearchLandingPage.isDisplayedButtonClearAll())
			{
			Application.Logger.addsubStep(LogStatus.PASS, "CLEAR ALL ICON (X)IS DISPLAYED ", false);	
			}
			else
			{
			Application.Logger.addsubStep(LogStatus.FAIL, "CLEAR ALL ICON (X)IS NOT DISPLAYED ", true);		
			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.CLICK ON CLEAR ALL (X) ICON","CLEAR ALL ICON (X) SHOULD CLEAR ALL THE CONTENTS IN SEARCH BOX");
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

    
 	/**CHEMEXP-752*/
  @Test
  public boolean validatePatentSuggestedKeyWord(Controller Application, HashMap<String, String> input) {
       		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
       		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
       		String searchText = input.get("searchtext");
       		boolean flag = true;
       		String expKeywordText;
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
    			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonInsights();
    			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonFirstKeyWord();
    			page_ChemicalSearchResults.clickOnSearchIcon();
    			rsKeyword=page_ChemicalSearchResults.getTextKeyword();
    			expKeywordText= page_ChemicalSearchResults.tabPatentSearch().getTextPatentInsightsKeyword();
    			if(rsKeyword.trim().equalsIgnoreCase(expKeywordText.trim()))
    			{
    			Application.Logger.addsubStep(LogStatus.PASS, "SEARCH IS PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION", false);	
    			}
    			else
    			{
    				Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH IS NOT PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION, INSIGHTS KEYWORD : \t"  +expKeywordText+ "\t\t RESULT SET KEYWORD:\t" +rsKeyword, true);		
    			}
    			Application.Logger.endStep();
    			
    			Application.Logger.addStep("3.IN LITERATURE TAB, CLICK ON THE KEYWORD FROM SUGGESTED KEY WORD SECTION AND VERIFY THE KEY WORD IS ADDED IN THE KEYWORD BOTTOM SECTION","KEYWORD SHOULD BE ADDED IN THE BOTTOM SECTION");
    			page_ChemicalSearchResults.tabLiteratureSearch().clickOnTabLiteratureSearch();
    			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonInsights();
    			page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonFirstKeyWord();
    			page_ChemicalSearchResults.clickOnSearchIcon();
    			rsKeyword=page_ChemicalSearchResults.getTextKeyword();
    			expKeywordText= page_ChemicalSearchResults.tabLiteratureSearch().getTextLiteratureInsightsKeyword();
    			expKeywordText=expKeywordText.toLowerCase();
    			if(!rsKeyword.isEmpty() && (!expKeywordText.isEmpty()))
    			{
    			Application.Logger.addsubStep(LogStatus.PASS, "SEARCH IS PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION", false);	
    			}
    			else
    			{
    			Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH IS NOT PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION, INSIGHTS KEYWORD : \t"  +expKeywordText+ "\t\t RESULT SET KEYWORD:\t" +rsKeyword, true);		
    			}
    			Application.Logger.endStep();
    			
    			Application.Logger.addStep("4.CLICK ON THE CLOSE ICON FOR THE PATENT IN THE RECORD VIEW","PATENT RECORD VIEW SHOULD BE CLOSED SUCCESSFULLY");
    			page_ChemicalSearchResults.tabPatentSearch().clickOnTabPatentSearch();
    			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
    			Application.waitTime(2);
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
         		Application.Logger.addStep("5.CLICK ON THE CLOSE ICON FOR THE LITERATURE IN THE RECORD VIEW","LITERATURE RECORD VIEW SHOULD BE CLOSED SUCCESSFULLY");
    			page_ChemicalSearchResults.tabLiteratureSearch().clickOnTabLiteratureSearch();
    			page_ChemicalSearchResults.tabLiteratureSearch().clickOnLiteratureRecord(1);
    			Application.waitTime(2);
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
  
  
  /**CHEMEXP-748*/
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
    			Application.Logger.addStep("2. IN PATENT TAB, CLICK ON THUMBS UP ICON FOR A RECORD","ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
    			page_ChemicalSearchResults.tabPatentSearch().clickOnThumbsUpIcon();
    			expThumbsDownHollowState=page_ChemicalSearchResults.tabPatentSearch().checkThumbsDownHollowState();
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
    			page_ChemicalSearchResults.tabPatentSearch().clickOnThumbsDownIcon();
    			expThumbsUpHollowState=page_ChemicalSearchResults.tabPatentSearch().checkThumbsUpHollowState();
    			if(!expThumbsUpHollowState.contains("filled"))
    			{
    			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS IN HOLLOW STATE", false);	
    			}
    			else
    			{
    			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT IN HOLLOW STATE", false);	
    			}
    			Application.Logger.endStep();
    			
    			Application.Logger.addStep("4.IN PATENT TAB, VERIFY THUMBS UP ICON FOR EVERY RECORD","THUMBS UP ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
      			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedPatentThumbsUpIcon())
      			{
      			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS DISPLAYED FOR EVERY RECORD", false);	
      			}
      			else
      			{
      			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT DISPLAYED FOR EVERY RECORD", false);	
      			}
      			Application.Logger.endStep();
      			Application.Logger.addStep("5.VERIFY THUMBS DOWN ICON FOR EVERY RECORD","THUMBS DOWN ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
      			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedPatentThumbsDownIcon())
      			{
      			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS DISPLAYED FOR EVERY RECORD", false);	
      			}
      			else
      			{
      			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT DISPLAYED FOR EVERY RECORD", false);	
      			}
      			Application.Logger.endStep();
       			
       		Application.Logger.addStep("6.IN LITERATURE TAB, CLICK ON THUMBS UP ICON FOR A RECORD","ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
       		page_ChemicalSearchResults.tabLiteratureSearch().clickOnTabLiteratureSearch();
 			page_ChemicalSearchResults.tabLiteratureSearch().clickOnThumbsUpIcon();
 			expThumbsDownHollowState=page_ChemicalSearchResults.tabLiteratureSearch().checkThumbsDownHollowState();
 			if(!expThumbsDownHollowState.contains("filled"))
 			{
 			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS DOWN ICON IS IN HOLLOW STATE", false);	
 			}
 			else
 			{
 			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS DOWN ICON IS NOT IN HOLLOW STATE", false);	
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("7.CLICK ON THUMBS DOWN ICON FOR A RECORD","ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
 			page_ChemicalSearchResults.tabLiteratureSearch().clickOnThumbsDownIcon();
 			expThumbsUpHollowState=page_ChemicalSearchResults.tabLiteratureSearch().checkThumbsUpHollowState();
 			if(!expThumbsUpHollowState.contains("filled"))
 			{
 			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS IN HOLLOW STATE", false);	
 			}
 			else
 			{
 			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT IN HOLLOW STATE", false);	
 			}
 			Application.Logger.endStep();
    		Application.Logger.addStep("8.IN LITERATURE TAB, VERIFY THUMBS UP ICON FOR EVERY RECORD","THUMBS UP ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
			if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedLiteratureThumbsUpIcon())
			{
			Application.Logger.addsubStep(LogStatus.PASS,"THUMBS UP ICON IS DISPLAYED FOR EVERY RECORD", false);	
			}
			else
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"THUMBS UP ICON IS NOT DISPLAYED FOR EVERY RECORD", false);	
			}
			Application.Logger.endStep();
			Application.Logger.addStep("9.VERIFY THUMBS DOWN ICON FOR EVERY RECORD","THUMBS DOWN ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
			if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedLiteratureThumbsDownIcon())
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

/**CHEMEXP-740*/
	
	@Test
	public boolean validateFieldsOnLiteratureResultset(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String patentRecordCount="500+";
 		String literatureRecordCount="300+";
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Switch to Literature tab","Literature result set is displayed");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.getLiteratureResultsCount()>0) {
				Application.Logger.addsubStep(LogStatus.PASS, "LITERATURE RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Verify the fields","Title and abstract fields should be displayed.");
			if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedTitle(1)) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Title Field is displayed  for row no :1 in Literature results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Title Field is not displayed for given row in Literature results page.", true);
			}
			
			if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedAbstract(1)) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Abstract Filed is displayed  for row no :1 in Literature results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Abstract Field is not displayed for given row in Literature results page. ", true);
			}
			Application.Logger.endStep();			
			Application.Logger.addStep("4.Verify row/record on the result set in Patent Tab.","Each row/record on the result set should show:\r\n" + 
					"3.1. Publication number\r\n" + 
					"3.2. Title\r\n" + 
					"Note: Title will be displayed without any field name.\r\n" + 
					"3.3. Abstract data\r\n" + 
					"3.3.1. Use\r\n" + 
					"3.3.2 Novelty\r\n" + 
					"3.4 Image");			
			page_ChemicalSearchResults.clickOnTabPatent();
			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedTitle(1)) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Title is displayed  for row no :1 in Patent results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Title is not displayed for given row in Patent results page.", true);
			}
			
			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedAbstract(1)) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Abstract Data is displayed  for row no :1 in Patent results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Abstract data is not displayed for given row in Patent results page. ", true);
			}
			

			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedPN(1)) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"PN  is displayed  for row no :1 in Patent results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"PN Number is not displayed for given row in Patent results page. ", true);
			}
			

			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedNovelty(1)) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Novelty Filed is displayed  for row no :1 in Patent results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Novelty Field is not displayed for given row in Patent results page. ", true);
			}
			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedUse(1)) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Use Filed is displayed  for row no :1 in Patent results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Use Field is not displayed for given row in Patent results page. ", true);
			}
			if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedImg(1)) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Image  is displayed  for row no :1 in Patent results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Image is not displayed for given row in Patent results page. ", true);
			}
			Application.Logger.endStep();
			
			Application.Logger.addStep("5.Verify tabs on Result set page.","Patent tab and Literature tab Results should be displayed");
			
			if(page_ChemicalSearchResults.isDisplayedTabPatent()) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Patent tab is displayed results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Patent tab is not displayed in results page.", true);
			}
			
			if(page_ChemicalSearchResults.isDisplayedTabLiterature()) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Literature tab is displayed results page.", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Literature tab is not displayed in results page.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.Click on Patent tab.","If the user is on Patent tab should highlight that tab.\r\n" + 
					"Should display count of patent results retrieved; If results retrieved are more than 500 then displayed as 500+\r\n" + 
					"should Display patent results in rows");
			page_ChemicalSearchResults.clickOnTabPatent();
			if(page_ChemicalSearchResults.getResultsCount()>500) 
			{
				if(page_ChemicalSearchResults.getTabPatentResultsCount().equals(patentRecordCount))
				
					Application.Logger.addsubStep(LogStatus.PASS,"Record Count  is displaying as expected for Patent results .", false);
					else
					Application.Logger.addsubStep(LogStatus.FAIL,"Record Count is not displayed  as expected for Patent results .", true);
							 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Results count is not morethan '500' for Patent results.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.Click on Literature tab","If the user is on Literature tab should highlight that tab.\r\n" + 
					"Should display count of literature results retrieved ; If results retrieved are more than 300 then displayed as 300+\r\n" + 
					"Display literature results in rows");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if(page_ChemicalSearchResults.getResultsCount()>300) 
			{
				if(page_ChemicalSearchResults.getTabLiteratureResultsCount().equals(literatureRecordCount))
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Record Count  is displaying as expected for Literature results .", false);
				}
					else
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Record Count is not displayed  as expected for Literature results .", true);
				}			 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"Results count is not morethan '300' for Literature results.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.Verify Search box","search box Should be next to the tabs.");
			if(page_ChemicalSearchResults.isDisplayedSearchBoxNextToTabLiturature()) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"SearchBox  is displayed next to Liturature Tab", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"SearchBox is not displayed next to Liturature Tab.", true);
			}
			Application.Logger.endStep();
			}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}

 
  	 /**CHEMEXP-741*/
   	@Test
  	public boolean validateSearchboxTopSection(Controller Application, HashMap<String, String> input) {
  		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
  		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
  		String searchText = input.get("searchtext");
  		String lengthySerachText="Novelty\r\n" + 
  				"A superabsorbent polymer comprises a base resin powder containing a crosslinked polymer of a water-soluble ethylenically unsaturated monomer including partially neutralized acidic group(s), and pores having diameter of 1 μ m or more, formed on the base resin powder, and has apparent density of more than 0.55 g/ml. The crosslinked polymer comprises layered silicate-based particles dispersed in the crosslinked structure.\r\n" + 
  				"\r\n" + 
  				"Use\r\n" + 
  				"Superabsorbent polymer. Uses include but are not limited to diaper, soil protective agent, sealant, seedling sheet, and foodstuffs applications.";
  		
  		boolean flag = true;
  		try {
  			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
  			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
  			page_ChemicalSearchLandingPage.clickOnSearchIcon();
  			Application.waitUntilFectchRecordProgressBarToDisappears();
  			if (page_ChemicalSearchResults.checkIfResultsFound()) {
  				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
  			} else {
  				throw new Exception("Results set is not loaded for the chemical search");
  			}
  			Application.Logger.endStep();
  			Application.Logger.addStep("2.Verify Top section in search bar.","Top section in the search bar must show the text entered by the user for searching.\r\n" + 
  					"Should show ellipsis if text is more than what can be accommodated in one row");
  			
  			if(page_ChemicalSearchResults.getTopSectionBarText().equalsIgnoreCase(searchText)) 
  			{
  				Application.Logger.addsubStep(LogStatus.PASS,"Top section in the search bar is showing the text :"+page_ChemicalSearchResults.getTopSectionBarText()+ "entered by the user :"+searchText+"for searching.", false); 
  			} 
  			else 
  			{
  			Application.Logger.addsubStep(LogStatus.FAIL,"Top section in the search bar is not showing the text entered by the user for searching.", true);
  			}
  			Application.Logger.endStep();
  			Application.Logger.addStep("3.verify By entering more than what can accomidate in one row" , "Pills are displayed.");
  			
  			if(page_ChemicalSearchResults.isDisplayedPillBox(1)) 
  			{
  				Application.Logger.addsubStep(LogStatus.PASS,"Pill is showing for entered by the user for searching  :'"+lengthySerachText+"'", false); 
  			} 
  			else 
  			{
  			Application.Logger.addsubStep(LogStatus.FAIL,"Pill is not showing for entered by the user for searching :'"+lengthySerachText+"'", true);
  			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("4.Verify the rollover of first row of Top section.","Rollover on the first row must show the full text");
  			if(searchText.trim().contains(page_ChemicalSearchResults.getTopSectionBarCompleteText())) 
  			{
  				Application.Logger.addsubStep(LogStatus.PASS,"First row of Top section is showing the Full text :"+page_ChemicalSearchResults.getTopSectionBarCompleteText()+"entered by the user :- "+searchText+"for searching.", false); 
  			} 
  			else 
  			{
  			Application.Logger.addsubStep(LogStatus.FAIL," First row of Top section is not showing the Full text entered by the user for searching.", true);
  			}
  			Application.Logger.endStep();
 			Application.Logger.addStep("5.Verify search box on the Bottom section.","Bottom section must show the keywords which are derived/extracted/expanded by the system from user's input text.");
  			List<String> getListOfPills=page_ChemicalSearchResults.getListOfPillBoxes();
  			for(String pills:getListOfPills) 
  			{
  			if(pills!= null) 
  			{
  				Application.Logger.addsubStep(LogStatus.PASS,"Pill :"+pills+" is showing for entered by the user for searching", false); 
  			} 
  			else 
  			{
  				Application.Logger.addsubStep(LogStatus.FAIL,"Pill is not showing for entered by the user for searching :'"+searchText+"'", true);
  			}
  			}
  			Application.Logger.endStep();
  		}
  	 catch (Exception e) {
  		Application.Logger.addException(e.getMessage());
  		return flag = false;
  	}
  	return flag;
  }
  	 	
   	/**CHEMEXP-742*/
 	
 	@Test
 	public boolean validateXDeleteOrRemoveKeyword(Controller Application, HashMap<String, String> input) {
 		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 		String searchText = input.get("searchtext");
 		List<String> finalKeywordList= Arrays.asList(input.get("keywords").split(","));
 		
 		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("2.Verify X to each of the keywords.",".All the extracted keywords must have x to delete/remove them. No toast should be shown in this case.");
 			int getListOfPills=page_ChemicalSearchResults.getNoOfPillBoxes();
 			for(int i=1;i<=getListOfPills;i++) 
 			{
 				if(page_ChemicalSearchResults.isDisplayedRemoveIcon(i)) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"Pill :"+i+" is showing 'X' for delete/Remove for keyword", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"Pill :"+i+"is not showing 'X' for delete/Remove for keyword", true);
 				}
 			}
 			if(page_ChemicalSearchResults.isNotDisplayedToastMessage()) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"ToastMessage is not displayed", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"ToastMessage is displayed", true);
 			}
 			Application.Logger.endStep();			
			Application.Logger.addStep("3.Add new keywords to search box after the extracted keywords and Click enter or press tab or clicks on search icon or click anywhere on screen.","New keywords should be entered and newly entered keyword should convert into a pill.Result set should get refresh.");
 			int resultsCountBeforeNewSearch=page_ChemicalSearchResults.getResultsCount();
 			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE NEW KEYWORD SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			List <String> getListOfPill=page_ChemicalSearchResults.getListOfPillBoxes();
 			for(String pillExpected:finalKeywordList) {
 			if(getListOfPill.contains(pillExpected))
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"newly entered keyword :"+pillExpected+" should converted into a pill", false);	
 			}
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"newly entered keyword :"+pillExpected+" should not converted into a pill", true);
 			}
 			}
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			int resultsCountAfterNewSearch=page_ChemicalSearchResults.getResultsCount();
 				if(resultsCountBeforeNewSearch!=resultsCountAfterNewSearch) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"Result set get refreshed with new keyword", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"Result set isnot get refreshed with new keyword", true);
 				}
 			
 			Application.Logger.endStep();
		}
 	 catch (Exception e) {
 		Application.Logger.addException(e.getMessage());
 		return flag = false;
 	}
 	return flag;
 }

 	/**CHEMEXP-743--Result set/Search page:"+XX more"* */
 	
 	@Test
 	public boolean validateXXMorePills(Controller Application, HashMap<String, String> input) {
 		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 		String searchText = input.get("searchtext");
 		List<String> finalKeywordList= Arrays.asList(input.get("keywords").split(","));
 		
 		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("2.Add new keywords to search box after the extracted keywords,so that content should more than 4 rows.","New keywords should be entered.");
 			int initialListOfPills=page_ChemicalSearchResults.getNoOfPillBoxes();
 			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
 			//page_ChemicalSearchResults.clickOnLinkFilters();
 			Application.waitTime(3);
 			int getListOfPills=page_ChemicalSearchResults.getNoOfPillBoxes();
 			if(getListOfPills>initialListOfPills) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"New keywords are entered successfully.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"New keywords are not entered.", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("3.Mouse hover on '+XX more'","Hover over on the '+XX more' pill should extend the box.");
 			page_ChemicalSearchResults.mouseOverToPillBox(getListOfPills);
 			int noOfPillsAfterMouseOver=page_ChemicalSearchResults.getNoOfPillBoxes();
 				if(noOfPillsAfterMouseOver>=getListOfPills) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"Hover over on the '+XX more' pill should extend the box.", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"Hover over on the '+XX more' pill should not extend the box.", true);
 				}
 				Application.Logger.endStep();
 				Application.Logger.addStep("4.Verify the cursor on last pill.","After the last pill, cursor must be shown to indicate that more keywords could be added.");
 				if(page_ChemicalSearchResults.isDisplayedTextBoxKeyword()) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"After the last pill, cursor shown to indicate that more keywords could be added.", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"After the last pill, cursor not shown to indicate that more keywords could be added.", true);
 				}
 			Application.Logger.endStep();
 		}
 	 catch (Exception e) {
 		Application.Logger.addException(e.getMessage());
 		return flag = false;
 	}
 	return flag;
 }
 	
 	/**CHEMEXP-744--Result set/Search page: Add pills more than what can fit in one row search box"
 	 * */
 	
 	@Test
 	public boolean validateByAddingPillsMorethanWhatCanFitInOneRowSearchBox(Controller Application, HashMap<String, String> input) {
 		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 		String searchText = input.get("searchtext");
 		List<String> finalKeywordList= Arrays.asList(input.get("keywords").split(","));
 		
 		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("2.Add pills more than what can fit in one row search box.","Should show '+XX more' pill .");
 			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
 			page_ChemicalSearchResults.hoverOnPhraseSection();
 			Application.waitTime(3);
 			int getNoOfPills=page_ChemicalSearchResults.getNoOfPillBoxes();
 			List<String> getListOfPills=page_ChemicalSearchResults.getListOfPillBoxes();
 			String lastPillText=getListOfPills.get(getListOfPills.size()-1);
 			if(lastPillText.contains("+")&&lastPillText.contains("more")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Last Pill should showing as "+lastPillText+" pill Name.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Last Pill should showing as +XX pill Name.", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("3.Mouse hover on '+XX more'","Hover over on the '+XX more' pill should extend the box.");
 			page_ChemicalSearchResults.mouseOverToPillBox(getNoOfPills);
 			int noOfPillsAfterMouseOver=page_ChemicalSearchResults.getNoOfPillBoxes();
 				if(noOfPillsAfterMouseOver>=getNoOfPills) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"Hover over on the '+XX more' pill should extend the box.", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"Hover over on the '+XX more' pill should not extend the box.", true);
 				}
 				Application.Logger.endStep();
 				Application.Logger.addStep("4.Verify the cursor on last pill.","After the last pill, cursor must be shown to indicate that more keywords could be added.");
 				if(page_ChemicalSearchResults.isDisplayedTextBoxKeyword()) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"After the last pill, cursor shown to indicate that more keywords could be added.", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"After the last pill, cursor not shown to indicate that more keywords could be added.", true);
 				}
 			Application.Logger.endStep();
 		}
 	 catch (Exception e) {
 		Application.Logger.addException(e.getMessage());
 		return flag = false;
 	}
 	return flag;
 }
 
 	/**CHEMEXP-746 * */ 	
 	@Test
 	public boolean validateClearAllX(Controller Application, HashMap<String, String> input) {
 		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 		String searchText = input.get("searchtext");
		String phrase="This invention provides a medical container comprising a body for storing a medicament, characterized in that the body of the medical container formed of a multilayered film comprising (i) \r\n" + 
 				"a layer formed of a linear polyolefin and (ii) a layer formed of a cyclic polyolefin adjacent to the layer (i), and the layer (i) formed of a linear polyolefin satisfies the following \r\n" + 
 				"conditions (a) and/or (b): condition (a) an amount of a liquid component, which stays after evaporation of an organic solvent after Soxhlet's extraction with the organic solvent, \r\n" + 
 				"of not more than 0.2% by weight; and condition (b) a degree of branching, per 1000 carbon atoms, of a component, which stays after evaporation of n-hexane after Soxhlet's extraction \r\n" + 
 				"with n-hexane, of not more than 50.  The multilayer film force (i) a layer also comprising a linear polyolefine, a layer (ii) a cyclic polyolefine adjacent to the layer (i), and (iii)\r\n" + 
 				"a polyester layer or an inorganic vapor-deposited thin film.  The peelable seal portion includes a force which is a portion where the innermost layers of the front seat and the rear \r\n" + 
 				"seat are weakly welded to each other, or another member formed from a resin having a low welding strength with the innermost layer, and the innermost layer.  The medical device contains \r\n" + 
 				"water for injection, physiological saline, glucose solution, amino acid solution, high-calorie infusion solution, fat emulsion, vitamin preparation, or metal element preparation as a \r\n" + 
 				"solution.  the printable layer means a layer capable of printing a medium such as printing ink when displaying characters or designs on a container. The barrier layer is a layer having \r\n" + 
 				"the performance of blocking the passage of moisture and Z or gas, for example, a gas noria layer. In the present invention, the printable layer may be, for example, a polyester resin layer \r\n" + 
 				"such as polyethylene terephthalate, polyamide, or polypropylene, or an inorganic deposited thin film in which an inorganic material such as silica, aluminiums\r\n" + 
 				"";
 		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("2.Click on X (Clear all).","Click on X (Clear all) must clear everything from search box including keywords and natural language text.");
 			int resultsCount=page_ChemicalSearchResults.getResultsCount();
 			page_ChemicalSearchResults.clickOnClearAllMark();
 			if(page_ChemicalSearchResults.getTextOfSerachBox().isEmpty()) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"cleared everything from search box including keywords and natural language text.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL," Not Cleared everything from search box including keywords and natural language text.", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("3.Verify the toast message","Should Show toast message to the user with undo functionality\\r\\n\" + \r\n" + 
 					"					\"Your search query is cleared out as action of 'clear all' . You could start a new search and generate new keywords or could undo to go back to previous state.\\r\\n\" + \r\n" + 
 					"					\"Action text: Undo.");
 			if(page_ChemicalSearchResults.isDisplayedToastMessage()) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Toast Message has been displayed.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Toast Message has not been displayed.", true);
 			}
 			if(page_ChemicalSearchResults.isDisplayedActionTextUndo()) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Undo Action text has been displayed.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Undo Action text has not been displayed.", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("4.Validate watermark on the search box","Should show below watermark to indicate the user that s/he can do a brand new natural language search from here also.\r\n" + 
 					"Watermark: 'Enter keywords, phrased or text blocks to search.'");
 			
 			if(page_ChemicalSearchResults.isDisplayedSearchBoxWithWaterMark()) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Water Mark with text as 'Watermark:Enter keywords, phrased or text blocks to search...' has been displayed.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Water Mark with text as 'Watermark:Enter keywords, phrased or text blocks to search...' has not been displayed..", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("5.Observe Search Icon and Result set","Search button must be grayed out.There should not be any change on the result set as new search has not been performed yet.");
 			if(page_ChemicalSearchResults.getColorOfButtonSerach().trim().equals("rgba(0, 0, 0, 0)")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Search button has been grayed out.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Search button has not been grayed out", true);
 			}
 			int resultsCountAfterClearAllButtonClick=page_ChemicalSearchResults.getResultsCount();
 			
 				if(resultsCountAfterClearAllButtonClick==resultsCount) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"There is no any change on the result set", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"There is a any change on the result set.", true);
 				}
 				Application.Logger.endStep();
 				Application.Logger.addStep("6.Click on undo","Action: Undo to bring the text in the box to previous state.");
 				page_ChemicalSearchResults.clickOnActionTextUndo();
 				if(page_ChemicalSearchResults.getTextOfSerachBox().equals(searchText)) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"Action: Undo to bring the text in the box to previous state.", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"Action: Undo not to bring the text in the box to previous state.", true);
 				}
 			Application.Logger.endStep();			
			Application.Logger.addStep("7.Enter 2000 characters and try to enter beyond limit on top section","Search box must accept up-to 2000 characters.\r\n" + 
 					"Box must not accept beyond supported limit.\r\n" + 
 					"Should Show red inline message below the box: Phrase: 2000/2000");
 			if(page_ChemicalSearchResults.getErrorMessagePhrase(phrase).trim().equalsIgnoreCase("Phrase: reached limit 2000 / 2000")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Search box not accepted up-to 2000 characters and Box not accepted beyond supported limit and Shown red inline message below the box: Phrase: 2000/2000.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Search box accepted morethan 2000 characters", true);
 			}
 			Application.Logger.endStep();			
			Application.Logger.addStep("8.Verify searched keywords are heighlighted in patent RS","Keyword should be heighlighted in patent RS");
 			page_ChemicalSearchResults.clickOnTabPatent();
 			int noOfTimesHightedOfGivenKeyword=page_ChemicalSearchResults.tabPatentSearch().getCountOfHighlightedTextInRS(searchText);
 				if(noOfTimesHightedOfGivenKeyword>0) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,noOfTimesHightedOfGivenKeyword+ "\t time the given keyword \t"+searchText+" are heighlighted in Record Set", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"keyword"+searchText+" are not heighlighted in Record Set", true);
 				}
 			Application.Logger.endStep();			
			Application.Logger.addStep("9.Switch to Literature tab ,then Verify searched keywords are heighlighted in RS","Keyword should be heighlighted in Literature RS");
 			page_ChemicalSearchResults.clickOnTabLiterature();
 			int noOfTimesHightedOfGivenKeywords=page_ChemicalSearchResults.tabLiteratureSearch().getCountOfHighlightedTextInRS(searchText);
 			if(noOfTimesHightedOfGivenKeywords>0) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,noOfTimesHightedOfGivenKeywords+ "\t time the given keyword \t"+searchText+" are heighlighted in Record view", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"keyword"+searchText+" \t are not heighlighted in Record view \t", true);
 				}
 			Application.Logger.endStep();
			}
 	 catch (Exception e) {
 		Application.Logger.addException(e.getMessage());
 		return flag = false;
 	}
 	return flag;
 }
 
 	/**CHEMEXP-753--Result set/Search page:Pills color on Bottom Section"
 	 * */
 	
 	@Test
 	public boolean validatePillsColorOnBottomSection(Controller Application, HashMap<String, String> input) {
 		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 		String searchText = input.get("searchtext");
 		
 		
 		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("2.Verify Pills box on the Bottom section and perform search","By default all the pills must be outlined by blue color which indicates optional.and Fiber should be heighlighted in Result set");
 			page_ChemicalSearchResults.clickOnTabPatent();
 			if(page_ChemicalSearchResults.isDisplayedPillBox(1)) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Looking for 1 st Pillbox has been displayed", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Looking Pillbox doesnot exist in result set", true);
 			}
 			if(page_ChemicalSearchResults.getColorOfPillBox(1).equals("state_0")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Pill box contains 'blue color' as Expected.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Pill box not contain 'blue color' as Expected.", true);
 			}
 			
 			int noOfTimesHightedOfGivenKeyword=page_ChemicalSearchResults.tabPatentSearch().getCountOfHighlightedTextInRS("fiber");
 			if(noOfTimesHightedOfGivenKeyword>0) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,noOfTimesHightedOfGivenKeyword+ "\t time the given keyword \t"+searchText+" are heighlighted in ResultSet", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"keyword"+searchText+" are not heighlighted in Result Set", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("3.Click on the word Fiber on bottom section and perform search","Blue color should turn to green color and Fiber should be heighlighted in Result set");
 			page_ChemicalSearchResults.clickOnPillBox(1);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if(page_ChemicalSearchResults.getColorOfPillBox(1).equals("state_1")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Pill box contains 'Green color' as Expected.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Pill box not contain 'Green color' as Expected.", true);
 			}
 			
 			int noOfTimesHightedOfGivenText=page_ChemicalSearchResults.tabPatentSearch().getCountOfHighlightedTextInRS("fiber");
 			if(noOfTimesHightedOfGivenText>0) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,noOfTimesHightedOfGivenText+ "\t time the given keyword \t"+searchText+" are heighlighted in ResultSet", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"keyword"+searchText+" are not heighlighted in Result Set", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("4.Click on the word Fiber on bottom section and perform search","Green color should turn to Red color and Fiber should not be heighlighted in Result set");
 			page_ChemicalSearchResults.clickOnPillBox(1);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if(page_ChemicalSearchResults.getColorOfPillBox(1).equals("state_2")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Pill box contains 'Red color' as Expected.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Pill box not contain 'Red color' as Expected.", true);
 			}
 			
 			int noOfTimesHightedOfGivenTextForRedColor=page_ChemicalSearchResults.tabPatentSearch().getCountOfHighlightedTextInRS("fiber");
 				if(noOfTimesHightedOfGivenTextForRedColor==0) 
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,noOfTimesHightedOfGivenTextForRedColor+ "\t time the given keyword \t"+searchText+" are heighlighted in ResultSet", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"keyword"+searchText+" are not heighlighted in Result Set", true);
 				}
 			Application.Logger.endStep();
 			Application.Logger.addStep("5.Click on the word Fiber on bottom section and perform search","Red color should turn to blue color and Fiber should be heighlighted in Result set");
 			page_ChemicalSearchResults.clickOnPillBox(1);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if(page_ChemicalSearchResults.getColorOfPillBox(1).equals("state_0")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Pill box contains 'blue color' as Expected.", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Pill box not contain 'blue color' as Expected.", true);
 			}
 			
 			int noOfTimesHightedOfGivenSearchText=page_ChemicalSearchResults.tabPatentSearch().getCountOfHighlightedTextInRS("fiber");
 			if(noOfTimesHightedOfGivenSearchText>0) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,noOfTimesHightedOfGivenSearchText+ "\t time the given keyword \t"+searchText+" are heighlighted in ResultSet", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"keyword"+searchText+" are not heighlighted in Result Set", true);
 			}
 			Application.Logger.endStep();
 		}
 	 catch (Exception e) {
 		Application.Logger.addException(e.getMessage());
 		return flag = false;
 	}
 	return flag;
 }
 	

 	/**CHEMEXP-747 * */ 
 	@Test
 	public boolean validateEditTextInBottomSection(Controller Application, HashMap<String, String> input) {
 		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 		String searchText = input.get("searchtext");
		List<String> finalKeywordList= Arrays.asList(input.get("keywords").split(","));
 		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("2.Type/paste keywords in bottom section.","Must be able to type/paste keywords in bottom section.");
 			page_ChemicalSearchResults.clickOnTabPatent();
 			page_ChemicalSearchResults.closeAllExistingKeyWords();
 			page_ChemicalSearchResults.AddKeyWordsBottomSection(finalKeywordList);
 			List<String> listOfPills=page_ChemicalSearchResults.getListOfPillBoxes();
 			for(String pill:listOfPills) 
 			{
 				if(!pill.equals(searchText))
 				{
 					Application.Logger.addsubStep(LogStatus.PASS,"Able to typed/pasted keyword :'"+pill+"' in bottom section.", false); 
 				} 
 				else 
 				{
 					Application.Logger.addsubStep(LogStatus.FAIL,"Not able to typed/pasted keywords in bottom section.", true);
 				}
 			}
 			Application.Logger.endStep();
			Application.Logger.addStep("3.Edit Keywords in the search box","Search Icon Must turn green/become enabled if something is edited in the search box");
 			page_ChemicalSearchResults.clickOnChemExpHomePage();
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText); 		
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			page_ChemicalSearchResults.setTextPhrase("Fiber");
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("4.type/insert/paste more text anywhere in-between the existing text in the top section.","Must be able to type/insert/paste more text anywhere in-between the existing text in the top section.");
 			page_ChemicalSearchResults.enterTextAnywhereInSearchBox("acid", 2);
 			Application.Logger.addsubStep(LogStatus.INFO, "Successfuly Entered/Inserted text at specified position", false);
 			Application.Logger.endStep();
 			Application.Logger.addStep("5.select and delete selected text in top section","selected text should be selected in top section.");
 			page_ChemicalSearchResults.deleteText();
 			String expSearchText=page_ChemicalSearchResults.getTextOfSerachBox();
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
 }

 	/**CHEMEXP-749* */
	@Test
	public boolean validateDoNotRetainVoteCasteForNewSearch(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList= Arrays.asList(input.get("keywords").split(","));
		
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			int resultsCountBeforeNewSearch=page_ChemicalSearchResults.getResultsCount();
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on thumbs up for few records.","Thumbs up icon should be changed to solid green for the records");
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.waitTime(3);
			for(int i=1;i<4;i++) 
			{
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Click on thumbs Down for few records.","Thumbs Down icon should be changed to solid green for the records");
			for(int j=5;j<9;j++)
			{
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Enter the text in the search text box and click on search icon","Results must be refreshed");
			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			int resultsCountAfterNewSearch=page_ChemicalSearchResults.getResultsCount();
			if(resultsCountBeforeNewSearch!=resultsCountAfterNewSearch) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Result set get refreshed with new search", false); 
			} 
			else 
			{
				Application.Logger.addsubStep(LogStatus.FAIL,"Result set is not get refreshed with new search", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.Verify the thumbs up and thumbs down for the records","Thumbs up and thumbs down should not be retained for the records");
			for(int i=1;i<4;i++) 
			{
				if(!page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsUpWithFillColor(i)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs up is not be retained for the record :"+i, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs up is be retained for the record :"+i, true);
				}
			}
			for(int j=5;j<9;j++)
			{
				if(!page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsDownWithFillColor(j)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs Down is not be retained for the record :"+j, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs Down is not be retained for the record :."+j, true);
				}
			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.Retain Vote Caste Navigate Between Patent And Literature Tabs.Click on thumbs up for few records", "Thumbs up icon should be changed to solid green for the records");
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.waitTime(3);
			for(int i=1;i<4;i++) 
			{
			  page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.Click on thumbs Down for few records.","Thumbs Down icon should be changed to solid green for the records");
			for(int j=5;j<9;j++)
			{
			  page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.Click on the Literature Tab","Literature Record set should be displayed.");
			page_ChemicalSearchResults.clickOnTabLiterature();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("9.Click on thumbs UP for few records","Thumbs up icon should be changed to solid green for the records.");
			for(int i=1;i<4;i++) 
			{
			  page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("10.Click on thumbs Down for few records.","Thumbs Down icon should be changed to solid green for the records");
			for(int j=5;j<9;j++)
			{
			  page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("11.Verify the thumbs up and thumbs down for the records in Patent Tabs","Thumbs up and thumbs down should be retained for the records in Patent Tab");
			page_ChemicalSearchResults.clickOnTabPatent();
			for(int i=1;i<4;i++) 
			{
				if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsUpWithFillColor(i)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs up is be retained for the record :"+i, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs up is not be retained for the record :"+i, true);
				}
			}
			for(int j=5;j<9;j++)
			{
				if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsDownWithFillColor(j)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs Down is be retained for the record :"+j, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs Down is not be retained for the record :."+j, true);
				}
			}
			Application.Logger.endStep();
			Application.Logger.addStep("12.Verify the thumbs up and thumbs down for the records in Literature Tab","Thumbs up and thumbs down should be retained for the records in Literature Tab");
			page_ChemicalSearchResults.clickOnTabLiterature();
			for(int i=1;i<4;i++) 
			{
				if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedButtonThumsUpWithFillColor(i)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs up is be retained for the record :"+i, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs up is not be retained for the record :"+i, true);
				}
			}
			for(int j=5;j<9;j++)
			{
				if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedButtonThumsDownWithFillColor(j)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs Down is be retained for the record :"+j, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs Down is not be retained for the record :."+j, true);
				}
			}
			Application.Logger.endStep();	
			
		}
		 catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}
			
			
	 /**CHEMEXP-750* */
	
	@Test
	public boolean validateDoNotRetainVoteCasteForSameSearch(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList= Arrays.asList(input.get("keywords").split(" "));
		boolean flag = true;
		
		try {
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			int resultsCountBeforeNewSearch=page_ChemicalSearchResults.getResultsCount();
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on thumbs up for few records.","Thumbs up icon should be changed to solid green for the records");
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.waitTime(3);
			for(int i=1;i<4;i++) 
			{
			  page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Click on thumbs Down for few records.","Thumbs Down icon should be changed to solid green for the records");
			for(int j=5;j<9;j++)
			{
			  page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Enter the text in the search text box and click on search icon","Results must be refreshed");
			page_ChemicalSearchResults.closeAllExistingKeyWords();
			page_ChemicalSearchResults.setFirstKeyWord(finalKeywordList);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			int resultsCountAfterNewSearch=page_ChemicalSearchResults.getResultsCount();
			if(resultsCountBeforeNewSearch==resultsCountAfterNewSearch) 
			{
				Application.Logger.addsubStep(LogStatus.PASS,"Result set get refreshed with new search", false); 
			} 
			else 
			{
				Application.Logger.addsubStep(LogStatus.FAIL,"Result set is not get refreshed with new search", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.Verify the thumbs up and thumbs down for the records","Thumbs up and thumbs down should not be retained for the records");
			for(int i=1;i<4;i++) 
			{
				if(!page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsUpWithFillColor(i)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs up is not be retained for the record :"+i, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs up is be retained for the record :"+i, true);
				}
			}
			for(int j=5;j<9;j++)
			{
				if(!page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsDownWithFillColor(j)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs Down is not be retained for the record :"+j, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs Down is not be retained for the record :."+j, true);
				}
			}
			Application.Logger.endStep();
			
			
			Application.Logger.addStep("6.Retain vote caste for filters - Click on Patent tab and Click on thumbs UP for few records","Thumbs up icon should be changed to solid green for the records.");
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			page_ChemicalSearchResults.clickOnTabPatent();
			for(int i=1;i<4;i++) 
			{
			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.Click on thumbs Down for few records.","Thumbs Down icon should be changed to solid green for the records");
			for(int j=5;j<9;j++)
			{
			  page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.Click on Literature tab and Click on thumbs UP for few records","Thumbs up icon should be changed to solid green for the records.");
			page_ChemicalSearchResults.clickOnTabLiterature();
			for(int i=1;i<4;i++) 
			{
			  page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("9.Click on thumbs Down for few records.","Thumbs Down icon should be changed to solid green for the records");
			for(int j=5;j<9;j++)
			{
			  page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("10.Click on fliter link and Select few filter fields and click on Apply Filters button","Should open filter with all the filter fields in collapsed state. and Results and charts must be refreshed");
			if(page_ChemicalSearchResults.isDisplayedFilterFieldsInCollapsedState())
			{
			Application.Logger.addsubStep(LogStatus.PASS,"All the fields in filter are in collapsed state", false); 
			} 
			else 
			{
			Application.Logger.addsubStep(LogStatus.FAIL,"All the fields in filter are not in collapsed state", true); 
			}
			page_ChemicalSearchResults.clickOnAnyCheckboxBasedOnFiltersName("Publication year",7);
			//page_ChemicalSearchResults.clickOnButtonApply();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("11.Verify the thumbs up and thumbs down for the records in Patent Tabs","Thumbs up and thumbs down should be retained for the records in Patent Tab");
			page_ChemicalSearchResults.clickOnTabPatent();
			for(int i=1;i<4;i++) 
			{
				if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsUpWithFillColor(i)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs up is be retained for the record :"+i, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs up is not be retained for the record :"+i, true);
				}
			}
			for(int j=5;j<9;j++)
			{
				if(page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsDownWithFillColor(j)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs Down is be retained for the record :"+j, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs Down is not be retained for the record :."+j, true);
				}
			}
			Application.Logger.endStep();
			Application.Logger.addStep("12.Verify the thumbs up and thumbs down for the records in Literature Tab","Thumbs up and thumbs down should be retained for the records in Literature Tab");
			page_ChemicalSearchResults.clickOnTabLiterature();
			for(int i=1;i<=1;i++) 
			{
				if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedButtonThumsUpWithFillColor(i)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs up is be retained for the record :"+i, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs up is not be retained for the record :"+i, true);
				}
			}
			for(int j=2;j<=2;j++)
			{
				if(page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedButtonThumsDownWithFillColor(j)) 
				{
					Application.Logger.addsubStep(LogStatus.PASS,"Thumbs Down is be retained for the record :"+j, false); 
				} 
				else 
				{
					Application.Logger.addsubStep(LogStatus.FAIL,"Thumbs Down is not be retained for the record :."+j, true);
				}
			}
			Application.Logger.endStep();
		}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}
 	
	 	
	/**CHEMEXP-745*/
 	
 	@Test
 	public boolean validateSearchIcon(Controller Application, HashMap<String, String> input) {
 		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 		String searchText = input.get("searchtext");
 		List<String> finalKeywordList= Arrays.asList(input.get("keywords").split(","));
		String lengthySearchText="Novelty\r\n" + 
 				"A superabsorbent polymer comprises a base resin powder containing a crosslinked polymer of a water-soluble ethylenically unsaturated monomer including partially neutralized acidic group(s), and pores having diameter of 1 μ m or more, formed on the base resin powder, and has apparent density of more than 0.55 g/ml. The crosslinked polymer comprises layered silicate-based particles dispersed in the crosslinked structure";
		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("2.Edit Keywords in the search box","Search Icon Must turn green/become enabled if something is edited in the search box");
 			page_ChemicalSearchResults.clickOnPillBox(1);
 			if(page_ChemicalSearchResults.getColorOfSerachIcon().equals("false")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Search Icon is displaying with 'Green' color", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL," Search Icon is not displaying with 'Green' color", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("3.Remove few Pills on the search box","Search Icon Must turn green/become enabled if pill is Removed in the search box");
 			page_ChemicalSearchResults.removePillBox(1);
 			if(page_ChemicalSearchResults.getColorOfSerachIcon().equals("false")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Search Icon is displaying with 'Green' color", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL," Search Icon is not displaying with 'Green' color.", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("4.Add keyword in the search box","Search Icon Must turn green/become enabled if keyword is inserted in the search box");
 			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
 			if(page_ChemicalSearchResults.getColorOfSerachIcon().equals("false")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Search Icon is displaying with 'Green' color", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"Search Icon is not displaying with 'Green' color", true);
 			}
 			Application.Logger.endStep();			
			Application.Logger.addStep("5.Make changes on Top section","while making change in top section,Botton Section will not changeble and gray out the bottom section");
 			page_ChemicalSearchResults.clickOnChemExpHomePage();
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText); 		
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			page_ChemicalSearchResults.setTextPhrase(lengthySearchText);
 			page_ChemicalSearchResults.clickOnPillBox(1);
 			page_ChemicalSearchResults.isDisabledBottomSection();
 			if(page_ChemicalSearchResults.getColorOfButtomSection().equals("rgba(255, 255, 255, 1)")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Bottom section is  grayed out", false); 
 			} 
 			else 
 			{
 			Application.Logger.addsubStep(LogStatus.FAIL,"Bottom section is not grayed out", true);
 			}
 			Application.Logger.endStep();
 			Application.Logger.addStep("6.Make changes on Botton section","The top section will not change when make changes in bottom section (it includes removing all the keywords) ");
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			Application.waitUntilFectchRecordProgressBarToDisappears();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			}
 			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
 			if(page_ChemicalSearchResults.isDisabledTopSection()) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Top section is not allow to changable.", false); 
 			} 
 			else 
 			{
 			   Application.Logger.addsubStep(LogStatus.FAIL,"Top section is allow to changable.", true);
 			}
 			if(page_ChemicalSearchResults.getColorOfTopSection().equals("rgba(0, 0, 0, 0)")) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"Top section is  grayed out", false); 
 			} 
 			else 
 			{
 			Application.Logger.addsubStep(LogStatus.FAIL,"Top section is not grayed out", true);
 			}
 			Application.Logger.endStep();
 		}
 	 catch (Exception e) {
 		Application.Logger.addException(e.getMessage());
 		return flag = false;
 	}
 	return flag;
 }
  	
 	/**CHEMEXP-751--Patent-PDF not available*/
	
	public boolean validatePdfNotAvailable(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			String CurrentWindowHandleID = Application.driver.getWindowHandle();
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE AND PDF GENERATION ","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND PDF SHOULD GET GENERATED");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on PDF","PDF is not available message displays");
			
			page_ChemicalSearchResults.clickOnLinkPdf();
			Application.waitTime(12);
			Application.switchToOtherWindow(CurrentWindowHandleID);
			String pdfText=page_ChemicalSearchResults.getTextPdfNotAvailable();
		    if (pdfText.equals("PDF is not available"))
				Application.Logger.addsubStep(LogStatus.PASS, "PDF window opens and PDF is not available message has been displayed",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "PDF is opened instead of PDF is not available message.", true);
			Application.Logger.endStep();
		}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}
 	
	// CHEMEXP-758
	
	

	
	public boolean validateFilterDataForDifferentFields(Controller Application, HashMap<String, String> input) {
		boolean flag = true;

		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			String errorMsg = input.get("ErrorMsg");
			String errorMsg2 = input.get("ErrorMsg2");
			Date date = new Date();
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			String dateFormat = localDate.getYear() + "-" + (localDate.getMonthValue() + 23) + "-"
					+ (localDate.getDayOfMonth() + 35);
			int currentDay = localDate.getDayOfMonth();
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.clickOnExpandDropdown();
			page_ChemicalSearchResults.clickOnFiltersPublicationDate();
			if (page_ChemicalSearchResults.getDateFormatFromDateInput().matches("YYYY-MM-DD")) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"date format for FromDateInput matches with the given format", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"date format for FromDateInput has not matched with the given format", true);
			}
			if (page_ChemicalSearchResults.getDateFormatToDateInput().matches("YYYY-MM-DD")) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"date format for ToDateInput matches with the given format", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"date format for ToDateInput has not matched with the given format", true);
			}

			if (page_ChemicalSearchResults.isDisplayedCalenderMarkInFromDateInput()) {
				Application.Logger.addsubStep(LogStatus.PASS, "calender mark is displayed in from date input", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "calender mark is not displayed in from date input",
						true);
			}

			if (page_ChemicalSearchResults.isDisplayedCalenderMarkInToDateInput()) {
				Application.Logger.addsubStep(LogStatus.PASS, "calender mark is displayed in to date input", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "calender mark is not displayed in to date input", true);
			}

			page_ChemicalSearchResults.setDateInFromDateInput(dateFormat);
			Application.waitTime(2);

			if (errorMsg.contains(page_ChemicalSearchResults.getTextErrorMessagePubDate().trim())) {
				Application.Logger.addsubStep(LogStatus.PASS, "error message is displayed as date is invalid", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "error message is not displayed with date is invalid",
						true);
			}
			page_ChemicalSearchResults.setDateInToDateInput(dateFormat);
			Application.waitTime(2);
			if (errorMsg.contains(page_ChemicalSearchResults.getTextErrorMessagePubDate().trim())) {
				Application.Logger.addsubStep(LogStatus.PASS, "error message is displayed as date is invalid", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "error message is not displayed with date is invalid",
						true);
			}
			page_ChemicalSearchResults.clickOnCalenderIconFromDateInput();
			page_ChemicalSearchResults.selectDateFromPublicationDateCalender(10);
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnCalenderIconToDateInput();
			page_ChemicalSearchResults.selectDateFromPublicationDateCalender(8);
			Application.waitTime(2);
			if (errorMsg2.contains(page_ChemicalSearchResults.getTextErrorMessagePubDate().trim())) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"error message is displayed as the todate is lesser than from date", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"error message is not displayed as the todate is lesser than from date", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	
	//CHEMEXP-759
	
	/*
	 
	 Steps:
1.Login to the application
2.Perform search with "antibacterial polyethylene resin containing transition metals"
3.Click on the PDF icon
Expected:
1.Should logged in successfully
2.Search is performed
3.PDF is opened
	 */
	
	public boolean validatePdfFromResultSet(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			String CurrentWindowHandleID = Application.driver.getWindowHandle();
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE AND PDF GENERATION ",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND PDF SHOULD GET GENERATED");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.clickOnLinkPdf();
			Application.waitTime(12);
			Application.switchToGivenWindow("PDF Window", "blob:https://qa-cloud.clarivate.com", CurrentWindowHandleID);
			if (Application.driver.getCurrentUrl().contains("blob"))
				Application.Logger.addsubStep(LogStatus.PASS, "PDF window opens and Navigated to that successfully",
						false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "PDF window opens has not opened", true);
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	
	// CHEMEXP-442|CHEMEXP-444|CHEMEXP-445|CHEMEXP-446
	
	/*
	 Steps:
1.Login to the application
2.Perform search with "antibacterial polyethylene resin containing transition metals"
3.Verify the assignees and scroll bar in Who are the major players?section
4.Click on collapse arrow
Expected:
1.Should logged in successfully
2.Search is performed
3.Should show 8 assignees in one frame with scroll to see more 
4.Should collapse players
	 */
	public boolean validateWhoAreTheMajorPlayersAndInventors(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep("1. SEARCH AND VERIFY THE Major Players And Inventors Section","USER SHOULD SEE Records with scroll bar And able to Collapse ");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
		    page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			} 
			page_ChemicalSearchResults.clickOnTabPatent();
			int assigneePlayersCountForPatent=page_ChemicalSearchResults.tabPatent().numberOfMajorPlayersAssigneeDisplayedForPatent();
			if (assigneePlayersCountForPatent==8) {
				Application.Logger.addsubStep(LogStatus.PASS, "8 records gets displayed successfully for patent page", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "the records has not displayed successfully", true);
			} 
			if(page_ChemicalSearchResults.isDisplayedScrollBarForWhoAreTheMajorPlayers())
				Application.Logger.addsubStep(LogStatus.PASS, "scroll bar displayed successfully for patent page",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "scroll bar has not displayed successfully for patent page", true);
			
			if(page_ChemicalSearchResults.isDisplayedWhoAreTheMajorPlayersInCollapsedState())
				Application.Logger.addsubStep(LogStatus.PASS, "WhoAreTheMajorPlayers collapsed successfully",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "WhoAreTheMajorPlayers has not collapsed ", true);
			
			int assigneeInventorsCountForPatent=page_ChemicalSearchResults.tabPatent().numberOfMajorInventorsAssigneeDisplayedForPatent();
			if (assigneeInventorsCountForPatent==8) {
				Application.Logger.addsubStep(LogStatus.PASS, "numberOfMajorInventors count displayed successfully", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "numberOfMajorInventors displayed successfully", true);
			} 
			if(page_ChemicalSearchResults.isDisplayedScrollBarForWhoAreTheMajorInventors())
				Application.Logger.addsubStep(LogStatus.PASS, "scroll bar is present for WhoAreTheMajorInventors",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "scroll bar is not present for WhoAreTheMajorInventors", true);
			
			if(page_ChemicalSearchResults.isDisplayedWhoAreTheMajorInventorsInCollapsedState())
				Application.Logger.addsubStep(LogStatus.PASS, "WhoAreTheMajorInventors is in collapsed state",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL,"WhoAreTheMajorInventors is not in collapsed state", true);
			
			page_ChemicalSearchResults.clickOnTabLiterature();
			int assigneePlayersCountForLiterature=page_ChemicalSearchResults.tabLiterature().numberOfMajorPlayersAssigneeDisplayedForLiterature();
			if (assigneePlayersCountForLiterature==8) {
				Application.Logger.addsubStep(LogStatus.PASS, "numberOfMajorPlayers is displayed in literature tab", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "numberOfMajorPlayers is not displayed in literature tab", true);
			} 
			if(page_ChemicalSearchResults.isDisplayedScrollBarForWhoAreTheMajorPlayers())
				Application.Logger.addsubStep(LogStatus.PASS, "scroll bar is present for WhoAreTheMajorPlayers",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "scroll bar is not present for WhoAreTheMajorPlayers", true);
			
			if(page_ChemicalSearchResults.isDisplayedWhoAreTheMajorPlayersInCollapsedState())
				Application.Logger.addsubStep(LogStatus.PASS,"WhoAreTheMajorPlayers is in collapsed state",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "WhoAreTheMajorPlayers is not in collapsed state", true);
			
			int assigneeInventorsCountForLiterature=page_ChemicalSearchResults.tabLiterature().numberOfMajorInventorsAssigneeDisplayedForLiterature();
			if (assigneeInventorsCountForLiterature==8) {
				Application.Logger.addsubStep(LogStatus.PASS, "numberOfMajorInventors count displayed successfully", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "numberOfMajorInventors count is not displayed", true);
			} 
			if(page_ChemicalSearchResults.isDisplayedScrollBarForWhoAreTheMajorInventors())
				Application.Logger.addsubStep(LogStatus.PASS, "scroll bar is present for WhoAreTheMajorInventors",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "scroll bar is not present for WhoAreTheMajorInventors", true);
			
			if(page_ChemicalSearchResults.isDisplayedWhoAreTheMajorInventorsInCollapsedState())
				Application.Logger.addsubStep(LogStatus.PASS, "WhoAreTheMajorInventors is in collapsed state",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "WhoAreTheMajorInventors is not in collapsed state", true);
			
			Application.Logger.endStep();
		}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}
	
	
	//CHEMEXP-452
	/*
	 
	 Steps:
1.Login to the application
2.Perform search with "no data available"
3.Switch to literature tab
4.Perform same operation for Literature
Expected:
1.Should logged in successfully
2.Search is performed
3.Hmmm,we are not getting any results message should display
4.Hmmm,we are not getting any results message should display*/
	 
	 
	 //CHEMEXP-764
	
	public boolean validatePatentAndLiteratureNoResultsPage(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH WITH INVALID SEARCH TEXT TAKES THE USER TO THE ERROR PAGE",
					" Error Displayed Successfully");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			String errorMsg = input.get("ErrorMsg");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			String actualErrorMsg = page_ChemicalSearchResults.getTextNoResultsPage();
			//page_ChemicalSearchResults.clickOnTabPatent();
			if (errorMsg.contentEquals(actualErrorMsg))
				Application.Logger.addsubStep(LogStatus.PASS, "error message displayed successfully in patent page",
						false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL,
						"error message has not displayed successfully in patent page", true);
			page_ChemicalSearchResults.clickOnTabLiterature();
			Application.waitTime(2);
			if (errorMsg.contains(actualErrorMsg))
				Application.Logger.addsubStep(LogStatus.PASS, "error message displayed successfully in Literature page",
						false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL,
						"error message has not displayed successfully in Literature page", true);

			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	/*CHEMEXP-760*/

	public boolean validatePatentAndLiteratureResultSetPagination(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE AND Pagination ","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND PAGINATION SHOULD BE VERIFIED");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			} 	
			page_ChemicalSearchResults.clickOnTabPatent();
			String dropdownDefaultValue=page_ChemicalSearchResults.tabPatent().getValueFromItemsPerPageDropdown();
			if(dropdownDefaultValue.equals("20"))
				Application.Logger.addsubStep(LogStatus.PASS, "default value for items per page dropdown is 20",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "default value for items per page dropdown is other than 20", true);
			
			if(page_ChemicalSearchResults.tabPatent().isDisabledPreviousPageArrow())
				Application.Logger.addsubStep(LogStatus.PASS, "previous page arrow is disabled",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "previous page arrow is not disabled", true);
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("10");
			String tenItemsPerPage=page_ChemicalSearchResults.tabPatent().getValueFromItemsPerPageDropdown();
			if(tenItemsPerPage.equals("10"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 10 records per page",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 10 records per page", true);
			
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("50");
			String fiftyItemsPerPage=page_ChemicalSearchResults.tabPatent().getValueFromItemsPerPageDropdown();
			if(fiftyItemsPerPage.equals("50"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 50 records per page",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 50 records per page", true);
			
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("100");
			String hundredItemsPerPage=page_ChemicalSearchResults.tabPatent().getValueFromItemsPerPageDropdown();
			if(hundredItemsPerPage.equals("100"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 100 records per page",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 100 records per page", true);
			
			String paginatorRange=page_ChemicalSearchResults.tabPatent().getTextPaginatorRange();
			String[] paginatorValue=paginatorRange.split("of");
			if(paginatorValue[1].trim().equals("1000"))
				Application.Logger.addsubStep(LogStatus.PASS, "max record that can display is 1000",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "max record that can display is not 1000", true);	
			
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("100");
			page_ChemicalSearchResults.tabPatent().clickOnArrowNextPageTillLastPage();
			
			if(page_ChemicalSearchResults.tabPatent().isDisabledNextPageArrow())
				Application.Logger.addsubStep(LogStatus.PASS, "next page arrow is disabled",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "next page arrow is not disabled", true);
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("20");
			
			page_ChemicalSearchResults.clickOnTabLiterature();
				
			if(dropdownDefaultValue.equals("20"))
				Application.Logger.addsubStep(LogStatus.PASS, "default value for items per page dropdown is 20",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "default value for items per page dropdown is other than 20", true);
			
			if(page_ChemicalSearchResults.tabPatent().isDisabledPreviousPageArrow())
				Application.Logger.addsubStep(LogStatus.PASS, "previous page arrow is disabled",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "previous page arrow is not disabled", true);
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("10");
		
			if(tenItemsPerPage.equals("10"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 10 records per page",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 10 records per page", true);
			
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("50");
			if(fiftyItemsPerPage.equals("50"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 50 records per page",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 50 records per page", true);
			
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("100");
			if(hundredItemsPerPage.equals("100"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 100 records per page",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 100 records per page", true);
			if(paginatorValue[1].trim().equals("1000"))
				Application.Logger.addsubStep(LogStatus.PASS, "max record that can display is 1000",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "max record that can display is not 1000", true);	
			
			page_ChemicalSearchResults.tabPatent().selectItemsPerPageFromDropDown("100");
			page_ChemicalSearchResults.tabPatent().clickOnArrowNextPageTillLastPage();
			
			if(page_ChemicalSearchResults.tabPatent().isDisabledNextPageArrow())
				Application.Logger.addsubStep(LogStatus.PASS, "next page arrow is disabled",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "next page arrow is not disabled", true);
			
			Application.Logger.endStep();
		}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}
	/*CHEMEXP-761*/

	
	public boolean validateFeedbackFunctionality(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VERIFY THE LANDING PAGE AND Feedback functionality ","Feedback should be submitted successfully");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
			page_ChemicalSearchLandingPage.clickOnLinkFeedback();
			if(page_ChemicalSearchLandingPage.isDisplayedFieldDropdown())
				Application.Logger.addsubStep(LogStatus.PASS, "dropdown is displayed",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "dropdown is not displayed", true);
			if(page_ChemicalSearchLandingPage.isDisplayedButtonSubmitFeedback())
				Application.Logger.addsubStep(LogStatus.PASS, "button submit feedback is displayed",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "button submit feedback is not displayed", true);
			if(page_ChemicalSearchLandingPage.isDisplayedLinkContactUs())
				Application.Logger.addsubStep(LogStatus.PASS, "link contact us is displayed",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "link contact us is not displayed", true);
			page_ChemicalSearchLandingPage.selectFeedbackValueFromDropdown("Search");
			page_ChemicalSearchLandingPage.enterValueInFeedbackArea("good");
			page_ChemicalSearchLandingPage.clickOnButtonSubmitFeedback();
			if(!page_ChemicalSearchLandingPage.isDisplayedDialogBoxFeedback())
				Application.Logger.addsubStep(LogStatus.PASS, "feedback submitted successfully and the dialog box is closed",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "feedback has not submitted successfully and the dialog box is not closed", true);
			
			Application.Logger.endStep();
		}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}
	
	
	public boolean validateLiteratureRecordViewField(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE AND Literature Record View Field ","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND All The Literature record view fields should display.");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			} 	
		    page_ChemicalSearchResults.clickOnTabLiterature();
			page_ChemicalSearchResults.tabLiterature().clickOnTitle(1);
			page_ChemicalSearchResults.tabLiterature().isDisplayedLiteratureRecordViewField();
			if(page_ChemicalSearchResults.tabLiterature().isDisplayedLiteratureRecordViewField())
				Application.Logger.addsubStep(LogStatus.PASS, "all the fields in LiteratureRecordView is displayed ",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "all the fields in LiteratureRecordView is not displayed", true);
			if(page_ChemicalSearchResults.tabLiterature().isDisplayedLabelOrganization())
				Application.Logger.addsubStep(LogStatus.PASS, "label organization is displayed ",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "label organization is not displayed", true);
			if(page_ChemicalSearchResults.tabLiterature().isDisplayedLabelOrganization())
				Application.Logger.addsubStep(LogStatus.PASS, "label organization is displayed ",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "label organization is not displayed", true);
			
			if(page_ChemicalSearchResults.tabLiterature().isValueForLabelOrganizationSeparatedBySemicolon())
				Application.Logger.addsubStep(LogStatus.PASS, "value in label organization is separated by semicolon",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "value in label organization is not separated by semicolon", true);
			if(page_ChemicalSearchResults.tabLiterature().isDisplayedLabelOrganizationAddress())
				Application.Logger.addsubStep(LogStatus.PASS, "label organization address is displayed",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "label organization address is not displayed", true);
			if(page_ChemicalSearchResults.tabLiterature().isDisplayedLabelLanguage())
				Application.Logger.addsubStep(LogStatus.PASS, "label language is displayed",false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "label language is not displayed", true);
			
			Application.Logger.endStep();
		}
	 catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}
	
	/*CHEMEXP-762
*/
	public boolean validateFilterSelectDeselectCheckbox(Controller Application, HashMap<String, String> input) {
		boolean flag = true;

		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE AND Filters Option ",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND Filters option are verified");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			String[] filtersForExpandCollapse = { "Publication country/region", "Publication year", "Assignee",
					"Inventor", "Priority country/region", "Priority year", "Dead/Alive", "Publication Date" };
			List<String> filterListForExpandCollapse = Arrays.asList(filtersForExpandCollapse);
			String[] filtersForCheckbox = { "Publication country/region", "Publication year", "Assignee", "Inventor",
					"Priority country/region", "Priority year", "Dead/Alive" };
			List<String> filtersListForCheckbox = Arrays.asList(filtersForCheckbox);
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
        page_ChemicalSearchResults.clickOnExpandDropdown();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/*****
	 * 
	 *CHEMEXP-754--Filters-Clear all Filters
	 */
	public boolean validateClearAllFilters(Controller Application, HashMap<String, String> input) {
		boolean flag = true;

		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE AND Filters Option ",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND Filters option are verified");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			
			Application.Logger.addStep(
					"2.Expand the publication country/region section and click on Select all  ",
					"Each filter is expandable/collapsible and All the items must get selected");
			page_ChemicalSearchResults.clickOnExpandDropdown();	
			page_ChemicalSearchResults.tabPatent().clickOnButtonSelectAllForGivenFilter("Publication country/region");
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.tabPatent().isEnabledButtonClearAll()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ClearAll button is in enable mode", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ClearAll button is in disable mode", true);
			}
			Application.Logger.endStep();
			
			Application.Logger.addStep("4.Now Click on clear all filters",
					".It must clear all applied filters. It must clear all selections in each field the results are filtered on. Clear all filters will be disabled until a selection is made. Clear all will \"Reset\" and bring the Result set to initial state (result set to the state before the filters were applied).");
			page_ChemicalSearchResults.tabPatent().clickOnButtonClearAllFilters();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.tabPatent().isEnabledButtonClearAll()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ClearAll button is in Disable mode", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ClearAll button is in Enable mode", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	
	/*****
	 * 
	 *CHEMEXP-756--Filters-Clicking on X from pill box
	 */
	public boolean validateFiltersClickingOnXFromPillBox(Controller Application, HashMap<String, String> input) {
		boolean flag = true;

		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE AND Filters Option ",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND Filters option are verified");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"2.Expand the Filter section and click on Select all for the Assignee and Publication year",
					"Each filter is expandable/collapsible and All the items must get selected");
			page_ChemicalSearchResults.clickOnExpandDropdown();
			page_ChemicalSearchResults.tabPatent().clickOnButtonSelectAllForGivenFilter("Publication country/region");
			int beforeResultsCount = page_ChemicalSearchResults.getResultsCount();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			
			Application.Logger.addStep("4.click on 'x' next to item in pill component",
					"Pill component should be removed and should unselect the that item in the filters panel The results will no longer be filtered by that item. Results/charts will be redrawn.");
			page_ChemicalSearchResults.clickOnXMark();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			int AfterFilterChangeResultsCount = page_ChemicalSearchResults.getResultsCount();
			if (AfterFilterChangeResultsCount != beforeResultsCount) {
				Application.Logger.addsubStep(LogStatus.PASS, "Results get refreshed after removed filter", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Results are not get refreshed ", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}
/*
 CHEMEXP-755

 Pre-requiste: Login to the application
Steps:
1. Enter the keywords or natural language block of text for their search criteria.
2.Enter upto 2000 characters
3.Enter/try to insert more than 2000 characters
4.Copy and Paste charaters to search box
Expected:
1.Must accept keywords or natural language block of text for their search criteria.
2.Search box must accept up-to 2000 characters
3.Once the limit is reached, should display 2000/2000 in red to indicate user cannot enter beyond this limit.
4.Characters should be pasted */
 	
 	@Test
 	public boolean validateLimitationOfCharactersInLandingSearchBox(Controller Application, HashMap<String, String> input) {
 		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 		String searchText = input.get("searchtext");
 		int StringLength;
 		String expErrorMessage = "2000 / 2000";
 		String actualErrorMessage, expTextMessage;
 		String phrase="A multilayer container, the container comprising:an outer layer defining an exterior surface,"
 				+ " an inner layer defining an interior surface and an interior space, and a clear barrier layer "
 				+ "disposed between the outer layer and the inner layer, wherein:the inner layer and the outer layer"
 				+ "  comprise poly(ethylene terephthalate) (PET);each of the outer layer and the inner layer "
 				+ "independently has a thickness from about 0.2 mm to about 1.2 mm; andthe barrier layer comprises a "
 				+ "poly(ethylene-2,5-furandicarboxylate) (PEF) polyester or co-polyester with long-chain branching"
 				+ ",wherein the PEF polyester or co-polyester has a weight average molecular weight (Mw) of from about"
 				+ " 10800 to about 62000 and an intrinsic viscosity (I.V.) of about 0.25 dL/g or greater, wherein the"
 				+ " multilayer container has an oxygen permeability less than about 600 cm3 (STP)-mil/m2-d-atm at 23°C"
 				+ " and a carbon dioxide permeability less than about 170 cm3 (STP)-mil/m2-d-atm at 23°C, and wherein"
 				+ " the multilayer container has a delamination energy per area.Graphene colloids in predispersions "
 				+ "using a magnet for mixing  A process for producing rigid polyurethane foams by reaction of At least "
 				+ "one organic Polyisocyanate  and At least one polyol component comprising Blowing agent mixture "
 				+ "comprising water and halogenated alkenes Wherein the amount of water is at least 1.80 mol/kg of "
 				+ "polyol component.  The amount of halogenated alkene is at most 2.00 mol/kg of polyol component. "
 				+ "It increases the adherence and reducing the thermal conductivity of rigid polyurethane foams formed."
 				+ " transition metal catalyst for butadiene polymerisation This invention provides a medical container"
 				+ " comprising a body for storing a medicament, characterized in that the body of the medical container"
 				+ " formed of a multilayered film comprising a layer formed of a linear polyolefin and (ii) a layer formed"
 				+ " of a cyclic polyolefin adjacent to the layer (i), and the layer (i) formed of a linear polyolefin satisfies "
 				+ "the following conditions polymer metal catale";
 		
 		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.ENTER THE KEYWORDS OR NATURAL LANGUAGE UP TO 2000 CHARACTERS","SEARCH TEXT BOX SHOULD ACCEPT UPTO 2000 CHARACTERS");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			StringLength = searchText.length();
 			if(StringLength<=1999)
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"SEARCH BOX ACCEPTS LESS THAN 2000 CHARACTERS", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"SEARCH BOX ACCEPTS MORE THAN 2000 CHARACTERS", true);
 			}
 			page_ChemicalSearchLandingPage.deleteTextSearchTextBox();
 			Application.Logger.endStep();
 			Application.Logger.addStep("2.ENTER BEYOND 2000 CHARACTERS","ERROR MESSAGE 2000/2000  SHOULD BE DISPLAYED");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(phrase);
 			actualErrorMessage=page_ChemicalSearchLandingPage.getTextLimitErrorMessage();
 			if(actualErrorMessage.equalsIgnoreCase(expErrorMessage)) 
 			{
 				Application.Logger.addsubStep(LogStatus.PASS,"2000 / 2000 ERROR MESSAGE IS DISPLAYED WHEN USER ENTERS MORE THAN 2000 CHARACTERS", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"2000 / 2000 ERROR MESSAGE IS NOT DISPLAYED WHEN USER ENTERS MORE THAN 2000 CHARACTERS", true);
 			}
 			page_ChemicalSearchLandingPage.deleteTextSearchTextBox();
 			Application.Logger.endStep();
 			Application.Logger.addStep("3.VERIFY COPY AND PASTE IN THE SEACRCH TEXT BOX","TEXT SHOULD BE COPIED AND PASTED");
 			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
 			page_ChemicalSearchLandingPage.copyAndPasteTextSearchTextBox();
 			Application.waitTime(2);
 			expTextMessage=page_ChemicalSearchLandingPage.getTextSearchTextBox();
 			if(expTextMessage.isEmpty())
 			{
 			
 				Application.Logger.addsubStep(LogStatus.PASS,"TEXT IS COPIED IS AND PASTED BACK IN THE SEARCH TEXT BOX", false); 
 			} 
 			else 
 			{
 				Application.Logger.addsubStep(LogStatus.FAIL,"TEXT IS NOT COPIED AND PASTED BACK IN THE SEARCH TEXT BOX", true);
 			}
 			page_ChemicalSearchLandingPage.deleteTextSearchTextBox();
 			Application.Logger.endStep();
 		}
 	 catch (Exception e) {
 		Application.Logger.addException(e.getMessage());
 		return flag = false;
 	}
 	return flag;
 }
 /*CHEMEXP-757 */
 	
 	public boolean validateSearchBarAndHighlightingTheFilterList(Controller Application,
			HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE AND Validate the search bar and highlighting ",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND Search Bar should get validated");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			String searchInCaps = "JAP";
			String searchInSmall = "jap";

			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.clickOnTabPatent();
			page_ChemicalSearchResults.clickOnExpandDropdown();
			int beforeCount = page_ChemicalSearchResults.tabPatent()
					.getCountOfFilterListForGivenFilter("Publication country/region");
			page_ChemicalSearchResults.tabPatent().enterTextInSearchAreaForGivenFilter("Publication country/region",
					searchInCaps);

			if (page_ChemicalSearchResults.tabPatent().isDisplayedCloseMarkX()) {
				Application.Logger.addsubStep(LogStatus.PASS, "close mark X is displayed successfully", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "close mark X is not displayed ", true);
			}

			String highlightedFilterList = page_ChemicalSearchResults.tabPatent().getTextTheHighlightedFilterList();
			if (highlightedFilterList.equalsIgnoreCase(searchInCaps)) {
				Application.Logger.addsubStep(LogStatus.PASS, "the search text is highlighted ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "the search text is not highlighted ", true);
			}
			page_ChemicalSearchResults.tabPatent().clickOnCloseMarkX();
			page_ChemicalSearchResults.tabPatent().enterTextInSearchAreaForGivenFilter("Publication country/region",
					searchInSmall);
			String highlightedFilterListSearchedInSmall = page_ChemicalSearchResults.tabPatent()
					.getTextTheHighlightedFilterList();

			if (highlightedFilterList.equalsIgnoreCase(highlightedFilterListSearchedInSmall)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"both the highlighted text are the same so search is case insensative", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"both the highlighted text are not the same so search is not case insensative", true);
			}
			page_ChemicalSearchResults.tabPatent().clickOnCloseMarkX();
			int afterCount = page_ChemicalSearchResults.tabPatent()
					.getCountOfFilterListForGivenFilter("Publication country/region");
			if (beforeCount == afterCount) {
				Application.Logger.addsubStep(LogStatus.PASS, "filter has displayed the full list", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "filter is not displayed the full list", true);
			}
			
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;

	}

/*CHEMEXP-763*/
 	
 	@SuppressWarnings("unlikely-arg-type")
 	public boolean validatePillFilterBox(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE AND Literature Record View Field ",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND All The Literature record view fields should display.");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			String searchText = input.get("searchtext");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.clickOnTabPatent();
			page_ChemicalSearchResults.clickOnExpandDropdown();
			page_ChemicalSearchResults.tabPatent().clickOnButtonSelectAllForGivenFilter("Publication country/region");
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			if (page_ChemicalSearchResults.tabPatent().isEnabledButtonClearAll()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ClearAll button is in enable mode", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ClearAll button is in disable mode", true);
			}
			if (page_ChemicalSearchResults.tabPatent().isDisplayedCrossXForAllThePills()) {
				Application.Logger.addsubStep(LogStatus.PASS, "all the pill value has cross mark", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "all the pill value is not having cross mark", true);
			}
			if (page_ChemicalSearchResults.tabPatent()
					.isHeaderAndTextDisplayedForDifferentPillsExceptTheSearchedString()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"header and text is displayed for all the filter pill value", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"header and text is not displayed for all the filter pill value", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;

	}

 	
 // CHEMEXP-404
	
 	public boolean validatePatentRecordViewField(Controller Application, HashMap<String, String> input) {
 		boolean flag = true;
 		try {
 			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE AND patent Record View Field ","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND All The Literature record view fields should display.");
 			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
 			page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
 			String searchText = input.get("searchtext");
 			page_ChemicalSearchLandingPage.setSearchText(searchText);
 			page_ChemicalSearchLandingPage.clickOnSearchIcon();
 			if (page_ChemicalSearchResults.checkIfResultsFound()) {
 				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
 			} else {
 				throw new Exception("Results set is not loaded for the chemical search");
 			} 	
 		    page_ChemicalSearchResults.clickOnTabPatent();
 			page_ChemicalSearchResults.tabPatent().clickOnTitle(1);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedPublicationNumber())
 				Application.Logger.addsubStep(LogStatus.PASS, "Publication number is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "Publication number is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedRecordTitle())
 				Application.Logger.addsubStep(LogStatus.PASS, "record title is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "record title is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedTextPublished())
 				Application.Logger.addsubStep(LogStatus.PASS, "text published is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "text published is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedTextStatus())
 				Application.Logger.addsubStep(LogStatus.PASS, "text status is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "text status is not displayed in patent record view", true);
 			
 			String statusValue=page_ChemicalSearchResults.tabPatent().getStatusValue();
 		
 			if (statusValue.equalsIgnoreCase("Alive") || statusValue.equalsIgnoreCase("Dead") || statusValue.equalsIgnoreCase("Indeterminate"))
 				Application.Logger.addsubStep(LogStatus.PASS, "the specified status value is displayed",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "the specified status value is not displayed", true);
 				
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldBibliography())
 				Application.Logger.addsubStep(LogStatus.PASS, "section bibliography is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "section bibliography is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isSectionBibliographyExpandedAndCollapsed())
 				Application.Logger.addsubStep(LogStatus.PASS, "section bibliography is expanded and collapse successfully",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "section bibliography has not expanded and collapsed", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldAssignee())
 				Application.Logger.addsubStep(LogStatus.PASS, "field assignee is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field assignee is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldInventor())
 				Application.Logger.addsubStep(LogStatus.PASS, "field inventor is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field inventor is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldPriority())
 				Application.Logger.addsubStep(LogStatus.PASS, "field priority is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field priority is not displayed in patent record view", true);
 			
 			List<String> priorityDetails= page_ChemicalSearchResults.tabPatent().getBibliographyPriorityDetails();
 			
 			for(String priorityDetailsValue:priorityDetails)
 			
 			{
 			
 			if(priorityDetailsValue.contains("NUMBER") || priorityDetailsValue.contains("DATE") || priorityDetailsValue.contains("COUNTRY CODE"))
 				Application.Logger.addsubStep(LogStatus.PASS, "priority details has number , date and country code",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "priority details does not have number , date and country code", true);
 			
 			}
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldApplication())
 				Application.Logger.addsubStep(LogStatus.PASS, "field application is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field application is not displayed in patent record view", true);
 			
 			List<String> ApplicationDetails= page_ChemicalSearchResults.tabPatent().getBibliographyApplicationDetails();
 			
 			for (String applicationDetailsValue:ApplicationDetails)
 			{
 			
 			if(applicationDetailsValue.contains("APPLICATION NUMBER") || applicationDetailsValue.contains("DATE"))
 				Application.Logger.addsubStep(LogStatus.PASS, "application details has publication number and date",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "application details does not have publication number and date", true);
 			}
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedSectionImage())
 				Application.Logger.addsubStep(LogStatus.PASS, "section image is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "section image is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isSectionAbstractExpandedAndCollapsed())
 				Application.Logger.addsubStep(LogStatus.PASS, "section abstract is expandable and collapsable",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "section abstract is not expandable and collapsable", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldNovelty())
 				Application.Logger.addsubStep(LogStatus.PASS, "field novelty is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field novelty is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldDetailedDesc())
 				Application.Logger.addsubStep(LogStatus.PASS, "field detailed desc is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field detailed desc is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldUse())
 				Application.Logger.addsubStep(LogStatus.PASS, "field Use is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field use is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldAdvantage())
 				Application.Logger.addsubStep(LogStatus.PASS, "field advantage is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field advantage is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldTechnologyFocus())
 				Application.Logger.addsubStep(LogStatus.PASS, "field tech focus is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field tech focus is not displayed in patent record view", true);
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldClaims())
 				Application.Logger.addsubStep(LogStatus.PASS, "field claim is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field claim is not displayed in patent record view", true);
 			
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldDWPIFamily())
 				Application.Logger.addsubStep(LogStatus.PASS, "field DWPI family is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field DWPI family is not displayed in patent record view", true);
 			if(page_ChemicalSearchResults.tabPatent().isDisplayedFieldDescription())
 				Application.Logger.addsubStep(LogStatus.PASS, "field description is displayed in patent record view",false);
 			else
 				Application.Logger.addsubStep(LogStatus.FAIL, "field description is not displayed in patent record view", true);
 			
 			Application.Logger.endStep();
 		}
 	 catch (Exception e) {
 		Application.Logger.addException(e.getMessage());
 		return flag = false;
 	}
 	return flag;
 }

 	
 	//@Test
	public boolean validateClustermapBubbleResultset(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults=new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		//String patentRecordCount="500+";
 		//String literatureRecordCount="300+";
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on Cluster map ","RS should be displayed");
			page_ChemicalSearchResults.clickOnClustermapLink();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on Bubble ","RS should be refreshed");
			page_ChemicalSearchResults.clickOnBubble();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();


}catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}
	}
