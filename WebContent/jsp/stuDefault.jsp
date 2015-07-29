<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
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
<title>违约记录</title>
</head>
<body>
	<c:choose>
	  <c:when test="${fn:length(jlblist)>0}"> 
			<table border=1 width=80% style=border-collapse:collapse;margin-left:10%;margin-right:10%;>
				<tr>
					<th>申请日期</th>
					<th>证卡类型</th>
		<!--			<th>拉黑日期</th>-->
				</tr>
				<c:forEach items="${jlblist}" var="jlb">
					<tr>
						<td>${jlb.sqrq }</td>
						<td>${jlb.zklx }</td>
					</tr>
				</c:forEach>
			</table>
	  </c:when>
	  <c:otherwise>
	  		无违约记录
	  </c:otherwise>
	 </c:choose>
</body>
</html>