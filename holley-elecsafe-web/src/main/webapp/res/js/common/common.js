/**
 * 获取浏览器窗口的宽度和高度
 * @returns {___anonymous879_921}
 */
function getViewSize(){
	var viewwidth;
	var viewheight;
	// the more standards compliant browsers (mozilla/netscape/opera/IE7) use window.innerWidth and window.innerHeight
	 if (typeof window.innerWidth != 'undefined'){
		 viewwidth = window.innerWidth,
		 viewheight = window.innerHeight
	 }
	// IE6 in standards compliant mode (i.e. with a valid doctype as the first line in the document)
	 else if (typeof document.documentElement != 'undefined'
	     && typeof document.documentElement.clientWidth !=
	     'undefined' && document.documentElement.clientWidth != 0){
		 viewwidth = document.documentElement.clientWidth,
		 viewheight = document.documentElement.clientHeight
	 }
	 // older versions of IE
	 else{
		 viewwidth = document.getElementsByTagName('body')[0].clientWidth,
		 viewheight = document.getElementsByTagName('body')[0].clientHeight
	 }
	return {width:viewwidth,height:viewheight}
}
/**
 * 判断是否为空
 * @param str
 * @returns {Boolean}
 */
function isEmpty(str){
	if(!str){
		return true;
	}else if(str == undefined){
		return true;
	}else if($.trim(str).length <= 0){
		return true;
	}
	return false;
}
/**
 * 删除左右两端的空格
 * @param str
 * @returns
 */
function trim(s){
	return s.replace(/(^s*)|(s*$)/g, "");
}

/**
 * 判断是否有重复数字
 * @param arr
 * @returns {Boolean}
 */
function isRepeat(arr) {
   var hash = {};
   for(var i in arr) {
       if(hash[arr[i]]){
           return true;
       }
       // 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
       hash[arr[i]] = true;
    }
   return false;
}

function getTimeStamp(){
	return new Date().getTime();
}

function getFormatDate(date){
	str="";
	if(!date)return "";
	if(date.indexOf("T") != -1){
		str = date.replace("T"," ")
	}else{
		str = date;
	}
	return str;
}

function shortStrToDate(str){
	if(isEmpty(str))return null;
	str = str.replace("-","/");
	return new Date(str);
}

function getThisMonthDay(){
	var date = new Date();
	return new Date(date.getFullYear(),date.getMonth(),1);
}

function getWindowHeight(){
	return $(window).height();
}

function getWindowWidth(){
	return $(window).width();
}

/**
 * 获取除去header和footer以外的高度
 * @returns {Number}
 */
//function getContentHeight(){
//	return $(window).height()-HEADER_HEIGHT-FOOTER_HEIGHT;
//}

//pagingToolbar---start----------------------------------------//

/**
 * 初始化分页条,绑定按钮事件
 * @param fn
 */
function initPagingToolbar(fn){
	//第一页
	$("#firstPage").on("click",function(){
		var pageIndex = 1;
		$("#currentPage").val(pageIndex);
		fn();
	});
	
	//上一页
	$("#prevPage").on("click",function(){
		var pageIndex = parseInt($("#currentPage").val())-1;
		$("#currentPage").val(pageIndex);
		fn();
	});
	
	//下一页
	$("#nextPage").on("click",function(){
		var pageIndex = parseInt($("#currentPage").val())+1;
		$("#currentPage").val(pageIndex);
		fn();
	});
	
	//最后一页
	$("#lastPage").on("click",function(){
		var pageIndex = $("#totalPage").text();
		$("#currentPage").val(pageIndex);
		fn();
	});
}

function setCurrentPage(index){
	$("#currentPage").val(index);
}

/**
 * 初始化分页条2,绑定按钮事件
 * @param fn
 */
function initPagingToolbar2(fn){
	//第一页
	$("#firstPage2").on("click",function(){
		var pageIndex = 1;
		$("#currentPage2").val(pageIndex);
		fn();
	});
	
	//上一页
	$("#prevPage2").on("click",function(){
		var pageIndex = parseInt($("#currentPage2").val())-1;
		$("#currentPage2").val(pageIndex);
		fn();
	});
	
	//下一页
	$("#nextPage2").on("click",function(){
		var pageIndex = parseInt($("#currentPage2").val())+1;
		$("#currentPage2").val(pageIndex);
		fn();
	});
	
	//最后一页
	$("#lastPage2").on("click",function(){
		var pageIndex = $("#totalPage2").text();
		$("#currentPage2").val(pageIndex);
		fn();
	});
}

function setCurrentPage2(index){
	$("#currentPage2").val(index);
}

/**
 * 初始化分页条2,绑定按钮事件
 * @param fn
 */
function initPagingToolbar3(fn){
	//第一页
	$("#firstPage3").on("click",function(){
		var pageIndex = 1;
		$("#currentPage3").val(pageIndex);
		fn();
	});
	
	//上一页
	$("#prevPage3").on("click",function(){
		var pageIndex = parseInt($("#currentPage3").val())-1;
		$("#currentPage3").val(pageIndex);
		fn();
	});
	
	//下一页
	$("#nextPage3").on("click",function(){
		var pageIndex = parseInt($("#currentPage3").val())+1;
		$("#currentPage3").val(pageIndex);
		fn();
	});
	
	//最后一页
	$("#lastPage3").on("click",function(){
		var pageIndex = $("#totalPage3").text();
		$("#currentPage3").val(pageIndex);
		fn();
	});
}

