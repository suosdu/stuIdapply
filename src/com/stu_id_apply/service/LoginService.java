package com.stu_id_apply.service;

import com.stu_id_apply.dao.LoginDao;
import com.stu_id_apply.vo.Login;

public class LoginService {
	public Login queryBy(String username,String system){
		Login vo = new Login();
		vo.setUsername(username);
		vo.setSystem(system);
		LoginDao logindao = new LoginDao();
		return logindao.select(vo);
	}
}
