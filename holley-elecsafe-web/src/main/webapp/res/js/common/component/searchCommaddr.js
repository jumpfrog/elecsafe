var param = {};
var loadEntAndStaTreeUrl = "comm/treeComm_loadEntAndStaTree.action";
var terminalUrl = "comm/searchComm_loadPro.action";
var deviceInfo = "record/rtuParam_queryDeviceInfo.action";

$(document).ready(function(){
	disableBack();
	//加载树
	loadEntTree();
});

/**
 * 载入企业设备树
 * @param id	
 * @param type
 * @returns
 */
function loadEntTree(id,type){
	param.nodeid = id;
	param.nodetype = type;
	$.ajax({
		type:'POST',
		url:loadEntAndStaTreeUrl,
		dataType:'json',
		data:param,
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			var treeNodes = data.treeNodes;
			$("#staTree").jstree({
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
				    "14" : {  
                        "icon" :IMG_SRC+"res/img/treeicon/station.png"  
                    } ,
				    "loading": {
                    	"icon": "res/plugin/jstree/dist/themes/default/throbber.gif"
                    }
				  },
				  "plugins" : [  "wholerow", "types"]
				}).on('loaded.jstree',function(e,data){
					//默认展开且选中第一个节点
					if(treeNodes != null && treeNodes.length > 0){
						var node = getTreeNode($("#staTree"),treeNodes[0]);
						$("#staTree").jstree("select_node",node);
					}
				});;
			//绑定树节点选中事件
			bindChangeNode($('#staTree'),onNodeChange);
			//绑定树节点点击事件
			bindActivateNode($('#staTree'),loadEntAndStaTreeUrl);
			//绑定树节点展开事件
			bindOpenNode($('#staTree'),loadEntAndStaTreeUrl);
		}
	});
}

/**
 * 树节点改变处理
 * @returns
 */
function onNodeChange(){
	  setCurrentPage(1);
      $("#nodeid").val(selectedNode.data);
      $("#nodetype").val(selectedNode.type);
      //载入点中节点上的所有设备
      queryRtu();
}

//终端主界面加载
function queryRtu(){
	param = {};
	param.nodeid = selectedNode.data;
	param.nodetype = selectedNode.type;
	param.pageIndex = $.trim($("#currentPage").val());
	param.pageLimit = PAGE_LIMIT;
	var tbody = $("#rtuTable").find("tbody");
	var html = '';
	
	$.ajax({
		type:"post",
		url:terminalUrl,
		data:param,
		dataType:'json',
		cache:false,
		beforeSend:function(){
			showLoading();
		},
		success:function(data,options){
			if(data.page){
				tbody.empty();
				var devList = data.page.root;
				$(devList).each(function(index,item){
					html += '<tr>';
					html += '<td><input onclick="onItemCheck(this)" type="checkbox" name="commaddrcheck" commaddr="'+item.commaddr+'"/></td>';
					html += '<td>'+item.entStr+'</td>';
					html += '<td>'+item.staStr+'</td>';
					html += '<td>'+item.commaddr+'</td>';
					html += '<td>'+item.proStr+'</td>';
					html += '<td><a href="javascript:;" onclick="queryDevice(this)" commaddr="'+item.commaddr+'" nodeid="'+selectedNode.data+'" nodetype="'+selectedNode.type+'" deviceNum="'+item.deviceNum+'">'+item.deviceNum+'</a></td>';
					html += '</tr>';
				});
				tbody.html(html);
				var currentPage = data.page.startRow/(data.page.endRow-data.page.startRow)+1;
				var totalPage = Math.ceil(data.page.totalProperty/(data.page.endRow-data.page.startRow));
				setPagingToolbarParams(data.page.totalProperty, totalPage, currentPage);
			}
		},
		complete:function(){
			hideLoading();
		}
	});
}

//行选中事件
function onItemCheck(obj){
	$('[name="commaddrcheck"]').each(function(index,value){
		if ($(value).get(0) != $(obj).get(0)){
			$(value).get(0).checked = false;
		}
	});
}

//终端下，设备详细信息显示
function queryDevice(obj){
	param = {};
	param.commaddr = $(obj).attr("commaddr");
	param.nodeid = $(obj).attr("nodeid");
	param.nodetype = $(obj).attr("nodetype");
	layer.open({
		  type: 1,
		  title: '设备详细',
		  zIndex:10,
		  skin: 'layui-layer-lan', //样式类名 
		  area: ['60%', '45%'], //宽高
		  offset: ['100px', '350px'],
		  resize :false,//不允许拉伸
		  content: $('#deviceInfoDiv') 
		});
	
	var deviceNum = $(obj).attr("deviceNum");
	
	if (deviceNum > 0 && deviceNum != null) {
		$("#noRecordDiv").addClass("hide");
		$("#recordDiv").removeClass("hide");
		var tbody = $("#deviceInfoTable").find("tbody");
		var html = '';
		$.ajax({
			type:"post",
			url:deviceInfo,
			data:param,
			dataType:'json',
			cache:false,
			success:function(data,options){
				if(options){
					deviceList = data.deviceList;
					$(deviceList).each(function(index,item){
						html += '<tr>';
						html += '<td>'+item.disc+'</td>';
						html += '<td>'+item.ownername+'</td>';
						html += '<td>'+item.name+'</td>';
						html += '<td>'+item.protocolname+'</td>';
						html += '<td>'+item.commsubaddr+'</td>';
						html += '</tr>';
					});
					tbody.html(html);
				}
			},
		});
	} else {
		$("#noRecordDiv").removeClass("hide");
		$("#recordDiv").addClass("hide");
	}
}