/**
 * 设置分页条的参数
 * @param totalProperty
 * @param totalPage
 */
function setPagingToolbarParams(totalProperty,totalPage,currentPage){
	 $("#currentPage").val(currentPage);
	 $("#totalProperty").text(totalProperty);
	 $("#totalPage").text(totalPage);
  	 if($("#currentPage").val() <= 1){
  		$("#prevPage").attr("disabled","disabled");
  		$("#firstPage").attr("disabled","disabled");
  	 }else{
  		$("#prevPage").removeAttr("disabled");
  		$("#firstPage").removeAttr("disabled");
  	 }
  	
  	 if($("#currentPage").val() >= totalPage){
  		$("#nextPage").attr("disabled","disabled");
  		$("#lastPage").attr("disabled","disabled");
  	 }else{
  		$("#nextPage").removeAttr("disabled");
  		$("#lastPage").removeAttr("disabled");
  	 }
}
/**
 * 设置分页条2的参数
 * @param totalProperty
 * @param totalPage
 */
function setPagingToolbar2Params(totalProperty,totalPage,currentPage){
	$("#currentPage2").val(currentPage);
	 $("#totalProperty2").text(totalProperty);
	 $("#totalPage2").text(totalPage);
  	 if($("#currentPage2").val() <= 1){
  		$("#prevPage2").attr("disabled","disabled");
  		$("#firstPage2").attr("disabled","disabled");
  	 }else{
  		$("#prevPage2").removeAttr("disabled");
  		$("#firstPage2").removeAttr("disabled");
  	 }
  	
  	 if($("#currentPage2").val() >= totalPage){
  		$("#nextPage2").attr("disabled","disabled");
  		$("#lastPage2").attr("disabled","disabled");
  	 }else{
  		$("#nextPage2").removeAttr("disabled");
  		$("#lastPage2").removeAttr("disabled");
  	 }
}

/**
 * 设置分页条2的参数
 * @param totalProperty
 * @param totalPage
 */
function setPagingToolbar3Params(totalProperty,totalPage,currentPage){
	$("#currentPage3").val(currentPage);
	 $("#totalProperty3").text(totalProperty);
	 $("#totalPage3").text(totalPage);
  	 if($("#currentPage3").val() <= 1){
  		$("#prevPage3").attr("disabled","disabled");
  		$("#firstPage3").attr("disabled","disabled");
  	 }else{
  		$("#prevPage3").removeAttr("disabled");
  		$("#firstPage3").removeAttr("disabled");
  	 }
  	
  	 if($("#currentPage3").val() >= totalPage){
  		$("#nextPage3").attr("disabled","disabled");
  		$("#lastPage3").attr("disabled","disabled");
  	 }else{
  		$("#nextPage3").removeAttr("disabled");
  		$("#lastPage3").removeAttr("disabled");
  	 }
}

/**
 * 分页1参数更新
 * @returns
 */
function updatePageParams(page){
	var currentPage = page.startRow/(page.endRow-page.startRow)+1;
	var totalPage = Math.ceil(page.totalProperty/(page.endRow-page.startRow));
	setPagingToolbarParams(page.totalProperty, totalPage,currentPage);
}

/**
 * 分页2参数更新
 * @returns
 */
function updatePageParams2(page){
	var currentPage = page.startRow/(page.endRow-page.startRow)+1;
	var totalPage = Math.ceil(page.totalProperty/(page.endRow-page.startRow));
	setPagingToolbar2Params(page.totalProperty, totalPage,currentPage);
}

/**
 * 分页3参数更新
 * @returns
 */
function updatePageParams3(page){
	var currentPage = page.startRow/(page.endRow-page.startRow)+1;
	var totalPage = Math.ceil(page.totalProperty/(page.endRow-page.startRow));
	setPagingToolbar3Params(page.totalProperty, totalPage,currentPage);
}

//pagingToolbar---end----------------------------------------//


/**
 * 设置默认值
 * @param data
 * @returns
 */
function getNotNullData(data){
	if(data){
		return data;
	}else{
		return "";
	}	
}

/**
 * 设置默认值
 * @param data
 * @returns
 */
function getNotNullData2(data,defaultstr){
	if(data){
		return data;
	}else{
		return defaultstr;
	}	
}

/**
 * 设置数字默认值
 * @param data
 * @returns
 */
function getDefaultZeroData(data){
	if(data || data == 0){
		return data;
	}else{
		return "0";
	}	
}

function getDefaultData(str){
	if(str == "0"){
		return str;
	}else if(isEmpty(str)){
		return "-";
	}else{
		return str;
	}
}

/**
 * 获取有文字缩略的列
 * @param value
 * @param length
 */
