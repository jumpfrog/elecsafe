var param = {};
var loadCityURL =  "comm/searchComm_queryCityByProvince.action";
var queryListURL = "main/stationdata_queryStationList.action";
//var queryMapURL = "main/stationdata_queryStationByRange.action";
var queryEntStationURL = "main/stationdata_queryEntStation.action";
var queryDetailURL = "main/stationdata_queryStationDetail.action";
var stationMainURL = "main/stationmain.action";
var showType = 1;//显示类型
var img = "data/stationImg/stationDefault.jpg";
$(document).ready(function(){
	if(type == 2){
		$("#gridTab").addClass("active");
		$("#chartTab").removeClass("active");
		showResult("showGrid");
		queryStationGrid();
	}
	//tab绑定事件
	$(".btn-group button").on("click",function(){
		if($(this).hasClass("active")){
			return;
		}
		//处理按钮选中样式
		$(".btn-group").find("button").each(function(index,item){
			if($(item) != $(this)){
				$(item).removeClass("active");
			}
		});
		$(this).addClass("active");
		
		showType = $(this).attr("showtype");
		if(showType == 3){
			$("#listDiv").css("height",getWindowHeight() - 130);
		}else{
			$("#listDiv").css("height",getWindowHeight() - 165);
		}
		
		if(showType == 1){
			showResult("showChart");
		}else if(showType == 2){
			showResult("showGrid");
		}else if(showType == 3){
			showResult("showMap");
		}else{
			return;
		}
		queryStation();
	});
	
	//搜索按钮事件
	$("#queryBtn").on("click",function(){
		setCurrentPage(1);
		$(this).trigger("blur");
		queryStation();
	});
	
	//省下拉框改变事件
	$("#province").on("change",function(){
		$("#city").html('<option value="0">选择市</option>');
		loadCity();
	});
	
//	setPageTableDivH($("#chartDiv"));
	$("#listDiv").css("height",getWindowHeight() - 165);
	$("#mapContainer").height(getWindowHeight() - 130);
	
	queryStation();
});

//根据选择的省份载入市
function loadCity(){
	param = {};
	var provinceid = $("#province option:selected").val();
	if(isEmpty(provinceid) || provinceid == 0){
//		showInfo("请先选择省");
		return;
	}
	param.provinceid = provinceid;
	
	var html = '';
	
	$.ajax({
		type:'POST',
		url:loadCityURL,
		data:param,
		dataType:'json',
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var cityList = data.cityList;
				if(cityList != null && cityList.length > 0){
					$(cityList).each(function(index,item){
						if(index == 0){
							html += '<option value="'+item.cityid+'" selected="selected">'+item.name+'</option>';
						}else{
							html += '<option value="'+item.cityid+'">'+item.name+'</option>';
						}
					});
					$("#city").append(html);
				}
				queryStation();
			}else{
				showInfo(data.message);
			}
		}
	});
}

function queryStation(){
	if(showType == 1){
		queryStationChart();
	}else if(showType == 2){
		queryStationGrid();
	}else if(showType == 3){
		queryStationMap();
	}else{
		return;
	}
}

/**
 * 查看电站数据，图表显示
 */
