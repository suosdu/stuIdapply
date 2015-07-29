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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>系统设置</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 $("#addYxnj").click(function(){
		 if($("#text").val().trim()==""){
			 alert("请输入完整数据");
		 }else{
		  	$("#form2").attr("action","addYxnj.action");
		  	$("#form2").submit();
		 }
		 });
	 $("#college").change(function(){
		$.post("showMajor.action",
			{"ccxxb.xsh":$(this).val()},
			function(resp){
				var list = resp.zyblist;
					
				$("#major").empty();
				for(var i=0; i<list.length;i++){
					$("#major").append("<option value='"+list[i].zyh+"'>"+list[i].zym+"</option>");
				}
				$("#class").empty();
				$("#major").trigger("change");
			}
			);
	 });

	 $("#major").change(function(){
		$.post("showClass.action",
			{
			"ccxxb.xsh":$("#college").val(),
			"ccxxb.zyh":$(this).val()
			},
			function(resp){
				var list = resp.bjblist;
				$("#class").empty();				
				for(var i=0; i<list.length;i++){
					$("#class").append("<option value='"+list[i].bm+"'>"+list[i].bm+"</option>");
					
				}
			}
			);
	 });

	 $("#search").click(function(){
		  	$("#form3").attr("action","showSystemInfo.action");
		  	$("#form3").submit();
	 });
	 
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

	$("#delete").click(function(){
		if($("[name=ifChoose]:checked").length==0){
			alert("至少选择一条记录");
		}else{
			if(window.confirm("确认删除？")){
				  $("#form3").attr("action","deleteCcxx.action");
				  $("#form3").submit();
			}
		}
	  });
	
	 $(".update").click(function(){
		 if($(this).val()=="修改"){
			$(this).val("保存");
			$(".update").attr("disabled",true); $(this).attr("disabled",false);//只能改一个
			$(this).parent().prev().prev().children().attr("disabled",false);
			$(this).parent().prev().children().attr("disabled",false);
			$(this).next().show();
		 }else if($(this).val()=="保存"){
			$(this).val("修改");
			$(".update").attr("disabled",false);
			$(this).next().hide();

			var mythis=$(this);//!!!!!!!!!!!!!!!!!!!!!!!在function里无法用this
			$.post("updateCcxx.action",
				{"ccxxb.xh":$(this).parent().prev().prev().prev().prev().prev().prev().prev().text(),
				 "ccxxb.qsz":$(this).parent().prev().prev().children().val(),
				 "ccxxb.zdz":$(this).parent().prev().children().val()
				},
				function(resp){									
					mythis.parent().prev().prev().children().val(resp.ccxxb.qsz);											
					mythis.parent().prev().children().val(resp.ccxxb.zdz);
					mythis.parent().prev().prev().children().attr("disabled",true);
					mythis.parent().prev().children().attr("disabled",true);
				}
			);
		 }
	 });

	 $(".cancel").click(function(){
		 $("#search").trigger("click");
	 });	
});
</script>

<!--<style type="text/css">-->
<!--	#tableTop th{ -->
<!--	position: relative; -->
<!--	z-index: 1; -->
<!--	background: #009933; -->
<!--	text-align: center; -->
<!--	}-->
<!--</style>-->
</head>


<body>
<form id="form1" method="post">
<div id="id1">
<h4>系统状态管理</h4>
		<span>当前状态--</span>
		<span style="color:red";>
			<c:if test="${xtsz.status == 1 }">
			  开启状态&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"  value="点击关闭"  onclick="location.href='changeStatus.action?xtsz.paramname=系统状态&xtsz.status=0'" />
			</c:if>
			<c:if test="${xtsz.status == 0}">
			  关闭状态&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"  value="点击开启"  onclick="location.href='changeStatus.action?xtsz.paramname=系统状态&xtsz.status=1'" />
			</c:if>
		</span>
</div>
</form>	
<hr />

