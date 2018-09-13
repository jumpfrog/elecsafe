package com.holley.elecsafe.sys.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.JxlXlsUtil;
import com.holley.elecsafe.common.constants.EsGlobals;
import com.holley.platform.common.constants.AccountTypeEnum;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.constants.LogOperatorEnum;
import com.holley.platform.common.constants.RoleLevelEnum;
import com.holley.platform.common.util.JsonUtil;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.WebUser;
import com.holley.platform.model.sys.SysAccountRoleExample;
import com.holley.platform.model.sys.SysAccountRoleKey;
import com.holley.platform.model.sys.SysRole;
import com.holley.platform.model.sys.SysRoleExample;
import com.holley.platform.model.sys.vo.PermissinTreeNode;
import com.holley.platform.service.sys.AccountRoleService;
import com.holley.platform.util.HostLogUtils;

/**
 * 角色管理相关action
 * 
 * @author tx
 */
public class RoleAction extends BaseAction {

    private static final long       serialVersionUID = 1L;
    private List<SysRole>           sysRoleList;
    private List<PermissinTreeNode> treeNodeList;
    private AccountRoleService      accountRoleService;

    /**
     * 角色管理页面初始化
     * 
     * @return
     */
    public String init() {
        return SUCCESS;
    }

    /**
     * 新增/修改角色初始化
     * 
     * @return
     */
    public String roleEditInit() {
        String roleid = this.getParameter("roleid");
        String requesttype = this.getParameter("requesttype");
        if (!StringUtil.isDigits(requesttype)) {
            this.getRequest().setAttribute(Globals.MSG, "参数非法.");
            return MSG;
        }

        int reqType = Integer.valueOf(requesttype);
        if (Globals.REQUEST_TYPE_EDIT == reqType) {// 修改
            if (!StringUtil.isDigits(roleid)) {
                this.getRequest().setAttribute(Globals.MSG, "参数非法.");
                return MSG;
            }
            SysRole record = accountRoleService.selectRoleByPK(Short.valueOf(roleid));
            this.getRequest().setAttribute("sysRole", record);
        }
        this.getRequest().setAttribute("memberLevel", RoleLevelEnum.values()); 
        this.getRequest().setAttribute("requestType", requesttype);
        return SUCCESS;
    }

    /**
     * 角色分配初始化
     * 
     * @return
     */
    public String loadPermissionInit() {
        String roleid = this.getParameter("roleid");
        if (StringUtil.isEmpty(roleid)) {
            this.getRequest().setAttribute(Globals.MSG, "参数非法");
            return MSG;
        }
        this.getRequest().setAttribute("roleid", roleid);
        return SUCCESS;
    }

    /**
     * 查询
     * 
     * @return
     * @throws Exception
     */
    public String queryList() throws Exception {
        String keyword = this.getParameter("keyword");
        WebUser user = getSessionWebUser();

        SysRoleExample emp = new SysRoleExample();
        SysRoleExample.Criteria cr = emp.createCriteria();
        cr.andDiscLike("%" + StringUtil.trim(keyword) + "%");
        // 如果平台账户登录，查看所有角色，如果其他类型账户登录，查看自己创建的角色
        if (AccountTypeEnum.PLATFORM.getValue() != user.getType()) {
            cr.andCreatorEqualTo(user.getAccount());
        }

        List<SysRole> list = accountRoleService.selectRoleByExample(emp);
        if (isExportExcel()) {
            String[] headsName = { "角色名称", "会员等级", "角色描述", "创建人", "创建时间" };
            String[] properiesName = { "disc", "memberlevelname", "remark", "creator", "createtimeStr" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(list, properiesName, headsName, SysRole.class);
            return null;
        } else {
            this.sysRoleList = list;
            return SUCCESS;
        }
    }

    /**
     * 新增角色信息
     * 
     * @return
     */
    public String addRole() {
        String sysRole = this.getParameter("sysRole");
        Map<String, Object> validMap = validRoleParams(sysRole);
        String msg = (String) validMap.get("msg");
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            WebUser webUser = getSessionWebUser();
            SysRole record = (SysRole) validMap.get("sysRole");
            record.setCreator(webUser.getAccount());
            record.setCreatetime(new Date());
            if (accountRoleService.insertRole(record) > 0) {
                WebUser user = getSessionWebUser();
                String content = JSONObject.fromObject(record, JsonUtil.returnJosnConfig(false, Date.class)).toString();
                content = "表名:SYS_ROLE;" + content;
                HostLogUtils.recordDocumentlog(user.getAccount(), LogOperatorEnum.ADD, getRemoteIP(), getActiveTabid(), content);
            }

        }
        return SUCCESS;
    }

