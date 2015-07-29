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
<title>申请补办</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#infoCorrect").click(function(){
		             window.location.href='showOptions.action'
				}
			);
		});		
</script>
</head>
<body>
<div>
<fieldset style="width:900px;">
	<legend><h3>信息核对</h3></legend>
	<table>
		<tr>
			<td valign="top">
			 	<div style="width:400px;height:400px;">
				<ul><li><h3>学籍信息</h3></li></ul>
				<table border=1  style="border-collapse:collapse;width:280px;height:320px;cellspacing:1;text-align:center;" > 
	        		<tr>
	                <td rowspan=3 style="text-align:left;width:35%"><img src="readPhoto.action?username=${jlb.xh }" width=120 height=160  /></td>
	                <td style="text-align:left;">姓名:</td>
	                <td style="width:33%;">${jlb.xm }</td>
	        		</tr>
	        		<tr>
	                <td align="left">性别:</td>
	                <td>${jlb.xb }</td>
	        		</tr>
	        		<tr>
	                <td align="left">年级:</td>
	                <td>${jlb.ssnj }</td>
	        		</tr>
	        		<tr>
	                <td align="left">学号:</td>
	                <td colspan=2>${jlb.xh }</td>
	        		</tr>
	        
	                <tr>
	                <td align="left">身份证号:</td>
	                <td colspan=2>${jlb.sfzh }</td>
	        		</tr>
	        
	                <td align="left">学院:</td>
	                <td colspan=2>${jlb.xy }</td>

	                <tr>
	                <td align="left">专业:</td>
	                <td colspan=2>${jlb.zy }</td>
	        		</tr>
				</table>
				</div>
			</td>
			<td valign="top">
				<div style="width:400px;height:250px;">
				<ul><li><h3>乘车信息</h3></li></ul>
				<table border=1  style="border-collapse:collapse;width:280px;height:80px;text-align:center;">	
				<tr>
					<td>家庭地址</td>
					<td>${ccxxb.jtdz}</td>		
				</tr>
				<tr>
					<td>起始站</td>
					<td>${ccxxb.qsz} </td>
				</tr>
				<tr>
					<td>终点站</td>	
					<td>${ccxxb.zdz}</td>					
				</tr>
				</table>
				</div>	
			</td>
		</tr>
	</table>


	
</fieldset>
 <input type="button" id="infoError"   value="信息有误?" onclick="alert('请到教务处教务科确认信息无误后方可申请补办');" />
 <input type="button" id="infoCorrect" value="信息正确!"  />
</div>
</body>
</html>