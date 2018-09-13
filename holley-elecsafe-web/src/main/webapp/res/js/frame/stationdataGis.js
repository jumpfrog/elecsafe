var map = new BMap.Map("mapContainer",{
		enableMapClick : false
}); // 创建地图实例
map.addControl(new BMap.NavigationControl());//添加缩放平移控件
// map.addControl(new BMap.ScaleControl());//添加比例尺控件
map.addControl(new BMap.OverviewMapControl());//添加缩略图控件
//	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
map.enableScrollWheelZoom();//允许使用鼠标滚轮进行地图缩放

//控制地图的最大和最小缩放级别
//	map.setMinZoom(5);
//	map.setMaxZoom(19);

var stationIcon = new BMap.Icon(IMG_SRC+"res/img/mark/map_sign_1.png", new BMap.Size(32,46));
var stationIcon2 = new BMap.Icon(IMG_SRC+"res/img/mark/map_sign_2.png", new BMap.Size(32,46));
var pointCenter = new BMap.Point(120.165717, 30.280309);

var myCity = new BMap.LocalCity();
if(myCity){
	myCity.get(setCityCenter);
}else{
	map.centerAndZoom(pointCenter, 5); // 初始化地图，设置中心点坐标和地图级别
}
	
//	map.addEventListener("tilesloaded",function(){
//		queryStationByRange();
//	});
	
//根据定位城市，设置地图中心
function setCityCenter(result){
	if(result && result.name){
		map.centerAndZoom(result.name, 5);
	}else{
		map.centerAndZoom(pointCenter, 5); // 初始化地图，设置中心点坐标和地图级别
	}
}

//创建点
function createMarkers(list){
	if(list == null || list.length == 0)return;
	var point;
	var lng = list[i].lng;
	var lat = list[i].lat;
	var marker;
	for ( var i = 0; i < list.length; i++) {
		 lng = list[i].lng;
		 lat = list[i].lat;
		if(!isEmpty(lng) && !isEmpty(lat)){
			point = new BMap.Point(lng,lat);
			marker = new BMap.Marker(point);
			marker.eid = list[i].eid;
			if(list[i].isself){//企业自持电站
				marker.setIcon(stationIcon2);
			}else{
				marker.setIcon(stationIcon);
			}
			marker.setTitle(list[i].disc);
			marker.entName = list[i].disc;
			marker.ownername = list[i].ownername;
			//marker.stationkey = list[i].stationkey;
			
			map.addOverlay(marker);
			
			marker.addEventListener("click", function() {
				this.setTop(true);
				queryStationDetail(this);
			});	
		}
	}
	
}

//清除覆盖物
function removeOverlay(){
	map.clearOverlays(); 
}

function queryStationDetail(marker){
	param = {};
	param.eid = marker.eid;
	//param.tm = new Date().getTime();
	
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

//创建信息窗口对象
function createInfoWindow(marker) {
	var html='';
	html += '<h4 class="box-title" style="font-size:16px;"><a class="text-aqua" href="javascript:;" onclick="openStationWindow(this)" stationkey="'+marker.stationkey+'">'+marker.stationname+'</a></h4>';
	html += '<h5 class="text-muted">'+marker.ownername+'</h5>';
	html += '<div class="box box-warning" style="margin-bottom:0;">';
	html += '<div class="box-body">';
	html += '<div class="thumbnail pull-left" style="width:100px;height:100px; padding:0;margin:0">';
	html += '<img src="'+marker.picture+'" alt="Product Image" style="width:100px;height:100px;">';
	html += '</div>';
	html += '<div class="pull-right text-left" style="width:230px;">';
	html += '<div class="form-group">';
	html += '<span>电站规模：'+marker.scale+'&nbsp kWp</span>';
	html += '</div>';
	html += '<div class="form-group">';
	html += '<span>今日发电：'+marker.dayenergystr+'&nbsp kWh</span>';
	html += '</div>';
	html += '<div class="form-group">';
	html += '<span>累计发电：'+marker.totalenergystr+'&nbsp MWh</span>';
	html += '</div>';
	html += '<div class="form-group">';
	html += '<span>更新时间：'+marker.updatetimestr+'</span>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	var infoWindow = new BMap.InfoWindow(html);
	infoWindow.setWidth(360);
	return infoWindow;
}

