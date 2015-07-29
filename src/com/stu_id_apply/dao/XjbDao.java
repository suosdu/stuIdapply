package com.stu_id_apply.dao;

import java.sql.SQLException;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Xjb;

public class XjbDao {
	public Xjb select(Xjb vo){
		try {
			return (Xjb)SqlMapClientFactory.getSqlMapClient().queryForObject("Xjb.selectByCategory", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public String selectBm(String xh)
	{
		try {
			return (String)SqlMapClientFactory.getSqlMapClient().queryForObject("Xjb.selectBm", xh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
