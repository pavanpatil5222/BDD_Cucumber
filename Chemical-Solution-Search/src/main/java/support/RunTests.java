package support;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.Page_Login;
import utils.TestUtil;
import utils.Xls_Reader;

/**
 * 
 * @author Anand/Rashmi
 *
 */

@SuppressWarnings("unused")
public class RunTests {

	 Controller Application = null;
	String Path = "";
	String browser = "";
	Object result;
	String SystemHostName = "";
	String SystemUserName = "";
	String LoginUI = "", LoginPwd = "";
	static Set<String> Tests = new HashSet<String>();
	boolean first = false;
	boolean rerun;
	boolean flag = true;
	String terminatedTime;
	public static int valCount = 0;

	/*@BeforeSuite
	public void deleteCoockies() {
		String username = "";
		try {
			String WorkingDir = System.getProperty("user.dir");
			String Path = WorkingDir + "\\historypath\\";
			DeleteAllBrowsingHistory(Path, username);
			DeleteAllSystemHistoryAndKillBrowser(Path, username);
		} catch (Exception e) {
			System.out.println("deleteCoockies is not working....");
		}

	}*/

	@Parameters({ "browser", "excelPath" })
	@BeforeTest()
	public void setUp(String browser, String xlpath) throws Exception {
		Path = xlpath;
		this.browser = browser;
		Application = new Controller(browser);
	}