function queryStationChart(){
	param = {};
	var provinceid = $("#province option:selected").val();
	var cityid = $("#city option:selected").val();
	var keyword = $.trim($("#keyword").val());
	
	param.provinceid = provinceid;
	param.cityid = cityid;
	param.keyword = keyword;
	param.pageindex = $.trim($("#currentPage").val());
	param.pagelimit = PAGE_LIMIT;
	
	$("chartDiv").html('');
	
	var html = '';
	$.ajax({
		type:'POST',
		url:queryListURL,
		data:param,
		dataType:'json',
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var list = data.page.root;
				var stationname;
				$(list).each(function(index,item){
					html += '<div class="col-sm-4 col-md-4">';
			        html += '<div class="box box-warning">';
			        html += '<div class="box-header with-border" style="padding-bottom:0;">';
			        html += '<h4 class="box-title" style="font-size:16px;">';
			        stationname = replaceSign(item.stationname);
			        if(stationname.length > 15){
			        	html += '<a class="text-aqua" data-toggle="tooltip" data-placement="bottom" title="'+stationname+'" href="javascript:;" onclick="openStationWindow(this)" stationkey="'+item.stationkey+'">'+substring(stationname,15)+'</a>';
			        }else{
			        	html += '<a class="text-aqua" href="javascript:;" onclick="openStationWindow(this)" stationkey="'+item.stationkey+'">'+stationname+'</a>'
			        }
			        html += '</h4>';
			        html += '<h5 class="text-muted">'+item.ownername+'</h5>';
			        html += '</div>';
			        html += '<div class="box-body" style="padding-top:0;">';
			        html += '<div class="col-xs-4 no-padding">';
			        if(isEmpty(item.picture)){
			        	html += '<img style="text-align: left;width:150px;height:150px" src="'+IMG_SRC+''+img+'"/>';
			        }else{
			        	 html += '<img style="text-align: left;width:150px;height:150px" src="'+IMG_SRC+''+item.picture+'"/>';
			        }
			        html += '</div>';
			        html += '<div class="col-xs-8">';
			        html += '<div>';
			        html += '<span>电站规模</span>';
			        html += '<span class="pull-right text-right">'+item.scale+'&nbsp kWp</span>';
			        html += '</div>';
			        html += '<div>';
			        html += '<span>当前功率</span>';
			        html += '<span class="pull-right">'+item.pstr+'&nbsp kW</span>';
			        html += '</div>';
			        html += '<div>';
			        html += '<span>当前辐照</span>';
			        html += '<span class="pull-right">'+item.sristr+'&nbsp W/㎡</span>';
			        html += '</div>';
			        html += '<div>';
			        html += '<span>环境温度</span>';
			        html += '<span class="pull-right">'+item.entstr+'&nbsp ℃</span>';
			        html += '</div>';
			        html += '<div>';
			        html += '<span>今日发电</span>';
			        html += '<span class="pull-right">'+item.dayenergystr+'&nbsp kWh</span>';
			        html += '</div>';
			        html += '<div>';
			        html += '<span>累计发电</span>';
			        if(isEmpty(item.totalenergy)){
			        	 html += '<span class="pull-right">'+''+'&nbsp kWh</span>';
			        }else{
			        	 html += '<span class="pull-right">'+item.totalenergy+'&nbsp kWh</span>';
			        }
			        html += '</div>';
			        html += '<div>';
			        html += '<span>更新时间</span>';
			        html += '<span class="pull-right">'+item.datatimestr+'</span>';
			        html += '</div>';
			        html += '</div>';
			        html += '</div>';
			        html += '</div>';
			        html += '</div>';
				});
				$("#chartDiv").html(html);
				
				var currentPage = data.page.startRow/(data.page.endRow-data.page.startRow)+1;
       		 	var totalPage = Math.ceil(data.page.totalProperty/(data.page.endRow-data.page.startRow));
       		 	setPagingToolbarParams(data.page.totalProperty, totalPage,currentPage);
			}else{
				showInfo(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
	    }
	});
}

function openStationWindow(obj){
	var stationkey = $.trim($(obj).attr("stationkey"));
	openWindow(stationMainURL + "?stationkey="+stationkey);
}


/**
 * 查看电站数据，列表显示
 */
function queryStationGrid(){
	param = {};
	var provinceid = $("#province option:selected").val();
	var cityid = $("#city option:selected").val();
	var keyword = $.trim($("#keyword").val());
	
	param.provinceid = provinceid;
	param.cityid = cityid;
	param.keyword = keyword;
	param.pageindex = $.trim($("#currentPage").val());
	param.pagelimit = PAGE_LIMIT;
	
	var tbody = $("#stationTable").find("tbody");
	
	var html = '';
	$.ajax({
		type:'POST',
		url:queryListURL,
		data:param,
		dataType:'json',
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var list = data.page.root;
				var value;
				var newValue;
				$(list).each(function(index,item){
					html += '<tr>';
					value = getNotNullData(item.stationname);
					if(value.length > 20){
						newValue = value.replace(/"/g,"&quot;");//title里如果有双引号要替换成转义符，否则显示不完全
						html += '<td><a data-toggle="tooltip" title="'+newValue+'" class="text-aqua" href="javascript:;" onclick="openStationWindow(this)" stationkey="'+item.stationkey+'">'+substring(value,20)+'</a></td>';
					}else{
						html += '<td><a class="text-aqua" href="javascript:;" onclick="openStationWindow(this)" stationkey="'+item.stationkey+'">'+value+'</a></td>';
					}
					html += getTdHtml(item.ownername, 20);
			        if (item.provincename != "" && item.cityname != "") {
			        	html += '<td>'+item.provincename+','+item.cityname+'</td>';
			        } else if (item.provincename == "" && item.cityname != "") {
			        	html += '<td>'+item.cityname+'</td>';
			        } else if (item.provincename != "" && item.cityname == "") {
			        	html += '<td>'+item.provincename+'</td>';
			        } else {
			        	html += '<td></td>';
			        }
					html += '<td>'+item.scale+'</td>';
					html += '<td>'+item.pstr+'</td>';
					if(isEmpty(item.totalenergy)){
						html += '<td>'+''+'</td>';
					}else{
						html += '<td>'+item.totalenergy+'</td>';
					}
					if(isEmpty(item.CO2)){
						html += '<td>'+''+'</td>';
					}else{
						html += '<td>'+item.CO2+'</td>';
					}
					html += '<td>'+item.dayenergystr+'</td>';
					html += '<td>'+item.updatetimestr+'</td>';
					html += '</tr>';
				});
				
				tbody.html(html);
				
				var currentPage = data.page.startRow/(data.page.endRow-data.page.startRow)+1;
       		 	var totalPage = Math.ceil(data.page.totalProperty/(data.page.endRow-data.page.startRow));
       		 	setPagingToolbarParams(data.page.totalProperty, totalPage,currentPage);
			}else{
				showInfo(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
	    }
	});
}

/**
 * 查询企业所有的电站，在地图上显示
 * @returns
 */
function queryStationMap(){
//	if(showType != 3)return;
	param = {};
	param.keyword =  $.trim($("#keyword").val());
	
	$.ajax({
		type:'POST',
		url:queryEntStationURL,
		data:param,
		dataType:'json',
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var stationList = data.stationList;
				if(stationList != null && stationList.length > 0 && !isEmpty(param.keyword)){
					var record = stationList[0];
            		var point = new BMap.Point(record.lng, record.lat);
                	map.centerAndZoom(point, 5); // 初始化地图，设置中心点坐标和地图级别
				}else{
					var myCity = new BMap.LocalCity();
					myCity.get(setCityCenter);
				}
				createMarkers(stationList);
			}else{
				showInfo(data.message);
			}
		}
	});
}

