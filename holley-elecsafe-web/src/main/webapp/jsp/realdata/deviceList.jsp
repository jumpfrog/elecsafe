<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
   <link rel="stylesheet" type="text/css" href="res/css/common/deviceList.css">
<style type="text/css">
	.form-group{
	  margin-bottom:5px;
	}
</style>
</head>
<!--监测点列表-->
<body>
	<audio id="chatAudio">
	<source src="${webRoot}/res/plugin/audio/notify.mp3" type="audio/mpeg">
	</audio>
  <div id="mainContent" class="content">
 	<form class="form-inline" role="form" style="margin-left: 16">
		<div class="form-group">
			 <input type="hidden" id="eid" name="eid" value="${eid}"/>
		     <input id="keyword" name="keyword" type="text" class="form-control input-sm" placeholder="监测点名称" style="width: 180px;"/>
		</div>
		<div class="form-group pull-right">
			 <button id="queryBtn" type="button" class="btn btn-primary btn-sm">搜索</button>
			  <s:if test="#request.entCount > 1">
		     <button id="returnBtn" type="button" class="btn btn-primary btn-sm">返回</button>
			</s:if>
		</div>
 	  </form>
		<div id="listDiv" style="overflow-y: auto;width: 100%;"></div>
		<!-- 分页条 -->
	   <%--  <%@include file="../common/component/pagingtoolbar.jsp" %> --%>
	</div>
	<!-- detail -->
	<div id="detailDetStatusDiv" class="content hide">
		  <div id="entDiv" class="col-md-4">
			  <div class="row">
			  	<div class="col-md-12">
			 	  <div class="box box-info" style="height: 15%;box-shadow:0 0px 0px #fff">
	            	<div class="box-header with-border bgcolor">
	              		<h3 class="box-title fontcolor">终端信息</h3>
	              		<span class="fa fa-reply" title="返回" style="float: right;cursor: pointer;color: #fff;" id="detailReturnBtn"></span>
	            	</div>
	            <div class="box-body" style="padding: 0">
	             	<div class="form-group">
				   		<label class="col-sm-4 control-label">监测点名称：</label>
				       	<div class="col-sm-8">
				      	 <p class="form-control-static" id="detname">科技园</p>
				        </div>
			   		</div>
			   		<div class="form-group">
				   		<label class="col-sm-4 control-label">在线状态：</label>
				       	<div class="col-sm-8">
				      	<p class="form-control-static" id="linestatus">离线</p>
				        </div>
			   		</div>
			   			<div class="form-group">
				   		<label class="col-sm-4 control-label">型号：</label>
				       	<div class="col-sm-8">
				      	 <p class="form-control-static" id="detmodal">HW-ERG(W)3-134(30A)</p>
				        </div>
			   		</div>
	            </div>
	        	</div>
			 </div>
			 
			 		 
			 <div class="col-md-12" >
			 	<div class="box box-info">
		            <div class="box-header with-border bgcolor">
		              <h3 class="box-title fontcolor">传感器数据</h3>
		            </div>
		            <div class="box-body" style="padding: 0;">
		            <ul id="entEnergyDistUl" class="products-list product-list-in-box list-group">
			         </ul>
		            </div>
	        	</div>
			 </div>
			 
			  </div>
		  </div>
		  <div id="chartDiv" class="col-md-8">
		  
		  	  <div class="row">
			  	<div class="col-md-12">
				 	<div class="box box-info">
			            <div class="box-header with-border bgcolor">
			              <h3 class="box-title fontcolor">电流走势图</h3>
			            </div>
			            <div class="box-body" style="padding: 0">
			              <div id="iChart"  style="height: 230px;"></div>
			            </div>
		        	</div>
			 	</div>
			 
			 	<div class="col-md-12">
				 	<div class="box box-info">
			            <div class="box-header with-border bgcolor">
			              <h3 class="box-title fontcolor">温度走势图</h3>
			            </div>
			            <div class="box-body" style="padding: 0;">
			              <div id="tChart"  style="height: 230px;"></div>
			            </div>
		        	</div>
			 	</div>
			  </div>
		  </div>
		</div>
  	<%@include file="../common/js.jsp" %>
  	<%@include file="newEventDeal.jsp" %>
  	<script type="text/javascript">
  	var currentDateTime = '${currentDateTime}';
  	var preTime = "${preTime}";
  	var entCount = "${entCount}"
  	</script>
   <script type="text/javascript" src="res/js/realdata/deviceList.js"></script>
   <script type="text/javascript" src="res/js/realdata/initchart.js"></script>
</body>
</html>

