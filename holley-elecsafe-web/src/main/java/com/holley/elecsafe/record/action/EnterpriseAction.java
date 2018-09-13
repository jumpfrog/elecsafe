package com.holley.elecsafe.record.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import jxl.write.WriteException;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.JxlXlsUtil;
import com.holley.elecsafe.common.cache.ESCacheUtil;
import com.holley.elecsafe.common.constants.EnterpriseGradEnum;
import com.holley.elecsafe.common.constants.KeySessionTypeEnum;
import com.holley.elecsafe.common.constants.ObjectTypeEnum;
import com.holley.elecsafe.common.util.CachedBusinessTypeUtil;
import com.holley.elecsafe.common.util.CachedCityUtil;
import com.holley.elecsafe.common.util.TreeUtil;
import com.holley.elecsafe.model.def.Enterprise;
import com.holley.elecsafe.model.obj.ObjEnterprise;
import com.holley.elecsafe.model.obj.ObjEnterpriseExample;
import com.holley.elecsafe.service.obj.EntDevService;
import com.holley.platform.common.constants.AccountTypeEnum;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.dataobject.TreeNode;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.common.web.util.Validator;
import com.holley.platform.model.def.EntBaseInfo;
import com.holley.platform.model.def.WebUser;

/**
 * 企业信息相关action
 * 
 * @author tx
 */
