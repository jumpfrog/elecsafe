/**
 * 数据定义
 */
var param = {};
var enterpriseInitUrl = "real/enterpriseList.action";
var queryDetStatusUrl = "real/realdata_queryDetStatus.action";
var queryNewAlarmUrl = "real/realdata_queryNewAlarm.action";
var queryDetailDetStatusUrl = "real/realdata_queryDetailDetStatus.action";
var html="";
var eventLayerIndex;
//add
var tempIntervalId;
/**
 * 初始化
 */
$(function(){
	init();
})

function alertMusic(){
	$('#chatAudio')[0].play(); //播放声音 
}
function alertNewAlarm(){
	param={};
	param.preTime = preTime;
	param.eid=$("#eid").val();
	$.ajax({
		type:"POST",
		url:queryNewAlarmUrl,
		data:param,
		dataType:'json',
        cache: false,
        success: function(data){
        	var detectorEvent = data.detectorEvent;
        	if(detectorEvent){//<span class="fa fa-clock-o">2017-09-12 14:06:58</span>
        	//	alertMusic();evtdesc
        		var temContent ="<div><span class='fa fa-clock-o'> 报警时间：</span>"+getDefaultData(detectorEvent.datatimestr)+"</div>";
        		 temContent +="<div style='margin-top:10;'><span class='fa fa-bell-o'> 报警信息：</span>"+getDefaultData(detectorEvent.evttypename)+"</div>";
        		 temContent +="<div style='margin-top:10;'><span class='fa fa-file-text-o'> 报警内容：</span>"+getDefaultData(detectorEvent.evtdesc)+"</div>";
        		 var tempIcon=0;
        		var tempBgColor="layui-layer-yellow";
        		if(detectorEvent.isAlarm){
        			tempIcon=0;
        			tempBgColor="background-color:#f39c12;color:#fff";// #f39c12
        		}else{
        			tempIcon=2;
        			tempBgColor="background-color:#dd4b39;color:#fff";//#dd4b39
        		}
        		layer.open({
      			  title: [detectorEvent.detname,tempBgColor]
      			//  ,icon:tempIcon
      			  //,skin: tempBgColor //样式类名 
      			  ,content: temContent
      			  ,btn: ['确定', '查看']
      			,yes:function(index){
      			layer.close(index)
      			},
      			btn2:function(){
      				goPageByPageId('55000000')
      			}
      			});
//        		eventLayerIndex = layer.open({
//        			  type: 1,
//        			  title: '告警信息',
//        			  zIndex:10,
//        			  skin: 'layui-layer-lan', //样式类名 
//        			  area: ['600px', '200px'], //宽高
//        			  content: $('#newEvtDeal_modal') ,
//        			});
        		preTime = detectorEvent.datatimestr;
        	}
        }
	
	})
}

function refreshDeviceInit(){
	query();
	alertNewAlarm();
}
/**
 * 函数定义
 */
function init(){
	if(entCount == 1){
		backBreadcrumb1();
	}else{
		backBreadcrumb2();
	}
	$("#newEvenConfirmBtn").on("click",function(){
		if(eventLayerIndex && eventLayerIndex > 0){
			layerBox.close(eventLayerIndex);
			eventLayerIndex=0;
		}
	})
	//refreshFn(query,10*1000);
	refreshFn(refreshDeviceInit,10*1000);
	$("#listDiv").height(getFrHeight_ExBtnPagebar());
	$("#detailDetStatusDiv").height($("#mainContent").height());
	$("#iChart").height($("#mainContent").height()/2-45);
	$("#tChart").height($("#mainContent").height()/2-45);
	initChartWidth($("#iChart"),600);
	initChartWidth($("#tChart"),600);
	initPagingToolbar(query);
	$("#returnBtn").on("click",function(){
		backBreadcrumb1();
		href(enterpriseInitUrl);
	})
	$("#detailReturnBtn").on("click",function(){
		stopRefreshDetail();
		isShowDetail(false);
	})
	$("#queryBtn").on("click",function(){
		setCurrentPage(1);
		query();
	});
	query();
	window.onresize = function () {
	    	initChartWidth($("#iChart"),600);
	    	initChartWidth($("#tChart"),600);
	    	if(iChart){
	    		iChart.resize();
	    	}
	    	if(tChart){
	    		tChart.resize();
	    	}
	    }
}

