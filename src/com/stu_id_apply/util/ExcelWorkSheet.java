package com.stu_id_apply.util;

import java.util.ArrayList;
import java.util.List;

public class ExcelWorkSheet<T> {

	 private String sheetName;// 工作单名称
	 private List<T> data = new ArrayList<T>();
	 private List<String> columns;// 列名
	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}
	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	/**
	 * @return the data
	 */
	public List<T> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<T> data) {
		this.data = data;
	}
	/**
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}
	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	 
}
