var iChart;//电流
var tChart;//温度
var legendI1 = ["电流",'剩余电流'];
var legendT1 = ["零线温度","火线温度","箱体温度"];
var legendI3 = ['A相电流','B相电流','C相电流','剩余电流'];
var legendT3 = ['A相温度','B相温度','C相温度',"箱体温度"];
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
	        data:['A相电流','B相电流','C相电流','剩余电流']
	    },
	    grid: {
	        left: '1%',
	        right: '0%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	        	magicType : {show: true, type: ['line', 'bar']},
	            saveAsImage: {}
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
	    yAxis: [{
	        type: 'value',
	       // name:"电流",
	        position: 'left',
	        axisLabel: {
                formatter: function (value, idx) {
                    return 	value+"A";
                }
            } 
	    },
	    {
	        type: 'value',
	        name:"剩余电流",
	        position: 'right',
	        //max:1500,
	        axisLabel: {
                formatter: function (value, idx) {
                    return 	value+"mA";
                }
            } 
	    }
	    ],
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
				},
				{
				  	name: '剩余电流',
				  	type:'line',
				  	data:[0]
				},
	             ]
	};

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
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	        	magicType : {show: true, type: ['line', 'bar']},
	            saveAsImage: {}
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
				}
	             ]
	};


function initChart(){
	iChart =  echarts.init(document.getElementById('iChart'));
	tChart = echarts.init(document.getElementById('tChart'));
	iChart.showLoading({text: '正在努力的读取数据中...'  });
	tChart.showLoading({text: '正在努力的读取数据中...'  });
	iChart.setOption(optionI);
	tChart.setOption(optionT);
	iChart.hideLoading();
	tChart.hideLoading();
}

function warpChartHoldLine(type,deviceType,data,option){
	if(null == data || data <= 0){
		return false;
	}
	var tempmarkLine={
			silent: false,
			label:{
				normal:{
	           		show:false
	           	}
			},
			itemStyle:{
       		normal:{
           		color:'red'
           	}
       	},
       data: [ { yAxis: data}
       ]		
	}


	var tempitemStyle = {
        normal : {  
       	  color:function(param){
       		 //seriesIndex, dataIndex, data, value
       		  if(param.dataIndex == 0){
       			  return '#3c8dbc';
       			}else if(param.data > 20){
       				return 'red';
       			}else{
       				 return '#3c8dbc';
       			}
       		  },
       		lineStyle:{  
 			   normal:{
 				   color:'#3c8dbc'  
 			   }
          }
       	//'#3c8dbc'
         	}  
		   }
	if('i' == type){
		option.series[0].markLine=tempmarkLine;
	}else if('il' == type){//剩余电流
		tempmarkLine.itemStyle={
				normal:{
	           		color:'#ff851b'
	           	}
		}
		if(deviceType == THREE_PHASE){
			option.series[3].markLine=tempmarkLine;
		}else if(deviceType == SINGLE_PHASE){
			option.series[1].markLine=tempmarkLine;
		}
	}else if('ta' == type){//A相温度
		tempmarkLine.itemStyle={
				normal:{
	           		color:'#3c8dbc'
	           	}
		}
		option.series[0].markLine=tempmarkLine;
	}else if('tb' == type){//A相温度
		tempmarkLine.itemStyle={
				normal:{
	           		color:'#00a65a'
	           	}
		}
		option.series[1].markLine=tempmarkLine;
	}else if('tc' == type){//A相温度
		tempmarkLine.itemStyle={
				normal:{
	           		color:'#605ca8'
	           	}
		}
		option.series[2].markLine=tempmarkLine;
	}else if('tn' == type){//零线温度
		tempmarkLine.itemStyle={
				normal:{
	           		color:'#3c8dbc'
	           	}
		}
		option.series[0].markLine=tempmarkLine;
	}else if('tl' == type){//火线温度
		tempmarkLine.itemStyle={
				normal:{
	           		color:'#00a65a'
	           	}
		}
		option.series[1].markLine=tempmarkLine;
	}
	
	else if('ti' == type){
		tempmarkLine.itemStyle={
				normal:{
	           		color:'#ff851b'
	           	}
		}
		if(deviceType == THREE_PHASE){
			option.series[3].markLine=tempmarkLine;
		}else if(deviceType == SINGLE_PHASE){
			option.series[2].markLine=tempmarkLine;
		}
	}
}


