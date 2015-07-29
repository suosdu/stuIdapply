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
<title>违约记录管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 
		});		
</script>
</head>
<body>
	
	<table border=1 width=80% style="border-collapse:collapse;margin-left:10%;margin-right:10%;">
	<caption>黑名单</caption>
		<tr>
			<th>序号</th>
			<th>学号</th>
			<th>姓名</th>
			<th>申请日期</th>
			<th>证卡类型号</th>
			<th>证卡类型</th>
			<th>拉黑日期</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${hmdlist}" var="hmd" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${hmd.xh }</td>
				<td>${hmd.xm }</td>
				<td>${hmd.sqrq }</td>
				<td>${hmd.zklxh }</td>
				<td>${hmd.zklx }</td>
				<td>${hmd.lhrq }</td>
				<td><input type="button"  value="移除" 
				onclick=" if (window.confirm('确定移除出黑名单?')) 
				  location.href='deleteBlcRecord.action?hmd.xh=${hmd.xh}&hmd.zklxh=${hmd.zklxh}';" /></td>
			</tr>
		</c:forEach>		
	</table>
</body>
</html>