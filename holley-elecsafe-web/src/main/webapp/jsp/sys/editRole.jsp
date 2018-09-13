<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
</style>
<title>角色信息管理</title>
</head>
<body>
 <section class="content">
 	
      
      <form class="form-inline" role="form" >
  	   <div class="form-group">
  	     	<a href="sys/role.action">
  	  			<span class="glyphicon glyphicon-menu-left backBtn" data-toggle="tooltip" data-placement="right" title="返回" aria-hidden="true"></span>
      		</a>
  	  </div>
      <div class="form-group pull-right">
       	 <button id="saveBtn" type="button" class="btn btn-primary btn-sm">保存</button>
		 <button id="cancelBtn" type="button" class="btn btn-primary btn-sm">取消</button>
	  </div>
  	</form>
 	<div class="box box-info">
 		<div class="box-header ui-sortable-handle">
          <h3 class="box-title">角色信息</h3>
        </div>
        <div class="box-body">
		  <form id="roleForm" class="form-horizontal" role="form">
		   	<div class="form-group">
		   		<label class="col-sm-2 control-label"><span style="color: red;">*</span>角色名称</label>
		        <div class="col-sm-4">
		      		<input type="text" class="form-control" id="disc" name="disc" maxlength="20" value="<s:property value='#request.sysRole.disc'/>"/>
		        	<input id="roleid" value="<s:property value='#request.sysRole.roleid'/>" type="hidden">
		        </div>
		   	</div>
			<div class="form-group">
			  <label class="col-sm-2 control-label"><span style="color: red;">*</span>角色等级</label>
		      <div class="col-sm-4">
		      	<select id="memberlevel" name="memberlevel" class="form-control">
			     	<s:iterator value="#request.memberLevel" var="item" status="st">
			     		<s:if test="#request.sysRole.memberlevel == #item.value">
			     			<option value="${item.value}" selected="selected">${item.text}</option>
			     		</s:if>
			     		<s:else>
				     		<option value="${item.value}">${item.text}</option>
			     		</s:else>
			     	</s:iterator>
			     </select>
		      </div>
		   	</div>
		   	<div class="form-group">
		   		<label class="col-sm-2 control-label"><span style="color: red;">*</span>角色描述</label>
		        <div class="col-sm-4">
		      		<input type="text" class="form-control" id="remark" name="remark" maxlength="128" value="<s:property value='#request.sysRole.remark'/>"/>
		        </div>
		   	</div>
		  </form>
        </div>
 	</div>
  </section>
  <%@include file="../common/js.jsp" %>
  <script src="res/js/sys/editRole.js" type="text/javascript"></script>
  <script type="text/javascript">
  var requestType = "<s:property value='#request.requestType'/>";
  var memberLevel = "<s:property value='#request.memberLevel'/>";
  var roleId = "<s:property value='#request.sysRole.roleid'/>";
  </script>
</body>
</html>

