var param={};
var roleListURL = "sys/role.action";
var loadPermissionURL = "sys/role_loadPermission.action";
var savePermissionURL = "sys/role_savePermission.action";

$(document).ready(function(){
	$("#treeDiv").height(getWindowHeight() - 260);
	
	$("#permissionTree").on("loaded.jstree", function (event, data) {
		// 展开所有节点
		$('#permissionTree').jstree('open_all');
	});
	
	//保存按钮
	$("#saveBtn").on('click',function(){
		savePermission();
	});
	
	//取消按钮
	$("#cancelBtn").on('click',function(){
		href(roleListURL)
	});
	
	loadPermission();
	
});

var nodeList;
function loadPermission(){
	param = {};
	param.roleid = roleid;
	
	$.ajax({
		type:"POST",
		url:loadPermissionURL,
		data:param,
		dataType:"json",
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				nodeList = data.treeNodeList;
				createTree(data.treeNodeList);
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

function savePermission(){
	param = {};
	param.roleid = roleid;
	var permission = $("#permissionTree").jstree("get_checked");
	param.permission = JSON.stringify(permission);
	
	$.ajax({
		type:"POST",
		url:savePermissionURL,
		data:param,
		dataType:"json",
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				//showSuccess("权限保存成功");
				layerBox.msgOk("权限保存成功", function(){href(roleListURL)});
				//href(roleListURL)
			}else{
				//showInfo(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

function createTree(data){
	$("#permissionTree").jstree({
		  "core" : {
		    "animation" : 0,
		    "check_callback" : true,
		    "themes" : { "stripes" : true },
		    'data' : data
		  },
		  'checkbox' : {  
              // 禁用级联选中  
              'three_state' : false,       
              'cascade' : 'down' //有三个选项，up, down, undetermined; 使用前需要先禁用three_state  
          },  
		  "types" : {
		    "module" : {
		      "valid_children" : ["default","file"]
		    },
		    "button" : {
		      "icon" : "glyphicon glyphicon-edit",
		      "valid_children" : []
		    }
		  },
		  "plugins" : [  "wholerow", "checkbox", "types"]
		});
}

$("#permissionTree").on("ready.jstree", function (event, data) {
	var nodes = $(this).jstree("get_checked");
	$(nodes).each(function(index,id){
		var checked = isChecked(id,nodeList);
		if(checked){
			$("#permissionTree").jstree("select_node",id);
		}else{
			$("#permissionTree").jstree("deselect_node",id);
		}
	});
});

function isChecked(id,list){
	var result = false;
	$(list).each(function(index,item){
		if(item.id == id && item.selected){
			result = true;
			return;
		}
		if(item.children != null && item.children.length>0){
			if(isChecked(id, item.children)){
				result = true;
				return;
			}
		}
	});
	return result;
}




