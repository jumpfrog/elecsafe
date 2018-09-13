var param = {};
var queryListURL = "sys/account_queryList.action";
var editAccountURL = "sys/accountEditInit.action";
var delAccountURL = "sys/account_delAccount.action";
var initPwdURL = "sys/account_initPwd.action";
var detailAccountURL = "sys/account_queryDetail.action";

var addBtnId = "1301";
var editBtnId = "1302";
var delBtnId = "1303";
var initPwdBtnId = "1304";
var editBtnShow = false;
var delBtnShow = false;
var initPwdBtnShow = false;
var account;
$(document).ready(function(){
	disableBack();
	//初始化列表高度
	setPageTableDivH($("#accountTableDiv"));
	
	//初始化列表内按钮是否显示
	isShowBtn();
	
	//初始化按钮工具条
	var basebtns = [{buttonid:"queryBtn",disc:"查询"},{buttonid:"exportBtn",disc:"导出"}];
	var exceptbtns = [{buttonid:editBtnId},{buttonid:delBtnId},{buttonid:initPwdBtnId}];
	var html = getModuleBtns(basebtns,exceptbtns);
	if(!isEmpty(html)){
  		$("#btnbar").append(html);
	}
	
	//初始化分页条
	initPagingToolbar(queryList);
	
	//查询按钮点击事件
	$("#queryBtn").on("click",function(){
		setCurrentPage(1);
		queryList();
	});
	
	//初始化新增按钮
	$("#buttondef_"+addBtnId).on("click",function(){
		href(editAccountURL+"?requestType="+REQUEST_TYPE_ADD);
	});
	
	//初始化导出按钮
	initExportBtn($("#exportBtn"),$("#btnbarFrom"),$("#fileName").val(),$("#accountTable"));
	
	//用户详细返回按钮
	$("#backListBtn").on("click",function(){
		queryList();
		showResult("showList");
	});
	
	queryList();
	
});

function queryList(){
	param = {};
	param.keyword = $.trim($("#keyword").val());
	param.accounttype = $("#accountType option:selected").val();
	param.pageindex = $.trim($("#currentPage").val());
	param.pagelimit = PAGE_LIMIT;
	
	var html = '';
	
	var tbody = $("#accountTable").find("tbody");
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
				var dataList = data.page.root;
				$(dataList).each(function(index,item){
					html += '<tr>';
					html += getTdHtml(item.account, 10);
					html += '<td>'+item.name+'</td>';
					html += '<td>'+item.typename+'</td>';
					html += getTdHtml(item.rolename,20);
					html += getTdHtml(item.departmentname,20);
					html += '<td>'+getNotNullData(item.telephone)+'</td>';
					html += '<td>'+getNotNullData(item.email)+'</td>';
					html += '<td>';
					html += '<div class="dropdown">';
					html += '<button type="button" class="btn btn-xs dropdown-toggle td-btn" id="dropdownMenu1" data-toggle="dropdown">';
				    html +='<span>详情</span>&nbsp&nbsp<span class="caret"></span>';
				    html += '</button>';
				    html += '<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="width:100%">';
					if(editBtnShow){
						html += '<li role="presentation">';
						html += '<a role="menuitem" tabindex="-1" href="'+editAccountURL+'?account='+item.account+'&requestType='+REQUEST_TYPE_EDIT+'">修改</a>';
						html += '</li>';
					}
					if(delBtnShow && item.account != ADMIN){
						html += '<li role="presentation">';
						html += '<a role="menuitem" tabindex="-1" href="javascript:;" onclick="showDelTip(this)" account="'+item.account+'" name="'+item.name+'">删除</a>';
						html += '</li>';
					}
					if(initPwdBtnShow && item.account != ADMIN){
						html += '<li role="presentation">';
						html += '<a role="menuitem" tabindex="-1" href="javascript:;" onclick="showInitPwdTip(this)" account="'+item.account+'" name="'+item.name+'">初始化密码</a>';
						html += '</li>';
					}
					
					html += '<li role="presentation">';
					html += '<a role="menuitem" tabindex="-1" href="javascript:;" onclick="showDetail(this)" account="'+item.account+'">详细</a>';
					html += '</li>';
					html += '</ul></div>';
					html += '</td>';
					html += '</tr>';
				});
				tbody.html(html);
				
				 var currentPage = data.page.startRow/(data.page.endRow-data.page.startRow)+1;
        		 var totalPage = Math.ceil(data.page.totalProperty/(data.page.endRow-data.page.startRow));
        		 setPagingToolbarParams(data.page.totalProperty, totalPage,currentPage);
			}else{
				layerBox.msgWarning(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
	    }
	});
}

