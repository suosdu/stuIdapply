package com.stu_id_apply.dao;

import java.sql.SQLException;
import java.util.List;

import com.stu_id_apply.util.SqlMapClientFactory;

public class XsbDao {
	public List select()
	{
		try {
			return SqlMapClientFactory.getSqlMapClient().queryForList("Xsb.select");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}		
	}
}
