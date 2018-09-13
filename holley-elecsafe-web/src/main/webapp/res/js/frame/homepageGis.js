var map;
var queryEntCountUrl = "main/homepage_queryEntCount.action";//查询告警
var stationIcon = new BMap.Icon(IMG_SRC+"res/img/mark/map_sign_1.png", new BMap.Size(22,46));
var stationIcon2 = new BMap.Icon(IMG_SRC+"res/img/mark/map_sign_2.png", new BMap.Size(22,46));
var pointCenter = new BMap.Point(120.165717, 30.280309);
$(function(){
	initMap(entListData);
	createMarkers(entListData);
})

function returnLngLat(entListData){
	var temp=";"
	if(entListData.length > 0){
		var lng;
		var lat;
		for(var i=0;i<entListData.length;i++){
			 lng = entListData[i].lng;
			 lat = entListData[i].lat;
			if(!isEmpty(lng) && !isEmpty(lat)){
				return new BMap.Point(lng, lat); 
			}
		}
	}
	return "";
}
function initMap(entListData){
	 map = new BMap.Map("mapDiv",{
			enableMapClick : false
		}); 
	 map.enableScrollWheelZoom();//允许使用鼠标滚轮进行地图缩放
	var point = returnLngLat(entListData);
	 if(point){
		 map.centerAndZoom(point, 10);
	 }else{
		 var myCity = new BMap.LocalCity();
			if(myCity){
				myCity.get(setCityCenter);
			} 
	 }
	 
	//去掉高速公路
	map.setMapStyle({
	    styleJson:[
	      {
	            "featureType": "highway",
	            "elementType": "all",
	            "stylers": {
	                      "visibility": "off"
	            }
	      }
	]})
}
// 创建地图实例

//map.addControl(new BMap.NavigationControl());//添加缩放平移控件
//map.addControl(new BMap.ScaleControl());//添加比例尺控件
//map.addControl(new BMap.OverviewMapControl());//添加缩略图控件
//map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
//控制地图的最大和最小缩放级别
//map.setMinZoom(5);
//map.setMaxZoom(19);

//根据定位城市，设置地图中心
function setCityCenter(result){
	if(result && result.name){
		map.centerAndZoom(result.name, 12);
	}else{
		map.centerAndZoom(pointCenter, 12); // 初始化地图，设置中心点坐标和地图级别
	}
}


//创建点
function createMarkers(list){
	removeOverlay();
	if(list == null || list.length == 0)return;
	var point;
	var lng;
	var lat;
	for ( var i = 0; i < list.length; i++) {
		lng = list[i].lng;
		lat = list[i].lat;
		if(!isEmpty(lng) && !isEmpty(lat)){
		point = new BMap.Point(lng, lat);
		var marker = new BMap.Marker(point);
		marker.eid = list[i].eid;
		if(list[i].detCount == list[i].normal){//是否有告警
			marker.setIcon(stationIcon);
		}else{
			marker.setIcon(stationIcon2);
		}
		marker.setTitle(list[i].entname);
		//marker.entname = list[i].disc;
		//marker.ownername = list[i].ownername;
		map.addOverlay(marker);
		
		marker.addEventListener("click", function() {
			this.setTop(true);
			queryEntDetail(this);
//			if(map.getInfoWindow()){
//				if($(map.getInfoWindow().getTitle()).val() != this.stationkey){
//					queryStationDetail(this);
//				}
//			}else{
//				queryStationDetail(this);
//			}
		});
	}
	}
}
//清除覆盖物
function removeOverlay(){
	map.clearOverlays(); 
}

function queryEntDetail(marker){
	param = {};
	param.eid = marker.eid;
	$.ajax({
		type:'POST',
		url:queryEntCountUrl,
		data:param,
		dataType:'json',
		cache:false,
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				var enterprise = data.enterprise;
				marker.entname = enterprise.entname;
				if(!isEmpty(enterprise.picture)){
					marker.picture = IMG_SRC + enterprise.picture;
				}else{
					marker.picture = IMG_SRC+"res/img/frame/default_logo.jpg";
				}
				//marker.ownername = enterprise.ownername;
				marker.detCount = enterprise.detCount;
				marker.normal = enterprise.normal;
				marker.waring = enterprise.waring;
				marker.fault = enterprise.fault;
				marker.offLine = enterprise.offLine;
				//marker.updateTimeStr = enterprise.updateTimeStr;
				marker.openInfoWindow(createInfoWindow(marker));
			}else{
				layerBox.msgWarning(data.message);
			}
		}
	});
}
//创建信息窗口对象
function createInfoWindow(marker) {
	stationkey = marker.stationkey;
	stationscale = marker.scale;
	$("#stationName").text(marker.stationname);
	//地图弹出电站信息
	var html='';
	html += '<h4 class="box-title" style="font-size:16px;"><a class="text-aqua" href="javascript:;" onclick="openStationWindow(this)">'+marker.entname+'</a></h4>';
/*	html += '<h5 class="text-muted">'+marker.ownername+'</h5>';*/
	html += '<div class="box box-warning" style="margin-bottom:0;">';
	html += '<div class="box-body">';
	html += '<div class="pull-left">';
	html += '<img class="img-rounded" src="'+marker.picture+'" alt="Product Image" style="width:80px;height:80px;">';
	html += '</div>';
	html += '<div class="pull-right text-left">';
	html += '<div class="form-group">';
	html += '<span>监测数量：<span class="text-blue">'+marker.detCount+'</span> 个</span>';
	html+="&nbsp;&nbsp;";
	html += '<span>正常数量：<span class="text-green">'+marker.normal+'</span> 个</span>';
	html += '</div>';
	html += '<div class="form-group">';
	html += '<span>告警数量：<span class="text-yellow">'+marker.waring+'</span> 个</span>';
	html+="&nbsp;&nbsp;";
	html += '<span>故障数量：<span class="text-red">'+marker.fault+'</span> 个</span>';
	html += '</div>';
/*	
	html += '<div class="form-group">';
	html += '<span>故障数量：<span class="text-red">'+marker.fault+'</span>&nbsp 个</span>';
	html += '</div>';*/
	html += '<div class="form-group">';
	html += '<span>离线数量：<span class="text-gray">'+marker.offLine+'</span> 个</span>';
	html += '</div>';
/*	html += '<div class="form-group">';
	html += '<span>更新时间：'+marker.updateTimeStr+'</span>';
	html += '</div>';*/
	html += '</div>';
	html += '</div>';
	var infoWindow = new BMap.InfoWindow(html);
	//infoWindow.setTitle("<input type='hidden' value='"+marker.stationkey+"'/>")
	infoWindow.setWidth(350);
	
	//刷新电站数据
	//refreshStationData();
	
	return infoWindow;
}