function getTdHtml(value,length){
	var html = "";
	value = getNotNullData(value);
	if(value.length > length){
		 var newValue = value.replace(/"/g,"&quot;");//title里如果有双引号要替换成转义符，否则显示不完全
		 html += '<td data-toggle="tooltip" title="'+newValue+'">'+substring(value,length)+'</td>';
	 }else{
		 html += '<td>'+value+'</td>';
	 }
	return html;
}

/**
 * 获取有文字缩略的列
 * @param value
 * @param length
 */
function getTdHtml2(id,value,length){
	var html = "";
	value = getNotNullData(value);
	if(value.length > length){
		 var newValue = value.replace(/"/g,"&quot;");//title里如果有双引号要替换成转义符，否则显示不完全
		 html += '<td id="'+id+'" data-toggle="tooltip" title="'+newValue+'">'+substring(value,length)+'</td>';
	 }else{
		 html += '<td id="'+id+'">'+value+'</td>';
	 }
	return html;
}

function replaceSign(value){
	value = $.trim(getNotNullData(value));
	if(isEmpty(value)){
		return value
	}
	value = value.replace(/"/g,"&quot;");//title里如果有双引号要替换成转义符，否则显示不完全
	return value;
}

function substring(str,length){
	if(!str)return "";
	var temp;
	if(str.length > length){
		temp = str.substring(0,length)+"...";
	}else{
		temp = str;
	}
	return temp;
}

/**
 * 重置表单
 * @param form
 */
function resetForm(form){
	form.get(0).reset();
}

/**
 * 封装表单数据
 * @param form
 * @returns {___anonymous120_121}
 */
function getFormJson(form) {
	var o = {};
	var a = form.serializeArray();
	$.each(a, function () {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}

			o[this.name].push(this.value || '');
		} else {

			o[this.name] = this.value || '';
		}
	});

	return o;
}
/**
 * 表单数据转化为JSON字符串
 * @param formObj
 * @returns
 */
function formDataToJsonString(formObj){
	return JSON.stringify(getFormJson(formObj));
}

function objToJsonString(dataObj){
	return JSON.stringify(dataObj);
} 

function jsonstrToObj(jsonstr){
	return JSON.parse(jsonstr)
}

//弹出框
//function showInfo(txt,opt){
//	if(opt){
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info,opt);
//	}else{
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
//	}
//	
//}
//
//function showConfirm(txt,opt){
//	if(opt){
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,opt);
//	}else{
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm);
//	}
//}
//
//function showWarning(txt,opt){
//	if(opt){
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,opt);
//	}else{
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
//	}
//}
//
//function showError(txt,opt){
//	if(opt){
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error,opt);
//	}else{
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
//	}
//}
//
//function showSuccess(txt,opt){
//	if(opt){
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,opt);
//	}else{
//		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
//	}
//}
//
//function showCustom(txt,option){
//	window.wxc.xcConfirm(txt, "custom", option);
//}
//
//function showDefault(txt){
//	window.wxc.xcConfirm(txt);
//}

function encodeHtml(s){
    return (typeof s != "string") ? s :
        s.replace(this.REGX_HTML_ENCODE,
                  function($0){
                      var c = $0.charCodeAt(0), r = ["&#"];
                      c = (c == 0x20) ? 0xA0 : c;
                      r.push(c); r.push(";");
                      return r.join("");
                  });
};

function reload() {
	location.reload() 
}

function goback(){
	history.back();//返回 
}

function gobackAndReload(){
//	history.back();//返回 
//	var bow = getBrowser();
//	if("IE" == bow){
//		location.reload();//刷新
//	}
	self.location=document.referrer;
}

function href(url,datas) {
	var temurl = WEB_ROOT+"/"+url;
	if(datas){
		for(var x=1;x<=3;x++){
			var temname;
			var temvalue;
			switch (x) {
			case 1:
				temname = datas.name1;
				temvalue = datas.value1;
				break;
			case 2:
				temname = datas.name2;
				temvalue = datas.value2;
				break;
			case 3:
				temname = datas.name3;
				temvalue = datas.value3;
				break;
			default:
				break;
			}
			if(temname && temvalue){
				if(x == 1){
					temurl+="?"+temname+"="+temvalue;
				}else{
					temurl+="&"+temname+"="+temvalue;
				}
			}
		}
		
	}
	window.location.href = temurl;
}

function openWindow(url) {
	window.open(WEB_ROOT+"/"+url);
}

function openImg(obj,title){
	$(obj).popover({ 
		html : true,
		title: function() {
		 return title;
		},
		content: function() {
			var html = "";
			html += '<div id="appcode_popover">'; 
			html += '<div style="width: 300px;height: 300px;">';
			html += '<img class="img-thumbnail" alt="" src="'+$(obj).attr("src")+'" style="width: 300px;height: 300px;">';
		 	html += '</div></div>';
		 return html;
		}
	});
	 $(obj).popover("show");
}

function closeImg(obj){
	$(obj).popover("hide");
}

