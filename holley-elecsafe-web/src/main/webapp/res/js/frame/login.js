var param = {};
var changeCodeUrl =  "frame/changecode.action";
var loginUrl = "frame/login_login.action";
$(document).ready(function(){
    changeCode();
    
    document.onkeydown = function(e){
        var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
        	 $('#loginBtn').click();
         }
    }
});

function changeCode() {
    $("#code").attr("src", changeCodeUrl+"?t=" + getTimeStamp());
}

function login(){
	if(!verifyLoginForm())return;
	var param = {};
	param.loginuser = $.trim($("#loginuser").val());
	param.password = $.trim($("#password").val());
	param.verifycode = $.trim($("#verifycode").val());
	param.tm = getTimeStamp();
	
	$.ajax({
		type:"POST",
		url:loginUrl,
		data:param,
		dataType:'json',
        cache: false,
        success: function(data,options){
             if(data.success){
            	 href(MAIN_URL);
             }else{
                 $("#message").text(data.message);
                 $("#message").css("visibility",'visible');
                 changeCode();
//                 $("#password").focus();
             }
         }
     });
}

//验证信息
function verifyLoginForm(){
	//验证手机或邮箱帐号
	var mobileReg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	 var mailReg = /^(?:[a-zA-Z0-9]+[_\-\+\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\-]?)*[a-zA-Z0-9]+\.)+([a-zA-Z]{2,})+$/;
	 var loginuser = $("#loginuser").val();
	 if(loginuser==""){
		 $("#message").text('请输入用户名!');
	     $("#message").css('visibility','visible');
	     return false;
	 }else if(!((mobileReg.test(loginuser) && loginuser.length == 11) ||  loginuser.length <= 50)){//mailReg.test(loginuser) ||
		 $("#message").text('请输入正确用户名!');
	     $("#message").css('visibility','visible');
	     return false;
	 }else{
		 $("#message").css('visibility','hidden');
		 $("#user").val($.trim($("#loginuser").val()));
	 }
    
    if($("#password").val()==""){
   	 	 $("#message").text('请输入登录密码!');
	     $("#message").css('visibility','visible');
	     $("#password").focus();
       return false;
   }else{
       $("#password").val($.trim($("#password").val()));
   }
    
    var verifycode = $("#verifycode").val();
    if(verifycode=="" || verifycode.length != 4){
  	 	 $("#message").text('请输入正确的验证码!');
	     $("#message").css('visibility','visible');
	     $("#verifycode").focus();
      return false;
  }else{
      $("#verifycode").val($.trim($("#verifycode").val()));
  }
    return true
}

