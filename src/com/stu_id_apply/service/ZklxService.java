package com.stu_id_apply.service;

import java.util.List;

import com.stu_id_apply.dao.ZklxDao;
import com.stu_id_apply.vo.Zklx;

public class ZklxService {
	public List<Zklx> query()
	{
		ZklxDao zklxdao = new ZklxDao();
		Zklx zklx = new Zklx();
		return  zklxdao.select(zklx);
	}
	
	public Zklx queryBy(String zklxh)
	{
		ZklxDao zklxdao = new ZklxDao();
		Zklx zklx = new Zklx();
		zklx.setZklxh(zklxh);
		return  zklxdao.select(zklx).get(0);
	}
}
