var param={};
var html = "";
var queryUrl="stat/monitorReport_query.action"
var legendI1 = ["电流"];
var legendT1 = ["零线温度","火线温度","箱体温度"];
var legendI3 = ['A相电流','B相电流','C相电流','剩余电流'];
var legendT3 = ['A相温度','B相温度','C相温度',"箱体温度"];

var legendIL = ['剩余电流'];
$(function(){
	//$('#pdfDiv').height(getViewSize().height-160);
	initDateCallBack($("#startTimeDiv"),startTime);
	$("#exportPdfBtn").on("click",function(){
		var temp = $.trim($("#switchEntBtn").text());
		if(isEmpty(temp) || temp=='请选择企业'){
			temp="监测报表";
			createJsPdf($('#pdfDiv'),temp,150);
		}else{
			temp+="_监测报表";
			createJsPdf($('#pdfDiv'),temp,1620);
		}
	});
	
	if(entCount > 1){
		$("#switchEntBtn").on("click",function(){
			popupSwitchEnt(query);
		});
	}
	$("#queryBtn").on("click",function(){
		query()	
	});
	query()
})
function query(){
	param={};
	var startTime = $("#startTime").val();
	var eid = $("#switchEntBtn").attr("cur_eid");
	if(isEmpty(startTime)){
		layerBox.msgWarning("请选择起始时间");
		return false;
	}else if(isEmpty(eid) || eid <= 0){
		layerBox.msgWarning("请选择企业");
		return false;
	}
	param.startTime = startTime;
	param.eid = eid;
	$.ajax({
		type:"POST",
		url:queryUrl,
		data:param,
		dataType:'json',
        cache: false,
        beforeSend:function(){
        	showLoading();
        },
        complete:function(){
        	hideLoading();
        },
        success: function(data){
        	if(preprocess(data))return false;
        	if(data.success){
        		var statAlarmData = data.statAlarmData;
        		var statDetList = data.statAlarmData.statDetList;
        		var detEventList = data.statAlarmData.detEventList;
        		$("#statWeekTime").text(statAlarmData.startTime+"~"+statAlarmData.endTime)
        		$("#statEntName").text(statAlarmData.entName);
        		$("#statCountAlarmDisc").html("报警总计 <strong>"+statAlarmData.allAlarm+"</strong> 次。"+"剩余电流报警 <strong>"+statAlarmData.allIlAlarm+"</strong> 次，"+"线缆电流报警 <strong>"+statAlarmData.allIAlarm+"</strong> 次，"+"线缆温度报警 <strong>"+statAlarmData.allTAlarm+"</strong> 次，"+"故障 <strong>"+statAlarmData.allFault+"</strong> 次。");
        		createStatDetTable(statDetList);//统计设备是否有报警
        		createDetEventTable(detEventList);//列表显示报警信息
        		createChartBody(statDetList);//创建设备图表
        		initStatCHart(statDetList);//初始化图表信息
        	}else{
        		layerBox.msgWarning(data.message)
        	}
        }
	})
}