	/**
	 * 
	 * this will take more time for delete
	 */
	public static void DeleteAllBrowsingHistory(String path, String username) {
		try {
			ProcessBuilder pb;
			if (username.contains("DIWin"))// AWS
				pb = new ProcessBuilder("cmd", "/c", "BrowserHistory.cmd");
			else
				pb = new ProcessBuilder("cmd", "/c", "BrowserHistoryLocal.cmd");
			File dir = new File(path);
			pb.directory(dir);
			Process p = pb.start();
			Thread.sleep(10000);
			p.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void DeleteAllSystemHistoryAndKillBrowser(String path, String username) {
		try {
			ProcessBuilder pb;
			if (username.contains("DIWin"))
				pb = new ProcessBuilder("cmd", "/c", "temp.bat");
			else
				pb = new ProcessBuilder("cmd", "/c", "tempLocal.bat");
			File dir = new File(path);
			pb.directory(dir);
			Process p = pb.start();
			Thread.sleep(10000);
			p.destroy();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		if (!(Application.driver == null)) {
			Application.driver.quit();
		}

	}

	@SuppressWarnings("static-access")
	@Test
	public void run() {
		try {
			String keyword;
			keyword = "Y";
			// boolean EveryTimeLogin = true;
			// System.out.println("Excel path::" + Path);
			Xls_Reader xls = new Xls_Reader(Path);
			HashMap<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>();
			HashMap<String, String> TestData = null;
			String ModuleName = "";
			String[][] Modules = xls.getAllData("Module");
			for (int Module = 1; Module < Modules.length; Module++) {
				if (Modules[Module][2].equalsIgnoreCase("Y")) {
					ModuleName = Modules[Module][0].trim();
					System.out.println("ModuleName::" + ModuleName);
					if (!xls.isSheetExist(ModuleName)) {
						TestUtil.reportDataSetResult(xls, "Module", Module + 1, "Execution", "Sheet not exist");
						continue;
					}
					String[][] TestCases = xls.getAllData(ModuleName);
					for (int Test = 1; Test < TestCases.length; Test++) {
						if (TestCases[Test][4].trim().equalsIgnoreCase(keyword)) {
							TestData = new HashMap<String, String>();
							for (int col = 0; col < TestCases[Test].length; col++)
								TestData.put(TestCases[0][col], TestCases[Test][col]);
							if (Tests.isEmpty() || !Tests.contains(TestData.get("TestCase") + browser))
								Tests.add(TestData.get("TestCase") + browser);
							else
								continue;
							Method methodToRun = null;
							try {
								Object obj;
								Class<?> classobject;
								try {

									/*classobject = Class.forName("test."
											+ ModuleName.trim().concat(".").concat(TestData.get("TestSuite").trim()));*/
									classobject = Class.forName(
											ModuleName.trim().concat(".").concat(TestData.get("TestSuite").trim()));

									obj = classobject.newInstance();
								} catch (Exception e) {
									System.out.println(e);
									TestUtil.reportDataSetResult(xls, ModuleName, Test + 1, "Results",
											"Class not implemented/checked in");
									TestUtil.reportDataSetResult(xls, ModuleName, Test + 1, "Runmode",
											"Class Not found");
									continue;
								}
								Method[] allMethods = classobject.getDeclaredMethods();
								boolean methodfound = false;
								for (Method m : allMethods) {
									if (m.getName().trim().equals(TestData.get("TestCase").trim())) {
										methodToRun = m;
										methodfound = true;
									}

								}
								if (!methodfound) {
									TestUtil.reportDataSetResult(xls, ModuleName, Test + 1, "Results",
											"Method not implemented/checked in");
									TestUtil.reportDataSetResult(xls, ModuleName, Test + 1, "Runmode",
											"Method Not found");
									continue;

								}

								try {
									methodToRun.setAccessible(true);
									Type[] pType = methodToRun.getGenericParameterTypes();

									Application.Logger.Parent = Application.Logger.extent
											.startTest(get(TestData.get("TSID")), TestData.get("Description"));
									Application.Logger.Parent.assignCategory(ModuleName);
									// Application.Logger.Parent.assignCategory(browser);
									try {
										Application.Logger.addStep("prereq:Login in to Thomson Innovation", "");
										if (Application.driver == null) {
											Application.launchBrowser();
											Application.Logger.setWebDriver(Application);
											Application.driver.manage().window().maximize();
										}
										Application.driver.get(Application.appurl);

										LoginUI = TestData.get("arg1");
										LoginPwd = TestData.get("arg2");

										Application.LoginUser = LoginUI;
										Application.LoginPass = LoginPwd;

										Page_Login page_login = new Page_Login(Application);
										page_login.loginToCS(LoginUI, LoginPwd);
										Application.Logger.endStep();

										Application.ErrorMsg = "";
										Application.Logger.status = true;
										result = methodToRun.invoke(obj, Application, TestData);
										valCount++;
									} catch (Exception e) {
										Application.Logger.addsubStep(LogStatus.FAIL, "" + e.getMessage(), false);
										Application.Logger.endStep();
										result = false;
										valCount++;
									}

									if (valCount == 10) {
										// deleteCoockies();
										valCount = 0;
									}

									if (!Application.Logger.status)
										result = false;
									System.out.format("%s() returned %b%n", methodToRun.getName(), (Boolean) result);

									if (!Application.Logger.Logger.getRunStatus().equals(LogStatus.PASS)) {
										if (!Application.Logger.Logger.getRunStatus().equals(LogStatus.WARNING))
											if (!Application.Logger.Logger.getRunStatus().equals(LogStatus.UNKNOWN))
												result = false;
									}
									if (result.equals(false)) {
										System.out.println("Testcase Failed ");
									}
									Application.Logger.extent.endTest(Application.Logger.Parent);
									Application.Logger.extent.flush();
									TestUtil.reportDataSetResult(xls, ModuleName, Test + 1, "Results",
											(Boolean) !flag ? "Terminated" + "," + terminatedTime
													: (Boolean) result ? "Pass" : "Fail:" + Application.ErrorMsg);
									TestUtil.reportDataSetResult(xls, ModuleName, Test + 1, "Runmode",
											(Boolean) result ? keyword : "Rerun");
									Tests.remove(TestData.get("TestCase") + browser);
									flag = true;
								} catch (Exception e) {
									Application.Logger.addsubStep(LogStatus.FAIL, e.getMessage(), false);
								}
								killBrowser();

							} catch (Exception x) {
								Application.Logger.Logger.log(LogStatus.ERROR, String.format(
										"Invocation of %s failed: %s%n", methodToRun.getName(), x.getMessage()));
								Application.Logger.extent.endTest(Application.Logger.Parent);
								Application.Logger.extent.flush();

								TestUtil.reportDataSetResult(xls, ModuleName, Test + 1, "Results",
										(Boolean) result ? "Pass" : "Fail");
								TestUtil.reportDataSetResult(xls, ModuleName, Test + 1, "Runmode",
										(Boolean) result ? keyword : "Rerun");
								Tests.remove(TestData.get("TestCase") + browser);

								System.out.println(String.format("Invocation of %s failed: %s%n", methodToRun.getName(),
										x.getMessage()));
								killBrowser();

							}
							if (result == null)
								result = false;
							continue;
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public void killBrowser() {
		Application.driver.quit();
		Application.driver = null;
	}

	private String get(String ids) {
		String url = "http://ent.jira.int.thomsonreuters.com/browse/";
		String value = "";
		if (ids.endsWith("||"))
			ids = ids.substring(0, ids.length() - 2);

		if (ids.contains("||")) {
			String[] issues = ids.split("\\|\\|");
			for (String string : issues) {
				value = value + "<a href='" + url + "" + string + "'>" + string + "</a>||";
			}
			value = value.substring(0, value.length() - 2);
			System.out.println(value);
		} else {
			value = "<a href='" + url + "" + ids + "'>" + ids + "</a>";
		}
		return value;
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

}
