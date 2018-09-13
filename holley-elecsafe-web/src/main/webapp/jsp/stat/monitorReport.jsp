<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
	<!-- @desc 监测报告 -->
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
	.callout.callout-light-green {
	    background-color: #c0edf1;
	    border-color: #58d0da;
	    color: #27a4b0;
	}
</style>
</head>
<body>
  <div id="mainContent" class="content">
  <!--  <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="padding-left:15px;padding-right: 15px;padding-top: 15"> -->
  	<form id="btnbarFrom" class="form-inline" role="form">
	    <div class="form-group form-group-sm">
			<div class="input-group date" id="startTimeDiv" style="width: 180px;">
                <input id="startTime" name="startTime" type="text" class="form-control" placeholder="数据时间"/>
                <span class="input-group-addon">
                	<span class="glyphicon glyphicon-calendar"></span>
                </span>
		     </div>
		     <s:if test="#request.eid != null">
		     	<label id="switchEntBtn" cur_eid="${eid}" cur_eidsc="${entName}" class="btn green btn-outline btn-circle btn-sm">${entName}</label>
		     </s:if>
		     <s:else>
		     	<label id="switchEntBtn" class="btn green btn-outline btn-circle btn-sm">暂无企业</label>	
		     </s:else>
		</div>
		<div class="form-group pull-right">
			<button type="button" id="queryBtn" class="btn btn-primary btn-sm">查询</button>
			<button type="button" id="exportPdfBtn" class="btn btn-primary btn-sm">导出PDF</button>
  		</div>
	</form>
	<!-- </nav> -->
	
	<div id="pdfDiv">
		<div class="callout callout-light-green">
          <h4>概况总览</h4>
          <p><span id="statEntName"></span>：本周(<span id="statWeekTime"></span>)安全用电检测情况</p>
        </div>
        <div class="box">
           <!--  <div class="box-header ui-sortable-handle" style="cursor: move;">
            </div> -->
            <!-- /.box-header -->
            <div class="box-body" style="padding: 0">
               <table class="table table-condensed table-hover "  id="statdataTable">
	   			<thead >
	   				<tr>
		   				<th>监测点名称</th>
		   				<th>剩余电流</th>
		   				<th>线缆电流</th>
		   				<th>线缆温度</th>
		   				<th>故障</th>
	   				</tr>
	   			</thead>
		   		<tbody id="statdataBody">
		   		</tbody>
			 </table>
        	</div>
            <!-- /.box-body -->
	        <div class="box-footer clearfix no-border" style="padding: 0;padding-top: 10">
	            	<span id="statCountAlarmDisc"></span>
	            	<span class="pull-right">注：<span class="fa fa-square" style='color:#dd4b39'></span>为告警，<span class="fa fa-square" style='color:#00a65a'></span>为正常</span>
	        </div> 
        </div>
        <!-- addEventTable start -->
        <div class="callout callout-light-green">
          <h4>事件总览</h4>
        </div>
        <div class="box">
            <div class="box-body" style="padding: 0;">
               <table class="table table-condensed table-hover" id="detEventTable">
	   			<thead >
	   				<tr>
		   				<th style="width: 160">监测点名称</th>
		   				<th style="width: 100">事件类型</th>
		   				<th style="width: 100">处理状态</th>
		   				<th>事件描述</th>
		   				<th>处理意见</th>
		   				<th style="width: 150">产生时间</th>
	   				</tr>
	   			</thead>
		   		<tbody id="detEventBody">
		   		</tbody>
			 </table>
        	</div>
            <!-- /.box-body -->
	       <!--  <div class="box-footer clearfix no-border" style="padding: 0;padding-top: 10">
	            	<span id="statCountAlarmDisc"></span>
	            	<span class="pull-right">注：<span class="fa fa-square" style='color:#dd4b39'></span>为告警，<span class="fa fa-square" style='color:#00a65a'></span>为正常</span>
	        </div>  -->
        </div>
        
        <!-- addEventTable end -->
        <div class="callout callout-light-green">
          <h4>特征分析</h4>
        </div>
        <div id="statBodyDiv">
        </div>
	</div>
  </div>
  <%@include file="../common/js.jsp" %>
  <%@include file="../common/component/switchEnt.jsp" %>
<script type="text/javascript">
  var startTime = '${startTime}';
  var entCount = '${entCount}';
  var currentDateTime = '${currentDateTime}';
</script>
<script type="text/javascript" src="${webRoot}/res/plugin/jspdf/jspdf.min.js"></script> 
<script type="text/javascript" src="${webRoot}/res/plugin/jspdf/html2canvas.js"></script>
<script type="text/javascript" src="${webRoot}/res/js/common/component/switchEnt.js"></script>
<script type="text/javascript" src="${webRoot}/res/js/stat/monitorReport.js"></script>
</body>
</html>