function initStatCHart(statDetList){
	
		$(statDetList).each(function(index,data){
			var detDataIList = data.detDataIList;//电流
			var detDataTList = data.detDataTList;//温度
			var detDataILList = data.detDataILList;//剩余电流
			var tempTimeI = detDataIList.length==0?[currentDateTime]:detDataIList.map(function(data){
		    	return data.longDatatimeStr;
		    })
		    var tempTimeT = detDataTList.length==0?[currentDateTime]:detDataTList.map(function(data){
		    	return data.longDatatimeStr;
		    })
		    var tempTimeIL = detDataILList.length==0?[currentDateTime]:detDataILList.map(function(data){
		    	return data.longDatatimeStr;
		    })
		     // optionI.xAxis.data = tempTimeI;
			  optionT.xAxis.data = tempTimeT;
			  optionI.legend.data = legendIL;
		      optionI.xAxis.data = tempTimeIL;
		      optionI.title.subtext="剩余电流阀值："+getDefaultData(data.holdIl)+"mA";
		      
		      optionI.yAxis.axisLabel.formatter=function (value, idx) {
                  return 	value+"mA";
              }
		    		  
		      
			  optionI.series=[
					          	{
					        			name: '剩余电流',
				        				type:'line',
				        				showSymbol: true,
				        			    hoverAnimation: true,
				        			    symbol:'triangle',
				        				smooth:true,
				        				showAllSymbol:true,
				        				symbolSize:function(value){
				              					return warpSymbolSize(value,data.holdIl);
				              				   },
				        				data: detDataILList.length==0?[0]:detDataILList.map(function(data){
			   		                	return data.il==null?0:data.il;
			        		  	 		})
					        		  }
					        		];
			 echarts.init(document.getElementById('chart'+index+'IL')).setOption(optionI);
			
			 optionI.xAxis.data = tempTimeI;
			 if(THREE_PHASE == data.type){//三相
				 optionI.legend.data = legendI3;
			     optionT.legend.data = legendT3;
			     optionI.title.subtext="电流阀值："+getDefaultData(data.holdOveri)+"A"
			     optionT.title.subtext="A相温度阀值："+getDefaultData(data.holdTa)+"℃,B相温度阀值："+getDefaultData(data.holdTb)+"℃,C相温度阀值："+getDefaultData(data.holdTc)+"℃,箱体温度阀值："+getDefaultData(data.holdTi)+"℃"
			     optionI.yAxis.axisLabel.formatter=function (value, idx) {
	                  return 	value+"A";
	              }
			     optionI.series=[
			   				     {
			   				       name: 'A相电流',
			       				   type:'line',
			       				   showSymbol: true,
			       				   hoverAnimation: true,
			       				   symbol:'triangle',
			       				   smooth:true,
			       				   showAllSymbol:true,
			       				   symbolSize:function(value){
		              					return warpSymbolSize(value,data.holdOveri);
		              				   },
			   			           data: detDataIList.length==0?[0]:detDataIList.map(function(data){
			   		                	return data.ia==null?0:data.ia;
			        		  	 		})
				        		  	 },
					        		 {
					        		   name: 'B相电流',
					        		   type:'line',
					        		   showSymbol: true,
					        	       hoverAnimation: true,
					        	       symbol:'triangle',
				       				   smooth:true,
				       				   showAllSymbol:true,
				       				   symbolSize:function(value){
			              					return warpSymbolSize(value,data.holdOveri);
			              				   },
					        		   data: detDataIList.length==0?[0]:detDataIList.map(function(data){
				   		                	return data.ib==null?0:data.ib;
			        		  	 		})
						        	  },
					        		  {
					        			name: 'C相电流',
				        				type:'line',
				        				showSymbol: true,
				        			    hoverAnimation: true,
				        			    symbol:'triangle',
				        				smooth:true,
				        				showAllSymbol:true,
					       				symbolSize:function(value){
				              					return warpSymbolSize(value,data.holdOveri);
				              				   },
				        				data: detDataIList.length==0?[0]:detDataIList.map(function(data){
			   		                	return data.ic==null?0:data.ic;
			        		  	 		})
					        		  }
					        		];
			     
			     
			 	optionT.series=[
  	        				     {
  	        				       name: 'A相温度',
  		        				   type:'line',
	   		        			   showSymbol: true,
	   		        			   hoverAnimation: true,
	   		        			   symbol:'triangle',
	   		        			   smooth:true,
	   		        			   showAllSymbol:true,
			       				   symbolSize:function(value){
		              					return warpSymbolSize(value,data.holdTa);
		              				   },
//  		        				   itemStyle : {  
//  		                           normal : {  
//  		                         	  color:'#f39c12',
//  		                           	}  
//  		        				   },
  		    			           data: detDataTList.length==0?[0]:detDataTList.map(function(data){
  		    		                	return data.ta==null?0:data.ta;
  		    		                	})
  			        		  	 },
  				        		 {
  				        		   name: 'B相温度',
  				        		   type:'line',
  				        		   showSymbol: true,
	   		        			   hoverAnimation: true,
	   		        			   symbol:'triangle',
	   		        			   smooth:true,
	   		        			   showAllSymbol:true,
			       				   symbolSize:function(value){
		              					return warpSymbolSize(value,data.holdTb);
		              				   },
  				        		   data:detDataTList.length==0?[0]:detDataTList.map(function(data){
 		    		                	return data.tb==null?0:data.tb;
		    		                	})
  					        	  },
  				        		  {
  				        			name: 'C相温度',
  			        				type:'line',
  			        				showSymbol: true,
  			        				hoverAnimation: true,
  			        				symbol:'triangle',
	   		        			   	smooth:true,
	   		        			    showAllSymbol:true,
			       				    symbolSize:function(value){
		              					return warpSymbolSize(value,data.holdTc);
		              				   },
  			        				data:detDataTList.length==0?[0]:detDataTList.map(function(data){
  		    		                	return data.tc==null?0:data.tc;
		    		                	})
  				        		  },
  				        		  {
    				        			name: '箱体温度',
    			        				type:'line',
    			        				showSymbol: true,
    		   		        			hoverAnimation: true,
    		   		        			symbol:'roundRect',
    		   		        			smooth:true,
    		   		        			showAllSymbol:true,
    		   		        			symbolSize:function(value){
		              					return warpSymbolSize(value,data.holdTi);
    		   		        			},
    			        				data:detDataTList.length==0?[0]:detDataTList.map(function(data){
  		    		                	return data.ti==null?0:data.ti;
  		    		                	})
    				        		  }
  				        		];	
				 echarts.init(document.getElementById('chart'+index+'I')).setOption(optionI);
				 echarts.init(document.getElementById('chart'+index+'T')).setOption(optionT);
				 
			 }else if(SINGLE_PHASE == data.type){
				 optionI.legend.data = legendI1;
			     optionT.legend.data = legendT1;
			     optionI.title.subtext="电流阀值："+getDefaultData(data.holdOveri)+"A"
			     optionT.title.subtext="零线温度阀值："+getDefaultData(data.holdTn)+"℃,火线温度阀值："+getDefaultData(data.holdTl)+"℃,箱体温度阀值："+getDefaultData(data.holdTi)+"℃)"
			     optionI.yAxis.axisLabel.formatter=function (value, idx) {
	                  return 	value+"A";
	              }
			     optionI.series=[
			   				     {
			   				       name: '电流',
			       				   type:'line',
			       				   showSymbol: true,
			       				   hoverAnimation: true,
			       				   symbol:'triangle',
			       				   smooth:true,
			       				   showAllSymbol:true,
			       				   symbolSize:function(value){
	              					return warpSymbolSize(value,data.holdOveri);
		   		        			},
			   			           data:  detDataIList.length==0?[0]:detDataIList.map(function(data){
			   		                	return data.i==null?0:data.i;
		        		  	 		})
				        		  	 }
					        		];
			     optionT.series=[
  	        				     {
  	        				       name: '零线温度',
  		        				   type:'line',
	   		        			   showSymbol: true,
	   		        			   hoverAnimation: true,
	   		        			   symbol:'triangle',
	   		        			   smooth:true,
	   		        			   showAllSymbol:true,
			       				   symbolSize:function(value){
	              					return warpSymbolSize(value,data.holdTn);
		   		        			},
//  		        				   itemStyle : {  
//  		                           normal : {  
//  		                         	  color:'#f39c12',
//  		                           	}  
//  		        				   },
  		    			           data: detDataTList.length==0?[0]:detDataTList.map(function(data){
 		    		                	return data.tn==null?0:data.tn;
	    		                	})
  			        		  	 },
  				        		 {
  				        		   name: '火线温度',
  				        		   type:'line',
  				        		   showSymbol: true,
	   		        			   hoverAnimation: true,
	   		        			   symbol:'triangle',
	   		        			   smooth:true,
	   		        			   showAllSymbol:true,
			       				   symbolSize:function(value){
	              					return warpSymbolSize(value,data.holdTl);
		   		        			},
  				        		   data:detDataTList.length==0?[0]:detDataTList.map(function(data){
		    		                	return data.tl==null?0:data.tl;
	    		                	})
  					        	  },
  				        		  {
    				        			name: '箱体温度',
    			        				type:'line',
    			        				showSymbol: true,
    		   		        			hoverAnimation: true,
    		   		        			symbol:'roundRect',
    		   		        			smooth:true,
    		   		        			showAllSymbol:true,
    				       				symbolSize:function(value){
    		              					return warpSymbolSize(value,data.holdTi);
    			   		        		},
    			        				data:detDataTList.length==0?[0]:detDataTList.map(function(data){
     		    		                	return data.ti==null?0:data.ti;
    	    		                	})
    				        		  }
  				        		];	
			     
				 echarts.init(document.getElementById('chart'+index+'I')).setOption(optionI);
				 echarts.init(document.getElementById('chart'+index+'T')).setOption(optionT);
				 
			 }
			
		})
//	iChart =  echarts.init(document.getElementById('chart'),'customed');
//	iChart.showLoading({text: '正在努力的读取数据中...'  });
//	tChart.showLoading({text: '正在努力的读取数据中...'  });
//	iChart.setOption(optionI);
//	tChart.setOption(optionT);
//	iChart.hideLoading();
//	tChart.hideLoading();
}

