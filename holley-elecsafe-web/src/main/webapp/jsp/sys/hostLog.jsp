<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <%@include file="../common/meta.jsp"%>
   <%@include file="../common/js.jsp"%>
   <link rel="stylesheet" type="text/css" href="res/plugin/layer/skin/default/layer.css">
</head>
<body>
   <div id="mainContent" class="content">
    <form id="btnbarFrom" class="form-inline" role="form" action="sys/hostLog_query.action">
	    <div class="form-group">
		 	<div class="input-group date" id="startDateDiv" style="width: 150px;">
                <input id="startDate" name="startDate" type="text" class="form-control input-sm" placeholder="开始时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
		     <span>—</span>
 		 	 <div class='input-group date' id="endDateDiv" style="width: 150px;">
                <input id="endDate" name="endDate" type="text" class="form-control input-sm" placeholder="结束时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
		     <label> &nbsp;&nbsp;账号/内容：</label>
		     <input id="keyword" name="keyword" type="text"  class="form-control input-sm" placeholder="请输入账号/内容" style="width: 240px;"/>
			&nbsp;&nbsp;
			<select id="logType" name="logtype" class="form-control input-sm">
				<option value="0">日志类型</option>
				<s:iterator value="#request.LogTypeList" var="item">
		     		<s:if test="#request.LogTypeList == #item.value">
		     			<option value="${item.value}" selected="selected">${item.name}</option>
		     		</s:if>
		     		<s:else>
			     		<option value="${item.value}">${item.name}</option>
		     		</s:else>
				</s:iterator>
			</select>
		</div>
		<div class="form-group pull-right">
			<input id="isExport" name="isExport" value="true" type="hidden">
			<input id="fileName" name="fileName" value="操作日志" type="hidden">
			<button type="button" id="queryBtn" class="btn btn-primary btn-sm">查询</button>
			<button type="button" id="exportBtn" class="btn btn-primary btn-sm">导出</button>
		</div>
    </form>
    <div id="tableDiv" style="overflow-y:auto;">
	    <table id="hostLogTable" class="table table-striped table-condensed table-hover">
	   		<thead class="table-header">
	   			<tr>
	   				<th>用户账号</th>
	   				<th>用户名称</th>
	   				<th>操作时间</th>
	   				<th>类型</th>
	   				<th>IP</th>
	   				<th id="content" width="200px;">内容</th>
	   				<th></th>
	   			</tr>
	   		</thead>
		   	<tbody></tbody>
		</table>
    </div>
    <!-- 分页条 -->
    <%@include file="../common/component/pagingtoolbar.jsp" %>
</div>

<div id="contentModal" style="display: none">
         <div class="modal-body">
		 	<div class="form-group">
			      	<textarea  class="form-control" id="contentDiv" style="height: 210px;resize:none"  readonly="readonly"></textarea>
		    	</div>
         </div>
         <div class="modal-footer">
            <button  id="closeButton" type="button" class="btn btn-primary">关闭</button>
         </div>
</div>
<script type="text/javascript" src="res/js/sys/hostLog.js"></script>
<script src="res/plugin/layer/layer.js"></script>
</body>
</html>