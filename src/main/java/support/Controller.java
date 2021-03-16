package support;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.PageLoadStrategy;


import com.paulhammant.ngwebdriver.NgWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author Anand/Rashmi
 *
 */
public class Controller {
/*	 public static WebDriver driver = null;
	public Properties properties;
	public String browser;
	String WorkingDir;
	public String ErrorMsg = "";
	public utils.Logger Logger;
	public String appurl;
	public String toolUrl;
	public String LoginUser = "";
	public String LoginPass = "";
	public Controller controller;
	public static String mainWindowHandleID;
	public int themescapeloadtime;

	@FindBy(css="div[class='loader-circle']")
	WebElement lodingcircle;
	@FindBy(css="iframe[id='frame']")
	protected WebElement element_iframe;
	@FindBy(css="#progressIndicator")
	WebElement element_ReskinningLodingcircle;
	
	@FindBy(xpath="//div[@id='progressIndicator' and contains(@style,'block')]/div")
	WebElement element_DottedLodingcircle;
	
	@FindBy(xpath = ".//*[@title='Your update is in progress.']")
	private WebElement updateProgress;
	
	@FindBy(xpath="//img[@src='img/loading2.gif")
    WebElement toolsloadingcircle;
	
	@FindBy(xpath="//*[contains(@class,'loader-wheel')]")
    WebElement element_SBLodingcircle; */
	
	public static WebDriver driver = null;
	public Properties properties;
	public String browser;
	String WorkingDir;
	public String ErrorMsg = "";
	public utils.Logger Logger;
	public String appurl;
	public String toolUrl;
	public String LoginUser = "";
	public String LoginPass = "";
	public Controller controller;
	public static String mainWindowHandleID;

	@FindBy(css="div[class='loader-circle']")
	WebElement lodingcircle;
	@FindBy(css="iframe[id='frame']")
	protected WebElement element_iframe;
	@FindBy(css="#progressIndicator")
	WebElement element_ReskinningLodingcircle;
	
	@FindBy(xpath="//div[@id='progressIndicator' and contains(@style,'block')]/div")
	WebElement element_DottedLodingcircle;
	
	@FindBy(xpath = ".//*[@title='Your update is in progress.']")
	private WebElement updateProgress;
	
	@FindBy(xpath="//img[@src='img/loading2.gif")
    WebElement toolsloadingcircle;
	
	@FindBy(xpath="//*[@id='chemexp']//section[2]//h1")
    WebElement element_FetchRecord;


	public Controller(Controller controller) {
		this.setController(controller);
		this.driver = controller.driver;
		PageFactory.initElements(driver, this);

	}

	private void setController(Controller controller) {
		this.controller = controller;
	}




