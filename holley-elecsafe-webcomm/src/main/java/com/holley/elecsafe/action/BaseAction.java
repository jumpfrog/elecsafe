package com.holley.elecsafe.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.TokenHelper;

import com.holley.elecsafe.common.cache.ESCacheUtil;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.elecsafe.common.constants.KeySessionTypeEnum;
import com.holley.platform.common.dataobject.JsonObjectValueProcessor;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.security.SecurityUtil;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.LogInfo;
import com.holley.platform.model.def.WebUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 基础struts的ACTION基类
 * 
 * @author zhouli
 */
public abstract class BaseAction extends ActionSupport implements SessionAware {

    private final static Logger   logger           = Logger.getLogger(BaseAction.class);
    private static final long     serialVersionUID = 1L;

    protected String              MSG              = "msg";                             // add by sc提示信息
    protected String              MEMBER           = "member";                          // add by sc返回主页
    // 返回的错误信息,从国际化资源文件取
    protected boolean             success          = true;
    protected String              message;                                              // json返回页面的
    protected String              encode           = "UTF-8";
    protected static int          limit            = 10;                                // 分页数据大小
    protected static String       ERROR            = "error";
    protected static int          IS_EXPORT        = 1;                                 // 导出Excel标志
    protected static int          MAX_EXPORT       = 3000;                              // 最大导出条数
    protected Map<String, Object> session;

    public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    public HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    public Map<String, Object> getApplication() {
        return ActionContext.getContext().getApplication();
    }

    public ServletContext getServletContext() {
        return ServletActionContext.getServletContext();
    }

    public String getRealyPath(String path) {
        return getServletContext().getRealPath(path);
    }

    public HttpSession getSession() {
        return this.getRequest().getSession();
    }

    protected String getParameter(String para) {
        String paraValue = this.getRequest().getParameter(para);
        return paraValue;
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

    public String getSessionId() {
        String jsessionID = getSession().getId();
        if (jsessionID.length() > 32) {
            jsessionID = jsessionID.substring(0, 32);
        }
        return jsessionID;
    }

    public String getRemoteIP() {
        String ip = this.getRequest().getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = this.getRequest().getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = this.getRequest().getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = this.getRequest().getRemoteAddr();
        }

        return ip;
    }

    protected Cookie saveToCookie30Min(String cookieName, String cookieValue) throws Exception {
        return saveToCookie(cookieName, cookieValue, 60 * 30);// cookie保存30分钟
    }

    protected Cookie saveToCookie(String cookieName, String cookieValue) throws Exception {
        return saveToCookie(cookieName, cookieValue, -1);// cookie保存当前会话
    }

