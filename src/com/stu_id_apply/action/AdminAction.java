package com.stu_id_apply.action;


import java.io.UnsupportedEncodingException;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.stu_id_apply.service.BjbService;
import com.stu_id_apply.service.CcxxbService;
import com.stu_id_apply.service.HmdService;
import com.stu_id_apply.service.JlbService;
import com.stu_id_apply.service.XsbService;
import com.stu_id_apply.service.XtszService;
import com.stu_id_apply.service.YxnjService;
import com.stu_id_apply.service.ZklxService;
import com.stu_id_apply.service.ZybService;
import com.stu_id_apply.util.DateOperation;
import com.stu_id_apply.vo.Bjb;
import com.stu_id_apply.vo.Ccxxb;
import com.stu_id_apply.vo.Hmd;
import com.stu_id_apply.vo.Jlb;
import com.stu_id_apply.vo.Xsb;
import com.stu_id_apply.vo.Xtsz;
import com.stu_id_apply.vo.Yxnj;
import com.stu_id_apply.vo.Zyb;

public class AdminAction {
	private Xtsz xtsz;
	private List<Jlb> jlblist;
	private List<Xsb> xsblist;
	private List<Hmd> hmdlist;
	private List<String> ifChoose;
	private List<Zyb> zyblist;
	private List<Bjb> bjblist;
	private List<Ccxxb> ccxxblist;
	private List<Yxnj> yxnjlist;
	private Jlb jlb =new Jlb();
	private Hmd hmd;
	private String message;
	private String nj;
	private Ccxxb ccxxb = new Ccxxb();
	private XtszService xtszservice = new XtszService();
	private JlbService jlbservice = new JlbService();
	private HmdService hmdservice = new HmdService();
	private ZklxService zklxservice = new ZklxService();
	private CcxxbService ccxxbservice = new CcxxbService();
	private XsbService xsbservice = new XsbService();
	private ZybService zybservice = new ZybService();
	private BjbService bjbservice = new BjbService();
	private YxnjService yxnjservice = new YxnjService();
	