	public Controller(String browser) throws IOException {
		properties = new Properties();
		FileInputStream fn = new FileInputStream("./src/test/resources/config.properties");
		properties.load(fn);
		this.browser = browser;
		WorkingDir = System.getProperty("user.dir");

		launchBrowser();
		Logger = new utils.Logger(driver, browser);
		Logger.setWebDriver(this);
		long implicitwait = new Long(properties.getProperty("implicitwait"));
		long pageloadtime = new Long(properties.getProperty("PageLoading"));
	//	themescapeloadtime = new Integer(properties.getProperty("ThemeScapeLoad"));
		
		driver.manage().timeouts().implicitlyWait(implicitwait, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(pageloadtime, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		try {
			appurl = properties.getProperty("url");
			toolUrl = properties.getProperty("ToolsUrl");
		} catch (Exception e) {
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
		}
	}

	/**
	 * 
	 * @param window -- Name of the Window
	 * @param strURLContains -- URL contains string differentiates other windows with our required window to switch
	 * @param currentWindowHandleID -- Main Window Handle ID
	 * @return boolean true if given window exist boolean false if given window doesn't exist by closing all windows except main window which we passed as a parameter 
	 * @throws Exception
	 */

	public boolean switchToGivenWindow(String window, String strURLContains,String currentWindowHandleID) throws Exception {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			boolean status = false;
			for (String handleID : windowHandles) {
				driver.switchTo().window(handleID);
				if (driver.getCurrentUrl().toLowerCase().contains(strURLContains.toLowerCase())) {
					status = true;
					break;
				}
			}
			if(!status) 
				closeOtherWindows(currentWindowHandleID);
			return status;
		} catch (Exception ex) {
			throw new Exception("Switch TO Given window is not working" + ex);
		}
	}

	public void closeOtherWindows(String currentWindowHandleID) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handleID : windowHandles) {
			if (!handleID.equalsIgnoreCase(currentWindowHandleID)) {
				driver.switchTo().window(handleID);
				driver.close();
			}
		}
		driver.switchTo().window(currentWindowHandleID);
	}

	public void switchToOtherWindow(String currentWindowHandleID) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handleID : windowHandles) {
			if (!handleID.equals(currentWindowHandleID)) {
				driver.switchTo().window(handleID);
				break;
			}
		}
	}

	public boolean isElementEnabled(WebElement ele) throws Exception {
		try {
			return ele.isEnabled();
		} catch (Exception e) {
			throw new Exception("isElementEnabled is not working" + e);

		}
	}

	public void hoverOnWebelement(WebElement ele) throws Exception {
		waitUntilElementIsDisplayed(ele);
		Actions action = new Actions(driver);
		action.moveToElement(ele).click().perform();
	}


	public void jsScrollToElement(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public void waitUntilReskiningProgressBarToDisappears() throws Exception {
		try {
			waitTime(2);
			waitUntilElementIsNotDisplayed(element_ReskinningLodingcircle);
			waitForPageLoad();
		} catch (Exception e) {
			throw new Exception("waitUntilProgressBarToDisappears is not working.." + e);
		}
	}
	
	public void waitUntilFectchRecordProgressBarToDisappears() throws Exception {
		try {
			waitTime(2);
			waitUntilElementIsNotDisplayed(element_FetchRecord);
			waitForPageLoad();
		} catch (Exception e) {
			throw new Exception("waitUntilDottedProgressBarToDisappears is not working.." + e);
		}
	}



	//Added by Mrityunjaya

	public boolean isElementSelected(WebElement ele) throws Exception {
		boolean status;
		try {
			return ele.isSelected();
		} catch (Exception e) {
			status = false;
		}
		return status;
	}


	public void launchBrowser() {
		if ((browser).equals("CH")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			System.setProperty("java.awt.headless", "false");
			WebDriverManager.chromedriver().clearCache();
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
		//	options.addArguments("--headless", "--start-maximized", "--no-sandbox", "--incognito", "--lang=en-US");
			/*options.addArguments("--start-maximized");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-default-apps");
	        options.addArguments("--ignore-certificate-errors");
	        options.addArguments("--disable-extensions-file-access-check");*/
//	        options.addArguments("--dns-prefetch-disable");
          
          
            options.addArguments("--disable-gpu");
          	options.addArguments("disable-infobars"); // disabling infobars
			//options.addArguments("--disable-extensions"); 
          	options.addArguments("--disable-dev-shm-usage");
			
			//Below code is for downloading files into specific directory
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "downloads");
//			prefs.put("download.default_directory",System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
			options.setExperimentalOption("prefs", prefs);
			
			driver = new ChromeDriver(options); 
      
			NgWebDriver ngDriver = new NgWebDriver((JavascriptExecutor) driver);
			ngDriver.waitForAngularRequestsToFinish();
		} else
			throw new RuntimeException("Chrome Browser type unsupported");
	}


	public void clear(WebElement ele) throws Exception {
		try {
			ele.clear();
		} catch (Exception e) {
			throw new Exception("clear the textbox is not working for :: " + ele + " " + e);
		}
	}


	

//	public void setText(WebElement ele, String value,String elementDescription) throws Exception {
//		try {
//			this.clear(ele);
//			ele.sendKeys(value);
//			this.waitTime(1);
//			Logger.addsubStep(LogStatus.INFO, "Entering text: "+value+" to textbox: "+elementDescription+" is Successful;", false);
//		} catch (Exception e) {
//			throw new Exception("setText is not working for :: " + ele + " " + e);
//		}
//	}

	public void waitUntilElementClickable(WebElement ele) throws Exception {
		try {
			new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {
			throw new Exception("wait Until Element is Clickable:: " + ele + " " + e);
		}
	}
	
	/*public void waitUntilElementGetsEnabled(WebElement element,boolean blnStatus) throws Exception {
		try {
			(new WebDriverWait(driver, 50)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					try {
						if(blnStatus)
							return Boolean.valueOf(element != null && element.isDisplayed());
						else
							return Boolean.valueOf(element != null && !element.isDisplayed());
					} catch (Exception e) {
						return Boolean.valueOf(false);
					}
				}
			});
		} catch (Exception e) {
			throw new Exception("waitUntilElementIsDisplayed is not working.. :: " + element + " " + e);
		}
	}*/
	
	public void click(WebElement ele) throws Exception {
		try {
			new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(ele)).click();
		} catch (Exception e) {
			throw new Exception("selenium click is not  working on:: " + ele + " " + e);
		}
	}

	public void jsClick(WebElement ele) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			throw new Exception("jsClick is not  working on:: " + ele + " " + e);
		}
	}