function isShowDetail(show,name){
	if(show){
		appendBreadcrumb(name);
		$("#mainContent").addClass("hide");
		$("#detailDetStatusDiv").removeClass("hide");
	}else{
		if(entCount == 1){
			backBreadcrumb1();
		}else{
			backBreadcrumb2();
		}
		$("#detailDetStatusDiv").addClass("hide");
		$("#mainContent").removeClass("hide");
	}
}

function query(){
	//backBreadcrumb2();
	param={};
	param.pageIndex=$("#currentPage").val();
	param.pageLimit=500;
	param.eid=$("#eid").val();
	param.keyword=$("#keyword").val();
	$.ajax({
		type:"POST",
		url:queryDetStatusUrl,
		data:param,
		dataType:'json',
        cache: false,
      //  beforeSend:showLoading(),
       // complete:hideLoading(),
        success: function(data){
        	if(preprocess(data))return false;
        	if(data.success){
        	var dataList = data.page.root;
        	$("#listDiv").empty();
        	if(dataList && dataList.length > 0){
        		$(dataList).each(function(index,item){
        			var temphtml  = createDeviceData(item);	
        			$("#listDiv").append(temphtml);
        		})
        	}else{
        		$("#listDiv").append("<p class='text-center'>暂无数据</p>");
        	}
        	}else{
        		layerBox.msgWarning(data.message)
        	}
        	//hideLoading();
        }
	
	})
}

