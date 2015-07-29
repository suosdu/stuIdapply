package com.stu_id_apply.util;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.JDialog;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * @author xy
 */
public class ExcelReader {
	private FileOutputStream fos = null;
	private FileInputStream fis = null;
	private HSSFWorkbook wb = null;
	private HSSFSheet sheet = null;
	private int rowCount = 0;

	public int getRowCount() {
		return rowCount;
	}

	/**
	 * ����Ҫд����ļ����������򴴽�
	 * 
	 * @param fileName
	 *            �ļ�ȫ·������(�ļ���
	 * @param sheetName
	 *            ������excel����������
	 */
	public void wFileOpen(String fileName, String sheetName) {
		try {
			fos = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�����ļ�ʧ��");
		}
		wb = new HSSFWorkbook();
		sheet = wb.createSheet();
		wb.setSheetName(0, sheetName);

	}

	/**
	 * �򿪶�ȡ��excel�ļ�
	 * 
	 * @param fileName
	 *            �ļ�ȫ·������(�ļ���
	 * @param sheetName
	 *            �����
	 */
	public void rFileOpen(String fileName, int sheetNum) {
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�ļ������ڣ�");
		}
		try {
			wb = new HSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(sheetNum);
		// ��ȡ��ݱ������
		rowCount = sheet.getLastRowNum();
	}

	/**
	 * ����д��excel
	 * 
	 * @param strLine
	 *            �����е���Ϣ����
	 * @param rowNum
	 *            �б�
	 */
	public void writeExcelLine(String[] strLine, int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		for (int i = 0; i < strLine.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(strLine[i]);
		}
	}

	public String[] readExcelLine(int rowNum, int cellNum) {
		String[] str = new String[cellNum];
		for (int i = 0; i < cellNum; i++) {
			HSSFCell cell = sheet.getRow(rowNum).getCell(i);
			if (cell.getCellType() == cell.CELL_TYPE_STRING)
				str[i] = cell.getStringCellValue().trim();
			else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				String sstr = String.valueOf(cell.getNumericCellValue());
				if (sstr.endsWith(".0"))
					str[i] = sstr.substring(0, sstr.indexOf("."));
				// ����ȡ���ǿ�ѧ����ĸ�ʽ����ת��Ϊ��ͨ��ʽ
				else if (sstr.indexOf(".") != -1 && sstr.indexOf("E") != -1) {
					DecimalFormat df = new DecimalFormat();
					try {
						str[i] = df.parse(sstr).toString();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else
				str[i] = "";
		}
		return str;
	}

	public String[] readExcelLine2(int rowNum, int cellNum) {
		String[] str = new String[cellNum];
		for (int i = 0; i < cellNum; i++) {
			HSSFCell cell = sheet.getRow(rowNum).getCell(i);
			// System.out.println(i+"s"+cell.getCellType()+"hhhh"+cell.CELL_TYPE_NUMERIC);
			if (cell != null) {
				if (cell.getCellType() == cell.CELL_TYPE_STRING)
					str[i] = cell.getStringCellValue().trim();
				else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
					String sstr = String.valueOf(cell.getNumericCellValue());
					str[i] = sstr;
					// System.out.println(sstr);
				} else {
					str[i] = "";
				}
			} else {
				str[i] = null;
			}
		}
		return str;
	}

	public void wFileClose() {
		try {
			if (wb != null) {
				wb.write(fos);
				wb = null;
			}
			if (fos != null) {
				fos.close();
				fos = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("д���ļ���?");
		}
	}

	public void rFileClose() {
		try {
			if (fis != null) {
				fis.close();
				fis = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {

	}

}