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
<title>紧急补办管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#look_for").click(function(){
			  $("#form1").attr("action","emergencySearch.action");//搜该生信息
			  $("#form1").submit();
			}
			);
		});		
</script>
</head>
<body>
	<span style="color:red;margin-left:10%">提示：学生已有申请且未发放时才可查询出信息</span>
	<hr />
	<form id="form1" method="post">
		<div style="margin-left:10%"">
			请输入学号:
			<input type="text" name="jlb.xh" value="${jlb.xh}" />
			<input type="button" id="look_for" value="查询" />
		</div>
	</form>
	<hr />
<c:choose>
	<c:when test="${fn:length(jlblist)>0}">
		  
		<table border=1 width=80% style="border-collapse:collapse;margin-left:10%;margin-right:10%;text-align:right">
			<caption>该生当前申请记录</caption>
				<tr>
					<th>学号</th>
					<th>姓名</th>
					<th>申请日期</th>
					<th>证卡类型号</th>
					<th>证卡类型</th>
					<th>当前状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${jlblist}" var="jlb" varStatus="status">
						<tr>
							<td>${jlb.xh }</td>
							<td>${jlb.xm }</td>
							<td>${jlb.sqrq }</td>
							<td>${jlb.zklxh }</td>
							<td>${jlb.zklx }</td>
							<c:if test="${jlb.dqzt == '0'}">
								<td>已申请</td>
							</c:if>	
							
							<c:if test="${jlb.dqzt == '1'}">
								<td>制作中</td>
							</c:if>	
							
							<c:if test="${jlb.dqzt == '2'}">
								<td>待发放</td>
							</c:if>
							<td><input type="button" value="点击完成发放" onclick="location.href='emergencyIssue.action?jlb.xh=${jlb.xh}&jlb.zklxh=${jlb.zklxh}';" /></td>					
						</tr>
				</c:forEach>
		</table>
</c:when>
 <c:otherwise>
 		${message}
 </c:otherwise>
</c:choose>
</body>
</html>