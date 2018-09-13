var param = {};
var modifyPwdUrl = "main/main_modifyPwd.action";
$(document).ready(function(){
	$("#savePwdBtn").on("click",function(){
		savePwd();
	});
});
//保存密码
function savePwd(){
	if(!verifyChangePwdForm())return;
	var param = {};
	param.oldpwd =  $.trim($("#oldpwd").val());
	param.newpwd = $.trim($("#newpwd").val());
	param.repeatpwd = $.trim($("#repeatpwd").val());
	$.post(modifyPwdUrl,param,function(data){
		if(preprocess2(data))return;
		if(data.success){
//			$("#modifyPwdModal").modal("hide");
//			var opt={};
//			opt.onOk = function(){
//				logout();
//			}
//			opt.onClose = opt.onOk;
//			showInfo("密码修改成功,请重新登录!",opt);
			
			layerBox.msgOk("密码修改成功,请重新登录!", logout);
			
		}else{
			//showInfo(data.message);
			layerBox.msgWarning(data.message);
		}
	});
}
	
//验证密码
function ispassword(item){
    var passreg = /^[a-zA-Z0-9]{6,16}$/;
    if($(item).val()==""){
    	$(item).next().removeClass("hide");
    }else if(!passreg.test($(item).val())){
    	$(item).next().removeClass("hide");
    }else{
    	$(item).next().addClass("hide");
    	$(item).val($.trim($(item).val()));
    }
}
	
//验证表单信息
function verifyChangePwdForm(){
    var passreg = /^[a-zA-Z0-9]{6,16}$/;
    if($("#oldpwd").val()=="" || !passreg.test($("#oldpwd").val())){
        $("#oldpwd").next().removeClass("hide");
        return false;
    }else{
    	$("#oldpwd").next().addClass("hide");
        $("#oldpwd").val($.trim($("#oldpwd").val()));
    }
    
    if($("#newpwd").val()=="" || !passreg.test($("#newpwd").val())){
        $("#newpwd").next().removeClass("hide");
        return false;
    }else{
    	$("#newpwd").next().addClass("hide");
        $("#newpwd").val($.trim($("#newpwd").val()));
    }
    
    if($("#repeatpwd").val()==""){
    	$("#repeatpwdmsg").text("请输入6-16位数字字母组合的密码!");
        $("#repeatpwd").next().removeClass("hide");
        return false;
    }else if($("#newpwd").val()!=$("#repeatpwd").val()){
    	$("#repeatpwdmsg").text("确认密码输入不一致!");
        $("#repeatpwd").next().removeClass("hide");
        return false;
    }else{
    	 $("#repeatpwd").next().addClass("hide");
         $("#repeatpwd").val($.trim($("#repeatpwd").val()));
    }
    return true
}