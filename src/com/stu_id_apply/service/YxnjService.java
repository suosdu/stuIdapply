package com.stu_id_apply.service;

import java.util.List;

import com.stu_id_apply.dao.YxnjDao;
import com.stu_id_apply.vo.Yxnj;

public class YxnjService {
	private YxnjDao dao = new YxnjDao();
	public List query()
	{	Yxnj yxnj = new Yxnj();	
		return dao.select(yxnj);
	}
	
	public List queryBy(String nj)
	{
		Yxnj yxnj= new Yxnj();
		yxnj.setNj(nj);
		return dao.select(yxnj);
	}
	public void add(String nj)
	{
		dao.insert(nj);
	}
	
	public void delete(String nj)
	{
		dao.delete(nj);
	}
}
