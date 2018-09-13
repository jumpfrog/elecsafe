var param = {};
var enterpriseURL = "record/enterprise.action";
var editEnterpriseURL = "record/enterprise_editInit.action";
var addEntURL = "record/enterprise_addEnterprise.action";
var editEntURL = "record/enterprise_modifyEnterprise.action";
var selectedNode;
var requestType;
var oldownerid;
$(document).ready(function(){
	initLngLat2($("#lngLatInfo2"));
	$("#ent_saveBtn").on("click",function(){
		if(requestType == REQUEST_TYPE_ADD){
			addEnterprise();
		}else{
			editEnterprise();
		}
	});
	//取消按钮点击事件
	$("#ent_cancelBtn").on("click",function(){
		$("#entMain").removeClass("hide");
		$("#entEditForm").addClass("hide");
	});
	
	loadCityAndBusinessTree();
	//搜索企业点击事件
	$("#ent_searchDeptBtn2").on("click",function(){
		popupSearchEnt2();
/*		layer.open({
			  type: 1,
			  title: '请选择企业信息',
			  zIndex:10,
			  skin: 'layui-layer-lan', //样式类名 
			  area: ['40%', '78%'], //宽高
			//  offset :'50px',
			  content: $('#searchEntModal2') ,
			  end :clearEntForm2()
			});*/
	});
	
	//清空所属企业文本框内容
	$("#ent_clearDeptBtn2").on('click',function(){
		$("#eid2").val("");
		$("#edisc2").val("");
		
	})
	//返回图标点击事件
	$("#ent_backBtn").on('click',function(){
		$("#entMain").removeClass("hide");
		$("#entEditForm").addClass("hide");
	})
});

function loadCityAndBusinessTree(){
	param={};
	param.tm = new Date().getTime();
	$.ajax({
		type:'POST',
		url:loadCityAndBusinessTreeUrl,
		data:param,
		dataType:'json',
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			var citytree = data.cityTree;
			$('#edit_citycombox').combotree('loadData',citytree);
			$('#edit_citycombox').combotree('tree').tree('collapseAll');//加载后合拢
			var businesstree = data.businessTree;
			$('#edit_businesscombox').combotree('loadData',businesstree);
			$('#edit_businesscombox').combotree('tree').tree('collapseAll');//加载后合
			if(requestType == REQUEST_TYPE_EDIT){
				$('#edit_citycombox').combotree('setValue', city);
				$('#edit_businesscombox').combotree('setValue', business);
			}
		}
	});
}


/**
 * 新增/修改企业初始化
 * @param entid
 * @returns
 */
function editEntInit(reqType,eid){
	if(webUserType != ACCOUNT_TYPE_PLATFORM){
		if(!selectedNode){
			//showInfo("请先选择企业");
			layerBox.msgWarning("请先选择企业");
			return;
		}
	}
	requestType = reqType;
	param = {};
	if(selectedNode){
		param.ownerid = selectedNode.data;
	}
	param.requestType = requestType;
	if(requestType == REQUEST_TYPE_EDIT){
		if(eid){
			param.eid = eid;
		}else{
			//showInfo("参数非法");
			errorMsg();
			return;
		}
	}
	
	$.ajax({
		type:'POST',
		url:editEnterpriseURL,
		dataType:'json',
		data:param,
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		complete:function(){
	    	hideLoading();
	    },
		success:function(data,options){
			if(preprocess(data))return;
			if(data.success){
				var record = data.objEnterprise;
				$("#ent_eid").val(record.eid);
				if(record.ownerid == -1){
					$('#show').hide();
				}else{
					$('#show').show();
				}
				$("#eid2").val(record.ownerid);
				$("#edisc2").val(record.ownername);
				$("#edit_disc").val(record.disc);
				$("#edit_grade").val(record.grade);
				$("#edit_legalperson").val(record.legalperson);
				$("#edit_linkman").val(record.linkman);
				$("#edit_telephone").val(record.telephone);
				$("#edit_email").val(record.email);
				$("#edit_abbr").val(record.abbr);
				$("#edit_activities").val(record.activities);
				$('#edit_citycombox').combotree('setValue', record.city);
				$('#edit_businesscombox').combotree('setValue',record.business);
				$("#lngLatInfo2").text(getDefaultData(data.objEnterprise.longitude)+","+getDefaultData(data.objEnterprise.latitude));
				$("#lngLatMapIcon2").attr("lng",data.objEnterprise.longitude);
				$("#lngLatMapIcon2").attr("lat",data.objEnterprise.latitude);
				$("#entMain").addClass("hide");
				$("#entEditForm").removeClass("hide");
				
				oldownerid = record.ownerid;
			}else{
				//showInfo(data.message);
				layerBox.msgWarning(data.message);
			}
			
		}
	});
}


