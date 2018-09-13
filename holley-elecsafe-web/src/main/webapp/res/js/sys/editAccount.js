var param = {};
var accountListURL = "sys/account.action";
var addAccountURL = "sys/account_addAccount.action";
var editAccountURL = "sys/account_editAccount.action";

$(document).ready(function(){
	//初始化系统角色下拉框
	var accounttype = $("#type option:selected").val()
	if(ACCOUNT_TYPE_PLATFORM == accounttype){
		$("#deptDiv").addClass("hide");
	}else{
		$("#deptDiv").removeClass("hide");
	}
	//queryRoleList(accounttype)
	//用户类型切换事件
	$("#type").on("change",function(){
		onTypeChange();
	});
	
	//查询按钮点击事件
	$("#saveBtn").on("click",function(){
		if(requestType == REQUEST_TYPE_ADD){
			addAccount();
		}else{
			editAccount();
		}
	});
	//取消按钮点击事件
	$("#cancelBtn").on("click",function(){
		href(accountListURL);
	});
	$("#searchEntBtn").on("click",function(){
		popupSwitchEnt();
	});
/*	//搜索企业点击事件
	$("#searchDeptBtn").on("click",function(){
		layer.open({
			  type: 1,
			  title: '请选择企业信息',
			  zIndex:10,
			  skin: 'layui-layer-lan', //样式类名 
			  area: ['48%', '78%'], //宽高
			  content: $('#searchEntModal') ,
			  end :clearEntForm()
			});
	});*/
	
	
});

function addAccount(){
	param = {};
	var accountInfo = getFormJson($("#accountForm"));
	accountInfo.departmentid = $("#switchEntBtn").attr("cur_eid");
	accountInfo.departmentname = $("#switchEntBtn").attr("cur_eidsc");
	msg = validForm(accountInfo);
	if ("success" != msg) {
		//showInfo(msg);
		layerBox.msgWarning(msg);
		return false;
	}
	param.accountInfo = objToJsonString(accountInfo);
	$('#accountForm').ajaxSubmit({
		url:addAccountURL,
		type:'post',
		dataType:'json',
		data:param,
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				//showInfo("用户新增成功");
				layerBox.msgWarning("用户新增成功");
				setTimeout(function (){
					href(accountListURL);
				}, 1000)
			}else{
				//showMsg(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

function editAccount(){
	param = {};
	var accountInfo = getFormJson($("#accountForm"));
	accountInfo.departmentid = $("#switchEntBtn").attr("cur_eid");
	accountInfo.departmentname = $("#switchEntBtn").attr("cur_eidsc");
	accountInfo.account = $("#account").val();
	msg = validForm(accountInfo);
	if ("success" != msg) {
		//showInfo(msg);
		layerBox.msgWarning(msg);
		return false;
	}
	param.accountInfo = objToJsonString(accountInfo);
	$('#accountForm').ajaxSubmit({
		url:editAccountURL,
		type:'post',
		dataType:'json',
		data:param,
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				//showInfo("用户修改成功");
				layerBox.msgWarning("用户修改成功");
				setTimeout(function (){
					href(accountListURL);
				}, 1000)
			}else{
				//showMsg(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

function onTypeChange(){
	var accounttype = $("#type option:selected").val();
	if(ACCOUNT_TYPE_PLATFORM == accounttype){
		$("#deptDiv").addClass("hide");
	}else{
		$("#deptDiv").removeClass("hide");
	}
	$("#eid").val("");
	$("#edisc").val("");
	queryRoleList(accounttype);
}

function queryRoleList(accounttype){
	  $("#roleid").empty();
	var str = "";
	$(roleListJson).each(function(index,value){
		 if(ACCOUNT_TYPE_BUSINESS == accounttype){//运营商
			//运营商等级(2)、企业管理等级(3)、企业操作等级(4)的角色列表。
				if(value.memberlevel != ROLE_PLATFORM ){
					str +="<option value="+ value.roleid+">" + value.disc + "</option>";
				}
		}else if(ACCOUNT_TYPE_ENTERPRISE == accounttype){//企业管理员
			//企业管理等级(3)、企业操作等级(4)的角色列表。
			if( value.memberlevel == ROLE_ENTERPRISE || value.memberlevel == ROLE_OPERATOR ){
				str += "<option value="+ value.roleid+">" + value.disc + "</option>";
			}
		}else if(ACCOUNT_TYPE_OPERATOR == accounttype){//操作员
			//企业操作等级(4)
			if(value.memberlevel == ROLE_OPERATOR ){
				str +="<option value="+ value.roleid+">" + value.disc + "</option>";
			}
		}else if(ACCOUNT_TYPE_PLATFORM  == accounttype){
			str +="<option value="+ value.roleid+">" + value.disc + "</option>";
		}
	});
	$("#roleid").append(str);
}
//校验表单信息
function validForm(obj){
	if(isEmpty(obj.account)){
		return "请填写用户账号";
	}else if(isEmpty(obj.name)){
		return "请填写用户名称";
	}else if(!(REQUEST_TYPE_EDIT == requestType && ADMIN == obj.account)){
		if(isEmpty(obj.type)){
			return "请选择用户类型";
		}else if(webUserType == 3 && isEmpty(obj.roleid)){
			return "请选择系统角色";
		}else if(obj.type != ACCOUNT_TYPE_PLATFORM && (isEmpty(obj.departmentid) || obj.departmentid == -1)){
			return "请选择所属企业";
		}
	}
	
	if(!isEmpty(obj.telephone) && !(REGBOX.regMobile.test(obj.telephone) || REGBOX.regTel.test(obj.telephone))){
		return "请正确输入电话/手机号码";
	}else if(!isEmpty(obj.email) && !REGBOX.regEmail.test(obj.email)){
		return "请输入正确电子邮箱";
	}
	return "success";
}



