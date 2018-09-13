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
<div id="evtDeal_modal" style="display:none;">
  <div class="modal-body" style="padding-bottom: 0">
 	<form class="form-horizontal" role="form" enctype="multipart/form-data" style="margin-bottom: 10px;">
		<div class="form-group">
		  <label class="col-sm-2 control-label">监测点</label>
	      <div class="col-sm-4">
	      	<p id="evtDeal_detname" class="form-control-static"></p>
	      </div>
	      <label class="col-sm-2 control-label">企业名称</label>
	      <div class="col-sm-4">
	      	<p id="evtDeal_ownername" class="form-control-static"></p>
	      </div>
    	</div>
    	<div class="form-group">
		  <label class="col-sm-2 control-label">事件类型</label>
	      <div class="col-sm-4">
	      	<p id="evtDeal_evttype" class="form-control-static"></p>
	      </div>
	      <label class="col-sm-2 control-label">产生时间</label>
	      <div class="col-sm-4">
	      	<p id="evtDeal_datatime" class="form-control-static"></p>
	      </div>
    	</div>
    	<div class="form-group">
		  <label class="col-sm-2 control-label">事件描述</label>
	      <div class="col-sm-10">
	      	<p id="evtDeal_evtdesc" class="form-control-static"></p>
	      </div>
    	</div>
    	<div class="form-group">
	      <label class="col-sm-2 control-label">处理状态</label>
	      <div class="col-sm-4">
	      	<p id="evtDeal_dealstatus" class="form-control-static"></p>
	      </div>
    	</div>
	</form>
	<div class="box box-info">
    	<div class="box-header">
          <i class="fa fa-comments-o"></i>
          <h3 class="box-title">处理意见</h3>
        </div>
        <div id="evtDeal_remarkBox" class="box-body chat">
        </div>
        <div id="evtDeal_box_footer" class="box-footer">
          <div class="input-group" style="padding-left: 10px;padding-right: 10px;">
            <textarea id="evtDeal_remark" class="form-control" rows="2" placeholder="请输入处理意见..." maxlength="256"  style="height: 50px;"></textarea>
            <span class="input-group-btn">
              <button id="evtDeal_saveBtn" type="button" class="btn btn-primary btn-flat"  style="height: 50px;width: 50px;"><i class="fa fa-plus fa-lg"></i></button>
            </span>
          </div>
        </div>
    </div>
   </div>
</div>

<!-- 模态框（Modal） 搜索企业 -->
<div id="evtDealBatch_modal" style="display:none;">
   <div class="modal-body" style="padding-bottom: 0">
        <div class="form-group">
        	<textarea id="evtDealBatch_remark" class="form-control" rows="5" placeholder="请输入处理意见..."  ></textarea>
        </div>
   </div>
   <div class="modal-footer">
     <button id="evtDealBatch_saveBtn" type="button" class="btn btn-primary btn-sm">确定</button>
     <button id="evtDealBatch_closeBtn" type="button" class="btn btn-primary btn-sm" >关闭</button>
   </div>
</div>

