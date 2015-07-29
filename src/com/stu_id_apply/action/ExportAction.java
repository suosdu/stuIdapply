package com.stu_id_apply.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.stu_id_apply.service.JlbService;
import com.stu_id_apply.util.ExcelOper;
import com.stu_id_apply.vo.Jlb;

public class ExportAction {
	private List<Jlb> jlblist;
	private String fileName;
	private Jlb jlb =new Jlb();
	private String inputPath;
	
	private JlbService jlbservice = new JlbService();
	
	public String exportData ()throws Exception//导出制作申请名单
	{
		jlblist = jlbservice.queryByDqzt(jlb,"1");
		export();

		this.inputPath = this.inputPath + this.fileName;

		String downloadDir = ServletActionContext.getServletContext()
				.getRealPath("/download");

		System.out.print(this.inputPath);
		String downloadFile = ServletActionContext.getServletContext()
				.getRealPath(this.inputPath);

		File file = new File(downloadFile);

		downloadFile = file.getCanonicalPath();

		if (!(downloadFile.startsWith(downloadDir))) {
			return null;
		}

		return "success";
	}
	
	public void export() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx
				.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		if(jlb.getZklxh()==null){
			this.fileName = "制作名单.xls";
		}else if("1".equals(jlb.getZklxh())){
			this.fileName = "学生证制作名单.xls";
		}else if("2".equals(jlb.getZklxh())){
			this.fileName = "乘车优惠卡制作名单.xls";
		}else if("3".equals(jlb.getZklxh())){
			this.fileName = "校徽制作名单.xls";
		}
		String saveURL = application.getRealPath("/") + "download/"
				+ this.fileName;
		new ExcelOper().exportJlb(jlblist, saveURL);

	}
	
	public InputStream getInputStream() throws Exception {
		return ServletActionContext.getServletContext().getResourceAsStream(
				this.inputPath);
	}
	
	public String getDownloadFileName() {
		String downFileName = this.fileName;
		try {
			downFileName = new String(downFileName.getBytes("gb2312"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("\ndownFileName:"+downFileName);//此处虽乱码，struts.xml里处理后正常了
		return downFileName;
	}

	public List<Jlb> getJlblist() {
		return jlblist;
	}

	public void setJlblist(List<Jlb> jlblist) {
		this.jlblist = jlblist;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Jlb getJlb() {
		return jlb;
	}

	public void setJlb(Jlb jlb) {
		this.jlb = jlb;
	}
	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
}
