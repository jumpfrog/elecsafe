package com.holley.elecsafe.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.holley.elecsafe.common.cache.ESCacheUtil;
import com.holley.elecsafe.common.constants.KeySessionTypeEnum;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.platform.common.security.SecurityUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.WebUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public abstract class BaseInterceptor extends AbstractInterceptor {

    private static final long   serialVersionUID = 299528989481069713L;
    @SuppressWarnings("unused")
    private static final Logger logger           = Logger.getLogger(BaseInterceptor.class);

    public abstract String intercept(ActionInvocation invocation) throws Exception;

    protected String getNamespce() {
        return ServletActionContext.getActionMapping().getNamespace();
    }

    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    protected String getServletPath() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getServletPath();
    }

    protected String getUserAccount() {
        return SecurityUtil.decrypt(getCookieByName(SecurityUtil.encrypt(EsGlobals.COOKIE_PV_WEB_ACCOUNT + getSessionId(), EsGlobals.COOKIE_DESKEY)), EsGlobals.COOKIE_DESKEY);
    }

    protected WebUser getSessionWebUser() {
        String account = getUserAccount();
        logger.info("cookie account:" + account == null ? "" : account);
        WebUser webUser = null;
        if (StringUtil.isNotEmpty(account)) {
            webUser = ESCacheUtil.getSession(account, KeySessionTypeEnum.ES_WEB);
        }
        logger.info("webUser:" + webUser == null ? "未登录" : "已登录");
        return webUser;
    }

    protected String getSessionId() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String jsessionID = request.getSession().getId();
        if (jsessionID.length() > 32) {
            jsessionID = jsessionID.substring(0, 32);
        }
        return jsessionID;
    }

    protected String getCookieByName(String cookieName) {
        Cookie allCookie[] = getRequest().getCookies();
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
}
