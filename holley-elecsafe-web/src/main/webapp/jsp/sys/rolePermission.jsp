<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
<%@include file="../common/meta.jsp"%>
<style type="text/css">
</style>
<title>角色分配权限</title>
</head>
<body>
<div id="mainContent" class="content">
	<a href="sys/role.action">
  	  	<span id="backListBtn" class="glyphicon glyphicon-menu-left backBtn" data-toggle="tooltip" data-placement="right" title="返回" aria-hidden="true"></span>
      </a>
      <div class="box box-info">
      	<div class="box-header ui-sortable-handle">
          <h3 class="box-title">权限分配</h3>
        </div>
        <div class="box-body">
		  	<div id="treeDiv" style="overflow: auto;">
			   <div id="permissionTree" class="jstree-open"></div>
		    </div>
			<div class="form-group" style="margin-top: 10px;">
			 <!--  <div class="col-sm-6" style="text-align: left;">
		         <button id="saveBtn" type="button" class="btn btn-primary" onclick="save()">展开</button>
		         <button id="cancelBtn" type="button" class="btn btn-primary">合拢</button>
		      </div> -->
		      <div class="col-sm-6" style="text-align: right;">
		         <button id="saveBtn" type="button" class="btn btn-primary">保存</button>
		         <button id="cancelBtn" type="button" class="btn btn-primary">取消</button>
		      </div>
		  	</div>
        </div>
   </div>
 </div>
<%@include file="../common/js.jsp" %>
  <script src="res/js/sys/rolePermission.js" type="text/javascript"></script>
  <script type="text/javascript">
	var roleid = "<s:property value='#request.roleid'/>";
  </script>
</body>
</html>

