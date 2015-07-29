package com.stu_id_apply.dao;

import java.sql.SQLException;
import java.util.List;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Ccxxb;

public class CcxxbDao {
	public Ccxxb select(Ccxxb vo)
	{
		try {
			return (Ccxxb)SqlMapClientFactory.getSqlMapClient().queryForObject("Ccxxb.select", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public List join_select(Ccxxb vo)
	{
		try {
			return SqlMapClientFactory.getSqlMapClient().queryForList("Ccxxb.join_select",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void insertORupdate(Ccxxb vo)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().update("Ccxxb.insertORupdate", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void delete(Ccxxb vo)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().delete("Ccxxb.delete", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
