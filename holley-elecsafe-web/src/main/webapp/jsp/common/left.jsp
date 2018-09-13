<%@ page contentType="text/html;charset=UTF-8"  language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<aside id="asideMenu" class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      	<!-- sidebar menu: : style can be found in sidebar.less -->
    	<ul class="sidebar-menu">
     		<li class="header"><i class="fa fa-dashboard"></i> 个人主菜单</li>
     		<li class="treeview active">
     			<a id="homepageMenu" href="javascript:;">
     				<i class="fa fa-fw fa-home"></i>
     				<span>首页</span>
     			</a>
     		</li>
     		<%-- <s:iterator id="topMenu" value="#session.topMenu">
      			<li class="treeview">
          			<a id=<s:property value="#topMenu.moduleid"/> href="javascript:;">
          				<s:if test="#topMenu.icon == 'dataMontior'">
          					<i class="fa fa-fw fa-database"></i>
          				</s:if>
          				<s:elseif test="#topMenu.icon == 'energyAnalyse'">
          					<i class="fa fa-fw fa-line-chart"></i>
          				</s:elseif>
          				<s:elseif test="#topMenu.icon == 'energyStatics'">
          					<i class="fa fa-fw fa-bar-chart"></i>
          				</s:elseif>
          				<s:elseif test="#topMenu.icon == 'entManager'">
          					<i class="fa fa-fw fa-credit-card"></i>
          				</s:elseif>
          				<s:elseif test="#topMenu.icon == 'sysManager'">
          					<i class="fa fa-fw fa-user"></i>
          				</s:elseif>
          				<s:elseif test="#topMenu.icon == 'setup'">
          					<i class="fa fa-fw fa-gears"></i>
          				</s:elseif>
	            		<span><s:property value="#topMenu.disc"/></span>
	            		<i class="fa fa-angle-left pull-right"></i>
          			</a>
		           	 <ul class="treeview-menu">
		          		<s:iterator id="subMenu" value="#session.subMenu">
		          			<s:if test="#topMenu.moduleid == #subMenu.parentmoduleid">
		          				<li>
		          					<a id=<s:property value="#subMenu.moduleid"/> href="javascript:;" link=<s:property value="#subMenu.url"/>>
		          						<i class="fa fa-circle-o"></i><span><s:property value="#subMenu.disc"/></span>
		          					</a>
		         				</li>
		          			</s:if>
		          		</s:iterator>
		          	 </ul>
      			</li>
     		</s:iterator> --%>
     		<s:iterator id="topMenu" value="#session.currentUser.permission.modules">
     			<s:if test="#topMenu.moduleid == #topMenu.parentmoduleid">
	      			<li class="treeview">
	          			<a id=<s:property value="#topMenu.moduleid"/> href="javascript:;">
	          				<s:if test="#topMenu.icon == 'dataMontior'">
	          					<i class="fa fa-fw fa-database"></i>
	          				</s:if>
	          				<s:elseif test="#topMenu.icon == 'energyAnalyse'">
	          					<i class="fa fa-fw fa-line-chart"></i>
	          				</s:elseif>
	          				<s:elseif test="#topMenu.icon == 'energyStatics'">
	          					<i class="fa fa-fw fa-bar-chart"></i>
	          				</s:elseif>
	          				<s:elseif test="#topMenu.icon == 'entManager'">
	          					<i class="fa fa-fw fa-credit-card"></i>
	          				</s:elseif>
	          				<s:elseif test="#topMenu.icon == 'sysManager'">
	          					<i class="fa fa-fw fa-user"></i>
	          				</s:elseif>
	          				<s:elseif test="#topMenu.icon == 'setup'">
	          					<i class="fa fa-fw fa-gears"></i>
	          				</s:elseif>
	          				<s:elseif test="#topMenu.icon == 'prepayment'">
	          					<i class="fa fa-fw fa-jpy"></i>
	          				</s:elseif>
		            		<span><s:property value="#topMenu.disc"/></span>
		            		<i class="fa fa-angle-left pull-right"></i>
	          			</a>
			           	 <ul class="treeview-menu">
			          		<s:iterator id="subMenu" value="#session.currentUser.permission.modules">
			          			<s:if test="#subMenu.moduleid != #subMenu.parentmoduleid && #topMenu.moduleid == #subMenu.parentmoduleid">
			          				<li>
			          					<a id=<s:property value="#subMenu.moduleid"/> href="javascript:;" link=<s:property value="#subMenu.url"/>>
			          						<i class="fa fa-circle-o"></i><span><s:property value="#subMenu.disc"/></span>
			          					</a>
			         				</li>
			          			</s:if>
			          		</s:iterator>
			          	 </ul>
	      			</li>
     			</s:if>
     		</s:iterator>
    	</ul>
    </section>
    <!-- /.sidebar -->
  </aside>