//当前浏览器
function getBrowser(){  
    var browserName=navigator.userAgent.toLowerCase(); 
    if(/msie/i.test(browserName) && !/opera/.test(browserName)){  
        return "IE";  
    }else if(/firefox/i.test(browserName)){  
        return "Firefox";  
    }else if(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName)){  
    	return"Chrome";  
    }else if(/opera/i.test(browserName)){  
    	return "Opera";  
    }else if(/webkit/i.test(browserName) &&!(/chrome/i.test(browserName) && /webkit/i.test(browserName) && /mozilla/i.test(browserName))){  
    	return "Safari";  
    }else{  
    	return "unKnow";  
    }  
}

function showMsg(message,errormsg){
	if(!isEmpty(message)){
		if("relogin" == message){
			var opt={};
			opt.onOk = function(){
				location.reload();
			}
			showInfo("登录超时，请重新登录！",opt);
		}else{
			showInfo(message);
		}
	}else if(!isEmpty(errormsg)){
		showError(errormsg);
	}
}


/**
 * 初始化导出按钮
 */
function initExportBtn(exportBtn,form,title,tableId){
	exportBtn.on("click",function(){
		var total = $("#totalProperty").text();
		if(total <=  0){
			layerBox.msgWarning("没有可导出的数据");
			return;
		}else if(total > EXPORT_MAX){
			layerBox.msgWarning("当前记录数过多，默认导出前"+EXPORT_MAX+"条记录");
		}
		
		//exportBtn.attr("disabled",true);
		//exportBtn.blur();
//		var option = {
//				title: "系统提示",
//				btn: window.wxc.xcConfirm.btnEnum.okcancel,
//				onOk: function(){
//					exportBtn.attr("disabled",false);
//					form.submit();
//				},
//				onClose:function(){
//					exportBtn.attr("disabled",false);
//				},
//				onCancel:function(){
//					exportBtn.attr("disabled",false);
//				}
//			}
//		showInfo("确定导出'"+title+"'吗?",option);
		
		layerBox.msgConfirm("确定导出'"+title+"'吗?", function(){
			form.submit();
		},true)
		
	});
}

/**
 * 初始化导出按钮,无分页
 */
function initExportBtn2(exportBtn,form,title,tableId){
	exportBtn.on("click",function(){
		if(tableId){
			if(tableId.find('tbody').find('tr').length== 0){
				showInfo("没有可导出数据");
				return;
			}
		}
		exportBtn.attr("disabled",true);
		exportBtn.blur();
		var option = {
				title: "系统提示",
				btn: window.wxc.xcConfirm.btnEnum.okcancel,
				onOk: function(){
					exportBtn.attr("disabled",false);
					form.submit();
				},
				onClose:function(){
					exportBtn.attr("disabled",false);
				},
				onCancel:function(){
					exportBtn.attr("disabled",false);
				}
			}
		showInfo("确定导出'"+title+"'吗?",option);
	});
}
//iframe里功能检测
function preprocess(data) {
	if(data.error == SESSION_TIMEOUT) {//会话结束
//		var opt={};
//		opt.onOk = function(){
//			parentLogout();
//		}
//		showInfo("您的会话已结束,请重新登录!",opt);
		layerBox.msgWarning("您的会话已结束,请重新登录!", parentLogout);
		setTimeout(function(){
			parentLogout();
		}, 1000 * 3);//3秒后自动跳转到登录页面
		return true;
	}else if(data.error == SESSION_CHANGE){//更换登录地点
//		var opt={};
//		opt.onOk = function(){
//			parentLogout();
//		}
		layerBox.msgWarning("您的账号已在其它地方登录!",parentLogout);
		setTimeout(function(){
			parentLogout();
		}, 1000 * 3);//3秒后自动跳转到登录页面
		return true;
	}else if(data.error == ACCESS_DENIED) {//无操作权限
		layerBox.msgWarning("很抱歉,您无改操作权限!");
		return true;
	}else if(data.error == DATAAREA_DENIED) {//无数据范围权限
		layerBox.msgWarning("很抱歉,您无操作该企业数据的权限!");
		return true;
	}else if(data.error == JSON_ERROR) {//操作异常
		layerBox.msgError(data.errormsg);
		return true;
	}
	return false;
}

//iframe外功能检测
function preprocess2(data) {
	if(data.error == SESSION_TIMEOUT) {//会话结束
		var opt={};
		opt.onOk = function(){
			logout();
		}
		showInfo("您的会话已结束,请重新登录!",opt);
		setTimeout(function(){
			logout();
		}, 1000 * 3);//3秒后自动跳转到登录页面
		return true;
	}else if(data.error == SESSION_CHANGE){//更换登录地点
		var opt={};
		opt.onOk = function(){
			logout();
		}
		showInfo("您的账号已在其它地方登录!",opt);
		setTimeout(function(){
			logout();
		}, 1000 * 3);//3秒后自动跳转到登录页面
		return true;
	}else if(data.error == ACCESS_DENIED) {//操作权限
		showInfo("很抱歉,您无改操作权限!");
		return true;
	}else if(data.error == JSON_ERROR) {//操作异常
		showInfo(data.errormsg);
		layerBox.msgError(data.errormsg);
		return true;
	}
	return false;
}

