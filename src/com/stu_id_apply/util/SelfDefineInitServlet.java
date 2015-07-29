package com.stu_id_apply.util;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.xml.DOMConfigurator;


public class SelfDefineInitServlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException{
        super.init(config);//�������Ŀ������struts2�ȿ�ܣ���һ������С�
        //�������... ...
        //log4j��ʼ��System.setPropertySystem.setProperty
//        System.setProperty("path_of_logs", "E:/apache-tomcat-6.0.18/logs"); //������־��ŵ�·��
//        DOMConfigurator.configure("E:/apache-tomcat-6.0.18/conf/log4j.xml");//����log4j�����ļ�
        //�������... ...
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j"); 
        System.out.println("--------  Log4J Start  --------- ");
        if (file != null) {
         DOMConfigurator.configure(prefix + file);
        }
}
    protected void doGet(HttpServletRequest request,
    		   HttpServletResponse response) throws ServletException, IOException {
    		 }

}