function createStatDetTable(statDetList){
	html="";
	if(statDetList && statDetList.length > 0){
		$(statDetList).each(function(index,data){
			html += "<tr>";
			html += "<td>";
			html += data.detName;
			html += "</td>";
			html += "<td>";
			if(data.ilAlarm > 0){
				html += "<span class='fa fa-square' style='color:#dd4b39'></span>";
			}else{
				html += "<span class='fa fa-square' style='color:#00a65a'></span>";
			}
			html += "</td>";
			
			html += "<td>";
			if(data.iAlarm > 0){
    			html += "<span class='fa fa-square' style='color:#dd4b39'></span>";
    		}else{
    			html += "<span class='fa fa-square' style='color:#00a65a'></span>";
    		}	
        	html += "</td>";
        	
        	html += "<td>";
        	if(data.tAlarm > 0){
    			html += "<span class='fa fa-square' style='color:#dd4b39'></span>";
    		}else{
    			html += "<span class='fa fa-square' style='color:#00a65a'></span>";
    		}
            html += "</td>";
            
            html += "<td>";
            if(data.fault > 0){
            	html += "<span class='fa fa-square' style='color:#dd4b39'></span>";
            }else{
            	html += "<span class='fa fa-square' style='color:#00a65a'></span>";
            }
            html += "</td>";
			html += "</tr>";
		})
	}else{
		html += "<tr>";
		html+="  <td class='text-center' colspan='5' >暂无数据</td>  ";
		html += "</tr>";
	}
	$("#statdataBody").html(html);
}
function createDetEventTable(detEventList){
	html="";
	if(detEventList && detEventList.length > 0){
		$(detEventList).each(function(index,data){
			html += "<tr>";
			html += "<td>";
			html += data.detname;
			html += "</td>";
			if(data.isAlarm){
				html += "<td class='text-yellow'>";
				html += data.evttypename;
				html += "</td>";
			}else{
				html += "<td class='text-red'>";
				html += data.evttypename;
				html += "</td>";
			}
			html += "<td>";
			html += getDefaultData(data.dealstatusname);
			html += "</td>";
			html += "<td>";
			html += getDefaultData(data.evtdesc);
			html += "</td>";
			html += "<td>";
			html += getDefaultData(data.dealremark);
			html += "</td>";
			html += "<td>";
			html += getDefaultData(data.datatimestr);
			html += "</td>";
			html += "</tr>";
		})
	}else{
		html += "<tr>";
		html+="  <td class='text-center' colspan='6' >暂无数据</td>  ";
		html += "</tr>";
	}
	$("#detEventBody").html(html);	
}

