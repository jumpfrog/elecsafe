var param={};
$(document).ready(function(){
	initPagingToolbar(queryList);
	initPagingToolbar2(queryDetailList);
	initStartEndDateLeft($('#startDateDiv'),$('#endDateDiv'));
	initStartEndDateLeft($('#startDateDiv2'),$('#endDateDiv2'));
	$("#webLogDiv").height(getWindowHeight()-190);
	$("#webLogDetailDiv").height(getWindowHeight()-230);
	$("#queryBtn").on('click',function(){
		setCurrentPage(1);
		queryList();
	});
	initExportBtn($("#exportBtn"),$("#webLogFrom"),$("#fileName").val(),$("#webLogTable"));
	//用户登录详细页面查询导出
	$("#queryDetailBtn").on('click',function(){
		setCurrentPage(1);
		queryDetailList();
	})
	initExportBtn($("#exportDetailBtn"),$("#webLogDetailForm"),$("#fileName").val(),$("#webLogDetailTable"));
	queryList();
});
function queryList(){
	param={};
	param.startdate =  $.trim($("#startDate").val());
	param.enddate =  $.trim($("#endDate").val());
	param.keyword = $.trim($("#keyword").val());
	param.pageIndex = $.trim($("#currentPage").val());
	param.pageLimit = PAGE_LIMIT;
	
	if(!isEmpty(param.startdate) && !isEmpty(param.enddate)){
		if(shortStrToDate(param.startdate) >=  shortStrToDate(param.enddate)){
			//showInfo("结束时间必须大于开始时间");
			layerBox.msgWarning("结束时间必须大于开始时间");
			return;
		}
	}
	var tbody = $("#webLogTable").find("tbody");
	var html = '';
	$.ajax({
		type:"POST",
		url:'sys/webLog_query.action',
		data:param,
		dataType:'json',
        cache: false,
        beforeSend:function(){
			showLoading();
		},
        success: function(data,options){
        	if(preprocess(data))return;
             if(data.success){
            	 tbody.empty();
            	 var dataList = data.page.root;
            	 $(dataList).each(function(index,item){
            		 html += '<tr>';
            		 html +='<td>'+item.account+'</td>';
            		 html += '<td>'+item.name+'</td>';
            		 html += '<td>'+item.accountTypeStr+'</td>';
            		 html += '<td>'+getNotNullData(item.esname)+'</td>';
            		 html += '<td><a href="javascript:;" onclick="showDetail(this)" account="'+item.account+'">'+item.count+'</a></td>';
            		 html += '<td>'+item.logtimeStr+'</td>';
            		 html += '<td>';
            		 html += '</td>';
            	 });
            	 tbody.html(html);
            	 var currentPage = data.page.startRow/(data.page.endRow-data.page.startRow)+1;
            	 var totalPage = Math.ceil(data.page.totalProperty/(data.page.endRow-data.page.startRow));
            	 setPagingToolbarParams(data.page.totalProperty, totalPage, currentPage);
             }
         },
         complete:function(){
	    	  hideLoading();
	    }
     });
}
function showDetail(obj){
	$("#account").val($(obj).attr("account"));
	$('#startDate2').val($('#startDate').val());
	$('#endDate2').val($('#endDate').val());
	account = $(obj).attr("account");
	setCurrentPage2(1);
	showResult("showWebLogDetail");
	queryDetailList();
}

function showResult(flag){
	if("showWebLogDetail" == flag){
		$("#webLogDetailList").removeClass("hide");
		$("#webLogList").addClass("hide");
		setCurrentPage2(1);
	}else if("webLogList" == flag){
		$("#webLogDetailList").addClass("hide");
		$("#webLogList").removeClass("hide");
	}
}

function queryDetailList(){
	param = {};
	param.startdate =  $.trim($("#startDate2").val());
	param.enddate =  $.trim($("#endDate2").val());
	param.pageIndex = $.trim($("#currentPage2").val());
	param.account = account;
	param.pageLimit = PAGE_LIMIT;
	if(!isEmpty(param.startdate) && !isEmpty(param.enddate)){
		if(shortStrToDate(param.startdate) >=  shortStrToDate(param.enddate)){
			//showInfo("结束时间必须大于开始时间");
			layerBox.msgWarning("结束时间必须大于开始时间");
			return;
		}
	}
	var tbody = $("#webLogDetailTable").find("tbody");
	var html = '';
	$.ajax({
		type:"POST",
		url:'sys/webLog_queryDetaiList.action',
		data:param,
		dataType:'json',
        cache: false,
        beforeSend:function(){
			showLoading();
		},
        success: function(data,options){
        	if(preprocess(data))return;
             if(data.success){
            	 tbody.empty();
            	 var dataList = data.page.root;
            	 $(dataList).each(function(index,item){
            		 html += '<tr>';
            		 html +='<td>'+item.account+'</td>';
            		 html += '<td>'+item.logtimeStr+'</td>';
            		 html += '<td>';
            		 html += '</td>';
            	 });
            	 tbody.html(html);
            	 var currentPage = data.page.startRow/(data.page.endRow-data.page.startRow)+1;
            	 var totalPage = Math.ceil(data.page.totalProperty/(data.page.endRow-data.page.startRow));
            	 setPagingToolbar2Params(data.page.totalProperty, totalPage, currentPage);
             }
         },
         complete:function(){
	    	  hideLoading();
	    }
     });
}