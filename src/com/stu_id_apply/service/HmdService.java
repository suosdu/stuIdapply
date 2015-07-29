package com.stu_id_apply.service;

import java.util.List;

import com.stu_id_apply.dao.HmdDao;
import com.stu_id_apply.vo.Hmd;

public class HmdService {
	
	public void insert(Hmd hmd)
	{
		HmdDao hmddao =new HmdDao();
		hmddao.insert(hmd);
	}
	public List query()
	{
		HmdDao hmddao =new HmdDao();
		return hmddao.select();
	}
	
	public void deleteHmd(String xh,String zklxh)
	{
		HmdDao hmddao =new HmdDao();
		Hmd hmd = new Hmd();
		hmd.setXh(xh);
		hmd.setZklxh(zklxh);
		hmddao.delete(hmd);
	}
}
