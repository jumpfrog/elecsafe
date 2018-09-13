function initHisToryChart(){
	initChartWidth($("#iChart"));
	initChartWidth($("#tChart"));
	initDateTime($('#startHChartDateDiv'),startHChartDateStr);
	initDateTime($('#endHChartDateDiv'),endHChartDateStr);
	$("#returnBtn").on("click",function(){
		isShowList(true);
	});
	window.onresize = function () {
    	initChartWidth($("#iChart"));
    	initChartWidth($("#tChart"));
    	if(iChart){
    		iChart.resize();	
    	}
    	if(tChart){
    		tChart.resize();
    	}
    }
	
	$("#queryHChartBtn").on("click",function(){
		
		showHChart($("#detid").val(),false)
	});
}

function showHChart(detid,isinit){
	param={}
	param.detid = detid;
	param.startHChartDate = $("#startHChartDate").val();
	param.endHChartDate = $("#endHChartDate").val();
	if(isEmpty(param.startHChartDate)){
		layerBox.msgWarning("开始时间不能为空");
		return false;
	}else if(isEmpty(param.endHChartDate)){
		layerBox.msgWarning("结束时间不能为空");
		return false;
	}else if(param.endHChartDate < param.startHChartDate){
		layerBox.msgWarning("结束时间不能小于开始时间");
		return false;
	}
	$.ajax({
		type:"POST",
		url:queryDetHChartDataUrl,
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
        			warpChartData(realData);
    				initChart();
        			isShowList(false,realData.name,isinit)
        		}
        	}else{
        		layerBox.msgWarning(data.message)
        	}
        	hideLoading()
        }
        })
	
}