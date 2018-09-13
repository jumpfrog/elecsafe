<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
  <link rel="stylesheet" type="text/css" href="res/plugin/jquery-easyui/easyui.css">
  <link rel="stylesheet" type="text/css" href="res/plugin/jquery-easyui/icon.css">
	<style type="text/css">
		.box {
		    position: relative;
		    border-radius: 3px;
		    background: #ffffff;
		    border-top: 3px solid #d2d6de;
		    margin-bottom: 20px;
		    width: 100%;
		    box-shadow: 0 1px 1px rgba(0,0,0,0.1);
		}
	</style>
</head>
<body>
  <!--编辑用电账户-->
  <section class="content">
  	 
    <form class="form-inline" role="form" >
  	   <div class="form-group">
  	     	<a href="sys/account.action">
  	  			<span id="backListBtn" class="glyphicon glyphicon-menu-left backBtn" data-toggle="tooltip" data-placement="right" title="返回" aria-hidden="true"></span>
      		</a>
  	  </div>
      <div class="form-group pull-right">
        <button id="saveBtn" type="button" class="btn btn-primary btn-sm">保存</button>
		<button id="cancelBtn" type="button" class="btn btn-primary btn-sm">取消</button>
	  </div>
  	</form>
      
      <div class="box box-info">
      	<div class="box-header ui-sortable-handle">
          <h3 class="box-title">用户信息</h3>
        </div>
        <div class="box-body">
		  <form id="accountForm" class="form-horizontal" role="form">
			<div class="col-sm-6">
				<div class="form-group">
			   		<label class="col-sm-4 control-label"><span style="color: red;">*</span>用户账号</label>
			        <div class="col-sm-8">
			        	<s:if test="#request.requestType == 1">
				      		<input type="text" class="form-control" id="account" name="account" maxlength="20" value="<s:property value='#request.accountInfo.account'/>"/>
			        	</s:if>
			        	<s:else>
			        		<input type="text" class="form-control" id="account" name="account" maxlength="20" value="<s:property value='#request.accountInfo.account'/>" disabled="disabled"/>
			        	</s:else>
			        </div>
			   	</div>
			   	<s:if test="#request.requestType == 1 || #request.accountInfo.account != 'admin'">
				   	<div class="form-group">
					  <label class="col-sm-4 control-label"><span style="color: red;">*</span>账号类型</label>
				      <div class="col-sm-8">
				      	<select id="type" name="type" class="form-control">
					     	<s:iterator value="#request.accountTypeList" var="item" status="st">
					     		<s:if test="#request.accountInfo.type == #item.value">
					     			<option value="${item.value}" selected="selected">${item.text}</option>
					     		</s:if>
					     		<s:else>
						     		<option value="${item.value}">${item.text}</option>
					     		</s:else>
					     	</s:iterator>
					     </select>
				      </div>
				   	</div>
			   	</s:if>
			   	<div class="form-group">
			   		<label class="col-sm-4 control-label">职位</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="position" name="position" maxlength="20" value="<s:property value='#request.accountInfo.position'/>"/>
			        </div>
			   	</div>
			   	<div class="form-group">
			        <label class="col-sm-4 control-label">岗位</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="station" name="station" maxlength="20" value="<s:property value='#request.accountInfo.station'/>"/>
			        </div>
			   	</div>
			   	<div class="form-group">
			   		<label class="col-sm-4 control-label">联系电话</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="telephone" name="telephone" value="<s:property value='#request.accountInfo.telephone'/>"/>
			        </div>
			   	</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
			        <label class="col-sm-4 control-label"><span style="color: red;">*</span>用户名称</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="name" name="name" maxlength="20" value="<s:property value='#request.accountInfo.name'/>"/>
			        </div>
			   	</div>
			   	<s:if test="#request.requestType == 1 || #request.accountInfo.account != 'admin'">
					   	<div class="form-group">
					      <label class="col-sm-4 control-label"><span style="color: red;">*</span>系统角色</label>
					      <div class="col-sm-8">
						      	<select id="roleid" name="roleid" class="form-control">
							     	<s:iterator value="#request.roleList" var="item" status="st">
								     		<option value="${item.roleid}">${item.disc}</option>
							     	</s:iterator>
							     </select>
					      </div>
					   	</div>
				   	<div id="deptDiv" class="form-group">
				   		<label class="col-sm-4 control-label"><span style="color: red;">*</span>所属企业</label>
				        <div class="col-sm-8">
				        
				        
				        	<div class="input-group">
				        	<p id="switchEntBtn" cur_eid="${accountInfo.departmentid}" cur_eidsc="${accountInfo.departmentname}" class="form-control">
				        	
				        		<s:if test="#request.accountInfo == null">
					      		请选择企业
					      		</s:if>
					      		<s:else>
					      		${accountInfo.departmentname}
					      		</s:else>
					      		</p>
					      		<%-- <input type="text" class="form-control hide" id="eid" name="departmentid" value="<s:property value='#request.accountInfo.departmentid'/>"/>
					      		<input type="text" class="form-control" id="edisc" name="departmentname" value="<s:property value='#request.accountInfo.departmentname'/>" disabled="disabled"/> --%>
					      		<span id="searchEntBtn" class="input-group-addon" style="cursor: pointer"><span class="fa fa-fw fa-search"></span></span>
				        	</div>
				        </div>
				   	</div>
			   	</s:if>
			   	<div class="form-group">
			        <label class="col-sm-4 control-label">电子邮箱</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="email" name="email" value="<s:property value='#request.accountInfo.email'/>"/>
			        </div>
			   	</div>
			   	<div class="form-group">
			        <label class="col-sm-4 control-label">联系地址</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="address" name="address" maxlength="50" value="<s:property value='#request.accountInfo.address'/>"/>
			        </div>
			   	</div>
			</div>
		  </form>
        </div>
	  </div>
  </section>
  <%@include file="../common/js.jsp" %>
 <%--  <%@include file="../common/component/searchEnterprise.jsp"%> --%>
 <%@include file="../common/component/switchEnt.jsp" %>
  <script type="text/javascript">
  		var requestType = <s:property value='#request.requestType'/>;
  		var webUserType = <s:property value='#request.webUserType'/>;
  		var roleListJson = ${ruleListJson};
  </script>
  <script type="text/javascript" src="${webRoot}/res/js/common/component/switchEnt.js"></script>
  <script src="res/plugin/jquery-easyui/jquery.easyui.min.js" type="text/javascript"></script>
  <script src="res/js/sys/editAccount.js" type="text/javascript"></script>
<!--   <script src="res/js/common/component/searchEnterprise.js" type="text/javascript"></script> -->
</body>
</html>

