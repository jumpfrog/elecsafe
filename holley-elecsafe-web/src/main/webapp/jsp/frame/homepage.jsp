<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN">
<head>
  <%@include file="../common/meta.jsp"%>
  <link rel="stylesheet" type="text/css" href="res/css/frame/homepage.css">
  <link rel="stylesheet" type="text/css" href="res/plugin/numberAnimate/numberAnimate.css">
<style type="text/css">
.box-myshadow{
	box-shadow:0 1px 55px rgba(0,0,0,0.15);
}
.boxStyle{
	width: 55px;
	cursor: pointer;
}
.fontColor{
	color:#00c0ef;
}
/* .anchorBL{
	display:none;
} */
</style>
</head>
<!--首页-->
<body>
  <div id="mainContent" class="content">
  
  
	<!-- 企业总览 -->
  	<div class="row">
        <div class="col-sm-3 col-xs-6">
          <div class="small-box bg-aqua" onclick="goPageByPageId('53000000')" style="cursor: pointer;">
            <div class="inner">
              <h3>${countdata.detCount}</h3>
              <p>监测点总数（个）</p>
            </div>
            <div class="icon">
              <i class="fa fa-fw fa-cubes"></i>
            </div>
            <a href="javascript:;" class="small-box-footer">&nbsp</a>
          </div>
        </div>
        <div class="col-sm-3 col-xs-6">
          <div class="small-box bg-red" onclick="goPageByPageId('55000000')" style="cursor: pointer;">
            <div class="inner">
              <h3><a href="javascript:;" style="color: white;" id="testnum">${countdata.fault}</a></h3>
              <p>故障数量（个）</p>
            </div>
            <div class="icon">
              <i class="fa fa-fw fa-question"></i>
            </div>
            <a href="javascript:;" class="small-box-footer">&nbsp</a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-sm-3 col-xs-6">
          <div class="small-box bg-yellow" onclick="goPageByPageId('55000000')" style="cursor: pointer;">
            <div class="inner">
              <h3>
              <a href="javascript:;" style="color: white;">${countdata.waring}</a>
              </h3>
              <p>告警数量（个）</p>
            </div>
            <div class="icon">
            	<i class="fa fa-fw fa-bell-o"></i>
            </div>
            <a href="javascript:;" class="small-box-footer">&nbsp</a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-sm-3 col-xs-6">
          <div class="small-box bg-gray">
            <div class="inner">
              <h3><a href="javascript:;"  style="color: white;">${countdata.offLine}</a></h3>
              <p style="color: #fff;">离线数量（个）</p>
            </div>
            <div class="icon">
              <i class="fa fa-fw fa-plug"></i>
            </div>
            <a href="javascript:;" class="small-box-footer">&nbsp</a>
          </div>
        </div>
      </div>
      <div class="row">
      <div class="col-sm-6 col-md-6">
       <div class="row">
       <div class="col-sm-12 col-md-12">
      	<div class="box box-default box-myshadow" style="border-top-color:#fff;height: 30%;overflow: hidden;min-height: 195;margin-bottom: 15">
            <div class="box-header ">
              <h3 class="box-title fontColor fa fa-home"> 企业信息</h3>
            </div>
            <div class="box-body">
            
                <form class="form-horizontal" role="form">
                <div class="form-group">
				   		<label class="col-sm-2 control-label">企业名称：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
					      		 <s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.disc == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:elseif test="#request.enterprise.disc == ''">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.disc}
					      		 </s:else>
				      		 </p>
				        </div>
				        <label class="col-sm-2 control-label">企业简称：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
					      		 <s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.abbr == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:elseif test="#request.enterprise.abbr == ''">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.abbr}
					      		 </s:else>
				      		 </p>
				        </div>
			   	</div>
			   	
			   	 <div class="form-group">
				   		<label class="col-sm-2 control-label">所属地区：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
				      		 	 <s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.city == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.city}
					      		 </s:else>
				      		 </p>
				        </div>
				        <label class="col-sm-2 control-label">所属产业：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
				      		  	 <s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.business == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.businessname}
					      		 </s:else>
				      		 </p>
				        </div>
			   	</div>
			   	
			   	 <div class="form-group">
				   		<label class="col-sm-2 control-label">联系人：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
				      		  	<s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.linkman == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:elseif test="#request.enterprise.linkman == ''">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.linkman}
					      		 </s:else>
				      		 </p>
				        </div>
				        <label class="col-sm-2 control-label">联系电话：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
				      		 	<s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.telephone == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:elseif test="#request.enterprise.telephone == ''">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.telephone}
					      		 </s:else>
				      		 </p>
				        </div>
			   	</div>
			   	
			   	<div class="form-group">
				   		<label class="col-sm-2 control-label">电子邮箱：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
				      		 	 <s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.email == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:elseif test="#request.enterprise.email == ''">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.email}
					      		 </s:else>
				      		 </p>
				        </div>
				        <label class="col-sm-2 control-label">企业类别：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
				      		 	 <s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.grade == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.gradeName}
					      		 </s:else>
				      		 </p>
				        </div>
			   	</div>
			   	
			<%--    	<div class="form-group">
				   		<label class="col-sm-2 control-label">主要经营活动：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
				      		     <s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.activities == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:elseif test="#request.enterprise.activities == ''">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.activities}
					      		 </s:else>
				      		 </p>
				        </div>
				        <label class="col-sm-2 control-label">法人代表：</label>
				       	<div class="col-sm-4">
				      		 <p class="form-control-static">
				      			 <s:if test="#request.enterprise == null">
					      		 	-
					      		 </s:if>
					      		 <s:elseif test="#request.enterprise.legalperson == null">
					      		 	-
					      		 </s:elseif>
					      		 <s:elseif test="#request.enterprise.legalperson == ''">
					      		 	-
					      		 </s:elseif>
					      		 <s:else>
					      		  	${enterprise.legalperson}
					      		 </s:else>
				      		 </p>
				        </div>
			   	</div> --%>
                </form>
                
            </div>
        </div>
        </div>
       
	        <div class="col-sm-6 col-md-6">
	        	<div class="box box-default box-myshadow overlay" style="border-top-color:#fff;height:45%;overflow: hidden;">
		            <div class="box-header with-border">
		              <h3 class="box-title fontColor fa fa-bell-o"> 告警信息</h3>
		              
		               <h3 id="refreshBtn" onclick="queryAlarm();" data-toggle="tooltip" data-placement="left" data-original-title="刷新"  class="pull-right fontColor fa fa-refresh" style="cursor: pointer;"></h3>
		            </div>
		             <div class="box-body" style="display: block;height: 112">
		             <ul id="newAlarm" class="products-list product-list-in-box list-group">
		             </ul>
		             </div>
		             
		             <div class="box box-info" style="border-top-color: rgba(0,0,0,0.18);height:60%;min-height: 172">
		            <div id="alarmBox" class="box-body" style="display: block;">
		            </div>
		            </div>
		        </div>
	        </div>
	        <div class="col-sm-6 col-md-6" style="padding-left: 0;">
		      	<div class="box box-default box-myshadow" style="border-top-color:#fff;height: 45%;overflow: hidden;">
		            <div class="box-header with-border">
		              <h3 class="box-title fontColor fa fa-line-chart"> ${year}年服务数据</h3>
		            </div>
		            <div class="box-body" style="display: block;">
		            	<div class="row">
		             		<div class="col-sm-12 text-center" style="font-family: fantasy;">
		            		<i>事件处理 &nbsp;<span style="font-size: 18;font-family: -webkit-pictograph;">${countService}</span> 次<i>
		            		</div> 
		            		<div class="col-sm-12" style="height: 73%" id="homePageChartDiv">
			            		<div id="countChartDiv" style="height: 85%;margin-left: -100px;">
			            		
			            		</div>
		            		</div>
		            	</div>
		            </div>
		        </div>
	        </div>
        </div>
      </div>
   
      <!-- 地图 -->
      <div class="col-sm-6 col-md-6">
	     <div id="mapDiv"  style="height:77%;overflow: hidden;" class="box-myshadow"></div>
	  </div>
	  </div>
  </div>
  <%@include file="../common/js.jsp" %>
   <script type="text/javascript" src="res/plugin/numberAnimate/numberAnimate.js"></script>
  <script type="text/javascript">

  
  var homePageContData = '${homePageCountJson}';
  homePageContData = JSON.parse(homePageContData);
  var entListData = '${entListJson}';
  entListData = JSON.parse(entListData);
 // var countService = '${countService}';
  </script>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ag2fYhtdY5BgQWqi6Xwsx0Pp"></script>
  <script type="text/javascript" src="res/js/frame/homepage.js"></script>
  <script type="text/javascript" src="res/js/frame/homepageGis.js"></script>
  <script type="text/javascript" src="res/js/frame/homepageChart.js"></script>
</body>
</html>

