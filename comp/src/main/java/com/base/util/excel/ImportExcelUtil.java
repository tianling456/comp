/**
 * 文件名称:ImportExcelUtil.java
 * 版本:1.0
 * JDK版本:1.6
 * 创建日期:2016 下午5:58:47
 *
 */
package com.base.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.base.common.exception.app.AppException;


public class ImportExcelUtil {
	private static ImportExcelUtil importExcel;
	private final static String XLS_FILE = "xls"; // xls格式的excel
	private final static String XLSX_FILE = "xlsx"; // xlsx格式的excel
	private Map<String, Object> mapKey = new HashMap<String, Object>(); // excel里面对应的值
	public static final String DELIM_DEFAULT = ".";
	private int startRowNum;
	private int getCount;

	public int getStartRowNum() {
		return startRowNum;
	}

	public int getGetCount() {
		return getCount;
	}

	public void setGetCount(int getCount) {
		this.getCount = getCount;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	private ImportExcelUtil() {

	}

	/**
	 * 获取ImportExcelUtil对象
	 * 
	 * @return
	 */
	public static ImportExcelUtil getInstance() {
		synchronized (ImportExcelUtil.class) {
			if (importExcel == null) {
				importExcel = new ImportExcelUtil();
			}
		}
		return importExcel;
	}

	/**
	 * 判断文件格式
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isExcel(String filePath) {
		boolean b = false;
		if (filePath.endsWith(".xls")) {
			b = true;
		}
		if (filePath.endsWith(".xlsx")) {
			b = true;
		}
		return b;
	}

	/**
	 * 正则表达式，匹配excel里面的取值的列
	 * 
	 * @param key
	 * @return
	 */
	public static boolean keyPattern(String key, String mapKey) {
		boolean flag = false;
		String regex = "^\\$\\{(\\$|[a-zA-Z]*)\\.(" + key + ")\\}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(mapKey);
		if (matcher.matches()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	/**
	 * 反射调用指定构造方法创建对象
	 * 
	 * @param clazz
	 *            对象类型
	 * @param argTypes
	 *            参数类型
	 * @param args
	 *            构造参数
	 * @return 返回构造后的对象
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * 
	 */
	public static <T> T invokeConstructor(Class<T> clazz, Class<?>[] argTypes,
			Object[] args) throws NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Constructor<T> constructor = clazz.getConstructor(argTypes);
		return constructor.newInstance(args);
	}

	/**
	 * 反射调用指定对象属性的getter方法
	 * 
	 * @param <T>
	 *            泛型
	 * @param target
	 *            指定对象
	 * @param fieldName
	 *            属性名
	 * @return 返回调用后的值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * 
	 */
	public static <T> Object invokeGetter(T target, String fieldName)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		// 如果属性名为xxx，则方法名为getXxx
		String methodName = "get" + toUpperCaseFirstOne(fieldName);
		Method method = target.getClass().getMethod(methodName);
		return method.invoke(target);
	}

	/**
	 * 反射调用指定对象属性的setter方法
	 * 
	 * @param <T>
	 *            泛型
	 * @param target
	 *            指定对象
	 * @param fieldName
	 *            属性名
	 * @param args
	 *            参数列表，set进的值
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * 
	 */
	public static <T> void invokeSetter(T target, String fieldName, Object args)
			throws NoSuchFieldException, SecurityException,
			NoSuchMethodException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		// 如果属性名为xxx，则方法名为setXxx
		String methodName = "set" + toUpperCaseFirstOne(fieldName);
		Class<?> clazz = target.getClass();
		Field field = clazz.getDeclaredField(fieldName);
		Method method = clazz.getMethod(methodName, field.getType());
		method.invoke(target, args);
	}

	/**
	 * 返回文件的后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileType(String filePath) {
		String fileType = filePath.substring(
				filePath.lastIndexOf(".", filePath.length()) + 1,
				filePath.length());
		return fileType;
	}

	/**
	 * 文件后缀为xls的
	 * 
	 * @param workbook
	 * @param boldweight
	 * @param color
	 *            颜色
	 * @param size
	 *            大小
	 * @return
	 */
	public HSSFFont createFont(HSSFWorkbook workbook, short boldweight,
			short color, short size) {
		HSSFFont font = workbook.createFont();
		font.setBoldweight(boldweight);
		font.setColor(color);
		font.setFontHeightInPoints(size);
		return font;
	}

