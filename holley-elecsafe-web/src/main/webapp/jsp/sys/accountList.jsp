<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
</style>
</head>
<!--用户管理-->
<body>
  <div id="mainContent" class="content">
  	<div id="accountList">
	  	<form id="btnbarFrom" class="form-inline" role="form" action="sys/account_queryList.action">
		    <div class="form-group">
				<input id="keyword" name="keyword" type="text" class="form-control input-sm" placeholder="请输入用户账号/用户名称/企业名称" style="width: 260px;"/>
				<select id="accountType" name="accounttype" class="form-control input-sm">
			     	<option value="0">账号类型</option>
			     	<s:iterator value="#request.accountTypeList" var="item" status="st">
			     		<s:if test="#request.accountType == #item.value">
			     			<option value="${item.value}" selected="selected">${item.text}</option>
			     		</s:if>
			     		<s:else>
				     		<option value="${item.value}">${item.text}</option>
			     		</s:else>
			     	</s:iterator>
			     </select>
			     <input id="isExport" name="isExport" value="true" type="hidden">
			 	 <input id="fileName" name="fileName" value="用户列表" type="hidden">
			 	 <input type="text" class="form-control hide"/>
			</div>
			<div id="btnbar" class="form-group pull-right">
	  		</div>
		</form>
		<div id="accountTableDiv" style="overflow-y:auto;">
		    <table id="accountTable" class="table table-striped table-condensed table-hover">
			    <thead class="table-header">
			        <tr>
			            <th>用户账号</th>
			            <th>用户名称</th>
			            <th>账号类型</th>
			            <th>系统角色</th>
			            <th>所属企业</th>
			            <th>联系电话</th>
			            <th>电子邮箱</th>
			            <th>操作</th>
			        </tr>
			    </thead>
			    <tbody>
			    </tbody>
			</table>
	    </div>
	     <!-- 分页条 -->
	    <%@include file="../common/component/pagingtoolbar.jsp" %>
  	</div>
  	<div id="accountDetail" class="hide">
  		<a href="javascript:;">
  			<span id="backListBtn" class="glyphicon glyphicon-menu-left backBtn" data-toggle="tooltip" data-placement="right" title="返回" aria-hidden="true"></span>
  		</a>
  		<div class="box box-info">
	      	<div class="box-header ui-sortable-handle">
	          <h3 class="box-title">用户信息</h3>
	        </div>
	        <div class="box-body">
		     <form id="detailForm" class="form-horizontal" role="form">
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">用户账号</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_account" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">用户名称</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_name" class="form-control-static"></p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">用户类型</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_typename" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">系统角色</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_rolename" class="form-control-static"></p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">所属企业</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_departmentname" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">岗位</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_station" class="form-control-static"></p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">职位</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_position" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">电子邮箱</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_email" class="form-control-static"></p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">联系电话</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_telephone" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">联系地址</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_address" class="form-control-static"></p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">创建者</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_creator" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">创建时间</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_createtimestr" class="form-control-static"></p>
			        </div>
		  		</div>
		     </form>
	        </div>
        </div>
  	</div>
  </div>
  <%@include file="../common/js.jsp" %>
  <script src="res/js/sys/accountList.js" type="text/javascript"></script>
</body>
</html>

