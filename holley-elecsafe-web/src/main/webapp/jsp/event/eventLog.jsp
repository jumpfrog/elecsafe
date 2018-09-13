<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
	<!-- @desc 事件日志 -->
  <%@include file="../common/meta.jsp"%>
  <link rel="stylesheet" type="text/css"  href="${webRoot}/res/plugin/bootstrap-multiselect/bootstrap-multiselect.css">
<style type="text/css">
</style>
</head>
<body>
  <div id="mainContent" class="content">
  	<form id="btnbarFrom" class="form-inline" role="form">
	    <div class="form-group form-group-sm">
			<div class="input-group date" id="startTimeDiv" style="width: 180px;">
                <input id="startTime" name="startTime" type="text" class="form-control" placeholder="开始时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
		     <span>—</span>
 		 	 <div class='input-group date' id="endTimeDiv" style="width: 180px;">
                <input id="endTime" name="endTime" type="text" class="form-control" placeholder="结束时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
			 <select id="eventItemCombo" multiple="multiple"  class="form-control">
                </select>
		     <select id="dealStatusCombo" name="dealstatus" class="form-control">
		     	<option value="0">处理状态</option>
		     	<s:iterator value="#request.dealStatusList" var="item" status="st">
			     	<option value="${item.value}">${item.text}</option>
		     	</s:iterator>
		     </select>
		     <input id="keyword" name="keyword" type="text" class="form-control" placeholder="请输入监测点/单位/描述" style="width: 180px;"/>
		 	 <input type="text" class="form-control hide"/>
		 	 <label id="switchEntBtn" class="btn green btn-outline btn-circle btn-sm">请选择企业</label>
		</div>
		<div class="form-group pull-right">
			<button type="button" id="queryBtn" class="btn btn-primary btn-sm">查询</button>
			<button type="button" id="dealBtn" class="btn btn-primary btn-sm hide">处理</button>
			<button type="button" id="exportBtn" class="btn btn-primary btn-sm">导出</button>
  		</div>
	</form>
	<div id="eventTableDiv">
	    <table id="eventTable" class="table table-condensed table-hover table-bordered" data-toggle="table">
		    <thead class="table-header">
		        <tr>
		            <th data-field="checked" data-width="35px;" data-checkbox="true"></th>
		            <th data-width="50px" data-formatter="renderRowNum">序号</th>
		            <th data-field="detname" data-width="160px">监测点名称</th>
		            <th data-field="ownername" data-width="200px">所属单位</th>
		            <th data-field="datatimestr" data-width="160px">产生时间</th>
		            <th data-field="evttypename" data-cell-style="renderEvtTypeStyle" data-width="100px">事件类型</th>
		            <th id="evtDescCol" data-width="200px" data-field="evtdesc" data-formatter="renderEvtDesc">事件描述</th>
		            <th data-field="dealstatusname" data-width="60px">处理状态</th>
		            <th data-field="dealtimestr" data-width="160px">处理时间</th>
		        </tr>
		    </thead>
		    <tbody>
		    </tbody>
		</table>
    </div>
     <!-- 分页条 -->
    <%@include file="../common/component/pagingtoolbar.jsp" %>
  </div>
  
  <%@include file="../common/js.jsp" %>
  <%@include file="../common/component/switchEnt.jsp" %>
  <%@include file="component/eventDeal.jsp" %>
  <script src="${webRoot}/res/plugin/bootstrap-multiselect/bootstrap-multiselect.js" type="text/javascript"></script>
  <script type="text/javascript">
  	var eventClassItemList = '${eventClassItemList}';//告警事件类型
  	/* var defaultStartTime = '${defaultStartTime}';//默认开始时间
  	var defaultEndTime = '${defaultEndTime}';//默认结束时间 */
  </script>
  <script src="${webRoot}/res/js/common/component/switchEnt.js" type="text/javascript"></script>
  <script src="${webRoot}/res/js/event/eventLog.js" type="text/javascript"></script>
  <script src="${webRoot}/res/js/event/component/eventDeal.js" type="text/javascript"></script>
</body>
</html>

