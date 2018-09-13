package com.holley.elecsafe.web.interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.holley.elecsafe.common.cache.CacheKeyProvide;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.platform.common.cache.RedisUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.EntBaseInfo;
import com.holley.platform.model.def.Permission;
import com.holley.platform.model.def.WebUser;
import com.holley.platform.model.sys.SysModuledef;
import com.holley.platform.util.CachedModuledefUtil;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 权限拦截器<br>
 * 1 判断是否已经登录 <br>
 * 2 判断是否有权访问对应的资源<br>
 * 3 记录选中tab
 * 
 * @author zhouli
 */
public class PermissionInterceptor extends BaseInterceptor {

    private static final long   serialVersionUID = 263973631301649276L;
    private static final Logger logger           = Logger.getLogger(PermissionInterceptor.class);

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest req = ServletActionContext.getRequest();

        WebUser user = checkIsLogin();
        if (user == null) {
            req.setAttribute("autoskip", true);// 自动跳转
            req.setAttribute("msg", "您的会话已结束,请重新登录!");
            req.setAttribute("returnDesc", "登录");
            req.setAttribute("backUrl", "frame/login.action");
            return "relogin";
        }

        if (!checkSessionid(user)) {
            req.setAttribute("msg", "您的账号已在其它地方登录!");
            req.setAttribute("returnDesc", "重新登录");
            req.setAttribute("backUrl", "frame/login.action");
            return "relogin";
        }

        if (!checkUrl(user)) {
            req.setAttribute("msg", "无访问权限!");
            return "msg";
        }

        if (!checkDataArea(user)) {
            req.setAttribute("msg", "无数据范围权限!");
            return "msg";
        }

        recordActiveTab();

        return invocation.invoke();
    }

    /**
     * 检测是否登录
     * 
     * @return
     */
    private WebUser checkIsLogin() {
        WebUser webUser = getSessionWebUser();
        if (webUser != null) {
            getRequest().setAttribute("entCount", webUser.getPermission().getEnterprises().size());
            // 重置WebUser过期时间
            String webUserKey = CacheKeyProvide.getKey(CacheKeyProvide.KEY_ES_WEB_SESSION, webUser.getAccount());
            long outTime = RedisUtil.ttl(webUserKey);
            logger.info("PermissionInterceptor-----------webUserKey:" + webUserKey + ",过期剩余时间:" + outTime);
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
        logger.info("PermissionInterceptor-----------" + user.getSessionid() + "==" + getSessionId());
        if (!StringUtils.equals(user.getSessionid(), getSessionId())) {
            logger.info("检测到用户【" + user.getAccount() + "】更换浏览器二次登陆-----------" + user.getSessionid() + "==" + getSessionId());
            return false;
        }
        return true;
    }

    protected String getCookieByName(String cookieName) {
        Cookie allCookie[] = ServletActionContext.getRequest().getCookies();
        if (allCookie != null && allCookie.length != 0) {
            for (int i = 0; i < allCookie.length; i++) {
                String keyname = allCookie[i].getName();
                String value = allCookie[i].getValue();

                if (StringUtils.equals(cookieName, keyname)) {
                    return value;
                }
            }
        }

        return null;
    }

    /**
     * 检测是否有权访问资源
     * 
     * @param url
     * @return
     */
    private boolean checkUrl(WebUser user) {

        HttpServletRequest req = ServletActionContext.getRequest();

        String url = req.getServletPath();
        url = url.substring(1); // 移除 首字符'/'
        logger.info("checkUrl=" + url);

        // 检查url是否存在于ModuleDef表中
        if (!CachedModuledefUtil.constains(url)) {
            return true;
        }
        Permission permission = user.getPermission();
        if (permission == null) return false;
        List<SysModuledef> moduleList = permission.getModules();
        for (SysModuledef sysModuledef : moduleList) {
            if (sysModuledef.getUrl() != null && sysModuledef.getUrl().indexOf(url) != -1) {
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

    public void recordActiveTab() {
        HttpServletRequest req = ServletActionContext.getRequest();
        String activeTab = req.getParameter(EsGlobals.ACTIVETAB);
        if (!(StringUtil.isEmpty(activeTab) || "undefined".equals(activeTab))) {
            HttpSession session = req.getSession();
            synchronized (this) {
                session.setAttribute(EsGlobals.ACTIVETAB, activeTab);
            }
        }
    }
}
