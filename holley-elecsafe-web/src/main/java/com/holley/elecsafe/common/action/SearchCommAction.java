package com.holley.elecsafe.common.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.holley.elecsafe.action.BaseAction;
import com.holley.elecsafe.common.util.CachedCityUtil;
import com.holley.elecsafe.model.def.EntInfo;
import com.holley.elecsafe.model.dic.DicCity;
import com.holley.elecsafe.service.common.SearchCommService;
import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.dataobject.Page;
import com.holley.platform.common.dataobject.TreeNode;
import com.holley.platform.common.util.StringUtil;
import com.holley.platform.model.def.WebUser;

public class SearchCommAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    @Resource
    private SearchCommService searchCommService;

    private List<DicCity>     provinceList;
    private List<DicCity>     cityList;
    private List<TreeNode>    citytree;
    private Page              page;

    /**
     * 根据省查询市信息
     * 
     * @return
     */
    public String queryCityByProvince() {
        String provinceid = this.getParameter("provinceid");
        if (StringUtil.isNotDigits(provinceid)) {
            this.success = false;
            this.message = "请先选择省";
            return SUCCESS;
        }
        this.cityList = CachedCityUtil.getCityByProvince(Integer.valueOf(provinceid));
        return SUCCESS;
    }

    /**
     * 区域树
     * 
     * @return
     */
    public String queryCityTree() {
        List<TreeNode> cityTree = CachedCityUtil.getTree();
        this.citytree = cityTree;
        return SUCCESS;
    }

    /**
     * 搜索企业
     * 
     * @return
     */
    public String searchEnterprise() {
        String keyword = this.getParameter("keyword");
        String provinceid = this.getParameter("provinceid");
        String cityid = this.getParameter("cityid");

        String pageindex = this.getParameter("pageindex");
        String pagelimit = this.getParameter("pagelimit");

        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(keyword)) {
            param.put("keyword", StringUtil.trim(keyword));
        }

        if (StringUtil.isNotDigits(provinceid, cityid, pageindex, pagelimit)) {
            return SUCCESS;
        }

        int provinceId = Integer.parseInt(provinceid);
        int cityId = Integer.parseInt(cityid);
        if (cityId > 0) {
            param.put("cityid", cityId);
        } else if (provinceId > 0) {
            param.put("cityid", provinceId);
        }

        Page page = this.returnPage(Integer.valueOf(pageindex), Integer.valueOf(pagelimit));
        param.put(Globals.PAGE, page);

        WebUser user = getSessionWebUser();
        param.put("eid", user.getDepartmentid());
        /*
         * if (AccountTypeEnum.PLATFORM.getValue() != user.getType()) { param.put("eid", user.getDepartmentid()); }
         */

        List<EntInfo> list = searchCommService.searchEnterpriseByPage(param);
        for (EntInfo e : list) {
            e.setCityname(CachedCityUtil.getCityNameById(e.getCity()));
        }
        page.setRoot(list);

        this.page = page;
        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<DicCity> getProvinceList() {
        return provinceList;
    }

    public List<DicCity> getCityList() {
        return cityList;
    }

    public List<TreeNode> getCitytree() {
        return citytree;
    }

    public Page getPage() {
        return page;
    }

}
