package com.stu_id_apply.service;

import com.stu_id_apply.dao.XtszDao;
import com.stu_id_apply.vo.Xtsz;

public class XtszService {
	public Xtsz queryBy(String paramname)
	{
		Xtsz xtsz = new Xtsz();
		xtsz.setParamname(paramname);
		XtszDao xtszdao = new XtszDao();
		return xtszdao.select(xtsz);
	}
	
	public void updateBy(String paramname,String status)
	{
		Xtsz xtsz = new Xtsz();
		xtsz.setParamname(paramname);
		xtsz.setStatus(status);
		XtszDao xtszdao = new XtszDao();
		xtszdao.update(xtsz);
	}
}
