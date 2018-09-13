/**
 * 数据定义
 */
var queryHistoryListUrl = "history/historydata_query.action";
var queryDetHChartDataUrl = "history/historydata_queryDetHChartData.action";
var param = {};
var textRed = 'text-red';
var textGreen = 'text-green';
var textYellow = 'text-yellow';
var detname;
/**
 * 初始化
 */

$(function(){
	init();
	$("#exportPdfBtn").on("click",function(){
		if(isEmpty(detname)){
			createJsPdf($('#pdfDiv'));
		}else{
			createJsPdf($('#pdfDiv'),detname);
		}
		
	});
})



/**
 * 初始化函数
 */
function init(){
	initHisToryChart();
	setTimeout(function(){
		resizeTableHeight($("#histotydataTable"),getFrHeight_ExBtnPagebar());
		//$("#detailDetHistoryChartDiv").height($("#mainContent").height());
		$("#iChart").height(getFrHeight_ExBtnPagebar()/2-40);
		$("#tChart").height(getFrHeight_ExBtnPagebar()/2-40);
	}, 500)
	
	//resizeTableHeight($("#histotydataTable"),getFrHeight_ExBtnPagebar());
	initStartEndDateDefault($('#startDateDiv'),$('#endDateDiv'),TIME_SHORT);
	initPagingToolbar(query);
	initExportBtn($("#exportBtn"),$("#btnbarFrom"),$("#fileName").val(),$("#histotydataTable"));
	$("#queryBtn").on("click",function(){
		setCurrentPage(1);
		query();
	});

	$("#switchEntBtn").on("click",function(){
		popupSwitchEnt();
	});
	query();
}
function initHistoryCharts(obj){
	var detid = $(obj).attr("detid");
	detname = $(obj).attr("detname");
	if(detid > 0){
		setDateTime($('#startHChartDateDiv'),startHChartDateStr);
		setDateTime($('#endHChartDateDiv'),endHChartDateStr);
		$("#detid").val(detid);
		showHChart(detid,true);
	}else{
		errorMsg();
	}
}

function isShowList(b,value,isinit){
	if(b){
		backBreadcrumb1();
		$("#detailDetHistoryChartDiv").addClass("hide");
		$("#mainContent").removeClass("hide");
	}else{
		if(isinit){
			appendBreadcrumb(value);
		}
		$("#mainContent").addClass("hide");
		$("#detailDetHistoryChartDiv").removeClass("hide");
	}
}
//render定义
function renderDetName(value, row, index) {
	var temhtml = "<a href='javascript:;' onclick='initHistoryCharts(this)' detid='"+row.detid+"' detname='"+row.name+"' data-toggle='tooltip' data-placement='right' data-original-title='点击查看曲线图'>"+value+"</a>";
    return temhtml;
}

function renderDetThreeI(value, row, index) {
	if(THREE_PHASE == row.type){
		if(value == null){
			return  "-";
		}
		
		if(row.holdOveri != null && row.holdOveri > 0 && value > row.holdOveri){
			return "<span class='text-red'>"+value+"</span>";
		}
		
		return "<span class='text-green'>"+value+"</span>";
	}else{
		return "-";
	}
}

function renderDetSingleI(value, row, index) {
	if(SINGLE_PHASE == row.type){
		if(value == null){
			return  "-";
		}
		
		if(row.holdOveri != null && row.holdOveri > 0 && value > row.holdOveri){
			return "<span class='text-red'>"+value+"</span>";
		}
		
		return "<span class='text-green'>"+value+"</span>";
	}else{
		return "-";
	}
}

function renderDetIL(value, row, index) {
		if(value == null){
			return  "-";
		}
		
		if(row.holdIl != null && row.holdIl > 0 && value > row.holdIl){
			return "<span class='text-red'>"+value+"</span>";
		}
		
		return "<span class='text-green'>"+value+"</span>";
	
}

function renderDetThreeTA(value, row, index) {
	if(THREE_PHASE == row.type){
		if(value == null){
			return  "-";
		}
		
		if(row.holdTa != null && row.holdTa > 0 && value > row.holdTa){
			return "<span class='text-red'>"+value+"</span>";
		}
		
		return "<span class='text-green'>"+value+"</span>";
	}else{
		return "-";
	}
}

function renderDetThreeTB(value, row, index) {
	if(THREE_PHASE == row.type){
		if(value == null){
			return  "-";
		}
		
		if(row.holdTb != null && row.holdTb > 0 && value > row.holdTb){
			return "<span class='text-red'>"+value+"</span>";
		}
		
		return "<span class='text-green'>"+value+"</span>";
	}else{
		return "-";
	}
}

function renderDetThreeTC(value, row, index) {
	if(THREE_PHASE == row.type){
		if(value == null){
			return  "-";
		}
		
		if(row.holdTc != null && row.holdTc > 0 && value > row.holdTc){
			return "<span class='text-red'>"+value+"</span>";
		}
		
		return "<span class='text-green'>"+value+"</span>";
	}else{
		return "-";
	}
}

function renderDetSingleTL(value, row, index) {
	if(SINGLE_PHASE == row.type){
		if(value == null){
			return  "-";
		}
		
		if(row.holdTl != null && row.holdTl > 0 && value > row.holdTl){
			return "<span class='text-red'>"+value+"</span>";
		}
		
		return "<span class='text-green'>"+value+"</span>";
	}else{
		return "-";
	}
}

function renderDetSingleTN(value, row, index) {
	if(SINGLE_PHASE == row.type){
		if(value == null){
			return "-";
		}
		if(row.holdTn != null && row.holdTn > 0 && value > row.holdTn){
			return "<span class='text-red'>"+value+"</span>";
		}
		return "<span class='text-green'>"+value+"</span>";
	}else{
		return "-";
	}
}

function renderDetTI(value, row, index){
	if(value == null){
		return "-";
	}
	
	if(row.holdTi != null && row.holdTi > 0 && value > row.holdTi){
		return "<span class='text-red'>"+value+"</span>";
	}
	
	return "<span class='text-green'>"+value+"</span>";
}
//

//函数定义//
function query(){
	backBreadcrumb1();
	param={};
	param.pageIndex=$("#currentPage").val();
	param.pageLimit=PAGE_LIMIT;
	param.keyword=$("#keyword").val();
	param.eid = $.trim($("#switchEntBtn").attr("cur_eid"));
	param.startDate = $("#startDate").val();
	param.endDate = $("#endDate").val();
	param.type=$("#type").val();
	$.ajax({
		type:"POST",
		url:queryHistoryListUrl,
		data:param,
		dataType:'json',
        cache: false,
        beforeSend:function(){
        	showLoading();
        	$('#histotydataTable').bootstrapTable('showLoading');
        },
        complete:function(){
        	hideLoading();
        	$('#histotydataTable').bootstrapTable('hideLoading');
        },
        success: function(data){
        	if(preprocess(data))return false;
        	if(data.success){
        		 var dataList = data.page.root;
            	 loadTableData($('#histotydataTable'),dataList);
            	 updatePageParams(data.page);
        	}else{
        		layerBox.msgWarning(data.message)
        	}
        }
	})
}

