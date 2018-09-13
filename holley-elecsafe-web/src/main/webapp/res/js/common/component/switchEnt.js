var switchParam = {};
var loadCityUrl = "comm/searchComm_queryCityByProvince.action";
var searchEntUrl = "comm/searchComm_searchEnterprise.action";
var switchLayerIndex;
var callBackFun;//回调方法
$(document).ready(function(){
	resizeTableHeight($("#switchEnt_entTable"),220);
	initPagingToolbar2(searchEnt4SwitchEnt);
	
	//加载城市树和行业树数据
	$("#switchEnt_provinceCombo").on("change",function(){
		queryCityByProvince4SwitchEnt();
	});
	//搜索企业按钮事件
	$("#switchEnt_searchBtn").on("click",function(){
		setCurrentPage2(1);
		searchEnt4SwitchEnt();
	});
	
	//清除企业列表事件
	$("#switchEnt_clearBtn").on("click",function(){
		$("#switchEntBtn").text("请选择企业");
		$("#switchEntBtn").attr("cur_eid","");
		$("#switchEntBtn").attr("cur_eidsc","");
		closeSwitchEnt();
		//removeTableAllData($("#switchEnt_entTable"));
	});
	
	//确定按钮事件
	$("#switchEnt_saveBtn").on("click",function(){
		saveSwitchEnt();
	});
	
	$("#switchEnt_closeBtn").on("click",function(){
		closeSwitchEnt();
	});
});

//载入区域树和行业树数据
function queryCityByProvince4SwitchEnt(){
	switchParam={};
	switchParam.provinceid = $("#switchEnt_provinceCombo option:selected").val();
	switchParam.tm = new Date().getTime();
	
	$("#switchEnt_cityCombo").html('<option value="0">请选择市</option>');
	
	if(!switchParam.provinceid || switchParam.provinceid < 1){
		return;
	}
	
	$.ajax({
		type:'POST',
		url:loadCityUrl,
		data:switchParam,
		dataType:'json',
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			var cityList = data.cityList;
			var html = '';
			$(cityList).each(function(index,item){
				html += '<option value="'+item.cityid+'">'+item.name+'</option>';
			});
			$("#switchEnt_cityCombo").append(html);
		}
	});
}

/**
 * 搜索企业
 * @returns
 */
function searchEnt4SwitchEnt(){
	switchParam = {};
	switchParam.keyword = $.trim($("#switchEnt_keyword").val());
	switchParam.provinceid = $("#switchEnt_provinceCombo option:selected").val();
	switchParam.cityid = $("#switchEnt_cityCombo option:selected").val();
	switchParam.pageindex = $.trim($("#currentPage2").val());
	switchParam.pagelimit = PAGE_LIMIT;
	removeTableAllData($("#switchEnt_entTable"));
	//$("#switchEnt_entTable").find("tbody").empty();
	$.ajax({
		type : 'POST',
		url : searchEntUrl,
		data : switchParam,
		dataType : 'json',
		cache : false,
		beforeSend:function(){
			showLoading();
			$('#switchEnt_entTable').bootstrapTable('showLoading');
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var dataList = data.page.root;
				if(dataList && dataList.length > 0){
					loadTableData($("#switchEnt_entTable"),dataList);
				}
				
				updatePageParams2(data.page);
			}else{
				showInfo(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
			$('#switchEnt_entTable').bootstrapTable('hideLoading');
	    }
	});
}

/**
 * 清空搜索表单和企业列表数据
 * @returns
 */
function clearEntForm4SwitchEnt(){
	resetForm($("#switchEnt_qryForm"));
	$("#switchEnt_provinceCombo").val(0);
	$("#switchEnt_cityCombo").val(0);
	removeTableAllData($("#switchEnt_entTable"));
}

/**
 * 确定选择的企业
 * @returns
 */
function saveSwitchEnt(){
	var selectedItem = getBsTableChecked($("#switchEnt_entTable"));;
	if(selectedItem == null){
		layerBox.msgWarning("请选择一个企业");
		return;
	}
	
	$("#switchEntBtn").text(selectedItem.disc);
	$("#switchEntBtn").attr("cur_eid",selectedItem.eid);
	$("#switchEntBtn").attr("cur_eidsc",selectedItem.disc);
	closeSwitchEnt();
	if(callBackFun){
		callBackFun();
	}
}

/**
 * 弹出搜索企业框
 * @returns
 */
function popupSwitchEnt(fn){
	if(fn){
		callBackFun = fn;
	}
	switchLayerIndex = layer.open({
		  type: 1,
		  title: '请选择企业信息',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  area: ['550px', '510px'], //宽高
		  content: $('#switchEnt_modal') ,
		  end :clearEntForm4SwitchEnt
		});
//	clearEntForm4SwitchEnt();
	//默认搜索企业
	searchEnt4SwitchEnt();
}

/**
 * 关闭搜索企业框
 * @returns
 */
function closeSwitchEnt(){
	layer.close(switchLayerIndex);
}

