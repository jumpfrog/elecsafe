var param = {};
var queryListUrl = "detector/detectordata_query.action";
var detectorAddOrEditUrl = "detector/detectorAddOrEdit.action";
var delUrl = "detector/detectordata_delDetector.action";
var detailUrl = "detector/detectordata_queryDetail.action";

var addBtnId = "1312";
var editBtnId = "1313";
var delBtnId = "1314";
var editBtnShow = false;
var delBtnShow = false;
$(document).ready(function(){
	$("#switchEntBtn").on("click",function(){
		popupSwitchEnt();
	});
	disableBack();
	//初始化列表高度
	resizeTableHeight($("#detectordataTable"),getFrHeight_ExBtnPagebar());
	//初始化列表内按钮是否显示
	isShowBtn();
	
	//初始化按钮工具条
	var basebtns = [{buttonid:"queryBtn",disc:"查询"},{buttonid:"exportBtn",disc:"导出"}];
	var exceptbtns = [{buttonid:editBtnId},{buttonid:delBtnId}];
	var html = getModuleBtns(basebtns,exceptbtns);
	if(!isEmpty(html)){
  		$("#btnbar").append(html);
	}
	
	//初始化分页条
	initPagingToolbar(query);
	
	//查询按钮点击事件
	$("#queryBtn").on("click",function(){
		setCurrentPage(1);
		query();
	});
	
	//初始化新增按钮
	$("#buttondef_"+addBtnId).on("click",function(){
		href(detectorAddOrEditUrl+"?requestType="+REQUEST_TYPE_ADD);
	});
	
	//初始化导出按钮
	initExportBtn($("#exportBtn"),$("#btnbarFrom"),$("#fileName").val(),$("#accountTable"));
	
	//用户详细返回按钮
	$("#returnBtn").on("click",function(){
		query();
		isShowList(true);
	});
	
	query();
	
});



function renderOperate(value, row, index){
	var btemhtml='<div class="dropdown">';
	btemhtml += '<button type="button" class="btn btn-xs dropdown-toggle td-btn" id="dropdownMenu1" data-toggle="dropdown">';
	btemhtml +='<span>详情</span>&nbsp&nbsp<span class="caret"></span>';
	btemhtml += '</button>';
	btemhtml += '<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="width:100%">';
	if(editBtnShow){
		btemhtml += '<li role="presentation">';
		btemhtml += '<a role="menuitem" tabindex="-1" href="'+detectorAddOrEditUrl+'?detid='+row.id+'&requestType='+REQUEST_TYPE_EDIT+'">修改</a>';
		btemhtml += '</li>';
	}
	if(delBtnShow){
		btemhtml += '<li role="presentation">';
		btemhtml += '<a role="menuitem" tabindex="-1" href="javascript:;" onclick="showDelTip(this)" detid="'+row.id+'" name="'+row.name+'">删除</a>';
		btemhtml += '</li>';
	}
	
	btemhtml += '<li role="presentation">';
	btemhtml += '<a role="menuitem" tabindex="-1" href="javascript:;" onclick="showDetail(this)" detid="'+row.id+'">详细</a>';
	btemhtml += '</li>';
	btemhtml += '</ul></div>';
	return btemhtml;
}

function query(){
	param = {};
	param.keyword = $.trim($("#keyword").val());
	param.type = $("#type").val();
	param.pageindex = $("#currentPage").val();
	param.pagelimit = PAGE_LIMIT;
	param.eid = $.trim($("#switchEntBtn").attr("cur_eid"));
	$.ajax({
		type:"POST",
		url:queryListUrl,
		data:param,
		dataType:"json",
		cache:false,
		beforeSend:function(){
			showLoading();
			$('#detectordataTable').bootstrapTable('showLoading');
		},
		complete:function(){
	    	hideLoading();
	    	$('#detectordataTable').bootstrapTable('hideLoading');
	    },
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				var dataList = data.page.root;
				 loadTableData($('#detectordataTable'),dataList);
            	 updatePageParams(data.page);
			}else{
				layerBox.msgWarning(data.message);
			}
		}
	});
}

