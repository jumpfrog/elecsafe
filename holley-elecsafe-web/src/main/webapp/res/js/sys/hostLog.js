var param = {};

var contentFontNum = 0;
$(document).ready(function(){
	initPagingToolbar(queryHostlogList);
	$("#tableDiv").height(getWindowHeight()-190);
	initStartEndDateLeft($('#startDateDiv'),$('#endDateDiv'));
	$("#queryBtn").on("click",function(){
		setCurrentPage(1);
		queryHostlogList();
	});
	initExportBtn($("#exportBtn"),$("#btnbarFrom"),$("#fileName").val());
	queryHostlogList();
	
	$("#content").width(getWindowWidth() - 650);
	contentFontNum = Math.floor(($("#content").width())/8);
	$("#closeButton").on('click',function(){
		var index = layer.index;
		layer.close(index);
	})
});

function queryHostlogList(){
	param={};
	param.startDate = $.trim($("#startDate").val());
	param.endDate = $.trim($("#endDate").val());
	param.keyword = $.trim($("#keyword").val());
	param.logtype = $("#logType option:selected").val();
	param.pageIndex = $.trim($("#currentPage").val());
	param.pageLimit = PAGE_LIMIT;
	if(!isEmpty(param.startDate) && !isEmpty(param.endDate)){
		if(shortStrToDate(param.startDate) >=  shortStrToDate(param.endDate)){
			//showInfo("结束时间必须大于开始时间");
			layerBox.msgWarning("结束时间必须大于开始时间");
			return;
		}
	}
	var tbody = $("#hostLogTable").find("tbody");
	var html = '';
	$.ajax({
			type:"post",
			url:'sys/hostLog_query.action',
			data:param,
			dataType:'json',
			cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(data.page){
				tbody.empty();
				var dataList = data.page.root;
				 $(dataList).each(function(index,item){
					 html += '<tr>';
					 html += '<td>'+item.account+'</td>';
					 html += '<td>'+item.name+'</td>';
					 html += '<td>'+item.logtimeStr+'</td>';
					 html += '<td>'+item.logtypeStr+'</td>';
					 html += '<td>'+item.ip+'</td>';
					 if (item.content.length <= contentFontNum) {
						 html += "<td><a href='javascript:;' onclick='queryContent(this)' content='"+item.content+"'>"+item.content+"</a></td>"
					 } else {
						 html += "<td><a href='javascript:;' onclick='queryContent(this)' content='"+item.content+"'>"+item.content.substring(0,contentFontNum)+'...'+"</a></td>"
					 }
            		 html += '</tr>';
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

function queryContent(obj) {
	layer.open({
		  type: 1,
		  title: '操作日志详细信息',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  area: ['48%', '51%'], //宽高
		  content: $('#contentModal') 
		});
	var content = $(obj).attr("content");
	$("#contentDiv").val(content);
};