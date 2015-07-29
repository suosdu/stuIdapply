package com.stu_id_apply.dao;

import java.sql.SQLException;
import java.util.List;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Zklx;

public class ZklxDao {
	public List<Zklx> select(Zklx vo)
	{
		List<Zklx> zklxlist = null;
		try {
			zklxlist = SqlMapClientFactory.getSqlMapClient().queryForList("Zklx.select", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
		return zklxlist;
	}
}
