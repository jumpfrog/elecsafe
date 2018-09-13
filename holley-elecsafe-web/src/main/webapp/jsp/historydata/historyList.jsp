<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
	.form-group{
	  margin-bottom:5px;
	}
	.thStyle{
		color: #fff;
	/* 	padding-bottom: 0px; */
	}
	.trStyle{
		height: 50px;
	}
</style>
</head>
<!--历史数据列表-->
<body>
  <div id="mainContent" class="content">
 	<form id="btnbarFrom" class="form-inline" role="form" action="history/historydata_query.action">
		<div class="form-group">
		 	<input id="isExport" name="isExport" value="true" type="hidden">
			<input id="fileName" name="fileName" value="历史数据" type="hidden">
		     <div class="input-group date" id="startDateDiv" style="width: 150px;">
                <input id="startDate" name="startDate"type="text" class="form-control input-sm" placeholder="开始时间"/>
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
		     <select id="type" class="form-control input-sm">
		     	<option value="0">监测点类型</option>
		     	<s:iterator value="#request.detectorTypeList" var="item" status="st">
			     	<option value="${item.value}">${item.text}</option>
		     	</s:iterator>
		     </select>
		     <input id="keyword" name="keyword" type="text" class="form-control input-sm" placeholder="监测点名称" style="width: 180px;"/>
		     <s:if test="#request.entCount > 1">
		     	<label id="switchEntBtn" class="btn green btn-outline btn-circle btn-sm">请选择企业</label>
		     </s:if>
		     
		</div>
		<div class="form-group pull-right">
			 <button id="queryBtn" type="button" class="btn btn-primary btn-sm">查询</button>
			 <button type="button" id="exportBtn" class="btn btn-primary btn-sm">导出</button>
 	 	 </div>
 	  </form>
		<div id="listDiv">
		<table class="table table-condensed table-hover table-bordered" data-toggle="table" id="histotydataTable">
	   			<thead class="table-header">
	   			<tr>
	   				<th data-width="46px" data-formatter="renderRowNum">序号</th>
	   				<th data-field="name" data-width="180px" data-formatter="renderDetName">监测点名称</th>
	   				<th data-field="typeStr" data-width="80px">监测点类型</th>
	   				<th data-field="ia" data-align="right" data-width="80px" data-formatter="renderDetThreeI">A相电流(A)</th>
	   				<th data-field="ib" data-align="right" data-width="80px" data-formatter="renderDetThreeI">B相电流(A)</th>
	   				<th data-field="ic" data-align="right" data-width="80px" data-formatter="renderDetThreeI">C相电流(A)</th>
	   				<th data-field="i"  data-align="right" data-width="80px" data-formatter="renderDetSingleI">电流(A)</th>
	   				<th data-field="il" data-align="right" data-width="80px" data-formatter="renderDetIL">剩余电流(mA)</th>
	   				<th data-field="ta" data-align="right" data-width="80px" data-formatter="renderDetThreeTA">A相温度(℃)</th>
	   				<th data-field="tb" data-align="right" data-width="80px" data-formatter="renderDetThreeTB">B相温度(℃)</th>
	   				<th data-field="tc" data-align="right" data-width="80px" data-formatter="renderDetThreeTC">C相温度(℃)</th>
	   				<th data-field="tn" data-align="right" data-width="80px" data-formatter="renderDetSingleTN">零线温度(℃)</th>
	   				<th data-field="tl" data-align="right" data-width="80px" data-formatter="renderDetSingleTL">火线温度(℃)</th>
	   				<th data-field="ti" data-align="right" data-width="80px" data-formatter="renderDetTI">箱体温度(℃)</th>
	   				<th data-field="longDatatimeStr" data-width="160px">数据时间</th>
	   			</tr>
	   		</thead>
		   	<tbody></tbody>
		</table>
		</div>
		<!-- 分页条 -->
	    <%@include file="../common/component/pagingtoolbar.jsp" %>
  </div> 
  
  
  <div id="detailDetHistoryChartDiv" class="content hide" style="overflow:auto;">
  		 <form id="btnbarFrom" class="form-inline" role="form" action="history/historydata_query.action">
			<input type="hidden" id="detid"/>
			<div class="form-group pull-right" style="margin-bottom: 10">
			 <div class="input-group date" id="startHChartDateDiv" style="width: 200px;">
                <input id="startHChartDate" type="text" class="form-control input-sm" placeholder="开始时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
		     <span>—</span>
 		 	 <div class='input-group date' id="endHChartDateDiv" style="width: 200px;">
                <input id="endHChartDate" type="text" class="form-control input-sm" placeholder="结束时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
			<button id="queryHChartBtn" type="button" class="btn btn-primary btn-sm">查询</button>
			<button id="exportPdfBtn" type="button" class="btn btn-primary btn-sm">导出PDF</button>
			<button id="returnBtn" type="button" class="btn btn-primary btn-sm">返回</button>
			</div>
		</form>
  		  <div class="row" id="pdfDiv">
			  	<div class="col-sm-12 col-md-12 col-lg-12">
				 	<div class="box box-info">
			            <div class="box-header with-border bgcolor">
			              <h3 class="box-title fontcolor">电流走势图</h3>
			            </div>
			            <div class="box-body" style="padding: 0;">
			              <div id="iChart"  style="height: 220px;"></div>
			            </div>
		        	</div>
			 	</div>
			 
			 	<div class="col-sm-12 col-md-12 col-lg-12">
				 	<div class="box box-info">
			            <div class="box-header with-border bgcolor">
			              <h3 class="box-title fontcolor">温度走势图</h3>
			            </div>
			            <div class="box-body" style="padding: 0">
			              <div id="tChart"  style="height: 220px;"></div>
			            </div>
		        	</div>
			 	</div>
			  </div>
  </div>
  <%@include file="../common/js.jsp" %>
  <%@include file="../common/component/switchEnt.jsp" %>
  <script type="text/javascript">
	  var startHChartDateStr = "${startHChartDateStr}";
	  var endHChartDateStr = "${endHChartDateStr}";
	  var currentDateTime = "${currentDateTime}";
  </script>
   <script type="text/javascript" src="${webRoot}/res/js/common/component/switchEnt.js"></script>
   <script type="text/javascript" src="${webRoot}/res/js/historydata/historyList.js"></script>
   <script type="text/javascript" src="${webRoot}/res/js/historydata/historyChart.js"></script>
   <script type="text/javascript" src="${webRoot}/res/js/realdata/initchart.js"></script>
   <script type="text/javascript" src="${webRoot}/res/plugin/jspdf/jspdf.min.js"></script> 
<script type="text/javascript" src="${webRoot}/res/plugin/jspdf/html2canvas.js"></script>
</body>
</html>

