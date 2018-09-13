<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
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
  <!--修改或添加监测点-->
  <section class="content">
  
  	<form class="form-inline" role="form" >
  	   <div class="form-group">
  	     <a href="detector/detectorList.action">
  	  		<span id="backListBtn" class="glyphicon glyphicon-menu-left backBtn" data-toggle="tooltip" data-placement="right" title="返回" aria-hidden="true"></span>
      	 </a>
  	  </div>
      <div class="form-group pull-right">
       	<button  id="saveBtn" type="button" class="btn btn-primary btn-sm">保存</button>
		<button  id="cancelBtn" type="button" class="btn btn-primary btn-sm">取消</button>
	  </div>
  	</form>

    
      <div class="box box-info">
        <div class="box-body">
		  <form id="detectorForm" class="form-horizontal" role="form">
		     <h5>监测点基本信息：</h5>
				<div class="form-group">
			   		<label class="col-sm-2 control-label"><span style="color: red;">*</span>监测点名称</label>
			        <div class="col-sm-4">
			        	<input type="hidden" name="id" id="detid" value="${detectorData.id}"/>
			        	<input type="text" class="form-control" placeholder="请输入监测点名称" id="name" name="name" maxlength="20" value="${detectorData.name}"/>
			        </div>
			        <label class="col-sm-2 control-label"><span style="color: red;">*</span>所属企业</label>
				    <div class="col-sm-4">
				        <div class="input-group">
					      		<p id="switchEntBtn" cur_eid="${detectorData.ownerid}" cur_eidsc="${detectorData.entName}" class="form-control">
					      		<s:if test="#request.detectorData == null">
					      		请选择企业
					      		</s:if>
					      		<s:else>
					      		${detectorData.entName}
					      		</s:else>
					      		</p>
					      		<span id="searchEntBtn" class="input-group-addon" style="cursor: pointer"><span class="fa fa-fw fa-search"></span></span>
				        </div>
				    </div>
			   	</div>
				<div class="form-group">
					      <label class="col-sm-2 control-label"><span style="color: red;">*</span>监测点型号</label>
					      <div class="col-sm-4">
						      	<select id="modelid" name="modelid" class="form-control">
							     	<s:iterator value="#request.modelList" var="item" status="st">
							     	<s:if test="#request.detectorData.modelid == #item.id">
							     		<option selected="selected" value="${item.id}">${item.brand}</option>
							     	</s:if>
							     	<s:else>
							     		<option value="${item.id}">${item.brand}</option>
							     	</s:else>
							     	</s:iterator>
							     </select>
					      </div>
					       <label class="col-sm-2 control-label"><span style="color: red;">*</span>监测点类型</label>
					      <div class="col-sm-4">
						      	<select id="type" name="type" class="form-control">
							     	<s:iterator value="#request.typeList" var="item" status="st">
							     	<s:if test="#request.detectorData.type == #item.value">
							     		<option selected="selected" value="${item.value}">${item.text}</option>
							     	</s:if>
							     	<s:else>
							     		<option value="${item.value}">${item.text}</option>
							     	</s:else>
							     	</s:iterator>
							     </select>
					      </div>
				</div>
				<div class="form-group">
					 <label class="col-sm-2 control-label"><span style="color: red;">*</span>通讯协议</label>
					 <div class="col-sm-4">
						      	<select id="protocolid" name="protocolid" class="form-control">
							     	<s:iterator value="#request.protocolList" var="item" status="st">
							     		<s:if test="#request.detectorData.protocolid == #item.id">
							     			<option selected="selected" value="${item.id}">${item.name}</option>
							     		</s:if>
							     		<s:else>
							     			<option value="${item.id}">${item.name}</option>
							     		</s:else>
							     	</s:iterator>
							     </select>
					</div>
					<label class="col-sm-2 control-label"><span style="color: red;">*</span>通讯地址</label>
			        <div class="col-sm-4">
			        		<input type="text" class="form-control" id="commaddr" name="commaddr" placeholder="请输入通讯地址" maxlength="20" value="${detectorData.commaddr}"/>
			        </div> 
				</div>
				
				<div class="form-group">
					 <label class="col-sm-2 control-label">安装地址</label>
					 <div class="col-sm-4">
					 	<textarea id="installaddr" name="installaddr" rows="2" cols="1" class="form-control" maxlength="55" placeholder="最多55个字符。。。">${detectorData.installaddr}</textarea>
					 </div>
				</div>
	<%-- 	<div id="holdParamDiv">
		<h5>阀值设置：</h5>
		 	<div class="form-group">
						 <label class="col-sm-2 control-label">过压阀值(V)</label>
						 <div class="col-sm-4">
							      <input type="text" class="form-control"  placeholder="请输入过压阀值" id="overu" name="overu" maxlength="9" value="${detectorData.overu}"/>
						</div>
						<label class="col-sm-2 control-label">欠压阀值(V)</label>
				        <div class="col-sm-4">
				        		<input type="text" class="form-control" placeholder="请输入欠压阀值" id="underu" name="underu" maxlength="9" value="${detectorData.underu}"/>
				        </div> 
			</div>
			<div class="form-group">
						 <label class="col-sm-2 control-label">过流阀值(A)</label>
						 <div class="col-sm-4">
							<input type="text" class="form-control" placeholder="请输入过流阀值"  id="overi" name="overi" maxlength="9" value="${detectorData.overi}"/>
						</div>
						<label class="col-sm-2 control-label">剩余电流阀值(mA)</label>
				        <div class="col-sm-4">
				        	<input type="text" class="form-control" placeholder="请输入剩余电流阀值" id="il" name="il" maxlength="9" value="${detectorData.il}"/>
				        </div> 
			</div>
			<div class="form-group">
						 <label class="col-sm-2 control-label">箱体温度阀值(℃)</label>
						 <div class="col-sm-4">
							      <input type="text" class="form-control" placeholder="请输入箱体温度阀值" id="ti" name="ti" maxlength="9" value="${detectorData.ti}"/>
						</div>
			</div>
		<div id="singleHoldDiv">
	 	<div class="form-group">
					 <label class="col-sm-2 control-label">零线温度阀值(℃)</label>
					 <div class="col-sm-4">
					 	<input type="text" class="form-control" placeholder="请输入零线温度阀值" id="tn" name="tn" maxlength="9" value="${detectorData.tn}"/>
					 </div>
					<label class="col-sm-2 control-label">火线温度阀值(℃)</label>
			        <div class="col-sm-4">
			        		<input type="text" class="form-control" placeholder="请输入火线温度阀值"  id="tl" name="tl" maxlength="9" value="${detectorData.tl}"/>
			        </div> 
		</div>
	</div>
	<div id="threeHoldDiv" class="hide">
	 	<div class="form-group">
					 <label class="col-sm-2 control-label">A相温度阀值(℃)</label>
					 <div class="col-sm-4">
						<input type="text" class="form-control" placeholder="请输入A相温度阀值" id="ta" name="ta" maxlength="9" value="${detectorData.ta}"/>
					</div>
					<label class="col-sm-2 control-label">B相温度阀值(℃)</label>
			        <div class="col-sm-4">
			        	<input type="text" class="form-control" placeholder="请输入B相温度阀值" id="tb" name="tb" maxlength="9" value="${detectorData.tb}"/>
			        </div> 
			</div>
			<div class="form-group">
					 <label class="col-sm-2 control-label">C相温度阀值(℃)</label>
					 <div class="col-sm-4">
						<input type="text" class="form-control" placeholder="请输入C相温度阀值" id="tc" name="tc" maxlength="9" value="${detectorData.tc}"/>
					</div>
			</div>
		</div>
	</div> --%>
</form>
</div>
</div>
</section>
  <%@include file="../common/js.jsp" %>
  <%@include file="../common/component/switchEnt.jsp" %>
   <script type="text/javascript" src="${webRoot}/res/js/common/component/switchEnt.js"></script>
  <script type="text/javascript">
  		var requestType = "${requestType}";
  		var type = "${detectorData.type}";
  </script>
 <script src="res/js/detectordata/addOrEditdetector.js" type="text/javascript"></script>
</body>
</html>

