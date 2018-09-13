var param = {};
var loadCityAndBusinessTreeUrl = "record/enterprise_loadCityAndBusinessTree.action";
var loadEnterpriseTreeUrl = "comm/treeComm_loadEntTree.action";
var queryEntpriseUrl = "record/enterprise_queryEnterInfo.action";
var saveEnterpriseURL = "record/enterprise_saveEnt.action";
	
var delEnterpriseURL = "record/enterprise_delEnterprise.action";

var addBtnId = "1309";
var editBtnId = "1310";
var delBtnId = "1311";

var editBtnShow = false;
var delBtnShow = false;
var isBtnList;
$(document).ready(function(){
	initLngLat($("#lngLatInfo"));
	disableBack();
	$("#enterpriseTableDiv").height(getWindowHeight()-485);
	//企业按钮
	var basebtns2;
	var html2;
	var exceptbtns2;
	//运营商和平台管理员可以进行新增
	if(webUserType != ACCOUNT_TYPE_PLATFORM && webUserType != ACCOUNT_TYPE_BUSINESS ){
		basebtns2 = [{buttonid:"saveBtn",disc:"保存"}];
		exceptbtns2 = [{buttonid:addBtnId},{buttonid:editBtnId},{buttonid:delBtnId}];
		html2 = getModuleBtns(basebtns2,exceptbtns2);
	}else{
		basebtns2 = [{buttonid:"saveBtn",disc:"保存"},{buttonid:"delBtn",disc:"删除"}];
		exceptbtns2 = [{buttonid:editBtnId},{buttonid:delBtnId},{buttonid:"delBtn",disc:"删除"}];
		html2 = getModuleBtns(basebtns2,exceptbtns2);
	}
	if(!isEmpty(html2)){
  		$("#ent_btnbar").append(html2);
	}
	//子公司按钮
	var basebtns = [{buttonid:"queryBtn",disc:"查询"},{buttonid:"exportBtn",disc:"导出"}];
	var html;
	var exceptbtns;
	if(webUserType != ACCOUNT_TYPE_PLATFORM || webUserType != ACCOUNT_TYPE_BUSINESS){
		exceptbtns = [{buttonid:addBtnId},{buttonid:editBtnId},{buttonid:delBtnId}];
		html = getModuleBtns(basebtns,exceptbtns);
	}else{
		exceptbtns = [{buttonid:addBtnId},{buttonid:editBtnId},{buttonid:delBtnId}];
		html = getModuleBtns(basebtns,exceptbtns);
	}
	if(!isEmpty(html)){
  		$("#btnbar").append(html);
	}
	//初始化列表内按钮是否显示
	isShowBtn();
	//企业帐号和操作员帐号不能新增和删除企业
	if(webUserType != ACCOUNT_TYPE_PLATFORM && webUserType != ACCOUNT_TYPE_BUSINESS){
		delBtnShow = false;
	}
	//加载行业树和区域树
	loadCityAndBusniessTree();
	
	//载入企业树
/*	var treeparam = {};
	treeparam.obj = $("#enterpriseTree");
	treeparam.url = loadEnterpriseTreeUrl;
	//treeparam.isOpenAll = true;
	treeparam.selectedNode = {data:nodeid,type:nodetype};
	treeparam.fn = queryEntInfo;
	createTree(treeparam);*/
	loadEntTree();
	//初始化子公司新增按钮
	$("#buttondef_"+addBtnId).on("click",function(){
		editEntInit(REQUEST_TYPE_ADD);
	});
	//初始化导出按钮
	initExportBtn2($("#exportBtn"),$("#btnbarFrom"),$("#fileName").val(),$("#enterpriseTable"));
	
	$("#saveBtn").on('click',function(){
		saveEnterprise();
	});
	$("#queryBtn").on('click',function(){
		queryEntInfo();
	});
	$("#delBtn").on('click',function(){
		if(!selectedNode){
			//showInfo("未选择企业");
			layerBox.msgWarning("未选择企业");
		}else{
			var objenterprise = getFormJson($("#enterpriseForm"));
			objenterprise.eid = selectedNode.data;
			showDelTip(objenterprise);
			triggerActivateNode($('#enterpriseTree'));
		}
	})
	
	//搜索企业点击事件
	$("#ent_searchDeptBtn").on("click",function(){
		popupSearchEnt();
//		
//		layer.open({
//			  type: 1,
//			  title: '请选择企业信息',
//			  zIndex:10,
//			  skin: 'layui-layer-lan', //样式类名 
//			  area: ['550px', '510px'], //宽高
//			//  offset :'50px',
//			  content: $('#searchEntModal') ,
//			  end :clearEntForm()
//			});
	});
	//清空所属企业文本框内容
	$("#ent_clearDeptBtn").on('click',function(){
		$("#eid2").val("");
		$("#edisc2").val("");
		
	})
	isBtnList = getButtondefsByModule();
	
	
	//企业类型切换事件
	$("#ent_type").on("change",function(){
		onEntTypeChange();
	});
	
});