function frameHref(url) {
	window.parent.href(url);
}

function frameReload() {
	frameHref(MAIN_URL);
	//window.parent.reload();
}

function parentLogout(){
	frameHref(LOGOUT_URL);
}

function logout(){
	href(LOGOUT_URL);
}

/**
 * 设置、获取、删除、清空前端sessionStorage里的参数
 * 
 */
function setSessionStorageItem(key,value){
	sessionStorage.setItem(key,value);
}

function getSessionStorageItem(key){
	return sessionStorage.getItem(key);
}

function removeSessionStorageItem(key){
	sessionStorage.removeItem(key);
}

function clearSessionStorage(){
	sessionStorage.clear();
}

/**
 * 设置、获取、删除、清空查询条件等参数
 * 
 */
function setQueryParam(key,value){
	sessionStorage.setItem(STORAGE_KEY_QUERY + key,value);
}
function getQueryParam(key){
	return sessionStorage.getItem(STORAGE_KEY_QUERY + key);
}
function removeQueryParam(key){
	sessionStorage.removeItem(STORAGE_KEY_QUERY + key);
}
function clearQueryParam(){
	var key;
	for (var i = 0; i < sessionStorage.length; i++) {
		var key = sessionStorage.key(i);
		if(!isEmpty(key) && key.indexOf(STORAGE_KEY_QUERY) != -1){
			removeSessionStorageItem(key);
		}
	}
}

/**
 * 设置cookie
 */
function setCookie(name,value){  
	document.cookie = name+"="+escape(value);  
}
/**
 * 获取cookie
 */
function getCookie(name){  
    var arr = document.cookie.match(new RegExp("(^|)"+name+"=([^;]*)(;|$)"));  
    if(arr != null){  
        return unescape(arr[2]);  
    }  
}

/**
 * 判断是否存在按钮
 * @param buttonid
 * @param btns
 * @returns {Boolean}
 */
function isExistBtn(buttonid,btns){
	if(buttonid == null || btns == null)return false;
	for(var i=0;i<btns.length;i++){
		if(buttonid == btns[i].buttonid)return true;
	}
	return false;
}

//显示loading
function showLoading(){
	$("#loading").removeClass("hide");
}

//隐藏loading
function hideLoading(){
	$("#loading").addClass("hide");
}

//设置有分页列表高度
function setPageTableDivH(obj){
	$(obj).height(getWindowHeight() - 170);
}
//设置无分页列表高度
function setTableDivH(obj){
	$(obj).height(getWindowHeight() - 160);
}

//检查图片大小格式
function checkImg(img,maxSize){
	var domObj = img.get(0).files[0];
	var path = img.val();
	var size;
	if(!path){
		return "请选择上传图片！！";
	}
	var end = path.substring(path.lastIndexOf(".")+1);
	end = end.toLowerCase();

	if(end != "jpg" && end != "png" && end != "gif"){
		return "上传图片必须jpg或png或gif格式！！";
	}
	else if(!domObj){
		return "请选择上传图片！！";
	}
	else{
		size = domObj.size;
		if(maxSize){
			if(size/(1000*1024) > maxSize){
				return "选择上传的文件不能大于"+maxSize+"M！！";
			}
		}
	}
	return "success";
}

var maxImgSize = 4;//图片大小4M
function commomChangeImg(ImgfileId,srcImgId){
	/* rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i; */
	var reader = new FileReader();
		reader.onload = function (oFREvent) {
		document.getElementById(srcImgId).src = oFREvent.target.result;
	};
	$("#"+ImgfileId).on("change",function(){
		msg = checkImg($(this), maxImgSize);
		if ("success" != msg) {
			showWarning(msg);
			$(this).val("");
		} else {
			var oFile = $(this).get(0).files[0];
			reader.readAsDataURL(oFile);
		}
	});
	if(srcImgId){
		$("#"+srcImgId).hover(function(){
			$(this).popover({
				html : true,
				title: function() {
					return "<p class='text-center' style='margin-bottom:1px;'>图片</p>";
				},
				content: function() {
					return "<img style='width:250px;' src='"+$(this).attr("src")+"'>";
				}
			});
			$(this).popover("show");
		},function(){
			$(this).popover("hide");
		});
	}
}
//禁止浏览器回退
function disableBack(){
	window.history.forward(0);
}

//绑定事件 任意点击将菜单的下拉框收起
$("#mainContent").on('click',function(){
	var temp = getParentElementByClassName("menuview");
	temp.each(function(index,data){
		$(data).removeClass("open")
	})
})

function getParentElementByClassName(className){
	var el = window.parent.document.getElementsByClassName("menuview")
	return $(el);
}

function getParentElementById(id){
	var el = window.parent.document.getElementById(id)
	return $(el);
}