//	public void jsClick(WebElement ele,String elementDescription) throws Exception {
//		try {
//			WebDriverWait wait = new WebDriverWait(driver, 50);
//			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
//			JavascriptExecutor js = (JavascriptExecutor) driver;
//			js.executeScript("arguments[0].click();", element);
//			controller.Logger.addsubStep(LogStatus.INFO, "Click on element: "+elementDescription+" is Successful;", false);
//		} catch (Exception e) {
//			throw new Exception("jsClick is not  working on:: " + ele + " " + e);
//		}
//	}

	public void waitUntilElementIsDisplayed(final WebElement element) throws Exception {
		try {
			(new WebDriverWait(driver, 50)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					try {
						return Boolean.valueOf(element != null && element.isDisplayed());
					} catch (Exception e) {
						return Boolean.valueOf(false);
					}
				}
			});
		} catch (Exception e) {
			throw new Exception("waitUntilElementIsDisplayed is not working.. :: " + element + " " + e);
		}

	}

	public void waitUntilElementIsNotDisplayed(final WebElement element) throws Exception {
		try {
			(new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					try {
						return Boolean.valueOf(element == null || !element.isDisplayed());
					} catch (Exception e) {
						return Boolean.valueOf(true);
					}
				}
			});
		} catch (Exception e) {
			throw new Exception("waitUntilElementIsNotDisplayed is not working.. :: " + element + " " + e);
		}
	}

	public void waitTime(final int secs) throws InterruptedException {
		Thread.sleep(secs * 1000);
	}

	public void waitUntilProgressBarToDisappears() throws Exception {
		try {
			waitTime(2);
			waitUntilElementIsNotDisplayed(element_FetchRecord);
			waitForPageLoad();
		} catch (Exception e) {
			throw new Exception("waitUntilProgressBarToDisappears is not working.." + e);
		}
	}
	
	public void waitUntilProgressBarToAppear() throws Exception {
		try {
			waitTime(2);
			waitUntilElementIsDisplayed(lodingcircle);
			waitForPageLoad();
		} catch (Exception e) {
			throw new Exception("waitUntilProgressBarToAppear is not working.." + e);
		}
	}
	
	public void waitUntilUpdateResultCountProgressBarToDisappears() throws Exception {
		try {
			waitTime(2);
			waitUntilElementIsNotDisplayed(updateProgress);
			waitForPageLoad();
		} catch (Exception e) {
			throw new Exception("waitUntilUpdateResultCountProgressBarToDisappears is not working.." + e);
		}
	}
	
	public void waitUntilToolsUrlProgressBarToDisappears() throws Exception {
        try {
               waitTime(5);
               waitUntilElementIsNotDisplayed(toolsloadingcircle);
               waitForPageLoad();
        } catch (Exception e) {
               throw new Exception("waitUntilToolsUrlProgressBarToDisappears is not working.." + e);
        }
  }


	public void switchToIFrame(WebElement ele_frame) throws Exception {
		try {
			driver.switchTo().defaultContent();
			waitUntilElementIsDisplayed(ele_frame);
			driver.switchTo().frame(ele_frame);
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			throw new Exception("ele_frame is not working.." + e);
		}
	}

	public boolean isElementDisplayed(WebElement ele) throws Exception {
		boolean status;
		try {
			status = ele.isDisplayed();
		} catch (Exception e) {
			status = false;
		}
		return status;
	}


	public String getText(WebElement ele) throws Exception {
		try {
			return ele.getText();
		} catch (Exception e) {
			throw new Exception("getText is not working" + e);

		}
	}
	
	public String getJSText(WebElement ele) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return  js.executeScript("return arguments[0].value", ele).toString().trim();
		} catch (Exception e) {
			return "";
		}
	}
	
	public String getJSValue(WebElement ele) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return  js.executeScript("return arguments[0].innerText", ele).toString().trim();
		} catch (Exception e) {
			return "";
		}
	}

	public  void waitForPageLoad() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			Thread.sleep(5);
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(expectation);
		} catch (Exception e) {
			throw new RuntimeException("Timeout waiting for Page Load Request to complete. ", e);
		}
	}
	
	public void waitForPageLoad(int time) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
			}
		};
		try {
			Thread.sleep(5);
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(expectation);
		} catch (Exception e) {
			throw new RuntimeException(time + " Sec Timeout waiting for Page Load Request to complete. ", e);
		}
	}


	public List<String> getListWebElementText(List<WebElement> WebElementsList) throws Exception {
		List<String> getData = new ArrayList<String>();
		for (WebElement element : WebElementsList) {
			String text = getText(element);
			getData.add(text.trim());
		}
		return getData;
	}
	
	public List<String> getListWebElementTextWithUpperCase(List<WebElement> WebElementsList) throws Exception {
		List<String> getData = new ArrayList<String>();
		for (WebElement element : WebElementsList) {
			String text = getText(element);
			getData.add(text.trim().toUpperCase());
		}
		return getData;
	}
	
	public List<String> getListWebElementJSText(List<WebElement> WebElementsList) throws Exception {
		List<String> getData = new ArrayList<String>();
		for (WebElement element : WebElementsList) {
			String text = element.getCssValue("innerText");
			getData.add(text.trim().toUpperCase());
		}
		return getData;
		
	}

	public void waitUntilWindowCountAsGiven(int intExpectedWindowsCount) throws Exception {
        try {
            new WebDriverWait(driver,30).until(ExpectedConditions.numberOfWindowsToBe(intExpectedWindowsCount));
        } catch (Exception e) {
            throw new Exception("waitUntilWindowCountAsGiven is not working.. "+ e);
        }
    }

	public String getElementAttribute(WebElement element,String strAttribute) {
		String eleAttribute;
		try {
			eleAttribute = element.getAttribute(strAttribute);
		} catch (Exception e) {
			eleAttribute = "";
		}
		return eleAttribute;
	}

	public boolean isAlertPresent() {
		boolean foundAlert = false;
		try {
			new WebDriverWait(driver, 20).until(ExpectedConditions.alertIsPresent());
			foundAlert = true;
		} catch (Exception e) {
			foundAlert = false;
		}
		return foundAlert;
	}

	public void handleAlert(boolean accept) {
		if(accept) {
			driver.switchTo().alert().accept();
		}else {
			driver.switchTo().alert().dismiss();
		}
	}

    public String getAlertMsg() throws Exception {
        String alertMsg="";
        try {
            alertMsg= driver.switchTo().alert().getText();
            return alertMsg;
            
        } catch (Exception e) {
            throw new Exception("Unable to find alert Popup.." + e);
        }
    //    return alertMsg;
    }
    
    public void selectDropDownByValue(WebElement eleSelect,String strValue) throws Exception{
    	try {
    		waitUntilElementIsDisplayed(eleSelect);
	    	Select dropdown = new Select(eleSelect);
	    	dropdown.selectByValue(strValue);
    	}
    	catch(Exception ex) {
    		throw new Exception("select Drop down by value is not working "+ex);
    	}
    }
    
    public void selectDropDownByIndex(WebElement eleSelect, int indexValue ) throws Exception {
		try {
			 waitUntilElementIsDisplayed(eleSelect);
			 Select sel = new Select(eleSelect);
			 sel.selectByIndex(indexValue);
		} catch (Exception e) {
			throw new Exception("select Drop down by Index is not working "+ e);
		}

	}
    
    public void selectDropDownByVisibleText(WebElement eleSelect,String strText) throws Exception{
	    try {
	    	waitUntilElementIsDisplayed(eleSelect);
	    	Select dropdown = new Select(eleSelect);
	    	dropdown.selectByVisibleText(strText);
	    }
		catch(Exception ex) {
			throw new Exception("select Drop down by visible text is not working "+ex);
		}
    }
    
	public void jsClickforReskinningElements(WebElement ele) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			throw new Exception("jsClickforReskiningElements is not  working on:: " + ele + " " + e);
		}

	}

	public String getSelectedDropdownValue(WebElement webelement) throws Exception {
		String selectedText;
		try {
			Select dropdownElement = new Select(webelement);
			selectedText = dropdownElement.getFirstSelectedOption().getText();
		} catch (Exception e) {
			selectedText = null;
		}
		return selectedText;
	}
	
	public List<WebElement> getDropdownOptions(WebElement selectTagElement){
		List<WebElement> list_Options;
		try {
			Select dropdownElement = new Select(selectTagElement);
			list_Options = dropdownElement.getOptions();
		} catch (Exception e) {
			list_Options = null;
		}
		return list_Options;
	}
	
	public void isFrameToBeAvailableAndSwitchToIt(WebElement frameLocator) throws Exception{
		try {
			driver.switchTo().defaultContent();
			new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		} catch (Exception e) {
			driver.switchTo().defaultContent();
		}
	}
	
	public int getOpenWindowCount() throws Exception {
        try {
            Set<String> handles=driver.getWindowHandles();
            System.out.println("window count"+handles.size());
            return handles.size();
           
        } catch (Exception e) {
            throw new Exception("getOpenWindowCount is not working" + e);
        }
    }
	
	public void navigateTO(String URL) throws Exception {
		driver.navigate().refresh();
		driver.get(URL);
		waitForPageLoad();
	}

	public WebElement waitUntilElementIsAvailable(By locator) throws Exception {
		try {
			WebElement ele = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
			return ele;
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			throw new Exception("WebElement  is not Found .... locator Details ::" + locator + e);
		}

	}
	
	public void switchToIFrameWithoutDefault(WebElement ele_frame) throws Exception {
		try {
			waitUntilElementIsDisplayed(ele_frame);
			driver.switchTo().frame(ele_frame);
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			throw new Exception("ele_frame is not working.." + e);
		}
	}
	
	public int generateRandomNumbersWithInRange(int Range)
	{
		Random random = new Random();
		if (Range <= 1)
			return Range;
		else
			return random.nextInt(Range);
	}

	public List<WebElement> waitUntilElementIsVisibility(By locator) throws Exception {
	try {
		List<WebElement> ele = new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		return ele;
	} catch (Exception e) {
		driver.switchTo().defaultContent();
		throw new Exception("WebElement  is not Found .... locator Details ::" + locator + e);
	}
	}
	
	public List<WebElement> waitUntilElementIsVisibility(List<WebElement> WebElements) throws Exception {
		try {
			List<WebElement> ele = new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOfAllElements(WebElements));
			return ele;
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			throw new Exception("WebElement  is not Found .... locator Details ::" + WebElements + e);
		}
	}
	
	public void waitUntilSBProgressBarToDisappears() throws Exception {
		try {
			waitTime(1);
			waitUntilElementIsNotDisplayed(element_FetchRecord);
			waitForPageLoad();
		} catch (Exception e) {
			throw new Exception("waitUntilSBProgressBarToDisappears is not working.." + e);
		}
	}
	
	public String generateRandomString(int targetStringLength) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < targetStringLength) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	public String generateRandomStringWithInRange(int targetStringLength) {
		String generatedString = RandomStringUtils.randomAlphanumeric(0, targetStringLength).toString().trim(); 	
		return generatedString;
	}
	
	public int getCountOfHighlightedGivenText(WebElement element,String word)
	{
		try
		{
			List<WebElement> elements;	
			elements = element.findElements(By.xpath(".//span[contains(@id,'highlight_"+word+"')]"));
			return elements.size();
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	public int getCountOfOccurenceText(WebElement element,String word)
	{
		try
		{
			int i=0;
			if(word.contains("*")||word.contains("?")||word.contains(".")||word.contains("^")||word.contains("$")||word.contains("+"))
				word = word + "\\b";
			String text = element.getText();
			Pattern p = Pattern.compile(word+" ", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher( text );
			while (m.find()) {
				i++;
			}
			return i;
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	
	public void waitUntilForAlertPresent() throws Exception {
		try {
			waitTime(2);
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.alertIsPresent());
			waitForPageLoad();
		} catch (Exception e) {
			throw new Exception("waitUntilForAlertPresent is not working.." + e);
		}
	}
	
	public void setText(WebElement ele, String value) throws Exception {     
		try {                                                                                                                                                             
			waitUntilElementIsDisplayed(ele);                                                     	                                                                               
			this.clear(ele);                                                      
			ele.sendKeys(value);                                                  
			this.waitTime(3);                     // Added now                    
		} catch (Exception e) {                                                
			throw new Exception("setText is not working for :: " + ele + " " + e);
		}                                                                      
	}    
	
	public void closePage() {
		driver.close();
		driver.switchTo().window(mainWindowHandleID);
	}
	
	public void waitUntilForIsDisplayedAndPresent(WebElement element) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 50);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			throw new Exception("waitUntilForIsDisplayedAndPresent is not working.." + e);
		}
	}

}