function createChartBody(statDetList){
	html="";
	if(statDetList && statDetList.length > 0){
		$(statDetList).each(function(index,data){
			html+='<div class="callout callout-light-green">';
			html+='<p>';
			html+=data.detName;
			if(SINGLE_PHASE == data.type){
				html+="（单相）";
			}else if(THREE_PHASE == data.type){
				html+="（三相）";
			}
			html+='</p>';
			html+=' </div>';
			
			//html+='<div class="col-sm-3 col-xs-6">';
			html+='<div class="callout"  style="height:300">';
			html+='<div class="col-sm-4 col-xs-12" style="height:100%;" id="chart'+index+'I">';
			html+='</div>';
			html+='<div class="col-sm-4 col-xs-12" style="height:100%;" id="chart'+index+'T">';
			html+='</div>';
			html+='<div class="col-sm-4 col-xs-12" style="height:100%;" id="chart'+index+'IL">';
			html+='</div>';
			html+='</div>';
			//html+=' </div>';
		})
	}else{
		html+="<p class='text-center'>暂无数据</p>";
	}
	$("#statBodyDiv").html(html);
}


var optionT = {
	    title: {
	        //text: '温度走势(℃)',
	        textStyle: {
	            fontSize: 8,
	            fontWeight: 'bolder',
	            color: '#333'          // 主标题文字颜色
	        }
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	    	//x: 'right',  
	    	//top:25,
	        data:['A相温度','B相温度','C相温度']
	    },
	    grid: {
	        left: '1%',
	        right: '7%',
	        bottom: '0%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	        	magicType : {show: true, type: ['line', 'bar']},
	           // saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: [0],
	        axisLabel: {
                formatter: function (value, idx) {
                    return 	getChartFormatDate(value,true);
                }
            }
	    },
	    yAxis: {
	        type: 'value',
	       // name:"温度",
	        axisLabel: {
                formatter: function (value, idx) {
                    return 	value+"℃";
                }
            } 
	    },
	    series: [
				{
				  	name: 'A相温度',
				  	type:'line',
				  	data:[0]
				},
				{
				  	name: 'B相温度',
				  	type:'line',
				  	data:[0]
				},
				{
				  	name: 'C相温度',
				  	type:'line',
				  	data:[0]
				},
	             ]
	};


var optionI = {
	    title: {
	        //text: '电流走势(A)',
	        textStyle: {
	            fontSize: 8,
	            fontWeight: 'bolder',
	            color: '#333'          // 主标题文字颜色
	        }
	    },
	    tooltip: {
	        trigger: 'axis',
	    },
	    legend: {
	    	//x: 'right',  
	    	//top:25,
	        data:['A相电流','B相电流','C相电流']
	    },
	    grid: {
	        left: '1%',
	        right: '7%',
	        bottom: '0%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	        	magicType : {show: true, type: ['line', 'bar']},
	           // saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: [0],
	        axisLabel: {
                formatter: function (value, idx) {
                    return 	getChartFormatDate(value,true);
                }
            }
	    },
	    yAxis: {
	        type: 'value',
	       // name:"电流",
	        position: 'left',
	        axisLabel: {
                formatter: function (value, idx) {
                    return 	value+"A";
                }
            } 
	    },
	    series: [
				{
				  	name: 'A相电流',
				  	type:'line',
				  	data:[0]
				},
				{
				  	name: 'B相电流',
				  	type:'line',
				  	data:[0]
				},
				{
				  	name: 'C相电流',
				  	type:'line',
				  	data:[0]
				}
	             ]
	};