//弹出框
var layerBox = {
		msgWarning:function(msg,fn){//提示
			if(fn){
				layer.alert(msg, {icon: 0, title:'系统提示',offset: ['40%','40%'],cancel:fn},function(index){layer.close(index);fn();});
			}else{
				layer.alert(msg, {icon: 0,title:'系统提示',offset: ['40%','40%']});
			}
			
		},
		msgOk:function(msg,fn){//成功
			if(fn){
				layer.alert(msg, {icon: 1, title:'系统提示',offset: ['40%','40%'],cancel:fn},function(index){layer.close(index);fn();});
			}else{
				layer.alert(msg, {icon: 1,title:'系统提示',offset: ['40%','40%']});	
			}
			
		},
		msgFail:function(msg,fn){//失败
			if(fn){
				layer.alert(msg, {icon: 2, title:'系统提示',offset: ['40%','40%'],cancel:fn},function(index){layer.close(index);fn();});
			}else{
				layer.alert(msg, {icon: 2, title:'系统提示',offset: ['40%','40%']});
			}
			
		},
		msgError:function(msg){
			layer.alert(msg, {icon: 2, title:'系统提示'});
		},
		msgInfo:function(title,msg,fn){//详细信息显示
			if(fn){
				layer.alert(msg,{title:title, offset: ['40%','40%']},function(index){layer.close(index);fn();});
			}else{
				layer.alert(msg,{title:title, offset: ['40%','40%']});
			}
			
		},
		msgConfirm:function(msg,fn,isClose){
//			layer.confirm(msg, {
//				title:"系统提示",
//				  btn: ['确定', '取消'] ,
//				btn1:function(index){
//					fn();
//				},
//				btn2:function(index){
//				layer.close(index)
//				}
//				}); 
				layer.confirm(msg, {icon: 3, title:'系统提示', offset: ['40%','40%']}, function(index){
					if(isClose){
						layer.close(index)
					}
					fn();
				});
		},
		load:function(){
			return layer.load();
		},
		close:function(index){
			layer.close(index);
		},
		closeAll:function(){
			layer.closeAll();
		}
}

//bootstrap table
/**
 * mainContentH
 * 没有按钮条和分页条
 * @returns
 */
function getFrHeight_Full(){
	 return (getViewSize().height - 27);
}

/**
 * mainContentH-btnbarH
 * 只有按钮条
 * @returns
 */
function getFrHeight_ExBtnbar(){
	 return (getViewSize().height - 67);//sm:67,md:71
}

/**
* mainContentH-btnbarH-pagebarH
* 有按钮条和分页条
* @returns
*/
function getFrHeight_ExBtnPagebar(){
	 return (getViewSize().height - 160);//sm:106,md:110
}

/**
 * 重置bootstrat-table的高度
 * @param obj
 * @param height
 * @returns
 */
function resizeTableHeight(obj,height){
	 obj.bootstrapTable('resetView', {
	      height: height
	  });
}

/**
 * 重置bootstrap-table的宽度
 * @param obj
 * @returns
 */
function resizeTableWidth(obj){
	 setTimeout(function(){
		 obj.bootstrapTable('resetWidth');	
		}, 100);
}


/**
 * 获取bootstarp-table checkbox选中行的对象
 * @param obj
 * @returns
 */
function getBsTableCheckedItem(obj){
	var checkedArray = $(obj).bootstrapTable('getSelections');
	if(checkedArray == null || checkedArray.length == 0){
		return null;
	}
	return checkedArray;
}

function errorMsg(){
	layerBox.msgWarning("非法参数");
}

//根据选择的省份载入市
function loadCity(provinceid,city){
	if(isEmpty(provinceid) || provinceid == 0){
		city.html('<option value="0">选择市</option>');
		return;
	}
	var html = '';
	$.ajax({
		type:'POST',
		url:"comm/searchComm_queryCityByProvince.action",
		data:{provinceid:provinceid},
		dataType:'json',
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var cityList = data.cityList;
				city.html('<option value="0">选择市</option>');
				if(cityList != null && cityList.length > 0){
					$(cityList).each(function(index,item){
							html += '<option value="'+item.cityid+'">'+item.name+'</option>';
					});
					city.append(html);
				}
			}else{
				layerBox.msgWarning(data.message);
			}
		}
	});
}

//bootstrap-table---start----------------------------------------//
/**
 * 获得表格序号
 * @param value
 * @param row
 * @param index
 * @returns
 */
function renderRowNum(value, row, index) {
    return index+1;
}

/**
 * 加载bootstrap-table的内容
 * @param obj
 * @param data
 * @returns
 */
function loadTableData(obj,data){
	if(!data)data = {};
	obj.bootstrapTable('load', data);
	//重置表格宽度，防止表头列宽和内容列宽不一致
	resizeTableWidth(obj);
}

/**
 * 清空表格的全部数据
 * @param obj
 * @returns
 */
function removeTableAllData(obj){
	obj.find("tbody").empty();
	//obj.bootstrapTable('removeAll');
}

/**
 * 获取bootstarp-table选中行的对象(单选)
 * @param obj
 * @returns
 */
function getBsTableChecked(obj){
	var item = null;
	var array = $(obj).bootstrapTable('getSelections');
	if(array != null && array.length > 0){
		item = array[0];
	}
	return item;
}

/**
 * 获取bootstarp-table选中行的对象(多选)
 * @param obj
 * @returns
 */
