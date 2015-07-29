package com.stu_id_apply.service;

import com.stu_id_apply.dao.TJwcXjZpbDao;
import com.stu_id_apply.vo.TJwcXjZpb;

public class TJwcXjZpbService {
	
	public TJwcXjZpb queryBy(String username){
		TJwcXjZpbDao zpbdao =new TJwcXjZpbDao();
		TJwcXjZpb zpb = new TJwcXjZpb();
		zpb.setXh(username);
		return zpbdao.select(zpb);
	}
}
