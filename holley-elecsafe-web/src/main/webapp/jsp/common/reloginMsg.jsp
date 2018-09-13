<%@ page contentType="text/html;charset=UTF-8" import="java.util.*" language="java" pageEncoding="UTF-8"%>
<%@include file="meta.jsp"%>
<%@include file="top.jsp"%>
<script src="<%=request.getContextPath()%>/res/js/common/common.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/res/js/common/constants.js" type="text/javascript"></script>
<script type="text/javascript">
autoskip= "${autoskip}";
if(autoskip){
	frameHref(LOGIN_URL);
}
</script>
    <!-- Content Header (Page header) -->
    <section class="content-header">
       <h1>系统提示</h1> 
    </section>
    <!-- Main content -->
    <section class="content">
      <div class="row">
      <div class="col-xs-12">
       <div class="box box-info">
			<p class="text-center" style="padding-top: 12%;padding-bottom: 12%">
				<strong>提示信息：</strong>
				<s:if test="#request.msg != null">
					<s:property value="#request.msg"/>
				</s:if>
				<s:if test="#request.backUrl != null">
					<a href="javascript:;" onclick="frameHref('${backUrl}');">
						<s:if test="#request.returnDesc != null"><s:property value="#request.returnDesc"/></s:if>
						<s:else>返回</s:else>
					</a>
				</s:if>
				<s:else>
					<a onclick="frameReload();" href="javascript:;">返回</a>
				</s:else>
			</p>
		</div>
       </div>
      </div>
    </section>
