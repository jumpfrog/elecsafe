package com.holley.elecsafe.common.action;

import java.util.ArrayList;
import java.util.List;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.util.TreeHelper;
import com.holley.elecsafe.common.util.TreeUtil;
import com.holley.elecsafe.model.obj.ObjEnterprise;
import com.holley.elecsafe.service.common.TreeCommService;
import com.holley.elecsafe.service.obj.EntDevService;
import com.holley.platform.common.dataobject.TreeNode;
import com.holley.platform.model.def.EntBaseInfo;
import com.holley.platform.model.def.WebUser;

public class TreeCommAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private TreeCommService   treeCommService;
    private EntDevService     entDevService;
    private List<TreeNode>    treeNodes;

    /**
     * 一次性加载企业树<br>
     * 节点类型:企业节点
     * 
     * @return
     */
    public String loadEntTreeAll() {
        WebUser webUser = this.getSessionWebUser();
        Integer pid = webUser.getDepartmentid();
        List<EntBaseInfo> entLit = webUser.getPermission().getEnterprises();
        if (entLit == null || entLit.size() == 0) {
            entLit = treeCommService.selectChildEntSelf(pid);
        }
        List<TreeNode> nodeList = TreeHelper.makeEntTreeByEid(entLit, pid);
        this.treeNodes = nodeList;
        return SUCCESS;
    }

    /**
     * 异步加载企业树<br>
     * 节点类型:企业节点
     * 
     * @return
     */
    public String loadEntTree() {
        WebUser webUser = this.getSessionWebUser();
        Integer departmentid = webUser.getDepartmentid();
        String pid = this.getParameter("nodeid");
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        List<EntBaseInfo> entLit = null;
        if (pid == null) {
            if (departmentid < 0) {
                entLit = treeCommService.selectChildEnt(departmentid);
                nodeList = TreeHelper.makeEntTreeByPid(entLit, departmentid);
            } else {
                ObjEnterprise ent = entDevService.selectEnterpriseByPK(departmentid);
                TreeNode node = TreeUtil.convertEnterprise2TreeNode(ent);
                nodeList.add(node);
            }
        } else {
            entLit = treeCommService.selectChildEnt(Integer.valueOf(pid));
            nodeList = TreeHelper.makeEntTreeByPid(entLit, Integer.valueOf(pid));
        }
        this.treeNodes = nodeList;
        return SUCCESS;
    }

    public void setTreeCommService(TreeCommService treeCommService) {
        this.treeCommService = treeCommService;
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

    public List<TreeNode> getTreeNodes() {
        return treeNodes;
    }

}