/**
 * 按地图边界或者关键字查询电站
 * @returns
 */
/*function queryStationByRange(){
	if(showType != 3)return;
	param = {};
	var bs = map.getBounds();   //获取可视区域
	var bssw = bs.getSouthWest();   //可视区域左下角
	var bsne = bs.getNorthEast();   //可视区域右上角
	
	param.keyword =  $.trim($("#keyword").val());
	param.swlng = bssw.lng;
	param.swlat = bssw.lat;
	param.nelng = bsne.lng;
	param.nelat = bsne.lat;
	
	$.ajax({
		type:'POST',
		url:queryMapURL,
		data:param,
		dataType:'json',
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var stationList = data.stationList;
				if(stationList == null || stationList.length == 0){
					removeOverlay();
				}else{
					if(!isEmpty(param.keyword)){
						var record = stationList[0];
	            		var point = new BMap.Point(record.lng, record.lat);
	                	map.centerAndZoom(point, 10); // 初始化地图，设置中心点坐标和地图级别
					}
					createMarkers(stationList);
				}
			}else{
				showInfo(data.message);
			}
		}
	});
}*/

function queryStationDetail(marker){
	param = {};
	param.stationkey = marker.stationkey;
	param.tm = new Date().getTime();
	
	$.ajax({
		type:'POST',
		url:queryDetailURL,
		data:param,
		dataType:'json',
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var station = data.station;
				marker.ownername = station.ownername;
				if(!isEmpty(station.picture)){
					marker.picture = IMG_SRC + station.picture;
				}
				marker.scale = station.scale;
				marker.dayenergystr = station.dayenergystr;
				marker.monthenergystr = station.monthenergystr;
				marker.totalenergystr = station.totalenergystr;
				marker.updatetimestr = station.updatetimestr;
				marker.openInfoWindow(createInfoWindow(marker));
			}else{
				showInfo(data.message);
			}
		}
	});
}

function showResult(flag){
	if(flag == "showGrid"){//显示列表
		$("#chartDiv").addClass("hide");
		$("#gridDiv").removeClass("hide");
		$("#mapDiv").addClass("hide");
		$("#pagingToolbar").removeClass("hide");
		$("#province").removeClass("hide");
		$("#city").removeClass("hide");
	}else if(flag == "showMap"){//显示地图
		$("#chartDiv").addClass("hide");
		$("#gridDiv").addClass("hide");
		$("#mapDiv").removeClass("hide");
		$("#pagingToolbar").addClass("hide");
		$("#province").addClass("hide");
		$("#city").addClass("hide");
	}else{//显示图表
		$("#chartDiv").removeClass("hide");
		$("#gridDiv").addClass("hide");
		$("#mapDiv").addClass("hide");
		$("#pagingToolbar").removeClass("hide");
		$("#province").removeClass("hide");
		$("#city").removeClass("hide");
	}
}



