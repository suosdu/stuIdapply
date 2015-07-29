package com.stu_id_apply.service;

import java.util.List;

import com.stu_id_apply.dao.ZybDao;
import com.stu_id_apply.vo.Zyb;

public class ZybService {

	public List queryBy(String xsh)
	{
		ZybDao zybdao = new ZybDao();
		Zyb zyb = new Zyb();
		zyb.setXsh(xsh);
		return zybdao.select(zyb);
	}
}
