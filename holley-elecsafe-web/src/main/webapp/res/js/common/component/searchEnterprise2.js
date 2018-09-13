var param = {};
var loadCityUrl = "comm/searchComm_queryCityByProvince.action";
var searchEntUrl = "comm/searchComm_searchEnterprise.action";
var layerModalIndex2;
$(document).ready(function(){
	initPagingToolbar2(searchEnt);
	
	//加载城市树和行业树数据
	$("#provinceCombo2").on("change",function(){
		queryCityByProvince2();
	});
	//搜索企业按钮事件
	$("#searchEntBtn2").on("click",function(){
		searchEnt2();
	});
	
	//清除企业列表事件
	$("#clearEntBtn2").on("click",function(){
		$("#entTable").find("tbody").empty();
	});
	
	//确定按钮事件
	$("#saveEntBtn2").on("click",function(){
		saveEnt2();
	});
	
	$("#closeEntBtn2").on("click",function(){
		closeSearchEnt2();
	});
});

//载入区域树和行业树数据
function queryCityByProvince2(){
	param={};
	param.provinceid = $("#provinceCombo2 option:selected").val();
	param.tm = new Date().getTime();
	
	$("#cityCombo2").html('<option value="0">请选择市</option>');
	
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
			$("#cityCombo2").append(html);
		}
	});
}

/**
 * 搜索企业
 * @returns
 */
function searchEnt2(){
	param = {};
	param.keyword = $.trim($("#keyword2").val());
	param.provinceid = $("#provinceCombo2 option:selected").val();
	param.cityid = $("#cityCombo2 option:selected").val();
	param.pageindex = $.trim($("#currentPage2").val());
	param.pagelimit = PAGE_LIMIT;
	
	removeTableAllData($("#entTable2"));
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
					loadTableData($("#entTable2"),dataList);
				}
				resizeTableHeight($("#entTable2"),220);
				updatePageParams2(data.page);
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
function clearEntForm2(){
	resetForm($("#entQryForm2"));
	$("#provinceCombo2").val(0);
	$("#cityCombo2").val(0);
	removeTableAllData($("#entTable2"));
}

/**
 * 确定选择的企业
 * @returns
 */
function saveEnt2(){
	var selectedItem = getBsTableChecked($("#entTable2"));;
	if(selectedItem == null){
		layerBox.msgWarning("请选择一个企业");
		//showInfo("请选择一个企业");
		return;
	}
	
	$("#eid2").val(selectedItem.eid);
	$("#edisc2").val(selectedItem.disc);
	closeSearchEnt2();
}

/**
 * 弹出搜索企业框
 * @returns
 */
function popupSearchEnt2(){
	layerModalIndex2 = layer.open({
		  type: 1,
		  title: '请选择企业信息',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  area: ['550px', '510px'], //宽高
		  offset: ['10%', '30%'],
		  content: $('#searchEntModal2') ,
		  end :clearEntForm2()
		});
	clearEntForm2();
	//默认搜索企业
	searchEnt2();
}

/**
 * 关闭搜索企业框
 * @returns
 */
function closeSearchEnt2(){
	layer.close(layerModalIndex2);
}

