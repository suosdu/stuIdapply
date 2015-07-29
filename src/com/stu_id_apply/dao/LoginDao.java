package com.stu_id_apply.dao;

import java.sql.SQLException;

import com.stu_id_apply.util.SqlMapClientFactory;
import com.stu_id_apply.vo.Login;

public class LoginDao{
	public Login select(Login vo){
		try {
			return (Login)SqlMapClientFactory.getSqlMapClient().queryForObject("Login.select", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage(),e);
		}
	}

}