function warpChartData(realData){
	var detL1HisList = realData.detL1HisList;
	var type = realData.type;
	var tempTime = detL1HisList.length==0?[currentDateTime]:detL1HisList.map(function(data){
    	return data.longDatatimeStr;
    })
      optionI.xAxis.data = tempTime;
	  optionT.xAxis.data = tempTime;
	  optionI.title.subtext="电流走势"+"(电流阀值："+getDefaultData(realData.overi)+"A,剩余电流阀值："+getDefaultData(realData.il)+"mA)"
	 
    if(THREE_PHASE == type){//三相
    	optionT.title.subtext="温度走势"+"(A相温度阀值："+getDefaultData(realData.ta)+"℃,B相温度阀值："+getDefaultData(realData.tb)+"℃,C相温度阀值："+getDefaultData(realData.tc)+"℃,箱体温度阀值："+getDefaultData(realData.ti)+"℃)"
    	optionI.legend.data = legendI3;
    	optionT.legend.data = legendT3;
    	optionI.series=[
   				     {
   				       name: 'A相电流',
       				   type:'line',
       				   showSymbol: true,
       				   hoverAnimation: true,
       				   symbol:'triangle',
//       				   symbolSize:function(data){
//       					 if(data > 20){
//       						 return 8;
//       					 }else{
//       						 return 1;
//       					 }
//       				   },
       				   symbolSize:function(data){
       					return warpSymbolSize(data,realData.overi);
       				   },
       				   showAllSymbol:true,
//       				   itemStyle : {
//                           normal : {  
//                         	  color:'green'
//                           	}  
//        				   },
       				   smooth:true,
   			           data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
   		                	return data.ia==null?0:data.ia;
   		                })
	        		  	 },
		        		 {
		        		   name: 'B相电流',
		        		   type:'line',
		        		   showSymbol: true,
		        	       hoverAnimation: true,
		        	       symbol:'triangle',
	       				   showAllSymbol:true,
	       				   symbolSize:function(data){
	          					return warpSymbolSize(data,realData.overi);
	          				   },
	       				   smooth:true,
//	       				   itemStyle : {  
//	                           normal : {  
//	                         	  color:'#00a65a'
//	                           	}  
//	        				   },
		        		   data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
     			        		return data.ib==null?0:data.ib;
     		                })
			        	  },
		        		  {
		        			name: 'C相电流',
	        				type:'line',
	        				showSymbol: true,
	        			    hoverAnimation: true,
	        			    symbol:'triangle',
	        				showAllSymbol:true,
	        				symbolSize:function(data){
	              					return warpSymbolSize(data,realData.overi);
	              				   },
	        				smooth:true,
//	        				itemStyle : {  
//		                           normal : {  
//		                         	  color:'#605ca8'
//		                           	}  
//		        				   },
	        				data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
 			        		return data.ic==null?0:data.ic;
 		                })
		        		  },
		        		  {
		        			name: '剩余电流',
	        				type:'line',
	        				showSymbol: true,
	        			    hoverAnimation: true,
	        			    symbol:'roundRect',
	        				showAllSymbol:true,
	        				symbolSize:function(data){
	              					return warpSymbolSize(data,realData.il);
	              				   },
	        				smooth:true,
/*	        				itemStyle : {  
		                           normal : {  
		                         	  color:'#ff851b'
		                           	}  
		        				   },*/
	        			    yAxisIndex: 1,
	        				data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
	        				return data.il==null?0:data.il;
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
	   		        			   showAllSymbol:true,
	   		        			   symbolSize:function(data){
		              					return warpSymbolSize(data,realData.ta);
		              				   },
		              			   smooth:true,
//	   		        			   itemStyle : {
//	   	                           normal : {  
//	   	                         	  color:'#3c8dbc'
//	   	                           	}  
//	   	        				   },
   		    			           data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
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
	   		        			   symbolSize:function(data){
		              					return warpSymbolSize(data,realData.tb);
		              				   },
//	   		       				   itemStyle : {  
//	   		                           normal : {  
//	   		                         	  color:'#00a65a'
//	   		                           	}  
//	   		        				   },
   				        		   data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
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
	   		        			    symbolSize:function(data){
		              					return warpSymbolSize(data,realData.tc);
		              				   },
// 	   		        			   	itemStyle : {  
// 	   		        			   		normal : {  
// 	   		        			   			color:'#605ca8'
// 		                           	}  
// 		        				   },
   			        				data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
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
     		   		        			symbolSize:function(data){
  		              					return warpSymbolSize(data,realData.ti);
  		              				   },
     		   		        			//yAxisIndex: 1,
//     		   		        			itemStyle : {  
//     		   		        				normal : {  
//     		   		        				color:'#ff851b'
//     		                           	}  
//     		        				   },
     			        				data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
     	      			        		return data.ti==null?0:data.ti;
     			        				})
     				        		  }
   				        		];