	/**
	 * 文件后缀为xlsx的
	 * 
	 * @param workbook
	 * @param boldweight
	 * @param color
	 *            颜色
	 * @param size
	 *            大小
	 * @return
	 */
	public XSSFFont createFont(XSSFWorkbook workbook, short boldweight,
			short color, short size) {
		XSSFFont font = workbook.createFont();
		font.setBoldweight(boldweight);
		font.setColor(color);
		font.setFontHeightInPoints(size);
		return font;
	}

	/**
	 * 创建sheet,并命名为sheetName，文件后缀为xls
	 * 
	 * @param workbook
	 * @param sheetName
	 *            sheet的名称
	 * @return
	 */
	public HSSFSheet createSheet(HSSFWorkbook workbook, String sheetName) {
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置行高
		sheet.setDefaultRowHeight((short) 12);
		// 设置列宽
		sheet.setDefaultColumnWidth((short) 12);
		sheet.setGridsPrinted(false);
		sheet.setDisplayGridlines(false);
		return sheet;
	}

	/**
	 * 创建sheet,并命名为sheetName，文件后缀为xlsx
	 * 
	 * @param workbook
	 * @param sheetName
	 *            sheet的名称
	 * @return
	 */
	public XSSFSheet createSheet(XSSFWorkbook workbook, String sheetName) {
		XSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置行高
		sheet.setDefaultRowHeight((short) 12);
		// 设置列宽
		sheet.setDefaultColumnWidth((short) 12);
		// sheet.setGridsPrinted(false);
		sheet.setDisplayGridlines(false);
		return sheet;
	}

	/**
	 * 创建样式 文件后缀为xls
	 * 
	 * @param workbook
	 * @param backgroundColor
	 *            背景颜色
	 * @param foregroundColor
	 *            前景色
	 * @param halign
	 *            水平居中方式
	 * @param font
	 *            字体
	 * @return
	 */
	public HSSFCellStyle createStyle(HSSFWorkbook workbook,
			int backgroundColor, int foregroundColor, int valign, int halign,
			HSSFFont font) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment((short) halign);
		style.setBorderLeft((short) 1);
		style.setBorderBottom((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderTop((short) 1);
		style.setFillBackgroundColor((short) backgroundColor);
		style.setFillForegroundColor((short) foregroundColor);
		style.setFont(font);
		style.setVerticalAlignment((short) valign);
		return style;
	}

	/**
	 * 创建样式 文件后缀为xlsx
	 * 
	 * @param workbook
	 * @param backgroundColor
	 *            背景颜色
	 * @param foregroundColor
	 *            前景色
	 * @param halign
	 *            水平居中方式
	 * @param font
	 *            字体
	 * @return
	 */
	public CellStyle createStyle(XSSFWorkbook workbook, int backgroundColor,
			int foregroundColor, int valign, int halign, Font font) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment((short) halign);
		style.setBorderLeft((short) 1);
		style.setBorderBottom((short) 1);
		style.setBorderRight((short) 1);
		style.setBorderTop((short) 1);
		style.setFillBackgroundColor((short) backgroundColor);
		style.setFillForegroundColor((short) foregroundColor);
		style.setFont(font);
		style.setVerticalAlignment((short) valign);
		return style;
	}

	/**
	 * 合并单元格，格式为xls的
	 * 
	 * @param sheet
	 * @param startRow
	 *            要合并的起始行行号
	 * @param endRow
	 *            要合并的结束行行号
	 * @param startCell
	 *            要合并的起始列列号
	 * @param endCell
	 *            要合并的结束列列号
	 */
	public void addMergedRegion(HSSFSheet sheet, int startRow, int endRow,
			int startCell, int endCell) {
		sheet.addMergedRegion(new CellRangeAddress(startRow, (short) startCell, endRow,
				(short) endCell));
	}

