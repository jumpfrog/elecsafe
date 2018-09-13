var param = {};
$(document).ready(function(){
	removeSessionStorageItem(STORAGE_KEY_ACTIVETABID);
	//安全退出按钮事件
	$("#logoutBtn").on("click",function(){
		logout();
	});
	//修改密码按钮事件
	$("#modifyPwdBtn").on("click",function(){
		resetForm($("#pwdform"));
		 layer.open({
				area: ['600px',"345px"],
				move  :false,
				resize :false,
				title:"修改密码",
				skin: 'layui-layer-lan', //样式类名 
				type: 1,
				content: $('#modifyPwdModal') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
				});
		
	});
	//一级菜单点击事件
	$(".menuview a").on("click",function(){
		$(".menuview a").parent().removeClass("active");//一级菜单全部不选中
		$(this).parent().addClass("active");//当前一级菜单选中
		var url = $(this).attr("link");
		var iconclass = $(this).find("span").attr("class");
		if(isEmpty(url)){//有下拉二级菜单的一级菜单
			//设置当前二级菜单选中
			var activetabid = getSessionStorageItem(STORAGE_KEY_ACTIVETABID);
			$(this).parent().find(".dropdown-menuview").each(function(index,item){
				if($(item).find("a").attr("id") == activetabid){
					$(item).addClass("active");
				}else{
					$(item).removeClass("active");
				}
			});
			
		}else{
//			url += url.indexOf("?") == -1 ? "?" : "&";
//			url += "activeTab=" + $(this).attr("id")+"&tmp=" + getTimeStamp();
//			setSessionStorageItem(STORAGE_KEY_ACTIVETABURL, url);
			
			setSessionStorageItem(STORAGE_KEY_ACTIVETABID, $(this).attr("id"));
			
			
			$("#mainWindow").attr("src",url);
			
			//改变面包屑导航
			$("#header_breadcrumb").html('');
			var html = '';
			html += '<li class="active" style="padding-right:5px;"> <a href="javascript:;" onclick="reloadIframe(this)" url="'+url+'">'+'<i class="'+iconclass+'"></i>'+$(this).text()+'</a></li>';
			$("#header_breadcrumb").html(html);
		}
		
	});
	//下拉菜单点击事件(系统管理/档案管理)
	$(".dropdown-menuview a").on("click",function(){
		//选中上级一级菜单
		$(this).parent().parent().parent(".menuview").addClass("active");
		
		var url = $(this).attr("link");
		if(!isEmpty(url)){
			url += url.indexOf("?") == -1 ? "?" : "&";
			url += "activeTab=" + $(this).attr("id")+"&tmp=" + getTimeStamp();
			setSessionStorageItem(STORAGE_KEY_ACTIVETABURL, url);
			
			$("#mainWindow").attr("src",url);
		}
		setSessionStorageItem(STORAGE_KEY_ACTIVETABID, $(this).attr("id"));
		//改变面包屑导航
		var topmenudisc = $(this).closest(".menuview").find(".menudisc").text();
		$("#header_breadcrumb").html('');
		var html = '';
		html += '<li class="active" style="padding-right:5px;"> '+topmenudisc+'</li>';
		html += '<li class="active"> '+$(this).text()+'</li>';
		$("#header_breadcrumb").html(html);
	});
		
});

function reloadIframe(obj){
	$("#mainWindow").attr("src",$(obj).attr("url"));
	backBreadcrumb1();
}
//function homepage(){
//	$("#mainWindow").attr("src","main/homepage.action");
//}
