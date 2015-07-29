package com.stu_id_apply.action;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.stu_id_apply.service.TJwcXjZpbService;
import com.stu_id_apply.vo.TJwcXjZpb;
import com.stu_id_apply.service.CcxxbService;
import com.stu_id_apply.service.JlbService;
import com.stu_id_apply.service.XtszService;
import com.stu_id_apply.service.YxnjService;
import com.stu_id_apply.service.ZklxService;
import com.stu_id_apply.vo.Ccxxb;
import com.stu_id_apply.vo.Jlb;
import com.stu_id_apply.vo.Xtsz;
import com.stu_id_apply.vo.Yxnj;
import com.stu_id_apply.vo.Zklx;
import com.stu_id_apply.util.DateOperation;

public class StuAction {

	private String message;
	private String whetherPermit;
	private Xtsz xtsz;
	private String dqzt;
	private String xh =(String)ActionContext.getContext().getSession().get("username");
	private Jlb jlb;
	private Ccxxb ccxxb;
	private String username;
	private List<Zklx> zklxlist;
	private List<String> choice;
	private List<Jlb> jlblist;
	private Yxnj yxnj;
	
	private XtszService xtszservice = new XtszService();
	private JlbService jlbservice = new JlbService();
	private CcxxbService ccxxbservice = new CcxxbService();
	private ZklxService zklxservice = new ZklxService();
	private TJwcXjZpbService zpbservice=new TJwcXjZpbService();
	private YxnjService yxnjservice = new YxnjService();
	
	public String showMainInfo()
	{	
		whetherPermit="0";
		/*---------显示状态信息----------*/
		/*系统状态*/
		xtsz = xtszservice.queryBy("系统状态");
		System.out.println("xtsz.getParamname():"+xtsz.getParamname()+"\t xtsz.getStatus():"+xtsz.getStatus());		
		/*学生当前状态*/
		dqzt = jlbservice.queryStuStatus(xh);
		System.out.println("当前状态dqzt:"+dqzt);
		
		/*---------学生基本信息----------*/
		/*学籍信息*/
		jlb = jlbservice.joinQuery(xh);		
		/*查出乘车信息*/
		ccxxb = ccxxbservice.queryBy(xh);
		/*判断本年级是否可以补办*/
		List<Yxnj>yxnjlist = yxnjservice.queryBy(jlb.getSsnj());
		if(yxnjlist!=null&&!yxnjlist.isEmpty()){
			whetherPermit="1";
			System.out.println("yxnjlist.size():"+yxnjlist.size());
		}
   		TJwcXjZpb zp=zpbservice.queryBy(xh);
   		if(zp!=null)
   			message="have_pic";
		/*---------显示补办项-----------*/
		zklxlist = zklxservice.query();
		
		return "success";
	}
	

	
//	public String showBasicInfo()//申请补办，先由此方法显示基本信息
//	{
//		/*先判断状态*/
//		String systemStatus =xtszservice.queryBy("系统状态").getStatus();
//		String currentStatus=jlbservice.queryStuStatus(xh);
//		if("1".equals(systemStatus)&&("无记录".equals(currentStatus)||"3".equals(currentStatus)||"5".equals(currentStatus))){
//			/*学籍信息*/
//			jlb = jlbservice.joinQuery(xh);		
//			/*查出乘车信息*/
//			ccxxb = ccxxbservice.queryBy(xh);
//			return "success";
//		}else{
//			message="当前无法申请,请注意状态信息!";
//			return "failed";
//		}
//
//	}
	
	public String apply()//确认申请
	{
		jlb = jlbservice.joinQuery(xh);
		ccxxb = ccxxbservice.queryBy(xh);
		jlb.setSqrq(DateOperation.getYYYYMMDD());
		jlb.setDqzt("0");
		jlb.setJllx("1");
		jlb.setYxq_ks(jlb.getSsnj()+"09");
		jlb.setYxq_jz((Integer.parseInt(jlb.getSsnj())+4)+"06");
		jlb.setQsz(ccxxb.getQsz());//jlb.set... 日期，区间等
		jlb.setZdz(ccxxb.getZdz());
		for(String s:choice){
			String zklxh=s.substring(0, s.lastIndexOf("+"));
			String zklx=s.substring(s.lastIndexOf("+")+1, s.length());
			jlb.setZklxh(zklxh);
			try {
				jlb.setZklx(new String(zklx.getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try{
				jlbservice.insert(jlb);			//插入记录表
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		message="申请成功";
		return "success";
	}

	public String showHistory(){
		jlblist = jlbservice.queryStuHistory(xh);
		return "success";
	}
	
//	public String showDefault(){
//		jlblist = jlbservice.queryStuDefault(xh);
//		return "success";
//	}

	public String readPhoto(){//从库里照片表读取图片并显示
		System.out.println("enter readPhoto");
		HttpServletResponse resp = ServletActionContext.getResponse();
		try {   
	        OutputStream os = resp.getOutputStream();
  		
	   		TJwcXjZpb zp=zpbservice.queryBy(username);
	   		if(zp==null)
	   			System.out.println("无此照片");
	   		else
	   			os.write(zp.getBlobdata());  
  
	   		os.flush();
	   		os.close();   
	   		
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        }  
		return null;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Xtsz getXtsz() {
		return xtsz;
	}

	public void setXtsz(Xtsz xtsz) {
		this.xtsz = xtsz;
	}

	public String getDqzt() {
		return dqzt;
	}

	public void setDqzt(String dqzt) {
		this.dqzt = dqzt;
	}

	public Ccxxb getCcxxb() {
		return ccxxb;
	}

	public void setCcxxb(Ccxxb ccxxb) {
		this.ccxxb = ccxxb;
	}

	public Jlb getJlb() {
		return jlb;
	}

	public void setJlb(Jlb jlb) {
		this.jlb = jlb;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Zklx> getZklxlist() {
		return zklxlist;
	}

	public void setZklxlist(List<Zklx> zklxlist) {
		this.zklxlist = zklxlist;
	}

	public List<String> getChoice() {
		return choice;
	}

	public void setChoice(List<String> choice) {
		this.choice = choice;
	}

	public List<Jlb> getJlblist() {
		return jlblist;
	}

	public void setJlblist(List<Jlb> jlblist) {
		this.jlblist = jlblist;
	}



	public Yxnj getYxnj() {
		return yxnj;
	}



	public void setYxnj(Yxnj yxnj) {
		this.yxnj = yxnj;
	}



	public String getWhetherPermit() {
		return whetherPermit;
	}



	public void setWhetherPermit(String whetherPermit) {
		this.whetherPermit = whetherPermit;
	}
	
}
