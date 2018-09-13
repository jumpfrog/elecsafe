var param = {};
var queryEventDealLogUrl = "event/eventLog_queryEventDealLog.action";
var saveEventDealUrl = "event/eventLog_saveEventDeal.action";
$(document).ready(function(){
	//处理单个事件
	$("#evtDeal_saveBtn").on("click",function(){
		saveDealResult();
	});
	
	//批量处理事件
	$("#evtDealBatch_saveBtn").on("click",function(){
		saveDealResultBatch();
	});
	
	//关闭事件处理弹出框
	$("#evtDealBatch_closeBtn").on("click",function(){
		closeEventDealWin();
	});
});

/***单个事件处理*****start*********************************************/
/**
 * 弹出事件处理框
 * @returns
 */
function popupEventDealWin(){
	//$("#evtDeal_box_footer").show();
	clearEventDealForm();
	setEventInfo();
	layer.open({
		  type: 1,
		  title: '事件处理',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  area: ['660px', '525px'], //宽高
		  content: $('#evtDeal_modal') ,
		  end :clearEntForm4SwitchEnt()
		});
	//默认搜索企业
	queryEventDealLog();
}

/**
 * 弹出事件的详细信息
 * @returns
 */
function popupEventDealDetailWin(){
	//$("#evtDeal_box_footer").hide();
	clearEventDealForm();
	setEventInfo();
	layer.open({
		  type: 1,
		  title: '事件处理',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  area: ['660px', '525px'], //宽高
		  content: $('#evtDeal_modal') ,
		  end :clearEntForm4SwitchEnt()
		});
	//默认搜索企业
	queryEventDealLog();
}

/**
 * 设置事件基本信息
 * @returns
 */
function setEventInfo(){
	if(selectedItem){
		$("#evtDeal_detname").text(getNotNullData2(selectedItem.detname,NO_DATA_TEXT));
		$("#evtDeal_ownername").text(getNotNullData2(selectedItem.ownername,NO_DATA_TEXT));
		$("#evtDeal_evttype").text(getNotNullData2(selectedItem.evttypename,NO_DATA_TEXT));
		$("#evtDeal_datatime").text(getNotNullData2(selectedItem.datatimestr,NO_DATA_TEXT));
		$("#evtDeal_evtdesc").text(getNotNullData2(selectedItem.evtdesc,NO_DATA_TEXT));
		$("#evtDeal_dealstatus").text(getNotNullData2(selectedItem.dealstatusname,NO_DATA_TEXT));
	}
}

/**
 * 关闭事件处理框
 * @returns
 */
function closeEventDealWin(){
	layer.closeAll();
//	layer.close(layer.index);
}

/**
 * 清空数据
 * @returns
 */
function clearEventDealForm(){
	$("#evtDeal_remarkBox").html('');
	$("#evtDeal_remark").val("");
	
	$("#evtDeal_detname").text(NO_DATA_TEXT);
	$("#evtDeal_ownername").text(NO_DATA_TEXT);
	$("#evtDeal_evttype").text(NO_DATA_TEXT);
	$("#evtDeal_datatime").text(NO_DATA_TEXT);
	$("#evtDeal_evtdesc").text(NO_DATA_TEXT);
	$("#evtDeal_dealstatus").text(NO_DATA_TEXT);
}

/**
 * 查询时间处理日志
 * @returns
 */
function queryEventDealLog(){
	if(!selectedItem){
		layerBox.msgWarning("请选择一条要处理的事件");
		return;
	}
	param = {};
	param.evtid = selectedItem.evtid;
	
	$.ajax({
		type:'POST',
		url:queryEventDealLogUrl,
		data:param,
		dataType:'json',
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var dataList = data.evtDealList;
				var html = '';
				$("#evtDeal_remarkBox").html('');
				$(dataList).each(function(index,item){
					html += '<div class="item">';
					html += '<p class="message">';
					html += '<a href="javascript:;" class="name">';
					html += '<span>'+item.dealaccountname+'</span>';
					html += '<small class="pull-right" style="color:#c5c5c5"><i class="fa fa-clock-o"></i>&nbsp;'+item.dealtimestr+'</small>';
	                html += '</a>';
	                html += '<span>'+item.dealremark+'</span>';
	                html += '</p>';
	                html += '</div>';
				});
				$("#evtDeal_remarkBox").html(html);
			}else{
				layerBox.msgWarning(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
	    }
	});
}

/**
 * 保存处理意见
 * @returns
 */
function saveDealResult(){
	if(!selectedItem){
		layerBox.msgWarning("请选择1条要处理的事件");
		return;
	}
	
	param = {};
	param.evtids = selectedItem.evtid;
	param.dealremark = $.trim($("#evtDeal_remark").val());
	if(isEmpty(param.dealremark)){
		layerBox.msgWarning("请输入处理意见");
		return;
	}
	$.ajax({
		type:'POST',
		url:saveEventDealUrl,
		data:param,
		dataType:'json',
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				//刷新列表数据
				queryList();
				layerBox.msgOk("事件处理成功!");
				setTimeout(function(){
					closeEventDealWin();
				}, 500);
			}else{
				layerBox.msgWarning(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
	    }
	});
}

/***单个事件处理*****end*********************************************/

/***批量事件处理*****start*******************************************/

/**
 * 弹出事件批量处理框
 * @returns
 */
function popupEventDealBatchWin(){
	$("#evtDealBatch_remark").val("");
	layer.open({
		  type: 1,
		  title: '事件批量处理',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  area: ['660px', '243px'], //宽高
		  content: $('#evtDealBatch_modal') ,
		  end :function(){
			  $("#evtDealBatch_remark").val("");
		  }
		});
}

/**
 * 批量保存处理意见
 * @returns
 */
function saveDealResultBatch(){
	var evtids = getBsTableCheckedIds($("#eventTable"),"evtid");
	if(evtids == null || evtids.length < 2){
		layerBox.msgWarning("请选择至少2条要处理的事件");
		return;
	}
	param = {};
	param.evtids = evtids.join(",") ;
	param.dealremark = $.trim($("#evtDealBatch_remark").val());
	if(isEmpty(param.dealremark)){
		layerBox.msgWarning("请输入处理意见");
		return;
	}
	$.ajax({
		type:'POST',
		url:saveEventDealUrl,
		data:param,
		dataType:'json',
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				//刷新列表数据
				queryList();
				layerBox.msgOk("事件处理成功!");
				setTimeout(function(){
					closeEventDealWin();
				}, 500);
			}else{
				layerBox.msgWarning(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
	    }
	});
}

/***批量事件处理*****end*******************************************/


/**控件参数初始化**************************************************/
$('#evtDeal_remarkBox').slimScroll({
    height: '170px'
  });