public class EnterpriseAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private EntDevService     entDevService;
    private List<TreeNode>    cityTree;
    private List<TreeNode>    businessTree;
    private List<TreeNode>    treeNodes;
    private ObjEnterprise     objEnterprise;
    private List<Enterprise>  list;
    private TreeNode          treeNode;
    private TreeNode          newSelectNode;

    public String init() {
        this.getRequest().setAttribute("nodeid", this.getParameter("nodeid"));
        this.getRequest().setAttribute("nodetype", this.getParameter("nodetype"));
        this.getRequest().setAttribute("webUserType", getSessionWebUser().getType());
        this.getRequest().setAttribute("gradeType", EnterpriseGradEnum.values());
        return SUCCESS;
    }

    /**
     * 载入区域和行业树
     * 
     * @return
     */
    public String loadCityAndBusinessTree() {
        List<TreeNode> cityTree = CachedCityUtil.getTree();
        List<TreeNode> businessTree = CachedBusinessTypeUtil.getTree();
        this.cityTree = cityTree;
        this.businessTree = businessTree;
        return SUCCESS;
    }

    /**
     * 查询企业详细信息
     * 
     * @return
     * @throws IOException
     * @throws WriteException
     */
    public String queryEnterInfo() throws Exception {
        String id = this.getParameter("nodeid");
        ObjEnterprise objEnterprise = new ObjEnterprise();
        List<Enterprise> list = new ArrayList<Enterprise>();
        if (StringUtil.isDigits(id)) {
            objEnterprise = entDevService.selectEnterpriseByPK(Integer.valueOf(id));

            if (objEnterprise.getOwnerid() != -1) {
                // 不是顶级企业 查询父企业
                ObjEnterprise obj = entDevService.selectEnterpriseByPK(objEnterprise.getOwnerid());
                objEnterprise.setOwnername(obj.getDisc());
            }

            // 查询选中企业下的所有子公司信息
            list = entDevService.selectChildEidSelf(Integer.valueOf(id));
            for (Enterprise ent : list) {
                // 查询所属父企业信息
                ObjEnterprise obj = entDevService.selectEnterpriseByPK(ent.getOwnerid());
                ent.setOwnername(obj.getDisc());
                ent.setCityname(CachedCityUtil.getCityNameById(ent.getCity()));
                ent.setBusinessname(CachedBusinessTypeUtil.getBusinessNameById(ent.getBusiness()));
                if (ent.getGrade() != null) {
                    ent.setGradeStr(EnterpriseGradEnum.getText(ent.getGrade().intValue()));
                } else {
                    ent.setGradeStr("");
                }
            }
        }
        if (isExportExcel()) {
            String[] headsName = { "企业名称", "所属企业", "企业类别", "所属地区", "所属行业", "联系人", "联系电话" };
            String[] properiesName = { "disc", "ownername", "gradeStr", "cityname", "businessname", "linkman", "telephone" };
            JxlXlsUtil jxl = new JxlXlsUtil(this.getRequest(), this.getResponse());
            jxl.exportXLS(list, properiesName, headsName, Enterprise.class);
            return null;
        } else {
            this.list = list;
            this.objEnterprise = objEnterprise;
            return SUCCESS;
        }

    }

    /**
     * 保存企业信息
     * 
     * @return
     */
    public String saveEnt() {
        String eid = this.getParameter("eid");
        String enterprise = this.getParameter("objenterprise");
        Map<String, Object> validMap = validEnterpriseParams(enterprise);
        String msg = (String) validMap.get("msg");
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            ObjEnterprise record = (ObjEnterprise) validMap.get("record");
            // 检验企业是否存在
            ObjEnterprise objEnterprise = entDevService.selectEnterpriseByPK(Integer.valueOf(eid));
            if (objEnterprise == null) {
                this.success = false;
                this.message = "企业不存在";
                return SUCCESS;
            }
            ObjEnterpriseExample example = new ObjEnterpriseExample();
            ObjEnterpriseExample.Criteria cr = example.createCriteria();
            cr.andDiscEqualTo(record.getDisc());
            List<ObjEnterprise> list = entDevService.selectEnterpriseByExample(example);
            if (list != null && list.size() > 0) {
                for (ObjEnterprise obj : list) {
                    if (!Integer.valueOf(eid).equals(obj.getEid()) && record.getDisc().equals(obj.getDisc())) {
                        this.success = false;
                        this.message = "该企业名称已经存在";
                        return SUCCESS;
                    }
                }
            }
            record.setEid(Integer.valueOf(eid));
            // (查询自己及子企业) 判断所属企业，所属企业不能选择自己和子企业
            List<EntBaseInfo> enterprises = entDevService.selectGetChildEidSelf(Integer.valueOf(eid));
            for (EntBaseInfo entBaseInfo : enterprises) {
                if (record.getOwnerid() != null) {
                    if (record.getOwnerid().equals(entBaseInfo.getEid())) {
                        this.success = false;
                        this.message = "所属企业不能选择自己及子企业";
                        return SUCCESS;
                    }
                } else {
                    record.setOwnerid(getSessionWebUser().getDepartmentid());
                    record.setOwnertype(ObjectTypeEnum.OBJ_ENTERPRISE.getShortValue());
                    TreeNode treeNode;
                    treeNode = TreeUtil.convertEnterprise2TreeNode(record);
                    treeNode.children = new LinkedList<TreeNode>();
                    treeNode.children.add(TreeUtil.getDefaultNode());
                    this.newSelectNode = treeNode;
                }
            }
            entDevService.updateByPrimaryKeySelective(record, getLogInfo());
            this.objEnterprise = record;
        } else {
            this.success = false;
            this.message = msg;
        }
        return SUCCESS;
    }

    /**
     * 新增/修改企业信息初始化
     * 
     * @return
     */
    public String editInit() {
        String requestType = this.getParameter("requestType");
        String ownerid = this.getParameter("ownerid");
        if (StringUtil.isNotDigits(requestType)) {
            this.getRequest().setAttribute(Globals.MSG, Globals.PARAM_ERROR_MESSAGE);
            return MSG;
        }
        ObjEnterprise obj = new ObjEnterprise();
        if (StringUtil.isNotEmpty(ownerid)) {
            // 查询当前所选企业名称
            obj = entDevService.selectEnterpriseByPK(Integer.valueOf(ownerid));
            if (obj == null) {
                this.getRequest().setAttribute(Globals.MSG, "所选企业不存在");
                return MSG;
            }
        }
        int reqType = Integer.valueOf(requestType);
        ObjEnterprise record;
        WebUser user = getSessionWebUser();
        if (Globals.REQUEST_TYPE_ADD == reqType) {
            if (AccountTypeEnum.PLATFORM.getShortValue() == user.getType() && StringUtil.isEmpty(ownerid)) {
                record = new ObjEnterprise();
                record.setOwnerid(user.getDepartmentid());
                record.setOwnertype(ObjectTypeEnum.OBJ_ENTERPRISE.getShortValue());
            } else {
                record = new ObjEnterprise();
                record.setOwnerid(obj.getEid());
                record.setOwnertype(ObjectTypeEnum.OBJ_ENTERPRISE.getShortValue());
                record.setOwnername(obj.getDisc());
            }
        } else if (Globals.REQUEST_TYPE_EDIT == reqType) {
            String eid = this.getParameter("eid");
            if (StringUtil.isEmpty(eid)) {
                this.getRequest().setAttribute(Globals.MSG, Globals.PARAM_ERROR_MESSAGE);
                return MSG;
            }
            record = entDevService.selectEnterpriseByPK(Integer.valueOf(eid));
            if (record == null) {
                this.getRequest().setAttribute(Globals.MSG, "企业不存在");
                return MSG;
            }
            record.setOwnername(obj.getDisc());
        } else {
            this.getRequest().setAttribute(Globals.MSG, Globals.PARAM_ERROR_MESSAGE);
            return MSG;
        }
        this.objEnterprise = record;
        this.getRequest().setAttribute("requestType", reqType);
        this.getRequest().setAttribute("gradeType", EnterpriseGradEnum.values());
        return SUCCESS;
    }

    /**
     * 新增企业信息
     * 
     * @return
     */
    public String addEnterprise() {
        String enterprise = this.getParameter("enterprise");
        String selectedNode = this.getParameter("selectedNode");
        Map<String, Object> validMap = validEnterpriseParams(enterprise);
        String msg = (String) validMap.get("msg");
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            WebUser user = getSessionWebUser();
            ObjEnterprise record = (ObjEnterprise) validMap.get("record");

            if (record.getOwnerid() == null) {
                record.setOwnerid(user.getDepartmentid());
            }
            // 查询是否有相同的企业名称
            ObjEnterpriseExample example = new ObjEnterpriseExample();
            ObjEnterpriseExample.Criteria cr = example.createCriteria();
            cr.andDiscEqualTo(record.getDisc());
            List<ObjEnterprise> list = entDevService.selectEnterpriseByExample(example);
            if (list != null && list.size() > 0) {
                this.success = false;
                this.message = "该企业名称已经存在";
                return SUCCESS;
            }
            record.setOwnertype(ObjectTypeEnum.OBJ_ENTERPRISE.getShortValue());
            entDevService.insertEnterprise(record, getLogInfo());
            this.objEnterprise = record;
            // 如果没有选中节点并且添加的为子企业 查询父企业节点

            if (StringUtil.isEmpty(selectedNode) && record.getOwnerid() != -1) {
                ObjEnterprise obj = entDevService.selectEnterpriseByPK(record.getOwnerid());
                TreeNode treeNode;
                treeNode = TreeUtil.convertEnterprise2TreeNode(obj);
                treeNode.children = new LinkedList<TreeNode>();
                treeNode.children.add(TreeUtil.getDefaultNode());
                this.newSelectNode = treeNode;
            }
            if (StringUtil.isNotEmpty(selectedNode)) {
                if (!Integer.valueOf(selectedNode).equals(record.getOwnerid())) {
                    ObjEnterpriseExample example2 = new ObjEnterpriseExample();
                    ObjEnterpriseExample.Criteria cr2 = example2.createCriteria();
                    cr2.andEidEqualTo(record.getOwnerid());
                    List<ObjEnterprise> list2 = entDevService.selectEnterpriseByExample(example2);
                    for (ObjEnterprise objEnterprise : list2) {
                        TreeNode treeNode;
                        treeNode = TreeUtil.convertEnterprise2TreeNode(objEnterprise);
                        treeNode.children = new LinkedList<TreeNode>();
                        treeNode.children.add(TreeUtil.getDefaultNode());
                        this.newSelectNode = treeNode;
                    }
                }
            }
            TreeNode treeNode;
            // 增加树节点
            treeNode = TreeUtil.convertEnterprise2TreeNode(record);
            treeNode.children = new LinkedList<TreeNode>();
            treeNode.children.add(TreeUtil.getDefaultNode());
            this.treeNode = treeNode;
            // 重新加载数据范围
            List<EntBaseInfo> enterprises = entDevService.selectGetChildEidSelf(user.getDepartmentid());
            user.getPermission().setEnterprises(enterprises);
            // 用户登录信息存入缓存
            ESCacheUtil.setSession(user, KeySessionTypeEnum.ES_WEB);
        } else {
            this.success = false;
            this.message = msg;
        }
        return SUCCESS;
    }

    /**
     * 修改企业信息
     * 
     * @return
     */
    public String modifyEnterprise() {
        String enterprise = this.getParameter("enterprise");
        Map<String, Object> validMap = validEnterpriseParams(enterprise);
        String msg = (String) validMap.get("msg");
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            ObjEnterprise record = (ObjEnterprise) validMap.get("record");
            // 检验企业账号是否存在
            ObjEnterprise objEnterprise = entDevService.selectEnterpriseByPK(record.getEid());
            if (objEnterprise == null) {
                this.success = false;
                this.message = "企业不存在";
                return SUCCESS;
            }
            // (查询自己及子企业) 判断所属企业，所属企业不能选择自己和子企业
            List<EntBaseInfo> enterprises = entDevService.selectGetChildEidSelf(record.getEid());
            for (EntBaseInfo entBaseInfo : enterprises) {
                if (record.getOwnerid() != null) {
                    if (record.getOwnerid().equals(entBaseInfo.getEid())) {
                        this.success = false;
                        this.message = "所属企业不能选择自己及子企业";
                        return SUCCESS;
                    }
                } else {
                    record.setOwnerid(getSessionWebUser().getDepartmentid());
                    record.setOwnertype(ObjectTypeEnum.OBJ_ENTERPRISE.getShortValue());
                    TreeNode treeNode;
                    treeNode = TreeUtil.convertEnterprise2TreeNode(record);
                    treeNode.children = new LinkedList<TreeNode>();
                    treeNode.children.add(TreeUtil.getDefaultNode());
                    this.newSelectNode = treeNode;
                }
            }
            ObjEnterpriseExample example = new ObjEnterpriseExample();
            ObjEnterpriseExample.Criteria cr = example.createCriteria();
            cr.andDiscEqualTo(record.getDisc());
            List<ObjEnterprise> list = entDevService.selectEnterpriseByExample(example);
            if (list != null && list.size() > 0) {
                for (ObjEnterprise obj : list) {
                    if (!record.getEid().equals(obj.getEid()) && record.getDisc().equals(obj.getDisc())) {
                        this.success = false;
                        this.message = "该企业名称已经存在";
                        return SUCCESS;
                    }
                }
            }
            this.objEnterprise = record;
            entDevService.updateByPrimaryKeySelective(record, getLogInfo());
        }

        return SUCCESS;
    }

    /**
     * 删除企业
     * 
     * @return
     */
    public String delEnterprise() {
        String eid = this.getParameter("eid");
        if (StringUtil.isEmpty(eid)) {
            this.success = false;
            this.message = "参数非法";
        }
        ObjEnterprise ent = entDevService.selectEnterpriseByPK(Integer.valueOf(eid));
        if (ent == null) {
            this.success = false;
            this.message = "企业不存在";
            return SUCCESS;
        }
        String msg = entDevService.deleteEnterprise(Integer.valueOf(eid));
        if (ent.getOwnerid() == -1) {
            TreeNode treeNode;
            treeNode = TreeUtil.convertEnterprise2TreeNode(ent);
            treeNode.children = new LinkedList<TreeNode>();
            treeNode.children.add(TreeUtil.getDefaultNode());
            this.newSelectNode = treeNode;
        }
        if (Globals.DEFAULT_MESSAGE.equals(msg)) {
            WebUser user = getSessionWebUser();
            // 重新加载数据范围
            List<EntBaseInfo> enterprises = entDevService.selectGetChildEidSelf(user.getDepartmentid());
            user.getPermission().setEnterprises(enterprises);
            // 用户登录信息存入缓存
            ESCacheUtil.setSession(user, KeySessionTypeEnum.ES_WEB);
        } else {
            this.success = false;
            this.message = msg;
        }
        return SUCCESS;
    }

    /**
     * 校验企业信息
     * 
     * @param jsonObj
     * @return
     */
    private Map<String, Object> validEnterpriseParams(String jsonObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        String msg = Globals.DEFAULT_MESSAGE;
        ObjEnterprise ent = this.JsonToBean(jsonObj, ObjEnterprise.class);
        if (StringUtil.isEmpty(ent.getDisc())) {
            msg = "企业名称不能为空";
        } else if (ent.getBusiness() == null) {
            msg = "企业所属行业不能为空";
        } else if (StringUtil.isEmpty(ent.getLinkman())) {
            msg = "企业联系人不能为空";
        } else if (StringUtil.isNotEmpty(ent.getTelephone()) && !(Validator.isMobile(ent.getTelephone()) || Validator.isTelephone(ent.getTelephone()))) {
            msg = "请正确输入电话/手机号码";
        }

        map.put("record", ent);
        map.put("msg", msg);
        return map;
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

    public List<TreeNode> getCityTree() {
        return cityTree;
    }

    public List<TreeNode> getBusinessTree() {
        return businessTree;
    }

    public List<TreeNode> getTreeNodes() {
        return treeNodes;
    }

    public ObjEnterprise getObjEnterprise() {
        return objEnterprise;
    }

    public List<Enterprise> getList() {
        return list;
    }

    public TreeNode getTreeNode() {
        return treeNode;
    }

    public TreeNode getNewSelectNode() {
        return newSelectNode;
    }

}
