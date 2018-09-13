<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
  <!--编辑企业信息-->
  <div id="entEditForm" class="hide">
  	
      <form class="form-inline" role="form" >
  	   <div class="form-group">
  	      	<a href="javascript:;">
  	  			<span id="ent_backBtn" class="glyphicon glyphicon-menu-left backBtn" data-toggle="tooltip" data-placement="right" title="返回" aria-hidden="true"></span>
      		</a>
  	  </div>
      <div class="form-group pull-right">
        <button id="ent_saveBtn" type="button" class="btn btn-primary btn-sm">保存</button>
		<button id="ent_cancelBtn" type="button" class="btn btn-primary btn-sm">返回</button>
	  </div>
  	</form>
      
      
      
      <div class="box box-info">
      	<div class="box-header ui-sortable-handle">
          <h3 class="box-title">企业信息</h3>
        </div>
        <div class="box-body">
		  <form id="entForm" class="form-horizontal" role="form">
			<div class="col-sm-6">
				<div class="form-group">
			   		<label class="col-sm-4 control-label"><span style="color: red;">*</span>企业名称</label>
			       <div class="col-sm-8">
			      		<input type="text" class="form-control" id="edit_disc" name="disc" maxlength="64" value="<s:property value='#request.enterprise.disc'/>"/>
			        	<input type="hidden" id="ent_eid"/>
			        </div>
			   	</div>
			   	<div class="form-group">
			        <label class="col-sm-4 control-label"><span style="color: red;">*</span>所属地区</label>
			        <div class="col-sm-8">
			      		<ul id="edit_citycombox" name="city" class="easyui-combotree" data-options="valueField:'id', textField:'text', panelHeight:'200px'" style="width:432px;height: 34px;"></ul>
			        </div>
			   	</div>
			   	<div class="form-group">
			   		<label class="col-sm-4 control-label"><span style="color: red;">*</span>联系人</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="edit_linkman" name="linkman" maxlength="32" value="<s:property value='#request.enterprise.linkman'/>"/>
			        </div>
			   	</div>
			   	<div class="form-group">
			        <label class="col-sm-4 control-label">电子邮箱</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="edit_email" name="email" maxlength="32" value="<s:property value='#request.enterprise.email'/>"/>
			        </div>
			   	</div>
			   	<div class="form-group">
			   		<label class="col-sm-4 control-label">法人代表</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="edit_legalperson" name="legalperson" maxlength="32" value="<s:property value='#request.enterprise.legalperson'/>"/>
			        </div>
			   	</div>
			   	<div id="deptDiv" class="form-group">
			   		<label class="col-sm-4 control-label"><span style="color: red;" id="show">*</span>所属企业</label>
			        <div class="col-sm-8">
			        	<div class="input-group">
				      		<input type="text" class="form-control hide" id="eid2" name="ownerid"/>
			      			<input type="text" class="form-control" id="edisc2" name="ownername" disabled="disabled"/>
				      		<span id="ent_searchDeptBtn2" class="input-group-addon" style="cursor: pointer"><span class="fa fa-fw fa-search"></span></span>
				      		<span id="ent_clearDeptBtn2" class="input-group-addon" style="cursor: pointer"><span class="fa fa-fw fa-trash"></span></span>
			        	</div>
			        </div>
			   	</div>
			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label class="col-sm-4 control-label"><span style="color: red;">*</span>企业名称简称</label>
			  			<div class="col-sm-8">
				      	  <input type="text" id="edit_abbr" name="abbr" class="form-control" maxlength="5" >
				        </div>
			   	</div>
			   	<div class="form-group">
			        <label class="col-sm-4 control-label"><span style="color: red;">*</span>所属行业</label>
			        <div class="col-sm-8">
			      		 <ul id="edit_businesscombox" name="business" class="easyui-combotree" data-options="valueField:'id', textField:'text', panelHeight:'200px'" style="width:432px; height: 34px;"></ul>
			        </div>
			   	</div>
			   	<div class="form-group">
			        <label class="col-sm-4 control-label"><span style="color: red;">*</span>联系电话</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="edit_telephone" name="telephone" maxlength="32" value="<s:property value='#request.enterprise.telephone'/>"/>
			        </div>
			   	</div>
			   	<div class="form-group">
			       <label class="col-sm-4 control-label">企业类别</label>
			        <div class="col-sm-8">
			      		<select id="edit_grade" name="grade" class="form-control">
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
			        <label class="col-sm-4 control-label">主要经营活动</label>
			        <div class="col-sm-8">
			      		<input type="text" class="form-control" id="edit_activities" name="activities" maxlength="128" value="<s:property value='#request.enterprise.activities'/>"/>
			        </div>
			   	</div>
			   	<div class="form-group">
			   	   <label class="col-sm-4 control-label"><span style="color: red;">*</span>经纬度</label>
			  			<div class="col-sm-6">
			  			<p class="form-control" id="lngLatInfo2"></p>
				        </div>
				        <div class="col-sm-2">
			  			<span id="lngLatMapIcon2" onclick="showLngLatMap2(this)" class="fa fa-map-marker pull-center" style="font-size: 30px;cursor: pointer;"></span>
				        </div>
			   	</div>
			</div>
		  </form>
        </div>
	  </div>
  </div>

