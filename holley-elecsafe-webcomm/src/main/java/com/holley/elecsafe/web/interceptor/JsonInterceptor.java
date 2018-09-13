package com.holley.elecsafe.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.holley.elecsafe.common.cache.CacheKeyProvide;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.platform.common.cache.RedisUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.EntBaseInfo;
import com.holley.platform.model.def.WebUser;
import com.holley.platform.model.sys.SysButtondef;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * JSON调用异常拦截
 * 
 * @author zhouli
 */
public class JsonInterceptor extends BaseInterceptor {

    private static final long   serialVersionUID = -9168414377586612804L;
    private static final Logger logger           = Logger.getLogger(JsonInterceptor.class);

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        WebUser webUser = checkIsLogin();
        ValueStack st = invocation.getStack();
        String result = null;
        if (webUser == null) {
            st.set("error", 1);// 重新登录
            return "errorjson";
        } else {
            if (!checkSessionid(webUser)) {
                st.set("error", 4);// 账号更换登录地点
                return "errorjson";
            }
            if (!checkUrl(webUser)) {// 检测ajax请求是否有操作权限
                st.set("error", 2);// 操作权限
                return "errorjson";
            }
            if (!checkDataArea(webUser)) {
                st.set("error", 3);// 无数据范围权限
                return "errorjson";
            }
            try {
                result = invocation.invoke();
            } catch (Exception e) {
                st.set("error", 201);// 异常
                st.set("errormsg", "系统异常");
                e.printStackTrace();
                result = "errorjson";
            }
            return result;
        }
    }

    /**
     * 检测是否登录
     * 
     * @return
     */
    private WebUser checkIsLogin() {
        WebUser webUser = getSessionWebUser();
        if (webUser != null) {
            // 重置WebUser过期时间
            String webUserKey = CacheKeyProvide.getKey(CacheKeyProvide.KEY_ES_WEB_SESSION, webUser.getAccount());
            long outTime = RedisUtil.ttl(webUserKey);
            logger.info("JsonInterceptor-----------webUserKey:" + webUserKey + ",过期剩余时间:" + outTime);
            RedisUtil.expire(webUserKey, RedisUtil.EXRP_30M);
        }
        return webUser;
    }

    /**
     * 检测是否更换了登录点
     * 
     * @param webUser
     * @return
     */
    private boolean checkSessionid(WebUser user) {
        // 判断用户是否换了地点登录
        logger.info("JsonInterceptor-----------" + user.getSessionid() + "==" + getSessionId());
        if (!StringUtils.equals(user.getSessionid(), getSessionId())) {
            logger.info("检测到用户【" + user.getAccount() + "】更换浏览器二次登陆-----------" + user.getSessionid() + "==" + getSessionId());
            return false;
        }
        return true;
    }

    /**
     * 检测请求是否符合操作权限
     * 
     * @return
     */
    private boolean checkUrl(WebUser user) {
        HttpServletRequest req = ServletActionContext.getRequest();
        String servletPath = req.getServletPath();
        servletPath = servletPath.replaceFirst("/", "");
        String activeTab = req.getParameter(EsGlobals.ACTIVETAB);// 当前功能菜单

        if (StringUtil.isNull(activeTab)) {
            return true;
        }
        if (user.getPermission() == null || user.getPermission().getButtons() == null || user.getPermission().getButtons().size() == 0) {
            return false;
        }
        List<SysButtondef> buttonList = user.getPermission().getButtons();

        // 判断访问路径是否受权限控制，如不受控制，则直接返回true
        if (!isExistBtn(servletPath, buttonList)) {
            return true;
        }

        for (SysButtondef record : buttonList) {
            if (activeTab.equals(record.getModuleid()) && servletPath.equals(record.getUrl())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测请求数据域是否合法
     * 
     * @return
     */
    private boolean checkDataArea(WebUser user) {
        HttpServletRequest req = ServletActionContext.getRequest();
        String eid = req.getParameter("eid");
        if (StringUtil.isDigits(eid)) {
            List<EntBaseInfo> entList = user.getPermission().getEnterprises();
            if (entList == null || entList.size() == 0) {
                return false;
            }
            for (EntBaseInfo ent : entList) {
                if (Integer.valueOf(eid).equals(ent.getEid())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private String getExceptionAllinformation(Exception e) {
        String sOut = "System Error:\n";
        sOut += e.toString() + "\n";
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += s + "\n";
        }
        return sOut;
    }

    private boolean isExistBtn(String url, List<SysButtondef> list) {
        if (StringUtil.isEmpty(url) || list == null || list.size() == 0) return false;
        for (SysButtondef record : list) {
            if (url.equals(record.getUrl())) return true;
        }
        return false;
    }

}
