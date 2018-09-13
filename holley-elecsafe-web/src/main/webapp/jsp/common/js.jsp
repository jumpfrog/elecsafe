<%--js放在页面底部、此处是全局js--%>
<%@ page contentType="text/html;charset=UTF-8"  language="java" pageEncoding="UTF-8" %>

<!-- 
<script src="//cdn.bootcss.com/jquery/2.2.1/jquery.js" charset="UTF-8"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js" type="text/javascript"></script>
 -->

<script src="${webRoot}/res/plugin/jquery/v2.2.0/jquery-2.2.0.min.js"></script>
<script src="${webRoot}/res/plugin/bootstrap/v3.3.6/js/bootstrap.min.js"></script>

<script src="${webRoot}/res/plugin/moment/moment-with-locales.js"></script>
<script src="${webRoot}/res/plugin/bootstrap-datetimepicker/bootstrap-datetimepicker.js" type="text/javascript"></script>

<script src="${webRoot}/res/plugin/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${webRoot}/res/plugin/bootstrap-table/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>

<script src="${webRoot}/res/plugin/jquery-goup/jquery.goup.js" type="text/javascript"></script>
<script src="${webRoot}/res/plugin/jquery-form/jquery-form.js" type="text/javascript"></script>
<script src="${webRoot}/res/plugin/jstree/dist/jstree.min.js" type="text/javascript"></script>
<script src="${webRoot}/res/plugin/layer/layer.js"></script>
<script src="${webRoot}/res/plugin/echarts/v3.5.1/echarts.min.js"></script>
<script src="${webRoot}/res/plugin/echarts/echarts_theme.js"></script>
<script src="${webRoot}/res/js/common/constants.js" type="text/javascript"></script>
<script src="${webRoot}/res/js/common/enum-constants.js" type="text/javascript"></script>
<script src="${webRoot}/res/js/common/common.js" type="text/javascript"></script>
<script src="${webRoot}/res/js/common/datetime.js" type="text/javascript"></script>

<!-- Slimscroll -->
<script src="${webRoot}/res/plugin/slimScroll/jquery.slimscroll.min.js"></script>
<!-- AdminLTE App -->
<script src="${webRoot}/res/plugin/adminLTE/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${webRoot}/res/plugin/adminLTE/demo.js"></script>

<!-- 在IE下测试，placeholder不被支持,所以引入jquery.placeholder.js，并加入以下代码 -->
<!--[if lte IE 9]>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<script src="//cdn.bootcss.com/html5shiv/r29/html5.js"></script>
<script type="text/javascript">
    
</script>
<![endif]-->


<script type="text/javascript">
	//禁止浏览器后退
	window.history.forward(0);
/**
 * 根据当前功能模块获取操作按钮权限
 */
function getButtondefsByModule(){
	var buttonList = ${buttondefs};
	var activeTab = getSessionStorageItem(STORAGE_KEY_ACTIVETABID);
	var moduleBtns = [];
	if(buttonList != null && buttonList.length > 0 && !isEmpty(activeTab)){
		$(buttonList).each(function(index,item){
			if(activeTab == item.moduleid){
				moduleBtns.push(item);
			}
		});
	}
	return moduleBtns;
}

/**
 * 根据当前功能模块的操作按钮权限创建按钮组件
 * basebtns：基本按钮数组，如查询、导出等，格式为[{buttonid:"queryBtn",disc:"查询"},{buttonid:"exportBtn",disc:"导出"}]；
 * exceptbtns：不显示的按钮编码数组，显示格式为[{buttonid:"1"},{buttonid:"2"}]
 */
function getModuleBtns(basebtns,exceptbtns){
	var html = '';
	var buttonList = getButtondefsByModule();
	if(buttonList != null && buttonList.length > 0){
		$(buttonList).each(function(index,item){
			if(!isExistBtn(item.buttonid,exceptbtns)){
				html += '<button type="button" id="buttondef_'+item.buttonid+'" class="btn btn-primary btn-sm">'+item.disc+'</button>\n';
			}
		});
	}
	if(basebtns != null && basebtns.length > 0){
		$(basebtns).each(function(index,item){
			html += '<button type="button" id="'+item.buttonid+'" class="btn btn-primary btn-sm">'+item.disc+'</button>\n';
		});
	}
	return html;
}

/**
 * 根据按钮编码判断该功能是否拥有该按钮的权限
 */
function isShowBtn(buttonid){
	var result = false;
	var buttonList = getButtondefsByModule();
	if(buttonList == null || buttonList.length == 0){
		return result;
	}
	$(buttonList).each(function(index,item){
		if(item.buttonid == buttonid){
			result = true;
			return false;
		}
	});
	return result;
}

</script>

