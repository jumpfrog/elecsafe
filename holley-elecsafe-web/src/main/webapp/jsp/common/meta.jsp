<%@ page contentType="text/html;charset=UTF-8"  language="java" pageEncoding="UTF-8" %>
<%--获取请求路径、引入全局css样式--%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" type="text/css" href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css"  > -->
<link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/bootstrap/v3.3.6/css/bootstrap.min.css"  >
<link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/font-awesome/v4.7.0/css/font-awesome.min.css">
<%-- <link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/xcconfirm/xcConfirm.css"> --%>

<link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/bootstrap-table/bootstrap-table.min.css">

<link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/jstree/dist/themes/default/style.min.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/adminLTE/AdminLTE.min.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/adminLTE/_all-skins.min.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/res/plugin/layer/skin/default/layer.css">

<link rel="stylesheet" type="text/css" href="${webRoot}/res/css/common/common.css">
<link rel="stylesheet" type="text/css" href="${webRoot}/res/css/common/ec.css">

<link rel="shortcut icon" href="${imgUrl}res/img/frame/logo_origin.png"/>

<%@include file="common.jsp"%>

<div id="basePath" class="hide"><%=basePath%></div>
<title>智慧安全用电云平台</title>

<script type="text/javascript">
var WEB_ROOT = "${webRoot}"; 
var IMG_SRC = "${imgUrl}";
</script>


