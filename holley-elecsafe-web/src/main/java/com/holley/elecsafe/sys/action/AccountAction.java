package com.holley.elecsafe.sys.action;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.write.WriteException;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.JxlXlsUtil;
import com.holley.platform.common.constants.AccountTypeEnum;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.constants.LogOperatorEnum;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.security.RijndaelUtil;
import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.common.web.util.Validator;
import com.holley.platform.model.def.AccountInfo;
import com.holley.platform.model.def.WebUser;
import com.holley.platform.model.sys.SysAccount;
import com.holley.platform.model.sys.SysAccountExample;
import com.holley.platform.model.sys.SysRole;
import com.holley.platform.model.sys.SysRoleExample;
import com.holley.platform.service.sys.AccountRoleService;
import com.holley.platform.util.HostLogUtils;

/**
 * 用户管理相关ACTION
 * 
 * @author zdd
 */
public class AccountAction extends BaseAction {

    private static final long  serialVersionUID = 1L;

    private AccountRoleService accountRoleService;
    private Page               page;
    private AccountInfo        accountInfo;

    /**
     * 用户管理初始化
     * 
     * @return
     */
    public String init() {
        this.getRequest().setAttribute("accountTypeList", AccountTypeEnum.values());
        return SUCCESS;
    }

    /**
     * 新增/修改用户初始化
     * 
     * @return
     */
    public String accountEditInit() {
        String requestType = this.getParameter("requestType");
        if (StringUtil.isNotDigits(requestType)) {
            this.getRequest().setAttribute(Globals.MSG, Globals.PARAM_ERROR_MESSAGE);
            return MSG;
        }

        WebUser user = getSessionWebUser();

        int reqType = Integer.valueOf(requestType);
        if (Globals.REQUEST_TYPE_ADD == reqType) {
            if (AccountTypeEnum.PLATFORM.getValue() != user.getType()) {// 不是平台账号，则新增账号默认显示登录账号的企业
                AccountInfo record = new AccountInfo();
                record.setDepartmentid(user.getDepartmentid());
                record.setDepartmentname(user.getDepartmentname());
                this.getRequest().setAttribute("accountInfo", record);
            }

        } else if (Globals.REQUEST_TYPE_EDIT == reqType) {
            String account = this.getParameter("account");
            if (StringUtil.isEmpty(account)) {
                this.getRequest().setAttribute(Globals.MSG, Globals.PARAM_ERROR_MESSAGE);
                return MSG;
            }
            AccountInfo record = accountRoleService.selectAccountInfoByPK(account);
            this.getRequest().setAttribute("accountInfo", record);

            if (record == null) {
                this.getRequest().setAttribute(Globals.MSG, "用户不存在");
                return MSG;
            }
        } else {
            this.getRequest().setAttribute(Globals.MSG, Globals.PARAM_ERROR_MESSAGE);
            return MSG;
        }

        this.getRequest().setAttribute("requestType", reqType);

        AccountTypeEnum[] accountTypeList = null;
        if (AccountTypeEnum.PLATFORM.getValue() == user.getType()) {// 平台管理员
            accountTypeList = AccountTypeEnum.values();
            // 平台管理员可选择所有角色
            List<SysRole> roleList = accountRoleService.selectRoleByExample(null);
            this.getRequest().setAttribute("roleList", roleList);
            this.getRequest().setAttribute("ruleListJson", JsonUtil.list2json(roleList));

        } else if (AccountTypeEnum.OPERATOR.getValue() == user.getType()) {// 企业操作员
            accountTypeList = new AccountTypeEnum[1];
            accountTypeList[1] = AccountTypeEnum.OPERATOR;
            // 企业操作员只能选择操作员角色
            SysRoleExample example = new SysRoleExample();
            SysRoleExample.Criteria cr = example.createCriteria();
            cr.andMemberlevelEqualTo(AccountTypeEnum.OPERATOR.getShortValue());
            List<SysRole> roleList = accountRoleService.selectRoleByExample(example);
            this.getRequest().setAttribute("roleList", roleList);
            this.getRequest().setAttribute("ruleListJson", JsonUtil.list2json(roleList));
        } else {// 企业管理员或运营商登录,用户类型：企业管理员和操作员两种类型
            accountTypeList = new AccountTypeEnum[2];
            accountTypeList[0] = AccountTypeEnum.ENTERPRISE;
            accountTypeList[1] = AccountTypeEnum.OPERATOR;
            // 企业管理员或运营商登录，系统角色可选择企业管理员角色和企业操作员角色
            List<Short> levels = new ArrayList<Short>();
            levels.add(AccountTypeEnum.PLATFORM.getShortValue());
            levels.add(AccountTypeEnum.BUSINESS.getShortValue());
            SysRoleExample example = new SysRoleExample();
            SysRoleExample.Criteria cr = example.createCriteria();
            cr.andMemberlevelIn(levels);
            List<SysRole> roleList = accountRoleService.selectRoleByExample(example);
            this.getRequest().setAttribute("roleList", roleList);
            this.getRequest().setAttribute("ruleListJson", JsonUtil.list2json(roleList));
        }

        this.getRequest().setAttribute("accountTypeList", accountTypeList);
        this.getRequest().setAttribute("webUserType", user.getType());
        return SUCCESS;
    }