//用电用户删除提示
function showDelTip(obj){
	account = $(obj).attr("account");
/*	var option = {
			title: "系统提示",
			btn: window.wxc.xcConfirm.btnEnum.okcancel,
			onOk: function(){
				delAccount(account);
			}
		}*/
	layerBox.msgConfirm("确定删除用户账户【"+account+":"+$(obj).attr("name")+"】吗?", function(){delAccount(account);}, true)
	//showCustom("确定删除用户账户【"+account+":"+$(obj).attr("name")+"】吗?",option);
}

//删除用电用户
function delAccount(id){
	param = {};
	if(isEmpty(id)){
		//showInfo("用户账号不能为空");
		layerBox.msgWarning("用户账号不能为空");
		return;
	}
	param.account = account;
	$.ajax({
		type:"POST",
		url:delAccountURL,
		data:param,
		dataType:"json",
		cache:false,
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				//showSuccess("用电账号删除成功");
				layerBox.msgOk("用电账号删除成功", queryList)
				//queryList();
			}else{
				//showInfo(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

//用电用户初始化密码提示
function showInitPwdTip(obj){
	account = $(obj).attr("account");
/*	var option = {
			title: "系统提示",
			btn: window.wxc.xcConfirm.btnEnum.okcancel,
			onOk: function(){
				initPwd(account);
			}
		}*/
	layerBox.msgConfirm("确定初始化用户账号【"+account+":"+$(obj).attr("name")+"】的密码吗?", function(){initPwd(account);}, true)
	//showCustom("确定初始化用户账号【"+account+":"+$(obj).attr("name")+"】的密码吗?",option);
}

//删除用电用户
function initPwd(id){
	param = {};
	if(isEmpty(id)){
		//showInfo("用户账号不能为空");
		layerBox.msgWarning("用户账号不能为空");
		return;
	}
	param.account = account;
	$.ajax({
		type:"POST",
		url:initPwdURL,
		data:param,
		dataType:"json",
		cache:false,
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				layerBox.msgOk("用户账号密码初始化成功", queryList);
				//showSuccess("用户账号密码初始化成功");
				//queryList();
			}else{
				//showInfo(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

//用户详细信息
function showDetail(obj){
	showResult("showDetail");
	
	param = {};
	account = $(obj).attr("account");
	param.account = account;
	
	$.ajax({
		type:"POST",
		url:detailAccountURL,
		data:param,
		dataType:"json",
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				var record = data.accountInfo;
				$("#detail_account").text(record.account);
				$("#detail_name").text(record.name);
				$("#detail_typename").text(record.typename);
				$("#detail_rolename").text(record.rolename);
				$("#detail_departmentname").text(record.departmentname);
				$("#detail_position").text(record.position);
				$("#detail_station").text(record.station);
				$("#detail_telephone").text(record.telephone);
				$("#detail_address").text(record.adress);
				$("#detail_creator").text(record.creator);
				$("#detail_createtimestr").text(record.creatortimestr);
			}else{
				layerBox.msgWarning(data.message);
				//showInfo(data.message);
			}
		},
		complete:function(){
	    	  hideLoading();
	    }
	});
	
}

function showResult(flag){
	if("showDetail" == flag){
		$("#accountList").addClass("hide");
		$("#accountDetail").removeClass("hide");
	}else{
		$("#accountList").removeClass("hide");
		$("#accountDetail").addClass("hide");
	}
	
}

//判断修改按钮和删除按钮是否有权限，有权限才可显示。
function isShowBtn(){
	editBtnShow = false;
	delBtnShow = false;
	initPwdBtnShow = false;
	var btnList = getButtondefsByModule();
	if(btnList == null || btnList.length == 0){
		return;
	}
	
	for(var i=0;i<btnList.length;i++){
		if(editBtnId == btnList[i].buttonid){
			editBtnShow = true;
		}else if(delBtnId == btnList[i].buttonid){
			delBtnShow = true;
		}else if(initPwdBtnId == btnList[i].buttonid){
			initPwdBtnShow = true;
		}
	}
}

