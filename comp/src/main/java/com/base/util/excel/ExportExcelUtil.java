package com.base.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.base.common.exception.app.AppException;


public class ExportExcelUtil {
	private final static String XLS_FILE = "xls"; // xls格式的excel
	private final static String XLSX_FILE = "xlsx"; // xlsx格式的excel

	/**
	 * 判断文件格式
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isExcel(String fileName) {
		boolean b = false;
		if (fileName.endsWith(".xls")) {
			b = true;
		}
		if (fileName.endsWith(".xlsx")) {
			b = true;
		}
		return b;
	}

	/**
	 * 返回文件的后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String fileName) {
		String fileType = fileName.substring(fileName.lastIndexOf(".", fileName.length()) + 1,
				fileName.length());
		return fileType;
	}

	
	/**
	 * 判断是不是合并单元格 xls格式的数据
	 * 
	 * @param sheet
	 *            HSSFSheet对象
	 * @param row
	 *            行号
	 * @param column
	 *            列号
	 * @return
	 */
	public static boolean isMergedRegion(HSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是不是合并单元格 xlsx格式的数据
	 * 
	 * @param sheet
	 *            XSSFSheet对象
	 * @param row
	 *            行号
	 * @param column
	 *            列号
	 * @return
	 */
	public static boolean isMergedRegion(XSSFSheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断行是否为空 xls格式
	 * 
	 * @param row
	 *            HSSFRow数据
	 * @return
	 */
	public static boolean isBlankRow(HSSFRow row) {
		if (row == null)
			return true;
		boolean result = true;
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			HSSFCell cell = row.getCell(i);
			String value = "";
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = String.valueOf((int) cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					value = String.valueOf(cell.getCellFormula());
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				default:
					break;
				}
				if (!value.trim().equals("")) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 判断行是否为空 xlsx格式
	 * 
	 * @param row
	 *            XSSFRow数据
	 * @return
	 */
	public static boolean isBlankRow(XSSFRow row) {
		if (row == null)
			return true;
		boolean result = true;
		for (short i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			XSSFCell cell = row.getCell(i);
			String value = "";
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = String.valueOf((int) cell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					value = String.valueOf(cell.getCellFormula());
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				default:
					break;
				}
				if (!value.trim().equals("")) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 将列的值放入Map中 xls格式
	 * 
	 * @param key
	 *            map的key
	 * @param row
	 *            HSSFRow行数据
	 * @return
	 */
	public static Map<String, Object> setMap(String[] key, HSSFRow row,int colStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 得到有多少列
		int cellCount = row.getPhysicalNumberOfCells();
		if (cellCount != key.length) {
			throw new AppException("Excel文件数据不对应，请检测！");
		}
		// 循环列数据
		for (int i = 0; i < cellCount; i++) {
			HSSFCell cell = row.getCell(i);
			Object value = "";
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = cell.getNumericCellValue();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_FORMULA:
					value = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				default:
					break;
				}
				map.put(key[i], value);
			}
		}
		return map;
	}

	/**
	 * 将列的值放入Map中 xlsx格式
	 * 
	 * @param key
	 *            map的key
	 * @param row
	 *            XSSFRow行数据
	 * @return
	 */
	public static Map<String, Object> setMap(String[] key, XSSFRow row,int colStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 得到有多少列
		int cellCount = row.getPhysicalNumberOfCells();
		if (cellCount != key.length) {
			throw new AppException("Excel文件数据不对应，请检测！");
		}
		// 循环列数据
		for (short i = (short) colStart; i < cellCount; i++) {
			XSSFCell cell = row.getCell(i);
			Object value = "";
			if (cell != null) {
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					value = cell.getStringCellValue();
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = cell.getNumericCellValue();
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					value = cell.getBooleanCellValue();
					break;
				case Cell.CELL_TYPE_FORMULA:
					value = cell.getCellFormula();
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				default:
					break;
				}
				map.put(key[i], value);
			}
		}
		return map;
	}

	/**
	 * 读取xls和xlsx格式的excel数据
	 * 
	 * @param rowStart
	 *            开始读取的行号
	 * @param key
	 *            Map的key值
	 * @param files
	 *            文件路径
	 * @return
	 */
	public static List<Map<String, Object>> getDataFromExcel(int rowStart,int colStart,
			String[] key, String... files) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		// 循环读取excel文件
		for (String file : files) {
			boolean b = isExcel(file);
			if (file == null || "".equals(file)) {
				throw new AppException(file + "路径为空");
			}
			if (!b) {
				throw new AppException(file + "不是excel文件");
			}
			//如果为xls后缀的
			if (XLS_FILE.equals(getFileType(file))) {
				// 创建XSSFWorkbook
				HSSFWorkbook workbook = null;
				File f = null;
				InputStream is = null;
				try {
					f = new File(file);
					if (!f.exists()) {
						throw new AppException(file + "文件不存在");
					}
					is = new FileInputStream(f);
					// 获得原excel
					workbook = new HSSFWorkbook(is);
					// 获取有多少个sheet
					int sheets = workbook.getNumberOfSheets();
					// 遍历每个sheet
					for (int i = 0; i < sheets; i++) {
						// 得到每个sheet
						HSSFSheet spreadsheet = workbook.getSheetAt(i);
						if (spreadsheet != null) {
							// 得到sheet有多少行
							// int endRow = spreadsheet.getLastRowNum();
							int endRow = spreadsheet.getPhysicalNumberOfRows();
							// 遍历每个sheet的行
							for (int j = rowStart; j < endRow; j++) {
								// 获得行
								HSSFRow row = spreadsheet.getRow(j);
								if (isBlankRow(row)) {
									continue;
								}
								if (row != null) {
									// 得到有多少列
									// int cellCount = row.getLastCellNum();
									// int cellCount =
									// row.getPhysicalNumberOfCells();
									Map<String, Object> map = setMap(key,row,colStart);
									data.add(map);
								}
							}
						}
					}
				} catch (Exception e) {
					String message = e.getMessage();
					throw new RuntimeException(message);
				} finally {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			//如果后缀为xlsx的
			if(XLSX_FILE.equals(getFileType(file))){
				XSSFWorkbook xworkbook = null;
				try {
					xworkbook = new XSSFWorkbook(file);
					// 得到有多少个sheet
					int sheets = xworkbook.getNumberOfSheets();
					// 遍历每个sheet
					for (int i = 0; i < sheets; i++) {
						// 得到sheet对象
						XSSFSheet sheet = xworkbook.getSheetAt(i);
						if (sheet != null) {
							// 得到有多少行数据
							int rows = sheet.getPhysicalNumberOfRows();
							// 遍历每行数据
							for (int k = rowStart; k < rows; k++) {
								// 得到行数据
								XSSFRow row = sheet.getRow(k);
								if (isBlankRow(row)) {
									continue;
								}
								if (row != null) {
									Map<String, Object> map = setMap(key,row,colStart);
									data.add(map);
								}
							}
						}
					}
				} catch (Exception e) {
					String message = e.getMessage();
					throw new AppException(message);
				} finally {

				}
			}
		}
		return data;
	}

	/**
	 * 读取xlsx格式的excel数据
	 * 
	 * @param rowStart
	 *            开始读取的行号
	 * @param key
	 *            Map的key值
	 * @param files
	 *            文件路径
	 * @return
	 */
	public static List<Map<String, Object>> getDataFromExcelX(int rowStart,int colStart,
			String[] key, String... files) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (String file : files) {
			boolean b = isExcel(file);
			if (file == null || "".equals(file)) {
				throw new AppException(file + "路径为空");
			}
			if (!b) {
				throw new AppException(file + "不是excel文件");
			}
			XSSFWorkbook xworkbook = null;
			try {
				xworkbook = new XSSFWorkbook(file);
				// 得到有多少个sheet
				int sheets = xworkbook.getNumberOfSheets();
				// 遍历每个sheet
				for (int i = 0; i < sheets; i++) {
					// 得到sheet对象
					XSSFSheet sheet = xworkbook.getSheetAt(i);
					if (sheet != null) {
						// 得到有多少行数据
						int rows = sheet.getPhysicalNumberOfRows();
						// 遍历每行数据
						for (int k = rowStart; k < rows; k++) {
							// 得到行数据
							XSSFRow row = sheet.getRow(k);
							if (isBlankRow(row)) {
								continue;
							}
							if (row != null) {
								Map<String, Object> map = setMap(key, row,colStart);
								data.add(map);
							}
						}
					}
				}
			} catch (Exception e) {
				String message = e.getMessage();
				throw new AppException(message);
			} finally {

			}
		}
		return data;
	}
}
