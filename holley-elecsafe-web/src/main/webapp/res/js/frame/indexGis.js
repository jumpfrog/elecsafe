	var map = new BMap.Map("mapContainer",{
			enableMapClick : false
	}); // 创建地图实例
	map.addControl(new BMap.NavigationControl());//添加缩放平移控件
// map.addControl(new BMap.ScaleControl());//添加比例尺控件
	map.addControl(new BMap.OverviewMapControl());//添加缩略图控件
//	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.enableScrollWheelZoom();//允许使用鼠标滚轮进行地图缩放
	
	//控制地图的最大和最小缩放级别
	map.setMinZoom(5);
//	map.setMaxZoom(19);
	
	var stationIcon = new BMap.Icon(IMG_SRC+"res/img/mark/map_sign_1.png", new BMap.Size(32,46));
	//var stationSelectIcon = new BMap.Icon(IMG_SRC+"res/img/mark/map_sign_2.png", new BMap.Size(32,46));
	
	var size = new BMap.Size(10, 20);
	var myCity = new BMap.LocalCity();
	myCity.get(setCityCenter);
	
	map.addEventListener("tilesloaded",function(){
		queryStationByRange();
	});
	
//根据定位城市，设置地图中心
function setCityCenter(result){
    var cityName = result.name;
    if(cityName == null){
    	var point = new BMap.Point(120.165717, 30.280309);
    	map.centerAndZoom(point, 10); // 初始化地图，设置中心点坐标和地图级别
    }else{
    	map.centerAndZoom(cityName, 12);
//    	map.setCenter(cityName);   //关于setCenter()可参考API文档---”传送门“
    }
}

//创建点
var markerArray = new Array();
var tempArray;
var selectMarkerid;
function createMarkers(list){
	if(list == null || list.length == 0)return;
	var point;
	tempArray = new Array();
	for ( var i = 0; i < list.length; i++) {
		point = new BMap.Point(list[i].lng, list[i].lat);
		
		var marker = new BMap.Marker(point);
		marker.id = list[i].stationid;
		marker.setIcon(stationIcon);
		marker.setTitle(list[i].stationname);
		marker.stationname = list[i].stationname;
		marker.ownername = list[i].ownername;
		marker.stationkey = list[i].stationkey;
		
		tempArray.push(marker);
		//添加地图可视区域内新增的点
		if(findMarker(markerArray,marker.id) == false){
			markerArray.push(marker);
			map.addOverlay(marker);
		}
		
		marker.addEventListener("click", function() {
			queryStationDetail(this);
		});
		
		if(selectMarkerid != null && selectMarkerid == marker.id){
			queryStationDetail(marker);
		}
	}
	
	//删除已经不在地图可是区域的点
	removeMarker();
	
}

//判断地图可视区域内是否已经存在点
function findMarker(list,id){
	var marker;
	var result = false;
	for(var i=0;i<list.length;i++){
		marker = list[i];
		if(marker.id == id){
			result = true;
			break;
		}
	}
	return result;
}

//删除地图上已经不在可视区域的点
function removeMarker(){
	if(markerArray == null || markerArray.length == 0)return;
	var marker;
	for(var i=markerArray.length-1;i>=0;i--){
		marker = markerArray[i];
		if(findMarker(tempArray, marker.id) == false){
			markerArray.splice(i,1);
			map.removeOverlay(marker); 
		}
	}
}

//清除覆盖物
function removeOverlay(){
	map.clearOverlays(); 
	markerArray = new Array();
	selectMarkerid = null;
}


//恢复默认图标
function clearMarkerIcon(){
	for ( var i = 0; i < markerArray.length; i++) {
		if(selectMarkerid == null || (selectMarkerid != null && selectMarkerid != markerArray[i].id)){
			markerArray[i].setIcon(stationIcon);
		}
		
	}
}

//创建信息窗口对象
function createInfoWindow(marker) {
	selectMarkerid = marker.id;
	marker.setTop(true);
	//marker.setIcon(stationSelectIcon);
	clearMarkerIcon();
	var html='';
     html += '<h4 class="box-title"><a class="text-aqua" href="javascript:;" onclick="openStationWindow(this)" stationkey="'+marker.stationkey+'">'+marker.stationname+'</a></a></h4>';
     html += '<h5 class="text-muted">'+marker.ownername+'</h5>';
    html += '<div class="box box-warning" style="margin-bottom:0;">';
    html += '<div class="box-body">';
    html += '<div>';
    html += '<span>电站规模</span>';
    html += '<span class="pull-right">'+marker.scale+'&nbsp kWp</span>';
    html += '</div>';
    html += '<div>';
    html += '<span>当前功率</span>';
    html += '<span class="pull-right">'+marker.pstr+'&nbsp kW</span>';
    html += '</div>';
    html += '<div>';
    html += '<span>当前辐照</span>';
    html += '<span class="pull-right">'+marker.sristr+'&nbsp W/㎡</span>';
    html += '</div>';
    html += '<div>';
    html += '<span>环境温度</span>';
    html += '<span class="pull-right">'+marker.entstr+'&nbsp ℃</span>';
    html += '</div>';
    html += '<div>';
    html += '<span>今日发电</span>';
    html += '<span class="pull-right">'+marker.dayenergystr+'&nbsp kWh</span>';
    html += '</div>';
    html += '<div>';
    html += '<span>累计发电</span>';
    html += '<span class="pull-right">'+marker.totalenergystr+'&nbsp kWh</span>';
    html += '</div>';
    html += '<div>';
    html += '<span>更新时间</span>';
    html += '<span class="pull-right">'+marker.updatetimestr+'</span>';
    html += '</div>';
    html += '</div>';
    html += '</div>';
	var infoWindow = new BMap.InfoWindow(html);
	infoWindow.setWidth(300);
	return infoWindow;
}

