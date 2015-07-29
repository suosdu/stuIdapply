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
	  $("#ensureApply").click(function(){
		  if($("[name=choice]:checked").length==0){
			  alert("至少选择一补办项");
		  }else{
			  if (window.confirm("提示：如因不认真检查而造成制作的证卡信息有误，则责任由学生承担，证卡需做销毁处理，而学生仍然需要缴纳有关费用。")) 
				  {
				  	$("#form1").attr("action","apply.action");
				  	$("#form1").submit();
				  }
		  }
				}
			);
		});		
</script>
</head>
<body>
	<div style="text-align:center">
		<h3 style="display:inline">系统状态:</h3> 
			<c:if test="${xtsz.status == 1}"><span style="color:red">当前系统开放</span></c:if>
			<c:if test="${xtsz.status == 0}"><span style="color:red">当前系统关闭</span></c:if>
			<c:if test="${whetherPermit==0}"><span style="color:red">本年级暂不可申请</span></c:if>
		<h3 style="display:inline">学生状态:</h3> 
			<c:if test="${dqzt == '0'||dqzt == '1'||dqzt == '2'}"><span style="color:red">您已有申请正在办理中，无法再次申请</span></c:if>
			<c:if test="${dqzt == '无记录'||dqzt == '3'||dqzt == '5'}"><span style="color:red">无正在办理或当前违约</span></c:if>
			<c:if test="${dqzt == '4'}"><span style="color:red">您当前有违约记录，无法申请</span></c:if>	
	<hr />
	
	<table border=0 width=80% style=border-collapse:collapse;margin-left:10%;margin-right:10%;>
		<caption>学籍与乘车信息</caption>
		<tr>
			<td rowspan=4 style="width:12%"><img src="readPhoto.action?username=${jlb.xh }" alt="无照片不能申请" width=120 height=160  /></td>
			<th style="width:22%;height:40px;text-align:center;">姓名:</th>
			<td style="width:22%">${jlb.xm }</td>
			<th style="width:22%">性别:</th>
            <td style="width:22%">${jlb.xb }</td>
		</tr>
		<tr>
             <th style="height:40px;">学号:</th>
             <td>${jlb.xh }</td>
             <th>身份证号:</th>
             <td>${jlb.sfzh }</td>
		</tr>
		<tr>
			 <th style="height:40px;">学院:</th>
			 <td>${jlb.xy }</td>
             <th>专业:</th>
             <td>${jlb.zy }</td>
		</tr>
		<tr>
			 <th style="height:40px;">乘车起始站:</th>
			 <td>${ccxxb.qsz}</td>
             <th>乘车终点站:</th>
             <td>${ccxxb.zdz}</td>
		</tr>
		<tr>
			<td></td>
			<th style="height:40px;">所属年级:</th>
			<td>${jlb.ssnj }</td>
		</tr>	
	</table>
	</div>
	<hr />

<div>
	<span style="margin-left:10%;color:red">提示：1.若信息有误,请到教务处教务科确认信息无误后申请补办; 2.无乘车信息情况下不可申请补办乘车优惠卡</span>
	<hr />
<c:choose>	
	<c:when test="${xtsz.status == 1&&(dqzt == '无记录'||dqzt == '3'||dqzt == '5')&&message == 'have_pic'&&whetherPermit==1}">
	<form id="form1" method="post">
		<fieldset style="margin-left:10%;margin-right:10%;">
		<legend>请选择补办类型</legend>
			<c:forEach items="${zklxlist}" var="item">
				<c:choose>
					<c:when test="${ccxxb.qsz==null&&item.zklxh == 2}">
						<input type="checkbox" name="choice" value="${item.zklxh}+${item.zklx }" disabled="disabled" />${item.zklx }(${item.price}元)
					</c:when>
					<c:otherwise>
						<input type="checkbox" name="choice" value="${item.zklxh}+${item.zklx }" />${item.zklx }(${item.price}元)
					</c:otherwise>
				</c:choose>								
			</c:forEach>
		<input type="button" id="ensureApply" value="确认申请" />
		</fieldset>
	</form>
	</c:when>
	<c:otherwise>
		<fieldset style="margin-left:10%;margin-right:10%;">
		<legend>请选择补办类型</legend>
			<c:forEach items="${zklxlist}" var="item">
				<input type="checkbox" name="choice" value="${item.zklxh}+${item.zklx }" disabled="disabled" />${item.zklx }(${item.price}元)
			</c:forEach>
		<input type="button" value="确认申请" disabled="disabled" />
		</fieldset>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>