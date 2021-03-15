package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.mail.BodyPart;
import javax.mail.Folder;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import support.Controller;
/**
 * 
 * @author Anand/Rashmi
 *
 */
public class TestUtil extends Controller{
	static Folder inbox;
	static BodyPart p;
	
	public TestUtil(Controller controller) {
		super(controller);
		PageFactory.initElements(driver, this);
	}

	// finds if the test suite is runnable
	public static boolean isSuiteRunnable(Xls_Reader xls, String suiteName) {
		boolean isExecutable = false;
		for (int i = 2; i <= xls.getRowCount("Test Suite"); i++) {
			if (xls.getCellData("Test Suite", "TSID", i).equalsIgnoreCase(suiteName)) {
				if (xls.getCellData("Test Suite", "Runmode", i).equalsIgnoreCase("Y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}

		}
		xls = null; // release memory
		return isExecutable;

	}

	// returns true if runmode of the test is equal to Y
	public static boolean isTestCaseRunnable(Xls_Reader xls, String testCaseName) {
		boolean isExecutable = false;
		for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
			if (xls.getCellData("Test Cases", "TCID", i).equalsIgnoreCase(testCaseName)) {
				if (xls.getCellData("Test Cases", "Runmode", i).equalsIgnoreCase("Y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}
		}

		return isExecutable;

	}

	// return the test data from a test in a 2 dim array
	public static Object[][] getData(Xls_Reader xls, String testCaseName) {
		// if the sheet is not present
		if (!xls.isSheetExist(testCaseName)) {
			xls = null;
			return new Object[1][0];
		}

		int rows = xls.getRowCount(testCaseName);
		int cols = xls.getColumnCount(testCaseName);
		// System.out.println("Rows are -- "+ rows);
		// System.out.println("Cols are -- "+ cols);

		Object[][] data = new Object[rows - 1][cols - 2];
		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols - 2; colNum++) {
				// System.out.print(xls.getCellData(testCaseName, colNum,
				// rowNum) + " -- ");
				data[rowNum - 2][colNum] = xls.getCellData(testCaseName, colNum, rowNum);
			}
			// System.out.println();
		}
		return data;

	}

	public static String toCamelCase(String inputString) {
		String result = "";
		if (inputString.length() == 0) {
			return result;
		}
		char firstChar = inputString.charAt(0);
		char firstCharToUpperCase = Character.toUpperCase(firstChar);
		result = result + firstCharToUpperCase;
		for (int i = 1; i < inputString.length(); i++) {
			char currentChar = inputString.charAt(i);
			char previousChar = inputString.charAt(i - 1);
			if (previousChar == ' ') {
				char currentCharToUpperCase = Character.toUpperCase(currentChar);
				result = result + currentCharToUpperCase;
			} else {
				char currentCharToLowerCase = Character.toLowerCase(currentChar);
				result = result + currentCharToLowerCase;
			}
		}
		return result;
	}

	public static boolean isStringNumeric(String str) {
		DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
		char localeMinusSign = currentLocaleSymbols.getMinusSign();

		if (!Character.isDigit(str.charAt(0)) && str.charAt(0) != localeMinusSign)
			return false;

		boolean isDecimalSeparatorFound = false;
		char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

		for (char c : str.substring(1).toCharArray()) {
			if (!Character.isDigit(c)) {
				if (c == localeDecimalSeparator && !isDecimalSeparatorFound) {
					isDecimalSeparatorFound = true;
					continue;
				}
				return false;
			}
		}
		return true;
	}

	// checks RUnmode for dataSet
	public static String[] getDataSetRunmodes(Xls_Reader xlsFile, String sheetName) {
		String[] runmodes = null;
		if (!xlsFile.isSheetExist(sheetName)) {
			xlsFile = null;
			sheetName = null;
			runmodes = new String[1];
			runmodes[0] = "Y";
			xlsFile = null;
			sheetName = null;
			return runmodes;
		}
		runmodes = new String[xlsFile.getRowCount(sheetName) - 1];
		for (int i = 2; i <= runmodes.length + 1; i++) {
			runmodes[i - 2] = xlsFile.getCellData(sheetName, "Runmode", i);
		}
		xlsFile = null;
		sheetName = null;
		return runmodes;

	}

	public static synchronized void reportDataSetResult(Xls_Reader xls, String testCaseName, int rowNum, String colName,
			String result) {
		xls.setCellData(testCaseName, colName, rowNum, result);
	}

	// return the row num for a test
	public static int getRowNum(Xls_Reader xls, String id) {
		for (int i = 2; i <= xls.getRowCount("Test Cases"); i++) {
			String tcid = xls.getCellData("Test Cases", "TSID", i);

			if (tcid.equals(id)) {
				xls = null;
				return i;
			}

		}

		return -1;
	}

	public static Date getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.length() == 0 || s.equalsIgnoreCase("BLANK"))
			return true;
		else
			return false;

	}

	public static boolean isNullOrEmpty(List<String> s) {
		if (s == null || s.size() == 0)
			return true;
		else
			return false;

	}

	public static boolean ListContains(List<String> list, String s) {
		boolean flag = false;
		for (String i : list) {
			if (i.equals(s)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public static boolean validateTwoLists(List<String> collectionName, List<String> sellected_Collection) {
		boolean Status = true;
		collectionName.replaceAll(String::toUpperCase);
		Collections.sort(collectionName);
		sellected_Collection.replaceAll(String::toUpperCase);
		Collections.sort(sellected_Collection);
		if (collectionName.size() == sellected_Collection.size()) {
			for (int i = 0; i < collectionName.size(); i++) {
				if (!sellected_Collection.get(i).toString().toUpperCase().trim().equalsIgnoreCase(collectionName.get(i).toString().toUpperCase().trim())) {
					return false;
				}
			}
		} else {
			return false;
		}
		return Status;
	}

	
	public static void replaceTextInFile(String fileName, String originalText, String newText) throws Exception {
		try {
			String WorkingDir = System.getProperty("user.dir");
			String filePath = WorkingDir + "\\src\\test\\resources\\cfuploads\\" + fileName;
			System.out.println("filePath for replace" + filePath);
			String data = FileUtils.readFileToString(new File(filePath), "UTF-8");
			data = data.replace(originalText, newText);
			FileUtils.writeStringToFile(new File(filePath), data, "UTF-8");
		} catch (Exception e) {
			throw new Exception("replaceTextInFile is not working.." + e);
		}
	}
	
	public static long getTimeStamp() {
		Date d = new Date();
		long n = d.getTime();
		return n;
	}
	
	/* Check the file from a specific directory with filename */
	public static boolean isFileDownloaded(String downloadPath, String fileName) throws InterruptedException {
	       boolean flag = false;
	       File dir = new File(downloadPath);
	       waitForFileDownloaded(fileName,60000, downloadPath);
	       //WebDriverWait(driver, 60000).until(d -> Paths.get(downloadPath, fileName).toFile().exists());
	       File[] dir_contents = dir.listFiles();
	       for (int i = 0; i < dir_contents.length; i++) {
	             //             if (dir_contents[i].getName().equals(fileName))
	             if (dir_contents[i].getName().contains(fileName))
	                    return flag=true;
	       }

	       return flag;
	}
	
	static void waitForFileDownloaded(String fileName, int timeoutSeconds, String downloadPath) {
//		WebDriver driver;
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until((x) -> {
            File[] files = new File(downloadPath).listFiles();
            for (File file : files) {
                if (file.getName().contains(fileName)) {
                    return true;
                }
            }
            return false;
        });
    }


	
	/* Check the file from a specific directory with extension */
	public static boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}
	
	/* Get the latest file from a specific directory*/
	public static File getLatestFilefromDir(String dirPath){
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
	}
	
	public static void deleteAllDownloadedFiles(String dirPath) {
		File downloads = new File(dirPath);
		try {
			FileUtils.cleanDirectory(downloads);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	
	public static String readTextFromFile(String filePath,String fileName) throws Exception {
        try {
            
//            String WorkingDir = System.getProperty("user.dir");
//            String filePath = WorkingDir + "\\src\\test\\resources\\cfuploads\\" + fileName;
            System.out.println("filePath for replace" + filePath+fileName);
            BufferedReader br = new BufferedReader(new FileReader(filePath+fileName));

 

            String lineInput = br.readLine();
            
            String line;
            //while((line = br.readLine()) != null){
            //process the line
            System.out.println(lineInput);
            //}
            //close resources
            br.close();
            //fr.close();
            return lineInput;
        } catch (Exception e) {
            throw new Exception("readTextFromFile is not working.." + e);
        }
    }
	
	
	public static void unzip(String zipFilePath, String destDir) {
		File dir = new File(destDir);
		// create output directory if it doesn't exist
		if (!dir.exists())
			dir.mkdirs();
		FileInputStream fis;
		// buffer for read and write data to file
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(zipFilePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				System.out.println("Unzipping to " + newFile.getAbsolutePath());
				// create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				// close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
