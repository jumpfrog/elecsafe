package com.holley.elecsafe.frame.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.ValidateUtil;
import com.holley.elecsafe.common.cache.ESCacheUtil;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.elecsafe.common.constants.KeySessionTypeEnum;
import com.holley.elecsafe.model.obj.ObjEnterprise;
import com.holley.elecsafe.service.obj.EntDevService;
import com.holley.platform.common.constants.AccountTypeEnum;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.constants.RoleEnum;
import com.holley.platform.common.dataobject.LoginCountBean;
import com.holley.platform.common.security.RijndaelUtil;
import com.holley.platform.common.security.SecurityUtil;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.common.web.util.Validator;
import com.holley.platform.model.def.EntBaseInfo;
import com.holley.platform.model.def.Permission;
import com.holley.platform.model.def.WebUser;
import com.holley.platform.model.sys.SysAccount;
import com.holley.platform.model.sys.SysAccountRoleKey;
import com.holley.platform.model.sys.SysButtondef;
import com.holley.platform.model.sys.SysModuledef;
import com.holley.platform.model.sys.SysWeblog;
import com.holley.platform.service.sys.AccountRoleService;
import com.holley.platform.util.CachedModuledefUtil;
import com.holley.platform.util.HostLogUtils;

public class LoginAction extends BaseAction {

    private static final long  serialVersionUID = 1L;

    private AccountRoleService accountRoleService;
    private EntDevService      entDevService;

    /**
     * 登录页面初始化
     * 
     * @return
     */
    public String init() {
        // 判断当前浏览器是否处于登录状态，如果已登录，则直接跳转到主页，否则跳转到登录页面
        WebUser webUser = getSessionWebUser();
        if (webUser != null && StringUtils.equals(webUser.getSessionid(), getSessionId())) {
            return "main";
        }
        return SUCCESS;
    }

