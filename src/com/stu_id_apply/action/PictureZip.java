package com.stu_id_apply.action;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.stu_id_apply.service.JlbService;
import com.stu_id_apply.service.TJwcXjZpbService;
import com.stu_id_apply.vo.Jlb;
import com.stu_id_apply.vo.TJwcXjZpb;

public class PictureZip {
	private Jlb jlb =new Jlb();
	private List<Jlb> jlblist;
	private JlbService jlbservice = new JlbService();
	private TJwcXjZpbService zpbservice=new TJwcXjZpbService();
	private OutputStream res;
	private ZipOutputStream zos;
	
	public String execute() throws Exception {
		
//		if (bmb != null && bmb.getBkjbyy() != null
//				&& !"".equals(bmb.getBkjbyy())) {
			// 预处理
			preProcess();
//		} else {
//			message = "请先选择考试语种";
//			return "error";
//		}

		outputZipFile();

		afterProcess();

		return null;

	}

	// 预处理
	public void preProcess() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();

		res = response.getOutputStream();

		// 清空输出流
		response.reset();

		// 设定输出文件头
		response.setHeader("Content-disposition",
				"attachment;filename=exportPics.zip");
//		response.setContentType("application/octet-stream;charset=ISO8859-1");
		response.setContentType("application/zip");
//		response.setCharacterEncoding("ISO8859-1");
		zos = new ZipOutputStream(res);
	}

	// 后处理
	public void afterProcess() throws Exception {

		zos.close();
		res.close();
	}
	
	@SuppressWarnings("unchecked")
	private void outputZipFile() throws IOException, FileNotFoundException {
		ZipEntry ze = null;
		byte[] bt=null;
		TJwcXjZpb tb=null;
		byte[] buf = new byte[1024];

		jlblist = jlbservice.queryByDqzt(jlb,"1");
			for (int j = 0; j < jlblist.size(); j++) {
				try {	
						tb = zpbservice.queryBy(jlblist.get(j).getXh());
						bt=tb.getBlobdata();//从库里读图片
						String  outputFile=jlblist.get(j).getXh()+".jpg";
//						File file = getFileFromBytes(bt,outputFile);
						int readLen = 0;
						// 1.动态压缩一个File到zip中
						// 创建一个ZipEntry，并设置Name和其它的一些属性
						// 压缩包中的路径和文件名称
						ze = new ZipEntry(outputFile);
//						ze.setSize(file.length());
//						ze.setTime(file.lastModified());
						// 将ZipEntry加到zos中，再写入实际的文件内容
						zos.putNextEntry(ze);
						InputStream is = new ByteArrayInputStream(bt); 

						// 把数据写入到客户端
						while ((readLen = is.read(buf, 0, 1024)) != -1) {
							zos.write(buf, 0, readLen);
						}
						is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
	}
	public Jlb getJlb() {
		return jlb;
	}

	public void setJlb(Jlb jlb) {
		this.jlb = jlb;
	}
	public List<Jlb> getJlblist() {
		return jlblist;
	}

	public void setJlblist(List<Jlb> jlblist) {
		this.jlblist = jlblist;
	}
}