function createDeviceData(data){
	var tempBtnHtml = "";
	html='';
	html+='<div class="pilldiv" onclick="showDetail(this)" detid="'+data.detid+'" detname="'+data.name+'">';
	var temStatus = getDeviceStatus(data);
	if(DEVICE_STATUS_NORMAL == temStatus){//正常
		html+='<div class="pilldivColor statecolorOk">';
		html+='</div>';
		html+='<div class="nameCount">';
		html+="正常";
		html+='</div>';
		tempBtnHtml = '<button class="pilldivButton statecolorOk">'
	}else if(DEVICE_STATUS_WARING == temStatus){//告警
		html+='<div class="pilldivColor statecolorWaring">';
		html+='</div>';
		html+='<div class="nameCount">';
		html+="告警";
		html+='</div>';
		tempBtnHtml = '<button class="pilldivButton statecolorWaring">'
	}else if(DEVICE_STATUS_FAULT == temStatus){//故障
		html+='<div class="pilldivColor statecolorFault">';
		html+='</div>';
		html+='<div class="nameCount">';
		html+="故障";
		html+='</div>';
		tempBtnHtml = '<button class="pilldivButton statecolorFault">'
	}else if(DEVICE_STATUS_WARING_FAULT == temStatus){//告警+故障
		html+='<div class="pilldivColor statecolorFault">';
		html+='</div>';
		html+='<div class="nameCount">';
		html+="故障";
		html+='</div>';
		
		html+='<div class="pilldivColor statecolorWaring pilldivSub">';
		html+='</div>';
		html+='<div class="nameCount nameCountSub">';
		html+="告警";
		html+='</div>';
		tempBtnHtml = '<button class="pilldivButton statecolorFault">'
	}else if(DEVICE_STATUS_WARING_OFFLINE == temStatus){//告警+离线
		html+='<div class="pilldivColor statecolorOffLine">';
		html+='</div>';
		html+='<div class="nameCount">';
		html+="离线";
		html+='</div>';
		
		html+='<div class="pilldivColor statecolorWaring pilldivSub">';
		html+='</div>';
		html+='<div class="nameCount nameCountSub">';
		html+="告警";
		html+='</div>';
		
		tempBtnHtml = '<button class="pilldivButton statecolorOffLine">'
	}else if(DEVICE_STATUS_FAULT_OFFLINE == temStatus){//故障+离线
		html+='<div class="pilldivColor statecolorOffLine">';
		html+='</div>';
		html+='<div class="nameCount">';
		html+="离线";
		html+='</div>';
		
		html+='<div class="pilldivColor statecolorFault pilldivSub">';
		html+='</div>';
		html+='<div class="nameCount nameCountSub">';
		html+="故障";
		html+='</div>';
		tempBtnHtml = '<button class="pilldivButton statecolorOffLine">'
	}else if(DEVICE_STATUS_WARING_FAULT_OFFLINE == temStatus){//告警+故障+离线
		html+='<div class="pilldivColor statecolorOffLine">';
		html+='</div>';
		html+='<div class="nameCount">';
		html+="离线";
		html+='</div>';
		
		html+='<div class="pilldivColor statecolorFault pilldivSub">';
		html+='</div>';
		html+='<div class="nameCount nameCountSub">';
		html+="故障";
		html+='</div>';
		
		html+='<div class="pilldivColor statecolorWaring pilldivSub">';
		html+='</div>';
		html+='<div class="nameCount nameCountSub">';
		html+="告警";
		html+='</div>';
		tempBtnHtml = '<button class="pilldivButton statecolorOffLine">'
	}else{//离线
		html+='<div class="pilldivColor statecolorOffLine">';
		html+='</div>';
		html+='<div class="nameCount">';
		html+="离线";
		html+='</div>';
		tempBtnHtml = '<button class="pilldivButton statecolorOffLine">'
	}
	
	
	html+='<div class="pilldivS">';
	html+='<div class="pilldivInfo">';
	html+='<div class="redname">';
	if(DEVICE_STATUS_NORMAL == temStatus){
		html+='<img class="img-rounded" style="width:60px;height:100px" src="'+IMG_SRC+'res/img/device/inverter_runstatus_run.gif'+'">';
	}else if(0 == temStatus || DEVICE_STATUS_OFFLINE == temStatus || DEVICE_STATUS_WARING_OFFLINE == temStatus || DEVICE_STATUS_FAULT_OFFLINE == temStatus || DEVICE_STATUS_WARING_FAULT_OFFLINE == temStatus){
		html+='<img class="img-rounded" style="width:60px;height:100px" src="'+IMG_SRC+'res/img/device/inverter_runstatus_stop.gif'+'">';
	}
	else{
		html+='<img class="img-rounded" style="width:60px;height:100px" src="'+IMG_SRC+'res/img/device/inverter_runstatus_alarm.gif'+'">';
	}
	
	html+='</div>';
		
	html+='<div class="rednameRight">';
	html+=data.name;
	html+='</div>';
	
	html+='<div class="rednameRight">';
	html+=getDefaultData(data.updateTimeStr);
	html+='</div>';
	html+='</div>';
	html+='</div>';
	
	
	html+=tempBtnHtml;
	html+='<span class="fa fa-shield"></span>';
	html+='</button>';
	html+='</div>';
	return html;
}


////////设备详细实时数据////////////////////////
function showDetail(obj){
	var detid = $(obj).attr("detid");
	if(detid > 0){
		refreshDetail(detid);
		initDetailRealData(detid,true);
	}else{
		layerBox.msgWarning("暂无数据");
	}
}

function refreshDetail(detid){
	tempIntervalId = setInterval(function(){
		initDetailRealData(detid,false);
	},5*60*1000);
	
}

function stopRefreshDetail(){
	if(tempIntervalId){
		clearInterval(tempIntervalId);
	}
}


