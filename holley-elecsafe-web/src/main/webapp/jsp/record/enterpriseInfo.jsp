<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
   <link rel="stylesheet" type="text/css" href="res/plugin/jquery-easyui/easyui.css">
  <link rel="stylesheet" type="text/css" href="res/plugin/jquery-easyui/icon.css">
<style type="text/css">
	.box-header {
	    color: #444;
	    display: block;
	    padding-left:5px;
	    padding-right:5px;
	    padding-top:5px;
	    padding-bottom:0;
	    position: relative;
	    vertical-align: middle;
	}
	
	.box-title{
		padding: 8px;
	}
	.anchorBL{
	display:none;
}
</style>
</head>
<!--企业信息-->
<body>
	<div id="mainContent" class="content">
     <div  id="entMain" class="row">
         <div class="col-sm-3" style="padding-right: 0">
             <div id="treeDiv" style="overflow: auto;">
			   <div id="enterpriseTree" class="jstree-open"></div>
		    </div>
		</div>
       <div class="col-sm-9" style="padding-left: 0">
       	  <div id="enterpriseInfo">
	          <div class="box box-info">
	       	    <div class="box-header">
		      	  <h3 class="box-title">企业信息</h3>
		      	  <div class="box-tools pull-right">
		      	  	<div id="ent_btnbar" class="form-group pull-right">
				  	</div>
	              </div>
		      	</div>
		        <div class="box-body">
			     <form id="enterpriseForm" class="form-horizontal" role="form">
			  		<div class="form-group">
			  			<label class="col-sm-2 control-label"><span style="color: red;">*</span>企业名称</label>
			  			<div class="col-sm-4">
				      	  <input type="text" id="disc" name="disc" class="form-control" maxlength="64" >
				      	  <input type="hidden" id="entid" value="<s:property value='#request.enterprise.eid'/>"/>
				        </div>
			  			<label class="col-sm-2 control-label"><span style="color: red;">*</span>企业名称简称</label>
			  			<div class="col-sm-4">
				      	  <input type="text" id="abbr" name="abbr" class="form-control" maxlength="5" >
				        </div>
			  		</div>
			  		<div class="form-group">
			  			<label class="col-sm-2 control-label"><span style="color: red;">*</span>所属地区</label>
			  			<div class="col-sm-4">
			  				<ul id="citycombox" name="city" class="easyui-combotree" data-options="valueField:'id', textField:'text', panelHeight:'200px'" style="width: 100%;height: 34px;"></ul>
				        </div>
			  			<label class="col-sm-2 control-label"><span style="color: red;">*</span>所属行业</label>
			  			<div class="col-sm-4">
				      	  <ul id="businesscombox" name="business" class="easyui-combotree" data-options="valueField:'id', textField:'text', panelHeight:'200px'" style="width: 100%;height: 34px;"></ul>
				        </div>
			  		</div>
			  		<div class="form-group">
			  			<label class="col-sm-2 control-label"><span style="color: red;">*</span>联系人</label>
			  			<div class="col-sm-4">
				      	 <input type="text" id="linkman" name="linkman" class="form-control" maxlength="32">
				        </div>
			  			<label class="col-sm-2 control-label"><span style="color: red;">*</span>联系电话</label>
			  			<div class="col-sm-4">
				      	  <input type="text" id="telephone" name="telephone" class="form-control" maxlength="32">
				        </div>
			  		</div>
			  		<div class="form-group">
			  			<label class="col-sm-2 control-label">电子邮箱</label>
			  			<div class="col-sm-4">
				      	  <input type="text" id="email" name="email" class="form-control" maxlength="32">
				        </div>
				         <label class="col-sm-2 control-label">企业类别</label>
			  			  <div class="col-sm-4">
				      		<select id="grade" name="grade" class="form-control">
					     	<s:iterator value="#request.gradeType" var="item" status="st">
					     		<s:if test="#request.enterprise.grade == #item.value">
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
			  			<label class="col-sm-2 control-label">主要经营活动</label>
			  			<div class="col-sm-4">
				      	  <input type="text" id="activities" name="activities" class="form-control"  maxlength="128">
				        </div>
				        <label class="col-sm-2 control-label">法人代表</label>
			  			<div class="col-sm-4">
				      	  <input type="text" id="legalperson" name="legalperson" class="form-control" maxlength="32">
				        </div>
			  		</div>
			  		<div class="form-group">
			  			<label class="col-sm-2 control-label">企业类型</label>
			  			<div class="col-sm-4">
			  			<select id="ent_type" name="ent_type" class="form-control">
						     <option value="1">顶级公司</option>
						     <option value="2">子公司</option>
						 </select>
				        </div>
				        <label class="col-sm-2 control-label"><span style="color: red;">*</span>经纬度</label>
			  			<div class="col-sm-3">
			  			<p class="form-control" id="lngLatInfo"></p>
				        </div>
				        <div class="col-sm-1">
			  			<span id="lngLatMapIcon" onclick="showLngLatMap(this)" class="fa fa-map-marker pull-center" style="font-size: 30px;cursor: pointer;"></span>
				        </div>
				      </div>
				      <div class="form-group">
				      <div id="entDiv" class="hide">
				        <label class="col-sm-2 control-label">所属企业</label>
			        	<div class="col-sm-4">
				        	<div class="input-group">
					      		<input type="text" class="form-control hide" id="eid" name="ownerid"/>
				      			<input type="text" class="form-control" id="edisc" name="ownername" disabled="disabled"/>
					      		<span id="ent_searchDeptBtn" class="input-group-addon" style="cursor: pointer"><span class="fa fa-fw fa-search"></span></span>
				        	</div>
			       		 </div>
				        </div>
				      </div>
			     </form>
		       </div>
	        </div>
       	  </div>
	      <div id="enterpriseGridDiv">
	    	<div class="box box-info">
	    		<div class="box-header">
		      	  <h3 class="box-title">子公司列表</h3>
		      	  <div class="box-tools pull-right">
	                  <form id="btnbarFrom" role="form" action="record/enterprise_queryEnterInfo.action">
						<div id="btnbar" class="form-group pull-right">
				  		</div>
			  		    <input id="isExport" name="isExport" value="true" type="hidden">
					 	<input id="fileName" name="fileName" value="企业信息列表" type="hidden">
					 	<input id="nodeid" name="nodeid" type="hidden">
					 	<input type="text" class="form-control hide"/>
					</form>
	              </div>
		      	</div>
		      	 <div class="box-body">
		      	 	<div id="enterpriseTableDiv" style="overflow-y:auto;">
					    <table id="enterpriseTable" class="table table-striped table-condensed table-hover"  data-striped="true" data-height="300px;">
						    <thead class="table-header">
						        <tr>
						            <th>企业名称</th>
						            <th>所属企业</th>
						            <th>企业类别</th>
						            <th>所属地区</th>
						            <th>所属行业</th>
						            <th>联系人</th>
						            <th>联系电话</th>
						            <th>操作</th>
						        </tr>
						    </thead>
						    <tbody>
						    </tbody>
						</table>
		      	 	</div>
		      	 </div>
	    	</div>
		 </div>
      </div>
    </div>
     <!--编辑企业信息，供列表新增、修改使用-->
	   <%@include file="editEnterprise.jsp"%>
   </div>
   <%@include file="../common/js.jsp" %>
   <%@include file="../common/component/lngLatMap.jsp"%>
    <%@include file="../common/component/lngLatMap2.jsp"%>
    <%@include file="../common/component/searchEnterprise.jsp"%>
     <%@include file="../common/component/searchEnterprise2.jsp"%>
  	 <script src="res/plugin/jquery-easyui/jquery.easyui.min.js" type="text/javascript"></script>
 	 <script src="res/js/common/treecom.js" type="text/javascript"></script>
 	 <script src="res/js/record/enterpriseInfo.js" type="text/javascript"></script>
    <script src="res/js/record/editEnterprise.js" type="text/javascript"></script>
      <script src="res/js/common/component/searchEnterprise.js" type="text/javascript"></script>
    <script src="res/js/common/component/searchEnterprise2.js" type="text/javascript"></script>
  <script type="text/javascript">
  	var nodeid = "<s:property value='#request.nodeid'/>";
	var nodetype = "<s:property value='#request.nodetype'/>";
	var webUserType = "<s:property value='#request.webUserType'/>";
	
	
	var requestType = "<s:property value='#request.requestType'/>";
   	var eid = "<s:property value='#request.enterprise.eid'/>";
	var ownerid = "<s:property value='#request.enterprise.ownerid'/>";
	var ownertype = "<s:property value='#request.enterprise.ownertype'/>"; 
	var city = "<s:property value='#request.enterprise.city'/>"; 
	var business = "<s:property value='#request.enterprise.business'/>"; 
  </script>
  
</html>

