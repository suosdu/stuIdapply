package com.stu_id_apply.dao;

import java.sql.SQLException;
import java.util.List;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Jlb;

public class JlbDao {
	public List select(Jlb vo)
	{
		List bjblist= null;
		try {
			bjblist =  SqlMapClientFactory.getSqlMapClient().queryForList("Jlb.select", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
		return bjblist;
	}
	
	public int selectNum(Jlb vo)
	{
		try {
			return (Integer)SqlMapClientFactory.getSqlMapClient().queryForObject("Jlb.select_num", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	public Jlb join_select(String xh)
	{
		try {
			return (Jlb)SqlMapClientFactory.getSqlMapClient().queryForObject("Jlb.join_select", xh);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void insert(Jlb vo)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().insert("Jlb.insert", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void update_status(Jlb vo)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().update("Jlb.update_status", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
