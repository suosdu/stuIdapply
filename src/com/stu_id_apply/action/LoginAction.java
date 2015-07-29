package com.stu_id_apply.action;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.jasig.cas.client.authentication.AttributePrincipal;
import com.opensymphony.xwork2.ActionContext;
import com.stu_id_apply.service.LoginService;
import com.stu_id_apply.service.XjbService;
import com.stu_id_apply.service.XtszService;
import com.stu_id_apply.vo.Login;
import com.stu_id_apply.vo.Xjb;
import com.stu_id_apply.vo.Xtsz;


public class LoginAction {
	
	private String username;
	private String message;
	private String role;
	
	public String login(){
		System.out.println("Entered login"+"\n");
		ActionContext ac = ActionContext.getContext();

		Map session = (Map) ac.get("com.opensymphony.xwork2.ActionContext.session");
		HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
		AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
		if (principal != null) {
			this.username = principal.getName();
			System.out.println("CAS获得username："+username+"\t");//20068001171	null
			ActionContext.getContext().getSession().put("username",this.username);
			if (username != null && session != null
					&& (session.get("username") != null)
					&& (session.get("role") != null)) 
			{
				if (ActionContext.getContext().getSession().get("role").equals(
						"0")) {
					return "adminLogin";
				}
				if (ActionContext.getContext().getSession().get("role").equals(
						"1")) {
					return "role1Login";
				}
			} else {//输入工号密码时先跳至此处

				LoginService loginservice = new LoginService();
				Login lgn = loginservice.queryBy(username,"stuid");
				if(lgn!=null){//管理员登录			
					this.role=lgn.getRole();
					System.out.print("admin刚刚登录,Role:"+role+"\t");
				}
				else {//普通学生
					XjbService xjbservice =new XjbService();
					Xjb xjb = xjbservice.queryBy(this.username,"是", "是");//是否有学籍，是否由国家学籍
					if(xjb!=null){//有记录才可使用
						this.role="1";
					}else{
						message = "只有在校本科生才可使用本系统！";
						return "input";
					}
				}	
				System.out.println(username+"\t");
				ActionContext.getContext().getSession().put("username",this.username);
				ActionContext.getContext().getSession().put("role", role);
				if (this.role.equals("0")) {
					return "adminLogin";
				}
				if (this.role.equals("1")) {
					return "role1Login";
				}
			}
		}
		message = "您还未登录";
		return "input";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
	
}
