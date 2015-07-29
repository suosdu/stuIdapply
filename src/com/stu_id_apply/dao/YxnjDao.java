package com.stu_id_apply.dao;

import java.sql.SQLException;
import java.util.List;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Yxnj;

public class YxnjDao {
	public List select(Yxnj vo)
	{
		try {
			return SqlMapClientFactory.getSqlMapClient().queryForList("Yxnj.select",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void insert(String nj)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().insert("Yxnj.insert",nj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void delete(String nj)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().delete("Yxnj.delete",nj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
