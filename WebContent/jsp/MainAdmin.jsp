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
<title>本科学生证卡补办报名系统</title>
<link href="Styles/frame.css" rel="stylesheet" type="text/css" />
<link href="Styles/menu.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body
{
margin: 0;
padding: 0;
border: 0;
overflow: hidden;
height: 100%;
max-height: 100%;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#exit").mousedown(function(){
						location.href="https:\/\/ykt.wh.sdu.edu.cn/cas/logout";
				}
			);
		});		
</script>
</head>


<body >
<form id="form1" runat="server">
	<div id="framecontentLeft">
		<div class="menu">
			<ul>
				<p />
				<li><a href="showSystemInfo.action" target="content">系统设置</a></li>
				<p />
				<li><a href="#">申请名单</a></li>
				<a href="showApplyList_ThisWeek.action" target="content">当前申请</a>
				<a href="showApplyList_LastWeek.action" target="content">制作名单</a>
				<p />
				<li><a href="showIssueList.action" target="content">发放确认</a></li>
				<p />
				<li><a href="showHisRecord.action" target="content">历史记录</a></li>
				<p />			
<!--				<li><a href="jsp/emergencyApply.jsp" target="content">紧急补办</a></li>-->
<!--				<p />				-->
				<li><a href="showBlackList.action" target="content">黑名单</a></li>
			</ul>
		</div>
	</div>
	
	<div id="framecontentTop" >
		<div style="text-align: center;">
			<div style="float: right;">
				欢迎您：${sessionScope.username}
				<a href="#" id="exit">注销</a>
			</div>
			<h1>
			本科学生证卡补办报名系统
			</h1>
		</div>
	</div>
	
	<div id="maincontent">
		<iframe  name="content"  frameborder="0"  src="init.html"
		scrolling="yes" width="100%" height="100%"></iframe>
	</div>
</form>
</body>
</html>

