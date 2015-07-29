package com.stu_id_apply.service;

import com.stu_id_apply.dao.XjbDao;
import com.stu_id_apply.vo.Xjb;

public class XjbService {
	public Xjb queryBy(String username,String sfyxj,String sfygjxj){
		Xjb vo = new Xjb();
		vo.setXh(username);
		vo.setSfyxj(sfyxj);
		vo.setSfygjxj(sfygjxj);
		XjbDao Xjbdao = new XjbDao();
		return Xjbdao.select(vo);
	}
	
	public String queryBm(String xh)
	{
		XjbDao Xjbdao = new XjbDao();
		return Xjbdao.selectBm(xh);
	}
}
