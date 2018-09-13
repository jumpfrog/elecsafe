<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
  <meta charset="utf-8">
  <%@include file="../common/meta.jsp"%>
  <link rel="stylesheet" href="res/css/frame/main.css" >
  <title>智慧安全用电云平台</title>
</head>
<body class="hold-transition skin-blue sidebar-mini" style="overflow-y:hidden;">
<div class="wrapper">
<%@include file="../common/header.jsp"%>
<%-- <%@include file="../common/left.jsp"%> --%>
  <!-- Content Wrapper. Contains page content -->
    <!-- Content Header (Page header) -->
 	 <div id="contentWrapper" class="content-wrapper" style="margin-left: 0">
 	 	<div style="padding-bottom: 0;height: 36px;background-color: #f5f5f5">
			<ol id="header_breadcrumb"  class="breadcrumb col-sm-6" style="margin-left: 0">
		       <li class="active"><a href="javascript:;" onclick="reloadIframe(this)" url="main/homepage.action"><i class="fa fa-fw fa-home"></i>首页</a></li>
		     </ol>
			<div class="col-sm-6 text-right">
				<!-- <div class="breadcrumb col-sm-8">
					<i class="fa fa-bell-o" style="color:orange;margin-top:5px" ><a>&nbsp;今日设备告警&nbsp;</a><a style="cursor: pointer;color:orange; " id="faultCount" onclick="showDetail()"></a></i><a>&nbsp;条</a>
				</div> -->
			    <iframe allowtransparency="true" frameborder="0" width="180" height="36" scrolling="no" src="//tianqi.2345.com/plugin/widget/index.htm?s=3&z=2&t=0&v=0&d=1&bd=0&k=&f=808080&q=1&e=0&a=1&c=58457&w=180&h=36&align=left"></iframe>
		    </div>
		<hr class="col-sm-12">
		</div>
	    <iframe id="mainWindow" src="main/homepage.action" allowtransparency="true" style="background-color:transparent;" title="test" frameborder="0" width="100%" height="100%" scrolling="no">
		</iframe>
	</div> 
	
<div class="modal fade" id="searchDeviceFaultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">设备故障列表</h4>
         </div>
         <div class="modal-body">
			<div id="deviceTableDiv" style="height: 250px;overflow-y: auto;">
			    <table class="table table-condensed table-striped table-hover table-bordered" id="deviceTable">
			   		<thead class="table-header">
			   			<tr>
			   				<th>所属电站</th>
			   				<th>设备名称</th>
			   				<th>设备类型</th>
			   				<th>告警时间</th>
			   				<th>状态</th>
			   			</tr>
			   		</thead>
				   	<tbody>
				   	</tbody>
				</table>
		    </div>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</div>
</body>
<%@include file="../common/outjs.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/common/header.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/res/js/common/component/modifyPwd.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		/*  document.onkeydown = keydown;
		  function keydown(){
		   var keycode = event.keyCode;
		   if(keycode == 122) {
				$("#mainWindow").attr("src",$("#mainWindow").attr("src"));
		   }
		  } */
		
		
		 $("#mainWindow").load(function(){
			 var contentH = getViewSize().height - 50;
			 $("#contentWrapper").height(contentH);
			 $("#contentWrapper").css("min-height",contentH);
			 $("#contentWrapper").minHeight = contentH;
			 var iframeH = contentH-39;
			 var contentDiv = $("#mainWindow").contents().find("#mainContent");
				if(contentDiv != null){
					contentDiv.height(iframeH-30);
					contentDiv.css("overflow-y","auto");
				}
			});
	});
	var deviceFaultCount ="<s:property value='#request.deviceFaultCount'/>";
	$("#faultCount").text(deviceFaultCount);
	function showDetail(){
		$("#searchDeviceFaultModal").modal('show');
		var tbody = $("#deviceTableDiv").find("tbody");
		var html = '';
		$.ajax({
				type:"post",
				url:'main/main_queryDeviceFault.action',
				data:param,
				dataType:'json',
				cache:false,
				success:function(data,options){
				if(preprocess2(data))return;
				if(data.success){
					tbody.empty();
					var dataList = data.list;
					 $(dataList).each(function(index,item){	
						 html += '<tr>';
						 html += '<td>'+item.stationName+'</td>';
						 html += '<td>'+item.devname+'</td>';
						 html += '<td>'+item.typeName+'</td>';
						 html += '<td>'+item.datatimestr+'</td>';
						 html += '<td>'+item.runstatusname+'</td>';
	            		 html += '</tr>';
					 });
					 tbody.html(html);				 
				}
			}
		});
	}
</script>
</html>