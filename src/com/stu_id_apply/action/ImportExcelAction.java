package com.stu_id_apply.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.stu_id_apply.service.CcxxbService;
import com.stu_id_apply.service.JlbService;
import com.stu_id_apply.util.ExcelWorkSheet;
import com.stu_id_apply.util.ImportInfo;

@SuppressWarnings("serial")
public class ImportExcelAction extends ActionSupport {
    private File excelFile;// 上传文件
    private String excelFileFileName;// 保存原始文件名
    private ExcelWorkSheet<ImportInfo> excelWorkSheet;
	private String message;
	private JSONArray jsonArray;	
	private CcxxbService ccxxbservice = new CcxxbService();

    @Override
    public String execute() throws Exception {
    	if("".equals(excelFileFileName)||null==excelFileFileName||!excelFileFileName.toLowerCase().endsWith("xls")&&!excelFileFileName.toLowerCase().endsWith("xlsx")){
    		message="导入失败";
    		return "input";
    	}
    	System.out.println("excelFileFileName:"+excelFileFileName);
        Workbook workbook = createWorkBook(new FileInputStream(excelFile));
        Sheet sheet = workbook.getSheetAt(0);
        
        excelWorkSheet = new ExcelWorkSheet<ImportInfo>();
        // 保存工作单名称
        excelWorkSheet.setSheetName(sheet.getSheetName());
        Row firstRow = sheet.getRow(0);
        Iterator<Cell> iterator = firstRow.iterator();
        // 保存列名
        List<String> cellNames = new ArrayList<String>();
        while (iterator.hasNext()) {
            cellNames.add(iterator.next().getStringCellValue());
        }
        excelWorkSheet.setColumns(cellNames);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            ImportInfo info = new ImportInfo();
            info.setXh(row.getCell(0).getStringCellValue());
            info.setQsz(row.getCell(1).getStringCellValue());
            info.setZdz(row.getCell(2).getStringCellValue());
//            info.set
            System.out.println(info.getXh());
            excelWorkSheet.getData().add(info);
            ccxxbservice.insertORupdate(info.getXh(),info.getQsz(),info.getZdz(),(String)ActionContext.getContext().getSession().get("username"));
            //导入文件，更改记录状态									//update
        }
        ActionContext.getContext().put("excelWorkSheet", excelWorkSheet);      
//        jsonArray = JSONArray.fromObject( excelWorkSheet.getData() );
//        System.out.println( jsonArray ); 
        message = "导入成功";
        return "success";

    }
    
    private Workbook createWorkBook(InputStream is) throws IOException {
        if (excelFileFileName.toLowerCase().endsWith("xls")) {
            return new HSSFWorkbook(is);
        }else if (excelFileFileName.toLowerCase().endsWith("xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;

    }
    
    public File getExcelFile() {
        return excelFile;
    }

    public void setExcelFile(File excelFile) {
        this.excelFile = excelFile;
    }

    public String getExcelFileFileName() {
        return excelFileFileName;
    }

    public void setExcelFileFileName(String excelFileFileName) {
        this.excelFileFileName = excelFileFileName;
    }
    
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the jsonArray
	 */
	public JSONArray getJsonArray() {
		return jsonArray;
	}

	/**
	 * @param jsonArray the jsonArray to set
	 */
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}


	
}