function getDetStatusStr(data){
	var temstatus = getDeviceStatus(data);
	if(DEVICE_STATUS_NORMAL == temstatus){//正常
		return '正常';
	}else if(DEVICE_STATUS_WARING == temstatus){//告警
		return '告警';
	}else if(DEVICE_STATUS_FAULT == temstatus){//故障
		return '故障';
	}else if(DEVICE_STATUS_OFFLINE == temstatus){//离线
		return '离线';
	}else if(DEVICE_STATUS_WARING_FAULT == temstatus){//故障,告警
		return '故障,告警';
	}else if(DEVICE_STATUS_WARING_OFFLINE == temstatus){//离线,告警
		return '离线,告警';
	}else if(DEVICE_STATUS_FAULT_OFFLINE == temstatus){//离线,故障
		return '离线,故障';
	}else if(DEVICE_STATUS_WARING_FAULT_OFFLINE == temstatus){//离线,故障,告警
		return '离线,故障,告警';
	}else{
		return '离线';
	}
}
function initDetailRealData(detid,isAdd){
	param={}
	param.detid = detid;
	$.ajax({
		type:"POST",
		url:queryDetailDetStatusUrl,
		data:param,
		dataType:'json',
        cache: false,
        timeout : 3000, //超时时间设置，单位毫秒
        beforeSend:showLoading(),
        error : function(xhr,textStatus){
        	hideLoading();
        	if('timeout' == textStatus){
        		layerBox.msgWarning("请求超时");
        	}
          },
     //  complete:hideLoading(),
        success: function(data){
        	if(preprocess(data))return false;
        	if(data.success){
        		var realData = data.realData;
        		if(realData){
        			$("#entEnergyDistUl").empty();
        			var detL1HisList = realData.detL1HisList;
        			$("#detname").text(getDefaultData(realData.name));
        			$("#linestatus").text(getDefaultData(realData.lineStatusStr));
        			$("#detmodal").text(getDefaultData(realData.brand));
        			$("#linestatus").text(getDetStatusStr(realData));
        			warpChartData(realData);
    				initChart();
        			if(detL1HisList.length > 0){
        				$("#entEnergyDistUl").html(getDetStatusListData(detL1HisList[detL1HisList.length-1],realData));
        			}else{
        				$("#entEnergyDistUl").html("<li class='text-center'>暂无数据</li>");
        			}
        			
        		if(isAdd){
        			isShowDetail(true,realData.name);	
        		}
        		}
        	}else{
        		layerBox.msgWarning(data.message)
        	}
        	hideLoading()
        }
        })
}

