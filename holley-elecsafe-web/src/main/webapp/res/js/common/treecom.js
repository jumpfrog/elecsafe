var selectedNode = null;//选中节点

/**
 * 创建企业树
 * @param obj：树容器
 * @param url：树数据请求路径
 * @param selectednode：默认选中节点
 * @param isopen：是否全部展开
 * @param fn:点击节点执行的方法
 * @returns
 */
function createTree(treeparam){
	if(!treeparam){
		return;
	}
	var obj = treeparam.obj;
	var url = treeparam.url;
	var node = treeparam.selectedNode;
	var isOpenAll = treeparam.isOpenAll;
	var fn = treeparam.fn;
	
	param = {};
	if(node != null){
		param.selectnodedata = node.data;
		param.selectnodetype = node.type;
	}
	$.ajax({
		type:'POST',
		url:url,
		dataType:'json',
		data:param,
		cache:false,
		success:function(data,options){
			if(preprocess(data))return;
			obj.jstree({
				  "core" : {
				    "animation" : 0,
				    "themes" : {  "responsive" : false   },
				   'data' :data.treeNodes,
				  },
				  "types" : {
					    "11" : {  
	                        "icon" :IMG_SRC+"res/img/treeicon/company.png"
					  },  
	                    "14" : {  
	                    	"valid_children": ["15", "16"],
	                        "icon" :IMG_SRC+"res/img/treeicon/station.png"  
	                    } ,
	                    "15": {
	                        "valid_children": [ "16"],
	                        "icon": IMG_SRC+"res/img/treeicon/station-unit.png"
	                    },
	                    "16": {
	                        "icon": IMG_SRC+"res/img/treeicon/equipment.png"
	                    },
	                    "18": {
	                    	"icon": IMG_SRC+"res/img/treeicon/environment.png"
	                    },
				  },
				  "plugins" : [  "wholerow", "types"]
				}) 
			
		}
	});
	//绑定节点点击事件
	if(fn){
		bindChangeTree(obj,fn);
	}
	
	//是否默认展开
	if(isOpenAll){
		openTreeAll(obj);
	}
}

/**
 * 绑定树节点切换事件
 * @param obj
 * @param fn
 */
function bindChangeTree(obj,fn){
	var nodeid;
	var node;
	obj.on("changed.jstree", function(e, data) {
		node = data.instance.get_node(data.selected[0]);
		if(nodeid != node.id){
			nodeid = node.id;
			selectedNode = node;
			fn();
		}
	})
}

/**
 * 树节点默认全部展开
 * @param obj
 * @returns
 */
function openTreeAll(obj){
	obj.on("loaded.jstree", function (event, data) {
		obj.jstree('open_all');
	});
}

/**
 * 判断孩子节点是否存在
 * @param cnodes
 * @param id
 * @returns {Boolean}
 */
function isExist(cnodes,id){
	if(cnodes == null || cnodes.length == 0)return false;
	var flag = false;
	$(cnodes).each(function(index,item){
		if(item == id){
			flag = true;
			return;
		}
	});
	return flag;
}
/**
 * 比较新的子节点和旧的子节点列表，旧的子节点多余将删除
 * @param oldNodes
 * @param newNodes
 * @returns
 */
function getDelNodes(oldNodes,newNodes){
	if(oldNodes == null || oldNodes.length == 0)return null;
	var flag = false;
	var delArray = [];
	$(oldNodes).each(function(index,oitem){
		flag = false;
		$(newNodes).each(function(index,nitem){
			if(oitem == nitem.id){
				flag = true;
			}
		});
		if(!flag){
			delArray.push(oitem);
		}
	});
	return delArray;
}

/**
 * 绑定改变节点事件
 * @returns
 */
function bindChangeNode(obj,fn){
	obj.on('changed.jstree',function(e,data){
		 //当前选中节点
	   	 selectedNode = data.instance.get_node(data.selected[0]);
          fn();
	 });
}

/**
 * 绑定选中节点事件
 * @returns
 */
