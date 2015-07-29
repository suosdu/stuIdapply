package com.stu_id_apply.dao;

import java.sql.SQLException;
import java.util.List;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Hmd;

public class HmdDao {
	public List select()
	{
		try {
			return SqlMapClientFactory.getSqlMapClient().queryForList("Hmd.select");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void delete(Hmd vo)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().delete("Hmd.delete",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void insert(Hmd vo)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().insert("Hmd.insert",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
