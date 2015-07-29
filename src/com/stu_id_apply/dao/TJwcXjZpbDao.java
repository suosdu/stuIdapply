package com.stu_id_apply.dao;

import java.sql.SQLException;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.TJwcXjZpb;

public class TJwcXjZpbDao {

	public TJwcXjZpb select(TJwcXjZpb zpb) {
		// TODO Auto-generated method stub
		try {
			return (TJwcXjZpb)SqlMapClientFactory.getSqlMapClient().queryForObject("TJwcXjZpb.select", zpb);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}

}