function getDetStatusListData(data,realData){
		html="";
		var tempOverI = getDefaultZeroData(realData.overi);//电流阀值
		var tempIL = getDefaultZeroData(realData.il);//剩余电流阀值
		var tempTI = getDefaultZeroData(realData.ti);//内部温度阀值
		
		var tempTA = getDefaultZeroData(realData.ta);//A相温度阀值
		var tempTB = getDefaultZeroData(realData.tb);//B相温度阀值
		var tempTC=  getDefaultZeroData(realData.tc);//C相温度阀值
		
		var tempTN = getDefaultZeroData(realData.tn);//零线温度阀值
		var tempTL = getDefaultZeroData(realData.tl);//火线温度阀值
		
		var temil = getDefaultZeroData(data.il);//剩余电流
		var temti = getDefaultZeroData(data.ti);//箱体温度
		
		var temia = getDefaultZeroData(data.ia);//A相电流
		var temib = getDefaultZeroData(data.ib);//B相电流
		var temic = getDefaultZeroData(data.ic);//C相电流
		var temi = getDefaultZeroData(data.i);//单相电流
		
		
		var temta = getDefaultZeroData(data.ta);//A相温度
		var temtb = getDefaultZeroData(data.tb);//B相温度
		var temtc = getDefaultZeroData(data.tc);//C相温度
		
		var temtn = getDefaultZeroData(data.tn);//零线温度
		var temtl = getDefaultZeroData(data.tl);//火线温度
		if(THREE_PHASE == realData.type){//三相
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'A相电流'+'&nbsp</span>';
			
			if(tempOverI > 0 && temia > tempOverI){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temia+'A</span>';
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temia+'A</span>';
			}
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
			
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'B相电流'+'&nbsp</span>';
			if(tempOverI > 0 && temib > tempOverI){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temib+'A</span>';
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temib+'A</span>';	
			}
			
			
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
			
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'C相电流'+'&nbsp</span>';
			if(tempOverI > 0 && temib > tempOverI){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temic+'A</span>';
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temic+'A</span>';
			}
			
			
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
			
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'A相温度'+'&nbsp</span>';
			if(tempTA > 0 && temta > tempTA){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temta+'℃</span>';	
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temta+'℃</span>';
			}
			
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
			
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'B相温度'+'&nbsp</span>';
			if(tempTB > 0 && temtb > tempTB){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temtb+'℃</span>';	
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temtb+'℃</span>';
			}
			
			
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
			
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'C相温度'+'&nbsp</span>';
			if(tempTC > 0 && temtc > tempTC){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temtc+'℃</span>';
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temtc+'℃</span>';
			}
			
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
		}else if(SINGLE_PHASE == realData.type){//单相
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'电流'+'&nbsp</span>';
			if(tempOverI > 0 && temi > tempOverI){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temi+'A</span>';
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temi+'A</span>';
			}
			
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
			
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'零线温度'+'&nbsp</span>';
			if(tempTN > 0 && temtn > tempTN){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temtn+'℃</span>';
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temtn+'℃</span>';
			}
			
			
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
			
			html += '<li class="list-group-item" style="height:10%">';
			html += '<div class="product-info" style="margin-left: 0px;">';
			html += '<a href="javascript:;" class="product-title" style="cursor:default">';
			html += '<span>'+'火线温度'+'&nbsp</span>';
			if(tempTL > 0 && temtl > tempTL){
				html += '<span class="label label-danger pull-right" style="width: 65px;">'+temtl+'℃</span>';
			}else{
				html += '<span class="label label-success pull-right" style="width: 65px;">'+temtl+'℃</span>';
			}
			
			
			html += '</a>';
			html += '<div class="product-description">';
			html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
			html += '</div>';
			html += '</div>';
			html += '</li>';
		}
	
		html += '<li class="list-group-item" style="height:10%">';
		html += '<div class="product-info" style="margin-left: 0px;">';
		html += '<a href="javascript:;" class="product-title" style="cursor:default">';
		html += '<span>'+'剩余电流'+'&nbsp</span>';
		if(tempIL > 0 && temil > tempIL){
			html += '<span class="label label-danger pull-right" style="width: 65px;">'+temil+'mA</span>';
		}else{
			html += '<span class="label label-success pull-right" style="width: 65px;">'+temil+'mA</span>';
		}
		
		
		html += '</a>';
		html += '<div class="product-description">';
		html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
		html += '</div>';
		html += '</div>';
		html += '</li>';
		
		html += '<li class="list-group-item" style="height:10%">';
		html += '<div class="product-info" style="margin-left: 0px;">';
		html += '<a href="javascript:;" class="product-title" style="cursor:default">';
		html += '<span>'+'箱体温度'+'&nbsp</span>';
		if(tempTI > 0 && temti > tempTI){
			html += '<span class="label label-danger pull-right" style="width: 65px;">'+temti+'℃</span>';
		}else{
			html += '<span class="label label-success pull-right" style="width: 65px;">'+temti+'℃</span>';
		}
		html += '</a>';
		html += '<div class="product-description">';
		html += '<span class="fa fa-clock-o"> '+data.longDatatimeStr+'</span>';
		html += '</div>';
		html += '</div>';
		html += '</li>';
	return html;
}


           