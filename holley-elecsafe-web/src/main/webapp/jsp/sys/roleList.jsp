<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
</style>
<title>角色管理</title>
</head>
<body>
 <div id="mainContent" class="content">
    <form id="btnbarFrom"  class="form-inline" role="form" action="sys/role_queryList.action">
	    <div class="form-group">
	    	<input id="keyword" name="keyword" type="text" class="form-control input-sm" placeholder="请输入角色名称" style="width: 200px;"/>
		    <input id="isExport" name="isExport" value="true" type="hidden">
		    <input id="fileName" name="fileName" value="角色列表" type="hidden">
		    <input type="text" class="form-control hide"/>
		</div>
	  	<div id="btnbar" class="form-group pull-right">
	  	</div>
    </form>
     <div id="roleTableDiv"  style="overflow-y:auto;">
	    <table class="table table-striped table-condensed table-hover" id="roleTable">
	   		<thead class="table-header">
	   			<tr>
	   				<th>角色名称</th>
			         <th>角色等级</th>
			         <th>角色描述</th>
			         <th>创建人</th>
			         <th>创建时间</th>
			         <th>操作</th>
	   			</tr>
	   		</thead>
		   	<tbody></tbody>
		</table>
    </div>
  </div>
  <%@include file="../common/js.jsp" %>
  <script src="res/js/sys/roleList.js" type="text/javascript"></script>
</body>
</html>

