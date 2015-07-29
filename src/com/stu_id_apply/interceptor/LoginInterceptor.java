package com.stu_id_apply.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String name = invocation.getInvocationContext().getName();
		if (name.equals("login")) {
			System.out.print("Is login Action"+"\n");
			return invocation.invoke();
		} else {
			ActionContext ac = invocation.getInvocationContext();
			Map session = (Map) ac.get(ServletActionContext.SESSION);
			if (session == null) {		//sessionΪ��
				System.out.print("no session");
				return "login";
			} else {					//session��Ϊ�գ�usernameΪ��
				String username = (String) session.get("username");
				if (username == null) {
					System.out.print("no session.name");
					return "login";
				} else {
					return invocation.invoke();
				}
			}

		}
	}
}