    /**
     * 分页查询用户列表
     * 
     * @return
     * @throws IOException
     * @throws WriteException
     */
    public String queryList() throws WriteException, IOException {
        String keyword = this.getParameter("keyword");
        String accounttype = this.getParameter("accounttype");
        String pageindex = this.getParameter("pageindex");
        String pagelimit = this.getParameter("pagelimit");

        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(keyword)) {
            param.put("keyword", StringUtil.trim(keyword));
        }
        if (StringUtil.isNotEmpty(accounttype) && !"0".equals(accounttype)) {
            param.put("type", Short.valueOf(accounttype));
        }
        WebUser webUser = getSessionWebUser();
        List<Short> types = new ArrayList<Short>();
        if (AccountTypeEnum.PLATFORM.getValue() == webUser.getType()) {// 平台账户，查看所有账号
            param.put("eid", -1);
        } else if (AccountTypeEnum.BUSINESS.getValue() == webUser.getType()) {// 运营商：查询该企业非运营商类型的账号
            types.add(AccountTypeEnum.ENTERPRISE.getShortValue());
            types.add(AccountTypeEnum.OPERATOR.getShortValue());
            param.put("eid", webUser.getDepartmentid());
            param.put("excludeAccount", webUser.getAccount());
            param.put("typeList", types);
        } else if (AccountTypeEnum.ENTERPRISE.getValue() == webUser.getType()) {// 企业管理员：查询该企业管理员类型和操作员类型的账号，并排除当前登录账号
            types.add(AccountTypeEnum.ENTERPRISE.getShortValue());
            types.add(AccountTypeEnum.OPERATOR.getShortValue());
            param.put("eid", webUser.getDepartmentid());
            param.put("excludeAccount", webUser.getAccount());
            param.put("typeList", types);
        } else if (AccountTypeEnum.OPERATOR.getValue() == webUser.getType()) {// 操作员：查询该企业操作员类型的账号，并排除当前登录账号
            types.add(AccountTypeEnum.OPERATOR.getShortValue());
            param.put("eid", webUser.getDepartmentid());
            param.put("excludeAccount", webUser.getAccount());
            param.put("typeList", types);
        }

        if (isExportExcel()) {
            List<AccountInfo> list = accountRoleService.selectAccountByPage(param);
            String[] headsName = { "用户账号", "用户名称", "账号类型", "系统角色", "所属企业", "联系电话", "电子邮箱" };
            String[] properiesName = { "account", "name", "typename", "rolename", "departmentname", "telephone", "email" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(list, properiesName, headsName, AccountInfo.class);
            return null;
        } else {
            if (StringUtil.isNotDigits(accounttype, pageindex, pagelimit)) {
                this.success = false;
                this.message = Globals.PARAM_ERROR_MESSAGE;
                return SUCCESS;
            }
        }

        Page page = this.returnPage(Integer.valueOf(pageindex), Integer.valueOf(pagelimit));
        param.put(Globals.PAGE, page);
        List<AccountInfo> list = accountRoleService.selectAccountByPage(param);
        page.setRoot(list);
        this.page = page;
        return SUCCESS;
    }

    /**
     * 查询用户详细信息
     * 
     * @return
     */
    public String queryDetail() {
        String account = this.getParameter("account");
        if (StringUtil.isEmpty(account)) {
            this.success = false;
            this.message = "参数非法";
            return SUCCESS;
        }
        // 查询用户信息
        AccountInfo record = accountRoleService.selectAccountInfoByPK(account);
        if (record == null) {
            this.success = false;
            this.message = "用户不存在";
            return SUCCESS;
        }
        this.accountInfo = record;
        return SUCCESS;
    }

    /**
     * 新增用户
     * 
     * @return
     * @throws InvalidKeyException
     */
    public String addAccount() throws InvalidKeyException {
        String accountjson = this.getParameter("accountInfo");
        Map<String, Object> validMap = validAccountParams(accountjson);
        String msg = (String) validMap.get("msg");
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            WebUser user = getSessionWebUser();
            AccountInfo record = (AccountInfo) validMap.get("record");
            record.setPassword(RijndaelUtil.encodePassword(Globals.INITPASSWORD));

            if (AccountTypeEnum.PLATFORM.getValue() != user.getType()) {
                // 不是平台管理账号类型登录，则默认创建的账号角色和当前登录的账号角色一直
                record.setRoleid((int) user.getRoleid());
            }

            // 检验账号名是否重复
            SysAccountExample emp = new SysAccountExample();
            SysAccountExample.Criteria cr = emp.createCriteria();
            cr.andAccountEqualTo(record.getAccount());
            List<SysAccount> list = accountRoleService.selectAccountByExample(emp);
            if (list != null && list.size() > 0) {
                this.success = false;
                this.message = "用户账号已经存在，请重新填写";
                return SUCCESS;
            }

            record.setCreateaccount(user.getAccount());
            record.setCreatetime(new Date());

            accountRoleService.insertAccountInfo(record, getLogInfo());

        }

