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
<title>历史记录管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#search").click(function(){
		  	$("#form1").attr("action","showHisRecord.action");
		  	$("#form1").submit();
		  }
			);
		});		
</script>
</head>
<body>
<div>
 <form id="form1" method="post">
 <p style="margin-left:10%">
 	当前状态:
	<select name="jlb.dqzt" style="width:100px">
		<option value="">所有</option>
		<option value="0" <c:if test="${0 == jlb.dqzt}">selected="selected"</c:if>>
			<c:out value="已申请" />
		</option>		
		<option value="1" <c:if test="${1 == jlb.dqzt}">selected="selected"</c:if>>
			<c:out value="制作中" />
		</option>
		<option value="2" <c:if test="${2 == jlb.dqzt}">selected="selected"</c:if>>
			<c:out value="待发放" />
		</option>
		<option value="3" <c:if test="${3 == jlb.dqzt}">selected="selected"</c:if>>
			<c:out value="已发放" />
		</option>
		<option value="4" <c:if test="${4 == jlb.dqzt}">selected="selected"</c:if>>
			<c:out value="已拉黑" />
		</option>
		<option value="5" <c:if test="${5 == jlb.dqzt}">selected="selected"</c:if>>
			<c:out value="曾违约" />
		</option>												
	</select>	
	学号:<input type="text" name="jlb.xh" value="${jlb.xh}" />
	<input type="button" id="search" value="查询" />
 </p>
 </form>
 <hr />	
	<table border=1 width=80% style="border-collapse:collapse;margin-left:10%;margin-right:10%;text-align:right">
	<caption>历史记录</caption>
		<tr>
			<th>序号</th>
			<th>学号</th>
			<th>姓名</th>
			<th>申请日期</th>
			<th>发放日期</th>
			<th>证卡类型号</th>
			<th>证卡类型</th>
			<th>当前状态</th>
		</tr>
		<c:forEach items="${jlblist}" var="jlb" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${jlb.xh }</td>
				<td>${jlb.xm }</td>
				<td>${jlb.sqrq }</td>
				<td>${jlb.qkrq }</td>
				<td>${jlb.zklxh }</td>
				<td>${jlb.zklx }</td>
				<td>
					<c:if test="${jlb.dqzt == '0'}">已申请</c:if>
					<c:if test="${jlb.dqzt == '1'}">制作中</c:if>
					<c:if test="${jlb.dqzt == '2'}">待发放</c:if>
					<c:if test="${jlb.dqzt == '3'}">已发放</c:if>
					<c:if test="${jlb.dqzt == '4'}">已拉黑</c:if>
					<c:if test="${jlb.dqzt == '5'}">曾违约</c:if>
				</td>
			</tr>
		</c:forEach>		
	</table>
	
	
</div>
</body>
</html>