<form id="form2" method="post">
	<div id="id2">
	<h4>允许申请年级管理</h4>
		<c:forEach items="${yxnjlist}" var="yxnj">
			<span style="margin-left:75px;margin-right:75px;">
			${yxnj.nj}&nbsp;&nbsp;<a href="#" onclick="location.href='deleteYxnj.action?nj=${yxnj.nj}';">删除</a></span>
		</c:forEach>
		<input type="text" name="nj" id="text" style="width:50px"/>
		<input type="button" id="addYxnj" value="添加"/>
	</div>
</form>

<h4>乘车信息管理</h4>	
<form id="form3" method="post">
	<p style="margin-left:7%;width:1100px;">
		学院：
		<select name="ccxxb.xsh" id="college"  style="width:100px">
			<option value="">所有</option>
			<c:forEach items="${xsblist}" var="xsb">
				<option value="${xsb.xsh }" <c:if test="${ccxxb.xsh == xsb.xsh}">selected="selected"</c:if>>${xsb.xsjc}</option>
			</c:forEach>
		</select>
		专业：
		<select name="ccxxb.zyh" id="major" style="width:210px">
<!--			<option value="">所有</option>-->
			<c:forEach items="${zyblist}" var="zyb">
				<option value="${zyb.zyh }" <c:if test="${ccxxb.zyh == zyb.zyh}">selected="selected"</c:if>>${zyb.zym}</option>
			</c:forEach>
		</select>
		班级：
		<select name="ccxxb.bm" id="class" style="width:100px">
<!--			<option value="">所有</option>-->
			<c:forEach items="${bjblist}" var="bjb">
				<option value="${bjb.bm }" <c:if test="${ccxxb.bm == bjb.bm}">selected="selected"</c:if>>${bjb.bm}</option>
			</c:forEach>
		</select>
		学号：<input type="text" name="ccxxb.xh" value="${ccxxb.xh}" />
		<input type="button" id="search" value="查询" />
		<input type="button" id="delete" value="删除所选项" />
	</p>


   <div style="width:1100px;min-height:100px;overflow-y:auto; max-height:300px;border: 1px solid #CCCCFF;"">
<!--	<div style="overflow-y: scroll; -->
<!--				overflow-x: hidden; -->
<!--				height: 100px; -->
<!--				margin-top: 5px; -->
<!--				margin-left: 12px; -->
<!--				padding-top: -2px; -->
<!--				padding-bottom: 5px; -->
<!--				border: 3px solid #009933;">-->
	<table border=1 width=100% style="border-collapse:collapse;margin-left:5%;width:990px;text-align:center;">
	<caption>乘车信息</caption>
		 <tr id="tableTop">
		 	<th>全选<input type="checkbox" id="checkAll" /></th>
			<th>序号</th>
			<th>学号</th>
			<th>姓名</th>
			<th>学院</th>
			<th>专业</th>
			<th>班级</th>
			<th>起始站</th>
			<th>终点站</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${ccxxblist}" var="ccxxb" varStatus="status">
			<tr>
				<td><input type="checkbox" name="ifChoose" value="${ccxxb.xh }" /></td>
				<td>${status.count}</td>
				<td>${ccxxb.xh }</td>
				<td>${ccxxb.xm }</td>
				<td>${ccxxb.college }</td>
				<td>${ccxxb.major }</td>
				<td>${ccxxb.bm }</td>
				<td><input type="text" value="${ccxxb.qsz }" disabled="disabled" style="width:50px" /></td>
				<td><input type="text" value="${ccxxb.zdz }" disabled="disabled" style="width:75px" /></td>
				<td style="width:100px">
					<input type="button" class="update" value="修改" />
					<input type="button" class="cancel" value="取消" style="display:none" />
				</td>
			</tr>
		</c:forEach>
	</table>
   </div>
</form>	

<form action="updateRide.action" method="post" enctype="multipart/form-data">  
    <div>
		<label>导入文件:</label>
		<input type="file"  name="excelFile" /><input type="submit" value="提交" />							
    </div>
</form>
	<br />
	<a href="download/ccxxb.xls">乘车信息模板.xls</a>
</body>
</html>