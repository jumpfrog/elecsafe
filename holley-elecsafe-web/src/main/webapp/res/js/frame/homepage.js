var param={};
var queryAlarmUrl = "main/homepage_queryAlarm.action";//查询告警
var alarmPageUrl = "event/eventLog.action";

var realDataPageUrl ="real/enterpriseList.action";
var layerIndex;
var html="";
$(function(){
	// $("#countService").numberAnimate({num:countService, speed:2000, symbol:","});
	 queryAlarm();
	delayRefreshAlarm();
})
function delayRefreshAlarm(){
	setInterval(queryAlarm,5*60*1000);
}

/**
 * 查询告警记录
 */
function queryAlarm(){
	param={};
	$.ajax({
		type:"POST",
		url:queryAlarmUrl,
		data:param,
		dataType:'json',
        cache: false,
        beforeSend:function(){
        	$("#refreshBtn").addClass('fa-spin');
        },
        complete:function(){
        	setTimeout(function(){
        		$("#refreshBtn").removeClass('fa-spin');
        	}, 1500)
        },
        success: function(data){
        	if(preprocess(data))return false;
        	if(data.success){
        		html='';
        		var temphtml='';
        		 var dataList = data.enventList;
        		 if(dataList && dataList.length > 0){
        			 html+='<marquee behavior="scroll" direction="up" height="100%" width="100%" loop="-1" scrollamount="2" scrolldelay="10" onMouseOut="this.start()" onMouseOver="this.stop()">';
        			 html+='<ul id="eventUl" class="products-list product-list-in-box list-group">';
        			 $(dataList).each(function(index,data){
        				
        				 if(index < 2){
        					 temphtml +='<li class="list-group-item" onclick="goPageByPageId(55000000)" style="border: 0px;cursor: pointer;">';
        					 temphtml +='<div class="product-info" style="margin-left: 0px;">'
        					 temphtml +='<a class="product-title">';
        					 temphtml +='<span>';
        					 temphtml += getDefaultData(data.evttypename);
        					 temphtml +='</span>';
            				if(data.isAlarm){
            					temphtml +='<span class="label label-warning pull-right boxStyle">'
            					temphtml += '告警';
            				}else{
            					temphtml +='<span class="label label-danger pull-right boxStyle">'
            					temphtml += '故障';
            				}
            				temphtml +='</span>';
            				temphtml +='</a>';
            				temphtml +='<div class="product-description"><span class="fa fa-clock-o">';
            				temphtml += getDefaultData(data.datatimestr);
            				temphtml +='</span>';
            				temphtml +='</div>';
            				temphtml +='</div>';
            				temphtml +='</li>';
        				 }
        				 
        				 html +='<li class="list-group-item" onclick="goPageByPageId(55000000)" style="border: 0px;cursor: pointer;">';
        				 html +='<div class="product-info" style="margin-left: 0px;">'
        			     html +='<a class="product-title">';
        				 html +='<span>';
        				 html += getDefaultData(data.evttypename);
        				 html +='</span>';
        				if(data.isAlarm){
        					 html +='<span class="label label-warning pull-right boxStyle">'
        					 html += '告警';
        				}else{
        					 html +='<span class="label label-danger pull-right boxStyle">'
        					 html += '故障';
        				}
        				 html +='</span>';
        				 html +='</a>';
        				 html +='<div class="product-description"><span class="fa fa-clock-o">';
        				 html += getDefaultData(data.datatimestr);
        				 html +='</span>';
        				 html +='</div>';
        				 html +='</div>';
        				 html +='</li>';
        			 });
        			 html+='</ul>';
        			 html+='</marquee>';
        		 }else{
        			 html+='<div style="height: 100%" >';
        			 html+='<p class="text-center">暂无告警数据</p>';
        			 html+='</div>';
        			 
        			 temphtml+='<div style="height: 100%" >';
        			 temphtml+='<p class="text-center">暂无告警数据</p>';
        			 temphtml+='</div>';
        			
        		 }
        		 $("#newAlarm").html(temphtml);
        		 $("#alarmBox").html(html);
        	}else{
        		layerBox.msgWarning(data.message)
        	}
        }
	})
}


