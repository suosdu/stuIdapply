package com.stu_id_apply.service;

import java.util.List;

import com.stu_id_apply.dao.BjbDao;
import com.stu_id_apply.vo.Bjb;

public class BjbService {

	public List queryBy(String xsh,String zyh)
	{
		BjbDao bjbdao = new BjbDao();
		Bjb bjb = new Bjb();
		bjb.setXsh(xsh);
		bjb.setZyh(zyh);
		return bjbdao.select(bjb);
	}
}