    /**
     * 修改角色信息
     * 
     * @return
     */
    public String editRole() {
        String sysRole = this.getParameter("sysRole");
        if (StringUtil.isEmpty(sysRole)) {
            this.success = false;
            this.message = "参数非法";
            return SUCCESS;
        }
        Map<String, Object> validMap = validRoleParams(sysRole);
        SysRole record = (SysRole) validMap.get("sysRole");
        String msg = (String) validMap.get("msg");
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            if (record.getRoleid() == null) {
                this.success = false;
                this.message = "角色编码不能为空";
                return SUCCESS;
            }

            if (Globals.ADMIN_ROLE_ID == record.getRoleid().intValue()) {
                this.success = false;
                this.message = "系统管理员角色为系统默认创建，不允许修改";
                return SUCCESS;
            }
            // 查询角色是否分配给用户
            SysAccountRoleExample example = new SysAccountRoleExample();
            SysAccountRoleExample.Criteria cr = example.createCriteria();
            cr.andRoleidEqualTo(record.getRoleid());
            List<SysAccountRoleKey> list = accountRoleService.selectAccountRoleByExample(example);
            if (list != null && list.size() > 0) {
                this.success = false;
                this.message = "该角色已经分配给用户,不允许修改";
                return SUCCESS;
            }
            if (accountRoleService.updateRoleByPKSelective(record) > 0) {
                WebUser user = getSessionWebUser();
                String content = JSONObject.fromObject(record, JsonUtil.returnJosnConfig(false, Date.class)).toString();
                content = "表名:SYS_ROLE;" + content;
                HostLogUtils.recordDocumentlog(user.getAccount(), LogOperatorEnum.MODIFY, getRemoteIP(), getActiveTabid(), content);
            }
        } else {
            this.success = false;
            this.message = msg;
        }
        return SUCCESS;
    }

    /**
     * 删除角色信息
     * 
     * @return
     */

    public String delRole() {
        String roleid = this.getParameter("roleid");
        if (!StringUtil.isDigits(roleid)) {
            this.success = false;
            this.message = "参数非法";
            return SUCCESS;
        }

        Short roleId = Short.valueOf(roleid);
        if (Globals.ADMIN_ROLE_ID == roleId.intValue()) {
            this.success = false;
            this.message = "系统管理员角色信息不允许删除";
            return SUCCESS;
        }
        SysRole sysRole = accountRoleService.selectRoleByPK(roleId);
        if (sysRole == null) {
            this.success = false;
            this.message = "角色不存在";
            return SUCCESS;
        }
        // 查询该角色是否分配给用户
        SysAccountRoleExample aremp = new SysAccountRoleExample();
        SysAccountRoleExample.Criteria cr = aremp.createCriteria();
        cr.andRoleidEqualTo(roleId);
        List<SysAccountRoleKey> accountRoleList = accountRoleService.selectAccountRoleByExample(aremp);
        if (accountRoleList != null && accountRoleList.size() > 0) {
            this.success = false;
            this.message = "系统管理员角色为系统默认创建，不允许删除";
            return SUCCESS;
        }
        if (accountRoleService.deleteRoleByPK(roleId) > 0) {
            WebUser user = getSessionWebUser();
            String content = "表名:SYS_ROLE;{roleid=" + roleId + "}";
            HostLogUtils.recordDocumentlog(user.getAccount(), LogOperatorEnum.MODIFY, getRemoteIP(), getActiveTabid(), content);
        }
        return SUCCESS;
    }

    /**
     * 载入权限树
     * 
     * @return
     */
    public String loadPermission() {
        String roleid = this.getParameter("roleid");
        if (StringUtil.isEmpty(roleid)) {
            this.success = false;
            this.message = "角色编码不能为空.";
            return SUCCESS;
        }

        WebUser user = getSessionWebUser();
        List<PermissinTreeNode> treeNodeList = accountRoleService.loadPermission(Short.valueOf(roleid), user, EsGlobals.DEFAULTSYSTEM);
        this.treeNodeList = treeNodeList;
        return SUCCESS;
    }

    /**
     * 保存权限分配
     * 
     * @return
     */
    public String savePermission() {
        String roleid = this.getParameter("roleid");
        String permission = this.getParameter("permission");
        if (StringUtil.isNotDigits(roleid)) {
            this.success = false;
            this.message = "角色编码不能为空";
            return SUCCESS;
        }
        if (Globals.ADMIN_ROLE_ID == Integer.valueOf(roleid)) {
            this.success = false;
            this.message = "系统管理员角色为系统默认创建，不允许修改";
            return SUCCESS;
        }

        List<String> idList = JSONArray.fromObject(permission);
        accountRoleService.updatePermission(Short.valueOf(roleid), idList, getLogInfo(), EsGlobals.DEFAULTSYSTEM);
        return SUCCESS;
    }

    private Map<String, Object> validRoleParams(String jsonObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = Globals.DEFAULT_MESSAGE;
        SysRole sysRole = this.JsonToBean(jsonObj, SysRole.class);
        if (StringUtil.isEmpty(sysRole.getDisc())) {
            msg = "角色名称不能为空";
        } else if (sysRole.getMemberlevel() == null) {
            msg = "会员等级不能为空";
        } else if (StringUtil.isEmpty(sysRole.getRemark())) {
            msg = "角色描述不能为空";
        }
        map.put("sysRole", sysRole);
        map.put("msg", msg);
        return map;

    }

    public List<SysRole> getSysRoleList() {
        return sysRoleList;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<PermissinTreeNode> getTreeNodeList() {
        return treeNodeList;
    }

    public void setAccountRoleService(AccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

}
