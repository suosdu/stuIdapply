package com.stu_id_apply.util;

import java.util.List;

import com.stu_id_apply.service.XjbService;
import com.stu_id_apply.vo.Jlb;



public class ExcelOper {
	
	public  XjbService xjbservice =new XjbService();
    public void exportJlb(List<Jlb> jlblist,String file){
    	
    	ExcelReader readExcel = new ExcelReader();
    	readExcel.wFileOpen(file, "sheet1");
//    	String[] data = {"学号","姓名","申请日期","证卡类型号","证卡类型"};
    	String[] data = {"学号","姓名","性别","学院","专业","班名","所属年级","身份证号","起始站","终点站"};
    	readExcel.writeExcelLine(data, 0);//写入表头
    	int row = 1;//从第二行开始写入数据
    		for(int i=0;i<jlblist.size();i++){
    			Jlb jlb = jlblist.get(i);
//    			String [] info = {jlb.getXh(),jlb.getXm(),jlb.getSqrq(),jlb.getZklxh(),jlb.getZklx()};
    			String [] info = {jlb.getXh(),jlb.getXm(),jlb.getXb(),jlb.getXy(),jlb.getZy(),xjbservice.queryBm(jlb.getXh()),jlb.getSsnj(),jlb.getSfzh(),jlb.getQsz(),jlb.getZdz()};
    			readExcel.writeExcelLine(info, row);
    			row++;
    		}
   	
    	readExcel.wFileClose();
    }
}
