<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8" %>
<%@ include file="../common/top.jsp" %>
<html lang="zh-CN" style="overflow: hidden;">
<head>
  <%@include file="../common/meta.jsp"%>
  <link rel="stylesheet" href="res/css/frame/login.css" >
<style type="text/css">

</style>
</head>
<body>
  <!--main-content-->
  <div class="container">
  	<div class="row">
  		<div class="col-sm-12" style=" margin-top: 15%;">
			<div class="login-page login-logo pull-right" >
	 			<div class="form-group col-sm-12"><span class="login-txt">登&nbsp录</span></div>
        		<p class="login-tip" id="message"></p>
	        	<form class="form-horizontal" role="form" action="">
					<div class="form-group has-feedback">
						<label for="loginuser" class="sr-only">帐号</label>
						<div class="col-sm-12">
							<span class="glyphicon glyphicon-user form-control-feedback"></span>
							<input type="text" class="form-control input-lg" name="loginuser" id="loginuser"
								placeholder="用户名" maxlength="25">
						</div>
					</div>
					<div class="form-group has-feedback">
						<label for="password" class="sr-only">密码</label>
						<div class="col-sm-12">
							<span class="glyphicon glyphicon-lock form-control-feedback"></span>
							<input type="password" class="form-control input-lg" name="password"
								id="password" placeholder="密码" maxlength="16">
						</div>
					</div>
					<div class="form-group has-feedback">
						<label for="verifycode" class="sr-only">验证码</label>
						<div class="col-sm-12">
							<span class="glyphicon glyphicon-barcode form-control-feedback"></span>
							<input class="form-control input-lg" style="width: 165px; float: left;"
								id="verifycode" name="verifycode" type="text" placeholder="验证码" maxlength="4"/>
							<img style="width: 90px; float: left; height: 46px; margin-left: 10px;"
								id="code" onclick="javascript:changeCode();" src="">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button type="button" id="loginBtn" onclick="login();" class="btn login-btn btn-lg" style="width: 100%">登录</button>
						</div>
					</div>
				</form>
			  </div>
	        </div>
  		</div>
  	</div>
  	<div class="login-logo-txt">
 			<img alt="" src="${imgUrl}res/img/frame/login_logo_txt.png">
 		</div>
  <div class="authcenter-background">
	<img alt="" src="${imgUrl}res/img/frame/login_bg.jpg" style="width:100%;height: 100%">
  </div>
  <%@include file="../common/footer.jsp" %>
  
  <%@include file="../common/outjs.jsp" %>
  <script src="res/js/frame/login.js" type="text/javascript"></script>
</body>
</html>

