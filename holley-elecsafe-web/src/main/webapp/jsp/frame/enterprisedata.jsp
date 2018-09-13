<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
	.form-group{
	  margin-bottom:5px;
	}
</style>
</head>
<!--首页-->
<body>
  <div id="mainContent" class="content">
 	<form class="form-inline" role="form">
	   <!-- <div class="btn-group" role="group" aria-label="...">
		  <button id="chartTab" type="button" class="btn btn-tab active" showtype="1">图表</button>
		  <button id="gridTab" type="button" class="btn btn-tab" showtype="2">列表</button>
		  <button id="mapTab" type="button" class="btn btn-tab" showtype="3">地图</button>
		</div>  -->
		<div class="form-group pull-right" style="margin-bottom: 10">
			<select id="province" class="form-control">
		     	<option value="0">选择省</option>
		     	<s:iterator value="#request.provinceList" var="item" status="st">
			     	<option value="${item.cityid}">${item.name}</option>
		     	</s:iterator>
		     </select>
		     <select id="city" class="form-control">
		     	<option value="0">选择市</option>
		     </select>
		     <input id="keyword" name="keyword" type="text" class="form-control" placeholder="企业名称/电站名称" style="width: 180px;"/>
		     <input type="text" class="form-control hide"/>
		     <button id="queryBtn" type="button" class="btn btn-primary">搜索</button>
		</div>
 	  </form>
		<div id="listDiv" style="overflow-y: auto;width: 100%;">
			<!--chart-->
			<section id="chartDiv">
			
			<div class="col-sm-4 col-md-3" style="cursor: pointer;">
			<div class="box box-default">
			<div class="box-header with-border" style="padding-bottom:0;">
			<h4 class="box-title" style="font-size:16px;">
			<a class="text-aqua" data-toggle="tooltip" data-placement="bottom" data-original-title="华立科技园创客园区331.52kWp项目">华立科技园创客园区</a>
			</h4>
			<h5 class="text-muted"><span class="fa fa-clock-o"> 2017-07-18 14:30:00</span></h5>
			</div>
			<div class="box-body" style="padding-top:0;">
			<div class="col-xs-4 no-padding">
			<img class="img-circle" style="width:100px;height:100px" src="http://120.55.104.113:8291/dpms/data/stationImg/stationImg_63.jpg">
			</div>
			<div class="col-xs-8">
				<div style="margin-top: 8">
					<span class="fa fa-bell-o text-yellow"> 告警</span>
					<span class="pull-right">33.3%</span>
				</div>
				<div style="margin-top: 10">
					<span class="fa fa-check-circle-o text-green"> 正常</span>
					<span class="pull-right">66.7%</span>
				</div>
				<div style="margin-top: 10">
					<span class="fa fa-power-off text-gray"> 离线</span>
					<span class="pull-right">0%</span>
				</div>
				<div style="margin-top: 10">
					<span class="fa fa-power-off text-red"> 故障</span>
					<span class="pull-right">0%</span>
				</div>
			</div>
			</div>
			</div>
			</div>
			</section>
		</div>
		<!-- 分页条 -->
	    <%@include file="../common/component/pagingtoolbar.jsp" %>
  </div> 
  <%@include file="../common/js.jsp" %>
  <script type="text/javascript">
  	$("#listDiv").css("height",getWindowHeight() - 165);
</script>
</body>
</html>

