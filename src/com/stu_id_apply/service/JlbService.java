package com.stu_id_apply.service;

import java.util.List;
import com.stu_id_apply.dao.JlbDao;
import com.stu_id_apply.util.DateOperation;
import com.stu_id_apply.vo.Jlb;

public class JlbService {
	@SuppressWarnings("unchecked")
	public String queryStuStatus(String xh)//从该生所有申请记录中判断当前状态
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb= new Jlb();
		jlb.setXh(xh);
		jlb.setJllx("1");
		List<Jlb> jlblist = jlbdao.select(jlb);
		if(jlblist==null||jlblist.isEmpty())//没有补办过，无记录
		{
			return "无记录";
		}else{
			String applydate="20140101";
			String dqzt="";
			int j=0;
			for(Jlb vo:jlblist)//从可能多条的记录中选出最近的申请日期
			{
				if(vo.getSqrq().compareTo(applydate)>0)
					applydate=vo.getSqrq();
			}

			for(Jlb vo:jlblist)//若最近一次同时申请了多条，用此方法判断学生状态
			{
				if(vo.getSqrq().compareTo(applydate)==0)
				{
					if("4".equals(vo.getDqzt()))
					{
						return "4";
					}else if("0".equals(vo.getDqzt())||"1".equals(vo.getDqzt())||"2".equals(vo.getDqzt())){
						dqzt = vo.getDqzt();
						j=1;
					}else if(j==0){
						dqzt = vo.getDqzt();
					}
				}
			}
			return dqzt;
		}			
	}
	
	public Jlb joinQuery(String xh)
	{
		JlbDao jlbdao = new JlbDao();
		return jlbdao.join_select(xh);
	}
	
	public void insert(Jlb vo)
	{
		JlbDao jlbdao = new JlbDao();
		jlbdao.insert(vo);
	}
	@SuppressWarnings("unchecked")	
	public List queryStuHistory(String xh)
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setXh(xh);
		jlb.setJllx("1");
		return jlbdao.select(jlb);
	}
	

	@SuppressWarnings("unchecked")
	public List queryStuDefault(String xh)
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setXh(xh);
		jlb.setJllx("1");
		jlb.setDqzt("4");
		List blackList = jlbdao.select(jlb);
		jlb.setDqzt("5");
		List defaultList = jlbdao.select(jlb);
		defaultList.addAll(blackList);
		return defaultList;
	}
	
	public List queryByDqzt(Jlb jlb,String dqzt)
	{
		JlbDao jlbdao = new JlbDao();
		jlb.setJllx("1");
		jlb.setDqzt(dqzt);
		return jlbdao.select(jlb);
	}
	
	public int count(Jlb jlb,String dqzt,String zklxh)
	{
		JlbDao jlbdao = new JlbDao();
		jlb.setJllx("1");
		jlb.setDqzt(dqzt);
		jlb.setZklxh(zklxh);
		return jlbdao.selectNum(jlb);
	}
	
	public void updateStatus_statistics(String xh,String zklxh)
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setDqzt("0");
		jlb.setDqzt2("1");
		jlb.setXh(xh);
		jlb.setZklxh(zklxh);
		jlbdao.update_status(jlb);
	}
	
	public void updateStatus_Made(String xh,String zklxh)//更新“制作完成”的当前记录状态
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setDqzt("1");
		jlb.setDqzt2("2");
		jlb.setXh(xh);
		jlb.setZklxh(zklxh);
		jlbdao.update_status(jlb);
	}
	
	public void updateStatus_Issue(String xh,String zklxh)//发放后更新当前记录状态
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setXh(xh);
		jlb.setZklxh(zklxh);
		jlb.setDqzt("2");
		jlb.setDqzt2("3");
		jlb.setQkrq(DateOperation.getYYYYMMDD());
		jlbdao.update_status(jlb);
	}
	
	public void  updateStatus_Emergency(String xh,String zklxh)
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setXh(xh);
		jlb.setZklxh(zklxh);
		jlb.setDqzt2("3");
		jlb.setQkrq(DateOperation.getYYYYMMDD());
		jlbdao.update_status(jlb);
	}
	
	public void updateStatus_IntoBlack(String xh,String zklxh)
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setXh(xh);
		jlb.setZklxh(zklxh);
		jlb.setDqzt("2");
		jlb.setDqzt2("4");
		jlbdao.update_status(jlb);
	}
	
	public void updateStatus_QuitBlack(String xh,String zklxh)
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setXh(xh);
		jlb.setZklxh(zklxh);
		jlb.setDqzt("4");
		jlb.setDqzt2("5");
		jlb.setQkrq(DateOperation.getYYYYMMDD());
		jlbdao.update_status(jlb);
	}
	
	
	public List queryHis(Jlb jlb)
	{
		JlbDao jlbdao = new JlbDao();
		jlb.setJllx("1");
		return jlbdao.select(jlb);
	}
	
	public List queryEmergency(String xh,String dqzt)
	{
		JlbDao jlbdao = new JlbDao();
		Jlb jlb = new Jlb();
		jlb.setJllx("1");
		jlb.setXh(xh);
		jlb.setDqzt(dqzt);
		return jlbdao.select(jlb);
	}
	

}