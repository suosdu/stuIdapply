package com.stu_id_apply.service;

import java.util.List;

import com.stu_id_apply.dao.CcxxbDao;
import com.stu_id_apply.vo.Ccxxb;

public class CcxxbService {
	public Ccxxb queryBy(String xh)
	{
		Ccxxb ccxxb = new Ccxxb();
		ccxxb.setXh(xh);
		CcxxbDao ccxxbdao = new CcxxbDao();
		return ccxxbdao.select(ccxxb);
	}
	
	public List joinQuery(Ccxxb ccxxb)
	{
		CcxxbDao ccxxbdao = new CcxxbDao();
		return ccxxbdao.join_select(ccxxb);
	}
	public void insertORupdate(String xh,String qsz,String zdz,String czyh)
	{
		Ccxxb ccxxb = new Ccxxb();
		ccxxb.setXh(xh);
		ccxxb.setQsz(qsz);
		ccxxb.setZdz(zdz);
		ccxxb.setCzyh(czyh);
		CcxxbDao ccxxbdao = new CcxxbDao();
		ccxxbdao.insertORupdate(ccxxb);
	}
	
	public void deleteBy(String xh)
	{
		Ccxxb ccxxb = new Ccxxb();
		ccxxb.setXh(xh);
		CcxxbDao ccxxbdao = new CcxxbDao();
		ccxxbdao.delete(ccxxb);
		
	}
}
