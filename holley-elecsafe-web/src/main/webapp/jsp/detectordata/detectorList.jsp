<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
<style type="text/css">
</style>
</head>
<!--漏电探测点管理-->
<body>
  <div id="mainContent" class="content">
  	<div id="detListDiv">
	  	<form id="btnbarFrom" class="form-inline" role="form" action="detector/detectordata_query.action">
		    <div class="form-group">
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
			     <input id="isExport" name="isExport" value="true" type="hidden">
			 	 <input id="fileName" name="fileName" value="监测点信息" type="hidden">
			</div>
			
			<!-- <div class="form-group pull-right">
				 <button id="queryBtn" type="button" class="btn btn-primary btn-sm">查询</button>
				 <button type="button" id="exportBtn" class="btn btn-primary btn-sm">导出</button>
 	 	 	</div> -->
			<div id="btnbar" class="form-group pull-right">
	  		</div>
		</form>
		<div id="listDiv">
			<table class="table table-condensed table-hover table-bordered" data-toggle="table" id="detectordataTable">
			    <thead class="table-header">
			        <tr>
				        <th data-width="46px" data-formatter="renderRowNum">序号</th>
		   				<th data-field="name" data-width="100px">监测点名称</th>
		   				<th data-field="entName" data-width="100px">所属企业</th>
				        <th data-field="brand" data-width="100px">监测点型号</th>
		   				<th data-field="typeStr" data-width="100px">监测点类型</th>
		   				<th data-field="protocolName" data-width="100px">通讯协议</th>
		   				<th data-field="commaddr" data-width="100px">通讯地址</th>
		   				<th data-field="updatetimeStr" data-width="100px">更新时间</th>
		   				<th data-width="80px" data-formatter="renderOperate">操作</th>
			        </tr>
			    </thead>
			    <tbody>
			    </tbody>
			</table>
	    </div>
	     <!-- 分页条 -->
	    <%@include file="../common/component/pagingtoolbar.jsp" %>
  	</div>
  	<div id="detDetailDiv" class="hide">
  		<a href="javascript:;">
  			<span id="returnBtn" class="glyphicon glyphicon-menu-left backBtn" data-toggle="tooltip" data-placement="right" title="返回" aria-hidden="true"></span>
  		</a>
  		<div class="box box-info">
	      <!-- 	<div class="box-header ui-sortable-handle">
	          <h3 class="box-title">用户信息</h3>
	        </div> -->
	        <div class="box-body">
		     <form id="detailForm" class="form-horizontal" role="form">
		      <h5>监测点基本信息：</h5>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">监测点名称：</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_name" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">所属企业：</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_entName" class="form-control-static"></p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">监测点型号：</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_brand" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">监测点类型：</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_type" class="form-control-static"></p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">通讯协议：</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_protocolName" class="form-control-static"></p>
			        </div>
		  			<label class="col-sm-2 control-label">通讯地址：</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_commaddr" class="form-control-static"></p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">安装地址：</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_installaddr" class="form-control-static"></p>
			        </div>
			        <label class="col-sm-2 control-label">更新时间：</label>
		  			<div class="col-sm-4">
			      	  <p id="detail_time" class="form-control-static"></p>
			        </div>
		  		</div>
		  		
		  		<h5>阀值设置信息：</h5>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">过压阀值(V)：</label>
		  			<div class="col-sm-4">
			      	  <p class="form-control-static"><span id="detail_overu"></span> V</p>
			        </div>
		  			<label class="col-sm-2 control-label">欠压阀值(V)：</label>
		  			<div class="col-sm-4">
			      	  <p class="form-control-static"><span id="detail_underu"></span> V</p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">过流阀值(A)：</label>
		  			<div class="col-sm-4">
			      	  <p class="form-control-static"><span id="detail_overi"></span> A</p>
			        </div>
		  			<label class="col-sm-2 control-label">剩余电流阀值(mA)：</label>
		  			<div class="col-sm-4">
			      	  <p class="form-control-static"><span id="detail_il"></span> mA</p>
			        </div>
		  		</div>
		  		<div class="form-group">
		  			<label class="col-sm-2 control-label">箱体温度阀值(℃)：</label>
		  			<div class="col-sm-4">
			      	  <p class="form-control-static"><span id="detail_ti"></span> ℃</p>
			        </div>
		  		</div>
		  		
		  		<div id="singleHoldDiv">
		  			<div class="form-group">
			  			<label class="col-sm-2 control-label">零线温度阀值(℃)：</label>
			  			<div class="col-sm-4">
				      	  <p class="form-control-static"><span id="detail_tn"></span> ℃</p>
				        </div>
			  			<label class="col-sm-2 control-label">火线温度阀值(℃)：</label>
			  			<div class="col-sm-4">
				      	  <p class="form-control-static"><span id="detail_tl"></span> ℃</p>
				        </div>
		  			</div>
		  		</div>
		  		<div id="threeHoldDiv">
		  			<div class="form-group">
			  			<label class="col-sm-2 control-label">A相温度阀值(℃)：</label>
			  			<div class="col-sm-4">
				      	  <p class="form-control-static"><span id="detail_ta"></span> ℃</p>
				        </div>
			  			<label class="col-sm-2 control-label">B相温度阀值(℃)：</label>
			  			<div class="col-sm-4">
				      	  <p class="form-control-static"><span id="detail_tb"></span> ℃</p>
				        </div>
		  			</div>
		  			<div class="form-group">
			  			<label class="col-sm-2 control-label">C相温度阀值(℃)：</label>
			  			<div class="col-sm-4">
				      	  <p class="form-control-static"><span id="detail_tc"></span> ℃</p>
				        </div>
		  			</div>
		  		</div>
		     </form>
	        </div>
        </div>
  	</div>
  </div>
  <%@include file="../common/js.jsp" %>
  <%@include file="../common/component/switchEnt.jsp" %>
  <script type="text/javascript" src="${webRoot}/res/js/common/component/switchEnt.js"></script>
  <script src="${webRoot}/res/js/detectordata/detectorList.js" type="text/javascript"></script>
</body>
</html>

