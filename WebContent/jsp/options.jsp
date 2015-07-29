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
<title>确认申请</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	  $("#ensure_apply").click(function(){
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
<form id="form1" method="post">
<div>
<fieldset>
	<legend>请选择补办类型</legend>
	<c:forEach items="${zklxlist}" var="item">
		<input type="checkbox" name="choice" value="${item.zklxh}+${item.zklx }" />${item.zklx }
	</c:forEach>
<!--			<input type="checkbox" name="bbitems" value="xsz"/>学生证-->
<!--			<input type="checkbox" name="bbitems" value="ccyhk"/>乘车优惠卡-->
<!--			<input type="checkbox" name="bbitems" value="xiaohui"/>校徽-->
</fieldset>
	<input type="button" id="ensure_apply" value="确认申请"  />
</div>
</form>
</body>
</html>