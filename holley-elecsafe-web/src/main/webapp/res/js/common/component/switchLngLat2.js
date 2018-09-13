//获取经纬度
var lngLatMap2;
var callbackObj2;
var lngLatMapLayer2;
var tempObj2;
var local2;
function initLngLat2(obj){
	initLngLatMap2();
	initSearchFun2();
	callbackObj2 = obj;
	$("#lngLatMapSaveBtn2").on("click",function(){
		lngLatSave2();
	});
	$("#searchKeyBtn2").on("click",function(){
		var tempKey = $("#searchKey2").val();
		if(!isEmpty(tempKey)){
			searchFun2(tempKey);
		}
	});
}
function initLngLatMap2(){
	lngLatMap2 = new BMap.Map("lngLatMapDiv2",{
			enableMapClick : false
		}); 
	lngLatMap2.enableScrollWheelZoom();//允许使用鼠标滚轮进行地图缩放
		 var myCity = new BMap.LocalCity();
			if(myCity){
				myCity.get(setCityCenter2);
			} 
	 lngLatMap2.addEventListener("click", rturnlngLat2);
}
function setCityCenter2(result){
	if(result && result.name){
		lngLatMap2.centerAndZoom(result.name, 12);
	}
}

function showLngLatMap2(obj){
	var lng = $(obj).attr("lng");
	var lat = $(obj).attr("lat");
	tempObj2=obj;
	var point;
	removeLngLatMapOverlay2();
	if(!isEmpty(lng) && !isEmpty(lat)){
		$("#lngMap2").val(lng);
		$("#latMap2").val(lat);
		point = new BMap.Point(lng, lat); 
		creatMarker2(point);
		lngLatMap2.panTo(point);
	}else{
		$("#lngMap2").val("");
		$("#latMap2").val("");
	}
	lngLatMapLayer2 = layer.open({
		  type: 1,
		  title: '经纬度选择器',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  offset: ['10%', '20%'],
		  area: ['1000px', '500px'], //宽高
		  content: $('#lngLatMapModal2') ,
		});
}

function rturnlngLat2(e){
	removeLngLatMapOverlay2();
	creatMarker2(e.point);
	$("#lngMap2").val(e.point.lng);
	$("#latMap2").val(e.point.lat);
}

function removeLngLatMapOverlay2(){
	$("#searchKey2").val("");
	lngLatMap2.clearOverlays();         
}

function creatMarker2(point){
	var marker = new BMap.Marker(point);  // 创建标注
	lngLatMap2.addOverlay(marker);               // 将标注添加到地图中
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
}

function lngLatSave2(){
	var lng = 	$("#lngMap2").val();
	var lat = $("#latMap2").val();
	if(isEmpty(lng) || isEmpty(lat)){
		layerBox.msgWarning("请选择经纬度")
	return false;	
	}
	callbackObj2.text(lng+","+lat);
	$(tempObj2).attr("lng",lng);
	$(tempObj2).attr("lat",lat);
	lngLatCloseLayer2();
}

function lngLatCloseLayer2(){
	layerBox.close(lngLatMapLayer2)
}

function initSearchFun2(){
	function myFun2(){
		var pp = local2.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
		lngLatMap2.centerAndZoom(pp, 15);
	}
	
	 local2 = new BMap.LocalSearch(lngLatMap2, { //智能搜索
		  onSearchComplete: myFun2
		});
}

function searchFun2(key){
	if(local2){
		local2.search(key);
	}
}