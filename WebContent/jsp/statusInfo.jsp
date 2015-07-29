<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh" xml:lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>状态信息</title>
</head>
<body>
<div>
<ul>
<li><h3>系统状态</h3></li>
	<c:if test="${xtsz.status == 1}">当前系统开放</c:if>
	<c:if test="${xtsz.status == 0}">当前系统关闭</c:if>
<!--	<c:out value="xtsz.status:${xtsz.status}" />-->
<li><h3>学生状态</h3></li>
	<c:if test="${dqzt == '0'||dqzt == '1'||dqzt == '2'}">您已有申请正在办理中，无法再次申请</c:if>
	<c:if test="${dqzt == '无记录'||dqzt == '3'||dqzt == '5'}">可以正常申请</c:if>
	<c:if test="${dqzt == '4'}">您已进入黑名单，无法申请</c:if>
</ul>
</div>
</body>
</html>