//设备删除提示
function showDelTip(obj){
	var name = $(obj).attr("name");
	var detId = $(obj).attr("detId");
	layerBox.msgConfirm("确定删除设备【"+name+"】吗?",function(){delDetector(detId)}, true)
}
//删除监测点
function delDetector(detid){
	if(isEmpty(detid) || detid <= 0){
		errorMsg();
		return false;
	}
	param={};
	param.detid = detid;
	$.ajax({
		type:"POST",
		url:delUrl,
		data:param,
		dataType:"json",
		cache:false,
	    timeout : 3000, //超时时间设置，单位毫秒
	    beforeSend:showLoading(),
	    error : function(xhr,textStatus){
	        	//hideLoading();
	        	if('timeout' == textStatus){
	        		layerBox.msgWarning("请求超时");
	        	}
	          },
	    complete:hideLoading(),	     
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				layerBox.msgOk("删除监测点成功",query);
			}else{
				layerBox.msgWarning(data.message);
			}
		}
	});
	
}





//监测点详细信息
function showDetail(obj){
	var detid = $(obj).attr("detid");
	param = {};
	param.detid = detid;
	
	$.ajax({
		type:"POST",
		url:detailUrl,
		data:param,
		dataType:"json",
		cache:false,
		timeout : 3000, //超时时间设置，单位毫秒
	    beforeSend:showLoading(),
	    error : function(xhr,textStatus){
	        	if('timeout' == textStatus){
	        		layerBox.msgWarning("请求超时");
	        	}
	          },
	    complete:hideLoading(),	     
	    success:function(data){
			if(preprocess(data))return;
			if(data.success){
				var record = data.detector;
				$("#detail_name").text(record.name);
				$("#detail_entName").text(record.entName);
				$("#detail_brand").text(record.brand);
				$("#detail_type").text(record.typeStr);
				$("#detail_protocolName").text(record.protocolName);
				$("#detail_commaddr").text(record.commaddr);
				$("#detail_installaddr").text(getDefaultData(record.installaddr));
				$("#detail_time").text(record.updatetimeStr);
				$("#detail_overu").text(getDefaultData(record.overu));
				$("#detail_underu").text(getDefaultData(record.underu));
				$("#detail_overi").text(getDefaultData(record.overi));
				$("#detail_il").text(getDefaultData(record.il));
				$("#detail_ti").text(getDefaultData(record.ti));
				$("#detail_tn").text(getDefaultData(record.tn));
				$("#detail_tl").text(getDefaultData(record.tl));
				$("#detail_ta").text(getDefaultData(record.ta));
				$("#detail_tb").text(getDefaultData(record.tb));
				$("#detail_tc").text(getDefaultData(record.tc));
				if(SINGLE_PHASE == record.type){
					$("#threeHoldDiv").addClass("hide");
					$("#singleHoldDiv").removeClass("hide");
				}else if(THREE_PHASE == record.type){
					$("#singleHoldDiv").addClass("hide");
					$("#threeHoldDiv").removeClass("hide");
				}
				isShowList(false);
			}else{
				layerBox.msgWarning(data.message);
			}
		}
	});
	
}

function isShowList(flag){
	if(flag){
		$("#detDetailDiv").addClass("hide");
		$("#detListDiv").removeClass("hide");
	}else{
		$("#detListDiv").addClass("hide");
		$("#detDetailDiv").removeClass("hide");
	}
	
}

//判断修改按钮和删除按钮是否有权限，有权限才可显示。
function isShowBtn(){
	editBtnShow = false;
	delBtnShow = false;
	var btnList = getButtondefsByModule();
	if(btnList == null || btnList.length == 0){
		return;
	}
	for(var i=0;i<btnList.length;i++){
		if(editBtnId == btnList[i].buttonid){
			editBtnShow = true;
		}else if(delBtnId == btnList[i].buttonid){
			delBtnShow = true;
		}
	}
}

