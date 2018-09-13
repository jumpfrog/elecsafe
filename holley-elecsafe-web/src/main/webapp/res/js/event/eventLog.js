var param = {};
var queryEventListUrl = "event/eventLog_queryList.action";
var selectedItem;
var descFontNum = 20;//事件描述默认显示文字的个数
var dealBtnId = "1315";
$(document).ready(function(){
	setTimeout(function(){
		resizeTableHeight($("#eventTable"),getFrHeight_ExBtnPagebar());
	}, 500)
	if(isShowBtn(dealBtnId)){
		$("#dealBtn").removeClass("hide");
	}else{
		$("#dealBtn").addClass("hide");
	}
	initStartEndDateTimeLeft($("#startTimeDiv"),$("#endTimeDiv"),TIME_LONG,null,null);
	dealEventItem();

	//descFontNum = Math.floor((getViewSize().width - 975)/13);
	//descFontNum = descFontNum < 0?40:descFontNum;
	//alert(descFontNum)
	//切换企业事件
	$("#switchEntBtn").on("click",function(){
		popupSwitchEnt();
	});
	
	//查询按钮事件
	$("#queryBtn").on("click",function(){
		queryList();
	});
	
	//处理按钮事件
	$("#dealBtn").on("click",function(){
		onDealClick();
	});
	
	//到处按钮事件
	$("#exportBtn").on("click",function(){
		exportList();
	});
	
	queryList();
});

/**
 * 处理事件类型下拉框数据
 * @returns
 */
function dealEventItem(){
	$("#eventItemCombo").html('');
	var html = '';
	
	var eventClassList =  $.parseJSON(eventClassItemList);
	if(!eventClassList || eventClassList.length == 0){
		return;
	}
	
	$(eventClassList).each(function(index,item){
		html += '<optgroup label="'+item.eventclassname+'" value="'+item.dataclass+'">';;
		$(item.eventitems).each(function(index2,item2){
			html += '<option value="'+item2.eventitem+'">'+item2.eventitemname+'</option>';
		});
		html += '</optgroup>';
	});
	$("#eventItemCombo").html(html);
	$("#eventItemCombo").multiselect('rebuild');
	$("#eventItemCombo").multiselect('refresh');
}

/**
 * 查询监测点事件
 * @returns
 */
function queryList(){
	param = {};
	param.eid = $.trim($("#switchEntBtn").attr("cur_eid"));
	param.starttime = $("#startTime").val();
	param.endtime = $("#endTime").val();
	param.dealstatus = $("#dealStatusCombo option:selected").val();
	param.keyword = $.trim($("#keyword").val());
	param.pageindex = $.trim($("#currentPage").val());
	param.pagelimit = PAGE_LIMIT;
	
	var eventItems = getSelectCheckedValues($("#eventItemCombo"));
	if(eventItems != null || eventItems.length > 0){
		param.eventtypes = eventItems.join(",") ;
	}
	
	removeTableAllData($("#eventTable"));
	
	$.ajax({
		type:'POST',
		url:queryEventListUrl,
		data:param,
		dataType:'json',
		cache:false,
		beforeSend:function(){
			showLoading();
			$('#eventTable').bootstrapTable('showLoading');
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var dataList = data.page.root;
				if(dataList != null && dataList.length > 0){
					loadTableData($("#eventTable"),dataList);
//					descFontNum = descFontNum<0?50:descFontNum;
				}
				updatePageParams(data.page);
			}else{
				layerBox.msgWarning(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
			$('#eventTable').bootstrapTable('hideLoading');
	    }
	});
}


/**
 * 处理按钮点击事件
 * @returns
 */
function onDealClick(){
	var evts = getBsTableCheckeds($("#eventTable"));
	if(evts == null || evts.length < 1){
		layerBox.msgWarning("请选择至少1条要处理的事件");
		return;
	}
	if(evts.length == 1){
		selectedItem = evts[0];
		if(selectedItem.dealstatus == 2){
			layerBox.msgConfirm("该事件已被处理过，确定要再处理?",function(){
				popupEventDealWin();
			},true);
		}else{
			popupEventDealWin();
		}
	}else{
		var alarmNum = 0;
		var faultNum = 0;
		var unprocessedNum = 0;
		var processedNum = 0;
		$(evts).each(function(index,item){
			if(item.evttype > 100){
				faultNum ++;
			}else{
				alarmNum ++;
			}
			if(item.dealstatus == 2){
				processedNum ++;
			}else{
				unprocessedNum ++;
			}
		});
		var msg = "要处理的事件共【"+evts.length+"】条";
		msg += ",其中：";
		if(alarmNum > 0){
			msg += "<br/>告警事件【"+alarmNum+"】条;";
		}
		if(faultNum > 0){
			msg += "<br/>故障事件【"+faultNum+"】条;";
		}
		if(unprocessedNum > 0){
			msg += "<br/>未处理事件【"+unprocessedNum+"】条;";
		}
		if(processedNum > 0){
			msg += "<br/>已处理事件【"+processedNum+"】条;";
		}
		
		layerBox.msgConfirm(msg+"<br/>确定要批量处理这些事件吗?",function(){
			popupEventDealBatchWin();
		},true);
	}
}

/**
 * 导出列表
 * @returns
 */
function exportList(){
	param = {};
	param.isExport = true;
	param.fileName = "事件日志";
	param.eid = $.trim($("#switchEntBtn").attr("cur_eid"));
	param.starttime = $("#startTime").val();
	param.endtime = $("#endTime").val();
	param.dealstatus = $("#dealStatusCombo option:selected").val();
	param.keyword = $.trim($("#keyword").val());
	
	var eventItems = getSelectCheckedValues($("#eventItemCombo"));
	if(eventItems != null || eventItems.length > 0){
		param.eventtypes = eventItems.join(",") ;
	}
	
	var url = queryEventListUrl;
	url += "?" + jQuery.param(param);
	href(url);
}

/**
 * 设置事件类型列样式
 * @param value
 * @param row
 * @param index
 * @returns
 */
function renderEvtTypeStyle(value,row,index,field){
	if(row.evttype > 100){
		return {"classes":"text-red hand"}
	}else{
		return {"classes":"text-yellow hand"}
	}
}

/**
 * 设置事件描述列样式
 * @param value
 * @param row
 * @param index
 * @returns
 */
function renderEvtDesc(value,row,index){
	var html = '<a href="javascript:;">'+substring(value,descFontNum);+'</a>';
	return html;
}

/**控件参数初始化********************************************************************/
$('#eventItemCombo').multiselect({
    enableClickableOptGroups: true,
    multiple:true,
    includeSelectAllOption: true,//是否显示全选框
    selectAllText: '全选事件',
    selectAllValue: 'selectAll',
    selectAll:true,
    maxHeight:250,
    buttonWidth:'140px',
    nonSelectedText: '请选择',
    nSelectedText: '项事件',
    allSelectedText: '全部事件',
    numberDisplayed:1,
    buttonClass: 'btn btn-default btn-sm text-left'
});

/**
 * 表格绑定事件
 */
$('#eventTable').bootstrapTable({ 
	'onClickCell':function onClickCell(field,value,row,e){
		if(isEmpty(value)){
			return;
		}
		if(field == "evtdesc"){
			layerBox.msgInfo("事件描述",value);
		}else if(field == "evttypename"){
			selectedItem = row;
			popupEventDealDetailWin();
		}
	}
})

$(window).resize(function() {
	resizeTableWidth($("#eventTable"));
});