	/**
	 * 合并单元格，格式为xlsx的
	 * 
	 * @param sheet
	 * @param startRow
	 *            要合并的起始行行号
	 * @param endRow
	 *            要合并的结束行行号
	 * @param startCell
	 *            要合并的起始列列号
	 * @param endCell
	 *            要合并的结束列列号
	 */
	public void addMergedRegion(XSSFSheet sheet, int startRow, int endRow,
			int startCell, int endCell) {
		sheet.addMergedRegion(new CellRangeAddress(startRow, endRow, startCell,
				endCell));
	}

	/**
	 * 创建行 文件后缀为xls的
	 * 
	 * @param sheet
	 *            指定的sheet
	 * @param rowNum
	 *            行号
	 * @param rowHeight
	 *            行高
	 * @return
	 */
	public HSSFRow createRow(HSSFSheet sheet, int rowNum, int rowHeight) {
		HSSFRow row = sheet.createRow(rowNum);
		row.setHeight((short) rowHeight);
		return row;
	}

	/**
	 * 创建行 文件后缀为xlsx的
	 * 
	 * @param sheet
	 *            指定的sheet
	 * @param rowNum
	 *            行号
	 * @param rowHeight
	 *            行高
	 * @return
	 */
	public XSSFRow createRow(XSSFSheet sheet, int rowNum, int rowHeight) {
		XSSFRow row = sheet.createRow(rowNum);
		row.setHeight((short) rowHeight);
		return row;
	}

	/**
	 * 创建列，文件后缀为xls的
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return
	 */
	public HSSFCell createCell(HSSFRow row, int column) {
		HSSFCell cell = row.createCell(column);
		return cell;
	}

	/**
	 * 创建列，文件后缀为xls的
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return
	 */
	public HSSFCell createCell(HSSFRow row, int column, HSSFCellStyle style) {
		HSSFCell cell = row.createCell(column);
		cell.setCellStyle(style);
		return cell;
	}

	/**
	 * 创建列，文件后缀为xlsx的
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return
	 */
	public XSSFCell createCell(XSSFRow row, int column) {
		XSSFCell cell = row.createCell((short) column);
		return cell;
	}