/**
 * 载入企业设备树
 * @param id
 * @param type
 * @returns
 */
function loadEntTree(){
	$.ajax({
		type:'POST',
		url:loadEnterpriseTreeUrl,
		dataType:'json',
		data:param,
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			var treeNodes = data.treeNodes;
			$("#enterpriseTree").jstree({
				  "core" : {
				    "animation" : 0,
				    "themes" : {  "responsive" : false  },
				    'check_callback': true,  
				    'data' :treeNodes,
				  },
				  "types" : {
				    "11" : {  
                        "icon" :IMG_SRC+"res/img/treeicon/company.png"
				    },  
				    "18": {
                    	"icon": IMG_SRC+"res/img/treeicon/environment.png"
                    }, 
				    "loading": {
                    	"icon": "res/plugin/jstree/dist/themes/default/throbber.gif"
                    }
				  },
				  "plugins" : [  "wholerow", "types"]
				});
			
			//绑定树节点选中事件
			bindChangeNode($('#enterpriseTree'),onNodeChange);
			//绑定树节点点击事件
			bindActivateNode($('#enterpriseTree'),loadEnterpriseTreeUrl);
			//绑定树节点展开事件
			bindOpenNode($('#enterpriseTree'),loadEnterpriseTreeUrl);
			
		}
	});
}

/**
 * 树节点改变处理
 * @returns
 */
function onNodeChange(){
      $("#nodeid").val(selectedNode.data);
     queryEntInfo();
}


/**
 * 载入区域和行业树
 * @returns
 */
function loadCityAndBusniessTree(){
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
			$('#citycombox').combotree('loadData',citytree);
			$('#citycombox').combotree('tree').tree('collapseAll');//加载后合拢
			
			var businesstree = data.businessTree;
			$('#businesscombox').combotree('loadData',businesstree);
			$('#businesscombox').combotree('tree').tree('collapseAll');//加载后合拢
		}
	});
}

/**
 * 查询当前企业信息和子公司信息
 * @returns
 */
function queryEntInfo(){
	if(!selectedNode){
		return;
	}
	param ={};
	param.nodeid = selectedNode.data;
	$("#nodeid").val(selectedNode.data)
	var tbody = $("#enterpriseTable").find("tbody");
	var html = '';
	$.ajax({
		type:'POST',
		url:queryEntpriseUrl,
		data:param,
		dataType:'json',
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(preprocess(data))return;
			if(data.objEnterprise != null){
				$("#disc").val(data.objEnterprise.disc);
				$("#abbr").val(data.objEnterprise.abbr);
				$("#grade").val(data.objEnterprise.grade);
				$("#legalperson").val(data.objEnterprise.legalperson);
				$("#linkman").val(data.objEnterprise.linkman);
				$("#telephone").val(data.objEnterprise.telephone);
				$("#email").val(data.objEnterprise.email);
				$("#activities").val(data.objEnterprise.activities);
				$('#citycombox').combotree('setValue', data.objEnterprise.city);
				$('#businesscombox').combotree('setValue',data.objEnterprise.business);
				$("#lngLatInfo").text(getDefaultData(data.objEnterprise.longitude)+","+getDefaultData(data.objEnterprise.latitude));
				$("#lngLatMapIcon").attr("lng",data.objEnterprise.longitude);
				$("#lngLatMapIcon").attr("lat",data.objEnterprise.latitude);
				if(data.objEnterprise.ownerid != -1){
					 $("#ent_type").val(2);
					$("#entDiv").removeClass("hide");
					$("#eid").val(data.objEnterprise.ownerid);
					$("#edisc").val(data.objEnterprise.ownername);
				}else{
					 $("#ent_type").val(1);
					$("#entDiv").addClass("hide");
					$("#eid").val("");
					$("#edisc").val("");
				}
				oldownerid = data.objEnterprise.ownerid;
			}
			var dataList = data.list;
           	 $(dataList).each(function(index,item){
           		 html += '<tr>';
           		 html +='<td>'+item.disc+'</td>';
           		 html +='<td>'+item.ownername+'</td>';
           		 html += '<td>'+item.gradeStr+'</td>';
           		 html += '<td>'+item.cityname+'</td>';
           		 html += '<td>'+item.businessname+'</td>';
           		 html += '<td>'+item.linkman+'</td>';
           		 html += '<td>'+item.telephone+'</td>';
           		 if(isBtnList.length == 0){
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
            				 html += '<a role="menuitem" tabindex="-1" href="javascript:;" onclick="editEntInit('+REQUEST_TYPE_EDIT+','+item.eid+')">修改</a>';
            				 html += '</li>';
            			 }
            			 if(delBtnShow){
            				 html += '<li role="presentation">';
            				 html += '<a role="menuitem" tabindex="-1" href="javascript:;" onclick="showDelTip(this)" eid="'+item.eid+'" disc="'+item.disc+'">删除</a>';
            				 html += '</li>';
            			 }
            			 html += '</ul></div>';
            			 html += '</td>';
            		 }
				 html += '</tr>';
	           	 });
	           	 tbody.html(html);
			},
			complete:function(){
		    	  hideLoading();
		    }
		});
  }