function addEnterprise(){
	param = {};
	var enterprise = getFormJson($("#entForm"));
	enterprise.longitude = $("#lngLatMapIcon2").attr("lng");
	enterprise.latitude = $("#lngLatMapIcon2").attr("lat");
	msg = validForm(enterprise);
	if ("success" != msg) {
		//showInfo(msg);
		layerBox.msgWarning(msg);
		return false;
	}
	param.enterprise = objToJsonString(enterprise);
	if(!isEmpty(selectedNode)){
		param.selectedNode = selectedNode.data;
	}
	$('#entForm').ajaxSubmit({
		url:addEntURL,
		type:'post',
		dataType:'json',
		data:param,
		beforeSend:function(){
			showLoading();
		},
		complete:function(){
	    	hideLoading();
	    },
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				//showInfo("企业新增成功");
				layerBox.msgOk("企业新增成功");
				$("#entMain").removeClass("hide");
				$("#entEditForm").addClass("hide");
				//有选中节点
				if(!isEmpty(selectedNode)){
					if(data.objEnterprise.ownerid == -1){//如果新增的企业是顶级企业，将新增企业加在最后
//					    $('#enterpriseTree').jstree("deselect_node",selectedNode);
						$('#enterpriseTree').jstree("create_node","#",data.treeNode,'last',function(event,data){
						},true);
//						$("#enterpriseTree").jstree("select_node",data.treeNode);
					}else{
						if(data.objEnterprise.ownerid != selectedNode.data){//如果新增企业的父Id不等于所选中的企业id,去除选中的企业，将新增企业选中
							//刷新列表和树节点数据
							triggerActivateNode($('#enterpriseTree'),selectedNode,queryEntInfo);
							$('#enterpriseTree').jstree("deselect_node",selectedNode);
							$("#enterpriseTree").jstree("select_node",data.newSelectNode)
						
						}else{//如果新增的企业父Id等于所选中的企业也id,刷新树节点
							triggerActivateNode($('#enterpriseTree'),selectedNode,queryEntInfo);
							$("#enterpriseTree").jstree("select_node",data.selectedNode);
						}
					}
				}else{//未选中节点
					if(data.objEnterprise.ownerid != -1){
						//刷新列表和树节点数据
						$("#enterpriseTree").jstree("select_node",data.newSelectNode)
					}else{
						$('#enterpriseTree').jstree("create_node","#",data.treeNode,'last',function(event,data){
						},true);
						$("#enterpriseTree").jstree("select_node",data.treeNode);
					}
				}
			}else{	
				//showMsg(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

function editEnterprise(){
	if(ownerid != null){
		onTypeChange();
	}
	param = {};
	var enterprise = getFormJson($("#entForm"));
	enterprise.eid = $("#ent_eid").val();
	enterprise.longitude = $("#lngLatMapIcon2").attr("lng");
	enterprise.latitude = $("#lngLatMapIcon2").attr("lat");
	msg = validForm(enterprise);
	if ("success" != msg) {
		//showInfo(msg);
		layerBox.msgWarning(msg);
		return false;
	}
	param.enterprise = objToJsonString(enterprise);
	$('#entForm').ajaxSubmit({
		url:editEntURL,
		type:'post',
		dataType:'json',
		data:param,
		beforeSend:function(){
	        	showLoading();
	        },
	    complete:function(){
	        	hideLoading();
	        },
		success:function(data){
			if(preprocess(data))return;
			if(data.success){
				//showInfo("修改企业成功");
				layerBox.msgOk("修改企业成功");
				$("#entMain").removeClass("hide");
				$("#entEditForm").addClass("hide");
				//刷新列表和树节点数据
				if(enterprise.ownerid == oldownerid){
					triggerActivateNode($('#enterpriseTree'),selectedNode,queryEntInfo);
				}else if(data.objEnterprise.ownerid == -1){
					$('#enterpriseTree').jstree("create_node","#",data.newSelectNode,'last',function(event,data){
					},true);
					$('#enterpriseTree').jstree("deselect_node",selectedNode);
					$("#enterpriseTree").jstree("select_node",data.newSelectNode);
				}else{
					var nodeid1 = selectedNode;//当前节点
					var nodeid2 = enterprise.ownerid+"#"+ENTERPRISE;//新父节点
					var node1 = getTreeNode($("#enterpriseTree"),nodeid1);
					var node2 = getTreeNode($("#enterpriseTree"), nodeid2);
					
					refreshNodes($("#enterpriseTree"),node1,node2,node2);
				}
			}else{
				//showMsg(data.message);
				layerBox.msgWarning(data.message);
			}
		}
	});
}

function onTypeChange(){
	if(ownerid == null){
		$("#deptDiv").addClass("hide");
		$("#eid").val("");
		$("#edisc").val("");
	}else{
		$("#deptDiv").removeClass("hide");
	}
}

//校验企业信息
function validForm(obj){
	if(isEmpty(obj.disc)){
		return "企业名称不能为空";
	}else if(isEmpty(obj.abbr)){
		return "企业名称简称不能为空";
	}else if(isEmpty(obj.city)){
		return "所属地区不能为空";
	}else if(isEmpty(obj.business)){
		return "所属行业不能为空";
	}else if(isEmpty(obj.linkman)){
		return "联系人不能为空";
	}else if(isEmpty(obj.telephone)){
		return "联系电话不能为空";
	}else if(!isEmpty(obj.telephone) && !(REGBOX.regMobile.test(obj.telephone) || REGBOX.regTel.test(obj.telephone))){
		return "请正确输入电话/手机号码";
	}else if(!isEmpty(obj.email) && !REGBOX.regEmail.test(obj.email)){
		return "请正确输入电子邮箱";
	}else if(isEmpty(obj.longitude) || isEmpty(obj.latitude)){
		return "请选择经纬度";
	}
	return "success";
}