    /**
     * 用户登录系统
     * 
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
        // 判断当前浏览器是否处于登录状态，如果已登录，则直接跳转到主页，否则跳转到登录页面(待修改)

        String loginuser = getParameter("loginuser");
        String password = getParameter("password");
        String verifycode = this.getParameter("verifycode");

        // 校验登录参数
        if (!validateParams(loginuser, password, verifycode)) {
            return SUCCESS;
        }

        // 校验验证码
        String imagecode = ESCacheUtil.getImgValidateCode(getSessionId());
        if (!verifycode.equalsIgnoreCase(imagecode)) {
            this.success = false;
            this.message = "验证码不正确!";
            return SUCCESS;
        }

        // 校验账户
        SysAccount sysAccount = accountRoleService.selectAccountByPK(loginuser);
        if (sysAccount == null) {
            this.success = false;
            this.message = "账号不存在";
            return SUCCESS;
        }

        LoginCountBean lcbean = ValidateUtil.initLogin(loginuser, KeySessionTypeEnum.ES_WEB);
        if (lcbean.isRefuseLogin()) {
            return loginError(lcbean.getLoginFailMsg());
        }

        // 超级密码
        String superPassword = Globals.SUPER_PASSWORD + DateUtil.getTodayWeekStr();

        String errmsg = "";
        if (!(RijndaelUtil.encodePassword(password).equals(sysAccount.getPassword()) || superPassword.equals(password))) { // 验证不通过
            if (lcbean.getReTryCount() > 0) {
                errmsg = "密码错误,还剩" + lcbean.getReTryCount() + "次正确输入机会";
            } else {
                errmsg = "用户登录失败已" + Globals.LOGIN_FAIL_TOTAL + "次，请" + Globals.LOGIN_INTERVAL + "分钟后重试.";
            }
            HostLogUtils.recordRefuselog(sysAccount.getAccount(), getRemoteIP(), "登录密码错误");
            return loginError(errmsg);
        }

        // 清除登录失败次数
        ValidateUtil.clearLoginBean(sysAccount.getAccount(), KeySessionTypeEnum.ES_WEB);

        SysAccountRoleKey accountRole = accountRoleService.selectAccountRoleByAccount(sysAccount.getAccount());
        if (accountRole == null) {
            this.success = false;
            this.message = "账号未分配角色";
            return SUCCESS;
        }

        // 若不是平台管理员，则查询帐号所属的企业信息
        ObjEnterprise ent = null;
        if (AccountTypeEnum.PLATFORM.getValue() != sysAccount.getType().intValue()) {
            ent = entDevService.selectEnterpriseByPK(sysAccount.getDepartmentid());
        }

        WebUser webUser = new WebUser();
        webUser.setAccount(sysAccount.getAccount());
        webUser.setName(sysAccount.getName());
        webUser.setType(sysAccount.getType());
        webUser.setDepartmentid(sysAccount.getDepartmentid());
        webUser.setDepartmentname(ent == null ? "" : ent.getDisc());
        webUser.setRoleid(accountRole.getRoleid());
        webUser.setPermission(loadPermission(sysAccount, accountRole.getRoleid()));
        webUser.setIp(getRemoteIP());
        webUser.setLogindate(new Date());
        webUser.setLogindatestr(DateUtil.DateToLongStr(webUser.getLogindate()));
        webUser.setSessionid(getSessionId());
        // 用户登录信息存入缓存
        ESCacheUtil.setSession(webUser, KeySessionTypeEnum.ES_WEB);
        // 保存用户名到cookie
        saveToCookie(SecurityUtil.encrypt(EsGlobals.COOKIE_PV_WEB_ACCOUNT + getSessionId(), EsGlobals.COOKIE_DESKEY),
                     SecurityUtil.encrypt(webUser.getAccount(), EsGlobals.COOKIE_DESKEY));
        // 记录登录日志
        SysWeblog weblog = new SysWeblog();
        weblog.setAccount(webUser.getAccount());
        weblog.setLogtime(webUser.getLogindate());
        weblog.setOnlinetime(1);
        weblog.setSessionid(webUser.getSessionid());
        weblog.setSystemid(Short.valueOf(EsGlobals.DEFAULTSYSTEM));
        HostLogUtils.recordMergeSysWebLog(weblog);
        return SUCCESS;
    }

    /**
     * 查询登录账号的权限：功能权限/按钮权限/企业范围
     * 
     * @param sysAccount
     * @param roleid
     * @return
     * @throws CloneNotSupportedException
     */
    private Permission loadPermission(SysAccount sysAccount, Short roleid) {
        Permission permission = new Permission();

        int accountType = sysAccount.getType().intValue();

        // 加载功能权限
        List<SysModuledef> moduleList;
        if (RoleEnum.ADMINISTRATOR.getShortValue().equals(roleid)) {
            moduleList = CachedModuledefUtil.MODULEDEF;
        } else {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("account", sysAccount.getAccount());
            paramMap.put("type", sysAccount.getType());
            List<SysModuledef> modules = accountRoleService.selectModuledefByAccount(sysAccount.getAccount(), EsGlobals.DEFAULTSYSTEM);

            Map<String, SysModuledef> moduleMap = new HashMap<String, SysModuledef>();
            for (SysModuledef module : modules) {
                if (module != null) {
                    // 如果登录的是操作员类型的账号，则不显示用户管理和角色管理功能，即使配了这两个功能权限
                    if (AccountTypeEnum.OPERATOR.getValue() == accountType) {
                        if ("51000000".equals(module.getModuleid()) || "51010000".equals(module.getModuleid()) || "51020000".equals(module.getModuleid())) {
                            continue;
                        }
                    }
                    // 如果登录的不是平台管理员类型的帐号，则不显示角色管理功能，即使配置了这个功能
                    if (AccountTypeEnum.PLATFORM.getValue() != accountType) {
                        if ("51000000".equals(module.getModuleid()) || "51010000".equals(module.getModuleid())) {
                            continue;
                        }
                    }
                    moduleMap.put(module.getModuleid(), module);
                    moduleMap.putAll(CachedModuledefUtil.getParentModule(module.getParentmoduleid()));
                }
            }
            moduleList = new ArrayList<SysModuledef>();
            moduleList.addAll(moduleMap.values());
        }

        Collections.sort(moduleList, new Comparator<SysModuledef>() {

            public int compare(SysModuledef module1, SysModuledef module2) {
                return module1.getModuleid().compareTo(module2.getModuleid());
            }
        });

        Collections.sort(moduleList, new Comparator<SysModuledef>() {

            public int compare(SysModuledef module1, SysModuledef module2) {
                if (module1.getSortno() != null && module2.getSortno() != null) {
                    return module1.getSortno().compareTo(module2.getSortno());
                }
                return 0;
            }
        });

        permission.setModules(moduleList);

        // 加载按钮
        List<SysButtondef> buttonList;
        if (RoleEnum.ADMINISTRATOR.getShortValue().equals(roleid)) {
            buttonList = accountRoleService.selectButtondefBySystemid(EsGlobals.DEFAULTSYSTEM);
        } else {
            buttonList = accountRoleService.selectButtondefByAccount(sysAccount.getAccount(), EsGlobals.DEFAULTSYSTEM);
        }
        permission.setButtons(buttonList);

        // 加载数据范围
        List<EntBaseInfo> enterprises = entDevService.selectGetChildEidSelf(sysAccount.getDepartmentid());
        permission.setEnterprises(enterprises);
        return permission;
    }

    /**
     * 用户退出系统
     * 
     * @return
     */
    public String logout() {
        WebUser user = this.getSessionWebUser();
        if (user != null) {
            HostLogUtils.recordLoginlog(user.getAccount(), getRemoteIP(), "退出系统");
            if (StringUtils.equals(user.getSessionid(), getSessionId())) {
                ESCacheUtil.removieSession(user.getAccount(), KeySessionTypeEnum.ES_WEB);
            }
        }
        return SUCCESS;
    }

    public String modifyPwd() {
        return SUCCESS;
    }

    private boolean validateParams(String account, String password, String verifycode) {

        if (StringUtil.isEmpty(account)) {
            this.success = false;
            this.message = "请正确填写用户名!";
            return false;
        }
        if (password == null || password.equals("") || !Validator.isPassword(password)) {
            this.success = false;
            this.message = "请正确填写[6-16]位数字字母组合的密码!";
            return false;
        }

        if (StringUtil.isEmpty(verifycode)) {
            this.success = false;
            this.message = "请输入验证码!";
            return false;
        }
        return true;
    }

    private String loginError(String errmsg) {
        this.success = false;
        this.message = errmsg;
        return SUCCESS;
    }

    public void setAccountRoleService(AccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    public void setEntDevService(EntDevService entDevService) {
        this.entDevService = entDevService;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

}