function getBsTableCheckeds(obj){
	return $(obj).bootstrapTable('getSelections');
}

/**
 * 获取bootstarp-table选中行的对象的主键(多选)
 * @param obj
 * @returns
 */
function getBsTableCheckedIds(obj,idfield){
	var idArray = [];
	if(isEmpty(idfield))return idArray;
	var checkedArray = $(obj).bootstrapTable('getSelections');
	if(checkedArray == null || checkedArray.length == 0){
		return idArray;
	}
	$(checkedArray).each(function(index,item){
		idArray.push(item[idfield]);
	});
	return idArray;
}

/**
 * 初始化固定表头表格(固定参数)
 * @param columns
 * @param data
 * @param height
 * @returns
 */
function initTable(table,columns,data,height){
	if(!columns){
		columns = {};
	}
	
	if(!data){
		data = {};
	}
	//先销毁表格  
    $(table).bootstrapTable('destroy');
    //初始化表格
    $(table).bootstrapTable({  
    	columns:columns,
    	data:data,
    	height:height
    });
    resizeTableWidth($(table));
}
//bootstrap-table---end----------------------------------------//

/**
 * 获取下拉多选框选中的值
 * @param obj
 * @returns
 */
function getSelectCheckedValues(obj){
	return $(obj).find('option:selected').map(function(a, item){return item.value;}).get();
}

/**
 * 返回
 * MM-dd
 * hh:mm:ss
 */
function getChartFormatDate(value,isShowYear){
	if(isEmpty(value)){
		value="";
	}
	var day=value.split(' ');
	var days = day[0].split('-');
	
	var mi = day[day.length - 1].split(':');
	var date = new Date();
	date.setUTCFullYear(days[0], days[1] - 1, days[2]);
    //给date赋值 时分秒  首先转换utc时区 ：+8      
	date.setUTCHours(mi[0] - 8, mi[1], mi[2]);
	  // var date = new Date(value);
	   var year=date.getFullYear(); 
       var months = date.getMonth() + 1;
       var dates = date.getDate();
       var hours = date.getHours();
       var minutes = date.getMinutes();
       var seconds = date.getSeconds();
       if(months < 10){
    	   months = "0"+months;
       }
       if(dates < 10){
    	   dates = "0"+dates;
       }
       if(hours < 10){
    	   hours = "0"+hours;
       }
       if(minutes < 10){
    	   minutes = "0"+minutes;
       }
       if(seconds < 10){
    	   seconds = "0"+seconds;
       }
       var tem1 = months+"-"+dates;
       var tem2 = hours+":"+minutes+":"+seconds;
       var tem;
       if(isShowYear){
    	   tem = year+"-"+tem1+ '\n' +tem2;
       }else{
    	   tem = tem1+ '\n' +tem2;  
       }
       return tem;
}

function appendBreadcrumb(value,url){
	var html="";
	if(isEmpty(url)){
		html = '<li style="padding-right:5px;"> '+value+'</li>';
	}else{
		html = '<li style="padding-right:5px;"> <a href="javascript:;" onclick="reloadParentIframe(this)" url="'+url+'">'+value+'</a></li>';
	}
	 
	getParentElementById("header_breadcrumb").append(html);
}
function reloadParentIframe(obj){
	getParentElementById("mainWindow").attr("src",$(obj).attr("url"));
	backBreadcrumb2();
}
function reloadParentIframe2(url){
	var iframe = getParentElementById("mainWindow");
	if(isEmpty(url)){
		iframe.attr("src",iframe.attr("src"));
	}else{
		iframe.attr("src",url);
	}
}
function backBreadcrumb1(){
	var tobj = getParentElementById("header_breadcrumb");
	tobj.html(tobj.find("li").eq(0))
}

function backBreadcrumb2(){
	var tobj = getParentElementById("header_breadcrumb");
	tobj.find("li").eq(2).remove()
}

/**
 * 获取设备状态
 * @param data
 * @returns
 */
function getDeviceStatus(data){
	var waring = data.isalarm;//告警
	var fault = data.isfault;//故障
	var offLine = data.isoffline;//离线
	if(waring==null && fault==null && offLine==null){
		return 0;
	}
	var temp = waring+fault+offLine;
	if(temp == 3){
		return DEVICE_STATUS_WARING_FAULT_OFFLINE;
	}else if(temp == 2){
		var waring_fault = waring+fault;
		var waring_offLine = waring+offLine;
		var fault_offLine = fault+offLine;
		
		if(waring_fault == 2){
			return DEVICE_STATUS_WARING_FAULT;
		}else if(waring_offLine == 2){
			return DEVICE_STATUS_WARING_OFFLINE;
		}else if(fault_offLine == 2){
			return DEVICE_STATUS_FAULT_OFFLINE;
		}
	}else if(temp == 1){
		if(waring == 1){
			return DEVICE_STATUS_WARING;
		}else if(fault == 1){
			return DEVICE_STATUS_FAULT;
		}else if(offLine == 1){
			return DEVICE_STATUS_OFFLINE;
		}
	}else{
		return DEVICE_STATUS_NORMAL
	}
}