//   				warpChartHoldLine("i",THREE_PHASE,realData.overi,optionI);
//   				warpChartHoldLine("il",THREE_PHASE,realData.il,optionI);
//   				warpChartHoldLine("ta",THREE_PHASE,realData.ta,optionT);
//   				warpChartHoldLine("tb",THREE_PHASE,realData.tb,optionT);
//   				warpChartHoldLine("tc",THREE_PHASE,realData.tc,optionT);
//   				warpChartHoldLine("ti",THREE_PHASE,realData.ti,optionT);
    }else if(SINGLE_PHASE == type){//单相
    	optionI.legend.data = legendI1;
    	optionT.legend.data = legendT1;
    	optionT.title.subtext="温度走势"+"(零线温度阀值："+getDefaultData(realData.tn)+"℃,火线温度阀值："+getDefaultData(realData.tl)+"℃,箱体温度阀值："+getDefaultData(realData.ti)+"℃)"
    	optionI.series=[
   				     {
   				       name: '电流',
       				   type:'line',
       				   showSymbol: true,
       				   hoverAnimation: true,
       				   symbol:'triangle',
       				   smooth:true,
       				   showAllSymbol:true,
       				   symbolSize:function(data){
    					return warpSymbolSize(data,realData.overi);
    				   },
//	        			   itemStyle : {
//   	                           normal : {  
//   	                         	  color:'#3c8dbc'
//   	                           	}  
//   	        				   },
   			           data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
   		                	return data.i==null?0:data.i;
   		                })
	        		  },
		        	{
		        			name: '剩余电流',
	        				type:'line',
	        				showSymbol: true,
 		        			hoverAnimation: true,
 		        			symbol:'roundRect',
 	        				symbolSize:8,
 	        				smooth:true,
 	        				showAllSymbol:true,
 	        				symbolSize:function(data){
 	     					return warpSymbolSize(data,realData.il);
 	     				   },
// 	        				itemStyle : {  
//	   		        				normal : {  
//	   		        				color:'#ff851b'      //'#ff851b'
//	                           	}  
//	        				   },
 		        			yAxisIndex: 1,
	        				data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
	        				return data.il==null?0:data.il;
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
	   	       				   	   symbolSize:function(data){
	   	       				   		   return warpSymbolSize(data,realData.tn);
	   	       				   	   },
//	   		        			   itemStyle : {
//		   	                           normal : {  
//		   	                         	  color:'#3c8dbc'
//		   	                           	}  
//		   	        				   },
   		    			           data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
   		    		                	return data.tn==null?0:data.tn;
   		    		                })
   			        		  	 },
   				        		 {
   				        		   name: '火线温度',
   				        		   type:'line',
   				        		   showSymbol: false,
	   		        			   hoverAnimation: true,
	   		        			   symbol:'triangle',
	   		        			   smooth:true,
	   		        			   showAllSymbol:true,
	   	       				   	   symbolSize:function(data){
	   	       				   		   return warpSymbolSize(data,realData.tl);
	   	       				   	   },
//	   		       				   itemStyle : {  
//	   		                           normal : {  
//	   		                         	  color:'#00a65a'
//	   		                           	}  
//	   		        				   },
   				        		   data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
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
	   	       				   	    symbolSize:function(data){
	   	       				   		   return warpSymbolSize(data,realData.ti);
	   	       				   	    },
// 	   		        			   	itemStyle : {  
//		   		        				normal : {  
//		   		        				color:'#ff851b'      //'#ff851b'
//		                           	}  
//		        				   },
   			        				data: detL1HisList.length==0?[0]:detL1HisList.map(function(data){
   	      			        		return data.ti==null?0:data.ti;
   			        				})
   				        		  }
   				        		];	
   				
//   				warpChartHoldLine("i",SINGLE_PHASE,realData.overi,optionI);
//   				warpChartHoldLine("il",SINGLE_PHASE,realData.il,optionI);
//   				warpChartHoldLine("tn",SINGLE_PHASE,realData.tn,optionT);
//   				warpChartHoldLine("tl",SINGLE_PHASE,realData.tl,optionT);
//   				warpChartHoldLine("ti",SINGLE_PHASE,realData.ti,optionT);
    }
}