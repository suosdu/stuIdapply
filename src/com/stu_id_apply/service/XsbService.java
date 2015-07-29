package com.stu_id_apply.service;

import java.util.List;

import com.stu_id_apply.dao.XsbDao;

public class XsbService {
	public List query()
	{
		XsbDao xsbdao = new XsbDao();
		return xsbdao.select();
	}
}
