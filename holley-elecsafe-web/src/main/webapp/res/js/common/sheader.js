var param = {};
$(document).ready(function(){
	removeSessionStorageItem(STORAGE_KEY_ACTIVETABID);
	//安全退出按钮事件
	$("#logoutBtn").on("click",function(){
		logout();
	});
	//修改密码按钮事件
	$("#modifyPwdBtn").on("click",function(){
		$("#modifyPwdModal").modal().css({
			'margin-top': function () {
				return '10%';
			}
		});
		resetForm($("#pwdform"));
	});
		
});


