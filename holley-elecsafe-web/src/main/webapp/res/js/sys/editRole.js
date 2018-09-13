var param={};
var roleListURL = "sys/role.action";
var addRoleURL = "sys/role_addRoel.action";
var editRoleURL = "sys/role_editRole.action";
$(document).ready(function(){
	//保存按钮
	$("#saveBtn").on('click',function(){
		if(requestType == REQUEST_TYPE_ADD){
			addRole();
		}else{
			editRole();
		}
	});
	//取消按钮
	$("#cancelBtn").on('click',function(){
		href(roleListURL)
	})
});



//新增角色信息
function addRole(){
	param={};
	var sysRole = getFormJson($("#roleForm"));
	msg = validForm(sysRole);
	if ("success" != msg) {
		//showInfo(msg);
		layerBox.msgWarning(msg);
		return false;
	}
	param.sysRole =objToJsonString(sysRole);
	$.ajax({
		type:"POST",
		url:'sys/role_addRole.action',
		data:param,
		dataType:"json",
		cache:false,
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				//showSuccess("角色新增成功");
				layerBox.msgOk("角色新增成功", function(){href(roleListURL)})
			}else{
				//showInfo(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

//修改角色信息
function editRole(){
	var sysRole = getFormJson($("#roleForm"));
	msg = validForm(sysRole);
	if ("success" != msg) {
		//showInfo(msg);
		layerBox.msgWarning(msg);
		return false;
	}
	sysRole.roleid = $("#roleid").val();
	if(ADMIN_ROLE_ID == sysRole.roleid){
		//showInfo("系统管理员角色为系统默认创建，不允许修改");
		layerBox.msgWarning("系统管理员角色为系统默认创建，不允许修改");
		return;
	}
	param.sysRole = objToJsonString(sysRole);
	$('#roleForm').ajaxSubmit({
		url:editRoleURL,
		type:'post',
		dataType:'json',
		data:param,
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				//showSuccess("角色修改成功");
				layerBox.msgOk("角色修改成功", function(){href(roleListURL);})
			}else{
				//showMsg(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

function validForm(obj){
	if(isEmpty(obj.disc)){
		return "角色名称不能为空";
	}else if(isEmpty(obj.memberlevel)){
		return "会员等级不能为空";
	}else if(isEmpty(obj.remark)){
		return "角色描述不能为空"
	}
	return "success";
}
