<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<style>
.form-group {
    margin-bottom: 5px;
}
.chat .item > .message {
    margin-left: 10px;
    margin-top: 0px;
}
</style>
<!-- 模态框（Modal） 搜索企业 -->
<div id="newEvtDeal_modal" style="display:none;">
  <div class="modal-body" style="padding-bottom: 0">
 	<form class="form-horizontal" role="form" enctype="multipart/form-data" style="margin-bottom: 10px;">
		<div class="form-group">
		  <label class="col-sm-2 control-label">监测点</label>
	      <div class="col-sm-4">
	      	<p id="newEvtDeal_detname" class="form-control-static"></p>
	      </div>
	      <label class="col-sm-2 control-label">企业名称</label>
	      <div class="col-sm-4">
	      	<p id="newEvtDeal_ownername" class="form-control-static"></p>
	      </div>
    	</div>
    	<div class="form-group">
		  <label class="col-sm-2 control-label">事件类型</label>
	      <div class="col-sm-4">
	      	<p id="newEvtDeal_evttype" class="form-control-static"></p>
	      </div>
	      <label class="col-sm-2 control-label">产生时间</label>
	      <div class="col-sm-4">
	      	<p id="newEvtDeal_datatime" class="form-control-static"></p>
	      </div>
    	</div>
    	<div class="form-group">
		  <label class="col-sm-2 control-label">事件描述</label>
	      <div class="col-sm-10">
	      	<p id="newEvtDeal_evtdesc" class="form-control-static"></p>
	      </div>
    	</div>
    	<div class="form-group">
    	 <div class="col-sm-12 text-right">
			<button id="newEvenConfirmBtn" type="button" class="btn btn-primary btn-sm">确定</button>
		</div>
    	</div>
	</form>
   </div>
</div>