        return SUCCESS;
    }

    /**
     * 修改用户
     * 
     * @return
     */
    public String editAccount() {
        String accountjson = this.getParameter("accountInfo");
        Map<String, Object> validMap = validAccountParams(accountjson);
        String msg = (String) validMap.get("msg");
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            AccountInfo record = (AccountInfo) validMap.get("record");

            // 检验用户账号是否存在
            SysAccount sysAccount = accountRoleService.selectAccountByPK(record.getAccount());
            if (sysAccount == null) {
                this.success = false;
                this.message = "用户不存在";
                return SUCCESS;
            }

            if (Globals.ADMIN.equals(record.getAccount())) {
                record.setType(null);
                record.setRoleid(null);
                record.setDepartmentid(null);
            }

            accountRoleService.updateAccountInfo(record, getLogInfo());

        }
        return SUCCESS;
    }

    /**
     * 删除用户
     * 
     * @return
     */
    public String delAccount() {
        String account = this.getParameter("account");
        if (StringUtil.isEmpty(account)) {
            this.success = false;
            this.message = "参数非法";
            return SUCCESS;
        }

        if (Globals.ADMIN.equals(account)) {
            this.success = false;
            this.message = "系统最高管理员账号不能删除";
            return SUCCESS;
        }

        // 检验用户账号是否存在
        SysAccount sysAccount = accountRoleService.selectAccountByPK(account);
        if (sysAccount == null) {
            this.success = false;
            this.message = "用户不存在";
            return SUCCESS;
        }
        accountRoleService.deleteAccountByPK(account, getLogInfo());
        return SUCCESS;
    }

    /**
     * 初始化密码
     * 
     * @return
     * @throws InvalidKeyException
     */
    public String initPwd() throws InvalidKeyException {
        String account = this.getParameter("account");
        if (StringUtil.isEmpty(account)) {
            this.success = false;
            this.message = "参数非法";
        }

        if (Globals.ADMIN.equals(account)) {
            this.success = false;
            this.message = "系统最高管理员账号的密码不能被初始化";
            return SUCCESS;
        }

        // 检验用户账号是否存在
        SysAccount sysAccount = accountRoleService.selectAccountByPK(account);
        if (sysAccount == null) {
            this.success = false;
            this.message = "用户不存在";
            return SUCCESS;
        }
        // 更新用户密码
        SysAccount record = new SysAccount();
        record.setAccount(sysAccount.getAccount());
        record.setPassword(RijndaelUtil.encodePassword(Globals.INITPASSWORD));
        if (accountRoleService.updateAccountByPKSelective(record) > 0) {
            WebUser webUser = getSessionWebUser();
            String content = "表名:SYS_ACCOUNT;{用户账号【" + account + "】初始化密码}";
            HostLogUtils.recordDocumentlog(webUser.getAccount(), LogOperatorEnum.MODIFY, getRemoteIP(), getActiveTabid(), content);
        }
        return SUCCESS;
    }

    /**
     * 校验用户账号信息
     * 
     * @param jsonObj
     * @return
     */
    private Map<String, Object> validAccountParams(String jsonObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = Globals.DEFAULT_MESSAGE;
        AccountInfo record = this.JsonToBean(jsonObj, AccountInfo.class);

        int webUserType = getSessionWebUser().getType();

        if (StringUtil.isEmpty(record.getAccount())) {
            msg = "请填写用户账号";
        } else if (StringUtil.isEmpty(record.getName())) {
            msg = "请填写用户名称";
        } else if (!Globals.ADMIN.equals(record.getAccount())) {
            if (record.getType() == null) {
                msg = "请选择用户类型";
            } else if (AccountTypeEnum.PLATFORM.getValue() != record.getType().intValue() && (record.getDepartmentid() == null || record.getDepartmentid() < 0)) {// 不是平台账号时
                msg = "请选择所属企业";
            } else if (AccountTypeEnum.PLATFORM.getValue() == webUserType && record.getRoleid() == null) {// 平台管理员账号类型登录，校验是否选择系统角色
                msg = "请选择系统角色";
            }

            if (AccountTypeEnum.PLATFORM.getValue() == record.getType().intValue()) {
                record.setDepartmentid(-1);
            }
        }
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            if (StringUtil.isNotEmpty(record.getTelephone()) && !(Validator.isMobile(record.getTelephone()) || Validator.isTelephone(record.getTelephone()))) {
                msg = "请正确输入电话/手机号码";
            } else if (StringUtil.isNotEmpty(record.getEmail()) && !Validator.isEmail(record.getEmail())) {
                msg = "请正确电子邮箱";
            }
        }

        map.put("record", record);
        map.put("msg", msg);
        return map;
    }

    // get--set-----------------
    public void setAccountRoleService(AccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public Page getPage() {
        return page;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

}