function onEntTypeChange(){
	var enttype = $("#ent_type option:selected").val();
	if(EntTypeEnum_TOP == enttype){
		$("#entDiv").addClass("hide");
	}else{
		$("#entDiv").removeClass("hide");
	}
	$("#eid").val("");
	$("#edisc").val("");
}
function saveEnterprise(){
	if(!selectedNode){
		//showInfo("请先选择企业");
		layerBox.msgWarning("请先选择企业");
		return;
	}
	param={};
	var ent = getFormJson($("#enterpriseForm"));
	ent.longitude = $("#lngLatMapIcon").attr("lng");
	ent.latitude = $("#lngLatMapIcon").attr("lat");
	var msg = validForm(ent);
	if ("success" != msg) {
		layerBox.msgWarning(msg);
		return false;
	}
	param.objenterprise = objToJsonString(ent)
	param.eid = selectedNode.data;
	
	$.post(saveEnterpriseURL,param,function(data){
		if(preprocess(data))return;
		if(data.success){
			//showSuccess("企业信息修改成功");
			layerBox.msgOk("企业信息修改成功");
			if(selectedNode.text != ent.disc){
				$("#enterpriseTree").jstree('set_text', selectedNode , ent.disc);
			}
			if(ent.ownerid == oldownerid){
				triggerActivateNode($('#enterpriseTree'),selectedNode,queryEntInfo);
			}else if(data.objEnterprise.ownerid == -1){
				$('#enterpriseTree').jstree("delete_node",selectedNode);//删除当前节点
				$('#enterpriseTree').jstree("create_node","#",data.newSelectNode,'last',function(event,data){
				},true);
				
				$("#enterpriseTree").jstree("select_node",data.newSelectNode);
			}else{
				var nodeid1 = selectedNode;//当前节点
				var nodeid2 = ent.ownerid+"#"+ENTERPRISE;//新父节点
				var node1 = getTreeNode($("#enterpriseTree"),nodeid1);
				var node2 = getTreeNode($("#enterpriseTree"), nodeid2);
				$('#enterpriseTree').jstree("delete_node",selectedNode);
				refreshNodes($("#enterpriseTree"),node1,node2,node2);
			}
		}else{
			//showMsg(data.message);
			layerBox.msgWarning(data.message);
		}
	})
}
	
	function showDelTip(obj){
/*		var option = {
				title: "系统提示",
				btn: window.wxc.xcConfirm.btnEnum.okcancel,
				onOk: function(){
					delEnt($(obj).attr("eid"));
				}
			}*/
		//showCustom("确定删除企业【"+$(obj).attr("disc")+"】吗?",option);
		layerBox.msgConfirm("确定删除企业【"+$(obj).attr("disc")+"】吗?", function(){delEnt($(obj).attr("eid"));}, true)
	}

	//删除企业
	function delEnt(id){
		param = {};
		if(isEmpty(id)){
			//showInfo("企业id不能为空");
			layerBox.msgWarning("企业id不能为空");
			return;
		}
		param.eid = id;
		$.ajax({
			type:"POST",
			url:delEnterpriseURL,
			data:param,
			dataType:"json",
			cache:false,
			success:function(data){
				if(preprocess(data))return;
				if(data.success){
					showSuccess("企业信息删除成功");
					if(selectedNode.data ==id ){
						$('#enterpriseTree').jstree("delete_node",selectedNode);
						resetForm($("#enterpriseForm"));
					}else{
						$('#enterpriseTree').jstree("delete_node",data.newSelectNode);
					}
					triggerActivateNode($('#enterpriseTree'),selectedNode,queryEntInfo());
				}else{
					//showInfo(data.message);
					layerBox.msgWarning(data.message);
				}
			}
		});
	}

	
	//判断修改按钮和删除按钮是否有权限，有权限才可显示。
	function isShowBtn(){
		editBtnShow = false;
		delBtnShow = false;
		var btnList = getButtondefsByModule();
		if(btnList == null || btnList.length == 0){
			return;
		}
		
		for(var i=0;i<btnList.length;i++){
			if(editBtnId == btnList[i].buttonid){
				editBtnShow = true;
			}else if(delBtnId == btnList[i].buttonid){
				delBtnShow = true;
			}
		}
	}

	
	function showMap(){
	}
