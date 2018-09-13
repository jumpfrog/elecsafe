<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
</style>
<title>登录日志</title>
</head>
<body>
 <div id="mainContent" class="content">
 <div id="webLogList">
  <form id="webLogFrom" class="form-inline" role="form" action="sys/webLog_query.action">
	   <div class="form-group">
		 	<div class="input-group date" id="startDateDiv" style="width: 150px;">
                <input id="startDate" name="startdate" type="text" class="form-control input-sm" placeholder="开始时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
		     <span>—</span>
 		 	 <div class='input-group date' id="endDateDiv" style="width: 150px;">
                <input id="endDate" name="enddate" type="text" class="form-control input-sm" placeholder="结束时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
		     <input id="keyword" name="keyword" type="text"  class="form-control input-sm" placeholder="请输入用户账号/用户名称/企业名称" style="width: 260px;"/>
		    <input id="isExport" name="isExport" value="true" type="hidden">
			<input id="fileName" name="fileName" value="登录日志" type="hidden">
			<input id="eid" name="eid" value="" type="hidden">
		</div>
	  	<div class="form-group pull-right">
			<button type="button" id="queryBtn" class="btn btn-primary btn-sm">查询</button>
		    <button type="button" id="exportBtn" class="btn btn-primary btn-sm">导出</button>
		</div>
    </form>
    
     <div id="webLogDiv"  style="overflow-y:auto;">
	    <table class="table table-striped table-condensed table-hover" id="webLogTable">
	   		<thead class="table-header">
	   			<tr>
	   				 <th> 用户账号 </th>
			         <th> 用户名称</th>
			         <th> 账号类型 </th>
			         <th> 所属企业 </th>
			         <th> 登录次数</th>
			         <th> 最近登录时间 </th>
			         <th></th>
	   			</tr>
	   		</thead>
		   	<tbody></tbody>
		</table>
    </div>
     <!-- 分页条 -->
    <%@include file="../common/component/pagingtoolbar.jsp" %>
    </div>
    
    <div id="webLogDetailList" class="hide">
    	<a href="javascript:;">
		    <span id="backListBtn" class="glyphicon glyphicon-menu-left backBtn" data-toggle="tooltip" data-placement="right" title="返回" aria-hidden="true" onclick="showResult('webLogList')"></span>
    	</a>
	    <span style="font-size: 18px;">用户信息</span>
    	<div class="box box-info">
    		<!-- <div class="box-header ui-sortable-handle">
	          <h3 class="box-title">用户信息</h3>
	        </div> -->
	        <div class="box-body">
		  		<form id="webLogDetailForm" class="form-inline" role="form" action="sys/webLog_queryDetaiList.action">
		  			<!-- <h5 class="form-header">用户登录明细</h5> -->
			  		<div class="form-group">
			  			<div class="input-group date" id="startDateDiv2" style="width: 150px;">
		                <input id="startDate2" name="startdate" type="text" class="form-control input-sm" placeholder="开始时间"/>
		                <span class="input-group-addon">
		                	<span class="glyphicon glyphicon-calendar"></span>
		                </span>
				     </div>
				     <span>—</span>
		 		 	 <div class='input-group date' id="endDateDiv2" style="width: 150px;">
		                <input id="endDate2" name="enddate" type="text" class="form-control input-sm" placeholder="结束时间"/>
		                <span class="input-group-addon">
		                	<span class="glyphicon glyphicon-calendar"></span>
		                </span>
				     </div>
					     <input id="isExport" name="isExport" value="true" type="hidden">
					 	 <input id="fileName" name="fileName" value="用户登录明细" type="hidden">
					 	 <input id="account" name="account" value="" type="hidden">
					 	 <input type="text" class="form-control hide"/>
			  		</div>
			  		<div class="form-group pull-right">
						<button id="queryDetailBtn" class="btn btn-primary btn-sm" type="button">查询</button>
						<button id="exportDetailBtn" class="btn btn-primary btn-sm" type="button">导出</button>
			  		</div>
		  		</form>
		  		<div id="webLogDetailDiv" style="overflow-y:auto;">
				    <table class="table table-condensed table-hover" id="webLogDetailTable">
				   		<thead>
				   			<tr>
				   			 <th width="100px;">用户账号 </th>
					         <th width="150px;">登录时间</th>
				   			</tr>
				   		</thead>
					   	<tbody>
					   	</tbody>
					</table>
			    </div>
			       <!-- 分页条 -->
		    <%@include file="../common/component/pagingtoolbar2.jsp" %>
	        </div>
    	</div>
  	</div>
 </div>
  <%@include file="../common/js.jsp" %>
  <script src="res/js/sys/webLog.js" type="text/javascript"></script>
</body>
</html>

