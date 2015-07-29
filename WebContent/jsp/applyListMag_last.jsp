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
<title>制作名单管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#search").click(function(){
		  	$("#form1").attr("action","showApplyList_LastWeek.action");
		  	$("#form1").submit();
		  }
			);
		
	  $("#checkAll").click(function(){  
		  if (this.checked) {  
	             $("[name=ifChoose]").each(function() {  
	           	  this.checked=true; 
	                     });  
	         } else {   //反之取消全选   
	             $("[name=ifChoose]").each(function() {  
	           	  this.checked=false;
	                     }); 
	         }  
	  });

	  $("#generateIssue").click(function(){
			if($("[name=ifChoose]:checked").length==0){
				alert("至少选择一条记录");
			}else{
				if(window.confirm("确认发放？")){
					  $("#form2").attr("action","generateIssue.action");
					  $("#form2").submit();
				}
			}
		  });
		
});
		
</script>
</head>
<body>
<form id="form1" method="post">
<p style="margin-left:10%">
	学号：
	<input type="text" name="jlb.xh" value="${jlb.xh}" />
	<input type="button" id="search" value="查询" />
</p>
</form>
<hr />
<form id="form2" method="post">
 <div style="height:400px; overflow-y:scroll;">
	<table border=1 width=80% style="border-collapse:collapse;margin-left:10%;margin-right:10%;text-align:right">
	<caption>制作名单</caption>
		<tr>
			<th style="text-align:center;">全选<input type="checkbox" id="checkAll" /></th>
			<th>序号</th>
			<th>学号</th>
			<th>姓名</th>
			<th>申请日期</th>
			<th>证卡类型号</th>
			<th>证卡类型</th>
		</tr>
		<c:forEach items="${jlblist}" var="jlb" varStatus="status">
			<tr>
				<td><input type="checkbox" name="ifChoose" value="${jlb.xh }+${jlb.zklxh }" /></td>
				<td>${status.count}</td>
				<td>${jlb.xh }</td>
				<td>${jlb.xm }</td>
				<td>${jlb.sqrq }</td>
				<td>${jlb.zklxh }</td>
				<td>${jlb.zklx }</td>
			</tr>
		</c:forEach>		
	</table>
</div>
</form>
	<hr />
	<p>
<!--		<input type="button" value="导出名单" onclick="location.href='exportData.action'"></input>-->
		<span style="margin-left:10%">
			<span style="font-weight:bold">分类导出:&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<a href="#" onclick="location.href='exportData.action'">所有记录</a>：<span style="color:red"><c:out value="${fn:length(jlblist)}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="location.href='exportData.action?jlb.zklxh=1'">学生证</a> <a href="#" onclick="location.href='exportPic.action?jlb.zklxh=1'">照片</a>：<span style="color:red"><c:out value="${requestScope.num1}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="location.href='exportData.action?jlb.zklxh=2'">乘车优惠卡</a>：<span style="color:red"><c:out value="${requestScope.num2}" /></span>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="location.href='exportData.action?jlb.zklxh=3'">校徽</a>：<span style="color:red"><c:out value="${requestScope.num3}" /></span>
		</span>
	</p>
		<hr />
		<input type="button" id="generateIssue" value="生成发放名单" />	
</body>
</html>