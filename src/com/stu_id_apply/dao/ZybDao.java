package com.stu_id_apply.dao;

import java.sql.SQLException;
import java.util.List;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Zyb;

public class ZybDao {

	public List select(Zyb vo)
	{
		try {
			return SqlMapClientFactory.getSqlMapClient().queryForList("Zyb.select",vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}	
	}
}