	/**
	 * 创建列，文件后缀为xlsx的
	 * 
	 * @param row
	 *            行
	 * @param column
	 *            列
	 * @return
	 */
	public XSSFCell createCell(XSSFRow row, int column, CellStyle style) {
		XSSFCell cell = row.createCell((short) column);
		cell.setCellStyle(style);
		return cell;
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
	 * 将列的值放入Map中 xls格式
	 * 
	 * @param key
	 *            map的key
	 * @param row
	 *            HSSFRow行数据
	 * @return
	 */
	public static Map<String, Object> setMap(String[] key, HSSFRow row,
			int colStart) {
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
	public static Map<String, Object> setMap(String[] key, XSSFRow row,
			int colStart) {
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
	 * 设置行数据 格式为xlsx
	 * 
	 * @param row
	 * @param colStart
	 * @param map
	 * @param key
	 */
	public <T> void setRowData(XSSFRow row, T target, Class<T> clazz) {
		// 循环设置列数据
		for (Map.Entry<String, Object> entry : mapKey.entrySet()) {
			XSSFCell cell = createCell(row, Integer.parseInt(entry.getKey()));
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldKey = field.getName();
				if ("${$.getCount(false)}".equals(entry.getValue().toString())) {
					cell.setCellValue(getGetCount());
				} else {
					if (ImportExcelUtil.keyPattern(fieldKey, entry.getValue()
							.toString())) {
						if (clazz != null) {
							Object o = null;
							try {
								o = ImportExcelUtil.invokeGetter(target,
										fieldKey);
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							if (o instanceof Double) {
								DecimalFormat df = new DecimalFormat("0");
								cell.setCellValue(df.format(Double
										.parseDouble(o.toString())));
							} else if (o instanceof Date) {
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyyMMdd");
								String d = sdf.format(o);
								cell.setCellValue(d);
							} else if (o instanceof Integer) {
								DecimalFormat df = new DecimalFormat("0");
								cell.setCellValue(df.format(Integer.parseInt(o
										.toString())));
							} else if (o instanceof Long) {
								DecimalFormat df = new DecimalFormat("0");
								cell.setCellValue(df.format(Long.parseLong(o
										.toString())));
							} else {
								cell.setCellValue(o.toString());
							}
						} else {
							cell.setCellValue(XSSFCell.CELL_TYPE_BLANK);
						}
					}
				}
			}
		}
	}

	/**
	 * 设置行数据 格式为xls
	 * 
	 * @param row
	 * @param colStart
	 * @param map
	 * @param key
	 */
	public <T> void setRowData(HSSFRow row, T target, Class<T> clazz) {
		// 循环设置列数据
		// 循环设置列数据
		for (Map.Entry<String, Object> entry : mapKey.entrySet()) {
			HSSFCell cell = createCell(row, Integer.parseInt(entry.getKey()));
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				String fieldKey = field.getName();
				if ("${$.getCount(false)}".equals(entry.getValue().toString())) {
					cell.setCellValue(getGetCount());
				} else {
					if (ImportExcelUtil.keyPattern(fieldKey, entry.getValue()
							.toString())) {
						if (clazz != null) {
							Object o = null;
							try {
								o = ImportExcelUtil.invokeGetter(target,
										fieldKey);
							} catch (SecurityException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
							if (o instanceof Double) {
								DecimalFormat df = new DecimalFormat("0");
								cell.setCellValue(df.format(Double
										.parseDouble(o.toString())));
							} else if (o instanceof Date) {
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyyMMdd");
								String d = sdf.format(o);
								cell.setCellValue(d);
							} else if (o instanceof Integer) {
								DecimalFormat df = new DecimalFormat("0");
								cell.setCellValue(df.format(Integer.parseInt(o
										.toString())));
							} else if (o instanceof Long) {
								DecimalFormat df = new DecimalFormat("0");
								cell.setCellValue(df.format(Long.parseLong(o
										.toString())));
							} else {
								cell.setCellValue(o.toString());
							}
						} else {
							cell.setCellValue(HSSFCell.CELL_TYPE_BLANK);
						}
					}
				}
			}
		}

	}

	/**
	 * 
	 * 
	 * @param templeteExcelPath
	 *            模板excel
	 * @param outExcelNeme
	 *            导出excel
	 * @param data
	 *            数据
	 * @param rowStart
	 *            开始行号
	 * @param colStart
	 *            开始列号
	 * @param key
	 */
	public <T> void setWorkbookDate(String templeteExcelPath,
			String outExcelNeme, Class<T> clazz, List<T> data) {
		if (XLS_FILE.equals(getFileType(templeteExcelPath))) {
			// 读取模板excel
			HSSFWorkbook workbook = readExcel(templeteExcelPath);
			int sheets = workbook.getNumberOfSheets();
			// 操作excel数据
			for (int i = 0; i < sheets; i++) {
				HSSFSheet sheet = workbook.getSheetAt(i);
				if (sheet != null) {
					for (int j = getStartRowNum(); j < data.size(); j++) {
						T target = data.get(j);
						HSSFRow row = createRow(sheet, j, 300);
						setRowData(row, target, clazz);
						setGetCount(j-getStartRowNum()+1);
					}
				}
				sheet.setAlternativeFormula(true);
			}
			// 写入excel数据
			setDataToExcel(outExcelNeme, workbook);
		}
		if (XLSX_FILE.equals(getFileType(templeteExcelPath))) {
			// 读取模板excel
			XSSFWorkbook workbook = readExcelX(templeteExcelPath);
			int sheets = workbook.getNumberOfSheets();
			// 操作excel数据
			for (int i = 0; i < sheets; i++) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				if (sheet != null) {
					for (int j = getStartRowNum(); j < data.size(); j++) {
						T target = data.get(j);
						XSSFRow row = createRow(sheet, j, 300);
						setRowData(row, target, clazz);
						setGetCount(j-getStartRowNum()+1);
					}
				}
				// sheet.setDisplayFormulas(false);
			}
			// 写入excel数据
			setDataToExcel(outExcelNeme, workbook);
		}
	}

	/**
	 * 读取excel模板,xls
	 * 
	 * @param fileName
	 * @param workbook
	 */
	public HSSFWorkbook readExcel(String fileName) {
		HSSFWorkbook workbook = null;
		if (fileName == null || "".equals(fileName)) {
			throw new AppException(fileName + "路径为空");
		}
		if (!isExcel(fileName)) {
			throw new AppException(fileName + "不是excel文件");
		}
		// excel的后缀为XLS
		if (getFileType(fileName).equals(XLS_FILE)) {
			File file = null;
			InputStream is = null;
			try {
				file = new File(fileName);
				is = new FileInputStream(file);
				workbook = new HSSFWorkbook(is);
				int sheets = workbook.getNumberOfSheets();
				for (int i = 0; i < sheets; i++) {
					HSSFSheet sheet = workbook.getSheetAt(i);
					int rows = sheet.getPhysicalNumberOfRows();
					for (int j = 0; j < rows; j++) {
						HSSFRow row = sheet.getRow(j);
						if (isBlankRow(row)) {
							continue;
						}
						int cells = row.getPhysicalNumberOfCells();
						for (int k = 0; k < cells; k++) {
							HSSFCell cell = row.getCell(k);
							String temp = cell.getStringCellValue();
							String key = "[A-Za-z0-9\\(\\)]*";
							if (ImportExcelUtil.keyPattern(key, temp)) {
								setStartRowNum(j);
								mapKey.put(String.valueOf(k),
										cell.getStringCellValue());
							}
						}
					}
				}
			} catch (Exception e) {
				throw new RuntimeException("文件不存在");
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}

	/**
	 * 读取excel模板,xlsx
	 * 
	 * @param fileName
	 * @param workbook
	 */
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public XSSFWorkbook readExcelX(String fileName) {
		XSSFWorkbook workbook = null;
		if (fileName == null || "".equals(fileName)) {
			throw new RuntimeException(fileName + "路径为空");
		}
		if (!isExcel(fileName)) {
			throw new RuntimeException(fileName + "不是excel文件");
		}
		if (getFileType(fileName).equals(XLSX_FILE)) {
			try {
				workbook = new XSSFWorkbook(fileName);
				int sheets = workbook.getNumberOfSheets();
				for (int i = 0; i < sheets; i++) {
					XSSFSheet sheet = workbook.getSheetAt(i);
					int rows = sheet.getPhysicalNumberOfRows();
					for (int j = 0; j < rows; j++) {
						XSSFRow row = sheet.getRow(j);
						if (isBlankRow(row)) {
							continue;
						}
						int cells = row.getPhysicalNumberOfCells();
						for (int k = 0; k < cells; k++) {
							XSSFCell cell = row.getCell(k);
							String temp = cell.getStringCellValue();
							String key = "[A-Za-z0-9\\(\\)]*";
							if (ImportExcelUtil.keyPattern(key, temp)) {
								setStartRowNum(j);
								mapKey.put(String.valueOf(k),
										cell.getStringCellValue());
							}
						}
					}
				}
			} catch (IOException e) {
				throw new RuntimeException("文件不存在");
			}
		}
		return workbook;
	}

	/**
	 * 
	 * 写入到excel,xls
	 * 
	 * @param fileName
	 *            生成的文件
	 */
	public void setDataToExcel(String fileName, HSSFWorkbook workbook) {
		File file = null;
		OutputStream os = null;
		try {
			file = new File(fileName);
			os = new FileOutputStream(file);

			workbook.write(os);
		} catch (Exception e) {
			String message = e.getMessage();
			throw new AppException(message);
		} finally {
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * 写入到excel,xlsx
	 * 
	 * @param fileName
	 *            生成的文件
	 */
	public void setDataToExcel(String fileName, XSSFWorkbook workbook) {
		File file = null;
		OutputStream os = null;
		try {
			file = new File(fileName);
			os = new FileOutputStream(file);
			workbook.write(os);
		} catch (Exception e) {
			throw new AppException(e.getMessage());
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