	public String showSystemInfo(){//显示系统状态，允许申请年级，乘车信息
		/*-------------------------------------------------------------------*/
		xtsz = xtszservice.queryBy("系统状态");		
		/*-------------------------------------------------------------------*/
		yxnjlist=yxnjservice.query();
		/*-------------------------------------------------------------------*/
		xsblist = xsbservice.query();
		if(ccxxb.getBm()!=null){
			try {
				ccxxb.setBm(new String(ccxxb.getBm().getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ccxxb.getXh()!=null)
			ccxxb.getXh().trim();
		if(ccxxb.getXsh()!=null){//（刚进入此页面，不执行）
			ccxxblist = ccxxbservice.joinQuery(ccxxb);//查询乘车信息记录
			zyblist = zybservice.queryBy(ccxxb.getXsh());//查询当前选中学院下的专业，以便保持选中状态
			if(ccxxb.getZyh()!=null)
				bjblist = bjbservice.queryBy(ccxxb.getXsh(), ccxxb.getZyh());//查询当前选中学院专业下的班级，以便保持选中状态
		//System.out.println("ccxxblist.size()"+ccxxblist.size());
		}
		return "success";
	}
	
	public String changeStatus(){//更改系统状态
		try {
			xtsz.setParamname(new String(xtsz.getParamname().getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xtszservice.updateBy(xtsz.getParamname(),xtsz.getStatus());
		return "success";
	}
	
	public String deleteYxnj()
	{
		yxnjservice.delete(nj);
		return "success";
	}
	public String addYxnj()
	{
		yxnjservice.add(nj);
		return "success";
	}
		
	public String showMajor()
	{
		zyblist = zybservice.queryBy(ccxxb.getXsh());
		return "success";
	}
	
	public String showClass()
	{
		bjblist = bjbservice.queryBy(ccxxb.getXsh(), ccxxb.getZyh());
		System.out.println(bjblist.size());
		return "success";
	}

	public String updateCcxx()//更改单条乘车信息
	{
		System.out.println(ccxxb.getXh()+ccxxb.getQsz()+ccxxb.getZdz());
		ccxxbservice.insertORupdate(ccxxb.getXh(),ccxxb.getQsz(), ccxxb.getZdz(),(String)ActionContext.getContext().getSession().get("username"));
		ccxxb = ccxxbservice.queryBy(ccxxb.getXh());//修改后再读出以显示
		System.out.println(ccxxb.getQsz()+ccxxb.getZdz());
		return "success";
	}
	
	public String deleteCcxx()//删除所选乘车信息记录
	{
		for(String xh:ifChoose){
			ccxxbservice.deleteBy(xh);
		}
		return "success";
	}
	
//////////////////////////////////////////////////////////////////////////////	
	public String showLastWeek(){//显示制作名单
		xhUtil(jlb);
		jlblist = jlbservice.queryByDqzt(jlb,"1");//状态为1
		jlblist = jlbservice.queryByDqzt(jlb,"1");
		int num1=jlbservice.count(jlb, "1","1");
		int num2=jlbservice.count(jlb, "1","2");
		int num3=jlbservice.count(jlb, "1","3");
		ActionContext.getContext().put("num1", num1);
		ActionContext.getContext().put("num2", num2);
		ActionContext.getContext().put("num3", num3);
		return "success";
	}
	
	public String showThisWeek(){//显示当前申请名单
		xhUtil(jlb);
		jlblist = jlbservice.queryByDqzt(jlb,"0");
		int num1=jlbservice.count(jlb, "0","1");
		int num2=jlbservice.count(jlb, "0","2");
		int num3=jlbservice.count(jlb, "0","3");
		ActionContext.getContext().put("num1", num1);
		ActionContext.getContext().put("num2", num2);
		ActionContext.getContext().put("num3", num3);
		return "success";
	}
	

	
	public String statistics()//统计数据，0->1
	{
		for(String s:ifChoose){
			String xh=s.substring(0, s.lastIndexOf("+"));
			String zklxh =s.substring(s.lastIndexOf("+")+1, s.length());
//			System.out.println("xh:"+xh+"zklxh:"+zklxh);
			jlbservice.updateStatus_statistics(xh,zklxh);
		}
		return "success";
	}
	public String generateIssue()//生成发放名单，1->2
	{
		for(String s:ifChoose){
			String xh=s.substring(0, s.lastIndexOf("+"));
			String zklxh =s.substring(s.lastIndexOf("+")+1, s.length());
			jlbservice.updateStatus_Made(xh,zklxh);
		}
		return "success";
	}
	
	public String showIssueList(){//查询待发放名单
//		if("ensure".equals(sign))
//			jlb = new Jlb();//新建一个，属性值全为空

		xhUtil(jlb);//预处理xh

		jlblist = jlbservice.queryByDqzt(jlb,"2");
		return "success";
	}
	
//	public String ensureIssue(){//“确认发放”按钮,2->3
//		jlbservice.updateStatus_Issue(jlb.getXh(), jlb.getZklxh());
//
//		sign = "ensure";
//		return "success";
//	}
	
	public String issueAll()//批量发放
	{
		for(String s:ifChoose){
			s=s.substring(0, s.lastIndexOf("#"));
			String xh=s.substring(0, s.lastIndexOf("+"));
			String zklxh =s.substring(s.lastIndexOf("+")+1, s.length());
			jlbservice.updateStatus_Issue(xh,zklxh);
		}
		return "success";
	}
	
	public String emergencySearch()//紧急办理 查询该生当前记录
	{
		jlb.setXh(jlb.getXh().trim());
		
		jlblist = jlbservice.queryEmergency(jlb.getXh(), "0");
		List list1 = jlbservice.queryEmergency(jlb.getXh(), "1");
		List list2 = jlbservice.queryEmergency(jlb.getXh(), "2");
		jlblist.addAll(list1);
		jlblist.addAll(list2);
		
		if(jlblist== null||jlblist.size()==0)
			message = "查无此人";

		return "success";
	}
	
	public String emergencyIssue()//紧急制作完成 发放
	{
		jlbservice.updateStatus_Emergency(jlb.getXh(), jlb.getZklxh());
		return "success";
	}
	
	public String showHisRecord(){//显示历史记录
		if("".equals(jlb.getDqzt()))
			jlb.setDqzt(null);

		xhUtil(jlb);//预处理xh
		
		jlblist = jlbservice.queryHis(jlb);
		return "success";
	}
	
	public String showBlackList(){//显示黑名单
		hmdlist = hmdservice.query();
		return "success";
	}
	
//	public String insertIntoBlack()//加入黑名单
//	{
//		jlbservice.updateStatus_IntoBlack(hmd.getXh(), hmd.getZklxh());//2->4
//		try {
//			hmd.setXm(new String(hmd.getXm().getBytes("ISO-8859-1"), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		hmd.setZklx(zklxservice.queryBy(hmd.getZklxh()).getZklx());
//		hmd.setLhrq(DateOperation.getYYYYMMDD());
//		hmdservice.insert(hmd);
//		return "success";
//	}
	
	public String blackAll()//批量拉黑
	{
		for(String s:ifChoose){
			String s1=s.substring(0,s.lastIndexOf("#"));
			String s2=s.substring(s.lastIndexOf("#")+1,s.length());

			String xh=s1.substring(0, s1.lastIndexOf("+"));
			String zklxh =s1.substring(s1.lastIndexOf("+")+1, s1.length());
			String sqrq=s2.substring(0, s2.lastIndexOf("+"));
			String xm =s2.substring(s2.lastIndexOf("+")+1, s2.length());
			try {
				xm = new String(xm.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(xh+"\t"+zklxh+"\t"+sqrq+"\t"+xm);
			
			jlbservice.updateStatus_IntoBlack(xh, zklxh);
			//------------------------------------------------
			hmd = new Hmd();
			hmd.setXh(xh);
			hmd.setZklxh(zklxh);
			hmd.setZklx(zklxservice.queryBy(zklxh).getZklx());
			hmd.setSqrq(sqrq);
			hmd.setLhrq(DateOperation.getYYYYMMDD());
			hmd.setXm(xm);
			hmdservice.insert(hmd);
		}
		return "success";
	}
	
	public String deleteBlcRecord(){//从黑名单中删除某条记录
		jlbservice.updateStatus_QuitBlack(hmd.getXh(), hmd.getZklxh());	//4->5 将Jlb对应记录状态由4为更新“5”
		hmdservice.deleteHmd(hmd.getXh(), hmd.getZklxh());//删除记录
		return "success";
	}


	void xhUtil(Jlb vo)//查询时，先预处理jlb.xh
	{
		if("".equals(vo.getXh())||vo.getXh()!=null&&"".equals(vo.getXh().trim())){////无输入点查询或输入空格点查询
			vo.setXh(null);
		}
		else if(vo.getXh()!=null)
			vo.setXh(vo.getXh().trim());
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////	
	public Xtsz getXtsz() {
		return xtsz;
	}

	public void setXtsz(Xtsz xtsz) {
		this.xtsz = xtsz;
	}

	public List<Jlb> getJlblist() {
		return jlblist;
	}

	public void setJlblist(List<Jlb> jlblist) {
		this.jlblist = jlblist;
	}

	public Jlb getJlb() {
		return jlb;
	}

	public void setJlb(Jlb jlb) {
		this.jlb = jlb;
	}

	public List<Hmd> getHmdlist() {
		return hmdlist;
	}

	public void setHmdlist(List<Hmd> hmdlist) {
		this.hmdlist = hmdlist;
	}

	public Hmd getHmd() {
		return hmd;
	}

	public void setHmd(Hmd hmd) {
		this.hmd = hmd;
	}

	public List<String> getIfChoose() {
		return ifChoose;
	}

	public void setIfChoose(List<String> ifChoose) {
		this.ifChoose = ifChoose;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Xsb> getXsblist() {
		return xsblist;
	}

	public void setXsblist(List<Xsb> xsblist) {
		this.xsblist = xsblist;
	}

	public List<Zyb> getZyblist() {
		return zyblist;
	}

	public void setZyblist(List<Zyb> zyblist) {
		this.zyblist = zyblist;
	}

	public List<Bjb> getBjblist() {
		return bjblist;
	}

	public void setBjblist(List<Bjb> bjblist) {
		this.bjblist = bjblist;
	}

	public Ccxxb getCcxxb() {
		return ccxxb;
	}

	public void setCcxxb(Ccxxb ccxxb) {
		this.ccxxb = ccxxb;
	}

	public List<Ccxxb> getCcxxblist() {
		return ccxxblist;
	}

	public void setCcxxblist(List<Ccxxb> ccxxblist) {
		this.ccxxblist = ccxxblist;
	}

	public List<Yxnj> getYxnjlist() {
		return yxnjlist;
	}

	public void setYxnjlist(List<Yxnj> yxnjlist) {
		this.yxnjlist = yxnjlist;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}
//
//	public String getSign() {
//		return sign;
//	}
//
//	public void setSign(String sign) {
//		this.sign = sign;
//	}
	
}
