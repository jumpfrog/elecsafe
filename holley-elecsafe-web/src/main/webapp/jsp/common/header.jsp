<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<header class="main-header">
    <!-- Logo -->
    <a href="${webRoot}/main/main.action" class="logo">
       <span class="logo-lg">
       		<img src="${imgUrl}res/img/frame/logo_origin.png"/>
       		<span>智慧安全用电云平台</span>
       </span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" style="height: 50px;">
	  <div class="navbar-ent hidden-md hidden-sm hidden-xs">
	    <span>${deptdisc}</span>
	  </div>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Notifications: style can be found in dropdown.less -->
          <li class="menuview active">
          	<a  href="javascript:;" link="main/homepage.action"><span class="fa fa-home" style="line-height:1.4"> 首页</span></a>
          </li> 
            <s:iterator var="noSubModule" value="#request.noSubModule">
		            <li class="menuview">
		          		<a id="<s:property value="#noSubModule.moduleid"/>" href="javascript:;" link='<s:property value="#noSubModule.url"/>'>
				          	<span class="fa fa-<s:property value="#noSubModule.icon"/>" style="line-height:1.4"> 
					          		<s:property value="#noSubModule.disc"/>
					        </span>
		          		</a>
	         		</li>
            </s:iterator>
            <s:iterator var="tophasSubModule" value="#request.hasSubModule">
	             <s:if test="#tophasSubModule.moduleid == #tophasSubModule.parentmoduleid">
	          		<li class="menuview dropdown">
	          			<a id="<s:property value="#tophasSubModule.moduleid"/>" href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="menudisc fa fa-<s:property value="#tophasSubModule.icon"/>"> <s:property value="#tophasSubModule.disc"/></span>&nbsp<span class="caret"></span></a>
	          			<ul class="dropdown-menu">
		          			<s:iterator var="hasSubModule" value="#request.hasSubModule">
		          				<s:if test="#hasSubModule.moduleid != #hasSubModule.parentmoduleid && #tophasSubModule.moduleid == #hasSubModule.parentmoduleid">
		          					 <li class="dropdown-menuview">
		          					 	<a href="javascript:;" id=<s:property value="#hasSubModule.moduleid"/> link="<s:property value="#hasSubModule.url"/>"><s:property value="#hasSubModule.disc"/></a>
		          					 </li>
		          				</s:if>
		          			</s:iterator>
	          			</ul>
	          		</li>
	          	</s:if>
             </s:iterator> 
            
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle text-center" data-toggle="dropdown" style="height: 50px;">
              <span class="glyphicon glyphicon-user " style="font-size: 18px;"></span>
              <span class="hidden-xs">${currentName}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${imgUrl}res/img/frame/user_head.png" class="img-circle" alt="User Image">
                <p>
                  ${currentAccount}
                  <small>${logindatestr}</small>
                </p>
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="javascript:;" id="modifyPwdBtn" class="btn btn-default btn-flat">修改密码</a>
                </div>
                <div class="pull-right">
                  <a href="javascript:;" id="logoutBtn" class="btn btn-default btn-flat">安全退出</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
        </ul>
      </div>
    </nav>
    <script type="text/javascript">
    	//var accountType = ${currentUser.type};
    </script>
  </header>
<%@include file="component/modifyPwd.jsp" %>