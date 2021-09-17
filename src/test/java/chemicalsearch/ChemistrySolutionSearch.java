package chemicalsearch;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.chemicalsearch.Page_ChemicalSearchLandingPage;
import pages.chemicalsearch.Page_ChemicalSearchResults;
import pages.chemicalsearch.Page_SavedRecords;
import support.Controller;
import utils.TestUtil;

/**
 * 
 * @author Anand/Rashmi
 *
 */
public class ChemistrySolutionSearch {

	Page_ChemicalSearchLandingPage page_ChemicalSearchLandingPage;
	Page_ChemicalSearchResults page_ChemicalSearchResults;
	Page_SavedRecords page_SavedRecords;

	/** CHEMEXP-739 */
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
		String rsPatent, rsLiterature, rsSearchBox, expSearchText;
		try {
			Application.Logger.addStep("1.VERIFY SEARCH ICON WHEN TEXT IS NOT ENTERED IN THE SEARCH TEXT BOX",
					"SEARCH ICON SHOULD BE GRAYED/DISABLED");
			blnchk = page_ChemicalSearchLandingPage.isDisabledSearchIcon();
			if (blnchk) {
				Application.Logger.addsubStep(LogStatus.PASS, "SEARCH ICON IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH ICON IS NOT DISABLED", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.VERIFY SEARCH ICON WHEN TEXT IS ENTERED IN THE SEARCH TEXT BOX",
					"SEARCH ICON SHOULD BE ENABLED");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			blnchk = page_ChemicalSearchLandingPage.isDisabledSearchIcon();
			if (!blnchk) {
				Application.Logger.addsubStep(LogStatus.PASS, "SEARCH ICON IS ENABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH ICON IS NOT ENABLED", true);
			}
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.VERIFY SEARCH BOX,PATENT AND LITERATURE TABS IN THE RESULTS SET PAGE",
					"SEARCH BOX,PATENT AND LITERATURE TABS SHOULD BE DISPLAYED IN THE RESULTS SET PAGE");
			rsSearchBox = page_ChemicalSearchResults.getTextSearchBox();
			if (rsSearchBox.trim().equals(searchText)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"SEARCH BOX WITH TEXT IS DISPLAYED IN THE RESULTS SET PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"SEARCH BOX WITH TEXT IS NOT DISPLAYED IN THE RESULTS SET PAGE", true);
			}
			rsPatent = page_ChemicalSearchResults.tabPatentSearch().getTextPatentName();
			rsLiterature = page_ChemicalSearchResults.tabLiteratureSearch().getTextLiteratureName();
			if (patent.equals(rsPatent.trim()) && literature.equals(rsLiterature.trim())) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"PATENT AND LITERATURE TABS ARE DISPLAYED IN RESULTS SET PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PATENT AND LITERATURE TABS ARE NOT DISPLAYED IN RESULTS SET PAGE", true);
			}
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			Application.Logger.endStep();
			Application.Logger.addStep("4.VERIFY CLEAR ALL ICON (X) WHEN NOTHING IS ENTERED IN THE SEARCH TEXT BOX",
					"CLEAR ALL ICON (X) SHOULD NOT BE DISPLAYED IN THE SEARCH TEXT BOX");
			if (!page_ChemicalSearchLandingPage.isDisplayedButtonClearAll()) {
				Application.Logger.addsubStep(LogStatus.PASS, "CLEAR ALL ICON (X)IS NOT DISPLAYED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "CLEAR ALL ICON (X)IS DISPLAYED ", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.ENTER A LETTER AND VERIFY THE CLEAR ALL (X) ICON",
					"CLEAR ALL ICON (X) SHOULD APPEAR WHEN THE USER ENTERS THE FIRST LETTER");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchTextLandingPage);
			if (page_ChemicalSearchLandingPage.isDisplayedButtonClearAll()) {
				Application.Logger.addsubStep(LogStatus.PASS, "CLEAR ALL ICON (X)IS DISPLAYED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "CLEAR ALL ICON (X)IS NOT DISPLAYED ", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.CLICK ON CLEAR ALL (X) ICON",
					"CLEAR ALL ICON (X) SHOULD CLEAR ALL THE CONTENTS IN SEARCH BOX");
			page_ChemicalSearchLandingPage.clickOnButtonClearAll();
			expSearchText = page_ChemicalSearchLandingPage.getTextSearchTextBox();
			if (expSearchText.isEmpty()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"SEARCH TEXT BOX IS EMPTY AFTER THE CLICK ON CLEAR ALL (X) ICON", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"SEARCH TEXT BOX IS NOT EMPTY AFTER THE CLICK ON CLEAR ALL (X) ICON", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-752 */
	@Test
	public boolean validatePatentSuggestedKeyWord(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		boolean flag = true;
		String expKeywordText;
		String rsKeyword;
		try {
			Application.Logger.addStep(
					"1.PERFORM A SEARCH WITH THE TEXT antibacterial polyethylene resin containing transition metals",
					"RESULTS SET SHOULD BE DISPLAYED");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"2.CLICK ON THE KEYWORD FROM SUGGESTED KEY WORD SECTION AND VERIFY THE KEY WORD IS ADDED IN THE KEYWORD BOTTOM SECTION",
					"KEYWORD SHOULD BE ADDED IN THE BOTTOM SECTION");
			page_ChemicalSearchResults.clickOnTabPatent();
			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonInsights();
			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonFirstKeyWord();
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			rsKeyword = page_ChemicalSearchResults.getTextKeyword();
			expKeywordText = page_ChemicalSearchResults.tabPatentSearch().getTextPatentInsightsKeyword();
			if (rsKeyword.trim().equalsIgnoreCase(expKeywordText.trim())) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"SEARCH IS PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"SEARCH IS NOT PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION, INSIGHTS KEYWORD : \t"
								+ expKeywordText + "\t\t RESULT SET KEYWORD:\t" + rsKeyword,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep(
					"3.IN LITERATURE TAB, CLICK ON THE KEYWORD FROM SUGGESTED KEY WORD SECTION AND VERIFY THE KEY WORD IS ADDED IN THE KEYWORD BOTTOM SECTION",
					"KEYWORD SHOULD BE ADDED IN THE BOTTOM SECTION");
			page_ChemicalSearchResults.clickOnTabLiterature();
			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonInsights();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonFirstKeyWord();
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			rsKeyword = page_ChemicalSearchResults.getTextKeyword();
			expKeywordText = page_ChemicalSearchResults.tabLiteratureSearch().getTextLiteratureInsightsKeyword();
			expKeywordText = expKeywordText.toLowerCase();
			if (!rsKeyword.isEmpty() && (!expKeywordText.isEmpty())) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"SEARCH IS PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"SEARCH IS NOT PERFORMED WITH THE EXPECTED KEYWORD IN THE KEYWORD SECTION, INSIGHTS KEYWORD : \t"
								+ expKeywordText + "\t\t RESULT SET KEYWORD:\t" + rsKeyword,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.CLICK ON THE CLOSE ICON FOR THE PATENT IN THE RECORD VIEW",
					"PATENT RECORD VIEW SHOULD BE CLOSED SUCCESSFULLY");
			page_ChemicalSearchResults.clickOnTabPatent();
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().clickOnClosePatentRecordView();
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedPatentRecordCloseIcon()) {
				Application.Logger.addsubStep(LogStatus.PASS, "PATENT RECORD VIEW IS CLOSED SUCCESSFULLY", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PATENT RECORD VIEW IS NOT CLOSED SUCCESSFULLY", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.CLICK ON THE CLOSE ICON FOR THE LITERATURE IN THE RECORD VIEW",
					"LITERATURE RECORD VIEW SHOULD BE CLOSED SUCCESSFULLY");
			page_ChemicalSearchResults.clickOnTabLiterature();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnLiteratureRecord(1);
			Application.waitTime(2);
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnCloseLiteratureRecordView();
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedLiteratureRecordCloseIcon()) {
				Application.Logger.addsubStep(LogStatus.PASS, "LITERATURE RECORD VIEW IS CLOSED SUCCESSFULLY", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "LITERATURE RECORD VIEW IS NOT CLOSED SUCCESSFULLY",
						true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-748 */
	@Test
	public boolean validatePatentThumbsUpThumbsDown(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		boolean flag = true;
		String expThumbsUpHollowState;
		String expThumbsDownHollowState;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH THE TEXT ", "RESULTS SET SHOULD BE DISPLAYED");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.Logger.endStep();
			Application.Logger.addStep("2. IN PATENT TAB, CLICK ON THUMBS UP ICON FOR A RECORD",
					"ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
			page_ChemicalSearchResults.tabPatentSearch().clickOnThumbsUpIcon();
			expThumbsDownHollowState = page_ChemicalSearchResults.tabPatentSearch().checkThumbsDownHollowState();
			if (!expThumbsDownHollowState.contains("filled")) {
				Application.Logger.addsubStep(LogStatus.PASS, "THUMBS DOWN ICON IS IN HOLLOW STATE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "THUMBS DOWN ICON IS NOT IN HOLLOW STATE", false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.CLICK ON THUMBS DOWN ICON FOR A RECORD",
					"ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
			page_ChemicalSearchResults.tabPatentSearch().clickOnThumbsDownIcon();
			expThumbsUpHollowState = page_ChemicalSearchResults.tabPatentSearch().checkThumbsUpHollowState();
			if (!expThumbsUpHollowState.contains("filled")) {
				Application.Logger.addsubStep(LogStatus.PASS, "THUMBS UP ICON IS IN HOLLOW STATE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "THUMBS UP ICON IS NOT IN HOLLOW STATE", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.IN PATENT TAB, VERIFY THUMBS UP ICON FOR EVERY RECORD",
					"THUMBS UP ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedPatentThumbsUpIcon()) {
				Application.Logger.addsubStep(LogStatus.PASS, "THUMBS UP ICON IS DISPLAYED FOR EVERY RECORD", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "THUMBS UP ICON IS NOT DISPLAYED FOR EVERY RECORD",
						false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.VERIFY THUMBS DOWN ICON FOR EVERY RECORD",
					"THUMBS DOWN ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedPatentThumbsDownIcon()) {
				Application.Logger.addsubStep(LogStatus.PASS, "THUMBS DOWN ICON IS DISPLAYED FOR EVERY RECORD", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "THUMBS DOWN ICON IS NOT DISPLAYED FOR EVERY RECORD",
						false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("6.IN LITERATURE TAB, CLICK ON THUMBS UP ICON FOR A RECORD",
					"ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
			page_ChemicalSearchResults.clickOnTabLiterature();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnThumbsUpIcon();
			expThumbsDownHollowState = page_ChemicalSearchResults.tabLiteratureSearch().checkThumbsDownHollowState();
			if (!expThumbsDownHollowState.contains("filled")) {
				Application.Logger.addsubStep(LogStatus.PASS, "THUMBS DOWN ICON IS IN HOLLOW STATE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "THUMBS DOWN ICON IS NOT IN HOLLOW STATE", false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.CLICK ON THUMBS DOWN ICON FOR A RECORD",
					"ICON SHOULD BE SELECTED AND CHANGED TO SOLID GREEN");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnThumbsDownIcon();
			expThumbsUpHollowState = page_ChemicalSearchResults.tabLiteratureSearch().checkThumbsUpHollowState();
			if (!expThumbsUpHollowState.contains("filled")) {
				Application.Logger.addsubStep(LogStatus.PASS, "THUMBS UP ICON IS IN HOLLOW STATE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "THUMBS UP ICON IS NOT IN HOLLOW STATE", false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.IN LITERATURE TAB, VERIFY THUMBS UP ICON FOR EVERY RECORD",
					"THUMBS UP ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedLiteratureThumbsUpIcon()) {
				Application.Logger.addsubStep(LogStatus.PASS, "THUMBS UP ICON IS DISPLAYED FOR EVERY RECORD", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "THUMBS UP ICON IS NOT DISPLAYED FOR EVERY RECORD",
						false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("9.VERIFY THUMBS DOWN ICON FOR EVERY RECORD",
					"THUMBS DOWN ICON SHOULD BE DISPLAYED FOR EVERY RECORD");
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedLiteratureThumbsDownIcon()) {
				Application.Logger.addsubStep(LogStatus.PASS, "THUMBS DOWN ICON IS DISPLAYED FOR EVERY RECORD", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "THUMBS DOWN ICON IS NOT DISPLAYED FOR EVERY RECORD",
						false);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-740 */

	@Test
	public boolean validateFieldsOnLiteratureResultset(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String patentRecordCount = "500+";
		String literatureRecordCount = "300+";
		List<String> fields = new ArrayList<String>(Arrays.asList("Novelty", "Assignee", "Use", "Inventor"));
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Switch to Literature tab", "Literature result set is displayed");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.getLiteratureResultsCount() > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, "LITERATURE RESULT SET IS DISPLAYED FOR THE TEXT SEARCH",
						false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Verify the fields", "Title and abstract fields should be displayed.");
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedTitle(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Title Field is displayed  for row no :1 in Literature results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Title Field is not displayed for given row in Literature results page.", true);
			}

			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedAbstract(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Abstract Filed is displayed  for row no :1 in Literature results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Abstract Field is not displayed for given row in Literature results page. ", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Verify row/record on the result set in Patent Tab.",
					"Each row/record on the result set should show:\r\n" + "3.1. Publication number\r\n"
							+ "3.2. Title\r\n" + "Note: Title will be displayed without any field name.\r\n"
							+ "3.3. Abstract data\r\n" + "3.3.1. Use\r\n" + "3.3.2 Novelty\r\n" + "3.4 Image");
			page_ChemicalSearchResults.clickOnTabPatent();
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);

			page_ChemicalSearchResults.manageFields().clickOnButtonClearAll();
			page_ChemicalSearchResults.manageFields().selectManageFieldCheckBoxes(fields);
			page_ChemicalSearchResults.manageFields().clickOnButtonApply();
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedTitle(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Title is displayed  for row no :1 in Patent results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Title is not displayed for given row in Patent results page.", true);
			}

			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedAbstract(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Abstract Data is displayed  for row no :1 in Patent results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Abstract data is not displayed for given row in Patent results page. ", true);
			}

			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedPN(1)) {
				Application.Logger.addsubStep(LogStatus.PASS, "PN  is displayed  for row no :1 in Patent results page.",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PN Number is not displayed for given row in Patent results page. ", true);
			}

			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedNovelty(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Novelty Filed is displayed  for row no :1 in Patent results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Novelty Field is not displayed for given row in Patent results page. ", true);
			}
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedUse(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Use Filed is displayed  for row no :1 in Patent results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Use Field is not displayed for given row in Patent results page. ", true);
			}
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedImg(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Image  is displayed  for row no :1 in Patent results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Image is not displayed for given row in Patent results page. ", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Verify tabs on Result set page.",
					"Patent tab and Literature tab Results should be displayed");

			if (page_ChemicalSearchResults.isDisplayedTabPatent()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Patent tab is displayed results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Patent tab is not displayed in results page.", true);
			}

			if (page_ChemicalSearchResults.isDisplayedTabLiterature()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Literature tab is displayed results page.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Literature tab is not displayed in results page.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.Click on Patent tab.",
					"If the user is on Patent tab should highlight that tab.\r\n"
							+ "Should display count of patent results retrieved; If results retrieved are more than 500 then displayed as 500+\r\n"
							+ "should Display patent results in rows");
			page_ChemicalSearchResults.clickOnTabPatent();
			if (page_ChemicalSearchResults.getResultsCount() > 500) {
				if (page_ChemicalSearchResults.getTabPatentResultsCount().equals(patentRecordCount))

					Application.Logger.addsubStep(LogStatus.PASS,
							"Record Count  is displaying as expected for Patent results .", false);
				else
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Record Count is not displayed  as expected for Patent results .", true);

			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Results count is not morethan '500' for Patent results.",
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.Click on Literature tab",
					"If the user is on Literature tab should highlight that tab.\r\n"
							+ "Should display count of literature results retrieved ; If results retrieved are more than 300 then displayed as 300+\r\n"
							+ "Display literature results in rows");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.getResultsCount() > 300) {
				if (page_ChemicalSearchResults.getTabLiteratureResultsCount().equals(literatureRecordCount)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"Record Count  is displaying as expected for Literature results .", false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Record Count is not displayed  as expected for Literature results .", true);
				}
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Results count is not morethan '300' for Literature results.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.Verify Search box", "search box Should be next to the tabs.");
			if (page_ChemicalSearchResults.isDisplayedSearchBoxNextToTabLiturature()) {
				Application.Logger.addsubStep(LogStatus.PASS, "SearchBox  is displayed next to Liturature Tab", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "SearchBox is not displayed next to Liturature Tab.",
						true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-741 */
	@Test
	public boolean validateSearchboxTopSection(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String lengthySerachText = "Novelty\r\n"
				+ "A superabsorbent polymer comprises a base resin powder containing a crosslinked polymer of a water-soluble ethylenically unsaturated monomer including partially neutralized acidic group(s), and pores having diameter of 1 μ m or more, formed on the base resin powder, and has apparent density of more than 0.55 g/ml. The crosslinked polymer comprises layered silicate-based particles dispersed in the crosslinked structure.\r\n"
				+ "\r\n" + "Use\r\n"
				+ "Superabsorbent polymer. Uses include but are not limited to diaper, soil protective agent, sealant, seedling sheet, and foodstuffs applications.";

		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Verify Top section in search bar.",
					"Top section in the search bar must show the text entered by the user for searching.\r\n"
							+ "Should show ellipsis if text is more than what can be accommodated in one row");

			if (page_ChemicalSearchResults.getTopSectionBarText().equalsIgnoreCase(searchText)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Top section in the search bar is showing the text :"
								+ page_ChemicalSearchResults.getTopSectionBarText() + "entered by the user :"
								+ searchText + "for searching.",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Top section in the search bar is not showing the text entered by the user for searching.",
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.verify By entering more than what can accomidate in one row",
					"Pills are displayed.");

			if (page_ChemicalSearchResults.isDisplayedPillBox(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Pill is showing for entered by the user for searching  :'" + lengthySerachText + "'", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Pill is not showing for entered by the user for searching :'" + lengthySerachText + "'", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Verify the rollover of first row of Top section.",
					"Rollover on the first row must show the full text");
			if (searchText.trim().contains(page_ChemicalSearchResults.getTopSectionBarCompleteText())) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"First row of Top section is showing the Full text :"
								+ page_ChemicalSearchResults.getTopSectionBarCompleteText() + "entered by the user :- "
								+ searchText + "for searching.",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						" First row of Top section is not showing the Full text entered by the user for searching.",
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.Verify search box on the Bottom section.",
					"Bottom section must show the keywords which are derived/extracted/expanded by the system from user's input text.");
			List<String> getListOfPills = page_ChemicalSearchResults.getListOfPillBoxes();
			for (String pills : getListOfPills) {
				if (pills != null) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"Pill :" + pills + " is showing for entered by the user for searching", false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Pill is not showing for entered by the user for searching :'" + searchText + "'", true);
				}
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-742 */

	@Test
	public boolean validateXDeleteOrRemoveKeyword(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(","));

		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Verify X to each of the keywords.",
					".All the extracted keywords must have x to delete/remove them. No toast should be shown in this case.");
			int getListOfPills = page_ChemicalSearchResults.getNoOfPillBoxes();
			for (int i = 1; i < getListOfPills; i++) {
				if (page_ChemicalSearchResults.isDisplayedRemoveIcon(i)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"Pill :" + i + " is showing 'X' for delete/Remove for keyword", false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Pill :" + i + "is not showing 'X' for delete/Remove for keyword", true);
				}
			}
			if (page_ChemicalSearchResults.isNotDisplayedToastMessage()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ToastMessage is not displayed", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ToastMessage is displayed", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"3.Add new keywords to search box after the extracted keywords and Click enter or press tab or clicks on search icon or click anywhere on screen.",
					"New keywords should be entered and newly entered keyword should convert into a pill.Result set should get refresh.");
			int resultsCountBeforeNewSearch = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
			page_ChemicalSearchResults.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE NEW KEYWORD SEARCH",
						false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			List<String> getListOfPill = page_ChemicalSearchResults.getListOfPillBoxes();
			for (String pillExpected : finalKeywordList) {
				if (getListOfPill.contains(pillExpected)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"newly entered keyword :" + pillExpected + " should converted into a pill", false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"newly entered keyword :" + pillExpected + " should not converted into a pill", true);
				}
			}
			page_ChemicalSearchResults.clickOnSearchIcon();
			int resultsCountAfterNewSearch = page_ChemicalSearchResults.getResultsCount();
			if (resultsCountBeforeNewSearch != resultsCountAfterNewSearch) {
				Application.Logger.addsubStep(LogStatus.PASS, "Result set get refreshed with new keyword", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Result set isnot get refreshed with new keyword", true);
			}

			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-743--Result set/Search page:"+XX more"* */

	@Test
	public boolean validateXXMorePills(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(","));

		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"2.Add new keywords to search box after the extracted keywords,so that content should more than 4 rows.",
					"New keywords should be entered.");
			int initialListOfPills = page_ChemicalSearchResults.getNoOfPillBoxes();
			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
			// page_ChemicalSearchResults.clickOnLinkFilters();
			Application.waitTime(3);
			int getListOfPills = page_ChemicalSearchResults.getNoOfPillBoxes();
			if (getListOfPills > initialListOfPills) {
				Application.Logger.addsubStep(LogStatus.PASS, "New keywords are entered successfully.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "New keywords are not entered.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Mouse hover on '+XX more'",
					"Hover over on the '+XX more' pill should extend the box.");
			page_ChemicalSearchResults.hoverOnPhraseSection();
			page_ChemicalSearchResults.hoverOnFirstKeyword();
			Application.waitTime(2);
			// page_ChemicalSearchResults.mouseOverToPillBox(getListOfPills);
			int noOfPillsAfterMouseOver = page_ChemicalSearchResults.getNoOfPillBoxes();
			if (noOfPillsAfterMouseOver >= getListOfPills) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Hover over on the '+XX more' pill should extend the box.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Hover over on the '+XX more' pill should not extend the box.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Verify the cursor on last pill.",
					"After the last pill, cursor must be shown to indicate that more keywords could be added.");
			if (page_ChemicalSearchResults.isDisplayedTextBoxKeyword()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"After the last pill, cursor shown to indicate that more keywords could be added.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"After the last pill, cursor not shown to indicate that more keywords could be added.", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/**
	 * CHEMEXP-744--Result set/Search page: Add pills more than what can fit in
	 * one row search box"
	 */

	@Test
	public boolean validateByAddingPillsMorethanWhatCanFitInOneRowSearchBox(Controller Application,
			HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(","));

		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Add pills more than what can fit in one row search box.",
					"Should show '+XX more' pill .");
			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
			page_ChemicalSearchResults.hoverOnPhraseSection();
			Application.waitTime(3);
			int getNoOfPills = page_ChemicalSearchResults.getNoOfPillBoxes();
			List<String> getListOfPills = page_ChemicalSearchResults.getListOfPillBoxes();
			String lastPillText = getListOfPills.get(getListOfPills.size() - 2);
			if (lastPillText.contains("more")) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Last Pill should showing as " + lastPillText + " pill Name.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Last Pill should showing as +XX pill Name.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Mouse hover on '+XX more'",
					"Hover over on the '+XX more' pill should extend the box.");
			page_ChemicalSearchResults.hoverOnPhraseSection();
			page_ChemicalSearchResults.hoverOnFirstKeyword();
			Application.waitTime(2);
			// page_ChemicalSearchResults.mouseOverToPillBox(getNoOfPills);
			int noOfPillsAfterMouseOver = page_ChemicalSearchResults.getNoOfPillBoxes();
			if (noOfPillsAfterMouseOver >= getNoOfPills) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Hover over on the '+XX more' pill should extend the box.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Hover over on the '+XX more' pill should not extend the box.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Verify the cursor on last pill.",
					"After the last pill, cursor must be shown to indicate that more keywords could be added.");
			if (page_ChemicalSearchResults.isDisplayedTextBoxKeyword()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"After the last pill, cursor shown to indicate that more keywords could be added.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"After the last pill, cursor not shown to indicate that more keywords could be added.", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-746 * */
	@Test
	public boolean validateClearAllX(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String phrase = "This invention provides a medical container comprising a body for storing a medicament, characterized in that the body of the medical container formed of a multilayered film comprising (i) \r\n"
				+ "a layer formed of a linear polyolefin and (ii) a layer formed of a cyclic polyolefin adjacent to the layer (i), and the layer (i) formed of a linear polyolefin satisfies the following \r\n"
				+ "conditions (a) and/or (b): condition (a) an amount of a liquid component, which stays after evaporation of an organic solvent after Soxhlet's extraction with the organic solvent, \r\n"
				+ "of not more than 0.2% by weight; and condition (b) a degree of branching, per 1000 carbon atoms, of a component, which stays after evaporation of n-hexane after Soxhlet's extraction \r\n"
				+ "with n-hexane, of not more than 50.  The multilayer film force (i) a layer also comprising a linear polyolefine, a layer (ii) a cyclic polyolefine adjacent to the layer (i), and (iii)\r\n"
				+ "a polyester layer or an inorganic vapor-deposited thin film.  The peelable seal portion includes a force which is a portion where the innermost layers of the front seat and the rear \r\n"
				+ "seat are weakly welded to each other, or another member formed from a resin having a low welding strength with the innermost layer, and the innermost layer.  The medical device contains \r\n"
				+ "water for injection, physiological saline, glucose solution, amino acid solution, high-calorie infusion solution, fat emulsion, vitamin preparation, or metal element preparation as a \r\n"
				+ "solution.  the printable layer means a layer capable of printing a medium such as printing ink when displaying characters or designs on a container. The barrier layer is a layer having \r\n"
				+ "the performance of blocking the passage of moisture and Z or gas, for example, a gas noria layer. In the present invention, the printable layer may be, for example, a polyester resin layer \r\n"
				+ "such as polyethylene terephthalate, polyamide, or polypropylene, or an inorganic deposited thin film in which an inorganic material such as silica, aluminiums\r\n"
				+ "";
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on X (Clear all).",
					"Click on X (Clear all) must clear everything from search box including keywords and natural language text.");
			int resultsCount = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnClearAllMark();
			if (page_ChemicalSearchResults.getTextOfSerachBox().isEmpty()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"cleared everything from search box including keywords and natural language text.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						" Not Cleared everything from search box including keywords and natural language text.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Verify the toast message",
					"Should Show toast message to the user with undo functionality\\r\\n\" + \r\n"
							+ "					\"Your search query is cleared out as action of 'clear all' . You could start a new search and generate new keywords or could undo to go back to previous state.\\r\\n\" + \r\n"
							+ "					\"Action text: Undo.");
			if (page_ChemicalSearchResults.isDisplayedToastMessage()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Toast Message has been displayed.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Toast Message has not been displayed.", true);
			}
			if (page_ChemicalSearchResults.isDisplayedActionTextUndo()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Undo Action text has been displayed.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Undo Action text has not been displayed.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Validate watermark on the search box",
					"Should show below watermark to indicate the user that s/he can do a brand new natural language search from here also.\r\n"
							+ "Watermark: 'Enter keywords, phrased or text blocks to search.'");

			if (page_ChemicalSearchResults.isDisplayedSearchBoxWithWaterMark()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Water Mark with text as 'Watermark:Enter keywords, phrased or text blocks to search...' has been displayed.",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Water Mark with text as 'Watermark:Enter keywords, phrased or text blocks to search...' has not been displayed..",
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.Observe Search Icon and Result set",
					"Search button must be grayed out.There should not be any change on the result set as new search has not been performed yet.");
			if (page_ChemicalSearchResults.getColorOfButtonSerach().trim().equals("rgba(0, 0, 0, 0)")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Search button has been grayed out.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Search button has not been grayed out", true);
			}
			int resultsCountAfterClearAllButtonClick = page_ChemicalSearchResults.getResultsCount();

			if (resultsCountAfterClearAllButtonClick == resultsCount) {
				Application.Logger.addsubStep(LogStatus.PASS, "There is no any change on the result set", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "There is a any change on the result set.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.Click on undo",
					"Action: Undo to bring the text in the box to previous state.");
			page_ChemicalSearchResults.clickOnActionTextUndo();
			if (page_ChemicalSearchResults.getTextOfSerachBox().equals(searchText)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Action: Undo to bring the text in the box to previous state.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Action: Undo not to bring the text in the box to previous state.", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.Enter 2000 characters and try to enter beyond limit on top section",
					"Search box must accept up-to 2000 characters.\r\n"
							+ "Box must not accept beyond supported limit.\r\n"
							+ "Should Show red inline message below the box: Phrase: 2000/2000");
			if (page_ChemicalSearchResults.getErrorMessagePhrase(phrase).trim()
					.equalsIgnoreCase("Phrase: reached limit 2000 / 2000")) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Search box not accepted up-to 2000 characters and Box not accepted beyond supported limit and Shown red inline message below the box: Phrase: 2000/2000.",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Search box accepted morethan 2000 characters", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.Verify searched keywords are heighlighted in patent RS",
					"Keyword should be heighlighted in patent RS");
			page_ChemicalSearchResults.clickOnTabPatent();
			int noOfTimesHightedOfGivenKeyword = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfHighlightedTextInRS(searchText);
			if (noOfTimesHightedOfGivenKeyword > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenKeyword
						+ "\t time the given keyword \t" + searchText + " are heighlighted in Record Set", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " are not heighlighted in Record Set", true);
			}
			Application.Logger.endStep();
			
			Application.Logger.addStep("9.Verify searched keywords are heighlighted in patent Record View",
					"Keyword should be heighlighted in patent Record View");
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
			Application.waitTime(2);
			int noOfTimesHightedOfGivenKeywordrv = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfHighlightedTextInRS(searchText);
			if (noOfTimesHightedOfGivenKeywordrv > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenKeywordrv
						+ "\t time the given keyword \t" + searchText + " are heighlighted in Patent Record View", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " are not heighlighted in Patent Record View", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("10.Switch to Literature tab ,then Verify searched keywords are heighlighted in RS",
					"Keyword should be heighlighted in Literature RS");
			page_ChemicalSearchResults.clickOnTabLiterature();
			Application.waitTime(2);
			int noOfTimesHightedOfGivenKeywords = page_ChemicalSearchResults.tabLiteratureSearch()
					.getCountOfHighlightedTextInRS(searchText);
			if (noOfTimesHightedOfGivenKeywords > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenKeywords
						+ "\t time the given keyword \t" + searchText + " are heighlighted in Literature RS", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " \t are not heighlighted in Literature RS \t", true);
			}
			Application.Logger.endStep();
			
			Application.Logger.addStep("11.Switch to Literature Record View ,then Verify searched keywords are heighlighted in RS",
					"Keyword should be heighlighted in Literature Record View");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnLiteratureRecord(1);
			Application.waitTime(2);
			int noOfTimesHightedOfGivenKeywordsLitRv = page_ChemicalSearchResults.tabLiteratureSearch()
					.getCountOfHighlightedTextInRS(searchText);
			if (noOfTimesHightedOfGivenKeywordsLitRv > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenKeywordsLitRv
						+ "\t time the given keyword \t" + searchText + " are heighlighted in Literature Record view", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " \t are not heighlighted in Literature Record view \t", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/**
	 * CHEMEXP-753--Result set/Search page:Pills color on Bottom Section"
	 */

	@Test
	public boolean validatePillsColorOnBottomSection(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");

		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Verify Pills box on the Bottom section and perform search",
					"By default all the pills must be outlined by blue color which indicates optional.and Fiber should be heighlighted in Result set");
			page_ChemicalSearchResults.clickOnTabPatent();
			if (page_ChemicalSearchResults.isDisplayedPillBox(1)) {
				Application.Logger.addsubStep(LogStatus.PASS, "Looking for 1 st Pillbox has been displayed", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Looking Pillbox doesnot exist in result set", true);
			}
			if (page_ChemicalSearchResults.getColorOfPillBox(1).equals("state_0")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Pill box contains 'blue color' as Expected.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Pill box not contain 'blue color' as Expected.", true);
			}

			int noOfTimesHightedOfGivenKeyword = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfHighlightedTextInRS("fiber");
			if (noOfTimesHightedOfGivenKeyword > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenKeyword
						+ "\t time the given keyword \t" + searchText + " are heighlighted in ResultSet", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " are not heighlighted in Result Set", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Click on the word Fiber on bottom section and perform search",
					"Blue color should turn to green color and Fiber should be heighlighted in Result set");
			page_ChemicalSearchResults.clickOnPillBox(1);
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.getColorOfPillBox(1).equals("state_1")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Pill box contains 'Green color' as Expected.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Pill box not contain 'Green color' as Expected.", true);
			}

			int noOfTimesHightedOfGivenText = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfHighlightedTextInRS("fiber");
			if (noOfTimesHightedOfGivenText > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenText
						+ "\t time the given keyword \t" + searchText + " are heighlighted in ResultSet", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " are not heighlighted in Result Set", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Click on the word Fiber on bottom section and perform search",
					"Green color should turn to Red color and Fiber should not be heighlighted in Result set");
			page_ChemicalSearchResults.clickOnPillBox(1);
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.getColorOfPillBox(1).equals("state_2")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Pill box contains 'Red color' as Expected.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Pill box not contain 'Red color' as Expected.", true);
			}

			int noOfTimesHightedOfGivenTextForRedColor = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfHighlightedTextInRS("fiber");
			if (noOfTimesHightedOfGivenTextForRedColor == 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenTextForRedColor
						+ "\t time the given keyword \t" + searchText + " are heighlighted in ResultSet", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " are not heighlighted in Result Set", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.Click on the word Fiber on bottom section and perform search",
					"Red color should turn to blue color and Fiber should be heighlighted in Result set");
			page_ChemicalSearchResults.clickOnPillBox(1);
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.getColorOfPillBox(1).equals("state_0")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Pill box contains 'blue color' as Expected.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Pill box not contain 'blue color' as Expected.", true);
			}

			int noOfTimesHightedOfGivenSearchText = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfHighlightedTextInRS("fiber");
			if (noOfTimesHightedOfGivenSearchText > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenSearchText
						+ "\t time the given keyword \t" + searchText + " are heighlighted in ResultSet", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " are not heighlighted in Result Set", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-747 * */
	@Test
	public boolean validateEditTextInBottomSection(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(","));
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Type/paste keywords in bottom section.",
					"Must be able to type/paste keywords in bottom section.");
			page_ChemicalSearchResults.clickOnTabPatent();
			page_ChemicalSearchResults.closeAllExistingKeyWords();
			page_ChemicalSearchResults.AddKeyWordsBottomSection(finalKeywordList);
			List<String> listOfPills = page_ChemicalSearchResults.getListOfPillBoxes();
			for (String pill : listOfPills) {
				if (!pill.equals(searchText)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"Able to typed/pasted keyword :'" + pill + "' in bottom section.", false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Not able to typed/pasted keywords in bottom section.", true);
				}
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Edit Keywords in the search box",
					"Search Icon Must turn green/become enabled if something is edited in the search box");
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			page_ChemicalSearchResults.setTextPhrase("Fiber");
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"4.type/insert/paste more text anywhere in-between the existing text in the top section.",
					"Must be able to type/insert/paste more text anywhere in-between the existing text in the top section.");
			page_ChemicalSearchResults.enterTextAnywhereInSearchBox("acid", 2);
			Application.Logger.addsubStep(LogStatus.INFO, "Successfuly Entered/Inserted text at specified position",
					false);
			Application.Logger.endStep();
			Application.Logger.addStep("5.select and delete selected text in top section",
					"selected text should be selected in top section.");
			page_ChemicalSearchResults.deleteText();
			String expSearchText = page_ChemicalSearchResults.getTextOfSerachBox();
			if (expSearchText.isEmpty()) {
				Application.Logger.addsubStep(LogStatus.PASS, "TEXT IS DELETED SUCCESSFULLY", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"TEXT IS NOT DELETED SUCCESSFULLY,SEARCH TEXT " + searchText, true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-749* */

	@Test
	public boolean validateDoNotRetainVoteCasteForNewSearch(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(","));

		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			int resultsCountBeforeNewSearch = page_ChemicalSearchResults.getResultsCount();
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on thumbs up for few records.",
					"Thumbs up icon should be changed to solid green for the records");
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.waitTime(3);
			for (int i = 1; i < 4; i++) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Click on thumbs Down for few records.",
					"Thumbs Down icon should be changed to solid green for the records");
			for (int j = 5; j < 9; j++) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Enter the text in the search text box and click on search icon",
					"Results must be refreshed");
			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			int resultsCountAfterNewSearch = page_ChemicalSearchResults.getResultsCount();
			if (resultsCountBeforeNewSearch != resultsCountAfterNewSearch) {
				Application.Logger.addsubStep(LogStatus.PASS, "Result set get refreshed with new search", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Result set is not get refreshed with new search", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.Verify the thumbs up and thumbs down for the records",
					"Thumbs up and thumbs down should not be retained for the records");
			for (int i = 1; i < 4; i++) {
				if (!page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsUpWithFillColor(i)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs up is not be retained for the record :" + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "Thumbs up is be retained for the record :" + i,
							true);
				}
			}
			for (int j = 5; j < 9; j++) {
				if (!page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsDownWithFillColor(j)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs Down is not be retained for the record :" + j,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Thumbs Down is not be retained for the record :." + j, true);
				}
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"6.Retain Vote Caste Navigate Between Patent And Literature Tabs.Click on thumbs up for few records",
					"Thumbs up icon should be changed to solid green for the records");
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.waitTime(3);
			for (int i = 1; i < 4; i++) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.Click on thumbs Down for few records.",
					"Thumbs Down icon should be changed to solid green for the records");
			for (int j = 5; j < 9; j++) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.Click on the Literature Tab", "Literature Record set should be displayed.");
			page_ChemicalSearchResults.clickOnTabLiterature();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("9.Click on thumbs UP for few records",
					"Thumbs up icon should be changed to solid green for the records.");
			for (int i = 1; i < 4; i++) {
				page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("10.Click on thumbs Down for few records.",
					"Thumbs Down icon should be changed to solid green for the records");
			for (int j = 5; j < 9; j++) {
				page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("11.Verify the thumbs up and thumbs down for the records in Patent Tabs",
					"Thumbs up and thumbs down should be retained for the records in Patent Tab");
			page_ChemicalSearchResults.clickOnTabPatent();
			for (int i = 1; i < 4; i++) {
				if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsUpWithFillColor(i)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs up is be retained for the record :" + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "Thumbs up is not be retained for the record :" + i,
							true);
				}
			}
			for (int j = 5; j < 9; j++) {
				if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsDownWithFillColor(j)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs Down is be retained for the record :" + j,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Thumbs Down is not be retained for the record :." + j, true);
				}
			}
			Application.Logger.endStep();
			Application.Logger.addStep("12.Verify the thumbs up and thumbs down for the records in Literature Tab",
					"Thumbs up and thumbs down should be retained for the records in Literature Tab");
			page_ChemicalSearchResults.clickOnTabLiterature();
			for (int i = 1; i < 4; i++) {
				if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedButtonThumsUpWithFillColor(i)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs up is be retained for the record :" + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "Thumbs up is not be retained for the record :" + i,
							true);
				}
			}
			for (int j = 5; j < 9; j++) {
				if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedButtonThumsDownWithFillColor(j)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs Down is be retained for the record :" + j,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Thumbs Down is not be retained for the record :." + j, true);
				}
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-750* */

	@Test
	public boolean validateDoNotRetainVoteCasteForSameSearch(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(" "));
		boolean flag = true;

		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			int resultsCountBeforeNewSearch = page_ChemicalSearchResults.getResultsCount();
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on thumbs up for few records.",
					"Thumbs up icon should be changed to solid green for the records");
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.waitTime(3);
			for (int i = 1; i < 4; i++) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Click on thumbs Down for few records.",
					"Thumbs Down icon should be changed to solid green for the records");
			for (int j = 5; j < 9; j++) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Enter the text in the search text box and click on search icon",
					"Results must be refreshed");
			page_ChemicalSearchResults.closeAllExistingKeyWords();
			page_ChemicalSearchResults.setFirstKeyWord(finalKeywordList);
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			int resultsCountAfterNewSearch = page_ChemicalSearchResults.getResultsCount();
			if (resultsCountBeforeNewSearch == resultsCountAfterNewSearch) {
				Application.Logger.addsubStep(LogStatus.PASS, "Result set get refreshed with new search", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Result set is not get refreshed with new search", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.Verify the thumbs up and thumbs down for the records",
					"Thumbs up and thumbs down should not be retained for the records");
			for (int i = 1; i < 4; i++) {
				if (!page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsUpWithFillColor(i)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs up is not be retained for the record :" + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "Thumbs up is be retained for the record :" + i,
							true);
				}
			}
			for (int j = 5; j < 9; j++) {
				if (!page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsDownWithFillColor(j)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs Down is not be retained for the record :" + j,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Thumbs Down is not be retained for the record :." + j, true);
				}
			}
			Application.Logger.endStep();

			Application.Logger.addStep(
					"6.Retain vote caste for filters - Click on Patent tab and Click on thumbs UP for few records",
					"Thumbs up icon should be changed to solid green for the records.");
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			page_ChemicalSearchResults.clickOnTabPatent();
			for (int i = 1; i < 4; i++) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.Click on thumbs Down for few records.",
					"Thumbs Down icon should be changed to solid green for the records");
			for (int j = 5; j < 9; j++) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.Click on Literature tab and Click on thumbs UP for few records",
					"Thumbs up icon should be changed to solid green for the records.");
			page_ChemicalSearchResults.clickOnTabLiterature();
			for (int i = 1; i < 4; i++) {
				page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonThumsUp(i);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("9.Click on thumbs Down for few records.",
					"Thumbs Down icon should be changed to solid green for the records");
			for (int j = 5; j < 9; j++) {
				page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonThumsDown(j);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"10.Click on fliter link and Select few filter fields and click on Apply Filters button",
					"Should open filter with all the filter fields in collapsed state. and Results and charts must be refreshed");
			if (page_ChemicalSearchResults.isDisplayedFilterFieldsInCollapsedState()) {
				Application.Logger.addsubStep(LogStatus.PASS, "All the fields in filter are in collapsed state", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "All the fields in filter are not in collapsed state",
						true);
			}
			page_ChemicalSearchResults.clickOnAnyCheckboxBasedOnFiltersName("Publication year", 23);
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("11.Verify the thumbs up and thumbs down for the records in Patent Tabs",
					"Thumbs up and thumbs down should be retained for the records in Patent Tab");
			page_ChemicalSearchResults.clickOnTabPatent();
			for (int i = 1; i < 4; i++) {
				if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsUpWithFillColor(i)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs up is be retained for the record :" + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "Thumbs up is not be retained for the record :" + i,
							true);
				}
			}
			for (int j = 5; j < 9; j++) {
				if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedButtonThumsDownWithFillColor(j)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs Down is be retained for the record :" + j,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"Thumbs Down is not be retained for the record :." + j, true);
				}
			}
			Application.Logger.endStep();
			Application.Logger.addStep("12.Verify the thumbs up and thumbs down for the records in Literature Tab",
					"Thumbs up and thumbs down should be retained for the records in Literature Tab");
			page_ChemicalSearchResults.clickOnTabLiterature();
			for (int i = 1; i < 1; i++) {
				if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedButtonThumsUpWithFillColor(i)) {
					Application.Logger.addsubStep(LogStatus.PASS, "Thumbs up is be retained for the record :" + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "Thumbs up is not be retained for the record :" + i,
							true);
				}
			}
			/*
			 * for(int j=1;j<1;j++) {
			 * if(page_ChemicalSearchResults.tabLiteratureSearch().
			 * isDisplayedButtonThumsDownWithFillColor(j)) {
			 * Application.Logger.addsubStep(LogStatus.PASS,
			 * "Thumbs Down is be retained for the record :"+j, false); } else {
			 * Application.Logger.addsubStep(LogStatus.FAIL,
			 * "Thumbs Down is not be retained for the record :."+j, true); } }
			 */
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-745 */

	@Test
	public boolean validateSearchIcon(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(","));
		String lengthySearchText = "Novelty\r\n"
				+ "A superabsorbent polymer comprises a base resin powder containing a crosslinked polymer of a water-soluble ethylenically unsaturated monomer including partially neutralized acidic group(s), and pores having diameter of 1 μ m or more, formed on the base resin powder, and has apparent density of more than 0.55 g/ml. The crosslinked polymer comprises layered silicate-based particles dispersed in the crosslinked structure";
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.Edit Keywords in the search box",
					"Search Icon Must turn green/become enabled if something is edited in the search box");
			page_ChemicalSearchResults.clickOnPillBox(1);
			if (page_ChemicalSearchResults.getColorOfSerachIcon().equals("false")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Search Icon is displaying with 'Green' color", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, " Search Icon is not displaying with 'Green' color",
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.Remove few Pills on the search box",
					"Search Icon Must turn green/become enabled if pill is Removed in the search box");
			page_ChemicalSearchResults.removePillBox(1);
			if (page_ChemicalSearchResults.getColorOfSerachIcon().equals("false")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Search Icon is displaying with 'Green' color", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, " Search Icon is not displaying with 'Green' color.",
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.Add keyword in the search box",
					"Search Icon Must turn green/become enabled if keyword is inserted in the search box");
			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
			if (page_ChemicalSearchResults.getColorOfSerachIcon().equals("false")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Search Icon is displaying with 'Green' color", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Search Icon is not displaying with 'Green' color", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.Make changes on Top section",
					"while making change in top section,Botton Section will not changeble and gray out the bottom section");
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			page_ChemicalSearchResults.setTextPhrase(lengthySearchText);
			page_ChemicalSearchResults.clickOnPillBox(1);
			page_ChemicalSearchResults.isDisabledBottomSection();
			if (page_ChemicalSearchResults.getColorOfButtomSection().equals("rgba(255, 255, 255, 1)")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Bottom section is  grayed out", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Bottom section is not grayed out", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.Make changes on Botton section",
					"The top section will not change when make changes in bottom section (it includes removing all the keywords) ");
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.setKeyWords(finalKeywordList);
			if (page_ChemicalSearchResults.isDisabledTopSection()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Top section is not allow to changable.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Top section is allow to changable.", true);
			}
			if (page_ChemicalSearchResults.getColorOfTopSection().equals("rgba(0, 0, 0, 0)")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Top section is  grayed out", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Top section is not grayed out", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/** CHEMEXP-751--Patent-PDF not available */

	public boolean validatePdfNotAvailable(Controller Application, HashMap<String, String> input) {
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
			Application.Logger.endStep();
			Application.Logger.addStep("2.Click on PDF", "PDF is not available message displays");

			page_ChemicalSearchResults.clickOnLinkPdf();
			Application.waitTime(12);
			Application.switchToOtherWindow(CurrentWindowHandleID);
			String pdfText = page_ChemicalSearchResults.getTextPdfNotAvailable();
			if (pdfText.equals("PDF is not available"))
				Application.Logger.addsubStep(LogStatus.PASS,
						"PDF window opens and PDF is not available message has been displayed", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "PDF is opened instead of PDF is not available message.",
						true);
			Application.Logger.endStep();
		} catch (Exception e) {
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

	// CHEMEXP-759

	/*
	 * 
	 * Steps: 1.Login to the application 2.Perform search with
	 * "antibacterial polyethylene resin containing transition metals" 3.Click
	 * on the PDF icon Expected: 1.Should logged in successfully 2.Search is
	 * performed 3.PDF is opened
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
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SET IS DISPLAYED FOR THE TEXT SEARCH AND  PDF WINDOW IS OPENED", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.waitTime(2);
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

	// CHEMEXP-764
	/*
	 * 
	 * Steps: 1.Login to the application 2.Perform search with
	 * "no data available" 3.Switch to literature tab 4.Perform same operation
	 * for Literature Expected: 1.Should logged in successfully 2.Search is
	 * performed 3.Hmmm,we are not getting any results message should display
	 * 4.Hmmm,we are not getting any results message should display
	 */

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
			Application.waitTime(2);
			String actualErrorMsg = page_ChemicalSearchResults.getTextNoResultsPage();
			Application.waitTime(2);
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
	/* CHEMEXP-760 */

	public boolean validatePatentAndLiteratureResultSetPagination(Controller Application,
			HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE AND Pagination ",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AND PAGINATION SHOULD BE VERIFIED");
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
			String dropdownDefaultValue = page_ChemicalSearchResults.tabPatentSearch()
					.getValueFromItemsPerPageDropdown();
			Application.waitTime(2);
			if (dropdownDefaultValue.equals("20"))
				Application.Logger.addsubStep(LogStatus.PASS, "default value for items per page dropdown is 20", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL,
						"default value for items per page dropdown is other than 20", true);

			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledPreviousPageArrow())
				Application.Logger.addsubStep(LogStatus.PASS, "previous page arrow is disabled", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "previous page arrow is not disabled", true);
			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("10");
			String tenItemsPerPage = page_ChemicalSearchResults.tabPatentSearch().getValueFromItemsPerPageDropdown();
			Application.waitTime(2);
			if (tenItemsPerPage.equals("10"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 10 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 10 records per page", true);

			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("50");
			String fiftyItemsPerPage = page_ChemicalSearchResults.tabPatentSearch().getValueFromItemsPerPageDropdown();
			if (fiftyItemsPerPage.equals("50"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 50 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 50 records per page", true);

			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("100");
			Application.waitTime(2);
			String hundredItemsPerPage = page_ChemicalSearchResults.tabPatentSearch()
					.getValueFromItemsPerPageDropdown();
			if (hundredItemsPerPage.equals("100"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 100 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 100 records per page", true);

			String paginatorRange = page_ChemicalSearchResults.tabPatentSearch().getTextPaginatorRange();
			String[] paginatorValue = paginatorRange.split("of");
			if (paginatorValue[1].trim().equals("1000"))
				Application.Logger.addsubStep(LogStatus.PASS, "max record that can display is 1000", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "max record that can display is not 1000", true);

			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("100");
			page_ChemicalSearchResults.tabPatentSearch().clickOnArrowNextPageTillLastPage();

			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledNextPageArrow())
				Application.Logger.addsubStep(LogStatus.PASS, "next page arrow is disabled", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "next page arrow is not disabled", true);
			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("20");

			page_ChemicalSearchResults.clickOnTabLiterature();

			if (dropdownDefaultValue.equals("20"))
				Application.Logger.addsubStep(LogStatus.PASS, "default value for items per page dropdown is 20", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL,
						"default value for items per page dropdown is other than 20", true);

			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledPreviousPageArrow())
				Application.Logger.addsubStep(LogStatus.PASS, "previous page arrow is disabled", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "previous page arrow is not disabled", true);
			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("10");

			if (tenItemsPerPage.equals("10"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 10 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 10 records per page", true);

			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("50");
			if (fiftyItemsPerPage.equals("50"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 50 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 50 records per page", true);

			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("100");
			if (hundredItemsPerPage.equals("100"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 100 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 100 records per page", true);
			if (paginatorValue[1].trim().equals("1000"))
				Application.Logger.addsubStep(LogStatus.PASS, "max record that can display is 1000", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "max record that can display is not 1000", true);

			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("100");
			page_ChemicalSearchResults.tabPatentSearch().clickOnArrowNextPageTillLastPage();

			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledNextPageArrow())
				Application.Logger.addsubStep(LogStatus.PASS, "next page arrow is disabled", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "next page arrow is not disabled", true);

			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	/* CHEMEXP-761 */

	public boolean validateFeedbackFunctionality(Controller Application, HashMap<String, String> input) {
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VERIFY THE LANDING PAGE AND Feedback functionality ",
					"Feedback should be submitted successfully");
			page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
			page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
			page_ChemicalSearchLandingPage.clickOnLinkFeedback();
			if (page_ChemicalSearchLandingPage.isDisplayedFieldDropdown())
				Application.Logger.addsubStep(LogStatus.PASS, "dropdown is displayed", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "dropdown is not displayed", true);
			if (page_ChemicalSearchLandingPage.isDisplayedButtonSubmitFeedback())
				Application.Logger.addsubStep(LogStatus.PASS, "button submit feedback is displayed", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "button submit feedback is not displayed", true);
			if (page_ChemicalSearchLandingPage.isDisplayedLinkContactUs())
				Application.Logger.addsubStep(LogStatus.PASS, "link contact us is displayed", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "link contact us is not displayed", true);
			page_ChemicalSearchLandingPage.selectFeedbackValueFromDropdown("Search");
			page_ChemicalSearchLandingPage.enterValueInFeedbackArea("good");
			page_ChemicalSearchLandingPage.clickOnButtonSubmitFeedback();
			if (!page_ChemicalSearchLandingPage.isDisplayedDialogBoxFeedback())
				Application.Logger.addsubStep(LogStatus.PASS,
						"feedback submitted successfully and the dialog box is closed", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL,
						"feedback has not submitted successfully and the dialog box is not closed", true);

			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/*
	 * CHEMEXP-762
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
			Application.Logger.endStep();

			page_ChemicalSearchResults.clickOnExpandDropdown();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	/*****
	 * 
	 * CHEMEXP-754--Filters-Clear all Filters
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

			Application.Logger.addStep("2.Expand the Assignee section and click on Select all  ",
					"Each filter is expandable/collapsible and All the items must get selected");
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnExpandDropdown();
			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonSelectAllForGivenFilter("Assignee");
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			if (page_ChemicalSearchResults.tabPatentSearch().isEnabledButtonClearAll()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ClearAll button is in enable mode", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ClearAll button is in disable mode", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Now Click on clear all filters",
					".It must clear all applied filters. It must clear all selections in each field the results are filtered on. Clear all filters will be disabled until a selection is made. Clear all will \"Reset\" and bring the Result set to initial state (result set to the state before the filters were applied).");
			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonClearAllFilters();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledButtonClearAll()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ClearAll button is in disable mode", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ClearAll button is in enable mode", true);
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
	 * CHEMEXP-756--Filters-Clicking on X from pill box
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
			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonSelectAllForGivenFilter("Assignee");
			int beforeResultsCount = page_ChemicalSearchResults.getResultsCount();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.click on 'x' next to item in pill component",
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
	 * CHEMEXP-755
	 * 
	 * Pre-requiste: Login to the application Steps: 1. Enter the keywords or
	 * natural language block of text for their search criteria. 2.Enter upto
	 * 2000 characters 3.Enter/try to insert more than 2000 characters 4.Copy
	 * and Paste charaters to search box Expected: 1.Must accept keywords or
	 * natural language block of text for their search criteria. 2.Search box
	 * must accept up-to 2000 characters 3.Once the limit is reached, should
	 * display 2000/2000 in red to indicate user cannot enter beyond this limit.
	 * 4.Characters should be pasted
	 */

	@Test
	public boolean validateLimitationOfCharactersInLandingSearchBox(Controller Application,
			HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int StringLength;
		String expErrorMessage = "2000 / 2000";
		String actualErrorMessage, expTextMessage;
		String phrase = "A multilayer container, the container comprising:an outer layer defining an exterior surface,"
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
			Application.Logger.addStep("1.ENTER THE KEYWORDS OR NATURAL LANGUAGE UP TO 2000 CHARACTERS",
					"SEARCH TEXT BOX SHOULD ACCEPT UPTO 2000 CHARACTERS");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			StringLength = searchText.length();
			if (StringLength <= 1999) {
				Application.Logger.addsubStep(LogStatus.PASS, "SEARCH BOX ACCEPTS LESS THAN 2000 CHARACTERS", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "SEARCH BOX ACCEPTS MORE THAN 2000 CHARACTERS", true);
			}
			page_ChemicalSearchLandingPage.deleteTextSearchTextBox();
			Application.Logger.endStep();
			Application.Logger.addStep("2.ENTER BEYOND 2000 CHARACTERS",
					"ERROR MESSAGE 2000/2000  SHOULD BE DISPLAYED");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(phrase);
			actualErrorMessage = page_ChemicalSearchLandingPage.getTextLimitErrorMessage();
			if (actualErrorMessage.equalsIgnoreCase(expErrorMessage)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"2000 / 2000 ERROR MESSAGE IS DISPLAYED WHEN USER ENTERS MORE THAN 2000 CHARACTERS", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"2000 / 2000 ERROR MESSAGE IS NOT DISPLAYED WHEN USER ENTERS MORE THAN 2000 CHARACTERS", true);
			}
			page_ChemicalSearchLandingPage.deleteTextSearchTextBox();
			Application.Logger.endStep();
			Application.Logger.addStep("3.VERIFY COPY AND PASTE IN THE SEACRCH TEXT BOX",
					"TEXT SHOULD BE COPIED AND PASTED");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.copyAndPasteTextSearchTextBox();
			Application.waitTime(2);
			expTextMessage = page_ChemicalSearchLandingPage.getTextSearchTextBox();
			if (expTextMessage.isEmpty()) {

				Application.Logger.addsubStep(LogStatus.PASS,
						"TEXT IS COPIED IS AND PASTED BACK IN THE SEARCH TEXT BOX", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"TEXT IS NOT COPIED AND PASTED BACK IN THE SEARCH TEXT BOX", true);
			}
			page_ChemicalSearchLandingPage.deleteTextSearchTextBox();
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	/* CHEMEXP-757 */

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
			String searchInCaps = "BASF";
			String searchInSmall = "basf";

			page_ChemicalSearchLandingPage.setSearchText(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.clickOnTabPatent();
			page_ChemicalSearchResults.clickOnExpandDropdown();
			int beforeCount = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfFilterListForGivenFilter("Assignee");
			page_ChemicalSearchResults.tabPatentSearch().enterTextInSearchAreaForGivenFilter("Assignee", searchInCaps);

			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedCloseMarkX()) {
				Application.Logger.addsubStep(LogStatus.PASS, "close mark X is displayed successfully", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "close mark X is not displayed ", true);
			}

			String highlightedFilterList = page_ChemicalSearchResults.tabPatentSearch()
					.getTextTheHighlightedFilterList();
			if (highlightedFilterList.equalsIgnoreCase(searchInCaps)) {
				Application.Logger.addsubStep(LogStatus.PASS, "the search text is highlighted ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "the search text is not highlighted ", true);
			}
			page_ChemicalSearchResults.tabPatentSearch().clickOnCloseMarkX();
			page_ChemicalSearchResults.tabPatentSearch().enterTextInSearchAreaForGivenFilter("Assignee", searchInSmall);
			String highlightedFilterListSearchedInSmall = page_ChemicalSearchResults.tabPatentSearch()
					.getTextTheHighlightedFilterList();

			if (highlightedFilterList.equalsIgnoreCase(highlightedFilterListSearchedInSmall)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"both the highlighted text are the same so search is case insensative", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"both the highlighted text are not the same so search is not case insensative", true);
			}
			page_ChemicalSearchResults.tabPatentSearch().clickOnCloseMarkX();
			int afterCount = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfFilterListForGivenFilter("Assignee");
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

	/* CHEMEXP-763 */

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
			page_ChemicalSearchResults.tabPatentSearch().clickOnButtonSelectAllForGivenFilter("Assignee");
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			if (page_ChemicalSearchResults.tabPatentSearch().isEnabledButtonClearAll()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ClearAll button is in enable mode", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ClearAll button is in disable mode", true);
			}
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedCrossXForAllThePills()) {
				Application.Logger.addsubStep(LogStatus.PASS, "all the pill value has cross mark", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "all the pill value is not having cross mark", true);
			}
			if (page_ChemicalSearchResults.tabPatentSearch()
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

	// CHEMEXP-931

	// @Test
	public boolean validateClustermapBubbleResultset(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("RESULTS SET IS NOT LOADED FOR THE CHEMICAL SEARCH");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.CLICK ON CLUSTER MAP ", "RS SHOULD BE DISPLAYED");
			page_ChemicalSearchResults.clickOnClustermapLink();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("RESULTS SET IS NOT LOADED FOR THE CHEMICAL SEARCH");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.CLICK ON BUBBLE ", "RS SHOULD BE REFRESHED");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnBubble();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.waitTime(2);
			Application.Logger.endStep();
			Application.Logger.addStep("4.CLICK ON SUBKEYWORD ", "RS SHOULD BE REFRESHED");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnSubKeyword();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t :\t" + afterFilter, true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.CLICK ON ANY LINE BETWEEN BUBBLE ", "RS SHOULD BE REFRESHED");
			page_ChemicalSearchResults.clickOnBubbleLine();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-932
	@Test
	public boolean validatePatentPlayersBarFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("RESULTS SET IS NOT LOADED FOR THE CHEMICAL SEARCH");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.NAVIGATE TO PLAYERS TAB AND CLICK ON ANY BAR FROM  TAB ",
					"RS SHOULD BE DISPLAYED");
			page_ChemicalSearchResults.clickOnPlayersTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER  FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.CHECK FILTER ASSIGNEE FIELD  FROM  FILTERS ",
					"ASSIGNEE FIELD SHOULD BE DISPLAYED");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ASSIGNEE FILTER FIELD IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ASSIGNEE FILTER FIELD  IS ENABLED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.DESELECT THE FILTERED BAR FROM PLAYERS ", "BAR SHOULD BE FILTERED");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER  FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.CHECK FILTER ASSIGNEE FIELD  FROM  FILTERS ",
					"ASSIGNEE FIELD SHOULD BE DISPLAYED");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "ASSIGNEE FILTER FIELD IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "ASSIGNEE FILTER FIELD  IS NOT DISABLED", true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-933

	@Test
	public boolean validatePatentPlayersTextFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("RESULTS SET IS NOT LOADED FOR THE CHEMICAL SEARCH");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Navigate to Players tab and click on any bar from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.clickOnPlayersTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersText();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.CHECK FILTER ASSIGNEE FIELD  FROM  FILTERS ",
					"ASSIGNEE FIELD SHOULD BE DISPLAYED");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ASSIGNEE FILTER FIELD IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ASSIGNEE FILTER FIELD  IS ENABLED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.DESELECT THE FILTERED TEXT FROM PLAYERS ", "TEXT SHOULD BE FILTERED");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersText();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER PY FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.CHECK FILTER ASSIGNEE FIELD  FROM  FILTERS ",
					"ASSIGNEE FIELD SHOULD BE DISPLAYED");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "ASSIGNEE FILTER FIELD IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "ASSIGNEE FILTER FIELD  IS NOT DISABLED", true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-934

	@Test
	public boolean validatePatentInventorsBarFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("RESULTS SET IS NOT LOADED FOR THE CHEMICAL SEARCH");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.NAVIGATE TO INVENTORS TAB AND CLICK ON ANY BAR FROM  TAB ",
					"RS SHOULD BE DISPLAYED");
			page_ChemicalSearchResults.tabPatentSearch().clickOnInventorsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().clickOnInventorsBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.CHECK FILTER INVENTOR FIELD  IS DISABLED ",
					" INVENTORFIELD SHOULD BE DISPLAYED");
			if (page_ChemicalSearchResults.isDisabledFilterInventorField()) {
				Application.Logger.addsubStep(LogStatus.PASS, "INVENTOR FILTER FIELD IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "INVENTOR FILTER FIELD  IS ENABLED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.DESELECT THE FILTERED TEXT FROM INVENTORS ", "TEXT SHOULD BE FILTERED");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().clickOnInventorsBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Check Filter Inventor field  is disabled ",
					"Assignee field should not be displayed");
			if (page_ChemicalSearchResults.isDisabledFilterInventorField()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "Inventor FILTER FIELD IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "Inventor FILTER FIELD  IS NOT DISABLED", true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-935
	@Test
	public boolean validatePatentInventorsTextFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Navigate to Inventors tab and click on any text from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.tabPatentSearch().clickOnInventorsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().clickOnInventorsText();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Check Filter Inventors field  is disabled ",
					"Inventor field should be disabled");
			if (page_ChemicalSearchResults.isDisabledFilterInventorField()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Inventor filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Inventor filter field  IS ENABLED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.Deselect the filtered text from players ", "text should be filtered");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().clickOnInventorsText();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER PY FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Check Filter Inventor field  is disabled ",
					"Inventors field should not be disabled");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "Inventor filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "Inventor filter field  IS not Disabled", true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-936
	@Test
	public boolean validateLiteraturePlayersBarFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Navigate to Players tab and click on any bar from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.clickOnPlayersTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Check Filter Organization field  is disabled ",
					"Assignee field should be disabled");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Organization filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Organization filter field  IS ENABLED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.Deselect the filtered bar from players ", "bar should be filtered");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Check Filter Organization field  is disabled ",
					"Assignee field should not be disabled");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "Organization filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "Organization filter field  IS not Disabled", true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-937
	@Test
	public boolean validateLiteraturePlayersTextFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Switch to Literature tab", "Results should be refreshed");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Navigate to Players tab and click on any text from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.clickOnPlayersTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersText();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.Check Filter Organization field  from  Filters ",
					"Assignee field should be displayed");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Organization filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Organization filter field  IS ENABLED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Deselect the filtered text from players ", "text should be filtered");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersText();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("6.Check Filter Organization field  is disabled",
					"Organization field should not be disabled");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledFilterField()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "Organization filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "Organization filter field  IS not Disabled", true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-938
	@Test
	public boolean validateLiteratureAuthorsBarFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Switch to Literature tab", "Results should be refreshed");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Navigate to Authors tab and click on any bar from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnAuthorsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnAuthorsBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.Check Filter Authors field  is disabled ",
					"Authors field should be displayed");
			if (page_ChemicalSearchResults.isDisabledFilterInventorField()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Authors filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Authors filter field  IS ENABLED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Deselect the filtered bar from players ", "bar should be filtered");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnAuthorsBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("6.Check Filter Authors field  is not disabled ",
					"Authors field should not be displayed");
			if (page_ChemicalSearchResults.isDisabledFilterInventorField()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "Authors filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "Authors filter field  IS not Disabled", true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-939
	@Test
	public boolean validateLiteratureAuthorsTextFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Switch to Literature tab", "Results should be refreshed");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Navigate to Authors tab and click on any text from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnAuthorsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnAuthorsText();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.Check Filter Authors field  from  Filters ",
					"Authors field should be displayed");
			if (page_ChemicalSearchResults.isDisabledFilterInventorField()) {
				Application.Logger.addsubStep(LogStatus.PASS, "Authors filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Authors filter field  IS ENABLED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Deselect the filtered text from Authors ", "text should be filtered");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnAuthorsText();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER  FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("6.Check Filter Authors field  is disabled ",
					"Authors field should not be disabled");
			if (page_ChemicalSearchResults.isDisabledFilterInventorField()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "Authors filter field IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "Authors filter field  IS not Disabled", true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-942
	@Test
	public boolean validatePatentKeywordsBarFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Navigate to Keywords tab and click on any bar from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.clickOnKeywordsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnKeywordsBar();
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-943
	@Test
	public boolean validatePatentKeywordsTextFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Navigate to Keywords tab and click on any text from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.clickOnKeywordsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnKeywordsText();
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-940

	@Test
	public boolean validateLiteratureKeywordsBarFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Switch to Literature tab", "Results should be refreshed");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Navigate to Keywords tab and click on any bar from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.clickOnKeywordsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnKeywordsBar();
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-941
	@Test
	public boolean validateLiteratureKeywordsTextFromInsights(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Switch to Literature tab", "Results should be refreshed");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Navigate to Keywords tab and click on any text from  tab ",
					"RS should be displayed");
			page_ChemicalSearchResults.clickOnKeywordsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnKeywordsText();
			page_ChemicalSearchResults.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-944
	@Test
	public boolean validatePatentClearallPlayersInventors(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Navigate to Players tab and click on any bar",
					"RS refreshed for filtered bar");
			page_ChemicalSearchResults.clickOnPlayersTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Click on Clear All ", "RS Should be refreshed");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnClearAllPlayers();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.Navigate to Inventors tab and click on any bar",
					"RS refreshed for filtered bar");
			page_ChemicalSearchResults.tabPatentSearch().clickOnInventorsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().clickOnInventorsBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Click on Clear All ", "RS Should be refreshed");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnClearAllInventors();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-945

	@Test
	public boolean validateLiteratureClearallPlayersAuthors(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE",
					"USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.Switch to Literature tab", "Results should be refreshed");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.Navigate to Players tab and click on any bar",
					"RS refreshed for filtered bar");
			page_ChemicalSearchResults.clickOnPlayersTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnPlayersBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.Click on Clear All ", "RS Should be refreshed");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnClearAllPlayers();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.Navigate to Authors tab and click on any bar",
					"RS refreshed for filtered bar");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnAuthorsTab();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnAuthorsBar();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("6.Click on Clear All ", "RS Should be refreshed");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnClearAllInventors();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(2);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-946
	@Test
	public boolean validateGuideToolTipsMessage(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String expToastMsg = "You are in Help mode. Use the toggle in the Support menu to turn the tips on and off any time.";
		String actualToastMsg, expToastMesg = "";
		String actualShowTips, expShowTips = "Show tips";
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.SET THE SHOW TIPS OPTION TO ON FROM SUPPORT MENU AND VERIFY THE GUIDED TIPS TOAST MESSAGE IN THE RS PAGE",
					"GUIDED TIPS TOAST MESSAGE SHOULD BE DISPLAYED IN RS PAGE");
			page_ChemicalSearchLandingPage.clickOnLinkSupportMenu();
			actualShowTips = page_ChemicalSearchLandingPage.getTextShowTips();
			if (expShowTips.trim().equals(actualShowTips.trim())) {
				Application.Logger.addsubStep(LogStatus.PASS, "SHOW TIPS MENU LABEL IS AS EXPECTED AND DISPLAYED",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "SHOW TIPS MENU LABEL IS NOT DISPLAYED", true);
			}
			page_ChemicalSearchLandingPage.setToolTipsOptionON();
			page_ChemicalSearchLandingPage.clickOnLinkSupportMenu();
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			/*
			 * Application.waitUntilFectchRecordProgressBarToDisappears(); if
			 * (page_ChemicalSearchResults.checkIfResultsFound()) {
			 * Application.Logger.addsubStep(LogStatus.PASS,
			 * "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false); } else {
			 * throw new Exception(
			 * "Results set is not loaded for the chemical search"); }
			 */
			// Application.waitTime(2);
			actualToastMsg = page_ChemicalSearchResults.getTextGuidedTipsToastMessage();
			if (expToastMsg.equals(actualToastMsg)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"GUIDED TIPS TOAST MESSAGE IS SUCCESSFULLY DISPLAYED IN THE RS PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"GUIDED TIPS TOAST MESSAGE IS NOT DISPLAYED IN THE RS PAGE", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.VERIFY THE GUIDED TIPS CUE IN KEYWORDS AND INSIGHT SECTION IN RS PAGE",
					"GUIDED TIPS CUE SHOULD BE DISPLAYED IN KEYWORDS AND INSIGHTS SECTION");
			if (page_ChemicalSearchResults.isDisplayedGuidedTipsKeywordCue()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"GUIDED TIPS KEYWORD CUE IS SUCCESSFULLY DISPLAYED IN THE TOP SECTION OF THE RS PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"GUIDED TIPS KEYWORD CUE IS NOT DISPLAYED IN THE TOP SECTION OF THE RS PAGE", true);
			}

			if (page_ChemicalSearchResults.isDisplayedInsightsGuidedTipsKeywordCue()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"GUIDED TIPS KEYWORD CUE IN INSIGHTS SECTION IS SUCCESSFULLY DISPLAYED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"GUIDED TIPS KEYWORD CUE IN INSIGHTS SECTION IS NOT DISPLAYED", true);
			}
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			Application.Logger.endStep();

			Application.Logger.addStep(
					"3.SET THE SHOW TIPS OPTION TO OFF AND VERIFY THE GUIDED TIPS CUE IN KEYWORDS AND INSIGHT SECTION IN RS PAGE",
					"GUIDED TIPS CUE SHOULD NOT BE DISPLAYED IN KEYWORDS AND INSIGHTS SECTION");
			page_ChemicalSearchLandingPage.clickOnLinkSupportMenu();
			page_ChemicalSearchLandingPage.setToolTipsOptionOFF();
			page_ChemicalSearchLandingPage.clickOnLinkSupportMenu();
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			if (!page_ChemicalSearchResults.isNotDisplayedGuidedTipsKeywordCue()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"GUIDED TIPS KEYWORD CUE IS NOT  DISPLAYED IN THE TOP SECTION OF THE RS PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"GUIDED TIPS KEYWORD CUE IS  DISPLAYED IN THE TOP SECTION OF THE RS PAGE", true);
			}
			if (!page_ChemicalSearchResults.isNotDisplayedInsightsGuidedTipsKeywordCue()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"GUIDED TIPS KEYWORD CUE IN INSIGHTS SECTION IS NOT DISPLAYED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"GUIDED TIPS KEYWORD CUE IN INSIGHTS SECTION IS DISPLAYED", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-947
	@Test
	public boolean validatePatentPublicationYearChart(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String expLabel = "Earliest publication per family";
		String actLabel, firstToolTip, secondToolTip;
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.VERIFY THE PUBLICATION CHART IN EXPANDED STATE",
					"PUBLICATION CHART SHOULD BE IN EXPANDED STATE");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedPublicationYearChartExpanded()) {
				Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR CHART IS DISPLAYED IN EXPANDED STATE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PUBLICATION YEAR CHART IS NOT DISPLAYED IN EXPANDED STATE", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.VERIFY THE PUBLICATION CHART LABEL",
					"PUBLICATION CHART LABEL SHOULD BE DISPLAYED");
			actLabel = page_ChemicalSearchResults.tabPatentSearch().getTextPublicationChartLabelName();
			if (actLabel.trim().equalsIgnoreCase(expLabel.trim())) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"EXPECTED AND ACTUAL PUBLICATION YEAR CHART LABEL NAMES ARE MATCHING", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"EXPECTED AND ACTUAL PUBLICATION YEAR CHART LABEL NAMES ARE NOT MATCHING", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"4.SELECT MULTIPLE BARS , COLLPASE THE CHART AND SWITCH TO LITERATURE TAB AND AGAIN SWITCH TO PATENT TAB",
					"MULTIPLE BARS SHOULD BE SELECTED AND PY CHART SHOULD BE COLLAPSED AND SESSION STICKY SHOULD BE MAINTAINED AND LITERATURE TAB SHOULD BE IN EXPANDED STATE");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().selectMultiplePublicationChartFilters();
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter > afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AFTER THE PUBLICATION FILTER CHART FILTER", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER THE PUBLICATION FILTER CHART FILTER, BEFORE FILTER : \t"
								+ beforeFilter + "\t\t AFTER PY FILTER :\t" + afterFilter,
						true);
			}
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedPublicationYearChartExpanded()) {
				page_ChemicalSearchResults.tabPatentSearch().clickOnPYChartExpandCollapseIcon();
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION YEAR CHART IS COLLAPSED AFTER THE CLICK ON THE COLLAPSE ICON", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART IS NOT COLLAPSED", true);
			}
			page_ChemicalSearchResults.clickOnTabLiterature();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedPublicationYearChartExpanded()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION YEAR CHART IN LITERATURE TAB IS DISPLAYED IN EXPANDED STATE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PUBLICATION YEAR CHART IN LITERATURE TAB IS NOT DISPLAYED IN EXPANDED STATE", true);
			}
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (!page_ChemicalSearchResults.tabPatentSearch().isDisplayedPublicationYearChartExpanded()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION YEAR CHART REMAINS IN COLLAPSED STATE WHILE SWICTHING BETWEEN THE PATENT AND LITERATURE TABS AND SESSION IS STICKY",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART IS NOT COLLAPSED", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.VERIFY THE LEFT AND RIGHT ARROWS IN PUBLICATION CHART",
					"LEFT ARROW SHOULD BE ENABLED FIRST AND RIGHT ARROW SHOULD BE ENABLED AFTER THE CLICK OF LEFT ARROW");
			page_ChemicalSearchResults.tabPatentSearch().clickOnPYChartExpandCollapseIcon();
			{
				if (page_ChemicalSearchResults.tabPatentSearch().isEnabledPYCharLeftArrow()) {
					Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR CHART LEFT ARROW IS ENABLED",
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART LEFT ARROW IS DISABLED",
							true);
				}

				if (page_ChemicalSearchResults.tabPatentSearch().isDisabledPYChartRightArrow()) {
					Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR CHART RIGHT ARROW IS DISABLED",
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART RIGHT ARROW IS ENABLED",
							true);
				}
				page_ChemicalSearchResults.tabPatentSearch().clickOnPYChartLeftArrow();
				if (page_ChemicalSearchResults.tabPatentSearch().isEnabledPYChartRightArrow()) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"PUBLICATION YEAR CHART RIGHT ARROW IS ENABLED AFTETR THE CLICK OF LEFT ARROW", false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART RIGHT ARROW IS DISABLED",
							true);
				}
				Application.Logger.endStep();

			}
			Application.Logger.addStep(
					"6.VERIFY PUBLICATION YEAR TEXT FILTER IS DISABLED WHILE ACCESSING PUBLICATION YEAR CHART FILTERS",
					"PUBLICATION YEAR TEXT FILTER SHOULD BE DISABLED");
			page_ChemicalSearchResults.tabPatentSearch().clickOnPublicationYearFilter();
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledPublicationYearFilter()) {
				Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR TEXT FILTER IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR TEXT FILTER IS ENABLED", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"7.VERIFY PUBLICATION YEAR CHAR FILTER IS DISABLED WHILE ACCESSING PUBLICATION YEAR TEXT FILTERS",
					"PUBLICATION YEAR CHART FILTER SHOULD BE DISABLED");
			page_ChemicalSearchResults.tabPatentSearch().deSelectMultiplePublicationChartFilters();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnAnyCheckboxBasedOnFiltersName("Publication year", 19);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter > afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AFTER THE PUBLICATION YEAR TEXT FILTER", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER THE PUBLICATION YEAR TEXT FILTER, BEFORE FILTER : \t"
								+ beforeFilter + "\t\t AFTER PY FILTER :\t" + afterFilter,
						true);
			}
			page_ChemicalSearchResults.tabPatentSearch().clickOnPublicationYearChart();
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledPublicationYearChart()) {
				Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR CHART FILTER IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART FILTER IS ENABLED", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-948
	@Test
	public boolean validateLiteraturePublicationYearChart(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String expLabel = "Publication timeline";
		String actLabel, firstToolTip, secondToolTip;
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.VERIFY THE PUBLICATION CHART IN EXPANDED STATE",
					"PUBLICATION CHART SHOULD BE IN EXPANDED STATE");
			page_ChemicalSearchResults.clickOnTabLiterature();
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedPublicationYearChartExpanded()) {
				Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR CHART IS DISPLAYED IN EXPANDED STATE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PUBLICATION YEAR CHART IS NOT DISPLAYED IN EXPANDED STATE", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.VERIFY THE PUBLICATION CHART LABEL",
					"PUBLICATION CHART LABEL SHOULD BE DISPLAYED");
			actLabel = page_ChemicalSearchResults.tabLiteratureSearch().getTextPublicationChartLabelName();
			if (actLabel.trim().equalsIgnoreCase(expLabel.trim())) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"EXPECTED AND ACTUAL PUBLICATION YEAR CHART LABEL NAMES ARE MATCHING", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"EXPECTED AND ACTUAL PUBLICATION YEAR CHART LABEL NAMES ARE NOT MATCHING", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"4.SELECT MULTIPLE BARS , COLLPASE THE CHART AND SWITCH TO PATENT TAB AND AGAIN SWITCH TO LITERATURE TAB",
					"MULTIPLE BARS SHOULD BE SELECTED AND PY CHART SHOULD BE COLLAPSED AND SESSION STICKY SHOULD BE MAINTAINED AND PATENT TAB SHOULD BE IN EXPANDED STATE");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabLiteratureSearch().selectMultiplePublicationChartFilters();
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter > afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AFTER THE PUBLICATION FILTER CHART FILTER", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER THE PUBLICATION FILTER CHART FILTER, BEFORE FILTER : \t"
								+ beforeFilter + "\t\t AFTER PY FILTER :\t" + afterFilter,
						true);
			}
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedPublicationYearChartExpanded()) {
				page_ChemicalSearchResults.tabLiteratureSearch().clickOnPYChartExpandCollapseIcon();
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION YEAR CHART IS COLLAPSED AFTER THE CLICK ON THE COLLAPSE ICON", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART IS NOT COLLAPSED", true);
			}
			page_ChemicalSearchResults.clickOnTabPatent();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedPublicationYearChartExpanded()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION YEAR CHART IN PATENT TAB IS DISPLAYED IN EXPANDED STATE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PUBLICATION YEAR CHART IN PATENT TAB IS NOT DISPLAYED IN EXPANDED STATE", true);
			}
			page_ChemicalSearchResults.clickOnTabLiterature();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (!page_ChemicalSearchResults.tabLiteratureSearch().isDisplayedPublicationYearChartExpanded()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION YEAR CHART REMAINS IN COLLAPSED STATE WHILE SWICTHING BETWEEN THE PATENT AND LITERATURE TABS AND SESSION IS STICKY",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART IS NOT COLLAPSED", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.VERIFY THE LEFT AND RIGHT ARROWS IN PUBLICATION CHART",
					"LEFT ARROW SHOULD BE ENABLED FIRST AND RIGHT ARROW SHOULD BE ENABLED AFTER THE CLICK OF LEFT ARROW");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnPYChartExpandCollapseIcon();
			{
				if (page_ChemicalSearchResults.tabLiteratureSearch().isEnabledPYCharLeftArrow()) {
					Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR CHART LEFT ARROW IS ENABLED",
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART LEFT ARROW IS DISABLED",
							true);
				}

				if (page_ChemicalSearchResults.tabLiteratureSearch().isDisabledPYChartRightArrow()) {
					Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR CHART RIGHT ARROW IS DISABLED",
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART RIGHT ARROW IS ENABLED",
							true);
				}
				page_ChemicalSearchResults.tabLiteratureSearch().clickOnPYChartLeftArrow();
				if (page_ChemicalSearchResults.tabLiteratureSearch().isEnabledPYChartRightArrow()) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"PUBLICATION YEAR CHART RIGHT ARROW IS ENABLED AFTETR THE CLICK OF LEFT ARROW", false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART RIGHT ARROW IS DISABLED",
							true);
				}
				Application.Logger.endStep();

			}
			Application.Logger.addStep(
					"6.VERIFY PUBLICATION YEAR TEXT FILTER IS DISABLED WHILE ACCESSING PUBLICATION YEAR CHART FILTERS",
					"PUBLICATION YEAR TEXT FILTER SHOULD BE DISABLED");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnPublicationYearFilter();
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisabledPublicationYearFilter()) {
				Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR TEXT FILTER IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR TEXT FILTER IS ENABLED", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"7.VERIFY PUBLICATION YEAR CHAR FILTER IS DISABLED WHILE ACCESSING PUBLICATION YEAR TEXT FILTERS",
					"PUBLICATION YEAR CHART FILTER SHOULD BE DISABLED");
			page_ChemicalSearchResults.tabLiteratureSearch().deSelectMultiplePublicationChartFilters();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnAnyCheckboxBasedOnFiltersName("Publication year", 19);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter > afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AFTER THE PUBLICATION YEAR TEXT FILTER", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER THE PUBLICATION YEAR TEXT FILTER, BEFORE FILTER : \t"
								+ beforeFilter + "\t\t AFTER PY FILTER :\t" + afterFilter,
						true);
			}
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnPublicationYearChart();
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisabledPublicationYearChart()) {
				Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION YEAR CHART FILTER IS DISABLED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION YEAR CHART FILTER IS ENABLED", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-949
	@Test
	public boolean validateManageFieldsClearAllRestoreDefaultOptions(Controller Application,
			HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> expectedManageFieldOptions = new ArrayList<String>(Arrays.asList("Publication number", "Title",
				"Publication date", "Novelty", "Assignee", "Use", "Inventor", "Advantage", "Example","First claim"));

		List<String> fields = new ArrayList<String>(Arrays.asList("Novelty", "Use", "Inventor", "Assignee"));
		List<String> checkBoxStatus = new ArrayList<String>();
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.COMPARE THE EXPECTED AND ACTUAL MANAGE FIELD OPTIONS",
					"OPTIONS SHOULD BE MATCHED");
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			List<String> actualManageFieldOptions = page_ChemicalSearchResults.manageFields()
					.getAllManageFieldsOptions();
			boolean blncheck = TestUtil.validateTwoLists(expectedManageFieldOptions, actualManageFieldOptions);
			if (blncheck) {
				Application.Logger.addsubStep(LogStatus.PASS, "EXPECTED AND ACTUAL MANAGE FIELD LABELS ARE MATCHING",
						false);
				Application.Logger.addsubStep(LogStatus.INFO,
						"ACTUAL MANAGE FIELD LABELS: " + actualManageFieldOptions.size() + actualManageFieldOptions,
						false);
				Application.Logger.addsubStep(LogStatus.INFO, "EXPECTED MANAGE FIELD LABELS: "
						+ expectedManageFieldOptions.size() + expectedManageFieldOptions, false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"EXPECTED AND ACTUAL MANAGE FIELD LABELS ARE NOT MATCHING", true);
				Application.Logger.addsubStep(LogStatus.INFO,
						"ACTUAL MANAGE FIELD LABELS: " + actualManageFieldOptions.size() + actualManageFieldOptions,
						false);
				Application.Logger.addsubStep(LogStatus.INFO, "EXPECTED MANAGE FIELD LABELS: "
						+ expectedManageFieldOptions.size() + expectedManageFieldOptions, false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"3.VERIFY THE NOVELTY,ASSIGNEE,USE AND INVENTOR CHECK BOXES AFTER CLICK ON CLEAR ALL BUTTON",
					"NOVELTY,ASSIGNEE,USE AND INVENTOR CHECK BOXES SHOULD BE DESELECTED");
			{
				page_ChemicalSearchResults.manageFields().clickOnButtonClearAll();
				page_ChemicalSearchResults.manageFields().clickOnButtonApply();
				page_ChemicalSearchResults.clickOnLinkManageFields();
				Application.waitTime(2);
				checkBoxStatus = page_ChemicalSearchResults.manageFields().getManageFieldCheckBoxStatus(fields);
				if (TestUtil.ListContains(checkBoxStatus, "false")) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"NOVELTY,ASSIGNEE,USE AND INVENTOR CHECK BOXES ARE UNCHECKED AFTER THE CLICK ON CLEAR ALL BUTTON",
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"NOVELTY,ASSIGNEE,USE AND INVENTOR CHECK BOXES ARE CHECKED AFTER THE CLICK ON CLEAR ALL BUTTON",
							true);
				}
				page_ChemicalSearchResults.manageFields().clickOnCloseIcon();
			}
			Application.Logger.endStep();
			/*Application.Logger.addStep(
					"4.VERIFY THE NOVELTY,ASSIGNEE,USE,INVENTOR CHECK BOXES AND DRAWIING SIZE(SMALL) AFTER CLICK  ON RESTORE DEFAULTS BUTTON",
					"NOVELTY,ASSIGNEE,USE,INVENTOR CHECK BOXES AND DRAWIING SIZE(SMALL) SHOULD BE SELECTED");
			{
				page_ChemicalSearchResults.clickOnLinkManageFields();
				Application.waitTime(2);
				page_ChemicalSearchResults.manageFields().clickOnButtonRestoreDefaults();
				page_ChemicalSearchResults.manageFields().clickOnButtonApply();
				page_ChemicalSearchResults.clickOnLinkManageFields();
				checkBoxStatus = page_ChemicalSearchResults.manageFields().getManageFieldCheckBoxStatus(fields);
				if (TestUtil.ListContains(checkBoxStatus, "true")) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"NOVELTY,ASSIGNEE,USE AND INVENTOR CHECK BOXES ARE CHECKED AFTER THE CLICK ON RESTORE DEFAULT BUTTON",
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"NOVELTY,ASSIGNEE,USE AND INVENTOR CHECK BOXES ARE CHECKED AFTER THE CLICK ON RESTORE DEFAULT BUTTON",
							true);
				}

				if (page_ChemicalSearchResults.manageFields().isRadioButtonSelected(1)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"DRAWING SIZE SMALL(Small 150x150) IS SEELCTED ON CLICK ON RESTORE DEFAULT BUTTON", false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"DRAWING SIZE SMALL(Small 150x150) IS NOT SEELCTED ON CLICK ON RESTORE DEFAULT BUTTON",
							false);
				}
			}
			Application.Logger.endStep();*/
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-950
	@Test
	public boolean validateManageFieldsNonDefaultOptions(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		List<String> fields = new ArrayList<String>(Arrays.asList("Publication date", "Advantage", "First claim"));
		List<String> checkBoxStatus = new ArrayList<String>();
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.SELECT PUBLICATION DATE,ADVANTAGE AND FIRST CLAIM OPTIONS AND VERIFY THE RS",
					"PUBLICATION DATE,ADVANTAGE AND FIRST CLAIM OPTIONS SHOULD BE DISPLAYED IN RS");
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			page_ChemicalSearchResults.manageFields().clickOnButtonClearAll();
			page_ChemicalSearchResults.manageFields().selectManageFieldCheckBoxes(fields);
			page_ChemicalSearchResults.manageFields().clickOnButtonApply();
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			checkBoxStatus = page_ChemicalSearchResults.manageFields().getManageFieldCheckBoxStatus(fields);
			if (TestUtil.ListContains(checkBoxStatus, "true")) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION DATE,ADVANTAGE AND FIRST CLAIM OPTION CHECK BOXES ARE SELECTED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PUBLICATION DATE,ADVANTAGE AND FIRST CLAIM OPTION CHECK BOXES ARE NOT SELECED", true);
			}
			page_ChemicalSearchResults.manageFields().clickOnCloseIcon();
			if (page_ChemicalSearchResults.isDisplayedPublished(1)) {
				Application.Logger.addsubStep(LogStatus.PASS, "PUBLICATION SECTION IS DISPLAYED IN RESULTS SET PAGE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PUBLICATION SECTION IS NOT DISPLAYED IN RESULTS SET PAGE", true);
			}
			if (page_ChemicalSearchResults.isDisplayedEarliestFamily(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"EARLIEST FAMILY MEMBER SECTION IS DISPLAYED IN RESULTS SET PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"EARLIEST FAMILY MEMBER SECTION IS NOT DISPLAYED IN RESULTS SET PAGE", true);
			}
			if (page_ChemicalSearchResults.isDisplayedAdvantage(1)) {
				Application.Logger.addsubStep(LogStatus.PASS, "ADVANTAGE SECTION IS DISPLAYED IN RESULTS SET PAGE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ADVANTAGE SECTION IS NOT DISPLAYED IN RESULTS SET PAGE",
						true);
			}

			if (page_ChemicalSearchResults.isDisplayedFirstClaim(1)) {
				Application.Logger.addsubStep(LogStatus.PASS, "FIRST CLAIM SECTION IS DISPLAYED IN RESULTS SET PAGE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"FIRST CLAIM SECTION IS NOT DISPLAYED IN RESULTS SET PAGE", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"3.DESELECT PUBLICATION DATE,ADVANTAGE AND FIRST CLAIM OPTIONS AND VERIFY THE RS",
					"PUBLICATION DATE,ADVANTAGE AND FIRST CLAIM OPTIONS SHOULD NOT BE DISPLAYED IN RS");
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			page_ChemicalSearchResults.manageFields().deSelectManageFieldCheckBoxes(fields);
			page_ChemicalSearchResults.manageFields().clickOnButtonApply();
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			checkBoxStatus = page_ChemicalSearchResults.manageFields().getManageFieldCheckBoxStatus(fields);
			if (TestUtil.ListContains(checkBoxStatus, "false")) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION DATE,ADVANTAGE AND FIRST CLAIM OPTION CHECK BOXES ARE NOT SELECTED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"PUBLICATION DATE,ADVANTAGE AND FIRST CLAIM OPTION CHECK BOXES ARE SELECED", true);
			}
			page_ChemicalSearchResults.manageFields().clickOnCloseIcon();
			if (!page_ChemicalSearchResults.isDisplayedPublished(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"PUBLICATION SECTION IS NOT DISPLAYED IN RESULTS SET PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "PUBLICATION SECTION IS DISPLAYED IN RESULTS SET PAGE",
						true);
			}
			if (!page_ChemicalSearchResults.isDisplayedEarliestFamily(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"EARLIEST FAMILY MEMBER SECTION IS NOT DISPLAYED IN RESULTS SET PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"EARLIEST FAMILY MEMBER SECTION IS DISPLAYED IN RESULTS SET PAGE", true);
			}
			if (!page_ChemicalSearchResults.isDisplayedAdvantage(1)) {
				Application.Logger.addsubStep(LogStatus.PASS, "ADVANTAGE SECTION IS NOT DISPLAYED IN RESULTS SET PAGE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ADVANTAGE SECTION IS DISPLAYED IN RESULTS SET PAGE",
						true);
			}

			if (!page_ChemicalSearchResults.isDisplayedFirstClaim(1)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"FIRST CLAIM SECTION IS NOT DISPLAYED IN RESULTS SET PAGE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "FIRST CLAIM SECTION IS DISPLAYED IN RESULTS SET PAGE",
						true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-951
	@Test
	public boolean validateManageFieldsDrawingSizeOptions(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String imgSize;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.SELECT SMALL SIZE DRAWING OPTION FROM THE MANAGE FIELD WINDOW",
					"SMALL IMAGE SHOULD BE DISPLAYED IN RS");
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			page_ChemicalSearchResults.manageFields().clickOnRadioButtonDrawingSize(1);
			if (page_ChemicalSearchResults.manageFields().isRadioButtonSelected(1)) {
				Application.Logger.addsubStep(LogStatus.PASS, "Small 150x150 OPTION IS SELECTED FROM MANAGE FIELD",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Small 150x150 OPTION IS NOT SELECTED FROM MANAGE FIELD",
						true);
			}
			page_ChemicalSearchResults.manageFields().clickOnButtonApply();
			Application.waitTime(2);
			imgSize = page_ChemicalSearchResults.getTextImageSize(1);
			if (imgSize.contains("150")) {
				Application.Logger.addsubStep(LogStatus.PASS, "SMALL SIZE IMAGE IS DISPLAYED IN RS", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "SMALL SIZE IMAGE IS NOT DISPLAYED IN RS", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.SELECT MEDIUM SIZE DRAWING OPTION FROM THE MANAGE FIELD WINDOW",
					"MEDIUM IMAGE SHOULD BE DISPLAYED IN RS");
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			page_ChemicalSearchResults.manageFields().clickOnRadioButtonDrawingSize(2);
			if (page_ChemicalSearchResults.manageFields().isRadioButtonSelected(2)) {
				Application.Logger.addsubStep(LogStatus.PASS, "Medium 280x280 OPTION IS SELECTED FROM MANAGE FIELD",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Medium 280x280 OPTION IS NOT SELECTED FROM MANAGE FIELD",
						true);
			}
			page_ChemicalSearchResults.manageFields().clickOnButtonApply();
			Application.waitTime(2);
			imgSize = page_ChemicalSearchResults.getTextImageSize(1);
			if (imgSize.contains("280")) {
				Application.Logger.addsubStep(LogStatus.PASS, "MEDIUM SIZE IMAGE IS DISPLAYED IN RS", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "MEDIUM SIZE IMAGE IS NOT DISPLAYED IN RS", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.SELECT LARGE SIZE DRAWING OPTION FROM THE MANAGE FIELD WINDOW",
					"LARGE IMAGE SHOULD BE DISPLAYED IN RS");
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			page_ChemicalSearchResults.manageFields().clickOnRadioButtonDrawingSize(3);
			if (page_ChemicalSearchResults.manageFields().isRadioButtonSelected(3)) {
				Application.Logger.addsubStep(LogStatus.PASS, "Large 360x360 OPTION IS SELECTED FROM MANAGE FIELD",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Large 360x360 OPTION IS NOT SELECTED FROM MANAGE FIELD",
						true);
			}
			page_ChemicalSearchResults.manageFields().clickOnButtonApply();
			Application.waitTime(2);
			imgSize = page_ChemicalSearchResults.getTextImageSize(1);
			if (imgSize.contains("360")) {
				Application.Logger.addsubStep(LogStatus.PASS, "LARGE SIZE IMAGE IS DISPLAYED IN RS", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "LARGE SIZE IMAGE IS NOT DISPLAYED IN RS", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	//// CHEMEXP-1016
	@Test
	public boolean validatePatentMoreLikeThis(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String rsPublicationNumber, moreLikePublicationNumber, rsAssignee, MoreLikeAssignee, rsInventor,
				MoreLikeInventor;
		int beforeMoreLikeFilter, afterMoreLikeFilter;
		List<String> fields = new ArrayList<String>(
				Arrays.asList("Title", "Novelty", "Assignee", "Use", "Inventor", "Advantage", "First claim"));
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			beforeMoreLikeFilter = page_ChemicalSearchResults.getResultsCount();
			Application.Logger.endStep();
			Application.Logger.addStep("2.PERFORM MORE LIKE THIS ON THE ASSIGNEE IN RS PAGE",
					"RESULT SETS SHOULD BE REFRESHED AND SHOULD DISPLAY RS FOR THE ASSIGNEE");
			page_ChemicalSearchResults.tabPatentSearch();
			page_ChemicalSearchResults.clickOnLinkManageFields();
			Application.waitTime(2);
			page_ChemicalSearchResults.manageFields().clickOnButtonClearAll();
			page_ChemicalSearchResults.manageFields().selectManageFieldCheckBoxes(fields);
			page_ChemicalSearchResults.manageFields().clickOnButtonApply();
			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkAssignee();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			afterMoreLikeFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeMoreLikeFilter <= afterMoreLikeFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "RESULT SETS ARE NOT REFRESHED  : \t"
						+ beforeMoreLikeFilter + "\t\t AFTER FILTER :\t" + afterMoreLikeFilter, true);
			}
			MoreLikeAssignee = page_ChemicalSearchResults.tabPatentSearch().getTextKeyWordMoreLike();
			for (int i = 1; i < 2; i++) {
				Application.waitTime(2);
				rsAssignee = page_ChemicalSearchResults.tabPatentSearch().getTextAssignee(i);
				if (MoreLikeAssignee.equalsIgnoreCase(rsAssignee)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"MORE LIKE ASSIGNEE IN THE KEYWORD : \t\t" + MoreLikeAssignee
									+ "\t\t IS MATCHING WITH RS ASSIGNEE :\t" + rsAssignee
									+ "FOR THE RECORD NUMBER:\t\t " + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"MORE LIKE ASSIGNEE IN THE KEYWORD : \t" + MoreLikeAssignee
									+ "\t\t IS NOT MATCHING WITH RS ASSIGNEE :\t" + rsAssignee
									+ "FOR THE RECORD NUMBER:\t\t " + i,
							true);
				}

			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.PERFORM MORE LIKE THIS ON THE INVENTOR IN RS PAGE",
					"RESULT SETS SHOULD BE REFRESHED AND SHOULD DISPLAY RS FOR THE INVENTOR");
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkInventor();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			MoreLikeInventor = page_ChemicalSearchResults.tabPatentSearch().getTextKeyWordMoreLike();
			for (int j = 1; j < 2; j++) {
				Application.waitTime(2);
				rsInventor = page_ChemicalSearchResults.tabPatentSearch().getTextInventor(j);
				if (MoreLikeInventor.equalsIgnoreCase(rsInventor)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"MORE LIKE INVENTOR IN THE KEYWORD : \t\t" + MoreLikeInventor
									+ "\t\t IS MATCHING WITH RS INVENTOR :\t" + rsInventor
									+ "FOR THE RECORD NUMBER:\t\t " + j,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"MORE LIKE INVENTOR IN THE KEYWORD : \t" + MoreLikeInventor
									+ "\t\t IS NOT MATCHING WITH RS INVENTOR :\t" + rsInventor
									+ "FOR THE RECORD NUMBER:\t\t " + j,
							true);
				}

			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.PERFORM MORE LIKE THIS ON THE PUBLICATION NUMBER IN RS PAGE",
					"RESULT SETS SHOULD BE REFRESHED AND SHOULD DISPLAY RECORDS MATCHING FOR THE PUBLICATION NUMBER ");
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkMoreLikeThis();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			rsPublicationNumber = page_ChemicalSearchResults.tabPatentSearch().getTextPublicationNumber();
			moreLikePublicationNumber = page_ChemicalSearchResults.tabPatentSearch().getTextKeyWordMoreLike();
			if (rsPublicationNumber.equals(moreLikePublicationNumber)) {
				Application.Logger
						.addsubStep(LogStatus.PASS,
								"MORE LIKE PUBLICATION NUMBER IN THE KEYWORD : \t\t" + moreLikePublicationNumber
										+ "\t\t IS MATCHING WITH RS PUBLICATION NUMBER :\t" + rsPublicationNumber,
								false);
			} else {
				Application.Logger
						.addsubStep(LogStatus.FAIL,
								"MORE LIKE PUBLICATION NUMBER IN THE KEYWORD : \t" + moreLikePublicationNumber
										+ "\t\t IS NOT MATCHING WITH RS PUBLICATION NUMBER :\t" + rsPublicationNumber,
								true);
			}

			Application.Logger.endStep();
			Application.Logger.addStep("5.PERFORM MORE LIKE THIS ON THE ASSIGNEE FROM PATENT RECORD VIEW",
					"RESULT SETS SHOULD BE REFRESHED AND SHOULD DISPLAY RS FOR THE ASSIGNEE");
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkPatentRecordViewAssignee();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			MoreLikeAssignee = page_ChemicalSearchResults.tabPatentSearch().getTextKeyWordMoreLike();
			for (int i = 1; i < 2; i++) {
				Application.waitTime(2);
				rsAssignee = page_ChemicalSearchResults.tabPatentSearch().getTextAssignee(i);
				if (MoreLikeAssignee.equalsIgnoreCase(rsAssignee)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"MORE LIKE ASSIGNEE IN THE KEYWORD : \t\t" + MoreLikeAssignee
									+ "\t\t IS MATCHING WITH RS ASSIGNEE :\t" + rsAssignee
									+ "FOR THE RECORD NUMBER:\t\t " + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"MORE LIKE ASSIGNEE IN THE KEYWORD : \t" + MoreLikeAssignee
									+ "\t\t IS NOT MATCHING WITH RS ASSIGNEE :\t" + rsAssignee
									+ "FOR THE RECORD NUMBER:\t\t " + i,
							true);
				}

			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.PERFORM MORE LIKE THIS ON THE INVENTOR FROM PATENT RECORD VIEW",
					"RESULT SETS SHOULD BE REFRESHED AND SHOULD DISPLAY RS FOR THE INVENTOR");
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkPatentRecordViewInventor();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			MoreLikeInventor = page_ChemicalSearchResults.tabPatentSearch().getTextKeyWordMoreLike();
			for (int i = 1; i < 2; i++) {
				Application.waitTime(2);
				rsInventor = page_ChemicalSearchResults.tabPatentSearch().getTextInventor(i);
				if (MoreLikeInventor.equalsIgnoreCase(rsInventor)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"MORE LIKE INVENTOR IN THE KEYWORD : \t\t" + MoreLikeInventor
									+ "\t\t IS MATCHING WITH RS INVENTOR :\t" + rsInventor
									+ "FOR THE RECORD NUMBER:\t\t " + i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"MORE LIKE INVENTOR IN THE KEYWORD : \t" + MoreLikeInventor
									+ "\t\t IS NOT MATCHING WITH RS INVENTOR :\t" + rsInventor
									+ "FOR THE RECORD NUMBER:\t\t " + i,
							true);
				}

			}
			Application.Logger.endStep();
			Application.Logger.addStep("7.VERIFY SEARCHED KEYWORDS ARE HIGHLIGHTED IN RS",
					"KEYWORD SHOULD BE HIGHLIGHTED IN RS");
			page_ChemicalSearchResults.clickOnTabPatent();
			int noOfTimesHightedOfGivenKeyword = page_ChemicalSearchResults.tabPatentSearch()
					.getCountOfHighlightedTextInRS(searchText);
			if (noOfTimesHightedOfGivenKeyword > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenKeyword
						+ "\t time the given keyword \t" + searchText + " are heighlighted in Record Set", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"keyword" + searchText + " are not heighlighted in Record Set", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.VERIFY THE KEYWORD SECTION IS DISABLED IN MORE LIKE THIS MODE",
					"KEYWORD SHOULD BE DISABLED MODE IN MORE LIKE THIS MODE");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledKeyWordSection()) {
				Application.Logger.addsubStep(LogStatus.PASS, "KEY WORD SECTION IS IN DISABLE MODE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "KEY WORD SECTION IS NOT IN DISABLE MODE", false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("9.VERIFY IF THE MORE LIKE OPTION IS CLOSED ON CLICK OF CLOSE ICON",
					"USER SHOULD BE SUCCESSFULLY MOVED OUT OF MORE LIKE MODE");
			page_ChemicalSearchResults.tabPatentSearch().clickOnCloseIconMoreLike();
			afterMoreLikeFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeMoreLikeFilter == afterMoreLikeFilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AND USER IS OUT OF MORE LIKE THIS MODE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER CLOSING THE MORE LIKE THIS MODE : \t"
								+ beforeMoreLikeFilter + "\t\t AFTER FILTER :\t" + afterMoreLikeFilter,
						true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1017

	@Test
	public boolean validateLiteratureMoreLikeThis(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String rsLitTitle, moreLikeLitTitle, rsAuthor, MoreLikeAuthor, rsInventor, MoreLikeInventor;
		int beforeMoreLikeFilter, afterMoreLikeFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.clickOnTabLiterature();
			beforeMoreLikeFilter = page_ChemicalSearchResults.getResultsCount();
			Application.Logger.endStep();
			Application.Logger.addStep("2.PERFORM MORE LIKE THIS ON THE LITERATURE TITLE IN RS PAGE",
					"RESULT SETS SHOULD BE REFRESHED AND SHOULD DISPLAY RECORDS MATCHING FOR THE LITERATURE TITLE");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnLinkMoreLikeThis();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			Application.waitTime(3);
			rsLitTitle = page_ChemicalSearchResults.tabLiteratureSearch().getTextLiteratureTitle(1);
			Application.waitTime(2);
			moreLikeLitTitle = page_ChemicalSearchResults.tabLiteratureSearch().getTextKeyWordMoreLike();
			afterMoreLikeFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeMoreLikeFilter >= afterMoreLikeFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "RESULT SETS ARE NOT REFRESHED  : \t"
						+ beforeMoreLikeFilter + "\t\t AFTER FILTER :\t" + afterMoreLikeFilter, true);
			}
			if (rsLitTitle.equals(moreLikeLitTitle)) {
				Application.Logger.addsubStep(LogStatus.PASS, "MORE LIKE LITERATURE TITLE IN THE KEYWORD : \t\t"
						+ moreLikeLitTitle + "\t\t IS MATCHING WITH RS LITERATURE TITLE :\t" + rsLitTitle, false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "MORE LIKE LITERATURE TITLE IN THE KEYWORD : \t"
						+ moreLikeLitTitle + "\t\t IS NOT MATCHING WITH RS LITERATURE TITLE:\t" + rsLitTitle, true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.VERIFY THE SEARCHED KEYWORDS ARE HIGHIGHTED IN RS",
					"KEYWORD SHOULD BE HIGHLIGHTED IN LITERATURE RS");
			int noOfTimesHightedOfGivenKeywords = page_ChemicalSearchResults.tabLiteratureSearch()
					.getCountOfHighlightedTextInRS(searchText);
			if (noOfTimesHightedOfGivenKeywords > 0) {
				Application.Logger.addsubStep(LogStatus.PASS, noOfTimesHightedOfGivenKeywords
						+ "\t TIME THE GIVEN KEYWORD \t" + searchText + " ARE HIGHLIIGHTED IN RESULT SET", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"KEYWORD" + searchText + " \t ARE NOT HIGHLIIGHTED IN RESULT SET \t", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.PERFORM MORE LIKE THIS ON THE AUTHOR FROM LITERATURE RECORD VIEW",
					"RESULT SETS SHOULD BE REFRESHED AND SHOULD DISPLAY RS FOR THE AUTHOR");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnLiteratureRecord(1);
			Application.waitTime(2);
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnLinkLiteratureRecordViewAuthor();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			MoreLikeAuthor = page_ChemicalSearchResults.tabLiteratureSearch().getTextKeyWordMoreLike();
			for (int i = 1; i <= 3; i++) {
				Application.waitTime(2);
				page_ChemicalSearchResults.tabLiteratureSearch().clickOnLiteratureRecord(i);
				rsAuthor = page_ChemicalSearchResults.tabLiteratureSearch().getTextLiteratureRecordViewAuthor();
				page_ChemicalSearchResults.tabLiteratureSearch().clickOnCloseLiteratureRecordView();
				if (MoreLikeAuthor.equalsIgnoreCase(rsAuthor)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"MORE LIKE AUTHOR IN THE KEYWORD : \t\t" + MoreLikeAuthor
									+ "\t\t IS MATCHING WITH RS AUTHOR :\t" + rsAuthor + "FOR THE RECORD NUMBER:\t\t "
									+ i,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"MORE LIKE AUTHOR IN THE KEYWORD : \t" + MoreLikeAuthor
									+ "\t\t IS NOT MATCHING WITH RS AUTHOR :\t" + rsAuthor
									+ "FOR THE RECORD NUMBER:\t\t " + i,
							true);
				}

			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.VERIFY THE KEYWORD SECTION IS DISABLED IN MORE LIKE THIS MODE",
					"KEYWORD SHOULD BE DISABLED MODE IN MORE LIKE THIS MODE");
			if (page_ChemicalSearchResults.tabLiteratureSearch().isDisabledKeyWordSection()) {
				Application.Logger.addsubStep(LogStatus.PASS, "KEY WORD SECTION IS IN DISABLE MODE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "KEY WORD SECTION IS NOT IN DISABLE MODE", false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("6.VERIFY IF THE MORE LIKE OPTION IS CLOSED ON CLICK OF CLOSE ICON",
					"USER SHOULD BE SUCCESSFULLY MOVED OUT OF MORE LIKE MODE");
			page_ChemicalSearchResults.tabLiteratureSearch().clickOnCloseIconMoreLike();
			afterMoreLikeFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeMoreLikeFilter == afterMoreLikeFilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AND USER IS OUT OF MORE LIKE THIS MODE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER CLOSING THE MORE LIKE THIS MODE : \t"
								+ beforeMoreLikeFilter + "\t\t AFTER FILTER :\t" + afterMoreLikeFilter,
						true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1018

	@Test
	public boolean validateMoreFiltersDropdownForFilters(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String searchfields = input.get("Fields");
		LinkedHashSet<String> actualfield = new LinkedHashSet<String>();
		List<String> expectedfield = new ArrayList<String>(Arrays.asList(searchfields.split("\n")));
		List<String> expected = new ArrayList<String>();
		for (String field : expectedfield) {
			expected.add(field.toUpperCase().trim());
		}
		List<String> actual;

		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep(
					"2.REMOVE ALL FILTER FIELDS FROM FILTER SECTION AND VERIFY ALL THE FILTER FIEDS ARE AVAILABLE IN DROPDOWN",
					"ALL THE FILTER FIELDS SHOULD PRESENT IN DROPDOWN");
			page_ChemicalSearchResults.tabPatentSearch().clickCloseOnFilterFields();
			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().clickOnMoreFiltersDropdown();
			actualfield = page_ChemicalSearchResults.tabPatentSearch().getFilterFields();
			Application.waitTime(2);
			actual = new ArrayList<String>(actualfield);
			boolean blncheck = TestUtil.validateTwoLists(actual, expected);
			if (blncheck) {
				Application.Logger.addsubStep(LogStatus.PASS, "Actual list is matched with expected", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Actual list is not matched with expected", false);
				Application.Logger.addsubStep(LogStatus.INFO, "Actual list: " + actual.size() + actual, false);
				Application.Logger.addsubStep(LogStatus.INFO, "Expected list: " + expected.size() + expected, false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.ADD ALL THE FILTER FIELDS AND VERIFY IN ADD BUTTON",
					"ADD BUTTON SHOULD BE DISABLED AFTER ADDING ALL FILTER FIELDS");
			actual = new ArrayList<String>(actualfield);
			page_ChemicalSearchResults.tabPatentSearch().addAllDropdownFields(actual);
			Application.waitTime(2);
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledAddButton()) {
				Application.Logger.addsubStep(LogStatus.PASS, " Add button is disabled", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Add button is not disabled", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.VERIFY FILTER FIELDS ARE PRESENT IN FILTER SECTION",
					"ALL THE FILTER FIELDS SHOULD PRESENT IN FILTER SECTION");
			boolean allFiltersAddedToList = page_ChemicalSearchResults.tabPatentSearch()
					.verifyFilterFieldsInFilterSection(expectedfield);
			if (allFiltersAddedToList) {
				Application.Logger.addsubStep(LogStatus.PASS, "Actual list is matched with expected", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Actual list is not matched with expected", false);
				Application.Logger.addsubStep(LogStatus.INFO, "Actual list: " + actual.size() + actual, false);
				Application.Logger.addsubStep(LogStatus.INFO, "Expected list: " + expected.size() + expected, false);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1019

	@Test
	public boolean validatePatentDWPITable(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String expDwpiPublicationNumber, recordViewPublicationNumer;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.VERIFY THE DWPI TABLE  SECTION IN PATENT RECORD VIEW",
					"DWPI TABLE SECTION SHOULD BE EXPANDED BY DEFAULT");
			page_ChemicalSearchResults.clickOnTabPatent();
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
			if (page_ChemicalSearchResults.tabPatentSearch().isExpandedDwpiTable()) {
				Application.Logger.addsubStep(LogStatus.PASS, "DWPI TABLE SECTION IS EXPANDED BY DEFAULT", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "DWPI TABLE SECTION IS NOT EXPANDED BY DEFAULT", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.VERIFY THE DWPI TABLE SECTION GETS COLLAPSED ON CLICK",
					"DWPI TABLE SECTION SHOULD BE COLLAPSED");
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkDwpiTable();
			if (page_ChemicalSearchResults.tabPatentSearch().isCollapsedDwpiTable()) {
				Application.Logger.addsubStep(LogStatus.PASS, "DWPI TABLE SECTION IS COLLAPSED ON CLICK", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "DWPI TABLE SECTION IS NOT COLLAPSED ON CLICK", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep(
					"4.VERIFY THAT CLICK ON PUBLICATION NUMBER FROM DWPI TABLE SECTION PAINTS THE RECORD VIEW FOR THE SELECTED NUMBER",
					"RECORD VIEW SHOULD BE PAINTED BACK FOR THE SELECTED PUBLICATION NUMBER");
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkDwpiTable();
			if (page_ChemicalSearchResults.tabPatentSearch().isExpandedDwpiTable()) {
				expDwpiPublicationNumber = page_ChemicalSearchResults.tabPatentSearch().getDwpiPublicationNumber();
				page_ChemicalSearchResults.tabPatentSearch().clickOnDwpiPublicationNumber();
				recordViewPublicationNumer = page_ChemicalSearchResults.tabPatentSearch()
						.getPublicationNumberRecordView();
				if (expDwpiPublicationNumber.equals(recordViewPublicationNumer)) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"DWPI PUBLICATION NUMBER : \t\t" + expDwpiPublicationNumber
									+ "\t\t IS MATCHING WITH RECORD VIEW DWPI PUBLICATION NUMBER :\t"
									+ recordViewPublicationNumer,
							false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"DWPI PUBLICATION NUMBER : \t\t" + expDwpiPublicationNumber
									+ "\t\t IS NOT MATCHING WITH RECORD VIEW DWPI PUBLICATION NUMBER :\t"
									+ recordViewPublicationNumer,
							true);
				}
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1020

	@Test
	public boolean validatePatentCitations(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String expCitingTitle, patRecordViewTitle, expCitedTitle;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("2.VERIFY THE CITING PATENT SECTION IN Patent RECORD VIEW",
					"CITING PATENT SECTION SHOULD BE COLLAPSED BY DEFAULT");
			// page_ChemicalSearchResults.clickOnTabLiterature();
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
			if (page_ChemicalSearchResults.tabPatentSearch().isCollapsedCitingPatent()) {
				Application.Logger.addsubStep(LogStatus.PASS, "CITING Patent SECTION IS COLLAPSED BY DEFAULT", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "CITING Patent SECTION IS NOT COLLAPSED BY DEFAULT",
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.VERIFY THE CITED PATENT SECTION IN Patent RECORD VIEW",
					"CITED PATENT SECTION SHOULD BE COLLAPSED BY DEFAULT");
			if (page_ChemicalSearchResults.tabPatentSearch().isCollapsedCitedPatent()) {
				Application.Logger.addsubStep(LogStatus.PASS, "CITED Patent SECTION IS COLLAPSED BY DEFAULT", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "CITED PATENT SECTION IS NOT COLLAPSED BY DEFAULT", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.VERIFY THE CITING Patent SECTION GETS EXPANDED ON CLICK",
					"CITING PATENT SECTION SHOULD BE EXPANDED");
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkCitingPatent();
			if (page_ChemicalSearchResults.tabPatentSearch().isExpandedCitingPatent()) {
				Application.Logger.addsubStep(LogStatus.PASS, "CITING PATENT SECTION IS EXPANDED ON CLICK", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "CITING PATENT SECTION IS NOT EXPANDED ON CLICK", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.VERIFY THE CITED PATENT SECTION GETS EXPANDED ON CLICK",
					"CITED PATENT SECTION SHOULD BE EXPANDED");
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkCitedPatent();
			if (page_ChemicalSearchResults.tabPatentSearch().isExpandedCitedPatent()) {
				Application.Logger.addsubStep(LogStatus.PASS, "CITED PATENT SECTION IS EXPANDED ON CLICK", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "CITED PATENT SECTION IS NOT EXPANDED ON CLICK", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"6.VERIFY THAT CLICK ON CITING TITLE FROM CITING Patent SECTION PAINTS THE RECORD VIEW FOR THE SELECTED TITLE",
					"RECORD VIEW SHOULD BE PAINTED BACK FOR THE SELECTED CITING TITLE");
			if (page_ChemicalSearchResults.tabPatentSearch().isExpandedCitingPatent()) {
				expCitingTitle = page_ChemicalSearchResults.tabPatentSearch().getTextCitingTitle();
				page_ChemicalSearchResults.tabPatentSearch().clickOnCitingPatentTitle();
				patRecordViewTitle = page_ChemicalSearchResults.tabPatentSearch().getTextPatentRecordViewTitle();
				if (expCitingTitle.equals(patRecordViewTitle)) {
					Application.Logger
							.addsubStep(LogStatus.PASS,
									"CITING PATENT TITLE : \t\t" + expCitingTitle
											+ "\t\t IS MATCHING WITH RECORD VIEW Patent TITLE :\t" + patRecordViewTitle,
									false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"CITING Patent TITLE : \t\t" + expCitingTitle
									+ "\t\t IS NOT MATCHING WITH RECORD VIEW Patent TITLE :\t" + patRecordViewTitle,
							true);
				}

			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"CITING PATENT TITLE IS NOT AVAILABLE IN CITING PATENT SECTION", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep(
					"7.VERIFY THAT CLICK ON CITED TITLE FROM CITED PATENT SECTION PAINTS THE RECORD VIEW FOR THE SELECTED TITLE",
					"RECORD VIEW SHOULD BE PAINTED BACK FOR THE SELECTED CITED TITLE");
			if (page_ChemicalSearchResults.tabPatentSearch().isExpandedCitedPatent()) {
				expCitedTitle = page_ChemicalSearchResults.tabPatentSearch().getTextCitedTitle();
				page_ChemicalSearchResults.tabPatentSearch().clickOnCitedPatentTitle();
				patRecordViewTitle = page_ChemicalSearchResults.tabPatentSearch().getTextPatentRecordViewTitle();
				if (expCitedTitle.equals(patRecordViewTitle)) {
					Application.Logger
							.addsubStep(LogStatus.PASS,
									"CITED Patent TITLE : \t\t" + expCitedTitle
											+ "\t\t IS MATCHING WITH RECORD VIEW Patent TITLE :\t" + patRecordViewTitle,
									false);
				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"CITED Patent TITLE : \t\t" + expCitedTitle
									+ "\t\t IS NOT MATCHING WITH RECORD VIEW Patent TITLE :\t" + patRecordViewTitle,
							true);
				}

			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"CITED PATENT TITLE IS NOT AVAILABLE IN CITED PATENT SECTION", false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"8.VERIFY THE CITING PATENT SECTION REMAINS IN EXPANDED STATE WHILE ACCESSING ANOTHER PATENT RECORD",
					"CITING PATENT SECTION SHOULD BE IN EXPANDED STATE");
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(4);
			if (page_ChemicalSearchResults.tabPatentSearch().isExpandedCitingPatent()) {
				Application.Logger.addsubStep(LogStatus.PASS, "CITING PATENT SECTION REAMAINS IN EXPANDED STATE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "CITING PATENT SECTION IS NOT IN EXPANDED STATE", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"9.VERIFY THE CITED PATENT SECTION REMAINS IN EXPANDED STATE WHILE ACCESSING ANOTHER PATENT RECORD",
					"CITED PATENT SECTION SHOULD BE IN EXPANDED STATE");
			if (page_ChemicalSearchResults.tabPatentSearch().isExpandedCitedPatent()) {
				Application.Logger.addsubStep(LogStatus.PASS, "CITED PATENT SECTION REMAINS IN EXPANDED STATE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "CITED PATENT SECTION IS NOT IN EXPANDED STATE", true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1086

	@Test
	public boolean validateSavedRecordsFeature(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String createdFolderName = "editCreatedFolders";
		String singleFolderName = "singleRecordFolders";
		String multipleFolderName = "multipleRecordFolder";
		String bigSavedRecordFoldername = "A multilayer container the container comprising:an outer layer defining an exterior surface, an inner layer defining an interior surface and an intern";
		String expErrorMessage = "This name exists. Please give a different name.";
		page_SavedRecords = new Page_SavedRecords(Application);
		String actErrorMessage;
		int folderLength;
		String recordViewTitle, expectedTitle, expFolderName, checkboxStatus;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.CREATE NEW FOLDER IN THE SAVED RECORDS PAGE",
					"FOLDER SHOULD BE CREATED SUCCESSFULLY");
			page_SavedRecords.clickOnLinkSavedRecords();
			page_SavedRecords.deleteExistingFolders();
			page_SavedRecords.clickOnLinkCreateNewFolder();
			page_SavedRecords.setTextFolderName(singleFolderName);
			Application.waitTime(2);
			page_SavedRecords.clickOnLinkSavedRecordsCreate();
			Application.waitTime(2);
			boolean folderExists = page_SavedRecords.isFolderDisplayed(singleFolderName);
			if (folderExists) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"NEW FOLDER IS CREATED SUCCESSFULLY WITH THE NAME :" + singleFolderName, false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"NEW FOLDER IS NOT CREATED SUCCESSFULLY WITH THE NAME :" + singleFolderName, true);
			}

			Application.Logger.endStep();

			Application.Logger.addStep(
					"2.VERIFY IF EEROR MESSAGE IS DISPLAYED ON CREATING THE FOLDER NAME WITH THE EXISTING NAME",
					"ERROR MESSAGE SHOULD BE DISPLAYED AND FOLDER SHOULD NOT BE CREATED");
			Application.waitTime(2);
			page_SavedRecords.clickOnLinkCreateNewFolder();
			page_SavedRecords.setTextFolderName(singleFolderName);
			actErrorMessage = page_SavedRecords.getTextFolderErrorMEssage();
			if (actErrorMessage.equals(expErrorMessage)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"ERROR MESSAGE IS DISPLAYED ON ENTERING THE EXISTING FOLDER NAME AT THE TIME OF CREATION",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"ERROR MESSAGE IS NOT DISPLAYED ON ENTERING THE EXISTING FOLDER NAME AT THE TIME OF CREATION : EXPECTED ERROR MESAGE : \t"
								+ expErrorMessage + "\t\t ACTUAL ERROR MESSAGE :\t" + actErrorMessage,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.VERIFY IF THE FOLDER NAME ACCEPTS UPTO 150 CHARACTERS FROM RS FOOTER",
					"FOLDER NAME SHOULD ACCEPT UPTO 150 CHARACTERS FROM RS FOOTER");
			Application.waitTime(2);
			page_SavedRecords.setTextFolderName(bigSavedRecordFoldername);
			Application.waitTime(2);
			page_SavedRecords.clickOnLinkCreate();
			Application.waitTime(2);
			folderLength = page_SavedRecords.getFolderNameSize(bigSavedRecordFoldername);
			if (folderLength == 150) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"NEW FOLDER IS SUCCESSFULLY CREATED WITH UPTO 150 CHARACTERS FROM RS FOOTER", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"NEW FOLDER IS NOT SUCCESSFULLY CREATED WITH UPTO 150 CHARACTERS FROM RS FOOTER", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.EDIT CREATED FOLDER IN THE SAVED RECORDS PAGE",
					"FOLDER SHOULD BE EDITED SUCCESSFULLY");
			page_SavedRecords.clickOnButtonEdit();
			page_SavedRecords.setTextFolderName(createdFolderName);
			Application.waitTime(2);
			page_SavedRecords.clickOnButtonSave();
			Application.waitTime(2);
			boolean editFolderExists = page_SavedRecords.isFolderDisplayed(createdFolderName);
			if (editFolderExists) {
				Application.Logger.addsubStep(LogStatus.PASS, "FOLDER NAME IS EDITED SUCCESSFULLY", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "FOLDER NAME IS NOT EDITED SUCCESSFULLY", true);
			}

			Application.Logger.endStep();

			Application.Logger.addStep("5.VERIFY THE CANCEL BUTTON AFTER CLICKING EDIT AND DELETE THE FOLDER",
					"NAME OF THE FOLDER SHOULD NOT BE CHANGED AND FOLDER SHOULD BE DELETED");
			page_SavedRecords.clickOnButtonEdit();
			Application.waitTime(2);
			page_SavedRecords.setTextFolderName(singleFolderName);
			page_SavedRecords.clickOnButtonCancel();
			Application.waitTime(2);
			boolean expectedFolderExists = page_SavedRecords.isFolderDisplayed(createdFolderName);
			if (expectedFolderExists) {
				Application.Logger.addsubStep(LogStatus.PASS, "FOLDER NAME IS NOT CHANGED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "FOLDER NAME IS NOT CHANGED ", true);
			}
			page_SavedRecords.clickOnButtonDelete();
			Application.waitTime(2);
			Application.Logger.endStep();

			Application.Logger.addStep(
					"6.VERIFY IF THE NEW FOLDER IS CREATED SUCCESSFULLY FROM FOOTER RESULTS SET PAGE",
					"FOLDER SHOULD BE CREATED SUCCESSFULLY");
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("RESULTS SET IS NOT LOADED FOR THE CHEMICAL SEARCH");
			}

			Application.waitUntilFectchRecordProgressBarToDisappears();
			page_ChemicalSearchResults.clickOnRSFooterGlobalCheckBox();
			page_ChemicalSearchResults.clickOnRsFooterSaveIcon();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnRsFooterCreateNewFolder();
			page_ChemicalSearchResults.setTextFolderName(multipleFolderName);
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnLinkCreate();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnFolderNameCheckBox(multipleFolderName);
			Application.waitTime(2);
			expFolderName = page_ChemicalSearchResults.getTextFolderName(multipleFolderName);
			if (expFolderName.equals(multipleFolderName)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"NEW FOLDER IS CREATED SUCCESSFULLY IN THE RS FOOTER WITH THE NAME :" + multipleFolderName,
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"NEW FOLDER IS NOT CREATED SUCCESSFULLY IN THE RS FOOTER WITH THE NAME :" + multipleFolderName,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("7.CLICK ON RECORD TITLE ", "RECORD VIEW SHOULD PAINT WITH THE SAME TITLE");
			page_SavedRecords.clickOnLinkSavedRecords();
			expectedTitle = page_SavedRecords.getTitle();
			page_SavedRecords.clickOnTitle();
			recordViewTitle = page_SavedRecords.getTitleRecordView();
			if (expectedTitle.contains(recordViewTitle)) {

				Application.Logger.addsubStep(LogStatus.PASS, "EXPECTED RECORD VIEW TITLE SHOULD PAINT ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "EXPECTED RECORD VIEW TITLE NOT EXISTS   ", true);
			}

			Application.Logger.endStep();

			Application.Logger.addStep(
					"8.NAVIGATE TO SAVED RECORDS PAGE SELECT ANY RECORD  AND DELETE SELECTED RECORD ",
					"SELECTED RECORD SHOULD BE DELETED");
			page_SavedRecords.clickOnLinkSavedRecords();
			page_SavedRecords.clickOnSavedRecordCheckBox();
			page_SavedRecords.clickOnSavedRecordsDelete();
			String actualDeletedCounts = page_SavedRecords.getCountAfterDeletedFolder(multipleFolderName);
			String expectedDeletedCounts = multipleFolderName + " - 2";
			if (expectedDeletedCounts.trim().equals(actualDeletedCounts.trim())) {
				Application.Logger.addsubStep(LogStatus.PASS, "RECORDS ARE DELETED SUCCESSFULLY ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "RECORDS ARE NOT DELETED  ", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("9.NAVIGATE TO SAVED RECORDS PAGE AND CLICK ON GLOBAL CHECKBOX ",
					"ALL THE RECORDS SHOULD BE SELECTED");
			page_SavedRecords.clickOnSavedRecordsFooterGlobalCheckBox();
			for (int j = 1; j < 2; j++) {
				checkboxStatus = page_SavedRecords.isSelectedCheckBox(j);
				if (checkboxStatus.contains("checked")) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"CHECK BOX " + j + "\t IS SELECTED IN THE SAVED RECORDS FOOTER PAGE", false);

				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"CHECK BOX " + j + "\t IS NOT SELECTED IN SAVED RECORDS FOOTER PAGE", true);
				}
			}
			Application.Logger.endStep();

			Application.Logger.addStep("10.CLICK ON DELETE OPTION ",
					"ALL THE RECORDS SHOULD BE DELETED AND COUNT SHOULD BECOME ZERO");
			page_SavedRecords.clickOnGlobalSavedRecordsDelete();
			String actualDeletedCount = page_SavedRecords.getCountAfterDeletedFolder(multipleFolderName);
			String expectedDeletedCount = multipleFolderName + " - 0";
			if (expectedDeletedCount.trim().equals(actualDeletedCount.trim())) {
				Application.Logger.addsubStep(LogStatus.PASS, "RECORDS ARE DELETED SUCCESSFULLY ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "RECORDS ARE NOT DELETED  ", true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1087

	@Test
	public boolean validateCheckboxSelection(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String expGlobalCheckboxHalfFilledState, expGlobalCheckboxFullFilledState, expNextPageRecordSelection;
		page_SavedRecords = new Page_SavedRecords(Application);
		boolean flag = true;
		try {
			Application.Logger.addStep("1.SELECT ANY RECORD FROM RS AND VERIFY THE GLOBAL CHECKBOX STATE",
					"GLOBAL CHECKBOX SHOULD PARTIALLY FILLED");
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			page_ChemicalSearchResults.tabPatentSearch().clickOnSingleRecordCheckBox();
			expGlobalCheckboxHalfFilledState = page_ChemicalSearchResults.tabPatentSearch().checkGlobalCheckboxState();
			if (expGlobalCheckboxHalfFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is half filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not half filled", false);
			}

			Application.Logger.endStep();

			Application.Logger.addStep("2.NAVIGATE TO NEXT PAGE AND VERIFY THE RECORD SELECTION ON FOOTER CHECKBOX",
					"RECORD SELECTION SHOULD REMAIN SAME");
			Application.waitTime(2);
			String actualRecordSelection = page_ChemicalSearchResults.tabPatentSearch().getTextRecordSelection();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnNextPage();
			Application.waitTime(2);
			expNextPageRecordSelection = page_ChemicalSearchResults.tabPatentSearch().getTextRecordSelection();
			Application.waitTime(2);
			if (actualRecordSelection.contentEquals(expNextPageRecordSelection)) {
				Application.Logger.addsubStep(LogStatus.PASS, "Record selection should be same  ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Record selection is not matching ", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.VERIFY THE GLOBAL CHECKBOX STATE AFTER NAVIGATING TO NEXT PAGE",
					"SHOULD BE IN HOLLOW STATE");
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.tabPatentSearch().checkGlobalCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("false")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is in hollow state ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not in hollow state", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.SELECT ANY RECORD ON NEXT PAGE AND VERIFY THE RECORD SELECTION ON FOOTER",
					"RECORD SELECTION SHOULD NOT BE SAME");
			String actualRecordSelectionInNextPage = page_ChemicalSearchResults.tabPatentSearch()
					.getTextRecordSelection();
			page_ChemicalSearchResults.tabPatentSearch().clickOnSingleRecordCheckBox();
			Application.waitTime(2);
			expNextPageRecordSelection = page_ChemicalSearchResults.tabPatentSearch().getTextRecordSelection();
			if (!(actualRecordSelectionInNextPage).contentEquals(expNextPageRecordSelection)) {
				Application.Logger.addsubStep(LogStatus.PASS, "Record selection should not be same  ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Record selection Should be same ", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.SET THE PAGINATION TO DIFFERENT LEVELS AND VERIFY THE GLOBAL CHECKBOX STATE",
					"GLOBAL CHECKBOX SHOULD HALF FILLED");
			page_ChemicalSearchResults.clickOnPreviousPage();
			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("50");
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.tabPatentSearch().checkGlobalCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is partialy filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not partialy filled", false);
			}
			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("100");
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.tabPatentSearch().checkGlobalCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is partialy filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not partialy filled", false);
			}

			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("10");
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.tabPatentSearch().checkGlobalCheckboxState();
			Application.waitTime(2);
			if (expGlobalCheckboxFullFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is partialy filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not partialy filled", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep(
					"6.SELECT ALL RECORDS FROM RS GLOBAL CHECKBOX AND VERIFY THE GLOBAL CHECKBOX STATE",
					"GLOBAL CHECKBOX SHOULD FULL FILLED");
			page_ChemicalSearchResults.tabPatentSearch().clickOnHalfFilledGlobalCheckBox();
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.tabPatentSearch().checkGlobalCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("true")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is full filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not full filled", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("7.NAVIGATE TO NEXT PAGE AND SELECT ALL RECORDS FROM RS GLOBAL CHECKBOX ",
					"GLOBAL CHECKBOX SHOULD FULL FILLED");
			page_ChemicalSearchResults.clickOnNextPage();
			page_ChemicalSearchResults.tabPatentSearch().clickOnSingleRecordCheckBox();
			page_ChemicalSearchResults.tabPatentSearch().clickOnHalfFilledGlobalCheckBox();
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.tabPatentSearch().checkGlobalCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("true")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is full filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not full filled", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("8.SET THE PAGINATION TO 100 AND VERIFY THE STATE OF GLOBAL CHECKBOX",
					"GLOBAL CHECKBOX IS PARTIALLY FILLED");
			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("100");
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.tabPatentSearch().checkGlobalCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is partialy filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not partialy filled", false);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;

		}
		return flag;
	}

	// CHEMEXP-1067
	@Test
	public boolean validatePatentViewAsResultsSet(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		String searchText = input.get("searchtext");
		String viewAsResultSetTitle, publicationNumber;
		int afterViewAsResultFilter, beforeCitingPatentfilter, afterCitingPatentfilter, afterCitedPatentfilter;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"2.VERIFY THE PATENT RS IS REFRESHED AFTER THE CLICK ON VIEW AS RESULT SET BUTTON FROM CITING PATENT SECTION",
					"RS PAGE SHOULD BE REFRESHED AFTER THE CLICK ON VIEW AS RESULT SET BUTTON");
			page_ChemicalSearchResults.clickOnTabPatent();
			beforeCitingPatentfilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
			Application.waitTime(1);
			publicationNumber = page_ChemicalSearchResults.tabPatentSearch().getTextPatentRecordViewPublicationNumber();
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkCitingPatent();
			page_ChemicalSearchResults.tabPatentSearch().clickOnCitingPatentViewASResultSet();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			afterCitingPatentfilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeCitingPatentfilter >= afterCitingPatentfilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AFTER THE CLICK ON VIEW AS RESULT SET BUTTON FROM CITING PATENT SECTION",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER THE CLICK ON VIEW AS RESULT SET BUTTON FROM CITING PATENT SECTION : \t"
								+ beforeCitingPatentfilter + "\t\t AFTER VIEW AS RESULT SET FILTER :\t"
								+ afterCitingPatentfilter,
						true);
			}
			viewAsResultSetTitle = page_ChemicalSearchResults.tabPatentSearch().getTextKeyWordResultSet();
			if (viewAsResultSetTitle.contains("Results set: Patents citing")
					&& (viewAsResultSetTitle.contains(publicationNumber))) {
				Application.Logger.addsubStep(LogStatus.PASS, "USER IS IN VIEW AS RESULTS SET : PATENTS CITING MODE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"USER IS NOT IN VIEW AS RESULTS SET : PATENTS CITING MODE", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"3.VERIFY THE PATENT RS IS REFRESHED AFTER THE CLICK ON VIEW AS RESULT SET BUTTON FROM CITED PATENT SECTION",
					"RS PAGE SHOULD BE REFRESHED AFTER THE CLICK ON VIEW AS RESULT SET BUTTON");
			page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
			Application.waitTime(1);
			publicationNumber = page_ChemicalSearchResults.tabPatentSearch().getTextPatentRecordViewPublicationNumber();
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkCitedPatent();
			Application.waitTime(2);
			page_ChemicalSearchResults.tabPatentSearch().clickOnCitedPatentViewASResultSet();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			afterCitedPatentfilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeCitingPatentfilter >= afterCitedPatentfilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AFTER THE CLICK ON VIEW AS RESULT SET BUTTON FROM CITED PATENT SECTION",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER THE CLICK ON VIEW AS RESULT SET BUTTON FROM CITED PATENT SECTION : \t"
								+ beforeCitingPatentfilter + "\t\t AFTER VIEW AS RESULT SET FILTER :\t"
								+ afterCitedPatentfilter,
						true);
			}
			viewAsResultSetTitle = page_ChemicalSearchResults.tabPatentSearch().getTextKeyWordResultSet();
			if (viewAsResultSetTitle.contains("Results set: Patents cited by")
					&& (viewAsResultSetTitle.contains(publicationNumber))) {
				Application.Logger.addsubStep(LogStatus.PASS, "USER IS IN VIEW AS RESULTS SET : PATENTS CITED MODE",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "USER IS NOT IN VIEW AS RESULTS SET : PATENTS CITED MODE",
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("4.VERIFY THE KEYWORD SECTION IS DISABLED IN VIEW AS RESULTS SET MODE",
					"KEYWORD SHOULD BE DISABLED MODE IN VIEW AS RESULTS SET MODE");
			if (page_ChemicalSearchResults.tabPatentSearch().isDisabledKeyWordSection()) {
				Application.Logger.addsubStep(LogStatus.PASS, "KEY WORD SECTION IS IN DISABLE MODE", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "KEY WORD SECTION IS NOT IN DISABLE MODE", false);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.VERIFY IF THE VIEW AS RESULTS SET OPTION IS CLOSED ON CLICK OF CLOSE ICON",
					"USER SHOULD BE SUCCESSFULLY MOVED OUT OF VIEW AS RESULTS SET MODE");
			page_ChemicalSearchResults.tabPatentSearch().clickOnCloseIconMoreLike();
			afterViewAsResultFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeCitingPatentfilter == afterViewAsResultFilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AND USER IS OUT OF VIEW AS RESULTS SET MODE", false);
			} else {
				Application.Logger
						.addsubStep(LogStatus.FAIL,
								"RESULT SETS ARE NOT REFRESHED AFTER CLOSING THE VIEW AS RESULTS SET : \t"
										+ beforeCitingPatentfilter + "\t\t AFTER FILTER :\t" + afterViewAsResultFilter,
								true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1068

	@Test
	public boolean validateCreateFolder(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		page_SavedRecords = new Page_SavedRecords(Application);
		String searchText = input.get("searchtext");
		String singleFolderName = "singleRecordFolder";
		String multipleFolderName = "multipleRecordFolder";
		String bigSingleFoldername = "A multilayer container the container comprising:an outer layer defining an exterior surface, an inner layer defining an interior surface and an interm";
		String bigRsFoldername = "A multilayer container the container comprising:an outer layer defining an exterior surface, an inner layer defining an interior surface and an intern";
		String expErrorMessage = "This name is already in use. Please enter a different name";
		String actErrorMessage, expFolderName;
		int folderLength, recordCount;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.NAVIGATE TO SAVED FOLDERS PAGE AND DELETE ALL EXISTING FOLDERS",
					"FOLDERS SHOULD BE DELETED SUCCESSFULLY");
			page_SavedRecords.clickOnLinkSavedRecords();
			page_SavedRecords.deleteExistingFolders();
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			Application.Logger.endStep();
			Application.Logger.addStep("2.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
					"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep("3.VERIFY IF THE NEW FOLDER IS CREATED SUCCESSFULLY IN RESULTS SET PAGE",
					"FOLDER SHOULD BE CREATED SUCCESSFULLY");
			page_ChemicalSearchResults.clickOnSaveIcon();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnLinkCreateNewFolder();
			Application.waitTime(2);
			page_ChemicalSearchResults.setTextFolderName(singleFolderName);
			page_ChemicalSearchResults.clickOnLinkCreate();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnFolderNameCheckBox(singleFolderName);
			Application.waitTime(2);
			expFolderName = page_ChemicalSearchResults.getTextFolderName(singleFolderName);
			if (expFolderName.equals(singleFolderName)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"NEW FOLDER IS CREATED SUCCESSFULLY WITH THE NAME :" + singleFolderName, false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"NEW FOLDER IS NOT CREATED SUCCESSFULLY WITH THE NAME :" + singleFolderName, true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"4.VERIFY IF EEROR MESSAGE IS DISPLAYED ON CREATING THE FOLDER NAME WITH THE EXISTING NAME",
					"ERROR MESSAGE SHOULD BE DISPLAYED AND FOLDER SHOULD NOT BE CREATED");
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnLinkCreateNewFolder();
			page_ChemicalSearchResults.setTextFolderName(singleFolderName);
			actErrorMessage = page_ChemicalSearchResults.getTextFolderErrorMEssage();
			if (actErrorMessage.equals(expErrorMessage)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"ERROR MESSAGE IS DISPLAYED ON ENTERING THE EXISTING FOLDER NAME AT THE TIME OF CREATION",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"ERROR MESSAGE IS NOT DISPLAYED ON ENTERING THE EXISTING FOLDER NAME AT THE TIME OF CREATION : EXPECTED ERROR MESAGE : \t"
								+ expErrorMessage + "\t\t ACTUAL ERROR MESSAGE :\t" + actErrorMessage,
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("5.VERIFY IF THE FOLDER NAME ACCEPTS UPTO 150 CHARACTERS",
					"FOLDER NAME SHOULD ACCEPT UPTO 150 CHARACTERS");
			Application.waitTime(2);
			page_ChemicalSearchResults.setTextFolderName(bigSingleFoldername);
			page_ChemicalSearchResults.clickOnLinkCreate();
			Application.waitTime(2);
			folderLength = page_ChemicalSearchResults.getFolderNameSize(bigSingleFoldername);
			if (folderLength == 150) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"NEW FOLDER IS SUCCESSFULLY CREATED WITH UPTO 150 CHARACTERS", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"NEW FOLDER IS NOT CREATED SUCCESSFULLY WITH UPTO 150 CHARACTERS", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"6.VERIFY IF THE NEW FOLDER IS CREATED SUCCESSFULLY FROM FOOTER RESULTS SET PAGE",
					"FOLDER SHOULD BE CREATED SUCCESSFULLY");
			page_ChemicalSearchResults.clickOnRSFooterGlobalCheckBox();
			page_ChemicalSearchResults.clickOnRsFooterSaveIcon();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnRsFooterCreateNewFolder();
			Application.waitTime(2);
			page_ChemicalSearchResults.setTextFolderName(multipleFolderName);
			page_ChemicalSearchResults.clickOnLinkCreate();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnFolderNameCheckBox(multipleFolderName);
			expFolderName = page_ChemicalSearchResults.getTextFolderName(multipleFolderName);
			if (expFolderName.equals(multipleFolderName)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"NEW FOLDER IS CREATED SUCCESSFULLY IN THE RS FOOTER WITH THE NAME :" + multipleFolderName,
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"NEW FOLDER IS NOT CREATED SUCCESSFULLY IN THE RS FOOTER WITH THE NAME :" + multipleFolderName,
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"7.VERIFY IF EEROR MESSAGE IS DISPLAYED ON CREATING THE FOLDER NAME WITH THE EXISTING NAME IN RS FOOTER",
					"ERROR MESSAGE SHOULD BE DISPLAYED AND FOLDER SHOULD NOT BE CREATED");
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnRsFooterCreateNewFolder();
			page_ChemicalSearchResults.setTextFolderName(multipleFolderName);
			actErrorMessage = page_ChemicalSearchResults.getTextFolderErrorMEssage();
			if (actErrorMessage.equals(expErrorMessage)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"ERROR MESSAGE IS DISPLAYED ON ENTERING THE EXISTING FOLDER NAME AT THE TIME OF CREATION IN RS FOOTER",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"ERROR MESSAGE IS NOT DISPLAYED ON ENTERING THE EXISTING FOLDER NAME AT THE TIME OF CREATION IN RS FOOTER: EXPECTED ERROR MESAGE : \t"
								+ expErrorMessage + "\t\t ACTUAL ERROR MESSAGE :\t" + actErrorMessage,
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("8.VERIFY IF THE FOLDER NAME ACCEPTS UPTO 150 CHARACTERS FROM RS FOOTER",
					"FOLDER NAME SHOULD ACCEPT UPTO 150 CHARACTERS FROM RS FOOTER");
			Application.waitTime(2);
			page_ChemicalSearchResults.setTextFolderName(bigRsFoldername);
			page_ChemicalSearchResults.clickOnLinkCreate();
			Application.waitTime(2);
			folderLength = page_ChemicalSearchResults.getFolderNameSize(bigRsFoldername);
			if (folderLength == 150) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"NEW FOLDER IS SUCCESSFULLY CREATED WITH UPTO 150 CHARACTERS FROM RS FOOTER", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"NEW FOLDER IS NOT SUCCESSFULLY CREATED WITH UPTO 150 CHARACTERS FROM RS FOOTER", true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep("9.VERIFY THE NEW CREATED FOLDERS ARE DISPLAYED IN SAVED RECORDS PAGE",
					"NEWLY CREATED FOLDERS WITH RECORDS SHOULD BE DISPLAYED IN SAVED RECORDS PAGE");
			Application.waitTime(2);
			page_SavedRecords.clickOnLinkSavedRecords();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			page_SavedRecords.clickOnFolderName(3);
			Application.waitTime(2);
			Application.waitUntilFectchRecordProgressBarToDisappears();
			recordCount = page_SavedRecords.getFolderRecordCount();
			Application.waitTime(2);
			if (recordCount == 20) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"" + recordCount + " RECORDS ARE SAVED IN THE FOLDER : " + multipleFolderName, false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"" + recordCount + " RECORDS ARE NOT SAVED IN THE FOLDER : " + multipleFolderName, true);
			}
			page_SavedRecords.clickOnFolderName(5);
			Application.waitUntilFectchRecordProgressBarToDisappears();
			recordCount = page_SavedRecords.getFolderRecordCount();
			if (recordCount == 1) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"" + recordCount + " RECORD IS SAVED IN THE FOLDER : " + singleFolderName, false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"" + recordCount + " RECORD IS NOT SAVED IN THE FOLDER : " + singleFolderName, true);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1069
	@Test
	public boolean validateGlobalCheckBoxAndPagination(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		page_SavedRecords = new Page_SavedRecords(Application);
		String searchText = input.get("searchtext");
		String multipleFolderName = "multipleRecordFolderPagination";
		int folderLength, recordCount, folderSize;
		String expFolderName, checkboxStatus;
		boolean blnCheck;
		boolean flag = true;
		try {
			Application.Logger.addStep(
					"1.NAVIGATE TO SAVED RECORDS PAGE AND DELETE ALL EXISTING FOLDERS AND PERFORM A SEARCH WITH A PHARSE IN THE LANDING PAGE AND VERIFY THE RS PAGE",
					"FOLDERS SHOULD BE DELETED SUCCESSFULLY AND RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
			page_SavedRecords.clickOnLinkSavedRecords();
			Application.waitTime(2);
			page_SavedRecords.deleteExistingFolders();
			page_ChemicalSearchResults.clickOnChemExpHomePage();
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"2.VERIFY IF THE NEW FOLDER IS CREATED SUCCESSFULLY FROM FOOTER RESULTS SET PAGE",
					"FOLDER SHOULD BE CREATED SUCCESSFULLY");
			page_ChemicalSearchResults.tabPatentSearch().selectItemsPerPageFromDropDown("100");
			Application.waitUntilFectchRecordProgressBarToDisappears();
			page_ChemicalSearchResults.clickOnRSFooterGlobalCheckBox();
			page_ChemicalSearchResults.clickOnRsFooterSaveIcon();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnRsFooterCreateNewFolder();
			page_ChemicalSearchResults.setTextFolderName(multipleFolderName);
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnLinkCreate();
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnFolderNameCheckBox(multipleFolderName);
			Application.waitTime(2);
			expFolderName = page_ChemicalSearchResults.getTextFolderName(multipleFolderName);
			if (expFolderName.equals(multipleFolderName)) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"NEW FOLDER IS CREATED SUCCESSFULLY IN THE RS FOOTER WITH THE NAME :" + multipleFolderName,
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"NEW FOLDER IS NOT CREATED SUCCESSFULLY IN THE RS FOOTER WITH THE NAME :" + multipleFolderName,
						true);
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"3.VERIFY IF THE ALL THE CHECK BOXES ARE SELECTED AFTER SELECTING THE GLOBAL CHECK BOX IN RS FOOTER PAGE",
					"ALL THE CHECK BOXES SHOULD BE SELECTED");
			for (int i = 1; i < 100; i++) {
				blnCheck = page_ChemicalSearchResults.isSelectedRSCheckBox(i);
				if (blnCheck) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"CHECK BOX " + i + "\t IS SELECTED IN THE RS FOOTER PAGE", false);

				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"CHECK BOX " + i + "\t IS NOT SELECTED IN THE RS FOOTER PAGE", true);
				}
			}
			Application.Logger.endStep();
			Application.Logger.addStep(
					"4.NAVIGATE TO SAVED RECORDS PAGE AND VERIFY THE GLOBAL CHECK BOX AND PAGINATION FUNCTIONALITIES",
					"GLOBAL CHECK BOX AND PAGINATION FUNCTIONALITIES SHOULD WORK AS EXPECTED");
			page_SavedRecords.clickOnLinkSavedRecords();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			page_SavedRecords.clickOnFooterGlobalCheckBox();
			for (int j = 1; j < 50; j++) {
				checkboxStatus = page_SavedRecords.isSelectedCheckBox(j);
				if (checkboxStatus.contains("checked")) {
					Application.Logger.addsubStep(LogStatus.PASS,
							"CHECK BOX " + j + "\t IS SELECTED IN THE SAVED RECORDS FOOTER PAGE", false);

				} else {
					Application.Logger.addsubStep(LogStatus.FAIL,
							"CHECK BOX " + j + "\t IS NOT SELECTED IN SAVED RECORDS FOOTER PAGE", true);
				}
			}
			String dropdownDefaultValue = page_SavedRecords.getValueFromItemsPerPageDropdown();
			if (dropdownDefaultValue.equals("50"))
				Application.Logger.addsubStep(LogStatus.PASS, "default value for items per page dropdown is 50", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL,
						"default value for items per page dropdown is other than 50", true);
			page_SavedRecords.selectItemsPerPageFromDropDown("10");
			Application.waitUntilFectchRecordProgressBarToDisappears();
			String tenItemsPerPage = page_SavedRecords.getValueFromItemsPerPageDropdown();
			if (tenItemsPerPage.equals("10"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 10 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 10 records per page", true);

			page_SavedRecords.selectItemsPerPageFromDropDown("20");
			Application.waitUntilFectchRecordProgressBarToDisappears();
			String twentyItemsPerPage = page_SavedRecords.getValueFromItemsPerPageDropdown();
			if (twentyItemsPerPage.equals("20"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 20 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 20 records per page", true);

			page_SavedRecords.selectItemsPerPageFromDropDown("100");
			Application.waitUntilFectchRecordProgressBarToDisappears();
			String hundredItemsPerPage = page_SavedRecords.getValueFromItemsPerPageDropdown();
			if (hundredItemsPerPage.equals("100"))
				Application.Logger.addsubStep(LogStatus.PASS, "User can see 100 records per page", false);
			else
				Application.Logger.addsubStep(LogStatus.FAIL, "User can not see 100 records per page", true);

			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1346
	@Test
	public boolean validateChemicalStructureFilterField(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		page_SavedRecords = new Page_SavedRecords(Application);
		String searchText = input.get("searchtext");
		String smiley = input.get("smiles");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SUB STRUCTURE SEARCH AND FREE TEXT SEARCH FROM THE LANDING PAGE",
					"RESULTS SET PAGE SHOULD BE DISPLAYED FOR BOTH EXACT STRUCURE SEARCH AND FREE TEXT SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
			page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.selectStructureRadioButton("Substructure");
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.CLICK ON ANY STRUCTURE ON THE CHEMICAL STRUCTURE FILTER PANEL ",
					"RESULTS COUNT SHOULD BE REFRESHED");
			int resultsCounts = page_ChemicalSearchResults.getResultsCount();
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnChemicalStructureFilterCheckbox("CHEMICAL STRUCTURE", 1);
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter > afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"RESULT SETS ARE REFRESHED AFTER THE PUBLICATION YEAR TEXT FILTER", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED AFTER THE PUBLICATION YEAR TEXT FILTER, BEFORE FILTER : \t"
								+ beforeFilter + "\t\t AFTER PY FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.CLICK ON SEE ALL STRUCTURES LINK",
					"FILTERCHEMICALSTRUCTUREMODAL IS DISPLAYED");
			int resultsCount = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkSeeAllStructures();
			Application.waitTime(2);
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedfilterChemicalStructureModal()) {
				Application.Logger.addsubStep(LogStatus.PASS, "filterChemicalStructureModal is DISPLAYED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "filterChemicalStructureModal is not DISPLAYED ", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.SELECT ANY STRUCTURE AND CLICK ON APPLY FILTERS",
					"RESULTS COUNT SHOULD BE REFRESHED");
			page_ChemicalSearchResults.filterChemicalStructure().clickOnSingleStructure();
			page_ChemicalSearchResults.filterChemicalStructure().clickOnButtonApplyFilters();
			Application.waitTime(2);
			int resultsCountAfterFilterChemicalStructure = page_ChemicalSearchResults.getResultsCount();
			if (resultsCountAfterFilterChemicalStructure <= resultsCount) {
				Application.Logger.addsubStep(LogStatus.PASS, "Results count is refreshed", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Results count is not refreshed.", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep(
					"5.CLICK ON SEE ALL STRUCTURES LINK ,SELECT ALL THE STRUCTURE BY CLICKING GLOBAL CHECKBOX AND CLICK ON APPLY FILTERS",
					"RESULTS COUNT SHOULD BE REFRESHED");
			int resultCount = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkSeeAllStructures();
			page_ChemicalSearchResults.filterChemicalStructure().clickOnGlobalStructureCheckbox();
			page_ChemicalSearchResults.filterChemicalStructure().clickOnButtonApplyFilters();
			Application.waitTime(2);
			int resultsCountAfterFilterChemicalStructures = page_ChemicalSearchResults.getResultsCount();
			if (resultsCountAfterFilterChemicalStructures >= resultCount) {
				Application.Logger.addsubStep(LogStatus.PASS, "Results count is refreshed", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Results count is not refreshed.", true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	// CHEMEXP-1349
	@Test
	public boolean validateChemicalStructureFilterPaginationAndCheckboxSelection(Controller Application,
			HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		page_SavedRecords = new Page_SavedRecords(Application);
		String searchText = input.get("searchtext");
		String smiley = input.get("smiles");
		String expGlobalCheckboxHalfFilledState, expGlobalCheckboxFullFilledState, expNextPageRecordSelection;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SUB STRUCTURE SEARCH AND FREE TEXT SEARCH FROM THE LANDING PAGE",
					"RESULTS SET PAGE SHOULD BE DISPLAYED FOR BOTH EXACT STRUCURE SEARCH AND FREE TEXT SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
			page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.selectStructureRadioButton("Substructure");
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("RESULTS SET IS NOT LOADED FOR THE CHEMICAL SEARCH");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.CLICK ON SEE ALL STRUCTURES LINK",
					"FILTERCHEMICALSTRUCTUREMODAL IS DISPLAYED");
			page_ChemicalSearchResults.tabPatentSearch().clickOnLinkSeeAllStructures();
			Application.waitTime(2);
			if (page_ChemicalSearchResults.tabPatentSearch().isDisplayedfilterChemicalStructureModal()) {
				Application.Logger.addsubStep(LogStatus.PASS, "filterChemicalStructureModal is DISPLAYED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "filterChemicalStructureModal is not DISPLAYED ", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.SELECT ANY STRUCTURE AND VERIFY GLOBAL CHECKBOX",
					"GLOBAL CHECKBOX SHOULD BE HALF FILLED");
			page_ChemicalSearchResults.filterChemicalStructure().clickOnSingleStructure();
			expGlobalCheckboxHalfFilledState = page_ChemicalSearchResults.filterChemicalStructure()
					.checkGlobalStructureCheckboxState();
			if (expGlobalCheckboxHalfFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is half filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not half filled", false);
			}

			Application.Logger.endStep();

			Application.Logger.addStep("4.NAVIGATE TO NEXT PAGE AND VERIFY THE RECORD SELECTION ON FOOTER CHECKBOX",
					"RECORD SELECTION SHOULD REMAIN SAME");
			Application.waitTime(2);
			String actualRecordSelection = page_ChemicalSearchResults.filterChemicalStructure()
					.getStructureTextRecordSelection();
			Application.waitTime(2);
			page_ChemicalSearchResults.filterChemicalStructure().clickOnNextPage();
			Application.waitTime(2);
			expNextPageRecordSelection = page_ChemicalSearchResults.filterChemicalStructure()
					.getStructureTextRecordSelection();
			Application.waitTime(2);
			if (actualRecordSelection.contentEquals(expNextPageRecordSelection)) {
				Application.Logger.addsubStep(LogStatus.PASS, "Record selection should be same  ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Record selection is not matching ", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.VERIFY THE GLOBAL CHECKBOX STATE AFTER NAVIGATING TO NEXT PAGE",
					"SHOULD BE IN HOLLOW STATE");
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.filterChemicalStructure()
					.checkGlobalStructureCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("false")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is in hollow state ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not in hollow state", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("6.SELECT ANY RECORD ON NEXT PAGE AND VERIFY THE RECORD SELECTION ON FOOTER",
					"RECORD SELECTION SHOULD NOT BE SAME");
			String actualRecordSelectionInNextPage = page_ChemicalSearchResults.filterChemicalStructure()
					.getStructureTextRecordSelection();
			page_ChemicalSearchResults.filterChemicalStructure().clickOnSingleStructure();
			Application.waitTime(2);
			expNextPageRecordSelection = page_ChemicalSearchResults.filterChemicalStructure()
					.getStructureTextRecordSelection();
			if (!(actualRecordSelectionInNextPage).contentEquals(expNextPageRecordSelection)) {
				Application.Logger.addsubStep(LogStatus.PASS, "Record selection should not be same  ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Record selection Should be same ", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("7.SET THE PAGINATION TO DIFFERENT LEVELS AND VERIFY THE GLOBAL CHECKBOX STATE",
					"GLOBAL CHECKBOX SHOULD HALF FILLED");
			page_ChemicalSearchResults.filterChemicalStructure().clickOnPreviousPage();
			Application.waitTime(2);
			page_ChemicalSearchResults.filterChemicalStructure().selectItemsPerPageFromDropDown("24");
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.filterChemicalStructure()
					.checkGlobalStructureCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is partialy filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not partialy filled", false);
			}
			page_ChemicalSearchResults.filterChemicalStructure().selectItemsPerPageFromDropDown("36");
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.filterChemicalStructure()
					.checkGlobalStructureCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is partialy filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not partialy filled", false);
			}

			page_ChemicalSearchResults.filterChemicalStructure().selectItemsPerPageFromDropDown("12");
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.filterChemicalStructure()
					.checkGlobalStructureCheckboxState();
			Application.waitTime(2);
			if (expGlobalCheckboxFullFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is partialy filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not partialy filled", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep(
					"8.SELECT ALL RECORDS FROM RS GLOBAL CHECKBOX AND VERIFY THE GLOBAL CHECKBOX STATE",
					"GLOBAL CHECKBOX SHOULD FULL FILLED");
			page_ChemicalSearchResults.filterChemicalStructure().clickOnHalfFilledGlobalCheckBox();
			Application.waitTime(2);
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.filterChemicalStructure()
					.checkGlobalStructureCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("true")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is full filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not full filled", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("9.NAVIGATE TO NEXT PAGE AND SELECT ALL RECORDS FROM RS GLOBAL CHECKBOX ",
					"GLOBAL CHECKBOX SHOULD FULL FILLED");
			page_ChemicalSearchResults.filterChemicalStructure().clickOnNextPage();
			page_ChemicalSearchResults.filterChemicalStructure().clickOnSingleStructure();
			page_ChemicalSearchResults.filterChemicalStructure().clickOnHalfFilledGlobalCheckBox();
			expGlobalCheckboxFullFilledState = page_ChemicalSearchResults.filterChemicalStructure()
					.checkGlobalStructureCheckboxState();
			if (expGlobalCheckboxFullFilledState.contains("true")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is full filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not full filled", false);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("10.SET THE PAGINATION TO 36 AND VERIFY THE STATE OF GLOBAL CHECKBOX",
					"GLOBAL CHECKBOX IS PARTIALLY FILLED");
			page_ChemicalSearchResults.filterChemicalStructure().selectItemsPerPageFromDropDown("36");
			Application.waitTime(2);
			expGlobalCheckboxHalfFilledState = page_ChemicalSearchResults.filterChemicalStructure()
					.checkGlobalStructureCheckboxState();
			if (expGlobalCheckboxHalfFilledState.contains("mixed")) {
				Application.Logger.addsubStep(LogStatus.PASS, "global checkbox is partialy filled ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "global checkbox is not partialy filled", false);
			}
			Application.Logger.endStep();
		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	//CHEMEXP-1347
	@Test
	public boolean validateTextInputBoxForStructure(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		page_SavedRecords = new Page_SavedRecords(Application);
		String smiley = input.get("smiles");
		String enterSmiley = input.get("smiley");
		boolean flag = true;
		try {
			Application.Logger.addStep("1.VALIDATE WATERMARK ON THE SEARCH BOX", "SHOULD SHOW BELOW WATERMARK .\r\n"
					+ "WATERMARK: ‘ENTER A NAME OR SMILES TO GENERATE A STRUCTURE’");
			page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
			if (page_ChemicalSearchLandingPage.isDisplayedStructureSearchBoxWithWaterMark()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Water Mark with text as '‘Enter a name or SMILES to generate a structure' has been displayed.",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Water Mark with text as '‘Enter a name or SMILES to generate a structure' has not been displayed..",
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.ENTER SMILEY ON TEXTBOX AND VALIDATE X ON THE SEARCH BOX",
					"X SHOULD PRESENT ON THE ENTERED SMILEY IN TEXTBOX ");
			page_ChemicalSearchLandingPage.setSmileyTextBox(enterSmiley);
			Application.waitTime(2);
			if (page_ChemicalSearchLandingPage.isDisplayedButtonClearX()) {
				Application.Logger.addsubStep(LogStatus.PASS, "X is displayed after entering Smiles", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "X is not displayed after entering Smiles", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.CLICK ON X ", "ENTERED SMILEY SHOULD BE CLEARED ");
			page_ChemicalSearchLandingPage.clickOnButtonX();
			if (page_ChemicalSearchLandingPage.isDisplayedStructureSearchBoxWithWaterMark()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Water Mark with text as '‘Enter a name or SMILES to generate a structure' has been displayed.",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Water Mark with text as '‘Enter a name or SMILES to generate a structure' has not been displayed..",
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.ENTER TEXT ON TEXTBOX AND VALIDATE X ON THE SEARCH BOX",
					"X SHOULD PRESENT ON THE ENTERED TEXT IN TEXTBOX ");
			page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
			Application.waitTime(2);
			if (page_ChemicalSearchLandingPage.isDisplayedButtonClearX()) {
				Application.Logger.addsubStep(LogStatus.PASS, "X is displayed after entering Smiles", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "X is not displayed after entering Smiles", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.CLICK ON X ", "ENTERED TEXT SHOULD BE CLEARED ");
			page_ChemicalSearchLandingPage.clickOnButtonX();
			if (page_ChemicalSearchLandingPage.isDisplayedStructureSearchBoxWithWaterMark()) {
				Application.Logger.addsubStep(LogStatus.PASS,
						"Water Mark with text as '‘Enter a name or SMILES to generate a structure' has been displayed.",
						false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"Water Mark with text as '‘Enter a name or SMILES to generate a structure' has not been displayed..",
						true);
			}
			Application.Logger.endStep();
		}

		catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}

	//CHEMEXP-1348
	@Test
	public boolean validateModifyChemicalStructureModal(Controller Application, HashMap<String, String> input) {
		page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
		page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
		page_SavedRecords = new Page_SavedRecords(Application);
		String searchText = input.get("searchtext");
		String smiley = input.get("smiles");
		int beforeFilter, afterFilter;
		boolean flag = true;
		try {
			Application.Logger.addStep("1.PERFORM A SUB STRUCTURE SEARCH AND FREE TEXT SEARCH FROM THE LANDING PAGE",
					"RESULTS SET PAGE SHOULD BE DISPLAYED FOR BOTH EXACT STRUCURE SEARCH AND FREE TEXT SEARCH");
			page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
			page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.selectStructureRadioButton("Substructure");
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.clickOnSearchIcon();
			Application.waitUntilFectchRecordProgressBarToDisappears();
			if (page_ChemicalSearchResults.checkIfResultsFound()) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
			} else {
				throw new Exception("Results set is not loaded for the chemical search");
			}
			Application.Logger.endStep();

			Application.Logger.addStep("2.validate the color of structure pill box",
					"pill box should contain purple color");
			if (page_ChemicalSearchResults.getColorOfStructureSerachPillBox().equals("rgba(101, 19, 231, 1)")) {
				Application.Logger.addsubStep(LogStatus.PASS, "Pill box contains 'purple color' as Expected.", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "Pill box not contain 'purple color' as Expected.", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("3.CLICK ON THE EDIT ICON ON THE STRUCTURE PILL BOX",
					"MODIFY CHEMICAL STRUCTURE MODAL IS DISPLAYED");
			beforeFilter = page_ChemicalSearchResults.getResultsCount();
			page_ChemicalSearchResults.clickOnButtonEdit();
			if (page_ChemicalSearchResults.isDisplayedModifyChemicalStructureModal()) {
				Application.Logger.addsubStep(LogStatus.PASS, "ModifyChemicalStructureModal is displayed", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL, "ModifyChemicalStructureModal is not displayed", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("4.ENTER TEXT ON TEXTBOX AND CLICK ON APPLY BUTTON", "RESULT SET IS REFRESHED");
			page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
			Application.waitTime(2);
			page_ChemicalSearchLandingPage.selectStructureRadioButton("Substructure");
			Application.waitTime(2);
			page_ChemicalSearchResults.clickOnStructureButtonApply();
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter <= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("5.CLICK ON CANCEL BUTTON ", "MODIFY CHEMICAL STRUCTURE MODAL SHOULD CLOSE");
			page_ChemicalSearchResults.clickOnButtonEdit();
			page_ChemicalSearchResults.clickOnStructureModalButtonCancel();
			Application.waitTime(2);
			if (page_ChemicalSearchResults.isDisplayedModifyChemicalStructureModal()) {
				Application.Logger.addsubStep(LogStatus.FAIL, "ModifyChemicalStructureModal is displayed", false);
			} else {
				Application.Logger.addsubStep(LogStatus.PASS, "ModifyChemicalStructureModal is not displayed", true);
			}
			Application.Logger.endStep();

			Application.Logger.addStep("6.CLICK ON X ON THE STRUCTURE PILL BOX ",
					"STRUCTURE PILL BOX SHOULD BE REMOVED");
			page_ChemicalSearchResults.clickOnStructurePillX();
			afterFilter = page_ChemicalSearchResults.getResultsCount();
			if (beforeFilter >= afterFilter) {
				Application.Logger.addsubStep(LogStatus.PASS, "RESULT SETS ARE REFRESHED ", false);
			} else {
				Application.Logger.addsubStep(LogStatus.FAIL,
						"RESULT SETS ARE NOT REFRESHED  : \t" + beforeFilter + "\t\t AFTER FILTER :\t" + afterFilter,
						true);
			}
			Application.Logger.endStep();

		} catch (Exception e) {
			Application.Logger.addException(e.getMessage());
			return flag = false;
		}
		return flag;
	}
	//CHEMEXP-1291
			@Test
		public boolean validateStructureSearchAndLiteratureTab(Controller Application, HashMap<String, String> input) {
				page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
				page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
				page_SavedRecords = new Page_SavedRecords(Application);
				String searchText = input.get("searchtext");
				String smiley = input.get("smiles");
				String expstructureLable = "Search patent structure as";
				String actstructureLable;
				boolean flag = true;
				try {
					Application.Logger.addStep("1.VERIFY IF THE SEARCH PATENT LABLE IS DISPLAYED ABOVE THE STRUCTURE RADIO BUTTONS","SEARCH PATENT STRUCTURE AS LABLE SHOULD BE DISPLAYED ABOVE STRUCTURE RADIO BUTTONS ");
					page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
					actstructureLable=page_ChemicalSearchLandingPage.getTextStructureSearchLable();
					if (actstructureLable.equalsIgnoreCase(expstructureLable)) {
						Application.Logger.addsubStep(LogStatus.PASS,"STRUCTURE LABLE NAME IS DISPLAYED ABOVE THE RADIO BUTTON",false);
					} else {
						Application.Logger.addsubStep(LogStatus.FAIL,"STRUCTURE LABLE NAME IS NOT DISPLAYED ABOVE THE RADIO BUTTON : EXPECTED STRUCTURE LABLE :\t" +expstructureLable+ "\t ACTUAL STRUCTURE LABLE:" +actstructureLable,true);
					}
					Application.Logger.endStep();				
					Application.Logger.addStep("2.PERFORM A STRUCTURE SEARCH FROM THE LANDING PAGE","RESULTS SET PAGE SHOULD BE DISPLAYED");
					page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.selectStructureRadioButton("exact");
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.clickOnSearchIcon();
					Application.waitUntilFectchRecordProgressBarToDisappears();
					if (page_ChemicalSearchResults.checkIfResultsFound()) {
						Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
					} else {
						throw new Exception("Results set is not loaded for the chemical search");
					}
					Application.Logger.endStep();
					Application.Logger.addStep("3.VERIFY THE LITERATURE TAB AFTER THE STRUCTURE SEARCH","LITERATURE TAB SHOULD BE DISABLED AFTER THE STRUCTURE SEARCH");
					if (page_ChemicalSearchResults.tabLiteratureSearch().isDisabledLiteratureTab()) {
						Application.Logger.addsubStep(LogStatus.PASS, "LITERATURE TAB IS DISABLED AFTER THE STRUCTURE SEARCH", false);
					} else {
						Application.Logger.addsubStep(LogStatus.FAIL, "LITERATURE TAB IS NOT DISABLED AFTER THE STRUCTURE SEARCH",true);
						}
					Application.Logger.endStep();
				} catch (Exception e) {
					Application.Logger.addException(e.getMessage());
					return flag = false;
				}
				return flag;
			}
			
			//CHEMEXP-1292	
			@Test
			public boolean validateStructureSearchKeywordsAndPhrase(Controller Application, HashMap<String, String> input) {
				page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
				page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
				page_SavedRecords = new Page_SavedRecords(Application);
				String searchText = input.get("searchtext");
				String smiley = input.get("smiles");
				String secondsearchText="polymer";
				String actSearchPhrase;
				int beforeStructureSearch,afterStructureSearch;
				List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(","));
				boolean flag = true;
				try {
					Application.Logger.addStep("1.PERFORM A EXACT STRUCTURE SEARCH AND FREE TEXT SEARCH FROM THE LANDING PAGE","RESULTS SET PAGE SHOULD BE DISPLAYED FOR BOTH EXACT STRUCURE SEARCH AND FREE TEXT SEARCH");
					page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
					page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.selectStructureRadioButton("exact");
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.clickOnSearchIcon();
					Application.waitUntilFectchRecordProgressBarToDisappears();
					if (page_ChemicalSearchResults.checkIfResultsFound()) {
						Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
					} else {
						throw new Exception("Results set is not loaded for the chemical search");
					}
					beforeStructureSearch=page_ChemicalSearchResults.getResultsCount();
					Application.Logger.endStep();				
					Application.Logger.addStep("2.MODIFY THE TOP SECTION AND VERIFY IF THE RESULTS ARE REFRESHED AFTER SEARCHING WITH THE MODIFIED PHRASE","RESULTS SHOULD BE REFRESHED AFTER SEARCHING WITH THE MODIFIED PHRASE");
					page_ChemicalSearchResults.setTextPhrase(secondsearchText);
					page_ChemicalSearchResults.clickOnSearchIcon();			
					Application.waitUntilFectchRecordProgressBarToDisappears();
					Application.waitUntilFectchRecordProgressBarToDisappears();
					afterStructureSearch=page_ChemicalSearchResults.getResultsCount();
					if (beforeStructureSearch<=afterStructureSearch) {
						Application.Logger.addsubStep(LogStatus.PASS,"RESULTS ARE REFRESHED AFTER SEARCHING WITH THE MODIFIED PHRASE",false);
					} else {
						Application.Logger.addsubStep(LogStatus.FAIL,"RESULTS ARE NOT REFRESHED AFTER SEARCHING WITH THE MODIFIED PHRASE : BEFORE FILTER COUNT :\t" +beforeStructureSearch+ "\t AFTER FILTER COUNT:" +afterStructureSearch,true);
					}
					Application.Logger.endStep();	
					Application.Logger.addStep("3.VERIFY IF THE MODIFIED PHRASE IS DISPLAYED IN THE RS TOP SECTION","MODIFIED PHRASE SHOULD BE DISPLAYED IN THE RS TOP SECTION");
					actSearchPhrase=page_ChemicalSearchResults.getTextSearchBox();
					if (secondsearchText.equalsIgnoreCase(actSearchPhrase)) {
						Application.Logger.addsubStep(LogStatus.PASS,"MODIFIED PHRASE IS DISPLAYED IN THE RS TOP SECTION",false);
					} else {
						Application.Logger.addsubStep(LogStatus.FAIL,"MODIFIED PHRASE IS NOT DISPLAYED IN THE RS TOP SECTION : EXPECTED SEARCH PHRASE :\t" +secondsearchText+ "\t ACTUAL SEARCH PHRASE:" +actSearchPhrase,true);
					}
					Application.Logger.endStep();				
					Application.Logger.addStep("4.MODIFY THE BOTTOM SECTION AND VERIFY IF THE RESULTS ARE REFRESHED AFTER ADDING THE KEYWORDS","RESULTS SHOULD BE REFRESHED AFTER ADDING THE KEYWORDS");
					page_ChemicalSearchResults.setKeyWords(finalKeywordList);
					page_ChemicalSearchResults.clickOnSearchIcon();
					Application.waitUntilFectchRecordProgressBarToDisappears();
					Application.waitUntilFectchRecordProgressBarToDisappears();				
					afterStructureSearch=page_ChemicalSearchResults.getResultsCount();
					if (beforeStructureSearch<=afterStructureSearch) {
						Application.Logger.addsubStep(LogStatus.PASS,"RESULTS ARE REFRESHED AFTER SEARCHING WITH THE NEWLY ADDED KEYWORDS",false);
					} else {
						Application.Logger.addsubStep(LogStatus.FAIL,"RESULTS ARE NOT REFRESHED AFTER SEARCHING WITH THE NEWLY ADDED KEYWORDS : BEFORE FILTER COUNT :\t" +beforeStructureSearch+ "\t AFTER FILTER COUNT:" +afterStructureSearch,true);
					}
					Application.Logger.endStep();				
					Application.Logger.addStep("5.VERIFY IF THE NEWLY ADDED PIILS ARE DISPLAYED IN THE RS BOTTOM PILLS SECTION","NEWLY ADDED PIILS SHOULD BE DISPLAYED IN THE RS BOTTOM PILLS SECTION");
					List<String> getListOfPills = page_ChemicalSearchResults.getListOfPillBoxes();
					for (String pills : getListOfPills) {
						if (pills != null) {
							Application.Logger.addsubStep(LogStatus.PASS,"PILL :" + pills + " IS DISPLAYED IN THE RS BOTTOM SECTION", false);
						} else {
							Application.Logger.addsubStep(LogStatus.FAIL,"PILLS ARE NOT DISPLAYED IN THE RS BOTTOM SECTION :'" + secondsearchText , true);
						}
					}
					Application.waitTime(2);
					Application.Logger.endStep();				
					Application.Logger.addStep("6.PERFORM A SUB STRUCTURE SEARCH AND FREE TEXT SEARCH FROM THE LANDING PAGE","RESULTS SET PAGE SHOULD BE DISPLAYED FOR BOTH EXACT STRUCURE SEARCH AND FREE TEXT SEARCH");
					page_ChemicalSearchResults.clickOnChemExpHomePage();
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
					page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.selectStructureRadioButton("Substructure");
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.clickOnSearchIcon();
					Application.waitUntilFectchRecordProgressBarToDisappears();
					if (page_ChemicalSearchResults.checkIfResultsFound()) {
						Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
					} else {
						throw new Exception("Results set is not loaded for the chemical search");
					}
					beforeStructureSearch=page_ChemicalSearchResults.getResultsCount();
					Application.Logger.endStep();				
					Application.Logger.addStep("7.MODIFY THE TOP SECTION AND VERIFY IF THE RESULTS ARE REFRESHED AFTER SEARCHING WITH THE MODIFIED PHRASE","RESULTS SHOULD BE REFRESHED AFTER SEARCHING WITH THE MODIFIED PHRASE");
					page_ChemicalSearchResults.setTextPhrase(secondsearchText);
					page_ChemicalSearchResults.clickOnSearchIcon();			
					Application.waitUntilFectchRecordProgressBarToDisappears();
					Application.waitUntilFectchRecordProgressBarToDisappears();
					afterStructureSearch=page_ChemicalSearchResults.getResultsCount();
					if (beforeStructureSearch>=afterStructureSearch) {
						Application.Logger.addsubStep(LogStatus.PASS,"RESULTS ARE REFRESHED AFTER SEARCHING WITH THE MODIFIED PHRASE",false);
					} else {
						Application.Logger.addsubStep(LogStatus.FAIL,"RESULTS ARE NOT REFRESHED AFTER SEARCHING WITH THE MODIFIED PHRASE : BEFORE FILTER COUNT :\t" +beforeStructureSearch+ "\t AFTER FILTER COUNT:" +afterStructureSearch,true);
					}
					Application.Logger.endStep();	
					Application.Logger.addStep("8.VERIFY IF THE MODIFIED PHRASE IS DISPLAYED IN THE RS TOP SECTION","MODIFIED PHRASE SHOULD BE DISPLAYED IN THE RS TOP SECTION");
					actSearchPhrase=page_ChemicalSearchResults.getTextSearchBox();
					if (secondsearchText.equalsIgnoreCase(actSearchPhrase)) {
						Application.Logger.addsubStep(LogStatus.PASS,"MODIFIED PHRASE IS DISPLAYED IN THE RS TOP SECTION",false);
					} else {
						Application.Logger.addsubStep(LogStatus.FAIL,"MODIFIED PHRASE IS NOT DISPLAYED IN THE RS TOP SECTION : EXPECTED SEARCH PHRASE :\t" +secondsearchText+ "\t ACTUAL SEARCH PHRASE:" +actSearchPhrase,true);
					}
					Application.Logger.endStep();				
					Application.Logger.addStep("9.MODIFY THE BOTTOM SECTION AND VERIFY IF THE RESULTS ARE REFRESHED AFTER ADDING THE KEYWORDS","RESULTS SHOULD BE REFRESHED AFTER ADDING THE KEYWORDS");
					page_ChemicalSearchResults.setKeyWords(finalKeywordList);
					page_ChemicalSearchResults.clickOnSearchIcon();
					Application.waitUntilFectchRecordProgressBarToDisappears();
					Application.waitUntilFectchRecordProgressBarToDisappears();				
					afterStructureSearch=page_ChemicalSearchResults.getResultsCount();
					if (beforeStructureSearch>=afterStructureSearch) {
						Application.Logger.addsubStep(LogStatus.PASS,"RESULTS ARE REFRESHED AFTER SEARCHING WITH THE NEWLY ADDED KEYWORDS",false);
					} else {
						Application.Logger.addsubStep(LogStatus.FAIL,"RESULTS ARE NOT REFRESHED AFTER SEARCHING WITH THE NEWLY ADDED KEYWORDS : BEFORE FILTER COUNT :\t" +beforeStructureSearch+ "\t AFTER FILTER COUNT:" +afterStructureSearch,true);
					}
					Application.Logger.endStep();				
					Application.Logger.addStep("10.VERIFY IF THE NEWLY ADDED PIILS ARE DISPLAYED IN THE RS BOTTOM PILLS SECTION","NEWLY ADDED PIILS SHOULD BE DISPLAYED IN THE RS BOTTOM PILLS SECTION");
					List<String> getListOfPills1 = page_ChemicalSearchResults.getListOfPillBoxes();
					for (String pills : getListOfPills1) {
						if (pills != null) {
							Application.Logger.addsubStep(LogStatus.PASS,"PILL :" + pills + " IS DISPLAYED IN THE RS BOTTOM SECTION", false);
						} else {
							Application.Logger.addsubStep(LogStatus.FAIL,"PILLS ARE NOT DISPLAYED IN THE RS BOTTOM SECTION :'" + secondsearchText , true);
						}
					}
					Application.Logger.endStep();
				} catch (Exception e) {
					Application.Logger.addException(e.getMessage());
					return flag = false;
				}
				return flag;
			}
			
			//CHEMEXP-1293
			@Test
			public boolean validateHitStructureImagesAndPagination(Controller Application, HashMap<String, String> input) {
				page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
				page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
				page_SavedRecords = new Page_SavedRecords(Application);
				String searchText = input.get("searchtext");
				String smiley = input.get("smiles");
				String paginatorValue;
				boolean flag = true;
				try {
					Application.Logger.addStep("1.PERFORM A EXACT STRUCTURE SEARCH AND FREE TEXT SEARCH FROM THE LANDING PAGE","RESULTS SET PAGE SHOULD BE DISPLAYED FOR BOTH EXACT STRUCURE SEARCH AND FREE TEXT SEARCH");
					page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
					page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.selectStructureRadioButton("Substructure");
					Application.waitTime(2);
					page_ChemicalSearchLandingPage.clickOnSearchIcon();
					Application.waitUntilFectchRecordProgressBarToDisappears();
					if (page_ChemicalSearchResults.checkIfResultsFound()) {
						Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
					} else {
						throw new Exception("Results set is not loaded for the chemical search");
					}
					
					Application.Logger.endStep();				
					Application.Logger.addStep("2.VERIFY THE PAGINATION IN HIT STRCUTURE IMAGE VIEWER","PAGINATION SHOULD BE DISPLAYED IF THE IMAGES COUNT ARE MORE THAN 20 IN IMAGE VIEWER WITH PREV AND NEXT BUTTONS FOR NAVIGATION");
					page_ChemicalSearchResults.tabPatentSearch().clickOnPatentRecord(1);
					page_ChemicalSearchResults.tabPatentSearch().clickOnHitStructureslink();	
					Application.waitTime(2);
					page_ChemicalSearchResults.tabPatentSearch().clickOnLinkSeeAll();
					Application.waitTime(2);
					paginatorValue=page_ChemicalSearchResults.tabPatentSearch().getTextImageViewerPaginatorRange();
					if(!page_ChemicalSearchResults.tabPatentSearch().isDisabledImageViewerNextPageArrow())
					{
						Application.Logger.addsubStep(LogStatus.PASS,paginatorValue+ "\t - NEXT BUTTON FROM THE IMAGE VIEWER PAGINATION IS ENABLED BEOFRE THE CLICK",false);	
					}
					else
					{
						Application.Logger.addsubStep(LogStatus.FAIL,paginatorValue+ "\t - NEXT BUTTON FROM THE IMAGE VIEWER PAGINATION IS NOT ENABLED BEOFRE THE CLICK",true);
					}
					page_ChemicalSearchResults.tabPatentSearch().clickOnImageViewerArrowNextPage();
					Application.waitTime(2);
					paginatorValue=page_ChemicalSearchResults.tabPatentSearch().getTextImageViewerPaginatorRange();
					if(page_ChemicalSearchResults.tabPatentSearch().isDisabledImageViewerNextPageArrow())
					{
						Application.Logger.addsubStep(LogStatus.PASS,paginatorValue+ "\t - NEXT BUTTON FROM THE IMAGE VIEWER PAGINATION IS DISABLED AFTER THE CLICK",false);	
					}
					else
					{
						Application.Logger.addsubStep(LogStatus.FAIL,paginatorValue+ "\t - NEXT BUTTON FROM THE IMAGE VIEWER PAGINATION IS NOT DISABLED AFTER THE CLICK",true);
					}
					paginatorValue=page_ChemicalSearchResults.tabPatentSearch().getTextImageViewerPaginatorRange();
					if(!page_ChemicalSearchResults.tabPatentSearch().isDisabledImageViewerPreviousPageArrow())
					{
						Application.Logger.addsubStep(LogStatus.PASS,paginatorValue+ "\t - PREV BUTTON FROM THE IMAGE VIEWER PAGINATION IS ENABLED BEFORE THE CLICK",false);	
					}
					else
					{
						Application.Logger.addsubStep(LogStatus.FAIL,paginatorValue+ "\t - PREV BUTTON FROM THE IMAGE VIEWER PAGINATION IS NOT ENABLED BEFORE THE CLICK",true);
					}
					page_ChemicalSearchResults.tabPatentSearch().clickOnImageViewerArrowPrevPage();
					Application.waitTime(2);	
					paginatorValue=page_ChemicalSearchResults.tabPatentSearch().getTextImageViewerPaginatorRange();
					if(page_ChemicalSearchResults.tabPatentSearch().isDisabledImageViewerPreviousPageArrow())
					{
						Application.Logger.addsubStep(LogStatus.PASS,paginatorValue+ "\t - PREV BUTTON FROM THE IMAGE VIEWER PAGINATION IS DISABLED AFTER THE CLICK",false);	
					}
					else
					{
						Application.Logger.addsubStep(LogStatus.FAIL,paginatorValue+ "\t -PREV BUTTON FROM THE IMAGE VIEWER PAGINATION IS NOT DISABLED AFTER THE CLICK",true);
					}
					Application.Logger.endStep();
				} catch (Exception e) {
					Application.Logger.addException(e.getMessage());
					return flag = false;
				}
				return flag;
			}

			
//CHEMEXP-1590
			
@Test
public boolean vaildateCompanyPeopleSearchRSPage(Controller Application, HashMap<String, String> input) {
	page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
	page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
	boolean flag = true;
	String searchText = input.get("searchtext");
	String expToastMsg2 = "Person pill is added";
	String expToastMsg1 = "Company pill is added";
	String expCompanyOrPersonToastMsg="You have reached the limit. If you have to add company/person criteria, you need to delete one pill/free up the space.";
	String actualToastMsg;
	boolean blnchk1 = false;
	boolean blnchk2 = false;
	List<String> finalKeywordList = Arrays.asList(input.get("keywords").split(","));
	try {
		Application.Logger.addStep("1.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
				"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
		page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
		page_ChemicalSearchLandingPage.clickOnSearchIcon();
		Application.waitUntilFectchRecordProgressBarToDisappears();
		if (page_ChemicalSearchResults.checkIfResultsFound()) {
			Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
		} else {
			throw new Exception("Results set is not loaded for the chemical search");
		}
		Application.Logger.endStep();
		
		Application.Logger.addStep("2.VERIFY PERFORMING THE SEARCH IN RESULTS SET PAGE WITH PERSON OR ORAGNIZATION ","RESULTS SET PAGE SHOULD BE REFRESHED");
		page_ChemicalSearchResults.clickOnPersonIcon();
		page_ChemicalSearchResults.setTextPersonOrOrganization("FUJITA M");
		page_ChemicalSearchResults.clickOnButtonAdd();				
		page_ChemicalSearchResults.clickOnOrganizationIcon();
		page_ChemicalSearchResults.setTextPersonOrOrganization("TORAY INDUSTRIES INC");
		page_ChemicalSearchResults.clickOnButtonAdd();				
		Application.waitTime(2);
		page_ChemicalSearchResults.clickOnSearchIcon();
		Application.waitUntilFectchRecordProgressBarToDisappears();			
		if (page_ChemicalSearchResults.checkIfResultsFound()) {
			Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED ", false);
		} else {
			throw new Exception("RESULTS NOT DISPLAYED ");
		}
		Application.Logger.endStep();
		
		Application.Logger.addStep("3.Verify the color of Person and Company icon",
				"color should be in purple color");
		if (page_ChemicalSearchResults.getColorOfPersonPillBox().trim().equals("rgba(101, 19, 231, 1)")) {
			Application.Logger.addsubStep(LogStatus.PASS, "PERSON ICON IS IN PURPLE COLOR ", false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL, "PERSON ICON IS NOT IN PURPLE COLOR", true);
		}
		if (page_ChemicalSearchResults.getColorOfCompanyPillBox().trim().equals("rgba(101, 19, 231, 1)")) {
			Application.Logger.addsubStep(LogStatus.PASS, "COMPANY ICON IS IN PURPLE COLOR ", false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL, "COMPANY ICON IS NOT IN PURPLE COLOR", true);
		}
		Application.Logger.endStep();
		
		
		Application.Logger.addStep("4.VERIFY TOAST MESSAGE AFTER ADDING PERSON AND COMPANY KEYWORDS","TOAST MESSAGE SHOULD BE DISPLAYED");
		page_ChemicalSearchResults.clickOnOrganizationIcon();
		page_ChemicalSearchResults.setTextPersonOrOrganization("TORAY");
		page_ChemicalSearchResults.clickOnButtonAdd();	
		actualToastMsg = page_ChemicalSearchResults.getTextPersonOrganizationToastMessage();
		if (expToastMsg1.equals(actualToastMsg)) {
			Application.Logger.addsubStep(LogStatus.PASS,
					"PERSON TOAST MESSAGE IS SUCCESSFULLY DISPLAYED IN THE RS PAGE", false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,
					"PERSON TIPS TOAST MESSAGE IS NOT DISPLAYED IN THE RS PAGE", true);
		}
		page_ChemicalSearchResults.clickOnPersonIcon();
		page_ChemicalSearchResults.setTextPersonOrOrganization("Einsteen");
		page_ChemicalSearchResults.clickOnButtonAdd();
		actualToastMsg = page_ChemicalSearchResults.getTextPersonToastMessage();
		if (expToastMsg2.equals(actualToastMsg)) {
			Application.Logger.addsubStep(LogStatus.PASS,
					"COMPANY TIPS TOAST MESSAGE IS SUCCESSFULLY DISPLAYED IN THE RS PAGE", false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,
					"COMPANY TIPS TOAST MESSAGE IS NOT DISPLAYED IN THE RS PAGE", true);
		}
		Application.Logger.endStep();
		
		Application.Logger.addStep(
				"5.VERIFY THE COMPANY AND PERSON ICONS AFTER EXCEEDS THE KEYWORDS LIMIT ",
				"COMPANY AND PERSON ICONS SHOULD BE DISABLED");
		page_ChemicalSearchResults.setKeyWords(finalKeywordList);
		Application.waitTime(3);
		blnchk1 = page_ChemicalSearchResults.isDisabledPersonIcon();
		if (blnchk1) {
			Application.Logger.addsubStep(LogStatus.PASS, "PERSON ICON IS DISABLED", false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL, "PERSON ICON IS NOT DISABLED", true);
		}
		blnchk2 = page_ChemicalSearchResults.isDisabledCompanyIcon();
		if (blnchk2) {
			Application.Logger.addsubStep(LogStatus.PASS, "COMPANY ICON IS DISABLED", false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL, "COMPANY ICON IS NOT DISABLED", true);
		}
		
		Application.Logger.endStep();
		
		Application.Logger.addStep(
				"6.VERIFY THE COMPANY AND PERSON ICONS TOAST MESSAGE WHEN ICONS ARE DISABLED ",
				"COMPANY AND PERSON ICONS SHOULD DISPLAY TOAST MESSAGE");
		Application.waitTime(3);
		page_ChemicalSearchResults.clickOnPersonIcon();
		actualToastMsg = page_ChemicalSearchResults.getTextPersonLimitToastMessage();
		if (expCompanyOrPersonToastMsg.equals(actualToastMsg)) {
			Application.Logger.addsubStep(LogStatus.PASS,
					"PERSON ICON LIMIT TOAST MESSAGE IS SUCCESSFULLY DISPLAYED IN THE RS PAGE", false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,
					"PERSON ICON LIMIT TOAST MESSAGE IS NOT DISPLAYED IN THE RS PAGE", true);
		}
		page_ChemicalSearchResults.clickOnOrganizationIcon();
		actualToastMsg = page_ChemicalSearchResults.getTextCompanyLimitToastMessage();
		if (expCompanyOrPersonToastMsg.equals(actualToastMsg)) {
			Application.Logger.addsubStep(LogStatus.PASS,
					"COMPANY ICON LIMIT TOAST MESSAGE IS SUCCESSFULLY DISPLAYED IN THE RS PAGE", false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,
					"COMPANY ICON LIMIT TOAST MESSAGE IS NOT DISPLAYED IN THE RS PAGE", true);
		}
		Application.Logger.endStep();
	} catch (Exception e) {
		
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}
/**  CHEMEXP-1569* */
@Test
public boolean vaildatePatentClusterItems(Controller Application, HashMap<String, String> input) {
	page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
	page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
	String searchText = input.get("searchtext");
	int beforeFilter, afterFilter;
	String expLabel = "Edit existing cluster items",expErrMsg="Minimum 2 items are required";
	String actLabel,actErrMsg;
	boolean flag = true;
	try {
		Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
		page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
		page_ChemicalSearchLandingPage.clickOnSearchIcon();
		Application.waitUntilFectchRecordProgressBarToDisappears();
		if (page_ChemicalSearchResults.checkIfResultsFound()) {
			Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
		} else {
			throw new Exception("RESULTS NOT DISPLAYED FOR THE CHEMICAL SEARCH");
		}
		beforeFilter = page_ChemicalSearchResults.getResultsCount();
		Application.Logger.endStep();
		Application.Logger.addStep("2.VERIFY IF THE LABEL IS DISPLAYED IN THE EDIT CLUSTER ITEMS SECTION","LABLE SHOULD BE DISPLAYED IN THE EDIT CLUSTER ITEMS SECTION");
		page_ChemicalSearchResults.clickOnClustermapLink();		
		Application.waitTime(3);
		page_ChemicalSearchResults.tabPatentSearch().clickOnLinkCustomize();
		Application.waitTime(2);
		actLabel = page_ChemicalSearchResults.tabPatentSearch().getTextClusterItemLabel();
		Application.waitTime(2);
		if (actLabel.equals(expLabel)) {
			Application.Logger.addsubStep(LogStatus.PASS," EXPECTED CLUSTER ITEM LABEL : \t\t" + expLabel+ "\t\t IS MATCHING WITH ACTUAL CLUSTER ITEM LABEL :" + actLabel,false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL," EXPECTED CLUSTER ITEM LABEL : \t\t" + expLabel+ "\t\t IS NOT MATCHING WITH ACTUAL CLUSTER ITEM LABEL :" + actLabel,true);
		}
		Application.Logger.endStep();
		Application.Logger.addStep("3.VERIFY IF VALIDATION MESSAGE IS DISPLAYED WHEN ALL THE CLUSTER ITEMS ARE DELETED","ERROR MESSAGE SHOULD BE DISPLAYED WHEN ALL THE CLUSTER ITEMS ARE DELETED");
		List<String> clusterItemsBeforeRestore = page_ChemicalSearchResults.tabPatentSearch().getAllClusterItems();
			page_ChemicalSearchResults.tabPatentSearch().deleteExistingClusterItems();
		actErrMsg=page_ChemicalSearchResults.tabPatentSearch().getTextClusterErrorMessage();
		if (actErrMsg.equals(expErrMsg)) {
			Application.Logger.addsubStep(LogStatus.PASS," EXPECTED ERROR MESSAGE : \t\t" + expErrMsg+ "\t\t IS MATCHING WITH ACTUAL ERROR MESSAGE:" + actErrMsg,false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL," EXPECTED ERROR MESSAGE : \t\t" + expErrMsg+ "\t\t IS NOT MATCHING WITH ACTUAL ERROR MESSAGE:" + actErrMsg,true);
		}
		Application.Logger.endStep();							
		Application.Logger.addStep("4.VERIFY IF CLUSTER ITEMS ARE RESTORED SUCCESSFULLY ON CLICK OF RESTORE DEFAULT BUTTON","CLUSTER ITEMS SHOULD BE RESTORED TO DEFAULT ITEMS");
		page_ChemicalSearchResults.tabPatentSearch().clickOnButtonRestorDefault();
		Application.waitTime(2);
		List<String> clusterItemsAfterRestore = page_ChemicalSearchResults.tabPatentSearch().getAllClusterItems();
		boolean blncheck = TestUtil.validateTwoLists(clusterItemsBeforeRestore, clusterItemsAfterRestore);
		if (blncheck) {
			Application.Logger.addsubStep(LogStatus.PASS, "CLUSTER ITEMS ARE RESTORED SUCCESSFULLY ON CLICK OF RESTORE DEFAULT BUTTON",false);
			Application.Logger.addsubStep(LogStatus.INFO,"CLUSTER ITEMS BEFORE RESTORE: " + clusterItemsBeforeRestore.size() + clusterItemsBeforeRestore,false);
			Application.Logger.addsubStep(LogStatus.INFO, "CLUSTER ITEMS AFTER RESTORE: "+ clusterItemsAfterRestore.size() + clusterItemsAfterRestore, false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"CLUSTER ITEMS ARE NOT RESTORED SUCCESSFULLY ON CLICK OF RESTORE DEFAULT BUTTON", true);
			Application.Logger.addsubStep(LogStatus.INFO,"CLUSTER ITEMS BEFORE RESTORE: " + clusterItemsBeforeRestore.size() + clusterItemsBeforeRestore,false);
			Application.Logger.addsubStep(LogStatus.INFO, "CLUSTER ITEMS AFTER RESTORE: "+ clusterItemsAfterRestore.size() + clusterItemsAfterRestore, false);
		}
		Application.Logger.endStep();				
		Application.Logger.addStep("5.PERFORM CLUSTER MAP SEARCH WITH THE NEWLY ADDED CLUSTER ITEMS AND VERIFY THE RESULTS SET PAGE","RESULT SET PAGE SHOULD BE RERESHED WITH THE NEWLY ADDED CLUSTER ITEMS");
		page_ChemicalSearchResults.tabPatentSearch().deleteExistingClusterItems();
		page_ChemicalSearchResults.tabPatentSearch().setTextFirstClusterItem("FIBER");
		page_ChemicalSearchResults.tabPatentSearch().setTextSecondClusterItem("POLYMER");
		page_ChemicalSearchResults.tabPatentSearch().clickOnButtonApply();
		Application.waitTime(2);
		page_ChemicalSearchResults.tabPatentSearch().clickOnFirstBubble();
		Application.waitUntilFectchRecordProgressBarToDisappears();	
		afterFilter = page_ChemicalSearchResults.getResultsCount();	
		Application.waitTime(4);						
		if (beforeFilter >= afterFilter) {
			Application.Logger.addsubStep(LogStatus.PASS,"RESULT SETS ARE REFRESHED AFTER THE CLICK ON NEWLY ADDED BUBBLE IN THE CLUSTER MAP",false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"RESULT SETS ARE NOT REFRESHED AFTER THE CLICK ON NEWLY ADDED BUBBLE IN THE CLUSTER MAP : \t"+ beforeFilter + "\t\t AFTER BUBBLE MAP FILTER :\t"+ afterFilter,true);
		}
		Application.Logger.endStep();
	} catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}

/**  CHEMEXP-1570* */
@Test
public boolean vaildateLiteratureClusterItems(Controller Application, HashMap<String, String> input) {
	page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
	page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
	String searchText = input.get("searchtext");
	int beforeFilter, afterFilter;
	String expLabel = "Edit existing cluster items",expErrMsg="Minimum 2 items are required";
	String actLabel,actErrMsg;
	boolean flag = true;
	try {
		Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL SEARCH");
		page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
		page_ChemicalSearchLandingPage.clickOnSearchIcon();
		Application.waitUntilFectchRecordProgressBarToDisappears();
		if (page_ChemicalSearchResults.checkIfResultsFound()) {
			Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
		} else {
			throw new Exception("RESULTS NOT DISPLAYED FOR THE CHEMICAL SEARCH");
		}
		beforeFilter = page_ChemicalSearchResults.getResultsCount();
		Application.Logger.endStep();
		Application.Logger.addStep("2.VERIFY IF THE LABEL IS DISPLAYED IN THE EDIT CLUSTER ITEMS SECTION","LABLE SHOULD BE DISPLAYED IN THE EDIT CLUSTER ITEMS SECTION");
		page_ChemicalSearchResults.clickOnTabLiterature();
		Application.waitUntilFectchRecordProgressBarToDisappears();
		page_ChemicalSearchResults.clickOnClustermapLink();		
		Application.waitTime(3);
		page_ChemicalSearchResults.tabLiteratureSearch().clickOnLinkCustomize();
		actLabel = page_ChemicalSearchResults.tabLiteratureSearch().getTextClusterItemLabel();
		if (actLabel.equals(expLabel)) {
			Application.Logger.addsubStep(LogStatus.PASS," EXPECTED CLUSTER ITEM LABEL : \t\t" + expLabel+ "\t\t IS MATCHING WITH ACTUAL CLUSTER ITEM LABEL :" + actLabel,false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL," EXPECTED CLUSTER ITEM LABEL : \t\t" + expLabel+ "\t\t IS NOT MATCHING WITH ACTUAL CLUSTER ITEM LABEL :" + actLabel,true);
		}
		Application.Logger.endStep();
		Application.Logger.addStep("3.VERIFY IF VALIDATION MESSAGE IS DISPLAYED WHEN ALL THE CLUSTER ITEMS ARE DELETED","ERROR MESSAGE SHOULD BE DISPLAYED WHEN ALL THE CLUSTER ITEMS ARE DELETED");
		List<String> clusterItemsBeforeRestore = page_ChemicalSearchResults.tabLiteratureSearch().getAllClusterItems();
			page_ChemicalSearchResults.tabLiteratureSearch().deleteExistingClusterItems();
		actErrMsg=page_ChemicalSearchResults.tabLiteratureSearch().getTextClusterErrorMessage();
		if (actErrMsg.equals(expErrMsg)) {
			Application.Logger.addsubStep(LogStatus.PASS," EXPECTED ERROR MESSAGE : \t\t" + expErrMsg+ "\t\t IS MATCHING WITH ACTUAL ERROR MESSAGE:" + actErrMsg,false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL," EXPECTED ERROR MESSAGE : \t\t" + expErrMsg+ "\t\t IS NOT MATCHING WITH ACTUAL ERROR MESSAGE:" + actErrMsg,true);
		}
		Application.Logger.endStep();							
		Application.Logger.addStep("4.VERIFY IF CLUSTER ITEMS ARE RESTORED SUCCESSFULLY ON CLICK OF RESTORE DEFAULT BUTTON","CLUSTER ITEMS SHOULD BE RESTORED TO DEFAULT ITEMS");
		page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonRestorDefault();
		Application.waitTime(2);
		List<String> clusterItemsAfterRestore = page_ChemicalSearchResults.tabLiteratureSearch().getAllClusterItems();
		boolean blncheck = TestUtil.validateTwoLists(clusterItemsBeforeRestore, clusterItemsAfterRestore);
		if (blncheck) {
			Application.Logger.addsubStep(LogStatus.PASS, "CLUSTER ITEMS ARE RESTORED SUCCESSFULLY ON CLICK OF RESTORE DEFAULT BUTTON",false);
			Application.Logger.addsubStep(LogStatus.INFO,"CLUSTER ITEMS BEFORE RESTORE: " + clusterItemsBeforeRestore.size() + clusterItemsBeforeRestore,false);
			Application.Logger.addsubStep(LogStatus.INFO, "CLUSTER ITEMS AFTER RESTORE: "+ clusterItemsAfterRestore.size() + clusterItemsAfterRestore, false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"CLUSTER ITEMS ARE NOT RESTORED SUCCESSFULLY ON CLICK OF RESTORE DEFAULT BUTTON", true);
			Application.Logger.addsubStep(LogStatus.INFO,"CLUSTER ITEMS BEFORE RESTORE: " + clusterItemsBeforeRestore.size() + clusterItemsBeforeRestore,false);
			Application.Logger.addsubStep(LogStatus.INFO, "CLUSTER ITEMS AFTER RESTORE: "+ clusterItemsAfterRestore.size() + clusterItemsAfterRestore, false);
		}
		Application.Logger.endStep();				
		Application.Logger.addStep("5.PERFORM CLUSTER MAP SEARCH WITH THE NEWLY ADDED CLUSTER ITEMS AND VERIFY THE RESULTS SET PAGE","RESULT SET PAGE SHOULD BE RERESHED WITH THE NEWLY ADDED CLUSTER ITEMS");
		page_ChemicalSearchResults.tabLiteratureSearch().deleteExistingClusterItems();
		page_ChemicalSearchResults.tabLiteratureSearch().setTextFirstClusterItem("FIBER");
		page_ChemicalSearchResults.tabLiteratureSearch().setTextSecondClusterItem("POLYMER");
		page_ChemicalSearchResults.tabLiteratureSearch().clickOnButtonApply();
		Application.waitTime(2);
		page_ChemicalSearchResults.tabLiteratureSearch().clickOnFirstBubble();
		Application.waitUntilFectchRecordProgressBarToDisappears();	
		afterFilter = page_ChemicalSearchResults.getResultsCount();	
		Application.waitTime(4);						
		if (beforeFilter >= afterFilter) {
			Application.Logger.addsubStep(LogStatus.PASS,"RESULT SETS ARE REFRESHED AFTER THE CLICK ON NEWLY ADDED BUBBLE IN THE CLUSTER MAP",false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"RESULT SETS ARE NOT REFRESHED AFTER THE CLICK ON NEWLY ADDED BUBBLE IN THE CLUSTER MAP : \t"+ beforeFilter + "\t\t AFTER BUBBLE MAP FILTER :\t"+ afterFilter,true);
		}
		Application.Logger.endStep();
	} catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}


/**  CHEMEXP-1567* */
@Test
public boolean vaildateCompanyPeopleSearch(Controller Application, HashMap<String, String> input) {
	page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
	page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
	boolean flag = true;
	try {
		Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE WITH PERSON OR ORAGNIZATION TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL PERSON/ORGANIZATION SEARCH");
		page_ChemicalSearchLandingPage.clickOnPersonIcon();
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.setTextPersonOrOrganization("JAJU");
		page_ChemicalSearchLandingPage.clickOnButtonAdd();				
		page_ChemicalSearchLandingPage.clickOnOrganizationIcon();
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.setTextPersonOrOrganization("JAJU");
		page_ChemicalSearchLandingPage.clickOnButtonAdd();				
		page_ChemicalSearchLandingPage.clickOnPersonIcon();
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.setTextPersonOrOrganization("JAJU");
		page_ChemicalSearchLandingPage.clickOnButtonAdd();				
		page_ChemicalSearchLandingPage.clickOnOrganizationIcon();
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.setTextPersonOrOrganization("JAJU");
		page_ChemicalSearchLandingPage.clickOnButtonAdd();	
		Application.waitTime(2);
		List<String> pillsAtLandingPage = page_ChemicalSearchLandingPage.getAllPersonsOrganizations();
		page_ChemicalSearchLandingPage.clickOnSearchIcon();
		Application.waitUntilFectchRecordProgressBarToDisappears();			
		if (page_ChemicalSearchResults.checkIfResultsFound()) {
			Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
		} else {
			throw new Exception("RESULTS NOT DISPLAYED FOR THE CHEMICAL SEARCH");
		}
		Application.Logger.endStep();				
		Application.Logger.addStep("2.VERIFY IF THE ADDED PERSONS OR ORGANIZATION IN THE LANDING PAGE MATCHES WITH PERSONS OR ORGANIZATION IN THE  PATENT RESULTS SET PAGE","PERSONS OR ORGANIZATION IN THE LANDING PAGE SHOULD MATCHE WITH PERSONS OR ORGANIZATION IN PATENT RESULTS SET PAGE");
		List<String> pillsAtResultSetPage = page_ChemicalSearchResults.tabPatentSearch().getAllPersonsOrganizations();
		Application.waitTime(3);
		boolean blncheck = TestUtil.validateTwoLists(pillsAtLandingPage, pillsAtResultSetPage);
		if (blncheck) {
			Application.Logger.addsubStep(LogStatus.PASS, "COMPANY AND PERSONS PILLS IN THE LANDING PAGE IS MATCHING WITH COMPANY AND PERSONS IN THE PATENT RESULTS SET PAGE",false);
			Application.Logger.addsubStep(LogStatus.INFO,"LANDING PAGE PILLS: " + pillsAtLandingPage.size() + pillsAtLandingPage,false);
			Application.Logger.addsubStep(LogStatus.INFO, "PATENT RESULT SET PAGE PILLS: "+ pillsAtResultSetPage.size() + pillsAtResultSetPage, false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"COMPANY AND PERSONS PILLS IN THE LANDING PAGE IS NOT MATCHING WITH COMPANY AND PERSONS IN THE PATENT RESULTS SET PAGE", true);
			Application.Logger.addsubStep(LogStatus.INFO,"LANDING PAGE PILLS: " + pillsAtLandingPage.size() + pillsAtLandingPage,false);
			Application.Logger.addsubStep(LogStatus.INFO, "PATENT RESULT SET PAGE PILLS:: "+ pillsAtResultSetPage.size() + pillsAtResultSetPage, false);
		}
		Application.Logger.endStep();				
		Application.Logger.addStep("3.VERIFY IF THE ADDED PERSONS OR ORGANIZATION IN THE LANDING PAGE MATCHES WITH PERSONS OR ORGANIZATION IN THE  LITERATURE RESULTS SET PAGE","PERSONS OR ORGANIZATION IN THE LANDING PAGE SHOULD MATCHE WITH PERSONS OR ORGANIZATION IN LITERATURE RESULTS SET PAGE");
		page_ChemicalSearchResults.clickOnTabLiterature();
		List<String> pillsAtLitResultSetPage = page_ChemicalSearchResults.tabPatentSearch().getAllPersonsOrganizations();
		Application.waitTime(3);
		boolean blnncheck = TestUtil.validateTwoLists(pillsAtLandingPage, pillsAtResultSetPage);
		if (blnncheck) {
			Application.Logger.addsubStep(LogStatus.PASS, "COMPANY AND PERSONS PILLS IN THE LANDING PAGE IS MATCHING WITH COMPANY AND PERSONS IN THE LITERATURE RESULTS SET PAGE",false);
			Application.Logger.addsubStep(LogStatus.INFO,"LANDING PAGE PILLS: " + pillsAtLandingPage.size() + pillsAtLandingPage,false);
			Application.Logger.addsubStep(LogStatus.INFO, "LITERATURE RESULT SET PAGE PILLS: "+ pillsAtLitResultSetPage.size() + pillsAtLitResultSetPage, false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"COMPANY AND PERSONS PILLS IN THE LANDING PAGE IS NOT MATCHING WITH COMPANY AND PERSONS IN THE LITERATURE RESULTS SET PAGE", true);
			Application.Logger.addsubStep(LogStatus.INFO,"LANDING PAGE PILLS: " + pillsAtLandingPage.size() + pillsAtLandingPage,false);
			Application.Logger.addsubStep(LogStatus.INFO, "LITERATURE RESULT SET PAGE PILLS:: "+ pillsAtLitResultSetPage.size() + pillsAtLitResultSetPage, false);
		}
		Application.Logger.endStep();
	} catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}

/**  CHEMEXP-1568* */
@Test
public boolean vaildateCompanyPeoplePhraseStructureSearch(Controller Application, HashMap<String, String> input) {
	page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
	page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
	String searchText = input.get("searchtext");
	String smiley = input.get("smiles");
	String actStructureLabel,rsSearchPhrase,expStructureLabel="Structure";
	boolean flag = true;
	try {
		Application.Logger.addStep("1.VERIFY PERFORMING THE SEARCH IN LANDING PAGE WITH PERSON/ ORAGNIZATION,STRUCTURE SEARCH AND FREE TEXT TAKES THE USER TO THE RESULTS SET PAGE","USER SHOULD BE TAKEN TO THE RESULTS SET PAGE AFTER THE SUCCESSFUL PERSON/ORGANIZATION,STRUCTURE AND FREE TEXT SEARCH");
		page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.clickOnStructureSearchIcon();
		page_ChemicalSearchLandingPage.setTextSmileyTextBox(smiley);
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.selectStructureRadioButton("Substructure");
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.clickOnPersonIcon();
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.setTextPersonOrOrganization("MILLER");
		page_ChemicalSearchLandingPage.clickOnButtonAdd();				
		page_ChemicalSearchLandingPage.clickOnOrganizationIcon();
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.setTextPersonOrOrganization("MILLER");
		page_ChemicalSearchLandingPage.clickOnButtonAdd();				
		page_ChemicalSearchLandingPage.clickOnPersonIcon();
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.setTextPersonOrOrganization("MILLER" ); 
		page_ChemicalSearchLandingPage.clickOnButtonAdd();
		page_ChemicalSearchLandingPage.clickOnOrganizationIcon();
		Application.waitTime(2);
		page_ChemicalSearchLandingPage.setTextPersonOrOrganization("MILLER");
		page_ChemicalSearchLandingPage.clickOnButtonAdd();				 
		Application.waitTime(2);				
		List<String> pillsAtLandingPage = page_ChemicalSearchLandingPage.getAllPersonsOrganizations();
		page_ChemicalSearchLandingPage.clickOnSearchIcon();
		Application.waitUntilFectchRecordProgressBarToDisappears();			
	 if (page_ChemicalSearchResults.checkIfResultsFound()) {
			Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
		} else {
			throw new Exception("RESULTS NOT DISPLAYED FOR THE CHEMICAL SEARCH");
		}
		Application.Logger.endStep();				
		Application.Logger.addStep("2.VERIFY IF THE ADDED PERSONS OR ORGANIZATION IN THE LANDING PAGE MATCHES WITH PERSONS OR ORGANIZATION IN THE  PATENT RESULTS SET PAGE","PERSONS OR ORGANIZATION IN THE LANDING PAGE SHOULD MATCHE WITH PERSONS OR ORGANIZATION IN PATENT RESULTS SET PAGE");
		List<String> pillsAtResultSetPage = page_ChemicalSearchResults.tabPatentSearch().getAllPersonsOrganizationsStructureSearch();
		Application.waitTime(3);
		boolean blncheck = TestUtil.validateTwoLists(pillsAtLandingPage, pillsAtResultSetPage);
		if (blncheck) {
			Application.Logger.addsubStep(LogStatus.PASS, "COMPANY AND PERSONS PILLS IN THE LANDING PAGE IS MATCHING WITH COMPANY AND PERSONS IN THE PATENT RESULTS SET PAGE",false);
			Application.Logger.addsubStep(LogStatus.INFO,"LANDING PAGE PILLS: " + pillsAtLandingPage.size() + pillsAtLandingPage,false);
			Application.Logger.addsubStep(LogStatus.INFO, "PATENT RESULT SET PAGE PILLS: "+ pillsAtResultSetPage.size() + pillsAtResultSetPage, false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"COMPANY AND PERSONS PILLS IN THE LANDING PAGE IS NOT MATCHING WITH COMPANY AND PERSONS IN THE PATENT RESULTS SET PAGE", true);
			Application.Logger.addsubStep(LogStatus.INFO,"LANDING PAGE PILLS: " + pillsAtLandingPage.size() + pillsAtLandingPage,false);
			Application.Logger.addsubStep(LogStatus.INFO, "PATENT RESULT SET PAGE PILLS:: "+ pillsAtResultSetPage.size() + pillsAtResultSetPage, false);
		}
		Application.Logger.endStep();				
		Application.Logger.addStep("3.VERIFY IF THE FREE TEXT IS DISPLAYED IN THE RS TOP SECTION","FREE TEXT SHOULD BE DISPLAYED IN THE RS TOP SECTION");
		rsSearchPhrase=page_ChemicalSearchResults.getTextSearchBox();
		if (searchText.equalsIgnoreCase(rsSearchPhrase)) {
			Application.Logger.addsubStep(LogStatus.PASS,"FREE TEXT ENTERED IN THE LANDING PAGE MATCHES WITH THE TEXT IN THE RS TOP SECTION",false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"FREE TEXT ENTERED IN THE LANDING PAGE DOESNT MATCHES WITH THE TEXT IN THE RS TOP SECTION : EXPECTED SEARCH PHRASE :\t" +searchText+ "\t ACTUAL SEARCH PHRASE:" +rsSearchPhrase,true);
		}
		Application.Logger.endStep();
		Application.Logger.addStep("4.VERIFY IF THE STRUCTURE PILL IS DISPLAYED IN THE RS BOTTON SECTION","STRUCTURE PILL SHOULD BE DISPLAYED IN THE RS BOTTOM SECTION");
		actStructureLabel=page_ChemicalSearchResults.getTextStructure();
		if (actStructureLabel.trim().equalsIgnoreCase(expStructureLabel.trim())) {
			Application.Logger.addsubStep(LogStatus.PASS,"STRUCTURE PILL IS DISPLAYED IN THE RS BOTTOM SECTION",false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"STRUCTURE PILL IS NOT DISPLAYED IN THE RS BOTTOM SECTION : EXPECTED STRUCTURE PILL LABEL :\t" +expStructureLabel+ "\t ACTUAL STRUCTURE PILL LABEL:" +actStructureLabel,true);
		}
		Application.Logger.endStep();
		
	} catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}


/**  CHEMEXP-1664* */
@Test
public boolean vaildateAnnotationResultSetAndSavedRecord(Controller Application, HashMap<String, String> input) {
	page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
	page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
	page_SavedRecords = new Page_SavedRecords(Application);
	String searchText = input.get("searchtext");
	String annotationMaxText = "If you need to stop reading before you reach the end, Word remembers where you left off - even on another device. "
			+ "Video provides a powerful way to help you prove your point. When you click Online Video, you can paste in the embed code for "
			+ "the video you want to add. You can also type a keyword to search online for the video that best fits your document. To make your "
			+ "document look professionally produced, Word provides header, footer, cover page, and text box designs that complement each other. "
			+ "For example, you can add a matching cover page, header, and sidebar. Click Insert and then choose the elements you want from the "
			+ "different galleries. Themes and styles also help keep your document coordinated. When you click Design and choose a new Theme, "
			+ "the pictures, charts, and SmartArt graphics change to match your new theme. When you apply styles, your headings change to match the new theme. "
			+ "Save time in Word with new buttons that show up where you need them. To change the way a picture fits in your document, click it "
			+ "and a button for layout options appears next to it. When you work on a table, click where you want to add a row or a column, and then click the plus sign. Reading is easier, too, in the new Reading view. "
			+ "You can collapse parts of the document and focus on the text you want. If you need to stop reading before you reach the end, Word remembers where you left off - even on another device. "
			+ "Video provides a powerful way to help you prove your point. When you click Online Video, you can paste in the embed code for the video you want to add. You can also type a keyword to search online for the "
			+ "video that best fits your document. To make your document look professionally produced, Word provides header, footer, cover page, and text box designs that complement each other. For example, you "
			+ "can add a matching cover page, header, and sidebar. Click Insert and then choose the elements you want from the different galleries. Themes and styles also help keep your document coo";
	String expAnnotationErrMsg = "2000 of 2000";
	String annotationText = "create annotation for record";
	String actAnnotationErrMsg,annotationFolderCount;
	boolean flag = true;
	
	try {
		Application.Logger.addStep("1.DELETE ALL THE EXISTING ANNOTATIONS FROM THE ANNOTATIONS RECORDS PAGE","ANNOTATIONS SHOULD BE DELETED SUCCESSFULLY FROM THE ANNOTATIONS RECORDS PAGE");
		page_SavedRecords.clickOnLinkSavedRecords();
		page_SavedRecords.clickOnLinkAnnotationRecords();
		page_SavedRecords.deleteExistingAnnotations();
		Application.Logger.endStep();
		Application.Logger.addStep("2.PERFORM A SEARCH WITH A PHRASE FROM THE LANDIING PAGE","RESULT SET PAGE SHOULD BE DISPLAYED");
		page_ChemicalSearchResults.clickOnChemExpHomePage();
		page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
		page_ChemicalSearchLandingPage.clickOnSearchIcon();
		Application.waitUntilFectchRecordProgressBarToDisappears();
		if (page_ChemicalSearchResults.checkIfResultsFound()) {
			Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
		} else {
			throw new Exception("RESULTS SET IS NOT LOADED FOR THE CHEMICAL SEARCH");
		}
		Application.Logger.endStep();	
	Application.Logger.addStep("3.VERIFY THE ERROR MESSAGE ONCE THE ANNOATION TEXT REACHES 2000 CHARACTERS ","ERROR MESSAGE SHOULD BE DISPLAYED ONCE THE ANNOTATION TEXT REACHES 2000");
	page_ChemicalSearchResults.tabPatentSearch().clickOnAnnotationIcon(1);
	page_ChemicalSearchResults.tabPatentSearch().setTextAnnotationText(annotationMaxText);
	actAnnotationErrMsg=page_ChemicalSearchResults.tabPatentSearch().getTextAnnotationErrorMessage();
	page_ChemicalSearchResults.tabPatentSearch().clickOnAnnotationCloseIcon();
	if (expAnnotationErrMsg.trim().equals(actAnnotationErrMsg.trim())) {
		Application.Logger.addsubStep(LogStatus.PASS,"ERROR MESSAGE IS DISPLAYED ONCE THE ANNOTATION TEXT REACHES 2000 CHARACTERS",false);
	} else {
		Application.Logger.addsubStep(LogStatus.FAIL,"ERROR MESSAGE IS NOT DISPLAYED ONCE THE ANNOTATION TEXT REACHES 2000 CHARACTERS : EXPECTED ERROR MESAGE : \t"
						+ expAnnotationErrMsg + "\t\t ACTUAL ERROR MESSAGE :\t" + actAnnotationErrMsg,true);
	}
	Application.Logger.endStep();
	Application.Logger.addStep("4.VERIFY THE ANNOTATION ADDED FOR A RECORD IN RS IS DISPLAYED IN THE ANNOTATED FOLDER SECTION","ANNOTATION RECORD SHOULD BE DISPLAYED IN ANNOTATED RECORDS SECTION");
	page_ChemicalSearchResults.tabPatentSearch().clickOnAnnotationIcon(3);
	page_ChemicalSearchResults.tabPatentSearch().setTextAnnotationText(annotationText);
	page_ChemicalSearchResults.tabPatentSearch().clickOnButtonSaveAnnotation();
	page_SavedRecords.clickOnLinkSavedRecords();
	Application.waitTime(2);
	page_SavedRecords.clickOnLinkAnnotationRecords();
	annotationFolderCount=page_SavedRecords.getTextAnnotationFolderCount();
	if(annotationFolderCount.contains("1"))
	{
		Application.Logger.addsubStep(LogStatus.PASS,"ANNOTATION RECORD IS AVAILABLE IN THE ANNOTATION FOLDER",false);
	} else {
		Application.Logger.addsubStep(LogStatus.FAIL,"ANNOTATION RECORD IS NOT AVAILABLE IN THE ANNOTATION FOLDER",true);
	}
	Application.Logger.endStep();	
	Application.Logger.addStep("5.VERIFY IF THE RECORD IS REMOVED FROM THE ANNOTATION FOLDER AFTER DELETING THE ANNOTATION","RECORD SHOULD NOT BE AVAILABLE AFTER IN THE ANNOTATION FOLDER AFTER DELETING THE ANNOTATION");
	page_SavedRecords.clickOnAnnotationIcon(1);
	page_SavedRecords.clickOnButtonDeleteAnnotation();
	Application.waitTime(2);
	annotationFolderCount=page_SavedRecords.getTextAnnotationFolderCount();
	if(annotationFolderCount.contains("0"))
	{
		Application.Logger.addsubStep(LogStatus.PASS,"ANNOTATION RECORD IS SUCCESSFULLY REMOVED FROM THE ANNOTATION FOLDER",false);
	} else {
		Application.Logger.addsubStep(LogStatus.FAIL,"ANNOTATION RECORD IS NOT REMOVED FROM THE ANNOTATION FOLDER",true);
	}
	Application.Logger.endStep();
	}catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}


/**  CHEMEXP-1665* */
@Test
public boolean vaildateAnnotationSavedFolderAndSavedRecord(Controller Application, HashMap<String, String> input) {
	page_ChemicalSearchLandingPage = new Page_ChemicalSearchLandingPage(Application);
	page_ChemicalSearchResults = new Page_ChemicalSearchResults(Application);
	page_SavedRecords = new Page_SavedRecords(Application);
	String searchText = input.get("searchtext");
	String singleFolderName = "singleRecordAnnotationFolder";
	String actErrorMessage, expFolderName,annotationFolderCount;
	int folderLength, recordCount;
	String annotationText = "create annotation for record in a folder";
	boolean flag = true;
	try {
		Application.Logger.addStep("1.NAVIGATE TO SAVED FOLDERS PAGE AND DELETE ALL EXISTING FOLDERS AND ANNOTATIONS",
				"FOLDERS AND ANNOTATIONS SHOULD BE DELETED SUCCESSFULLY");
		page_SavedRecords.clickOnLinkSavedRecords();
		page_SavedRecords.deleteExistingFolders();
		page_SavedRecords.clickOnLinkAnnotationRecords();
		page_SavedRecords.deleteExistingAnnotations();
		page_ChemicalSearchResults.clickOnChemExpHomePage();
		Application.Logger.endStep();
		Application.Logger.addStep("2.PERFORM A SEARCH WITH A PHARSE AND VERIFY THE RS PAGE",
				"RS PAGE SHOULD BE DISPLAYED AFTER THE SUCCESSFUL SEARCH");
		page_ChemicalSearchLandingPage.setTextSearchTextBox(searchText);
		page_ChemicalSearchLandingPage.clickOnSearchIcon();
		Application.waitUntilFectchRecordProgressBarToDisappears();
		if (page_ChemicalSearchResults.checkIfResultsFound()) {
			Application.Logger.addsubStep(LogStatus.PASS, "RESULT SET IS DISPLAYED FOR THE TEXT SEARCH", false);
		} else {
			throw new Exception("Results set is not loaded for the chemical search");
		}
		Application.Logger.endStep();
		Application.Logger.addStep("3.VERIFY IF THE NEW FOLDER IS CREATED SUCCESSFULLY IN RESULTS SET PAGE",
				"FOLDER SHOULD BE CREATED SUCCESSFULLY");
		page_ChemicalSearchResults.clickOnSaveIcon();
		Application.waitTime(2);
		page_ChemicalSearchResults.clickOnLinkCreateNewFolder();
		Application.waitTime(2);
		page_ChemicalSearchResults.setTextFolderName(singleFolderName);
		page_ChemicalSearchResults.clickOnLinkCreate();
		Application.waitTime(2);
		page_ChemicalSearchResults.clickOnFolderNameCheckBox(singleFolderName);
		Application.waitTime(2);
		expFolderName = page_ChemicalSearchResults.getTextFolderName(singleFolderName);
		if (expFolderName.equals(singleFolderName)) {
			Application.Logger.addsubStep(LogStatus.PASS,
					"NEW FOLDER IS CREATED SUCCESSFULLY WITH THE NAME :" + singleFolderName, false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,
					"NEW FOLDER IS NOT CREATED SUCCESSFULLY WITH THE NAME :" + singleFolderName, true);
		}
		Application.Logger.endStep();
		
		Application.Logger.addStep("4.VERIFY THE NEWLY CREATED FOLDERS ARE DISPLAYED IN SAVED RECORDS PAGE",
				"NEWLY CREATED FOLDERS WITH RECORDS SHOULD BE DISPLAYED IN SAVED RECORDS PAGE");
		Application.waitTime(2);
		page_SavedRecords.clickOnLinkSavedRecords();
		Application.waitUntilFectchRecordProgressBarToDisappears();
		page_SavedRecords.clickOnFolderName(3);
		Application.waitTime(2);
		Application.waitUntilFectchRecordProgressBarToDisappears();
		recordCount = page_SavedRecords.getFolderRecordCount();
		Application.waitTime(2);
		if (recordCount == 1) {
			Application.Logger.addsubStep(LogStatus.PASS,
					"" + recordCount + " RECORD IS SAVED IN THE FOLDER : " + singleFolderName, false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,
					"" + recordCount + " RECORD IS NOT SAVED IN THE FOLDER : " + singleFolderName, true);
		}
		Application.Logger.endStep();
		Application.Logger.addStep("5.VERIFY THE ANNOTATION ADDED FOR A RECORD IN A FOLDER IS DISPLAYED IN THE ANNOTATED FOLDER SECTION","ANNOTATION RECORD SHOULD BE DISPLAYED IN ANNOTATED RECORDS SECTION");
		page_SavedRecords.clickOnFolderAnnotationIcon(1);
		page_SavedRecords.setTextAnnotationText(annotationText);
		page_SavedRecords.clickOnButtonSaveAnnotation();	
		Application.Logger.addsubStep(LogStatus.INFO,"ANNOTATION IS SUCCESSFULLY SAVED IN THE FOLDER : " + singleFolderName, false);	
		Application.Logger.endStep();
		Application.Logger.addStep("6.VERIFY IF THE RECORD IS REMOVED FROM THE ANNOTATION FOLDER AFTER DELETING THE ANNOTATION","RECORD SHOULD NOT BE AVAILABLE AFTER IN THE ANNOTATION FOLDER AFTER DELETING THE ANNOTATION");
		Application.waitTime(2);
		page_SavedRecords.clickOnLinkAnnotationRecords();
		Application.waitTime(2);
		page_SavedRecords.clickOnAnnotationIcon(1);
		page_SavedRecords.clickOnButtonDeleteAnnotation();
		Application.waitTime(2);
		annotationFolderCount=page_SavedRecords.getTextAnnotationFolderCount();
		if(annotationFolderCount.contains("0"))
		{
			Application.Logger.addsubStep(LogStatus.PASS,"ANNOTATION RECORD IS SUCCESSFULLY REMOVED FROM THE ANNOTATION FOLDER",false);
		} else {
			Application.Logger.addsubStep(LogStatus.FAIL,"ANNOTATION RECORD IS NOT REMOVED FROM THE ANNOTATION FOLDER",true);
		}
		Application.Logger.endStep();
	} catch (Exception e) {
		Application.Logger.addException(e.getMessage());
		return flag = false;
	}
	return flag;
}

}