function bindSelectNode(obj,fn){
	obj.on('select_node.jstree',function(e,data){
	   	 selectedNode = data.instance.get_node(data.selected[0]);
          fn();
	 });
}

/**
 * 绑定节点点击事件
 * @returns
 */
function bindOpenNode(obj,url){
	obj.bind("open_node.jstree", function (o,e) {
		var node = e.node;
//		selectedNode = e.node;
		param = {};
    	param.nodeid = node.data;
    	param.nodetype = node.type;
		$.ajax({
			type:'POST',
			url:url,
			dataType:'json',
			data:param,
			cache:false,
			success:function(data,options){
				var nodes = data.treeNodes;
				//新增子节点
				$(nodes).each(function(index,item){
					var temp = isExist(e.node.children,item.id);
					if(temp == false){//不存在，创建新节点
						obj.jstree("create_node",e.node,item,'last',function(event,data){
						},true);
					}else{//存在,判断是否更改节点名称
						var n = obj.jstree("get_node", item.id);
						if(n.text != item.text){
							obj.jstree('set_text', n , item.text);
						}
					}
				});
				//删除子节点
				var delarray = getDelNodes(e.node.children,nodes);
				if(delarray != null && delarray.length > 0){
					obj.jstree("delete_node",delarray);
				}
			}
		})
	});
}

/**
 * 绑定节点点击事件
 * @returns
 */
function bindActivateNode(obj,url){
	obj.bind("activate_node.jstree", function (o,e) {
//		selectedNode = e.node;
		var node = e.node;
		param = {};
    	param.nodeid = node.data;
    	param.nodetype = node.type;
		$.ajax({
			type:'POST',
			url:url,
			dataType:'json',
			data:param,
			cache:false,
			success:function(data,options){
				var nodes = data.treeNodes;
				//新增子节点
				$(nodes).each(function(index,item){
					var temp = isExist(e.node.children,item.id);
					if(temp == false){//不存在，创建新节点
						obj.jstree("create_node",e.node,item,'last',function(event,data){
						},true);
					}else{//存在,判断是否更改节点名称
						var n = obj.jstree("get_node", item.id);
						if(n.text != item.text){
							obj.jstree('set_text', n , item.text);
						}
					}
				});
				//删除子节点
				var delarray = getDelNodes(e.node.children,nodes);
				if(delarray != null && delarray.length > 0){
					obj.jstree("delete_node",delarray);
				}
			}
		})
	});
}


/**
 * 触发树节点点击事件
 * @param obj
 * @param node
 * @param fn
 * @returns
 */
function triggerActivateNode(obj,node,fn){
	if(!node)return;
	//刷新树节点和列表
	var param = {};
	param.node = node;
	obj.trigger("activate_node.jstree",param);
	
	if(fn){
		fn();
	}
}

/**
 * 触发树节点展开事件
 * @param obj
 * @param node
 * @param fn
 * @returns
 */
function triggerOpenNode(obj,node,fn){
	if(!node)return;
	//刷新树节点和列表
	var param = {};
	param.node = node;
	obj.trigger("open_node.jstree",param);
	
	if(fn){
		fn();
	}
}
/**
 * 根据id获取节点
 * @param obj
 * @param id
 * @returns
 */
function getTreeNode(obj,id){
	if(!obj || isEmpty(id))return null;
	var node = obj.jstree("get_node", id);
	return node;
}

function setNodeText(obj,node,text){
	if(node.text != text){
		obj.jstree('set_text', node ,text);
	}
}

/**
 * 刷新节点
 * @param obj
 * @param node1：旧父节点
 * @param node2:新父节点
 * @returns
 */
function refreshNodes(obj,node1,node2,selectnode){
	
	if(node1 != null){
		triggerActivateNode(obj,node1);
	}
	
	if(node2 != null){
		setTimeout(function(){
			triggerActivateNode(obj,node2);
			if(selectnode){
				obj.jstree("deselect_all");
				obj.jstree("select_node",selectnode);	
			}
		}, 500);
	}
}