    protected Cookie saveToCookie(String cookieName, String cookieValue, int maxAge) throws Exception {
        Cookie cookie = null;
        if (this.getCookieByName(cookieName) == null) {
            cookie = new Cookie(cookieName, cookieValue);
        } else {
            Cookie[] cookies = this.getRequest().getCookies();
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i];
                if (cookie.getName().equals(cookieName)) {
                    cookie.setValue(cookieValue);
                    break;
                }

            }
        }
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);// 秒
        this.getResponse().addCookie(cookie);
        return cookie;
    }

    /**
     * 是否需要导出excel
     * 
     * @return
     */
    public boolean isExportExcel() {
        String isExport = getRequest().getParameter("isExport");
        return StringUtils.equals("true", isExport);
    }

    /**
     * 发送JSON数据
     * 
     * @author shencheng
     * @param json
     */
    protected void printJson(String json) {
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.info("发送json数据，失败！！");
            e.printStackTrace();
        }
    }

    /**
     * seconds 设置几分钟后可以 重新获取验证码
     *
     * @param seconds
     */

    protected String checkCanGetMobileCode(long seconds) {
        String msg = "success";
        Object codeInteceptor = getSession().getAttribute("phone_code_time");
        if (codeInteceptor != null) {// 申请过一次 计算时间差
            long currTime = System.currentTimeMillis();
            long remainTime = currTime - Long.parseLong(codeInteceptor.toString());
            long get_time = seconds * 60 * 1000;
            if (remainTime < get_time) {
                msg = "请您" + (get_time - remainTime) / (1000) + " 秒后，重新获取验证码";

            } else {
                getSession().setAttribute("phone_code_time", System.currentTimeMillis());
            }
        } else {
            getSession().setAttribute("phone_code_time", System.currentTimeMillis());
        }
        return msg;
    }

    /**
     * 提示消息
     *
     * @param msg
     */
    protected void message(String msg) {
        getRequest().setAttribute("msg", msg);
        // String urltext = "<a href=" + request.getContextPath() + url + " >" + text + "></a>";
        // request.setAttribute("backurl", urltext);
    }

    // protected Page returnPage(int currentPage, int limit) {
    // currentPage = currentPage == 0 ? 1 : currentPage;
    // limit = limit == 0 ? 8 : limit;
    // Page page = new Page((currentPage - 1) * limit, limit);
    // return page;
    // }

    protected String getServletRealPath(String pathName) {
        return ServletActionContext.getServletContext().getRealPath(pathName);
    }

    protected int getParamInt(String param) {
        return NumberUtils.toInt(getParameter(param));
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

    /**
     * 取当前功能模块编码
     * 
     * @return
     */

    public String getActiveTabid() {
        HttpSession session = getSession();
        String activetabid = (String) session.getAttribute(EsGlobals.ACTIVETAB);
        return activetabid == null ? "" : activetabid;
    }

    /**
     * 操作日志基本信息
     * 
     * @return
     */
    protected LogInfo getLogInfo() {
        LogInfo logInfo = new LogInfo();
        logInfo.setAccount(getUserAccount());
        logInfo.setIp(getRemoteIP());
        logInfo.setModuleid(getActiveTabid());
        return logInfo;
    }

    /**
     * 数据加密
     * 
     * @param key
     * @return
     */
    public String encrypt(String key) {
        if (StringUtil.isEmpty(key)) {
            logger.info("加密参数非法");
            return null;
        }
        String securityKey = EsGlobals.DATA_SECURIRY_KEY + DateUtil.getTodayWeekStr();
        return SecurityUtil.aesEncode(key, securityKey);
    }

    /**
     * 数据解密
     * 
     * @param key
     * @return
     */
    public String decrypt(String key) {
        if (StringUtil.isEmpty(key)) {
            logger.info("解密参数非法");
            return null;
        }
        key = key.replaceAll(" ", "+");
        String securityKey = EsGlobals.DATA_SECURIRY_KEY + DateUtil.getTodayWeekStr();
        return SecurityUtil.aesDecode(key, securityKey);
    }

    // 当前用户
    // protected WebUser getWebUser() {
    // Object user = getSession().getAttribute(Globals.CURRENTUSER);
    // return user == null ? null : (WebUser) user;
    // return null;
    // }

    // 当前用户帐号
    // protected String getCurrentAccount() {
    // Object user = getSession().getAttribute(Globals.CURRENTUSER);
    // return user == null ? "" : ((WebUser) user).getAccount();
    // }

    // 当前用户名称
    // protected String getCurrentName() {
    // Object user = getSession().getAttribute(Globals.CURRENTUSER);
    // return user == null ? "" : ((WebUser) user).getName();
    // }

    // 当前功能
    // protected String getCurrentModule() {
    // Object activeTab = getSession().getAttribute(Globals.ACTIVETAB);
    // return activeTab == null ? "" : (String) activeTab;
    // }

    // protected WebUser getWebuser() {
    // String webUserKey = getSessionUserId();
    // String userId = SecurityUtil.decrypt(getCookieByName(SecurityUtil.encrypt(Globals.COOKIE_USERID + getSessionID(),
    // Globals.COOKIE_DESKEY)), Globals.COOKIE_DESKEY);
    //
    // WebUser webUser = null;
    // if (webUserKey != null && userId != null) {
    // webUser = ChargingCacheUtil.getSession(userId, KeySessionTypeEnum.WEB, webUserKey);
    // }
    // return webUser;
    // }

    // protected String getSessionUserId() {
    // return SecurityUtil.decrypt(getCookieByName(SecurityUtil.encrypt(Globals.COOKIE_SESSION_USERID + getSessionID(),
    // Globals.COOKIE_DESKEY)), Globals.COOKIE_DESKEY);
    // }
    //
    // protected String getSessionBmsUserId() {
    // return SecurityUtil.decrypt(getCookieByName(SecurityUtil.encrypt(Globals.COOKIE_BMS_USERID + getSessionID(),
    // Globals.COOKIE_DESKEY)), Globals.COOKIE_DESKEY);
    // }

    protected <T> T JsonToBean(String JsonString, Class<T> clazz) {
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM" }));
        JSONObject jsonObject = JSONObject.fromObject(JsonString);
        T entity = (T) JSONObject.toBean(jsonObject, clazz);
        return entity;
    }

    protected JsonConfig returnJosnConfig(boolean isFilterNull, Class... filterClass) {
        JsonConfig jsonConfig = new JsonConfig();
        if (isFilterNull) {
            // 过滤空值
            PropertyFilter filter = new PropertyFilter() {

                public boolean apply(Object object, String fieldName, Object fieldValue) {
                    return null == fieldValue;
                }
            };
            jsonConfig.setJsonPropertyFilter(filter);
        }
        for (Class clazz : filterClass) {
            jsonConfig.registerJsonValueProcessor(clazz, new JsonObjectValueProcessor());
        }
        // 日期类型的值转化成yyyy-MM-dd HH:mm:ss

        return jsonConfig;
    }

    protected String saveToken(String name) {
        String token = TokenHelper.setToken(name);
        session.put(name, token);
        return token;
    }

    protected String checkToken(String name) {
        String paramValue = getParameter(name);
        Object token = session.get(name);
        String tokenValue = token == null ? "" : token.toString();
        // 参数、session中都没用token值提示错误
        if (StringUtils.isBlank(tokenValue)) {
            return "会话Token未设定！";
        } else if (StringUtils.isBlank(paramValue)) {
            return "表单Token未设定！";
        } else if (paramValue.equals(tokenValue)) { // session中有token,防止重复提交检查
            session.remove(name);
            return "success";
        } else {
            return "请勿重复提交！";
        }
    }

    public void setUrlParam(String urlparam) {
        if (StringUtil.isNotEmpty(urlparam)) {
            getRequest().setAttribute("urlparam", urlparam);
        }
    }

    protected Page returnPage(int currentPage, int limit) {
        currentPage = currentPage == 0 ? 1 : currentPage;
        limit = limit == 0 ? 10 : limit;
        Page page = new Page((currentPage - 1) * limit, limit);
        return page;
    }

    public void errorParam() {
        this.success = false;
        this.message = "非法参数";
    }

    public void waringParam(String msg) {
        this.success = false;
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}
