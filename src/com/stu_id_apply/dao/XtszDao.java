package com.stu_id_apply.dao;

import java.sql.SQLException;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Xtsz;

public class XtszDao {
	public Xtsz select(Xtsz vo)
	{
		try {
			return (Xtsz)SqlMapClientFactory.getSqlMapClient().queryForObject("Xtsz.select", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public void update(Xtsz vo)
	{
		try {
			SqlMapClientFactory.getSqlMapClient().update("Xtsz.update", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}
}
