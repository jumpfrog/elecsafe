var param = {};
var loadCityUrl = "comm/searchComm_queryCityByProvince.action";
var searchEntUrl = "comm/searchComm_searchEnterprise.action";
var layerModalIndex;
$(document).ready(function(){
	initPagingToolbar(searchEnt);
	
	//加载城市树和行业树数据
	$("#provinceCombo").on("change",function(){
		queryCityByProvince();
	});
	//搜索企业按钮事件
	$("#searchEntBtn").on("click",function(){
		searchEnt();
	});
	
	//清除企业列表事件
	$("#clearEntBtn").on("click",function(){
		removeTableAllData($("#entTable"));
	});
	
	//确定按钮事件
	$("#saveEntBtn").on("click",function(){
		saveEnt();
	});
	
	$("#closeEntBtn").on("click",function(){
		closeSearchEnt();
	});
});

//载入区域树和行业树数据
function queryCityByProvince(){
	param={};
	param.provinceid = $("#provinceCombo option:selected").val();
	param.tm = new Date().getTime();
	
	$("#cityCombo").html('<option value="0">请选择市</option>');
	
	if(!param.provinceid || param.provinceid < 1){
		return;
	}
	
	$.ajax({
		type:'POST',
		url:loadCityUrl,
		data:param,
		dataType:'json',
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			var cityList = data.cityList;
			var html = '';
			$(cityList).each(function(index,item){
				html += '<option value="'+item.cityid+'">'+item.name+'</option>';
			});
			$("#cityCombo").append(html);
		}
	});
}

/**
 * 搜索企业
 * @returns
 */
function searchEnt(){
	param = {};
	param.keyword = $.trim($("#keyword").val());
	param.provinceid = $("#provinceCombo option:selected").val();
	param.cityid = $("#cityCombo option:selected").val();
	param.pageindex = $.trim($("#currentPage2").val());
	param.pagelimit = PAGE_LIMIT;
	
	removeTableAllData($("#entTable"));
	$.ajax({
		type : 'POST',
		url : searchEntUrl,
		data : param,
		dataType : 'json',
		cache : false,
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var dataList = data.page.root;
				if(dataList && dataList.length > 0){
					loadTableData($("#entTable"),dataList);
				}
				resizeTableHeight($("#entTable"),220);
				updatePageParams(data.page);
			}else{
				layerBox.msgWarning(data.message)
				//showInfo(data.message);
			}
			
		}
	});
}

/**
 * 清空搜索表单和企业列表数据
 * @returns
 */
function clearEntForm(){
	resetForm($("#entQryForm"));
	$("#provinceCombo").val(0);
	$("#cityCombo").val(0);
	removeTableAllData($("#entTable"));
}

/**
 * 确定选择的企业
 * @returns
 */
function saveEnt(){
	var selectedItem = getBsTableChecked($("#entTable"));;
	if(selectedItem == null){
		layerBox.msgWarning("请选择一个企业");
	//	showInfo("请选择一个企业");
		return;
	}
	
	$("#eid").val(selectedItem.eid);
	$("#edisc").val(selectedItem.disc);
	closeSearchEnt();
}

/**
 * 弹出搜索企业框
 * @returns
 */
function popupSearchEnt(){
	layerModalIndex = layer.open({
		  type: 1,
		  title: '请选择企业信息',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  area: ['550px', '510px'], //宽高
		  offset: ['10%', '30%'],
		  content: $('#searchEntModal') ,
		  end :clearEntForm()
		});
	clearEntForm();
	//默认搜索企业
	searchEnt();
}

/**
 * 关闭搜索企业框
 * @returns
 */
function closeSearchEnt(){
	layer.close(layerModalIndex);
}