function initChartWidth(obj,subWidth){
	if(subWidth > 0){
		obj.width(window.parent.window.innerWidth-subWidth)
	}else{
		obj.width(window.parent.window.innerWidth-50)
	}
	
}
/**
 * 需要jquery，jspdf，html2canvas支持
 * @param btn
 * @param body
 */
function createJsPdf(body,title,height){
	     html2canvas(body, {
	         onrendered: function (canvas) {
	        	 var contentWidth = canvas.width;
	             var contentHeight = canvas.height;
	             //一页pdf显示html页面生成的canvas高度;
	             var pageHeight = contentWidth / 592.28 * 841.89;
	             //未生成pdf的html页面高度
	             var leftHeight = contentHeight;
	             //页面偏移
	             var position = 0;
	             //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
	             var imgWidth = 595.28;
	             var imgHeight = 592.28/contentWidth * contentHeight;
	             var pageData = canvas.toDataURL('image/jpeg', 1.0);
	             var pdf = new jsPDF('', 'pt', 'a4');
	             //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
	             //当内容未超过pdf一页显示的范围，无需分页
	             if (leftHeight < pageHeight) {
	             pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight );
	             } else {
	                 while(leftHeight > 0) {
	                     pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
	                     leftHeight -= pageHeight;
	                     position -= 841.89;
	                     //避免添加空白页
	                     if(leftHeight > 0) {
	                       pdf.addPage();
	                     }
	                 }
	             }
	             if(!isEmpty(title)){
	            	  pdf.save(title+"_"+getTimeStamp()+'.pdf');
	             }else{
	            	  pdf.save(getTimeStamp()+'.pdf');
	             }
	         },
	         //height: body.outerHeight()+((height && height>0)?height:0),
	         height:(body.outerWidth() / 592.28 * 841.89)*Math.ceil((body.outerHeight()/(body.outerWidth() / 592.28 * 841.89))),
	         background: "#fff",//这里给生成的图片默认背景，不然的话，如果你的html根节点没设置背景的话，会用黑色填充。
	         allowTaint: true //避免一些不识别的图片干扰，默认为false，遇到不识别的图片干扰则会停止处理html2canvas
	     });
}
/**
 * 循环方法
 * @param fn
 * @param time
 */
function refreshFn(fn,time){
	setInterval(fn, time);
}

/**
 * iframe页面跳转
 * @param pageId
 */
function goPageByPageId(pageId){
	if(pageId){
		var tem = getParentElementByClassName();
		var temObj = getParentElementById(pageId);
		$(tem).each(function(index,data){
			if($(data).hasClass("active")){
				$(data).removeClass("active");
			}
		});
		temObj.parent().addClass("active");
		var temUrl = temObj.attr("link");
		var temIconclass = temObj.find("span").attr("class");
		setSessionStorageItem(STORAGE_KEY_ACTIVETABID, temObj.attr("id"));
		//改变面包屑导航
		getParentElementById("header_breadcrumb").html('');
		var html = '';
		html += '<li class="active" style="padding-right:5px;"> <a href="javascript:;" onclick="reloadIframe(this)" url="'+temUrl+'">'+'<i class="'+temIconclass+'"></i>'+temObj.text()+'</a></li>';
		getParentElementById("header_breadcrumb").html(html);
		getParentElementById("mainWindow").attr("src",temUrl);
	}
}

//$(document).on('keydown', function (e) {
//    if(e.keyCode == 122){//切到全屏自动刷新
//    	var tempobj = getParentElementById("mainWindow");
//    	tempobj.attr("src",tempobj.attr("src"));
//    	backBreadcrumb1()
//}
//})
function warpSymbolSize(value,hold){
	if(hold && hold > 0){
		if(value > hold){
			return 8;
		}else{
			return 0;
		}
	}else{
		return 0;
	}
}

$(document).on('keydown', function (e) {
     //var e = event || window.event || arguments.callee.caller.arguments[0];
    
     if(e && e.keyCode == 122){//捕捉F11键盘动作
       e.preventDefault();  //阻止F11默认动作
       var el = document.documentElement;
       
       var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen;//定义不同浏览器的全屏API
       if (typeof rfs != "undefined" && rfs) {
             rfs.call(el);
       } else if(typeof window.ActiveXObject != "undefined"){
             var wscript = new ActiveXObject("WScript.Shell");
             if (wscript!=null) {
                 wscript.SendKeys("{F11}");
             }
       }
     }
     })
     
document.addEventListener("webkitfullscreenchange", function() {
    var tempobj = getParentElementById("mainWindow");
    tempobj.attr("src",tempobj.attr("src"));
    backBreadcrumb1()
}, false);

document.addEventListener("mozfullscreenchange", function() {
	var tempobj = getParentElementById("mainWindow");
	tempobj.attr("src",tempobj.attr("src"));
	backBreadcrumb1()
}, false);

document.addEventListener("msfullscreenchange", function() {
	var tempobj = getParentElementById("mainWindow");
	tempobj.attr("src",tempobj.attr("src"));
	backBreadcrumb1()
}, false)