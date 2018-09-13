var param={};
var queryListURL = "sys/role_queryList.action";
var editRoleURL = "sys/roleEditInit.action";
var delRoleURL = "sys/role_delRole.action";
var loadPermissionURL = "sys/loadPermissionInit.action";

var addBtnId = "1305";
var editBtnId = "1306";
var delBtnId = "1307";
var perBtnId = "1308";

var editBtnShow = false;
var delBtnShow = false;
var perBtnShow = false;
var roleid;
$(document).ready(function(){
	disableBack();
	//初始化列表高度
	setTableDivH($("#roleTableDiv"));
	
	//初始化列表内按钮是否显示
	isShowBtn();
	
	//初始化按钮工具条
	var basebtns = [{buttonid:"queryBtn",disc:"查询"},{buttonid:"exportBtn",disc:"导出"}];
	var exceptbtns = [{buttonid:editBtnId},{buttonid:delBtnId},{buttonid:perBtnId}];
	var html = getModuleBtns(basebtns,exceptbtns);
	if(!isEmpty(html)){
  		$("#btnbar").append(html);
	}
	
	//查询按钮
	$("#queryBtn").on("click",function(){
		queryList();
	});
	//新增按钮
	$("#buttondef_"+addBtnId).on("click",function(){
		href(editRoleURL+"?requesttype="+REQUEST_TYPE_ADD);
	});
	initExportBtn2($("#exportBtn"),$("#btnbarFrom"),$("#fileName").val(),$("#roleTable"));
	queryList();
	
});

function queryList(){
	param = {};
	param.keyword = $.trim($("#keyword").val());
	
	var html = '';
	var tbody = $("#roleTable").find("tbody")
	$.ajax({
		type:"POST",
		url:queryListURL,
		data:param,
		dataType:"json",
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				tbody.empty();
				var dataList = data.sysRoleList;
				$(dataList).each(function(index,item){
					html += '<tr>';
					html += '<td>'+item.disc+'</td>';
					html += '<td>'+item.memberlevelname+'</td>';
					html += '<td>'+item.remark+'</td>';
					html += '<td>'+item.creator+'</td>';
					html += '<td>'+item.createtimeStr+'</td>';
					if(ADMIN_ROLE_ID == item.roleid){
						html += '<td><a class="a-disable">&nbsp暂无</a>';
						html += '</td>';
					}else{
						html += '<td>';
						html += '<div class="dropdown">';
						html += '<button type="button" class="btn btn-xs dropdown-toggle td-btn" id="dropdownMenu1" data-toggle="dropdown">';
						html +='<span>详情</span>&nbsp&nbsp<span class="caret"></span>';
						html += '</button>';
						html += '<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="width:100%">';
						if(editBtnShow){
							html += '<li role="presentation">';
							html += '<a role="menuitem" tabindex="-1" href="'+editRoleURL+'?roleid='+item.roleid+'&requesttype='+REQUEST_TYPE_EDIT+'">修改</a>';
							html += '</li>';
						}
						if(delBtnShow){
							html += '<li role="presentation">';
							html += '<a role="menuitem" tabindex="-1" href="javascript:;" onclick="showDelTip(this)" roleid="'+item.roleid+'" disc="'+item.disc+'">删除</a>';
							html += '</li>';
						}
						if(perBtnShow){
							html += '<li role="presentation">';
							html += '<a role="menuitem" tabindex="-1" href="'+loadPermissionURL+'?roleid='+item.roleid+'">分配权限</a>';
							html += '</li>';
						}
						html += '</ul></div>';
						html += '</td>';
					}
					html += '</tr>';
				});
				tbody.html(html);
			}else{
				//showInfo(data.message);
				layerBox.msgWarning(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
	    }
	});
}

//角色删除提示
function showDelTip(obj){
	roleid = $(obj).attr("roleid");
	if(ADMIN_ROLE_ID == roleid){
		//showInfo("该角色正在被使用，请确认解除占用后再进行删除");
		layerBox.msgWarning("该角色正在被使用，请确认解除占用后再进行删除");
		return;
	}
	/*var option = {
			title: "系统提示",
			btn: window.wxc.xcConfirm.btnEnum.okcancel,
			onOk: function(){
				delRole(roleid);
			}
		}*/
	layerBox.msgConfirm("确定删除角色【"+$(obj).attr("disc")+"】吗?'", function(){delRole(roleid);}, true)
	//showConfirm("确定删除角色【"+$(obj).attr("disc")+"】吗?'",option);
}


//删除角色信息
function delRole(id){
	param = {};
	if(isEmpty(id)){
		//showInfo("角色编码不能为空.");
		layerBox.msgWarning("角色编码不能为空.");
		return;
	}
	if(ADMIN_ROLE_ID == id){
		//showInfo("系统管理员角色为系统默认创建，不允许删除");
		layerBox.msgWarning("系统管理员角色为系统默认创建，不允许删除");
		return;
	}
	param.roleid = id;
	$.ajax({
		type:"POST",
		url:delRoleURL,
		data:param,
		dataType:"json",
		cache:false,
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				//showSuccess("角色删除成功");
				layerBox.msgOk("角色删除成功", queryList);
				//queryList();
			}else{
				//showMsg(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

//判断修改按钮和删除按钮是否有权限，有权限才可显示。
function isShowBtn(){
	editBtnShow = false;
	delBtnShow = false;
	perBtnShow = false;
	var btnList = getButtondefsByModule();
	if(btnList == null || btnList.length == 0){
		return;
	}
	
	for(var i=0;i<btnList.length;i++){
		if(editBtnId == btnList[i].buttonid){
			editBtnShow = true;
		}else if(delBtnId == btnList[i].buttonid){
			delBtnShow = true;
		}else if(perBtnId == btnList[i].buttonid){
			perBtnShow = true;
		}
	}
}

