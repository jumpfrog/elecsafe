/**
 * 数据定义
 */
var param = {};
var html="";
var deviceInitUrl = "real/deviceList.action";
var queryEntUrl =  "real/realdata_queryEnt.action";
/**
 * 初始化
 */
$(function(){
	init();
})

/**
 * 函数定义
 */
function init(){
	setTimeout(function(){
		$("#listDiv").height(getFrHeight_ExBtnPagebar());
	}, 500)
	//$("#listDiv").height(getFrHeight_ExBtnPagebar());
	initPagingToolbar(query);
	$("#queryBtn").on("click",function(){
		setCurrentPage(1);
		query();
	});
	$("#province").on("change",function(){
		var provinceid = $(this).val();
		loadCity(provinceid,$("#city"));
	});
	query();
}

function query(){
	param={};
	param.pageIndex=$("#currentPage").val();
	param.pageLimit=PAGE_LIMIT;
	param.eid=$("#eid").val();
	param.keyword=$("#keyword").val();
	param.province=$("#province").val();
	param.city=$("#city").val();
	$.ajax({
		type:"POST",
		url:queryEntUrl,
		data:param,
		dataType:'json',
        cache: false,
        beforeSend:showLoading(),
       // complete:hideLoading(),
        success: function(data){
        	if(preprocess(data))return false;
        	if(data.success){
        		var dataList = data.page.root;
            	$("#listDiv").empty();
            	if(dataList && dataList.length > 0){
            		$(dataList).each(function(index,item){
            			var temphtml  = createEnterpriseData(item);	
            			$("#listDiv").append(temphtml);
            		})
            	}else{
            		$("#listDiv").append("<p class='text-center'>暂无数据</p>");
            	}
            	updatePageParams(data.page);
        	}else{
        		layerBox.msgWarning(data.message)
        	}
        	hideLoading()
        }
	})
}
function createEnterpriseData(data){
	html='';
	html+='<div class="col-sm-4 col-md-3" style="cursor: pointer;" eid="'+data.eid+'" entName="'+data.name+'" onclick="deviceInit(this)">';
	html+='<div class="box box-default" style="border-top-color:#fff;box-shadow:0 1px 55px rgba(0,0,0,0.15);margin-top:8px;">';
	html+='<div class="box-header with-border" style="padding-bottom:0;">';
	html+='<h4 class="box-title" style="font-size:16px;">';
	html+='<a class="text-aqua" data-toggle="tooltip" data-placement="bottom" data-original-title="'+data.name+'">'+data.name+'</a>';
	html+='</h4>';
	html+='<h5 class="text-muted"><span class="fa fa-clock-o">'+getDefaultData(data.updateTimeStr)+'</span></h5>';
	html+='</div>';
	html+='<div class="box-body" style="padding-top:0;">';
	html+='<div class="col-xs-4 no-padding">';
	html+='<img class="img-circle" style="width:100px;height:100px" src="'+IMG_SRC+'res/img/frame/default_logo.jpg">';
	html+='</div>';
	html+='<div class="col-xs-8">';
	
	html+='<div style="margin-top: 8">';	
	html+='<span class="fa fa-check-circle-o text-green"> 正常</span>';
	html+='<span class="pull-right" style="color:#2492cf;">'+data.normalPer+' %</span>';
	html+='</div>';	
	
	html+='<div style="margin-top: 10">';
	html+='<span class="fa fa-bell-o text-yellow"> 告警</span>';
	html+='<span class="pull-right" style="color:#2492cf;">'+data.waringPer+' %</span>';
	html+='</div>';
	
	html+='<div style="margin-top: 10">';	
	html+='<span class="fa fa-power-off text-red"> 故障</span>';	
	html+='<span class="pull-right" style="color:#2492cf;">'+data.faultPer+' %</span>';	
	html+='</div>';	
	
	html+='<div style="margin-top: 10">';	
	html+='<span class="fa fa-power-off text-gray"> 离线</span>';	
	html+='<span class="pull-right" style="color:#2492cf;">'+data.offLinePer+' %</span>';
	html+='</div>';	
	
	html+='</div>';
	html+='</div>';
	html+='</div>';
	html+='</div>';
	return html;
}

function deviceInit(obj){
	var eid = $(obj).attr("eid");
	if(eid > 0){
		var temphref = deviceInitUrl+"?eid="+eid;
		appendBreadcrumb($(obj).attr("entName"),temphref);//面包屑
//		var datas = {};
//		datas.name1 = "eid";
//		datas.value1 = eid;
		href(temphref);
		return false;
	}
	errorMsg();
}