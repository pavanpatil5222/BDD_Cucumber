package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author Anand/Rashmi
 *
 */

public class Xls_Reader {
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public Xls_Reader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	@SuppressWarnings("unused")
	public String[][] getAllData(String sheetName) {
		XSSFRow row;
		XSSFCell cell;
		String[][] value = null;
		double[][] nums = null;

		try {

			XSSFSheet sheet = workbook.getSheet(sheetName);

			// get number of rows from sheet
			int rows = sheet.getPhysicalNumberOfRows();

			// get number of cell from row
			int cells = sheet.getRow(0).getPhysicalNumberOfCells();

			value = new String[rows][cells];

			for (int r = 0; r < rows; r++) {
				row = sheet.getRow(r); // bring row
				if (row != null) {
					for (int c = 0; c < cells; c++) {
						cell = row.getCell(c);
						nums = new double[rows][cells];

						if (cell != null) {

							switch (cell.getCellType()) {

							case XSSFCell.CELL_TYPE_FORMULA:
								value[r][c] = cell.getCellFormula();
								break;

							case XSSFCell.CELL_TYPE_NUMERIC:
								value[r][c] = "" + cell.getNumericCellValue();
								break;

							case XSSFCell.CELL_TYPE_STRING:
								value[r][c] = "" + cell.getStringCellValue();
								break;

							case XSSFCell.CELL_TYPE_BOOLEAN:
								value[r][c] = "" + cell.getBooleanCellValue();
								break;

							case XSSFCell.CELL_TYPE_BLANK:
								value[r][c] = "";
								break;

							case XSSFCell.CELL_TYPE_ERROR:
								value[r][c] = "" + cell.getErrorCellValue();
								break;
							default:
							}
							// System.out.print(value[r][c]);

						} else {
							// System.out.print("[null]\t");
						}
					} // for(c)
						// System.out.print("\n");
				}
				// for(r)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	@SuppressWarnings("unused")
	public String[][] getAllData(String sheetName, int numberToSkip) {
		XSSFRow row;
		XSSFCell cell;
		String[][] value = null;
		double[][] nums = null;

		try {
			// FileInputStream inputStream = new FileInputStream("TEST.xlsx");
			// XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			// get sheet number

			// get 0th sheet data
			XSSFSheet sheet = workbook.getSheet(sheetName);

			// get number of rows from sheet
			int rows = sheet.getPhysicalNumberOfRows();

			// get number of cell from row
			int cells = 0;
			if (numberToSkip == 0)
				cells = sheet.getRow(0).getPhysicalNumberOfCells();
			else if (numberToSkip == 1)
				cells = sheet.getRow(numberToSkip).getPhysicalNumberOfCells();
			else if (numberToSkip == 2)
				cells = sheet.getRow(numberToSkip).getPhysicalNumberOfCells();
			else
				cells = sheet.getRow(numberToSkip).getPhysicalNumberOfCells();

			value = new String[rows][cells];

			for (int r = 0; r < rows; r++) {
				row = sheet.getRow(r); // bring row
				if (row != null) {
					for (int c = 0; c < cells; c++) {
						cell = row.getCell(c);
						nums = new double[rows][cells];

						if (cell != null) {

							switch (cell.getCellType()) {

							case XSSFCell.CELL_TYPE_FORMULA:
								value[r][c] = cell.getCellFormula();
								break;

							case XSSFCell.CELL_TYPE_NUMERIC:
								value[r][c] = "" + cell.getNumericCellValue();
								break;

							case XSSFCell.CELL_TYPE_STRING:
								value[r][c] = "" + cell.getStringCellValue();
								break;

							case XSSFCell.CELL_TYPE_BOOLEAN:
								value[r][c] = "" + cell.getBooleanCellValue();
								break;

							case XSSFCell.CELL_TYPE_BLANK:
								value[r][c] = "";
								break;

							case XSSFCell.CELL_TYPE_ERROR:
								value[r][c] = "" + cell.getErrorCellValue();
								break;
							default:
							}
							// System.out.print(value[r][c]);

						} else {
							// System.out.print("[null]\t");
						}
					} // for(c)
						// System.out.print("\n");
				}
				// for(r)
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	// returns the data from a cell
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";
			// System.out.println(cell.getCellType());
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;

					// System.out.println(cellText);

				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;

					// System.out.println(cellText);

				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;
			System.out.println();
			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
		// System.out.println("setCellData setCellData******************");
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);
			// System.out.println("A");
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
					colNum = i;
			}

			if (colNum == -1)
				return false;
			sheet.autoSizeColumn(colNum); // ashish
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);
			XSSFCreationHelper createHelper = workbook.getCreationHelper();

			// cell style for hyperlinks
			// by default hypelrinks are blue and underlined
			CellStyle hlink_style = workbook.createCellStyle();
			XSSFFont hlink_font = workbook.createFont();
			hlink_font.setUnderline(XSSFFont.U_SINGLE);
			hlink_font.setColor(IndexedColors.BLUE.getIndex());
			hlink_style.setFont(hlink_font);
			// hlink_style.setWrapText(true);

			XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
			link.setAddress(url);
			cell.setHyperlink(link);
			cell.setCellStyle(hlink_style);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);

			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if sheet is created successfully else false
	public boolean addSheet(String sheetname) {

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if sheet is removed successfully else false if sheet does
	// not exist
	public boolean removeSheet(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if column is created successfully
	public boolean addColumn(String sheetName, String colName) {
		// System.out.println("**************addColumn*********************");

		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);

			// cell = row.getCell();
			// if (cell == null)
			// System.out.println(row.getLastCellNum());
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			cell.setCellStyle(style);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;

	}

	// removes a column and all the contents
	@SuppressWarnings("unused")
	public boolean removeColumn(String sheetName, int colNum) {
		try {
			if (!isSheetExist(sheetName))
				return false;
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			XSSFCreationHelper createHelper = workbook.getCreationHelper();
			style.setFillPattern(HSSFCellStyle.NO_FILL);

			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	// find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();

	}

	// String sheetName, String testCaseName,String keyword ,String URL,String
	// message
	public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url,
			String message) {
		// System.out.println("ADDING addHyperLink******************");

		url = url.replace('\\', '/');
		if (!isSheetExist(sheetName))
			return false;

		sheet = workbook.getSheet(sheetName);

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
				// System.out.println("**caught "+(i+index));
				setCellData(sheetName, screenShotColName, i + index, message, url);
				break;
			}
		}

		return true;
	}

	public int getCellRowNum(String sheetName, String colName, String cellValue) {

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;

	}

	// to run this on stand alone
	/*
	 * public static void main(String arg[]) throws IOException{
	 * 
	 * //System.out.println(filename); Xls_Reader datatable = null;
	 * 
	 * 
	 * datatable = new
	 * Xls_Reader("C:/Users/u0150445/Desktop/VisualRS/MasterTrack.xlsx");
	 * for(int col=0 ;col< datatable.getColumnCount("TC5"); col++){
	 * System.out.println(datatable.getCellData("TC5", col, 1)); } }
	 */

	public int getPagesCount() {
		int index = workbook.getNumberOfSheets();
		if (index == -1)
			return 0;
		return index + 1;

	}

	public String getPageNameAt(int page) {
		return workbook.getSheetAt(page).getSheetName();
	}

	@SuppressWarnings("unused")
	public List<String> getAllDataAsString(int Page) {
		XSSFRow row;
		XSSFCell cell;
		double[][] nums = null;
		List<String> Data = new ArrayList<String>();
		try {
			// FileInputStream inputStream = new FileInputStream("TEST.xlsx");
			// XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			// get sheet number

			// get 0th sheet data
			XSSFSheet sheet = workbook.getSheetAt(Page);

			// get number of rows from sheet
			int rows = sheet.getPhysicalNumberOfRows();

			// get number of cell from row
			int cells = sheet.getRow(1).getPhysicalNumberOfCells();

			String str = "";

			for (int r = 0; r < rows; r++) {
				row = sheet.getRow(r); // bring row
				if (row != null) {
					for (int c = 0; c < cells; c++) {
						cell = row.getCell(c);
						nums = new double[rows][cells];

						if (cell != null) {

							switch (cell.getCellType()) {

							case XSSFCell.CELL_TYPE_FORMULA:
								str = str + cell.getCellFormula() + ", ";
								break;

							case XSSFCell.CELL_TYPE_NUMERIC:
								str = str + cell.getNumericCellValue() + ", ";
								break;

							case XSSFCell.CELL_TYPE_STRING:
								str = str + cell.getStringCellValue() + ", ";
								break;

							case XSSFCell.CELL_TYPE_BLANK:
								str = str + "[BLANK]" + ", ";
								break;

							case XSSFCell.CELL_TYPE_ERROR:
								str = str + cell.getErrorCellValue() + ", ";
								break;
							default:
							}
						} else {

						}
					}
					Data.add(str);
					str = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Data;
	}
	
	public ArrayList<String> getColumnHeadings(String sheetName) {
		try {
			ArrayList<String> columnHeading = new ArrayList<String>();
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return null;

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(1);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				String val = row.getCell(i).getStringCellValue().trim();
				columnHeading.add(val);
			}
			return columnHeading;
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllCellValues(String sheetName, String colName) {
		try {
			ArrayList<String> columnValues = new ArrayList<String>();
			if (isSheetExist(sheetName)) {
				
				int rowStartNum = getHeaderRowValue(sheetName, colName);
				int columNum = getExpectedColumIndex(sheetName, colName, rowStartNum);

				for (int i = rowStartNum+1; i < getRowCount(sheetName); i++) {
					String val = sheet.getRow(i).getCell(columNum).getStringCellValue().trim();
					columnValues.add(val);
				}
				return columnValues;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getHeaderRowValue(String sheetName, String colName) {

		if (isSheetExist(sheetName)) {
			sheet = workbook.getSheet(sheetName);
			for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
				for (int k = sheet.getRow(i).getFirstCellNum(); k < sheet.getRow(i).getLastCellNum(); k++) {
					if (sheet.getRow(i).getCell(k).getStringCellValue().trim().equalsIgnoreCase(colName))
						return i;
				}

			}
		}
		return 0;}
	

	public int getExpectedColumIndex(String sheetName, String colName,int rowindex)
	{
		int indexValue =-1;
		try {
			if (isSheetExist(sheetName)) {
				sheet = workbook.getSheet(sheetName);
				for (int i = sheet.getRow(rowindex).getFirstCellNum(); i < sheet.getRow(rowindex).getLastCellNum(); i++) {
					String val = sheet.getRow(rowindex).getCell(i).getStringCellValue().trim();
					if (val.equalsIgnoreCase(colName))
						return i;
				}
			} else
				return indexValue;
			return rowindex;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return indexValue;
	}
	

}
