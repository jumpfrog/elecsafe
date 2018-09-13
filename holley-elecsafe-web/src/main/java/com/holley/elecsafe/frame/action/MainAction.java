package com.holley.elecsafe.frame.action;

import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.service.dat.DatDcsEsService;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.constants.LogOperatorEnum;
import com.holley.platform.common.security.RijndaelUtil;
import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.common.web.util.Validator;
import com.holley.platform.model.def.WebUser;
import com.holley.platform.model.sys.SysAccount;
import com.holley.platform.model.sys.SysModuledef;
import com.holley.platform.service.sys.AccountRoleService;
import com.holley.platform.util.HostLogUtils;

public class MainAction extends BaseAction {

    private static final long  serialVersionUID = 1L;

    private AccountRoleService accountRoleService;
    private DatDcsEsService    datDcsPvService;

    /**
     * 主页初始化
     * 
     * @return
     */
    public String init() {
        WebUser user = getSessionWebUser();
        List<SysModuledef> allModule = user.getPermission().getModules();
        List<SysModuledef> hasSubModule = new ArrayList<SysModuledef>();
        List<SysModuledef> noSubModule = new ArrayList<SysModuledef>();

        for (SysModuledef m : allModule) {
            List<SysModuledef> temp = getChildModuleList(m.getModuleid(), allModule);
            if (temp.size() > 0) {
                hasSubModule.add(m);
                hasSubModule.addAll(temp);
            } else if (m.getModuleid().equals(m.getParentmoduleid())) {
                noSubModule.add(m);
            }
        }
        this.getRequest().setAttribute("currentAccount", user.getAccount());
        this.getRequest().setAttribute("currentName", user.getName());
        this.getRequest().setAttribute("logindatestr", user.getLogindatestr());
        this.getRequest().setAttribute("deptdisc", user.getDepartmentname());
        this.getRequest().setAttribute("noSubModule", noSubModule);
        this.getSession().setAttribute("buttondefs", JsonUtil.list2json(user.getPermission().getButtons()));
        this.getRequest().setAttribute("hasSubModule", hasSubModule);
        return SUCCESS;
    }

    private List<SysModuledef> getChildModuleList(String parentmoduleid, List<SysModuledef> allModule) {
        List<SysModuledef> result = new LinkedList<SysModuledef>();
        for (SysModuledef sysModuledef : allModule) {
            if (parentmoduleid.equals(sysModuledef.getParentmoduleid()) && !sysModuledef.getModuleid().equals(sysModuledef.getParentmoduleid())) {
                result.add(sysModuledef);
            }
        }
        return result;
    }

    /**
     * 密码修改
     * 
     * @return
     * @throws Exception
     */
    public String modifyPwd() throws Exception {
        String oldPwd = this.getParameter("oldpwd");
        String newPwd = this.getParameter("newpwd");
        String repeatPwd = this.getParameter("repeatpwd");

        String account = getUserAccount();

        SysAccount user = accountRoleService.selectAccountByPK(account);
        if (user == null) {
            this.success = false;
            this.message = "用户不存在";
            return SUCCESS;
        }

        String msg = checkChangePwd(user, oldPwd, newPwd, repeatPwd);
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            SysAccount record = new SysAccount();
            record.setAccount(user.getAccount());
            record.setPassword(RijndaelUtil.encodePassword(newPwd));
            accountRoleService.updateAccountByPKSelective(record);
            HostLogUtils.recordDocumentlog(account, LogOperatorEnum.MODIFY, getRemoteIP(), getActiveTabid(), "修改登录密码");
            this.success = true;
        } else {
            this.success = false;
            this.message = msg;
        }
        return SUCCESS;
    }

    private String checkChangePwd(SysAccount user, String oldPwd, String newPwd, String repeatPwd) throws InvalidKeyException {
        String msg = Globals.DEFAULT_MESSAGE;
        if (StringUtil.isEmpty(oldPwd)) {
            msg = "旧密码不能为空.";
        } else if (StringUtil.isEmpty(newPwd)) {
            msg = "新密码不能为空.";
        } else if (StringUtil.isEmpty(repeatPwd)) {
            msg = "确认密码不能为空.";
        } else if (!RijndaelUtil.encodePassword(oldPwd).equals(user.getPassword())) {
            msg = "登录原密码不正确.";
        } else if (!newPwd.equals(repeatPwd)) {
            msg = "确认密码不一致.";
        } else if (!Validator.isPassword(newPwd)) {
            msg = " 密码为6-16位数字字母组合!";
        }
        return msg;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setAccountRoleService(AccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    public void setDatDcsPvService(DatDcsEsService datDcsPvService) {
        this.datDcsPvService = datDcsPvService;
    }

}
