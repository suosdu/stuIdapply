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
<title>发放确认</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#search").click(function(){
		  	$("#form1").attr("action","showIssueList.action");
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

	  $("#issueAll").click(function(){
			if($("[name=ifChoose]:checked").length==0){
				alert("至少选择一条记录");
			}else{
				if(window.confirm("确认发放所选项？")){
					  $("#form1").attr("action","issueAll.action");
					  $("#form1").submit();
				}
			}
		  });
	  
	  $("#blackAll").click(function(){
			if($("[name=ifChoose]:checked").length==0){
				alert("至少选择一条记录");
			}else{
				if(window.confirm("确认拉黑所选项？")){
					  $("#form1").attr("action","blackAll.action");
					  $("#form1").submit();
				}
			}
		  });	  
});		

</script>
</head>
<body>	

	<form id="form1" method="post">
		<p style="margin-left:10%">
			学号:<input type="text" name="jlb.xh" value="${jlb.xh}" />
			<input type="button" id="search" value="查询" />
			<input type="button" id="issueAll" value="发放勾选项" />
			<input type="button" id="blackAll" value="拉黑勾选项" />
		</p>
	
	<hr />	
	<table border=1 width=80% style=border-collapse:collapse;margin-left:10%;margin-right:10%;text-align:center>
	<caption>待发放名单</caption>
		<tr>
			<th style="text-align:center;">全选<input type="checkbox" id="checkAll" /></th>
			<th>序号</th>
			<th>学号</th>
			<th>姓名</th>
			<th>申请日期</th>
			<th>证卡类型号</th>
			<th>证卡类型</th>
<!--			<th>操作1</th>-->
<!--			<th>操作2</th>-->
		</tr>
		<c:forEach items="${jlblist}" var="jlb" varStatus="status">
			<tr>
				<td><input type="checkbox" name="ifChoose" value="${jlb.xh }+${jlb.zklxh }#${jlb.sqrq}+${jlb.xm }" /></td>
				<td>${status.count}</td>
				<td>${jlb.xh }</td>
				<td>${jlb.xm }</td>
				<td>${jlb.sqrq }</td>
				<td>${jlb.zklxh }</td>
				<td>${jlb.zklx }</td>
<!--				<td><a href="#" onclick="if (window.confirm('确定发放?'))-->
<!--				 location.href='ensureIssue.action?jlb.xh=${jlb.xh}&jlb.zklxh=${jlb.zklxh}';">发放</a>-->
<!--				</td>-->
<!--				<td><a href="#" onclick="if (window.confirm('确定加入黑名单?'))-->
<!--				 location.href='insertIntoBlack.action?hmd.xh=${jlb.xh}&hmd.zklxh=${jlb.zklxh}&hmd.sqrq=${jlb.sqrq}&hmd.xm=${jlb.xm }';">加入黑名单</a>-->
<!--				</td>-->
			</tr>
		</c:forEach>	
	</table>
	
	</form>
</body>
</html>