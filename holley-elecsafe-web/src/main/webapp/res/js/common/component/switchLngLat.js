//获取经纬度
var lngLatMap;
var callbackObj;
var lngLatMapLayer;
var tempObj;
var local;
function initLngLat(obj){
	initLngLatMap();
	initSearchFun();
	callbackObj = obj;
	$("#lngLatMapSaveBtn").on("click",function(){
		lngLatSave();
	});
	$("#searchKeyBtn").on("click",function(){
		var tempKey = $("#searchKey").val();
		if(!isEmpty(tempKey)){
			searchFun(tempKey);
		}
	});
}
function initLngLatMap(){
	lngLatMap = new BMap.Map("lngLatMapDiv",{
			enableMapClick : false
		}); 
	lngLatMap.enableScrollWheelZoom();//允许使用鼠标滚轮进行地图缩放
		 var myCity = new BMap.LocalCity();
			if(myCity){
				myCity.get(setCityCenter);
			} 
	 lngLatMap.addEventListener("click", rturnlngLat);
}
function setCityCenter(result){
	if(result && result.name){
		lngLatMap.centerAndZoom(result.name, 12);
	}
}

function showLngLatMap(obj){
	var lng = $(obj).attr("lng");
	var lat = $(obj).attr("lat");
	tempObj=obj;
	var point;
	removeLngLatMapOverlay();
	if(!isEmpty(lng) && !isEmpty(lat)){
		$("#lngMap").val(lng);
		$("#latMap").val(lat);
		point = new BMap.Point(lng, lat); 
		creatMarker(point);
		lngLatMap.panTo(point);
	}else{
		$("#lngMap").val("");
		$("#latMap").val("");
	}
	lngLatMapLayer = layer.open({
		  type: 1,
		  title: '经纬度选择器',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  offset: ['10%', '20%'],
		  area: ['1000px', '500px'], //宽高
		  content: $('#lngLatMapModal') ,
		});
}

function rturnlngLat(e){
	removeLngLatMapOverlay();
	creatMarker(e.point);
	$("#lngMap").val(e.point.lng);
	$("#latMap").val(e.point.lat);
}

function removeLngLatMapOverlay(){
	$("#searchKey").val("");
	lngLatMap.clearOverlays();         
}

function creatMarker(point){
	var marker = new BMap.Marker(point);  // 创建标注
	lngLatMap.addOverlay(marker);               // 将标注添加到地图中
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
}

function lngLatSave(){
	var lng = 	$("#lngMap").val();
	var lat = $("#latMap").val();
	if(isEmpty(lng) || isEmpty(lat)){
		layerBox.msgWarning("请选择经纬度")
	return false;	
	}
	callbackObj.text(lng+","+lat);
	$(tempObj).attr("lng",lng);
	$(tempObj).attr("lat",lat);
	lngLatCloseLayer();
}

function lngLatCloseLayer(){
	layerBox.close(lngLatMapLayer)
}

function initSearchFun(){
	function myFun(){
		var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
		lngLatMap.centerAndZoom(pp, 15);
	}
	
	 local = new BMap.LocalSearch(lngLatMap, { //智能搜索
		  onSearchComplete: myFun
		});
}

function searchFun(key){
	if(local){
		local.search(key);
	}
}