var param = {};
var returnUrl = "detector/detectorList.action";
var addDetectorUrl = "detector/detectordata_addDetector.action";
var editDetectorUrl = "detector/detectordata_editDetector.action";

$(document).ready(function(){
		$("#searchEntBtn").on("click",function(){
				popupSwitchEnt();
			});
		$("#holdParamDiv").find("input").on("input",function(){
			var temp = $(this).val();
			if(!isEmpty(temp)){
				 if(isNaN(temp) || temp > 9999.9999){
					 $(this).val("").focus();
				 }
			}
		});
	
		$("#saveBtn").on("click",function(){
			save();
		})
		$("#cancelBtn").on("click",function(){
			href(returnUrl);
		});
//		$("#type").on("change",function(){
//			$("#threeHoldDiv").find("input").val("");
//			$("#singleHoldDiv").find("input").val("");
//			initHoldPage($(this).val());
//		});
//		initHoldPage(type);
});

function initHoldPage(type){
	 if(THREE_PHASE == type){
		 isShowHold(false);
	}else{
		 isShowHold(true);
	}
}

function isShowHold(flag){
	if(flag){
		$("#threeHoldDiv").addClass("hide");
		$("#singleHoldDiv").removeClass("hide");
	}else{
		$("#singleHoldDiv").addClass("hide");
		$("#threeHoldDiv").removeClass("hide");
	}
}
function checkData(detector){
	if(isEmpty( detector.name)){
		return "监测点名称不能为空";
	}else if(isEmpty( detector.commaddr)){
		return "通讯地址不能为空";
	}else if(!detector.ownerid || detector.ownerid <= 0){
		return "请选择所属企业";
	}
	return SUCCESS;
}

function save(){
	var detector = getFormJson($("#detectorForm"));
	detector.ownerid = $("#switchEntBtn").attr("cur_eid");
	var msg  =checkData(detector);
	var url;

	if(REQUEST_TYPE_ADD == requestType){
		url = addDetectorUrl;
	}else if(REQUEST_TYPE_EDIT == requestType){
		url = editDetectorUrl;
	}else{
		errorMsg();
		return false;
	}
	if(SUCCESS == msg){
		param={};
		param.requestType = requestType;
		param.detectorJson = objToJsonString(detector);
		$.ajax({
			type:"POST",
			url:url,
			data:param,
			dataType:"json",
			cache:false,
			beforeSend:function(){
				showLoading();
			},
			complete:function(){
		    	hideLoading();
		    },
			success:function(data){
				if(preprocess(data))return;
				if(data.success){
					if(REQUEST_TYPE_ADD == param.requestType){
						layerBox.msgOk("添加成功",reload);
					}else if(REQUEST_TYPE_EDIT == param.requestType){
						layerBox.msgOk("修改成功",reload);
					}
				}else{
					layerBox.msgWarning(data.message);
				}
			}
		})
	}else{
		layerBox.msgWarning(msg);
	}
}

