<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
	.form-group{
	  margin-bottom:5px;
	}
</style>
<script type="text/javascript">
var entCount = "${entCount}"
var eid = "${eid}";
if(entCount == 1){
	var temphref = "real/deviceList.action"+"?eid="+eid;
	window.location.href = temphref;
}
</script>
</head>
<!--企业信息列表-->
<body>
  <div id="mainContent" class="content">
 	<form class="form-inline" role="form">
		<div class="form-group" style="margin-left: 15">
			<select id="province" class="form-control input-sm">
		     	<option value="0">选择省</option>
		     	<s:iterator value="#application.province" var="item" status="st">
			     	<option value="${item.cityid}">${item.name}</option>
		     	</s:iterator>
		     </select>
		     <select id="city" class="form-control input-sm" style="width: 100px;">
		     	<option value="0">选择市</option>
		     </select>
		     <input id="keyword" name="keyword" type="text" class="form-control input-sm" placeholder="企业名称" style="width: 180px;"/>
		     <input type="hidden" id="eid" name="eid" value="${eid}"/>
		</div>
		<div class="form-group pull-right">
		 <button id="queryBtn" type="button" class="btn btn-primary btn-sm">查询</button>
		</div>
 	  </form>
		<div id="listDiv" style="overflow-y: auto;width: 100%;">
			
		</div>
		<!-- 分页条 -->
	    <%@include file="../common/component/pagingtoolbar.jsp" %>
  </div> 
  <%@include file="../common/js.jsp" %>
   <script type="text/javascript" src="res/js/realdata/enterpriseList.js"></script>
</body>
</html>

